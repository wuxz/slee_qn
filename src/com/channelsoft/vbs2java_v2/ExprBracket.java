package com.channelsoft.vbs2java_v2;

/**
 * 括号表达式，用于参与四则运算。方法/数组/obj调用的括号单独处理
 * 
 * @author dragon.huang
 */
public class ExprBracket extends Expr
{
	Expr expr = null;

	protected static ExprBracket parseBracket(ParserEnv env) throws Exception
	{
		ExprBracket eb = new ExprBracket();

		eb.expr = g_parse(env, eb.new EC());

		if (eb.expr == null)
		{
			throw new ParserException(env, ExceptionDesc.DS002_BRACKET_NO_EXPR);
		}
		Token tk = env.nextToken();
		if (tk.type != ParserConst.KT_RIGHT_BRACKET)
		{
			throw new ParserException(env, tk, ExceptionDesc.DS002_BRACKET_NO_END);
		}
		return eb;
	}

	@Override
	protected boolean isConstBool()
	{
		return expr.isConstBool();
	}

	@Override
	protected boolean isConstNum()
	{
		return expr.isConstNum();
	}

	@Override
	protected boolean isConstStr()
	{
		return expr.isConstStr();
	}

	@Override
	protected boolean isConstInt()
	{
		return expr.isConstInt();
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append("(");
		expr.printJava(sb, env);
		sb.append(")");
	}

	class EC implements ExprContext
	{

		public boolean checkEndExpr(Token tk)
		{
			return (tk.type == ParserConst.KT_RIGHT_BRACKET);
		}

	}
}
