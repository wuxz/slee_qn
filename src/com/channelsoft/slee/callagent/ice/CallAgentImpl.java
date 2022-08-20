package com.channelsoft.slee.callagent.ice;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import Ice.IntHolder;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.VGProxy.VGServicePrx;
import com.channelsoft.VGProxy.VGServicePrxHelper;
import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.channelmanager.AtsSGEvent;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class CallAgentImpl implements CallAgent
{
	static Map<Integer, CallAgentImpl> callAgentMap = Collections
			.synchronizedMap(new HashMap<Integer, CallAgentImpl>());

	static LinkedBlockingQueue<AtsSGEvent> sgEventQueue = new LinkedBlockingQueue<AtsSGEvent>();

	static private ISysCfgData sysCfgData;

	static VGServicePrx vgproxy;

	String ani;

	int callId = -1;

	int callState = Constant.StatusIdle;

	/**
	 * 单步会议时创建的会议ID
	 */
	int confId = -1;

	String dnis;

	CallAgentImpl hostAgent = null;

	Ice.Communicator ic = null;

	boolean isPlayingVoiceAsynch = false;

	String monitorDnis;

	LinkedBlockingQueue<CallAgentMessage> msgQueue = new LinkedBlockingQueue<CallAgentMessage>();

	String oriAni;

	String oriDnis;

	CallAgentImpl otherPartyAgent = null;

	String playVoiceFileName;

	String playVoiceInterruptKeys;

	int playVoiceRate;

	String reserved;

	int resId = -1;

	String sgIp;

	int sgPort;

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

				msg = msgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);
				if (msg == null)
				{
					return Constant.ERR_TimeOut;
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent event = ((SGEventMessage) msg).sgEvent;
					Log.wDebug(LogDef.id_0x10050023, resId, callId,
							event.ResID, event.CallID, event.EventID);

					if ((event.ResID != resId) || (event.CallID != callId))
					{
						if (event.CallID > callId)
						{
							Log.wError(LogDef.id_0x10150010, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}

						continue;
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

	public int atsAnswerCall(int port, int callId, int reserved)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		int result;
		try
		{
			result = vgproxy.sgAnswerCall(port, callId);
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

		int timeout = 30 * 1000;

		return agent.answerCall(callId, timeout);
	}

	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

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
			ret = vgproxy.mgASR(port, callId, -1, interruptKeys, rate,
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
		ret = agent.doMgOperation(callId, 3 * 60 * 1000,
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
		// TODO VGProxy无此接口.
		return atsASR(port, callId, interruptKeys, rate, fileCount, result,
				voiceFileList, grammar, noSpeechTimeout, acceptScore,
				resultNum, timeoutBetweenWord);
	}

	public int atsClearDTMFBuffer(int port, int callId)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		try
		{
			int result = vgproxy.mgFlushBuffer(port, callId);
			return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
					: result);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			return Constant.ERR_RPC;
		}
	}

	public int atsCloseConf(int port, int callId)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
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
			result = vgproxy.rmBlindCloseConf(agent.confId);
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	public int atsDisSwitchTwoPort(int port1, int callId1, int port2,
			int reserved)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = null;
		agent = checkCallState(port1, callId1);
		if ((agent == null) && (checkCallState(port2, callId1) == null))
		{
			return Constant.ERR_NoCall;
		}

		if (agent == null)
		{
			agent = getCallAgent(port1);
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
				port2 = agent.otherPartyAgent.resId;
			}
			else
			{
				return Constant.ERR_NoCall;
			}
		}

		int result;

		try
		{
			result = vgproxy.rmUnrouteTwoRes(port1, port2, reserved);
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

		CallAgentImpl agent = checkCallState(port, callId);
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
		CallAgentImpl agent = checkCallState(port, callId);
		if ((agent == null) || (agent.otherPartyAgent == null))
		{
			return -1;
		}

		return agent.otherPartyAgent.callId;
	}

	public int atsGetOtherPartyTrunkID(int port, int callId)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if ((agent == null) || (agent.otherPartyAgent == null))
		{
			return -1;
		}

		return agent.otherPartyAgent.resId;
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
		int ret = atsInitialize(CallAgentImpl.sysCfgData.getUvmgServerIp(),
				CallAgentImpl.sysCfgData.getUvmgServerPort(),
				CallAgentImpl.sysCfgData.getUvmgServerIp(),
				CallAgentImpl.sysCfgData.getUvmgServerPort(), "*");
		Log.wTrace(LogDef.id_0x10010004, UnifiedServiceManagement.configData
				.getUvmgServerIp(), UnifiedServiceManagement.configData
				.getUvmgServerPort());
		return ret;
	}

	public int atsInitialize(String mgIp, int mgPort, String sgIp, int sgPort,
			String monitorDnis)
	{
		this.sgIp = mgIp;
		this.sgPort = mgPort;
		this.monitorDnis = monitorDnis;

		int result = 0;

		try
		{
			result = initialize();
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);

			Log.wDebug(LogDef.id_0x10250003, this.sgIp, this.sgPort);

			result = 0;
		}

		SGEventReceiver sgr = new SGEventReceiver(this);
		sgr.start();
		MGEventReceiver mgr = new MGEventReceiver(this);
		mgr.start();

		return result;
	}

	public int atsMakeCall(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall)
	{
		return atsMakeCall(port, callingNumber, destNumber, callId, timeout,
				reserved, transactionId, false, isVideoCall);
	}

	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean routeMedia, boolean isVideoCall)
	{
		return atsMakeCall(port, callingNumber, destNumber, callId, timeout,
				reserved, transactionId, false, 0, false, isVideoCall);
	}

	int atsMakeCall(IntWrapper port, String callingNumber, String destNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean routeMedia, int mode, boolean reverseRoute,
			boolean isVideoCall)
	{
		if ((port != null) && (port.value != -1))
		{
			CallAgentImpl callAgent = getCallAgent(port.value);
			if ((callAgent.otherPartyAgent != null)
					&& (callAgent.otherPartyAgent.resId != -1))
			{
				Log.wError(LogDef.id_0x10150015, port.value, callAgent.callId);
				return Constant.ERR_DestBusy;
			}
		}
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
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
			result = vgproxy.sgBlindMakeCall(callingNumber, destNumber,
					timeout / 1000, portHolder, callIdHolder);
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

		CallAgentImpl agent = getCallAgent(portHolder.value);
		if (agent == null)
		{
			return Constant.ERR_GeneralError;
		}
		agent.callId = callIdHolder.value;
		agent.callState = Constant.StatusConnect;
		agent.ani = callingNumber;
		agent.dnis = destNumber;

		CallAgentImpl oriAgent = null;
		if (port.value >= 0)
		{
			oriAgent = getCallAgent(port.value);

			if (oriAgent.callState != Constant.StatusIdle)
			{
				oriAgent.callState = Constant.StatusConference;
			}

			oriAgent.otherPartyAgent = agent;
			agent.hostAgent = oriAgent;
		}

		int ret = agent.makeCall(agent.callId, timeout);
		if (ret == Constant.ERR_Success)
		{
			port.value = agent.resId;
			callId.value = agent.callId;
		}
		else
		{
			if (ret == Constant.ERR_TimeOut)
			{
				Log.wDebug(LogDef.id_0x1005000F, transactionId, agent.resId,
						agent.callId, callingNumber, destNumber);
				atsReleaseCall(agent.resId, agent.callId, 0);
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
				return error;
			}
		}
		// end send DTMF

		return ret;
	}

	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String voiceFileList[], int voiceLib)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			for (int i = 0; i < fileCount; i++)
			{
				String fileName = new String(voiceFileList[i]
						.getBytes("GB2312"), "ISO-8859-1");
				voiceFileList[i] = fileName;
			}
			result = vgproxy.mgPlayList(port, callId, interruptKeys, rate,
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

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			String textStr = new String(text.getBytes("GB2312"), "ISO-8859-1");
			result = vgproxy.mgPlayTTS(port, callId, interruptKeys, type,
					textStr, voiceLib, 6);
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			String fileNameStr = new String(fileName.getBytes("GB2312"),
					"ISO-8859-1");
			if (secondsPlayed != null)
			{
				result = vgproxy.mgPlayVoice(port, callId, fileNameStr,
						interruptKeys, rate, secondsPlayed.value);
			}
			else
			{
				result = vgproxy.mgPlayVoice(port, callId, fileNameStr,
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

		int timeout = 30 * 60 * 1000;
		if (secondsPlayed == null)
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			String fileNameStr = new String(fileName.getBytes("GB2312"),
					"ISO-8859-1");
			result = vgproxy.mgPlayVoice(port, callId, fileNameStr,
					interruptKeys, rate, 0);
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		buffer.value = "";

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgproxy.mgReceiveDTMF(port, callId, keyCount,
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgproxy.mgReceiveFax(port, callId, fileName);
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			String fileNameStr = new String(fileName.getBytes("GB2312"),
					"ISO-8859-1");
			result = vgproxy.mgRecordVoice(port, callId, fileNameStr, ""
					+ endFlag, maxTime, rate);
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		try
		{
			int ret = vgproxy.sgReleaseCall(port, callId);
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

		return Constant.ERR_Success;
	}

	public int atsRetrieveCall(int port, int callId, int reserved)
	{
		CallAgentImpl agent = checkCallState(port, callId);
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

		return atsReleaseCall(agent.otherPartyAgent.resId,
				agent.otherPartyAgent.callId, 0);
	}

	public int atsSendFax(int port, int callId, String fileName)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgproxy.mgSendFax(port, callId, new String(fileName
					.getBytes("GB2312"), "ISO-8859-1"));
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		try
		{
			return vgproxy.mgSendDTMF(port, callId, signals);
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
		return atsSingleStepTransfer(port, callId, callingNumber,
				oriCallingNumber, destNumber, oriDestNumber, timeout,
				routeMediaFirst, reserved, transactionId, true, 0, false,
				isVideoCall, "", "");
	}

	public int atsSingleStepTransfer(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean createConf, int mode,
			boolean reverseRoute, boolean isVideoCall, String videoUrl1,
			String videoUrl2)
	{
		CallAgentImpl agent = checkCallState(port, callId);
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
				routeMediaFirst, mode, reverseRoute, isVideoCall);
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
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		int result;
		try
		{
			result = vgproxy.rmStartConferrenceRecord(port, callId,
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

	public int atsSwitchTwoPort(int port1, int callId1, int port2,
			int reserved, String videoUrl1, String videoUrl2)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = null;
		agent = checkCallState(port1, callId1);
		if ((agent == null) && (checkCallState(port2, callId1) == null))
		{
			return Constant.ERR_NoCall;
		}

		if (agent == null)
		{
			agent = getCallAgent(port1);
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
				port2 = agent.otherPartyAgent.resId;
			}
			else
			{
				return Constant.ERR_NoCall;
			}
		}
		int result = 0;
		try
		{
			result = vgproxy.rmRouteTwoRes(port1, port2, reserved);

		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		return (result == Constant.GATEWAY_SUCCESS ? Constant.ERR_Success
				: result);
	}

	public void attachCall(int port, int callID)
	{
		CallAgentImpl agent = checkCallState(port, callID);
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
		// TODO Auto-generated method stub

	}

	public int blindCreateConference(int port1, int callId, int port2,
			boolean isVideoCall)
	{
		if (vgproxy == null)
		{
			return Constant.ERR_RPC;
		}

		int result;

		try
		{
			IntHolder confId = new IntHolder();
			result = vgproxy.rmBlindCreateConf(port1, port2, confId);
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

	CallAgentImpl checkCallState(int port, int callId)
	{
		CallAgentImpl agent = getCallAgent(port);

		agent = ((agent != null) && (agent.callId == callId) && (agent.callState != Constant.StatusIdle)) ? agent
				: null;
		if (agent != null)
		{
			agent.msgQueue.clear();
		}

		return agent;
	}

	void dispatchMessage(CallAgentMessage msg) throws Exception
	{
		switch (msg.messageType)
		{
		case Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT:
		{
			SGEvent event = ((SGEventMessage) msg).sgEvent;
			CallAgentImpl agent = getCallAgent(event.ResID);

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

				if ((agent != null) && (agent.callId == event.CallID))
				{
					agent.onDisconnect();
				}

				sgEventQueue.add(sgEvent);
			}

				break;
			}

			agent.msgQueue.add(msg);

			break;
		}

		case Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT:
		{
			MGEvent event = ((MGEventMessage) msg).mgEvent;
			CallAgentImpl agent = getCallAgent(event.ResID);

			if ((agent != null) && (agent.callId == event.CallID))
			{
				agent.msgQueue.add(msg);

				if (agent.isPlayingVoiceAsynch
						&& (event.EventID == Constant.MG_TDX_PLAY)
						&& (translateMGReason(event.Reason) == Constant.ERR_Success)
						&& (event.Reason != Constant.MGREASON_TM_USRSTOP))
				{
					// 普通的放音认为StopIO是正常结束，异步放音则以此作为终止条件
					int result;
					try
					{
						result = vgproxy.mgPlayVoice(agent.resId, agent.callId,
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

				msg = msgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);

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
					if ((sgReply.ResID != resId) || (sgReply.CallID != callId))
					{
						if (sgReply.CallID > callId)
						{
							Log.wError(LogDef.id_0x10150012, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_NoCall;
						}
						continue;
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
					MGEvent mgReply = ((MGEventMessage) msg).mgEvent;
					if ((mgReply.ResID != resId) || (mgReply.CallID != callId))
					{
						if (mgReply.CallID > callId)
						{
							Log.wError(LogDef.id_0x10150013, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_NoCall;
						}

						continue;
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
		catch (Exception e)
		{
			return Constant.ERR_RPC;
		}
	}

	synchronized CallAgentImpl getCallAgent(int resId)
	{
		if (resId < 0)
		{
			return null;
		}

		CallAgentImpl callAgent = callAgentMap.get(resId);
		if ((callAgent != null) && (callAgent.resId == resId))
		{
			return callAgent;
		}

		callAgent = new CallAgentImpl();
		callAgent.resId = resId;
		callAgentMap.put(resId, callAgent);

		return callAgent;
	}

	synchronized public int initialize()
	{
		try
		{
			if (ic != null)
			{
				ic.destroy();
			}
		}
		catch (Throwable e)
		{
		}

		ic = null;

		Log.wDebug(LogDef.id_0x10050000_2, sgIp, sgPort);

		ic = Ice.Util.initialize(new String[] { "" });
		Ice.ObjectPrx base = ic.stringToProxy("VGProxy:tcp -p " + sgPort
				+ " -h " + sgIp);
		vgproxy = VGServicePrxHelper.checkedCast(base);
		vgproxy.VGInitialize(1, monitorDnis, null);

		Log.wDebug(LogDef.id_0x1005000B, sgIp, sgPort);

		onConnect2Server();

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

				msg = msgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);
				if (msg == null)
				{
					return Constant.ERR_TimeOut;
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent sgReply = ((SGEventMessage) msg).sgEvent;

					Log.wDebug(LogDef.id_0x10050006, resId, callId,
							sgReply.ResID, sgReply.CallID, sgReply.EventID,
							sgReply.EventData);

					if ((sgReply.ResID != resId) || (sgReply.CallID != callId))
					{
						if (sgReply.CallID > callId)
						{
							Log.wError(LogDef.id_0x10150014, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}
						continue;
					}

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

	void onConnect2Server()
	{
		Iterator<Entry<Integer, CallAgentImpl>> it = callAgentMap.entrySet()
				.iterator();
		while (it.hasNext())
		{
			CallAgentImpl callAgent = it.next().getValue();
			callAgent.msgQueue.clear();
		}
	}

	void onDisconnect()
	{
		callState = Constant.StatusIdle;
		confId = -1;
		isPlayingVoiceAsynch = false;

		releaseOtherParty();
		updateHostParty();
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
		confId = -1;

		msgQueue.clear();
	}

	int processAsrMGEvent(MGEvent mgReply, StringWrapper resultWrapper)
	{
		if (mgReply.EventID == Constant.MG_TDX_ASR)
		{
			if (mgReply.Reason == Constant.MGREASON_ASR_RECGNIZED)
			{
				try
				{
					String ResultStr = new String(mgReply.DTMFString
							.getBytes("ISO-8859-1"), "GB2312");
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

				msg = msgQueue.poll(millisToWait, TimeUnit.MILLISECONDS);
				if (msg == null)
				{
					return Constant.ERR_TimeOut;
				}

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEvent event = ((SGEventMessage) msg).sgEvent;
					Log.wDebug(LogDef.id_0x10050004, resId, callId,
							event.ResID, event.CallID, event.EventID);

					if ((event.ResID != resId) || (event.CallID != callId))
					{
						if (event.CallID > callId)
						{
							Log.wError(LogDef.id_0x10150011, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}

						continue;
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
						return Constant.ERR_GeneralError;
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
				atsReleaseCall(otherPartyAgent.resId, otherPartyAgent.callId,
						0, false);
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
		if (vgproxy == null)
		{
			return;
		}

		if (!isPlayingVoiceAsynch && !force)
		{
			return;
		}

		try
		{
			vgproxy.mgStopIO(resId, callId);
		}
		catch (Throwable e)
		{
		}
		isPlayingVoiceAsynch = false;

		doMgOperation(callId, 5 * 1000);
	}

	int translateAsrReason(int reason)
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

	int translateMGEvent(int eventId, int reason)
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

	int translateMGReason(int reason)
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

	int translateSGReason(int reason)
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

	@Override
	public int atsAppendVideo(int port, int callId, String[][] infoOnVideo)
	{
		return Constant.ERR_GeneralError;
	}

	@Override
	public int atsCallRecordRing(IntWrapper port, String callingNumber,
			String oriCallNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall, String fileName, char endFlag, int maxTime,
			int rate)
	{
		// TODO Auto-generated method stub
		return Constant.ERR_GeneralError;
	}
}
