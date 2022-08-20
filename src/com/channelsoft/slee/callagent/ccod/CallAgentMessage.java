package com.channelsoft.slee.callagent.ccod;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.slee.util.Constant;

public abstract class CallAgentMessage
{
	int messageType;

	CallAgentMessage()
	{

	}
}

class MGEventMessage extends CallAgentMessage
{
	MGEvent mgEvent;

	MGEventMessage()
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT;
	}

	MGEventMessage(MGEvent event)
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT;
		mgEvent = event;
	}
}

class SGEventMessage extends CallAgentMessage
{
	SGEvent sgEvent;

	int timestamp;

	SGEventMessage()
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT;
	}

	SGEventMessage(SGEvent event)
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT;
		sgEvent = event;
	}
}
