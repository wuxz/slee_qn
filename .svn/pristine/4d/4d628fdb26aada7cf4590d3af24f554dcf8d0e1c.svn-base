package com.channelsoft.vbs2java_v2;

public class NodeExitSub extends Node
{

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		env.check(new MSC());
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append("if(true)").append("\r\n");
		sb.append(env.lBracket());
		sb.append(env.indent()).append("return;\r\n");
		sb.append(env.rBracket());
	}

	private class MSC implements MainStatementContext
	{

		public boolean check(Token tk, Object grammerinfo)
		{
			return (tk.type == ParserConst.KT_SUB);
		}
	}

}
