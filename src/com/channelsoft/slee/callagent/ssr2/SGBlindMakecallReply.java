/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class SGBlindMakecallReply extends VGCPMessage
{

	int resId;

	int callId;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		resId = conn.readInt();
		callId = conn.readInt();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}
}
