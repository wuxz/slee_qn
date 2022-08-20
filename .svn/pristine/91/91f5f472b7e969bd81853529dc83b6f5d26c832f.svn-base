/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author wuxz
 *         <p>
 *         所有消息的父类。因为ACK消息与父类完全相同，故父类不设计为抽象类。
 */
public class VGCPMessage implements PackageMessage
{
	static int invokeIdSeed = 0;

	static int VGCP_HEADER_LENGTH = 4 * 4;

	/**
	 * 计算字符串的对齐后长度。
	 * 
	 * @param buffer
	 *            字符串的数据
	 * @return 对齐后的长度
	 */
	static int calcStringLength(byte[] buffer)
	{
		int len = buffer.length % 4;
		if (len != 0)
		{
			return buffer.length / 4 * 4 + 4;
		}

		return buffer.length;
	}

	synchronized static int generateNewInvokeId()
	{
		invokeIdSeed++;
		if (invokeIdSeed > 0x00ffffff)
		{
			invokeIdSeed = 1;
		}

		return invokeIdSeed;
	}

	static boolean readBoolean(TCPConnection conn) throws Exception
	{
		return (conn.readInt() == 0 ? false : true);
	}

	/**
	 * 读出一个对齐后的字符串
	 * 
	 * @param conn
	 * @return 读出的字符串
	 * @throws Exception
	 */
	static String readString(TCPConnection conn) throws Exception
	{
		int stringLength = conn.readInt();
		if (stringLength == 0)
		{
			return "";
		}

		byte[] buffer = new byte[stringLength];
		conn.read(buffer);

		int i = buffer.length - 1;
		while (i >= 0)
		{
			if (buffer[i] != 0)
			{
				break;
			}

			i--;
		}

		return new String(buffer, 0, i + 1);
	}

	int invokeId;

	int messageId;

	int messageLength = VGCP_HEADER_LENGTH;

	int status;

	VGCPMessage()
	{
		invokeId = generateNewInvokeId();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.PackageMessage#readPackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	public void readPackage(TCPConnection conn) throws Exception
	{
		messageLength = conn.readInt();
		messageId = conn.readInt();
		invokeId = conn.readInt();
		status = conn.readInt();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.PackageMessage#writePackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	public void writePackage(TCPConnection conn) throws Exception
	{
		conn.writeInt(messageLength);
		conn.writeInt(messageId);
		conn.writeInt(invokeId);
		conn.writeInt(status);
	}
}
