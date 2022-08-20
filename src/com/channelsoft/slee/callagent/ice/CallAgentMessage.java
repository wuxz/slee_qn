package com.channelsoft.slee.callagent.ice;

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

class SGEventMessage extends CallAgentMessage
{
	SGEvent sgEvent;
	
	SGEventMessage(SGEvent event)
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT;
		sgEvent = event;
	}
}

class MGEventMessage extends CallAgentMessage
{
	MGEvent mgEvent;
	
	MGEventMessage(MGEvent event)
	{
		messageType = Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT;
		mgEvent = event;
	}
}
