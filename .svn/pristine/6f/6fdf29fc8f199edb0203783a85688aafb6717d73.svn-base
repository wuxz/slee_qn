/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;
import com.channelsoft.slee.util.Constant;

/**
 * @author dragon.huang
 */
public class SGBlindMakecallMessage extends VGCPMessage
{

	String caller = "";

	String called = "";

	String oriCaller = "";

	String oriCalled = "";

	int timeout;
	
	int mediaType = Constant.VGCP_MEDIA_TYPE_AUDIO;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		messageId = VGCPConstants.VGCP_CMD_SG_BLINDMAKECALL;

		byte[] callerBuffer = caller.getBytes();
		byte[] calledBuffer = called.getBytes();
		byte[] oriCallerBuffer = oriCaller.getBytes();
		byte[] oriCalledBuffer = oriCalled.getBytes();
		int callerLength = calcStringLength(callerBuffer);
		int calledLength = calcStringLength(calledBuffer);
		int oriCallerLength = calcStringLength(oriCallerBuffer);
		int oriCalledLength = calcStringLength(oriCalledBuffer);

		messageLength = 4 * 4 + 6 * 4 + callerLength + calledLength + oriCallerLength
				+ oriCalledLength;

		super.writePackage(conn);

		client.writeString(callerBuffer, callerLength);
		client.writeString(calledBuffer, calledLength);
		client.writeString(oriCallerBuffer, oriCallerLength);
		client.writeString(oriCalledBuffer, oriCalledLength);
		conn.writeInt(timeout);
		conn.writeInt(mediaType);
	}
}
