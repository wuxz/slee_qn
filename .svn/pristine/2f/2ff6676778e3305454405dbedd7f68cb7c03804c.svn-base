/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.reusable.net.TCPConnection;
import com.channelsoft.slee.util.Constant;

/**
 * @author wuxz
 */
public class IncomingCallEventMessage extends EventMessage
{
	String called = "";

	String caller = "";

	String origCalled = "";

	String origCaller = "";

	int mediaType = Constant.VGCP_MEDIA_TYPE_AUDIO;
	
	String rfu = "";

	@Override
	public SGEvent convert2SGEvent(SGEvent sgEvent)
	{
		super.convert2SGEvent(sgEvent);

		sgEvent.CalledID = called;
		sgEvent.CallerID = caller;
		sgEvent.OriCalledID = origCalled;
		sgEvent.OriCallerID = origCaller;
		sgEvent.mediaType = mediaType;
		sgEvent.rfu = rfu;

		return sgEvent;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#readPackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		super.readPackage(conn);

		caller = readString(conn);
		called = readString(conn);
		origCaller = readString(conn);
		origCalled = readString(conn);
		mediaType = conn.readInt();
		rfu = readString(conn);
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
