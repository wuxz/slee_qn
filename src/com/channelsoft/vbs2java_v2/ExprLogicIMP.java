package com.channelsoft.vbs2java_v2;

public class ExprLogicIMP extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".imp(");
		right.printVarJava(sb, env);
		sb.append(")");
	}
}
