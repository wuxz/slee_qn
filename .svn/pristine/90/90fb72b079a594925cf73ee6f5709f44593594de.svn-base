package com.channelsoft.slee.perfmonitor;

import java.io.DataOutputStream;

public class ChannelStatusStatistic extends PerfData
{
	public int channelWorkingCount; // 4 bytes

	public void writePackage(DataOutputStream os) throws Exception
	{
		os.writeByte(5);
		os.writeInt(4);//
		os.writeInt(channelWorkingCount);
	}
}
