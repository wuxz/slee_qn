package com.channelsoft.slee.usmlreasoning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.channelsoft.slee.capability.SleeSession;
import com.channelsoft.slee.channelmanager.ATSChannel;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;

public class SleeSessionImpl implements SleeSession
{
	ATSChannel channel;

	HashMap<String, String> contents = new HashMap<String, String>();

	Vector<String> params = new Vector<String>();

	public SleeSessionImpl()
	{
		channel = null;
	}

	public SleeSessionImpl(ATSChannel channel)
	{
		this.channel = channel;
	}

	public String argv(int index)
	{
		if ((index <= params.size()) && (index > 0))
		{
			String ret = params.elementAt(index - 1);
			return (ret == null ? "" : ret);
		}
		else
		{
			return "";
		}
	}

	public void contentRemoveAll()
	{
		contents.clear();
	}

	public int getArgc()
	{
		return params.size();
	}

	public String getContent(String key)
	{
		if (key != null)
		{
			String ret = contents.get(key.toUpperCase());
			return (ret == null ? "" : ret);
		}
		return "";
	}

	public String getKnowledgeVariables()
	{
		String ret = "";

		Vector<String> vars = new Vector<String>();
		channel.atsBrowser.getKnowledgeVariables(vars);

		Iterator<String> it = vars.iterator();
		while (it.hasNext())
		{
			ret += it.next();
			if (it.hasNext())
			{
				ret += " ";
			}
		}

		return ret;
	}

	public String getKnowledgeVariableValue(String varName)
	{
		return channel.atsBrowser.getKnowledgeVariableValue(varName);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.capability.SleeSession#getSessionId()
	 */
	public String getSessionId()
	{
		return "" + channel.getSessionId();
	}

	public void reset(String startParam)
	{
		contents.clear();
		params.clear();
		if (startParam == null)
		{
			return;
		}
		startParam = startParam.trim();
		try
		{
			if (!"".equals(startParam))
			{
				int pos = 0;
				String sub = "";
				do
				{
					pos = startParam.indexOf(" ");
					if (pos > 0)
					{
						sub = startParam.substring(0, pos);
						startParam = startParam.substring(pos + 1);
						startParam = startParam.trim();
					}
					else
					{
						sub = startParam;
					}
					sub = sub.trim();
					params.addElement(sub);
				} while (pos > 0);
			}
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10130001, e);
		}
	}

	public void setContent(String key, String value)
	{
		if (key != null)
		{
			contents.put(key.toUpperCase(), value);
		}
	}

	public void setKnowledgeVariableValue(String varName, String value)
	{
		channel.atsBrowser.setKnowledgeVariableValue(varName, value);
	}
}
