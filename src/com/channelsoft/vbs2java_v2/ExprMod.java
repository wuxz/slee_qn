package com.channelsoft.vbs2java_v2;

public class ExprMod extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append("(");
		left.printNumJava(sb, env);
		sb.append("%");
		right.printNumJava(sb, env);
		sb.append(")");
	}
}
