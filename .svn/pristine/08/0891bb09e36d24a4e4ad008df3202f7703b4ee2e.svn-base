package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;
import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.slee.util.Constant;

public class MenuNode extends ServiceNode
{
	public class Branch implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7273852192872881802L;

		public String expression;

		public ServiceNode nextSrvNode;

		public String operator;

		String varName;

		Branch()
		{
			nextSrvNode = null;
			varName = "branch_" + service.getNewVarIndex();
		}

		public String getVarName()
		{
			return varName;
		}
		// CServiceNode* m_pRefSrvNode;//存在NextNode时不允许有RefNode;都合并为NextNode
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6677558093987112472L;

	public Vector<Branch> branches = new Vector<Branch>();// 分支项数组；

	private Branch currentBranch;

	private ServiceNode currentNode;

	public Branch defaultBranch;

	public KnowledgeVariable judgeVar;;

	public MenuNode()
	{
		super();

	}

	public MenuNode(Service service, Workflow workflow)

	{
		super(service, workflow);
		judgeVar = null;
	}

	ServiceNode browseNode(Node node) throws Exception
	{
		nodeName = XMLFunction.returnValueFromAttr(node, "Name");
		String szArrib = XMLFunction.returnValueFromAttr(node, "VarName");
		judgeVar = service.lookupKeyVar(szArrib);
		if (null == judgeVar)
		{
			throw new MyException(String.format("菜单节点中m_pJudgeVar=%s等于NULL",
					szArrib));
		}

		String strNodeName;
		ServiceNode srvNode = null;
		// 菜单接点

		Node itemNode = null;
		itemNode = XMLFunction.getFirstChild(node);
		while (null != itemNode)
		{
			strNodeName = XMLFunction.getNodeName(itemNode);
			// 设置属性BranchItem
			Branch branch = new Branch();
			branch.operator = XMLFunction.returnValueFromAttr(itemNode,
					"Operator");
			branch.expression = XMLFunction.returnValueFromAttr(itemNode,
					"ItemExpression");
			// 浏览是否存在子节点
			srvNode = workflow.browseWorkflow(itemNode, null, true);// 递归
			branch.nextSrvNode = srvNode;

			if ("Default".equals(strNodeName))
			{
				defaultBranch = branch;
			}
			else
			{
				branches.add(branch);
			}

			itemNode = XMLFunction.getNextSibling(itemNode);
		}
		return null;
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		if (error.value != Constant.EVENT_ResumeService)
		{
			currentBranch = null;
			try
			{
				int i = 0;
				for (; i < branches.size(); i++)
				{
					currentBranch = branches.elementAt(i);
					if (Constant.Equal.equals(currentBranch.operator))
					{// 等于
						if (judgeVar.valueEqual(currentBranch.expression))
						{
							break;
						}
						else
						{
							continue;
						}
					}
					else if (Constant.Unequal.equals(currentBranch.operator))
					{// 不等于
						if (judgeVar.valueEqual(currentBranch.expression))
						{
							continue;
						}
						else
						{
							break;
						}
					}
					else if (Constant.More.equals(currentBranch.operator))
					{// 大于
						if (judgeVar.valueMore(currentBranch.expression))
						{
							break;
						}
						else
						{
							continue;
						}
					}
					else if (Constant.Less.equals(currentBranch.operator))
					{// 小于
						if (judgeVar.valueLess(currentBranch.expression))
						{
							break;
						}
						else
						{
							continue;
						}
					}
					else if (Constant.MoreAndEqual
							.equals(currentBranch.operator))
					{// 大于等于
						if (judgeVar.valueMore(currentBranch.expression))
						{
							break;
						}
						else if (judgeVar.valueEqual(currentBranch.expression))
						{
							break;
						}
						else
						{
							continue;
						}
					}
					else if (Constant.LessAndEqual
							.equals(currentBranch.operator))
					{// 小于等于
						if (judgeVar.valueEqual(currentBranch.expression))
						{
							break;
						}
						else if (judgeVar.valueLess(currentBranch.expression))
						{
							break;
						}
						else
						{
							continue;
						}
					}
					else if (Constant.OperatorIn.equals(currentBranch.operator))
					{//In操作符，每个元素间用逗号“,”分隔。如[5In(1,2,3,4,5)]=true；[6In(1,2,3,4,5
						// )]=false
						if (judgeVar.valueIn(currentBranch.expression))
						{
							break;
						}
						else
						{
							continue;
						}
					}
					else
					{
						;
					}

				}

				if (i == branches.size())
				{
					currentBranch = defaultBranch;
				}
			}
			catch (Exception e)
			{// 在变量比较错误时抛出异常；此时返回缺省节点
				currentBranch = defaultBranch;
				service.browserSite.onRunError("NodeName=" + nodeName
						+ "执行Menu节点异常");
			}

			currentNode = currentBranch.nextSrvNode;
		}

		ServiceNode lastNode = currentNode;
		while (null != currentNode)
		{
			lastNode = currentNode;
			currentNode = currentNode.doNode(error);
			if (error.value == Constant.EVENT_To_RootNode)
			{
				currentNode = null;
			}
		}

		if (error.value == Constant.EVENT_No_Error)
		{
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
				return nextSrvNode;
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

	void generateBranchCode(StringBuilder buff, Branch branch) throws Exception
	{
		buff.append(branch.getVarName());
		buff.append(" = ");
		buff.append(varName);
		buff.append(".newBranch();\n");

		buff.append(branch.getVarName());
		buff.append(".expression = ");
		translateString2SourceCode(buff, branch.expression);

		buff.append(branch.getVarName());
		buff.append(".operator = ");
		translateString2SourceCode(buff, branch.operator);
		buff.append("\n");

		ServiceNode node = branch.nextSrvNode;
		while (node != null)
		{
			node.setNodeName(node.nodeName);
			node.translate2SourceCode(buff);

			if (branch.nextSrvNode == node)
			{
				buff.append(branch.getVarName());
				buff.append(".nextSrvNode = ");
				buff.append(node.varName);
				buff.append(";\n");
			}

			buff.append("wf.srvNodes.add(");
			buff.append(node.varName);
			buff.append(");\n");

			node = node.nextSrvNode;

			buff.append("\n");
		}

		node = branch.nextSrvNode;
		while (node != null)
		{
			node.generateSiblingCode(buff);
			node = node.nextSrvNode;
		}
	}

	public Branch newBranch()
	{
		return new Branch();
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("MenuNode ");
		buff.append(varName);
		buff.append(" = new MenuNode(");
		buff.append("this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");

		buff.append(varName);
		buff.append(".judgeVar = ");
		translateVar2SourceCode(buff, judgeVar);
		buff.append("\n");

		buff.append("MenuNode.Branch ");
		generateBranchCode(buff, defaultBranch);
		buff.append(varName);
		buff.append(".defaultBranch = ");
		buff.append(defaultBranch.getVarName());
		buff.append(";\n\n");

		for (int i = 0; i < branches.size(); i++)
		{
			Branch branch = branches.elementAt(i);
			buff.append("MenuNode.Branch ");
			generateBranchCode(buff, branch);

			buff.append(varName);
			buff.append(".branches.add(");
			buff.append(branch.getVarName());
			buff.append(");\n");
		}

		buff.append("\n");
	}

	@Override
	void translateMethodCaller2SourceCode(StringBuilder buff,
			String newClassName) throws Exception
	{
		int i = 0;
		MenuNode.Branch branch = null;
		for (; i < branches.size(); i++)
		{
			branch = branches.elementAt(i);
			ServiceNode branchNode = branch.nextSrvNode;
			while (branchNode != null)
			{
				branchNode.translateMethodCaller2SourceCode(buff, newClassName);
				branchNode = branchNode.nextSrvNode;
			}
		}
		ServiceNode defaultBranchNode = (defaultBranch).nextSrvNode;
		while (defaultBranchNode != null)
		{
			defaultBranchNode.translateMethodCaller2SourceCode(buff,
					newClassName);
			defaultBranchNode = defaultBranchNode.nextSrvNode;
		}
	}

	@Override
	void translateScript2SourceCode(StringBuilder computeNodeBuffer)
			throws Exception
	{
		int i = 0;
		MenuNode.Branch branch = null;
		for (; i < branches.size(); i++)
		{
			branch = branches.elementAt(i);
			ServiceNode branchNode = branch.nextSrvNode;
			while (branchNode != null)
			{
				branchNode.translateScript2SourceCode(computeNodeBuffer);
				branchNode = branchNode.nextSrvNode;
			}
		}
		ServiceNode defaultBranchNode = (defaultBranch).nextSrvNode;
		while (defaultBranchNode != null)
		{
			defaultBranchNode.translateScript2SourceCode(computeNodeBuffer);
			defaultBranchNode = defaultBranchNode.nextSrvNode;
		}
	}
}
