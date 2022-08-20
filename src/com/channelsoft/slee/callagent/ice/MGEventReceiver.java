/**
 * 
 */
package com.channelsoft.slee.callagent.ice;

import com.channelsoft.VGProxy.MGEventHolder;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

/**
 * @author wuxz
 */
public class MGEventReceiver extends Thread
{
	CallAgentImpl callAgent = null;

	/**
	 * 
	 */
	public MGEventReceiver(CallAgentImpl callAgent)
	{
		this.callAgent = callAgent;
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
				if (CallAgentImpl.vgproxy == null)
				{
					Thread.sleep(1000);
					continue;
				}

				int result = CallAgentImpl.vgproxy.GetMgEvt(200, event);
				if (result == Constant.GATEWAY_SUCCESS)
				{
					Log.wDebug(LogDef.id_0x10050012, event.value.ResID,
							event.value.CallID, event.value.EventID,
							event.value.Reason);
					callAgent.dispatchMessage(new MGEventMessage(event.value));
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
