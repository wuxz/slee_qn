package com.channelsoft.slee.callagent;

import java.util.Calendar;

import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.channelmanager.AtsSGEvent;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;

/**
 * 呼叫控制接口，所有的呼叫控制的实现都必须实现此接口。
 * 
 * @author wuxz
 */
public interface CallAgent
{
	public int atsAdjustSpeed(int port, int callId, int adjment);

	public int atsAdjustVolume(int port, int callId, int adjment);

	public int atsAnswerCall(int port, int callId, int isBilling);

	public int atsAppendVideo(int port, int callId, String[][] infoOnVideo);
	
	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord);

	public int atsASR2(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String voiceFileList[],
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord, int DTMF_dtmfCount,
			String DTMF_endFlag, int DTMF_timeoutSecond, int DTMF_betweenTimeout);

	public int atsClearDTMFBuffer(int port, int callId);

	public int atsCloseConf(int port, int callId);

	public int atsDisSwitchTwoPort(int port1, int callId1, int port2,
			int reserved);

	public int atsGetCallInfo(int port, int callId, StringWrapper ani,
			StringWrapper dnis, StringWrapper oriAni, StringWrapper oriDnis,
			StringWrapper reserved, BooleanWrapper isVideoCall);

	public int atsGetOtherPartyCallSn(int port, int callId);

	public int atsGetOtherPartyTrunkID(int port, int callId);

	public AtsSGEvent atsGetSGEvent();

	public int atsInitialize(ISysCfgData sysCfgData);

	public int atsMakeCall(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall);

	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String voiceFileList[], int voiceLib);

	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib);

	public int atsPlayVoice(int port, int callId, String fileName,
			String interruptKeys, int rate, IntWrapper secondsPlayed,
			Calendar lastInteractionTime);

	public int atsPlayVoiceAsync(int port, int callId, String fileName,
			String interruptKeys, int rate);

	public int atsQueryCallState(int port, int callId, IntWrapper callStatus);

	public int atsReceiveDTMF(int port, int callId, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime);

	public int atsReceiveFax(int port, int callId, String fileName);

	public int atsRecordVoice(int port, int callId, String fileName,
			char endFlag, int maxTime, int rate);

	public int atsReleaseCall(int port, int callId, int reserved);

	public int atsReleaseCall(int port, int callId, int reserved, boolean wait);

	public int atsRetrieveCall(int port, int callId, int reserved);

	public int atsSendFax(int port, int callId, String fileName);

	public int atsSendSignals(int port, int callId, String signals);

	public int atsSingleStepConference(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean isVideoCall);

	public int atsSingleStepTransfer(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean createConf, int mode,
			boolean reverseRoute, boolean isVideoCall, String videoUrl1,
			String videoUrl2);
	
	public int atsStartConferencRecording(int port, int callId,
			String recordFileName, int reserved);

	public int atsSwitchTwoPort(int port1, int callId1, int port2, int reserved);

	public int atsSwitchTwoPort(int port1, int callId1, int port2,
			int reserved, String videoUrl1, String videoUrl2);

	public int atsVoiceEdit(int port, int callId, String fileName, int rate,
			IntWrapper secondsPlayed, Calendar lastInteractionTime,
			StringWrapper buffer, int keyCount, String interruptKeys,
			int maxTime, int betweenTime);

	public int atsVoiceListEdit(int port, int callId, int rate, int fileCount,
			String voiceFileList[], int voiceLib, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime);

	public void attachCall(int port, int callID);

	public void attachOtherParty(int port, int callID, int otherPort,
			int otherCallId);

	public int blindCreateConference(int port1, int callId, int port2,
			boolean isVideoCall);
	
	public int atsCallRecordRing(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved,
			int transactionId, boolean isVideoCall, /*int port, int callId, */
			String fileName, char endFlag, int maxTime, int rate);
}
