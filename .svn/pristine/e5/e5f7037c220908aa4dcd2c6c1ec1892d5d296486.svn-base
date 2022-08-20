package com.channelsoft.slee.perfmonitor;

import java.io.DataOutputStream;

public class PerfData
{
	static byte bufferZero[] = new byte[2048];
	static
	{
		for (int i = 0; i < bufferZero.length; i ++)
		{
			bufferZero[i] = 0;
		}
	}
	
	void writePackage(DataOutputStream os) throws Exception
	{

	}

	public static int writeString(DataOutputStream os, String value, int maxLength)
			throws Exception
	{
		int bufferLen = 0;
		byte data[] = null;
		if (value != null)
		{
			data = value.getBytes();
			bufferLen = data.length;
		}
		
		if (maxLength == -1)
		{
			maxLength = bufferLen + 1;
		}

		maxLength -= 1;
		int lengthToWrite = (bufferLen > maxLength ? maxLength : bufferLen);
		if (lengthToWrite > 0)
		{
			os.write(data, 0, lengthToWrite);
		}
		
		int lengthLeft = maxLength - lengthToWrite + 1;
		if (lengthLeft <= bufferZero.length)
		{
			os.write(bufferZero, 0, lengthLeft);
		}
		else
		{
			for (int i = 0; i < lengthLeft; i++)
			{
				os.writeByte(0);
			}
		}
		
		return maxLength + 1;
	}
}
