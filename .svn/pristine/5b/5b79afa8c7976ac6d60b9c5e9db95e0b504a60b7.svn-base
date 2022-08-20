package com.channelsoft.slee.usmlreasoning;

import java.util.Calendar;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class GetDTMF extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6582808457272048161L;

	public int betweenTimeout;

	public KnowledgeVariable betweenTimeoutVar;

	StringWrapper dtmf = new StringWrapper("");

	public int dtmfCount;

	public KnowledgeVariable dtmfVar;

	public KnowledgeVariable endCharVar;

	public String endFlag;

	public boolean isClearBuffer;

	public boolean returnEndChar;

	public int timeoutSecond;

	public KnowledgeVariable timeoutSecondVar;

	public GetDTMF(Service service)
	{
		super(service);
		timeoutSecondVar = null;
		betweenTimeoutVar = null;
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		dtmf.value = "";

		int timeoutSecond = this.timeoutSecond;
		if (null != timeoutSecondVar)
		{
			timeoutSecond = timeoutSecondVar.getValueInt();
		}

		int betweenTimeout = this.betweenTimeout;
		if (null != betweenTimeoutVar)
		{
			betweenTimeout = betweenTimeoutVar.getValueInt();
		}

		int error = browser.getUICapability().getDtmf(dtmfCount, endFlag,
				isClearBuffer, timeoutSecond, betweenTimeout, dtmf);
		if ((error == Constant.EVENT_No_Error)
				|| (error == Constant.EVENT_TimeOut))
		{// m_cEndCharVar
			if (error == Constant.EVENT_No_Error)
			{
				service.usmlReasoningEnv.lastInteractionTime = Calendar
						.getInstance();
			}
			String endChar = "";
			if ((dtmf.value != null) && !"".equals(dtmf.value))
			{
				endChar = String.valueOf(dtmf.value
						.charAt(dtmf.value.length() - 1));
			}
			if (!"".equals(endChar) && (-1 != endFlag.indexOf(endChar)))
			{
				dtmf.value = dtmf.value.substring(0, dtmf.value.length() - 1);
				if ((returnEndChar) && (null != endCharVar))
				{
					endCharVar.setValue(endChar);
				}
			}
			if (null != dtmfVar)
			{
				dtmfVar.setValue(dtmf.value);
			}

		}

		return error;
	}

	@Override
	void onLeaveIOProcess(BaseBrowser browser)
	{
		String processInfo = "";

		if (UnifiedServiceManagement.configData.getIsWriteDTMFLog())
		{
			processInfo = String.format("DTMF£½%s", dtmf.value);
		}
		else
		{
			char[] dtmfBuffer = new char[dtmf.value.length()];
			for (int i = 0; i < dtmfBuffer.length; i++)
			{
				dtmfBuffer[i] = '*';
			}

			processInfo = String.format("DTMF£½%s", new String(dtmfBuffer));
		}

		browser.onLeaveIOProcess(ioProcessName, processInfo);
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("GetDTMF ");
		buff.append(getVarName());
		buff.append(" = new GetDTMF(this);\n");

		buff.append(getVarName());
		buff.append(".betweenTimeout = ");
		buff.append(betweenTimeout);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".betweenTimeoutVar = ");
		ServiceNode.translateVar2SourceCode(buff, betweenTimeoutVar);

		buff.append(getVarName());
		buff.append(".dtmfCount = ");
		buff.append(dtmfCount);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".dtmfVar = ");
		ServiceNode.translateVar2SourceCode(buff, dtmfVar);

		buff.append(getVarName());
		buff.append(".endCharVar = ");
		ServiceNode.translateVar2SourceCode(buff, endCharVar);

		buff.append(getVarName());
		buff.append(".endFlag = ");
		ServiceNode.translateString2SourceCode(buff, endFlag);

		buff.append(getVarName());
		buff.append(".isClearBuffer = ");
		buff.append(isClearBuffer);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".returnEndChar = ");
		buff.append(returnEndChar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".timeoutSecond = ");
		buff.append(timeoutSecond);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".timeoutSecondVar = ");
		ServiceNode.translateVar2SourceCode(buff, timeoutSecondVar);
	}
}
