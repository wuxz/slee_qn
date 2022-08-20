package com.channelsoft.vbs2java_v2;

public class ExprLogicNLT extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".notLT(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
