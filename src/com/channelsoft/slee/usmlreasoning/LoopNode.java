package com.channelsoft.slee.usmlreasoning;

import org.w3c.dom.Node;

import com.channelsoft.slee.util.Constant;

public class LoopNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3162589078833127525L;

	public ServiceNode childNode;

	private ServiceNode currentNode;

	private int loopValue;

	public KnowledgeVariable loopVar;

	public int maxValue;

	public KnowledgeVariable maxValueVar;

	public int waitingTime;

	public KnowledgeVariable waitingTimeVar;

	public LoopNode()
	{
		super();
	}

	public LoopNode(Service service, Workflow workflow)
	{
		super(service, workflow);
		childNode = null;
		loopVar = null;
		maxValue = 0;
		waitingTime = 0;
		maxValueVar = null;
		waitingTimeVar = null;
	}

	ServiceNode browseNode(Node node) throws Exception
	{
		nodeName = XMLFunction.returnValueFromAttr(node, "Name");
		String szArrib = XMLFunction.returnValueFromAttr(node, "LoopVarName");
		loopVar = service.lookupKeyVar(szArrib);
		maxValue = XMLFunction.getIntFromAttrib(node, "MaxValue");
		waitingTime = XMLFunction.getIntFromAttrib(node, "WaitingTime");
		maxValueVar = service.lookupKeyVar(XMLFunction.returnValueFromAttr(
				node, "MaxValueVar"));
		waitingTimeVar = service.lookupKeyVar(XMLFunction.returnValueFromAttr(
				node, "WaitingTimeVar"));
		childNode = workflow.browseWorkflow(node, null, true);// 递归
		return null;
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		try
		{
			if (service.browserSite.shouldExit())
			{
				error.value = Constant.EVENT_ForceExit;
				return null;
			}

			if (error.value != Constant.EVENT_ResumeService)
			{
				prepare4Execute();
			}

			boolean haveCall = !service.browserSite.isIdle();
			int waitingTime = this.waitingTime;
			if (null != waitingTimeVar)
			{
				waitingTime = waitingTimeVar.getValueInt();
			}
			int maxValue = this.maxValue;
			if (null != maxValueVar)
			{
				maxValue = maxValueVar.getValueInt();
			}

			ServiceNode lastNode = currentNode;
			for (; loopValue < maxValue; loopValue++)
			{
				if (error.value != Constant.EVENT_ResumeService)
				{
					currentNode = childNode;
				}

				while (null != currentNode)
				{
					lastNode = currentNode;
					currentNode = currentNode.doNode(error);
				}

				if ((error.value == Constant.EVENT_No_Error)
						|| (error.value == Constant.EVENT_Loop_continue))// ||
				// ((nError
				// &
				// 0xffff0000)
				// ==
				// EVENT_UserEvent))
				{
					Thread.sleep(waitingTime);

					if (service.browserSite.shouldExit())
					{
						error.value = Constant.EVENT_ForceExit;
					}

					// ******************************************************
					if (haveCall && service.browserSite.isIdle())
					{
						error.value = Constant.EVENT_CustomHangup;
						break;
					}
					else
					{
						continue;
					}
				}
				else if (error.value == Constant.EVENT_Loop_Break)
				{
					break;
				}
				else
				{
					break;
				}
			}

			if ((error.value == Constant.EVENT_No_Error)
					|| (error.value == Constant.EVENT_Loop_Break)
					|| (error.value == Constant.EVENT_Loop_continue))
			{
				error.value = Constant.EVENT_No_Error; // 事件只在节点内有效
				return nextSrvNode;
			}
			else if (error.value == Constant.EVENT_To_PrevNode)
			{
				return prevSrvNode;
			}
			else if (error.value == Constant.EVENT_To_RootNode)
			{
				return null;
			}
			else if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
			{
				if (0 != service.browserSite.hasFiredUserEvent())
				{
					return prevSrvNode;
				}

				return null;
			}
			else if (error.value == Constant.EVENT_PauseService)
			{
				currentNode = lastNode;
				return null;
			}
			else
			{
				return null;
			}

		}
		catch (Exception e)
		{
			service.browserSite.onRunError("NodeName=" + nodeName
					+ "执行LoopNode节点异常");
		}

		return null;
	}

	/**
	 * 
	 */
	private void prepare4Execute()
	{
		loopValue = 0;
		if (null != loopVar)
		{
			loopValue = loopVar.getValueInt();
		}
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("LoopNode ");
		buff.append(varName);
		buff.append(" = new LoopNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");

		if (loopVar != null)
		{
			buff.append(varName);
			buff.append(".loopVar = ");
			ServiceNode.translateVar2SourceCode(buff, loopVar);
		}

		buff.append(varName);
		buff.append(".maxValue = ");
		buff.append(maxValue);
		buff.append(";\n");

		if (maxValueVar != null)
		{
			buff.append(varName);
			buff.append(".maxValueVar = ");
			ServiceNode.translateVar2SourceCode(buff, maxValueVar);
		}

		buff.append(varName);
		buff.append(".waitingTime = ");
		buff.append(waitingTime);
		buff.append(";\n");

		if (waitingTimeVar != null)
		{
			buff.append(varName);
			buff.append(".waitingTimeVar = ");
			ServiceNode.translateVar2SourceCode(buff, waitingTimeVar);
		}

		ServiceNode node = childNode;
		while (node != null)
		{
			node.setNodeName(node.nodeName);
			node.translate2SourceCode(buff);

			if (childNode == node)
			{
				buff.append(varName);
				buff.append(".childNode = ");
				buff.append(node.varName);
				buff.append(";\n");
			}

			buff.append("wf.srvNodes.add(");
			buff.append(node.varName);
			buff.append(");\n");

			node = node.nextSrvNode;

			buff.append("\n");
		}

		node = childNode;
		while (node != null)
		{
			node.generateSiblingCode(buff);
			node = node.nextSrvNode;
		}
	}

	@Override
	void translateMethodCaller2SourceCode(StringBuilder buff,
			String newClassName) throws Exception
	{
		ServiceNode sn = childNode;
		while (sn != null)
		{
			sn.translateMethodCaller2SourceCode(buff, newClassName);
			sn = sn.nextSrvNode;
		}
	}

	@Override
	void translateScript2SourceCode(StringBuilder computeNodeBuffer)
			throws Exception
	{
		ServiceNode sn = childNode;
		while (sn != null)
		{
			sn.translateScript2SourceCode(computeNodeBuffer);
			sn = sn.nextSrvNode;
		}
	}
}
