/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.slee.channelmanager.Contact;
import com.channelsoft.slee.util.Constant;

/**
 * 需要在后台执行的休眠任务。
 * 
 * @author wuxz
 */
public class SleepTask implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4509208404636247188L;

	public int counter = 0;

	/**
	 * 任务创建的时间。
	 */
	public long createdMillis;

	public Contact curContact = null;

	public boolean isExpired = false;

	public int result = Constant.EVENT_No_Error;

	public long sessionId;

	/**
	 * 超时时长，单位为毫秒。
	 */
	public long timeout;

	/**
	 * 超时的时刻。
	 */
	// public long timeoutMillis;
	SleepTask()
	{
		createdMillis = System.currentTimeMillis();
	}

	SleepTask(long timeout)
	{
		this();
		this.timeout = timeout;
		// timeoutMillis = System.currentTimeMillis() + timeout;
	}
}
