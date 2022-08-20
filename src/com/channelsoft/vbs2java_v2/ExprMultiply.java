package com.channelsoft.vbs2java_v2;

public class ExprMultiply extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.isConstNum())
		{
			left.printJava(sb, env);
			sb.append("*");
			right.printJava(sb, env);
		}
		else if (!left.isReturnVar() && right.isReturnVar())
		{
			right.printJava(sb, env);
			sb.append(".multiply(");
			left.printJava(sb, env);
			sb.append(")");
		}
		else
		{
			left.printVarJava(sb, env);
			sb.append(".multiply(");
			right.printJava(sb, env);
			sb.append(")");
		}
	}
}
