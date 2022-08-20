package com.channelsoft.slee.callagent.ice;

import com.channelsoft.VGProxy.SGEventHolder;
import com.channelsoft.reusable.icemonitor.IIceConnectionMonitorCallback;
import com.channelsoft.reusable.icemonitor.IceConnectionMonitor;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_SGEventMessage;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class V2_SGEventReceiver extends Thread implements
		IIceConnectionMonitorCallback
{
	IceConnectionMonitor icMonitor = null;

	V2_VGProxyImp vgProxyImp = null;

	public V2_SGEventReceiver(V2_VGProxyImp vgProxy)
	{
		this.vgProxyImp = vgProxy;
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

		V2_VGProxyImp.vgproxy = null;
	}

	private void reConnectVGProxy()
	{
		try
		{
			vgProxyImp.initialize();
		}
		catch (Throwable ee)
		{
			Log.wException(LogDef.id_0x10150002, ee);
			V2_VGProxyImp.vgproxy = null;
		}

		registerMonitor();
	}

	private void registerMonitor()
	{
		if (icMonitor == null)
		{
			icMonitor = new IceConnectionMonitor();
		}

		if (V2_VGProxyImp.vgproxy != null)
		{
			try
			{
				icMonitor.register(V2_VGProxyImp.vgproxy, this);
			}
			catch (Exception e)
			{
				V2_VGProxyImp.vgproxy = null;
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
				if (!vgProxyImp.isInitialized())
				{
					Thread.sleep(5000);

					reConnectVGProxy();

					continue;
				}

				int result = vgProxyImp.GetSgEvt(200, event);
				if (result == Constant.GATEWAY_SUCCESS)
				{
					Log.wDebug(LogDef.id_0x10050013, event.value.ResID,
							event.value.CallID, event.value.EventID);
					V2_CallAgentImpl.dispatchMessage(new V2_SGEventMessage(
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
				catch (InterruptedException e1)
				{
				}
			}
		}
	}
}
