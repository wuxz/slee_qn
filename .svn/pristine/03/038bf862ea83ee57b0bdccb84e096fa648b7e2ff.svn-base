package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.*;
import com.channelsoft.slee.util.*;

public class SendDTMF extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4830866524600570984L;
	public KnowledgeVariable dtmfVar;

	public SendDTMF(Service service)
	{
		super(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper signal = new StringWrapper();
		dtmfVar.getValue(signal);

		return browser.getUICapability().sendDtmf(signal.value);
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SendDTMF ");
		buff.append(getVarName());
		buff.append(" = new SendDTMF(this);\n");

		buff.append(getVarName());
		buff.append(".dtmfVar = ");
		ServiceNode.translateVar2SourceCode(buff, dtmfVar);
	}
}
