package com.channelsoft.vbs2java_v2;

/**
 * do {loopType: while||until}{condition}
 * </p>
 * {statements}
 * </p>
 * {loop}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeDo extends Node
{
	ExprCondition condition = null;

	/**
	 * loopType : while 或 until
	 */
	Token loopType = null;

	Node statements = null;

	NodeLoop loop = null;

	/**
	 * do（或while）循环break时，设置break标记。
	 */
	GrammerInfo gi = new GrammerInfo();

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		gi.breakTag = "tag" + this.linenum;
		env.setGrammerInfo(gi);

		Token tk = env.nextToken();
		if (tk.type == ParserConst.KT_WHILE || tk.type == ParserConst.KT_UNTIL)
		{
			loopType = tk;
			env.setCurSub(loopType);
			condition = ExprCondition.parse(env);
		}
		else
		{
			env.pushBackT();
		}

		statements = g_parse(env, new SC());
		if (loopType != null)
		{
			env.setPreSub(loopType);
		}

		loop = (NodeLoop) createSubStatement(env, ParserConst.KT_LOOP);
	}

	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (gi.needBreakTag)
		{
			sb.append(gi.breakTag).append(" : ");
		}

		if (condition == null)
		{
			sb.append(env.indent()).append("do").append("\r\n");
		}
		else
		{
			if (loopType.type == ParserConst.KT_WHILE)
			{
				sb.append(env.indent()).append("while(");
				condition.printJava(sb, env);
				sb.append(")").append("\r\n");
			}
			else
			{
				sb.append(env.indent()).append("while(!(");
				condition.printJava(sb, env);
				sb.append("))").append("\r\n");
			}
		}
		sb.append(env.lBracket());
		if (statements != null)
		{
			statements.printJava(sb, env);
		}
		sb.append(env.rBracket());
		loop.print(sb, env);
	}

	/**
	 * do（或while）循环break时，设置break标记。
	 */
	static class GrammerInfo
	{
		String breakTag = "";

		boolean needBreakTag = false;
	}

	private class SC implements StatementContext
	{

		public boolean checkMainStatement(Token tk)
		{
			return false;
		}

		public boolean checkEndSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_LOOP);
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return false;
		}
	}
}
