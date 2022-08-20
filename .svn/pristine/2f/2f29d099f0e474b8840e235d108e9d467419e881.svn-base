package com.channelsoft.vbs2java_v2;

/**
 * function定义
 * 
 * @author dragon.huang
 */
public class NodeFunction extends NodeSefFun
{
	/**
	 * 方法返回值
	 * 
	 * @param funname
	 * @return
	 */
	static String returnVal(String funname)
	{
		return "_channelsoft_" + funname + "_retval";
	}

	@Override
	protected void check(ParserEnv env) throws Exception
	{
		if (endfun instanceof NodeEndFunction)
		{
		}
		else
		{
			throw new ParserException(env, ExceptionDesc.DS004_FUNCTION_NO_END);
		}
	}

	@Override
	protected void printDefine(StringBuilder sb, ParserEnv env)
			throws Exception
	{
		String retVal = returnVal(funname.str);

		sb.append(env.indent()).append("Variant ").append(
				env.e_funname(funname.str));
		arglist.printJava(sb, env);
		sb.append(" throws Exception");
		sb.append("\r\n");
		sb.append(env.lBracket());
		sb.append(env.indent()).append(
				"Variant " + retVal + " = new Variant(cci);\r\n");
		printVar(sb, env);
		if (statements != null)
		{
			statements.printJava(sb, env);
		}
		sb.append(env.indent()).append("return ").append(retVal)
				.append(";\r\n");
		sb.append(env.rBracket());
		sb.append("\r\n");
	}
}
