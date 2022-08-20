/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 *
 */
public class RMBlindCloseConfMessage extends VGCPMessage
{
	int confId;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		messageId = VGCPConstants.VGCP_CMD_RM_BLINDCLOSECONF;
		messageLength = 4 * 4 + 4;

		super.writePackage(conn);
		conn.writeInt(confId);
	}

}
