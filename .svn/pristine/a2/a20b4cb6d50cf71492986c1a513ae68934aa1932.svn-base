/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class VecStr implements PackageMessage
{

	String[] vecStr;

	private byte[][] vecStrBuffer;

	private int[] vecStrLength;

	public VecStr(String[] str)
	{
		vecStr = str;
	}

	public void readPackage(TCPConnection conn) throws Exception
	{
	}

	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		parse();

		if (vecStr == null || vecStr.length == 0)
		{
			conn.writeInt(0);
			return;
		}
		else
		{
			conn.writeInt(vecStr.length);
		}

		for (int i = 0; i < vecStr.length; i++)
		{
			client.writeString(vecStrBuffer[i], vecStrLength[i]);
		}
	}

	public int length()
	{
		parse();

		int length = 4;

		if (vecStr == null || vecStr.length == 0)
		{
			return length;
		}

		for (int i = 0; i < vecStrLength.length; i++)
		{
			length += 4 + vecStrLength[i];
		}
		return length;
	}

	private void parse()
	{
		if (vecStr == null || vecStr.length == 0)
		{
			return;
		}

		if (vecStrBuffer == null)
		{
			vecStrBuffer = new byte[vecStr.length][];
			vecStrLength = new int[vecStr.length];
			for (int i = 0; i < vecStr.length; i++)
			{
				vecStrBuffer[i] = vecStr[i].getBytes();
				vecStrLength[i] = VGCPMessage.calcStringLength(vecStrBuffer[i]);
			}
		}
	}
}
