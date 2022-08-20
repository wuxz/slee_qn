package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

/**
 * 方法定义参数列表。
 * 
 * @author dragon.huang
 */
class ExprArglistForFunction extends ExprArglist
{
	static ExprArglistForFunction parseArglistX(ParserEnv env) throws Exception
	{
		ExprArglistForFunction arglist = new ExprArglistForFunction();
		arglist.parse(env);
		return arglist;
	}

	@Override
	void parse(ParserEnv env) throws Exception
	{
		Token tk = env.nextToken();
		if (tk.type != ParserConst.KT_LEFT_BRACKET)
		{// 没有参数
			env.pushBackT();
			return;
		}

		tk = env.nextToken();
		while (tk.type != ParserConst.KT_RIGHT_BRACKET)
		{
			ArgNode an = new ArgNode();

			// 参数调用方式
			if (tk.type == ParserConst.KT_BYREF
					|| tk.type == ParserConst.KT_BYVAL)
			{
				an.callmanner = tk;
				tk = env.nextToken();
			}

			// 参数名称必须符合vb变量命名法则
			if (tk.isAlpha())
			{
				an.varname = new ExprVar();
				an.varname.exprTk = tk;
				arglist.add(an);
			}
			else
			{// 非参数
				throw new ParserException(env, tk,
						ExceptionDesc.DS003_NAME_NORMS);
			}

			// 去掉‘，’
			tk = env.nextToken();
			if (tk.type == ParserConst.KT_COMMA)
			{
				tk = env.nextToken();
			}
			else if (tk.type != ParserConst.KT_RIGHT_BRACKET)
			{
				if (tk.isLineEnd() || tk.isSkip())
				{
					throw new ParserException(env, tk,
							ExceptionDesc.DS004_FUN_DEF_BRACKET);
				}
				else
				{
					throw new ParserException(env, tk,
							ExceptionDesc.DS004_FUN_NEED_COMMA);
				}
			}
		}
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		// (Variant a, Variant b, .....)
		sb.append("(");
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{
			ArgNode an = itor.next();
			sb.append("Variant ");
			an.varname.printJava(sb, env);
			if (itor.hasNext())
			{
				sb.append(", ");
			}
		}
		sb.append(")");
	}
}
