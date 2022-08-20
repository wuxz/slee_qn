/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGAdjustSpeedMessage extends VGCPMessage
{
	int resId;

	int callId;

	int adjment;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		messageId = VGCPConstants.VGCP_CMD_MG_ADJSPEED;
		messageLength = 4 * 4 + 3 * 4;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		conn.writeInt(adjment);
	}
}
