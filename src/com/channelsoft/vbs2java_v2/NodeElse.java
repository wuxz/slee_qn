package com.channelsoft.vbs2java_v2;

public class NodeElse extends Node
{
	Node statements = null;

	protected void parse(ParserEnv env) throws Exception
	{
		SC sc = new SC(env);
		env.check(sc);
		Object gi = env.getGrammerInfo();
		if (gi == null
				|| ((Integer) gi == ParserConst.SEF_IF_LINE_GRAMMER && env
						.readNextToken().isSkip()))
		{// 如果if语句只有一行，则else语句也要在行内。
			throw new ParserException(env, ExceptionDesc.DS004_ELSE);
		}
		else
		{
			statements = g_parse(env, sc);
		}
	}

	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append("else\r\n");
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
				return (tk.type == ParserConst.KT_END);
			}
			else
			{
				return (tk.type == ParserConst.KT_END || tk.type == ParserConst.KT_EOL);
			}
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_THEN || tk.type == ParserConst.KT_ELSEIF);
		}
	}
}
