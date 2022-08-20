package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.util.Constant;

public class SendMessageNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7382675441795936429L;

	public String className;

	public String content;

	public KnowledgeVariable contentVar;

	public String destination;

	public KnowledgeVariable destinationVar;

	public Vector<KnowledgeVariable> inVars = new Vector<KnowledgeVariable>();

	public boolean isPersistent;

	public Vector<KnowledgeVariable> outVars = new Vector<KnowledgeVariable>();

	public boolean processError;

	public KnowledgeVariable resultVar;

	public KnowledgeVariable sessionIdVar;

	public int timeoutSecond;

	public KnowledgeVariable timeoutSecondVar;

	public SendMessageNode()
	{

	}

	public SendMessageNode(Service service, Workflow workflow)
	{
		super(service, workflow);
		processError = false;
		isPersistent = false;
		nextSrvNode = null;
		resultVar = null;
		destinationVar = null;
		contentVar = null;
		sessionIdVar = null;
		timeoutSecond = 0;
		timeoutSecondVar = null;
	}

	ServiceNode browseNode(Node node) throws Exception
	{
		// Attribute
		String atrribute = "";
		atrribute = XMLFunction.returnValueFromAttr(node, "ProcessError");
		if ("true".equals(atrribute))
		{
			processError = true;
		}
		else
		{
			processError = false;
		}

		atrribute = XMLFunction.returnValueFromAttr(node, "IsPersistent");
		if ("true".equals(atrribute))
		{
			isPersistent = true;
		}
		else
		{
			isPersistent = false;
		}

		nodeName = XMLFunction.returnValueFromAttr(node, "Name");

		content = XMLFunction.returnValueFromAttr(node, "Content");
		contentVar = service.lookupKeyVar(XMLFunction.returnValueFromAttr(node,
				"ContentVar"));

		timeoutSecond = XMLFunction.getIntFromAttrib(node, "TimeoutSecond");
		timeoutSecondVar = service.lookupKeyVar(XMLFunction
				.returnValueFromAttr(node, "TimeoutSecondVar"));

		destination = XMLFunction.returnValueFromAttr(node, "Destination");
		destinationVar = service.lookupKeyVar(XMLFunction.returnValueFromAttr(
				node, "DestinationVar"));

		sessionIdVar = service.lookupKeyVar(XMLFunction.returnValueFromAttr(
				node, "SessionIdVar"));

		Node childNode = null;
		String varName;
		String varType;
		String strNodeName;

		childNode = XMLFunction.getFirstChild(node);
		while (null != childNode)
		{
			// InputVarNameSet
			strNodeName = XMLFunction.getNodeName(childNode);
			if ("InputVarNameSet".equals(strNodeName))
			{
				Node inVarNode = null;
				inVarNode = XMLFunction.getFirstChild(childNode);
				while (null != inVarNode)
				{
					XMLFunction.verifyNodeTag(inVarNode, "VarName");
					varName = XMLFunction.getNodeText(inVarNode);
					KnowledgeVariable var = service.lookupKeyVar(varName);
					if (null == var)
					{
						throw new MyException(String.format("节点[%s]中输入变量%s不存在",
								nodeName, varName));
					}
					inVars.add(var);

					inVarNode = XMLFunction.getNextSibling(inVarNode);
				}

			}
			else if ("OutVarNameSet".equals(strNodeName))
			{
				// OutVarNameSet
				Node outVarNode = null;
				outVarNode = XMLFunction.getFirstChild(childNode);
				while (null != outVarNode)
				{
					// CXMLFunction::VerifyNodeTag(OutVarNode,"VarName");
					varName = XMLFunction.getNodeText(outVarNode);
					KnowledgeVariable var = service.lookupKeyVar(varName);
					if (null == var)
					{
						throw new MyException(String.format(
								"节点%s中输出变量%s等于NULL", nodeName, varName));
					}

					varType = XMLFunction.getNodeName(outVarNode);

					if (Constant.ResultVarNameTag.equals(varType))
					{
						resultVar = var;
					}
					else
					{
						outVars.add(var);
					}

					outVarNode = XMLFunction.getNextSibling(outVarNode);
				}

			}

			childNode = XMLFunction.getNextSibling(childNode);
		}

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
				String msg = String.format("文档执行已结束，不执行此节点" + nodeName);
				service.browserSite.onMessage(msg);
				return null;
			}

			if (contentVar != null)
			{
				content = contentVar.getValue();
			}

			if (content.equals(""))
			{
				error.value = Constant.EVENT_GeneralError;
				service.browserSite.onRunError("不允许发送空白消息");

				if (processError)
				{
					return nextSrvNode;
				}
				else
				{
					return null;
				}
			}

			if (destinationVar != null)
			{
				destination = destinationVar.getValue();
			}

			if (timeoutSecondVar != null)
			{
				timeoutSecond = timeoutSecondVar.getValueInt();
			}

			if (timeoutSecond <= 0)
			{
				timeoutSecond = -1;
			}

			String sessionId = "";
			if (sessionIdVar != null)
			{
				sessionId = sessionIdVar.getValue();
			}

			boolean succeeded = service.sleeService.sendMessage(content,
					destination, sessionId, isPersistent, timeoutSecond);

			error.value = succeeded ? Constant.EVENT_No_Error
					: Constant.EVENT_GeneralError;

			resultVar.setUSMLEventValue(error.value);
			service.setEventVar(error.value);

			if (error.value == Constant.EVENT_No_Error)
			{
				return nextSrvNode;
			}
			else if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
			{
				String msg = String.format("文档执行被用户事件终止，节点名称=%s", nodeName);
				service.browserSite.onMessage(msg);
				return null;
			}

			if (error.value == Constant.EVENT_To_PrevNode)
			{
				return prevSrvNode;
			}
			else if (error.value == Constant.EVENT_To_RootNode)
			{
				return null;
			}
			else
			{
				String errString = Constant.transEventToString(error.value,
						false);
				if ("".equals(errString))
				{
					errString = String.format("%d", error.value);
				}

				String temp;
				temp = String.format("发送消息节点返回%s, m_bProcessError=%s",
						errString, processError);
				service.browserSite.onMessage(temp);

				if (processError)
				{
					return nextSrvNode;
				}
				else
				{
					return null;
				}
			}
		}
		catch (Exception e)
		{
			error.value = Constant.EVENT_GeneralError;

			service.browserSite.onRunError("NodeName=[" + nodeName
					+ "]执行发送消息节点异常");
			Log.wException(e);
			return null;
		}
	}

	@Override
	public void setNodeName(String name)
	{
		super.setNodeName(name);

		className = varName.substring(0, 1).toUpperCase()
				+ varName.substring(1);
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SendMessageNode ");
		buff.append(varName);
		buff.append(" = new SendMessageNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");

		buff.append(varName);
		buff.append(".varName = ");
		ServiceNode.translateString2SourceCode(buff, varName);

		buff.append(varName);
		buff.append(".processError = ");
		buff.append(processError);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".resultVar = ");
		translateVar2SourceCode(buff, resultVar);

		for (int i = 0; i < inVars.size(); i++)
		{
			KnowledgeVariable var = inVars.elementAt(i);

			buff.append(varName);
			buff.append(".inVars.add(");
			buff.append(var.codeName);
			buff.append(");\n");
		}

		for (int i = 0; i < outVars.size(); i++)
		{
			KnowledgeVariable var = outVars.elementAt(i);

			buff.append(varName);
			buff.append(".outVars.add(");
			buff.append(var.codeName);
			buff.append(");\n");
		}

		buff.append(varName);
		buff.append(".content = ");
		ServiceNode.translateString2SourceCode(buff, content);

		buff.append(varName);
		buff.append(".contentVar = ");
		translateVar2SourceCode(buff, contentVar);

		buff.append(varName);
		buff.append(".destination = ");
		ServiceNode.translateString2SourceCode(buff, destination);

		buff.append(varName);
		buff.append(".destinationVar = ");
		translateVar2SourceCode(buff, destinationVar);

		buff.append(varName);
		buff.append(".sessionIdVar = ");
		translateVar2SourceCode(buff, sessionIdVar);

		buff.append(varName);
		buff.append(".timeoutSecond = ");
		buff.append(timeoutSecond);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".timeoutSecondVar = ");
		translateVar2SourceCode(buff, timeoutSecondVar);

		buff.append(varName);
		buff.append(".isPersistent = ");
		buff.append(isPersistent);
		buff.append(";\n");
	}
}
