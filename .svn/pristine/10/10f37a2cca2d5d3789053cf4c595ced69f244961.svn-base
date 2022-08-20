package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.slee.util.Constant;

public class ContinueNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7918247789892535100L;

	public ContinueNode()
	{
		super();
	}

	public ContinueNode(Service service, Workflow workflow)
	{
		super(service, workflow);
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		error.value = Constant.EVENT_Loop_continue;
		return null;
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		nodeName = "Continue";

		buff.append("ContinueNode ");
		buff.append(varName);
		buff.append(" = new ContinueNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");
	}
}
