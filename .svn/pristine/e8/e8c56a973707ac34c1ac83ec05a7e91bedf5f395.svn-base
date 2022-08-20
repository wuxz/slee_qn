package com.channelsoft.slee.usmlreasoning;

import java.util.Date;
import java.util.Vector;

import com.channelsoft.reusable.util.MyDateFormat;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.jni.SmsClient;
import com.channelsoft.slee.util.Constant;

public class SendSmsMsg extends IOProcess
{
	static MyDateFormat sdf = new MyDateFormat("yyyymmddHHmmss");

	/**
	 * 
	 */
	private static final long serialVersionUID = -4108102538911762478L;

	public boolean m_bIsUssdMsg;

	public int m_nParamLevel;

	public KnowledgeVariable m_pAgentFlag;

	public KnowledgeVariable m_pChargeNumberVar;

	public KnowledgeVariable m_pCorpId;

	public KnowledgeVariable m_pExpireTimeVar;

	public KnowledgeVariable m_pFeeTypeVar;

	public KnowledgeVariable m_pFeeValueVar;

	public KnowledgeVariable m_pGivenValueVar;

	public KnowledgeVariable m_pLinkID;

	public KnowledgeVariable m_pMessageCoding;

	public KnowledgeVariable m_pMessageType;

	public KnowledgeVariable m_pMorelatetoMTFlag;

	public KnowledgeVariable m_pMsgFileVar;

	public KnowledgeVariable m_pMsgID;

	public KnowledgeVariable m_pMsgNumber;

	public KnowledgeVariable m_pMsgTotal;

	public KnowledgeVariable m_pMsgVar;

	public KnowledgeVariable m_pPhoneNumberVar;

	public KnowledgeVariable m_pPriorityVar;

	public KnowledgeVariable m_pReportFlag;

	public KnowledgeVariable m_pScheduleTimeVar;

	public KnowledgeVariable m_pServiceTypeVar;

	public KnowledgeVariable m_pSPVar;

	public KnowledgeVariable m_pTP_pid;

	public KnowledgeVariable m_pTP_udhi;

	public SendSmsMsg(Service service)
	{
		super(service);
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		Vector<String> cNumberArray = new Vector<String>();
		if (m_pPhoneNumberVar != null)
		{
			StringWrapper cszPhoneNumber = new StringWrapper();
			m_pPhoneNumberVar.getValue(cszPhoneNumber);
			if (cszPhoneNumber.value.length() > 0)
			{
				if (cszPhoneNumber.value
						.charAt(cszPhoneNumber.value.length() - 1) != ',')
				{
					cszPhoneNumber.value += ",";
				}
				int nStart = 0;
				int nPos = cszPhoneNumber.value.indexOf(',');
				while (nPos != -1)
				{
					String cszNumber = cszPhoneNumber.value.substring(nStart,
							nPos - nStart);
					if (cszNumber.compareTo("") != 0)
					{
						cNumberArray.add(cszNumber);
					}
					nStart = nPos + 1;
					nPos = cszPhoneNumber.value.indexOf(',', nStart);
				}
			}
		}
		StringWrapper cszSP = new StringWrapper();
		if (m_pSPVar != null)
		{
			m_pSPVar.getValue(cszSP);
		}
		StringWrapper cszChargeNumber = new StringWrapper();
		if (m_pChargeNumberVar != null)
		{
			m_pChargeNumberVar.getValue(cszChargeNumber);
		}

		StringWrapper cszCorpId = new StringWrapper();
		if (m_pCorpId != null)
		{
			m_pCorpId.getValue(cszCorpId);
		}
		StringWrapper cszServiceType = new StringWrapper();
		if (m_pServiceTypeVar != null)
		{
			m_pServiceTypeVar.getValue(cszServiceType);
		}
		StringWrapper cszLinkID = new StringWrapper();
		if (m_pLinkID != null)
		{
			m_pLinkID.getValue(cszLinkID);
		}

		StringWrapper cszMsg = new StringWrapper();
		if (m_pMsgVar != null)
		{
			m_pMsgVar.getValue(cszMsg);
		}

		Date sExpireTime = null;
		Date sScheduleTime = null;

		if (m_pExpireTimeVar != null)
		{
			sExpireTime = m_pExpireTimeVar.datetime.getTime();
		}
		if (m_pScheduleTimeVar != null)
		{
			sScheduleTime = m_pScheduleTimeVar.datetime.getTime();
		}

		StringWrapper szMsgID = new StringWrapper("");
		if (m_pMsgID != null)
		{
			m_pMsgID.getValue(szMsgID);
		}

		String numberArray[] = new String[cNumberArray.size()];
		int nReturn = Constant.convertError2Event(SmsClient.sendSmsMsg(
				m_bIsUssdMsg, m_nParamLevel, szMsgID,
				m_pMsgTotal != null ? m_pMsgTotal.getValueInt() : 1,
				m_pMsgNumber != null ? m_pMsgNumber.getValueInt() : 1,
				cNumberArray.toArray(numberArray), cszSP.value,
				cszChargeNumber.value, cszCorpId.value, cszServiceType.value,
				m_pFeeTypeVar != null ? m_pFeeTypeVar.getValueInt() : 0,
				m_pFeeValueVar != null ? m_pFeeValueVar.getValueInt() : 0,
				m_pGivenValueVar != null ? m_pGivenValueVar.getValueInt() : 0,
				m_pAgentFlag != null ? m_pAgentFlag.getValueInt() : 1,
				m_pMorelatetoMTFlag != null ? m_pMorelatetoMTFlag.getValueInt()
						: 1, m_pPriorityVar != null ? m_pPriorityVar
						.getValueInt() : 0, sExpireTime == null ? "" : "032+"
						+ sdf.format(sExpireTime), sScheduleTime == null ? ""
						: "032+" + sdf.format(sScheduleTime),
				m_pReportFlag != null ? m_pReportFlag.getValueInt() : 0,
				m_pTP_pid != null ? m_pTP_pid.getValueInt() : 0,
				m_pTP_udhi != null ? m_pTP_udhi.getValueInt() : 0,
				m_pMessageType != null ? m_pMessageType.getValueInt() : 0,
				cszLinkID.value, 15, cszMsg.value.length(), cszMsg.value));
		if (nReturn == Constant.EVENT_No_Error)
		{
			if (m_pMsgID != null)
			{
				m_pMsgID.setValue(szMsgID.value);
			}
		}
		return nReturn;
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SendSmsMsg ");
		buff.append(getVarName());
		buff.append(" = new SendSmsMsg(this);\n");

		buff.append(getVarName());
		buff.append(".m_bIsUssdMsg = ");
		buff.append(m_bIsUssdMsg);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_nParamLevel = ");
		buff.append(m_nParamLevel);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMsgID = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMsgID);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMsgTotal = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMsgTotal);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMsgNumber = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMsgNumber);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pPhoneNumberVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pPhoneNumberVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pSPVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pSPVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pChargeNumberVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pChargeNumberVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pCorpId = ");
		ServiceNode.translateVar2SourceCode(buff, m_pCorpId);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pServiceTypeVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pServiceTypeVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pFeeTypeVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pFeeTypeVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pFeeValueVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pFeeValueVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pGivenValueVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pGivenValueVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pAgentFlag = ");
		ServiceNode.translateVar2SourceCode(buff, m_pAgentFlag);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMorelatetoMTFlag = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMorelatetoMTFlag);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pPriorityVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pPriorityVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pExpireTimeVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pExpireTimeVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pScheduleTimeVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pScheduleTimeVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pReportFlag = ");
		ServiceNode.translateVar2SourceCode(buff, m_pReportFlag);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pTP_pid = ");
		ServiceNode.translateVar2SourceCode(buff, m_pTP_pid);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pTP_udhi = ");
		ServiceNode.translateVar2SourceCode(buff, m_pTP_udhi);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMessageType = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMessageType);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pLinkID = ");
		ServiceNode.translateVar2SourceCode(buff, m_pLinkID);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMessageCoding = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMessageCoding);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMsgVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMsgVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".m_pMsgFileVar = ");
		ServiceNode.translateVar2SourceCode(buff, m_pMsgFileVar);
		buff.append(";\n");
	}

}
