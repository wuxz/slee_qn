/**
 * 
 */
package com.channelsoft.slee.channelmanager;

import java.util.HashMap;
import java.util.Vector;

import javax.jms.TextMessage;

import com.channelsoft.reusable.comobj.jmscom.JmsCom;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

/**
 * @author wuxz
 */
public class QueueMessageReceiver extends Thread
{
	public static HashMap<Long, Vector<String>> pendingMsgs = new HashMap<Long, Vector<String>>();

	public static String getMessage(long sessionId)
	{
		synchronized (pendingMsgs)
		{
			Vector<String> msgs = pendingMsgs.get(sessionId);
			if ((msgs == null) || (msgs.size() == 0))
			{
				return null;
			}

			return msgs.remove(0);
		}
	}

	public static void processMessage(long sessionId, String content)
			throws InterruptedException
	{
		if (sessionId <= 0)
		{
			Log.wError("QueueMessageReceiver 收到无效的消息，丢弃");

			return;
		}

		if (ServiceTimerTask.processMessage(sessionId, content))
		{
			Log.wDebug(LogDef.id_0x10020000_31, sessionId);
			UnifiedServiceManagement.channelManager.resumeChannel(sessionId);
		}
		else
		{
			synchronized (pendingMsgs)
			{
				Vector<String> msgs = pendingMsgs.get(sessionId);
				if (msgs == null)
				{
					msgs = new Vector<String>();
					pendingMsgs.put(sessionId, msgs);
				}

				msgs.add(content);
			}

			Log.wDebug(LogDef.id_0x10020000_32, sessionId);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		setName("QueueMessageReceiver");

		JmsCom jmsCom = new JmsCom();
		jmsCom.setComponentContext(UnifiedServiceManagement.comContext);

		String queueName = UnifiedServiceManagement.configData
				.getInternalQueueName();

		while (true)
		{
			try
			{
				TextMessage msg = jmsCom.receiveInternalMsg(queueName);
				if (msg != null)
				{
					Log.wDebug(LogDef.id_0x10020000_33, msg
							.getJMSCorrelationID());
					processMessage(Long.parseLong(msg.getJMSCorrelationID()),
							msg.getText());
				}
			}
			catch (Throwable e)
			{
				Log.wException(e);
			}
		}
	}
}
