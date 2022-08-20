package com.channelsoft.slee.usmlreasoning;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class SrvScriptNode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4515529986370913106L;

	public boolean callDll;

	public String content = "";

	public DllCaller dllCaller = new DllCaller();

	public Vector<KnowledgeVariable> inVars = new Vector<KnowledgeVariable>();

	public boolean isCallPlugin = false;

	public boolean isSafeMode;

	public boolean isValidPlugin = false;

	public String languageType;

	transient public java.lang.reflect.Method method2Exec;

	public Vector<KnowledgeVariable> outVars = new Vector<KnowledgeVariable>();

	Vector<PlugIn.Command> pluginCommands = new Vector<PlugIn.Command>();

	public boolean processError;

	public KnowledgeVariable resultVar;

	public int timeoutSecond;

	public KnowledgeVariable timeoutSecondVar;

	public SrvScriptNode()
	{

	}

	public SrvScriptNode(Service service, Workflow workflow)
	{
		super(service, workflow);
		processError = false;
		nextSrvNode = null;
		timeoutSecond = 0;
		timeoutSecondVar = null;
		resultVar = null;
		isCallPlugin = false;
		isValidPlugin = true;
		callDll = false;
	}

	public void browseContent()
	{
		String temp = content;
		temp = temp.toLowerCase();

		if (temp.indexOf("sleeplg.") >= 0)
		{
			isCallPlugin = true;

			// 分解Content内容
			Vector<String> urls = new Vector<String>();
			char end = 0x0A;
			if (content.length() >= 2)
			{
				if (content.charAt(content.length() - 1) != end)
				{
					content += end;
				}
			}

			int start = 0;
			int linePos = content.indexOf(end, start);
			while (linePos != -1)
			{
				urls.add(content.substring(start, linePos));
				start = linePos + 1;
				linePos = content.indexOf(end, start);
			}

			for (int count = 0; count < urls.size(); count++)
			{
				temp = urls.elementAt(count);
				temp = temp.trim();

				if (temp.length() <= 0)
				{
					continue;
				}

				if (temp.charAt(0) == '\'')
				{
					continue; // '\'' 是注释
				}

				// szTemp.MakeLower();

				PlugIn plugIn = new PlugIn();
				PlugIn.Command command = plugIn.createCommand();
				if (plugIn.getCommand(temp, command) == true)
				{
					pluginCommands.add(command); // 析构时delete
				}
				else
				{
					isValidPlugin = false;
				}
			}
		}
		else if (temp.indexOf("rundll") >= 0)
		{
			callDll = true;
			dllCaller.browseContent(this);
		}
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
		languageType = XMLFunction.returnValueFromAttr(node, "Language");
		isSafeMode = XMLFunction.getBoolFromAttrib(node, "SafeMode", true);

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
			else if ("Content".equals(strNodeName))
			{
				content = XMLFunction.getNodeText(childNode);
			}

			childNode = XMLFunction.getNextSibling(childNode);
		}

		if (languageType.compareToIgnoreCase("ServerPage") == 0)
		{
			browseContent();
		}

		return null;
	}

	int callDll()
	{
		if (!dllCaller.isValid)
		{
			String errorMsg;
			errorMsg = String.format("NodeName=%s执行DLL节点失败，原因:%s", nodeName,
					dllCaller.errorInfo);
			service.browserSite.onRunError(errorMsg);

			return Constant.EVENT_GeneralError;
		}

		int error = Constant.EVENT_No_Error;

		if (!dllCaller.execute(this))
		{
			String errorMsg;
			errorMsg = String.format("NodeName=%s执行DLL节点失败，原因:%s", nodeName,
					dllCaller.errorInfo);
			service.browserSite.onRunError(errorMsg);

			return Constant.EVENT_GeneralError;
		}

		return error;
	}

	int callPlugin()
	{
		if (!isValidPlugin)
		{
			return Constant.EVENT_GeneralError;
		}

		int error = Constant.EVENT_No_Error;

		int ret = 0;
		PlugIn.Command command = null;
		KnowledgeVariable var = null;
		int i = 0;
		for (int count = 0; count < pluginCommands.size(); count++)
		{
			command = pluginCommands.elementAt(count);

			switch (command.id)
			{
			case cmdSleep:
			{
				if (command.params.size() < 1)
				{
					return Constant.EVENT_GeneralError;
				}

				int sleepMillis = 0;
				if (!command.params.elementAt(0).isConst)
				{
					var = getInVar(command.params.elementAt(0).name);
					String sleepStr = command.params.elementAt(0).name;
					if (null != var)
					{
						sleepStr = var.getValue();
					}

					sleepMillis = Integer.parseInt(sleepStr);
				}
				else
				{
					sleepMillis = Integer
							.parseInt(command.params.elementAt(0).value);
				}

				PlugIn.sleep(sleepMillis);
			}

				break;

			case cmdIfEx:
			{
				if (command.params.size() < 4)
				{
					return Constant.EVENT_GeneralError;
				}

				for (i = 0; i < 4; i++)
				{
					if (!command.params.elementAt(i).isConst)
					{
						var = getInVar(command.params.elementAt(i).name);
						if ((null != var)
								|| ((var = getOutVar(command.params
										.elementAt(i).name)) != null))
						{
							command.params.elementAt(i).value = var.getValue();
						}

					}
				}

				int channel = 0;
				try
				{
					channel = Integer
							.parseInt(command.params.elementAt(0).value);
				}
				catch (Exception e)
				{

				}

				int switchCase = 0;
				try
				{
					switchCase = Integer
							.parseInt(command.params.elementAt(1).value);
				}
				catch (Exception e)
				{

				}

				StringWrapper p1Wrapper = new StringWrapper(command.params
						.elementAt(2).value);
				StringWrapper p2Wrapper = new StringWrapper(command.params
						.elementAt(3).value);
				// //////////////////////////////// exec
				ret = PlugIn.ifEx(channel, switchCase, p1Wrapper, p2Wrapper);
				command.params.elementAt(2).value = p1Wrapper.value;
				command.params.elementAt(3).value = p2Wrapper.value;

				for (i = 2; i < 4; i++)
				{
					if (!command.params.elementAt(i).isConst)
					{
						var = getOutVar(command.params.elementAt(i).name);
						if (null != var)
						{
							var.setValue(command.params.elementAt(i).value);
						}
					}
				}

				var = getOutVar(command.returnName);
				if (null != var)
				{
					var.setValue(ret);
				}
			}
				break;

			case cmdSIf:
			{
				if (command.params.size() < 6)
				{
					return Constant.EVENT_GeneralError;
				}

				for (i = 0; i < 6; i++)
				{
					if (!command.params.elementAt(i).isConst)
					{
						var = getInVar(command.params.elementAt(i).name);
						if ((null != var)
								|| ((var = getOutVar(command.params
										.elementAt(i).name)) != null))
						{
							command.params.elementAt(i).value = var.getValue();
						}

					}
				}
				int channel = 0;
				try
				{
					channel = Integer
							.parseInt(command.params.elementAt(0).value);
				}
				catch (Exception e)
				{

				}

				int timeout = 0;
				try
				{
					timeout = Integer
							.parseInt(command.params.elementAt(5).value);
				}
				catch (Exception e)
				{

				}

				String server = command.params.elementAt(1).value;
				int switchCase = 0;
				try
				{
					switchCase = Integer
							.parseInt(command.params.elementAt(2).value);
				}
				catch (Exception e)
				{

				}
				String in = command.params.elementAt(3).value;
				StringWrapper outWrapper = new StringWrapper(command.params
						.elementAt(4).value);
				// //////////////////////////////// exec
				ret = PlugIn.sIf(channel, server, switchCase, in, outWrapper,
						timeout);
				command.params.elementAt(4).value = outWrapper.value;

				if (!command.params.elementAt(4).isConst)
				{
					var = getOutVar(command.params.elementAt(4).name);
					if (null != var)
					{
						var.setValue(command.params.elementAt(4).value);
					}
				}

				var = getOutVar(command.returnName);
				if (null != var)
				{
					var.setValue(ret);
				}
			}
				break;

			default:
				break;
			}// switch
			if (error != Constant.EVENT_No_Error)
			{
				return error;
			}
		}// for

		return error;
	}

	int callServerPage()
	{
		if (isCallPlugin)
		{
			return callPlugin();
		}
		if (callDll == true)
		{
			return callDll();
		}
		return 0;
	}

	int execScriptSafe()
	{
		int error = Constant.EVENT_No_Error;
		boolean haveCall = !service.browserSite.isIdle();

		try
		{
			service.cci.clear();

			if ("VBScript".equals(languageType))
			{
				execVBScript(content);
			}
		}
		catch (MyException e)
		{
			error = Constant.EVENT_ScriptRunError;
			String info = String.format("执行脚本时出现错误：节点名称=%s, 行号=%d, 错误原因=%s %s",
					nodeName, service.cci.getCurrentLine(), e.getMessage(), e
							.toString());
			service.browserSite.onRunError(info);
		}
		catch (Throwable e)
		{
			error = Constant.EVENT_ScriptRunError;
			String info = String.format("执行脚本时出现异常：节点名称=%s, 行号=%d, 错误原因=%s %s",
					nodeName, service.cci.getCurrentLine(), e.getMessage(), e
							.toString());
			Log.wException(LogDef.id_0x10130000, e);
			service.browserSite.onRunError(info);

		}
		finally
		{
			service.cci.clear();
		}

		if (haveCall && service.browserSite.isIdle())
		{
			error = Constant.EVENT_CustomHangup;
		}

		return error;
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

			if ("ServerPage".equals(languageType))
			{
				boolean haveCall = !service.browserSite.isIdle();
				error.value = callServerPage();
				if (haveCall && service.browserSite.isIdle())
				{
					error.value = Constant.EVENT_CustomHangup;
				}
			}
			else
			{
				error.value = execScriptSafe();
			}

			resultVar.setUSMLEventValue(error.value);
			service.setEventVar(error.value);

			if (error.value == Constant.EVENT_No_Error)
			{
				return nextSrvNode;
			}
			else if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
			{
				String msg;
				msg = String.format("文档执行被用户事件终止，节点名称=%s", nodeName);
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
				// }else if( (nError == EVENT_CustomHangup)||(nError ==
				// EVENT_ToAgent))
				// {//这两种事件不能够本地处理
				// return NULL;
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
				temp = String.format("执行计算节点返回%s, m_bProcessError=%s",
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
			service.browserSite.onRunError("NodeName=" + nodeName
					+ "执行Script节点异常");
			Log.wException(LogDef.id_0x10130000, e);
			return null;
		}
	}

	public void execVBScript() throws Exception
	{
		try
		{
			if (method2Exec == null)
			{
				method2Exec = Service.getMethodCaller(service.getClass(),
						varName + "_execVBScript");
			}

			method2Exec.invoke(service);
		}
		catch (InvocationTargetException ite)
		{
			Throwable e = ite.getTargetException();
			if (e != null)
			{
				Log.wException(LogDef.id_0x10130000, e);
				throw new Exception(e);
			}
		}
	}

	void execVBScript(String script) throws Exception
	{
		execVBScript();
	}

	String getHideName(String varName)
	{
		String tmpHideVarName;
		tmpHideVarName = varName + Constant.HIDKEY;
		return tmpHideVarName;
	}

	KnowledgeVariable getInVar(String varName)
	{
		for (int i = 0; i < inVars.size(); i++)
		{
			if (varName.compareToIgnoreCase(inVars.elementAt(i).varName) == 0)
			{
				return inVars.elementAt(i);
			}
		}
		return null;
	}

	KnowledgeVariable getOutVar(String varName)
	{
		for (int i = 0; i < outVars.size(); i++)
		{
			if (varName.compareToIgnoreCase(outVars.elementAt(i).varName) == 0)
			{
				return outVars.elementAt(i);
			}
		}
		return null;
	}

	@Override
	void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SrvScriptNode ");
		buff.append(varName);
		buff.append(" = new SrvScriptNode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");

		buff.append(varName);
		buff.append(".varName = ");
		ServiceNode.translateString2SourceCode(buff, varName);

		if ("VBScript".equals(languageType))
		{
			buff.append(varName);
			buff.append(".method2Exec = method_");
			buff.append(varName);
			buff.append("_execVBScript");
			buff.append(";\n");
		}

		buff.append(varName);
		buff.append(".callDll = ");
		buff.append(callDll);
		buff.append(";\n");

		if (!"VBScript".equals(languageType))
		{
			buff.append(varName);
			buff.append(".content = ");
			ServiceNode.translateString2SourceCode(buff, content);
		}

		buff.append(varName);
		buff.append(".isSafeMode = ");
		buff.append(isSafeMode);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".languageType = ");
		ServiceNode.translateString2SourceCode(buff, languageType);

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

		buff.append(varName);
		buff.append(".isValidPlugin = ");
		buff.append(isValidPlugin);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".isCallPlugin = ");
		buff.append(isCallPlugin);
		buff.append(";\n");

		if (callDll || isCallPlugin)
		{
			buff.append(varName);
			buff.append(".browseContent();\n");
		}
	}

	@Override
	void translateMethodCaller2SourceCode(StringBuilder buff,
			String newClassName) throws Exception
	{
		if (!"VBScript".equals(languageType))
		{
			return;
		}

		buff.append("transient static public java.lang.reflect.Method method_");
		buff.append(varName);
		buff.append("_execVBScript = getMethodCaller(");
		buff.append(newClassName);
		buff.append(".class, \"");
		buff.append(varName);
		buff.append("_execVBScript");
		buff.append("\");\n");
	}

	@Override
	public void translateScript2SourceCode(StringBuilder buff) throws Exception
	{
		if (!"VBScript".equals(languageType))
		{
			return;
		}

		buff.append("public void ");
		buff.append(varName);
		buff.append("_execVBScript() throws Exception\n");
		buff.append("{\n");
		com.channelsoft.vbs2java_v2.Parser parser = new com.channelsoft.vbs2java_v2.Parser(
				varName);
		StringBuilder sp = new StringBuilder();

		boolean bodySucceeded = false;

		try
		{
			parser
					.outPutJavaCode(content, inVars, outVars,
							UnifiedServiceManagement.comContext
									.getComponentRegistrys(), sp);
			bodySucceeded = true;
		}
		catch (InstantiationError ie) // 上海10086项目临时修改，待删除
		{
			String errorInfo = String.format("脚本有错误，原因：%s", ie.getMessage());
			sp.append("throw new MyException(");
			ServiceNode.translateString2SourceCode2(sp, errorInfo);
			sp.append(");\n");
		}
		catch (Exception e)
		{
			if (service.browserSite instanceof BaseBrowserMock)
			{
				String errorInfo = String.format(
						"文档[%s]-工作流[%s]-节点[%s]脚本有错误，原因：%s",
						service.usmlFileName, workflow.workflowName, nodeName,
						e.getMessage());
				throw new MyException(errorInfo);
			}
			else
			{
				String errorInfo = String.format("脚本有错误，原因：%s", e.getMessage());
				if (service.browserSite instanceof RuntimeBaseBrowserMock)
				{
					errorInfo = String.format(
							"文档[%s]-工作流[%s]-节点[%s]脚本有错误，原因：%s",
							service.usmlFileName, workflow.workflowName,
							nodeName, e.getMessage());
					System.out.println(errorInfo);
					e.printStackTrace(System.out);
				}

				sp.append("throw new MyException(");
				ServiceNode.translateString2SourceCode2(sp, errorInfo);
				sp.append(");\n");
			}
		}
		buff.append(sp.toString());
		buff.append("}\n\n");

		// 如果脚本输出不成功，则不输出方法的代码。
		if (!bodySucceeded)
		{
			return;
		}

		StringBuilder sp1 = new StringBuilder();
		try
		{
			parser.outPutJavaMethod(sp1);
		}
		catch (Exception e)
		{
			if (service.browserSite instanceof BaseBrowserMock)
			{
				String errorInfo = String.format(
						"文档[%s]-工作流[%s]-节点[%s]脚本函数定义有错误，原因：%s",
						service.usmlFileName, workflow.workflowName, nodeName,
						e.getMessage());
				throw new MyException(errorInfo);
			}
			else
			{
				String errorInfo = String.format("脚本函数定义有错误，原因：%s", e
						.getMessage());
				sp1.append("throw new MyException(");
				ServiceNode.translateString2SourceCode2(sp1, errorInfo);
				sp1.append(");\n");
			}
		}
		buff.append(sp1.toString());
	}
}
