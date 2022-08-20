package com.channelsoft.slee.usmlreasoning;

import java.util.*;
import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;
import com.channelsoft.slee.capability.*;

public class ASREdit extends IOProcess
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5979132822746391719L;

	public PlayTTS playTTS;

	public GetASR getASR;

	public boolean isPreGetCash;

	public int doCount;

	public ASREdit(Service service)
	{
		super(service);
		playTTS = new PlayTTS(service);
		getASR = new GetASR(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		//browser.onEnterIOProcess(ioProcessName, null);

		// ////tts
		Vector<TTSSegment> ttsSegs = new Vector<TTSSegment>();
		for (int i = 0; i < playTTS.ttsSegmentGroup.size(); i++)
		{
			ttsSegs.add(playTTS.ttsSegmentGroup.elementAt(i));
		}
		// *****************************************
		int error = Constant.EVENT_No_Error;
		int playCount = playTTS.playCount;
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
		if (null != playTTS.languageTypeVar)
		{
			StringWrapper languageType = new StringWrapper();
			playTTS.languageTypeVar.getValue(languageType);
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
			error = browser.getUICapability().asr(pronLang, ttsSegs,
					grammarFile.value, noSpeechTimeout, betweenWordTimeout,
					acceptScore, resultMaxNum, result);

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
					Thread.sleep(playTTS.waitTimeOnce * 1000);
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
		buff.append("ASREdit ");
		buff.append(getVarName());
		buff.append(" = new ASREdit(this);\n");
		
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
		
		playTTS.translate2SourceCode(buff);
		buff.append(getVarName());
		buff.append(".playTTS = ");
		buff.append(playTTS.getVarName());
		buff.append(";\n");
	}
}
