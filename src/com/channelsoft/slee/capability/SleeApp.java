package com.channelsoft.slee.capability;

public interface SleeApp
{
	public void contentRemoveAll();

	public String getContent(String key);

	public String getInternalQueueName();

	public String getUserDefined(String key);

	public void setContent(String key, String value);

	public String startService(String usmlDoc, String startParam);
}
