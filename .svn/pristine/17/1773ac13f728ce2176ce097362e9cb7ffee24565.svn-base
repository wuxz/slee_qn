package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.jni.SmsClient;
import com.channelsoft.slee.util.Constant;

public class GetSmsMsg extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5650823501591384007L;

	public boolean isUssdMsg;

	public KnowledgeVariable linkID;

	public KnowledgeVariable msgVar;

	public KnowledgeVariable phoneNumberVar;

	public KnowledgeVariable serviceTypeVar;

	public KnowledgeVariable sPVar;

	public int timeout;

	public KnowledgeVariable tP_pid;

	public KnowledgeVariable tP_udhi;

	public GetSmsMsg(Service service)
	{
		super(service);
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper szSP = new StringWrapper();
		StringWrapper szPhoneNumber = new StringWrapper();
		StringWrapper szMsg = new StringWrapper();
		StringWrapper szServiceType = new StringWrapper();
		IntWrapper MsgLength = new IntWrapper();
		IntWrapper TP_pid = new IntWrapper();
		IntWrapper TP_udhi = new IntWrapper();
		StringWrapper szLinkID = new StringWrapper();

		int nReturn = Constant.convertError2Event(SmsClient.getSmsMsg(
				isUssdMsg, szSP, szPhoneNumber, MsgLength, szMsg,
				szServiceType, TP_pid, TP_udhi, szLinkID, timeout));
		if (nReturn == Constant.EVENT_No_Error)
		{
			if (sPVar != null)
			{
				sPVar.setValue(szSP.value);
			}
			if (phoneNumberVar != null)
			{
				phoneNumberVar.setValue(szPhoneNumber.value);
			}
			if (serviceTypeVar != null)
			{
				serviceTypeVar.setValue(szServiceType.value);
			}
			if (msgVar != null)
			{
				msgVar.setValue(szMsg.value);
			}
			if (tP_pid != null)
			{
				tP_pid.setValue(TP_pid.value);
			}
			if (tP_udhi != null)
			{
				tP_udhi.setValue(TP_udhi.value);
			}
			if (linkID != null)
			{
				linkID.setValue(szLinkID.value);
			}
		}
		return nReturn;
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("GetSmsMsg ");
		buff.append(getVarName());
		buff.append(" = new GetSmsMsg(this);\n");

		buff.append(getVarName());
		buff.append(".isUssdMsg = ");
		buff.append(isUssdMsg);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".sPVar = ");
		ServiceNode.translateVar2SourceCode(buff, sPVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".phoneNumberVar = ");
		ServiceNode.translateVar2SourceCode(buff, phoneNumberVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".serviceTypeVar = ");
		ServiceNode.translateVar2SourceCode(buff, serviceTypeVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".msgVar = ");
		ServiceNode.translateVar2SourceCode(buff, msgVar);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".tP_pid = ");
		ServiceNode.translateVar2SourceCode(buff, tP_pid);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".tP_udhi = ");
		ServiceNode.translateVar2SourceCode(buff, tP_udhi);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".linkID = ");
		ServiceNode.translateVar2SourceCode(buff, linkID);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".timeout = ");
		buff.append(timeout);
		buff.append(";\n");

	}

}
