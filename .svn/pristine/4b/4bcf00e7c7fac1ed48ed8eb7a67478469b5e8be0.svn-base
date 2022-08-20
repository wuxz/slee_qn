package com.channelsoft.vbs2java_v2;

public class ExprLogicXOR extends Expr
{
	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.isConstBool())
		{
			sb.append("(");
			if (!left.exprTk.isConstBool())
			{
				sb.append("(");
				left.printJava(sb, env);
				sb.append(")");
			}
			else
			{
				left.printJava(sb, env);
			}
			sb.append("^");
			
			if (!right.exprTk.isConstBool())
			{
				sb.append("(");
				right.printJava(sb, env);
				sb.append(")");
			}
			else
			{
				right.printJava(sb, env);
			}
			sb.append(")");
		}
		else
		{
			left.printVarJava(sb, env);
			sb.append(".xor(");
			right.printVarJava(sb, env);
			sb.append(")");
		}
	}
}
