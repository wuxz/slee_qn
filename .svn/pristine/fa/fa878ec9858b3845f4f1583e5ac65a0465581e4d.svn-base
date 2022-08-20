package com.channelsoft.vbs2java_v2;

import java.util.Iterator;
import java.util.Vector;

/**
 * select case {testexpr}
 * </p>
 * {case}
 * </p>
 * ...
 * </p>
 * {caseelse}
 * </p>
 * {endselect}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeSelect extends Node
{

	Expr testexpr = null;

	Vector<NodeCase> nclist = null;

	NodeCaseElse caseelse = null;

	NodeEndSelect endselect = null;

	/**
	 * 在java代码中testvariant代替testexpr实用。之前需将testexpr赋值给testvariant
	 */
	String testvariant = "";

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		if (env.nextToken().type != ParserConst.KT_CASE)
		{
			throw new ParserException(env, ExceptionDesc.DS004_NO_CASE);
		}

		testvariant = "_selectcase_" + this.linenum;
		env.setGrammerInfo(testvariant);

		testexpr = Expr.g_parse(env);
		if (testexpr == null)
		{
			throw new ParserException(env, ExceptionDesc.DS004_NO_TESTEXPR);
		}
		skipLine(env, null);

		while (endselect == null)
		{
			Node nd = createSubStatement(env);
			if (nd instanceof NodeCase)
			{
				if (nclist == null)
				{
					nclist = new Vector<NodeCase>();
				}
				nclist.add((NodeCase) nd);
			}
			else if (nd instanceof NodeCaseElse)
			{
				caseelse = (NodeCaseElse) nd;
			}
			else if (nd instanceof NodeEndSelect)
			{
				endselect = (NodeEndSelect) nd;
			}
			else
			{
				if (nd != null)
				{
					throw new ParserException(env, nd.keyword,
							ExceptionDesc.DS011);
				}
				else
				{
					throw new ParserException(env,
							ExceptionDesc.DS004_SELECT_NO_END);
				}
			}
		}
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append(
				"Variant " + testvariant + " = new Variant(cci);\r\n");
		sb.append(env.indent()).append(testvariant + ".setValue(");
		testexpr.printJava(sb, env);
		sb.append(");\r\n");
		boolean isif = true;
		if (nclist != null)
		{
			Iterator<NodeCase> itor = nclist.iterator();
			while (itor.hasNext())
			{
				NodeCase nc = itor.next();
				if (isif)
				{
					sb.append(env.indent()).append("if");
					isif = false;
				}
				else
				{
					sb.append("\r\n");
					sb.append(env.indent()).append("else if");
				}
				nc.print(sb, env);
			}
		}

		if (caseelse != null)
		{
			if (!isif)
			{
				sb.append("\r\n");
				sb.append(env.indent()).append("else\r\n");
				caseelse.print(sb, env);
			}
			else
			{
				caseelse.print(sb, env);
			}
		}
	}
}
