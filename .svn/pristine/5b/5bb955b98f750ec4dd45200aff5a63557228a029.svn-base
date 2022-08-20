package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;
import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.slee.util.Constant;

public class Workflow implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4624334221985902405L;

	ServiceNode currentNode;

	Service service;

	public Vector<ServiceNode> srvNodes = new Vector<ServiceNode>();

	public ServiceNode startNode;

	String varName;

	public String workflowName;

	public Workflow()
	{

	}

	public Workflow(Service service)
	{
		this.service = service;
		varName = "n_" + service.getNewVarIndex();
		initWorkflow();
	}

	ServiceNode browseWorkflow(Node node, ServiceNode prevNode,
			boolean isMenuNode) throws Exception
	{
		if (node == null)
		{
			throw new MyException("BrowseWorkflow node == null");
		}

		Node curNode = null;
		String nodeType;
		ServiceNode curSrvNode = null;
		ServiceNode startSrvNode = null;
		curNode = XMLFunction.getFirstChild(node);
		for (int i = 0; curNode != null; i++)
		{
			nodeType = curNode.getNodeName();
			if ("IO_Node".equals(nodeType))
			{
				SrvIONode srvIoNode;
				curSrvNode = srvIoNode = new SrvIONode(service, this);
				srvNodes.add(curSrvNode);
				srvIoNode.browseNode(curNode);
			}
			else if ("Compute_Node".equals(nodeType))
			{// 计算结点
				SrvScriptNode srvScriptNode;
				curSrvNode = srvScriptNode = new SrvScriptNode(service, this);
				srvNodes.add(curSrvNode);
				srvScriptNode.browseNode(curNode);
			}
			else if ("Branch_Node".equals(nodeType))
			{// 菜单结点
				MenuNode menuNode;
				curSrvNode = menuNode = new MenuNode(service, this);
				srvNodes.add(curSrvNode);
				menuNode.browseNode(curNode);
			}
			else if ("ReferceNode".equals(nodeType))
			{
				ReferenceNode refNode;
				curSrvNode = refNode = new ReferenceNode(service, this);
				srvNodes.add(curSrvNode);
				refNode.browseNode(curNode);
			}
			else if ("Loop_Node".equals(nodeType))
			{
				LoopNode loopNode;
				curSrvNode = loopNode = new LoopNode(service, this);
				srvNodes.add(curSrvNode);
				loopNode.browseNode(curNode);
			}
			else if ("Break".equals(nodeType))
			{
				curSrvNode = new BreakNode(service, this);
				curSrvNode.nodeName = "" + curSrvNode.service.getNewVarIndex();
				srvNodes.add(curSrvNode);
			}
			else if ("Continue".equals(nodeType))
			{
				curSrvNode = new ContinueNode(service, this);
				curSrvNode.nodeName = "" + curSrvNode.service.getNewVarIndex();
				srvNodes.add(curSrvNode);
			}
			else if ("Sleep_Node".equals(nodeType))
			{// 休眠结点
				SleepNode srvSleepNode;
				curSrvNode = srvSleepNode = new SleepNode(service, this);
				srvNodes.add(curSrvNode);
				srvSleepNode.browseNode(curNode);
			}
			else if ("SendMessage_Node".equals(nodeType))
			{// 发送消息结点
				SendMessageNode sendMsgNode;
				curSrvNode = sendMsgNode = new SendMessageNode(service, this);
				srvNodes.add(curSrvNode);
				sendMsgNode.browseNode(curNode);
			}
			else if ("WaitMessage_Node".equals(nodeType))
			{// 接收消息结点
				WaitMessageNode waitMsgNode;
				curSrvNode = waitMsgNode = new WaitMessageNode(service, this);
				srvNodes.add(curSrvNode);
				waitMsgNode.browseNode(curNode);
			}
			else if ("ExitWorkflow_Node".equals(nodeType))
			{// 接收消息结点
				curSrvNode = new ExitWorkflowNode(service, this);
				curSrvNode.nodeName = "" + curSrvNode.service.getNewVarIndex();
				srvNodes.add(curSrvNode);
			}
			else
			{
				throw new MyException("Workflow中出现了节点名以外的非法标签" + nodeType);
			}
			if (i == 0)
			{
				startSrvNode = curSrvNode;
			}
			curSrvNode.prevSrvNode = prevNode;
			if (null != prevNode)
			{
				prevNode.nextSrvNode = curSrvNode;
			}
			else
			{
				if (!isMenuNode)
				{// 当前为工作流的第一个节点
					currentNode = startNode = startSrvNode;
				}
			}
			prevNode = curSrvNode;
			curNode = XMLFunction.getNextSibling(curNode);
		}

		return startSrvNode;
	}

	public void clearWorkflow()
	{
		startNode = null;
		currentNode = null;
		service = null;
		for (int i = 0; i < srvNodes.size(); i++)
		{
			ServiceNode node = srvNodes.elementAt(i);
			node.nextSrvNode = null;
			node.prevSrvNode = null;
			node.service = null;
			node.workflow = null;
		}

		srvNodes.clear();
	}

	public String getVarName()
	{
		return varName;
	}

	void initWorkflow()
	{
		startNode = null;
		currentNode = null;
		srvNodes.removeAllElements();
	}

	void reason(USMLReasoningResult result)
	{
		try
		{
			service.setCurrentWorkflow(this);

			if ((result.value != Constant.EVENT_ResumeService))
			{
				service.browserSite.onEnterWorkflow(workflowName, result);
				if (result.value != Constant.EVENT_No_Error)
				{
					return;
				}

				currentNode = startNode;
			}

			ServiceNode lastNode = currentNode;
			while ((currentNode != null))
			{
				lastNode = currentNode;
				currentNode = currentNode.doNode(result);
				if (result.value == Constant.EVENT_To_RootNode)
				{
					currentNode = startNode;
				}
			}

			if (result.value == Constant.EVENT_ExitWorkflow)
			{
				result.value = Constant.EVENT_No_Error;
			}
			else if (result.value != Constant.EVENT_PauseService)
			{
				service.browserSite.onLeaveWorkflow(workflowName);
			}
			else
			{
				currentNode = lastNode;
			}
		}
		catch (Exception e)
		{
			service.browserSite.onRunError("WorkflowName=" + workflowName
					+ "Reason执行工作流异常");
		}
	}

	void setParent(Service service)
	{
		this.service = service;
	}

	public void setWorkflowName(String name)
	{
		workflowName = name;
	}

	void translate2SourceCode(StringBuilder buff,
			StringBuilder computeNodeBuffer) throws Exception
	{
		ServiceNode node = startNode;
		while (node != null)
		{
			node.setNodeName(node.nodeName);
			node.translate2SourceCode(buff);
			node.translateScript2SourceCode(computeNodeBuffer);

			buff.append("wf.srvNodes.add(");
			buff.append(node.varName);
			buff.append(");\n\n");

			if (startNode == node)
			{
				buff.append("wf.startNode = ");
				buff.append(node.varName);
				buff.append(";\n\n");
			}

			node = node.nextSrvNode;
		}

		node = startNode;
		while (node != null)
		{
			node.generateSiblingCode(buff);

			node = node.nextSrvNode;
		}
	}

	void translateMethodCaller2SourceCode(StringBuilder buff,
			String newClassName) throws Exception
	{
		ServiceNode node = startNode;
		while (node != null)
		{
			node.setNodeName(node.nodeName);
			node.translateMethodCaller2SourceCode(buff, newClassName);

			node = node.nextSrvNode;
		}
	}
}
