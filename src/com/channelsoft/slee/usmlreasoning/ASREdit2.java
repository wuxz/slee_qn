package com.channelsoft.slee.usmlreasoning;

import java.util.*;
import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;
import com.channelsoft.slee.capability.*;

public class ASREdit2 extends IOProcess
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7261567737687827337L;

	public VoiceListEdit voiceListEdit;

	public GetASR getASR;

	public boolean isPreGetCash;

	public int doCount;

	public ASREdit2(Service service)
	{
		super(service);
		voiceListEdit = new VoiceListEdit(service);
		getASR = new GetASR(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		// ////tts
		Vector<TTSSegment> ttsSegs = new Vector<TTSSegment>();
		for (int i = 0; i < voiceListEdit.playTTS.ttsSegmentGroup.size(); i++)
		{
			ttsSegs.add(voiceListEdit.playTTS.ttsSegmentGroup.elementAt(i));
		}
		// *****************************************
		int error = Constant.EVENT_No_Error;
		int playCount = voiceListEdit.playTTS.playCount;
		// //////asr
		StringWrapper grammarFile = new StringWrapper();
		if (null != getASR.grammarStringVar)
			getASR.grammarStringVar.getValue(grammarFile);
		if (null != getASR.grammarFileVar)
			getASR.grammarFileVar.getValue(grammarFile);

		int acceptScore = getASR.acceptScore;
		if (null != getASR.acceptScoreVar)
			acceptScore = getASR.acceptScoreVar.getValueInt();
		int resultMaxNum = getASR.resultMaxNum;
		if (null != getASR.resultMaxNumVar)
			resultMaxNum = getASR.resultMaxNumVar.getValueInt();
		int noSpeechTimeout = getASR.noSpeechTimeout;
		if (null != getASR.noSpeechTimeoutVar)
			noSpeechTimeout = getASR.noSpeechTimeoutVar.getValueInt();
		int betweenWordTimeout = getASR.betweenWordTimeout;
		if (null != getASR.betweenWordTimeoutVar)
			betweenWordTimeout = getASR.betweenWordTimeoutVar.getValueInt();
		int pronLang;
		if (null != voiceListEdit.playTTS.languageTypeVar)
		{
			StringWrapper languageType = new StringWrapper();
			voiceListEdit.playTTS.languageTypeVar.getValue(languageType);
			pronLang = USMLFunction.transPronLangToInt(languageType.value);
		}
		else
		{
			pronLang = service.getPronLanguageTypeVar();
		}

		for (int i = 0; i < playCount; i++)
		{
			StringWrapper result = new StringWrapper();
			// String bstrResult ;
			error = browser.getUICapability().asr2(pronLang, ttsSegs,
					grammarFile.value, noSpeechTimeout, betweenWordTimeout,
					acceptScore, resultMaxNum, result,
					voiceListEdit.getDTMF.dtmfCount,
					voiceListEdit.getDTMF.endFlag,
					voiceListEdit.getDTMF.timeoutSecond,
					voiceListEdit.getDTMF.betweenTimeout);

			if (error == Constant.EVENT_No_Error)
			{
				if (null != getASR.resultVar)
					getASR.resultVar.setValue(result.value);
				break;
			}
			if (error != Constant.EVENT_TimeOut)
				break;

			if (playCount != 1)
			{
				try
				{
					Thread.sleep(voiceListEdit.playTTS.waitTimeOnce * 1000);
				}
				catch (Exception e)
				{
				}
			}
		}

		return error;
	}

	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("ASREdit2 ");
		buff.append(getVarName());
		buff.append(" = new ASREdit2(this);\n");

		buff.append(getVarName());
		buff.append(".doCount = ");
		buff.append(doCount);
		buff.append(";\n");

		getASR.translate2SourceCode(buff);
		buff.append(getVarName());
		buff.append(".getASR = ");
		buff.append(getASR.getVarName());
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".isPreGetCash = ");
		buff.append(isPreGetCash);
		buff.append(";\n");

		voiceListEdit.translate2SourceCode(buff);
		buff.append(getVarName());
		buff.append(".voiceListEdit = ");
		buff.append(voiceListEdit.getVarName());
		buff.append(";\n");
	}
}
