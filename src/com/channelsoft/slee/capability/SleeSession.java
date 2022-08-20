package com.channelsoft.slee.capability;

public interface SleeSession
{
	public String argv(int index);

	public void contentRemoveAll();

	public int getArgc();

	public String getContent(String key);

	public String getKnowledgeVariables();

	public String getKnowledgeVariableValue(String varName);

	public String getSessionId();

	public void reset(String startParam);

	public void setContent(String key, String value);

	public void setKnowledgeVariableValue(String varName, String value);
}
