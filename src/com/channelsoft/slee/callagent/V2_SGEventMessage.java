package com.channelsoft.slee.callagent;

import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.slee.util.Constant;

public class V2_SGEventMessage extends V2_EventMessage
{
	public SGEvent sgEvent;

	public int timestamp;

	public V2_SGEventMessage()
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT;
	}

	public V2_SGEventMessage(SGEvent event)
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT;
		sgEvent = event;
	}

	@Override
	public SGEvent getSgEvent()
	{
		return sgEvent;
	}
}
