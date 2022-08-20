package com.channelsoft.slee.channelmanager;

import java.util.Calendar;

public class Contact implements Cloneable, java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4938476781227159167L;

	static int totalContact;

	public Calendar accessTime;

	public String accountId;

	String ani = "";

	public Calendar callHangupTime;

	String callingNumber = "";

	int callSn = -1;

	String channelDn = "";

	String dnis = "";

	boolean inboundCall;

	boolean isVideoCall;

	String oriAni = "";

	String oriDnis = "";

	@Override
	public Contact clone()
	{
		Contact ret = new Contact();
		ret.ani = ani;
		ret.callingNumber = callingNumber;
		ret.callSn = callSn;
		ret.dnis = dnis;
		ret.inboundCall = inboundCall;
		ret.oriAni = oriAni;
		ret.oriDnis = oriDnis;
		ret.accessTime = accessTime;
		ret.accountId = accountId;
		ret.callHangupTime = callHangupTime;

		return ret;
	}

	public String getAni()
	{
		return ani;
	}

	public String getCallingNumber()
	{
		return callingNumber;
	}

	public int getCallSn()
	{
		return callSn;
	}

	public String getChannelDn()
	{
		return channelDn;
	}

	public String getDnis()
	{
		return dnis;
	}

	public String getOriAni()
	{
		return oriAni;
	}

	public String getOriDnis()
	{
		return oriDnis;
	}

	public boolean isInboundCall()
	{
		return inboundCall;
	}

	/**
	 * @return the isVideoCall
	 */
	public boolean isVideoCall()
	{
		return isVideoCall;
	}

	public void setAni(String ani)
	{
		this.ani = ani;
	}

	public void setCallingNumber(String callingNumber)
	{
		this.callingNumber = callingNumber;
	}

	public void setCallSn(int contactId)
	{
		this.callSn = contactId;
	}

	public void setChannelDn(String channelDn)
	{
		this.channelDn = channelDn;
	}

	public void setDnis(String dnis)
	{
		this.dnis = dnis;
	}

	public void setInboundCall(boolean inboundCall)
	{
		this.inboundCall = inboundCall;
	}

	public void setOriAni(String oriAni)
	{
		this.oriAni = oriAni;
	}

	public void setOriDnis(String oriDnis)
	{
		this.oriDnis = oriDnis;
	}

	/**
	 * @param isVideoCall
	 *            the isVideoCall to set
	 */
	public void setVideoCall(boolean isVideoCall)
	{
		this.isVideoCall = isVideoCall;
	}
}
