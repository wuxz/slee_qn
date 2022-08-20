/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.channelsoft.reusable.comobj.IDispatch;
import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.capability.CallControlCapability;
import com.channelsoft.slee.capability.SleeApp;
import com.channelsoft.slee.capability.SleeService;
import com.channelsoft.slee.capability.SleeSession;

/**
 * 内置对象，封装对内置对象和方法的调用。
 * 
 * @author wuxz
 */
public abstract class InternalObject implements IDispatch
{
	protected SleeApp app;

	protected CallControlCapability callController;

	protected ComputingContext computingContext;

	protected SleeService service;

	protected SleeSession session;

	public InternalObject()
	{
	}

	public abstract String getDefaultPropertyName();

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.IDispatch#getProperty(java.lang.String, com.channelsoft.reusable.util.Variant[])
	 */
	public Variant getProperty(String propertyName, Variant[] args)
			throws Exception
	{
		if (propertyName.equals(""))
		{
			propertyName = getDefaultPropertyName();
		}

		return invoke(propertyName, args);
	}

	public void init(SleeApp app, SleeSession session, SleeService service,
			CallControlCapability callController)
	{
		this.app = app;
		this.service = service;
		this.session = session;
		this.callController = callController;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.IDispatch#invoke(java.lang.String, com.channelsoft.reusable.util.Variant[])
	 */
	public Variant invoke(String method, Variant[] args) throws Exception
	{
		method = method.toLowerCase();

		try
		{
			Method method2Exec = getClass().getMethod(method, Variant[].class);
			return (Variant) method2Exec.invoke(this, new Object[] { args });
		}
		catch (InvocationTargetException ite)
		{
			Throwable e = ite.getTargetException();
			if (e instanceof Exception)
			{
				throw (Exception) e;
			}

			throw new Exception(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.IDispatch#setComputingContext(com.channelsoft.reusable.comobj.service.ComputingContext)
	 */
	public void setComputingContext(ComputingContext cc)
	{
		computingContext = cc;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.IDispatch#setProperty(java.lang.String, com.channelsoft.reusable.util.Variant[])
	 */
	public void setProperty(String propertyName, Variant[] args)
			throws Exception
	{
		if (propertyName.equals(""))
		{
			propertyName = "set" + getDefaultPropertyName();
		}
		else
		{
			propertyName = "set" + propertyName;
		}

		invoke(propertyName, args);
	}
}
