/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author wuxz
 *         <p>
 *         ������Ϣ�ĸ��ࡣ��ΪACK��Ϣ�븸����ȫ��ͬ���ʸ��಻���Ϊ�����ࡣ
 */
public class VGCPMessage implements PackageMessage
{
	static int invokeIdSeed = 0;

	static int VGCP_HEADER_LENGTH = 4 * 4;

	/**
	 * �����ַ����Ķ���󳤶ȡ�
	 * 
	 * @param buffer
	 *            �ַ���������
	 * @return �����ĳ���
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
	 * ����һ���������ַ���
	 * 
	 * @param conn
	 * @return �������ַ���
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
