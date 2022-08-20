package com.channelsoft.vbs2java_v2;

public class ExprDiv extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.isConstNum())
		{
			if (left.isConstInt() && right.isConstInt())
			{
				if (left.exprTk.isConstInt())
				{
					left.printJava(sb, env);
					sb.append(".0");
					sb.append("/");
					right.printJava(sb, env);
				}
				else if (right.exprTk.isConstInt())
				{
					left.printJava(sb, env);
					sb.append("/");
					right.printJava(sb, env);
					sb.append(".0");
				}
				else
				{
					left.printJava(sb, env);
					sb.append("*1.0");
					sb.append("/");
					right.printJava(sb, env);
				}
			}
			else
			{
				left.printJava(sb, env);
				sb.append("/");
				right.printJava(sb, env);
			}
		}
		else
		{
			left.printVarJava(sb, env);
			sb.append(".divide(");
			right.printJava(sb, env);
			sb.append(")");
		}
	}
}
