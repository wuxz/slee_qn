package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.util.Constant;

public class SleepNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6849017719440008835L;

	public Vector<KnowledgeVariable> inVars = new Vector<KnowledgeVariable>();

	public Vector<KnowledgeVariable> outVars = new Vector<KnowledgeVariable>();

	public boolean processError;

	public KnowledgeVariable resultVar;

	public int timeoutSecond;

	public KnowledgeVariable timeoutSecondVar;

	public SleepNode()
	{

	}

	public SleepNode(Service service, Workflow workflow)
	{
		super(service, workflow);
		processError = false;
		nextSrvNode = null;
		timeoutSecond = 0;
		timeoutSecondVar = null;
		resultVar = null;
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
		timeoutSecond = XMLFunction.getIntFromAttrib(node, "TimeoutSecond");
		if (timeoutSecond == 0)
		{
			timeoutSecond = 300;
		}
		timeoutSecondVar = service.lookupKeyVar(XMLFunction
				.returnValueFromAttr(node, "TimeoutSecondVar"));

		nodeName = XMLFunction.returnValueFromAttr(node, "Name");

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

			if (error.value != Constant.EVENT_ResumeService)
			{
				int seconds = timeoutSecond;
				if (timeoutSecondVar != null)
				{
					seconds = timeoutSecondVar.getValueInt();
				}

				// 未执行休眠。准备执行休眠。
				if (!service.usmlReasoningEnv.canPauseService())
				{
					// 不允许挂起，则直接休眠后继续执行。
					Thread.sleep(seconds * 1000);
				}
				else
				{
					// 为防止在文档完全退出之前就被另外一个通道恢复，所以不能直接在这里挂起文档，
					// 而只能在退出之后再通知通道执行休眠任务。
					error.value = Constant.EVENT_PauseService;
					service.usmlReasoningEnv.currentTask = new SleepTask(
							seconds * 1000);

					String msg;
					msg = String.format("文档执行被挂起，节点名称=%s", nodeName);
					service.browserSite.onMessage(msg);

					return null;
				}
			}

			// 休眠完毕。
			error.value = Constant.EVENT_No_Error;

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
				temp = String.format("休眠节点返回%s, m_bProcessError=%s", errString,
						processError);
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
					+ "]执行休眠节点异常");
			Log.wException(e);
			return null;
		}
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SleepNode ");
		buff.append(varName);
		buff.append(" = new SleepNode(this, wf);\n");

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
		buff.append(".timeoutSecond = ");
		buff.append(timeoutSecond);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".timeoutSecondVar = ");
		translateVar2SourceCode(buff, timeoutSecondVar);
	}
}
