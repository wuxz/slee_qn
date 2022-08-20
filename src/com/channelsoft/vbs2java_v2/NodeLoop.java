package com.channelsoft.vbs2java_v2;

public class NodeLoop extends Node
{
	ExprCondition condition = null;

	Token loopType = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		Token preTk = env.check(new SC());

		if (preTk.type == ParserConst.KT_DO)
		{
			Token tk = env.nextToken();
			if (tk.type == ParserConst.KT_WHILE || tk.type == ParserConst.KT_UNTIL)
			{
				loopType = tk;
				condition = ExprCondition.parse(env);
			}
			else
			{// do ... loop 后面不带while/until，缺省为while(true)
				env.pushBackT();
				loopType = new Token();
				loopType.type = ParserConst.KT_WHILE;
				condition = ExprCondition.createTrue();
			}
		}
	}

	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (condition != null)
		{
			if (loopType.type == ParserConst.KT_WHILE)
			{
				sb.append("while(");
				condition.printJava(sb, env);
				sb.append(")");
			}
			else
			{
				sb.append("while(!(");
				condition.printJava(sb, env);
				sb.append("))");
			}
			sb.append(";");
		}
	}

	private class SC implements StatementContext
	{

		public boolean checkMainStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_DO);
		}

		public boolean checkEndSubStatement(Token tk)
		{
			return false;
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_WHILE || tk.type == ParserConst.KT_UNTIL || tk.type == ParserConst.KT_DO);
		}
	}

}
