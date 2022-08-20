package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;

public class TransferCall extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8820945565040593882L;
	public KnowledgeVariable destNumberVar;

	public TransferCall(Service service)
	{
		super(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper destDN = new StringWrapper();
		destNumberVar.getValue(destDN);

		return browser.getCallControlCapability().transferCall();
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("TransferCall ");
		buff.append(getVarName());
		buff.append(" = new TransferCall(this);\n");

		buff.append(getVarName());
		buff.append(".destNumberVar = ");
		ServiceNode.translateVar2SourceCode(buff, destNumberVar);
	}
}
