package com.channelsoft.slee.callagent.ccod;

import Ice.IntHolder;

import com.channelsoft.CCODServices.ADIsWriteSDR;
import com.channelsoft.CCODServices.AccessTime;
import com.channelsoft.CCODServices.AccountID;
import com.channelsoft.CCODServices.CCODResultT;
import com.channelsoft.CCODServices.CMSInterfacePrx;
import com.channelsoft.CCODServices.ContentTypeT;
import com.channelsoft.CCODServices.ElemT;
import com.channelsoft.CCODServices.EncodingT;
import com.channelsoft.CCODServices.FinishedSDR;
import com.channelsoft.CCODServices.MediaDirectionT;
import com.channelsoft.CCODServices.MediaTypeT;
import com.channelsoft.CCODServices.OperateTypeT;
import com.channelsoft.CCODServices.PlayListElemT;
import com.channelsoft.CCODServices.ServiceDataTHolder;
import com.channelsoft.CCODServices.ServiceInfoT;
import com.channelsoft.CCODServices.ServiceInfoTHolder;
import com.channelsoft.CCODServices.ServiceTypeT;
import com.channelsoft.CCODServices.StartTime;
import com.channelsoft.CCODServices.TTSVoiceLibT;
import com.channelsoft.CCODServices.VideoContentParamT;
import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_CallSession;
import com.channelsoft.slee.callagent.V2_VGProxy;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

/**
 * CMS接口的服务器线程。
 */
class SleeServer extends Thread
{

	Ice.Communicator server = null;

	public SleeServer(Ice.Communicator server)
	{
		this.server = server;
	}

	@Override
	public void run()
	{
		setName("CallAgent4CCOD SleeServer");

		server.waitForShutdown();
	}
}

public class V2_VGProxyImp implements V2_VGProxy
{
	static int callSnSeed = 0;

	// static CMSInterfacePrx ccodProxy;

	// static String cmsName;

	/**
	 * 最大的callId的值。
	 */
	static final int MAX_CALLSN = 800000;

	/**
	 * 最大允许的未完成呼叫数量。正常情况下应该与单机容量相同。
	 */
	static final int MAX_PENDING_CALL = 10000;

	private boolean isSupportVideo = false;

	private String outboundCms = "";

	SleeServer ss = null;

	public static CMSInterfacePrx getCcodProxy(long sessionId)
	{
		return V2_VGProxySession.getProxy(sessionId);
	}

	public static CCODResultT GetServiceData(long sessionID,
			EncodingT encoding, ServiceDataTHolder data)
	{
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionID);
		if (ccodProxy == null)
		{
			return CCODResultT.ResInvalidParameter;
		}
		return ccodProxy.GetServiceData(sessionID, encoding, data);
	}

	/**
	 * 容错。清除所有未完成的呼叫。
	 */
	static void onDisconnectFromServer()
	{
		Log.wError(LogDef.id_0x10150008, V2_VGProxySession.callSn2SessionId
				.size());

		synchronized (V2_CallSession.callAgentMap)
		{
			for (CallAgent agent : V2_CallSession.callAgentMap.values())
			{
				V2_CallAgentImpl agentX = (V2_CallAgentImpl) agent;
				long sessionId = V2_VGProxySession
						.getSessionIdFromCallId(agentX.callId);
				CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
				if ((agentX.confId != -1) && (ccodProxy != null))
				{
					ccodProxy.DestroyConference(sessionId, agentX.confId);
					agentX.confId = -1;
				}
			}
		}

		V2_CallSession.callAgentMap.clear();

		V2_VGProxySession.callSn2SessionId.clear();
		V2_VGProxySession.sessionId2Callsn.clear();
	}

	static void setCCODProxy(CMSInterfacePrx proxy, String cmsName)
	{
		// ccodProxy = proxy;
		// V2_VGProxyImp.cmsName = cmsName;
		V2_VGProxySession.setProxy(cmsName, proxy);
	}

	public static void SetServiceData(long sessionID,
			java.util.Map<java.lang.String, java.lang.String> data,
			EncodingT encoding)
	{
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionID);
		if (ccodProxy == null)
		{
			return;
		}
		ccodProxy.SetServiceData(sessionID, data, encoding);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#attachMedia(int)
	 */
	public int attachMedia(int callID)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int connectionId = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId, callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		if (mepId != -1)
		{
			V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
					.get(sessionId);
			if (callIds.needJoin2Mep(callID))
			{// 接回媒体
				int ret = Constant.ERR_GeneralError;
				if (!isSupportVideo)
				{
					ret = V2_ConstTransform
							.turnCCODResultT2SleeResult(ccodProxy.Join(
									sessionId, connectionId, mepId,
									MediaDirectionT.DirectionFull));
				}
				else
				{
					ret = V2_ConstTransform
							.turnCCODResultT2SleeResult(ccodProxy.JoinV(
									sessionId, connectionId, mepId,
									MediaDirectionT.DirectionFull, "", ""));
				}
				if (ret == Constant.GATEWAY_SUCCESS)
				{
					callIds.setJoin2Mep(callID);
				}
				Log.wDebug("接回[connectionId=%d mepId=%d]返回  [0x%X]",
						connectionId, mepId, ret);
				return ret;
			}
			return Constant.ERR_NoCall;
		}

		if (sessionId == -1l)
		{
			return Constant.ERR_NoCall;
		}

		Ice.IntHolder mepIdWrapper = new Ice.IntHolder();
		int ret = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.CreateMediaEndpoint(sessionId, mepIdWrapper));
		if (ret == Constant.GATEWAY_SUCCESS)
		{
			mepId = mepIdWrapper.value;

			V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
					.get(sessionId);
			callIds.mepId = mepId;

			MediaDirectionT mdt = null;
			mdt = MediaDirectionT.DirectionFull;
			if (!isSupportVideo)
			{
				ret = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
						.Join(sessionId, connectionId, mepId, mdt));
			}
			else
			{
				ret = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
						.JoinV(sessionId, connectionId, mepId, mdt, "", ""));
			}

			if (ret != Constant.GATEWAY_SUCCESS)
			{
				Log.wError(LogDef.id_0x10150006, sessionId, connectionId, ret);
			}
		}
		else
		{
			Log.wError(LogDef.id_0x10150007, sessionId, ret);
		}

		return ret;
	}

	public boolean isInitialized()
	{
		return (null != V2_VGProxySession.getProxy());
	}

	public int mgAdjustSpeed(int resID, int callID, int adjment)
	{
		return Constant.ERR_GeneralError;
	}

	public int mgAdjustVolume(int resID, int callID, int adjment)
	{
		return Constant.ERR_GeneralError;
	}

	public int mgASR(int resID, int callID, int maxKeys, String szInteruptKeys,
			int rate, int fileCount, String[] szVoxFileList, String szGrammar,
			int noSpeechTimeout, int acceptScore, int resultNum,
			float timeoutBetweenWord, int voiceLib)
	{
		return Constant.ERR_GeneralError;
	}

	public int mgFlushBuffer(int resID, int callID)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.FlushDtmfBuffer(sessionId, mepId));
	}

	public int mgPlayList(int resID, int callID, String interruptKeys,
			int rate, int fileCount, String[] voiceFileList, int voiceLib)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		PlayListElemT[] list = new PlayListElemT[voiceFileList.length];
		for (int i = 0; i < voiceFileList.length; i++)
		{
			list[i] = new PlayListElemT();

			if (voiceFileList[i].indexOf(":\\") != -1)
			{
				list[i].type = ElemT.VoiceFile;
				list[i].content = transFileName(voiceFileList[i]);
			}
			else
			{
				list[i].type = ElemT.TTSString;
				list[i].content = voiceFileList[i];
			}

			list[i].encoding = EncodingT.UTF8;
			list[i].rate = rate;
		}

		TTSVoiceLibT tvlt = V2_ConstTransform.turnASRVoiceLib(voiceLib);

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy.PlayList(
				sessionId, mepId, list, tvlt, interruptKeys));
	}

	public int mgPlayTTS(int resID, int callID, String interruptKeys, int type,
			String szText, int voiceLib, int rate)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		TTSVoiceLibT tvlt = V2_ConstTransform.turnASRVoiceLib(voiceLib);

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy.PlayTTS(
				sessionId, mepId, szText, EncodingT.UTF8, tvlt, interruptKeys));

	}

	public int mgPlayVoice(int resID, int callID, String fileName,
			String interruptKeys, int rate, int beginTime)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform
				.turnCCODResultT2SleeResult(ccodProxy.PlayFile(sessionId,
						mepId, transFileName(fileName), rate, interruptKeys));

	}

	public int mgReceiveDTMF(int resID, int callID, int keyCount,
			String szInteruptKeys, int max1stkeyTime, int timeBetweenKey)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.ReceiveDTMF(sessionId, mepId, keyCount, max1stkeyTime,
						timeBetweenKey, szInteruptKeys));

	}

	public int mgReceiveFax(int resID, int callID, String szFileName)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.ReceiveFax(sessionId, mepId, transFileName(szFileName)));

	}

	public int mgRecordVoice(int resID, int callID, String szFileName,
			String szInteruptKeys, int maxTime, int rate)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.RecordVoice(sessionId, mepId, transFileName(szFileName),
						maxTime, szInteruptKeys));
	}

	public int mgSendDTMF(int resID, int callID, String szSignals)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy.SendDTMF(
				sessionId, mepId, szSignals));
	}

	public int mgSendFax(int resID, int callID, String szFileName)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy.SendFax(
				sessionId, mepId, transFileName(szFileName)));
	}

	public int mgStopIO(int resID, int callID)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		ccodProxy.StopIO(sessionId, mepId);

		return 0;
	}

	public int rmBlindCloseConf(int nResID, int nCallID, int confID)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(nCallID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		if ((confID != -1) && (ccodProxy != null))
		{
			return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.DestroyConference(sessionId, confID));
		}
		return 0;
	}

	public int rmBlindCreateConf(int resID1, int resID2, int nCallID,
			IntHolder pnConfID, boolean isVideoCall)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(nCallID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		if (!isSupportVideo)
		{
			return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.CreateConference(sessionId, 3, pnConfID));
		}
		else
		{
			return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.CreateConferenceV(sessionId, 3,
							((isVideoCall) ? MediaTypeT.VideoMedia
									: MediaTypeT.VoiceMedia), pnConfID));
		}
	}

	public int rmRouteTwoRes(int res1ID, int res2ID, int reserved,
			String videoUrl1, String videoUrl2)
	{
		long sessionId1 = V2_VGProxySession.getSessionIdFromCallId(res1ID);
		long sessionId2 = V2_VGProxySession
				.getSessionIdFromCallId((res2ID == -1) ? res1ID : res2ID);
		int connectionId1 = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId1, res1ID);
		int connectionId2 = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId2, res2ID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId1);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		MediaDirectionT mdt = null;
		if (reserved == Constant.RM_FULLDUP)
		{
			mdt = MediaDirectionT.DirectionFull;
		}
		else if (reserved == Constant.RM_HALFDUP)
		{
			mdt = MediaDirectionT.DirectionHalf;
		}
		else if (reserved == Constant.RM_DSPDUP)
		{
			mdt = MediaDirectionT.DirectionNone;
		}
		else if (reserved == Constant.RM_BOTHVIDEO)
		{
			mdt = MediaDirectionT.DirectionVideoBoth;
		}
		else if (reserved == Constant.RM_ORIVIDEO)
		{
			mdt = MediaDirectionT.DirectionOriVideo;
		}
		else if (reserved == Constant.RM_DESTVIDEO)
		{
			mdt = MediaDirectionT.DirectionDestVideo;
		}

		int ret = Constant.ERR_GeneralError;
		if (!isSupportVideo)
		{
			ret = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy.Join(
					sessionId1, connectionId1, connectionId2, mdt));
		}
		else
		{
			ret = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy.JoinV(
					sessionId1, connectionId1, connectionId2, mdt, videoUrl1,
					videoUrl2));
		}

		if (ret == Constant.GATEWAY_SUCCESS)
		{
			V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
					.get(sessionId1);
			callIds.setJoin2Connection(res1ID);
		}
		return ret;
	}

	public int rmStartConferrenceRecord(int resID, int callID,
			String strRecordFileName)
	{
		return Constant.ERR_GeneralError;
	}

	public int rmUnrouteTwoRes(int res1ID, int res2ID, int mode)
	{
		long sessionId1 = V2_VGProxySession.getSessionIdFromCallId(res1ID);
		long sessionId2 = V2_VGProxySession.getSessionIdFromCallId(res2ID);
		if ((sessionId1 == -1) || (sessionId2 == -1))
		{
			return Constant.ERR_NoCall;
		}
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId1);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}
		int connectionId1 = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId1, res1ID);
		int connectionId2 = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId2, res2ID);

		MediaDirectionT mdt = null;
		if (mode == Constant.RM_FULLDUP)
		{
			mdt = MediaDirectionT.DirectionFull;
		}
		else if (mode == Constant.RM_HALFDUP)
		{
			mdt = MediaDirectionT.DirectionHalf;
		}
		else if (mode == Constant.RM_DSPDUP)
		{
			mdt = MediaDirectionT.DirectionNone;
		}
		else if (mode == Constant.RM_BOTHVIDEO)
		{
			mdt = MediaDirectionT.DirectionVideoBoth;
		}
		else if (mode == Constant.RM_ORIVIDEO)
		{
			mdt = MediaDirectionT.DirectionOriVideo;
		}
		else if (mode == Constant.RM_DESTVIDEO)
		{
			mdt = MediaDirectionT.DirectionDestVideo;
		}

		if (!isSupportVideo)
		{
			return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.Unjoin(sessionId1, connectionId1, connectionId2));
		}
		else
		{
			return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.UnjoinV(sessionId1, connectionId1, connectionId2, mdt));
		}
	}

	public int sgAnswerCall(int resID, int callID)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		String enterpriseId = V2_VGProxySession.getEnterpriseIdFromCallId(
				sessionId, callID);
		int connectionId = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId, callID);
		boolean isOldCall = V2_VGProxySession.getIsOldCallFromCallId(sessionId,
				callID);
		V2_CallAgentImpl agent = V2_CallAgentImpl.getCallAgent(resID, callID);
		V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
				.get(sessionId);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}
		CMSInterfacePrx ccodProxyOld = ccodProxy;
		IntHolder mepID = new IntHolder();

		if (isOldCall)
		{
			// 如果转移前没有记过话单，则当前呼叫的时间使用随路数据中的原始呼叫时间，否则两个时间全部使用接口中的时间。
			ServiceDataTHolder serviceData = new ServiceDataTHolder();
			ccodProxy.GetServiceData(sessionId, EncodingT.UTF8, serviceData);
			Log.wDebug(LogDef.id_0x10050000_7, sessionId, serviceData.value
					.toString());

			if (FinishedSDR.value.equals(serviceData.value
					.get(ADIsWriteSDR.value)))
			{
				// 明确声明写过话单。
				callIds.setStartTimestampFromCallID(callID, callIds
						.getAccessTimestampFromCallID(callID));
			}
			else
			{
				// 随路数据中必须有时间。
				String accessStr = serviceData.value.get(AccessTime.value);
				String startStr = serviceData.value.get(StartTime.value);

				if ((accessStr != null) && !"".equals(accessStr))
				{
					callIds.setAccessTimestampFromCallID(callID, Integer
							.parseInt(accessStr));
				}
				else
				{
					callIds.setAccessTimestampFromCallID(callID, 0);
				}

				if ((startStr != null) && !"".equals(startStr))
				{
					callIds.setStartTimestampFromCallID(callID, Integer
							.parseInt(startStr));
				}
				else
				{
					callIds.setStartTimestampFromCallID(callID, 0);
				}
			}
		}

		int result;
		try
		{
			if (isOldCall)
			{
				result = this.attachMedia(callID);
				mepID.value = callIds.mepId;
			}
			else
			{
				result = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
						.Accept(sessionId, connectionId, mepID));

				if (result != Constant.GATEWAY_SUCCESS)
				{
					V2_CallStatImp.addEvent(callID,
							V2_CMSEventReceiver.IVRERR_RES_ID, sessionId,
							enterpriseId,
							(int) (System.currentTimeMillis() / 1000)
									+ V2_CMSEventReceiver.cmsTimeOffset,
							agent.ani, agent.dnis, Constant.SG_CALLRELEASED);
				}
				else
				{
					callIds.mepId = mepID.value;
				}
			}
		}
		catch (Ice.SocketException se)
		{
			if (ccodProxy == ccodProxyOld)
			{
				Log.wError(LogDef.id_0x10150003, callID, sessionId);
				ccodProxy = null;
				onDisconnectFromServer();
			}
			else
			{
				Log.wError(LogDef.id_0x10150004, callID, sessionId);
			}

			V2_CallSession.clear(callID);

			return Constant.ERR_RPC;
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150001, e);
			result = Constant.ERR_RPC;
		}

		if (result != Constant.GATEWAY_SUCCESS)
		{
			Log.wError(LogDef.id_0x10150005, callID, sessionId, result);

			V2_CallSession.clear(callID);

			return result;
		}
		else
		{
			Log.wDebug(LogDef.id_0x10050032, callID, sessionId, mepID.value);
		}

		// CallIdsInSession callIds =
		// VGProxySession.sessionId2Callsn.get(sessionId);
		// if (callIds != null)
		// {
		// callIds.mepId = mepID.value;
		// }

		return result;
	}
/**
 * --------如果szOriCallerNum szOriCalledNum为空-------
 * 纯外呼：
 * szCallerNum格式: 主叫_enterpriseID_accountId
 * 
 * 单步转移：
 * szCallerNum格式: 主叫_原始主叫
 * szCalledNum格式：主叫_record_原始被叫
 * 
 * --------如果szOriCallerNum szOriCalledNum非空--------
 * 纯外呼：
 * szCallerNum格式: 主叫_enterpriseID
 * 
 * 单步转移：
 * szCalledNum格式：主叫_record
 */
	public int sgBlindMakeCall(int callID, String szCallerNum,
			String szOriCallerNum, String szCalledNum, String szOriCalledNum,
			int timeout, IntHolder resID1, IntHolder callID1,
			boolean isVideoCall)
	{
		boolean isHostAgent = false;
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		CMSInterfacePrx ccodProxy = null;
		if (sessionId == -1)
		{// 纯外呼操作
			if (outboundCms.equals(""))
			{// 没有指定外呼cms，任选一个
				ccodProxy = V2_VGProxySession.getProxy();
			}
			else
			{
				ccodProxy = V2_VGProxySession.getProxy(outboundCms);
			}
		}
		else
		{
			ccodProxy = V2_VGProxySession.getProxy(sessionId);
			Log.wDebug("sgBlindMakeCall select cms[%s] proxy[%s]",
					V2_VGProxySession.getCmsId(ccodProxy), ccodProxy);
		}
		if (ccodProxy == null)
		{
			return Constant.ERR_RPC;
		}
		int result = Constant.ERR_GeneralError;
		String enterpriseID = null;
		String accountID = null;
		if (sessionId == -1)
		{// 纯外呼操作，生成sessionId.
			String[] callerInfo = szCallerNum.split("_");
			if (callerInfo.length == 1)
			{// 没有指定enterpriseID
				Log.wError("外呼没有指定enterpriseId.");
				return result;
			}
			else
			{
				szCallerNum = callerInfo[0];
				enterpriseID = callerInfo[1];
				if(callerInfo.length > 2)
				{
					accountID = callerInfo[2];
				}
			}
			ServiceInfoT si = new ServiceInfoT();
			si.srcService = ServiceTypeT.IVRService;
			si.hostService = ServiceTypeT.IVRService;
			ServiceInfoTHolder info = new ServiceInfoTHolder(si);
			result = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.CreateSession(enterpriseID, null, ServiceTypeT.IVRService,
							info));
			if (result == Constant.GATEWAY_SUCCESS)
			{
				sessionId = info.value.sessionID;
				isHostAgent = true;
			}
			else
			{
				ccodProxy.SetCallback(ss.server
						.stringToIdentity("SLEE4CCODCMSServer"),
						V2_VGProxySession.getSleeServiceName(),
						ServiceTypeT.IVRService);
				result = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
						.CreateSession(enterpriseID, null,
								ServiceTypeT.IVRService, info));
				if (result == Constant.GATEWAY_SUCCESS)
				{
					sessionId = info.value.sessionID;
					isHostAgent = true;
				}
				else
					return result;
			}
		}

		IntHolder connectionIdHolder = new IntHolder();
		IntHolder mepIdHolder = new IntHolder();

		String callerNum = szCallerNum;
		String oriCallerNum = "";
		String calledNum = szCalledNum;
		String oriCalledNum = "";
		String trunkTag = "";
		if((szOriCallerNum.isEmpty() ||szOriCallerNum.equals(""))
				&& (szOriCalledNum.isEmpty() || szOriCalledNum.equals("")))
		{// 解析原始主被叫，以及扩展参数
			String[] callerInfo = szCallerNum.split("_");
			if (callerInfo.length > 1)
			{
				callerNum = callerInfo[0];
				oriCallerNum = szCallerNum.replaceFirst(callerNum, "")
						.replaceFirst("_", "");
			}
			String[] calledInfo = szCalledNum.split("_");
			if (calledInfo.length > 1)
			{
				calledNum = calledInfo[0];
				trunkTag = calledInfo[1];
				oriCalledNum = szCalledNum.replaceFirst(
						calledNum + "_" + trunkTag, "").replaceFirst("_", "");
			}
		}
		else
		{
			oriCallerNum = szOriCallerNum;
			oriCalledNum = szOriCalledNum;

			String[] calledInfo = szCalledNum.split("_");
			if (calledInfo.length > 1)
			{
				calledNum = calledInfo[0];
				trunkTag = calledInfo[1];
			}
		}
		
		if (!isSupportVideo)
		{
			result = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.CreateCallEx(sessionId, callerNum, oriCallerNum,
							calledNum, oriCalledNum, trunkTag, 0,
							MediaDirectionT.DirectionFull, timeout,
							connectionIdHolder, mepIdHolder));
		}
		else
		{// 支持视频
			result = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
					.CreateCallExV(sessionId, callerNum, oriCallerNum,
							calledNum, oriCalledNum, trunkTag, 0,
							MediaDirectionT.DirectionFull, timeout,
							((isVideoCall) ? MediaTypeT.VideoMedia
									: MediaTypeT.VoiceMedia),
							connectionIdHolder, mepIdHolder));
		}

		if (result != Constant.GATEWAY_SUCCESS)
		{
			return result;
		}
		V2_CallAgentImpl agent = V2_VGProxySession.newCallAgent(sessionId,
				connectionIdHolder.value, mepIdHolder.value, enterpriseID,
				accountID, 0, false, isHostAgent, V2_VGProxySession
						.getCmsId(ccodProxy));

		if (agent == null)
		{
			return Constant.ERR_GeneralError;
		}

		resID1.value = agent.callId;
		callID1.value = agent.callId;
		return result;
	}

	public int sgReleaseCall(int resID, int callID)
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		int connectionId = V2_VGProxySession.getConnectionIdFromCallId(
				sessionId, callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.Disconnect(sessionId, connectionId));
	}

	public boolean shiftService(int port, int callId, String callingNumber,
			String destNumber, IntHolder result)
	{
		if (destNumber.startsWith("TEL:") || destNumber.startsWith("SIP:"))
		{
			return false;
		}

		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callId);
		boolean isOldCall = V2_VGProxySession.getIsOldCallFromCallId(sessionId,
				callId);
		V2_CallIdsInSession callIds = V2_VGProxySession.sessionId2Callsn
				.get(sessionId);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return false;
		}

		ServiceTypeT stt = ServiceTypeT.ACDService;
		Ice.IntHolder timestamp = new Ice.IntHolder();

		ServiceDataTHolder serviceData = new ServiceDataTHolder();
		ccodProxy.GetServiceData(sessionId, EncodingT.UTF8, serviceData);

		if (!isOldCall
				|| FinishedSDR.value.equals(serviceData.value
						.get(ADIsWriteSDR.value)))
		{
			// 电话是新呼叫或者UCDS记过SDR，所以在随路数据中使用当前呼叫的时间，否则只能使用原有随路数据中的原始呼叫时间。
			serviceData.value.put(AccessTime.value, ""
					+ callIds.getAccessTimestampFromCallID(callId));
			serviceData.value.put(StartTime.value, ""
					+ callIds.getStartTimestampFromCallID(callId));
		}

		serviceData.value.put("EnterpriseID", callIds.enterpriseId);
		serviceData.value.put(AccountID.value, callIds.accountId);

		ccodProxy.SetServiceData(sessionId, serviceData.value, EncodingT.UTF8);

		int ret = V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.ShiftService(sessionId, stt, timestamp));

		if (ret != Constant.GATEWAY_SUCCESS)
		{
			Log.wError(LogDef.id_0x10150009, sessionId, port, callId,
					callingNumber, destNumber);
			result.value = ret;
		}
		else
		{
			/**
			 * 给自己发一个假挂机事件。
			 */
			int connectionId = callIds.getConnectionIdFromCallId(callId);
			V2_CMSEventReceiver.onCustomHangup(callId, sessionId, connectionId,
					timestamp.value);
			result.value = Constant.ERR_Success;
		}

		return true;
	}

	String transFileName(String fileName)
	{
		if (fileName == null)
		{
			return "";
		}

		if (fileName.indexOf('/') == 0)
		{
			return "file:" + fileName.substring(1);
		}
		else if ((fileName.indexOf('/') == -1)
				|| (fileName.indexOf(":\\") != -1))
		{
			return "file:///" + fileName;
		}

		return fileName;
	}

	public int VGInitialize(ISysCfgData sysCfgData)
	{
		try
		{
			String glsConnection = sysCfgData.getGlsConnection();
			int cmsServerPort = sysCfgData.getCmsServerPort();
			String sleeName = sysCfgData.getGlsSleeServiceName();
			// int sgPort = sysCfgData.getCmsServerPort();
			String statFileDir = sysCfgData.getCcodStatFileDir();
			String serviceId = sysCfgData.getCcodServiceId();
			String agentServiceId = sysCfgData.getCcodAgentServiceId();
			isSupportVideo = sysCfgData.isCcodSupportVideo();
			outboundCms = sysCfgData.getCcodOutboundCms();

			V2_CMSEventReceiver.cmsmp.start();

			V2_VGProxySession.Initialize(sleeName, serviceId, agentServiceId);

			Log.wDebug(LogDef.id_0x10050000, cmsServerPort);
			Ice.Communicator ice_server = null;
			Ice.ObjectAdapter adapter = null;

			if (UnifiedServiceManagement.configData.isCcodUseSSL())
			{
				ice_server = Ice.Util
						.initialize(new String[] { "--Ice.Config="
								+ ISysCfgData.queryRegData()
								+ (ISysCfgData.isNewPath() ? "/config/Ice_SLEE4CCODCMSServer.cfg"
										: "/SoftSwitch/Config/Ice_SLEE4CCODCMSServer.cfg") });
				adapter = ice_server.createObjectAdapterWithEndpoints(
						"SLEE4CCODCMSServer:", "ssl -p " + cmsServerPort);
			}
			else
			{
				ice_server = Ice.Util.initialize(new String[] {
						"--Ice.ThreadPool.Server.Size=" + 20,
						"--Ice.ACM.Client=0", "--Ice.ACM.Server=0" });
				adapter = ice_server.createObjectAdapterWithEndpoints(
						"SLEE4CCODCMSServer:", "default -p " + cmsServerPort);
			}

			Ice.Object object = new V2_ServiceUnitInterfaceImpl();
			adapter.add(object, ice_server
					.stringToIdentity("SLEE4CCODCMSServer"));
			adapter.activate();

			ss = new SleeServer(ice_server);
			ss.start();
			Log.wDebug(LogDef.id_0x10050002, cmsServerPort);

			GLSClient glsClient = new GLSClient(glsConnection, sleeName);
			glsClient.start();
			Log.wDebug(LogDef.id_0x10050003);

			V2_CallStatImp.start(statFileDir, glsClient);

			return 1;
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);
			Log.wDebug(LogDef.id_0x10250003, sysCfgData.getGlsConnection(),
					sysCfgData.getCmsServerPort());
			return 0;
		}
	}

	@Override
	public int mgVoiceEdit(int resID, int callID, String szFileName, int rate,
			int beginTime, int keyCount, String szInteruptKeys,
			int max1stkeyTime, int timeBetweenKey) throws Exception
	{
		return Constant.ERR_GeneralError;
	}

	@Override
	public int mgVoiceListEdit(int resID, int callID, int rate, int fileCount,
			String[] szVoxFileList, int voiceLib, int keyCount,
			String szInteruptKeys, int max1stkeyTime, int timeBetweenKey)
			throws Exception
	{
		return Constant.ERR_GeneralError;
	}

	@Override
	public int mgAppendVideo(int resID, int callID, String[][] infoOnVideo)
			throws Exception
	{
		long sessionId = V2_VGProxySession.getSessionIdFromCallId(callID);
		CMSInterfacePrx ccodProxy = getCcodProxy(sessionId);
		if (ccodProxy == null)
		{
			return Constant.ERR_NoCall;
		}

		int size = infoOnVideo.length;
		VideoContentParamT[] vcps = new VideoContentParamT[size];

		for (int i = 0; i < size; i++)
		{
			vcps[i] = new VideoContentParamT();
			vcps[i].infoID = Integer.parseInt(infoOnVideo[i][0]);
			int opType = Integer.parseInt(infoOnVideo[i][1]);
			if (opType == 0)
				vcps[i].opType = OperateTypeT.SHOW;
			else if (opType == 1)
				vcps[i].opType = OperateTypeT.MODIFY;
			else
				vcps[i].opType = OperateTypeT.HIDE;
			int contType = Integer.parseInt(infoOnVideo[i][2]);
			if (contType == 0)
				vcps[i].contType = ContentTypeT.CAPTION;
			else if (contType == 1)
				vcps[i].contType = ContentTypeT.LOGO;
			else
				vcps[i].contType = ContentTypeT.OTHER;
			vcps[i].cfgIndex = Integer.parseInt(infoOnVideo[i][3]);
			vcps[i].content = infoOnVideo[i][4];
		}
		int mepId = V2_VGProxySession.getMepIdFromCallId(sessionId, callID);
		return V2_ConstTransform.turnCCODResultT2SleeResult(ccodProxy
				.AppendVideo(sessionId, mepId, vcps));
	}
}
