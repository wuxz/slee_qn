package com.channelsoft.vbs2java_v2;

/**
 * object方法调用参数表达式。
 * 
 * @author dragon.huang
 */
public class ExprArglistForCallDotfun extends ExprArglist
{

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
