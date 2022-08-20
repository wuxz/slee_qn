package com.channelsoft.vbs2java_v2;

import java.util.Iterator;

/**
 * redim语句的参数列表。参数只能是数组。
 * 
 * @author dragon.huang
 */
public class ExprArglistForRedim extends ExprArglist
{

	boolean isPreserve = false;

	ExprArglistForRedim(boolean isPreserve)
	{
		this.isPreserve = isPreserve;
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
			if (expr instanceof ExprVar && ((ExprVar) expr).isArrRedefine())
			{// 未明确定义的数组, redim a(1,2,b,...)
			}
			else if (expr instanceof ExprArr
					&& ((ExprArr) expr).isArrRedefine())
			{// 明确定义的数组, redim a(1,2,b,...)
			}
			else if (expr == null)
			{
				throw new ParserException(env, ExceptionDesc.DS004_REDIM);
			}
			else
			{
				throw new ParserException(env, expr.exprTk,
						ExceptionDesc.DS006_REDIM);
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
		{// 变体在外部定义；这里进行数组初始化
			ArgNode an = itor.next();
			if (an.varname instanceof ExprArr)
			{
				ExprArr ea = (ExprArr) an.varname;
				sb.append(env.indent()).append(ea.exprTk.str).append(
						".redimVariant(");
				ea.arglist.printJavaForDefine(sb, env);
				sb.append(", cci, ").append(isPreserve ? "true" : "false")
						.append(");");
			}
			else
			{
				ExprVar ev = (ExprVar) an.varname;
				sb.append(env.indent()).append(ev.exprTk.str).append(
						".redimVariant(");
				ExprArglistForArr arglist = (ExprArglistForArr) ev.subexprlist
						.elementAt(0);
				arglist.printJavaForDefine(sb, env);
				sb.append(", cci, ").append(isPreserve ? "true" : "false")
						.append(");");
			}
		}
	}
}
