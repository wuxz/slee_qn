package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.jni.SmsClient;
import com.channelsoft.slee.util.Constant;

public class GetSmsReport extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4345344470524637849L;

	public boolean m_bIsUssdMsg;

	public int m_nTimeout;

	public KnowledgeVariable m_pErrorCode;

	public KnowledgeVariable m_pMsgIdVar;

	public KnowledgeVariable m_pPhoneNumberVar;

	public KnowledgeVariable m_pState;

	public GetSmsReport(Service service)
	{
		super(service);
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper szMsgID = new StringWrapper();
		StringWrapper szPhoneNumber = new StringWrapper();
		IntWrapper state = new IntWrapper();
		IntWrapper errorCode = new IntWrapper();

		int nReturn = Constant.convertError2Event(SmsClient.getSmsReport(
				m_bIsUssdMsg, szMsgID, szPhoneNumber, state, errorCode,
				m_nTimeout));
		if (nReturn == Constant.EVENT_No_Error)
		{
			if (m_pPhoneNumberVar != null)
			{
				m_pPhoneNumberVar.setValue(szPhoneNumber.value);
			}
			if (m_pMsgIdVar != null)
			{
				m_pMsgIdVar.setValue(szMsgID.value);
			}
			if (m_pState != null)
			{
				m_pState.setValue(state.value);
			}
			if (m_pErrorCode != null)
			{
				m_pErrorCode.setValue(errorCode.value);
			}
		}
		return nReturn;
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("GetSmsReport ");
		buff.append(getVarName());
		buff.append(" = new GetSmsReport(this);\n");

		buff.append(getVarName());
		buff.append(".m_bIsUssdMsg = ");
		buff.append(m_bIsUssdMsg);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pPhoneNumberVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pPhoneNumberVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMsgIdVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMsgIdVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pState = ");
		ServiceNode.translateVar2SourceCode(buff, m_pState);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pErrorCode = ");
		ServiceNode.translateVar2SourceCode(buff, m_pErrorCode);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_nTimeout = ");
		buff.append(m_nTimeout);
		buff.append(";\n");
	}

}
