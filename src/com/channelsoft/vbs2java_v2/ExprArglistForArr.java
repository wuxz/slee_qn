package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

/**
 * ��������б�
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
	 * ������ý�����Redim arr(...)Ҳ�������������
	 * 
	 * @param env
	 * @throws Exception
	 */
	void parseForCall(ParserEnv env) throws Exception
	{
		if (env.nextToken().type != ParserConst.KT_LEFT_BRACKET)
		{// ����û��ָ���±꣬��ʾ������Ϊ�������󱻵��á���for each ... in arr
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
	 * ���鶨�������dim arr(...)
	 * 
	 * @param env
	 * @throws Exception
	 */
	void parseForDefine(ParserEnv env) throws Exception
	{
		if (env.nextToken().type != ParserConst.KT_LEFT_BRACKET)
		{// ���鶨�����������
			throw new ParserException(env, ExceptionDesc.DS002_ARR_DEF_BRACKET);
		}

		EC ec = new EC();
		Expr expr = null;
		while ((expr = Expr.g_parse(env, ec)) != null)
		{
			ArgNode an = new ArgNode();
			if (expr.exprTk.isConstInt())
			{// ���������±����Ϊ��������
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
	 * �ж���������Ƿ���Ե������鶨����±�
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
