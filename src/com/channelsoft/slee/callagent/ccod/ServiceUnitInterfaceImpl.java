// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

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

/**
 * @author liyan
 */
public final class ServiceUnitInterfaceImpl extends _ServiceUnitInterfaceDisp
{
	CallAgentImpl callAgent = null;

	public ServiceUnitInterfaceImpl(CallAgentImpl agentUtility)
	{
		this.callAgent = agentUtility;
	}

	public void SendMediaEvent(MediaEventT event, Ice.Current __current)
	{
		Log.wDebug(LogDef.id_0x10050024, event.eventType, event.eventCause,
				event.sessionID, event.mepID);

		CallAgentImpl.onMediaEventT(event);
	}

	public void SendSignalEvent(SignalEventT event, Ice.Current __current)
	{
		Log.wDebug(LogDef.id_0x10050009, event.eventType, event.failedReason,
				event.sessionID, event.connectionID);
		com.channelsoft.slee.callagent.ccod.CallAgentImpl.onSignalEventT(event);
	}

	public void SetCallback(Ice.Identity ident, String serviceName,
			Ice.Current __current)
	{
		Ice.ObjectPrx base = __current.con.createProxy(ident);
		CMSInterfacePrx client = CMSInterfacePrxHelper.uncheckedCast(base);
		com.channelsoft.slee.callagent.ccod.CallAgentImpl.setCCODProxy(client,
				serviceName);
		Log.wDebug(LogDef.id_0x1005000A);
	}

	public CCODResultT StartService(ServiceInfoT serviceInfo,
			Ice.Current __current)
	{
		if (CallAgentImpl.ccodProxy == null)
		{
			return CCODResultT.ResCallbackLost;
		}

		Log.wDebug(LogDef.id_0x10050000_18, serviceInfo.sessionID,
				serviceInfo.connectionList[0].connectionID);

		try
		{
			CallAgentImpl.onStartService(serviceInfo);

			Log.wDebug(LogDef.id_0x10050000_19, serviceInfo.sessionID,
					serviceInfo.connectionList[0].remoteUri,
					serviceInfo.connectionList[0].localUri,
					serviceInfo.connectionList[0].origRemoteUri,
					serviceInfo.connectionList[0].origLocalUri, "", "");
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10150000, e);
			return CCODResultT.ResInvokeFailed;
		}

		return CCODResultT.ResSuccess;
	}
}
