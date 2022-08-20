package com.channelsoft.vbs2java_v2;

/**
 * vbs中的is 表示两个obj是同一个
 * 
 * @author dragon.huang
 */
public class ExprLogicIS extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append("(");
		left.printVarJava(sb, env);
		sb.append(".getObject() == ");
		right.printVarJava(sb, env);
		sb.append(".getObject()");
		sb.append(")");
	}
}
