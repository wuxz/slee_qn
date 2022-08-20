package com.channelsoft.vbs2java_v2;

/**
 * 方法调用语句。适用于内部函数和自定义函数。
 * </p>
 * 如：call fun1(); 或 fun1();
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeCall extends Node
{
	NodeComplex callfun = null;

	/**
	 * 方法调用语句是否有call符号。
	 */
	boolean hasCallSymbol = false;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		if (env.getPreSub().type == ParserConst.KT_CALL)
		{
			hasCallSymbol = true;
		}
		else
		{
			env.pushBackT();
		}

		callfun = NodeComplex.parseWithCheck(env, null);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (hasCallSymbol)
		{// call符号检查。call后面必须跟内置方法或自定义方法
			Token funname = callfun.expr.exprTk;
			if (funname.isInternalFun()
					|| env.keyword().isSefDefFun(funname.str))
			{
			}
			else
			{
				throw new ParserException(env, funname,
						ExceptionDesc.DS003_CALL);
			}
		}
		callfun.print(sb, env);
	}
}
