/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGReceiveDtmfsMessage extends VGCPMessage
{
	int resId;

	int callId;

	MGIOPara iopParm;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		messageId = VGCPConstants.VGCP_CMD_MG_RECEIVEDTMFS;
		messageLength = 4 * 4 + 2 * 4 + iopParm.length();

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		iopParm.writePackage(conn);
	}

}
