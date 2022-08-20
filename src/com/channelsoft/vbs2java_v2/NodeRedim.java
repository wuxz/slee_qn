package com.channelsoft.vbs2java_v2;

public class NodeRedim extends Node
{
	boolean isPreserve = false;

	ExprArglistForRedim arglist = null;
	
	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		Token tk = env.readNextToken();
		if (tk.type == ParserConst.KT_PRESERVE)
		{
			isPreserve = true;
			env.nextToken();
		}

		arglist = new ExprArglistForRedim(isPreserve);
		arglist.parse(env);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		arglist.printJava(sb, env);
	}
}
