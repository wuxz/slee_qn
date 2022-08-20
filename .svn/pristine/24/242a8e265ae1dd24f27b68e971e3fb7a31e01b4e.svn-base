package com.channelsoft.vbs2java_v2;

/**
 * Function的设置返回值节点。
 * 
 * @author dragon.huang
 */
public class NodeFunctionReturn extends Node
{
	Token funname = null;

	Expr right = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		String grammerInfo = ((NodeSefFun.GrammerInfo) env.check(new MSC()).grammerinfo).funname;

		funname = env.currToken();
		if (!grammerInfo.equals(funname.str))
		{
			throw new ParserException(env, funname, ExceptionDesc.DS004_FUNCTION_USAGE);
		}

		if (env.nextToken().type == ParserConst.KT_EQ)
		{
			//将=左表达式设为语法信息，解析=右表达式时可能使用
			env.setGrammerInfo(funname);
			right = Expr.g_parse(env);
			if (right == null)
			{
				throw new ParserException(env, funname,
						ExceptionDesc.DS004_FUNCTION_RETURN);
			}
		}
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		String retVal = NodeFunction.returnVal(funname.str);

		sb.append(env.indent()).append(retVal).append(".setValue(");
		right.printJava(sb, env);
		sb.append(");");
	}

	private class MSC implements MainStatementContext
	{

		public boolean check(Token tk, Object grammerinfo)
		{
			return (tk.type == ParserConst.KT_FUNCTION);
		}

	}

	class EC implements ExprContext
	{

		public boolean checkEndExpr(Token tk)
		{
			return (tk.type == ParserConst.KT_EQ);
		}

	}

}
