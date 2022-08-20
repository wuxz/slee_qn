package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;

public class RecodeVoice extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1269281646005692474L;

	public KnowledgeVariable fileNameVar;

	public int timeDuration;

	public KnowledgeVariable timeDurationVar;

	public char endFlag;
	
	public int rate;

	public RecodeVoice(Service service)
	{
		super(service);
		timeDurationVar = null;
	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper fileName = new StringWrapper();
		if (null != fileNameVar)
			fileNameVar.getValue(fileName);

		int tempTimeDuration = timeDuration;
		if (null != timeDurationVar)
			tempTimeDuration = timeDurationVar.getValueInt();
		return browser.getUICapability().recodeVoice(fileName.value, endFlag,
				tempTimeDuration, rate);
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("RecodeVoice ");
		buff.append(getVarName());
		buff.append(" = new RecodeVoice(this);\n");

		buff.append(getVarName());
		buff.append(".fileNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, fileNameVar);
		
		buff.append(getVarName());
		buff.append(".timeDuration = ");
		buff.append(timeDuration);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".timeDurationVar = ");
		ServiceNode.translateVar2SourceCode(buff, timeDurationVar);
		
		buff.append(getVarName());
		buff.append(".endFlag = '");
		buff.append(endFlag);
		buff.append("';\n");
		
		buff.append(getVarName());
		buff.append(".rate = ");
		buff.append(rate);
		buff.append(";\n");
	}
}
