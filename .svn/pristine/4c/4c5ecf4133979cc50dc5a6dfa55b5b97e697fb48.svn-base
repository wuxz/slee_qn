package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.Constant;

public class RecodeVoiceAsync extends RecodeVoice
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7950514594911280327L;

	public RecodeVoiceAsync(Service service)
	{
		super(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper fileName = new StringWrapper();
		if (null != fileNameVar)
			fileNameVar.getValue(fileName);

		return browser.getUICapability()
				.startConferencRecording(fileName.value);
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("RecodeVoiceAsync ");
		buff.append(getVarName());
		buff.append(" = new RecodeVoiceAsync(this);\n");

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
	}
}
