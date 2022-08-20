package com.channelsoft.slee.capability;

public interface SleeService
{
	public void fireUserEvent(String channelDn, int callSn, int eventId);

	public String getAccountID();

	public int getAccountType();

	public String getAgentDn();

	public String getAgentId();

	public String getContentId();

	public String getMapPath();

	public String getNetworkAccount();

	public String getNetworkId();

	public String getNetworkPortalId();

	public String getReserve1();

	public String getReserve2();

	public String getReserve3();

	public String getReserve4();

	public String getReserve5();

	public String getServiceID();

	public String getSkillId();

	public String getSubscriberId();

	public String getUserIpAddr();

	public String getUserNumber();

	public void msgBox(String value);

	/**
	 * ������Ϣ��
	 * 
	 * @param source
	 *            ��Ϣ����Դ��""��ʾ����SLEE�ڲ���Ϣ��
	 * @param sessionId
	 *            ָ��Ҫ���յ���Ϣ�ĻỰID��""��ʾ��ָ���Ự��
	 * @param timeout
	 *            ��ʱʱ������λΪ���롣0Ϊ�����ڵȴ���
	 * @return ���յ�����Ϣ���ݡ�""Ϊ����ʧ�ܡ�
	 */
	public String receiveMessage(String source, String sessionId, int timeout);

	/**
	 * ������Ϣ��
	 * 
	 * @param content
	 *            ��Ϣ����
	 * @param destination
	 *            ��ϢĿ�꣬���������""��ʾ����SLEE�ڲ���Ϣ��
	 * @param sessionId
	 *            Ŀ��Ự�ĻỰID��""��ʾû��ָ����Ŀ��Ự��
	 * @param isPersistent
	 *            ��Ϣ�Ƿ�Ϊ�־û���Ϣ��
	 * @param timeout
	 *            ��Ϣ�ĳ�ʱʱ������λΪ�롣
	 * @return trueΪ�ɹ���falseΪʧ�ܡ�
	 */
	public boolean sendMessage(String content, String destination,
			String sessionId, boolean isPersistent, int timeout);

	public void setAccountID(String value);

	public void setAccountType(int value);

	public void setAgentDn(String value);

	public void setAgentId(String value);

	public void setContentId(String value);

	public void setNetworkAccount(String value);

	public void setNetworkId(String value);

	public void setNetworkPortalId(String value);

	public void setReserve1(String value);

	public void setReserve2(String value);

	public void setReserve3(String value);

	public void setReserve4(String value);

	public void setReserve5(String value);

	public void setServiceID(String value);

	public void setSkillId(String value);

	public void setSubscriberId(String value);

	public void setUserIpAddr(String value);

	public void setUserNumber(String value);
}
