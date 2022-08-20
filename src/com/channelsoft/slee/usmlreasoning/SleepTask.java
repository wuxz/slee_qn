/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.slee.channelmanager.Contact;
import com.channelsoft.slee.util.Constant;

/**
 * ��Ҫ�ں�ִ̨�е���������
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
	 * ���񴴽���ʱ�䡣
	 */
	public long createdMillis;

	public Contact curContact = null;

	public boolean isExpired = false;

	public int result = Constant.EVENT_No_Error;

	public long sessionId;

	/**
	 * ��ʱʱ������λΪ���롣
	 */
	public long timeout;

	/**
	 * ��ʱ��ʱ�̡�
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
