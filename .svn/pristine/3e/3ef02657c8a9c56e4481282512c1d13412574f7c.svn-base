package com.channelsoft.slee.unifiedservicemanagement;

import java.util.HashMap;

import com.channelsoft.slee.unifiedservicemanagement.config.ATSE;
import com.channelsoft.slee.unifiedservicemanagement.config.DBPools;
import com.channelsoft.slee.unifiedservicemanagement.config.DNGroups;
import com.channelsoft.slee.unifiedservicemanagement.config.JMSPools;
import com.channelsoft.slee.unifiedservicemanagement.config.ServiceProviders;
import com.channelsoft.slee.unifiedservicemanagement.config.ATSE.Components;
import com.channelsoft.slee.util.Constant;

public abstract class ISysCfgData
{
	protected static boolean isNewPath = true;

	public static String getLogPath()
	{
		String logPath = System.getenv("SLEE_LOG_PATH");
		if (logPath == null)
		{
			logPath = ISysCfgData.queryRegData()
					+ (ISysCfgData.isNewPath() ? "/runlog" : "/RunLog");
		}

		return logPath.replace('\\', '/');
	}

	public static boolean isNewPath()
	{
		return isNewPath;
	}

	public static String queryRegData()
	{
		return System.getenv("SLEE_HOME").replace('\\', '/');
	}

	protected String appMediaPath;

	protected String atsMediaServer;

	protected int autoClear;

	protected String ccodAgentServiceId;

	protected String ccodServiceId;

	protected String ccodStatFileDir;

	protected boolean ccodUseSSL;

	protected boolean ccodSupportVideo;
	
	protected String ccodOutboundCms;
	
	protected ATSE cfg;

	/**
	 * slee配置文件路径
	 */
	protected String cfgFileName;

	protected int cmsServerPort;

	protected boolean combineVoiceEdit;

	protected String confGWServer;

	/**
	 * 发送消息的默认有效期，单位为秒。
	 */
	protected int defaultMessageTTL;

	protected String deviceCode;

	/**
	 * 业务流程编译生成中间文件和执行文件
	 */
	protected String generatedPath;

	protected String glsConnection;

	protected String glsSleeServiceName;

	/**
	 * 内部消息队列的名称。
	 */
	protected String internalQueueName;

	protected boolean isAutoAnswer;

	protected boolean isWriteDTMFLog;

	protected boolean linkToUse;

	/**
	 * 性能监控命令的监听端口。
	 */
	protected int listenPort;

	/**
	 * 上传服务的监听端口。
	 */
	protected int manageServerPort;

	protected int maxCallStack;

	protected int maxUserIdleSeconds;

	protected String nodeCode;

	/**
	 * 在进入和退出节点时是否记日志。
	 */
	protected boolean nodeStatis;

	protected int scriptEngineWorkTime;

	/**
	 * 在进入和退出服务时是否记日志。
	 */
	protected boolean serviceStatis;

	/**
	 * 会话的有效期。单位为秒。过期则不再保存。
	 */
	protected int sessionTTL;

	protected boolean shouldWriteSDR;

	protected String smsServerIP;

	protected int snmpListenPort;

	protected int snmpNodeId;

	protected String snmpNodeLocation;

	protected String snmpNodeName;

	protected String ssr2SleeName;

	protected String ssr2SleePassword;

	protected String statisticDir;

	protected boolean supportUnixPath;

	protected String sysWavPath;

	/**
	 * TTS语音文件目录
	 */
	protected String szTTSWavPathChinese;

	/**
	 * 英语TTS语音文件目录
	 */
	protected String szTTSWavPathEnglish;

	/**
	 * 粤语TTS语音文件目录
	 */
	protected String szTTSWavPathHongkong;

	protected boolean traceStatistic;

	protected String ucce2SleeName;

	protected String ucce2SleePassword;

	/**
	 * 是否连接CMS
	 */
	protected boolean useCMS;

	protected HashMap<String, String> userDefined = new HashMap<String, String>();

	protected String useServerIP;

	protected boolean useSSR2 = false;

	protected boolean useUCCE2 = false;

	protected boolean useVGProxy;

	/**
	 * usml文档路径:'.../SoftSwtich/Document'
	 */
	protected String usmlPath;

	protected String uvmgServerIp;

	protected int uvmgServerPort;

	/**
	 * 在进入和退出工作流时是否记日志。
	 */
	protected boolean workFlowStatis;

	protected boolean writeUserDefinedCallID;

	public String assembleTTSFileName(int pronLanguage, String szFileName)
	{
		String szNewFileName = szFileName;
		if (szFileName.indexOf('\\') == -1)
		{// 包括文件路径的Segment不需要重新确定文件路径
			switch (pronLanguage)
			{
			case Constant.INT_MaleMandarin:
			case Constant.INT_FemaleMandarin:
				szNewFileName = szTTSWavPathChinese + szFileName;
				break;
			case Constant.INT_MaleCantonese:
			case Constant.INT_FemaleCantonese:
				szNewFileName = szTTSWavPathHongkong + szFileName;
				break;
			case Constant.INT_MaleEnglish:
			case Constant.INT_FemaleEnglish:
				szNewFileName = szTTSWavPathEnglish + szFileName;
				break;
			default:
				szNewFileName = szTTSWavPathChinese + szFileName;
				break;
			}
		}
		return szNewFileName;
	}

	public String getAppMediaPath()
	{
		return appMediaPath;
	}

	public String getAtsMediaServer()
	{
		return atsMediaServer;
	}

	public int getAutoClear()
	{
		return autoClear;
	}

	public String getCcodAgentServiceId()
	{
		return ccodAgentServiceId;
	}

	public String getCcodServiceId()
	{
		return ccodServiceId;
	}

	public String getCcodStatFileDir()
	{
		return ccodStatFileDir;
	}

	public String getCcodOutboundCms()
	{
		return ccodOutboundCms;
	}
	
	public String getCfgFileName()
	{
		return cfgFileName;
	}

	public int getCmsServerPort()
	{
		return cmsServerPort;
	}

	public Components getComponents()
	{
		return (cfg == null) ? null : cfg.getComponents();
	}

	public String getConfGWServer()
	{
		return confGWServer;
	}

	public DBPools getDBPools()
	{
		return (cfg == null) ? null : cfg.getDBPools();
	}

	/**
	 * @return the defaultMessageTTL
	 */
	public int getDefaultMessageTTL()
	{
		return defaultMessageTTL;
	}

	public String getDeviceCode()
	{
		return deviceCode;
	}

	public DNGroups getDNGroups()
	{
		return (cfg == null) ? null : cfg.getDNGroups();
	}

	public String getGeneratedPath()
	{
		return (generatedPath == null) ? (queryRegData() + (ISysCfgData
				.isNewPath() ? "/generated" : "/SoftSwitch/generated"))
				: generatedPath;
	}

	public String getGlsConnection()
	{
		return glsConnection;
	}

	public String getGlsSleeServiceName()
	{
		return glsSleeServiceName;
	}

	/**
	 * @return the internalQueueName
	 */
	public String getInternalQueueName()
	{
		return internalQueueName;
	}

	public boolean getIsAutoAnswer()
	{
		return isAutoAnswer;
	}

	public boolean getIsWriteDTMFLog()
	{
		return isWriteDTMFLog;
	}

	public JMSPools getJMSPools()
	{
		return (cfg == null) ? null : cfg.getJMSPools();
	}

	public boolean getLinkToUse()
	{
		return linkToUse;
	}

	public int getListenPort()
	{
		return listenPort;
	}

	/**
	 * @return the manageServerPort
	 */
	public int getManageServerPort()
	{
		return manageServerPort;
	}

	public int getMaxCallStack()
	{
		return maxCallStack;
	}

	public int getMaxUserIdleSeconds()
	{
		return maxUserIdleSeconds;
	}

	public String getNodeCode()
	{
		return nodeCode;
	}

	public boolean getNodeStatis()
	{
		return nodeStatis;
	}

	public int getScriptEngineWorkTime()
	{
		return scriptEngineWorkTime;
	}

	public ServiceProviders getServiceProviders()
	{
		return (cfg == null) ? null : cfg.getServiceProviders();
	}

	public boolean getServiceStatis()
	{
		return serviceStatis;
	}

	/**
	 * @return the sessionTTL
	 */
	public int getSessionTTL()
	{
		return sessionTTL;
	}

	public boolean getShouldWriteSDR()
	{
		return shouldWriteSDR;
	}

	public String getSmsServerIP()
	{
		return smsServerIP;
	}

	public int getSnmpListenPort()
	{
		return snmpListenPort;
	}

	public int getSnmpNodeId()
	{
		return snmpNodeId;
	}

	public String getSnmpNodeLocation()
	{
		return snmpNodeLocation;
	}

	public String getSnmpNodeName()
	{
		return snmpNodeName;
	}

	/**
	 * @return the sSR2SleeName
	 */
	public String getSSR2SleeName()
	{
		return ssr2SleeName;
	}

	/**
	 * @return the sSR2SleePassword
	 */
	public String getSSR2SleePassword()
	{
		return ssr2SleePassword;
	}

	public String getStatisticDir()
	{
		return statisticDir;
	}

	public boolean getSupportUnixPath()
	{
		return supportUnixPath;
	}

	public String getSysWavPath()
	{
		return sysWavPath;
	}

	public boolean getTraceStatistic()
	{
		return traceStatistic;
	}

	/**
	 * TTS语音文件目录
	 */
	public String getTTSWavPathChinese()
	{
		return szTTSWavPathChinese;
	}

	/**
	 * 英语TTS语音文件目录
	 */
	public String getTTSWavPathEnglish()
	{
		return szTTSWavPathEnglish;
	}

	/**
	 * 粤语TTS语音文件目录
	 */
	public String getTTSWavPathHongkong()
	{
		return szTTSWavPathHongkong;
	}

	/**
	 * @return the ucce2SleeName
	 */
	public String getUcce2SleeName()
	{
		return ucce2SleeName;
	}

	/**
	 * @return the ucce2SleePassword
	 */
	public String getUcce2SleePassword()
	{
		return ucce2SleePassword;
	}

	/**
	 * 是否连接CMS
	 */
	public boolean getUseCMS()
	{
		return useCMS;
	}

	public String getUserDefinedValue(String key)
	{
		return userDefined.get(key);
	}

	public String getUseServerIP()
	{
		return useServerIP;
	}

	public boolean getUseVGProxy()
	{
		return useVGProxy;
	}

	public String getUsmlPath()
	{
		return (usmlPath == null) ? (queryRegData() + (ISysCfgData.isNewPath() ? "/document"
				: "/SoftSwitch/Document"))
				: usmlPath;
	}

	public String getUvmgServerIp()
	{
		return uvmgServerIp;
	}

	public int getUvmgServerPort()
	{
		return uvmgServerPort;
	}

	public boolean getWorkFlowStatis()
	{
		return workFlowStatis;
	}

	public boolean getWriteUserDefinedCallID()
	{
		return writeUserDefinedCallID;
	}

	/**
	 * @return the ccodUseSSL
	 */
	public boolean isCcodUseSSL()
	{
		return ccodUseSSL;
	}
	
	public boolean isCcodSupportVideo()
	{
		return ccodSupportVideo;
	}

	public boolean isCombineVoiceEdit()
	{
		return combineVoiceEdit;
	}

	/**
	 * @return the useSSR2
	 */
	public boolean isUseSSR2()
	{
		return useSSR2;
	}

	/**
	 * @return the useUCCE2
	 */
	public boolean isUseUCCE2()
	{
		return useUCCE2;
	}

	/**
	 * @param needLoadXmlCfg
	 *            是否加载ATSEConfigData.cfg。true为加载。
	 * @return
	 */
	public abstract boolean load(boolean needLoadXmlCfg);

	abstract boolean reload();

	public void setShouldWriteSDR(boolean shouldWriteSDR)
	{
		this.shouldWriteSDR = shouldWriteSDR;
	}
}
