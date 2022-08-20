package com.channelsoft.slee.callagent.ccod.servicedata;

import java.util.HashMap;

import com.channelsoft.CCODServices.EncodingT;
import com.channelsoft.CCODServices.ServiceDataTHolder;
import com.channelsoft.reusable.comobj.IDispatch;
import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.comobj.service.SLEEComponentContext;
import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.callagent.ccod.CallAgentImpl;

public class ServiceData implements IDispatch
{
	/**
	 * 呼叫ID
	 */
	int callId = -1;

	ComputingContext cc = null;

	SLEEComponentContext comContext = null;

	/**
	 * 内存中保存的随路数据
	 */
	java.util.Map<String, String> serviceDataMap = null;

	public ServiceData()
	{
	}

	private int getCallId()
	{
		return callId;
	}

	/**
	 * 获取随路数据
	 * 
	 * @param key
	 * @return
	 */
	private String getDefaultValue(String key)
	{
		if ((serviceDataMap == null) || (serviceDataMap.size() == 0))
		{
			return "";
		}

		String ret = serviceDataMap.get(key);
		return (ret == null ? "" : ret);
	}

	public Variant getProperty(String propertyName, Variant[] args)
			throws Exception
	{
		if (propertyName.equalsIgnoreCase("CallId"))
		{
			if (args.length != 0)
			{
				cc.reportError(new InvalidParameterCountException());

				return null;
			}

			return new Variant(getCallId(), cc);
		}
		else if (propertyName.equals(""))
		{
			if (args.length != 1)
			{
				cc.reportError(new InvalidParameterCountException());

				return null;
			}
			return new Variant(getDefaultValue(args[0].toString()), cc);
		}

		return null;
	}

	public Variant invoke(String method, Variant[] args) throws Exception
	{
		if (method.equalsIgnoreCase("save"))
		{
			if (args.length != 0)
			{
				cc.reportError(new InvalidParameterCountException());

				return null;
			}

			save();
			return null;
		}

		return getProperty(method, args);
	}

	/**
	 * 提交内存数据到CCOD
	 */
	private void save() throws Exception
	{
		if (CallAgentImpl.ccodProxy == null)
		{
			throw new Exception("未成功连接CMS！");
		}

		CallAgentImpl.ccodProxy.SetServiceData(CallAgentImpl.callSn2SessionId
				.get(callId), serviceDataMap, EncodingT.UTF8);
	}

	/**
	 * 设置呼叫ID，重复设置时抛出异常;取随路数据的map
	 * 
	 * @param callId
	 * @throws Exception
	 */
	private void setCallId(int callId) throws Exception
	{
		if (this.callId == -1)
		{
			this.callId = callId;
			if (CallAgentImpl.ccodProxy == null)
			{
				throw new Exception("未成功连接CMS！");
			}

			ServiceDataTHolder data = new ServiceDataTHolder();
			CallAgentImpl callAgent = CallAgentImpl.getCallAgent(callId);
			if (callAgent == null)
			{
				throw new Exception(String.format("指定的电话不存在，callId=%d", callId));
			}
			else
			{
				CallAgentImpl.ccodProxy.GetServiceData(
						callAgent.getSessionId(), EncodingT.UTF8, data);
			}

			if (data.value != null)
			{
				serviceDataMap = data.value;
			}
			else
			{
				serviceDataMap = new HashMap<String, String>();
			}

			serviceDataMap.put("SessionId", "" + callAgent.getSessionId());
			serviceDataMap.put("EnterpriseId", callAgent.getEnterpriseId());
			serviceDataMap.put("CMSName", CallAgentImpl.getCmsName());
			serviceDataMap.put("SLEEName", CallAgentImpl.getSleeServiceName());
		}
		else
		{
			throw new Exception("随路数据组件不能重复设置呼叫ID！");
		}
	}

	void setComponentContext(SLEEComponentContext comContext)
	{
		this.comContext = comContext;
	}

	public void setComputingContext(ComputingContext cc)
	{
		this.cc = cc;
		setComponentContext(cc.getSLEEComponentContext());
	}

	/**
	 * 设置随路数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private void setDefaultValue(String key, String value) throws Exception
	{
		if (serviceDataMap == null)
		{
			throw new Exception("未成功连接CMS！");
		}

		serviceDataMap.put(key, value);
	}

	public void setProperty(String propertyName, Variant[] args)
			throws Exception
	{
		if (propertyName.equalsIgnoreCase("CallId"))
		{
			if (args.length != 1)
			{
				cc.reportError(new InvalidParameterCountException());

				return;
			}

			setCallId((int) args[0].getDouble());
			return;
		}
		else if (propertyName.equals(""))
		{
			if (args.length != 2)
			{
				cc.reportError(new InvalidParameterCountException());

				return;
			}
			setDefaultValue(args[0].toString(), args[1].toString());
			return;
		}

		cc.reportError(new NoSuchFieldException());
	}
}
