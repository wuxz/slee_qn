package com.channelsoft.vbs2java_v2;

/**
 * sub∂®“Â
 * 
 * @author dragon.huang
 */
public class NodeSub extends NodeSefFun
{
	@Override
	protected void check(ParserEnv env) throws Exception
	{
		if (endfun instanceof NodeEndSub)
		{
		}
		else
		{
			throw new ParserException(env, ExceptionDesc.DS004_SUB_NO_END);
		}
	}

	@Override
	protected void printDefine(StringBuilder sb, ParserEnv env)
			throws Exception
	{
		sb.append(env.indent()).append("void ").append(
				env.e_funname(funname.str));
		arglist.printJava(sb, env);
		sb.append(" throws Exception");
		sb.append("\r\n");
		sb.append(env.lBracket());
		printVar(sb, env);
		if (statements != null)
		{
			statements.printJava(sb, env);
		}
		sb.append(env.rBracket());
		sb.append("\r\n");
	}
}
