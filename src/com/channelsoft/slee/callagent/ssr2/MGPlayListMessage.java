/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGPlayListMessage extends VGCPMessage
{
	int resId;

	int callId;

	MGIOPara iopParm;

	int rate;

	int voiceLib;

	VecStr files;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		messageId = VGCPConstants.VGCP_CMD_MG_PLAYLIST;

		messageLength = 4 * 4 + 2 * 4 + iopParm.length() + 2 * 4
				+ files.length();

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		iopParm.writePackage(conn);
		conn.writeInt(rate);
		conn.writeInt(voiceLib);
		files.writePackage(conn);
	}

}
