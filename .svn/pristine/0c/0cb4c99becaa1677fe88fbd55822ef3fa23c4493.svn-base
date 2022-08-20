package com.channelsoft.slee.callagent;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.slee.util.Constant;

public class V2_MGEventMessage extends V2_EventMessage
{
	public MGEvent mgEvent;

	public V2_MGEventMessage()
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT;
	}

	public V2_MGEventMessage(MGEvent event)
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT;
		mgEvent = event;
	}

	@Override
	public MGEvent getMgEvent()
	{
		return mgEvent;
	}
}
