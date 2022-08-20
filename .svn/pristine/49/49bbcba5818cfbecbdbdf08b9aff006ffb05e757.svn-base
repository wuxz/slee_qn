package com.channelsoft.slee.unifiedservicemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.channelsoft.reusable.serviceprovider.CallEventListener;
import com.channelsoft.reusable.serviceprovider.ReasoningProvider;
import com.channelsoft.slee.billing.BillingDataMgThread;
import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.callagent.V2_CallAgentFactory;
import com.channelsoft.slee.channelmanager.ChannelManager;
import com.channelsoft.slee.jni.DRWRClient;
import com.channelsoft.slee.jni.JniFunction;
import com.channelsoft.slee.jni.SmsClient;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.perfmonitor.PerfMonitor;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.snmpagent.SnmpRelatedData;
import com.channelsoft.slee.unifiedservicemanagement.config.ATSE;
import com.channelsoft.slee.unifiedservicemanagement.config.ATSE.Components.Component.Config;
import com.channelsoft.slee.unifiedservicemanagement.config.DBPool;
import com.channelsoft.slee.unifiedservicemanagement.config.DBPools;
import com.channelsoft.slee.unifiedservicemanagement.config.JMSPool;
import com.channelsoft.slee.unifiedservicemanagement.config.JMSPools;
import com.channelsoft.slee.unifiedservicemanagement.config.ServiceProviders;
import com.channelsoft.slee.usmlreasoning.precompile.ManageServer;

class ConfigMonitor extends Thread
{
	private long lastModifiedTime;

	private ISysCfgData sysCfgData;

	public ConfigMonitor(ISysCfgData sysCfgData)
	{
		this.sysCfgData = sysCfgData;
	}

	@Override
	public void run()
	{
		setName("ConfigMonitor");

		File aFile = new File(sysCfgData.getCfgFileName());
		this.lastModifiedTime = aFile.lastModified();

		while (true)
		{
			if (aFile.exists())
			{
				if (this.lastModifiedTime != aFile.lastModified())
				{
					Log.wTrace(LogDef.id_0x10010003);
					if (UnifiedServiceManagement.configData.reload())
					{
						this.lastModifiedTime = aFile.lastModified();
					}
				}
				else
				{
					try
					{
						Thread.sleep(5000);
					}
					catch (InterruptedException e)
					{
					}
				}
			}
			else
			{
				try
				{
					Thread.sleep(3000);
				}
				catch (InterruptedException e)
				{
				}
			}
		}
	}
}

public class UnifiedServiceManagement extends Thread
{
	public static CallAgent callAgent;

	public static CallEventListener[] callEventListeners;

	public static ChannelManager channelManager;

	public static final SLEEComponentContextImpl comContext;

	public static ISysCfgData configData;

	public static ConfigMonitor configMonitor;

	public static boolean hasSnmpAgent = false;

	public static ManageServer manager;

	public static PerfMonitor perfMonitor;

	public static ReasoningProvider[] reasoningProviders;

	public static final ServiceProviderContextImpl spContext;

	public static String versionInfo = "3.1.6.0";

	static
	{
		try
		{
			updateClasspath();
		}
		catch (Exception e)
		{
			System.exit(-1);
		}

		channelManager = new ChannelManager();
		comContext = new SLEEComponentContextImpl();
		configData = new SysCfgData();
		configMonitor = new ConfigMonitor(configData);
		manager = new ManageServer();
		reasoningProviders = new ReasoningProvider[0];
		callEventListeners = new CallEventListener[0];
		spContext = new ServiceProviderContextImpl();
	}

	@SuppressWarnings("unchecked")
	static void addURL2Classpath(URL url) throws Exception
	{
		URLClassLoader classLoader = (URLClassLoader) ClassLoader
				.getSystemClassLoader();
		Class<?> clazz = URLClassLoader.class;

		// Use reflection
		Method method = clazz.getDeclaredMethod("addURL",
				new Class[] { URL.class });
		method.setAccessible(true);
		method.invoke(classLoader, new Object[] { url });
	}

	public static ChannelManager getChannelManager()
	{
		return channelManager;
	}

	static public void loadComponentRegistry()
	{
		ATSE.Components components = UnifiedServiceManagement.configData
				.getComponents();
		if (components == null)
		{
			return;
		}

		// 初始化组件注册表
		for (int i = 0; i < components.getComponent().size(); i++)
		{
			Map<String, String> configs = new HashMap<String, String>();
			Config cmCfg = components.getComponent().get(i).getConfig();
			for (int j = 0; (cmCfg != null) && (j < cmCfg.getEntry().size()); j++)
			{
				configs.put(cmCfg.getEntry().get(j).getKey(), cmCfg.getEntry()
						.get(j).getValue());
			}

			UnifiedServiceManagement.comContext.addComponentRegistry(components
					.getComponent().get(i).getProgID().toUpperCase(),
					components.getComponent().get(i).getClassName(), configs);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		if (args.length != 0)
		{
			if (args[0].equals("-help"))
			{
				printHelpMessage();
			}
			else if (args[0].equals("-version"))
			{
				printVersion();
			}
			else if (args[0].equals("-releasenote"))
			{
				printReleaseNote();
			}
			else
			{
				System.out.println("Invalid command line option: " + args[0]);
				printHelpMessage();
			}

			return;
		}

		new UnifiedServiceManagement().start();
	}

	/**
	 * 打印命令行帮助信息到屏幕上
	 */
	static void printHelpMessage()
	{
		System.out
				.println("Usage: slee [option]\r\nValid Options:\t-help:\t\tPrint this help message.\r\n\t\t-version:\tPrint SLEE version information.\r\n\t\t-releasenote:\tPrint release history.\r\n");
	}

	/**
	 * 打印发布历史到屏幕上。
	 * 
	 * @throws Exception
	 */
	static void printReleaseNote() throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(Thread
				.currentThread().getContextClassLoader()
				.getResourceAsStream("SLEE Release Note.txt")));

		String line = reader.readLine();
		while (line != null)
		{
			System.out.println(line);
			line = reader.readLine();
		}
	}

	/**
	 * 打印版本信息到屏幕上。
	 */
	static void printVersion()
	{
		System.out.println("SLEE Version: " + versionInfo);
	}

	static void updateClasspath() throws Exception
	{
		String sleeHome = ISysCfgData.queryRegData();
		if (sleeHome == null)
		{
			throw new Exception("Can not find SLEE_HOME, exit.");
		}

		ISysCfgData.isNewPath = (sleeHome.indexOf("ATS4") != -1);
		String javaHome = System.getenv("JAVA_HOME");
		if (javaHome == null)
		{
			throw new Exception("Can not find JAVA_HOME, exit.");
		}

		addURL2Classpath(new File(javaHome + "/lib/tools.jar").toURI().toURL());
		addURL2Classpath(new File(sleeHome
				+ (ISysCfgData.isNewPath() ? "/generated"
						: "/SoftSwitch/generated")).toURI().toURL());

		File files[] = new File(sleeHome
				+ (ISysCfgData.isNewPath() ? "/lib" : "/SoftSwitch/lib"))
				.listFiles();
		for (File file : files)
		{
			if (file.getName().endsWith(".jar"))
			{
				if (file.getName().toLowerCase().startsWith("slee")
						|| file.getName().toLowerCase()
								.startsWith("reusablelib"))
				{
					// 不把slee.jar重复加入路径中。
					continue;
				}

				addURL2Classpath(file.toURI().toURL());
			}
		}
	}

	private String getDBDriverFromType(String type)
	{
		if ("odbc".equalsIgnoreCase(type))
		{
			return "sun.jdbc.odbc.JdbcOdbcDriver";
		}
		else if ("oracle".equalsIgnoreCase(type))
		{
			return "oracle.jdbc.driver.OracleDriver";
		}
		else if ("sqlserver".equalsIgnoreCase(type))
		{
			return "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		}

		return null;
	}

	private String getDBUrlFromProperties(String driverType, String serverIp,
			int serverPort, String database)
	{
		if ("odbc".equalsIgnoreCase(driverType))
		{
			return "jdbc:odbc:" + database;
		}
		else if ("oracle".equalsIgnoreCase(driverType))
		{
			if (serverPort == 0)
			{
				serverPort = 1521;
			}

			return "jdbc:oracle:thin:@" + serverIp + ":" + serverPort + ":"
					+ database;
		}
		else if ("sqlserver".equalsIgnoreCase(driverType))
		{
			if (serverPort == 0)
			{
				serverPort = 1433;
			}

			return "jdbc:microsoft:sqlserver://" + serverIp + ":" + serverPort
					+ ";DatabaseName=" + database;

		}

		return null;
	}

	private void initCallEventListeners()
	{
		ServiceProviders providers = UnifiedServiceManagement.configData
				.getServiceProviders();
		if ((providers == null) || (providers.getCallEventListener() == null))
		{
			return;
		}

		Vector<CallEventListener> rps = new Vector<CallEventListener>();
		for (int i = 0; i < providers.getCallEventListener().size(); i++)
		{
			com.channelsoft.slee.unifiedservicemanagement.config.CallEventListener rp = providers
					.getCallEventListener().get(i);
			if (!rp.isEnabled())
			{
				continue;
			}

			HashMap<String, String> config = new HashMap<String, String>();
			for (int j = 0; (rp.getConfig() != null)
					&& (rp.getConfig().getEntry() != null)
					&& (j < rp.getConfig().getEntry().size()); j++)
			{
				config.put(rp.getConfig().getEntry().get(j).getKey(), rp
						.getConfig().getEntry().get(j).getValue());
			}

			try
			{
				CallEventListener rpObj = (CallEventListener) Class.forName(
						rp.getClassName()).newInstance();
				if (rpObj.startProvider(UnifiedServiceManagement.spContext,
						config))
				{
					Log.wInfo(LogDef.id_0x10010000_1, rp.getClassName());
					rps.add(rpObj);
				}
				else
				{
					Log.wInfo(LogDef.id_0x10010001, rp.getClassName());
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10110000, e);
				Log.wInfo(LogDef.id_0x10010001, rp.getClassName());
			}
		}

		UnifiedServiceManagement.callEventListeners = rps
				.toArray(UnifiedServiceManagement.callEventListeners);
	}

	private void initReasoningProviders()
	{
		ServiceProviders providers = UnifiedServiceManagement.configData
				.getServiceProviders();
		if ((providers == null) || (providers.getReasoningProvider() == null))
		{
			return;
		}

		Vector<ReasoningProvider> rps = new Vector<ReasoningProvider>();
		for (int i = 0; i < providers.getReasoningProvider().size(); i++)
		{
			com.channelsoft.slee.unifiedservicemanagement.config.ReasoningProvider rp = providers
					.getReasoningProvider().get(i);
			if (!rp.isEnabled())
			{
				continue;
			}

			HashMap<String, String> config = new HashMap<String, String>();
			for (int j = 0; (rp.getConfig() != null)
					&& (rp.getConfig().getEntry() != null)
					&& (j < rp.getConfig().getEntry().size()); j++)
			{
				config.put(rp.getConfig().getEntry().get(j).getKey(), rp
						.getConfig().getEntry().get(j).getValue());
			}

			try
			{
				ReasoningProvider rpObj = (ReasoningProvider) Class.forName(
						rp.getClassName()).newInstance();
				if (rpObj.startProvider(UnifiedServiceManagement.spContext,
						config))
				{
					Log.wInfo(LogDef.id_0x10010000_1, rp.getClassName());
					rps.add(rpObj);
				}
				else
				{
					Log.wInfo(LogDef.id_0x10010001, rp.getClassName());
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10110000, e);
				Log.wInfo(LogDef.id_0x10010001, rp.getClassName());
			}
		}

		UnifiedServiceManagement.reasoningProviders = rps
				.toArray(UnifiedServiceManagement.reasoningProviders);
	}

	private boolean loadDBPools()
	{
		DBPools pools = UnifiedServiceManagement.configData.getDBPools();
		if (pools == null)
		{
			return true;
		}

		for (int i = 0; i < pools.getDBPool().size(); i++)
		{
			DBPool pool = pools.getDBPool().get(i);
			String driver = getDBDriverFromType(pool.getDriverType());
			String url = getDBUrlFromProperties(pool.getDriverType(),
					pool.getServerIp(), pool.getServerPort(),
					pool.getDatabase());

			UnifiedServiceManagement.comContext.getDBConnectionManager()
					.initPool(pool.getName(), driver, url, pool.getUserName(),
							pool.getPassword(), pool.getInitCapacity(),
							pool.getMaxCapacity());

		}

		return true;
	}

	private boolean loadJMSPools()
	{
		JMSPools pools = UnifiedServiceManagement.configData.getJMSPools();
		if (pools == null)
		{
			return true;
		}

		for (int i = 0; (i < 1) && (i < pools.getJMSPool().size()); i++)
		{
			JMSPool pool = pools.getJMSPool().get(i);
			String driver = pool.getInitialContextFactory();
			String url = pool.getProviderUrl();

			UnifiedServiceManagement.comContext.getJmsConnManager()
					.initJmsConnManager(driver, url);
		}

		return true;
	}

	@Override
	public void run()
	{
		setName("SLEE Main");

		Log.setInstance(comContext.log);

		Log.initialize("SLEE", ISysCfgData.getLogPath());

		String szAppPath = ISysCfgData.queryRegData();
		if (szAppPath == null)
		{
			Log.wError(LogDef.id_0x10110011);
			return;
		}
		Log.wTrace(LogDef.id_0x10010000_2, szAppPath);
		showVersion();

		Log.wTrace(LogDef.id_0x10010000_3);
		if (!UnifiedServiceManagement.configData.load(true) || !loadDBPools()
				|| !loadJMSPools() || !channelManager.initialize(configData))
		{
			return;
		}
		UnifiedServiceManagement.loadComponentRegistry();
		initReasoningProviders();
		initCallEventListeners();
		Log.wTrace(LogDef.id_0x10010000_4);

		UnifiedServiceManagement.perfMonitor = new PerfMonitor(configData);
		if (!perfMonitor.checkListening())
		{
			Log.wError(LogDef.id_0x10110012,
					UnifiedServiceManagement.configData.getListenPort());
			return;
		}

		try
		{
			DRWRClient.init();
		}
		catch (UnsatisfiedLinkError e)
		{
			if (UnifiedServiceManagement.configData.getShouldWriteSDR())
			{
				throw e;
			}
		}

		try
		{
			System.loadLibrary("SleeJni");
			JniFunction.sleePlgStart();
		}
		catch (UnsatisfiedLinkError e)
		{
		}

		if (UnifiedServiceManagement.configData.getShouldWriteSDR())
		{
			int ret = DRWRClient.drwr_init();
			if (ret == -1)
			{
				UnifiedServiceManagement.configData.setShouldWriteSDR(false);
				Log.wDebug(LogDef.id_0x10020000_35, -1);
			}
			else
			{
				Log.wTrace(LogDef.id_0x10020000_36, ret);
			}
		}

		if (!UnifiedServiceManagement.configData.getSmsServerIP().equals(""))
		{
			SmsClient
					.init(UnifiedServiceManagement.configData.getSmsServerIP());
		}

		callAgent = V2_CallAgentFactory
				.create(UnifiedServiceManagement.configData);

		UnifiedServiceManagement.configMonitor.start();
		UnifiedServiceManagement.perfMonitor.start();
		if (SnmpAgentHandler.instance().configure(
				UnifiedServiceManagement.configData.getSnmpListenPort()))

		{
			hasSnmpAgent = true;
			SnmpAgentHandler.instance().start();
			SnmpRelatedData.instance().setSysCfgData(
					UnifiedServiceManagement.configData);
		}

		try
		{
			Thread.sleep(200);
		}
		catch (Exception e)
		{
		}
		Log.wTrace(LogDef.id_0x10010000_5);
		Log.wFatal(LogDef.id_0x10010000_6);
		Log.wTrace(LogDef.id_0x10010000_7);

		UnifiedServiceManagement.channelManager.start();

		if (UnifiedServiceManagement.configData.getShouldWriteSDR())
		{
			BillingDataMgThread.instance().start();
		}

		manager.start();

		while (true)
		{
			if (!UnifiedServiceManagement.configMonitor.isAlive())
			{
				UnifiedServiceManagement.configMonitor.start();
			}
			if (UnifiedServiceManagement.configData.getShouldWriteSDR()
					&& !BillingDataMgThread.instance().isAlive())
			{
				BillingDataMgThread.instance().start();
			}

			try
			{
				Thread.sleep(60000);
			}
			catch (Exception e)
			{
			}
		}
	}

	void showVersion()
	{
		Log.wTrace(LogDef.id_0x10010000_8);
		Log.wTrace(LogDef.id_0x10010002, versionInfo);
	}
}
