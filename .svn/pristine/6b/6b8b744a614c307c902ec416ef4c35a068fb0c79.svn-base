package com.channelsoft.vbs2java_v2;

public class ExprLogicEQ extends Expr
{
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".equal(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
