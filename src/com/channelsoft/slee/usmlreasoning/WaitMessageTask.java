/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

/**
 * @author wuxz
 */
public class WaitMessageTask extends SleepTask
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5570538467334079199L;

	/**
	 * 取到的消息。
	 */
	public String content = "";

	WaitMessageTask(long timeout)
	{
		super(timeout);
	}
}
