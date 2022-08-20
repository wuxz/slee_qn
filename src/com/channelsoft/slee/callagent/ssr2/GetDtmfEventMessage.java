/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class GetDtmfEventMessage extends EventMessage
{
	String dtmfs = "";

	@Override
	public MGEvent convert2MGEvent(MGEvent mgEvent)
	{
		super.convert2MGEvent(mgEvent);
		mgEvent.DTMFString = dtmfs;

		return mgEvent;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#readPackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		super.readPackage(conn);

		dtmfs = readString(conn);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#writePackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}
}
