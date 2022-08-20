package com.channelsoft.vbs2java_v2;

import java.util.Iterator;
import java.util.Vector;

/**
 * if {condition} {then}
 * </p>
 * {elseif}
 * </p>
 * ...
 * </p>
 * {else}
 * </p>
 * {endif}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeIf extends Node
{
	ExprCondition condition = null;

	NodeThen nodethen = null;

	Vector<NodeElseif> nodeelseif = null;

	NodeElse nodeelse = null;

	NodeEndIf nodeendif = null;

	protected void parse(ParserEnv env) throws Exception
	{
		condition = ExprCondition.parse(env);

		while (nodeendif == null)
		{
			Node nd = createSubStatement(env);
			if (nd instanceof NodeThen)
			{
				nodethen = (NodeThen) nd;
			}
			else if (nd instanceof NodeElseif)
			{
				if (nodeelseif == null)
				{
					nodeelseif = new Vector<NodeElseif>();
				}
				nodeelseif.add((NodeElseif) nd);
			}
			else if (nd instanceof NodeElse)
			{
				nodeelse = (NodeElse) nd;
			}
			else if (nd instanceof NodeEndIf)
			{
				nodeendif = (NodeEndIf) nd;
			}
			else
			{
				if (nd == null)
				{
					break;
				}
				else
				{
					throw new ParserException(env, nd.keyword,
							ExceptionDesc.DS011);
				}
			}
		}

		check(env);
	}

	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent()).append("if(");
		condition.printJava(sb, env);
		sb.append(")").append("\r\n");
		nodethen.print(sb, env);
		if (nodeelseif != null)
		{
			Iterator<NodeElseif> itor = nodeelseif.iterator();
			while (itor.hasNext())
			{
				sb.append("\r\n");
				itor.next().print(sb, env);
			}
		}

		if (nodeelse != null)
		{
			sb.append("\r\n");
			nodeelse.print(sb, env);
		}
	}

	protected void check(ParserEnv env) throws Exception
	{
		Object gi = env.getGrammerInfo();
		if (nodethen == null)
		{
			throw new ParserException(env, ExceptionDesc.DS004_THEN);
		}
		else if (nodeendif == null)
		{
			if (gi != null
					&& (Integer) env.getGrammerInfo() == ParserConst.SEF_IF_LINE_GRAMMER)
			{
			}
			else
			{
				throw new ParserException(env, ExceptionDesc.DS004_ENDIF);
			}
		}
	}
}
