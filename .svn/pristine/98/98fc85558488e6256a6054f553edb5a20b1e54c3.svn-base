package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

/**
 * dim语句的参数列表。参数可以是变体或数组定义。
 * 
 * @author dragon.huang
 */
class ExprArglistForDim extends ExprArglist
{

	private boolean hasDefineArr = false;

	/**
	 * 是否定义了数组
	 * 
	 * @return
	 */
	boolean hasDefineArr()
	{
		return hasDefineArr;
	}

	@Override
	void parse(ParserEnv env) throws Exception
	{
		EC ec = new EC();
		Expr expr = null;
		while (true)
		{
			expr = Expr.g_parse(env, ec);
			ArgNode an = new ArgNode();
			if (expr instanceof ExprVar && ((ExprVar) expr).isVar())
			{// 变量定义, dim a
			}
			else if (expr instanceof ExprArr && ((ExprArr) expr).isArrDefine())
			{// 表达式定义, dim a(1,2..)
				hasDefineArr = true;
			}
			else if (expr instanceof ExprCallObj
					&& ((ExprCallObj) expr).isObj())
			{// 表达式定义, dim a。object可以被重定义为普通变体，但不能被重定义为object。
			}
			else if (expr == null)
			{
				throw new ParserException(env, env.nextToken(),
						ExceptionDesc.DS004_DIM);
			}
			else
			{
				throw new ParserException(env, expr.exprTk,
						ExceptionDesc.DS003_NAME_NORMS);
			}

			an.varname = expr;
			arglist.add(an);

			// 去掉‘，’
			if (env.nextToken().type != ParserConst.KT_COMMA)
			{
				env.pushBackT();
				break;
			}
		}
		// skipRightBracket(env);

		Token tk = env.readNextToken();
		if (!(tk.isLineEnd() || tk.isSkip()))
		{
			throw new ParserException(env, tk, ExceptionDesc.DS003_NAME_NORMS);
		}
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{// 变体在外部定义；这里进行数组初始化.
			ArgNode an = itor.next();
			if (an.varname instanceof ExprArr)
			{
				((ExprArr) an.varname).printJavaForDefine(sb, env);
				sb.append("\r\n");
			}
		}
	}
}
