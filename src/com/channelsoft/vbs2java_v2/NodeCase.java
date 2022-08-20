package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

import com.channelsoft.vbs2java_v2.ExprArglist.ArgNode;

/**
 * case {arglist}
 * </p>
 * {statements}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeCase extends Node
{
	ExprArglistForCase arglist = null;

	Node statements = null;

	/**
	 * select语句的条件变量
	 */
	String testvariant = "";

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		SC sc = new SC();
		env.check(sc);

		testvariant = (String) env.getGrammerInfo();

		arglist = new ExprArglistForCase();
		arglist.parse(env);
		if (!arglist.hasArgument())
		{
			throw new ParserException(env, ExceptionDesc.DS004_CASE);
		}

		statements = Node.g_parse(env, sc);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		Iterator<ArgNode> itor = arglist.arglist.iterator();
		sb.append("(");
		while (itor.hasNext())
		{
			ArgNode an = itor.next();
			sb.append(testvariant).append(".equal(");
			if (an.varname.isConst())
			{
				an.varname.printJava(sb, env);
			}
			else
			{
				an.varname.printVarJava(sb, env);
			}
			sb.append(")");
			if (itor.hasNext())
			{
				sb.append("||");
			}
		}
		sb.append(")\r\n");
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
			return (tk.type == ParserConst.KT_END || tk.type == ParserConst.KT_CASE);
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
