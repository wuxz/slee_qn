package com.channelsoft.slee.callagent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.slee.util.Constant;

/**
 * 保存未完成的会话。
 * 
 * @author wuxz
 */
public class V2_CallSession
{
	/**
	 * （CCOD:）CallId到CallAgentImpl实例的映射关系。 </p>
	 * （ICE:）resId到CallAgentImpl实例的映射关系。
	 */
	public static Map<Integer, CallAgent> callAgentMap = Collections
			.synchronizedMap(new HashMap<Integer, CallAgent>());

	public static final int FUN_ATTACHCALL = 5;

	/**
	 * switch2port时检查呼叫有效性
	 */
	public static final int FUN_CHECKCALL2_IN_SWITCH = 6;

	/**
	 * 检查消息的有效性
	 */
	public static final int FUN_CHECKMESSAGE = 2;

	/**
	 * 话路释放时关闭会议
	 */
	public static final int FUN_CLOSECONF_WHEN_DISCONNECTED = 9;

	/**
	 * DTMF进行编解码
	 */
	public static final int FUN_ENCODE_DTMFSTRING = 3;

	/**
	 * 支持用户最大空闲时间
	 */
	public static final int FUN_MAXUSERIDLESECONDS = 7;

	/**
	 * 普通外呼
	 */
	public static final int FUN_NORMAL_MAKECALL = 0;

	/**
	 * 外呼发送按键失败，释放呼叫
	 */
	public static final int FUN_RELEASE_IF_MAKECALL_SENDDTMFERR = 8;

	/**
	 * 获取未知消息，返回错误
	 */
	public static final int FUN_RET_ERR_FOR_UNKOWNMSG = 4;

	public static final int FUN_SINGLESTEPCONFERENCE = 1;

	protected static V2_CallSession session;

	public static void bill(int port, int callId, V2_EventMessage event)
	{
		if (session != null)
		{
			session.billI(port, callId, event);
		}
	}

	protected static void clear()
	{
		Iterator<Entry<Integer, CallAgent>> it = V2_CallSession.callAgentMap
				.entrySet().iterator();
		while (it.hasNext())
		{
			V2_CallAgentImpl callAgent = (V2_CallAgentImpl) it.next()
					.getValue();
			callAgent.mgMsgQueue.clear();
			callAgent.sgMsgQueue.clear();

			// 在服务断开之后添加一个假的挂机事件，防止通道挂死。
			SGEvent sgEvent = new SGEvent();
			sgEvent.CallID = Integer.MAX_VALUE;
			sgEvent.ResID = callAgent.resId;
			sgEvent.EventID = Constant.SG_LINE_DOWN;

			V2_SGEventMessage eventMsg = new V2_SGEventMessage(sgEvent);

			callAgent.mgMsgQueue.add(eventMsg);
			callAgent.sgMsgQueue.add(eventMsg);
		}
	}

	public static void clear(int callId)
	{
		if (session != null)
		{
			session.clearI(callId);
		}
	}

	public static void clearAgentMessage(int portId)
	{
		if (session != null)
		{
			session.clearAgentMessageI(portId);
		}
	}

	public static int getAgentId(int port, int callId)
	{
		if (session != null)
		{
			return session.getAgentIdI(port, callId);
		}
		return port;
	}

	public static IntWrapper getAgentId(IntWrapper port, IntWrapper callId)
	{
		if (session != null)
		{
			return session.getAgentIdI(port, callId);
		}
		return port;
	}

	public static CallAgent getCallAgent(int port, int callId)
	{
		if (session != null)
		{
			return session.getCallAgentI(port, callId);
		}
		return null;
	}

	public static boolean isOldCall(int callId)
	{
		if (session != null)
		{
			return session.isOldCallI(callId);
		}
		return false;
	}

	public static boolean isSupport(int funType)
	{
		if (session != null)
		{
			return session.isSupportI(funType);
		}
		return false;
	}

	public static void updatebymsg(V2_EventMessage event)
	{
		if (session != null)
		{
			session.changeI(event);
		}
	}

	protected void billI(int port, int callId, V2_EventMessage event)
	{

	}

	protected void changeI(V2_EventMessage event)
	{

	}

	protected void clearAgentMessageI(int port)
	{

	}

	protected void clearI(int callId)
	{

	}

	protected int getAgentIdI(int port, int callId)
	{
		return port;
	}

	protected IntWrapper getAgentIdI(IntWrapper port, IntWrapper callId)
	{
		return port;
	}

	protected CallAgent getCallAgentI(int port, int callId)
	{
		return null;
	}

	protected boolean isOldCallI(int callId)
	{
		return false;
	}

	protected boolean isSupportI(int funType)
	{
		return false;
	}
}
