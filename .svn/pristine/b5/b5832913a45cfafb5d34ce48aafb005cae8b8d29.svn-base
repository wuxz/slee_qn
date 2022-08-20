package com.channelsoft.vbs2java_v2;

public class ExprArr extends ExprComplex
{
	ExprArglistForArr arglist = null;

	static ExprArr parseForDefine(ParserEnv env) throws Exception
	{
		ExprArr ea = new ExprArr();
		ea.exprTk = env.currToken();
		ea.arglist = new ExprArglistForArr();
		ea.arglist.parseForDefine(env);
		return ea;
	}

	static ExprArr parseForCall(ParserEnv env) throws Exception
	{
		ExprArr ea = new ExprArr();
		ea.exprTk = env.currToken();
		ea.arglist = new ExprArglistForArr();
		ea.arglist.parseForCall(env);
		if (env.getCurSub().type != ParserConst.KT_REDIM)
		{
			ea.parseSubExpr(env);
		}
		return ea;
	}

	private boolean isArr()
	{
		return (subexprlist == null);
	}

	boolean isArrDefine()
	{
		return (isArr() && arglist.isdefined());
	}
	
	boolean isArrRedefine()
	{
		return (isArr() && arglist.hasArgument());
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (right != null)
		{// arr(1) = ...
			printJavaForSetProp(sb, env);
		}
		else if (this.arglist.hasArgument() || this.subexprlist != null)
		{// arr(1)(2).fun(3)... || a = arr(...)
			printJavaForGetProp(sb, env);
			super.printJava(sb, env);
		}
		else
		{// foreach ... in arr
			sb.append(exprTk.e_str());
		}
	}

	void printJavaForDefine(StringBuilder sb, ParserEnv env) throws Exception
	{// dim arr(1,2...) : arr.dimVariant(arglist..., cci, false);
		sb.append(env.indent()).append(exprTk.e_str()).append(".dimVariant(");
		arglist.printJavaForDefine(sb, env);
		sb.append(", cci, false);");
	}

	void printJavaForSetProp(StringBuilder sb, ParserEnv env) throws Exception
	{// arr(1,2..) = ... : arr.setElementAt(new Variant[]{arglist..., prop});
		if (arglist.arglist == null || arglist.arglist.size() == 0)
		{
			throw new ParserException(env, this.exprTk, ExceptionDesc.DS003_SET_ARR_PROP);
		}
		sb.append(exprTk.e_str()).append(".setElementAt(new Variant[]{");
		arglist.printJavaForSetProp(sb, env);
		sb.append(", ");
		right.printVarJava(sb, env);
		sb.append("})");
	}

	void printJavaForGetProp(StringBuilder sb, ParserEnv env) throws Exception
	{// a = arr(1,2...) : arr.getElementAt(arglist...)
		if (arglist.arglist == null || arglist.arglist.size() == 0)
		{// 整个数组作为表达式
			sb.append(exprTk.e_str());
		}
		else
		{
			sb.append(exprTk.e_str()).append(".getElementAt(");
			arglist.printJavaForGetProp(sb, env);
			sb.append(")");
		}
	}
}
