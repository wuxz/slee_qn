package com.channelsoft.slee.unifiedservicemanagement;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.channelsoft.reusable.log.Logger.LogLevel;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.config.ATSE;

public class SysCfgData extends ISysCfgData
{
	public SysCfgData()
	{
	}

	@Override
	public boolean load(boolean needLoadXmlCfg)
	{
		String appPath = queryRegData();
		usmlPath = appPath
				+ (ISysCfgData.isNewPath() ? "/document"
						: "/SoftSwitch/Document");
		statisticDir = appPath + "/Statistics";
		cfgFileName = appPath
				+ (ISysCfgData.isNewPath() ? "/config/ATSEConfigData.cfg"
						: "/SoftSwitch/Config/ATSEConfigData.cfg");
		generatedPath = appPath
				+ (ISysCfgData.isNewPath() ? "/generated"
						: "/SoftSwitch/generated");
		sysWavPath = ISysCfgData.isNewPath() ? "D:\\ChannelSoft\\CsCCP\\SoftSwitch\\syswav\\"
				: "D:\\ChannelSoft\\CsCCP\\SoftSwitch\\SysWav\\";
		appMediaPath = usmlPath + "/AppMedia";

		if (!new File(appPath
				+ (ISysCfgData.isNewPath() ? "/bin" : "/SoftSwitch/Service"))
				.exists())
		{
			Log.wFatal(LogDef.id_0x10210001);
			return false;
		}

		if (!new File(cfgFileName).exists())
		{
			Log.wFatal(LogDef.id_0x10210002);
			return false;
		}

		if (new File(appMediaPath).exists())
		{
			appMediaPath = appMediaPath + "/";
		}
		else
		{
			appMediaPath = "";
		}
		if (needLoadXmlCfg)
		{
			return loadCfgFromXML();
		}
		return true;
	}

	private boolean loadCfgFromXML()
	{
		try
		{
			JAXBContext jc = JAXBContext
					.newInstance("com.channelsoft.slee.unifiedservicemanagement.config");
			Unmarshaller u = jc.createUnmarshaller();
			cfg = (ATSE) u.unmarshal(new FileInputStream(cfgFileName));

			atsMediaServer = cfg.getAttributeSet().getMediaServerIP();
			if ((atsMediaServer == null) || "".equals(atsMediaServer))
			{
				atsMediaServer = "127.0.0.1";
			}

			smsServerIP = cfg.getAttributeSet().getSMSMediaServerIP();

			isAutoAnswer = cfg.getAttributeSet().isAutoAnswer();
			isWriteDTMFLog = cfg.getAttributeSet().isWriteDTMFLog();
			linkToUse = cfg.getAttributeSet().isIsLinkToICCP();
			writeUserDefinedCallID = cfg.getAttributeSet()
					.isWriteUserDefinedCallID();
			listenPort = cfg.getAttributeSet().getListenPort();
			manageServerPort = cfg.getAttributeSet().getManageServerPort();
			useServerIP = cfg.getAttributeSet().getUSEServerIP();
			if (cfg.getAttributeSet().getSysWavePath() != null)
			{
				sysWavPath = cfg.getAttributeSet().getSysWavePath() + "\\";
			}
			szTTSWavPathChinese = sysWavPath + "Mandarin\\TTSVoice\\";
			szTTSWavPathEnglish = sysWavPath + "English\\TTSVoice\\";
			szTTSWavPathHongkong = sysWavPath + "GuangdongDialect\\TTSVoice\\";
			maxUserIdleSeconds = cfg.getAttributeSet().getMaxUserIdleSeconds();
			if (maxUserIdleSeconds == 0)
			{
				maxUserIdleSeconds = 24 * 60 * 60;
			}

			supportUnixPath = cfg.getAttributeSet().isSupportFileOnUnix();

			int nStatisLevel = cfg.getAttributeSet().getStatisticsLevel();
			if (nStatisLevel == 1)
			{
				serviceStatis = true;
			}
			else if (nStatisLevel == 2)
			{
				serviceStatis = true;
				workFlowStatis = true;
			}
			else if (nStatisLevel == 3)
			{
				serviceStatis = true;
				workFlowStatis = true;
				nodeStatis = true;
			}

			if (!cfg.getAttributeSet().isRunLog())
			{
				Log.wTrace(LogDef.id_0x10010000);
				Log.setLogLevel(LogLevel.FR);
			}

			autoClear = cfg.getAttributeSet().getAutoClear();

			if (cfg.getAttributeSet().getLogLevel() != null)
			{
				Log.setLogLevel(cfg.getAttributeSet().getLogLevel());
			}

			maxCallStack = cfg.getAttributeSet().getMaxCallStack();

			traceStatistic = ((cfg.getAttributeSet().getStatistics() != null) && cfg
					.getAttributeSet().getStatistics().isTrace());
			if (traceStatistic)
			{
				String szDir = cfg.getAttributeSet().getStatistics()
						.getTracePath();
				if (szDir.length() != 0)
				{
					statisticDir = szDir;
				}
			}
			if (cfg.getAttributeSet().getConference() != null)
			{
				confGWServer = cfg.getAttributeSet().getConference()
						.getConfGWServer();

			}

			if (cfg.getAttributeSet().getUSMLScriptHost() != null)
			{
				scriptEngineWorkTime = cfg.getAttributeSet()
						.getUSMLScriptHost().getWorkTime();
			}

			if (cfg.getAttributeSet().getUVMGServer() != null)
			{
				uvmgServerIp = cfg.getAttributeSet().getUVMGServer().getIP();
				uvmgServerPort = cfg.getAttributeSet().getUVMGServer()
						.getPort();
				useVGProxy = cfg.getAttributeSet().getUVMGServer()
						.isUseVGProxy();
			}

			if (cfg.getAttributeSet().getSSR2() != null)
			{
				useSSR2 = true;
				uvmgServerIp = cfg.getAttributeSet().getSSR2().getIP();
				uvmgServerPort = cfg.getAttributeSet().getSSR2().getPort();
				ssr2SleeName = cfg.getAttributeSet().getSSR2().getSLEEName();
				ssr2SleePassword = cfg.getAttributeSet().getSSR2()
						.getSLEEPassword();
			}

			if (cfg.getAttributeSet().getUCCE2() != null)
			{
				useUCCE2 = true;
				uvmgServerIp = cfg.getAttributeSet().getUCCE2().getIP();
				uvmgServerPort = cfg.getAttributeSet().getUCCE2().getPort();
				ssr2SleeName = cfg.getAttributeSet().getUCCE2().getSLEEName();
				ssr2SleePassword = cfg.getAttributeSet().getUCCE2()
						.getSLEEPassword();
			}

			glsConnection = (cfg.getAttributeSet().getCCOD() == null ? null
					: cfg.getAttributeSet().getCCOD().getGLSConnection());
			if ((glsConnection != null) && !"".equals(glsConnection))
			{
				useCMS = true;
				glsSleeServiceName = cfg.getAttributeSet().getCCOD()
						.getSleeServiceName();
				if ((glsSleeServiceName == null)
						|| glsSleeServiceName.equals(""))
				{
					Log.wFatal(LogDef.id_0x10210003);
					return false;
				}

				ccodServiceId = cfg.getAttributeSet().getCCOD().getServiceID();
				ccodAgentServiceId = cfg.getAttributeSet().getCCOD()
						.getAgentServiceID();
				if (ccodAgentServiceId.equals(""))
				{
					ccodAgentServiceId = ccodServiceId;
				}

				ccodStatFileDir = cfg.getAttributeSet().getCCOD()
						.getStatFileDir();
				if ((ccodStatFileDir == null) || "".equals(ccodStatFileDir))
				{
					ccodStatFileDir = queryRegData() + "/CCODLog";
				}

				cmsServerPort = cfg.getAttributeSet().getCCOD()
						.getCMSServerPort();
				if (cmsServerPort == 0)
				{
					cmsServerPort = 11112;
				}

				ccodUseSSL = cfg.getAttributeSet().getCCOD().isUseSSL();
				ccodSupportVideo = cfg.getAttributeSet().getCCOD().isSupportVideo();
				ccodOutboundCms = cfg.getAttributeSet().getCCOD().getOutboundCMS();
			}

			if (uvmgServerPort == 0)
			{
				uvmgServerPort = (useSSR2 ? 12000 : useVGProxy ? 10000 : 1220);
			}

			deviceCode = (cfg.getAttributeSet().getSDR() == null ? null : cfg
					.getAttributeSet().getSDR().getDeviceCode());
			nodeCode = (cfg.getAttributeSet().getSDR() == null ? null : cfg
					.getAttributeSet().getSDR().getNodeCode());

			defaultMessageTTL = cfg.getAttributeSet().getMessagingService() == null ? -1
					: cfg.getAttributeSet().getMessagingService()
							.getDefaultMessageTTL();
			internalQueueName = cfg.getAttributeSet().getMessagingService() == null ? "SLEE_Internal_Queue"
					: cfg.getAttributeSet().getMessagingService()
							.getInternalQueueName();
			sessionTTL = cfg.getAttributeSet().getMessagingService() == null ? 172800
					: cfg.getAttributeSet().getMessagingService()
							.getSessionTTL();

			shouldWriteSDR = false;
			if (((nodeCode != null) && !nodeCode.equals(""))
					|| ((smsServerIP != null) && !smsServerIP.equals("")))
			{
				shouldWriteSDR = true;
			}

			if ((uvmgServerIp == null) || "".equals(uvmgServerIp))
			{
				uvmgServerIp = atsMediaServer;
			}

			if (cfg.getAttributeSet().getSNMPAgent() != null)
			{
				SnmpAgentHandler.mibTargetIP = cfg.getAttributeSet()
						.getSNMPAgent().getTargetAddress();
				SnmpAgentHandler.mibTargetPort = ""
						+ cfg.getAttributeSet().getSNMPAgent().getTargetPort();
				if ((SnmpAgentHandler.mibTargetIP != null)
						&& !"".equals(SnmpAgentHandler.mibTargetIP))
				{
					SnmpAgentHandler.newTargetAddress = true;
				}

				snmpListenPort = cfg.getAttributeSet().getSNMPAgent()
						.getListenPort();

				snmpNodeId = cfg.getAttributeSet().getSNMPAgent().getNodeId();
				snmpNodeLocation = cfg.getAttributeSet().getSNMPAgent()
						.getNodeLocation();
				snmpNodeName = cfg.getAttributeSet().getSNMPAgent()
						.getNodeName();
			}

			combineVoiceEdit = false;
			if (cfg.getAttributeSet().getVoiceEdit() != null)
			{
				combineVoiceEdit = cfg.getAttributeSet().getVoiceEdit()
						.isCombineVoiceEdit();
			}

			// 初始化用户自定义键值表
			for (int i = 0; (cfg.getUserDefined() != null)
					&& (i < cfg.getUserDefined().getEntry().size()); i++)
			{
				userDefined.put(
						cfg.getUserDefined().getEntry().get(i).getKey(), cfg
								.getUserDefined().getEntry().get(i).getValue());
			}

		}
		catch (Exception e)
		{
			Log.wError(LogDef.id_0x10110010);
			Log.wException(LogDef.id_0x10110000, e);

			return false;
		}

		return true;
	}

	@Override
	boolean reload()
	{
		try
		{
			JAXBContext jc = JAXBContext
					.newInstance("com.channelsoft.slee.unifiedservicemanagement.config");
			Unmarshaller u = jc.createUnmarshaller();
			cfg = (ATSE) u.unmarshal(new FileInputStream(cfgFileName));
		}
		catch (Exception e)
		{
			Log.wError(LogDef.id_0x10110010);
			Log.wException(LogDef.id_0x10110000, e);
			return false;
		}

		int nStatisLevel = cfg.getAttributeSet().getStatisticsLevel();
		nodeStatis = false;
		workFlowStatis = false;
		serviceStatis = false;
		if (nStatisLevel == 1)
		{
			serviceStatis = true;
		}
		else if (nStatisLevel == 2)
		{
			serviceStatis = true;
			workFlowStatis = true;
		}
		else if (nStatisLevel == 3)
		{
			serviceStatis = true;
			workFlowStatis = true;
			nodeStatis = true;
		}

		if (cfg.getAttributeSet().getLogLevel() != null)
		{
			Log.setLogLevel(cfg.getAttributeSet().getLogLevel());
		}

		autoClear = cfg.getAttributeSet().getAutoClear();

		defaultMessageTTL = cfg.getAttributeSet().getMessagingService() == null ? -1
				: cfg.getAttributeSet().getMessagingService()
						.getDefaultMessageTTL();
		sessionTTL = cfg.getAttributeSet().getMessagingService() == null ? 172800
				: cfg.getAttributeSet().getMessagingService().getSessionTTL();

		return true;
	}

}
