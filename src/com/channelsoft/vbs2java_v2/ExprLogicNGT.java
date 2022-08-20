package com.channelsoft.vbs2java_v2;

public class ExprLogicNGT extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".notGT(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
