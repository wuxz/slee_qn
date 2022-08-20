/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGPlayTTSMessage extends VGCPMessage
{
	int resId;

	int callId;

	MGIOPara iopParm;

	int type;

	String text;

	int rate;

	int voiceLib;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		messageId = VGCPConstants.VGCP_CMD_MG_PLAYTTS;

		byte[] textBuffer = text.getBytes();
		int textLength = calcStringLength(textBuffer);
		messageLength = 4 * 4 + 2 * 4 + iopParm.length() + 4 * 4 + textLength;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		iopParm.writePackage(conn);
		conn.writeInt(type);
		client.writeString(textBuffer, textLength);
		conn.writeInt(rate);
		conn.writeInt(voiceLib);

	}

}
