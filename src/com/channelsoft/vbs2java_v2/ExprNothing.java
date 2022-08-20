package com.channelsoft.vbs2java_v2;

public class ExprNothing extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append("new Variant(cci)");
	}
}
