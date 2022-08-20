package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.util.Constant;

public class CallRecordRing extends IOProcess
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5131899859133769729L;

	// Makecall
	public MakeCall makeCall;

	public RecodeVoice recordVoice;

	public CallRecordRing(Service service)
	{
		super(service);
		makeCall = new MakeCall(service);
		recordVoice = new RecodeVoice(service);
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		//
		StringWrapper destDN = new StringWrapper();
		makeCall.destNumberVar.getValue(destDN);

		int timeOutSecond = makeCall.timeOutSecond;
		if (null != makeCall.timeOutSecondVar)
		{
			timeOutSecond = makeCall.timeOutSecondVar.getValueInt();
		}
		//
		StringWrapper fileName = new StringWrapper();
		if (null != recordVoice.fileNameVar)
			recordVoice.fileNameVar.getValue(fileName);

		int tempTimeDuration = recordVoice.timeDuration;
		if (null != recordVoice.timeDurationVar)
			tempTimeDuration = recordVoice.timeDurationVar.getValueInt();

		// 发起呼叫//在MakeCall中摘机
		int error = browser.getCallControlCapability().callRecordRing(
				browser.getCallControlCapability().getCallingNumber(),
				browser.getCallControlCapability().getOriAni(), destDN.value,
				browser.getCallControlCapability().getOriDnis(),
				timeOutSecond * 1000, makeCall.isVideoCall, fileName.value,
				recordVoice.endFlag, tempTimeDuration, recordVoice.rate);
		if (error == Constant.EVENT_NoFaxDevice)
		{
			if (makeCall.isForFax == false)
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
		buff.append("CallRecordRing ");
		buff.append(getVarName());
		buff.append(" = new CallRecordRing(this);\n");

		makeCall.translate2SourceCode(buff);
		
		buff.append(makeCall.getVarName());
		buff.append(".ioProcessName = ");
		ServiceNode.translateString2SourceCode(buff, makeCall.ioProcessName);

		buff.append(getVarName());
		buff.append(".makeCall = ");
		buff.append(makeCall.getVarName());
		buff.append(";\n");
		
		recordVoice.translate2SourceCode(buff);
		
		buff.append(recordVoice.getVarName());
		buff.append(".ioProcessName = ");
		ServiceNode.translateString2SourceCode(buff, recordVoice.ioProcessName);

		buff.append(getVarName());
		buff.append(".recordVoice = ");
		buff.append(recordVoice.getVarName());
		buff.append(";\n");
	}

}
