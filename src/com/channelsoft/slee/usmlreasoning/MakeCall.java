package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.util.Constant;

public class MakeCall extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2385096807033553064L;

	public KnowledgeVariable destNumberVar;

	public boolean isForFax;

	public boolean isVideoCall;

	public int timeOutSecond;

	public KnowledgeVariable timeOutSecondVar;

	public MakeCall(Service service)
	{
		super(service);
		timeOutSecondVar = null;
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper destDN = new StringWrapper();
		destNumberVar.getValue(destDN);

		int timeOutSecond = this.timeOutSecond;
		if (null != timeOutSecondVar)
		{
			timeOutSecond = timeOutSecondVar.getValueInt();
		}
		// 发起呼叫//在MakeCall中摘机
		int error = browser.getCallControlCapability().makeCall(
				browser.getCallControlCapability().getCallingNumber(),
				browser.getCallControlCapability().getOriAni(), destDN.value,
				browser.getCallControlCapability().getOriDnis(),
				timeOutSecond * 1000, isVideoCall);
		if (error == Constant.EVENT_NoFaxDevice)
		{
			if (isForFax == false)
			{
				return Constant.EVENT_No_Error;
			}
			else
			{
				return Constant.EVENT_NoFaxDevice;
			}
		}
		else
		{
			return error;
		}
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("MakeCall ");
		buff.append(getVarName());
		buff.append(" = new MakeCall(this);\n");

		buff.append(getVarName());
		buff.append(".destNumberVar = ");
		ServiceNode.translateVar2SourceCode(buff, destNumberVar);

		buff.append(getVarName());
		buff.append(".isForFax = ");
		buff.append(isForFax);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".isVideoCall = ");
		buff.append(isVideoCall);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".timeOutSecond = ");
		buff.append(timeOutSecond);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".timeOutSecondVar = ");
		ServiceNode.translateVar2SourceCode(buff, timeOutSecondVar);
	}

}
