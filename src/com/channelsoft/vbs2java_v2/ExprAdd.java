package com.channelsoft.vbs2java_v2;

public class ExprAdd extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.isConstStr() || this.isConstNum())
		{
			left.printJava(sb, env);
			sb.append("+");
			right.printJava(sb, env);
		}
		else if (!left.isReturnVar() && right.isReturnVar())
		{
			right.printJava(sb, env);
			sb.append(".plus(");
			left.printJava(sb, env);
			sb.append(")");
		}
		else
		{
			left.printVarJava(sb, env);
			sb.append(".plus(");
			right.printJava(sb, env);
			sb.append(")");
		}
	}
}
