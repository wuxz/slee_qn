package com.channelsoft.slee.capability;

public interface CallControlCapability
{
	public int answerCall(boolean isBilling);

	public int appendVideo(String[][] infoOnVideo);
	
	/**
	 * ��SLEE��ͨ����ָ�����а󶨡�����˺����Ѿ��󶨵�ĳ��ͨ���ϣ����Ƚ����ͨ������еİ󶨡�
	 * 
	 * @param callSn
	 * @param port
	 * @return
	 */
	public int attachCall(int port, int callSn);

	/**
	 * ��SLEE��ͨ����ָ�����а�Ϊ���������С�����˺����Ѿ��󶨵�ĳ��ͨ���ϣ����Ƚ����ͨ������еİ󶨡�
	 * 
	 * @param callSn
	 * @param port
	 * @return
	 */
	public int attachOtherParty(int port, int callSn);

	public int closeConference();

	public int consultCall(String callingNumber, String destNumber,
			int timeout, boolean isVideoCall);

	public String convertError2String(int code);

	/**
	 * ��SLEE��ͨ���뵱ǰ���н���󶨡�
	 * 
	 * @return
	 */
	public int detachCall();

	public int disconnetCall();

	public String getAni();

	public String getCallingNumber();

	public String getCallSn();

	public String getChannelDn();

	public String getDnis();

	public String getOriAni();

	public String getOriDnis();

	/**
	 * ������������У����������еĺ�����š�
	 * 
	 * @return
	 */
	public String getOtherPartyCallSn();

	public String getOtherPartyTrunkID();

	public String getTrunkID();

	public boolean isInConference();

	public boolean isVideoCall();

	public int makeCall(String callingNumber, String oriCallingNumber,
			String destNumber, String oriDestNumber, int timeOut,
			boolean isVideoCall);
	
	public int callRecordRing(String callingNumber, String oriCallingNumber,
			String destNumber, String oriDestNumber, int timeOut,
			boolean isVideoCall, String fileName, char endFlag,
			int timeDuration, int rate);
	
	public int releaseConnection();

	public int routeConnection(int otherResId);

	public int routeConnection(int port1, int port2, int mode);

	public int routeConnection(int port1, int port2, int mode,
			String videoUrl1, String videoUrl2);
	
	public void setAni(String value);

	public void setCallingNumber(String value);

	public void setDnis(String value);

	public int setMaxCallTime(int maxCallTime);

	public void setOriAni(String value);

	public void setOriDnis(String value);

	public int singleStepConference(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean isVideoCall);

	public int singleStepTransfer(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean isVideoCall);

	public int singleStepTransfer(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean reverseRoute, int mode,
			boolean isVideoCall);

	public int singleStepTransfer(String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			int timeout, boolean routeFirst, boolean reverseRoute, int mode,
			boolean isVideoCall, String videoUrl1, String videoUrl2);
	
	public int transferCall();

	public int unrouteConnection(int otherResId);

	public int unrouteConnection(int port1, int port2, int mode);
}
