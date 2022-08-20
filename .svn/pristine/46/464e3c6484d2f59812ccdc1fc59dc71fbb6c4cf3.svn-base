/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGPlayFileMessage extends VGCPMessage
{
	int resId;

	int callId;

	String filename = "";

	MGIOPara iopParm;

	int rate;

	int begintime;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		messageId = VGCPConstants.VGCP_CMD_MG_PLAYFILE;

		byte[] filenameBuffer = filename.getBytes();
		int filenameLength = calcStringLength(filenameBuffer);
		messageLength = 4 * 4 + 3 * 4 + filenameLength + iopParm.length() + 2
				* 4;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		client.writeString(filenameBuffer, filenameLength);
		iopParm.writePackage(conn);
		conn.writeInt(rate);
		conn.writeInt(begintime);

	}

}
