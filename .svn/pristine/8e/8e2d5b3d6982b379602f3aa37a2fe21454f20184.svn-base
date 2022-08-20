package com.channelsoft.slee.callagent.ccod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.channelsoft.CCODServices.CMSInterfacePrx;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.slee.billing.BillingDataMgThread;
import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_CallSession;
import com.channelsoft.slee.callagent.V2_EventMessage;
import com.channelsoft.slee.callagent.V2_SGEventMessage;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

public class V2_VGProxySession extends V2_CallSession
{
	public static Map<Integer, Long> callSn2SessionId = Collections
			.synchronizedMap(new HashMap<Integer, Long>());

	static Map<String, CMSInterfacePrx> cmsId2Proxy = Collections
			.synchronizedMap(new HashMap<String, CMSInterfacePrx>());

	/**
	 * CCOD中，IVR固定的ServiceId。
	 */
	private static String serviceId;

	/**
	 * CCOD中，用户与外线通话话单AgentServiceId。
	 */
	private static String agentServiceId;
	
	static Map<Long, V2_CallIdsInSession> sessionId2Callsn = Collections
			.synchronizedMap(new HashMap<Long, V2_CallIdsInSession>());

	private static String sleeServiceName;

	public static V2_CallAgentImpl getCallAgentFromSessionId(Long sessionId,
			int connectionId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return null;
		}

		int destCallId = callIds.getCallIdFromConnectionId(connectionId);
		if (destCallId == -1)
		{
			return null;
		}

		V2_CallAgentImpl agent = V2_CallAgentImpl.getCallAgent(-1, destCallId);

		return agent;
	}

	public static V2_CallAgentImpl getCallAgentFromSessionIdMepId(
			Long sessionId, int mepId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return null;
		}

		int destCallId = callIds.getCallIdFromMepId(mepId);
		if (destCallId == -1)
		{
			return null;
		}

		V2_CallAgentImpl agent = V2_CallAgentImpl.getCallAgent(-1, destCallId);

		return agent;
	}

	public static String getCmsId(CMSInterfacePrx proxy)
	{
		Iterator<Entry<String, CMSInterfacePrx>> it = cmsId2Proxy.entrySet()
				.iterator();
		while (it.hasNext())
		{
			Entry<String, CMSInterfacePrx> entry = it.next();
			if (entry.getValue() == proxy)
			{
				return entry.getKey();
			}
		}
		return null;
	}

	public static String getCmsName(int callId)
	{
		long sessionId = getSessionIdFromCallId(callId);
		if (sessionId == -1)
		{
			return null;
		}
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return null;
		}
		return callIds.cmsId;
	}

	public static int getConnectionIdFromCallId(long sessionId, int callId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds != null)
		{
			return callIds.getConnectionIdFromCallId(callId);
		}
		return -1;
	}

	public static String getEnterpriseIdFromCallId(long sessionId, int callId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds != null)
		{
			return callIds.enterpriseId;
		}
		return "";
	}

	public static boolean getIsOldCallFromCallId(long sessionId, int callId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds != null)
		{
			return callIds.getIsOldCallFromCallId(callId);
		}
		return false;
	}

	public static int getMepIdFromCallId(long sessionId, int callId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds != null)
		{
			return callIds.getMepIdFromCallId(callId);
		}
		return -1;
	}

	public static CMSInterfacePrx getProxy()
	{
		Iterator<Entry<String, CMSInterfacePrx>> it = cmsId2Proxy.entrySet()
				.iterator();
		while (it.hasNext())
		{
			Entry<String, CMSInterfacePrx> entry = it.next();
			return entry.getValue();
		}
		return null;
	}

	public static CMSInterfacePrx getProxy(int callId)
	{
		long sessionId = getSessionIdFromCallId(callId);
		if (sessionId == -1)
		{
			return null;
		}
		return getProxy(sessionId);
	}

	public static CMSInterfacePrx getProxy(long sessionId)
	{
		V2_CallIdsInSession callIds = sessionId2Callsn.get(sessionId);
		if (callIds == null)
		{
			return null;
		}
		return getProxy(callIds.cmsId);
	}

	public static CMSInterfacePrx getProxy(String cmsId)
	{
		return cmsId2Proxy.get(cmsId);
	}

	public static long getSessionIdFromCallId(int callId)
	{
		Long sid = callSn2SessionId.get(callId);
		if (sid != null)
		{
			return sid;
		}
		return -1;
	}

	public static String getSleeServiceName()
	{
		return sleeServiceName;
	}

	static void Initialize(String sleeName, String serviceId, String agentServiceId)
	{
		V2_VGProxySession.sleeServiceName = sleeName;
		V2_VGProxySession.serviceId = serviceId;
		V2_VGProxySession.agentServiceId = agentServiceId;
		V2_CallSession.session = new V2_VGProxySession();
		clear();
	}

	public synchronized static V2_CallAgentImpl newCallAgent(long sessionId,
			int connectionId, int mepId, String enterpriseId, String accountId,
			int timestamp, boolean isOldCall, boolean isHostAgent, String cmsId)
	{
		// 如果未完成的呼叫太多，为了防止内存不足，丢弃当前所有呼叫
		if (V2_CallSession.callAgentMap.size() > V2_VGProxyImp.MAX_PENDING_CALL)
		{
			V2_VGProxyImp.onDisconnectFromServer();
		}

		V2_CallAgentImpl agent = new V2_CallAgentImpl();
		// agent.accountId = accoundId;
		// agent.sessionId = sessionId;
		// agent.connectionId = connectionId;
		// agent.enterpriseId = enterpriseId;
		// agent.accessTimestamp = timestamp;
		// agent.isOldCall = isOldCall;

		agent.callId = V2_VGProxyImp.callSnSeed++;
		if (V2_VGProxyImp.callSnSeed > V2_VGProxyImp.MAX_CALLSN)
		{
			V2_VGProxyImp.callSnSeed = 0;
		}

		V2_CallSession.callAgentMap.put(agent.callId, agent);
		V2_VGProxySession.callSn2SessionId.put(agent.callId, sessionId);

		V2_CallIdsInSession callIds = null;

		if (isHostAgent)
		{
			callIds = new V2_CallIdsInSession();
			callIds.callId = agent.callId;
			callIds.connectionId = connectionId;
			callIds.enterpriseId = (enterpriseId == null) ? callIds.enterpriseId
					: enterpriseId;
			callIds.accountId = (accountId == null) ? callIds.accountId
					: accountId;
			callIds.mepId = mepId;
			callIds.isOldCall = isOldCall;
			callIds.accessTimestamp = timestamp;
			callIds.cmsId = cmsId;

			V2_VGProxySession.sessionId2Callsn.put(sessionId, callIds);
		}
		else
		{
			callIds = V2_VGProxySession.sessionId2Callsn.get(sessionId);
			if (callIds == null)
			{
				Log
						.wDebug(LogDef.id_0x10050000_10, sessionId,
								connectionId, agent.callId,
								V2_VGProxySession.callSn2SessionId.size());

				V2_CallSession.clear(agent.callId);
				return null;
			}

			callIds.otherCallId = agent.callId;
			callIds.otherConnectionId = connectionId;
			callIds.otherMepId = mepId;
			callIds.isOtherOldCall = isOldCall;
			callIds.otherAccessTimestamp = timestamp;
		}

		Log.wDebug(LogDef.id_0x10050000_11, sessionId, connectionId,
				agent.callId, V2_VGProxySession.callSn2SessionId.size(),
				V2_VGProxySession.sessionId2Callsn.size());

		return agent;
	}

	public static void setProxy(String cmsId, CMSInterfacePrx proxy)
	{
		cmsId2Proxy.put(cmsId, proxy);
	}

	@Override
	protected void billI(int port, int callId, V2_EventMessage event)
	{
		V2_CallAgentImpl agent = V2_CallAgentImpl.getCallAgent(port, callId);

		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callId);
		V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
				.get(sessionId);
		if ((agent != null) && (callIds != null))
		{
			int accessTimestamp = callIds.getAccessTimestampFromCallID(callId);
			int startTimestamp = callIds.getStartTimestampFromCallID(callId);

			if ((port != V2_CMSEventReceiver.DUMMY_RES_ID)
					&& (startTimestamp != 0)
					&& (callIds.accountId != null))
			{ // 不是假挂机，而且应答成功。这时需要写SDR。
				String serviceId = V2_VGProxySession.agentServiceId;
				int endTimeStamp = ((V2_SGEventMessage) event).timestamp;
				if(callIds.isHostAgent(callId))
				{//用户与外线通话开始到通话结束，另生成话单(AgentServiceId)，因此，话单只记录到外线接通时间。
					serviceId = V2_VGProxySession.serviceId;
					if(callIds.otherStartTimestamp != 0)
					{
						endTimeStamp = callIds.otherStartTimestamp;
					}
				}
				String ani = agent.ani.split("_")[0];
				String dnis = agent.dnis.split("_")[0];
				BillingDataMgThread
						.instance()
						.bill(
								ani.substring(ani.indexOf(':') + 1),
								dnis.substring(dnis.indexOf(':') + 1),
								agent.oriAni.substring(agent.oriAni
										.indexOf(':') + 1),
								agent.oriDnis.substring(agent.oriDnis
										.indexOf(':') + 1),
								callIds.accountId.substring(callIds.accountId
										.indexOf(':') + 1), accessTimestamp,
								startTimestamp,
								endTimeStamp,
								serviceId,
								callIds.enterpriseId);
			}
		}
	}

	@Override
	protected void changeI(V2_EventMessage msg)
	{
		if (Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT == msg.messageType)
		{
			int callId = ((V2_SGEventMessage) msg).sgEvent.CallID;
			int eventId = ((V2_SGEventMessage) msg).sgEvent.EventID;
			int timestamp = ((V2_SGEventMessage) msg).timestamp;

			long sessionId = V2_VGProxySession.getSessionIdFromCallId(callId);
			V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
					.get(sessionId);
			if (null == callIds)
			{
				return;
			}

			if (Constant.SG_CALLCONNECTED == eventId)
			{
				callIds.setStartTimestampFromCallID(callId, timestamp);
				if (callIds.getAccessTimestampFromCallID(callId) == 0)
				{
					// 有时候会丢失振铃事件。
					callIds.setAccessTimestampFromCallID(callId, timestamp);
				}
			}
			else if (Constant.SG_CALLALERTING == eventId)
			{
				callIds.setAccessTimestampFromCallID(callId, timestamp);
			}
		}
	}

	/**
	 * 清除当前呼叫相关的会话信息。
	 */
	@Override
	protected void clearI(int callId)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callId);
		int connectionId = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId, callId);
		V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
				.get(sessionId);

		V2_CallSession.callAgentMap.remove(callId);
		V2_VGProxySession.callSn2SessionId.remove(callId);

		if (callIds == null)
		{
			return;
		}

		synchronized (callIds)
		{
			if ((callIds.callId == callId))
			{
				callIds.callId = -1;
				callIds.connectionId = -1;
				callIds.mepId = -1;
			}
			else if (callIds.otherCallId == callId)
			{
				callIds.otherCallId = -1;
				callIds.otherConnectionId = -1;
				callIds.otherMepId = -1;
			}

			Log.wDebug(LogDef.id_0x10050000_8, sessionId, connectionId, callId,
					V2_VGProxySession.callSn2SessionId.size(),
					V2_CallSession.callAgentMap.size());

			if ((callIds.callId == -1) && (callIds.otherCallId == -1))
			{
				// 清除会话
				V2_VGProxySession.sessionId2Callsn.remove(sessionId);

				Log.wDebug(LogDef.id_0x10050000_9, sessionId,
						V2_VGProxySession.sessionId2Callsn.size());
			}
		}
	}

	@Override
	protected int getAgentIdI(int port, int callId)
	{
		return callId;
	}

	@Override
	protected IntWrapper getAgentIdI(IntWrapper port, IntWrapper callId)
	{
		return callId;
	}

	/**
	 * 通过指定的callId查找CallAgent
	 * 
	 * @param callId
	 * @return 找到则返回CallAgent，否则返回null。
	 */
	@Override
	protected CallAgent getCallAgentI(int port, int callId)
	{
		if (callId < 0)
		{
			return null;
		}

		return V2_CallSession.callAgentMap.get(callId);
	}

	@Override
	protected boolean isOldCallI(int callId)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callId);
		return V2_VGProxySession.getIsOldCallFromCallId(sessionId, callId);
	}

	@Override
	protected boolean isSupportI(int funType)
	{
		return ((FUN_NORMAL_MAKECALL == funType)
				|| (funType == FUN_ENCODE_DTMFSTRING)
				|| (funType == FUN_CHECKCALL2_IN_SWITCH)
				|| (funType == FUN_RELEASE_IF_MAKECALL_SENDDTMFERR) || (funType == FUN_CLOSECONF_WHEN_DISCONNECTED));
	}
}
