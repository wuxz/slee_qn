package com.channelsoft.vbs2java_v2;

/**
 * 函数表达式，用于内部函数和自定义函数
 * 
 * @author dragon.huang
 */
public class ExprCallFun extends ExprComplex
{
	static ExprCallFun parseCallFun(ParserEnv env) throws Exception
	{
		ExprCallFun cf = new ExprCallFun();
		cf.exprTk = env.currToken();
		cf.arglist = new ExprArglistForCallFun();
		cf.arglist.parse(env);
		cf.parseSubExpr(env);
		return cf;
	}

	ExprArglistForCallFun arglist = null;

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.exprTk.isInternalFun())
		{// 内部函数：internalMethod.fun(new Variant[]{var1, var2, ...});
			String funname = exprTk.str.equals("int") ? "iint" : exprTk.str;
			sb.append(ParserWord.SLEE_INTERNAL_FUN).append(".").append(funname);
		}
		else
		{// 自定义函数：fun(var1, var2, ...)
			sb.append(env.e_funname(exprTk.str));
		}
		sb.append("(");
		if (this.exprTk.isInternalFun())
		{
			arglist.printJavaForGetProp(sb, env);
		}
		else
		{// 自定义函数
			ExprArglist def = env.keyword().getSefDefFun(exprTk.str).arglist;
			ExprArglistForCallFun.printWithCheck(sb, env, def, arglist);
		}
		sb.append(")");

		if (subexprlist != null)
		{// fun(1)(2).fun(3)...
			super.printJava(sb, env);
		}
		else
		{// fun(1) = ...
			if (this.right != null)
			{
				throw new ParserException(env, exprTk,
						ExceptionDesc.DS012_FUN_ASSIGN);
			}
		}
	}

	void superPrintJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		super.printJava(sb, env);
	}
}
