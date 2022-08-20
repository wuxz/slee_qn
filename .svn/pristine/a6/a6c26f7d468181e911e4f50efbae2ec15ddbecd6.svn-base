package com.channelsoft.vbs2java_v2;

/**
 * 数字。包括vbs定义的常量。
 * 
 * @author dragon.huang
 */
public class ExprNum extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (exprTk != null)
		{
			String num = "";
			if (exprTk.type == ParserConst.KT_VBCONST)
			{
				num = ParserWord.vbConst(exprTk.str);
			}
			else
			{
				num = exprTk.str;
			}
			sb.append(num);
			if (num.indexOf('.') < 0 && num.length() > 9)
			{
				sb.append("l");
			}
		}
	}
}
