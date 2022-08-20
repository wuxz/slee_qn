package com.channelsoft.slee.perfmonitor;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

class ConfigInfo extends PerfData
{
	String content;

	@Override
	void writePackage(DataOutputStream os)
	{
		try
		{
			os.writeByte(4);
			int length = content.getBytes().length;
			os.writeInt(length + 1);
			writeString(os, content, length + 1);
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10170001, e);
		}
	}
}

public class PerfMonitor extends Thread
{
	class PerfCommand
	{
		static final int CHANNEL_DEBUG_DATA = 2;

		static final int CHANNEL_DETAIL_DATA = 1;

		static final int CHANNEL_STATUS_DATA = 0;

		static final int CHANNEL_WORKING_COUNT_DATA = 5;

		static final int COMMAND_REQUEST_BEGIN_CHANNEL_DEBUGINFO = 1;

		static final int COMMAND_REQUEST_CHANNEL_DETAIL = 0;

		static final int COMMAND_REQUEST_CHANNEL_WORKING_COUNT = 4;

		static final int COMMAND_REQUEST_CONFIG = 3;

		static final int COMMAND_REQUEST_DIALOUT = 5;

		static final int COMMAND_REQUEST_END_CHANNEL_DEBUGINFO = 2;

		byte command;

		int endChannelDNNum;

		int length;

		int startChannelDNNum;
	}

	class PerfDataSender extends Thread
	{
		@Override
		public void run()
		{
			setName("PerfDataSender");

			try
			{
				PerfData perfData = null;

				while (true)
				{
					try
					{
						perfData = perfDatas.take();
					}
					catch (Exception e)
					{
					}

					if (!isConnected())
					{
						Thread.sleep(200);
						continue;
					}

					try
					{
						perfData.writePackage(dos);
						dos.flush();
					}

					catch (IOException ioe)
					{
						synchronized (perfDatas)
						{
							if (sk != null)
							{
								sk.close();
								sk = null;
							}
						}

						Log.wException(LogDef.id_0x10170003, ioe);
					}
					catch (Exception e)
					{
						Log.wException(LogDef.id_0x10170003, e);
					}
				}
			}
			catch (Exception e)
			{
				Log.wException(LogDef.id_0x10170005, e);
			}
		}
	}

	private ISysCfgData sysCfgData;

	public static boolean readBytes(byte data[], DataInputStream dis)
			throws IOException
	{
		int lenGot = 0;
		while (lenGot < data.length)
		{
			int len = dis.read(data, lenGot, data.length - lenGot);
			if (len < 0)
			{
				return false;
			}
			else
			{
				lenGot += len;
			}
		}

		return true;
	}

	public ChannelPerfData cpfs[];

	DataInputStream dis = null;

	DataOutputStream dos = null;

	LinkedBlockingQueue<PerfData> perfDatas =
			new LinkedBlockingQueue<PerfData>();

	Socket sk;

	ServerSocket ssk;

	public PerfMonitor(ISysCfgData sysCfgData)
	{
		this.sysCfgData = sysCfgData;
		cpfs =
				new ChannelPerfData[UnifiedServiceManagement.channelManager.channels
						.size()];
		for (int i = 0; i < cpfs.length; i++)
		{
			cpfs[i] = new ChannelPerfData();
		}
	}

	public boolean checkListening()
	{
		try
		{
			ssk = new ServerSocket(sysCfgData.getListenPort());
			return true;
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10170002, e);
			return false;
		}
	}

	boolean isConnected()
	{
		return ((sk != null) && sk.isConnected() && !sk.isClosed());
	}

	boolean parseCommand(PerfCommand pc) throws Exception
	{
		Log.wError(LogDef.id_0x10070003, pc.command);
		switch (pc.command)
		{
		case PerfCommand.COMMAND_REQUEST_CHANNEL_DETAIL:
		{
			for (ChannelPerfData element : cpfs)
			{
				if ((element.channelDNNum >= pc.startChannelDNNum)
						&& (element.channelDNNum <= pc.endChannelDNNum))
				{
					if (isConnected())
					{
						perfDatas.add(element);
					}
				}
			}

			return true;
		}

		case PerfCommand.COMMAND_REQUEST_BEGIN_CHANNEL_DEBUGINFO:
		{
			if (UnifiedServiceManagement.channelManager
					.getChannel(pc.startChannelDNNum) != null)
			{
				UnifiedServiceManagement.channelManager.getChannel(
						pc.startChannelDNNum).setDebugging(true);
			}

			return true;
		}

		case PerfCommand.COMMAND_REQUEST_END_CHANNEL_DEBUGINFO:
		{
			if (UnifiedServiceManagement.channelManager
					.getChannel(pc.startChannelDNNum) != null)
			{
				UnifiedServiceManagement.channelManager.getChannel(
						pc.startChannelDNNum).setDebugging(false);
			}

			return true;
		}

		case PerfCommand.COMMAND_REQUEST_CONFIG:
		{
			ConfigInfo configInfo = new ConfigInfo();
			File configFile = new File(sysCfgData.getCfgFileName());
			FileInputStream in = new FileInputStream(configFile);

			int configLen = (int) (configFile.length());
			byte[] configContent = new byte[configLen];
			in.read(configContent);
			in.close();

			String configtemp = new String(configContent);
			configInfo.content = configtemp;
			if (isConnected())
			{
				perfDatas.add(configInfo);
			}
			return true;
		}
		case PerfCommand.COMMAND_REQUEST_CHANNEL_WORKING_COUNT:
		{
			int total = 0;
			for (ChannelPerfData element : cpfs)
			{
				if (element.status == 1)
				{
					total++;
				}
			}

			ChannelStatusStatistic css = new ChannelStatusStatistic();
			css.channelWorkingCount = total;
			if (isConnected())
			{
				perfDatas.add(css);
			}

			return true;
		}
		case PerfCommand.COMMAND_REQUEST_DIALOUT:
		{
			if (UnifiedServiceManagement.channelManager
					.getChannel(pc.startChannelDNNum) != null)
			{
				UnifiedServiceManagement.channelManager.getChannel(
						pc.startChannelDNNum).startOutboundSrv();
			}

			return true;
		}

		default:
			return false;
		}
	}

	@Override
	public void run()
	{
		setName("PerfMonitor");

		PerfDataSender pds = new PerfDataSender();
		pds.start();

		try
		{
			PerfCommand pc = new PerfCommand();
			while (true)
			{
				Socket skNew = ssk.accept();
				Log.wError(LogDef.id_0x10070002, skNew.getInetAddress()
						.getHostAddress());
				if (sk != null)
				{
					sk.close();
				}

				sk = skNew;
				dis = new DataInputStream(sk.getInputStream());
				dos =
						new DataOutputStream(new BufferedOutputStream(sk
								.getOutputStream(), 128 * 1024));

				while (true)
				{
					try
					{
						pc.command = dis.readByte();
						pc.length = dis.readInt();
						pc.startChannelDNNum = dis.readInt();
						pc.endChannelDNNum = dis.readInt();
						if (!parseCommand(pc))
						{
							Log.wTrace(LogDef.id_0x10070001);
							sk.close();
						}
					}
					catch (IOException ioe)
					{
						synchronized (perfDatas)
						{
							if (sk != null)
							{
								sk.close();
								sk = null;
							}
						}

						dis.close();
						dos.close();
						Log.wException(LogDef.id_0x10170004, ioe);
						break;
					}
				}
			}
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10170000, e);
		}
	}

	public void updateChannelState(int channelDNNum, int status)
	{
		if (!isConnected())
		{
			return;
		}

		ChannelStatus cstatus = new ChannelStatus();
		cstatus.channelDN = String.valueOf(channelDNNum);
		cstatus.channelDNNum = channelDNNum;
		cstatus.status = status;
		perfDatas.add(cstatus);
	}

	public void writeChannelDebugInfo(String channelDN, String debugInfo)
	{
		if (!isConnected())
		{
			return;
		}

		ChannelDebugInfo cdi = new ChannelDebugInfo();
		cdi.channelDN = channelDN;
		cdi.debugInfo = debugInfo;
		perfDatas.add(cdi);
	}

	public void writeChannelDetailInfo(ChannelPerfData cpi)
	{
		if (!isConnected())
		{
			return;
		}

		perfDatas.add(cpi);
	}

	public void writeLogErrorInfo(String content)
	{
		if (!isConnected())
		{
			return;
		}

		LogErrorInfo logErrorInfo = new LogErrorInfo();
		logErrorInfo.content = content;
		perfDatas.add(logErrorInfo);
	}
}
