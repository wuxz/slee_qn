package com.channelsoft.slee.channelmanager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.channelsoft.reusable.comobj.sessiondata.GarbageCollectionCallback;
import com.channelsoft.reusable.comobj.sessiondata.SessionDataManagerByJE;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.unifiedservicemanagement.config.DNGroups;
import com.channelsoft.slee.unifiedservicemanagement.config.ResGroup;
import com.channelsoft.slee.usmlreasoning.ATSBrowser;
import com.channelsoft.slee.usmlreasoning.SleepTask;
import com.channelsoft.slee.util.Constant;

public class ChannelManager extends Thread
{
	public static ATSChannel channelsInPort[] = new ATSChannel[1000000];

	static int disconnectCount = 0;

	public static volatile int endServiceCount = 0;

	public static volatile int failedServiceCount = 0;

	static int inboundCount = 0;

	static int moCount = 0;

	static int reportCount = 0;

	static SessionDataManagerByJE sessionDataManager = new SessionDataManagerByJE();

	static AtomicLong sessionIdSeed = new AtomicLong();

	public static SgEventMonitor sgEventMonitor = new SgEventMonitor();

	public static LinkedBlockingQueue<AtsSGEvent> sgEventQueue = new LinkedBlockingQueue<AtsSGEvent>();

	static SessionDataManagerByJE sleepTaskDataManager = new SessionDataManagerByJE();

	public static volatile int startServiceCount = 0;

	public static volatile int successfulServiceCount = 0;

	/**
	 * 取消指定的会话
	 * 
	 * @param sessionId
	 * @throws Exception
	 */
	public static void cancelSession(String sessionId) throws Exception
	{
		ChannelManager.sessionDataManager.removeSessionData("" + sessionId);
		ServiceTimerTask.removeTimerTask(Long.parseLong(sessionId));
		ChannelManager.sleepTaskDataManager.removeSessionData(sessionId);
	}

	public static void forceSessionTimeout(String sessionId) throws Exception
	{
		ServiceTimerTask task = ServiceTimerTask.removeTimerTask(Long
				.parseLong(sessionId));
		if (task != null)
		{
			task.run();
		}
	}

	/**
	 * 获得指定会话的创建时间
	 * 
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public static long getSessionCreatedMillis(String sessionId)
			throws Exception
	{
		SleepTask task = (SleepTask) ChannelManager.sleepTaskDataManager
				.getSessionData(sessionId);

		return task == null ? 0 : task.createdMillis;
	}

	/**
	 * 获得指定会话的当前挂起的推理位置
	 * 
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public static String getSessionReasoningPos(String sessionId)
			throws Exception
	{
		ATSBrowser browser = (ATSBrowser) ChannelManager.sessionDataManager
				.getSessionData(sessionId);

		if (browser == null)
		{
			return "";
		}

		StringWrapper serviceName = new StringWrapper();
		StringWrapper workflowName = new StringWrapper();
		StringWrapper nodeName = new StringWrapper();

		browser.getStatisData(serviceName, workflowName, nodeName);

		return serviceName.value + "\\" + workflowName.value + "\\"
				+ nodeName.value;
	}

	/**
	 * 获得当前挂起的会话的数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public static long getSessionSize() throws Exception
	{
		return ChannelManager.sessionDataManager.size();
	}

	public LinkedBlockingQueue<ATSChannel> appChannels = new LinkedBlockingQueue<ATSChannel>();

	public Vector<ATSChannel> channels = new Vector<ATSChannel>();

	GarbageCollectionCallbackImpl gcci = new GarbageCollectionCallbackImpl();

	public List<ATSChannel> inboundChannels = Collections
			.synchronizedList(new LinkedList<ATSChannel>());

	public List<ATSChannel> moChannels = Collections
			.synchronizedList(new LinkedList<ATSChannel>());

	public List<ATSChannel> reportChannels = Collections
			.synchronizedList(new LinkedList<ATSChannel>());

	public ATSChannel addChannel(String channelDn, String channelType)
	{
		for (int i = 0; i < channels.size(); i++)
		{
			if (channels.elementAt(i).channelDn.equals(channelDn))
			{
				return channels.elementAt(i);
			}
		}

		ATSChannel pChannel = new ATSChannel(channelDn);
		channels.addElement(pChannel);

		if (channelType.equals("MO") || channelType.equals("mo"))
		{
			moChannels.add(pChannel);
			pChannel.channelType = ChannelType.Channel_SMS_MO;
		}
		else if (channelType.equals("Report") || channelType.equals("report"))
		{
			reportChannels.add(pChannel);
			pChannel.channelType = ChannelType.Channel_SMS_Report;
		}
		else if (channelType.equals("App") || channelType.equals("app"))
		{
			appChannels.add(pChannel);
			pChannel.channelType = ChannelType.Channel_App;
		}
		else if (channelType.equals("Inbound") || channelType.equals("inbound"))
		{
			inboundChannels.add(pChannel);
			pChannel.channelType = ChannelType.Channel_Tel;
		}

		return pChannel;
	}

	public String beginService(String usmlDoc, String startParam)
	{
		ATSChannel pChannel = getAppChannel(false);
		if (pChannel != null)
		{
			pChannel.beginService(usmlDoc, startParam);
			Log.wTrace(LogDef.id_0x10020020, pChannel.channelDn);
			return pChannel.channelDn;
		}
		else
		{
			Log.wTrace(LogDef.id_0x10020021);
		}

		return "";
	}

	/**
	 * 获得一个空闲的APP通道。
	 * 
	 * @param isWait
	 *            是否阻塞
	 * @return
	 */
	ATSChannel getAppChannel(boolean isWait)
	{
		ATSChannel pChannel = null;

		try
		{
			if (isWait)
			{
				pChannel = appChannels.take();
			}
			else
			{
				pChannel = appChannels.poll();
			}
		}
		catch (Exception e)
		{
			Log.wError(LogDef.id_0x1012001A);
			Log.wException(LogDef.id_0x10120000, e);
			return null;
		}

		return pChannel;
	}

	public ATSChannel getChannel(int channelDnNum)
	{
		for (int i = 0; i < channels.size(); i++)
		{
			if (channels.elementAt(i).channelDnNum == channelDnNum)
			{
				return channels.elementAt(i);
			}
		}
		return null;
	}

	public ATSChannel getChannel(String channelDn)
	{
		for (int i = 0; i < channels.size(); i++)
		{
			if (channels.elementAt(i).channelDn.equals(channelDn))
			{
				return channels.elementAt(i);
			}
		}
		return null;
	}

	ATSChannel getChannelByPort(int port)
	{
		if ((port >= 0) && (port < channelsInPort.length))
		{
			return channelsInPort[port];
		}

		return null;
		// for (int i = 0; i < channels.size(); i++)
		// {
		// if (channels.elementAt(i).getMediaResID() == port)
		// return channels.elementAt(i);
		// }
		// return null;
	}

	int getChannelCount()
	{
		return channels.size();
	}

	ATSChannel getInboundChannel()
	{
		ATSChannel pChannel = null;

		try
		{
			if (inboundChannels.isEmpty() == false)
			{
				pChannel = inboundChannels.remove(0);
			}
			else
			{
				Log.wError(LogDef.id_0x1012001B);
				if (UnifiedServiceManagement.hasSnmpAgent)
				{
					SnmpAgentHandler.instance().addTrapData(
							"ChannelManage GetInboundChannel 队列为空", 5);
				}
			}

			Log.wDebug(LogDef.id_0x1002001E,
					((pChannel != null) ? pChannel.channelDn : ""),
					inboundChannels.size());
		}
		catch (Exception e)
		{
			Log.wError(LogDef.id_0x1012001C);
			Log.wException(LogDef.id_0x10120000, e);
			return null;
		}

		return pChannel;
	}

	ATSChannel getMoChannel()
	{
		ATSChannel pChannel = null;

		try
		{
			if (moChannels.isEmpty() == false)
			{
				pChannel = moChannels.remove(0);
			}
		}
		catch (Exception e)
		{
			Log.wError(LogDef.id_0x1012001D);
			Log.wException(LogDef.id_0x10120000, e);
			return null;
		}

		return pChannel;
	}

	public long getNewSessionId()
	{
		return sessionIdSeed.incrementAndGet();
	}

	ATSChannel getReportChannel()
	{
		ATSChannel pChannel = null;
		try
		{

			if (reportChannels.isEmpty() == false)
			{
				pChannel = reportChannels.remove(0);
			}
		}
		catch (Exception e)
		{
			Log.wError(LogDef.id_0x1012001E);
			Log.wException(LogDef.id_0x10120000, e);
			return null;
		}

		return pChannel;
	}

	public boolean initialize(ISysCfgData sysCfgData)
	{
		DNGroups groups = sysCfgData.getDNGroups();
		for (int k = 0; k < groups.getResGroup().size(); k++)
		{
			ResGroup resGroup = groups.getResGroup().get(k);

			String szAttrib;
			String szGroupType = resGroup.getType();
			Vector<ATSChannel> arrayChannel = new Vector<ATSChannel>();
			ATSChannel pChannel;
			int nStart = resGroup.getDNList().getComplexDN().getStartDN();
			int nEnd = resGroup.getDNList().getComplexDN().getEndDN();
			for (int i = nStart; i <= nEnd; i++)
			{
				szAttrib = String.format("%1$d", i);
				pChannel = addChannel(szAttrib, szGroupType);
				arrayChannel.addElement(pChannel);
			}

			AppInfo appInfo = new AppInfo();
			appInfo.startTime = resGroup.getRunTime().getRunPeriod().getBegin();

			appInfo.endTime = resGroup.getRunTime().getRunPeriod().getEnd();

			appInfo.usmlFileName = resGroup.getRunTime().getRunPeriod()
					.getApplication().getXMLFile();
			appInfo.usmlFileName = sysCfgData.getUsmlPath() + "/"
					+ appInfo.usmlFileName;
			appInfo.isAutoStart = resGroup.getRunTime().getRunPeriod()
					.getApplication().isIsAutoStart();
			boolean isInbound = resGroup.getRunTime().getRunPeriod()
					.getApplication().isIsInbound();

			for (int j = 0; j < arrayChannel.size(); j++)
			{
				pChannel = arrayChannel.elementAt(j);
				if (isInbound == true)
				{
					pChannel.inAppInfos.add(appInfo);
				}
				else
				{
					pChannel.outAppInfos.add(appInfo);
				}
			}
		}
		return true;
	}

	void reclaimChannel(ATSChannel pChannel)
	{
		if (pChannel.channelType == ChannelType.Channel_App)
		{
			// if (-1 == appChannels.indexOf(pChannel))
			{
				try
				{
					appChannels.put(pChannel);
				}
				catch (InterruptedException e)
				{
				}
				Log.wDebug(LogDef.id_0x1002001F, pChannel.channelDn,
						appChannels.size());
			}
		}
		else if (pChannel.channelType == ChannelType.Channel_SMS_MO)
		{
			if (-1 == moChannels.indexOf(pChannel))
			{
				moChannels.add(pChannel);
				Log.wDebug(LogDef.id_0x1002001F, pChannel.channelDn, moChannels
						.size());
			}

		}
		else if (pChannel.channelType == ChannelType.Channel_SMS_Report)
		{
			if (-1 == reportChannels.indexOf(pChannel))
			{
				reportChannels.add(pChannel);
				Log.wDebug(LogDef.id_0x1002001F, pChannel.channelDn,
						reportChannels.size());
			}
		}
		else if (pChannel.channelType == ChannelType.Channel_Tel)
		{
			if (-1 == inboundChannels.indexOf(pChannel))
			{
				inboundChannels.add(pChannel);
				Log.wDebug(LogDef.id_0x1002001F, pChannel.channelDn,
						inboundChannels.size());
			}
		}
	}

	/**
	 * 注册通道与呼叫PORT之间的对应关系
	 * 
	 * @param channel
	 * @param port
	 * @param callSn
	 */
	void registerChannelOnPort(ATSChannel channel, int port, int callSn)
	{
		if ((port < 0) || (port >= channelsInPort.length))
		{
			return;
		}

		ATSChannel oriChannel = channelsInPort[port];
		if ((oriChannel != null)
				&& (oriChannel.curContact.getCallSn() == callSn))
		{
			oriChannel.detachCall();
		}

		channelsInPort[port] = channel;
	}

	public void resumeChannel(long sessionId) throws InterruptedException
	{
		AppMessage msg = new AppMessage();
		msg.msgId = Constant.EVENT_ResumeService;
		msg.sessionId = sessionId;

		AppMessageProcessor.msgs.put(msg);
	}

	@Override
	public void run()
	{
		setName("ChannelManager");

		try
		{
			Log.wDebug("ChannelManager 恢复会话数据");

			gcci.timeoutMillis = UnifiedServiceManagement.configData
					.getSessionTTL() * 1000;
			sessionDataManager.init(ISysCfgData.queryRegData()
					+ "/SessionData/ChannelSessionData", "channelSessionData",
					gcci);

			Log.wDebug("ChannelManager 恢复会话数据成功，总数=%d个", sessionDataManager
					.size());
		}
		catch (Exception e)
		{
			Log.wException(e);
		}

		try
		{
			Log.wDebug("ChannelManager 恢复计时器数据");

			sleepTaskDataManager.init(ISysCfgData.queryRegData()
					+ "/SessionData/SleepTaskData", "SleepTaskData", null);

			Log.wDebug("ChannelManager 恢复计时器数据成功，总数=%d个", sleepTaskDataManager
					.size());

			sessionIdSeed.set(ServiceTimerTask.restoreData(gcci));

			Log.wDebug("ChannelManager 恢复计时器成功，总数=%d个",
					ServiceTimerTask.timerTasks.size());
		}
		catch (Exception e)
		{
			Log.wException(e);
		}

		sgEventMonitor.start();
		for (int i = 0; i < channelsInPort.length; i++)
		{
			channelsInPort[i] = null;
		}

		for (int i = 0; i < channels.size(); i++)
		{
			channels.elementAt(i).detachMediaResID();
			channels.elementAt(i).sleeResPerfData = UnifiedServiceManagement.perfMonitor.cpfs[i];
			UnifiedServiceManagement.perfMonitor.cpfs[i].channelDN = channels
					.elementAt(i).channelDn;
			UnifiedServiceManagement.perfMonitor.cpfs[i].channelDNNum = channels
					.elementAt(i).channelDnNum;
			channels.elementAt(i).start();
		}
		try
		{
			Thread.sleep(500);
		}
		catch (Exception e)
		{
		}

		new AppMessageProcessor().start();

		new QueueMessageReceiver().start();

		AtsSGEvent sgEvent;

		while (true)
		{
			try
			{
				sgEvent = sgEventQueue.take();

				ATSChannel pChannel;
				if (sgEvent.eventID == Constant.Event_InboundCall)
				{
					inboundCount++;
					Log.wTrace(LogDef.id_0x10020022, sgEvent.port,
							sgEvent.callSN, inboundCount);
					pChannel = getInboundChannel();
					if (pChannel != null)
					{
						pChannel.attachMediaResId(sgEvent.port);
						if ((sgEvent.port >= 0)
								&& (sgEvent.port < channelsInPort.length))
						{
							channelsInPort[sgEvent.port] = pChannel;
						}

						pChannel.curContact.setCallSn(sgEvent.callSN);
						Log.wTrace(LogDef.id_0x10020023, pChannel.channelDn,
								sgEvent.port, sgEvent.callSN);

						pChannel.beginService("", "");
					}
					else
					{
						Log.wTrace(LogDef.id_0x10020024, sgEvent.port,
								sgEvent.callSN);
						UnifiedServiceManagement.callAgent.atsReleaseCall(
								sgEvent.port, sgEvent.callSN, 0, false);
					}
				}
				else if (sgEvent.eventID == Constant.Event_Disconnect)
				{
					disconnectCount++;
					Log.wTrace(LogDef.id_0x10020025, sgEvent.port,
							sgEvent.callSN, disconnectCount);
					pChannel = getChannelByPort(sgEvent.port);
					if ((pChannel != null)
							&& (pChannel.curContact.getCallSn() == sgEvent.callSN))
					{
						pChannel.queryChannelState();
						pChannel.detachMediaResID();
						if ((sgEvent.port >= 0)
								&& (sgEvent.port < channelsInPort.length))
						{
							channelsInPort[sgEvent.port] = null;
						}

					}
				}
				else if (sgEvent.eventID == Constant.Event_SMS_MO)
				{
					moCount++;
					pChannel = getMoChannel();
					if (pChannel != null)
					{
						pChannel.beginService("", "");
						Log.wTrace(LogDef.id_0x10020026, pChannel.channelDn,
								sgEvent.port, sgEvent.callSN, moCount);
					}
					else
					{
						Log.wTrace(LogDef.id_0x10020027, moCount);
					}
				}
				else if (sgEvent.eventID == Constant.Event_SMS_Report)
				{
					reportCount++;
					pChannel = getReportChannel();
					if (pChannel != null)
					{
						pChannel.beginService("", "");
						Log.wTrace(LogDef.id_0x10020028, pChannel.channelDn,
								sgEvent.port, sgEvent.callSN, reportCount);
					}
					else
					{
						Log.wTrace(LogDef.id_0x10020029, reportCount);
					}
				}
				else
				{
					Log.wError(LogDef.id_0x1012001F, sgEvent.eventID);
				}
			}
			catch (Throwable e)
			{
				try
				{
					Thread.sleep(200);
				}
				catch (Exception e1)
				{
				}

				Log.wFatal(LogDef.id_0x10220005);
				Log.wException(LogDef.id_0x10120000, e);
			}
		}
	}

	/**
	 * 反注册通道与呼叫PORT之间的对应关系
	 * 
	 * @param channel
	 *            需要反注册的通道，如果为null，则无条件反注册。
	 * @param port
	 */
	void unregisterChannelOnPort(ATSChannel channel, int port)
	{
		if ((port < 0) || (port >= channelsInPort.length))
		{
			return;
		}

		ATSChannel oriChannel = channelsInPort[port];
		if (oriChannel != channel)
		{
			return;
		}

		channelsInPort[port] = null;
	}
}

class GarbageCollectionCallbackImpl implements GarbageCollectionCallback
{
	int timeoutMillis = 0;

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.comobj.sessiondata.GarbageCollectionCallback#isSessionExpired(java.lang.Object, long)
	 */
	public boolean isSessionExpired(Object msgData, long millisCreated)
	{
		if (System.currentTimeMillis() - millisCreated >= timeoutMillis)
		{
			if (msgData instanceof ATSBrowser)
			{
				ATSBrowser browser = (ATSBrowser) msgData;
				Log.wDebug("SessionManager 删除过期Browser会话，SessionId=%d", browser
						.getSessionId());
			}
			else if (msgData instanceof SleepTask)
			{
				SleepTask task = (SleepTask) msgData;
				Log.wDebug("SessionManager 删除过期SleepTask会话，SessionId=%d",
						task.sessionId);
			}

			return true;
		}

		return false;
	}
}
