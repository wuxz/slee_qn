/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_MGEventMessage;
import com.channelsoft.slee.callagent.V2_SGEventMessage;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;

/**
 * @author wuxz
 */
public class V2_VGProxyImpl extends SSR2Agent
{
	@Override
	public void dispatchMessage(EventMessage event) throws Exception
	{
		if (event.messageId >> 16 == VGCPConstants.VGCP_MSG_TYPE_EVT_SG)
		{
			V2_CallAgentImpl.dispatchMessage(new V2_SGEventMessage(event
					.convert2SGEvent(new SGEvent())));
		}
		else if (event.messageId >> 16 == VGCPConstants.VGCP_MSG_TYPE_EVT_MG)
		{
			V2_CallAgentImpl.dispatchMessage(new V2_MGEventMessage(event
					.convert2MGEvent(new MGEvent())));
		}
		else
		{
			Log.wDebug(LogDef.id_0x10050000_24, event.resId, event.callId,
					event.messageId);
		}
	}

	/**
	 * @return
	 */
	@Override
	synchronized public int initialize()
	{
		super.initialize();

		V2_VGProxySession.Initialize();

		Log.wDebug(LogDef.id_0x10050000_2, sgIp, sgPort);

		return 1;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#VGInitialize(com.channelsoft.slee.unifiedservicemanagement.ISysCfgData)
	 */
	@Override
	public int VGInitialize(ISysCfgData sysCfgData)
	{
		try
		{
			setSleeName(sysCfgData.getSSR2SleeName());
			setSleePassword(sysCfgData.getSSR2SleePassword());

			int ret = VGInitialize(sysCfgData.getUvmgServerIp(), sysCfgData
					.getUvmgServerPort(), sysCfgData.getUvmgServerIp(),
					sysCfgData.getUvmgServerPort(), "*");

			Log.wTrace(LogDef.id_0x10010004, sysCfgData.getUvmgServerIp(),
					sysCfgData.getUvmgServerPort());

			return ret;
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);
			Log.wDebug(LogDef.id_0x10250003, sysCfgData.getUvmgServerIp(),
					sysCfgData.getUvmgServerPort());
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#VGInitialize(java.lang.String, int, java.lang.String, int, java.lang.String)
	 */
	@Override
	public int VGInitialize(String mgIp, int mgPort, String sgIp, int sgPort,
			String monitorDnis)
	{
		this.sgIp = sgIp;
		this.sgPort = sgPort;

		int result = 0;
		try
		{
			result = initialize();
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);

			Log.wDebug(LogDef.id_0x10250003, this.sgIp, this.sgPort);

			result = 0;
		}

		return result;
	}

}
