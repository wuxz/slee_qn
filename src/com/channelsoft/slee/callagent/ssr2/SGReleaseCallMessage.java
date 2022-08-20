/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author wuxz
 */
public class SGReleaseCallMessage extends VGCPMessage
{
	int callId;

	int reason = VGCPConstants.VGCP_RELEASE_NORMAL;

	int resId;

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
		messageId = VGCPConstants.VGCP_CMD_SG_RELEASECALL;

		messageLength = 4 * 4 + 3 * 4;

		super.writePackage(conn);

		conn.writeInt(resId);
		conn.writeInt(callId);
		conn.writeInt(reason);
	}
}
