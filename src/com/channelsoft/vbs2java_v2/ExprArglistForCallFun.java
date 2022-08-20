package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

/**
 * 方法调用参数列表。
 * 
 * @author dragon.huang
 */
class ExprArglistForCallFun extends ExprArglist
{

	/**
	 * 根据方法参数定义，翻译方法参数调用
	 * 
	 * @param sb
	 * @param env
	 * @param def
	 *            方法定义的参数列表
	 * @param call
	 *            方法调用的参数列表
	 * @throws Exception
	 */
	static void printWithCheck(StringBuilder sb, ParserEnv env,
			ExprArglist def, ExprArglist call) throws Exception
	{
		if (def.arglist.size() != call.arglist.size())
		{
			throw new ParserException(env, ExceptionDesc.DS005);
		}
		Iterator<ArgNode> defItor = def.arglist.iterator();
		Iterator<ArgNode> calItor = call.arglist.iterator();
		while (defItor.hasNext())
		{
			Token callManner = defItor.next().callmanner;
			Expr callArg = calItor.next().varname;
			if (callManner != null && callManner.type == ParserConst.KT_BYVAL)
			{
				if (!callArg.isReturnVar())
				{
					callArg.printVarJava(sb, env);
				}
				else
				{
					sb.append("new Variant(cci).setValue(");
					callArg.printJava(sb, env);
					sb.append(")");
				}
			}
			else
			{
				if (callArg == null)
				{
					sb.append("null");
				}
				else
				{
					callArg.printVarJava(sb, env);
				}
			}

			if (defItor.hasNext())
			{
				sb.append(", ");
			}
		}
	}

	@Override
	void parse(ParserEnv env) throws Exception
	{
		// 括号解析
		EC ec = null;
		if (env.nextToken().type == ParserConst.KT_LEFT_BRACKET)
		{
			ec = new EC();
		}
		else
		{
			env.pushBackT();
			// 根据上下文判断该方法调用是否带参数
			if (env.getGrammerInfo() == null && env.isRootExpr())
			{
			}
			else
			{// 如果方法调用是一个子语句 或 是一个被嵌套(或括号内)表达式时，如果调用不带括号，则认为该方法没有参数
				return;
			}
		}

		// 参数列表解析
		Expr expr = null;
		while ((expr = Expr.g_parse(env, ec)) != null
				|| env.readNextToken().type == ParserConst.KT_COMMA)
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
}
