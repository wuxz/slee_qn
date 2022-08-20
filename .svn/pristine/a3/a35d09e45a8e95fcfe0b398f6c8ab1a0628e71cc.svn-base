package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;

public class PlayVoiceByTTS extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1787914858002997658L;

	public boolean canBreak;

	public String breakList;

	public int playCount;

	public int waitTimeOnce;

	public boolean useConvertAudioFile;

	public String fileName;

	public String txtString;

	public KnowledgeVariable fileNameVar;

	public KnowledgeVariable txtStringVar;
	
	public KnowledgeVariable languageTypeVar;

	public PlayVoiceByTTS(Service service)
	{
		super(service);

	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper tempFileName = new StringWrapper();
		StringWrapper tempTxtString = new StringWrapper();

		if (null != fileNameVar)
		{
			fileNameVar.getValue(tempFileName);
			fileName = tempFileName.value;
		}

		if (null != txtStringVar)
		{
			txtStringVar.getValue(tempTxtString);
			txtString = tempTxtString.value;
		}

		int pronLang;
		if(languageTypeVar != null)
		{
			StringWrapper languageType = new StringWrapper();
			languageTypeVar.getValue(languageType);
			pronLang = USMLFunction.transPronLangToInt(languageType.value);
		}else
		{
			pronLang = service.getPronLanguageTypeVar();
		}
		
		String text;
		boolean isFile = false;
		if ("".equals(txtString))
		{
			isFile = true;
			text = fileName;
		}
		else
		{
			isFile = false;
			text = txtString;
		}

		int error;
		for (int i = 0; i < playCount; i++)
		{
			error = browser.getUICapability().playVoiceByTTS(text, isFile,
					useConvertAudioFile, pronLang, canBreak, breakList, playCount,
					waitTimeOnce);
			if (error != Constant.EVENT_No_Error)
				return error;
			if (playCount != 1)
				try
				{
					Thread.sleep(waitTimeOnce * 1000);
				}
				catch (Exception e)
				{
				}
		}

		return Constant.EVENT_No_Error;
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("PlayVoiceByTTS ");
		buff.append(getVarName());
		buff.append(" = new PlayVoiceByTTS(this);\n");

		buff.append(getVarName());
		buff.append(".breakList = ");
		ServiceNode.translateString2SourceCode(buff, breakList);

		buff.append(getVarName());
		buff.append(".canBreak = ");
		buff.append(canBreak);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".useConvertAudioFile = ");
		buff.append(useConvertAudioFile);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".fileName = ");
		ServiceNode.translateString2SourceCode(buff, fileName);

		buff.append(getVarName());
		buff.append(".fileNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, fileNameVar);
		
		buff.append(getVarName());
		buff.append(".txtString = ");
		ServiceNode.translateString2SourceCode(buff, txtString);

		buff.append(getVarName());
		buff.append(".txtStringVar = ");
		ServiceNode.translateVar2SourceCode(buff, txtStringVar);
		
		buff.append(getVarName());
		buff.append(".languageTypeVar = ");
		ServiceNode.translateVar2SourceCode(buff, languageTypeVar);

		buff.append(getVarName());
		buff.append(".playCount = ");
		buff.append(playCount);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".waitTimeOnce = ");
		buff.append(waitTimeOnce);
		buff.append(";\n");
	}
}
