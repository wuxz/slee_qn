package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

public class OAMGetClientStateMessage extends VGCPMessage
{
	String clientId = "";  //#如果为空，表示当前的链路
	
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

		messageId = VGCPConstants.VGCP_CMD_OAM_GETCLIENTSTATE;

		byte[] clientIdBuffer = clientId.getBytes();
		int clientIdLength = calcStringLength(clientIdBuffer);

		messageLength = 4 * 4 + 4 + clientIdLength;

		super.writePackage(client);

		client.writeString(clientIdBuffer, clientIdLength);
	}

}
