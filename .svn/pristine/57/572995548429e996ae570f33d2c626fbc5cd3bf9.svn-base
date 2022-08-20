package com.channelsoft.vbs2java_v2;

public class NodeCaseElse extends Node
{

	Node statements = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		SC sc = new SC();
		env.check(sc);

		statements = Node.g_parse(env, sc);

	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.lBracket());
		if (statements != null)
		{
			statements.printJava(sb, env);
		}
		sb.append(env.rBracket());
	}

	private class SC implements StatementContext
	{

		public boolean checkEndSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_END);
		}

		public boolean checkMainStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_SELECT);
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_SELECT || tk.type == ParserConst.KT_CASE);
		}
	}
}
