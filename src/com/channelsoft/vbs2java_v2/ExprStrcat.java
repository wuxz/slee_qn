package com.channelsoft.vbs2java_v2;

public class ExprStrcat extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (left.isReturnVar())
		{
			left.printStrJava(sb, env);
		}
		else if (left.isConstBool() || left.isConstNum())
		{
			left.printJava(sb, env);
			sb.append("+\"\"");
		}
		else
		{// ConstStr
			left.printJava(sb, env);
		}

		sb.append("+");

		if (right.isReturnVar())
		{
			right.printStrJava(sb, env);
		}
		else if ((right.isConstBool() || right.isConstNum())
				&& !(right.exprTk.isConstBool() || right.exprTk.isConstNum()))
		{
			sb.append("(");
			right.printJava(sb, env);
			sb.append(")");
		}
		else
		{
			right.printJava(sb, env);
		}
	}
}
