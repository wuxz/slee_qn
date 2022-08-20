package com.channelsoft.slee.capability;

import java.util.Calendar;
import java.util.Vector;
import com.channelsoft.reusable.util.*;

public interface UICapability
{
	int playTTSFile(int pronLanguage, Vector<TTSSegment> ttsSeg,
			boolean canBreak, String breakList, int rate, boolean thirdParty);

	int playVoice(String fileName, boolean canBreak, String breakList,
			int rate, int playCount, int waitTimeOnce,IntWrapper secondsPlayed,
			Calendar lastInteractionTime);

	int playVoiceAsync(String fileName, boolean canBreak, String breakList,
			int rate, int playCount, int waitTimeOnce);

	int recodeVoice(String fileName, char endFlag, int timeDuration, int rate);

	int sendFax(String fileName);

	int receiveFax(String fileName, int timeDuration);

	int getDtmf(int count, String endFlag, boolean shouldClearBuffer,
			int timeout, int betweenTimeout, StringWrapper dtmf);

	int sendDtmf(String dtmf);

	int clearDtmfBuffer();

	int playVoiceByTTS(String text, boolean isFile, boolean shouldConvert,
			int voiceLib, boolean canBreak, String breakList, int playCount,
			int waitTimeOnce);

	int voiceEdit(String fileName, int rate, int playCount, int waitTimeOnce,
			IntWrapper secondsPlayed, Calendar lastInteractionTime, int count,
			String endFlag, boolean shouldClearBuffer, int timeout,
			int betweenTimeout, StringWrapper dtmf);

	int voiceListEdit(int pronLanguage, Vector<TTSSegment> ttsSeg, int rate,
			boolean thirdParty, int count, String endFlag,
			boolean shouldClearBuffer, int timeout, int betweenTimeout,
			StringWrapper dtmf);
	
	int asr(int pronLanguage, Vector<TTSSegment> ttsSeg, String grammarFile,
			int noSpeechTimeout, int timeoutBetweenWord, int acceptScore,
			int resultNum, StringWrapper result);

	int asr2(int pronLanguage, Vector<TTSSegment> ttsSeg, String grammarFile,
			int noSpeechTimeout, int timeoutBetweenWord, int acceptScore,
			int resultNum, StringWrapper result, int dtmfCount, String endFlag,
			int timeoutSecond, int betweenTimeout);

	int startConferencRecording(String recordFileName);
}
