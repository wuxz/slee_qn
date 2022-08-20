package com.channelsoft.slee.callagent.ice;

import com.channelsoft.VGProxy.MGEventHolder;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_MGEventMessage;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

public class V2_MGEventReceiver extends Thread
{
	V2_VGProxyImp vgProxyImp = null;

	public V2_MGEventReceiver(V2_VGProxyImp vgProxy)
	{
		this.vgProxyImp = vgProxy;
	}

	@Override
	public void run()
	{
		setName("CallAgent4VGProxy MGEventReceiver");

		// 如果取事件出现异常，则认为VGProxy已关闭。因为SG事件的收取队列会进行重新连接，所以这里不需要重新连接。
		MGEventHolder event = new MGEventHolder();

		while (true)
		{
			try
			{
				if (!vgProxyImp.isInitialized())
				{
					Thread.sleep(1000);
					continue;
				}

				int result = vgProxyImp.GetMgEvt(200, event);
				if (result == Constant.GATEWAY_SUCCESS)
				{
					Log.wDebug(LogDef.id_0x10050012, event.value.ResID,
							event.value.CallID, event.value.EventID,
							event.value.Reason);
					V2_CallAgentImpl.dispatchMessage(new V2_MGEventMessage(
							event.value));
				}
				else
				{
					Thread.sleep(300);
				}
			}
			catch (Throwable e)
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (InterruptedException ie)
				{
				}
			}
		}
	}
}
