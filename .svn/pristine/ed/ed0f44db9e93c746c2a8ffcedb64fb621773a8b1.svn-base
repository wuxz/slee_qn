package com.channelsoft.vbs2java_v2;

public class ExprArglistForCase extends ExprArglist
{

	@Override
	void parse(ParserEnv env) throws Exception
	{
		EC ec = new EC();
		Expr expr = null;
		while ((expr = Expr.g_parse(env, ec)) != null)
		{
			ArgNode an = new ArgNode();
			an.varname = expr;
			arglist.add(an);

			if (isBreak(env))
			{
				break;
			}
		}

		if (!hasArgument())
		{// case 语句必须有参数
			throw new ParserException(env, ExceptionDesc.DS004_CASE);
		}
	}

}
