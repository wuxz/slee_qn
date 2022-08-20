package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.slee.util.Constant;

public class ExitWorkflowNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 511240605595972128L;

	public ExitWorkflowNode()
	{
		super();
	}

	public ExitWorkflowNode(Service service, Workflow workflow)
	{
		super(service, workflow);
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		error.value = Constant.EVENT_ExitWorkflow;
		return null;
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		nodeName = "ExitWorkflow";

		buff.append("ExitWorkflowNode ");
		buff.append(varName);
		buff.append(" = new ExitWorkflowNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");
	}
}
