package com.channelsoft.slee.channelmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import com.channelsoft.reusable.serviceprovider.ReasoningProvider;
import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.capability.CallControlCapability;
import com.channelsoft.slee.capability.TTSSegment;
import com.channelsoft.slee.capability.UICapability;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.perfmonitor.ChannelPerfData;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.usmlreasoning.ATSBrowser;
import com.channelsoft.slee.usmlreasoning.SleepTask;
import com.channelsoft.slee.usmlreasoning.USMLReasoningResult;
import com.channelsoft.slee.usmlreasoning.WaitMessageTask;
import com.channelsoft.slee.util.Constant;

public class ATSChannel extends Thread implements CallControlCapability,
		UICapability
{
	static int errorCount = 0;

	static int finishedCount = 0;

	static Timer serviceTimer = new Timer();

	public String appName;

	public ATSBrowser atsBrowser;

	public String channelDn;

	public int channelDnNum;

	public boolean channelIsIdle;

	IntWrapper channelStatus = new IntWrapper();

	public ChannelType channelType;

	public Contact curContact;

	Vector<String> daemonChannelDnSet = new Vector<String>();

	public Vector<AppInfo> inAppInfos = new Vector<AppInfo>();

	boolean inboundSrvTime;

	boolean isDebugging = false;

	public boolean nowInit;

	public Vector<AppInfo> outAppInfos = new Vector<AppInfo>();

	String parentChannelDn;

	StringWrapper reserved = new StringWrapper();

	long sessionId;

	public boolean shouldExit;

	public ChannelPerfData sleeResPerfData;

	boolean startDebugFlag;

	String startParam;

	StringWrapper szANI = new StringWrapper();

	StringWrapper szDNIS = new StringWrapper();

	StringWrapper szOriANI = new StringWrapper();

	StringWrapper szOriDNIS = new StringWrapper();

	boolean testDialer;

	public Calendar timeOfEnd;

	int trunkResId = -1;

	HashMap<Integer, Integer> userEventFiredMap = new HashMap<Integer, Integer>();

	Vector<Integer> userEventSet = new Vector<Integer>();

	Vector<Integer> userEventSetSaved = new Vector<Integer>();

	String usmlDoc;

	CyclicBarrier waiter = new CyclicBarrier(2);

	public ATSChannel()
	{
	}

	public ATSChannel(String channelDn)
	{
		curContact = new Contact();
		channelType = ChannelType.Channel_Tel;
		this.channelDn = channelDn;
		channelDnNum = Integer.parseInt(channelDn);
		appName = "";
		channelIsIdle = true;
		curContact.callHangupTime = null;
		curContact.accessTime = null;
		timeOfEnd = null;
		nowInit = false;
		shouldExit = false;
		curContact.channelDn = channelDn;
		curContact.inboundCall = true;
		atsBrowser = new ATSBrowser();
	}

	public int answerCall(boolean isBilling)
	{
		int billing = (isBilling ? 1 : 0);
		Log.wTrace(LogDef.id_0x10020000_2, channelDn, trunkResId, curContact
				.getCallSn(), billing);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsAnswerCall(trunkResId,
				curContact.getCallSn(), billing);
		Log.wTrace(LogDef.id_0x10020008, channelDn, trunkResId, curContact
				.getCallSn(), error, billing);
		int ret = errorCodeConvert(error);
		if (ret == Constant.ERR_Success)
		{
		}
		else
		{
			ChannelManager.failedServiceCount++;
		}
		return ret;
	}

	public int asr(int pronLanguage, Vector<TTSSegment> ttsSeg,
			String grammarFile, int noSpeechTimeout, int timeoutBetweenWord,
			int acceptScore, int resultNum, StringWrapper result)
	{
		TTSFile ttsFile = new TTSFile();
		getTTSFile(ttsSeg, true, ttsFile);
		ttsFile.assembleTTSFileNameEx(pronLanguage);

		String fileList[] = new String[ttsFile.arrayTVM.size()];
		ttsFile.arrayTVM.toArray(fileList);

		if (fileList.length == 0)
		{
			return Constant.EVENT_No_Error;
		}
		Log.wTrace(LogDef.id_0x10020000_11, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsASR(trunkResId,
				curContact.getCallSn(), null, 6, ttsFile.arrayTVM.size(),
				result, fileList, grammarFile, noSpeechTimeout, acceptScore,
				resultNum, timeoutBetweenWord);
		Log.wTrace(LogDef.id_0x10020010, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	public int asr2(int pronLanguage, Vector<TTSSegment> ttsSeg,
			String grammarFile, int noSpeechTimeout, int timeoutBetweenWord,
			int acceptScore, int resultNum, StringWrapper result,
			int DTMF_dtmfCount, String DTMF_endFlag, int DTMF_timeoutSecond,
			int DTMF_betweenTimeout)
	{
		TTSFile ttsFile = new TTSFile();
		getTTSFile(ttsSeg, true, ttsFile);
		ttsFile.assembleTTSFileNameEx(pronLanguage);

		String fileList[] = new String[ttsFile.arrayTVM.size()];
		ttsFile.arrayTVM.toArray(fileList);

		if (fileList.length == 0)
		{
			return Constant.EVENT_No_Error;
		}
		Log.wTrace(LogDef.id_0x10020000_12, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsASR2(trunkResId,
				curContact.getCallSn(), null, 6, ttsFile.arrayTVM.size(),
				result, fileList, grammarFile, noSpeechTimeout, acceptScore,
				resultNum, timeoutBetweenWord, DTMF_dtmfCount, DTMF_endFlag,
				DTMF_timeoutSecond, DTMF_betweenTimeout);
		Log.wTrace(LogDef.id_0x10020011, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.capability.CallControlCapability#attachCall(int,
	 *      int)
	 */
	public int attachCall(int port, int callSn)
	{
		if ((port < 0) || (callSn < 0))
		{
			return Constant.EVENT_CustomHangup;
		}

		// 先检查当前通道是否已经与此呼叫绑定。如果端口号相同而呼叫流水号不同，则应报错。
		if (trunkResId == port)
		{
			return (curContact.getCallSn() == callSn ? Constant.EVENT_No_Error
					: Constant.EVENT_CustomHangup);
		}

		// 查询呼叫信息并更新到通道中。
		BooleanWrapper isVideoCallWrapper = new BooleanWrapper();
		int error = UnifiedServiceManagement.callAgent.atsGetCallInfo(port,
				callSn, szANI, szDNIS, szOriANI, szOriDNIS, reserved,
				isVideoCallWrapper);
		if (error != Constant.ERR_Success)
		{
			Log.wError(LogDef.id_0x10120019, channelDn, port, callSn);

			return errorCodeConvert(error);
		}

		trunkResId = port;
		curContact.setCallSn(callSn);
		curContact.setInboundCall(true);
		curContact.setAni(szANI.value);
		sleeResPerfData.setAni(szANI.value);
		curContact.setDnis(szDNIS.value);
		sleeResPerfData.setDnis(szDNIS.value);
		curContact.setOriAni(szOriANI.value);
		curContact.setOriDnis(szOriDNIS.value);
		curContact.accountId = szANI.value;
		curContact.setVideoCall(isVideoCallWrapper.value);

		queryChannelState();
		UnifiedServiceManagement.callAgent.attachCall(port, callSn);

		// 在通道管理器中注册此绑定关系。
		UnifiedServiceManagement.channelManager.registerChannelOnPort(this,
				port, callSn);

		Log.wTrace(LogDef.id_0x10020004, channelDn, trunkResId, curContact
				.getCallSn(), curContact.getAni(), curContact.getDnis(),
				curContact.getOriAni(), curContact.getOriDnis());

		Log.wTrace(LogDef.id_0x10020005, channelDn, trunkResId, curContact
				.getCallSn());

		return errorCodeConvert(Constant.ERR_Success);
	}

	public void attachDaemonChannel(String daemonChannelDN)
	{
		daemonChannelDnSet.addElement(daemonChannelDN);
	}

	void attachMediaResId(int resID)
	{
		trunkResId = resID;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.capability.CallControlCapability#attachOtherParty(int, int)
	 */
	public int attachOtherParty(int port, int callSn)
	{
		if ((port < 0) || (callSn < 0) || (trunkResId < 0)
				|| (curContact.getCallSn() < 0))
		{
			return Constant.EVENT_CustomHangup;
		}

		if (port == trunkResId)
		{
			// 不能与当前通道的主控呼叫相同。
			return Constant.EVENT_DestBusy;
		}

		// 先检查当前通道是否已经与此呼叫绑定。如果端口号相同而呼叫流水号不同，则应报错。
		if (UnifiedServiceManagement.callAgent.atsGetOtherPartyTrunkID(
				trunkResId, curContact.getCallSn()) == port)
		{
			return (curContact.getCallSn() == UnifiedServiceManagement.callAgent
					.atsGetOtherPartyCallSn(trunkResId, curContact.getCallSn()) ? Constant.EVENT_No_Error
					: Constant.EVENT_CustomHangup);
		}

		// 待绑定的呼叫不能是一个已经存在的三方呼叫中的主控呼叫。
		if (UnifiedServiceManagement.callAgent.atsGetOtherPartyTrunkID(port,
				callSn) != -1)
		{
			return Constant.EVENT_DestBusy;
		}

		// 查询呼叫信息并更新到通道中。
		UnifiedServiceManagement.callAgent.attachOtherParty(trunkResId,
				curContact.getCallSn(), port, callSn);

		ATSChannel oriChannel = UnifiedServiceManagement.channelManager
				.getChannelByPort(port);
		if (oriChannel != null)
		{
			oriChannel.detachCall();
		}

		Log.wTrace("通道%s resId=%d callSn=%d 绑定第三方呼叫[resId=%d callSn=%d]成功",
				channelDn, trunkResId, curContact.getCallSn(), port, callSn);

		return errorCodeConvert(Constant.ERR_Success);
	}

	public void attachParentChannel(String parentChannelDN)
	{
		parentChannelDn = parentChannelDN;
	}

	public void beginService(String usmlDoc, String startParam)
	{
		this.usmlDoc = usmlDoc;
		this.startParam = startParam;
		parentChannelDn = null;

		sessionId = -1;

		wakeMeUp();
	}

	void browseUSMLService(String usmlFileName, String param,
			boolean isNewSession) throws Exception
	{
		browseUSMLService(usmlFileName, param, isNewSession, true);
	}

	void browseUSMLService(String usmlFileName, String param,
			boolean isNewSession, boolean shouldRestoreSession)
			throws Exception
	{
		USMLReasoningResult result = new USMLReasoningResult();
		result.usmlFileName = usmlFileName;

		if (!isNewSession)
		{
			if (shouldRestoreSession)
			{
				atsBrowser = restoreServiceSession(sessionId);
				if (atsBrowser == null)
				{
					Log.wError(LogDef.id_0x1015000E, channelDn, sessionId);

					return;
				}

				atsBrowser.restoreServiceSession(this);
			}

			result.value = Constant.EVENT_ResumeService;
		}

		if (isNewSession)
		{
			sessionId = UnifiedServiceManagement.channelManager
					.getNewSessionId();
			Log.wDebug(LogDef.id_0x10020000_25, channelDn, sessionId);
		}
		else if (shouldRestoreSession)
		{
			Log.wDebug(LogDef.id_0x10020000_26, channelDn, sessionId);
		}

		atsBrowser.browseUSMLService(param, sessionId, result);

		if (!isNewSession && shouldRestoreSession)
		{
			if (result.value != Constant.EVENT_PauseService)
			{
				// 本次执行的旧服务片断全部结束，清理会话。

				Log.wDebug(LogDef.id_0x10020000_27, channelDn, sessionId,
						++finishedCount, ChannelManager.sessionDataManager
								.size());
			}
		}

		if (result.value == Constant.EVENT_PauseService)
		{
			boolean needPause = true;
			String msg = null;

			synchronized (ChannelManager.sleepTaskDataManager)
			{
				if (atsBrowser.getCurrentTask() instanceof WaitMessageTask)
				{
					msg = QueueMessageReceiver.getMessage(sessionId);
					if (msg != null)
					{
						needPause = false;
					}
				}

				if (needPause)
				{
					pauseServiceSession(sessionId, atsBrowser.getCurrentTask());
				}
			}

			if (!needPause)
			{
				WaitMessageTask task = (WaitMessageTask) atsBrowser
						.getCurrentTask();
				task.content = msg;
				task.result = Constant.EVENT_No_Error;

				Log.wDebug(LogDef.id_0x10020000_28, channelDn, sessionId,
						++finishedCount, ChannelManager.sessionDataManager
								.size());

				browseUSMLService(usmlFileName, param, false, false);

				return;
			}

			// 以后通道执行下一个会话则不能使用旧的browser对象了，因为无法预知下一个会话是否是新会话。
			// 在通道中缓存当前会话的browser对象也没有意义，因为下次仍然由本通道继续执行上一次会话的可能性太小了。
			// 清除browser之后，就可以释放旧的browser对象了。
			atsBrowser = new ATSBrowser();
			atsBrowser.initBrowser(this);
		}
	}

	public int clearDtmfBuffer()
	{
		Log.wTrace(LogDef.id_0x10020000_13, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsClearDTMFBuffer(
				trunkResId, curContact.getCallSn());
		Log.wTrace(LogDef.id_0x10020012, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	public void clearUserEvent(int id)
	{
		userEventFiredMap.remove(Integer.valueOf(id));
	}

	public int closeConference()
	{
		Log.wTrace(LogDef.id_0x10020000_8, channelDn);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsCloseConf(trunkResId,
				curContact.getCallSn());
		Log.wTrace(LogDef.id_0x1002000D, channelDn, error);
		return errorCodeConvert(error);
	}

	public int consultCall(String callingNumber, String destNumber,
			int timeout, boolean isVideoCall)
	{
		UnifiedServiceManagement.callAgent.atsQueryCallState(trunkResId,
				curContact.callSn, channelStatus);
		if (channelStatus.value != Constant.StatusConnect)
		{
			return Constant.EVENT_CustomHangup;
		}

		IntWrapper portWrapper = new IntWrapper(trunkResId);
		IntWrapper callIdWrapper = new IntWrapper();
		Log.wTrace(LogDef.id_0x10020000_3, channelDn, callingNumber,
				destNumber, timeout);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsMakeCall(portWrapper,
				callingNumber, "", destNumber, "", callIdWrapper,
				timeout * 1000, 0, channelDnNum, isVideoCall);
		Log.wTrace(LogDef.id_0x10020009, channelDn, callingNumber, destNumber,
				error, timeout);

		return errorCodeConvert(error);
	}

	int convertError2Event(int atsError)
	{
		return Constant.convertError2Event(atsError);
	}

	public String convertError2String(int code)
	{
		String result;
		switch (code)
		{
		case Constant.EVENT_No_Error:
			result = "No_Error";
			break;

		case Constant.EVENT_User_StopIO:
			result = "UserStopIO";
			break;

		case Constant.EVENT_GeneralError:
			result = "GeneralError";
			break;

		case Constant.EVENT_DiskFull:
			result = "DiskFull";
			break;

		case Constant.EVENT_InvalidFile:
			result = "InvalidFile";
			break;

		case Constant.EVENT_DestBusy:
			result = "DestBusy";
			break;

		case Constant.EVENT_InvalidTelNum:
			result = "InvalidTelNum";
			break;

		case Constant.EVENT_NoAnswer:
			result = "NoAnswer";
			break;

		case Constant.EVENT_NoFaxDevice:
			result = "NoFaxDevice";
			break;

		case Constant.EVENT_TimeOut:
			result = "TimeOut";
			break;

		case Constant.EVENT_ToAgent:
			result = "ToAgent";
			break;

		case Constant.EVENT_CustomHangup:
			result = "CustomHangup";
			break;

		// **************************
		// case ERR_IVALID_DN:
		// return Constant.EVENT_GeneralError;
		// case ERR_RPC:
		// return Constant.EVENT_GeneralError;
		// case ERR_NOSTART:
		// return Constant.EVENT_GeneralError;
		// case ERR_NO_MSG:
		// return Constant.EVENT_GeneralError;
		case Constant.EVENT_UnsupportProc:
			result = "UnsupportProc";
			break;

		case Constant.EVENT_IVALID_PARAM:
			result = "IVALID_PARAM";
			break;

		case Constant.EVENT_IVALID_Teminal:
			result = "IVALID_Teminal";
			break;

		default:
			result = "GeneralError";
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.capability.CallControlCapability#detachCall()
	 */
	public int detachCall()
	{
		UnifiedServiceManagement.channelManager.unregisterChannelOnPort(this,
				trunkResId);
		detachMediaResID();
		channelIsIdle = true;

		return errorCodeConvert(Constant.ERR_Success);
	}

	void detachDaemonChannel(String daemonChannelDn)
	{

	}

	void detachMediaResID()
	{
		trunkResId = -1;
		curContact.setCallSn(-1);
	}

	public int disconnetCall()
	{
		Log.wTrace(LogDef.id_0x10020000_1, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		int resId = trunkResId;
		error = UnifiedServiceManagement.callAgent.atsReleaseCall(resId,
				curContact.getCallSn(), 0);
		Log.wTrace(LogDef.id_0x10020007, channelDn, resId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	public void endDaemonChannel(String daemonChannelDn)
	{
		if (daemonChannelDn == null)
		{
			for (int i = 0; i < daemonChannelDnSet.size(); i++)
			{
				String dn = daemonChannelDnSet.elementAt(i);
				endDaemonChannel(dn);
			}

			return;
		}

		ATSChannel pChannel = UnifiedServiceManagement.channelManager
				.getChannel(daemonChannelDn);
		if ((pChannel != null) && (pChannel.shouldExit != true)
				&& (channelDn.equals(pChannel.parentChannelDn)))
		{
			pChannel.shouldExit = true;
			pChannel.attachParentChannel(null);
		}

		detachDaemonChannel(daemonChannelDn);

	}

	int errorCodeConvert(int errorCode)
	{
		return Constant.convertError2Event(errorCode);
	}

	void execInboundSrv()
	{
		sleeResPerfData.setStatus(1);
		UnifiedServiceManagement.perfMonitor
				.updateChannelState(channelDnNum, 1);

		if (!onChannelBusy())
		{
			return;
		}

		daemonChannelDnSet.removeAllElements();
		shouldExit = false;
		String usmlFileName;

		try
		{
			curContact.callHangupTime = null;
			timeOfEnd = null;
			curContact.accountId = "";
			Log.wTrace(LogDef.id_0x10020001, channelDn);
			queryChannelState();

			if ((channelIsIdle == true)
					&& (channelType == ChannelType.Channel_Tel))
			{
				Log.wError(LogDef.id_0x10120010, channelDn);
				return;
			}

			if ((usmlDoc != null) && !usmlDoc.equals(""))
			{
				// Thread.sleep(1000);
			}

			if (startParam.startsWith("_channelsoft_attachCall_"))
			{
				int beginIndex = "_channelsoft_attachCall_".length();
				int endIndex = startParam.indexOf("_", beginIndex);
				int port = Integer.parseInt(startParam.substring(beginIndex,
						endIndex));
				beginIndex = endIndex + 1;
				endIndex = startParam.indexOf(" ", beginIndex);
				int calSn = Integer.parseInt(startParam.substring(beginIndex,
						endIndex));
				beginIndex = endIndex + 1;
				startParam = startParam.substring(beginIndex);

				if (attachCall(port, calSn) != Constant.EVENT_No_Error)
				{
					Log.wError(LogDef.id_0x10120011, channelDn, usmlDoc);
					return;
				}
			}

			Log.wTrace(LogDef.id_0x10020002, channelDn, usmlDoc, startParam);
			usmlFileName = lookupInboundAppFile();

			if (usmlFileName == null)
			{
				Log.wError(LogDef.id_0x10120012, channelDn);
				ChannelManager.failedServiceCount++;
				return;
			}

			if (channelType == ChannelType.Channel_Tel)
			{
				if (sessionId < 0)
				{
					sessionId = UnifiedServiceManagement.channelManager
							.getNewSessionId();
				}

				int error = 0;
				BooleanWrapper isVideoCallWrapper = new BooleanWrapper();
				error = UnifiedServiceManagement.callAgent.atsGetCallInfo(
						trunkResId, curContact.getCallSn(), szANI, szDNIS,
						szOriANI, szOriDNIS, reserved, isVideoCallWrapper);

				StringWrapper usmlFileNameWrapper = new StringWrapper(
						usmlFileName);

				if (Constant.ERR_Success == error)
				{
					curContact.accessTime = Calendar.getInstance();

					for (int i = 0; i < UnifiedServiceManagement.reasoningProviders.length; i++)
					{
						try
						{
							UnifiedServiceManagement.reasoningProviders[i]
									.getServiceFile(channelDn, szANI.value,
											szDNIS.value, usmlFileNameWrapper);

							if (usmlFileNameWrapper.value == null)
							{
								Log.wError(LogDef.id_0x10120013, channelDn,
										trunkResId, curContact.getCallSn(), i);
								break;
							}
						}
						catch (Throwable e)
						{
							Log.wException(LogDef.id_0x10120000, e);
						}
					}

					if (!usmlFileName.equals(usmlFileNameWrapper.value))
					{
						usmlFileName = usmlFileNameWrapper.value;
					}

					if (usmlFileName != null)
					{
						if (UnifiedServiceManagement.configData
								.getIsAutoAnswer())
						{
							Log.wTrace(LogDef.id_0x10020003, channelDn,
									trunkResId, curContact.getCallSn());
							int err = answerCall(false);
							if (err != Constant.EVENT_No_Error)
							{
								Log.wError(LogDef.id_0x10120014, channelDn,
										trunkResId, curContact.getCallSn());
								channelIsIdle = true;
								return;
							}
						}

						ChannelManager.successfulServiceCount++;
						curContact.setInboundCall(true);
						curContact.setAni(szANI.value);
						sleeResPerfData.setAni(szANI.value);
						curContact.setDnis(szDNIS.value);
						sleeResPerfData.setDnis(szDNIS.value);
						curContact.setOriAni(szOriANI.value);
						curContact.setOriDnis(szOriDNIS.value);
						curContact.accountId = szANI.value;
						curContact.setVideoCall(isVideoCallWrapper.value);

						Log.wTrace(LogDef.id_0x10020004, channelDn, trunkResId,
								curContact.getCallSn(), curContact.getAni(),
								curContact.getDnis(), curContact.getOriAni(),
								curContact.getOriDnis());

						Log.wTrace(LogDef.id_0x10020005, channelDn, trunkResId,
								curContact.getCallSn());

						browseUSMLService(usmlFileName, null, true);

						userEventFiredMap.clear();
						userEventSet.removeAllElements();
					}
				}
				else
				{
					Log.wError(LogDef.id_0x10120015, channelDn, error);
					userEventFiredMap.clear();
					userEventSet.removeAllElements();
				}
			}
			else
			{
				ChannelManager.successfulServiceCount++;
				if (sessionId >= 0)
				{
					browseUSMLService(null, null, false);
				}
				else
				{
					browseUSMLService(usmlFileName, startParam, true);
				}
			}
			Log.wTrace(LogDef.id_0x1002002A, channelDn);
		}
		catch (Throwable e)
		{
			Log.wFatal(LogDef.id_0x10220003, channelDn);
			Log.wException(LogDef.id_0x10120000, e);
		}

		ATSChannel pChannel = (parentChannelDn == null ? null
				: UnifiedServiceManagement.channelManager
						.getChannel(parentChannelDn));

		if (pChannel != null)
		{
			pChannel.detachDaemonChannel(channelDn);
		}
		parentChannelDn = null;
		daemonChannelDnSet.removeAllElements();
		endDaemonChannel(null);

		queryChannelState();

		if (channelIsIdle == false)
		{
			disconnetCall();
		}
		detachMediaResID();

		Log.wError("" + ChannelManager.successfulServiceCount);

		return;

	}

	void execOutboundSrv()
	{
		try
		{
			if (channelIsIdle == true)
			{
				daemonChannelDnSet.removeAllElements();
				shouldExit = false;
				String szUSMLFileName;
				szUSMLFileName = lookupOutboundAppFile();

				if (szUSMLFileName == null)
				{
					Thread.sleep(200);
					return;
				}

				if (!onChannelBusy())
				{
					return;
				}

				ChannelManager.startServiceCount++;

				ChannelManager.successfulServiceCount++;
				sleeResPerfData.setStatus(1);
				UnifiedServiceManagement.perfMonitor.updateChannelState(
						channelDnNum, 1);

				queryChannelState();
				curContact.callHangupTime = null;
				curContact.accessTime = null;
				timeOfEnd = null;
				curContact.accountId = "";
				detachMediaResID();
				curContact.callHangupTime = null;
				browseUSMLService(szUSMLFileName, null, true);

				ChannelManager.endServiceCount++;
			}

			queryChannelState();

			if (!channelIsIdle)
			{
				Log.wError(LogDef.id_0x10120017, channelDn);
				disconnetCall();

				channelIsIdle = true;
				curContact.callHangupTime = Calendar.getInstance();
			}
			if (trunkResId != -1)
			{
				detachMediaResID();
			}
		}
		catch (Throwable e)
		{
			Log.wFatal(LogDef.id_0x10220004, channelDn);
			Log.wException(LogDef.id_0x10120000, e);
			if (trunkResId != -1)
			{
				detachMediaResID();
			}
		}

		endDaemonChannel(null);

		onChannelIdle();

		sleeResPerfData.setStatus(0);
		UnifiedServiceManagement.perfMonitor
				.updateChannelState(channelDnNum, 0);
		return;
	}

	public void fireUserEvent(int callSn, int id)
	{
		if (callSn != curContact.getCallSn())
		{
			return;
		}

		userEventFiredMap.put(Integer.valueOf(id), Integer.valueOf(1));
	}

	public String getAni()
	{
		return curContact.getAni();
	}

	public String getCallingNumber()
	{
		return curContact.getCallingNumber();
	}

	public String getCallSn()
	{
		return "" + curContact.getCallSn();
	}

	public String getChannelDn()
	{
		return channelDn;
	}

	public String getDnis()
	{
		return curContact.getDnis();
	}

	public int getDtmf(int count, String endFlag, boolean shouldClearBuffer,
			int timeout, int betweenTimeout, StringWrapper dtmf)
	{
		Log.wTrace(LogDef.id_0x10020000_14, channelDn, trunkResId, curContact
				.getCallSn(), count, endFlag);
		if (shouldClearBuffer)
		{
			clearDtmfBuffer();
		}
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsReceiveDTMF(trunkResId,
				curContact.getCallSn(), dtmf, count, endFlag, timeout,
				betweenTimeout);

		if (UnifiedServiceManagement.configData.getIsWriteDTMFLog())
		{
			Log.wTrace(LogDef.id_0x10020013, channelDn, trunkResId, curContact
					.getCallSn(), error, dtmf.value);
		}
		else
		{
			char[] dtmfBuffer = new char[dtmf.value.length()];
			for (int i = 0; i < dtmfBuffer.length; i++)
			{
				dtmfBuffer[i] = '*';
			}

			Log.wTrace(LogDef.id_0x10020013, channelDn, trunkResId, curContact
					.getCallSn(), error, new String(dtmfBuffer));
		}

		return errorCodeConvert(error);
	}

	int getMediaResID()
	{
		return trunkResId;
	}

	public String getOriAni()
	{
		return curContact.getOriAni();
	}

	public String getOriDnis()
	{
		return curContact.getOriDnis();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.capability.CallControlCapability#getOtherPartyCallSn()
	 */
	public String getOtherPartyCallSn()
	{
		return ""
				+ UnifiedServiceManagement.callAgent.atsGetOtherPartyCallSn(
						trunkResId, curContact.getCallSn());
	}

	public String getOtherPartyTrunkID()
	{
		return ""
				+ UnifiedServiceManagement.callAgent.atsGetOtherPartyTrunkID(
						trunkResId, curContact.getCallSn());
	}

	/**
	 * @return the sessionId
	 */
	public long getSessionId()
	{
		return sessionId;
	}

	public String getTrunkID()
	{
		return "" + trunkResId;
	}

	void getTTSFile(Vector<TTSSegment> ttsSegs, boolean isThirdParty,
			TTSFile ttsFile)
	{
		TTSSegment ttsSeg;
		for (int i = 0; i < ttsSegs.size(); i++)
		{
			ttsSeg = ttsSegs.elementAt(i);

			if (ttsSeg.isVariable())
			{
				String varType = ttsSeg.getVarType();

				if (varType.equals(Constant.Int))
				{
					ttsFile.transFromInt(ttsSeg.getVarValue().intValue,
							isThirdParty);
				}
				else if (varType.equals(Constant.Float))
				{
					ttsFile.transFromFloat(ttsSeg.getVarValue().doubleValue,
							isThirdParty);
				}
				else if (varType.equals(Constant.String))
				{
					ttsFile.transFromDigit(ttsSeg.getVarValue().strValue,
							isThirdParty);
				}
				else if (varType.equals(Constant.TelNumber))
				{
					ttsFile.transFromTelNum(ttsSeg.getVarValue().strValue,
							isThirdParty);
				}
				else if (varType.equals(Constant.Date))
				{
					IntWrapper year = new IntWrapper();
					IntWrapper month = new IntWrapper();
					IntWrapper day = new IntWrapper();
					ttsSeg.getVarValue().getDateValue(year, month, day);
					ttsFile.transFromDate(year.value, month.value, day.value,
							isThirdParty);
				}
				else if (varType.equals(Constant.Time))
				{
					IntWrapper hour = new IntWrapper();
					IntWrapper minute = new IntWrapper();
					IntWrapper second = new IntWrapper();
					ttsSeg.getVarValue().getTimeValue(hour, minute, second);
					ttsFile.transFromTime(hour.value, minute.value,
							second.value, isThirdParty);
				}
				else if (varType.equals(Constant.Currency))
				{
					ttsFile.transFromMoney(ttsSeg.getVarValue().currencyValue,
							isThirdParty);
				}
			}
			else
			{
				String fileName = ttsSeg.getTTSVoiceFileName();
				fileName = atsBrowser.getTrueFilePath(fileName);
				ttsFile.addVoiceFile(fileName);
			}
		}
	}

	public int hasFiredUserEvent()
	{
		for (int i = 0; i < userEventSet.size(); i++)
		{
			if (hasFiredUserEvent(userEventSet.elementAt(i).intValue()))
			{
				return userEventSet.elementAt(i).intValue();
			}
		}

		return 0;
	}

	boolean hasFiredUserEvent(int id)
	{
		if (userEventFiredMap.get(Integer.valueOf(id)) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isDebugging()
	{
		return isDebugging;
	}

	public boolean isIdle()
	{
		queryChannelState();
		return channelIsIdle;
	}

	public boolean isInConference()
	{
		IntWrapper status = new IntWrapper();
		if (queryCallState(status) == Constant.ERR_Success)
		{
			return (status.value == Constant.StatusConference);
		}

		return false;
	}

	boolean isNowInit()
	{
		return nowInit;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.capability.CallControlCapability#isVideoCall()
	 */
	public boolean isVideoCall()
	{
		return curContact.isVideoCall();
	}

	String lookupInboundAppFile()
	{
		Calendar time = Calendar.getInstance();
		int nHour = time.get(Calendar.HOUR_OF_DAY);
		// 支持自身带参数的服务启动方式
		// 如果文件不存在,使用配置的文件
		String szTemp;

		if (usmlDoc.equals("") == false)
		{
			if ((usmlDoc.charAt(0) == '/') || (usmlDoc.indexOf(':') != -1))
			{
				// 路径为绝对路径
				szTemp = usmlDoc;
			}
			else
			{
				szTemp = UnifiedServiceManagement.configData.getUsmlPath()
						+ "/" + usmlDoc;
			}

			File aFile = new File(szTemp);
			if (aFile.exists())
			{
				return szTemp;
			}

			Log.wError(LogDef.id_0x10120016, this.channelDn, szTemp);
		}

		for (int i = 0; i < inAppInfos.size(); i++)
		{
			if ((inAppInfos.elementAt(i).startTime <= nHour)
					&& (inAppInfos.elementAt(i).endTime >= nHour))
			{
				szTemp = inAppInfos.elementAt(i).usmlFileName;
				return szTemp;
			}
		}
		return null;
	}

	String lookupOutboundAppFile()
	{
		Calendar tTime = Calendar.getInstance();
		int nHour = tTime.get(Calendar.HOUR_OF_DAY);
		String szTemp;

		for (int i = 0; i < outAppInfos.size(); i++)
		{
			if (outAppInfos.elementAt(i).isAutoStart == false)
			{
				if (testDialer == false)
				{
					continue;
				}
				else
				{
					// 外拨测试只执行一次
					testDialer = false;
				}
			}
			if ((outAppInfos.elementAt(i).startTime <= nHour)
					&& (outAppInfos.elementAt(i).endTime >= nHour))
			{
				szTemp = outAppInfos.elementAt(i).usmlFileName;
				return szTemp;
			}
		}
		if (outAppInfos.size() <= 0)
		{
			inboundSrvTime = true; // 无出呼叫应用，为处理入呼叫通道
		}
		return null;
	}

	public int makeCall(String callingNumber, String oriCallingNumber,
			String destNumber, String oriDestNumber, int timeout,
			boolean isVideoCall)
	{
		IntWrapper portWrapper = new IntWrapper();
		IntWrapper callIdWrapper = new IntWrapper();
		portWrapper.value = trunkResId;
		callIdWrapper.value = curContact.getCallSn();
		Log.wTrace(LogDef.id_0x10020000_4, channelDn, callingNumber,
				destNumber, timeout);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsMakeCall(portWrapper,
				callingNumber, oriCallingNumber, destNumber, oriDestNumber,
				callIdWrapper, timeout, 0, channelDnNum, isVideoCall);
		Log.wTrace(LogDef.id_0x1002000A, channelDn, callingNumber, destNumber,
				error, timeout);
		if (error != Constant.ERR_Success)
		{
			String content = String.format("ChannelDN=%s makeCall[%s %s %d]失败",
					channelDn, callingNumber, destNumber, timeout);
			Log.wError(LogDef.id_0x10120018, channelDn, callingNumber,
					destNumber, timeout);
			if (UnifiedServiceManagement.hasSnmpAgent)
			{
				SnmpAgentHandler.instance().addTrapData(content, 4);
			}
			return errorCodeConvert(error);
		}

		if ((trunkResId == -1) && (portWrapper.value >= 0)
				&& (portWrapper.value < 1000000))
		{

			curContact.setInboundCall(false);
			curContact.setAni(callingNumber);
			curContact.setDnis(destNumber);
			curContact.accessTime = Calendar.getInstance();
			sleeResPerfData.setAni(callingNumber);
			sleeResPerfData.setDnis(destNumber);
			UnifiedServiceManagement.perfMonitor
					.writeChannelDetailInfo(sleeResPerfData);
			curContact.setCallSn(callIdWrapper.value);
			curContact.accountId = callingNumber;
			attachMediaResId(portWrapper.value);
			Log.wTrace(LogDef.id_0x10020005, channelDn, trunkResId,
					callIdWrapper.value);
		}

		return errorCodeConvert(error);
	}

	boolean onChannelBusy()
	{
		for (int i = 0; i < UnifiedServiceManagement.reasoningProviders.length; i++)
		{
			try
			{
				if (!UnifiedServiceManagement.reasoningProviders[i]
						.onChannelBusy(channelDn, -1, -1, "", "", "", ""))
				{
					Log.wWarn(LogDef.id_0x10020006, channelDn, i);
					return false;
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10120000, e);
			}
		}

		return true;
	}

	void onChannelIdle()
	{
		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onChannelIdle(channelDn);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10120000, e);
			}
		}
	}

	/**
	 * 暂停执行一个服务实例，此实例如果在超时前没有被其它条件唤醒，则会触发超时唤醒。
	 * 
	 * @param sessionId
	 * @param task
	 */
	public void pauseServiceSession(long sessionId, SleepTask task)
			throws Exception
	{
		ChannelManager.sessionDataManager.putSessionData("" + sessionId,
				atsBrowser);
		// ChannelManager.sdm.put(sessionId, atsBrowser);

		ServiceTimerTask timerTask = new ServiceTimerTask(sessionId, task,
				atsBrowser.currentTaskCounter++, curContact.clone());

		atsBrowser.clearCurrentTask();

		synchronized (ServiceTimerTask.timerTasks)
		{
			ServiceTimerTask.timerTasks.put(sessionId, timerTask);
		}

		serviceTimer.schedule(timerTask, task.timeout);

		Log.wDebug(LogDef.id_0x10020000_29, channelDn, sessionId,
				ChannelManager.sessionDataManager.size());
	}

	public int playTTSFile(int pronLanguage, Vector<TTSSegment> ttsSeg,
			boolean canBreak, String breakList, int rate, boolean thirdParty)
	{
		TTSFile ttsFile = new TTSFile();
		getTTSFile(ttsSeg, thirdParty, ttsFile);
		if (thirdParty)
		{
			ttsFile.assembleTTSFileNameEx(pronLanguage);
		}
		else
		{
			ttsFile.assembleTTSFileName(pronLanguage);
		}

		// return EVENT_CustomHangup;
		return playVoiceArray(ttsFile.arrayTVM, canBreak, breakList, 1, 0,
				rate, thirdParty);
	}

	public int playVoice(String fileName, boolean canBreak, String breakList,
			int rate, int playCount, int waitTimeOnce,
			IntWrapper secondsPlayed, Calendar lastInteractionTime)
	{
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		fileName = atsBrowser.getTrueFilePath(fileName);

		if (canBreak && "".equals(breakList))
		{
			breakList = Constant.BREAK_LIST_ALL;
		}

		Log.wTrace(LogDef.id_0x10020000_17, channelDn, trunkResId, curContact
				.getCallSn(), fileName, canBreak, breakList);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsPlayVoice(trunkResId,
				curContact.getCallSn(), fileName, breakList, rate,
				secondsPlayed, lastInteractionTime);
		Log.wTrace(LogDef.id_0x10020015, channelDn, trunkResId, curContact
				.getCallSn(), error, fileName);
		return errorCodeConvert(error);
	}

	int playVoiceArray(Vector<String> vms, boolean canBreak, String breakList,
			int playCount, int waitTimeOnce, int rate, boolean thirdParty)
	{
		String fileList[] = new String[vms.size()];
		vms.toArray(fileList);
		if (fileList.length == 0)
		{
			return Constant.EVENT_No_Error;
		}
		Log.wTrace(LogDef.id_0x10020000_15, channelDn, trunkResId, curContact
				.getCallSn());
		for (String element : fileList)
		{
			Log.wTrace(LogDef.id_0x10020000_16, channelDn, element);
		}

		if (canBreak && "".equals(breakList))
		{
			breakList = Constant.BREAK_LIST_ALL;
		}

		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsPlayList(trunkResId,
				curContact.getCallSn(), breakList, rate, fileList.length,
				fileList, waitTimeOnce);

		Log.wTrace(LogDef.id_0x10020014, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	public int playVoiceAsync(String fileName, boolean canBreak,
			String breakList, int rate, int playCount, int waitTimeOnce)
	{
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		fileName = atsBrowser.getTrueFilePath(fileName);

		if (canBreak && "".equals(breakList))
		{
			breakList = Constant.BREAK_LIST_ALL;
		}

		Log.wTrace(LogDef.id_0x10020000_18, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsPlayVoiceAsync(
				trunkResId, curContact.getCallSn(), fileName, breakList, rate);
		Log.wTrace(LogDef.id_0x10020016, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	public int playVoiceByTTS(String text, boolean isFile,
			boolean shouldConvert, int voiceLib, boolean canBreak,
			String breakList, int playCount, int waitTimeOnce)
	{
		if (playCount == 0)
		{
			return Constant.EVENT_No_Error;
		}

		if ((text == null) || text.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		int type = 0;
		if (isFile)
		{
			text = atsBrowser.getTrueFilePath(text);
			if (shouldConvert)
			{
				type = Constant.TTS_TYPE_TEXT_FILE_TO_AUDIO_FILE;
			}
			else
			{
				type = Constant.TTS_TYPE_TEXT_FILE_TO_AUDIO_BUF;
			}
		}
		else
		{
			if (shouldConvert)
			{
				type = Constant.TTS_TYPE_TEXT_BUF_TO_AUDIO_FILE;
			}
			else
			{
				type = Constant.TTS_TYPE_TEXT_BUF_TO_AUDIO_BUF;
			}
		}

		Log.wTrace(LogDef.id_0x10020000_19, channelDn, trunkResId, curContact
				.getCallSn());

		if (canBreak && "".equals(breakList))
		{
			breakList = Constant.BREAK_LIST_ALL;
		}

		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsPlayTTS(trunkResId,
				curContact.getCallSn(), breakList, type, text, voiceLib);

		Log.wTrace(LogDef.id_0x10020017, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	public void popUserEventSet()
	{
		userEventSet.removeAllElements();
		userEventSet.addAll(userEventSetSaved);
	}

	public void pushUserEventSet(Vector<Integer> userEventSet)
	{
		userEventSetSaved.removeAllElements();
		userEventSetSaved.addAll(userEventSet);
		userEventSet.addAll(userEventSet);
	}

	public int queryCallState(IntWrapper status)
	{
		return UnifiedServiceManagement.callAgent.atsQueryCallState(trunkResId,
				curContact.callSn, status);
	}

	public void queryChannelState()
	{
		channelStatus.value = Constant.StatusConnect;
		int error = Constant.ERR_Success;

		if (trunkResId == -1)
		{
			channelIsIdle = true;
			return;
		}

		error = queryCallState(channelStatus);

		if (error == Constant.ERR_Success)
		{
			if (channelStatus.value == Constant.StatusIdle)
			{
				channelIsIdle = true;
				curContact.callHangupTime = Calendar.getInstance();
			}
			else
			{
				channelIsIdle = false;
				curContact.callHangupTime = null;
			}
		}
		else if (error == Constant.ERR_IVALID_DN)
		{
		}
		else
		{
			Log.wError(LogDef.id_0x10120001, channelDn, error);
		}
	}

	public int receiveFax(String fileName, int timeDuration)
	{
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		fileName = atsBrowser.getTrueFilePath(fileName);

		Log.wTrace(LogDef.id_0x10020000_20, channelDn, trunkResId, curContact
				.getCallSn(), fileName);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsReceiveFax(trunkResId,
				curContact.getCallSn(), fileName);
		Log.wTrace(LogDef.id_0x10020018, channelDn, trunkResId, curContact
				.getCallSn(), error, fileName);
		return errorCodeConvert(error);
	}

	public int recodeVoice(String fileName, char endFlag, int timeDuration,
			int rate)
	{
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		fileName = atsBrowser.getTrueFilePath(fileName);

		Log.wTrace(LogDef.id_0x10020000_21, channelDn, trunkResId, curContact
				.getCallSn(), fileName);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsRecordVoice(trunkResId,
				curContact.getCallSn(), fileName, endFlag, timeDuration, rate);
		Log.wTrace(LogDef.id_0x10020019, channelDn, trunkResId, curContact
				.getCallSn(), error, fileName);
		return errorCodeConvert(error);
	}

	public int releaseConnection()
	{
		Log.wTrace(LogDef.id_0x10020000_5, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsRetrieveCall(trunkResId,
				curContact.getCallSn(), 0);
		Log.wTrace(LogDef.id_0x1002001D, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	/**
	 * @param sessionId
	 * @return
	 */
	private ATSBrowser restoreServiceSession(long sessionId) throws Exception
	{
		String key = "" + sessionId;
		ATSBrowser browser = null;

		// synchronized (ChannelManager.sdm)
		{
			browser = (ATSBrowser) ChannelManager.sessionDataManager
					.getSessionData(key);
			// browser = ChannelManager.sdm.remove(sessionId);
			if (browser != null)
			{
				ChannelManager.sessionDataManager.removeSessionData(key);
			}
		}

		return browser;
	}

	public SleepTask restoreTask(long sessionId)
	{
		ServiceTimerTask.removeTimerTask(sessionId);
		SleepTask task = null;

		try
		{
			synchronized (ChannelManager.sleepTaskDataManager)
			{
				task = (SleepTask) ChannelManager.sleepTaskDataManager
						.getSessionData("" + sessionId);
				ChannelManager.sleepTaskDataManager.removeSessionData(""
						+ sessionId);
				if ((task != null) && (task.curContact != null))
				{
					curContact = task.curContact;
				}
			}

			Log.wDebug(LogDef.id_0x10020000_30, sessionId,
					ServiceTimerTask.timerTasks.size(),
					ChannelManager.sleepTaskDataManager.size());
		}
		catch (Exception e)
		{
		}

		return task;
	}

	void resumeService(long sessionId)
	{
		usmlDoc = "";
		startParam = "";
		parentChannelDn = null;

		this.sessionId = sessionId;

		wakeMeUp();
	}

	public int routeConnection(int otherResId)
	{
		return routeConnection(trunkResId, otherResId, Constant.RM_FULLDUP);
	}

	public int routeConnection(int port1, int port2, int mode)
	{
		return routeConnection(port1, port2, mode, "", "");
	}

	public int routeConnection(int port1, int port2, int mode,
			String videoUrl1, String videoUrl2)
	{
		Log.wTrace(LogDef.id_0x10020000_6, channelDn, port1, port2);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsSwitchTwoPort(port1,
				curContact.getCallSn(), port2, mode, videoUrl1, videoUrl2);
		Log.wTrace(LogDef.id_0x1002000B, channelDn, port1, port2, error);
		return errorCodeConvert(error);
	}

	@Override
	public void run()
	{
		setName("ATSChannel DN=" + channelDn);

		atsBrowser.initBrowser(this);
		channelIsIdle = true;
		testDialer = false;
		inboundSrvTime = false;
		Log.wTrace(LogDef.id_0x10020000, channelDn);

		while (true)
		{
			try
			{
				userEventFiredMap.clear();
				userEventSet.removeAllElements();

				if (inboundSrvTime == true)
				{
					synchronized (this)
					{
						try
						{
							waitForCall();
						}
						catch (Exception e)
						{
						}
					}

					try
					{
						ChannelManager.startServiceCount++;
						execInboundSrv();
					}
					catch (Throwable e)
					{
						Log.wFatal(LogDef.id_0x10220001, channelDn);
						Log.wException(LogDef.id_0x10120000, e);
					}
					finally
					{
						ChannelManager.endServiceCount++;
						UnifiedServiceManagement.channelManager
								.reclaimChannel(this);

						onChannelIdle();

						sleeResPerfData.setStatus(0);
						UnifiedServiceManagement.perfMonitor
								.updateChannelState(channelDnNum, 0);
					}
				}
				else
				{
					execOutboundSrv();
				}

				detachMediaResID();
			}
			catch (Throwable e)
			{
				Log.wFatal(LogDef.id_0x10220002, channelDn);
				Log.wException(LogDef.id_0x10120000, e);
			}
		}
	}

	public int sendDtmf(String dtmf)
	{
		Log.wTrace(LogDef.id_0x10020000_22, channelDn, trunkResId, curContact
				.getCallSn(), dtmf);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsSendSignals(trunkResId,
				curContact.getCallSn(), dtmf);
		Log.wTrace(LogDef.id_0x1002001A, channelDn, trunkResId, curContact
				.getCallSn(), error, dtmf);
		return errorCodeConvert(error);
	}

	public int sendFax(String fileName)
	{
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		fileName = atsBrowser.getTrueFilePath(fileName);

		Log.wTrace(LogDef.id_0x10020000_23, channelDn, trunkResId, curContact
				.getCallSn(), fileName);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsSendFax(trunkResId,
				curContact.getCallSn(), fileName);
		Log.wTrace(LogDef.id_0x1002001B, channelDn, trunkResId, curContact
				.getCallSn(), error, fileName);
		return errorCodeConvert(error);
	}

	public void setAni(String value)
	{
		atsBrowser.setCurANI(value);
	}

	public void setCallingNumber(String value)
	{
		curContact.setCallingNumber(value);
	}

	public void setDebugging(boolean isDebugging)
	{
		this.isDebugging = isDebugging;
	}

	public void setDnis(String value)
	{
		atsBrowser.setCurDNIS(value);
	}

	public int setMaxCallTime(int maxCallTime)
	{
		return 0;
	}

	public void setOriAni(String value)
	{
		atsBrowser.setOriginCallingId(value);
	}

	public void setOriDnis(String value)
	{
		atsBrowser.setOriginCalledId(value);
	}

	public boolean shouldExit()
	{
		return shouldExit;
	}

	public int singleStepConference(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean isVideoCall)
	{
		Log.wTrace(LogDef.id_0x10020000_7, channelDn, trunkResId, curContact
				.getCallSn(), callingNumber, destNumber, timeout);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsSingleStepConference(
				trunkResId, curContact.getCallSn(), callingNumber,
				oriCallingNumber, destNumber, oriDestNumber, timeout,
				routeFirst, 0, channelDnNum, isVideoCall);
		Log.wTrace(LogDef.id_0x1002000C, channelDn, trunkResId, curContact
				.getCallSn(), callingNumber, destNumber, error, timeout);
		return errorCodeConvert(error);
	}

	public int singleStepTransfer(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean isVideoCall)
	{
		return singleStepTransfer(callingNumber, oriCallingNumber, destNumber,
				oriDestNumber, timeout, routeFirst, false, 0, isVideoCall);
	}

	public int singleStepTransfer(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean reverseRoute, int mode,
			boolean isVideoCall)
	{
		return singleStepTransfer(callingNumber, oriCallingNumber, destNumber,
				oriDestNumber, timeout, routeFirst, reverseRoute, mode,
				isVideoCall, "", "");
	}

	public int singleStepTransfer(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean reverseRoute, int mode,
			boolean isVideoCall, String videoUrl1, String videoUrl2)
	{
		Log.wTrace(LogDef.id_0x10020000_9, channelDn, trunkResId, curContact
				.getCallSn(), callingNumber, destNumber, timeout);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsSingleStepTransfer(
				trunkResId, curContact.getCallSn(), callingNumber,
				oriCallingNumber, destNumber, oriDestNumber, timeout,
				routeFirst, 0, channelDnNum, false, mode, reverseRoute,
				isVideoCall, videoUrl1, videoUrl2);
		Log.wTrace(LogDef.id_0x1002000E, channelDn, trunkResId, curContact
				.getCallSn(), callingNumber, destNumber, error, timeout);
		return errorCodeConvert(error);
	}

	public int startConferencRecording(String recordFileName)
	{
		if ((recordFileName == null) || recordFileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		recordFileName = atsBrowser.getTrueFilePath(recordFileName);

		Log.wTrace(LogDef.id_0x10020000_24, channelDn, trunkResId, curContact
				.getCallSn(), recordFileName);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsStartConferencRecording(
				trunkResId, curContact.getCallSn(), recordFileName, 0);
		Log.wTrace(LogDef.id_0x1002001C, channelDn, trunkResId, curContact
				.getCallSn(), error, recordFileName);
		return errorCodeConvert(error);
	}

	public boolean startOutboundSrv()
	{
		testDialer = true;
		return true;
	}

	public int transferCall()
	{
		return routeConnection(trunkResId, -1, Constant.RM_FULLDUP);
	}

	public int unrouteConnection(int otherResId)
	{
		return unrouteConnection(trunkResId, otherResId, Constant.RM_FULLDUP);
	}

	public int unrouteConnection(int port1, int port2, int mode)
	{
		Log.wTrace(LogDef.id_0x10020000_10, channelDn, port1, port2);
		int error = 0;

		error = UnifiedServiceManagement.callAgent.atsDisSwitchTwoPort(port1,
				curContact.getCallSn(), port2, mode);

		Log.wTrace(LogDef.id_0x1002000F, channelDn, port1, port2, error);
		return errorCodeConvert(error);
	}

	@Override
	public int voiceEdit(String fileName, int rate, int playCount,
			int waitTimeOnce, IntWrapper secondsPlayed,
			Calendar lastInteractionTime, int count, String endFlag,
			boolean shouldClearBuffer, int timeout, int betweenTimeout,
			StringWrapper dtmf)
	{
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}

		if (shouldClearBuffer)
		{
			clearDtmfBuffer();
		}

		fileName = atsBrowser.getTrueFilePath(fileName);

		Log.wTrace(LogDef.id_0x10020000_39, channelDn, trunkResId, curContact
				.getCallSn());
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsVoiceEdit(trunkResId,
				curContact.getCallSn(), fileName, rate, secondsPlayed,
				lastInteractionTime, dtmf, count, endFlag, timeout,
				betweenTimeout);
		if (UnifiedServiceManagement.configData.getIsWriteDTMFLog())
		{
			Log.wTrace(LogDef.id_0x10020000_37, channelDn, trunkResId,
					curContact.getCallSn(), error, dtmf.value);
		}
		else
		{
			char[] dtmfBuffer = new char[dtmf.value.length()];
			for (int i = 0; i < dtmfBuffer.length; i++)
			{
				dtmfBuffer[i] = '*';
			}

			Log.wTrace(LogDef.id_0x10020000_37, channelDn, trunkResId,
					curContact.getCallSn(), error, new String(dtmfBuffer));
		}

		return errorCodeConvert(error);
	}

	@Override
	public int voiceListEdit(int pronLanguage, Vector<TTSSegment> ttsSeg,
			int rate, boolean thirdParty, int count, String endFlag,
			boolean shouldClearBuffer, int timeout, int betweenTimeout,
			StringWrapper dtmf)
	{
		TTSFile ttsFile = new TTSFile();
		getTTSFile(ttsSeg, thirdParty, ttsFile);
		if (thirdParty)
		{
			ttsFile.assembleTTSFileNameEx(pronLanguage);
		}
		else
		{
			ttsFile.assembleTTSFileName(pronLanguage);
		}

		if (shouldClearBuffer)
		{
			clearDtmfBuffer();
		}

		String fileList[] = new String[ttsFile.arrayTVM.size()];
		ttsFile.arrayTVM.toArray(fileList);
		if (fileList.length == 0)
		{
			return Constant.EVENT_No_Error;
		}

		Log.wTrace(LogDef.id_0x10020000_40, channelDn, trunkResId, curContact
				.getCallSn());

		for (String element : fileList)
		{
			Log.wTrace(LogDef.id_0x10020000_16, channelDn, element);
		}
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsVoiceListEdit(trunkResId,
				curContact.getCallSn(), rate, fileList.length, fileList, 0,
				dtmf, count, endFlag, timeout, betweenTimeout);

		if (UnifiedServiceManagement.configData.getIsWriteDTMFLog())
		{
			Log.wTrace(LogDef.id_0x10020000_38, channelDn, trunkResId,
					curContact.getCallSn(), error, dtmf.value);
		}
		else
		{
			char[] dtmfBuffer = new char[dtmf.value.length()];
			for (int i = 0; i < dtmfBuffer.length; i++)
			{
				dtmfBuffer[i] = '*';
			}

			Log.wTrace(LogDef.id_0x10020000_38, channelDn, trunkResId,
					curContact.getCallSn(), error, new String(dtmfBuffer));
		}

		return errorCodeConvert(error);
	}

	void waitForCall()
	{
		try
		{
			waiter.await();
		}
		catch (Throwable e)
		{
		}

		try
		{
			waiter.reset();
		}
		catch (Throwable e)
		{
		}
	}

	public void wakeMeUp()
	{
		try
		{
			waiter.await(0, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
		}
	}

	@Override
	public int appendVideo(String[][] infoOnVideo)
	{
		int error = 0;

		Log.wTrace(LogDef.id_0x10020000_41, channelDn, trunkResId, curContact
				.getCallSn());

		error = UnifiedServiceManagement.callAgent.atsAppendVideo(trunkResId,
				curContact.getCallSn(), infoOnVideo);

		Log.wTrace(LogDef.id_0x10020000_42, channelDn, trunkResId, curContact
				.getCallSn(), error);
		return errorCodeConvert(error);
	}

	@Override
	public int callRecordRing(String callingNumber, String oriCallingNumber,
			String destNumber, String oriDestNumber, int timeout,
			boolean isVideoCall, String fileName, char endFlag,
			int timeDuration, int rate)
	{
		IntWrapper portWrapper = new IntWrapper();
		IntWrapper callIdWrapper = new IntWrapper();
		portWrapper.value = trunkResId;
		callIdWrapper.value = curContact.getCallSn();
		
		if ((fileName == null) || fileName.equals(""))
		{
			return Constant.EVENT_InvalidFile;
		}
		fileName = atsBrowser.getTrueFilePath(fileName);

		Log.wTrace(LogDef.id_0x10020000_43, channelDn, callingNumber,
				destNumber, fileName, timeout);
		int error = 0;
		error = UnifiedServiceManagement.callAgent.atsCallRecordRing(
				portWrapper, callingNumber, oriCallingNumber, destNumber,
				oriDestNumber, callIdWrapper, timeout, 0, channelDnNum,
				isVideoCall, fileName, endFlag, timeDuration, rate);
		Log.wTrace(LogDef.id_0x10020000_44, channelDn, callingNumber, destNumber,
				error, fileName, timeout);
		if (error != Constant.ERR_Success)
		{
			String content = String.format("ChannelDN=%s makeCall[%s %s %d]失败",
					channelDn, callingNumber, destNumber, timeout);
			Log.wError(LogDef.id_0x10020000_45, channelDn, callingNumber,
					destNumber, timeout);
			if (UnifiedServiceManagement.hasSnmpAgent)
			{
				SnmpAgentHandler.instance().addTrapData(content, 4);
			}
			return errorCodeConvert(error);
		}

		if ((trunkResId == -1) && (portWrapper.value >= 0)
				&& (portWrapper.value < 1000000))
		{

			curContact.setInboundCall(false);
			curContact.setAni(callingNumber);
			curContact.setDnis(destNumber);
			curContact.accessTime = Calendar.getInstance();
			sleeResPerfData.setAni(callingNumber);
			sleeResPerfData.setDnis(destNumber);
			UnifiedServiceManagement.perfMonitor
					.writeChannelDetailInfo(sleeResPerfData);
			curContact.setCallSn(callIdWrapper.value);
			curContact.accountId = callingNumber;
			attachMediaResId(portWrapper.value);
			Log.wTrace(LogDef.id_0x10020005, channelDn, trunkResId,
					callIdWrapper.value);
		}

		return errorCodeConvert(error);

	}
}

enum ChannelType
{
	Channel_App, Channel_SMS_MO, Channel_SMS_Report, Channel_Tel
}

class ServiceTimerTask extends TimerTask
{
	/**
	 * 存放所有待处理的异步任务。
	 * <p>
	 * 为了避免查询整个会话开销太大，使用此数据结构保存等候的会话的基本信息。
	 */
	// public static HashMap<Long, SleepTask> asyncTasks = new HashMap<Long,
	// SleepTask>();
	/**
	 * 存放所有待处理的定时器任务。
	 */
	public static HashMap<Long, ServiceTimerTask> timerTasks = new HashMap<Long, ServiceTimerTask>();

	/**
	 * 处理一个消息。如果当前会话正在等待消息，则取消相应的定时器任务。
	 * 
	 * @param sessionId
	 *            会话ID
	 * @param content
	 *            消息内容
	 * @return true为有会话在等待，false为没有。
	 */
	public static boolean processMessage(long sessionId, String content)
	{
		boolean ret = false;

		synchronized (ChannelManager.sleepTaskDataManager)
		{
			try
			{

				SleepTask task = (SleepTask) ChannelManager.sleepTaskDataManager
						.getSessionData("" + sessionId);
				if ((task != null) && !task.isExpired
						&& (task instanceof WaitMessageTask))
				{
					WaitMessageTask wmt = (WaitMessageTask) task;
					wmt.isExpired = true;
					wmt.content = content;
					wmt.result = Constant.EVENT_No_Error;

					ChannelManager.sleepTaskDataManager.putSessionData(""
							+ sessionId, wmt);
					ret = true;
				}
			}
			catch (Exception e)
			{
			}
		}

		if (ret)
		{
			removeTimerTask(sessionId);
		}

		return ret;
	}

	public static ServiceTimerTask removeTimerTask(long sessionId)
	{
		synchronized (ServiceTimerTask.timerTasks)
		{
			ServiceTimerTask task = ServiceTimerTask.timerTasks
					.remove(sessionId);
			if (task != null)
			{
				task.cancel();
			}

			return task;
		}

	}

	public static long restoreData(GarbageCollectionCallbackImpl gcci)
			throws Exception
	{
		long maxSessionId = 0;

		ArrayList<Object> asyncTasks = ChannelManager.sleepTaskDataManager
				.getAllData();

		for (Iterator<Object> it = asyncTasks.iterator(); it.hasNext();)
		{
			SleepTask task = (SleepTask) it.next();
			if (gcci.isSessionExpired(task, task.createdMillis))
			{
				ChannelManager.sleepTaskDataManager.removeSessionData(""
						+ task.sessionId);
				continue;
			}

			ServiceTimerTask timerTask = new ServiceTimerTask();
			timerTask.counter = task.counter;
			timerTask.sessionId = task.sessionId;

			if (task.sessionId > maxSessionId)
			{
				maxSessionId = task.sessionId;
			}

			timerTasks.put(task.sessionId, timerTask);

			// long timeout = task.timeoutMillis - System.currentTimeMillis();
			// if (timeout <= 0)
			// {
			// timeout = 1000;
			// }

			ATSChannel.serviceTimer.schedule(timerTask, task.timeout);
		}

		return maxSessionId;
	}

	int counter = 0;

	long sessionId = 0;

	ServiceTimerTask()
	{
	}

	ServiceTimerTask(long sessionId, SleepTask task, int counter,
			Contact curContact) throws Exception
	{
		this.sessionId = sessionId;
		this.counter = counter;

		task.counter = counter;
		task.curContact = curContact;
		task.sessionId = sessionId;

		ChannelManager.sleepTaskDataManager
				.putSessionData("" + sessionId, task);
	}

	@Override
	public void run()
	{
		SleepTask task = null;

		try
		{
			task = (SleepTask) ChannelManager.sleepTaskDataManager
					.getSessionData("" + sessionId);
			if ((task == null) || (task.counter != counter) || task.isExpired)
			{
				task = null;
			}

			synchronized (timerTasks)
			{
				timerTasks.remove(sessionId);
			}

			if (task != null)
			{
				task.isExpired = true;
				task.result = Constant.EVENT_TimeOut;

				ChannelManager.sleepTaskDataManager.putSessionData(""
						+ sessionId, task);

				UnifiedServiceManagement.channelManager
						.resumeChannel(sessionId);

				Log.wDebug(LogDef.id_0x10020000_34, sessionId, timerTasks
						.size(), ChannelManager.sleepTaskDataManager.size());
			}
		}
		catch (Exception e)
		{
		}
	}
}
