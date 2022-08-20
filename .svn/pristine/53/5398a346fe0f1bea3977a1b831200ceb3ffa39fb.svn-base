package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;

public class ReceiveFax extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7673322494457566911L;

	public KnowledgeVariable fileNameVar;
	
	public int timeDuration;
	
	public ReceiveFax(Service service)
	{
		super(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper fileName = new StringWrapper();
		fileNameVar.getValue(fileName);

		return browser.getUICapability().receiveFax(fileName.value, timeDuration);
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("ReceiveFax ");
		buff.append(getVarName());
		buff.append(" = new ReceiveFax(this);\n");

		buff.append(getVarName());
		buff.append(".fileNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, fileNameVar);
		
		buff.append(getVarName());
		buff.append(".timeDuration = ");
		buff.append(timeDuration);
		buff.append(";\n");
	}
}
