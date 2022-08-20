package com.channelsoft.vbs2java_v2;

/**
 * 数字表达式。返回数字。不同于ExprNum,后者为常量数字。
 * 
 * @author dragon.huang
 */
public class ExprConstNum
{
	Expr num = null;

	static ExprConstNum parse(ParserEnv env) throws Exception
	{
		ExprConstNum constnum = new ExprConstNum();
		constnum.num = Expr.g_parse(env);
		if (constnum.num == null)
		{
			throw new ParserException(env, env.currToken(),
					ExceptionDesc.DS004_CONSTNUM);
		}
		return constnum;
	}

	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		num.printJava(sb, env);
	}
}
