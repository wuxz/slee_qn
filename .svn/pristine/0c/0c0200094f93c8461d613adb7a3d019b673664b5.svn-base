package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.capability.TTSSegment;

public class TTSSegmentImpl implements TTSSegment, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5456953882752074435L;

	public String fileName;

	public KnowledgeVariable fileNameVar;

	public boolean isVar;

	public KnowledgeVariable ttsOutVar;

	public TTSSegmentImpl()
	{
		fileNameVar = null;
		ttsOutVar = null;
		fileName = "";
		isVar = false;
	}

	public String getTTSVoiceFileName()
	{
		if (isVar == false)
		{
			String tempFile;
			if (fileNameVar == null)
			{
				tempFile = fileName;
			}
			else
			{
				StringWrapper tem = new StringWrapper();
				fileNameVar.getValue(tem);
				tempFile = tem.value;

			}
			return tempFile;
		}
		return "";
	}

	public String getVarType()
	{
		return ttsOutVar.varType.toString();
	}

	public KnowledgeVariable getVarValue()
	{
		if (ttsOutVar != null)
		{
			return ttsOutVar;
		}
		else
		{
			return null;
		}
	}

	public boolean isVariable()
	{
		return isVar;
	}
}
