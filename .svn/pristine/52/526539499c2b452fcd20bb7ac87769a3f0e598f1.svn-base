package com.channelsoft.vbs2java_v2;

import java.util.Iterator;
import java.util.Vector;

/**
 * 复合表达式。可以是变量、数组、对象、自定义方法等。 表达式结构如：xxx.fun(...)(...).fun(...);
 * 
 * @author dragon.huang
 */
public class ExprComplex extends Expr
{
	Vector<Expr> subexprlist = null;

	protected void parseSubExpr(ParserEnv env) throws Exception
	{
		Expr subexpr = null;
		while ((subexpr = Expr.createSubExpr(env)) != null)
		{
			if (this.subexprlist == null)
			{
				this.subexprlist = new Vector<Expr>();
			}
			this.subexprlist.add(subexpr);
		}
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (subexprlist != null)
		{
			Iterator<Expr> itor = subexprlist.iterator();
			while (itor.hasNext())
			{
				Expr expr = itor.next();
				expr.left = this;
				if (expr instanceof ExprDotFun)
				{
					if (itor.hasNext() || right == null)
					{// get prop
						((ExprDotFun) expr).printJavaForGetProp(sb, env);
					}
					else
					{// set prop
						((ExprDotFun) expr).right = this.right;
						((ExprDotFun) expr).printJavaForSetProp(sb, env);
					}
				}
				else
				{// ExprArglistForArr || ExprArglistForCallFun
					if (itor.hasNext() || right == null)
					{// get prop
						if (this.exprTk.isObj() || this.exprTk.isInternalObj())
						{
							if (exprTk.str.equals("sleesession")
									|| exprTk.str.equals("sleeapp"))
							{
								sb.append(".content(");
							}
							else
							{
								sb.append(".getObjectProperty(\"\", ");
							}
						}
						else
						{
							sb.append(".getElementAt(");
						}
						((ExprArglist) expr).printJavaForGetProp(sb, env);
						sb.append(")");
					}
					else
					{// set prop
						if (this.exprTk.isObj() || this.exprTk.isInternalObj())
						{
							if (exprTk.str.equals("sleesession")
									|| exprTk.str.equals("sleeapp"))
							{
								sb.append(".setcontent(new Variant[]{");
							}
							else
							{
								sb.append(".setObjectProperty(\"\", new Variant[]{");
							}
						}
						else
						{
							sb.append(".setElementAt(new Variant[]{");
						}
						((ExprArglist) expr).printJavaForSetProp(sb, env);
						sb.append(", ");
						right.printVarJava(sb, env);
						sb.append("})");
					}
				}
			}
		}
		else if (right != null)
		{// var/obj = ...
			sb.append(".setValue(");
			right.printJava(sb, env);
			sb.append(")");
		}
	}
}
