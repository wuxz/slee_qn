package com.channelsoft.slee.usmlreasoning;

public class DisconnetCall extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4202931683859344320L;

	public DisconnetCall(Service service)
	{
		super(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		return browser.getCallControlCapability().disconnetCall();
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("DisconnetCall ");
		buff.append(getVarName());
		buff.append(" = new DisconnetCall(this);\n");
	}
}
