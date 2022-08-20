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
	 * 接收消息。
	 * 
	 * @param source
	 *            消息的来源。""表示接收SLEE内部消息。
	 * @param sessionId
	 *            指定要接收的消息的会话ID。""表示不指定会话。
	 * @param timeout
	 *            超时时长，单位为毫秒。0为无限期等待。
	 * @return 接收到的消息内容。""为接收失败。
	 */
	public String receiveMessage(String source, String sessionId, int timeout);

	/**
	 * 发送消息。
	 * 
	 * @param content
	 *            消息内容
	 * @param destination
	 *            消息目标，如队列名。""表示发送SLEE内部消息。
	 * @param sessionId
	 *            目标会话的会话ID。""表示没有指定的目标会话。
	 * @param isPersistent
	 *            消息是否为持久化消息。
	 * @param timeout
	 *            消息的超时时长，单位为秒。
	 * @return true为成功，false为失败。
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
