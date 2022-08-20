package com.channelsoft.vbs2java_v2;

public class ExprMinus extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (exprTk.type == ParserConst.KT_MINUX)
		{// 负数运算
			if (right.isConstNum())
			{
				sb.append("(");
				sb.append("-");
				right.printJava(sb, env);
				sb.append(")");
			}
			else
			{
				right.printVarJava(sb, env);
				sb.append(".multiply(-1)");
			}
		}
		else
		{// 减法运算
			if (this.isConstNum())
			{
				left.printJava(sb, env);
				sb.append("-");
				right.printJava(sb, env);
			}
			else
			{
				left.printVarJava(sb, env);
				sb.append(".minus(");
				right.printJava(sb, env);
				sb.append(")");
			}
		}
	}
}
