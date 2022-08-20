package com.channelsoft.vbs2java_v2;

/**
 * while {condition}
 * </p>
 * {statements}
 * </p>
 * {wend}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeWhile extends Node
{

	ExprCondition condition = null;

	Node statements = null;

	NodeWend wend = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		condition = ExprCondition.parse(env);

		statements = Node.g_parse(env, new SC());

		wend = (NodeWend) Node.createSubStatement(env, ParserConst.KT_WEND);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append("while(");
		condition.printJava(sb, env);
		sb.append(")").append("\r\n");
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
			return (tk.type == ParserConst.KT_WEND);
		}

		public boolean checkMainStatement(Token tk)
		{
			return false;
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return false;
		}

	}
}
