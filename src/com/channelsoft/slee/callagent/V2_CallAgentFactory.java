package com.channelsoft.slee.callagent;

import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;

/**
 * @author wuxz 呼叫控制接口的工厂类
 */
public class V2_CallAgentFactory
{
	/**
	 * 根据系统配置，生成一个新的呼叫控制接口实例。
	 * 
	 * @param sysCfgData
	 * @return
	 */
	public static CallAgent create(ISysCfgData sysCfgData)
	{
		CallAgent callAgent;
		if (sysCfgData.getUseCMS())
		{
			callAgent = new V2_CallAgentImpl();
			V2_CallAgentImpl.vgProxy = new com.channelsoft.slee.callagent.ccod.V2_VGProxyImp();
		}
		else if (sysCfgData.getUseVGProxy())
		{
			callAgent = new V2_CallAgentImpl();
			V2_CallAgentImpl.vgProxy = new com.channelsoft.slee.callagent.ice.V2_VGProxyImp();
		}
		else if (sysCfgData.isUseSSR2())
		{
			callAgent = new V2_CallAgentImpl();
			V2_CallAgentImpl.vgProxy = new com.channelsoft.slee.callagent.ssr2.V2_VGProxyImpl();
		}
		else if (sysCfgData.isUseUCCE2())
		{
			callAgent = new com.channelsoft.slee.callagent.ucce.CallAgentImpl();
			V2_CallAgentImpl.vgProxy = new com.channelsoft.slee.callagent.ssr2.V2_VGProxyImpl();
		}
		else
		{
			callAgent = new com.channelsoft.slee.callagent.socket.CallAgentImpl();
		}

		if (callAgent != null)
		{
			callAgent.atsInitialize(sysCfgData);
		}
		return callAgent;
	}
}
