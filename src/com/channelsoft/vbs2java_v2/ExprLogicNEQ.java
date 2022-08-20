package com.channelsoft.vbs2java_v2;

public class ExprLogicNEQ extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append("!");
		left.printVarJava(sb, env);
		sb.append(".equal(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
