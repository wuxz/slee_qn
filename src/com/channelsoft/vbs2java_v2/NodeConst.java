package com.channelsoft.vbs2java_v2;

/**
 * const a = 1
 * 
 * @author dragon.huang
 */
public class NodeConst extends Node
{
	Expr expr = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		expr = Expr.g_parse(env, new EC());
		if (expr == null)
		{
			throw new ParserException(env, ExceptionDesc.DS003_CONST);
		}
		else if (!(expr instanceof ExprVar && ((ExprVar) expr).isVar()))
		{
			throw new ParserException(env, expr.exprTk,
					ExceptionDesc.DS003_CONST);
		}

		if (env.nextToken().type == ParserConst.KT_EQ)
		{
			// 将=左表达式设为语法信息，解析=右表达式时可能使用
			env.setGrammerInfo(expr);
			expr.right = Expr.g_parse(env);
			if (expr.right == null)
			{
				throw new ParserException(env, ExceptionDesc.DS004_CONST);
			}
		}
		else
		{
			throw new ParserException(env, ExceptionDesc.DS004_CONST);
		}
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
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
