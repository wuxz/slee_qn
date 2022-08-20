package com.channelsoft.vbs2java_v2;

/**
 * 复合语句。可以是调用变量、数组、对象、方法等。 语法结构。表达式结构如：xxx.fun(...)(...).fun(...)[=...];
 * 
 * @author dragon.huang
 */
public class NodeComplex extends Node
{
	Expr expr = null;

	/**
	 * 当前语句是否作为一个主句。当NodeComplex在For语句中使用时，作为一个子句。
	 */
	boolean isMainStatement = true;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		expr = Expr.g_parse(env, new EC());
		if (expr == null)
		{
			throw new ParserException(env, env.nextToken(),
					ExceptionDesc.DS004_NOT_FUN_OR_VAR);
		}
		else if (!expr.isReturnVar())
		{
			throw new ParserException(env, expr.exprTk,
					ExceptionDesc.DS004_NOT_FUN_OR_VAR);
		}

		if (env.nextToken().type == ParserConst.KT_EQ)
		{
			if (isMainStatement)
			{
				// 将=左表达式设为语法信息，解析=右表达式时可能使用
				env.setGrammerInfo(expr);
			}
			expr.right = Expr.g_parse(env);
			if (expr.right == null)
			{
				throw new ParserException(env,
						ExceptionDesc.DS004_NO_ASSIGNEXPR);
			}
		}
		else
		{
			env.pushBackT();
		}
	}

	/**
	 * 解析并检查符合表达式的语法
	 * 
	 * @param env
	 * @param cg
	 *            表达式应满足的语法
	 * @return
	 * @throws Exception
	 */
	static NodeComplex parseWithCheck(ParserEnv env, CheckComplexGrammer cg)
			throws Exception
	{
		return parseWithCheck(env, cg, true);
	}

	/**
	 * 解析并检查符合表达式的语法
	 * 
	 * @param env
	 * @param cg
	 *            表达式应满足的语法
	 * @return
	 * @throws Exception
	 */
	static NodeComplex parseWithCheck(ParserEnv env, CheckComplexGrammer cg,
			boolean isMainStatement) throws Exception
	{
		NodeComplex na = new NodeComplex();
		na.isMainStatement = isMainStatement;
		na.parse(env);
		if (cg != null && !cg.check(na))
		{
			throw new ParserException(env, na.expr.exprTk,
					ExceptionDesc.DS004_COMPLEX);
		}
		return na;
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (expr.right == null)
		{// obj ; var 不能独立成句
			if (expr instanceof ExprVar || expr instanceof ExprArr)
			{
				if (((ExprComplex) expr).subexprlist == null)
				{
					throw new ParserException(env, expr.exprTk,
							ExceptionDesc.DS004_COMPLEX_NOT_FUN);
				}
			}
		}
		sb.append(env.indent());
		expr.printJava(sb, env);
		sb.append(";");
	}

	class EC implements ExprContext
	{

		public boolean checkEndExpr(Token tk)
		{
			return (tk.type == ParserConst.KT_EQ);
		}

	}

}
