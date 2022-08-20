package com.channelsoft.slee.usmlreasoning;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.channelsoft.slee.capability.SleeApp;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class SleeAppImpl implements SleeApp
{
	Map<String, String> contents = Collections
			.synchronizedMap(new HashMap<String, String>());

	public synchronized void contentRemoveAll()
	{
		contents.clear();
	}

	public synchronized String getContent(String key)
	{
		if (key != null)
		{
			String ret = contents.get(key.toUpperCase());
			return (ret == null ? "" : ret);
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.capability.SleeApp#getInternalQueueName()
	 */
	public String getInternalQueueName()
	{
		return UnifiedServiceManagement.configData.getInternalQueueName();
	}

	public String getUserDefined(String key)
	{
		String ret = UnifiedServiceManagement.configData
				.getUserDefinedValue(key);
		return (ret == null ? "" : ret);
	}

	public synchronized void setContent(String key, String value)
	{
		if (key != null)
		{
			contents.put(key.toUpperCase(), value);
		}
	}

	public String startService(String usmlDoc, String startParam)
	{
		return UnifiedServiceManagement.channelManager.beginService(usmlDoc,
				startParam);
	}
}
