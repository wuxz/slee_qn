package com.channelsoft.vbs2java_v2;

public class ExprLogicOR extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.isConstBool())
		{
			left.printJava(sb, env);
			sb.append("||");
			right.printJava(sb, env);
		}
		else
		{
			left.printVarJava(sb, env);
			sb.append(".or(");
			right.printVarJava(sb, env);
			sb.append(")");
		}
	}
}
