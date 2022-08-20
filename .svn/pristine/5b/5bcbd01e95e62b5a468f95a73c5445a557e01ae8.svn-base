package com.channelsoft.slee.callagent;

/**
 * 呼叫信息统计
 * 
 * @author wuxz
 */
public class V2_CallStat
{
	protected static V2_CallStat callStat;

	public static void addEvent(int callId, int port, int timestamp,
			String ani, String dnis, int eventType)
	{
		if (callStat != null)
		{
			callStat.addEventI(callId, port, timestamp, ani, dnis, eventType);
		}
	}

	public static void addEvent(int callId, int port, V2_EventMessage msg,
			String ani, String dnis, int eventType)
	{
		if (callStat != null)
		{
			callStat.addEventI(callId, port, msg, ani, dnis, eventType);
		}
	}

	protected void addEventI(int callId, int port, int timestamp, String ani,
			String dnis, int eventType)
	{
	}

	protected void addEventI(int callId, int port, V2_EventMessage msg,
			String ani, String dnis, int eventType)
	{
	}
}
