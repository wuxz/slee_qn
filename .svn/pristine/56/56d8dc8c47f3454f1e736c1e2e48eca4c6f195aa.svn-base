/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 *
 */
public class RMBlindCreateConfReply extends VGCPMessage
{

	int confId;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		confId = conn.readInt();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

}
