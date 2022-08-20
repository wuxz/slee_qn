/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 *
 */
public class RMBlindCreateConfMessage extends VGCPMessage
{
	int resId1;

	int resId2;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		messageId = VGCPConstants.VGCP_CMD_RM_BLINDCREATECONF;
		messageLength = 4 * 4 + 2 * 4;

		super.writePackage(conn);
		conn.writeInt(resId1);
		conn.writeInt(resId2);
	}

}
