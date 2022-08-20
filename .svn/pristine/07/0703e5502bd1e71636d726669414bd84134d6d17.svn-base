/**
 * 
 */
package com.channelsoft.slee.callagent.ice;

import com.channelsoft.VGProxy.SGEventHolder;
import com.channelsoft.reusable.icemonitor.IIceConnectionMonitorCallback;
import com.channelsoft.reusable.icemonitor.IceConnectionMonitor;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

/**
 * @author wuxz
 */
public class SGEventReceiver extends Thread implements
		IIceConnectionMonitorCallback
{
	CallAgentImpl callAgent = null;

	IceConnectionMonitor icMonitor = null;

	/**
	 * 
	 */
	public SGEventReceiver(CallAgentImpl callAgent)
	{
		this.callAgent = callAgent;
		registerMonitor();
	}

	public void notifyConnectionErr()
	{
		Log.wError(LogDef.id_0x10150018);
		if (UnifiedServiceManagement.hasSnmpAgent)
		{
			SnmpAgentHandler.instance().addTrapData("CallAgent与VGProxy的连接出现错误",
					1);
		}

		CallAgentImpl.vgproxy = null;
	}

	private void reConnectVGProxy()
	{
		try
		{
			callAgent.initialize();
		}
		catch (Throwable ee)
		{
			Log.wException(LogDef.id_0x10150002, ee);
			CallAgentImpl.vgproxy = null;
		}

		registerMonitor();
	}

	private void registerMonitor()
	{
		if (icMonitor == null)
		{
			icMonitor = new IceConnectionMonitor();
		}

		if (CallAgentImpl.vgproxy != null)
		{
			try
			{
				icMonitor.register(CallAgentImpl.vgproxy, this);
			}
			catch (Exception e)
			{
				CallAgentImpl.vgproxy = null;
			}
		}
	}

	@Override
	public void run()
	{
		setName("CallAgent4VGProxy SGEventReceiver");

		// 如果取事件出现异常，则认为VGProxy已关闭。进行重新连接。
		SGEventHolder event = new SGEventHolder();

		while (true)
		{
			try
			{
				if (CallAgentImpl.vgproxy == null)
				{
					Thread.sleep(5000);

					reConnectVGProxy();

					continue;
				}

				int result = CallAgentImpl.vgproxy.GetSgEvt(200, event);
				if (result == Constant.GATEWAY_SUCCESS)
				{
					Log.wDebug(LogDef.id_0x10050013, event.value.ResID,
							event.value.CallID, event.value.EventID);
					callAgent.dispatchMessage(new SGEventMessage(event.value));
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
				catch (InterruptedException e1)
				{
				}
			}
		}
	}
}
