package com.channelsoft.vbs2java_v2;

/**
 * 变量。明确定义的变量、未明确定义变量调用、变量的赋值运算、为明确定义方法调用(在声明之前调用方法)、未明确定义数组调用(包括设置赋值)、未明确定义object调用(包括设置属性)。
 * 
 * @author dragon.huang
 */
public class ExprVar extends ExprComplex
{
	static ExprVar parseVar(ParserEnv env) throws Exception
	{
		ExprVar ev = new ExprVar();
		ev.exprTk = env.currToken();
		ev.parseSubExpr(env);
		return ev;
	}

	/**
	 * 是否为变量
	 * 
	 * @return
	 */
	boolean isVar()
	{
		return (this.exprTk.isVar() && this.left == null && this.right == null && this.subexprlist == null);
	}

	/**
	 * 是否为变量的赋值运算
	 * 
	 * @return
	 */
	boolean isVarAssign()
	{
		return (this.exprTk.isVar() && this.left == null && this.right != null && this.subexprlist == null);
	}

	/**
	 * 是否为数组的Redim
	 * 
	 * @return
	 */
	boolean isArrRedefine()
	{
		if (this.exprTk.isArr())
		{
			if (subexprlist.size() == 1
					&& subexprlist.elementAt(0) instanceof ExprArglistForArr)
			{
				return ((ExprArglistForArr) subexprlist.elementAt(0)).arglist
						.size() > 0;
			}
		}
		else if (this.exprTk.isVar() && this.left == null && this.right == null
				&& subexprlist != null)
		{
			if (subexprlist.size() == 1
					&& subexprlist.elementAt(0) instanceof ExprArglistForArr)
			{
				return ((ExprArglistForArr) subexprlist.elementAt(0)).arglist
						.size() > 0;
			}
		}

		return false;
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		boolean isSefDefFun = env.keyword().isSefDefFun(this.exprTk.str);
		if (isSefDefFun)
		{// 自定义函数调用
			Expr arglist = null;
			sb.append(env.e_funname(exprTk.str));
			sb.append("(");
			if (this.subexprlist != null)
			{
				arglist = this.subexprlist.firstElement();
				if (arglist instanceof ExprArglist)
				{
					this.subexprlist.removeElementAt(0);
					if (this.subexprlist.size() == 0)
					{
						this.subexprlist = null;
					}
					ExprArglist def = env.keyword().getSefDefFun(exprTk.str).arglist;
					ExprArglistForCallFun.printWithCheck(sb, env, def,
							(ExprArglist) arglist);
				}
			}
			sb.append(")");
		}
		else
		{
			sb.append(this.exprTk.e_str());
		}

		if (this.subexprlist != null)
		{// 未明确定义数组或object调用：var(1)(2).fun(3)...
			super.printJava(sb, env);
		}
		else
		{
			if (this.right != null)
			{
				if (isSefDefFun)
				{
					throw new ParserException(env, exprTk, ExceptionDesc.DS004_FUNCTION_USAGE);
				}
				else
				{// 变量赋值：var = ...
					sb.append(".setValue(");
					this.right.printJava(sb, env);
					sb.append(")");
				}
			}
		}
	}
}
