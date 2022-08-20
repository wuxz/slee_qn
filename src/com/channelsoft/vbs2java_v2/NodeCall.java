package com.channelsoft.vbs2java_v2;

/**
 * ����������䡣�������ڲ��������Զ��庯����
 * </p>
 * �磺call fun1(); �� fun1();
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeCall extends Node
{
	NodeComplex callfun = null;

	/**
	 * ������������Ƿ���call���š�
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
		{// call���ż�顣call�����������÷������Զ��巽��
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
