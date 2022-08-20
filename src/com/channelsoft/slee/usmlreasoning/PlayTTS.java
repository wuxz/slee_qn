package com.channelsoft.slee.usmlreasoning;

import java.util.*;
import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;
import com.channelsoft.slee.capability.TTSSegment;

public class PlayTTS extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1707344404452971697L;

	public Vector<TTSSegmentImpl> ttsSegmentGroup = new Vector<TTSSegmentImpl>();

	public boolean canBreak;

	public String breakList;
	
	public int rate;

	public boolean thirdParty;

	public int playCount;

	public int waitTimeOnce;

	public KnowledgeVariable languageTypeVar;

	public PlayTTS(Service service)
	{
		super(service);

	}

	int doIOProcess(BaseBrowser browser)
	{
		Vector<TTSSegment> ttsSegs = new Vector<TTSSegment>();
		for (int i = 0; i < ttsSegmentGroup.size(); i++)
		{
			ttsSegs.add(ttsSegmentGroup.elementAt(i));
		}
		// *****************************************
		int error = Constant.EVENT_No_Error;
		for (int i = 0; i < playCount; i++)
		{
			int pronLang;
			if (null != languageTypeVar)
			{
				StringWrapper languageType = new StringWrapper();
				languageTypeVar.getValue(languageType);
				pronLang = USMLFunction.transPronLangToInt(languageType.value);
			}
			else
			{
				pronLang = service.getPronLanguageTypeVar();
			}

			error = (browser.getUICapability()).playTTSFile(pronLang, ttsSegs,
					canBreak, breakList, rate, thirdParty);
			if (error != Constant.EVENT_No_Error)
				break;
			if (playCount != 1)
			{
				try
				{
					Thread.sleep(waitTimeOnce * 1000);
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
		buff.append("PlayTTS ");
		buff.append(getVarName());
		buff.append(" = new PlayTTS(this);\n");

		buff.append(getVarName());
		buff.append(".breakList = ");
		ServiceNode.translateString2SourceCode(buff, breakList);

		buff.append(getVarName());
		buff.append(".canBreak = ");
		buff.append(canBreak);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".rate = ");
		buff.append(rate);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".languageTypeVar = ");
		ServiceNode.translateVar2SourceCode(buff, languageTypeVar);

		buff.append(getVarName());
		buff.append(".playCount = ");
		buff.append(playCount);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".thirdParty = ");
		buff.append(thirdParty);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".ttsSegmentGroup");
		buff.append(" = new Vector<TTSSegmentImpl>()");
		buff.append(";\n");
		for (int i = 0; i < ttsSegmentGroup.size(); i++)
		{
			String ttsVarName=getVarName()+"_ttsSegment_"+i;
			buff.append("TTSSegmentImpl ");
			buff.append(ttsVarName);
			buff.append("=new TTSSegmentImpl();\n");
			buff.append(ttsVarName);
			buff.append(".fileName=");
			ServiceNode.translateString2SourceCode(buff, ttsSegmentGroup
					.elementAt(i).fileName);
			buff.append(ttsVarName);
			buff.append(".fileNameVar=");
			ServiceNode.translateVar2SourceCode(buff, ttsSegmentGroup
					.elementAt(i).fileNameVar);
			buff.append(ttsVarName);
			buff.append(".isVar=");
			buff.append(ttsSegmentGroup
					.elementAt(i).isVar);
			buff.append(";\n");
			buff.append(ttsVarName);
			buff.append(".ttsOutVar=");
			ServiceNode.translateVar2SourceCode(buff, ttsSegmentGroup
					.elementAt(i).ttsOutVar);
			buff.append(getVarName());
			buff.append(".ttsSegmentGroup.add(");
			buff.append(ttsVarName);
			buff.append(");\n");
		}

		buff.append(getVarName());
		buff.append(".waitTimeOnce = ");
		buff.append(waitTimeOnce);
		buff.append(";\n");
	}
}
