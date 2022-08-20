/**
 * 
 */
package com.channelsoft.slee.unifiedservicemanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.channelsoft.reusable.log.Logger;
import com.channelsoft.reusable.serviceprovider.ProviderContext;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.channelmanager.ATSChannel;

/**
 * @author wuxz
 */
public class ServiceProviderContextImpl implements ProviderContext
{
	static SimpleDateFormat sdFormatBill = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.serviceprovider.ProviderContext#getVarValue(java.lang.String, java.lang.String, com.channelsoft.reusable.util.StringWrapper)
	 */
	public boolean getVarValue(String channelDn, String varName,
			StringWrapper varValue)
	{
		ATSChannel channel = UnifiedServiceManagement.channelManager
				.getChannel(channelDn);
		if (channel == null)
		{
			return false;
		}

		return channel.atsBrowser.getVarValue(varName, varValue);
	}

	public Logger getLogger()
	{
		return UnifiedServiceManagement.comContext.getLogger();
	}

	public String getUsmlPath()
	{
		return UnifiedServiceManagement.configData.usmlPath;
	}

	@Override
	public boolean getCallInfo(String channelDn, StringWrapper callSn,
			StringWrapper port, StringWrapper callingId,
			StringWrapper calledId, StringWrapper oriCallingId,
			StringWrapper oriCalledId, StringWrapper accessTime,
			StringWrapper startTime)
	{
		ATSChannel channel = UnifiedServiceManagement.channelManager
				.getChannel(channelDn);
		if (channel == null)
		{
			return false;
		}
		callSn.value = channel.getCallSn();
		port.value = channel.getTrunkID();
		callingId.value = channel.getAni();
		calledId.value = channel.getDnis();
		oriCallingId.value = channel.getOriAni();
		oriCalledId.value = channel.getOriDnis();
		Calendar stratTime = Calendar.getInstance();
		if (channel.curContact.accessTime == null)
		{
			channel.curContact.accessTime = stratTime;
		}
		accessTime.value = sdFormatBill.format(channel.curContact.accessTime
				.getTime());
		startTime.value = sdFormatBill.format(stratTime.getTime());
		return true;
	}

}
