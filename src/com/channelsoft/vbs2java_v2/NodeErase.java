package com.channelsoft.vbs2java_v2;

public class NodeErase extends Node
{
	Token arrname = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		arrname = env.nextToken();
		if (!(arrname.type == ParserConst.KT_ARR || arrname.type == ParserConst.KT_VAR))
		{
			throw new ParserException(env, ExceptionDesc.DS003_ERASE);
		}
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (env.keyword().isSefDefFun(arrname.str))
		{
			throw new ParserException(env, ExceptionDesc.DS003_ERASE);
		}
		sb.append(env.indent()).append(arrname.str);
		sb.append(".erase();");
	}

}
