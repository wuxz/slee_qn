package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

/**
 * @author liyan
 */
public class ReferenceNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2721078929431232572L;

	String daemonChannelDN = null;

	public KnowledgeVariable daemonParamVar;

	public String daemonServiceName = "";

	public KnowledgeVariable daemonServiceNameVar;

	boolean daemonStartOk = false;

	Service idlePromptRefService;

	public Vector<KnowledgeVariable> inVars = new Vector<KnowledgeVariable>();

	public boolean isGoto;

	public boolean isProcessError;

	public boolean isToService;

	public KnowledgeVariable lastErrorVar;

	public Vector<KnowledgeVariable> outVars = new Vector<KnowledgeVariable>();

	Service refService = null;

	Workflow refWorkflow;

	public String refWorkflowName;

	public KnowledgeVariable resultVar;

	public String serviceName = "";

	public KnowledgeVariable serviceNameVar;

	Vector<KnowledgeVariable> tempInVars = new Vector<KnowledgeVariable>();

	Vector<KnowledgeVariable> tempOutVars = new Vector<KnowledgeVariable>();

	Vector<Integer> userEvents = new Vector<Integer>();

	public ReferenceNode()
	{
		super();

	}

	public ReferenceNode(Service service, Workflow workflow)
	{
		super(service, workflow);
		isProcessError = false;
		nextSrvNode = null;
		refWorkflow = null;
		serviceNameVar = null;
		resultVar = null;
		isToService = false;
		isGoto = false;
		lastErrorVar = null;
		daemonServiceNameVar = null;
		daemonParamVar = null;
	}

	ServiceNode browseNode(Node node) throws Exception
	{
		userEvents.removeAllElements();

		nodeName = XMLFunction.returnValueFromAttr(node, "Name");
		refWorkflowName = XMLFunction.returnValueFromAttr(node,
				"RefWorkflowName");
		isToService = XMLFunction.getBoolFromAttrib(node, "ToService", false);

		isGoto = XMLFunction.getBoolFromAttrib(node, "Goto", false);

		refWorkflow = service.getWorkflow(refWorkflowName);
		if ((null == refWorkflow) && (isToService == false))
		{
			throw new MyException(String.format("RefNode=%s指定的Workflow=%s不存在",
					nodeName, refWorkflowName));
		}

		isProcessError = XMLFunction.getBoolFromAttrib(node, "ProcessError",
				false);

		serviceName = XMLFunction.returnValueFromAttr(node, "ServiceName");
		serviceNameVar = service.lookupKeyVar(XMLFunction.returnValueFromAttr(
				node, "ServiceNameVar"));

		Node childNode = null;
		// Node *tempNode = NULL;

		String szVarName;
		String szVarType;
		String strNodeName;
		childNode = XMLFunction.getFirstChild(node);
		while (null != childNode)
		{
			// InputVarNameSet
			strNodeName = XMLFunction.getNodeName(childNode);
			if ("Daemon".equals(strNodeName))
			{
				daemonServiceName = XMLFunction.returnValueFromAttr(childNode,
						"ServiceName");
				daemonServiceNameVar = service.lookupKeyVar(XMLFunction
						.returnValueFromAttr(childNode, "ServiceNameVar"));
				daemonParamVar = service.lookupKeyVar(XMLFunction
						.returnValueFromAttr(childNode, "ParameterName"));

				Node userEventSetNode = null;
				Node userEventNode = null;
				userEventSetNode = XMLFunction.getFirstChild(childNode);
				userEventNode = XMLFunction.getFirstChild(userEventSetNode);
				while (null != userEventNode)
				{
					XMLFunction.verifyNodeTag(userEventNode, "UserEvent");
					String eventIdString = XMLFunction
							.getNodeText(userEventNode);
					this.userEvents.add(Integer.parseInt(eventIdString));

					userEventNode = XMLFunction.getNextSibling(userEventNode);
				}

				if (((daemonServiceName.length() > 0) || (null != daemonServiceNameVar))
						&& (null == daemonParamVar))
				{
					throw new MyException(String.format(
							"RefNode=%s没有指定Daemon服务的启动参数", nodeName));
				}

				if (((daemonServiceName.length() > 0) || (null != daemonServiceNameVar))
						&& (userEvents.size() == 0))
				{
					throw new MyException(String.format(
							"RefNode=%s没有指定Daemon服务的用户事件", nodeName));
				}
			}
			if ("InputVarNameSet".equals(strNodeName))
			{
				Node inVarNode = null;
				inVarNode = XMLFunction.getFirstChild(childNode);
				while (null != inVarNode)
				{
					XMLFunction.verifyNodeTag(inVarNode, "VarName");
					szVarName = XMLFunction.getNodeText(inVarNode);
					KnowledgeVariable var = service.lookupKeyVar(szVarName);
					if (null == var)
					{
						throw new MyException(String.format(
								"节点%s中输入变量%s等于NULL", nodeName, szVarName));
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
					szVarName = XMLFunction.getNodeText(outVarNode);
					KnowledgeVariable var = service.lookupKeyVar(szVarName);
					if (null == var)
					{
						throw new MyException(String.format(
								"节点%s中输出变量%s等于NULL", nodeName, szVarName));
					}

					szVarType = XMLFunction.getNodeName(outVarNode);

					if (Constant.ResultVarNameTag.equals(szVarType))
					{
						if (Constant.DefResultVarName.equals(szVarName))
						{
							resultVar = var;
						}
						else if (Constant.DefLastErrorVarName.equals(szVarName))
						{
							lastErrorVar = var;
						}
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

	/**
	 * 用来实现指定时间放音提示用户的功能,在该方法中，第一次加载idle_prompt文档
	 */
	void executeIdlePrompt(USMLReasoningResult error) throws Exception
	{
		try
		{
			idlePromptRefService = service.usmlReasoningEnv.compileUsml(
					serviceName, idlePromptRefService);
		}
		catch (MyException e)
		{
			service.browserSite.onRunError(e.errMsg);

		}
		catch (Exception e)
		{
			service.browserSite.onRunError("BaseBrowser::BrowseUSMLService异常");
			Log.wException(LogDef.id_0x10130000, e);
		}
		if (idlePromptRefService == null)
		{
			return;
		}

		idlePromptRefService.browserSite = service.browserSite;
		idlePromptRefService.usmlReasoningEnv = service.usmlReasoningEnv;
		idlePromptRefService.browseUSML(serviceName, service.getServiceResID(),
				null, null, error);
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		try
		{
			boolean isNewTask = (error.value != Constant.EVENT_ResumeService);

			if (isToService)
			{// 引用服务
				if (isNewTask)
				{
					StringWrapper temp = new StringWrapper();
					if (null != serviceNameVar)
					{
						serviceNameVar.getValue(temp);
						serviceName = temp.value;
					}

					if (null != daemonServiceNameVar)
					{
						daemonServiceNameVar.getValue(temp);
						daemonServiceName = temp.value;
					}

					serviceName = service.getUSMLFilePathName(serviceName);

					if (isGoto)
					{
						error.value = Constant.EVENT_GotoService;
						error.usmlFileName = serviceName;

						return null;
					}

					refService = null;
					try
					{
						refService = service.getImportService(serviceName);
					}
					catch (Exception e)
					{
						service.browserSite.onRunError("NodeName=" + nodeName
								+ "执行引用节点异常, usmlFile = " + serviceName);
						Log.wException(LogDef.id_0x10130000, e);
						return null;
					}

					daemonStartOk = false;
					daemonChannelDN = null;

					if (null != refService)
					{
						error.value = Constant.EVENT_No_Error;
						tempInVars.clear();
						for (int i = 0; i < inVars.size(); i++)
						{
							tempInVars.add(inVars.elementAt(i));
						}

						tempOutVars.clear();
						for (int i = 0; i < outVars.size(); i++)
						{
							tempOutVars.add(outVars.elementAt(i));
						}

						// 合并引用节点的事件集与当前流程的事件集到引用流程的事件集
						if (userEvents.size() > 0)
						{
							service.browserSite.pushUserEventSet(userEvents);
						}

						if (daemonServiceName.length() > 0)
						{
							StringWrapper param = new StringWrapper();
							daemonParamVar.getValue(param);
							daemonChannelDN = service.browserSite
									.startDaemonService(daemonServiceName,
											param.value);
							if ("".equals(daemonChannelDN))
							{
								daemonStartOk = true;
							}
						}
						else
						{
							daemonStartOk = true;
						}
					}
					else
					{
						error.value = Constant.EVENT_InvalidUSMLFile;
					}
				}

				if (daemonStartOk)
				{
					refService.execute(service.usmlReasoningEnv,
							service.browserSite, service.getServiceResID(),
							tempInVars, tempOutVars, error);
				}

				if (isNewTask)
				{
					if (null != daemonChannelDN)
					{
						service.browserSite.endDaemonChannel(daemonChannelDN);
					}

					// 恢复引用流程的事件集
					if (userEvents.size() > 0)
					{
						service.browserSite.popUserEventSet();
					}

					if (null != resultVar)
					{
						resultVar
								.setUSMLEventValue((error.value & 0xffff0000) == Constant.EVENT_UserEvent ? Constant.EVENT_UserEvent
										: error.value);
					}

					int eventId = 0;
					if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
					{
						eventId = error.value & 0xffff;
						if (isLocalUserEvent(eventId))
						{
							service.browserSite.clearUserEvent(eventId);
						}
					}
					if (null != lastErrorVar)
					{
						lastErrorVar.setValue(daemonStartOk ? eventId
								: Constant.EVENT_InsufficientResource);
					}

					service.usmlReasoningEnv.setCurService(service);
					service.setEventVar(refService.getEventVar());
				}
			}
			else
			{// 引用工作流
				if (isNewTask)
				{
					// 添加当引用节点的事件集到当前事件集
					if (userEvents.size() > 0)
					{
						service.browserSite.pushUserEventSet(userEvents);
					}

					daemonStartOk = false;
					daemonChannelDN = null;
					if (daemonServiceName.length() > 0)
					{
						StringWrapper param = new StringWrapper();
						daemonParamVar.getValue(param);
						daemonChannelDN = service.browserSite
								.startDaemonService(daemonServiceName,
										param.value);
						if ("".equals(daemonChannelDN))
						{
							daemonStartOk = true;
						}
					}
					else
					{
						daemonStartOk = true;
					}

					if (refWorkflow == null)
					{
						refWorkflow = service.getWorkflow(refWorkflowName);
					}
				}

				if (daemonStartOk)
				{
					refWorkflow.reason(error);
				}

				if (isNewTask)
				{
					if (null != daemonChannelDN)
					{
						service.browserSite.endDaemonChannel(daemonChannelDN);
					}

					// 恢复当前事件集
					if (userEvents.size() > 0)
					{
						service.browserSite.popUserEventSet();
					}

					if (null != resultVar)
					{
						resultVar
								.setUSMLEventValue((error.value & 0xffff0000) == Constant.EVENT_UserEvent ? Constant.EVENT_UserEvent
										: error.value);
					}

					int eventId = 0;
					if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
					{
						eventId = error.value & 0xffff;
						if (isLocalUserEvent(eventId))
						{
							service.browserSite.clearUserEvent(eventId);
						}
					}
					if (null != lastErrorVar)
					{
						lastErrorVar.setValue(eventId);
					}

					service.setEventVar(error.value);
				}
			}

			if ((error.value == Constant.EVENT_Loop_continue)
					|| (error.value == Constant.EVENT_Loop_Break))
			{// 容错处理：break、Continue只在当前工作流中有效。
				error.value = Constant.EVENT_No_Error;
			}

			if (error.value == Constant.EVENT_No_Error)
			{
				return nextSrvNode;
			}
			else if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
			{
				int eventId = error.value & 0xffff;
				return isLocalUserEvent(eventId) ? nextSrvNode : null;
			}
			if (error.value == Constant.EVENT_To_PrevNode)
			{
				return prevSrvNode;
			}
			else if (error.value == Constant.EVENT_To_RootNode)
			{
				return null;
			}
			else if (error.value == Constant.EVENT_PauseService)
			{
				return null;
			}
			else
			{
				if (isProcessError == true)
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
			Log.wException(LogDef.id_0x10130000, e);
			service.browserSite.onRunError("NodeName=" + nodeName
					+ "执行Reference节点异常");
		}

		return null;
	}

	void initIdlePrompt() throws Exception
	{
		nodeName = "30分钟无按键提示";
		isToService = true;
		serviceName = UnifiedServiceManagement.configData.getUsmlPath()
				+ "/idle_prompt.usml";
	}

	boolean isLocalUserEvent(int eventId)
	{
		for (int i = 0; i < this.userEvents.size(); i++)
		{
			if (eventId == userEvents.elementAt(i))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("ReferenceNode ");
		buff.append(varName);
		buff.append(" = new ReferenceNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		String newNodeName = nodeName.replace("\r", "");
		newNodeName = newNodeName.replace("\n", "");
		ServiceNode.translateString2SourceCode2(buff, newNodeName);
		buff.append(");\n");

		buff.append(varName);
		buff.append(".daemonParamVar = ");
		ServiceNode.translateVar2SourceCode(buff, daemonParamVar);

		buff.append(varName);
		buff.append(".daemonServiceName = ");
		ServiceNode.translateString2SourceCode(buff, daemonServiceName);

		for (int i = 0; i < inVars.size(); i++)
		{
			KnowledgeVariable var = inVars.elementAt(i);

			buff.append(varName);
			buff.append(".inVars.add(");
			buff.append(var.codeName);
			buff.append(");\n");
		}

		buff.append(varName);
		buff.append(".isProcessError = ");
		buff.append(isProcessError);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".isToService = ");
		buff.append(isToService);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".isGoto = ");
		buff.append(isGoto);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".lastErrorVar = ");
		ServiceNode.translateVar2SourceCode(buff, lastErrorVar);

		for (int i = 0; i < outVars.size(); i++)
		{
			KnowledgeVariable var = outVars.elementAt(i);

			buff.append(varName);
			buff.append(".outVars.add(");
			buff.append(var.codeName);
			buff.append(");\n");
		}

		buff.append(varName);
		buff.append(".refWorkflowName = ");
		ServiceNode.translateString2SourceCode(buff, refWorkflowName);

		buff.append(varName);
		buff.append(".resultVar = ");
		ServiceNode.translateVar2SourceCode(buff, resultVar);

		buff.append(varName);
		buff.append(".serviceName = ");
		ServiceNode.translateString2SourceCode(buff, serviceName);

		buff.append(varName);
		buff.append(".serviceNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, serviceNameVar);
	}
}
