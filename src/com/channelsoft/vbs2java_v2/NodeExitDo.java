package com.channelsoft.vbs2java_v2;

import com.channelsoft.vbs2java_v2.NodeDo.GrammerInfo;

public class NodeExitDo extends Node
{
	GrammerInfo gi = null;

	boolean needBreakTag = false;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		env.check(new MSC());
	}

	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append("break");
		if (needBreakTag && gi != null)
		{
			sb.append(" ").append(gi.breakTag);
		}
		sb.append(";");
	}

	private class MSC implements MainStatementContext
	{

		public boolean check(Token tk, Object grammerinfo)
		{
			if (tk.type == ParserConst.KT_FOR || tk.type == ParserConst.KT_WHILE)
			{
				needBreakTag = true;
			}
			else if(tk.type == ParserConst.KT_DO)
			{
				gi = (NodeDo.GrammerInfo) grammerinfo;
				if(gi != null)
				{
					gi.needBreakTag = needBreakTag;
				}
				return true;
			}

			return false;
		}
	}

}
