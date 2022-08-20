package com.channelsoft.vbs2java_v2;

public class ExprLogicGT extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".moreThan(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
