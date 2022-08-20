package com.channelsoft.vbs2java_v2;

public class NodeStep extends Node
{

	ExprConstNum constnum = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		SC sc = new SC();
		env.check(sc);
		constnum = ExprConstNum.parse(env);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		constnum.printJava(sb, env);
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		print(sb, env);
	}

	private class SC implements StatementContext
	{

		public boolean checkEndSubStatement(Token tk)
		{
			return false;
		}

		public boolean checkMainStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_FOR);
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_TO);
		}

	}
}
