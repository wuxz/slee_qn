package com.channelsoft.slee.perfmonitor;

import java.io.DataOutputStream;

public class ChannelDebugInfo extends PerfData
{
	public String channelDN; // 16 bytes

	public String debugInfo; // 256 bytes

	public void writePackage(DataOutputStream os) throws Exception
	{
		os.writeByte(2);
		os.writeInt(256);//
		writeString(os, debugInfo,256);
	}
}