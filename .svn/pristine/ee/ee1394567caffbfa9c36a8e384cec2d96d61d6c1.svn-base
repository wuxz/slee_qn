package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;

public class SendFax extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5024073912047598790L;
	public KnowledgeVariable fileNameVar;

	public SendFax(Service service)
	{
		super(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper fileName = new StringWrapper();
		fileNameVar.getValue(fileName);

		return browser.getUICapability().sendFax(fileName.value);
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SendFax ");
		buff.append(getVarName());
		buff.append(" = new SendFax(this);\n");

		buff.append(getVarName());
		buff.append(".fileNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, fileNameVar);
	}
}
