package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.comobj.jmscom.JmsCom;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.capability.SleeService;
import com.channelsoft.slee.channelmanager.ATSChannel;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class SleeServiceImpl implements SleeService
{
	ATSBrowser browser;

	ATSChannel channel;

	public SleeServiceImpl()
	{
		channel = null;
		browser = null;
	}

	public SleeServiceImpl(ATSChannel channel, ATSBrowser browser)
	{
		this.channel = channel;
		this.browser = browser;
	}

	public void fireUserEvent(String channelDn, int callSn, int eventId)
	{
		ATSChannel pChannel = UnifiedServiceManagement.channelManager
				.getChannel(channelDn);
		pChannel.fireUserEvent(callSn, eventId);
	}

	public String getAccountID()
	{
		return browser.getAccountID();
	}

	public int getAccountType()
	{
		return browser.getAccountType();
	}

	public String getAgentDn()
	{
		return browser.getAgentDn();
	}

	public String getAgentId()
	{
		return browser.getAgentId();
	}

	public String getContentId()
	{
		return browser.getContentId();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.capability.SleeService#getMapPath()
	 */
	public String getMapPath()
	{
		return UnifiedServiceManagement.configData.getUsmlPath() + "/";
	}

	public String getNetworkAccount()
	{
		return browser.getNetworkAccount();
	}

	public String getNetworkId()
	{
		return browser.getNetworkId();
	}

	public String getNetworkPortalId()
	{
		return browser.getNetworkPortalId();
	}

	public String getReserve1()
	{
		return browser.getReserve1();
	}

	public String getReserve2()
	{
		return browser.getReserve2();
	}

	public String getReserve3()
	{
		return browser.getReserve3();
	}

	public String getReserve4()
	{
		return browser.getReserve4();
	}

	public String getReserve5()
	{
		return browser.getReserve5();
	}

	public String getServiceID()
	{
		return browser.getCurServiceID();
	}

	public String getSkillId()
	{
		return browser.getSkillId();
	}

	public String getSubscriberId()
	{
		return browser.getSubscriberId();
	}

	public String getUserIpAddr()
	{
		return browser.getUserIpAddr();
	}

	public String getUserNumber()
	{
		return browser.getUserNumber();
	}

	public void msgBox(String value)
	{
		if (value != null)
		{
			// value = value.replace("%", "%%");
		}

		browser.onMessage(String.format("MsgBox:\"%1$s\"", value));
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.capability.SleeService#receiveMessage(java.lang.String, java.lang.String, int)
	 */
	public String receiveMessage(String source, String sessionId, int timeout)
	{
		if (source.equals(""))
		{
			// SLEE通道间消息。
			source = UnifiedServiceManagement.configData.getInternalQueueName();

			sessionId = browser.sleeSession.getSessionId();
		}

		JmsCom jmsCom = new JmsCom();

		jmsCom.setComponentContext(UnifiedServiceManagement.comContext);

		try
		{
			String selector = null;
			if (!sessionId.equals(""))
			{
				selector = "JMSCorrelationID = '" + sessionId + "'";
			}

			return jmsCom.invoke(
					"receiveP2PMsg",
					new Variant[] { new Variant(source, null),
							new Variant(timeout / 1000, null),
							new Variant(selector, null) }).toString();
		}
		catch (Exception e)
		{
			UnifiedServiceManagement.comContext.getLogger().writeException(e);

			return "";
		}
	}

	public boolean sendMessage(String content, String destination,
			String sessionId, boolean isPersistent, int timeout)
	{
		if (destination.equals(""))
		{
			// SLEE通道间消息。
			destination = UnifiedServiceManagement.configData
					.getInternalQueueName();

			if (sessionId.equals(""))
			{
				UnifiedServiceManagement.comContext.getLogger().writeError(
						"发送内部消息缺少会话ID，放弃发送。");
				return false;
			}

			isPersistent = false;
			timeout = -1;
		}
		else if (timeout <= 0)
		{
			timeout = UnifiedServiceManagement.configData
					.getDefaultMessageTTL();
		}

		JmsCom jmsCom = new JmsCom();

		jmsCom.setComponentContext(UnifiedServiceManagement.comContext);

		boolean ret;

		try
		{
			if (sessionId.equals(""))
			{
				ret = jmsCom.invoke(
						"sendP2PMsg",
						new Variant[] { new Variant(content, null),
								new Variant(destination, null),
								new Variant("", null), new Variant("", null),
								new Variant(isPersistent, null),
								new Variant(timeout, null) }).getBoolean();
			}
			else
			{
				ret = jmsCom.invoke(
						"sendP2PMsg",
						new Variant[] { new Variant(content, null),
								new Variant(destination, null),
								new Variant("JMSCorrelationID", null),
								new Variant(sessionId, null),
								new Variant(isPersistent, null),
								new Variant(timeout, null) }).getBoolean();
			}
		}
		catch (Exception e)
		{
			UnifiedServiceManagement.comContext.getLogger().writeException(e);

			return false;
		}

		return ret;
	}

	public void setAccountID(String value)
	{
		browser.setAccountID(value);
	}

	public void setAccountType(int value)
	{
		browser.setAccountType(value);
	}

	public void setAgentDn(String value)
	{
		browser.setAgentDn(value);
	}

	public void setAgentId(String value)
	{
		browser.setAgentId(value);
	}

	public void setContentId(String value)
	{
		browser.setContentId(value);
	}

	public void setNetworkAccount(String value)
	{
		browser.setNetworkAccount(value);
	}

	public void setNetworkId(String value)
	{
		browser.setNetworkId(value);
	}

	public void setNetworkPortalId(String value)
	{
		browser.setNetworkPortalId(value);
	}

	public void setReserve1(String value)
	{
		browser.setReserve1(value);
	}

	public void setReserve2(String value)
	{
		browser.setReserve2(value);
	}

	public void setReserve3(String value)
	{
		browser.setReserve3(value);
	}

	public void setReserve4(String value)
	{
		browser.setReserve4(value);
	}

	public void setReserve5(String value)
	{
		browser.setReserve5(value);
	}

	public void setServiceID(String value)
	{
		browser.setCurServiceID(value);
	}

	public void setSkillId(String value)
	{
		browser.setSkillId(value);
	}

	public void setSubscriberId(String value)
	{
		browser.setSubscriberId(value);
	}

	public void setUserIpAddr(String value)
	{
		browser.setUserIpAddr(value);
	}

	public void setUserNumber(String value)
	{
		browser.setUserNumber(value);
	}
}
