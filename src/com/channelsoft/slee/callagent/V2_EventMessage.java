package com.channelsoft.slee.callagent;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;

/**
 * ���ز�������Ϣ��
 * 
 * @author wuxz
 */
public abstract class V2_EventMessage
{
	public int messageType;

	public V2_EventMessage()
	{

	}

	public MGEvent getMgEvent()
	{
		return null;
	}

	public SGEvent getSgEvent()
	{
		return null;
	}
}
