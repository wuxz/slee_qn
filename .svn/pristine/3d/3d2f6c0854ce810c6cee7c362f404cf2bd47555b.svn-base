/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author wuxz
 */
public class OAMClientRegisterMessage extends VGCPMessage
{
	String clientId = "";

	String pwd = "";

	int version;

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#readPackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#writePackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		messageId = VGCPConstants.VGCP_CMD_OAM_REGISTER;

		byte[] clientIdBuffer = clientId.getBytes();
		byte[] pwdBuffer = pwd.getBytes();
		int clientIdLength = calcStringLength(clientIdBuffer);
		int pwdLength = calcStringLength(pwdBuffer);

		messageLength = 4 * 4 + 4 * 3 + clientIdLength + pwdLength;

		super.writePackage(client);

		conn.writeInt(version);
		client.writeString(clientIdBuffer, clientIdLength);
		client.writeString(pwdBuffer, pwdLength);
	}
}
