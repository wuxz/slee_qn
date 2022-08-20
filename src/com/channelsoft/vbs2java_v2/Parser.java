package com.channelsoft.vbs2java_v2;

import java.util.Map;
import java.util.Vector;

import com.channelsoft.reusable.comobj.service.ComponentRegistry;
import com.channelsoft.slee.usmlreasoning.KnowledgeVariable;

public class Parser
{
	private ParserEnv env = null;

	private String globalId = null;

	Node root = null;

	public Parser(String globalId)
	{
		this.globalId = globalId;
	}

	public void outPutJavaCode(String vbs, Vector<KnowledgeVariable> inkvlist,
			Vector<KnowledgeVariable> outkvlist,
			Map<String, ComponentRegistry> progID2Class, StringBuilder sb)
			throws Exception
	{
		StringBuilder tmpsb = new StringBuilder();
		env = new ParserEnv(vbs, globalId, inkvlist, outkvlist);
		root = Node.g_parse(env);
		env.keyword().printJavaForVar(tmpsb);
		env.keyword().printJavaForInitKV(tmpsb);
		tmpsb.append("\r\n");
		if (root != null)
		{
			root.printJava(tmpsb, env);
		}
		tmpsb.append("\r\n");

		env.setCurLineNum(0);
		tmpsb.append(env.indent()).append("cci.setCurrentLine(0);\r\n");
		env.keyword().printJavaForOutKV(tmpsb);
		sb.append(tmpsb);
	}

	public void outPutJavaMethod(StringBuilder sb) throws Exception
	{
		StringBuilder tmpsb = new StringBuilder();
		env.keyword().printJavaForSefDefFun(tmpsb);
		sb.append(tmpsb);
	}
}
