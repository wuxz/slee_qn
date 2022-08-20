package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

public class OAMGetClientStateReply extends VGCPMessage
{
	boolean bConnected; // #�����Ƿ���ͨ

	boolean bRegisted; // #�Ƿ��Ѿ��ɹ�ע��

	boolean bHold; // #�Ƿ�Hold״̬

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
