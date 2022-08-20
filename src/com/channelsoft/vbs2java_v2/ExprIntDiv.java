package com.channelsoft.vbs2java_v2;

public class ExprIntDiv extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		left.printVarJava(sb, env);
		sb.append(".intDivide(");
		right.printJava(sb, env);
		sb.append(")");
	}
}
