package com.channelsoft.vbs2java_v2;

/**
 * dim {arglist: a, arr(1),...}
 * 
 * @author dragon.huang
 */
public class NodeDim extends Node
{

	ExprArglistForDim arglist = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		arglist = new ExprArglistForDim();
		arglist.parse(env);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		arglist.printJava(sb, env);
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (this.arglist.hasDefineArr())
		{
			if (linenum != -1)
			{
				sb.append(env.indent()).append("cci.setCurrentLine(").append(
						linenum).append(");\r\n");
			}

			print(sb, env);
		}

		if (sibling != null)
		{
			sibling.printJava(sb, env);
		}
	}
}
