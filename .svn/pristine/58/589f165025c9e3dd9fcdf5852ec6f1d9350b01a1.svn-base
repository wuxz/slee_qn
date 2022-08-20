package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;

import com.channelsoft.slee.util.Constant;

abstract public class IOProcess implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -340074130320889217L;

	public String ioProcessName;

	Service service;

	String varName;

	IOProcess(Service service)
	{
		this.service = service;
		varName = "ioprocess_" + service.getNewVarIndex();
	}

	abstract int doIOProcess(BaseBrowser browser);

	int executeIOProcess(BaseBrowser browser)
	{
		USMLReasoningResult error = new USMLReasoningResult(
				Constant.EVENT_No_Error);

		onEnterIOProcess(browser, error);
		if (error.value != Constant.EVENT_No_Error)
		{
			return error.value;
		}

		int result = doIOProcess(browser);

		onLeaveIOProcess(browser);

		return result;
	}

	public String getVarName()
	{
		return varName;
	}

	void onEnterIOProcess(BaseBrowser browser, USMLReasoningResult error)
	{
		service.browserSite.onEnterIOProcess(ioProcessName, error);
	}

	void onLeaveIOProcess(BaseBrowser browser)
	{
		service.browserSite.onLeaveIOProcess(ioProcessName, "");
	}

	abstract public void translate2SourceCode(StringBuilder buff)
			throws Exception;
}
