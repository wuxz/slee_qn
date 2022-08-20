package com.channelsoft.slee.callagent.ccod;

import com.channelsoft.DDSSpace.IvrCallEventT;
import com.channelsoft.DDSSpace.IvrEventReasonT;
import com.channelsoft.slee.callagent.V2_CallStat;
import com.channelsoft.slee.callagent.V2_EventMessage;
import com.channelsoft.slee.callagent.V2_SGEventMessage;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

public class V2_CallStatImp extends V2_CallStat
{
	static void start(String statFileDir, GLSClient glsClient)
	{
		V2_CallStatImp callStat = new V2_CallStatImp();
		callStat.ddsClient = new DDSClient(statFileDir, glsClient);
		callStat.ddsClient.startClients();
		V2_CallStat.callStat = callStat;

		Log.wDebug(LogDef.id_0x10050000_12);
	}

	/**
	 * CCOD呼叫数据统计
	 */
	protected DDSClient ddsClient;

	static void addEvent(int callId, int port, long sessionId,
			String enterpriseId, int timestamp, String ani, String dnis,
			int eventType)
	{
		if (V2_CallStat.callStat != null)
		{
			((V2_CallStatImp) V2_CallStat.callStat).addEventI(callId, port,
					sessionId, enterpriseId, timestamp, ani, dnis, eventType);
		}
	}

	protected void addEventI(int callId, int port, long sessionId,
			String enterpriseId, int timestamp, String ani, String dnis,
			int eventType)
	{
		int ccodEventType = -1;
		int ccodEventReason = -1;

		if (Constant.SG_INCOMINGCALL == eventType)
		{
			ccodEventType = IvrCallEventT._TIvrAlerting;
			ccodEventReason = IvrEventReasonT._TIvrResNormal;
		}
		else if (Constant.SG_CALLRELEASED == eventType)
		{
			ccodEventType = IvrCallEventT._TIvrReleased;
			if (port == V2_CMSEventReceiver.DUMMY_RES_ID)
			{
				ccodEventReason = IvrEventReasonT._TIvrResTransfer;
			}
			else if (port == V2_CMSEventReceiver.IVRERR_RES_ID)
			{
				ccodEventReason = IvrEventReasonT._TIvrResError;
			}
			else
			{
				ccodEventReason = IvrEventReasonT._TIvrResRemoteRelease;
			}
		}
		else if (Constant.SG_CALLCONNECTED == eventType)
		{
			ccodEventType = IvrCallEventT._TIvrConnected;
			ccodEventReason = IvrEventReasonT._TIvrResNormal;
		}
		else if (Constant.SG_CALLALERTING == eventType)
		{
			ccodEventType = IvrCallEventT._TIvrConnected;
			ccodEventReason = IvrEventReasonT._TIvrResNormal;
		}

		if (ccodEventType != -1)
		{
			ddsClient.addEvent(sessionId, timestamp, enterpriseId, ani, dnis,
					ccodEventType, ccodEventReason);
		}
	}

	@Override
	protected void addEventI(int callId, int port, int timestamp, String ani,
			String dnis, int eventType)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callId);
		String enterpriseId = V2_VGProxySession.getEnterpriseIdFromCallId(
				sessionId, callId);
		addEventI(callId, port, sessionId, enterpriseId, timestamp, ani, dnis,
				eventType);
	}

	@Override
	protected void addEventI(int callId, int port, V2_EventMessage msg,
			String ani, String dnis, int eventType)
	{
		this.addEventI(callId, port, ((V2_SGEventMessage) msg).timestamp, ani,
				dnis, eventType);
	}
}
