package com.channelsoft.vbs2java_v2;

public class NodeIn extends Node
{
	Expr group = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		SC sc = new SC();
		env.check(sc);

		group = Expr.g_parse(env);
		if (group == null || !group.isReturnVar())
		{// 是数组还是对象集合，在运行时判断
			throw new ParserException(env, ExceptionDesc.DS003_IN);
		}
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		group.printJava(sb, env);
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
			return (tk.type == ParserConst.KT_EACH);
		}

	}
}
