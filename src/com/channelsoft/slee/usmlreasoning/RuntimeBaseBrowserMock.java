package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import com.channelsoft.slee.capability.CallControlCapability;
import com.channelsoft.slee.capability.SleeApp;
import com.channelsoft.slee.capability.SleeService;
import com.channelsoft.slee.capability.SleeSession;
import com.channelsoft.slee.capability.UICapability;

public class RuntimeBaseBrowserMock implements BaseBrowser
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#clearUserEvent(int)
	 */
	public void clearUserEvent(int id)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#endDaemonChannel(java.lang.String)
	 */
	public void endDaemonChannel(String daemonChannelDN)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getCallControlCapability()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getSleeApp()
	 */
	public SleeApp getSleeApp()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getSleeService()
	 */
	public SleeService getSleeService()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getSleeSession()
	 */
	public SleeSession getSleeSession()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getUICapability()
	 */
	public UICapability getUICapability()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#hasFiredUserEvent()
	 */
	public int hasFiredUserEvent()
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#isIdle()
	 */
	public boolean isIdle()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onEnterIOProcess(java.lang.String,
	 *      com.channelsoft.reusable.util.USMLReasoningResult)
	 */
	public void onEnterIOProcess(String ioProcessName, USMLReasoningResult error)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onEnterService(java.lang.String,
	 *      java.lang.String, java.lang.String,
	 *      com.channelsoft.reusable.util.USMLReasoningResult)
	 */
	public void onEnterService(String srvName, String seviceID,
			String channelType, USMLReasoningResult error)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onEnterSrvNode(java.lang.String,
	 *      com.channelsoft.reusable.util.USMLReasoningResult)
	 */
	public void onEnterSrvNode(String srvNodeName, USMLReasoningResult error)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onEnterWorkflow(java.lang.String,
	 *      com.channelsoft.reusable.util.USMLReasoningResult)
	 */
	public void onEnterWorkflow(String workflowName, USMLReasoningResult error)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onLeaveIOProcess(java.lang.String,
	 *      java.lang.String)
	 */
	public void onLeaveIOProcess(String ioProcessName, String result)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onLeaveService()
	 */
	public void onLeaveService()
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onLeaveSrvNode(java.lang.String,
	 *      int)
	 */
	public void onLeaveSrvNode(String srvNodeName, int error)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onLeaveWorkflow(java.lang.String)
	 */
	public void onLeaveWorkflow(String workflowName)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onMessage(java.lang.String)
	 */
	public void onMessage(String msg)
	{
		System.out.println("Info:[" + msg + "]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onRunError(java.lang.String)
	 */
	public void onRunError(String errorInfo)
	{
		System.out.println("Error:[" + errorInfo + "]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#onUSMLEvent(boolean,
	 *      int)
	 */
	public void onUSMLEvent(boolean isSysProcess, int event)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#popUserEventSet()
	 */
	public void popUserEventSet()
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#pushUserEventSet(java.util.Vector)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#shouldExit()
	 */
	public boolean shouldExit()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#startDaemonService(java.lang.String,
	 *      java.lang.String)
	 */
	public String startDaemonService(String usmlDoc, String startParam)
	{
		return null;
	}

}
