/**
 * 
 */
package com.channelsoft.slee.callagent.ccod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.channelsoft.DDSSpace.DDSInterfacePrx;
import com.channelsoft.DDSSpace.DDSInterfacePrxHelper;
import com.channelsoft.DDSSpace.IvrCallEventT;
import com.channelsoft.DDSSpace.IvrCallTypeT;
import com.channelsoft.DDSSpace.IvrEventInfoT;
import com.channelsoft.DDSSpace.IvrEventReasonT;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * DDS客户端，连接DDS服务器，并发送监控消息、写统计文件供入库。
 * 
 * @author wuxz
 */
public class DDSClient
{
	/**
	 * DDSClientDLL动态库
	 * 
	 * @author dragon.huang
	 */
	public interface DDSClientDLL extends Library
	{
		int DDSC_initialize(String ClientID, String GLSProxyString,
				String AreaName, String PersistPath, String strLogFilePath,
				int nLogLevel, String DDSProxyString);

		int DDSC_sendMessage(String Title, String SubTitle, String Message);

		int DDSC_unInitialize();
	}

	/**
	 * 向DDS发送消息的线程。
	 * 
	 * @author wuxz
	 */
	class EventSender extends Thread
	{
		String EventToString(IvrEventInfoT event)
		{
			return String.format("%s|%d|%d|%s|%s|%s|%d|%d|%d|", event.veName,
					event.TimeStamp, event.SessionID, event.EnterpriseID,
					event.LocalURI, event.RemoteURI, event.CallType.ordinal(),
					event.CallEvent.ordinal(), event.EventReason.ordinal());
		}

		@Override
		public void run()
		{
			setName("CallAgent4CCOD DDSClient EventSender");

			while (true)
			{
				try
				{
					if ((ddsClientDll == null)
							&& ((glsClient.ddsConnection == null) || ""
									.equals(glsClient.ddsConnection)))
					{
						// 如果还未获得DDS服务器的地址，则休眠后直接返回。
						Thread.sleep(1000);

						continue;
					}

					if (!isDDSClientInitialed && (dds == null))
					{
						connect();
					}

					if (isDDSClientInitialed)
					{
						IvrEventInfoT event = events2Send.take();
						ddsClientDll.DDSC_sendMessage(event.EnterpriseID, "1",
								EventToString(event));
					}
					else
					{
						if (dds != null)
						{
							IvrEventInfoT event = events2Send.take();
							dds.setIvrEvent(event);
						}
					}
				}
				catch (Throwable e)
				{
					Log.wException(LogDef.id_0x10150000, e);
					Log.wError(LogDef.id_0x1015000A);

					disconnect();

					try
					{
						Thread.sleep(5000);
					}
					catch (Throwable ee)
					{
					}
				}
			}
		}
	}

	/**
	 * 写日志的线程。
	 * 
	 * @author wuxz
	 */
	class EventWriter extends Thread
	{
		/**
		 * 每个文件最多包含的记录条数。
		 */
		static final int MAX_LINE_PER_FILE = 10000;

		/**
		 * 每个文件最多包含的记录的时间跨度，单位为分钟。
		 */
		static final int MAX_MINUTES_PER_FILE = 20;

		String lineSeparator = "\r\n";

		long logStartMillis;

		private PrintWriter outStream;

		SimpleDateFormat sdFormatLog = new SimpleDateFormat(
				"yyyyMMdd_HHmmssSSS");

		private int totalLogTimes;

		boolean close()
		{
			outStream.close();
			return true;
		}

		void init()
		{
			totalLogTimes = 0;
			outStream = null;
		}

		private void newLog()
		{
			close();
			open();
		}

		private boolean open()
		{
			String fileName = statFileDir + "/"
					+ sdFormatLog.format(new Date()) + ".log";

			try
			{
				outStream = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(fileName),
								"GBK")));
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}

			logStartMillis = System.currentTimeMillis();

			totalLogTimes = 0;

			return true;
		}

		@Override
		public void run()
		{
			setName("CallAgent4CCOD DDSClient EventWriter");

			try
			{
				lineSeparator = System.getProperty("line.separator");
			}
			catch (Exception e)
			{
			}

			if (!new File(statFileDir).exists())
			{// 如果文件路径不存在，则不写日志
				Log.wError(LogDef.id_0x1015000B, statFileDir);
				return;
			}

			open();

			while (true)
			{
				try
				{
					long millis2Wait = MAX_MINUTES_PER_FILE * 60 * 1000
							- (System.currentTimeMillis() - logStartMillis);
					IvrEventInfoT event = events2Write.poll(millis2Wait,
							TimeUnit.MILLISECONDS);

					if (event != null)
					{
						writeEvent(event);
					}
					else
					{
						newLog();
					}
				}
				catch (Throwable e)
				{
				}
			}
		}

		void write(String content)
		{
			try
			{
				totalLogTimes++;
				if ((totalLogTimes > MAX_LINE_PER_FILE)
						|| ((System.currentTimeMillis() - logStartMillis) >= MAX_MINUTES_PER_FILE * 60 * 1000))
				{
					newLog();
				}

				outStream.write(content);
				outStream.write(lineSeparator);
				outStream.flush();
			}
			catch (Throwable e)
			{
			}
		}

		/**
		 * 写一个消息到日志文件中。按需要创建文件。
		 * 
		 * @param event
		 */
		public void writeEvent(IvrEventInfoT event)
		{
			String content = String.format("%x|%d|%s|%s|%s|0|%d",
					event.SessionID, event.TimeStamp, event.EnterpriseID,
					event.LocalURI, event.RemoteURI, event.CallEvent.ordinal());

			write(content);
		}
	}

	/**
	 * 消息队列的最大长度。
	 */
	static final int MAX_QUEUE_SIZE = 1000;

	DDSInterfacePrx dds = null;

	/**
	 * DDSClientDll.dll(libDDSClient.so)实例
	 */
	DDSClientDLL ddsClientDll = null;

	LinkedBlockingQueue<IvrEventInfoT> events2Send = new LinkedBlockingQueue<IvrEventInfoT>();

	LinkedBlockingQueue<IvrEventInfoT> events2Write = new LinkedBlockingQueue<IvrEventInfoT>();

	GLSClient glsClient = null;

	Ice.Communicator ic = null;

	/**
	 * isDDSClientInitialed是否已经初始化
	 */
	boolean isDDSClientInitialed = false;

	String statFileDir = null;

	DDSClient(String statFileDir, GLSClient glsClient)
	{
		this.statFileDir = statFileDir;
		this.glsClient = glsClient;
	}

	/**
	 * 添加一个待处理的消息。
	 * 
	 * @param sessionId
	 * @param timestamp
	 * @param enterpriseId
	 * @param ani
	 * @param dnis
	 * @param eventType
	 * @param eventReason
	 */
	public void addEvent(long sessionId, int timestamp, String enterpriseId,
			String ani, String dnis, int eventType, int eventReason)
	{
		IvrEventInfoT event = new IvrEventInfoT();
		event.CallEvent = IvrCallEventT.convert(eventType);
		event.CallType = IvrCallTypeT.TIvrIn;
		event.EnterpriseID = enterpriseId;
		event.EventReason = IvrEventReasonT.convert(eventReason);
		event.LocalURI = dnis;
		event.RemoteURI = ani;
		event.SessionID = sessionId;
		event.TimeStamp = timestamp;
		event.veName = glsClient.glsServiceName;

		if (events2Send.size() < MAX_QUEUE_SIZE)
		{
			events2Send.add(event);
		}

		if (events2Write.size() < MAX_QUEUE_SIZE)
		{
			events2Write.add(event);
		}
	}

	void connect() throws Exception
	{
		if (isDDSClientInitialed || (ic != null))
		{
			disconnect();
		}

		if (ddsClientDll == null)
		{
			if (dds == null)
			{
				Log.wDebug(LogDef.id_0x10050000_14, glsClient.ddsConnection);

				ic = Ice.Util.initialize(new String[] { "" });
				Ice.ObjectPrx base = ic.stringToProxy(glsClient.ddsConnection);
				dds = DDSInterfacePrxHelper
						.checkedCast(base.ice_timeout(15000));

				if (dds == null)
				{
					Log.wDebug(LogDef.id_0x10050000_15);
					Thread.sleep(5000);
				}
				else
				{
					Log.wDebug(LogDef.id_0x10050000_16);
				}
			}
		}
		else
		{
			int result = -1;
			String ClientID = glsClient.glsServiceName;
			String GLSProxyString = glsClient.glsConnection;
			String AreaName = "";

			String PersistPath = statFileDir;
			String strLogFilePath = PersistPath + "/ddsclientdll.log";
			int nLogLevel = 700;
			String DDSProxyString = "";

			Log.wDebug(LogDef.id_0x10050000_17, PersistPath);
			Log.wDebug(LogDef.id_0x10050000_14, GLSProxyString);

			result = ddsClientDll.DDSC_initialize(ClientID, GLSProxyString,
					AreaName, PersistPath, strLogFilePath, nLogLevel,
					DDSProxyString);

			if (result == 0)
			{
				isDDSClientInitialed = true;
				Log.wDebug(LogDef.id_0x10050000_16);
			}
			else
			{
				Log.wDebug(LogDef.id_0x10050000_15);
				Thread.sleep(1000);
			}
		}
	}

	void disconnect()
	{
		if (ddsClientDll == null)
		{
			if (ic != null)
			{
				try
				{
					ic.destroy();
				}
				catch (Throwable ee)
				{
				}

				ic = null;
				dds = null;
			}
		}
		else
		{
			if (isDDSClientInitialed)
			{
				try
				{
					ddsClientDll.DDSC_unInitialize();
				}
				catch (Throwable e)
				{
				}
			}
			isDDSClientInitialed = false;
		}
	}

	/**
	 * 启动消息处理线程。
	 */
	public void startClients()
	{
		try
		{
			ddsClientDll = (DDSClientDLL) Native.loadLibrary("DDSClient",
					DDSClientDLL.class);
		}
		catch (Throwable e)
		{
			ddsClientDll = null;
			Log.wDebug("装载DDSClient动态库失败 : " + e.toString());
		}

		EventSender sender = new EventSender();
		sender.start();

		EventWriter writer = new EventWriter();
		writer.start();
	}

}
