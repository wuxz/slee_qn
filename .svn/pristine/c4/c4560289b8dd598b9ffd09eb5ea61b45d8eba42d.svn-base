package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

public class OAMGetClientStateReply extends VGCPMessage
{
	boolean bConnected; // #网络是否联通

	boolean bRegisted; // #是否已经成功注册

	boolean bHold; // #是否Hold状态

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#readPackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		bConnected = readBoolean(conn);
		bRegisted = readBoolean(conn);
		bHold = readBoolean(conn);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#writePackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

}
