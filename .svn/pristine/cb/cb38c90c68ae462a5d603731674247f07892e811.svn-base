package com.channelsoft.vbs2java_v2;

public class NodeElseif extends Node
{
	ExprCondition condition = null;

	Node then = null;

	protected void parse(ParserEnv env) throws Exception
	{
		env.check(new SC());
		Token preTk = env.currToken();
		
		condition = ExprCondition.parse(env);
		env.setPreSub(preTk);

		then = createSubStatement(env, ParserConst.KT_THEN);
	}

	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append("else if(");
		condition.printJava(sb, env);
		sb.append(")").append("\r\n");
		then.print(sb, env);
	}
	
	private class SC implements StatementContext
	{

		public boolean checkMainStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_IF);
		}

		public boolean checkEndSubStatement(Token tk)
		{
			return false;
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_THEN || tk.type == ParserConst.KT_ELSEIF);
		}
		
	}
}
