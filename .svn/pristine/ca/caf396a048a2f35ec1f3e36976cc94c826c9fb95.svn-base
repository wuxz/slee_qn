package com.channelsoft.vbs2java_v2;

/**
 * 赋值语句。只针对知识变量。普通变量的赋值语句在ExprVar中实现。
 * 
 * @author dragon.huang
 */
public class NodeAssign extends Node
{
	NodeComplex complex = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		env.pushBackT();
		complex = NodeComplex.parseWithCheck(env, new CG());
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		complex.print(sb, env);
	}

	private class CG implements CheckComplexGrammer
	{

		public boolean check(NodeComplex nc)
		{
			return (nc.expr.right != null);
		}

	}
}
