package com.channelsoft.slee.usmlreasoning;

import org.w3c.dom.Node;

import com.channelsoft.slee.util.Constant;

public class BreakNode extends ServiceNode
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6126929650151564771L;

	public BreakNode()
	{
		super();
	}

	public BreakNode(Service service, Workflow workflow)
	{
		super(service, workflow);
	}

	ServiceNode browseNode(Node node)
	{
		return new BreakNode();
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		error.value = Constant.EVENT_Loop_Break;
		return null;
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		nodeName = "Break";

		buff.append("BreakNode ");
		buff.append(varName);
		buff.append(" = new BreakNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");
	}
}
