package com.channelsoft.slee.perfmonitor;

import java.io.*;

public class LogErrorInfo extends PerfData
{
	public String content; // 256 bytes

	void writePackage(DataOutputStream os) throws Exception
	{
		os.writeByte(3);
		os.writeInt(256);
		writeString(os, content, 256);
	}
}
