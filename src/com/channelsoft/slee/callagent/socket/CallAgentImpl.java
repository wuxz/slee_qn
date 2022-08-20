package com.channelsoft.slee.callagent.socket;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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

	static UVMGClient client;

	static LinkedBlockingQueue<AtsSGEvent> sgEventQueue = new LinkedBlockingQueue<AtsSGEvent>();

	static private ISysCfgData sysCfgData;

	String ani;

	int callId = -1;

	int callState = Constant.StatusIdle;

	String dnis;

	CallAgentMessageWrapper eventWrapper = new CallAgentMessageWrapper();

	CallAgentImpl hostAgent = null;

	boolean isPlayingVoiceAsynch = false;

	LinkedBlockingQueue<CallAgentMessage> msgQueue = new LinkedBlockingQueue<CallAgentMessage>();

	String oriAni;

	String oriDnis;

	CallAgentImpl otherPartyAgent = null;

	String playVoiceFileName;

	String playVoiceInterruptKeys;

	int playVoiceRate;

	String reserved;

	int resId = -1;

	int answerCall(int callId, int timeout)
	{
		int ret = Constant.ERR_Success;

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

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_ANSWERCALL)
				{
					MessageAck ackMsg = (MessageAck) msg;

					Log.wDebug(LogDef.id_0x10050014, resId, callId,
							ackMsg.port, ackMsg.callId, ackMsg.result);

					if ((ackMsg.port != resId) || (ackMsg.callId != callId))
					{
						if (ackMsg.callId > callId)
						{
							Log.wError(LogDef.id_0x10150010, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}

						continue;
					}

					ret = translateResult(ackMsg.result);
					if (ret != Constant.ERR_Success)
					{
						return ret;
					}

					continue;
				}
				else if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEventReply sgReply = (SGEventReply) msg;
					Log.wDebug(LogDef.id_0x10050023, resId, callId,
							sgReply.portId, sgReply.callId, sgReply.eventId);

					if ((sgReply.portId != resId) || (sgReply.callId != callId))
					{
						if (sgReply.callId > callId)
						{
							Log.wError(LogDef.id_0x10150010, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}

						continue;
					}

					switch (sgReply.eventId)
					{
					case Constant.SG_CALLCONNECTED:
						return Constant.ERR_Success;

					case Constant.SG_CALLDISCONNECTED:
						return translateSGReason(sgReply.eventData);

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

	public int atsAnswerCall(int port, int callId, int isBilling)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		AnswerCallRequest request = new AnswerCallRequest();
		request.callId = callId;
		request.port = port;
		request.isBilling = isBilling;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 2 * 60 * 1000;

		return agent.answerCall(callId, timeout);
	}

	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		ASRRequest request = new ASRRequest();
		request.callId = callId;
		request.port = port;
		request.interruptKeys = interruptKeys;
		request.rate = rate;
		request.fileCount = fileCount;
		request.voiceFileList = voiceFileList;
		request.grammar = grammar;
		request.noSpeechTimeout = noSpeechTimeout;
		request.acceptScore = acceptScore;
		request.resultNum = resultNum;
		request.timeoutBetweenWord = (int) timeoutBetweenWord;
		request.voiceLib = Constant.TTS_VOICE_LIB_WOMAN;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		CallAgentMessageWrapper eventWrapper = agent.eventWrapper;
		if (eventWrapper.event != null)
		{
			eventWrapper = new CallAgentMessageWrapper();
		}
		eventWrapper.event = request;

		int ret = agent.doMgOperation(callId, 3 * 60 * 1000,
				Constant.SOFTSWITCH_MESSAGE_REPLY_ASR, eventWrapper);
		if (ret == Constant.ERR_Success)
		{
			ASRReply reply = (ASRReply) eventWrapper.event;
			if (ret == Constant.ERR_Success)
			{
				result.value = reply.result;
			}
		}

		eventWrapper.event = null;

		return ret;
	}

	public int atsASR2(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord, int DTMF_dtmfCount,
			String DTMF_endFlag, int DTMF_timeoutSecond, int DTMF_betweenTimeout)
	{
		System.out.println("interruptKeys=  " + interruptKeys);
		System.out.println("fileCount=  " + fileCount);
		System.out.println("noSpeechTimeout=  " + noSpeechTimeout);
		System.out.println("acceptScore=  " + acceptScore);
		System.out.println("resultNum=  " + resultNum);
		System.out.println("timeoutBetweenWord=  " + timeoutBetweenWord);
		System.out.println("DTMF_dtmfCount=  " + DTMF_dtmfCount);
		System.out.println("DTMF_endFlag=  " + DTMF_endFlag);
		System.out.println("DTMF_timeoutSecond=  " + DTMF_timeoutSecond);
		System.out.println("DTMF_betweenTimeout=  " + DTMF_betweenTimeout);
		int count = 0;
		for (String s : voiceFileList)
		{
			count++;
			System.out.println("file" + count + "=  " + s);
		}
		return Constant.ERR_Success;
	}

	public int atsClearDTMFBuffer(int port, int callId)
	{
		return Constant.ERR_Success;
	}

	public int atsCloseConf(int port, int callId)
	{
		return Constant.ERR_GeneralError;
	}

	public int atsDisSwitchTwoPort(int port1, int callId1, int port2,
			int reserved)
	{
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

		CallAgentMessage request = null;
		DisSwitchPortRequest request1 = new DisSwitchPortRequest();
		request1.port1 = port1;
		request1.port2 = port2;
		request1.reserved = reserved;
		request = request1;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		CallAgentMessageWrapper eventWrapper = agent.eventWrapper;
		if (eventWrapper.event != null)
		{
			eventWrapper = new CallAgentMessageWrapper();
		}
		eventWrapper.event = request;
		int ret = agent.doMgOperation(callId1, 30 * 1000,
				Constant.SOFTSWITCH_MESSAGE_REPLY_DISSWITCH, eventWrapper);
		eventWrapper.event = null;
		return ret;
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
		CallAgentImpl agent = getCallAgent(port);

		agent = ((agent != null) && (agent.callId == callId) && (agent.callState == Constant.StatusConference)) ? agent
				: null;
		if (agent == null)
		{
			return -1;
		}
		else if (agent.otherPartyAgent == null)
		{
			return -1;
		}

		return agent.otherPartyAgent.resId;
	}

	public AtsSGEvent atsGetSGEvent()
	{
		AtsSGEvent event = null;
		client.hasReceivedMsg = false;

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

		if (!client.hasReceivedMsg)
		{
			// 30秒没有消息，则发一次测试消息
			client.register();
		}

		try
		{
			event = sgEventQueue.poll(30, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
		}

		if ((event == null) && !client.hasReceivedMsg)
		{
			// 如果30后还是没有消息，则认为连接中断
			Log.wDebug(LogDef.id_0x10050025);
			client.closeConnection();
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
		client = new UVMGClient(mgIp, mgPort, monitorDnis, this);
		client.open();

		return 1;
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
				reserved, transactionId, routeMedia, 0, false, isVideoCall);
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
		MakeCallRequest request = new MakeCallRequest();
		request.calledNumber = destNumber;
		request.callingNumber = callingNumber;
		request.reserved = reserved;
		request.timeout = timeout;
		request.transactionId = transactionId;

		MakeCallAgent makeCallAgent = MakeCallAgent.getMakeCallAgent(
				transactionId, this);
		if (makeCallAgent.result == -1)
		{
			Log.wError(LogDef.id_0x10150019, transactionId);
			return Constant.ERR_DestBusy;
		}

		// result为-1表示正在外拨
		makeCallAgent.result = -1;

		try
		{
			if (!client.sendPackage(request))
			{
				return Constant.ERR_RPC;
			}

			int result = makeCallAgent.result;

			if (result == -1)
			{
				try
				{
					synchronized (makeCallAgent)
					{
						makeCallAgent.wait(timeout);
					}

					result = makeCallAgent.result;
				}
				catch (Exception e)
				{
					return Constant.ERR_GeneralError;
				}
			}

			if (result == -1)
			{
				result = Constant.ERR_TimeOut;
			}

			Log.wDebug(LogDef.id_0x1005000C, makeCallAgent.port,
					makeCallAgent.callId, callingNumber, destNumber, result);
			if (result != Constant.ERR_Success)
			{
				return result;
			}

			Log.wDebug(LogDef.id_0x1005000D, makeCallAgent.port,
					makeCallAgent.callId, callingNumber, destNumber);

			if (routeMedia)
			{
				int ret = Constant.ERR_GeneralError;
				if (reverseRoute)
				{
					ret = atsSwitchTwoPort(makeCallAgent.port, callId.value,
							port.value, mode);
					Log
							.wDebug(LogDef.id_0x1005000E, makeCallAgent.port,
									makeCallAgent.callId, port.value,
									callId.value, ret);
				}
				else
				{
					ret = atsSwitchTwoPort(port.value, callId.value,
							makeCallAgent.port, mode);
					Log.wDebug(LogDef.id_0x1005000E, port.value, callId.value,
							makeCallAgent.port, makeCallAgent.callId, ret);
				}

				if (ret != Constant.ERR_Success)
				{
					Log.wError(LogDef.id_0x10150016, port.value, callId.value,
							makeCallAgent.port, makeCallAgent.callId);
					atsReleaseCall(makeCallAgent.port, makeCallAgent.callId, 0);
					return Constant.ERR_GeneralError;
				}
			}

			CallAgentImpl agent = getCallAgent(makeCallAgent.port);
			if (agent == null)
			{
				return Constant.ERR_GeneralError;
			}
			agent.callId = makeCallAgent.callId;
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
					Log.wDebug(LogDef.id_0x1005000F, transactionId,
							agent.resId, agent.callId, callingNumber,
							destNumber);
					atsReleaseCall(agent.resId, agent.callId, 0);
				}

				if (agent.callId == makeCallAgent.callId)
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
		finally
		{
			// 复位外呼标志
			makeCallAgent.result = 0;
		}
	}

	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String voiceFileList[], int voiceLib)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		PlayListRequest request = new PlayListRequest();
		request.callId = callId;
		request.port = port;
		request.interruptKeys = interruptKeys;
		request.voiceLib = voiceLib;
		request.fileCount = fileCount;
		request.voiceFileList = voiceFileList;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		PlayTTSRequest request = new PlayTTSRequest();
		request.callId = callId;
		request.port = port;
		request.interruptKeys = interruptKeys;
		request.type = type;
		request.text = text;
		request.voiceLib = voiceLib;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsPlayVoice(int port, int callId, String fileName,
			String interruptKeys, int rate, IntWrapper secondsPlayed,
			Calendar lastInteractionTime)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		PlayVoiceRequest request = new PlayVoiceRequest();
		request.callId = callId;
		request.port = port;
		request.fileName = fileName;
		request.interruptKeys = interruptKeys;
		request.rate = rate;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout, -1, null, secondsPlayed,
				lastInteractionTime);
	}

	public int atsPlayVoiceAsync(int port, int callId, String fileName,
			String interruptKeys, int rate)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		PlayVoiceRequest request = new PlayVoiceRequest();
		request.callId = callId;
		request.port = port;
		request.fileName = fileName;
		request.interruptKeys = interruptKeys;
		request.rate = rate;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
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
		buffer.value = "";

		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		ReceiveDTMFRequest request = new ReceiveDTMFRequest();
		request.callId = callId;
		request.port = port;
		request.maxTime = maxTime;
		request.iddTime = betweenTime;
		request.interruptKeys = interruptKeys;
		request.keyCount = keyCount;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		CallAgentMessageWrapper eventWrapper = agent.eventWrapper;
		if (eventWrapper.event != null)
		{
			eventWrapper = new CallAgentMessageWrapper();
		}
		eventWrapper.event = request;

		int ret = agent.doMgOperation(callId, 5 * 60 * 1000,
				Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF, eventWrapper);
		if (ret == Constant.ERR_Success)
		{
			ReceiveDtmfReply reply = (ReceiveDtmfReply) eventWrapper.event;
			ret = translateMGEvent(reply.eventId, reply.reason);
			buffer.value = reply.dtmf;
		}

		eventWrapper.event = null;

		return ret;
	}

	public int atsReceiveFax(int port, int callId, String fileName)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		ReceiveFaxRequest request = new ReceiveFaxRequest();
		request.callId = callId;
		request.port = port;
		request.fileName = fileName;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsRecordVoice(int port, int callId, String fileName,
			char endFlag, int maxTime, int rate)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		RecordVoiceRequest request = new RecordVoiceRequest();
		request.callId = callId;
		request.port = port;
		request.fileName = fileName;
		char endFlags[] = { endFlag };
		request.interruptKeys = new String(endFlags);
		request.maxTime = maxTime;
		request.rate = rate;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
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
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		ReleaseCallRequest request = new ReleaseCallRequest();
		request.callId = callId;
		request.port = port;
		request.reserved = reserved;

		if (!client.sendPackage(request))
		{
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
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		SendFaxRequest request = new SendFaxRequest();
		request.callId = callId;
		request.port = port;
		request.fileName = fileName;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 60 * 1000;

		return agent.doMgOperation(callId, timeout);
	}

	public int atsSendSignals(int port, int callId, String signals)
	{
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		SendDtmfRequest request = new SendDtmfRequest();
		request.callId = callId;
		request.port = port;
		request.dtmf = signals;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 1000;

		return agent.doMgOperation(callId, timeout,
				Constant.SOFTSWITCH_MESSAGE_ACK_SENDDTMF);
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
		CallAgentImpl agent = checkCallState(port, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		agent.stopIo();

		ConferenceRecordRequest request = new ConferenceRecordRequest();
		request.callId = callId;
		request.port = port;
		request.fileName = recordFileName;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		int timeout = 30 * 1000;

		CallAgentMessageWrapper eventWrapper = agent.eventWrapper;
		if (eventWrapper.event != null)
		{
			eventWrapper = new CallAgentMessageWrapper();
		}
		eventWrapper.event = request;

		return agent.doMgOperation(callId, timeout,
				Constant.SOFTSWITCH_MESSAGE_REPLY_STARTCONFERENCERECORD,
				eventWrapper);
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2, int mode)
	{
		return atsSwitchTwoPort(port1, callId1, port2, mode, "", "");
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2,
			int reserved, String videoUrl1, String videoUrl2)
	{
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

		CallAgentMessage request = null;

		SwitchPortRequest request1 = new SwitchPortRequest();
		request1.port1 = port1;
		request1.port2 = port2;
		request1.reserved = reserved;
		request = request1;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		CallAgentMessageWrapper eventWrapper = agent.eventWrapper;
		if (eventWrapper.event != null)
		{
			eventWrapper = new CallAgentMessageWrapper();
		}
		eventWrapper.event = request;
		int ret = agent.doMgOperation(callId1, 30 * 1000,
				Constant.SOFTSWITCH_MESSAGE_REPLY_SWITCH, eventWrapper);
		eventWrapper.event = null;
		return ret;
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
		BlindCreateConferenceRequest request = new BlindCreateConferenceRequest();
		request.port1 = port1;
		request.port2 = port2;
		request.callId1 = callId;

		if (!client.sendPackage(request))
		{
			return Constant.ERR_RPC;
		}

		CallAgentImpl agent = checkCallState(port1, callId);
		if (agent == null)
		{
			return Constant.ERR_NoCall;
		}

		CallAgentMessageWrapper eventWrapper = agent.eventWrapper;
		if (eventWrapper.event != null)
		{
			eventWrapper = new CallAgentMessageWrapper();
		}
		eventWrapper.event = request;

		return agent.doMgOperation(callId, 30 * 1000,
				Constant.SOFTSWITCH_MESSAGE_REPLY_BLINDCREATECONFERENCE,
				eventWrapper);
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
		case Constant.SOFTSWITCH_MESSAGE_REQUEST_INCOMINGCALL:
		{
			IncomingCall event = (IncomingCall) msg;
			AtsSGEvent sgEvent = new AtsSGEvent();
			sgEvent.callSN = event.callId;
			sgEvent.eventID = Constant.Event_InboundCall;
			sgEvent.port = event.portId;

			CallAgentImpl agent = getCallAgent(event.portId);
			if (agent != null)
			{
				agent.onIncomingCall(event);
			}

			sgEventQueue.add(sgEvent);

			return;
		}

		case Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT:
		{
			SGEventReply event = (SGEventReply) msg;
			CallAgentImpl agent = getCallAgent(event.portId);

			if ((event.eventId == Constant.SG_CALLDISCONNECTED)
					|| (event.eventId == Constant.SG_CALLRELEASED)
					|| (event.eventId == Constant.SG_LINE_START)
					|| (event.eventId == Constant.SG_LINE_DOWN)
					|| (event.eventId == Constant.SG_LINE_IN_SERVICE)
					|| (event.eventId == Constant.SG_LINE_OUT_SERVICE))
			{
				AtsSGEvent sgEvent = new AtsSGEvent();
				sgEvent.callSN = event.callId;
				sgEvent.eventID = Constant.Event_Disconnect;
				sgEvent.port = event.portId;

				if ((agent != null) && (agent.callId == event.callId))
				{
					agent.onDisconnect();
				}

				sgEventQueue.add(sgEvent);
			}

			agent.msgQueue.add(msg);
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_ACK_MAKECALL:
		{
			MakeCallAck event = (MakeCallAck) msg;
			MakeCallAgent agent = MakeCallAgent.getMakeCallAgent(
					event.transactionId, this);
			if (agent != null)
			{
				agent.processAck(event);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_MAKECALL:
		{
			MakeCallReply event = (MakeCallReply) msg;
			CallAgentImpl agent = getCallAgent(event.port);
			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_SWITCH:
		case Constant.SOFTSWITCH_MESSAGE_REPLY_DISSWITCH:
		{
			SwitchPortReply event = (SwitchPortReply) msg;
			CallAgentImpl agent = getCallAgent(event.port1);
			if (agent != null)
			{
				agent.msgQueue.add(msg);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT:
		{
			MGEventReply event = (MGEventReply) msg;
			CallAgentImpl agent = getCallAgent(event.resId);

			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);

				if (agent.isPlayingVoiceAsynch
						&& (event.eventId == Constant.MG_TDX_PLAY)
						&& (translateMGReason(event.reason) == Constant.ERR_Success)
						&& (event.reason != Constant.MGREASON_TM_USRSTOP))
				{
					// 普通的放音认为StopIO是正常结束，异步放音则以此作为终止条件
					PlayVoiceRequest request = new PlayVoiceRequest();
					request.callId = agent.callId;
					request.port = agent.resId;
					request.fileName = agent.playVoiceFileName;
					request.interruptKeys = agent.playVoiceInterruptKeys;
					request.rate = agent.playVoiceRate;

					if (!client.sendPackage(request))
					{
						agent.isPlayingVoiceAsynch = false;
					}
				}

				if (agent.isPlayingVoiceAsynch
						&& (event.eventId == Constant.MG_TDX_PLAY)
						&& (translateMGReason(event.reason) == Constant.ERR_Success)
						&& (event.reason == Constant.MGREASON_TM_USRSTOP))
				{
					agent.isPlayingVoiceAsynch = false;
				}
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF:
		{
			ReceiveDtmfReply event = (ReceiveDtmfReply) msg;
			CallAgentImpl agent = getCallAgent(event.resId);
			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_ASR:
		{
			ASRReply event = (ASRReply) msg;
			CallAgentImpl agent = getCallAgent(event.resId);
			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_BLINDCREATECONFERENCE:
		{
			BlindCreateConferenceReply event = (BlindCreateConferenceReply) msg;
			CallAgentImpl agent = getCallAgent(event.port1);
			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_STARTCONFERENCERECORD:
		{
			ConferenceRecordReply event = (ConferenceRecordReply) msg;
			CallAgentImpl agent = getCallAgent(event.port);
			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);
			}
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_ACK_ANSWERCALL:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RELEASECALL:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEFAX:
		case Constant.SOFTSWITCH_MESSAGE_ACK_SENDFAX:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEDTMF:
		case Constant.SOFTSWITCH_MESSAGE_ACK_SENDDTMF:
		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYVOICE:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RECORDVOICE:
		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYTTS:
		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYLIST:
		case Constant.SOFTSWITCH_MESSAGE_ACK_ASR:
		case Constant.SOFTSWITCH_MESSAGE_ACK_STOPIO:
		{
			MessageAck event = (MessageAck) msg;
			CallAgentImpl agent = getCallAgent(event.port);
			if ((agent != null) && (agent.callId == event.callId))
			{
				agent.msgQueue.add(msg);
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
			CallAgentMessageWrapper eventWrapper)
	{
		return doMgOperation(callId, timeout, waitMsgType, eventWrapper, null,
				null);
	}

	/**
	 * @param callId
	 *            呼叫ID
	 * @param timeout
	 *            超时设定
	 * @param waitMsgType
	 *            当前操作等待的事件类型
	 * @param eventWrapper
	 *            返回事件，事件类型和waitMsgType一致，否则方法转入错误处理
	 * @param secondsPlayed
	 *            已放音时长，仅为playVoice调用时需要用到的参数，其它操作传值为null
	 * @param lastInteractionTime
	 *            最后一次用户交互时间，仅为playVoice调用时需要用到的参数，其它操作传值为null
	 * @return 调用结果
	 */
	int doMgOperation(int callId, int timeout, int waitMsgType,
			CallAgentMessageWrapper eventWrapper, IntWrapper secondsPlayed,
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
					SGEventReply sgReply = (SGEventReply) msg;
					if ((sgReply.portId != resId) || (sgReply.callId != callId))
					{
						if (sgReply.callId > callId)
						{
							Log.wError(LogDef.id_0x10150012, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_NoCall;
						}
						continue;
					}

					switch (sgReply.eventId)
					{
					case Constant.SG_CALLCONNECTED:
						return Constant.ERR_Success;

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

				if ((waitMsgType != -1) && (msg.messageType != waitMsgType))
				{
					continue;
				}

				if ((msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_PLAYVOICE)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEFAX)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_SENDFAX)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEDTMF)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_SENDDTMF)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_RECORDVOICE)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_PLAYTTS)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_PLAYLIST)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_STOPIO)
						|| (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_ASR))
				{
					MessageAck ack = (MessageAck) msg;
					if ((ack.port != resId) || (ack.callId != callId))
					{
						if (ack.callId > callId)
						{
							Log.wError(LogDef.id_0x10150013, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_NoCall;
						}

						continue;
					}

					int error = translateResult(ack.result);
					if (error != Constant.ERR_Success)
					{
						return error;
					}

					if ((waitMsgType != -1) && (msg.messageType == waitMsgType))
					{
						return error;
					}
				}
				else if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT)
				{
					MGEventReply mgReply = (MGEventReply) msg;
					if ((mgReply.resId != resId) || (mgReply.callId != callId))
					{
						if (mgReply.callId > callId)
						{
							Log.wError(LogDef.id_0x10150013, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_NoCall;
						}
						continue;
					}
					if (secondsPlayed != null)
					{
						if ((mgReply.eventId == Constant.MG_TDX_PLAY)
								&& ((mgReply.reason == Constant.MGREASON_TM_DIGIT) || (mgReply.reason == Constant.MGREASON_TM_MAXDTMF)))
						{
							secondsPlayed.value = -1;
							lastInteractionTime = Calendar.getInstance();
						}
					}

					return translateMGEvent(mgReply.eventId, mgReply.reason);
				}
				else if ((waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_ASR)
						&& (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_ASR))
				{
					if (eventWrapper != null)
					{
						ASRRequest request = (ASRRequest) eventWrapper.event;
						ASRReply event = (ASRReply) msg;
						if ((request.port == event.resId)
								&& (request.callId == event.callId))
						{
							eventWrapper.event = event;
							return translateAsrEvent(event.eventId,
									event.reason);
						}
						else
						{
							if (event.callId > callId)
							{
								Log.wError(LogDef.id_0x10150013, resId, callId);
								msgQueue.add(msg);
								return Constant.ERR_NoCall;
							}
						}
					}
				}
				else if (((waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_SWITCH) && (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SWITCH))
						|| ((waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_DISSWITCH) && (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_DISSWITCH)))
				{
					if (eventWrapper != null)
					{
						SwitchPortRequest request = (SwitchPortRequest) eventWrapper.event;
						SwitchPortReply event = (SwitchPortReply) msg;
						if (request.port1 == event.port1)
						{
							return translateResult(event.result);
						}
					}
				}
				else if ((waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF)
						&& (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF))
				{
					if (eventWrapper != null)
					{
						ReceiveDTMFRequest request = (ReceiveDTMFRequest) eventWrapper.event;
						ReceiveDtmfReply event = (ReceiveDtmfReply) msg;
						if ((request.port == event.resId)
								&& (request.callId == event.callId))
						{
							eventWrapper.event = event;
							return Constant.ERR_Success;
						}
						else
						{
							if (event.callId > callId)
							{
								Log.wError(LogDef.id_0x10150013, resId, callId);
								msgQueue.add(msg);
								return Constant.ERR_NoCall;
							}
						}
					}
				}
				else if ((waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_STARTCONFERENCERECORD)
						&& (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_STARTCONFERENCERECORD))
				{
					if (eventWrapper != null)
					{
						ConferenceRecordRequest request = (ConferenceRecordRequest) eventWrapper.event;
						ConferenceRecordReply event = (ConferenceRecordReply) msg;
						if ((request.port == event.port)
								&& (request.callId == event.callId))
						{
							return translateResult(event.result);
						}
						else
						{
							if (event.callId > callId)
							{
								Log.wError(LogDef.id_0x10150013, resId, callId);
								msgQueue.add(msg);
								return Constant.ERR_NoCall;
							}
						}
					}
				}
				else if ((waitMsgType == Constant.SOFTSWITCH_MESSAGE_REPLY_BLINDCREATECONFERENCE)
						&& (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_BLINDCREATECONFERENCE))
				{
					if (eventWrapper != null)
					{
						BlindCreateConferenceRequest request = (BlindCreateConferenceRequest) eventWrapper.event;
						BlindCreateConferenceReply event = (BlindCreateConferenceReply) msg;
						if ((request.port1 == event.port1)
								&& (request.callId1 == event.callId))
						{
							return translateResult(event.result);
						}
						else
						{
							if (event.callId > callId)
							{
								Log.wError(LogDef.id_0x10150013, resId, callId);
								msgQueue.add(msg);
								return Constant.ERR_NoCall;
							}
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

	/**
	 * @param args
	 */
	public void main(String[] args) throws Exception
	{
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
					SGEventReply sgReply = (SGEventReply) msg;

					Log.wDebug(LogDef.id_0x10050006, resId, callId,
							sgReply.portId, sgReply.callId, sgReply.eventId,
							sgReply.eventData);

					if ((sgReply.portId != resId) || (sgReply.callId != callId))
					{
						if (sgReply.callId > callId)
						{
							Log.wError(LogDef.id_0x10150014, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}
						continue;
					}

					switch (sgReply.eventId)
					{
					case Constant.SG_CALLCONNECTED:
						return Constant.ERR_Success;

					case Constant.SG_CALLDISCONNECTED:
						return translateSGReason(sgReply.eventData);

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
				else if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_MAKECALL)
				{
					MakeCallReply reply = (MakeCallReply) msg;
					if ((reply.port != resId) || (reply.callId != callId))
					{
						continue;
					}

					return translateResult(reply.result);
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
		isPlayingVoiceAsynch = false;

		releaseOtherParty();
		updateHostParty();
	}

	void onIncomingCall(IncomingCall event)
	{
		callId = event.callId;
		ani = event.ani;
		dnis = event.dnis;
		oriAni = event.oriAni;
		oriDnis = event.oriDnis;
		reserved = event.reserved;

		callState = Constant.StatusConnect;

		msgQueue.clear();
	}

	int releaseCall(int callId, int timeout)
	{
		int ret = Constant.ERR_Success;

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

				if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_ACK_RELEASECALL)
				{
					MessageAck ackMsg = (MessageAck) msg;

					Log.wDebug(LogDef.id_0x10050015, resId, callId,
							ackMsg.port, ackMsg.callId, ackMsg.result);

					if ((ackMsg.port != resId) || (ackMsg.callId != callId))
					{
						if (ackMsg.callId > callId)
						{
							Log.wError(LogDef.id_0x10150011, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}

						continue;
					}

					ret = translateResult(ackMsg.result);
					if (ret != Constant.ERR_Success)
					{
						return ret;
					}

					continue;
				}
				else if (msg.messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT)
				{
					SGEventReply sgReply = (SGEventReply) msg;
					Log.wDebug(LogDef.id_0x10050004, resId, callId,
							sgReply.portId, sgReply.callId, sgReply.eventId);

					if ((sgReply.portId != resId) || (sgReply.callId != callId))
					{
						if (sgReply.callId > callId)
						{
							Log.wError(LogDef.id_0x10150011, resId, callId);
							msgQueue.add(msg);
							return Constant.ERR_GeneralError;
						}

						continue;
					}

					switch (sgReply.eventId)
					{
					case Constant.SG_CALLCONNECTED:
						return Constant.ERR_GeneralError;

					case Constant.SG_CALLDISCONNECTED:
					case Constant.SG_CALLRELEASED:
					case Constant.SG_LINE_START:
					case Constant.SG_LINE_DOWN:
					case Constant.SG_LINE_IN_SERVICE:
					case Constant.SG_LINE_OUT_SERVICE:
						return Constant.ERR_Success;
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

	/**
	 * @param force
	 *            是否强制停止底层的IO操作
	 */
	void stopIo(boolean force)
	{
		if (!isPlayingVoiceAsynch && !force)
		{
			return;
		}

		StopIORequest request = new StopIORequest();
		request.callId = callId;
		request.port = resId;

		if (!client.sendPackage(request))
		{
			return;
		}

		isPlayingVoiceAsynch = false;

		int ret = doMgOperation(callId, 5 * 1000,
				Constant.SOFTSWITCH_MESSAGE_ACK_STOPIO);
		if (ret == Constant.ERR_Success)
		{
			ret = doMgOperation(callId, 5 * 1000);
		}
	}

	int translateAsrEvent(int eventId, int reason)
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
			return Constant.ERR_Success;

		case Constant.MGREASON_TM_DISCONNECT:
			return Constant.ERR_Disconnect;
		case Constant.MGREASON_TM_USRSTOP:
			return Constant.ERR_Success;

		case Constant.MGREASON_TM_MAXTIME:
			return Constant.ERR_TimeOut;

		default:
			return Constant.ERR_GeneralError;
		}
	}

	int translateResult(int code)
	{
		int ret = Constant.ERR_GeneralError;

		switch (code)
		{
		case Constant.SG_NO_ENOUGH_DIAL_RES:
		case Constant.ERR_ASR_EXCEPTION:
		case Constant.ERR_TTS_CONVERT:
		case Constant.ERR_FATALERROR:
		case Constant.ERR_UNSUPPORTED_STATE:
		case Constant.ERR_GENERALERROR:
			ret = Constant.ERR_GeneralError;
			break;
		case Constant.ERR_INVALID_RESID:
			ret = Constant.ERR_IVALID_DN;
			break;
		case Constant.ERR_INVALID_FILE:
			ret = Constant.ERR_InvalidFile;
			break;
		case Constant.ERR_REMOTE_DISCONNECT:
		case Constant.ERR_NOCALL_STATE:
			ret = Constant.ERR_NoCall;
			break;
		case Constant.ERR_EXISTCALL_STATE:
			ret = Constant.ERR_ChannelBusy;
			break;
		case Constant.ERR_INVLAIDARG:
			ret = Constant.ERR_IVALID_PARAM;
			break;
		case Constant.GATEWAY_SUCCESS:
			ret = Constant.ERR_Success;
			break;
		}

		return ret;
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
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall, String fileName, char endFlag, int maxTime,
			int rate)
	{
		// TODO Auto-generated method stub
		return Constant.ERR_GeneralError;
	}

}
