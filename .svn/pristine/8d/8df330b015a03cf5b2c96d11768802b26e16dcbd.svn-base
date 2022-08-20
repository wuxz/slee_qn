/**
 * 
 */
package com.channelsoft.slee.callagent.ucce;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.channelmanager.AtsSGEvent;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;
import com.channelsoft.ucce.protocol.UCCEService;
import com.channelsoft.ucce.protocol.UCCEService.AtsASRRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsASRResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsAdjustSpeedRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsAdjustVolumeRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsAnswerCallRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsAttachCallRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsAttachOtherPartyRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsBlindCreateConferenceRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsClearDTMFBufferRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsCloseConfRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsDisSwitchTwoPortRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsGetCallInfoRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsGetCallInfoResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsGetOtherPartyCallSnRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsGetOtherPartyTrunkIDRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsMakeCallRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsMakeCallResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsPlayListRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsPlayTTSRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsPlayVoiceAsyncRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsPlayVoiceRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsPlayVoiceResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsQueryCallStateRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsQueryCallStateResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsReceiveDTMFRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsReceiveDTMFResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsReceiveFaxRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsRecordVoiceRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsReleaseCallRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsRetrieveCallRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsSGEventRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsSendFaxRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsSendSignalsRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsSingleStepConferenceRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsSingleStepTransferRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsStartConferencRecordingRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsSwitchTwoPortRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsVoiceEditRequest;
import com.channelsoft.ucce.protocol.UCCEService.AtsVoiceEditResponse;
import com.channelsoft.ucce.protocol.UCCEService.AtsVoiceListEditRequest;
import com.channelsoft.ucce.protocol.UCCEService.MessageType;
import com.channelsoft.ucce.protocol.UCCEService.UCCEMessage;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;

/**
 * @author wuxz
 */
class CallerWaiter
{
	UCCEMessage msg = null;

	CyclicBarrier waiter = new CyclicBarrier(2);
}

public class UCCEClientHandler extends SimpleChannelUpstreamHandler
{
	/**
	 * 
	 */
	private static final int OPERATE_TIMEOUT_SECONDS = 30;

	private static final int PLAY_TIMEOUT_SECONDS = 60 * 60;

	private static final int RECEIVE_TIMEOUT_SECONDS = 3 * 60;

	static LinkedBlockingQueue<AtsSGEvent> sgEvents = new LinkedBlockingQueue<AtsSGEvent>();

	HashMap<Integer, CallerWaiter> callerWaiters = new HashMap<Integer, CallerWaiter>();

	private volatile Channel channel;

	AtomicInteger sequenceIdSeed = new AtomicInteger(0);

	public int atsAdjustSpeed(int port, int callId, int adjment)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsAdjustSpeedRequest.Builder builder = AtsAdjustSpeedRequest
				.newBuilder();

		builder.setAdjment(adjment);

		return sendRequest(UCCEService.adjustSpeedRequest, builder.build(),
				MessageType.AtsAdjustSpeedRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsAdjustVolume(int port, int callId, int adjment)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsAdjustVolumeRequest.Builder builder = AtsAdjustVolumeRequest
				.newBuilder();

		builder.setAdjment(adjment);

		return sendRequest(UCCEService.adjustVolumeRequest, builder.build(),
				MessageType.AtsAdjustVolumeRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsAnswerCall(int port, int callId, int isBilling)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsAnswerCallRequest.Builder builder = AtsAnswerCallRequest
				.newBuilder();
		builder.setIsBilling(isBilling);

		return sendRequest(UCCEService.answerCallRequest, builder.build(),
				MessageType.AtsAnswerCallRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String[] voiceFileList,
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord, int DTMF_dtmfCount,
			String DTMF_endFlag, int DTMF_timeoutSecond, int DTMF_betweenTimeout)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsASRRequest.Builder builder = AtsASRRequest.newBuilder();
		builder.setAcceptScore(acceptScore);
		builder.setFileCount(fileCount);
		builder.setGrammar(grammar);
		builder.setInterruptKeys(interruptKeys);
		builder.setNoSpeechTimeout(noSpeechTimeout);
		builder.setRate(rate);
		builder.setResultNum(resultNum);
		builder.setTimeoutBetweenWord(timeoutBetweenWord);
		builder.setDTMFBetweenTimeout(DTMF_betweenTimeout);
		builder.setDTMFDtmfCount(DTMF_dtmfCount);
		builder.setDTMFEndFlag(DTMF_endFlag);
		builder.setDTMFTimeoutSecond(DTMF_timeoutSecond);

		for (String file : voiceFileList)
		{
			builder.addVoiceFileList(file);
		}

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.asrRequest, builder
					.build(), MessageType.AtsASRRequestType, port, callId,
					RECEIVE_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsASRResponse response = msg
						.getExtension(UCCEService.asrResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					result.value = response.getResult();
				}

				return response.getReturnValue();
			}
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsAttachCall(int port, int callId)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsAttachCallRequest.Builder builder = AtsAttachCallRequest
				.newBuilder();

		return sendRequest(UCCEService.attachCallRequest, builder.build(),
				MessageType.AtsAttachCallRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsAttachOtherParty(int port, int callId, int otherPort,
			int otherCallId)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsAttachOtherPartyRequest.Builder builder = AtsAttachOtherPartyRequest
				.newBuilder();
		builder.setOtherCallId(otherCallId);
		builder.setOtherPort(otherPort);

		return sendRequest(UCCEService.attachOtherPartyRequest,
				builder.build(), MessageType.AtsAttachOtherPartyRequestType,
				port, callId, OPERATE_TIMEOUT_SECONDS);
	}

	public int atsBlindCreateConference(int port, int callId, int port2,
			boolean isVideoCall)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsBlindCreateConferenceRequest.Builder builder = AtsBlindCreateConferenceRequest
				.newBuilder();
		builder.setPort2(port2);
		builder.setIsVideoCall(isVideoCall);

		return sendRequest(UCCEService.blindCreateConferenceRequest, builder
				.build(), MessageType.AtsBlindCreateConferenceRequestType,
				port, callId, OPERATE_TIMEOUT_SECONDS);
	}

	public int atsClearDTMFBuffer(int port, int callId)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsClearDTMFBufferRequest.Builder builder = AtsClearDTMFBufferRequest
				.newBuilder();

		return sendRequest(UCCEService.clearDTMFBufferRequest, builder.build(),
				MessageType.AtsClearDTMFBufferRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsCloseConf(int port, int callId)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsCloseConfRequest.Builder builder = AtsCloseConfRequest.newBuilder();

		return sendRequest(UCCEService.closeConfRequest, builder.build(),
				MessageType.AtsCloseConfRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsDisSwitchTwoPort(int port1, int callId1, int port2,
			int reserved)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsDisSwitchTwoPortRequest.Builder builder = AtsDisSwitchTwoPortRequest
				.newBuilder();
		builder.setPort2(port2);

		return sendRequest(UCCEService.disSwitchTwoPortRequest,
				builder.build(), MessageType.AtsDisSwitchTwoPortRequestType,
				port1, callId1, OPERATE_TIMEOUT_SECONDS);
	}

	public int atsGetCallInfo(int port, int callId, StringWrapper ani,
			StringWrapper dnis, StringWrapper oriAni, StringWrapper oriDnis,
			StringWrapper reserved, BooleanWrapper isVideoCall)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsGetCallInfoRequest.Builder builder = AtsGetCallInfoRequest
				.newBuilder();

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.getCallInfoRequest,
					builder.build(), MessageType.AtsGetCallInfoRequestType,
					port, callId, OPERATE_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsGetCallInfoResponse response = msg
						.getExtension(UCCEService.getCallInfoResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					ani.value = response.getAni();
					dnis.value = response.getDnis();
					oriAni.value = response.getOriAni();
					oriDnis.value = response.getOriDnis();
					isVideoCall.value = response.getIsVideoCall();
				}

				return response.getReturnValue();
			}
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsGetOtherPartyCallSn(int port, int callId)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsGetOtherPartyCallSnRequest.Builder builder = AtsGetOtherPartyCallSnRequest
				.newBuilder();

		return sendRequest(UCCEService.getOtherPartyCallSnRequest, builder
				.build(), MessageType.AtsGetOtherPartyCallSnRequestType, port,
				callId, OPERATE_TIMEOUT_SECONDS);
	}

	public int atsGetOtherPartyTrunkID(int port, int callId)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsGetOtherPartyTrunkIDRequest.Builder builder = AtsGetOtherPartyTrunkIDRequest
				.newBuilder();

		return sendRequest(UCCEService.getOtherPartyTrunkIDRequest, builder
				.build(), MessageType.AtsGetOtherPartyTrunkIDRequestType, port,
				callId, OPERATE_TIMEOUT_SECONDS);
	}

	public int atsMakeCall(IntWrapper port, String callingNumber,
			String destNumber, IntWrapper callId, int timeout, int reserved,
			int transactionId, boolean isVideoCall)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsMakeCallRequest.Builder builder = AtsMakeCallRequest.newBuilder();
		builder.setCallingNumber(callingNumber);
		builder.setDestNumber(destNumber);
		builder.setTimeout(timeout);
		builder.setIsVideoCall(isVideoCall);

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.makeCallRequest, builder
					.build(), MessageType.AtsMakeCallRequestType, port.value,
					callId.value, OPERATE_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsMakeCallResponse response = msg
						.getExtension(UCCEService.makeCallResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					callId.value = msg.getCallId();
					port.value = msg.getPort();
				}

				return response.getReturnValue();
			}
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String[] voiceFileList, int voiceLib)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsPlayListRequest.Builder builder = AtsPlayListRequest.newBuilder();
		builder.setFileCount(fileCount);
		builder.setInterruptKeys(interruptKeys);
		builder.setRate(rate);
		builder.setVoiceLib(voiceLib);

		for (String file : voiceFileList)
		{
			builder.addVoiceFileList(file);
		}

		return sendRequest(UCCEService.playListRequest, builder.build(),
				MessageType.AtsPlayListRequestType, port, callId,
				PLAY_TIMEOUT_SECONDS);
	}

	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsPlayTTSRequest.Builder builder = AtsPlayTTSRequest.newBuilder();
		builder.setInterruptKeys(interruptKeys);
		builder.setVoiceLib(voiceLib);
		builder.setText(text);
		builder.setVoiceLib(voiceLib);

		return sendRequest(UCCEService.playTTSRequest, builder.build(),
				MessageType.AtsPlayTTSRequestType, port, callId,
				PLAY_TIMEOUT_SECONDS);
	}

	public int atsPlayVoice(int port, int callId, String fileName,
			String interruptKeys, int rate, IntWrapper secondsPlayed,
			Calendar lastInteractionTime)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsPlayVoiceRequest.Builder builder = AtsPlayVoiceRequest.newBuilder();
		builder.setInterruptKeys(interruptKeys);
		builder.setFileName(fileName);
		builder.setRate(rate);
		builder.setSecondsPlayed(secondsPlayed.value);
		builder.setLastInteractionTime(lastInteractionTime.getTimeInMillis());

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.playVoiceRequest,
					builder.build(), MessageType.AtsPlayVoiceRequestType, port,
					callId, PLAY_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsPlayVoiceResponse response = msg
						.getExtension(UCCEService.playVoiceResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					secondsPlayed.value = response.getSecondsPlayed();
					lastInteractionTime.setTimeInMillis(response
							.getLastInteractionTime());
				}

				return response.getReturnValue();
			}
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsPlayVoiceAsync(int port, int callId, String fileName,
			String interruptKeys, int rate)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsPlayVoiceAsyncRequest.Builder builder = AtsPlayVoiceAsyncRequest
				.newBuilder();
		builder.setInterruptKeys(interruptKeys);
		builder.setFileName(fileName);
		builder.setRate(rate);

		return sendRequest(UCCEService.playVoiceAsyncRequest, builder.build(),
				MessageType.AtsPlayVoiceAsyncRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);

	}

	public int atsQueryCallState(int port, int callId, IntWrapper callStatus)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}
		else if ((port < 0) || (callId < 0))
		{
			callStatus.value = Constant.StatusIdle;

			return Constant.ERR_Success;
		}

		AtsQueryCallStateRequest.Builder builder = AtsQueryCallStateRequest
				.newBuilder();

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.queryCallStateRequest,
					builder.build(), MessageType.AtsQueryCallStateRequestType,
					port, callId, OPERATE_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsQueryCallStateResponse response = msg
						.getExtension(UCCEService.queryCallStateResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					callStatus.value = response.getCallStatus();
				}

				return response.getReturnValue();
			}
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsReceiveDTMF(int port, int callId, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsReceiveDTMFRequest.Builder builder = AtsReceiveDTMFRequest
				.newBuilder();
		builder.setInterruptKeys(interruptKeys);
		builder.setKeyCount(keyCount);
		builder.setMaxTime(maxTime);
		builder.setBetweenTime(betweenTime);

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.receiveDTMFRequest,
					builder.build(), MessageType.AtsReceiveDTMFRequestType,
					port, callId, RECEIVE_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsReceiveDTMFResponse response = msg
						.getExtension(UCCEService.receiveDTMFResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					buffer.value = response.getBuffer();
				}

				return response.getReturnValue();
			}
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsReceiveFax(int port, int callId, String fileName)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsReceiveFaxRequest.Builder builder = AtsReceiveFaxRequest
				.newBuilder();
		builder.setFileName(fileName);

		return sendRequest(UCCEService.receiveFaxRequest, builder.build(),
				MessageType.AtsReceiveFaxRequestType, port, callId,
				PLAY_TIMEOUT_SECONDS);
	}

	public int atsRecordVoice(int port, int callId, String fileName,
			char endFlag, int maxTime, int rate)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsRecordVoiceRequest.Builder builder = AtsRecordVoiceRequest
				.newBuilder();
		builder.setFileName(fileName);
		builder.setEndFlag(endFlag);
		builder.setMaxTime(maxTime);
		builder.setRate(rate);

		return sendRequest(UCCEService.recordVoiceRequest, builder.build(),
				MessageType.AtsRecordVoiceRequestType, port, callId,
				PLAY_TIMEOUT_SECONDS);
	}

	public int atsReleaseCall(int port, int callId, int reserved, boolean wait)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsReleaseCallRequest.Builder builder = AtsReleaseCallRequest
				.newBuilder();

		builder.setWait(wait);

		return sendRequest(UCCEService.releaseCallRequest, builder.build(),
				MessageType.AtsReleaseCallRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsRetrieveCall(int port, int callId, int reserved)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsRetrieveCallRequest.Builder builder = AtsRetrieveCallRequest
				.newBuilder();

		return sendRequest(UCCEService.retrieveCallRequest, builder.build(),
				MessageType.AtsRetrieveCallRequestType, port, callId,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsSendFax(int port, int callId, String fileName)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsSendFaxRequest.Builder builder = AtsSendFaxRequest.newBuilder();
		builder.setFileName(fileName);

		return sendRequest(UCCEService.sendFaxRequest, builder.build(),
				MessageType.AtsSendFaxRequestType, port, callId,
				PLAY_TIMEOUT_SECONDS);
	}

	public int atsSendSignals(int port, int callId, String signals)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsSendSignalsRequest.Builder builder = AtsSendSignalsRequest
				.newBuilder();
		builder.setSignals(signals);

		return sendRequest(UCCEService.sendSignalsRequest, builder.build(),
				MessageType.AtsSendSignalsRequestType, port, callId,
				RECEIVE_TIMEOUT_SECONDS);
	}

	public int atsSingleStepConference(int port, int callId,
			String callingNumber, String destNumber, int timeout,
			boolean routeMediaFirst, int reserved, int transactionId,
			boolean isVideoCall)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsSingleStepConferenceRequest.Builder builder = AtsSingleStepConferenceRequest
				.newBuilder();
		builder.setCallingNumber(callingNumber);
		builder.setDestNumber(destNumber);
		builder.setIsVideoCall(isVideoCall);
		builder.setRouteMediaFirst(routeMediaFirst);
		builder.setTimeout(timeout);

		return sendRequest(UCCEService.singleStepConferenceRequest, builder
				.build(), MessageType.AtsSingleStepConferenceRequestType, port,
				callId, RECEIVE_TIMEOUT_SECONDS);
	}

	public int atsSingleStepTransfer(int port, int callId,
			String callingNumber, String destNumber, int timeout,
			boolean routeMediaFirst, int reserved, int transactionId,
			boolean createConf, int mode, boolean reverseRoute,
			boolean isVideoCall)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsSingleStepTransferRequest.Builder builder = AtsSingleStepTransferRequest
				.newBuilder();
		builder.setCallingNumber(callingNumber);
		builder.setDestNumber(destNumber);
		builder.setIsVideoCall(isVideoCall);
		builder.setRouteMediaFirst(routeMediaFirst);
		builder.setTimeout(timeout);
		builder.setCreateConf(createConf);
		builder.setMode(mode);
		builder.setReverseRoute(reverseRoute);

		return sendRequest(UCCEService.singleStepTransferRequest, builder
				.build(), MessageType.AtsSingleStepTransferRequestType, port,
				callId, RECEIVE_TIMEOUT_SECONDS);
	}

	public int atsStartConferencRecording(int port, int callId,
			String recordFileName, int reserved)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsStartConferencRecordingRequest.Builder builder = AtsStartConferencRecordingRequest
				.newBuilder();
		builder.setRecordFileName(recordFileName);

		return sendRequest(UCCEService.startConferencRecordingRequest, builder
				.build(), MessageType.AtsStartConferencRecordingRequestType,
				port, callId, OPERATE_TIMEOUT_SECONDS);
	}

	public int atsSwitchTwoPort(int port1, int callId1, int port2, int reserved)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsSwitchTwoPortRequest.Builder builder = AtsSwitchTwoPortRequest
				.newBuilder();
		builder.setPort2(port2);

		return sendRequest(UCCEService.switchTwoPortRequest, builder.build(),
				MessageType.AtsSwitchTwoPortRequestType, port1, callId1,
				OPERATE_TIMEOUT_SECONDS);
	}

	public int atsVoiceEdit(int port, int callId, String fileName, int rate,
			IntWrapper secondsPlayed, Calendar lastInteractionTime,
			StringWrapper buffer, int keyCount, String interruptKeys,
			int maxTime, int betweenTime)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsVoiceEditRequest.Builder builder = AtsVoiceEditRequest.newBuilder();
		builder.setInterruptKeys(interruptKeys);
		builder.setFileName(fileName);
		builder.setRate(rate);
		builder.setSecondsPlayed(secondsPlayed.value);
		builder.setLastInteractionTime(lastInteractionTime.getTimeInMillis());
		builder.setKeyCount(keyCount);
		builder.setMaxTime(maxTime);
		builder.setBetweenTime(betweenTime);

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.voiceEditRequest,
					builder.build(), MessageType.AtsVoiceEditRequestType, port,
					callId, PLAY_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsVoiceEditResponse response = msg
						.getExtension(UCCEService.voiceEditResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					secondsPlayed.value = response.getSecondsPlayed();
					lastInteractionTime.setTimeInMillis(response
							.getLastInteractionTime());
					buffer.value = response.getBuffer();
				}

				return response.getReturnValue();
			}
		}
		catch (Throwable e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	public int atsVoiceListEdit(int port, int callId, int rate, int fileCount,
			String[] voiceFileList, int voiceLib, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		if (!isConnected())
		{
			return Constant.ERR_RPC;
		}

		AtsVoiceListEditRequest.Builder builder = AtsVoiceListEditRequest
				.newBuilder();
		builder.setInterruptKeys(interruptKeys);
		builder.setFileCount(fileCount);
		builder.setVoiceLib(voiceLib);
		builder.setRate(rate);
		builder.setKeyCount(keyCount);
		builder.setMaxTime(maxTime);
		builder.setBetweenTime(betweenTime);

		for (String file : voiceFileList)
		{
			builder.addVoiceFileList(file);
		}

		try
		{
			UCCEMessage msg = sendRequest2(UCCEService.voiceListEditRequest,
					builder.build(), MessageType.AtsVoiceListEditRequestType,
					port, callId, PLAY_TIMEOUT_SECONDS);

			if (msg != null)
			{
				AtsReceiveDTMFResponse response = msg
						.getExtension(UCCEService.receiveDTMFResponse);
				if (response.getReturnValue() == Constant.ERR_Success)
				{
					buffer.value = response.getBuffer();
				}

				return response.getReturnValue();
			}
		}
		catch (Throwable e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}

		return Constant.ERR_TimeOut;
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception
	{
		channel = null;
		super.channelDisconnected(ctx, e);

		((CallAgentImpl) UnifiedServiceManagement.callAgent)
				.onConnectionClosed();
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#channelDisconnected(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ChannelStateEvent)
	 */
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception
	{
		channel = e.getChannel();
		super.channelOpen(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
	{
		// Log.wException(e.getCause());
		e.getChannel().close();
	}

	public boolean isConnected()
	{
		return (channel != null) && channel.isConnected();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, final MessageEvent e)
	{
		UCCEMessage protocol = (UCCEMessage) e.getMessage();
		Log.wDebug(e.getMessage().toString());
		switch (protocol.getType())
		{
		case AtsSGEventRequestType:
		{
			AtsSGEvent event = new AtsSGEvent();
			AtsSGEventRequest msg = protocol
					.getExtension(UCCEService.atsSGEventRequest);
			event.callSN = protocol.getCallId();
			event.eventID = msg.getEventId();
			event.port = protocol.getPort();
			sgEvents.add(event);
		}
			break;

		default:
			onReply(protocol);
			break;
		}
	}

	boolean onReply(UCCEMessage msg)
	{
		CallerWaiter callerWaiter = null;
		synchronized (callerWaiters)
		{
			callerWaiter = callerWaiters.remove(msg.getSequenceId());
		}

		if (callerWaiter != null)
		{
			callerWaiter.msg = msg;
			try
			{
				callerWaiter.waiter.await(100, TimeUnit.MILLISECONDS);
			}
			catch (Exception e)
			{
			}
		}

		return callerWaiter != null;
	}

	final <Type> int sendRequest(
			final GeneratedExtension<UCCEMessage, Type> extension,
			final Type value, MessageType type, int port, int callId,
			int timeout)
	{
		try
		{
			UCCEMessage msg = UCCEMessage.newBuilder().setExtension(extension,
					value).setType(type).setCallId(callId).setPort(port)
					.setSequenceId(sequenceIdSeed.getAndIncrement()).build();

			msg = waitForReply(msg, timeout);

			return msg == null ? Constant.ERR_TimeOut : msg.getExtension(
					UCCEService.response).getReturnValue();
		}
		catch (TimeoutException te)
		{
			return Constant.ERR_TimeOut;
		}
		catch (Exception e)
		{
			Log.wException(e);

			return Constant.ERR_GeneralError;
		}
	}

	final <Type> UCCEMessage sendRequest2(
			final GeneratedExtension<UCCEMessage, Type> extension,
			final Type value, MessageType type, int port, int callId,
			int timeout) throws Exception
	{
		try
		{
			UCCEMessage msg = UCCEMessage.newBuilder().setExtension(extension,
					value).setType(type).setCallId(callId).setPort(port)
					.setSequenceId(sequenceIdSeed.getAndIncrement()).build();

			msg = waitForReply(msg, timeout);

			return msg;
		}
		catch (TimeoutException te)
		{
			return null;
		}
	}

	UCCEMessage waitForReply(UCCEMessage msg, int operationTimeoutSeconds)
			throws Exception
	{
		CallerWaiter callWaiter = new CallerWaiter();

		try
		{
			synchronized (callerWaiters)
			{
				callerWaiters.put(msg.getSequenceId(), callWaiter);
			}

			channel.write(msg);

			callWaiter.waiter.await(operationTimeoutSeconds, TimeUnit.SECONDS);
			return callWaiter.msg;
		}
		finally
		{
			callWaiter.waiter.reset();
			synchronized (callerWaiters)
			{
				callerWaiters.remove(msg.getSequenceId());
			}
		}
	}
}
