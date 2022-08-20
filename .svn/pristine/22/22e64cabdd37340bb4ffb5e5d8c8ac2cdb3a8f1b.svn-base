/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGIOPara implements PackageMessage
{
	int keyLength = -1; // 最大收键个数

	int firstKeyTimeout = -1; // 首键超时时长

	int betweenKeyTimeout = -1; // 键间超时

	int silenceTime = -1; // 操作静音时长

	int IDDTime = -1; // 录音静音超时

	int timelength = -1; // 操作时长，如录音时长、收键总时长

	String breakKeys = ""; // 打断键列表

	private byte[] breakKeysBuffer = null;

	private int breakKeysLength;

	public void readPackage(TCPConnection conn) throws Exception
	{
	}

	public void writePackage(TCPConnection conn) throws Exception
	{
		parse();

		SSR2Client client = (SSR2Client) conn;

		conn.writeInt(keyLength);
		conn.writeInt(firstKeyTimeout);
		conn.writeInt(betweenKeyTimeout);
		conn.writeInt(silenceTime);
		conn.writeInt(IDDTime);
		conn.writeInt(timelength);
		if (breakKeysBuffer != null)
		{
			client.writeString(breakKeysBuffer, breakKeysLength);
		}
	}

	public int length()
	{
		parse();
		return 7 * 4 + breakKeysLength;
	}

	private void parse()
	{
		if (breakKeysBuffer == null)
		{
			breakKeys = (breakKeys == null)?"":breakKeys;
			breakKeysBuffer = breakKeys.getBytes();
			breakKeysLength = VGCPMessage.calcStringLength(breakKeysBuffer);
		}
	}

}
