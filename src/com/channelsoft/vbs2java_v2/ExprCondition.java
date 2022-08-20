package com.channelsoft.vbs2java_v2;

/**
 * 条件表达式。表达式返回boolean。
 * 
 * @author dragon.huang
 */
public class ExprCondition
{
	Expr expr = null;

	// 条件正确表达式
	boolean isTrue = false;

	static ExprCondition parse(ParserEnv env) throws Exception
	{
		ExprCondition ec = new ExprCondition();
		ec.expr = Expr.g_parse(env);
		if (ec.expr == null)
		{
			throw new ParserException(env, ExceptionDesc.DS004_CONDITION);
		}
		return ec;
	}

	static ExprCondition createTrue() throws Exception
	{
		ExprCondition ec = new ExprCondition();
		ec.isTrue = true;
		return ec;
	}

	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (expr != null)
		{
			if (expr.isNull())
			{
				sb.append("false");
			}
			else if (expr.isConstBool())
			{
				expr.printJava(sb, env);
			}
			else
			{
				expr.printVarJava(sb, env);
				sb.append(".getBoolean()");
			}
		}
		else
		{
			if (isTrue)
			{
				sb.append("true");
			}
			else
			{
				sb.append("false");
			}
		}
	}
}
