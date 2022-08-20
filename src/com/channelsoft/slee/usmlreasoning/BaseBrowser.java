package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import com.channelsoft.slee.capability.CallControlCapability;
import com.channelsoft.slee.capability.SleeApp;
import com.channelsoft.slee.capability.SleeService;
import com.channelsoft.slee.capability.SleeSession;
import com.channelsoft.slee.capability.UICapability;

public interface BaseBrowser
{
	void clearUserEvent(int id);

	void endDaemonChannel(String daemonChannelDN);

	CallControlCapability getCallControlCapability();

	void getKnowledgeVariables(Vector<String> vars);

	String getKnowledgeVariableValue(String varName);

	SleeApp getSleeApp();

	SleeService getSleeService();

	SleeSession getSleeSession();

	UICapability getUICapability();

	int hasFiredUserEvent();

	boolean isIdle();

	void onEnterIOProcess(String ioProcessName, USMLReasoningResult error);

	void onEnterService(String srvName, String seviceID, String channelType,
			USMLReasoningResult error);

	void onEnterSrvNode(String srvNodeName, USMLReasoningResult error);

	void onEnterWorkflow(String workflowName, USMLReasoningResult error);

	void onLeaveIOProcess(String ioProcessName, String result);

	void onLeaveService();

	void onLeaveSrvNode(String srvNodeName, int error);

	void onLeaveWorkflow(String workflowName);

	void onMessage(String msg);

	void onRunError(String errorInfo);

	void onUSMLEvent(boolean isSysProcess, int event);

	void popUserEventSet();

	void pushUserEventSet(Vector<Integer> userEventSet);

	SleepTask restoreTask(long sessionId);

	void setKnowledgeVariableValue(String varName, String value);

	boolean shouldExit();

	String startDaemonService(String usmlDoc, String startParam);
}
