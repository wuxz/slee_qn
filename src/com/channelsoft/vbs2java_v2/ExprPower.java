package com.channelsoft.vbs2java_v2;

public class ExprPower extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".power(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
