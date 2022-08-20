package com.channelsoft.slee.billing;

import java.util.Calendar;

import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class BillingData implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6808281420923444925L;

	Calendar accessTime;

	String accountID = "";

	int accountType;

	String agentDn = "";

	String agentId = "";

	String calledID = "";

	int callID;

	String callingID = "";

	int channelType;

	String contentId = "";

	Calendar endTime;

	int feeType;

	public boolean isBilling;

	String networkAccount = "";

	String networkId = "";

	String networkPortalId = "";

	String originCalledId = "";

	String originCallingId = "";

	String reserve1 = "";

	String reserve2 = "";

	String reserve3 = "";

	String reserve4 = "";

	String reserve5 = "";

	int serviceAgentRate;

	String serviceID = "";

	int serviceQty;

	int serviceRate;

	String skillId = "";

	String spID = "";

	Calendar startTime;

	String subscriberId = "";

	String userIpAddr = "";

	String userNumber = "";

	public BillingData(boolean bIsBilling)
	{
		isBilling = bIsBilling;
		callID = 0;
		callingID = "XX";
		calledID = "XX";
		accountID = "XX";
		startTime = null;
		endTime = null;
		serviceQty = 0;
		channelType = 0;
		serviceID = "XX";
		spID = "XX";
		accountType = 0;
		feeType = 0;
		serviceAgentRate = 0;
		serviceRate = 0;
	}

	public void beginService(String mServiceID, String mSpID, int mChannelType,
			int mAccountType, int mFeeType, int mServiceAgentRate,
			int mServiceRate, Calendar mAccessTime, Calendar mStartTime)
	{
		if (!UnifiedServiceManagement.configData.getShouldWriteSDR())
		{
			return;
		}

		serviceID = mServiceID;
		channelType = mChannelType;
		spID = mSpID;
		accountType = mAccountType;
		feeType = mFeeType;
		serviceAgentRate = mServiceAgentRate;
		serviceRate = mServiceRate;
		endTime = null;
		accessTime = mAccessTime;
		serviceQty = 0;
		startTime = Calendar.getInstance();
	}

	public void endService(int mdwSN, String mCallingID, String mCalledID,
			String mAccountID, Calendar mEndTime)
	{
		if (!UnifiedServiceManagement.configData.getShouldWriteSDR())
		{
			return;
		}

		callID = mdwSN;
		callingID = mCallingID;
		calledID = mCalledID;
		accountID = mAccountID;

		if (mEndTime == null)
		{
			endTime = Calendar.getInstance();
		}
		else
		{
			endTime = mEndTime;
		}

		if (endTime.compareTo(startTime) < 0)
		{
			endTime = startTime;
		}

		long serviceQtyMilis = endTime.getTimeInMillis()
				- startTime.getTimeInMillis();
		if ((serviceQtyMilis < 1000) && (serviceQtyMilis >= 500))
		{
			serviceQtyMilis = 1000;
		}

		serviceQty = (int) serviceQtyMilis / 1000;

		if ((serviceQty < 0) || (serviceQty > 3600 * 2400))
		{
			serviceQty = 0;
		}
		BillingDataMgThread.instance().billingDataQueue.add(this);

	}

	public Calendar getAccessTime()
	{
		return accessTime;
	}

	public String getAccountID()
	{
		return accountID;
	}

	public int getAccountType()
	{
		return accountType;
	}

	public String getAgentDn()
	{
		return agentDn;
	}

	public String getAgentId()
	{
		return agentId;
	}

	public String getCalledID()
	{
		return calledID;
	}

	public String getCallingID()
	{
		return callingID;
	}

	public String getContentId()
	{
		return contentId;
	}

	public String getNetworkAccount()
	{
		return networkAccount;
	}

	public String getNetworkId()
	{
		return networkId;
	}

	public String getNetworkPortalId()
	{
		return networkPortalId;
	}

	public String getOriginCalledId()
	{
		return originCalledId;
	}

	public String getOriginCallingId()
	{
		return originCallingId;
	}

	public String getReserve1()
	{
		return reserve1;
	}

	public String getReserve2()
	{
		return reserve2;
	}

	public String getReserve3()
	{
		return reserve3;
	}

	public String getReserve4()
	{
		return reserve4;
	}

	public String getReserve5()
	{
		return reserve5;
	}

	public String getServiceID()
	{
		return serviceID;
	}

	public String getSkillId()
	{
		return skillId;
	}

	public String getSubscriberId()
	{
		return subscriberId;
	}

	public String getUserIpAddr()
	{
		return userIpAddr;
	}

	public String getUserNumber()
	{
		return userNumber;
	}

	public boolean isBilling()
	{
		return isBilling;
	}

	public void setAccessTime(Calendar accessTime)
	{
		this.accessTime = accessTime;
	}

	public void setAccountID(String accountID)
	{
		this.accountID = accountID;
		if (serviceAgentRate == 0)
		{
			serviceAgentRate = 2;
		}
	}

	public void setAccountType(int accountType)
	{
		this.accountType = accountType;
	}

	public void setAgentDn(String agentDn)
	{
		this.agentDn = agentDn;
	}

	public void setAgentId(String agentId)
	{
		this.agentId = agentId;
	}

	public void setCalledID(String calledID)
	{
		this.calledID = calledID;
		serviceAgentRate |= 0x10;
	}

	public void setCallingID(String callingID)
	{
		this.callingID = callingID;
		serviceAgentRate |= 0x8;
	}

	public void setContentId(String contentId)
	{
		this.contentId = contentId;
	}

	public void setNetworkAccount(String networkAccount)
	{
		this.networkAccount = networkAccount;
	}

	public void setNetworkId(String networkId)
	{
		this.networkId = networkId;
	}

	public void setNetworkPortalId(String networkPortalId)
	{
		this.networkPortalId = networkPortalId;
	}

	public void setOriginCalledId(String originCalledId)
	{
		this.originCalledId = originCalledId;
	}

	public void setOriginCallingId(String originCallingId)
	{
		this.originCallingId = originCallingId;
	}

	public void setReserve1(String reserve1)
	{
		this.reserve1 = reserve1;
	}

	public void setReserve2(String reserve2)
	{
		this.reserve2 = reserve2;
	}

	public void setReserve3(String reserve3)
	{
		this.reserve3 = reserve3;
	}

	public void setReserve4(String reserve4)
	{
		this.reserve4 = reserve4;
	}

	public void setReserve5(String reserve5)
	{
		this.reserve5 = reserve5;
	}

	public void setServiceID(String serviceID)
	{
		this.serviceID = serviceID;
		if (serviceAgentRate == 0)
		{
			serviceAgentRate = 3;
		}
		else if (serviceAgentRate == 2)
		{
			serviceAgentRate = 5;
		}
	}

	public void setSkillId(String skillId)
	{
		this.skillId = skillId;
	}

	public void setSubscriberId(String subscriberId)
	{
		this.subscriberId = subscriberId;
	}

	public void setUserIpAddr(String userIpAddr)
	{
		this.userIpAddr = userIpAddr;
	}

	public void setUserNumber(String userNumber)
	{
		this.userNumber = userNumber;
	}
}
