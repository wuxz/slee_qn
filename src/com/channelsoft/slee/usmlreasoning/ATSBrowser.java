package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

import com.channelsoft.reusable.serviceprovider.ReasoningProvider;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.billing.BillingData;
import com.channelsoft.slee.billing.BillingDataMgThread;
import com.channelsoft.slee.capability.CallControlCapability;
import com.channelsoft.slee.capability.SleeApp;
import com.channelsoft.slee.capability.SleeService;
import com.channelsoft.slee.capability.SleeSession;
import com.channelsoft.slee.capability.UICapability;
import com.channelsoft.slee.channelmanager.ATSChannel;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class ATSBrowser implements BaseBrowser, Serializable
{
	public class ServiceStatisData implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7534683759611336625L;

		public String firstWorkflowName = "";

		public String lastWorkflowName = "";

		public String serviceFileName = "";

		public String serviceID = "";

		public Calendar startTime;

		ServiceStatisData()
		{
			initData();
		}

		public void initData()
		{
			serviceFileName = "";
			serviceID = "";
			firstWorkflowName = "";
			lastWorkflowName = "";
		}
	}

	public class WorkflowStatisData implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 57996784047665551L;

		public String endType = "";

		public String lastNodeName = "";

		public Calendar startTime;

		public String workflowName = "";

		WorkflowStatisData()
		{
			initData();
		}

		public void initData()
		{
			workflowName = "";
			lastNodeName = "";
			endType = "";
		}
	}

	public static final int DoIOProcess = 7;

	// **SLEE_ServiceMsgType*******************
	public static final int EnterService = 1;

	public static final int EnterServiceNode = 5;

	public static final int EnterSession = 10;

	public static final int EnterWorkflow = 3;

	public static final int LeaveService = 2;

	public static final int LeaveServiceNode = 6;

	public static final int LeaveSession = 11;

	public static final int LeaveWorkflow = 4;

	public static final int OnMessage = 8;

	public static final int OnRunError = 9;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5606400991789650451L;

	static transient SleeAppImpl sleeApp = new SleeAppImpl();

	public Vector<BillingData> billingDatas = new Vector<BillingData>();

	transient ATSChannel channel;

	int currentCallStack;

	/**
	 * 当前会话中已经产生的休眠任务数量。
	 */
	public int currentTaskCounter = 0;

	String curServiceName;

	public Vector<String> filePaths = new Vector<String>();

	Vector<ServiceStatisData> serviceStatises = new Vector<ServiceStatisData>();

	long sessionId;

	transient SleeServiceImpl sleeService;

	transient SleeSessionImpl sleeSession;

	USMLReasoningEnv usmlReasoningEnv;

	Vector<WorkflowStatisData> workflowStatises = new Vector<WorkflowStatisData>();

	String assembleSysFile(String src)
	{
		String filePath;
		int language;
		language = usmlReasoningEnv.getPronouncingLanguage();
		switch (language)
		{
		case Constant.INT_MaleMandarin:
		case Constant.INT_FemaleMandarin:
			filePath = UnifiedServiceManagement.configData.getSysWavPath()
					+ Constant.Mandarin + "/" + src;
			break;
		case Constant.INT_MaleCantonese:
		case Constant.INT_FemaleCantonese:
			filePath = UnifiedServiceManagement.configData.getSysWavPath()
					+ Constant.GuangdongDialect + "/" + src;
			break;
		case Constant.INT_MaleEnglish:
		case Constant.INT_FemaleEnglish:
			filePath = UnifiedServiceManagement.configData.getSysWavPath()
					+ Constant.English + "/" + src;
			break;
		default:
			filePath = UnifiedServiceManagement.configData.getSysWavPath()
					+ Constant.Mandarin + "/" + src;
			break;
		}
		return filePath;
	}

	public void browseUSMLService(String startParam, long sessionId,
			USMLReasoningResult error)
	{
		this.sessionId = sessionId;

		if (error.value != Constant.EVENT_ResumeService)
		{
			currentCallStack = 0;
			sleeSession.reset(startParam);
			serviceStatises.removeAllElements();
			workflowStatises.removeAllElements();
			filePaths.removeAllElements();
			billingDatas.removeAllElements();
		}

		usmlReasoningEnv.browseUSMLService(sessionId, error);
	}

	public void clearCurrentTask()
	{
		usmlReasoningEnv.currentTask = null;
	}

	public void clearUserEvent(int id)
	{
		channel.clearUserEvent(id);
	}

	public void endDaemonChannel(String daemonChannelDN)
	{
		channel.endDaemonChannel(daemonChannelDN);
	}

	public String getAccountID()
	{
		return channel.curContact.accountId;
	}

	public int getAccountType()
	{
		int ret = 0;
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			ret = billingData.getAccountType();
		}

		return ret;
	}

	public String getAgentDn()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getAgentDn();
		}

		return strReturn;
	}

	public String getAgentId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getAgentId();
		}

		return strReturn;
	}

	public CallControlCapability getCallControlCapability()
	{
		return channel;
	}

	public String getContentId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getContentId();
		}

		return strReturn;
	}

	public String getCurANI()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getCallingID();
		}

		return strReturn;
	}

	public String getCurDNIS()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getCalledID();
		}

		return strReturn;
	}

	public SleepTask getCurrentTask()
	{
		return usmlReasoningEnv.currentTask;
	}

	public String getCurServiceID()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getServiceID();
		}

		return strReturn;
	}

	public void getKnowledgeVariables(Vector<String> vars)
	{
		usmlReasoningEnv.getKnowledgeVariables(vars);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#getKnowledgeVariableValue(java.lang.String)
	 */
	public String getKnowledgeVariableValue(String varName)
	{
		return usmlReasoningEnv.getKnowledgeVariableValue(varName);
	}

	public String getNetworkAccount()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getNetworkAccount();
		}

		return strReturn;
	}

	public String getNetworkId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getNetworkId();
		}

		return strReturn;
	}

	public String getNetworkPortalId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getNetworkPortalId();
		}

		return strReturn;
	}

	public String getOriginCalledId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getOriginCalledId();
		}

		return strReturn;
	}

	public String getOriginCallingId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getOriginCallingId();
		}

		return strReturn;
	}

	public String getReserve1()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getReserve1();
		}

		return strReturn;
	}

	public String getReserve2()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getReserve2();
		}

		return strReturn;
	}

	public String getReserve3()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getReserve3();
		}

		return strReturn;
	}

	public String getReserve4()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getReserve4();
		}

		return strReturn;
	}

	public String getReserve5()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getReserve5();
		}

		return strReturn;
	}

	/**
	 * @return the sessionId
	 */
	public long getSessionId()
	{
		return sessionId;
	}

	public String getSkillId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getSkillId();
		}

		return strReturn;
	}

	public SleeApp getSleeApp()
	{
		return sleeApp;
	}

	public SleeService getSleeService()
	{
		return sleeService;
	}

	public SleeSession getSleeSession()
	{
		return sleeSession;
	}

	public void getStatisData(StringWrapper serviceName,
			StringWrapper workflowName, StringWrapper nodeName)
	{
		serviceName.value = serviceStatises.get(0).serviceFileName;
		workflowName.value = workflowStatises.get(0).workflowName;
		nodeName.value = curServiceName;
	}

	public String getSubscriberId()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getSubscriberId();
		}

		return strReturn;
	}

	public String getTrueFilePath(String filePath)
	{
		if (UnifiedServiceManagement.configData.getSupportUnixPath()
				&& (filePath.indexOf('/') == 0))
		{
			// UNIX格式的绝对路径。
			return filePath.replace('\\', '/');
		}

		if ("".equals(UnifiedServiceManagement.configData.getAppMediaPath()))
		{// 使用新的目录结构
			// 如果文件名中不包括目录信息，则指定为当前的应用语音文件路径。
			if ((filePath.indexOf(":\\") == -1)
					&& (filePath.indexOf("\\\\") == -1))
			{
				if (filePath.charAt(0) == '/')
				{
					filePath = ((ISysCfgData.isNewPath() ? "D:\\ChannelSoft\\ATS4\\document"
							: "D:\\ChannelSoft\\CsCCP\\SoftSwitch\\Document") + filePath)
							.replace('/', '\\');
				}
				else
				{
					if (filePaths.size() > 0)
					{
						String curPath = filePaths.elementAt(0);
						curPath = curPath.replace(
								UnifiedServiceManagement.configData
										.getUsmlPath(),
								"D:\\ChannelSoft\\CsCCP\\SoftSwitch\\Document");
						if (filePath.startsWith("../"))
						{
							filePath = curPath.replace('/', '\\')
									+ filePath.substring(3);
						}
						else
						{
							filePath = curPath.replace('/', '\\') + filePath;
						}
					}
				}
			}
		}
		else
		{// 使用旧的ATSE的目录结构
			filePath = filePath.replace('/', '\\');
			if ((filePath.indexOf(":\\") == -1)
					&& (filePath.indexOf("\\\\") == -1))
			{
				filePath = UnifiedServiceManagement.configData
						.getAppMediaPath().replace('/', '\\')
						+ filePath;
			}

		}

		return filePath;
	}

	public UICapability getUICapability()
	{
		return channel;
	}

	public String getUserIpAddr()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getUserIpAddr();
		}

		return strReturn;
	}

	public String getUserNumber()
	{
		String strReturn = "";
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			strReturn = billingData.getUserNumber();
		}

		return strReturn;
	}

	public boolean getVarValue(String varName, StringWrapper varValue)
	{
		varValue.value = "";
		boolean returnValue = usmlReasoningEnv.getKeyValue(varName, varValue);
		return returnValue;
	}

	public int hasFiredUserEvent()
	{
		return channel.hasFiredUserEvent();
	}

	public boolean initBrowser(ATSChannel atsChannel)
	{
		channel = atsChannel;
		if (sleeSession == null)
		{
			sleeSession = new SleeSessionImpl(atsChannel);
		}
		if (sleeService == null)
		{
			sleeService = new SleeServiceImpl(atsChannel, this);
		}

		if (null == usmlReasoningEnv)
		{
			usmlReasoningEnv = USMLReasoningEnv.createAUSMLReasoningEnv(this,
					channel.channelDn);
		}

		currentTaskCounter = 0;

		return true;
	}

	public boolean isIdle()
	{
		return channel.isIdle();
	}

	public void onEnterIOProcess(String ioProcessName, USMLReasoningResult error)
	{
		error.value = Constant.EVENT_No_Error;

		for (int i = 0; i < UnifiedServiceManagement.reasoningProviders.length; i++)
		{
			try
			{
				if (!UnifiedServiceManagement.reasoningProviders[i]
						.onEnterIOProcess(channel.channelDn, ioProcessName))
				{
					Log.wWarn(LogDef.id_0x10030008, channel.channelDn,
							ioProcessName, i);

					if (error != null)
					{
						error.value = Constant.EVENT_GeneralError;
					}

					return;
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}
	}

	public void onEnterService(String srvName, String seviceID,
			String channelType, USMLReasoningResult error)
	{
		error.value = Constant.EVENT_No_Error;

		if (channel.nowInit == true)
		{
			Log.wTrace(LogDef.id_0x10030001, channel.channelDn);
			error.value = Constant.EVENT_Over_ValidDateTime;
			return;
		}

		for (int i = 0; i < UnifiedServiceManagement.reasoningProviders.length; i++)
		{
			try
			{
				if (!UnifiedServiceManagement.reasoningProviders[i]
						.onEnterService(channel.channelDn, srvName, seviceID,
								channel.curContact.accountId))
				{
					Log.wWarn(LogDef.id_0x10030002, channel.channelDn, srvName,
							i);

					error.value = Constant.EVENT_GeneralError;
					return;
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(EnterService, srvName);

		error.value = Constant.EVENT_No_Error;
		if (!channel.isIdle())
		{
			BillingData billingData = BillingDataMgThread.instance()
					.createBillingData(true);

			billingData.beginService(seviceID, "", 1, 0, 0, 0, 0,
					channel.curContact.accessTime, null);

			billingDatas.add(0, billingData);
		}
		else
		{
			BillingData billingData = BillingDataMgThread.instance()
					.createBillingData(false);
			billingDatas.add(0, billingData);

		}

		ServiceStatisData serviceStatisData = new ServiceStatisData();
		// *********************************
		serviceStatisData.initData();
		serviceStatisData.startTime = Calendar.getInstance();
		serviceStatisData.serviceFileName = srvName;
		serviceStatisData.serviceID = seviceID;
		if (serviceStatises.isEmpty())
		{
			channel.sleeResPerfData.setLastStartTime((int) (Calendar
					.getInstance().getTimeInMillis() / 1000));
			UnifiedServiceManagement.perfMonitor
					.writeChannelDetailInfo(channel.sleeResPerfData);
		}

		serviceStatises.add(0, serviceStatisData);
		channel.appName = srvName;
		channel.sleeResPerfData.setServiceName(srvName);
		String fileName = srvName;
		int nPos = -1;
		nPos = fileName.lastIndexOf("/");
		if (nPos != -1)
		{
			filePaths.add(0, fileName.substring(0, nPos + 1));
		}
	}

	public void onEnterSrvNode(String srvNodeName, USMLReasoningResult error)
	{
		error.value = Constant.EVENT_No_Error;

		for (int i = 0; i < UnifiedServiceManagement.reasoningProviders.length; i++)
		{
			try
			{
				if (!UnifiedServiceManagement.reasoningProviders[i]
						.onEnterSrvNode(channel.channelDn, srvNodeName))
				{
					Log.wWarn(LogDef.id_0x10030005, channel.channelDn,
							srvNodeName, i);

					error.value = Constant.EVENT_GeneralError;
					return;
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		// 指定时间就结束 return EVENT_Over_ValidDateTime//暂时使用
		if ((channel.timeOfEnd != null)
				&& (Calendar.getInstance().compareTo(channel.timeOfEnd) >= 0))
		{
			error.value = Constant.EVENT_Over_ValidDateTime; // 暂时使用
			Log.wWarn(LogDef.id_0x10030004, channel.channelDn, srvNodeName);
			return;
		}

		sendUSServiceMsg(EnterServiceNode, srvNodeName);
		curServiceName = srvNodeName;
		channel.sleeResPerfData.setNodeName(srvNodeName);
	}

	public void onEnterWorkflow(String workflowName, USMLReasoningResult error)
	{
		error.value = Constant.EVENT_No_Error;

		for (int i = 0; i < UnifiedServiceManagement.reasoningProviders.length; i++)
		{
			try
			{
				if (!UnifiedServiceManagement.reasoningProviders[i]
						.onEnterWorkflow(channel.channelDn, workflowName))
				{
					Log.wWarn(LogDef.id_0x10030003, channel.channelDn,
							workflowName, i);

					error.value = Constant.EVENT_GeneralError;
					return;
				}
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(EnterWorkflow, workflowName);
		currentCallStack++;
		error.value = Constant.EVENT_No_Error;
		if (currentCallStack > UnifiedServiceManagement.configData
				.getMaxCallStack())
		{
			error.value = Constant.EVENT_StackOverflow;
			Log.wError(LogDef.id_0x10130010, channel.channelDn, workflowName);
			return;
		}
		// 指定时间就结束 return EVENT_Over_ValidDateTime//暂时使用
		// *****************************************************

		if ((channel.timeOfEnd != null)
				&& (Calendar.getInstance().compareTo(channel.timeOfEnd) >= 0))
		{
			error.value = Constant.EVENT_Over_ValidDateTime; // 暂时使用
			Log.wWarn(LogDef.id_0x10030004, channel.channelDn, workflowName);
			return;
		}

		// 取当前业务队列的头，为其添加正确的firstWorkflowName 和lastWorkFlowName 2006-06-28
		ServiceStatisData serviceStatisData = serviceStatises.elementAt(0);
		WorkflowStatisData workflowStatisData = new WorkflowStatisData();

		if (serviceStatisData.firstWorkflowName.equals(""))
		{
			serviceStatisData.firstWorkflowName = workflowName;
		}
		serviceStatisData.lastWorkflowName = workflowName;

		workflowStatisData.initData();
		workflowStatisData.startTime = Calendar.getInstance();
		workflowStatisData.workflowName = workflowName;
		// ********************************************************

		workflowStatises.add(0, workflowStatisData);
		channel.sleeResPerfData.setWorkflowName(workflowName);
	}

	public void onLeaveIOProcess(String ioProcessName, String result)
	{
		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onLeaveIOProcess(channel.channelDn, ioProcessName,
						result);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		if (result != null)
		{
			sendUSServiceMsg(DoIOProcess, result);
			channel.sleeResPerfData.setIoProcess(result);
		}
	}

	public void onLeaveService()
	{
		ServiceStatisData serviceStatisData = new ServiceStatisData();
		ServiceStatisData serviceStatisDataLeft;

		if (serviceStatises.isEmpty())
		{
			serviceStatisData.serviceFileName = channel.appName;
		}
		else
		{
			serviceStatisData = serviceStatises.remove(0);
		}

		// 保留要离开的数据
		serviceStatisDataLeft = serviceStatisData;

		if (serviceStatises.isEmpty())
		{
			channel.sleeResPerfData.setLastStopTime((int) (Calendar
					.getInstance().getTimeInMillis() / 1000));
		}
		else
		{
			serviceStatisData = serviceStatises.elementAt(0);
			channel.sleeResPerfData
					.setServiceName(serviceStatisData.serviceFileName);

		}
		if (!filePaths.isEmpty())
		{
			filePaths.remove(0);
		}

		// 添加计费操作
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.remove(0);
			if (billingData.isBilling)
			{
				if (channel.curContact.isInboundCall())// 入呼叫主叫付费
				{

					billingData.endService(channel.curContact.getCallSn(),
							channel.curContact.getAni(), channel.curContact
									.getDnis(), channel.curContact.accountId,
							channel.curContact.callHangupTime);
				}
				else
				// 出呼叫被叫付费
				{// 计费系统目前只对主叫进行计费
					//出呼叫接入时间为呼叫接通时间
					billingData.setAccessTime(channel.curContact.accessTime);
					billingData.endService(channel.curContact.getCallSn(),
							channel.curContact.getAni(), channel.curContact
									.getDnis(), channel.curContact.accountId,
							channel.curContact.callHangupTime);
				}
			}// else;不做计费处理
		}

		channel.appName = serviceStatisData.serviceFileName; // 回退到上一个服务

		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onLeaveService(channel.channelDn,
						serviceStatisDataLeft.serviceFileName,
						serviceStatisDataLeft.serviceID);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(LeaveService, serviceStatisDataLeft.serviceFileName);
	}

	public void onLeaveSrvNode(String srvNodeName, int error)
	{
		if (workflowStatises.isEmpty())
		{
			return;
		}
		WorkflowStatisData workflowStatisData = workflowStatises.elementAt(0);

		// ***********************************************
		workflowStatisData.lastNodeName = srvNodeName;
		String errorMsg = "";
		errorMsg = Constant.transEventToString(error, true);
		workflowStatisData.endType = errorMsg;

		// ***********************************************

		if (error != Constant.EVENT_No_Error)
		{
			channel.sleeResPerfData.setExecFail();
			// 一个问题，流程是一层一层退出的，此值会累加......
			// 有些节点正常执行返回的不是EVENT_No_Error，
			// 如 CContinueNode::ExecuteNode 返回 EVENT_Loop_continue
			// CBreakNode::ExecuteNode 返回 EVENT_Loop_Break
			// CUnifiedServiceMgService::m_cRunLog.TraceLog(
			// "ChannelDN=%s;执行服务节点[%s]返回[%s]",m_pChannel->m_szChannelDN,
			// szSrvNodeName, TransferEvent(nError));
			// SendUSServiceMsg(::OnRunError,szSrvNodeName);
		}

		String temp = "-";
		temp += srvNodeName;
		channel.sleeResPerfData.setNodeName(temp);

		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onLeaveSrvNode(channel.channelDn, srvNodeName);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(LeaveServiceNode, srvNodeName);
		curServiceName = "";
	}

	public void onLeaveWorkflow(String workflowName)
	{
		currentCallStack--;
		// 生成Workflow统计数据
		if (workflowStatises.isEmpty())
		{
			return;
		}
		workflowStatises.remove(0);
		if (workflowStatises.isEmpty())
		{
			channel.sleeResPerfData.setWorkflowName("");
		}
		else
		{
			channel.sleeResPerfData.setWorkflowName(workflowStatises
					.elementAt(0).workflowName);
		}

		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onLeaveWorkflow(channel.channelDn, workflowName);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(LeaveWorkflow, workflowName);
	}

	public void onMessage(String msg)
	{
		String info;
		info = String.format("ChannelDN=%1$s;%2$s", channel.channelDn, msg);

		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onMessage(channel.channelDn, msg);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(OnMessage, info);
		Log.wTrace(LogDef.id_0x10030000_7, channel.channelDn, msg);
	}

	public void onRunError(String errorInfo)
	{
		String info;
		info = "ChannelDN=" + channel.channelDn + ";" + errorInfo;

		for (ReasoningProvider element : UnifiedServiceManagement.reasoningProviders)
		{
			try
			{
				element.onRunError(channel.channelDn, errorInfo);
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10130000, e);
			}
		}

		sendUSServiceMsg(OnRunError, info);
		Log.wError(LogDef.id_0x10130011, channel.channelDn, errorInfo);
	}

	public void onUSMLEvent(boolean isSysProcess, int event)
	{
		if (isSysProcess == false)
		{
			sendUSServiceMsg(OnMessage, "启动事件工作流");
			Log.wWarn(LogDef.id_0x10030006, channel.channelDn,
					transferEvent(event));
		}
		else
		{
			sendUSServiceMsg(OnMessage, "事件由ATSE系统处理");
			Log.wWarn(LogDef.id_0x10030007, channel.channelDn,
					transferEvent(event));
			processAppEvent(event);
		}
	}

	public void popUserEventSet()
	{
		channel.popUserEventSet();
	}

	void processAppEvent(int eventType)
	{
		String fileName;
		switch (eventType)
		{
		case Constant.EVENT_GeneralError:
			fileName = assembleSysFile(Constant.ATSESYSWAV_GeneralError);
			break;
		case Constant.EVENT_ScriptRunError:
			fileName = assembleSysFile(Constant.ATSESYSWAV_ScriptRunError);
			break;
		case Constant.EVENT_CantFindUSMLFile:
			fileName = assembleSysFile(Constant.ATSESYSWAV_CantFindFile);
			break;
		case Constant.EVENT_InvalidUSMLFile:
			fileName = assembleSysFile(Constant.ATSESYSWAV_InvalidUSMLFile);
			break;
		case Constant.EVENT_InvalidFile:
			fileName = assembleSysFile(Constant.ATSESYSWAV_InvalidFile);
			break;
		case Constant.EVENT_TimeOut:
			fileName = assembleSysFile(Constant.ATSESYSWAV_Timeout);
			break;
		case Constant.EVENT_DiskFull:
			fileName = assembleSysFile(Constant.ATSESYSWAV_DiskFull);
			break;
		case Constant.EVENT_Script_End:
			return;
		case Constant.EVENT_No_Error:
			return;
		case Constant.EVENT_DestBusy:
			return;
		case Constant.EVENT_NoFaxDevice:
			return;
		case Constant.EVENT_NoCall:
			return;
		case Constant.EVENT_NoAnswer:
			return;
		case Constant.EVENT_InvalidTelNum:
			return;
		case Constant.EVENT_CustomHangup:
			return;
		case Constant.EVENT_ToAgent:
			return;
		case Constant.EVENT_UnsupportedMedia:
			return;
		case Constant.EVENT_MediaUnmatch:
			return;
		default:
			fileName = assembleSysFile(Constant.ATSESYSWAV_GeneralError);
			break;
		}
		channel.playVoice(fileName, true, "", 6, 1, 0, null, null);
	}

	public void pushUserEventSet(Vector<Integer> userEventSet)
	{
		channel.pushUserEventSet(userEventSet);
	}

	public void restoreServiceSession(ATSChannel atsChannel)
	{
		initBrowser(atsChannel);

		usmlReasoningEnv.restoreReasoningEnv(this, channel.channelDn);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#restoreTask(long)
	 */
	public SleepTask restoreTask(long sessionId)
	{
		return channel.restoreTask(sessionId);
	}

	void sendUSServiceMsg(int msgType, String data)
	{
		switch (msgType)
		{
		case EnterService:
			if (UnifiedServiceManagement.configData.getServiceStatis() == true)
			{
				Log.wTrace(LogDef.id_0x10030000, channel.channelDn, data);
			}
			// 跟踪信息入监控队列操作 2006-06-28
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##EnterService##%s",
								channel.channelDn, data));
			}
			break;
		case LeaveService:
			if (UnifiedServiceManagement.configData.getServiceStatis() == true)
			{
				Log.wTrace(LogDef.id_0x10030000_1, channel.channelDn, data);
			}
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##LeaveService##%s",
								channel.channelDn, data));
			}
			break;
		case EnterWorkflow:
			if (UnifiedServiceManagement.configData.getWorkFlowStatis() == true)
			{
				Log.wTrace(LogDef.id_0x10030000_2, channel.channelDn, data);
			}
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##EnterWorkflow##%s",
								channel.channelDn, data));
			}
			break;
		case LeaveWorkflow:
			if (UnifiedServiceManagement.configData.getWorkFlowStatis() == true)
			{

				Log.wTrace(LogDef.id_0x10030000_3, channel.channelDn, data);
			}
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##LeaveWorkflow##%s",
								channel.channelDn, data));
			}
			break;
		case EnterServiceNode:
			if (UnifiedServiceManagement.configData.getNodeStatis() == true)
			{
				Log.wTrace(LogDef.id_0x10030000_4, channel.channelDn, data);
			}
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##EnterServiceNode##%s",
								channel.channelDn, data));
			}
			break;
		case LeaveServiceNode:
			if (UnifiedServiceManagement.configData.getNodeStatis() == true)
			{
				Log.wTrace(LogDef.id_0x10030000_5, channel.channelDn, data);
			}
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##LeaveServiceNode##%s",
								channel.channelDn, data));
			}
			break;
		case DoIOProcess:
			if (UnifiedServiceManagement.configData.getNodeStatis() == true)
			{
				Log.wTrace(LogDef.id_0x10030000_6, channel.channelDn, data);
			}
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, String.format(
								"ChannelDN=%s;##IOProcess##%s",
								channel.channelDn, data));
			}
			break;
		case OnMessage:
			if (channel.isDebugging())
			{
				UnifiedServiceManagement.perfMonitor.writeChannelDebugInfo(
						channel.channelDn, data);
			}
			break;
		// case ::OnRunError:
		default:
			break;
		}
	}

	public void setAccountID(String value)
	{
		String temp = channel.curContact.accountId;
		channel.curContact.accountId = String.format("%s", value);

		Log.wTrace(LogDef.id_0x10030000_11, channel.channelDn, temp, value);
	}

	public void setAccountType(int newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setAccountType(newValue);
		}
	}

	public void setAgentDn(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setAgentDn(newValue);
		}
	}

	public void setAgentId(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setAgentId(newValue);
		}
	}

	public void setContentId(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setContentId(newValue);
		}
	}

	public void setCurANI(String newValue)
	{
		// m_pChannel->m_cCurContact.SetOriANI(m_pChannel->m_cCurContact.GetANI()
		// );
		channel.curContact.setAni(newValue);

		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.remove(0); // 重点
			String temp = billingData.getCallingID();
			billingData.setCallingID(newValue);
			billingDatas.add(0, billingData); // 重点中的重点
			Log.wTrace(LogDef.id_0x10030000_8, channel.channelDn, temp,
					newValue);
		}
	}

	public void setCurDNIS(String newValue)
	{
		// m_pChannel->m_cCurContact.SetOriDNIS(m_pChannel->m_cCurContact.GetDNIS
		// ());
		channel.curContact.setDnis(newValue);

		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.remove(0); // 重点
			String temp = billingData.getCalledID();
			billingData.setCalledID(newValue);
			billingDatas.add(0, billingData); // 重点中的重点
			Log.wTrace(LogDef.id_0x10030000_9, channel.channelDn, temp,
					newValue);
		}
	}

	public void setCurServiceID(String value)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.remove(0); // 重点
			String temp = billingData.getServiceID();
			billingData.setServiceID(value);
			billingDatas.add(0, billingData); // 重点中的重点
			Log.wTrace(LogDef.id_0x10030000_10, channel.channelDn, temp, value);
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.BaseBrowser#setKnowledgeVariableValue(java.lang.String, java.lang.String)
	 */
	public void setKnowledgeVariableValue(String varName, String value)
	{
		usmlReasoningEnv.setKnowledgeVariableValue(varName, value);
	}

	public void setNetworkAccount(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setNetworkAccount(newValue);
		}
	}

	public void setNetworkId(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setNetworkId(newValue);
		}
	}

	public void setNetworkPortalId(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setNetworkPortalId(newValue);
		}
	}

	public void setOriginCalledId(String newValue)
	{
		channel.curContact.setOriDnis(newValue);

		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setOriginCalledId(newValue);
		}
	}

	public void setOriginCallingId(String newValue)
	{
		channel.curContact.setOriAni(newValue);

		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setOriginCallingId(newValue);
		}
	}

	public void setReserve1(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setReserve1(newValue);
		}
	}

	public void setReserve2(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setReserve2(newValue);
		}
	}

	public void setReserve3(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setReserve3(newValue);
		}
	}

	public void setReserve4(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setReserve4(newValue);
		}
	}

	public void setReserve5(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setReserve5(newValue);
		}
	}

	public void setSkillId(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setSkillId(newValue);
		}
	}

	public void setSubscriberId(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setSubscriberId(newValue);
		}
	}

	public void setUserIpAddr(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setUserIpAddr(newValue);
		}
	}

	public void setUserNumber(String newValue)
	{
		if (!billingDatas.isEmpty())
		{
			BillingData billingData = billingDatas.elementAt(0); // 重点
			billingData.setUserNumber(newValue);
		}
	}

	public boolean shouldExit()
	{
		return channel.shouldExit();
	}

	public String startDaemonService(String usmlDoc, String startParam)
	{
		String newChannelDN = UnifiedServiceManagement.channelManager
				.beginService(usmlDoc, startParam);
		if (!"".equals(newChannelDN))
		{
			channel.attachDaemonChannel(newChannelDN);
			ATSChannel tempChannel = UnifiedServiceManagement.channelManager
					.getChannel(newChannelDN);
			if (null != tempChannel)
			{
				tempChannel.attachParentChannel(channel.channelDn);
			}
		}

		return newChannelDN;
	}

	String transferEvent(int event)
	{
		if ((event & 0xffff0000) == Constant.EVENT_UserEvent)
		{
			return "UserEvent";
		}

		switch (event)
		{
		case Constant.EVENT_ForceExit:
			return "ForceExit";
		case Constant.EVENT_DestBusy:
			return Constant.DestBusy;
		case Constant.EVENT_NoFaxDevice:
			return Constant.NoFaxDevice;
		case Constant.EVENT_NoCall:
			return Constant.NoCall;
		case Constant.EVENT_InvalidFile:
			return Constant.InvalidFile;
		case Constant.EVENT_DiskFull:
			return Constant.DiskFull;

		case Constant.EVENT_NoAnswer:
			return Constant.NoAnswer;
		case Constant.EVENT_TimeOut:
			return Constant.TimeOut;
		case Constant.EVENT_InvalidTelNum:
			return Constant.InvalidTelNum;
		case Constant.EVENT_CustomHangup:
			return Constant.CustomHangup;
		case Constant.EVENT_ToAgent:
			return Constant.ToAgent;

		case Constant.EVENT_GeneralError:
			return Constant.GeneralError;
		case Constant.EVENT_No_Error:
			return Constant.No_Error;
		case Constant.EVENT_Script_End:
			return "EVENT_Script_End";
		case Constant.EVENT_ScriptRunError:
			return Constant.ScriptRunError;
		case Constant.EVENT_CantFindUSMLFile:
			return Constant.CantFindUSMLFile;

		case Constant.EVENT_InvalidUSMLFile:
			return Constant.InvalidUSMLFile;
		case Constant.EVENT_UnsupportProc:
			return Constant.UnsupportProc;
		case Constant.EVENT_IVALID_PARAM:
			return Constant.InvalidParam;
		case Constant.EVENT_IVALID_Teminal:
			return Constant.InvalidTerminal;
		case Constant.EVENT_No_Authorization:
			return Constant.No_Authorization;

		case Constant.EVENT_InvalidAuthorization:
			return Constant.InvalidAuthorization;
		case Constant.EVENT_Over_ValidDateTime:
			return Constant.Over_ValidDateTime;
		case Constant.EVENT_InvalidNodeCode:
			return Constant.InvalidNodeCode;
		case Constant.EVENT_UnsupportedMedia:
			return Constant.UnsupportedMedia;
		case Constant.EVENT_MediaUnmatch:
			return Constant.MediaUnmatch;
		case Constant.EVENT_StackOverflow:
			return Constant.StackOverflow;

		case Constant.EVENT_ASRError:
			return Constant.ASRError;
		case Constant.EVENT_SmsError:
			return Constant.SmsError;
		case Constant.EVENT_TelDialOut:
			return Constant.TelDialOut;
		default:
			String temp;
			temp = String.format("default=%1$X", event);
			return temp;
		}
	}
}
