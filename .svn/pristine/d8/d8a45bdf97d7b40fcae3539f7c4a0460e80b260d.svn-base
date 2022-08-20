/**
 * 
 */
package com.channelsoft.slee.channelmanager;

import java.util.concurrent.LinkedBlockingQueue;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

/**
 * @author wuxz
 */
public class AppMessageProcessor extends Thread
{
	public static LinkedBlockingQueue<AppMessage> msgs = new LinkedBlockingQueue<AppMessage>(
			100);

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		setName("AppMessageProcessor");

		while (true)
		{
			try
			{
				AppMessage msg = msgs.take();

				switch (msg.msgId)
				{
				case Constant.EVENT_ResumeService:
				{
					Log.wTrace(
							"AppMessageProcessor接收到ResumeService事件, 会话id:%d。",
							msg.sessionId);

					ATSChannel pChannel = UnifiedServiceManagement.channelManager
							.getAppChannel(true);
					pChannel.resumeService(msg.sessionId);

					Log
							.wTrace(
									"AppMessageProcessor处理ResumeService事件, 会话id:%d。 分配ChannelDN=%s",
									msg.sessionId, pChannel.channelDn);

					break;
				}

				default:
				{
					Log.wError(LogDef.id_0x1015000D, msg.msgId);

					break;
				}

				}
			}
			catch (Throwable e)
			{
				Log.wException(e);
			}
		}
	}
}
