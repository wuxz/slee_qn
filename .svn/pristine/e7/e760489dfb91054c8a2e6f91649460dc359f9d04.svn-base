/**
 * 
 */
package com.channelsoft.slee.jni;

import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author wuxz
 */
public class DRWRClient
{
	static DRWRClientLib INSTANCE = null;

	public static int drwr_cleanup()
	{
		return INSTANCE.drwr_cleanup();
	}

	public static int drwr_init()
	{
		if (ISysCfgData.isNewPath())
		{
			return INSTANCE.drwr_init(Platform.isWindows() ? ISysCfgData
					.queryRegData()
					+ "\\config\\drwr_client.cfg" : ISysCfgData.queryRegData()
					+ "/config/drwr_client.cfg");
		}
		else
		{
			return INSTANCE.drwr_init(Platform.isWindows() ? ISysCfgData
					.queryRegData()
					+ "\\SoftSwitch\\cfg\\drwr_client.cfg" : null);
		}
	}

	public static int drwr_saveSDR(String node_code, String device_code,
			String service_sn, String call_sn, String service_id,
			String calling_id, String called_id, String origin_calling_id,
			String origin_called_id, String account_id, int account_id_type,
			String agent_id, String agent_dn, String skill_id,
			String content_id, int channel_type, String sp_id,
			String subscriber_id, String access_time, String start_time,
			String end_time, int service_rate, int fee_type,
			int serviceAgentRate, int capacity, String user_number,
			String user_ip_addr, String network_account, String network_id,
			String network_portal_id, String reserve_1, String reserve_2,
			String reserve_3, String reserve_4, String reserve_5)
	{
		return INSTANCE.drwr_saveSDR(node_code, device_code, service_sn,
				call_sn, service_id, calling_id, called_id, origin_calling_id,
				origin_called_id, account_id, account_id_type, agent_id,
				agent_dn, skill_id, content_id, channel_type, sp_id,
				subscriber_id, access_time, start_time, end_time, service_rate,
				fee_type, serviceAgentRate, capacity, user_number,
				user_ip_addr, network_account, network_id, network_portal_id,
				reserve_1, reserve_2, reserve_3, reserve_4, reserve_5);
	}

	public static int drwr_threadAttach()
	{
		return INSTANCE.drwr_threadAttach();
	}

	public static int drwr_threadDetach()
	{
		return INSTANCE.drwr_threadDetach();
	}

	public static void init()
	{
		INSTANCE = (DRWRClientLib) Native.loadLibrary(
				(Platform.isWindows() ? "DRWRClient" : "drwr_client"),
				DRWRClientLib.class);
	}
}

interface DRWRClientLib extends Library
{
	int drwr_cleanup();

	int drwr_init(String fileName);

	int drwr_saveSDR(String node_code, String device_code, String service_sn,
			String call_sn, String service_id, String calling_id,
			String called_id, String origin_calling_id,
			String origin_called_id, String account_id, int account_id_type,
			String agent_id, String agent_dn, String skill_id,
			String content_id, int channel_type, String sp_id,
			String subscriber_id, String access_time, String start_time,
			String end_time, int service_rate, int fee_type,
			int serviceAgentRate, int capacity, String user_number,
			String user_ip_addr, String network_account, String network_id,
			String network_portal_id, String reserve_1, String reserve_2,
			String reserve_3, String reserve_4, String reserve_5);

	int drwr_threadAttach();

	int drwr_threadDetach();
}
