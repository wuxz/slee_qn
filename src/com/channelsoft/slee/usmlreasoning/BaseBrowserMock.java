package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import com.channelsoft.slee.capability.CallControlCapability;
import com.channelsoft.slee.capability.SleeApp;
import com.channelsoft.slee.capability.SleeService;
import com.channelsoft.slee.capability.SleeSession;
import com.channelsoft.slee.capability.UICapability;
import com.channelsoft.slee.channelmanager.ATSChannel;

public class BaseBrowserMock implements BaseBrowser
{
	/**
	 * @author wuxz
	 */
	/**
	 * @author wuxz
	 */
	class SleeServiceMock implements SleeService
	{

		public void fireUserEvent(String channelDn, int callSn, int eventId)
		{
		}

		public String getAccountID()
		{
			return null;
		}

		public int getAccountType()
		{
			return 0;
		}

		public String getAgentDn()
		{
			return null;
		}

		public String getAgentId()
		{
			return null;
		}

		public String getContentId()
		{
			return null;
		}

		/* (non-Javadoc)
		 * @see com.channelsoft.slee.capability.SleeService#getMapPath()
		 */
		public String getMapPath()
		{
			return null;
		}

		public String getNetworkAccount()
		{
			return null;
		}

		public String getNetworkId()
		{
			return null;
		}

		public String getNetworkPortalId()
		{
			return null;
		}

		public String getReserve1()
		{
			return null;
		}

		public String getReserve2()
		{
			return null;
		}

		public String getReserve3()
		{
			return null;
		}

		public String getReserve4()
		{
			return null;
		}

		public String getReserve5()
		{
			return null;
		}

		public String getServiceID()
		{
			return null;
		}

		public String getSkillId()
		{
			return null;
		}

		public String getSubscriberId()
		{
			return null;
		}

		public String getUserIpAddr()
		{
			return null;
		}

		public String getUserNumber()
		{
			return null;
		}

		public void msgBox(String value)
		{
			System.out.println("Msgbox[" + value + "]");
		}

		/* (non-Javadoc)
		 * @see com.channelsoft.slee.capability.SleeService#receiveMessage(java.lang.String, java.lang.String, int)
		 */
		public String receiveMessage(String source, String sessionId,
				int timeout)
		{
			return "";
		}

		/* (non-Javadoc)
		 * @see com.channelsoft.slee.capability.SleeService#sendMessage(java.lang.String, java.lang.String, java.lang.String, int)
		 */
		public boolean sendMessage(String content, String destination,
				String sessionId, boolean isPersistent, int timeout)
		{
			return false;
		}

		public void setAccountID(String value)
		{
		}

		public void setAccountType(int value)
		{
		}

		public void setAgentDn(String value)
		{
		}

		public void setAgentId(String value)
		{
		}

		public void setContentId(String value)
		{
		}

		public void setNetworkAccount(String value)
		{
		}

		public void setNetworkId(String value)
		{
		}

		public void setNetworkPortalId(String value)
		{
		}

		public void setReserve1(String value)
		{
		}

		public void setReserve2(String value)
		{
		}

		public void setReserve3(String value)
		{
		}

		public void setReserve4(String value)
		{
		}

		public void setReserve5(String value)
		{
		}

		public void setServiceID(String value)
		{
		}

		public void setSkillId(String value)
		{
		}

		public void setSubscriberId(String value)
		{
		}

		public void setUserIpAddr(String value)
		{
		}

		public void setUserNumber(String value)
		{
		}

	}

	SleeService sleeService = new SleeServiceMock();

	SleeSession sleeSession = new SleeSessionImpl();

	public void clearUserEvent(int id)
	{
	}

	public void endDaemonChannel(String daemonChannelDN)
	{
	}

	public ATSChannel getATSChannel()
	{
		return null;
	}

	public CallControlCapability getCallControlCapability()
	{
		return null;
	}

	public void getKnowledgeVariables(Vector<String> vars)
	{
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getKnowledgeVariableValue(java.lang.String)
	 */
	public String getKnowledgeVariableValue(String varName)
	{
		return null;
	}

	public SleeApp getSleeApp()
	{
		return null;
	}

	public SleeService getSleeService()
	{
		return sleeService;
	}

	public SleeSession getSleeSession()
	{
		return sleeSession;
	}

	public UICapability getUICapability()
	{
		return null;
	}

	public int hasFiredUserEvent()
	{
		return 0;
	}

	public boolean isIdle()
	{
		return false;
	}

	public void onEnterIOProcess(String ioProcessName, USMLReasoningResult error)
	{
	}

	public void onEnterService(String srvName, String seviceID,
			String channelType, USMLReasoningResult error)
	{
	}

	public void onEnterSrvNode(String srvNodeName, USMLReasoningResult error)
	{
	}

	public void onEnterWorkflow(String workflowName, USMLReasoningResult error)
	{
	}

	public void onLeaveIOProcess(String ioProcessName, String result)
	{
	}

	public void onLeaveService()
	{
	}

	public void onLeaveSrvNode(String srvNodeName, int error)
	{
	}

	public void onLeaveWorkflow(String workflowName)
	{
	}

	public void onMessage(String msg)
	{
		System.out.println("Info:[" + msg + "]");
	}

	public void onRunError(String errorInfo)
	{
		System.out.println("Error:[" + errorInfo + "]");
	}

	public void onUSMLEvent(boolean isSysProcess, int event)
	{
	}

	public void popUserEventSet()
	{
	}

	public void pushUserEventSet(Vector<Integer> userEventSet)
	{
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#restoreTask(long)
	 */
	public SleepTask restoreTask(long sessionId)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#setKnowledgeVariableValue(java.lang.String, java.lang.String)
	 */
	public void setKnowledgeVariableValue(String varName, String value)
	{
	}

	public boolean shouldExit()
	{
		return false;
	}

	public String startDaemonService(String usmlDoc, String startParam)
	{
		return null;
	}

}
