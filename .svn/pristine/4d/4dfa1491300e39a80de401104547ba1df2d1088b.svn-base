/**
 * 
 */
package com.channelsoft.slee.callagent.ccod;

import com.channelsoft.CCODServices.GLSInterfacePrx;
import com.channelsoft.CCODServices.GLSInterfacePrxHelper;
import com.channelsoft.CCODServices.GLSResult;
import com.channelsoft.CCODServices.ServiceUnitParamMapHolder;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;

/**
 * GLS客户端，连接GLS服务器，并定时传送心跳。
 * 
 * @author wuxz
 */
public class GLSClient extends Thread
{
	static GLSInterfacePrx gls = null;

	/**
	 * 心跳间隔，单位为秒。默认-1，表示未初始化。
	 */
	static int hbrInterval = -1;

	static Ice.Communicator ic = null;

	String ddsConnection;

	String glsConnection;

	String glsServiceName;

	public GLSClient(String glsConnection, String glsServiceName)
	{
		this.glsConnection = glsConnection;
		this.glsServiceName = glsServiceName;
	}

	void connect() throws Exception
	{
		if (ic != null)
		{
			disconnect();
		}

		if (glsConnection.indexOf("-h 0.0.0.0") != -1)
		{
			return;
		}

		if (gls == null)
		{
			Log.wDebug(LogDef.id_0x10050000_1, glsConnection);

			ic = Ice.Util.initialize(new String[] { "" });
			Ice.ObjectPrx base = ic.stringToProxy(glsConnection);
			gls = GLSInterfacePrxHelper.checkedCast(base.ice_timeout(15000));

			Log.wDebug(LogDef.id_0x10050007);
		}

		ServiceUnitParamMapHolder svrUnitParamMap = new ServiceUnitParamMapHolder();
		int result = gls.RegisterServiceUnit(glsServiceName, svrUnitParamMap)
				.value();
		if (result != GLSResult._GLSSuccessReturn)
		{
			Log.wFatal(LogDef.id_0x10250002, result);
			disconnect();

			return;
		}

		String hbrStr = svrUnitParamMap.value.get("HBR");
		hbrInterval = Integer.parseInt(hbrStr);

		ddsConnection = svrUnitParamMap.value.get("DDS");
	}

	/**
	 * 
	 */
	void disconnect()
	{
		if (ic != null)
		{
			try
			{
				ic.destroy();
			}
			catch (Throwable ee)
			{
			}

			ic = null;
			gls = null;
			hbrInterval = -1;
		}
	}

	@Override
	public void run()
	{
		setName("CallAgent4CCOD GLSClient");

		while (true)
		{
			try
			{
				if (hbrInterval == -1)
				{
					connect();
				}

				if (gls == null)
				{
					Thread.sleep(5000);

					continue;
				}

				gls.HeartBeat(glsServiceName);

				// 为防止休眠长度不准，把休眠间隔长度减半。
				Thread.sleep(hbrInterval * 1000 / 2);
			}
			catch (Throwable e)
			{
				Log.wError(LogDef.id_0x1015001D, e.toString());

				disconnect();

				try
				{
					Thread.sleep(5000);
				}
				catch (Throwable ee)
				{
				}
			}
		}
	}
}
