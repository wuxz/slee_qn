package com.channelsoft.slee.snmpagent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import com.channelsoft.reusable.comobj.jmscom.JmsCom;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.channelmanager.ChannelManager;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

/**
 * @author liyan
 */
public class SnmpRelatedData
{

	private static SnmpRelatedData theInstance = null;

	public static SnmpRelatedData instance()
	{
		if (null == theInstance)
		{
			theInstance = new SnmpRelatedData();
		}
		return theInstance;
	}

	int channelOccupiedRate_Upper = 80;

	int highestOccupiedChannelCount;

	int serviceFailedCount;

	String serviceStartFile;// 启动文档

	String serviceStartParam;// 启动参数

	int serviceSucceedCount;

	String sleeCreateTime;

	int sleeFlag;

	String sleeIpAddr;

	String sleeLocation;

	String sleeName;

	int sleeWorkingState;

	int sleeWorkingType;

	int startServiceCount;

	int statisticID;

	int stopServiceCount;

	/**
	 * 取消指定的会话
	 * 
	 * @param sessionId
	 */
	public void cancelSession(String sessionId)
	{
		try
		{
			ChannelManager.cancelSession(sessionId);
		}
		catch (Exception e)
		{
			Log.wException(e);
		}
	}

	public int getChannelOccupiedRate_Upper()
	{
		return channelOccupiedRate_Upper;
	}

	/**
	 * 查看当前挂起的会话数
	 * 
	 * @return
	 */
	public int getCurHungSessionCount()
	{
		try
		{
			return (int) ChannelManager.getSessionSize();
		}
		catch (Exception e)
		{
			Log.wException(e);

			return 0;
		}
	}

	public int getHighestOccupiedChannelCount()
	{
		return highestOccupiedChannelCount;
	}

	public int getOccupiedChannelCount()
	{
		return SnmpAgentHandler.computeBusyChannel();
	}

	public int getServiceFailedCount()
	{
		return serviceFailedCount;
	}

	public int getServiceSucceedCount()
	{
		return serviceSucceedCount;
	}

	/**
	 * 获取指定会话信息
	 * 
	 * @param sessionId
	 * @param hungTime
	 *            挂起时间
	 * @param hungPos
	 *            挂起位置
	 */
	public void getSessionInfo(String sessionId, StringWrapper hungTime,
			StringWrapper hungPos)
	{
		try
		{
			hungTime.value = ""
					+ ChannelManager.getSessionCreatedMillis(sessionId);
			hungPos.value = ChannelManager.getSessionReasoningPos(sessionId);
		}
		catch (Exception e)
		{
			Log.wException(e);
		}
	}

	public String getSleeCreateTime()
	{
		return sleeCreateTime;
	}

	public int getSleeFlag()
	{
		return sleeFlag;
	}

	public String getSleeIpAddr()
	{
		return sleeIpAddr;
	}

	public String getSleeLocation()
	{
		return sleeLocation;
	}

	public String getSleeName()
	{
		return sleeName;
	}

	public String getSleeSoftVersion()
	{
		return UnifiedServiceManagement.versionInfo;
	}

	public String getSleeTimePrick()
	{
		return Variant.dateTimeFormat.format(Calendar.getInstance().getTime());
	}

	public int getSleeWorkingState()
	{
		return sleeWorkingState;
	}

	public int getSleeWorkingType()
	{
		return sleeWorkingType;
	}

	public String getStartFile()
	{
		return serviceStartFile;
	}

	public String getStartParam()
	{
		return serviceStartParam;
	}

	public int getStartServiceCount()
	{
		return startServiceCount;
	}

	public int getStatisticID()
	{
		return statisticID;
	}

	public int getStopServiceCount()
	{
		return stopServiceCount;
	}

	public int getTotalChannelCount()
	{
		return UnifiedServiceManagement.channelManager.channels.size();
	}

	/**
	 * 强制指定的会话超时
	 * 
	 * @param sessionId
	 */
	public void makeSessionTimeout(String sessionId)
	{
		try
		{
			ChannelManager.forceSessionTimeout(sessionId);
		}
		catch (Exception e)
		{
			Log.wException(e);
		}
	}

	/**
	 * 给指定的会话发消息
	 * 
	 * @param sessionId
	 * @param msg
	 *            消息
	 */
	public void sendMsgToSession(String sessionId, String msg)
	{
		try
		{
			if (ChannelManager.getSessionCreatedMillis(sessionId) == 0)
			{
				return;
			}

			String destination = UnifiedServiceManagement.configData
					.getInternalQueueName();
			int timeout = UnifiedServiceManagement.configData
					.getDefaultMessageTTL();

			JmsCom jmsCom = new JmsCom();

			jmsCom.setComponentContext(UnifiedServiceManagement.comContext);

			jmsCom.invoke("sendP2PMsg", new Variant[] { new Variant(msg, null),
					new Variant(destination, null), new Variant("JMSCorrelationID", null),
					new Variant(sessionId, null), new Variant(false, null),
					new Variant(timeout, null) });
		}
		catch (Exception e)
		{
			Log.wException(e);
		}
	}

	public void setChannelOccupiedRate_Upper(int channelOccupiedRate_Upper)
	{
		this.channelOccupiedRate_Upper = channelOccupiedRate_Upper;
	}

	public void setHighestOccupiedChannelCount(int highestOccupiedChannelCount)
	{
		this.highestOccupiedChannelCount = highestOccupiedChannelCount;
	}

	public void setServiceFailedCount(int serviceFailedCount)
	{
		this.serviceFailedCount = serviceFailedCount;
	}

	public void setServiceSucceedCount(int serviceSucceedCount)
	{
		this.serviceSucceedCount = serviceSucceedCount;
	}

	public void setStartFile(String serviceStartFile)
	{
		this.serviceStartFile = serviceStartFile;
	}

	public void setStartParam(String serviceStartParam)
	{
		this.serviceStartParam = serviceStartParam;
	}

	public void setStartServiceCount(int startServiceCount)
	{
		this.startServiceCount = startServiceCount;
	}

	public void setStatisticID(int statisticID)
	{
		this.statisticID = statisticID;
	}

	public void setStopServiceCount(int stopServiceCount)
	{
		this.stopServiceCount = stopServiceCount;
	}

	public void setSysCfgData(ISysCfgData sysCfgData)
	{
		this.sleeCreateTime = Variant.dateTimeFormat.format(Calendar
				.getInstance().getTime());
		this.sleeWorkingType = 2;
		this.sleeWorkingState = 1;
		this.sleeFlag = sysCfgData.getSnmpNodeId();
		this.sleeLocation = sysCfgData.getSnmpNodeLocation();
		this.sleeName = sysCfgData.getSnmpNodeName();

		try
		{
			this.sleeIpAddr = InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e)
		{
			Log.wException(LogDef.id_0x10110001, e);
		}
	}
}
