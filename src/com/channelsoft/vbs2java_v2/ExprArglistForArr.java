package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

/**
 * 数组参数列表。
 * 
 * @author dragon.huang
 */
public class ExprArglistForArr extends ExprArglist
{
	@Override
	void parse(ParserEnv env) throws Exception
	{
		parseForCall(env);
	}

	/**
	 * 数组调用解析。Redim arr(...)也被当成数组调用
	 * 
	 * @param env
	 * @throws Exception
	 */
	void parseForCall(ParserEnv env) throws Exception
	{
		if (env.nextToken().type != ParserConst.KT_LEFT_BRACKET)
		{// 数组没有指定下标，表示数组作为整个对象被调用。如for each ... in arr
			env.pushBackT();
			return;
		}

		EC ec = new EC();
		Expr expr = null;
		while ((expr = Expr.g_parse(env, ec)) != null)
		{
			ArgNode an = new ArgNode();
			an.varname = expr;
			arglist.add(an);

			if (isBreak(env))
			{
				break;
			}
		}
		if (ec != null)
		{
			skipRightBracket(env);
		}
	}

	/**
	 * 数组定义解析。dim arr(...)
	 * 
	 * @param env
	 * @throws Exception
	 */
	void parseForDefine(ParserEnv env) throws Exception
	{
		if (env.nextToken().type != ParserConst.KT_LEFT_BRACKET)
		{// 数组定义必须有括号
			throw new ParserException(env, ExceptionDesc.DS002_ARR_DEF_BRACKET);
		}

		EC ec = new EC();
		Expr expr = null;
		while ((expr = Expr.g_parse(env, ec)) != null)
		{
			ArgNode an = new ArgNode();
			if (expr.exprTk.isConstInt())
			{// 定义数组下标必须为常量整数
				an.varname = expr;
				arglist.add(an);
			}
			else
			{
				throw new ParserException(env, expr.exprTk, ExceptionDesc.DS003_ARR_DEF_INT);
			}

			if (isBreak(env))
			{
				break;
			}
		}
		skipRightBracket(env);
	}

	/**
	 * 判断数组参数是否可以当成数组定义的下标
	 * 
	 * @return
	 */
	boolean isdefined()
	{
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{
			ArgNode an = itor.next();
			if (!an.varname.exprTk.isConstInt())
			{
				return false;
			}
		}
		return true;
	}

	void printJavaForDefine(StringBuilder sb, ParserEnv env) throws Exception
	{// new int[]{1,2,3...}
		sb.append("new int[]{");
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{
			itor.next().varname.printIntJava(sb, env);
			if (itor.hasNext())
			{
				sb.append(", ");
			}
		}
		sb.append("}");
	}
}
