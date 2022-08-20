package com.channelsoft.vbs2java_v2;

public class ExprLogicEQV extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".eqv(");
		right.printVarJava(sb, env);
		sb.append(")");
	}
}
