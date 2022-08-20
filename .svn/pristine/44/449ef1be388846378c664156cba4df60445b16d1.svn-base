package com.channelsoft.vbs2java_v2;

public class NodeThen extends Node
{
	Node statements = null;

	protected void parse(ParserEnv env) throws Exception
	{
		SC sc = new SC(env);
		Token preSub = env.check(sc);

		if (preSub.type == ParserConst.KT_IF)
		{
			Token tk = env.readNextToken();
			if (tk.isSkip())
			{
				env.setGrammerInfo(ParserConst.SEF_IF_BLOCK_GRAMMER);
			}
			else
			{// If condition Then statements [Else elsestatements] [end if]
				env.setGrammerInfo(ParserConst.SEF_IF_LINE_GRAMMER);
			}
		}
		statements = g_parse(env, sc);
	}

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
		ParserEnv env = null;

		SC(ParserEnv _env)
		{
			env = _env;
		}

		public boolean checkMainStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_IF);
		}

		public boolean checkEndSubStatement(Token tk)
		{
			Object gi = env.getGrammerInfo();
			if (gi == null || (Integer) gi == ParserConst.SEF_IF_BLOCK_GRAMMER)
			{
				return (tk.type == ParserConst.KT_ELSE
						|| tk.type == ParserConst.KT_ELSEIF || tk.type == ParserConst.KT_END);
			}
			else
			{
				return (tk.type == ParserConst.KT_ELSE || tk.type == ParserConst.KT_END || tk.type == ParserConst.KT_EOL);
			}
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_IF || tk.type == ParserConst.KT_ELSEIF);
		}
	}
}
