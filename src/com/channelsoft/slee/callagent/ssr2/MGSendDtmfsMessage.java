/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGSendDtmfsMessage extends VGCPMessage
{
	int resId;

	int callId;

	String dtmfs = "";

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		messageId = VGCPConstants.VGCP_CMD_MG_SENDDTMFS;

		byte[] dtmfsBuffer = dtmfs.getBytes();
		int dtmfsLength = calcStringLength(dtmfsBuffer);

		messageLength = 4 * 4 + 3 * 4 + dtmfsLength;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		client.writeString(dtmfsBuffer, dtmfsLength);
	}

}
