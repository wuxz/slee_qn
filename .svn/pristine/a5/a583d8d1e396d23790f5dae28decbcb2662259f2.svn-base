/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.comobj.service.SLEEComponentContext;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class ComputingContextImpl implements ComputingContext
{
	/**
	 * 当前脚本的行号。
	 */
	public int currentLineNum = 0;

	/**
	 * 脚本执行失败时的错误消息。
	 */
	public String errorInfo = "";

	/**
	 * 临时变量的命名计数器。
	 */
	private int tempCounter = 0;

	public Map<String, Variant> vars = new HashMap<String, Variant>();

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#clear()
	 */
	public void clear()
	{
		currentLineNum = 0;
		tempCounter = 0;

		for (Iterator<Entry<String, Variant>> it = vars.entrySet().iterator(); it
				.hasNext();)
		{
			Entry<String, Variant> entry = it.next();
			entry.getValue().init();
		}

		vars.clear();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#getCurrentLine()
	 */
	public int getCurrentLine()
	{
		return currentLineNum;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#getSLEEComponentContext()
	 */
	public SLEEComponentContext getSLEEComponentContext()
	{
		return UnifiedServiceManagement.comContext;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#getVariant(java.lang.String)
	 */
	public Variant getVariant(String name)
	{
		Variant var = vars.get(name);
		if (var == null)
		{
			var = new Variant(this, name);
		}

		return var;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#registerVariant(com.channelsoft.reusable.util.Variant)
	 */
	public void registerVariant(Variant var)
	{
		String name = var.getVarName();
		if (name == null)
		{
			name = "{temp var}" + (tempCounter++);
		}

		vars.put(name, var);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#reportError(java.lang.Exception)
	 */
	public void reportError(Exception e) throws Exception
	{
		errorInfo = e.getMessage();
		throw e;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.ComputingContext#setCurrentLine(int)
	 */
	public void setCurrentLine(int num)
	{
		currentLineNum = num;
	}

}