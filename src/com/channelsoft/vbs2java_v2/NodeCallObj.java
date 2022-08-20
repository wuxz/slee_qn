package com.channelsoft.vbs2java_v2;

/**
 * objectµ÷ÓÃÓï¾ä
 * </p>
 * obj.fun1();»ò obj.property() = ...
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeCallObj extends Node
{
	NodeComplex callobj = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		env.pushBackT();
		callobj = NodeComplex.parseWithCheck(env, new CG());
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		callobj.print(sb, env);
	}

	private class CG implements CheckComplexGrammer
	{

		public boolean check(NodeComplex nc)
		{
			if (nc.expr instanceof ExprCallObj)
			{
				ExprCallObj co = (ExprCallObj) nc.expr;
				if (co.subexprlist != null || co.right != null)
				{// obj(...) or obj.fun(...) || obj = a
					return true;
				}
			}
			return false;
		}

	}

}
