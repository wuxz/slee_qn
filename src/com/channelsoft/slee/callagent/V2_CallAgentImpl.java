package com.channelsoft.slee.callagent;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import Ice.IntHolder;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.channelmanager.AtsSGEvent;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

class AsynOperator extends Thread
{
	static AsynOperator asynOperator = null;

	static void releaseCall(CallAgent callAgent, int port, int callId)
	{
		if (asynOperator == null)
		{
			asynOperator = new AsynOperator();
			asynOperator.threadPool = new ThreadPoolExecutor(5, 5, 10,
					TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100),
					new ThreadPoolExecutor.DiscardOldestPolicy());
		}
		AsynReleaseCallTask oi = new AsynReleaseCallTask();
		oi.port = port;
		oi.callId = callId;
		oi.callAgent = callAgent;
		asynOperator.threadPool.execute(oi);
	}

	ThreadPoolExecutor threadPool = null;
}

class AsynReleaseCallTask implements Runnable, Serializable
{
	private static final long serialVersionUID = -5194127552173248293L;

	CallAgent callAgent = null;

	public int callId;

	public int port;

	@Override
	public void run()
	{
		Log.wInfo("[port=%d callId=%d]挂断外呼通道。size[%d]", port, callId,
				AsynOperator.asynOperator.threadPool.getQueue().size());
		callAgent.atsReleaseCall(port, callId, 0, false);
	}
}

interface AlertingMgOperator
{
	int callMg(V2_VGProxy vgProxy, V2_CallAgentImpl agent);
	int doMgOperation(int timeout);
}

class AlertingRecord implements AlertingMgOperator
{
	String fileName;

	char endFlag;

	int maxTime;

	int rate;
	
	V2_VGProxy vgProxy;
	
	V2_CallAgentImpl agent;

	AlertingRecord(String fileName, char endFlag, int maxTime, int rate)
	{
		this.fileName = fileName;
		this.endFlag = endFlag;
		this.maxTime = maxTime;
		this.rate = rate;
		vgProxy = null;
		agent = null;
	}
	
	@Override
	public int callMg(V2_VGProxy vgProxy, V2_CallAgentImpl agent)
	{
		if (vgProxy == null || !vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgRecordVoice(agent.resId, agent.callId, fileName,
					"" + endFlag, maxTime, rate);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}
		if (result != Constant.GATEWAY_SUCCESS)
		{
			Log.wWarn("Call mgRecordVoice Error[%d]!", result);
			return result;
		}

		this.vgProxy = vgProxy;
		this.agent = agent;
		return result;
	}

	@Override
	public int doMgOperation(int timeout)
	{
		if (vgProxy == null || !vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}
		
		return agent.doMgOperation(agent.callId, timeout);
	}
}

/**
 * 呼叫控制接口的实现类。所有的呼叫控制接口都由此类实现。
 * <p>
 * 由于底层网关提供的接口类型不同，呼叫控制有多种实现方式，差异由VGProxy的实现类封装。
 * 
 * @author wuxz
 */
public class V2_CallAgentImpl implements CallAgent
{
	static int SEC = 1000;

	/**
	 * 要发送给ChannelManager的信令事件队列。
	 */
	static LinkedBlockingQueue<AtsSGEvent> sgEventQueue = new LinkedBlockingQueue<AtsSGEvent>();

	private static ISysCfgData sysCfgData;

	public static V2_VGProxy vgProxy;

	public static void dispatchMessage(V2_EventMessage msg) throws Exception
	{
		switch (msg.messageType)
		{
		case Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT:
		{
			SGEvent event = msg.getSgEvent();
			V2_CallAgentImpl agent = getCallAgent(event.ResID, event.CallID);

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
					V2_CallStat.addEvent(agent.callId, sgEvent.port, msg,
							event.CallerID, event.CalledID,
							Constant.SG_INCOMINGCALL);
				}
				return;
			}

			case Constant.SG_CALLDISCONNECTED:
			{
				for (int i = 0; i < UnifiedServiceManagement.callEventListeners.length; i++)
				{
					try
					{
						if (agent.hostAgent != null)
						{
							UnifiedServiceManagement.callEventListeners[i]
									.on3rdPartyDisconnect(
											agent.hostAgent.resId,
											agent.hostAgent.callId,
											agent.resId, agent.callId,
											Constant.REASON_DESTUNKNOWN);
						}
						else
						{
							UnifiedServiceManagement.callEventListeners[i]
									.onDisconnect(agent.resId, agent.callId,
											-1, -1, Constant.REASON_DESTUNKNOWN);
						}
					}
					catch (Throwable e)
					{
						Log.wException(LogDef.id_0x10130000, e);
					}
				}
			}
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

				if ((agent != null) && (agent.callId == event.CallID))
				{
					agent.onDisconnect();
				}

				sgEventQueue.add(sgEvent);

				if (agent != null)
				{
					boolean isHostAgent = ((agent != null) && (agent.hostAgent == null));

					if (isHostAgent)
					{
						V2_CallStat
								.addEvent(agent.callId, sgEvent.port, msg,
										agent.ani, agent.dnis,
										Constant.SG_CALLRELEASED);
					}

					V2_CallSession.bill(sgEvent.port, event.CallID, msg);

					V2_CallSession.clear(agent.callId);

					agent.mgMsgQueue.add(msg);
				}
			}
				break;

			case Constant.SG_CALLCONNECTED:
			{
				V2_CallSession.updatebymsg(msg);

				boolean isHostAgent = ((agent != null) && (agent.hostAgent == null));

				if (isHostAgent)
				{
					V2_CallStat.addEvent(agent.callId, event.ResID, msg, msg
							.getSgEvent().CallerID, msg.getSgEvent().CalledID,
							Constant.SG_CALLCONNECTED);
				}
			}
				break;

			case Constant.SG_CALLALERTING:
			{
				V2_CallSession.updatebymsg(msg);

				boolean isHostAgent = ((agent != null) && (agent.hostAgent == null));

				if (isHostAgent)
				{
					V2_CallStat.addEvent(agent.callId, event.ResID, msg, msg
							.getSgEvent().CallerID, msg.getSgEvent().CalledID,
							Constant.SG_CALLALERTING);
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
			MGEvent event = msg.getMgEvent();
			V2_CallAgentImpl agent = getCallAgent(event.ResID, event.CallID);

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
						result = vgProxy.mgPlayVoice(agent.resId, agent.callId,
								agent.playVoiceFileName,
								agent.playVoiceInterruptKeys,
								agent.playVoiceRate, 0);
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

	public static synchronized V2_CallAgentImpl getCallAgent(int port,
			int callId)
	{
		return (V2_CallAgentImpl) V2_CallSession.getCallAgent(port, callId);
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
		case Constant.REASON_UNSUPPORTED_MEDIA:
			return Constant.Err_UnsupportedMedia;
		case Constant.REASON_MEDIA_NEGOTIATE:
			return Constant.Err_UnsupportedMedia;
		default:
			return Constant.ERR_GeneralError;
		}
	}

	public String ani;

	/**
	 * （CCOD中）只使用自己维护的callId标志呼叫，不使用resId等。
	 */
	public int callId = -1;

	int callState = Constant.StatusIdle;

	/**
	 * 单步会议时创建的会议ID
	 */
	public int confId = -1;

	public String dnis;

	/**
	 * 三方呼叫中，本方呼叫的宿主呼叫。
	 */
	V2_CallAgentImpl hostAgent = null;

	boolean isPlayingVoiceAsynch = false;

	boolean isVideoCall = false;

	LinkedBlockingQueue<V2_EventMessage> mgMsgQueue = new LinkedBlockingQueue<V2_EventMessage>();

	public String oriAni;

	public String oriDnis;

	V2_CallAgentImpl otherPartyAgent = null;

	String playVoiceFileName;

	String playVoiceInterruptKeys;

	int playVoiceRate;

	String reserved;

	public int resId = -1;

	LinkedBlockingQueue<V2_EventMessage> sgMsgQueue = new LinkedBlockingQueue<V2_EventMessage>();

	int answerCall(int callId, int timeout)
	{
		try
		{
			V2_EventMessage msg = null;
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
					SGEvent event = msg.getSgEvent();
					Log.wDebug(LogDef.id_0x10050023, resId, callId,
							event.ResID, event.CallID, event.EventID);

					if (V2_CallSession
							.isSupport(V2_CallSession.FUN_CHECKMESSAGE))
					{
						if ((event.ResID != resId) || (event.CallID != callId))
						{
							if (event.CallID > callId)
							{
								Log.wError(LogDef.id_0x10150010, resId, callId);
								sgMsgQueue.add(msg);
								return Constant.ERR_GeneralError;
							}

							continue;
						}
					}

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
		catch (Throwable e)
		{
			return Constant.ERR_RPC;
		}
	}

	public int atsAdjustSpeed(int port, int callId, int adjment)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;
		try
		{
			result = vgProxy.mgAdjustSpeed(port, callId, adjment);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return result;
	}

	public int atsAdjustVolume(int port, int callId, int adjment)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;
		try
		{
			result = vgProxy.mgAdjustVolume(port, callId, adjment);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.callagent.CallAgent#atsAnswerCall(int, int,
	 * int)
	 */
	public int atsAnswerCall(int port, int callId, int reserved)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;

		try
		{
			result = vgProxy.sgAnswerCall(port, callId);
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

		if (!V2_CallSession.isOldCall(callId))
		{
			int timeout = 30 * SEC;

			result = agent.answerCall(callId, timeout);

			if (result != Constant.ERR_Success)
			{
				V2_CallSession.clear(agent.callId);
			}
		}
		else
		{
			result = Constant.ERR_Success;
		}

		return result;
	}

	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();
		agent.stopIo();

		int ret;
		try
		{
			String grammarStr = new String(grammar.getBytes("GB2312"),
					"ISO-8859-1");
			for (int i = 0; i < fileCount; i++)
			{
				String fileName = new String(voiceFileList[i]
						.getBytes("GB2312"), "ISO-8859-1");
				voiceFileList[i] = fileName;
			}
			ret = vgProxy.mgASR(port, callId, -1, interruptKeys, rate,
					fileCount, voiceFileList, grammarStr, noSpeechTimeout,
					acceptScore, resultNum, timeoutBetweenWord,
					Constant.TTS_VOICE_LIB_WOMAN);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			ret = Constant.ERR_RPC;
		}
		if (ret != Constant.GATEWAY_SUCCESS)
		{
			return ret;
		}

		StringWrapper reply = new StringWrapper();
		ret = agent.doMgOperation(callId, 3 * 60 * SEC,
				Constant.SOFTSWITCH_MESSAGE_REPLY_ASR, reply);
		if (ret == Constant.ERR_Success)
		{
			result.value = reply.value;
		}

		return ret;
	}

	public int atsASR2(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord, int DTMF_dtmfCount,
			String DTMF_endFlag, int DTMF_timeoutSecond, int DTMF_betweenTimeout)
	{
		return atsASR(port, callId, interruptKeys, rate, fileCount, result,
				voiceFileList, grammar, noSpeechTimeout, acceptScore,
				resultNum, timeoutBetweenWord);
	}

	public int atsClearDTMFBuffer(int port, int callId)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();

		try
		{
			int result = vgProxy.mgFlushBuffer(port, callId);
			return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
					: result);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			return Constant.ERR_RPC;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.callagent.CallAgent#atsCloseConf()
	 */
	public int atsCloseConf(int port, int callId)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
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
			result = vgProxy.rmBlindCloseConf(port, callId, agent.confId);
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
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = null;
		agent = checkCallState(port1, callId1);
		if ((agent == null)
				&& (checkCallState(port2, V2_CallSession.getAgentId(callId1,
						port2)) == null))
		{
			return Constant.ERR_NoCall;
		}

		if (agent == null)
		{
			agent = getCallAgent(port1, callId1);
			if (agent == null)
			{
				return Constant.ERR_NoCall;
			}
		}

		if (port2 == -1)
		{
			if ((agent.callState == Constant.StatusConference)
					&& (agent.otherPartyAgent != null)
					&& (agent.otherPartyAgent.callState == Constant.StatusConnect))
			{
				port2 = V2_CallSession.getAgentId(agent.otherPartyAgent.resId,
						agent.otherPartyAgent.callId);
			}
			else
			{
				return Constant.ERR_NoCall;
			}
		}

		int result;

		try
		{
			result = vgProxy.rmUnrouteTwoRes(V2_CallSession.getAgentId(port1,
					callId1), port2, reserved);
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

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		ani.value = agent.ani;
		dnis.value = agent.dnis;
		oriAni.value = agent.oriAni;
		oriDnis.value = agent.oriDnis;
		reserved.value = agent.reserved;
		isVideoCall.value = agent.isVideoCall;

		return Constant.ERR_Success;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.callagent.CallAgent#atsGetOtherPartyCallSn(int,
	 * int)
	 */
	public int atsGetOtherPartyCallSn(int port, int callId)
	{
		V2_CallAgentImpl agent = checkCallState(port, callId);
		if ((agent == null) || (agent.otherPartyAgent == null))
		{
			return -1;
		}

		return agent.otherPartyAgent.callId;
	}

	public int atsGetOtherPartyTrunkID(int port, int callId)
	{
		V2_CallAgentImpl agent = checkCallState(port, callId);
		if ((agent == null) || (agent.otherPartyAgent == null))
		{
			return -1;
		}

		return V2_CallSession.getAgentId(agent.otherPartyAgent.resId,
				agent.otherPartyAgent.callId);
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

		return event;
	}

	public int atsInitialize(ISysCfgData syscfgdata)
	{
		V2_CallAgentImpl.sysCfgData = syscfgdata;

		try
		{
			return vgProxy.VGInitialize(syscfgdata);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);
			return 0;
		}
	}

	public int atsInitialize(String mgIp, int mgPort, String sgIp, int sgPort,
			String monitorDnis)
	{
		// try
		// {
		// return vgProxy
		// .VGInitialize(mgIp, mgPort, sgIp, sgPort, monitorDnis);
		// }
		// catch (Throwable e)
		// {
		// Log.wException(LogDef.id_0x10150000, e);
		// Log.wDebug(LogDef.id_0x10250003, sgIp, sgPort);
		// return 0;
		// }
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.channelsoft.slee.callagent.CallAgent#atsMakeCall(com.channelsoft.
	 * reusable.util.IntWrapper, java.lang.String, java.lang.String,
	 * com.channelsoft.reusable.util.IntWrapper, int, int, int)
	 */
	public int atsMakeCall(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall)
	{
		// CCOD:不支持普通外呼，只支持三方呼叫。
		if (!V2_CallSession.isSupport(V2_CallSession.FUN_NORMAL_MAKECALL))
		{
			return Constant.ERR_GeneralError;
		}
		else
		{
			return atsMakeCall(port, callingNumber, oriCallingNumber,
					destNumber, oriDestNumber, callId, timeout, reserved,
					transactionId, false, 0, false, isVideoCall, null);
		}
	}

//	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
//			IntWrapper callId, int timeout, int reserved, int transactionId,
//			boolean routeMedia, boolean isVideoCall)
//	{
//		return atsMakeCall(port, callingNumber, destNumber, callId, timeout,
//				reserved, transactionId, routeMedia, 0, false, isVideoCall);
//	}

//	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
//			IntWrapper callId, int timeout, int reserved, int transactionId,
//			boolean routeMedia, int mode, boolean reverseRoute,
//			boolean isVideoCall)
//	{
//		return atsMakeCall(port, callingNumber, destNumber, callId, timeout,
//				reserved, transactionId, routeMedia, mode, reverseRoute,
//				isVideoCall, null);
//	}
//	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
//			IntWrapper callId, int timeout, int reserved, int transactionId,
//			boolean routeMedia, int mode, boolean reverseRoute,
//			boolean isVideoCall, AlertingMgOperator alertingMgOpr)
//	{
//		return atsMakeCall(port, callingNumber, null, destNumber, null, callId,
//				timeout, reserved, transactionId, routeMedia, mode,
//				reverseRoute, isVideoCall, alertingMgOpr);
//	}
	int atsMakeCall(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean routeMedia, int mode, boolean reverseRoute,
			boolean isVideoCall, AlertingMgOperator alertingMgOpr)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		IntWrapper agentId = V2_CallSession.getAgentId(port, callId);
		if ((agentId != null) && (agentId.value != -1))
		{
			V2_CallAgentImpl callAgent = getCallAgent(port.value, callId.value);
			if (callAgent == null)
			{
				return Constant.ERR_GeneralError;
			}

			if ((callAgent.otherPartyAgent != null)
					&& (V2_CallSession.getAgentId(
							callAgent.otherPartyAgent.resId,
							callAgent.otherPartyAgent.callId) != -1))
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

		try
		{
			result = vgProxy.sgBlindMakeCall(V2_CallSession.getAgentId(port,
					callId).value, callingNumber, oriCallingNumber, destNumber,
					oriDestNumber, timeout / 1000, portHolder, callIdHolder,
					isVideoCall);
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
		V2_CallAgentImpl agent = getCallAgent(portHolder.value,
				callIdHolder.value);
		if (agent == null)
		{
			return Constant.ERR_GeneralError;
		}
		agent.callId = callIdHolder.value;
		agent.callState = Constant.StatusConnect;
		agent.ani = callingNumber;
		agent.dnis = destNumber;
		agent.oriAni = agent.oriDnis = "";

		if (routeMedia)
		{
			int ret = Constant.ERR_GeneralError;
			if (reverseRoute)
			{
				ret = atsSwitchTwoPort(portHolder.value, callIdHolder.value,
						port.value, mode);
				Log.wDebug(LogDef.id_0x1005000E, portHolder.value,
						callIdHolder.value, port.value, callId.value, ret);
			}
			else
			{
				ret = atsSwitchTwoPort(port.value, callId.value,
						portHolder.value, mode);
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

		V2_CallAgentImpl oriAgent = null;
		if (V2_CallSession.getAgentId(port, callId).value >= 0)
		{
			oriAgent = getCallAgent(port.value, callId.value);

			if (oriAgent.callState != Constant.StatusIdle)
			{
				oriAgent.callState = Constant.StatusConference;
			}

			oriAgent.otherPartyAgent = agent;
			agent.hostAgent = oriAgent;
		}

		for (int i = 0; i < UnifiedServiceManagement.callEventListeners.length; i++)
		{
			try
			{
				if (agent.hostAgent == null)
				{
					UnifiedServiceManagement.callEventListeners[i].onMakeCall(
							portHolder.value, callIdHolder.value, -1, -1,
							callingNumber, destNumber);
				}
				else
				{
					UnifiedServiceManagement.callEventListeners[i].onMakeCall(
							agent.hostAgent.resId, agent.hostAgent.callId,
							portHolder.value, callIdHolder.value,
							callingNumber, destNumber);
				}

			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		int ret = agent.makeCall(agent.callId, timeout, alertingMgOpr);
		if (ret == Constant.ERR_Success)
		{
			port.value = V2_CallSession.getAgentId(agent.resId, agent.callId);
			callId.value = agent.callId;
		}
		else
		{
			if (ret == Constant.ERR_TimeOut)
			{
				Log.wDebug(LogDef.id_0x1005000F, transactionId, V2_CallSession
						.getAgentId(agent.resId, agent.callId), agent.callId,
						callingNumber, destNumber);

				atsReleaseCall(V2_CallSession.getAgentId(agent.resId,
						agent.callId), agent.callId, 0);
			}

			if (agent.callId == callIdHolder.value)
			{
				agent.callState = Constant.StatusIdle;
			}

			if (oriAgent != null)
			{
				if (oriAgent.callState != Constant.StatusIdle)
				{
					oriAgent.callState = Constant.StatusConnect;
				}

				oriAgent.otherPartyAgent = null;
			}

			agent.hostAgent = null;

			V2_CallSession.clear(agent.callId);

			return ret;
		}

		// send DTMF
		if (shouldSendDTMF)
		{
			Log.wTrace(LogDef.id_0x10050000_3, transactionId, port.value,
					callId.value, 3);
			try
			{
				Thread.sleep(3 * SEC);
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
				if (V2_CallSession
						.isSupport(V2_CallSession.FUN_RELEASE_IF_MAKECALL_SENDDTMFERR))
				{
					Log.wDebug(LogDef.id_0x10050000_6, agent.resId,
							agent.callId, transactionId, callingNumber,
							destNumber);

					atsReleaseCall(agent.callId, agent.callId, 0);
				}
				return error;
			}
		}
		// end send DTMF

		return ret;
	}

	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String voiceFileList[], int voiceLib)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();
		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgPlayList(port, callId, interruptKeys, rate,
					fileCount, voiceFileList, voiceLib);
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

		int timeout = 30 * 60 * SEC;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();
		agent.stopIo();

		int result;

		try
		{
			result = vgProxy.mgPlayTTS(port, callId, interruptKeys, type, text,
					voiceLib, 6);
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

		int timeout = 30 * 60 * SEC;

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
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();
		agent.stopIo();

		int result;
		try
		{
			if (secondsPlayed != null)
			{
				result = vgProxy.mgPlayVoice(port, callId, fileName,
						interruptKeys, rate, secondsPlayed.value);
			}
			else
			{
				result = vgProxy.mgPlayVoice(port, callId, fileName,
						interruptKeys, rate, 0);
			}
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

		int timeout = 30 * 60 * SEC;
		if ((secondsPlayed == null)
				|| !V2_CallSession
						.isSupport(V2_CallSession.FUN_MAXUSERIDLESECONDS))
		{
			return agent.doMgOperation(callId, timeout);
		}
		else
		{
			return agent.doMgOperation(callId, timeout, -1, null,
					secondsPlayed, lastInteractionTime);
		}

	}

	public int atsPlayVoiceAsync(int port, int callId, String fileName,
			String interruptKeys, int rate)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgPlayVoice(port, callId, fileName, interruptKeys,
					rate, 0);
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

		agent.playVoiceFileName = fileName;

		agent.playVoiceInterruptKeys = interruptKeys;

		agent.playVoiceRate = rate;

		return Constant.ERR_Success;
	}

	public int atsQueryCallState(int port, int callId, IntWrapper callStatus)
	{
		V2_CallAgentImpl agent = getCallAgent(port, callId);
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
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		buffer.value = "";

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgReceiveDTMF(port, callId, keyCount,
					interruptKeys, maxTime, betweenTime);
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
		result = agent.doMgOperation(callId, 5 * 60 * SEC,
				Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF, reply);
		// if (result == Constant.ERR_Success)
		{
			buffer.value = reply.value;
		}

		return result;
	}

	public int atsReceiveFax(int port, int callId, String fileName)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgReceiveFax(port, callId, fileName);
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

		int timeout = 30 * 60 * SEC;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsRecordVoice(int port, int callId, String fileName,
			char endFlag, int maxTime, int rate)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgRecordVoice(port, callId, fileName,
					"" + endFlag, maxTime, rate);
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

		int timeout = 3600 * SEC;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsReleaseCall(int port, int callId, int reserved)
	{
		return atsReleaseCall(port, callId, reserved, true);
	}

	public int atsReleaseCall(int port, int callId, int reserved, boolean wait)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		try
		{
			int ret = vgProxy.sgReleaseCall(port, callId);
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
			int timeout = 3 * SEC;

			agent.releaseCall(callId, timeout);
		}

		return Constant.ERR_Success;
	}

	public int atsRetrieveCall(int port, int callId, int reserved)
	{
		V2_CallAgentImpl agent = checkCallState(port, callId);
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

		return atsReleaseCall(V2_CallSession.getAgentId(
				agent.otherPartyAgent.resId, agent.otherPartyAgent.callId),
				agent.otherPartyAgent.callId, 0);
	}

	public int atsSendFax(int port, int callId, String fileName)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.mgSendFax(port, callId, fileName);
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

		int timeout = 30 * 60 * SEC;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsSendSignals(int port, int callId, String signals)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		try
		{
			return (vgProxy.mgSendDTMF(port, callId, signals) == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
					: Constant.ERR_GeneralError);
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
		if (!V2_CallSession.isSupport(V2_CallSession.FUN_SINGLESTEPCONFERENCE))
		{
			return Constant.ERR_GeneralError;
		}
		else
		{
			return atsSingleStepTransfer(port, callId, callingNumber,
					oriCallingNumber, destNumber, oriDestNumber, timeout,
					routeMediaFirst, reserved, transactionId, true, 0, false,
					isVideoCall, "", "");
		}
	}

//	public int atsSingleStepTransfer(int port, int callId,
//			String callingNumber, String destNumber, int timeout,
//			boolean routeMediaFirst, int reserved, int transactionId,
//			boolean isVideoCall)
//	{
//		return atsSingleStepTransfer(port, callId, callingNumber, destNumber,
//				timeout, routeMediaFirst, reserved, transactionId, false,
//				isVideoCall);
//	}
//
//	public int atsSingleStepTransfer(int port, int callId,
//			String callingNumber, String destNumber, int timeout,
//			boolean routeMediaFirst, int reserved, int transactionId,
//			boolean createConf, boolean isVideoCall)
//	{
//		return atsSingleStepTransfer(port, callId, callingNumber, destNumber,
//				timeout, routeMediaFirst, reserved, transactionId, createConf,
//				0, false, isVideoCall);
//	}
//
//	public int atsSingleStepTransfer(int port, int callId,
//			String callingNumber, String destNumber, int timeout,
//			boolean routeMediaFirst, int reserved, int transactionId,
//			boolean createConf, int mode, boolean reverseRoute,
//			boolean isVideoCall)
//	{
//		return atsSingleStepTransfer(port, callId, callingNumber, destNumber,
//				timeout, routeMediaFirst, reserved, transactionId, createConf,
//				mode, reverseRoute, isVideoCall, "", "");
//	}
//
//	public int atsSingleStepTransfer(int port, int callId,
//			String callingNumber, String destNumber, int timeout,
//			boolean routeMediaFirst, int reserved, int transactionId,
//			boolean createConf, int mode, boolean reverseRoute,
//			boolean isVideoCall, String videoUrl1, String videoUrl2)
//	{
//		return atsSingleStepTransfer(port, callId, callingNumber, "",
//				destNumber, "", timeout, routeMediaFirst, reserved,
//				transactionId, createConf, mode, reverseRoute, isVideoCall,
//				videoUrl1, videoUrl2);
//	}
	
	public int atsSingleStepTransfer(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean createConf, int mode,
			boolean reverseRoute, boolean isVideoCall, String videoUrl1,
			String videoUrl2)
	{
		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		// if (agent.isVideoCall != isVideoCall)
		// {
		// return Constant.Err_UnsupportedMedia;
		// }

		Ice.IntHolder result = new Ice.IntHolder();
		if (vgProxy.shiftService(port, callId, callingNumber, destNumber,
				result))
		{
			return result.value;
		}

		IntWrapper portWrapper = new IntWrapper();
		IntWrapper callIdWrapper = new IntWrapper();
		portWrapper.value = port;
		callIdWrapper.value = callId;

		int ret = atsMakeCall(portWrapper, callingNumber, oriCallingNumber,
				destNumber, oriDestNumber, callIdWrapper, timeout * SEC,
				reserved, transactionId, routeMediaFirst, mode, reverseRoute,
				isVideoCall, null);
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
				ret = atsSwitchTwoPort(portWrapper.value, callId, port, mode);
				Log.wDebug(LogDef.id_0x1005000E, portWrapper.value,
						callIdWrapper.value, port, callId, ret);
			}
			else
			{
				ret = atsSwitchTwoPort(port, callId, portWrapper.value, mode);
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

		ret = agent.blindCreateConference(port, callId, portWrapper.value,
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
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgProxy.rmStartConferrenceRecord(port, callId,
					recordFileName);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2, int mode)
	{
		return atsSwitchTwoPort(port1, callId1, port2, mode, "", "");
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2, int mode,
			String videoUrl1, String videoUrl2)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = null;
		agent = checkCallState(port1, callId1);
		if ((agent == null)
				&& (checkCallState(port2, V2_CallSession.getAgentId(callId1,
						port2)) == null))
		{
			return Constant.ERR_NoCall;
		}

		if (agent == null)
		{
			agent = getCallAgent(port1, callId1);
			if (agent == null)
			{
				return Constant.ERR_NoCall;
			}
		}

		if (port2 == -1)
		{
			if ((agent.callState == Constant.StatusConference)
					&& (agent.otherPartyAgent != null)
					&& (agent.otherPartyAgent.callState == Constant.StatusConnect))
			{
				port2 = V2_CallSession.getAgentId(agent.otherPartyAgent.resId,
						agent.otherPartyAgent.callId);
			}
			else
			{
				return Constant.ERR_NoCall;
			}
		}
		else
		{
			if (V2_CallSession
					.isSupport(V2_CallSession.FUN_CHECKCALL2_IN_SWITCH))
			{
				// port2这里是callId
				if (checkCallState(port2, V2_CallSession.getAgentId(callId1,
						port2)) == null)
				{
					return Constant.ERR_NoCall;
				}
			}
		}

		int result = 0;
		try
		{
			result = vgProxy.rmRouteTwoRes(V2_CallSession.getAgentId(port1,
					callId1), port2, mode, videoUrl1, videoUrl2);
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
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();
		agent.stopIo();

		buffer.value = "";
		int result;
		try
		{
			result = vgProxy.mgVoiceEdit(port, callId, fileName, rate, 0,
					keyCount, interruptKeys, maxTime, betweenTime);
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
		result = agent.doMgOperation(callId, 5 * 60 * SEC,
				Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF, reply);
		// if (result == Constant.ERR_Success)
		{
			buffer.value = reply.value;
		}

		return result;
	}

	@Override
	public int atsVoiceListEdit(int port, int callId, int rate, int fileCount,
			String[] voiceFileList, int voiceLib, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.checkMediaState();
		agent.stopIo();

		int result;
		buffer.value = "";
		try
		{
			result = vgProxy.mgVoiceListEdit(port, callId, rate, fileCount,
					voiceFileList, voiceLib, keyCount, interruptKeys, maxTime,
					betweenTime);
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
		result = agent.doMgOperation(callId, 5 * 60 * SEC,
				Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF, reply);
		// if (result == Constant.ERR_Success)
		{
			buffer.value = reply.value;
		}

		return result;
	}

	public void attachCall(int port, int callID)
	{
		if (!V2_CallSession.isSupport(V2_CallSession.FUN_ATTACHCALL))
		{
			return;
		}

		V2_CallAgentImpl agent = checkCallState(port, callID);
		if (agent == null)
		{
			return;
		}
		agent.updateHostParty();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#attachOtherParty(int, int)
	 */
	public void attachOtherParty(int port, int callID, int otherPort,
			int otherCallId)
	{
		if (!V2_CallSession.isSupport(V2_CallSession.FUN_ATTACHCALL))
		{
			return;
		}

		V2_CallAgentImpl agent = checkCallState(port, callID);
		if (agent == null)
		{
			return;
		}

		if (agent.otherPartyAgent != null)
		{
			agent.otherPartyAgent.updateHostParty();
		}

		V2_CallAgentImpl otherAgent = checkCallState(otherPort, otherCallId);
		if (otherAgent == null)
		{
			return;
		}

		otherAgent.updateHostParty();

		otherAgent.hostAgent = agent;
		agent.otherPartyAgent = otherAgent;
		agent.callState = Constant.StatusConference;
	}

	public int blindCreateConference(int port1, int callId, int port2,
			boolean isVideoCall)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port1, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;

		try
		{
			IntHolder confId = new IntHolder();
			result = vgProxy.rmBlindCreateConf(port1, port2, callId, confId,
					isVideoCall);
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

	V2_CallAgentImpl checkCallState(int port, int callId)
	{
		V2_CallAgentImpl agent = getCallAgent(port, callId);

		agent = ((agent != null) && (agent.callId == callId) && (agent.callState != Constant.StatusIdle)) ? agent
				: null;
		if (agent != null)
		{
			agent.mgMsgQueue.clear();
		}

		return agent;
	}

	int checkMediaState()
	{
		return vgProxy.attachMedia(callId);
	}

	public void clearMessages()
	{
		mgMsgQueue.clear();
		sgMsgQueue.clear();
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
			V2_EventMessage msg = null;
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
					SGEvent sgReply = msg.getSgEvent();
					if (V2_CallSession
							.isSupport(V2_CallSession.FUN_CHECKMESSAGE))
					{
						if ((sgReply.ResID != resId)
								|| (sgReply.CallID != callId))
						{
							if (sgReply.CallID > callId)
							{
								Log.wError(LogDef.id_0x10150012, resId, callId);
								mgMsgQueue.add(msg);
								return Constant.ERR_NoCall;
							}
							continue;
						}
					}

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
					MGEvent mgReply = msg.getMgEvent();
					if (V2_CallSession
							.isSupport(V2_CallSession.FUN_CHECKMESSAGE))
					{
						if ((mgReply.ResID != resId)
								|| (mgReply.CallID != callId))
						{
							if (mgReply.CallID > callId)
							{
								Log.wError(LogDef.id_0x10150013, resId, callId);
								mgMsgQueue.add(msg);
								return Constant.ERR_NoCall;
							}

							continue;
						}
					}

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
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			return Constant.ERR_RPC;
		}
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

	int makeCall(int callId, int timeout, AlertingMgOperator alertingMgOpr)
	{
		try
		{
			V2_EventMessage msg = null;
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
					SGEvent sgReply = msg.getSgEvent();

					Log.wDebug(LogDef.id_0x10050006, resId, callId,
							sgReply.ResID, sgReply.CallID, sgReply.EventID,
							sgReply.EventData);

					if (V2_CallSession
							.isSupport(V2_CallSession.FUN_CHECKMESSAGE))
					{
						if ((sgReply.ResID != resId)
								|| (sgReply.CallID != callId))
						{
							if (sgReply.CallID > callId)
							{
								Log.wError(LogDef.id_0x10150014, resId, callId);
								mgMsgQueue.add(msg);
								return Constant.ERR_GeneralError;
							}
							continue;
						}
					}

					switch (sgReply.EventID)
					{
					case Constant.SG_CALLCONNECTED:
					{
						for (int i = 0; i < UnifiedServiceManagement.callEventListeners.length; i++)
						{
							try
							{
								if (hostAgent == null)
								{
									UnifiedServiceManagement.callEventListeners[i]
											.onConnect(
													resId,
													callId,
													otherPartyAgent == null ? -1
															: otherPartyAgent.resId,
													otherPartyAgent == null ? -1
															: otherPartyAgent.callId);
								}
								else
								{
									UnifiedServiceManagement.callEventListeners[i]
											.on3rdPartyConnect(hostAgent.resId,
													hostAgent.callId, resId,
													callId);
								}
							}
							catch (Throwable e)
							{
								Log.wException(LogDef.id_0x10130000, e);
							}
						}
					}
						return Constant.ERR_Success;

					case Constant.SG_CALLDISCONNECTED:
						return translateSGReason(sgReply.EventData);

					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_GeneralError;
					case Constant.SG_CALLALERTING:
					{
						for (int i = 0; i < UnifiedServiceManagement.callEventListeners.length; i++)
						{
							try
							{
								if (hostAgent == null)
								{
									UnifiedServiceManagement.callEventListeners[i]
											.onRemoteAlerting(resId, callId,
													-1, -1);
								}
								else
								{
									UnifiedServiceManagement.callEventListeners[i]
											.on3rdPartyAlerting(
													hostAgent.resId,
													hostAgent.callId, resId,
													callId);
								}
							}
							catch (Throwable e)
							{
								Log.wException(LogDef.id_0x10130000, e);
							}
						}
						//启动振铃媒体操作
						if(alertingMgOpr != null)
						{
							int mgResult = alertingMgOpr.callMg(vgProxy, checkCallState(resId, callId));
							if (mgResult != Constant.GATEWAY_SUCCESS)
							{
								return mgResult;
							}
						}
					}
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

	void onDisconnect() throws Exception
	{
		callState = Constant.StatusIdle;
		isPlayingVoiceAsynch = false;

		releaseOtherParty();
		updateHostParty();

		if (vgProxy.isInitialized()
				&& V2_CallSession
						.isSupport(V2_CallSession.FUN_CLOSECONF_WHEN_DISCONNECTED))
		{
			vgProxy.rmBlindCloseConf(resId, callId, confId);
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
		isVideoCall = (event.mediaType == Constant.VGCP_MEDIA_TYPE_VIDEO);
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
					String ResultStr = "";
//					if (V2_CallSession
//							.isSupport(V2_CallSession.FUN_ENCODE_DTMFSTRING))
//					{
						ResultStr = new String(mgReply.DTMFString
								.getBytes("ISO-8859-1"), "GB2312");
//					}
//					else
//					{
//						ResultStr = mgReply.DTMFString;
//					}

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
			// if (result == Constant.ERR_Success)
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
			V2_EventMessage msg = null;
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
					SGEvent event = msg.getSgEvent();
					Log.wDebug(LogDef.id_0x10050004, resId, callId,
							event.ResID, event.CallID, event.EventID);

					if (V2_CallSession
							.isSupport(V2_CallSession.FUN_CHECKMESSAGE))
					{
						if ((event.ResID != resId) || (event.CallID != callId))
						{
							if (event.CallID > callId)
							{
								Log.wError(LogDef.id_0x10150011, resId, callId);
								sgMsgQueue.add(msg);
								return Constant.ERR_GeneralError;
							}

							continue;
						}
					}

					switch (event.EventID)
					{
					case Constant.SG_CALLDISCONNECTED:
					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_Success;
					default:
						if (V2_CallSession
								.isSupport(V2_CallSession.FUN_RET_ERR_FOR_UNKOWNMSG))
						{
							return Constant.ERR_GeneralError;
						}
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
				Log.wDebug(LogDef.id_0x10050001, otherPartyAgent.resId,
						otherPartyAgent.callId);
				AsynOperator.releaseCall(this, otherPartyAgent.resId,
						otherPartyAgent.callId);
				// atsReleaseCall(otherPartyAgent.resId, otherPartyAgent.callId,
				// 0, false);
			}
		}

		otherPartyAgent = null;
	}

	void stopIo()
	{
		stopIo(false);
	}

	void stopIo(boolean force)
	{
		if (!vgProxy.isInitialized())
		{
			return;
		}

		if (!isPlayingVoiceAsynch && !force)
		{
			return;
		}

		try
		{
			vgProxy.mgStopIO(resId, callId);
		}
		catch (Throwable e)
		{
		}

		isPlayingVoiceAsynch = false;

		doMgOperation(callId, 5 * SEC);
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
		}

		hostAgent = null;
	}

	@Override
	public int atsAppendVideo(int port, int callId, String[][] infoOnVideo)
	{
		if (!vgProxy.isInitialized())
		{
			return Constant.ERR_RPC;
		}

		V2_CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;
		try
		{
			result = vgProxy.mgAppendVideo(port, callId, infoOnVideo);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return result;
	}

	@Override
	public int atsCallRecordRing(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall, String fileName, char endFlag, int maxTime,
			int rate)
	{
		AlertingRecord ar = new AlertingRecord(fileName, endFlag, maxTime, rate);
		int result = atsMakeCall(port, callingNumber, oriCallingNumber,
				destNumber, oriDestNumber, callId, timeout, reserved,
				transactionId, false, 0, false, isVideoCall, ar);
		if (result == Constant.ERR_Success)
		{
			return ar.doMgOperation(3600 * SEC);
		}
		return result;
	}

}
