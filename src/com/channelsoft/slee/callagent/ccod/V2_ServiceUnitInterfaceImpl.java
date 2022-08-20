package com.channelsoft.slee.callagent.ccod;

import com.channelsoft.CCODServices.CCODResultT;
import com.channelsoft.CCODServices.CMSInterfacePrx;
import com.channelsoft.CCODServices.CMSInterfacePrxHelper;
import com.channelsoft.CCODServices.MediaEventT;
import com.channelsoft.CCODServices.ServiceInfoT;
import com.channelsoft.CCODServices.SignalEventT;
import com.channelsoft.CCODServices._ServiceUnitInterfaceDisp;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;

final class V2_ServiceUnitInterfaceImpl extends _ServiceUnitInterfaceDisp
{

	public V2_ServiceUnitInterfaceImpl()
	{
	}

	public void SendMediaEvent(MediaEventT event, Ice.Current __current)
	{
		String cmsId = null;
		if(__current.ctx != null)
		{
			cmsId = __current.ctx.get("CMSNAME"); //__current
		}
		Log.wDebug(LogDef.id_0x10050024, event.eventType, event.eventCause,
				event.sessionID, event.mepID, cmsId);
		V2_CMSEventReceiver.onMediaEventT(event, cmsId);
	}

	public void SendSignalEvent(SignalEventT event, Ice.Current __current)
	{
		String cmsId = null;
		if(__current.ctx != null)
		{
			cmsId = __current.ctx.get("CMSNAME"); //__current
		}
		Log.wDebug(LogDef.id_0x10050009, event.eventType, event.failedReason,
				event.sessionID, event.connectionID, cmsId);
		V2_CMSEventReceiver.onSignalEventT(event, cmsId);
	}

	public void SetCallback(Ice.Identity ident, String serviceName,
			Ice.Current __current)
	{
		String cmsId = null;
		if(__current.ctx != null)
		{
			cmsId = __current.ctx.get("CMSNAME"); //__current
		}
		Ice.ObjectPrx base = __current.con.createProxy(ident);
		CMSInterfacePrx client = CMSInterfacePrxHelper.uncheckedCast(base);
		V2_VGProxyImp.setCCODProxy(client, cmsId);
		Log.wDebug(LogDef.id_0x1005000A, cmsId, client);
	}

	public CCODResultT StartService(ServiceInfoT serviceInfo,
			Ice.Current __current)
	{
		String cmsId = null;
		if(__current.ctx != null)
		{
			cmsId = __current.ctx.get("CMSNAME"); //__current
		}
		if (V2_VGProxySession.getProxy(cmsId) == null)
		{
			return CCODResultT.ResCallbackLost;
		}

		Log.wDebug(LogDef.id_0x10050000_18, serviceInfo.sessionID,
				serviceInfo.connectionList[0].connectionID, cmsId);

		try
		{
			V2_CMSEventReceiver.onStartService(serviceInfo, cmsId);

			Log.wDebug(LogDef.id_0x10050000_19, serviceInfo.sessionID,
					serviceInfo.connectionList[0].remoteUri,
					serviceInfo.connectionList[0].localUri,
					serviceInfo.connectionList[0].origRemoteUri,
					serviceInfo.connectionList[0].origLocalUri,
					cmsId, "");
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10150000, e);
			return CCODResultT.ResInvokeFailed;
		}

		return CCODResultT.ResSuccess;
	}
}