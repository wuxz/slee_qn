/**
 * 
 */
package com.channelsoft.slee.unifiedservicemanagement;

import java.util.HashMap;
import java.util.Map;

import com.channelsoft.reusable.comobj.service.ComponentRegistry;
import com.channelsoft.reusable.comobj.service.DBConnectionManager;
import com.channelsoft.reusable.comobj.service.JmsConnManager;
import com.channelsoft.reusable.comobj.service.SLEEComponentContext;
import com.channelsoft.reusable.log.Logger;
import com.channelsoft.slee.log.Log;

/**
 * @author wuxz
 */
public class SLEEComponentContextImpl implements SLEEComponentContext
{
	Map<String, ComponentRegistry> comConfigs = new HashMap<String, ComponentRegistry>();

	Map<String, ComponentRegistry> comConfigsByProgId = new HashMap<String, ComponentRegistry>();

	Map<String, ComponentRegistry> comRegistrys = new HashMap<String, ComponentRegistry>();

	DBConnectionManager dbConnMan = new DBConnectionManager();

	JmsConnManager jmsConnMan = new JmsConnManager();

	Log log = new Log();

	public SLEEComponentContextImpl()
	{
		dbConnMan.setComContext(this);
		jmsConnMan.setComContext(this);
	}

	public void addComponentRegistry(String progId, String className,
			Map<String, String> configs)
	{
		// progId全部转换为大写，方便以后查找。
		progId = progId.toUpperCase();

		ComponentRegistry cr = new ComponentRegistry(className);
		cr.configs = configs;

		comRegistrys.put(progId, cr);

		cr = new ComponentRegistry(className);
		cr.configs = configs;

		comConfigs.put(className, cr);

		cr = new ComponentRegistry(className);
		cr.configs = configs;

		comConfigsByProgId.put(progId, cr);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.SLEEComponentContext#getComponentClassByProgID(java.lang.String)
	 */
	public String getComponentClassByProgID(String progId)
	{
		progId = progId.toUpperCase();

		return ((comConfigsByProgId == null)
				|| (comConfigsByProgId.get(progId) == null) ? null
				: comConfigsByProgId.get(progId).className);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.reusable.comobj.service.SLEEComponentContext#getComponentRegistry(java.lang.String,
	 *      java.lang.String)
	 */
	public String getComponentRegistry(String className, String key)
	{
		return ((comConfigs == null) || (comConfigs.get(className) == null) ? null
				: comConfigs.get(className).configs.get(key));
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.service.SLEEComponentContext#getComponentRegistryByProgID(java.lang.String, java.lang.String)
	 */
	public String getComponentRegistryByProgID(String progId, String key)
	{
		progId = progId.toUpperCase();

		return ((comConfigsByProgId == null)
				|| (comConfigsByProgId.get(progId) == null) ? null
				: comConfigsByProgId.get(progId).configs.get(key));
	}

	public Map<String, ComponentRegistry> getComponentRegistrys()
	{
		return comRegistrys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.channelsoft.reusable.comobj.service.SLEEComponentContext#getDBConnectionManager()
	 */
	public DBConnectionManager getDBConnectionManager()
	{
		return dbConnMan;
	}

	public JmsConnManager getJmsConnManager()
	{
		return jmsConnMan;
	}

	public Logger getLogger()
	{
		return log;
	}
}
