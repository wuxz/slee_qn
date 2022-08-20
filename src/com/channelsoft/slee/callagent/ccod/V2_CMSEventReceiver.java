package com.channelsoft.slee.callagent.ccod;

import java.util.concurrent.LinkedBlockingQueue;

import com.channelsoft.CCODServices.ConnectionStateT;
import com.channelsoft.CCODServices.MediaEventT;
import com.channelsoft.CCODServices.MediaTypeT;
import com.channelsoft.CCODServices.ProtocolT;
import com.channelsoft.CCODServices.ServiceInfoT;
import com.channelsoft.CCODServices.SignalEventT;
import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_MGEventMessage;
import com.channelsoft.slee.callagent.V2_SGEventMessage;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

class CMSMessageX
{
	final static int MG_MSG = 1;

	final static int SG_MSG = 0;

	String accountId;

	int connectionId;

	String enterpriseId;
	
	String cmsId;

	int mepId;

	int messageType = -1;

	V2_MGEventMessage mgm = new V2_MGEventMessage();

	long sessionId;

	V2_SGEventMessage sgm = new V2_SGEventMessage();

	CMSMessageX(MediaEventT event, String cmsId)
	{
		messageType = MG_MSG;

		mgm.mgEvent = new MGEvent();
		sessionId = event.sessionID;
		mepId = event.mepID;
		mgm.mgEvent.CallID = -1;

		mgm.mgEvent.DTMFString = event.eventBuffer;
		mgm.mgEvent.ResID = -1; // ResID没有用处，只用callId就足够了。
		mgm.mgEvent.EventID = V2_ConstTransform
				.turnMediaEventTypeT2MGEventID(event.eventType);
		mgm.mgEvent.Reason = V2_ConstTransform
				.turnMediaEventTypeT2MGEventReason(event.eventCause);
		
		this.cmsId = cmsId;
	}

	CMSMessageX(ServiceInfoT serviceInfo, String cmsId) throws Exception
	{
		if (serviceInfo.connectionList.length < 1)
		{
			throw new Exception("Inbound事件缺少Connection。");
		}

		messageType = SG_MSG;

		sgm.sgEvent = new SGEvent();
		sessionId = serviceInfo.sessionID;
		connectionId = serviceInfo.connectionList[0].connectionID;
		enterpriseId = serviceInfo.enterpriseID;
		accountId = serviceInfo.accountID;
		sgm.timestamp = serviceInfo.timeStamp;
		sgm.sgEvent.EventID = Constant.SG_INCOMINGCALL;

		// 此时消息中的callid存放的其实是connectionid。
		sgm.sgEvent.CallID = serviceInfo.connectionList[0].connectionID;

		sgm.sgEvent.ResID = -1;

		if (serviceInfo.connectionList[0].state.value() == ConnectionStateT._StateConnected)
		{
			// 电话在到达时已经接通，表示是座席转移过来的电话。不能再应答了。
			sgm.timestamp = -sgm.timestamp;
			Log.wDebug(LogDef.id_0x10050000_13, sessionId);
		}

		sgm.sgEvent.CallerID = serviceInfo.connectionList[0].remoteUri;
		sgm.sgEvent.CalledID = serviceInfo.connectionList[0].localUri;
		sgm.sgEvent.OriCallerID = serviceInfo.connectionList[0].origRemoteUri;
		sgm.sgEvent.OriCalledID = serviceInfo.connectionList[0].origLocalUri;
		if(serviceInfo.connectionList[0].protocol == ProtocolT.ProtoH323)
		{//用ProtocolT代替MediaTypeT
			sgm.sgEvent.mediaType = Constant.VGCP_MEDIA_TYPE_VIDEO;
		}
		this.cmsId = cmsId;
	}

	CMSMessageX(SGEvent event, long sessionId, int connectionId, int timestamp)
	{
		this.sessionId = sessionId;
		this.connectionId = connectionId;
		messageType = SG_MSG;

		sgm.timestamp = timestamp;
		sgm.sgEvent = event;
	}

	CMSMessageX(SignalEventT event, String cmsId)
	{
		messageType = SG_MSG;

		sgm.sgEvent = new SGEvent();
		sessionId = event.sessionID;
		connectionId = event.connectionID;
		sgm.sgEvent.CalledID = event.connectionObj.localUri;
		sgm.sgEvent.CallerID = event.connectionObj.remoteUri;
		sgm.sgEvent.OriCalledID = event.connectionObj.origLocalUri;
		sgm.sgEvent.OriCallerID = event.connectionObj.origRemoteUri;
		sgm.sgEvent.ResID = -1;
		sgm.sgEvent.EventID = V2_ConstTransform
				.turnSignalEventTypeT2SGEventID(event.eventType);
		sgm.sgEvent.EventData = V2_ConstTransform
				.turnSignalFailedReasonT2SGEventData(event.failedReason);
		sgm.timestamp = event.timeStamp;
		this.cmsId = cmsId;
	}
}

/**
 * CMS消息处理线程。CMS发过来的消息先入队列，然后再由此线程异步处理。防止阻塞CMS的调用端。
 * 
 * @author dragon.huang
 */
public class V2_CMSEventReceiver extends Thread
{
	static V2_CMSEventReceiver cmsmp = new V2_CMSEventReceiver();

	/**
	 * CMS-本机的时间差，以秒为单位。
	 */
	static int cmsTimeOffset = 0;

	/**
	 * 假事件的ResId
	 */
	static final int DUMMY_RES_ID = -2;

	/**
	 * ivr错误的ResId
	 */
	static final int IVRERR_RES_ID = -10;

	static void onCustomHangup(int callId, long sessionId, int connectionId,
			int timestamp)
	{
		SGEvent event = new SGEvent();
		event.CallID = callId;
		event.EventID = Constant.SG_CALLDISCONNECTED;
		event.CalledID = "";
		event.CallerID = "";
		event.OriCalledID = "";
		event.OriCallerID = "";
		event.ResID = DUMMY_RES_ID;

		cmsmp.msgQueue.add(new CMSMessageX(event, sessionId, connectionId,
				timestamp));
	}

	public static void onMediaEventT(MediaEventT event, String fromCMS)
	{
		cmsmp.msgQueue.add(new CMSMessageX(event, fromCMS));
	}

	public static void onSignalEventT(SignalEventT event, String fromCMS)
	{
		cmsmp.msgQueue.add(new CMSMessageX(event, fromCMS));
	}

	static void onStartService(ServiceInfoT serviceInfo, String fromCMS) throws Exception
	{
		cmsTimeOffset = serviceInfo.timeStamp
				- (int) (System.currentTimeMillis() / 1000);
		cmsmp.msgQueue.add(new CMSMessageX(serviceInfo, fromCMS));
	}

	private LinkedBlockingQueue<CMSMessageX> msgQueue = new LinkedBlockingQueue<CMSMessageX>();

	boolean processCMSMessage(CMSMessageX msg) throws Exception
	{
		switch (msg.messageType)
		{
		case CMSMessageX.SG_MSG:
		{
			if (msg.sgm.sgEvent.EventID == Constant.SG_INCOMINGCALL)
			{
				int timestamp = msg.sgm.timestamp;
				timestamp = (timestamp > 0 ? timestamp : -timestamp);

				// 此时消息中的callid存放的其实是connectionid。
				V2_CallAgentImpl agent = V2_VGProxySession.newCallAgent(
						msg.sessionId, msg.sgm.sgEvent.CallID, -1,
						msg.enterpriseId, msg.accountId, timestamp,
						(msg.sgm.timestamp < 0), true, msg.cmsId);

				// 把假的时间再改为真的。
				msg.sgm.timestamp = timestamp;

				// 把callId从connecitonid改成真正的值。
				msg.sgm.sgEvent.CallID = agent.callId;

				msg.sgm.sgEvent.ResID = agent.callId;

				V2_CallAgentImpl.dispatchMessage(msg.sgm);

				Log
						.wDebug(LogDef.id_0x10050027, msg.sessionId, msgQueue
								.size());

				return true;
			}
			else
			{
				V2_CallAgentImpl agent = V2_VGProxySession
						.getCallAgentFromSessionId(msg.sessionId,
								msg.connectionId);
				if (agent == null)
				{
					Log.wWarn(LogDef.id_0x10050028, msg.sgm.sgEvent.EventID,
							msg.sessionId, msgQueue.size());
					return false;
				}

				msg.sgm.sgEvent.CallID = agent.callId;

				V2_CallAgentImpl.dispatchMessage(msg.sgm);

				Log.wDebug(LogDef.id_0x10050029, msg.sgm.sgEvent.EventID,
						msg.sessionId, msgQueue.size());

				return true;
			}
		}

		case CMSMessageX.MG_MSG:
		{
			V2_CallAgentImpl agent = V2_VGProxySession
					.getCallAgentFromSessionIdMepId(msg.sessionId, msg.mepId);
			if (agent == null)
			{
				Log.wWarn(LogDef.id_0x10050030, msg.mgm.mgEvent.EventID,
						msg.sessionId, msgQueue.size());
				return false;
			}

			msg.mgm.mgEvent.CallID = agent.callId;

			V2_CallAgentImpl.dispatchMessage(msg.mgm);

			Log.wDebug(LogDef.id_0x10050031, msg.mgm.mgEvent.EventID,
					msg.sessionId, msgQueue.size());

			return true;
		}
		}

		return false;
	}

	@Override
	public void run()
	{
		setName("CallAgent4CCOD CMSMessageProcessor");

		while (true)
		{
			try
			{
				CMSMessageX msg = msgQueue.take();
				processCMSMessage(msg);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10150000, e);
			}
		}
	}

}
