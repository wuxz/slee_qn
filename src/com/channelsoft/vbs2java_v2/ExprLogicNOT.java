package com.channelsoft.vbs2java_v2;

public class ExprLogicNOT extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if(right.isConstBool())
		{
			sb.append("!");
			sb.append("(");
			right.printJava(sb, env);
			sb.append(")");
		}
		else
		{
			right.printVarJava(sb, env);
			sb.append(".not()");
		}
	}
}
