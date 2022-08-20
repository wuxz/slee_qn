package com.channelsoft.slee.channelmanager;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class SgEventMonitor extends Thread
{
	@Override
	public void run()
	{
		setName("SGEventMonitor");

		AtsSGEvent msg = new AtsSGEvent();

		while (true)
		{
			try
			{
				msg = UnifiedServiceManagement.callAgent.atsGetSGEvent();
				if (msg == null)
				{
					// Log.wDebug("有60秒没有收到事件了。");
					continue;
				}

				ChannelManager.sgEventQueue.add(msg);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10120002, e);
			}
		}
	}
}
