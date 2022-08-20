package com.channelsoft.slee.callagent.ccod;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import Ice.IntHolder;

import com.channelsoft.CCODServices.ADIsWriteSDR;
import com.channelsoft.CCODServices.AccessTime;
import com.channelsoft.CCODServices.AccountID;
import com.channelsoft.CCODServices.CCODResultT;
import com.channelsoft.CCODServices.CMSInterfacePrx;
import com.channelsoft.CCODServices.ConnectionStateT;
import com.channelsoft.CCODServices.ElemT;
import com.channelsoft.CCODServices.EncodingT;
import com.channelsoft.CCODServices.FinishedSDR;
import com.channelsoft.CCODServices.MediaDirectionT;
import com.channelsoft.CCODServices.MediaEventCauseT;
import com.channelsoft.CCODServices.MediaEventT;
import com.channelsoft.CCODServices.MediaEventTypeT;
import com.channelsoft.CCODServices.PlayListElemT;
import com.channelsoft.CCODServices.ServiceDataTHolder;
import com.channelsoft.CCODServices.ServiceInfoT;
import com.channelsoft.CCODServices.ServiceTypeT;
import com.channelsoft.CCODServices.SignalEventT;
import com.channelsoft.CCODServices.SignalEventTypeT;
import com.channelsoft.CCODServices.SignalFailedReasonT;
import com.channelsoft.CCODServices.StartTime;
import com.channelsoft.CCODServices.TTSVoiceLibT;
import com.channelsoft.DDSSpace.IvrCallEventT;
import com.channelsoft.DDSSpace.IvrEventReasonT;
import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.billing.BillingDataMgThread;
import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.channelmanager.AtsSGEvent;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class CallAgentImpl implements CallAgent
{
	/**
	 * CMS接口的服务器线程。
	 */
	class SleeServer extends Thread
	{

		Ice.Communicator server = null;

		public SleeServer(Ice.Communicator server)
		{
			this.server = server;
		}

		@Override
		public void run()
		{
			setName("CallAgent4CCOD SleeServer");

			server.waitForShutdown();
		}
	}

	/**
	 * CallId到CallAgentImpl实例的映射关系。
	 */
	static Map<Integer, CallAgentImpl> callAgents = Collections
			.synchronizedMap(new HashMap<Integer, CallAgentImpl>());

	public static Map<Integer, Long> callSn2SessionId = Collections
			.synchronizedMap(new HashMap<Integer, Long>());

	static int callSnSeed = 0;

	public static CMSInterfacePrx ccodProxy;

	static CMSMessageProcessor cmsmp = new CMSMessageProcessor();

	static String cmsName;

	/**
	 * CMS-本机的时间差，以秒为单位。
	 */
	static int cmsTimeOffset = 0;

	static DDSClient ddsClient;

	// 假事件的ResId
	static final int DUMMY_RES_ID = -2;

	/**
	 * 最大的callId的值。
	 */
	static final int MAX_CALLSN = 800000;

	/**
	 * 最大允许的未完成呼叫数量。正常情况下应该与单机容量相同。
	 */
	static final int MAX_PENDING_CALL = 10000;

	/**
	 * CCOD中，IVR固定的ServiceId。
	 */
	static String serviceId;

	static Map<Long, CallIdsInSession> sessionId2Callsn = Collections
			.synchronizedMap(new HashMap<Long, CallIdsInSession>());

	/**
	 * 要发送给ChannelManager的信令事件队列。
	 */
	static LinkedBlockingQueue<AtsSGEvent> sgEventQueue = new LinkedBlockingQueue<AtsSGEvent>();

	static String sleeServiceName;

	static private ISysCfgData sysCfgData;

	static void dispatchMessage(CallAgentMessage msg) throws Exception
	{
		switch (msg.messageType)
		{
		case Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT:
		{
			SGEvent event = ((SGEventMessage) msg).sgEvent;
			CallAgentImpl agent = getCallAgent(event.CallID);

			switch (event.EventID)
			{
			case Constant.SG_INCOMINGCALL:
			{
				AtsSGEvent sgEvent = new AtsSGEvent();
				sgEvent.callSN = event.CallID;
				sgEvent.eventID = Constant.Event_InboundCall;
				sgEvent.port = event.ResID;

				if (agent != null)
				{
					agent.onIncomingCall(event);
				}

				sgEventQueue.add(sgEvent);

				if (agent != null)
				{
					CallAgentImpl.ddsClient.addEvent(agent.sessionId,
							((SGEventMessage) msg).timestamp,
							agent.enterpriseId, event.CallerID, event.CalledID,
							IvrCallEventT._TIvrAlerting,
							IvrEventReasonT._TIvrResNormal);
				}
				return;
			}

			case Constant.SG_CALLDISCONNECTED:
			case Constant.SG_CALLRELEASED:
			case Constant.SG_LINE_START:
			case Constant.SG_LINE_DOWN:
			case Constant.SG_LINE_IN_SERVICE:
			case Constant.SG_LINE_OUT_SERVICE:
			{
				AtsSGEvent sgEvent = new AtsSGEvent();
				sgEvent.callSN = event.CallID;
				sgEvent.eventID = Constant.Event_Disconnect;
				sgEvent.port = event.ResID;

				boolean isHostAgent = ((agent != null) && (agent.hostAgent == null));

				if ((agent != null) && (agent.callId == event.CallID))
				{
					agent.onDisconnect();
				}

				sgEventQueue.add(sgEvent);

				if (agent != null)
				{
					if (isHostAgent)
					{
						CallAgentImpl.ddsClient
								.addEvent(
										agent.sessionId,
										((SGEventMessage) msg).timestamp,
										agent.enterpriseId,
										agent.ani,
										agent.dnis,
										IvrCallEventT._TIvrReleased,
										(sgEvent.port == DUMMY_RES_ID ? IvrEventReasonT._TIvrResTransfer
												: IvrEventReasonT._TIvrResRemoteRelease));

					}

					agent.clearSession();

					agent.mgMsgQueue.add(msg);

					if ((sgEvent.port != DUMMY_RES_ID)
							&& (agent.startTimestamp != 0))
					{
						// 不是假挂机，而且应答成功。这时需要写SDR。
						BillingDataMgThread.instance()
								.bill(
										agent.ani.substring(agent.ani
												.indexOf(':') + 1),
										agent.dnis.substring(agent.dnis
												.indexOf(':') + 1),
										agent.oriAni,
										agent.oriDnis,
										agent.accountId
												.substring(agent.accountId
														.indexOf(':') + 1),
										agent.accessTimestamp,
										agent.startTimestamp,
										((SGEventMessage) msg).timestamp,
										serviceId, agent.enterpriseId);
					}
				}
			}

				break;

			case Constant.SG_CALLCONNECTED:
			{
				if (agent != null)
				{
					agent.startTimestamp = ((SGEventMessage) msg).timestamp;
					if (agent.accessTimestamp == 0)
					{
						// 有时候会丢失振铃事件。
						agent.accessTimestamp = agent.startTimestamp;
					}
				}

				boolean isHostAgent = ((agent != null) && (agent.hostAgent == null));

				if (isHostAgent)
				{
					CallAgentImpl.ddsClient.addEvent(agent.sessionId,
							((SGEventMessage) msg).timestamp,
							agent.enterpriseId,
							((SGEventMessage) msg).sgEvent.CallerID,
							((SGEventMessage) msg).sgEvent.CalledID,
							IvrCallEventT._TIvrConnected,
							IvrEventReasonT._TIvrResNormal);
				}
			}

				break;

			case Constant.SG_CALLALERTING:
			{
				if (agent != null)
				{
					agent.accessTimestamp = ((SGEventMessage) msg).timestamp;
				}

				boolean isHostAgent = ((agent != null) && (agent.hostAgent == null));

				if (isHostAgent)
				{
					CallAgentImpl.ddsClient.addEvent(agent.sessionId,
							((SGEventMessage) msg).timestamp,
							agent.enterpriseId,
							((SGEventMessage) msg).sgEvent.CallerID,
							((SGEventMessage) msg).sgEvent.CalledID,
							IvrCallEventT._TIvrConnected,
							IvrEventReasonT._TIvrResNormal);

				}
			}

				break;
			}

			if (agent != null)
			{
				agent.sgMsgQueue.add(msg);
			}

			break;
		}

		case Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT:
		{
			MGEvent event = ((MGEventMessage) msg).mgEvent;
			CallAgentImpl agent = getCallAgent(event.CallID);

			if ((agent != null) && (agent.callId == event.CallID))
			{
				agent.mgMsgQueue.add(msg);

				if (agent.isPlayingVoiceAsynch
						&& (event.EventID == Constant.MG_TDX_PLAY)
						&& (translateMGReason(event.Reason) == Constant.ERR_Success)
						&& (event.Reason != Constant.MGREASON_TM_USRSTOP))
				{
					// 普通的放音认为StopIO是正常结束，异步放音则以此作为终止条件
					int result;
					try
					{
						result = turnCCODResultT2SleeResult(ccodProxy.PlayFile(
								agent.sessionId, agent.mepId, "file:///"
										+ agent.playVoiceFileName,
								agent.playVoiceRate,
								agent.playVoiceInterruptKeys));
					}
					catch (Throwable e)
					{
						Log.wException(LogDef.id_0x10150001, e);
						result = Constant.ERR_RPC;
					}
					if (result != Constant.GATEWAY_SUCCESS)
					{
						agent.isPlayingVoiceAsynch = false;
					}
				}
			}
		}
			break;

		}
	}

	/**
	 * 通过指定的callId查找CallAgent
	 * 
	 * @param callId
	 * @return 找到则返回CallAgent，否则返回null。
	 */
	public static synchronized CallAgentImpl getCallAgent(int callId)
	{
		if (callId < 0)
		{
			return null;
		}

		return callAgents.get(callId);
	}

	static CallAgentImpl getCallAgentFromSessionId(Long sessionId,
			int connectionId)
	{
		CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return null;
		}

		int destCallId = callIds.getCallIdFromConnectionId(connectionId);
		if (destCallId == -1)
		{
			return null;
		}

		CallAgentImpl agent = getCallAgent(destCallId);

		return agent;
	}

	static CallAgentImpl getCallAgentFromSessionIdMepId(Long sessionId,
			int mepId)
	{
		CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return null;
		}

		int destCallId = callIds.getCallIdFromMepId(mepId);
		if (destCallId == -1)
		{
			return null;
		}

		CallAgentImpl agent = getCallAgent(destCallId);

		return agent;
	}

	/**
	 * @return the cmsName
	 */
	public static String getCmsName()
	{
		return cmsName;
	}

	/**
	 * 生成新的呼叫代理对象的实例。
	 * 
	 * @param sessionId
	 * @param connectionId
	 * @param enterpriseId
	 * @param accoundId
	 * @param timestamp
	 *            呼叫到达的统一时间戳，由CMS传输的值为准。
	 * @param isHostAgent
	 *            是否为主呼叫方。
	 * @return
	 */
	synchronized static CallAgentImpl getNewCallAgent(long sessionId,
			int connectionId, String enterpriseId, String accoundId,
			int timestamp, boolean isOldCall, boolean isHostAgent)
	{
		// 如果未完成的呼叫太多，为了防止内存不足，丢弃当前所有呼叫
		if (callAgents.size() > MAX_PENDING_CALL)
		{
			onDisconnectFromServer();
		}

		CallAgentImpl agent = new CallAgentImpl();
		agent.accountId = accoundId;
		agent.sessionId = sessionId;
		agent.connectionId = connectionId;
		agent.enterpriseId = enterpriseId;
		agent.accessTimestamp = timestamp;
		agent.isOldCall = isOldCall;

		agent.callId = callSnSeed++;
		if (callSnSeed > MAX_CALLSN)
		{
			callSnSeed = 0;
		}

		callAgents.put(agent.callId, agent);
		callSn2SessionId.put(agent.callId, sessionId);

		CallIdsInSession callIds = null;

		if (isHostAgent)
		{
			callIds = new CallIdsInSession();
			callIds.callId = agent.callId;
			callIds.connectionId = agent.connectionId;
			sessionId2Callsn.put(sessionId, callIds);
		}
		else
		{
			callIds = sessionId2Callsn.get(sessionId);
			if (callIds == null)
			{
				Log.wDebug(LogDef.id_0x10050000_10, agent.sessionId,
						agent.connectionId, agent.callId, callSn2SessionId
								.size());

				agent.clearSession();
				return null;
			}

			callIds.otherCallId = agent.callId;
			callIds.otherConnectionId = agent.connectionId;
		}

		Log.wDebug(LogDef.id_0x10050000_11, agent.sessionId,
				agent.connectionId, agent.callId, callSn2SessionId.size(),
				sessionId2Callsn.size());

		return agent;
	}

	/**
	 * @return the sleeServiceName
	 */
	static public String getSleeServiceName()
	{
		return sleeServiceName;
	}

	/**
	 * 容错。清除所有未完成的呼叫。
	 */
	static void onDisconnectFromServer()
	{
		Log.wError(LogDef.id_0x10150008, callSn2SessionId.size());

		synchronized (callAgents)
		{
			for (CallAgentImpl agent : callAgents.values())
			{
				if ((agent.confId != -1) && (ccodProxy != null))
				{
					ccodProxy.DestroyConference(agent.sessionId, agent.confId);
					agent.confId = -1;
				}
			}
		}

		callAgents.clear();

		callSn2SessionId.clear();
		sessionId2Callsn.clear();
	}

	public static void onMediaEventT(MediaEventT event)
	{
		cmsmp.msgQueue.add(new CMSMessage(event));
	}

	public static void onSignalEventT(SignalEventT event)
	{
		cmsmp.msgQueue.add(new CMSMessage(event));
	}

	static void onStartService(ServiceInfoT serviceInfo) throws Exception
	{
		cmsTimeOffset = serviceInfo.timeStamp
				- (int) (System.currentTimeMillis() / 1000);
		cmsmp.msgQueue.add(new CMSMessage(serviceInfo));
	}

	static void setCCODProxy(CMSInterfacePrx proxy, String cmsName)
	{
		ccodProxy = proxy;
		CallAgentImpl.cmsName = cmsName;
	}

	static int translateAsrReason(int reason)
	{
		switch (reason)
		{
		case Constant.MGREASON_ASR_RECGNIZED:
			return Constant.ERR_Success;

		case Constant.MGREASON_ASR_NO_SPEECH:
			return Constant.ERR_TimeOut;

		case Constant.MGREASON_ASR_TOOMUCH_SPEECH:
			return Constant.ERR_ASR_TooMuchSpeech;

		case Constant.MGREASON_ASR_REC_SLOW:
			return Constant.ERR_TimeOut;

		case Constant.MGREASON_ASR_SPEECH_TOO_EARLY:
			return Constant.ERR_ASR_SpeechTooEarly;

		case Constant.MGREASON_ASR_REJECT:
			return Constant.ERR_ASR_Reject;

		case Constant.MGREASON_ASR_MEMRECORD:
			return Constant.ERR_ASR_MemRecord;

		case Constant.MGREASON_ARS_PLAYPROMPT:
			return Constant.ERR_ASR_PlayPrompt;

		default:
			return Constant.ERR_GeneralError;
		}
	}

	static int translateMGEvent(int eventId, int reason)
	{
		switch (eventId)
		{
		case Constant.MG_TFX_SEND:
		case Constant.MG_TFX_RECV:
			return Constant.ERR_Success;

		case Constant.MG_TDX_PLAY:
		case Constant.MG_TDX_RECORD:
		case Constant.MG_TDX_GETDIGIT:
		case Constant.MG_TFX_FAIL:
		case Constant.MG_FAIL:
			return translateMGReason(reason);

		default:
			return Constant.ERR_GeneralError;
		}
	}

	static int translateMGReason(int reason)
	{
		switch (reason)
		{
		case Constant.MGREASON_TM_INVALIDFILE:
			return Constant.ERR_InvalidFile;

		case Constant.MGREASON_TM_MAXDTMF:
		case Constant.MGREASON_TM_DIGIT:
		case Constant.MGREASON_TM_EOD:
		case Constant.MGREASON_ASR_RECGNIZED:
			return Constant.ERR_Success;

		case Constant.MGREASON_TM_DISCONNECT:
			return Constant.ERR_Disconnect;
		case Constant.MGREASON_TM_USRSTOP:
			return Constant.ERR_Success;

		case Constant.MGREASON_TM_MAXTIME:
		case Constant.MGREASON_ASR_NO_SPEECH:
			return Constant.ERR_TimeOut;

		case Constant.MGREASON_ASR_TOOMUCH_SPEECH:
			return Constant.ERR_ASR_TooMuchSpeech;

		case Constant.MGREASON_ASR_REC_SLOW:
			return Constant.ERR_ASR_RecSlow;

		case Constant.MGREASON_ASR_MEMRECORD:
			return Constant.ERR_ASR_MemRecord;

		case Constant.MGREASON_ARS_PLAYPROMPT:
			return Constant.ERR_ASR_PlayPrompt;

		case Constant.MGREASON_ASR_SPEECH_TOO_EARLY:
			return Constant.ERR_ASR_SpeechTooEarly;

		case Constant.MGREASON_ASR_REJECT:
			return Constant.ERR_ASR_Reject;

		default:
			return Constant.ERR_GeneralError;
		}
	}

	static int translateSGReason(int reason)
	{
		switch (reason)
		{
		case Constant.REASON_DESTBUSY:
			return Constant.ERR_DestBusy;
		case Constant.REASON_DESTNOANSWER:
			return Constant.ERR_NoAnswer;
		case Constant.REASON_INVLAIDNUM:
			return Constant.ERR_InvalidTelNum;
		case Constant.REASON_VOICE_DETECTED:
			return Constant.ERR_Connect_Voice;
		case Constant.REASON_POWER_OFF:
			return Constant.ERR_TerminalPowerOff;
		case Constant.REASON_OUT_AREA:
			return Constant.ERR_OutOfService;
		case Constant.REASON_NUMBER_NULL:
			return Constant.ERR_NothingTelNum;
		case Constant.REASON_REFUSE:
			return Constant.ERR_TerminalRefuse;
		default:
			return Constant.ERR_GeneralError;
		}
	}

	static int turnCCODResultT2SleeResult(CCODResultT ccodR)
	{
		switch (ccodR)
		{
		case ResSuccess:
			return Constant.GATEWAY_SUCCESS;
		case ResConnIDNotExist:
		case ResInvalidState:
		case ResNotAttachedWithMedia:
		case ResSessionIDNotExist:
			return Constant.ERR_NoCall;
		case ResInvalidUri:
			return Constant.ERR_InvalidFile;
		case ResMepIDNotExist:
			return Constant.ERR_INVALID_RESID;
		case ResNotEnoughResource:
			return Constant.ERR_RES_ALREADYOPEN;

		default:
			return Constant.ERR_GeneralError;

		}
	}

	/**
	 * 转换CCOD到SLEE的媒体事件ID
	 * 
	 * @param event
	 * @return
	 */
	static int turnMediaEventTypeT2MGEventID(MediaEventTypeT event)
	{
		switch (event)
		{
		case MgEvtFailed:
		{
			return Constant.MG_FAIL;
		}
		case MgEvtFaxFailed:
		{
			return Constant.MG_TFX_FAIL;
		}
		case MgEvtGotDtmf:
		{
			return Constant.MG_TDX_GETDIGIT;
		}
		case MgEvtPlayEnd:
		{
			return Constant.MG_TDX_PLAY;
		}
		case MgEvtRecordEnd:
		{
			return Constant.MG_TDX_RECORD;
		}
		case MgEvtRecvFaxEnd:
		{
			return Constant.MG_TFX_RECV;
		}
		case MgEvtSendFaxEnd:
		{
			return Constant.MG_TFX_SEND;
		}
		default:
		{
			return Constant.MG_SUCCESS;
		}
		}
	}

	/**
	 * 转换CCOD到SLEE的媒体事件原因
	 * 
	 * @param event
	 * @return
	 */
	static int turnMediaEventTypeT2MGEventReason(MediaEventCauseT event)
	{
		switch (event)
		{
		case MecTmDigit:
		{
			return Constant.MGREASON_TM_DIGIT;
		}
		case MecTmDisconnect:
		{
			return Constant.MGREASON_TM_DISCONNECT;
		}
		case MecTmEod:
		{
			return Constant.MGREASON_TM_EOD;
		}
		case MecTmInvalidFile:
		{
			return Constant.MGREASON_TM_INVALIDFILE;
		}
		case MecTmMaxDtmf:
		{
			return Constant.MGREASON_TM_MAXDTMF;
		}
		case MecTmMaxTime:
		{
			return Constant.MGREASON_TM_MAXTIME;
		}
		case MecTmUserStop:
		{
			return Constant.MGREASON_TM_USRSTOP;
		}
		default:
		{
			return Constant.ERR_GeneralError;
		}
		}
	}

	static int turnSignalEventTypeT2SGEventID(SignalEventTypeT sett)
	{
		switch (sett)
		{
		case SgEvtConnected:
		{
			return Constant.SG_CALLCONNECTED;
		}
		case SgEvtDisconnected:
		{
			return Constant.SG_CALLDISCONNECTED;
		}
		case SgEvtFailed:
		{
			return Constant.SG_CALLDISCONNECTED;
		}
		case SgEvtLocalAlerting:
		{
			return Constant.SG_CALLALERTING;
		}
		case SgEvtRemoteAlerting:
		{
			return Constant.SG_CALLALERTING;
		}
		default:
		{
			return Constant.ERR_GeneralError;
		}
		}
	}

	/**
	 * CCOD到SLEE信令事件原因的映射
	 * 
	 * @param sfrt
	 * @return
	 */
	static int turnSignalFailedReasonT2SGEventData(SignalFailedReasonT sfrt)
	{
		switch (sfrt)
		{
		case ReasonCallDestBusy:
		{
			return Constant.REASON_DESTBUSY;
		}
		case ReasonCallDestFaxtone:
		{
			return Constant.REASON_DESTFAXTONE;
		}
		case ReasonCallDestInvalid:
		{
			return Constant.REASON_INVLAIDNUM;
		}
		case ReasonCallDestNotAnswer:
		{
			return Constant.REASON_DESTNOANSWER;
		}
		case ReasonCallDestNotFax:
		{
			return Constant.REASON_DESTNOTFAX;
		}
		case ReasonCallDestUnknown:
		{
			return Constant.REASON_DESTUNKNOWN;
		}
		case ReasonSuccess:
		{
			return Constant.REASON_DESTNOTFAX;
		}
		default:
		{
			return Constant.REASON_DESTUNKNOWN;
		}
		}
	}

	/**
	 * 呼叫到达的统一时间戳。只保存传过来的数据，不标志呼叫。
	 */
	int accessTimestamp = 0;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	String accountId;

	String ani;

	/**
	 * 只使用自己维护的callId标志呼叫，不使用resId等。
	 */
	int callId = -1;

	int callState = Constant.StatusIdle;

	int cmsServerPort;

	/**
	 * 单步会议时创建的会议ID
	 */
	int confId = -1;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	int connectionId = -1;

	String dnis;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	String enterpriseId;

	String glsConnection;

	/**
	 * 三方呼叫中，本方呼叫的宿主呼叫。
	 */
	CallAgentImpl hostAgent = null;

	/**
	 * 当前电话是由UCDS转过来的的
	 */
	boolean isOldCall = false;

	boolean isPlayingVoiceAsynch = false;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	int mepId = -1;

	public LinkedBlockingQueue<CallAgentMessage> mgMsgQueue = new LinkedBlockingQueue<CallAgentMessage>();

	String oriAni;

	String oriDnis;

	CallAgentImpl otherPartyAgent = null;

	String playVoiceFileName;

	String playVoiceInterruptKeys;

	int playVoiceRate;

	String reserved;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	long sessionId = -1l;

	public LinkedBlockingQueue<CallAgentMessage> sgMsgQueue = new LinkedBlockingQueue<CallAgentMessage>();

	/**
	 * 呼叫接通的统一时间戳。只保存传过来的数据，不标志呼叫。
	 */
	int startTimestamp = 0;

	String statFileDir;

	int answerCall(int callId, int timeout)
	{
		try
		{
			CallAgentMessage msg = null;
			long startMills = System.currentTimeMillis();

			while (true)
			{
				long millisToWait = timeout
						- (System.currentTimeMillis() - startMills);
				if (millisToWait <= 0)
				{
					return Constant.ERR_TimeOut;
				}

				msg = sgMsgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);
				if (msg == null)
				{
					return Constant.ERR_TimeOut;
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent event = ((SGEventMessage) msg).sgEvent;
					Log.wDebug(LogDef.id_0x10050023, -1, callId, event.ResID,
							event.CallID, event.EventID);

					switch (event.EventID)
					{
					case Constant.SG_CALLCONNECTED:
						return Constant.ERR_Success;

					case Constant.SG_CALLDISCONNECTED:
						return translateSGReason(event.EventData);

					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_GeneralError;
					default:
						continue;
					}
				}
			}
		}
		catch (Exception e)
		{
			return Constant.ERR_RPC;
		}
	}

	public int atsAdjustSpeed(int port, int callId, int adjment)
	{
		return Constant.ERR_GeneralError;
	}

	public int atsAdjustVolume(int port, int callId, int adjment)
	{
		return Constant.ERR_GeneralError;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsAnswerCall(int, int, int)
	 */
	public int atsAnswerCall(int port, int callId, int reserved)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		if (agent.isOldCall)
		{
			// 如果转移前没有记过话单，则当前呼叫的时间使用随路数据中的原始呼叫时间，否则两个时间全部使用接口中的时间。
			ServiceDataTHolder serviceData = new ServiceDataTHolder();
			ccodProxy.GetServiceData(agent.sessionId, EncodingT.UTF8,
					serviceData);
			Log.wDebug(LogDef.id_0x10050000_7, agent.sessionId,
					serviceData.value.toString());

			if (FinishedSDR.value.equals(serviceData.value
					.get(ADIsWriteSDR.value)))
			{
				// 明确声明写过话单。
				agent.startTimestamp = agent.accessTimestamp;
			}
			else
			{
				// 随路数据中必须有时间。
				String accessStr = serviceData.value.get(AccessTime.value);
				String startStr = serviceData.value.get(StartTime.value);

				if ((accessStr != null) && !"".equals(accessStr))
				{
					agent.accessTimestamp = Integer.parseInt(accessStr);
				}
				else
				{
					agent.accessTimestamp = 0;
				}

				if ((startStr != null) && !"".equals(startStr))
				{
					agent.startTimestamp = Integer.parseInt(startStr);
				}
				else
				{
					agent.startTimestamp = 0;
				}
			}

		}

		int result;
		IntHolder mepID = new IntHolder();

		CMSInterfacePrx ccodProxyOld = ccodProxy;

		try
		{
			if (agent.isOldCall)
			{
				result = agent.createMedia();
				mepID.value = agent.mepId;
			}
			else
			{
				result = turnCCODResultT2SleeResult(ccodProxy.Accept(
						agent.sessionId, agent.connectionId, mepID));

				if (result != Constant.GATEWAY_SUCCESS)
				{
					CallAgentImpl.ddsClient.addEvent(agent.sessionId,
							(int) (System.currentTimeMillis() / 1000)
									+ CallAgentImpl.cmsTimeOffset,
							agent.enterpriseId, agent.ani, agent.dnis,
							IvrCallEventT._TIvrReleased,
							IvrEventReasonT._TIvrResError);
				}
			}
		}
		catch (Ice.SocketException se)
		{
			if (ccodProxy == ccodProxyOld)
			{
				Log.wError(LogDef.id_0x10150003, callId, agent.sessionId);
				ccodProxy = null;
				onDisconnectFromServer();
			}
			else
			{
				Log.wError(LogDef.id_0x10150004, callId, agent.sessionId);
			}

			agent.clearSession();

			return Constant.ERR_RPC;
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		if (result != Constant.GATEWAY_SUCCESS)
		{
			Log.wError(LogDef.id_0x10150005, callId, agent.sessionId, result);

			agent.clearSession();

			return result;
		}
		else
		{
			Log.wDebug(LogDef.id_0x10050032, callId, agent.sessionId,
					mepID.value);
		}

		agent.mepId = mepID.value;
		CallIdsInSession callIds = sessionId2Callsn.get(agent.sessionId);
		if (callIds != null)
		{
			callIds.mepId = agent.mepId;
		}

		if (!agent.isOldCall)
		{
			int timeout = 30 * 1000;

			result = agent.answerCall(callId, timeout);

			if (result != Constant.ERR_Success)
			{
				agent.clearSession();
			}
		}
		else
		{
			result = Constant.ERR_Success;

			// 无法通过发一个假接通事件来制造监控消息，所以直接发消息。
			// ddsClient.addEvent(agent.sessionId, agent.startTimestamp,
			// agent.enterpriseId, agent.ani, agent.dnis,
			// IvrCallEventT._TIvrConnected,
			// IvrEventReasonT._TIvrResTransfer);
		}

		return result;
	}

	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord)
	{
		return Constant.ERR_GeneralError;
	}

	public int atsASR2(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord, int DTMF_dtmfCount,
			String DTMF_endFlag, int DTMF_timeoutSecond, int DTMF_betweenTimeout)
	{
		return Constant.ERR_GeneralError;
	}

	public int atsClearDTMFBuffer(int port, int callId)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.createMedia();

		try
		{
			int result = turnCCODResultT2SleeResult(ccodProxy.FlushDtmfBuffer(
					agent.sessionId, agent.mepId));
			return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
					: result);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			return Constant.ERR_RPC;
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsCloseConf()
	 */
	public int atsCloseConf(int port, int callId)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		if (agent.confId < 0)
		{
			return Constant.ERR_Success;
		}

		int result;

		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.DestroyConference(
					agent.sessionId, agent.confId));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		agent.confId = -1;

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	public int atsDisSwitchTwoPort(int port1, int callId1, int port2,
			int reserved)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = null;
		agent = checkCallState(callId1);
		if ((agent == null) && (checkCallState(port2) == null))
		{
			return Constant.ERR_NoCall;
		}

		if (agent == null)
		{
			agent = getCallAgent(callId1);
			if (agent == null)
			{
				return Constant.ERR_NoCall;
			}
		}

		CallAgentImpl agent2 = null;
		if (port2 == -1)
		{
			if ((agent.callState == Constant.StatusConference)
					&& (agent.otherPartyAgent != null)
					&& (agent.otherPartyAgent.callState == Constant.StatusConnect))
			{
				agent2 = agent.otherPartyAgent;
			}
			else
			{
				return Constant.ERR_NoCall;
			}
		}
		else
		{
			agent2 = checkCallState(port2);
			if (agent2 == null)
			{
				return Constant.ERR_NoCall;
			}
		}

		int result;

		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.Unjoin(
					agent.sessionId, agent.connectionId, agent2.connectionId));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	public int atsGetCallInfo(int port, int callId, StringWrapper ani,
			StringWrapper dnis, StringWrapper oriAni, StringWrapper oriDnis,
			StringWrapper reserved, BooleanWrapper isVideoCall)
	{
		ani.value = "";
		dnis.value = "";
		oriAni.value = "";
		oriDnis.value = "";
		reserved.value = "";

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		ani.value = agent.ani;
		dnis.value = agent.dnis;
		oriAni.value = agent.oriAni;
		oriDnis.value = agent.oriDnis;
		reserved.value = agent.reserved;

		return Constant.ERR_Success;

	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsGetOtherPartyCallSn(int, int)
	 */
	public int atsGetOtherPartyCallSn(int port, int callId)
	{
		CallAgentImpl agent = checkCallState(callId);
		if ((agent == null) || (agent.otherPartyAgent == null))
		{
			return -1;
		}

		return agent.otherPartyAgent.callId;
	}

	public int atsGetOtherPartyTrunkID(int port, int callId)
	{
		CallAgentImpl agent = checkCallState(callId);
		if ((agent == null) || (agent.otherPartyAgent == null))
		{
			return -1;
		}

		return agent.otherPartyAgent.callId;
	}

	public AtsSGEvent atsGetSGEvent()
	{
		AtsSGEvent event = null;

		try
		{
			event = sgEventQueue.poll(30, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
		}

		if (event != null)
		{
			return event;
		}

		return event;
	}

	public int atsInitialize(ISysCfgData sysCfgData)
	{
		CallAgentImpl.sysCfgData = sysCfgData;
		return atsInitialize(CallAgentImpl.sysCfgData.getGlsConnection(),
				CallAgentImpl.sysCfgData.getCmsServerPort(),
				CallAgentImpl.sysCfgData.getGlsSleeServiceName(),
				CallAgentImpl.sysCfgData.getCmsServerPort(),
				CallAgentImpl.sysCfgData.getCcodStatFileDir() + "|"
						+ CallAgentImpl.sysCfgData.getCcodServiceId());
	}

	public int atsInitialize(String mgIp, int mgPort, String sgIp, int sgPort,
			String monitorDnis)
	{
		glsConnection = mgIp;
		sleeServiceName = sgIp;

		int pos = monitorDnis.indexOf('|');
		statFileDir = monitorDnis.substring(0, pos);
		serviceId = monitorDnis.substring(pos + 1);
		cmsServerPort = mgPort;

		cmsmp.start();

		int result = 0;

		try
		{
			result = initialize();
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);

			Log.wDebug(LogDef.id_0x10250001);

			result = 0;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsMakeCall(com.channelsoft.reusable.util.IntWrapper, java.lang.String, java.lang.String, com.channelsoft.reusable.util.IntWrapper, int, int, int)
	 */
	public int atsMakeCall(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall)
	{
		// 不支持普通外呼，只支持三方呼叫。
		return Constant.ERR_GeneralError;
	}

	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean routeMedia, boolean isVideoCall)
	{
		return atsMakeCall(port, callingNumber, destNumber, callId, timeout,
				reserved, transactionId, false, 0, false, isVideoCall, "", "");
	}

	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean routeMedia, int mode, boolean reverseRoute,
			boolean isVideoCall, String videoUrl1, String videoUrl2)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl callAgent = null;
		if ((callId != null) && (callId.value != -1))
		{
			callAgent = getCallAgent(callId.value);
			if ((callAgent.otherPartyAgent != null)
					&& (callAgent.otherPartyAgent.callId != -1))
			{
				Log.wError(LogDef.id_0x10150015, port.value, callAgent.callId);
				return Constant.ERR_DestBusy;
			}
		}

		// judge whether send DTMF
		boolean shouldSendDTMF = false;
		int commaCount = 0;
		String dtmfPart = "";
		StringWrapper destDnWrapper = new StringWrapper();
		StringWrapper dtmfPartWrapper = new StringWrapper();
		commaCount = judgeSendDTMF(destNumber, destDnWrapper, dtmfPartWrapper);
		if (commaCount == -1)
		{
			return Constant.ERR_InvalidTelNum;
		}
		else if (commaCount == 1)
		{
			destNumber = destDnWrapper.value;
			dtmfPart = dtmfPartWrapper.value;
			shouldSendDTMF = true;
		}
		else if (commaCount == 2)
		{
			destNumber = destDnWrapper.value;
		}
		// end judge whether send DTMF

		IntHolder portHolder = new IntHolder();
		IntHolder callIdHolder = new IntHolder();

		int result;
		IntHolder connectionIdHolder = new IntHolder();
		IntHolder mepIdHolder = new IntHolder();

		try
		{
			if (callAgent == null)
			{
				// ServiceInfoTHolder siHolder = new ServiceInfoTHolder();
				// result =
				// turnCCODResultT2SleeResult(ccodProxy.CreateSession("",
				// new HashMap<String, String>(), ServiceTypeT.IVRService,
				// siHolder));
				//
				// Log.wDebug("CallAgent 创建会话返回%X", result);
				// if (result != Constant.GATEWAY_SUCCESS)
				// {
				// return result;
				// }
				//
				// callAgent = getNewCallAgent(siHolder.value.sessionID, 0, "",
				// dtmfPart, siHolder.value.timeStamp, false);
				return Constant.ERR_GeneralError;
			}
			else
			{
				result = turnCCODResultT2SleeResult(ccodProxy.CreateCall(
						callAgent.sessionId, callingNumber, destNumber, 0,
						MediaDirectionT.DirectionFull, timeout / 1000,
						connectionIdHolder, mepIdHolder));
			}
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		Log.wDebug(LogDef.id_0x1005000C, portHolder.value, callIdHolder.value,
				callingNumber, destNumber, result);
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		Log.wDebug(LogDef.id_0x1005000D, portHolder.value, callIdHolder.value,
				callingNumber, destNumber);

		// 生成代表新的呼叫方的CallAgent。
		CallAgentImpl agent = getNewCallAgent(callAgent.sessionId,
				connectionIdHolder.value, callAgent.enterpriseId,
				callAgent.accountId, 0, false, false);
		agent.callState = Constant.StatusConnect;
		agent.ani = callingNumber;
		agent.dnis = destNumber;
		portHolder.value = agent.callId;
		callIdHolder.value = agent.callId;
		agent.mepId = mepIdHolder.value;

		CallIdsInSession callIds = sessionId2Callsn.get(agent.sessionId);

		CallAgentImpl oriAgent = null;
		if (callId.value >= 0)
		{
			oriAgent = getCallAgent(callId.value);

			if (oriAgent.callState != Constant.StatusIdle)
			{
				oriAgent.callState = Constant.StatusConference;
			}

			oriAgent.otherPartyAgent = agent;
			agent.hostAgent = oriAgent;
			agent.enterpriseId = oriAgent.enterpriseId;
			agent.accountId = oriAgent.accountId;
			callIds.otherMepId = agent.mepId;
		}
		else
		{
			callIds.mepId = agent.mepId;
		}

		if (routeMedia)
		{
			int ret = Constant.ERR_GeneralError;
			if (reverseRoute)
			{
				ret = atsSwitchTwoPort(portHolder.value, callIdHolder.value,
						port.value, mode, videoUrl2, videoUrl1);
				Log.wDebug(LogDef.id_0x1005000E, portHolder.value,
						callIdHolder.value, port.value, callId.value, ret);
			}
			else
			{
				ret = atsSwitchTwoPort(port.value, callId.value,
						portHolder.value, mode, videoUrl1, videoUrl2);
				Log.wDebug(LogDef.id_0x1005000E, port.value, callId.value,
						portHolder.value, callIdHolder.value, ret);
			}

			if (ret != Constant.ERR_Success)
			{
				Log.wError(LogDef.id_0x10150016, port.value, callId.value,
						portHolder.value, callIdHolder.value);
				atsReleaseCall(portHolder.value, callIdHolder.value, 0);
				return Constant.ERR_GeneralError;
			}
		}

		int ret = agent.makeCall(agent.callId, timeout);
		if (ret == Constant.ERR_Success)
		{
			port.value = agent.callId;
			callId.value = agent.callId;
		}
		else
		{
			if (ret == Constant.ERR_TimeOut)
			{
				Log.wDebug(LogDef.id_0x1005000F, transactionId, agent.callId,
						agent.callId, callingNumber, destNumber);

				atsReleaseCall(agent.callId, agent.callId, 0);
			}

			agent.callState = Constant.StatusIdle;

			if (oriAgent != null)
			{
				if (oriAgent.callState != Constant.StatusIdle)
				{
					oriAgent.callState = Constant.StatusConnect;
				}

				oriAgent.otherPartyAgent = null;
			}

			agent.hostAgent = null;

			agent.clearSession();

			return ret;
		}

		// send DTMF
		if (shouldSendDTMF)
		{
			Log.wTrace(LogDef.id_0x10050000_3, transactionId, port.value,
					callId.value, 3);
			try
			{
				Thread.sleep(3 * 1000);
			}
			catch (Exception e)
			{
			}

			Log.wTrace(LogDef.id_0x10050000_4, transactionId, port.value,
					callId.value, dtmfPart);
			int error = 0;
			error = atsSendSignals(port.value, callId.value, dtmfPart);
			Log.wTrace(LogDef.id_0x10050010, transactionId, port.value,
					callId.value, error, dtmfPart);
			if (error != Constant.ERR_Success)
			{
				Log.wDebug(LogDef.id_0x10050000_6, agent.callId, agent.callId,
						transactionId, callingNumber, destNumber);

				atsReleaseCall(agent.callId, agent.callId, 0);

				return error;
			}
		}
		// end send DTMF

		return ret;
	}

	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String voiceFileList[], int voiceLib)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.createMedia();

		agent.stopIo();

		int result;
		try
		{
			PlayListElemT[] list = new PlayListElemT[voiceFileList.length];
			for (int i = 0; i < voiceFileList.length; i++)
			{
				list[i] = new PlayListElemT();

				if (voiceFileList[i].indexOf(":\\") != -1)
				{
					list[i].type = ElemT.VoiceFile;
					list[i].content = "file:///" + voiceFileList[i];
				}
				else
				{
					list[i].type = ElemT.TTSString;
					list[i].content = voiceFileList[i];
				}

				list[i].encoding = EncodingT.UTF8;
				list[i].rate = rate;
			}

			TTSVoiceLibT tvlt = null;
			if ((voiceLib == Constant.TTS_VOICE_LIB_MAN)
					|| (voiceLib == Constant.TTS_VOICE_LIB_MAN2))
			{
				tvlt = TTSVoiceLibT.TTSVoiceLibMan;
			}
			else if ((voiceLib == Constant.TTS_VOICE_LIB_WOMAN)
					|| (voiceLib == Constant.TTS_VOICE_LIB_WOMAN1))
			{
				tvlt = TTSVoiceLibT.TTSVoiceLibWoman;
			}
			else
			{
				tvlt = TTSVoiceLibT.TTSVoiceLibWoman;// 其余情况用女生读
			}
			result = turnCCODResultT2SleeResult(ccodProxy.PlayList(
					agent.sessionId, agent.mepId, list, tvlt, interruptKeys));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.createMedia();

		agent.stopIo();

		EncodingT et = EncodingT.UTF8;
		TTSVoiceLibT tvlt = null;
		if ((voiceLib == Constant.TTS_VOICE_LIB_MAN)
				|| (voiceLib == Constant.TTS_VOICE_LIB_MAN2))
		{
			tvlt = TTSVoiceLibT.TTSVoiceLibMan;
		}
		else if ((voiceLib == Constant.TTS_VOICE_LIB_WOMAN)
				|| (voiceLib == Constant.TTS_VOICE_LIB_WOMAN1))
		{
			tvlt = TTSVoiceLibT.TTSVoiceLibWoman;
		}

		int result;

		try
		{
			String textStr = text;
			result = turnCCODResultT2SleeResult(ccodProxy.PlayTTS(
					agent.sessionId, agent.mepId, textStr, et, tvlt,
					interruptKeys));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayVoice(int port, int callId, String fileName,
			String interruptKeys, int rate)
	{
		return atsPlayVoice(port, callId, fileName, interruptKeys, rate, null,
				null);
	}

	public int atsPlayVoice(int port, int callId, String fileName,
			String interruptKeys, int rate, IntWrapper secondsPlayed,
			Calendar lastInteractionTime)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.createMedia();

		agent.stopIo();

		int result;
		try
		{
			String fileNameStr = fileName;

			if (fileNameStr.indexOf('/') == 0)
			{
				fileNameStr = "file:" + fileNameStr.substring(1);
			}
			else if (fileNameStr.indexOf('/') == -1)
			{
				fileNameStr = "file:///" + fileNameStr;
			}

			result = turnCCODResultT2SleeResult(ccodProxy.PlayFile(
					agent.sessionId, agent.mepId, fileNameStr, rate,
					interruptKeys));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		int timeout = 30 * 60 * 1000;
		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayVoiceAsync(int port, int callId, String fileName,
			String interruptKeys, int rate)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		String fileNameStr = fileName;

		if (fileNameStr.indexOf('/') == 0)
		{
			fileNameStr = "file:" + fileNameStr.substring(1);
		}
		else if (fileNameStr.indexOf('/') == -1)
		{
			fileNameStr = "file:///" + fileNameStr;
		}

		int result;
		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.PlayFile(
					agent.sessionId, agent.mepId, fileNameStr, rate,
					interruptKeys));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		agent.isPlayingVoiceAsynch = true;

		agent.playVoiceFileName = fileNameStr;

		agent.playVoiceInterruptKeys = interruptKeys;

		agent.playVoiceRate = rate;

		return Constant.ERR_Success;
	}

	public int atsQueryCallState(int port, int callId, IntWrapper callStatus)
	{
		CallAgentImpl agent = getCallAgent(port);
		if ((agent != null) && (agent.callId == callId))
		{
			callStatus.value = agent.callState;
		}
		else
		{
			callStatus.value = Constant.StatusIdle;
		}

		return Constant.ERR_Success;
	}

	public int atsReceiveDTMF(int port, int callId, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		buffer.value = "";

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.ReceiveDTMF(
					agent.sessionId, agent.mepId, keyCount, maxTime,
					betweenTime, interruptKeys));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		StringWrapper reply = new StringWrapper();
		result = agent.doMgOperation(callId, 5 * 60 * 1000,
				Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF, reply);
		if (result == Constant.ERR_Success)
		{
			buffer.value = reply.value;
		}

		return result;
	}

	public int atsReceiveFax(int port, int callId, String fileName)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.ReceiveFax(
					agent.sessionId, agent.mepId, "file:///" + fileName));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsRecordVoice(int port, int callId, String fileName,
			char endFlag, int maxTime, int rate)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			String fileNameStr = fileName;
			if (fileNameStr.indexOf('/') == 0)
			{
				fileNameStr = "file:" + fileNameStr.substring(1);
			}
			else if (fileNameStr.indexOf('/') == -1)
			{
				fileNameStr = "file:///" + fileNameStr;
			}

			result = turnCCODResultT2SleeResult(ccodProxy.RecordVoice(
					agent.sessionId, agent.mepId, fileNameStr, maxTime, String
							.valueOf(endFlag)));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		int timeout = 3600 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsReleaseCall(int port, int callId, int reserved)
	{
		return atsReleaseCall(port, callId, reserved, true);
	}

	public int atsReleaseCall(int port, int callId, int reserved, boolean wait)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		try
		{
			int ret = turnCCODResultT2SleeResult(ccodProxy.Disconnect(
					agent.sessionId, agent.connectionId));
			if (ret != Constant.GATEWAY_SUCCESS)
			{
				return ret;
			}
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			return Constant.ERR_RPC;
		}

		if (wait)
		{
			int timeout = 3 * 1000;

			agent.releaseCall(callId, timeout);
		}

		// CallAgentImpl.ddsClient.addEvent(agent.sessionId, 0,
		// agent.enterpriseId, agent.ani, agent.dnis,
		// IvrCallEventT._TIvrReleased,
		// IvrEventReasonT._TIvrResLocateRelease);

		return Constant.ERR_Success;
	}

	public int atsRetrieveCall(int port, int callId, int reserved)
	{
		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		if (agent.callState != Constant.StatusConference)
		{
			return Constant.ERR_Success;
		}

		if (agent.otherPartyAgent.callState != Constant.StatusConnect)
		{
			return Constant.ERR_Success;
		}

		return atsReleaseCall(agent.otherPartyAgent.callId,
				agent.otherPartyAgent.callId, 0);
	}

	public int atsSendFax(int port, int callId, String fileName)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.SendFax(
					agent.sessionId, agent.mepId, "file:///" + fileName));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsSendSignals(int port, int callId, String signals)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		try
		{
			return turnCCODResultT2SleeResult(ccodProxy.SendDTMF(
					agent.sessionId, agent.mepId, signals));
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			return Constant.ERR_RPC;
		}
	}

	public int atsSingleStepConference(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean isVideoCall)
	{
		return Constant.ERR_GeneralError;
	}

	public int atsSingleStepTransfer(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean createConf, int mode,
			boolean reverseRoute, boolean isVideoCall, String videoUrl1,
			String videoUrl2)
	{
		if (!destNumber.startsWith("TEL:") && !destNumber.startsWith("SIP:"))
		{
			return shiftService(port, callId, callingNumber, destNumber);
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		IntWrapper portWrapper = new IntWrapper();
		IntWrapper callIdWrapper = new IntWrapper();
		portWrapper.value = port;
		callIdWrapper.value = callId;

		int ret = atsMakeCall(portWrapper, callingNumber, destNumber,
				callIdWrapper, timeout * 1000, reserved, transactionId,
				routeMediaFirst, mode, reverseRoute, isVideoCall, videoUrl1,
				videoUrl2);
		if (ret != Constant.ERR_Success)
		{
			Log
					.wError(LogDef.id_0x1015001E, callingNumber, destNumber,
							timeout);
			return ret;
		}

		if (!routeMediaFirst)
		{
			if (reverseRoute)
			{
				ret = atsSwitchTwoPort(portWrapper.value, callId, port, mode,
						videoUrl2, videoUrl1);
				Log.wDebug(LogDef.id_0x1005000E, portWrapper.value,
						callIdWrapper.value, port, callId, ret);
			}
			else
			{
				ret = atsSwitchTwoPort(port, callId, portWrapper.value, mode,
						videoUrl1, videoUrl2);
				Log.wDebug(LogDef.id_0x1005000E, port, callId,
						portWrapper.value, callIdWrapper.value, ret);
			}

			if (ret != Constant.ERR_Success)
			{
				Log.wError(LogDef.id_0x10150016, port, callId,
						portWrapper.value, callIdWrapper.value);
				atsReleaseCall(portWrapper.value, callIdWrapper.value, 0);

				return ret;
			}
		}

		if (!createConf)
		{
			return ret;
		}

		ret = blindCreateConference(port, callId, portWrapper.value,
				isVideoCall);
		Log.wDebug(LogDef.id_0x10050011, port, callId, portWrapper.value,
				callIdWrapper.value, ret);
		if (ret != Constant.ERR_Success)
		{
			Log.wError(LogDef.id_0x10150017, port, callId, portWrapper.value,
					callIdWrapper.value);
			atsReleaseCall(portWrapper.value, callIdWrapper.value, 0);
		}

		return ret;
	}

	public int atsStartConferencRecording(int port, int callId,
			String recordFileName, int reserved)
	{
		return Constant.ERR_GeneralError;
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2, int mode)
	{
		return atsSwitchTwoPort(port1, callId1, port2, mode, "", "");
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2,
			int reserved, String videoUrl1, String videoUrl2)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = null;
		agent = checkCallState(callId1);
		if ((agent == null) && (checkCallState(port2) == null))
		{
			return Constant.ERR_NoCall;
		}

		if (agent == null)
		{
			agent = getCallAgent(callId1);
			if (agent == null)
			{
				return Constant.ERR_NoCall;
			}
		}

		CallAgentImpl agent2 = null;
		if (port2 == -1)
		{
			if ((agent.callState == Constant.StatusConference)
					&& (agent.otherPartyAgent != null)
					&& (agent.otherPartyAgent.callState == Constant.StatusConnect))
			{
				agent2 = agent.otherPartyAgent;
			}
			else
			{
				return Constant.ERR_NoCall;
			}
		}
		else
		{
			agent2 = checkCallState(port2);
			if (agent2 == null)
			{
				return Constant.ERR_NoCall;
			}
		}

		int result = 0;
		MediaDirectionT mdt = null;
		if (reserved == Constant.RM_FULLDUP)
		{
			mdt = MediaDirectionT.DirectionFull;
		}
		else if (reserved == Constant.RM_HALFDUP)
		{
			mdt = MediaDirectionT.DirectionHalf;
		}
		else if (reserved == Constant.RM_DSPDUP)
		{
			mdt = MediaDirectionT.DirectionNone;
		}
		try
		{
			result = turnCCODResultT2SleeResult(ccodProxy.Join(agent.sessionId,
					agent.connectionId, agent2.connectionId, mdt));

		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	@Override
	public int atsVoiceEdit(int port, int callId, String fileName, int rate,
			IntWrapper secondsPlayed, Calendar lastInteractionTime,
			StringWrapper buffer, int keyCount, String interruptKeys,
			int maxTime, int betweenTime)
	{
		return Constant.ERR_GeneralError;
	}

	@Override
	public int atsVoiceListEdit(int port, int callId, int rate, int fileCount,
			String[] voiceFileList, int voiceLib, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		return Constant.ERR_GeneralError;
	}

	public void attachCall(int port, int callID)
	{
		// CallAgentImpl agent = checkCallState(callId);
		// if (agent == null)
		// {
		// return;
		// }
		//
		// agent.updateHostParty();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#attachOtherParty(int, int)
	 */
	public void attachOtherParty(int port, int callID, int otherPort,
			int otherCallId)
	{
		// TODO Auto-generated method stub

	}

	public int blindCreateConference(int port1, int callId, int port2,
			boolean isVideoCall)
	{
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;

		try
		{
			IntHolder confId = new IntHolder();
			result = turnCCODResultT2SleeResult(ccodProxy.CreateConference(
					agent.sessionId, 3, confId));
			if (result == Constant.GATEWAY_SUCCESS)
			{
				this.confId = confId.value;
			}
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	CallAgentImpl checkCallState(int callId)
	{
		CallAgentImpl agent = getCallAgent(callId);

		agent = ((agent != null) && (agent.callId == callId) && (agent.callState != Constant.StatusIdle)) ? agent
				: null;
		if (agent != null)
		{
			agent.mgMsgQueue.clear();
		}

		return agent;
	}

	/**
	 * 清除当前呼叫相关的会话信息。
	 */
	void clearSession()
	{
		callAgents.remove(callId);
		callSn2SessionId.remove(callId);

		CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return;
		}

		synchronized (callIds)
		{
			if ((callIds.callId == callId))
			{
				callIds.callId = -1;
				callIds.connectionId = -1;
				callIds.mepId = -1;
			}
			else if (callIds.otherCallId == callId)
			{
				callIds.otherCallId = -1;
				callIds.otherConnectionId = -1;
				callIds.otherMepId = -1;
			}

			Log.wDebug(LogDef.id_0x10050000_8, sessionId, connectionId, callId,
					callSn2SessionId.size(), callAgents.size());

			if ((callIds.callId == -1) && (callIds.otherCallId == -1))
			{
				// 清除会话
				sessionId2Callsn.remove(sessionId);

				Log.wDebug(LogDef.id_0x10050000_9, sessionId, sessionId2Callsn
						.size());
			}
		}
	}

	/**
	 * 如果当前没有媒体资源，则创建媒体资源。
	 * 
	 * @return 。
	 */
	int createMedia()
	{
		if (mepId != -1)
		{
			return Constant.ERR_NoCall;
		}

		if (sessionId == -1l)
		{
			return Constant.ERR_NoCall;
		}

		Ice.IntHolder mepIdWrapper = new Ice.IntHolder();
		int ret = turnCCODResultT2SleeResult(ccodProxy.CreateMediaEndpoint(
				sessionId, mepIdWrapper));
		if (ret == Constant.GATEWAY_SUCCESS)
		{
			mepId = mepIdWrapper.value;

			CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
			callIds.otherMepId = mepId;

			MediaDirectionT mdt = null;
			mdt = MediaDirectionT.DirectionFull;
			ret = turnCCODResultT2SleeResult(ccodProxy.Join(sessionId,
					connectionId, mepId, mdt));

			if (ret != Constant.GATEWAY_SUCCESS)
			{
				Log.wError(LogDef.id_0x10150006, sessionId, connectionId, ret);
			}
		}
		else
		{
			Log.wError(LogDef.id_0x10150007, sessionId, ret);
		}

		return ret;
	}

	/**
	 * 给自己发一个假挂机事件。
	 */
	void customHangup(int timestamp)
	{
		SGEvent event = new SGEvent();
		event.CallID = callId;
		event.EventID = Constant.SG_CALLDISCONNECTED;
		event.CalledID = "";
		event.CallerID = "";
		event.OriCalledID = "";
		event.OriCallerID = "";
		event.ResID = DUMMY_RES_ID;

		cmsmp.msgQueue.add(new CMSMessage(event, sessionId, connectionId,
				timestamp));
	}

	int doMgOperation(int callId, int timeout)
	{
		return doMgOperation(callId, timeout, -1, null);
	}

	int doMgOperation(int callId, int timeout, int waitMsgType)
	{
		return doMgOperation(callId, timeout, waitMsgType, null);
	}

	int doMgOperation(int callId, int timeout, int waitMsgType,
			StringWrapper resultWrapper)
	{
		return doMgOperation(callId, timeout, waitMsgType, resultWrapper, null,
				null);
	}

	int doMgOperation(int callId, int timeout, int waitMsgType,
			StringWrapper resultWrapper, IntWrapper secondsPlayed,
			Calendar lastInteractionTime)
	{
		try
		{
			CallAgentMessage msg = null;
			long startMills = System.currentTimeMillis();

			while (true)
			{
				long millisToWait = timeout
						- (System.currentTimeMillis() - startMills);
				if (millisToWait <= 0)
				{
					return Constant.ERR_TimeOut;
				}

				// 以操作超时时间制定取事件的阻塞时间时，waitForEvent标志为true
				boolean waitForEvent = true;

				if (secondsPlayed != null)
				{
					// 检查到目前为止的用户无交互时间，是否>=配置文件所配置的最大无交互时间
					// 若是，则置交互时间为January 1, 1970 00:00:00.000，累加已放音时长，返回成功
					long idleMillis = Math.max(System.currentTimeMillis()
							- lastInteractionTime.getTimeInMillis(), 0);
					if (idleMillis / 1000 >= sysCfgData.getMaxUserIdleSeconds())
					{
						int playedTime = (int) (System.currentTimeMillis() - startMills) / 1000;
						stopIo(true);
						secondsPlayed.value += playedTime;
						lastInteractionTime.setTimeInMillis(0);
						return Constant.ERR_Success;
					}
					// 修改取事件时的最长等待时间:操作超时时间和当前允许最大用户无交互时间的最小值
					if (millisToWait > sysCfgData.getMaxUserIdleSeconds()
							* 1000 - idleMillis)
					{
						waitForEvent = false;
					}
					millisToWait = Math.min(sysCfgData.getMaxUserIdleSeconds()
							* 1000 - idleMillis, millisToWait);
				}

				msg = mgMsgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);

				if (msg == null)
				{
					// 是playVoice操作的调用，并且取事件阻塞时间为允许用户无交互时间的最大值
					if ((secondsPlayed != null) && !waitForEvent)
					{
						int playedTime = (int) (System.currentTimeMillis() - startMills) / 1000;
						stopIo(true);
						secondsPlayed.value += playedTime;
						lastInteractionTime.setTimeInMillis(0);

						return Constant.ERR_Success;
					}
					else
					{

						if (secondsPlayed != null)
						{
							// 是playVoice操作的调用，但取事件阻塞时间小于允许用户无交互时间的最大值
							// 则累加已放音时长（实际上该参数只要不等于-1，没什么实际意义）
							int playedTime = (int) (System.currentTimeMillis() - startMills) / 1000;
							stopIo(true);
							secondsPlayed.value += playedTime;
						}

						return Constant.ERR_TimeOut;

					}
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent sgReply = ((SGEventMessage) msg).sgEvent;

					switch (sgReply.EventID)
					{
					case Constant.SG_CALLDISCONNECTED:
					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_Disconnect;
					default:
						continue;
					}
				}
				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT)
				{
					MGEvent mgReply = ((MGEventMessage) msg).mgEvent;

					if (waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_ASR)
					{
						int result = processAsrMGEvent(mgReply, resultWrapper);
						if (result == Constant.ERR_NO_MSG)
						{
							continue;
						}
						else
						{
							return result;
						}
					}
					else if (waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF)
					{
						int result = processReceiveDtmfMGEvent(mgReply,
								resultWrapper);
						if (result == Constant.ERR_NO_MSG)
						{
							continue;
						}
						else
						{
							return result;
						}
					}
					else
					{
						if (secondsPlayed != null)
						{
							if ((mgReply.EventID == Constant.MG_TDX_PLAY)
									&& ((mgReply.Reason == Constant.MGREASON_TM_DIGIT) || (mgReply.Reason == Constant.MGREASON_TM_MAXDTMF)))
							{
								secondsPlayed.value = -1;
								lastInteractionTime = Calendar.getInstance();
							}
						}
						return translateMGEvent(mgReply.EventID, mgReply.Reason);
					}
				}
			}
		}
		catch (Exception e)
		{
			return Constant.ERR_RPC;
		}
	}

	/**
	 * @return the accessTimestamp
	 */
	public int getAccessTimestamp()
	{
		return accessTimestamp;
	}

	/**
	 * @return the enterpriseId
	 */
	public String getEnterpriseId()
	{
		return enterpriseId;
	}

	/**
	 * @return the sessionId
	 */
	public long getSessionId()
	{
		return sessionId;
	}

	/**
	 * @return the startTimestamp
	 */
	public int getStartTimestamp()
	{
		return startTimestamp;
	}

	public int initialize()
	{
		Log.wDebug(LogDef.id_0x10050000, cmsServerPort);
		Ice.Communicator ice_server = null;
		Ice.ObjectAdapter adapter = null;
		if (UnifiedServiceManagement.configData.isCcodUseSSL())
		{
			ice_server = Ice.Util.initialize(new String[] { "--Ice.Config="
					+ ISysCfgData.queryRegData()
					+ "/config/Ice_SLEE4CCODCMSServer.cfg" });
			adapter = ice_server.createObjectAdapterWithEndpoints(
					"SLEE4CCODCMSServer:", "ssl -p " + cmsServerPort);
		}
		else
		{
			ice_server = Ice.Util.initialize(new String[] {
					"--Ice.ThreadPool.Server.Size=" + 20, "--Ice.ACM.Client=0",
					"--Ice.ACM.Server=0" });
			adapter = ice_server.createObjectAdapterWithEndpoints(
					"SLEE4CCODCMSServer:", "default -p " + cmsServerPort);
		}
		Ice.Object object = new ServiceUnitInterfaceImpl(this);
		adapter.add(object, ice_server.stringToIdentity("SLEE4CCODCMSServer"));
		adapter.activate();

		SleeServer ss = new SleeServer(ice_server);
		ss.start();
		Log.wDebug(LogDef.id_0x10050002, cmsServerPort);

		GLSClient glsClient = new GLSClient(glsConnection, sleeServiceName);
		glsClient.start();
		Log.wDebug(LogDef.id_0x10050003);

		ddsClient = new DDSClient(statFileDir, glsClient);
		ddsClient.startClients();
		Log.wDebug(LogDef.id_0x10050000_12);

		return 1;
	}

	/**
	 * 判断MakeCall时是否需要发送DTMF
	 * 
	 * @param destNumber
	 *            全部目的号码
	 * @param destDn
	 *            真实目的号码
	 * @param dtmfPart
	 *            发送的DTMF部分
	 * @return -1:号码无效； 0,2:无需发送DTMF；1:需要发送DTMF
	 */
	int judgeSendDTMF(String destNumber, StringWrapper destDn,
			StringWrapper dtmfPart)
	{
		int commaCount = 0;
		int startPos = destNumber.indexOf(",");
		if (startPos == 0)
		{
			return -1;
		}
		else if (startPos > 0)
		{
			int endPos = destNumber.lastIndexOf(",");
			if (endPos != destNumber.length() - 1)
			{
				commaCount = 1;
			}
			else
			{
				commaCount = 2;
			}

			dtmfPart.value = destNumber.substring(endPos + 1);
			destDn.value = destNumber.substring(0, startPos);
		}

		return commaCount;
	}

	int makeCall(int callId, int timeout)
	{
		try
		{
			CallAgentMessage msg = null;
			long startMills = System.currentTimeMillis();

			while (true)
			{
				long millisToWait = timeout
						- (System.currentTimeMillis() - startMills);
				if (millisToWait <= 0)
				{
					return Constant.ERR_TimeOut;
				}

				msg = sgMsgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);
				if (msg == null)
				{
					return Constant.ERR_TimeOut;
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent sgReply = ((SGEventMessage) msg).sgEvent;

					Log.wDebug(LogDef.id_0x10050006, -1, callId, sgReply.ResID,
							sgReply.CallID, sgReply.EventID, sgReply.EventData);

					switch (sgReply.EventID)
					{
					case Constant.SG_CALLCONNECTED:
						return Constant.ERR_Success;

					case Constant.SG_CALLDISCONNECTED:
						return translateSGReason(sgReply.EventData);

					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_GeneralError;
					default:
						continue;
					}
				}
			}
		}
		catch (Exception e)
		{
			return Constant.ERR_RPC;
		}
	}

	void onDisconnect()
	{
		callState = Constant.StatusIdle;
		isPlayingVoiceAsynch = false;

		releaseOtherParty();
		updateHostParty();

		if ((confId != -1) && (ccodProxy != null))
		{
			ccodProxy.DestroyConference(sessionId, confId);
		}

		confId = -1;
	}

	void onIncomingCall(SGEvent event)
	{
		callId = event.CallID;
		ani = event.CallerID;
		dnis = event.CalledID;
		oriAni = event.OriCallerID;
		oriDnis = event.OriCalledID;
		reserved = event.rfu;

		callState = Constant.StatusConnect;

		mgMsgQueue.clear();
		sgMsgQueue.clear();

		confId = -1;
	}

	int processAsrMGEvent(MGEvent mgReply, StringWrapper resultWrapper)
	{
		if (mgReply.EventID == Constant.MG_TDX_ASR)
		{
			if (mgReply.Reason == Constant.MGREASON_ASR_RECGNIZED)
			{
				try
				{
					String ResultStr = mgReply.DTMFString;
					resultWrapper.value = ResultStr;
				}
				catch (Exception e)
				{
					Log.wException(LogDef.id_0x10150000, e);
				}

				Log.wDebug(LogDef.id_0x10050005, mgReply.ResID, mgReply.CallID,
						resultWrapper.value);

				return Constant.ERR_Success;
			}
			else
			{
				return translateAsrReason(mgReply.Reason);
			}
		}

		return Constant.ERR_NO_MSG;
	}

	int processReceiveDtmfMGEvent(MGEvent mgReply, StringWrapper resultWrapper)
	{
		if (mgReply.EventID == Constant.MG_TDX_GETDIGIT)
		{
			int result = translateMGEvent(mgReply.EventID, mgReply.Reason);
			if (result == Constant.ERR_Success)
			{
				resultWrapper.value = mgReply.DTMFString;
			}

			return result;
		}

		return Constant.ERR_NO_MSG;
	}

	int releaseCall(int callId, int timeout)
	{
		try
		{
			CallAgentMessage msg = null;
			long startMills = System.currentTimeMillis();

			while (true)
			{
				long millisToWait = timeout
						- (System.currentTimeMillis() - startMills);
				if (millisToWait <= 0)
				{
					return Constant.ERR_TimeOut;
				}

				msg = sgMsgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);
				if (msg == null)
				{
					return Constant.ERR_TimeOut;
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent event = ((SGEventMessage) msg).sgEvent;
					Log.wDebug(LogDef.id_0x10050004, -1, callId, event.ResID,
							event.CallID, event.EventID);

					switch (event.EventID)
					{
					case Constant.SG_CALLDISCONNECTED:
					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_Success;
						// default:
						// return Constant.ERR_GeneralError;
					}
				}
			}
		}
		catch (Exception e)
		{
			return Constant.ERR_RPC;
		}
	}

	void releaseOtherParty()
	{
		if (otherPartyAgent != null)
		{
			if (otherPartyAgent.callState != Constant.StatusIdle)
			{
				Log.wDebug(LogDef.id_0x10050001, -1, otherPartyAgent.callId);
				atsReleaseCall(otherPartyAgent.callId, otherPartyAgent.callId,
						0, false);
			}
		}

		otherPartyAgent = null;
	}

	int shiftService(int port, int callId, String callingNumber,
			String destNumber)
	{
		CallAgentImpl agent = checkCallState(callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}

		ServiceTypeT stt = ServiceTypeT.ACDService;
		Ice.IntHolder timestamp = new Ice.IntHolder();

		ServiceDataTHolder serviceData = new ServiceDataTHolder();
		ccodProxy.GetServiceData(agent.sessionId, EncodingT.UTF8, serviceData);

		if (!agent.isOldCall
				|| FinishedSDR.value.equals(serviceData.value
						.get(ADIsWriteSDR.value)))
		{
			// 电话是新呼叫或者UCDS记过SDR，所以在随路数据中使用当前呼叫的时间，否则只能使用原有随路数据中的原始呼叫时间。
			serviceData.value.put(AccessTime.value, ""
					+ agent.getAccessTimestamp());
			serviceData.value.put(StartTime.value, ""
					+ agent.getStartTimestamp());
		}

		serviceData.value.put("EnterpriseID", agent.getEnterpriseId());
		serviceData.value.put(AccountID.value, agent.accountId);

		ccodProxy.SetServiceData(agent.sessionId, serviceData.value,
				EncodingT.UTF8);

		int ret = turnCCODResultT2SleeResult(ccodProxy.ShiftService(
				agent.sessionId, stt, timestamp));

		if (ret != Constant.GATEWAY_SUCCESS)
		{
			Log.wError(LogDef.id_0x10150009, agent.sessionId, port, callId,
					callingNumber, destNumber);
			return ret;
		}

		agent.customHangup(timestamp.value);

		return Constant.ERR_Success;
	}

	void stopIo()
	{
		stopIo(false);
	}

	void stopIo(boolean force)
	{
		if (ccodProxy == null)
		{
			return;
		}

		if (!isPlayingVoiceAsynch && !force)
		{
			return;
		}

		try
		{
			ccodProxy.StopIO(sessionId, mepId);
		}
		catch (Throwable e)
		{
		}

		isPlayingVoiceAsynch = false;

		doMgOperation(callId, 5 * 1000);
	}

	/**
	 * 把父呼叫的状态从三方通话改为两方通话。
	 */
	void updateHostParty()
	{
		if ((hostAgent != null) && (hostAgent.otherPartyAgent == this)
				&& (hostAgent.callState == Constant.StatusConference))
		{
			hostAgent.callState = Constant.StatusConnect;
			hostAgent.otherPartyAgent = null;

			int ret = turnCCODResultT2SleeResult(ccodProxy.Join(
					hostAgent.sessionId, hostAgent.connectionId,
					hostAgent.mepId, MediaDirectionT.DirectionFull));
			Log.wDebug(
					"CallAgent 三方呼叫结束，接回[connectionId=%d mepId=%d]返回  [0x%X]",
					hostAgent.connectionId, hostAgent.mepId, ret);
		}

		hostAgent = null;
	}

	@Override
	public int atsAppendVideo(int port, int callId, String[][] infoOnVideo)
	{
		return Constant.ERR_GeneralError;
	}

	@Override
	public int atsCallRecordRing(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall, String fileName, char endFlag, int maxTime,
			int rate)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}

class CallIdsInSession
{
	int callId = -1;

	int connectionId = -1;

	int mepId = -1;

	int otherCallId = -1;

	int otherConnectionId = -1;

	int otherMepId = -1;

	int getCallIdFromConnectionId(int connectionId)
	{
		return this.connectionId == connectionId ? callId
				: (this.otherConnectionId == connectionId ? otherCallId : -1);
	}

	int getCallIdFromMepId(int mepId)
	{
		return this.mepId == mepId ? callId
				: (this.otherMepId == mepId ? otherCallId : -1);
	}
}

class CMSMessage
{
	final static int MG_MSG = 1;

	final static int SG_MSG = 0;

	String accountId;

	int connectionId;

	String enterpriseId;

	int mepId;

	int messageType = -1;

	MGEventMessage mgm = new MGEventMessage();

	long sessionId;

	SGEventMessage sgm = new SGEventMessage();

	CMSMessage(MediaEventT event)
	{
		messageType = MG_MSG;

		mgm.mgEvent = new MGEvent();
		sessionId = event.sessionID;
		mepId = event.mepID;
		mgm.mgEvent.CallID = -1;

		mgm.mgEvent.DTMFString = event.eventBuffer;
		mgm.mgEvent.ResID = -1; // ResID没有用处，只用callId就足够了。
		mgm.mgEvent.EventID = CallAgentImpl
				.turnMediaEventTypeT2MGEventID(event.eventType);
		mgm.mgEvent.Reason = CallAgentImpl
				.turnMediaEventTypeT2MGEventReason(event.eventCause);
	}

	CMSMessage(ServiceInfoT serviceInfo) throws Exception
	{
		if (serviceInfo.connectionList.length < 1)
		{
			throw new Exception("Inbound事件缺少Connection。");
		}

		messageType = SG_MSG;

		sgm.sgEvent = new SGEvent();
		sessionId = serviceInfo.sessionID;
		connectionId = serviceInfo.connectionList[0].connectionID;
		enterpriseId = serviceInfo.enterpriseID;
		accountId = serviceInfo.accountID;
		sgm.timestamp = serviceInfo.timeStamp;
		sgm.sgEvent.EventID = Constant.SG_INCOMINGCALL;

		// 此时消息中的callid存放的其实是connectionid。
		sgm.sgEvent.CallID = serviceInfo.connectionList[0].connectionID;

		sgm.sgEvent.ResID = -1;

		if (serviceInfo.connectionList[0].state.value() == ConnectionStateT._StateConnected)
		{
			// 电话在到达时已经接通，表示是座席转移过来的电话。不能再应答了。
			sgm.timestamp = -sgm.timestamp;
			Log.wDebug(LogDef.id_0x10050000_13, sessionId);
		}

		sgm.sgEvent.CallerID = serviceInfo.connectionList[0].remoteUri;
		sgm.sgEvent.CalledID = serviceInfo.connectionList[0].localUri;
		sgm.sgEvent.OriCallerID = serviceInfo.connectionList[0].origRemoteUri;
		sgm.sgEvent.OriCalledID = serviceInfo.connectionList[0].origLocalUri;
	}

	CMSMessage(SGEvent event, long sessionId, int connectionId, int timestamp)
	{
		this.sessionId = sessionId;
		this.connectionId = connectionId;
		messageType = SG_MSG;

		sgm.timestamp = timestamp;
		sgm.sgEvent = event;
	}

	CMSMessage(SignalEventT event)
	{
		messageType = SG_MSG;

		sgm.sgEvent = new SGEvent();
		sessionId = event.sessionID;
		connectionId = event.connectionID;
		sgm.sgEvent.CalledID = event.connectionObj.localUri;
		sgm.sgEvent.CallerID = event.connectionObj.remoteUri;
		sgm.sgEvent.OriCalledID = event.connectionObj.origLocalUri;
		sgm.sgEvent.OriCallerID = event.connectionObj.origRemoteUri;
		sgm.sgEvent.ResID = -1;
		sgm.sgEvent.EventID = CallAgentImpl
				.turnSignalEventTypeT2SGEventID(event.eventType);
		sgm.sgEvent.EventData = CallAgentImpl
				.turnSignalFailedReasonT2SGEventData(event.failedReason);
		sgm.timestamp = event.timeStamp;
	}
}

/**
 * CMS消息处理线程。CMS发过来的消息先入队列，然后再由此线程异步处理。防止阻塞CMS的调用端。
 * 
 * @author wuxz
 */
class CMSMessageProcessor extends Thread
{
	LinkedBlockingQueue<CMSMessage> msgQueue = new LinkedBlockingQueue<CMSMessage>();

	boolean processCMSMessage(CMSMessage msg) throws Exception
	{
		switch (msg.messageType)
		{
		case CMSMessage.SG_MSG:
		{
			if (msg.sgm.sgEvent.EventID == Constant.SG_INCOMINGCALL)
			{
				int timestamp = msg.sgm.timestamp;
				timestamp = (timestamp > 0 ? timestamp : -timestamp);

				// 此时消息中的callid存放的其实是connectionid。
				CallAgentImpl agent = CallAgentImpl.getNewCallAgent(
						msg.sessionId, msg.sgm.sgEvent.CallID,
						msg.enterpriseId, msg.accountId, timestamp,
						(msg.sgm.timestamp < 0), true);

				// 把假的时间再改为真的。
				msg.sgm.timestamp = timestamp;

				// 把callId从connecitonid改成真正的值。
				msg.sgm.sgEvent.CallID = agent.callId;

				msg.sgm.sgEvent.ResID = agent.callId;

				CallAgentImpl.dispatchMessage(msg.sgm);

				Log
						.wDebug(LogDef.id_0x10050027, msg.sessionId, msgQueue
								.size());

				return true;
			}
			else
			{
				CallAgentImpl agent = CallAgentImpl.getCallAgentFromSessionId(
						msg.sessionId, msg.connectionId);
				if (agent == null)
				{
					Log.wWarn(LogDef.id_0x10050028, msg.sgm.sgEvent.EventID,
							msg.sessionId, msgQueue.size());
					return false;
				}

				msg.sgm.sgEvent.CallID = agent.callId;

				CallAgentImpl.dispatchMessage(msg.sgm);

				Log.wDebug(LogDef.id_0x10050029, msg.sgm.sgEvent.EventID,
						msg.sessionId, msgQueue.size());

				return true;
			}
		}

		case CMSMessage.MG_MSG:
		{
			CallAgentImpl agent = CallAgentImpl.getCallAgentFromSessionIdMepId(
					msg.sessionId, msg.mepId);
			if (agent == null)
			{
				Log.wWarn(LogDef.id_0x10050030, msg.mgm.mgEvent.EventID,
						msg.sessionId, msgQueue.size());
				return false;
			}

			msg.mgm.mgEvent.CallID = agent.callId;

			CallAgentImpl.dispatchMessage(msg.mgm);

			Log.wDebug(LogDef.id_0x10050031, msg.mgm.mgEvent.EventID,
					msg.sessionId, msgQueue.size());

			return true;
		}
		}

		return false;
	}

	@Override
	public void run()
	{
		setName("CallAgent4CCOD CMSMessageProcessor");

		while (true)
		{
			try
			{
				CMSMessage msg = msgQueue.take();
				processCMSMessage(msg);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10150000, e);
			}
		}
	}
}