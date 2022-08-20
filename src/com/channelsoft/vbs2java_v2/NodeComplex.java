package com.channelsoft.vbs2java_v2;

/**
 * ������䡣�����ǵ��ñ��������顢���󡢷����ȡ� �﷨�ṹ�����ʽ�ṹ�磺xxx.fun(...)(...).fun(...)[=...];
 * 
 * @author dragon.huang
 */
public class NodeComplex extends Node
{
	Expr expr = null;

	/**
	 * ��ǰ����Ƿ���Ϊһ�����䡣��NodeComplex��For�����ʹ��ʱ����Ϊһ���Ӿ䡣
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
				// ��=����ʽ��Ϊ�﷨��Ϣ������=�ұ��ʽʱ����ʹ��
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
	 * �����������ϱ��ʽ���﷨
	 * 
	 * @param env
	 * @param cg
	 *            ���ʽӦ������﷨
	 * @return
	 * @throws Exception
	 */
	static NodeComplex parseWithCheck(ParserEnv env, CheckComplexGrammer cg)
			throws Exception
	{
		return parseWithCheck(env, cg, true);
	}

	/**
	 * �����������ϱ��ʽ���﷨
	 * 
	 * @param env
	 * @param cg
	 *            ���ʽӦ������﷨
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
		{// obj ; var ���ܶ����ɾ�
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
