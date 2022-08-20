package com.channelsoft.slee.usmlreasoning;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class Service implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6716572552221234084L;

	public static int varIndex = 0;

	/**
	 * 判断类名或者包名是否合法，如果非法，则利用转义字符把它合法化。
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void encodeIdentifierName(StringWrapper name)
			throws Exception
	{
		String ret = "";
		for (int i = 0; i < name.value.length(); i++)
		{
			char c = name.value.charAt(i);
			if (Character.isJavaIdentifierPart(c))
			{
				ret += c;
			}
			else
			{
				ret += String.format("_%x",
						Character.codePointAt(name.value, i));
			}
		}

		name.value = ret;
	}

	static public java.lang.reflect.Method getMethodCaller(
			java.lang.Class<?> cls, String methodName)
	{
		try
		{
			return cls.getMethod(methodName, (Class[]) null);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 检查给定的变量名称是否是合法的JAVA变量名。
	 * 
	 * @param name
	 *            要检查的变量名
	 * @return true为合法，false为非法。
	 */
	static boolean isValidVarName(String name)
	{
		if ((name == null) || (name.indexOf('.') != -1) || (name.length() == 0))
		{
			return false;
		}

		if (!Character.isJavaIdentifierStart(name.charAt(0)))
		{
			return false;
		}

		for (int i = 1; i < name.length(); i++)
		{
			char c = name.charAt(i);
			if (!Character.isJavaIdentifierPart(c))
			{
				return false;
			}
		}

		return true;
	}

	public static void parsePath(String fullPathName,
			StringWrapper packageName, StringWrapper className)
			throws Exception
	{
		String[] packageList = null;

		if (fullPathName == null)
		{
			return;
		}

		fullPathName = USMLReasoningEnv.getUniformPath(fullPathName);

		int pos = fullPathName.indexOf(UnifiedServiceManagement.configData
				.getUsmlPath());
		if (pos == -1)
		{
			throw new Exception("USML文件必须位于SLEE安装目录下");
		}

		fullPathName = fullPathName.substring(pos
				+ UnifiedServiceManagement.configData.getUsmlPath().length());

		String splitPath[] = fullPathName.split("/");
		if (splitPath.length >= 3)
		{
			packageList = new String[splitPath.length - 2];
		}

		for (int i = 1, j = 0; i < splitPath.length - 1; i++, j++)
		{
			packageList[j] = splitPath[i];

			StringWrapper pl = new StringWrapper(packageList[j]);
			encodeIdentifierName(pl);

			packageList[j] = "p_" + pl.value;
		}

		className.value = "S_"
				+ splitPath[splitPath.length - 1].substring(0,
						splitPath[splitPath.length - 1].indexOf('.'));
		encodeIdentifierName(className);

		packageName.value = "servicecode";
		for (int i = 0; (packageList != null) && (i < packageList.length); i++)
		{
			packageName.value += ".";
			packageName.value += packageList[i];
		}
	}

	protected Workflow baseWorkflow;

	transient public BaseBrowser browserSite;

	transient public ComputingContextImpl cci = new ComputingContextImpl();

	protected String channelAttribute;

	public String className;

	Workflow currentWorkflow;

	protected KnowledgeVariable eventTypeVar;

	protected Workflow eventWorkflow;

	protected HashMap<String, Service> importServices = new HashMap<String, Service>();

	protected boolean initialized;

	transient protected InternalErr internalErr = new InternalErr();

	transient protected InternalMethod internalMethod = new InternalMethod();

	transient protected InternalObject internalObject = new InternalErr();

	transient protected InternalSleeApp internalSleeApp = new InternalSleeApp();

	transient protected InternalSleeCallController internalSleeCallController = new InternalSleeCallController();

	transient protected InternalSleeService internalSleeService = new InternalSleeService();

	transient protected InternalSleeSession internalSleeSession = new InternalSleeSession();

	protected Vector<KnowledgeVariable> inVars = new Vector<KnowledgeVariable>();

	protected boolean isRootService;

	protected Vector<KnowledgeVariable> keyVars = new Vector<KnowledgeVariable>();

	protected KnowledgeVariable languageTypeVar;

	protected long lastModifyTime = 0;

	protected Vector<KnowledgeVariable> outVars = new Vector<KnowledgeVariable>();

	public String packageName;

	protected Vector<Integer> processEventTypes = new Vector<Integer>();

	String remoteUsmlPath;

	protected String serviceName;

	transient public com.channelsoft.slee.capability.SleeApp sleeApp;

	transient public com.channelsoft.slee.capability.CallControlCapability sleeCallController;

	transient public com.channelsoft.slee.capability.SleeService sleeService;

	transient public com.channelsoft.slee.capability.SleeSession sleeSession;

	protected KnowledgeVariable srvResIDVar;

	transient public com.channelsoft.slee.capability.UICapability ui;

	public String usmlFileName = "";

	protected String usmlFilePath;

	public USMLReasoningEnv usmlReasoningEnv;

	protected Vector<KnowledgeVariable> varInitValues = new Vector<KnowledgeVariable>();

	protected Vector<Workflow> workflows = new Vector<Workflow>();

	public Service()
	{
		initService();
		saveInitVars();
	}

	public Service(USMLReasoningEnv env, BaseBrowser browserSite)
	{
		usmlReasoningEnv = env;
		this.browserSite = browserSite;
		initialized = false;
		initService();
		saveInitVars();
	}

	void browseUSML(String usmlFileName, String resID,
			Vector<KnowledgeVariable> inputVars,
			Vector<KnowledgeVariable> outputVars, USMLReasoningResult error)
			throws Exception
	{
		execute(usmlReasoningEnv, browserSite, resID, inputVars, outputVars,
				error);
	}

	public void clearService()
	{
		srvResIDVar = null;
		eventTypeVar = null;
		languageTypeVar = null;
		baseWorkflow = null;
		currentWorkflow = null;
		eventWorkflow = null;
		keyVars.removeAllElements();
		inVars.removeAllElements();
		outVars.removeAllElements();
		processEventTypes.removeAllElements();
		varInitValues.removeAllElements();
		workflows.removeAllElements();

		Iterator<Entry<String, Service>> it = importServices.entrySet()
				.iterator();
		while (it.hasNext())
		{
			Service svc = it.next().getValue();
			if (svc != null)
			{
				svc.clearService();
			}
		}

		importServices.clear();
	}

	void execute(String resID, Vector<KnowledgeVariable> inputVars,
			Vector<KnowledgeVariable> outputVars, USMLReasoningResult error)
	{
		usmlReasoningEnv.setCurService(this);
		sleeCallController = browserSite.getCallControlCapability();
		sleeService = browserSite.getSleeService();
		sleeApp = browserSite.getSleeApp();
		sleeSession = browserSite.getSleeSession();
		ui = browserSite.getUICapability();

		if (cci == null)
		{
			cci = new ComputingContextImpl();
			internalErr = new InternalErr();
			internalMethod = new InternalMethod();
			internalObject = new InternalErr();
			internalSleeApp = new InternalSleeApp();
			internalSleeCallController = new InternalSleeCallController();
			internalSleeService = new InternalSleeService();
			internalSleeSession = new InternalSleeSession();
		}

		internalErr.setComputingContext(cci);
		internalMethod.setComputingContext(cci);
		internalObject.setComputingContext(cci);
		internalSleeApp.setComputingContext(cci);
		internalSleeCallController.setComputingContext(cci);
		internalSleeService.setComputingContext(cci);
		internalSleeSession.setComputingContext(cci);

		internalErr.init(sleeApp, sleeSession, sleeService, sleeCallController);
		internalMethod.init(sleeApp, sleeSession, sleeService,
				sleeCallController);
		internalObject.init(sleeApp, sleeSession, sleeService,
				sleeCallController);
		internalSleeApp.init(sleeApp, sleeSession, sleeService,
				sleeCallController);
		internalSleeCallController.init(sleeApp, sleeSession, sleeService,
				sleeCallController);
		internalSleeService.init(sleeApp, sleeSession, sleeService,
				sleeCallController);
		internalSleeSession.init(sleeApp, sleeSession, sleeService,
				sleeCallController);

		if (error.value != Constant.EVENT_ResumeService)
		{
			int result = prepare4Execute(resID, inputVars);
			if (result != Constant.EVENT_No_Error)
			{
				error.value = result;
				return;
			}
		}

		try
		{
			currentWorkflow = baseWorkflow;
			currentWorkflow.reason(error);
			if ((error.value != Constant.EVENT_No_Error)
					&& ((error.value & 0xffff0000) != Constant.EVENT_UserEvent)
					&& (error.value != Constant.EVENT_ForceExit)
					&& (error.value != Constant.EVENT_PauseService)
					&& (error.value != Constant.EVENT_GotoService)
					&& (error.value != Constant.EVENT_ExitWorkflow))// 非正常结束工作流时；
			// 启动事件工作流
			{
				if (isAppProcess(error.value))// 应用处理
				{
					browserSite.onUSMLEvent(false, error.value);
					if (eventWorkflow != null)
					{
						eventWorkflow.reason(error);
					}
				}
				else
				// 系统处理
				{
					if (isRootService)
					{
						browserSite.onUSMLEvent(true, error.value);
					}
				}
			}

			if (((error.value & 0xffff0000) != Constant.EVENT_UserEvent)
					&& (error.value != Constant.EVENT_ForceExit)
					&& (error.value != Constant.EVENT_PauseService)
					&& (error.value != Constant.EVENT_GotoService))
			{
				error.value = Constant.EVENT_No_Error;
			}
		}
		catch (Exception e)
		{
			browserSite
					.onRunError("Service::BrowseUSML异常，文件名称：" + usmlFileName);
			Log.wException(LogDef.id_0x10130000, e);
		}
		// 结束服务

		// *******返回服务执行结果*********************************
		try
		{
			if ((outputVars != null)
					&& (error.value != Constant.EVENT_PauseService))
			{
				StringWrapper value = new StringWrapper();
				int size = 0;
				if (outputVars.size() != outVars.size())
				{
					browserSite.onMessage("服务文档输出参数与传出参数个数不匹配。文件名称："
							+ usmlFileName);
				}
				size = Math.min(outputVars.size(), outVars.size());
				for (int i = 0; i < size; i++)
				{
					outVars.elementAt(i).getValue(value);
					KnowledgeVariable var = outputVars.elementAt(i);
					var.setValue(value.value);
				}
			}
		}
		catch (Exception e)
		{
			browserSite.onRunError("返回服务执行结果异常，请检查服务文档输出变量。文件名称："
					+ usmlFileName);
			Log.wException(LogDef.id_0x10130000, e);
		}

		if (error.value != Constant.EVENT_PauseService)
		{
			browserSite.onLeaveService();
		}
	}

	public void execute(USMLReasoningEnv env, BaseBrowser browserSite,
			String resID, Vector<KnowledgeVariable> inputVars,
			Vector<KnowledgeVariable> outputVars, USMLReasoningResult error)
	{
		usmlReasoningEnv = env;
		this.browserSite = browserSite;
		if (baseWorkflow == null)
		{
			initService();
			saveInitVars();
		}
		else if (error.value != Constant.EVENT_ResumeService)
		{
			resetService();
		}

		execute(resID, inputVars, outputVars, error);
		if ((error.value != Constant.EVENT_PauseService)
				&& (((int) (Math.random() * 10) < UnifiedServiceManagement.configData
						.getAutoClear()) || (Runtime.getRuntime().freeMemory() < (Runtime
						.getRuntime().totalMemory() / 10))))
		{
			clearService();
		}
	}

	boolean fileHaveUpdate(String fileName)
	{
		try
		{
			String temp = fileName;
			if (!usmlFileName.equals(temp))
			{// 此文件未被加载过
				return true;
			}
			else
			{

				File usmlFile = new File(fileName);

				if (lastModifyTime == usmlFile.lastModified())
				{
					return false;
				}
				else
				{
					return true;
				}

			}
		}
		catch (Exception e)
		{
			browserSite.onRunError("取文档修改时间出现异常!");
		}
		return true;
	}

	int getEventVar()
	{
		if (eventTypeVar != null)
		{
			StringWrapper szValue = new StringWrapper();
			eventTypeVar.getValue(szValue);
			return USMLFunction.transErrToInt(szValue.value);
		}
		return Constant.EVENT_No_Error;
	}

	/**
	 * 如果是编译并运行的模式，则导入的Service实例不在加载文档时创建。而是 在需要查找Service实例时，才创建它。创建完成后就进行缓存。
	 * 这是为了避免创建无用的Service实例。因为加载文档时创建的Service实例 不是真实的Service子类，而是Service类。
	 * 
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public Service getImportService(String serviceName) throws Exception
	{
		Service service = null;
		serviceName = USMLReasoningEnv.getUniformPath(serviceName);
		service = importServices.get(serviceName);
		service = usmlReasoningEnv.compileUsml(serviceName, service);
		service.usmlReasoningEnv = usmlReasoningEnv;
		service.browserSite = browserSite;
		service.initialized = false;
		importServices.put(serviceName, service);

		return service;
	}

	public HashMap<String, Service> getImportServices()
	{
		return importServices;
	}

	public synchronized int getNewVarIndex()
	{
		return varIndex++;
	}

	int getPronLanguageTypeVar()
	{
		if (languageTypeVar != null)
		{
			StringWrapper languageType = new StringWrapper();
			languageTypeVar.getValue(languageType);
			return USMLFunction.transPronLangToInt(languageType.value);

		}
		else
		{
			return 0;
		}
	}

	String getRealUSMLFilePathName(String fileName)
	{
		fileName = fileName.replace('\\', '/');
		if ((fileName.indexOf(':') == -1) && (fileName.indexOf('/') != 0))
		{
			return usmlFilePath + fileName;
		}
		return fileName;
	}

	String getServiceResID()
	{
		StringWrapper resID = new StringWrapper();
		if (srvResIDVar != null)
		{
			srvResIDVar.getValue(resID);
		}
		return resID.value;
	}

	String getUSMLFilePathName(String fileName)
	{
		return USMLReasoningEnv
				.getUniformPath(getRealUSMLFilePathName(fileName));
	}

	Workflow getWorkflow(String name)
	{
		Workflow workflow = null;
		for (int i = 0; i < workflows.size(); i++)
		{
			workflow = workflows.elementAt(i);
			if (workflow.workflowName.equals(name))
			{
				return workflow;
			}
		}
		return null;
	}

	protected void initService()
	{
	}

	boolean isAppProcess(int eventType)
	{
		for (int i = 0; i < processEventTypes.size(); i++)
		{
			if (eventType == processEventTypes.elementAt(i))
			{
				return true;
			}
		}
		return false;
	}

	boolean loadAppDoc(String fileName) throws Exception
	{
		fileName = USMLReasoningEnv.getUniformPath(fileName);

		if (!fileHaveUpdate(fileName))
		{// 已经加载过并且加载后未被修改的文件不用再次加载
			// m_pBrowserSite->OnMessage(CString(szFileName)+CString(
			// "文档未修改，初始化知识变量。"));
			resetService();
			return true;
		}

		browserSite.onMessage(fileName + "文档第一次使用,或者被修改,加载文档。");
		initService();// 刷新内部变量；

		String strNodeName;
		setUsmlFile(fileName);

		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(new File(usmlFileName));
		NodeList root = doc.getElementsByTagName("Service");
		if ((root == null) || (root.getLength() <= 0))
		{
			usmlFileName = "";
			throw new MyException("文件不存在或者文件不是合法的USML文档；Root节点为null");
		}

		serviceName = root.item(0).getNodeName();
		String szArrib;
		String szBaseWorkflowName = "";
		String szEventWorkflowName = "";
		Node firstNode = null, tempNode = null;
		firstNode = XMLFunction.getFirstChild(root.item(0));
		while (firstNode != null)
		{

			strNodeName = XMLFunction.getNodeName(firstNode);

			if ("BaseWorkflow".equals(strNodeName))
			{
				szBaseWorkflowName = XMLFunction.returnValueFromAttr(firstNode,
						"StartWorkflow");

			}
			else if ("EventWorkflow".equals(strNodeName))
			{
				szEventWorkflowName = XMLFunction.returnValueFromAttr(
						firstNode, "StartWorkflow");

				// *******************************************************
				szArrib = XMLFunction.returnValueFromAttr(firstNode,
						Constant.EventVarNameTag);

				if (!szArrib.equals(Constant.DefEventVarName))
				{
					throw new MyException(
							String.format(
									"EventWorkflow 中的EventVarName=%s不等于m_StarEventType",
									szArrib));
				}
				eventTypeVar = lookupKeyVar(Constant.DefEventVarName);
				if (eventTypeVar == null)
				{
					throw new MyException(String.format(
							"EventWorkflow 中的EventVarName=%s对应的知识变量不存在",
							Constant.DefEventVarName));
				}
				srvResIDVar = lookupKeyVar(Constant.ChannelDNVarName);
				if (srvResIDVar == null)
				{
					throw new MyException(String.format(
							"EventWorkflow ChannelDNVarName=%s对应的知识变量不存在",
							Constant.ChannelDNVarName));
				}
				languageTypeVar = lookupKeyVar(Constant.DefPronLangTypeVarName);
				if (languageTypeVar == null)
				{
					// ASSERT(FALSE);
					// 兼容老版本
					// throw new CMyException("EventWorkflow
					// 中的EventVarName=%s对应的知识变量不存在",LPCSTR(szArrib) );
				}

				Node itemNode = null;
				itemNode = XMLFunction.getFirstChild(firstNode);
				if (itemNode != null)
				{// 浏览事件定义集合
					XMLFunction.verifyNodeTag(itemNode, "EventTypeSet");
					Node childNode = null;
					childNode = XMLFunction.getFirstChild(itemNode);
					while (childNode != null)
					{
						szArrib = XMLFunction.getNodeText(childNode);
						int eventType = USMLFunction.transErrToInt(szArrib);
						if (eventType != 0)
						{
							processEventTypes.add(new Integer(eventType)); // ***
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
							// *
						}
						childNode = XMLFunction.getNextSibling(childNode);
					}
				}
			}
			else if ("WorkflowSet".equals(strNodeName))
			{// 工作流集
				Node itemNode = null;
				Workflow workflow = null;
				itemNode = XMLFunction.getFirstChild(firstNode);
				while (itemNode != null)
				{
					if (itemNode.getNodeType() != 1)
					{
						itemNode = XMLFunction.getNextSibling(itemNode);
						continue;
					}
					workflow = new Workflow(this);
					workflow.setWorkflowName(XMLFunction.returnValueFromAttr(
							itemNode, "Name"));
					workflows.add(workflow);
					itemNode = XMLFunction.getNextSibling(itemNode);

				}

				itemNode = XMLFunction.getFirstChild(firstNode);
				for (int i = 0; i < workflows.size(); i++)
				{
					if (itemNode == null)
					{
						break;
					}
					workflow = workflows.elementAt(i);
					workflow.browseWorkflow(itemNode, null, false);
					itemNode = XMLFunction.getNextSibling(itemNode);
				}
			}
			else if ("KeyVarDef".equals(strNodeName))
			{
				// 知识变量
				Node keyVarNode = null;
				keyVarNode = XMLFunction.getFirstChild(firstNode);

				while (keyVarNode != null)
				{
					KnowledgeVariable variable = new KnowledgeVariable();
					variable.varName = XMLFunction.returnValueFromAttr(
							keyVarNode, "VarName");
					if (!isValidVarName(variable.varName))
					{
						throw new Exception(String.format("知识变量名称[%s]非法",
								variable.varName));
					}

					variable.varName = variable.varName.trim();
					variable.codeName = "var_" + variable.varName;
					variable.varType = KnowledgeVariable
							.convertStringToVarType(XMLFunction
									.returnValueFromAttr(keyVarNode, "VarType"));

					variable.setValue(XMLFunction.returnValueFromAttr(
							keyVarNode, "InitiaValue"));

					if (lookupKeyVar(variable.varName) == null)
					{
						keyVars.add(variable);
					}

					keyVarNode = XMLFunction.getNextSibling(keyVarNode);

				}
				// 检查是否预定义的知识变量都已经定义
				KnowledgeVariable var = lookupKeyVar(Constant.ChannelDNVarName);
				if (var == null)
				{
					throw new MyException("知识变量m_ChannelDN未定义");
				}
				var = lookupKeyVar(Constant.DefResultVarName);
				if (var == null)
				{
					throw new MyException("知识变量m_PrevResult未定义");
				}
				var = lookupKeyVar(Constant.DefEventVarName);
				if (var == null)
				{
					throw new MyException("知识变量m_StarEventType未定义");
				}
			}
			else if ("SrvDefine".equals(strNodeName))
			{
				String srcNodeName;
				serviceName = XMLFunction.returnValueFromAttr(firstNode,
						"ServiceName");
				tempNode = null;
				tempNode = XMLFunction.getFirstChild(firstNode);
				while (tempNode != null)
				{
					srcNodeName = XMLFunction.getNodeName(tempNode);

					if ("SrvAttributeSet".equals(srcNodeName))
					{
						channelAttribute = XMLFunction.getNodeText(tempNode);
					}

					if ("InputVarSet".equals(srcNodeName))
					{
						// 输入变量

						Node inVarNode = null;
						inVarNode = XMLFunction.getFirstChild(tempNode);
						while (null != inVarNode)
						{
							KnowledgeVariable variable = new KnowledgeVariable();
							variable.varName = XMLFunction.returnValueFromAttr(
									inVarNode, "VarName");
							variable.varName = variable.varName.trim();
							variable.codeName = "var_" + variable.varName;

							variable.varType = KnowledgeVariable
									.convertStringToVarType(XMLFunction
											.returnValueFromAttr(inVarNode,
													"VarType"));
							String var;
							var = XMLFunction.returnValueFromAttr(inVarNode,
									"InitiaValue");
							variable.setValue(var);

							if (lookupKeyVar(variable.varName) == null)
							{
								inVars.add(variable);
							}

							inVarNode = XMLFunction.getNextSibling(inVarNode);

						}
					}

					// 输出变量
					if ("OutputVarSet".equals(srcNodeName))
					{
						// 输入变量

						Node outVarNode = null;
						outVarNode = XMLFunction.getFirstChild(tempNode);
						while (null != outVarNode)
						{
							KnowledgeVariable variable = new KnowledgeVariable();
							variable.varName = XMLFunction.returnValueFromAttr(
									outVarNode, "VarName");
							variable.varName = variable.varName.trim();
							variable.codeName = "var_" + variable.varName;

							variable.varType = KnowledgeVariable
									.convertStringToVarType(XMLFunction
											.returnValueFromAttr(outVarNode,
													"VarType"));
							String var;
							var = XMLFunction.returnValueFromAttr(outVarNode,
									"InitiaValue");
							variable.setValue(var);

							if (lookupKeyVar(variable.varName) == null)
							{
								outVars.add(variable);
							}

							outVarNode = XMLFunction.getNextSibling(outVarNode);

						}
					}

					tempNode = XMLFunction.getNextSibling(tempNode);

				}
			}
			else if ("ImportServiceSet".equals(strNodeName))
			{
				// 知识变量
				Node srvNameNode = null;
				srvNameNode = XMLFunction.getFirstChild(firstNode);
				while ((srvNameNode != null)
						&& !srvNameNode.getNodeName().equals("ServiceName"))
				{
					srvNameNode = XMLFunction.getNextSibling(srvNameNode);
				}
				tempNode = null;
				String srvName;
				while (srvNameNode != null)
				{
					srvName = XMLFunction.getNodeText(srvNameNode);
					srvName = getUSMLFilePathName(srvName).replace('\\', '/');

					Service importSrv = null;

					importServices.put(srvName, importSrv);
					srvNameNode = XMLFunction.getNextSibling(srvNameNode);
				}
			}

			firstNode = XMLFunction.getNextSibling(firstNode);

		}// while
		baseWorkflow = getWorkflow(szBaseWorkflowName);
		if (baseWorkflow == null)
		{
			throw new MyException("BaseWorkflow中指定的StartWorkflow名称错误");
		}
		eventWorkflow = getWorkflow(szEventWorkflowName);
		/*
		 * if( m_pEventWorkflow == NULL) { throw new
		 * CMyException("EventWorkflow中指定的StartWorkflow名称错误"); }
		 */

		// 获得文件最后被修改的时间
		File usmlFile = new File(fileName);
		if (lastModifyTime != usmlFile.lastModified())
		{
			lastModifyTime = usmlFile.lastModified();
		}
		else
		{
			throw new MyException("无法获得文件最后一次修改的时间");
		}

		// 纪录变量初始化值
		saveInitVars();

		return true;
	}

	protected KnowledgeVariable lookupKeyVar(String varName)
	{
		if (varName == null)
		{
			return null;
		}

		varName = varName.trim();
		for (int i = 0; i < keyVars.size(); i++)
		{

			if (keyVars.elementAt(i).varName.equals(varName))
			{
				return keyVars.elementAt(i);
			}
		}
		for (int i = 0; i < inVars.size(); i++)
		{
			if (inVars.elementAt(i).varName.equals(varName))
			{
				return inVars.elementAt(i);
			}
		}
		for (int i = 0; i < outVars.size(); i++)
		{
			if (outVars.elementAt(i).varName.equals(varName))
			{
				return outVars.elementAt(i);
			}
		}
		return null;
	}

	protected void onSetUsmlFile() throws Exception
	{
		StringWrapper classNameWrapper = new StringWrapper();
		StringWrapper packageNameWrapper = new StringWrapper();

		parsePath(usmlFileName, packageNameWrapper, classNameWrapper);
		packageName = packageNameWrapper.value;
		className = classNameWrapper.value;

		usmlFilePath = usmlFileName;
		int pos = usmlFilePath.lastIndexOf('/');
		if (pos != -1)
		{
			usmlFilePath = usmlFilePath.substring(0, pos + 1);
		}

		if (remoteUsmlPath != null)
		{
			usmlFilePath = usmlFilePath.replace(
					UnifiedServiceManagement.configData.getUsmlPath(),
					remoteUsmlPath);
		}
	}

	/**
	 * @param resID
	 * @param inputVars
	 */
	private int prepare4Execute(String resID,
			Vector<KnowledgeVariable> inputVars)
	{
		USMLReasoningResult enterServiceResult = new USMLReasoningResult(
				Constant.EVENT_No_Error);
		browserSite.onEnterService(usmlFileName, serviceName, channelAttribute,
				enterServiceResult);
		if (enterServiceResult.value != Constant.EVENT_No_Error)
		{
			browserSite.onRunError("服务执行被终止，可能是未授权，文件名称：" + usmlFileName);
			browserSite.onLeaveService();
			return enterServiceResult.value;
		}

		// *******服务初始化*********************************
		try
		{
			if (srvResIDVar != null)
			{
				srvResIDVar.setValue(resID);
			}
			if (inputVars != null)
			{
				StringWrapper value = new StringWrapper();
				int size = 0;
				if (inputVars.size() != inVars.size())
				{
					browserSite.onRunError("服务文档输入参数与传入参数个数不匹配。文件名称："
							+ usmlFileName);
				}

				size = Math.min(inputVars.size(), inVars.size());
				for (int i = 0; i < size; i++)
				{
					KnowledgeVariable var = inputVars.elementAt(i);
					var.getValue(value);
					inVars.elementAt(i).setValue(value.value);
				}
			}
		}
		catch (Exception e)
		{
			browserSite.onRunError("服务初始化异常，请检查服务文档输入变量。文件名称：" + usmlFileName);
			Log.wException(e);
		}

		// ****************************************
		if (baseWorkflow == null)
		{
			browserSite.onRunError("服务初始化异常，baseWorkflow=null");
			browserSite.onLeaveService();
			return Constant.EVENT_InvalidUSMLFile;
		}

		currentWorkflow = baseWorkflow;

		return Constant.EVENT_No_Error;
	}

	void resetService()
	{
		// 变量初始化
		for (int i = 0; i < keyVars.size(); i++)
		{
			for (int j = 0; j < varInitValues.size(); j++)
			{
				if (keyVars.elementAt(i).varName.equals(varInitValues
						.elementAt(j).varName))
				{
					keyVars.elementAt(i).copyVar(varInitValues.elementAt(j));
				}
			}
		}

		for (int i = 0; i < inVars.size(); i++)
		{
			for (int j = 0; j < varInitValues.size(); j++)
			{
				if (inVars.elementAt(i).varName.equals(varInitValues
						.elementAt(j).varName))
				{
					inVars.elementAt(i).copyVar(varInitValues.elementAt(j));
				}
			}
		}
		for (int i = 0; i < outVars.size(); i++)
		{
			for (int j = 0; j < varInitValues.size(); j++)
			{
				if (outVars.elementAt(i).varName.equals(varInitValues
						.elementAt(j).varName))
				{
					outVars.elementAt(i).copyVar(varInitValues.elementAt(j));
				}
			}
		}
	}

	public void resetVarIndex()
	{
		varIndex = 0;
	}

	void saveInitVars()
	{
		varInitValues.removeAllElements();
		for (int i = 0; i < keyVars.size(); i++)
		{
			KnowledgeVariable srcVar = keyVars.elementAt(i);
			KnowledgeVariable var = new KnowledgeVariable();
			var.varName = srcVar.varName;
			var.codeName = srcVar.codeName;
			var.cloneVar(srcVar);
			varInitValues.add(var);
		}

		for (int i = 0; i < inVars.size(); i++)
		{
			KnowledgeVariable srcVar = inVars.elementAt(i);
			KnowledgeVariable var = new KnowledgeVariable();
			var.varName = srcVar.varName;
			var.codeName = srcVar.codeName;
			var.cloneVar(srcVar);

			varInitValues.add(inVars.elementAt(i));
		}

		for (int i = 0; i < outVars.size(); i++)
		{
			KnowledgeVariable srcVar = outVars.elementAt(i);
			KnowledgeVariable var = new KnowledgeVariable();
			var.varName = srcVar.varName;
			var.codeName = srcVar.codeName;
			var.cloneVar(srcVar);
			varInitValues.add(outVars.elementAt(i));
		}
	}

	void setCurrentWorkflow(Workflow workflow)
	{
		currentWorkflow = workflow;
	}

	void setEventVar(int eventType)
	{
		if (null != this.eventTypeVar)
		{
			eventTypeVar.setUSMLEventValue(eventType);
		}
	}

	void setPronLanguageTypeVar(String pronLang)
	{
		if (languageTypeVar != null)
		{
			languageTypeVar.setPronLanguageTypeValue(pronLang);
		}
	}

	public void setRemoteUsmlPath(String remoteUsmlPath)
	{
		this.remoteUsmlPath = remoteUsmlPath;
	}

	public void setUsmlFile(String fileName) throws Exception
	{
		usmlFileName = USMLReasoningEnv.getUniformPath(fileName);
		int pos = usmlFileName.indexOf(UnifiedServiceManagement.configData
				.getUsmlPath());
		if (pos == -1)
		{
			throw new Exception("USML文件必须位于SLEE安装目录下");
		}

		onSetUsmlFile();
	}

	public void translate2SourceCode(String fileName, String newClassName,
			StringBuilder buff) throws Exception
	{
		resetVarIndex();

		usmlReasoningEnv.setCurService(this);
		loadAppDoc(fileName);
		usmlFileName = (remoteUsmlPath == null ? usmlFileName : usmlFileName
				.replace(UnifiedServiceManagement.configData.getUsmlPath(),
						remoteUsmlPath));

		buff.append("package ");
		buff.append(packageName);
		buff.append(";\n\n");

		buff.append("import java.io.File;\n");
		buff.append("import java.util.Calendar;\n");
		buff.append("import java.util.HashMap;\n");
		buff.append("import java.util.Iterator;\n");
		buff.append("import java.util.Vector;\n");
		buff.append("import java.util.Map.Entry;\n\n");
		buff.append("import com.channelsoft.slee.util.Constant;\n");
		buff.append("import com.channelsoft.reusable.util.*;\n");
		buff.append("import com.channelsoft.slee.usmlreasoning.*;\n\n");
		buff.append("import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;\n\n");

		buff.append("public class ");
		buff.append(newClassName);
		buff.append(" extends Service{\n");
		buff.append("public ");
		buff.append(newClassName);
		buff.append("() {}\n\n");

		for (int i = 0; i < varInitValues.size(); i++)
		{
			KnowledgeVariable var = varInitValues.elementAt(i);

			buff.append("KnowledgeVariable ");
			buff.append(var.codeName);
			buff.append(";\n\n");
		}

		for (int i = 0; i < workflows.size(); i++)
		{
			Workflow wf = workflows.elementAt(i);

			buff.append("Workflow ");
			buff.append(wf.getVarName());

			buff.append(";\n");
		}

		buff.append(";\n");
		for (int i = 0; i < workflows.size(); i++)
		{
			Workflow wf = workflows.elementAt(i);
			wf.translateMethodCaller2SourceCode(buff, newClassName);
		}

		buff.append("protected void initService()\n");
		buff.append("{\n");

		for (int i = 0; i < keyVars.size(); i++)
		{
			KnowledgeVariable var = keyVars.elementAt(i);

			buff.append(var.codeName);
			buff.append(" = new KnowledgeVariable();\n");
			buff.append(var.codeName);
			buff.append(".varName = ");
			ServiceNode.translateString2SourceCode(buff, var.varName);

			buff.append(var.codeName);
			buff.append(".varType = KnowledgeVariable.VarType.");
			buff.append(var.varType.toString());
			buff.append(";\n");

			buff.append(var.codeName);
			buff.append(".setValue(");
			StringWrapper valueWrapper = new StringWrapper();
			if (!var.getValue(valueWrapper))
			{
				throw new Exception("变量" + var.varName + "初始化代码生成失败!");
			}
			ServiceNode.translateString2SourceCode2(buff, valueWrapper.value);
			buff.append(");\n");

			buff.append("keyVars.addElement(");
			buff.append(var.codeName);
			buff.append(");\n\n");
		}

		for (int i = 0; i < inVars.size(); i++)
		{
			KnowledgeVariable var = inVars.elementAt(i);

			buff.append(var.codeName);
			buff.append(" = new KnowledgeVariable();\n");
			buff.append(var.codeName);
			buff.append(".varName = ");
			ServiceNode.translateString2SourceCode(buff, var.varName);

			buff.append(var.codeName);
			buff.append(".varType = KnowledgeVariable.VarType.");
			buff.append(var.varType.toString());
			buff.append(";\n");

			buff.append(var.codeName);
			buff.append(".setValue(");
			StringWrapper valueWrapper = new StringWrapper();
			if (!var.getValue(valueWrapper))
			{
				throw new Exception("变量" + var.varName + "初始化代码生成失败!");
			}
			ServiceNode.translateString2SourceCode2(buff, valueWrapper.value);
			buff.append(");\n");

			buff.append("inVars.addElement(");
			buff.append(var.codeName);
			buff.append(");\n\n");
		}

		for (int i = 0; i < outVars.size(); i++)
		{
			KnowledgeVariable var = outVars.elementAt(i);

			buff.append(var.codeName);
			buff.append(" = new KnowledgeVariable();\n");
			buff.append(var.codeName);
			buff.append(".varName = ");
			ServiceNode.translateString2SourceCode(buff, var.varName);

			buff.append(var.codeName);
			buff.append(".varType = KnowledgeVariable.VarType.");
			buff.append(var.varType.toString());
			buff.append(";\n");

			buff.append(var.codeName);
			buff.append(".setValue(");
			StringWrapper valueWrapper = new StringWrapper();
			if (!var.getValue(valueWrapper))
			{
				throw new Exception("变量" + var.varName + "初始化代码生成失败!");
			}
			ServiceNode.translateString2SourceCode2(buff, valueWrapper.value);
			buff.append(");\n");

			buff.append("outVars.addElement(");
			buff.append(var.codeName);
			buff.append(");\n\n");
		}

		if (eventTypeVar != null)
		{
			buff.append("eventTypeVar = ");
			buff.append(eventTypeVar.codeName);
			buff.append(";\n");
		}

		if (srvResIDVar != null)
		{
			buff.append("srvResIDVar = ");
			buff.append(srvResIDVar.codeName);
			buff.append(";\n");
		}

		if (languageTypeVar != null)
		{
			buff.append("languageTypeVar = ");
			buff.append(languageTypeVar.codeName);
			buff.append(";\n\n");
		}

		for (int i = 0; i < processEventTypes.size(); i++)
		{
			Integer eventType = processEventTypes.elementAt(i);

			buff.append("processEventTypes.addElement(new Integer(");
			buff.append(eventType.intValue());
			buff.append("));\n\n");
		}

		buff.append("channelAttribute = ");
		ServiceNode.translateString2SourceCode(buff, channelAttribute);

		buff.append("serviceName = ");
		ServiceNode.translateString2SourceCode(buff, serviceName);

		buff.append("try{setUsmlFile(\"");
		buff.append(usmlFileName);
		buff.append("\");}catch (Exception e){}\n\n");

		Iterator<Entry<String, Service>> it = importServices.entrySet()
				.iterator();
		for (int i = 0; it.hasNext(); i++)
		{
			Entry<String, Service> entry = it.next();
			String svcFileName = entry.getKey();

			buff.append("importServices.put(\"");
			buff.append(svcFileName);
			buff.append("\", null);\n\n");
		}

		for (int i = 0; i < workflows.size(); i++)
		{
			Workflow wf = workflows.elementAt(i);

			buff.append(wf.getVarName());

			buff.append(" = new Workflow(this);\n");
		}

		for (int i = 0; i < workflows.size(); i++)
		{
			Workflow wf = workflows.elementAt(i);

			buff.append("initWorkflow_");
			buff.append(wf.getVarName());
			buff.append("(");
			buff.append(wf.getVarName());
			buff.append(");\n");

			buff.append(wf.getVarName());
			buff.append(".setWorkflowName(\"");
			buff.append(wf.workflowName);
			buff.append("\");\n");

			if (baseWorkflow.workflowName.equals(wf.workflowName))
			{
				buff.append("baseWorkflow = ");
				buff.append(wf.getVarName());
				buff.append(";\n");
			}

			if ((eventWorkflow != null)
					&& eventWorkflow.workflowName.equals(wf.workflowName))
			{
				buff.append("eventWorkflow = ");
				buff.append(wf.getVarName());
				buff.append(";\n");
			}

			buff.append("workflows.addElement(");
			buff.append(wf.getVarName());
			buff.append(");\n\n");
		}

		buff.append("}\n");

		for (int i = 0; i < workflows.size(); i++)
		{
			Workflow wf = workflows.elementAt(i);

			buff.append("protected void initWorkflow_");
			buff.append(wf.getVarName());
			buff.append("(Workflow wf)\n");
			buff.append("{\n");

			StringBuilder computeNodeBuffer = new StringBuilder();
			wf.translate2SourceCode(buff, computeNodeBuffer);

			buff.append("}\n\n");

			buff.append(computeNodeBuffer);
		}

		buff.append("public void clearService()\n");
		buff.append("{\n");
		buff.append("if (baseWorkflow == null) return;");

		for (int i = 0; i < workflows.size(); i++)
		{
			Workflow wf = workflows.elementAt(i);

			buff.append(wf.getVarName());
			buff.append(".clearWorkflow();\n");
			buff.append(wf.getVarName());
			buff.append(" = null;\n");

			buff.append("\n");
		}

		buff.append("super.clearService();\n");

		buff.append("}\n");

		buff.append("}\n");
	}
}
