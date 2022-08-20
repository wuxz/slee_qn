package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.slee.util.*;

public class AnswerCall extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6010201400147647479L;

	public AnswerCall(Service service)
	{
		super(service);

	}

	int doIOProcess(BaseBrowser browser)
	{
		// 其接口现在不对外提供
		return Constant.EVENT_No_Error;
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("AnswerCall ");
		buff.append(getVarName());
		buff.append(" = new AnswerCall(this);\n");
	}



}
