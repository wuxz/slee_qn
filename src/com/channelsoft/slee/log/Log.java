package com.channelsoft.slee.log;

import java.util.Vector;

import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class Log extends com.channelsoft.reusable.log.Log
{
	static Log instance = null;

	public static void addObserver(ErrorObserver eo)
	{
		synchronized (instance.errorObservers)
		{
			instance.errorObservers.add(eo);
		}
	}

	public static void delObserver(ErrorObserver eo)
	{
		synchronized (instance.errorObservers)
		{
			instance.errorObservers.removeElement(eo);
		}
	}

	/**
	 * @return the instance
	 */
	public static Log getInstance()
	{
		return instance;
	}

	public static LogLevel getLogLevel()
	{
		return instance.getLoggerLogLevel();
	}

	public static void initialize(String tag, String logPath)
	{
		instance.initializeLogger(tag, logPath);
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(Log instance)
	{
		Log.instance = instance;
	}

	public static void setLogLevel(LogLevel level)
	{
		instance.setLoggerLogLevel(level);
	}

	public static void setLogLevel(String logLevel)
	{
		instance.setLoggerLogLevel(logLevel);
	}

	public static void setMaxLine(int maxLine)
	{
		instance.setLoggerMaxLine(maxLine);
	}

	public static void wDebug(int id, Object... strArgs)
	{
		instance.writeDebug(LogDef.fmts[id], strArgs);
	}

	public static void wDebug(String format, Object... strArgs)
	{
		instance.writeDebug(LogDef.fmts[LogDef.id_0x10000000] + format, strArgs);
	}

	public static void wError(int id, Object... strArgs)
	{
		instance.writeError(LogDef.fmts[id], strArgs);
	}

	public static void wError(String format, Object... strArgs)
	{
		instance.writeError(LogDef.fmts[LogDef.id_0x10100010] + format, strArgs);
	}

	public static void wException(int id, Throwable e)
	{
		instance.writeError(LogDef.fmts[id], e.toString());
		instance.writeException(e);
	}

	public static void wException(Throwable e)
	{
		wException(LogDef.id_0x10100000, e);
	}

	public static void wFatal(int id, Object... strArgs)
	{
		instance.writeFatal(LogDef.fmts[id], strArgs);
	}

	public static void wFatal(String format, Object... strArgs)
	{
		instance.writeFatal(LogDef.fmts[LogDef.id_0x10200000] + format, strArgs);
	}

	public static void wInfo(int id, Object... strArgs)
	{
		instance.writeInfo(LogDef.fmts[id], strArgs);
	}

	public static void wInfo(String format, Object... strArgs)
	{
		instance.writeInfo(LogDef.fmts[LogDef.id_0x10000000] + format, strArgs);
	}

	public static void wTrace(int id, Object... strArgs)
	{
		instance.writeTrace(LogDef.fmts[id], strArgs);
	}

	public static void wTrace(String format, Object... strArgs)
	{
		instance.writeTrace(LogDef.fmts[LogDef.id_0x10000000] + format, strArgs);
	}

	public static void wWarn(int id, Object... strArgs)
	{
		instance.writeWarn(LogDef.fmts[id], strArgs);
	}

	public static void wWarn(String format, Object... strArgs)
	{
		instance.writeWarn(LogDef.fmts[LogDef.id_0x10000000] + format, strArgs);
	}

	Vector<ErrorObserver> errorObservers = new Vector<ErrorObserver>();

	public void notifyAllObservers(String content)
	{
		synchronized (errorObservers)
		{
			for (int i = 0; i < errorObservers.size(); i++)
			{
				errorObservers.elementAt(i).addVitalInfo(content);
			}
		}
	}

	@Override
	protected void trace(LogLevel logLevel, String content)
	{
		super.trace(logLevel, content);
		if (logLevel.compareTo(LogLevel.ER) <= 0)
		{
			if (null != UnifiedServiceManagement.perfMonitor)
			{
				UnifiedServiceManagement.perfMonitor.writeLogErrorInfo(content);
			}
			notifyAllObservers(content);
		}
	}

}
