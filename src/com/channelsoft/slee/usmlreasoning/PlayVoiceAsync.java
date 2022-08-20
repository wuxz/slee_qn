package com.channelsoft.slee.usmlreasoning;


import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;


public class PlayVoiceAsync extends PlayVoice 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8818270561717118006L;

	public PlayVoiceAsync(Service service)
	{
		super(service);
	}
	
	
	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper tempFileName = new StringWrapper();
		if (null != fileNameVar)
		{
			fileNameVar.getValue(tempFileName);
			fileName = tempFileName.value;
		}

		int error = browser.getUICapability().playVoiceAsync(fileName, canBreak,
				breakList, rate, playCount, waitTimeOnce);

		return error;
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("PlayVoiceAsync ");
		buff.append(getVarName());
		buff.append(" = new PlayVoiceAsync(this);\n");

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
		buff.append(".fileName = ");
		ServiceNode.translateString2SourceCode(buff, fileName);

		buff.append(getVarName());
		buff.append(".fileNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, fileNameVar);

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
