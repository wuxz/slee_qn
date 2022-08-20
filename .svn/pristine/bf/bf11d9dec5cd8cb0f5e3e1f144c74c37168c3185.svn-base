package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;

import com.channelsoft.slee.util.Constant;

public abstract class ServiceNode implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1798456793461650461L;

	public static void translateString2SourceCode(StringBuilder buff,
			String value)
	{
		translateString2SourceCode2(buff, value);
		buff.append(";\n");
	}

	public static void translateString2SourceCode2(StringBuilder buff,
			String value)
	{
		if (value == null)
		{
			buff.append("null");
		}
		else
		{
			buff.append("\"");
			buff.append(value.replace("\\", "\\\\").replace("\"", "\\\"")
					.replace("\t", "\\t").replace("\n", "\\n").replace("\r",
							"\\r"));
			buff.append("\"");
		}
	}

	public static void translateVar2SourceCode(StringBuilder buff,
			KnowledgeVariable var)
	{
		if (var == null)
		{
			buff.append("null;\n");
		}
		else
		{
			buff.append(var.codeName);
			buff.append(";\n");
		}
	}

	public ServiceNode nextSrvNode;

	public String nodeName;

	public ServiceNode prevSrvNode;// 当前节点的前一个节点

	public Service service;

	public String varName;

	protected Workflow workflow;

	public ServiceNode()
	{
		varName = workflow.getVarName() + "_" + service.getNewVarIndex();
	}

	public ServiceNode(Service service, Workflow workflow)
	{
		this.service = service;
		this.workflow = workflow;
		nextSrvNode = null;
		prevSrvNode = null;
		varName = workflow.getVarName() + "_" + service.getNewVarIndex();
	}

	public ServiceNode doNode(USMLReasoningResult error)
	{
		if (error.value != Constant.EVENT_ResumeService)
		{
			onEnterNode(error);

			if (error.value != Constant.EVENT_No_Error)
			{
				return null;
			}
		}

		ServiceNode node = executeNode(error);

		if (error.value != Constant.EVENT_PauseService)
		{
			onLeaveNode(error.value);
		}

		return node;
	}

	abstract ServiceNode executeNode(USMLReasoningResult error);

	public void generateSiblingCode(StringBuilder buff)
	{
		if (prevSrvNode != null)
		{
			buff.append(varName);
			buff.append(".prevSrvNode = ");
			prevSrvNode.setNodeName(prevSrvNode.nodeName);
			buff.append(prevSrvNode.varName);
			buff.append(";\n");
		}

		if (nextSrvNode != null)
		{
			buff.append(varName);
			buff.append(".nextSrvNode = ");
			nextSrvNode.setNodeName(nextSrvNode.nodeName);
			buff.append(nextSrvNode.varName);
			buff.append(";\n");
		}
	}

	public String getNodeName()
	{
		return nodeName;
	}

	void onEnterNode(USMLReasoningResult error)
	{
		service.browserSite.onEnterSrvNode(nodeName, error);
	}

	void onLeaveNode(int error)
	{
		service.browserSite.onLeaveSrvNode(nodeName, error);
	}

	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}

	void translate2SourceCode(StringBuilder buff) throws Exception
	{

	}

	void translateMethodCaller2SourceCode(StringBuilder buff,
			String newClassName) throws Exception
	{
	}

	void translateScript2SourceCode(StringBuilder computeNodeBuffer)
			throws Exception
	{

	}
}
