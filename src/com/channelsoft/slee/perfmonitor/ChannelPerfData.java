package com.channelsoft.slee.perfmonitor;

import java.io.DataOutputStream;
import com.channelsoft.slee.unifiedservicemanagement.*;

class ChannelStatus extends PerfData
{
	public String channelDN; // 16 bytes

	public int channelDNNum;

	int status; // 4 bytes

	void writePackage(DataOutputStream os) throws Exception
	{
		os.writeByte(0);
		os.writeInt(20);
		writeString(os, channelDN, 16);
		os.writeInt(status);
	}
}

public class ChannelPerfData extends ChannelStatus
{
	String serviceName; // 128 bytes

	String workflowName; // 128 bytes

	String nodeName; // 128 bytes

	String ioProcess; // 128 bytes

	String ani; // 32 bytes

	String dnis; // 32 bytes

	int lastStartTime;

	int lastStopTime;

	int persistSeconds;

	byte succeeded;

	int totalCount;

	int totalBusyTime;

	int totalIdleTime;

	int totalFail;

	void writePackage(DataOutputStream os) throws Exception
	{
		os.writeByte(1);
		os.writeInt(625);
		writeString(os, channelDN, 16);
		os.writeInt(status);
		writeString(os, serviceName, 128);
		writeString(os, workflowName, 128);
		writeString(os, nodeName, 128);
		writeString(os, ioProcess, 128);
		writeString(os, ani, 32);
		writeString(os, dnis, 32);
		os.writeInt(lastStartTime);
		os.writeInt(lastStopTime);
		os.writeInt(persistSeconds);
		os.writeByte(succeeded);
		os.writeInt(totalCount);
		os.writeInt(totalBusyTime);
		os.writeInt(totalIdleTime);
		os.writeInt(totalFail);
	}

	public ChannelPerfData()
	{
		status = 0;
		ani = null;
		channelDN = null;
		dnis = null;
		serviceName = null;
		workflowName = null;
		nodeName = null;
		ioProcess = null;
		totalCount = 0;
		totalBusyTime = 0;
		totalIdleTime = 0;
		totalFail = 0;
		lastStartTime = 0;
		lastStopTime = 0;
		persistSeconds = 0;
		succeeded = 0;
	}

	public String getAni()
	{
		return ani;
	}

	public void setStatus(int newStatus)
	{
		if ((status == 0) && (newStatus != 0))
		{// from Idle to busy or working
			totalCount++;
			succeeded = 1;// 服务初始化为执行成功；
		}

		if ((status != 0) && (newStatus == 0))
		{// from busy or working to Idle

			workflowName = null;
			nodeName = null;
			ioProcess = null;
			ani=null;
			dnis=null;
			serviceName=null;
			
		}
		status = newStatus;
	}

	public void setAni(String ani)
	{
		this.ani = ani;
	}

	public String getDnis()
	{
		return dnis;
	}

	public void setDnis(String dnis)
	{
		this.dnis = dnis;
	}

	public String getIoProcess()
	{
		return ioProcess;
	}

	public void setIoProcess(String ioProcess)
	{
		this.ioProcess = ioProcess;
	}

	public int getLastStartTime()
	{
		return lastStartTime;
	}

	public void setLastStartTime(int lastStartTime)
	{
		this.lastStartTime = lastStartTime;
	}

	public int getLastStopTime()
	{
		return lastStopTime;
	}
	public int getStatus()
	{
		return status;
	}

	public void setLastStopTime(int lastStopTime)
	{
		this.lastStopTime = lastStopTime;
		persistSeconds = lastStopTime - lastStartTime;
	}

	public String getNodeName()
	{
		return nodeName;
	}

	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}

	public int getPersistSeconds()
	{
		return persistSeconds;
	}

	public void setPersistSeconds(int persistSeconds)
	{
		this.persistSeconds = persistSeconds;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName.substring(serviceName.lastIndexOf("\\")+1);
	}

	public byte getSucceeded()
	{
		return succeeded;
	}

	public void setSucceeded(byte succeeded)
	{
		this.succeeded = succeeded;
	}

	public int getTotalBusyTime()
	{
		return totalBusyTime;
	}

	public void setTotalBusyTime(int totalBusyTime)
	{
		this.totalBusyTime = totalBusyTime;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}

	public int getTotalFail()
	{
		return totalFail;
	}

	public void setTotalFail(int totalFail)
	{
		this.totalFail = totalFail;
	}

	public int getTotalIdleTime()
	{
		return totalIdleTime;
	}

	public void setTotalIdleTime(int totalIdleTime)
	{
		this.totalIdleTime = totalIdleTime;
	}

	public String getWorkflowName()
	{
		return workflowName;
	}

	public void setWorkflowName(String workflowName)
	{
		this.workflowName = workflowName;
	}

	public void setExecFail()
	{
		succeeded = 0;
		totalFail++;
	}
}