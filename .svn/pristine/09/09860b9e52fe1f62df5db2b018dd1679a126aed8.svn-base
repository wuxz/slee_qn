/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Ice.IntHolder;

import com.channelsoft.slee.callagent.V2_VGProxy;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.util.Constant;

class CallerWaiter
{
	VGCPMessage msg = null;

	CyclicBarrier waiter = new CyclicBarrier(2);
}

/**
 * 封装基本的网关操作，并把从网关收到的事件保存起来。
 * 
 * @author wuxz
 */
public class SSR2Agent implements V2_VGProxy
{
	HashMap<Integer, CallerWaiter> callerWaiters = new HashMap<Integer, CallerWaiter>();

	SSR2Client client = null;

	LinkedBlockingQueue<EventMessage> eventMsgs = new LinkedBlockingQueue<EventMessage>();

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#attachMedia(int)
	 */

	/**
	 * 任一操作的超时时长，单位为秒。
	 */
	int operationTimeoutSeconds = 8;

	String sgIp;

	int sgPort;

	String sleeName;

	String sleePassword;

	public int attachMedia(int callID)
	{
		return Constant.ERR_GeneralError;
	}

	public void dispatchMessage(EventMessage event) throws Exception
	{
		eventMsgs.add(event);
	}

	/**
	 * 取得一条事件消息。
	 * 
	 * @param timeout
	 *            超时时长，单位为毫秒。
	 * @return 取得的事件消息。如果在指定的超时时间内没有消息，则返回null。
	 * @throws InterruptedException
	 */
	public EventMessage getEvent(int timeout) throws InterruptedException
	{
		return eventMsgs.poll(timeout, TimeUnit.MILLISECONDS);
	}

	/**
	 * @return the operationTimeoutSeconds
	 */
	public int getOperationTimeoutSeconds()
	{
		return operationTimeoutSeconds;
	}

	/**
	 * @return the sleeName
	 */
	public String getSleeName()
	{
		return sleeName;
	}

	/**
	 * @return the sleePassword
	 */
	public String getSleePassword()
	{
		return sleePassword;
	}

	/**
	 * @return
	 */
	synchronized public int initialize()
	{
		try
		{
			if ((client != null) && client.isConnected())
			{
				client.closeConnection();
			}
		}
		catch (Throwable e)
		{
		}

		client = new SSR2Client(sgIp, sgPort);
		client.setEventConsumer(this);
		client.open();

		return 1;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#isInitialized()
	 */
	public boolean isInitialized()
	{
		return ((client != null) && client.isConnected() && client.isRegistered);
	}

	public int mgAdjustSpeed(int resID, int callID, int adjment)
			throws Exception
	{
		MGAdjustSpeedMessage msg = new MGAdjustSpeedMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.adjment = adjment;

		return waitForReply(msg).status;
	}

	public int mgAdjustVolume(int resID, int callID, int adjment)
			throws Exception
	{
		MGAdjustVolumeMessage msg = new MGAdjustVolumeMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.adjment = adjment;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgASR(int, int, int, java.lang.String, int, int, java.lang.String[], java.lang.String, int, int, int, float, int)
	 */
	public int mgASR(int resID, int callID, int maxKeys, String szInteruptKeys,
			int rate, int fileCount, String[] szVoxFileList, String szGrammar,
			int noSpeechTimeout, int acceptScore, int resultNum,
			float timeoutBetweenWord, int voiceLib) throws Exception
	{
		MGASRMessage msg = new MGASRMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.iopParm = new MGIOPara();
		msg.iopParm.keyLength = maxKeys;
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.rate = rate;
		msg.files = new VecStr(szVoxFileList);
		msg.grammar = szGrammar;
		msg.noSpeechTimeout = noSpeechTimeout;
		msg.acceptScore = acceptScore;
		msg.resultNum = resultNum;
		msg.timeoutBetweenWord = (int) timeoutBetweenWord;
		msg.voiceLib = voiceLib;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgFlushBuffer(int, int)
	 */
	public int mgFlushBuffer(int resID, int callID) throws Exception
	{
		MGFlushBufferMessage msg = new MGFlushBufferMessage();
		msg.resId = resID;
		msg.callId = callID;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgPlayList(int, int, java.lang.String, int, int, java.lang.String[], int)
	 */
	public int mgPlayList(int resID, int callID, String szInteruptKeys,
			int rate, int fileCount, String[] szVoxFileList, int voiceLib)
			throws Exception
	{
		MGPlayListMessage msg = new MGPlayListMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.iopParm = new MGIOPara();
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.rate = rate;
		msg.voiceLib = voiceLib;
		msg.files = new VecStr(szVoxFileList);

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgPlayTTS(int, int, java.lang.String, int, java.lang.String, int, int)
	 */
	public int mgPlayTTS(int resID, int callID, String szInteruptKeys,
			int type, String szText, int voiceLib, int rate) throws Exception
	{
		MGPlayTTSMessage msg = new MGPlayTTSMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.iopParm = new MGIOPara();
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.type = type;
		msg.text = szText;
		msg.rate = rate;
		msg.voiceLib = voiceLib;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgPlayVoice(int, int, java.lang.String, java.lang.String, int, int)
	 */
	public int mgPlayVoice(int resID, int callID, String szFileName,
			String szInteruptKeys, int rate, int beginTime) throws Exception
	{
		MGPlayFileMessage msg = new MGPlayFileMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.filename = szFileName;
		msg.iopParm = new MGIOPara();
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.rate = rate;
		msg.begintime = beginTime;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgReceiveDTMF(int, int, int, java.lang.String, int, int)
	 */
	public int mgReceiveDTMF(int resID, int callID, int keyCount,
			String szInteruptKeys, int max1stkeyTime, int timeBetweenKey)
			throws Exception
	{
		MGReceiveDtmfsMessage msg = new MGReceiveDtmfsMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.iopParm = new MGIOPara();
		msg.iopParm.betweenKeyTimeout = timeBetweenKey;
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.iopParm.firstKeyTimeout = max1stkeyTime;
		msg.iopParm.keyLength = keyCount;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgReceiveFax(int, int, java.lang.String)
	 */
	public int mgReceiveFax(int resID, int callID, String szFileName)
			throws Exception
	{
		MGReceiveFaxMessage msg = new MGReceiveFaxMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.filename = szFileName;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgRecordVoice(int, int, java.lang.String, java.lang.String, int, int)
	 */
	public int mgRecordVoice(int resID, int callID, String szFileName,
			String szInteruptKeys, int maxTime, int rate) throws Exception
	{
		MGRecordFileMessage msg = new MGRecordFileMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.filename = szFileName;
		msg.iopParm = new MGIOPara();
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.iopParm.timelength = maxTime;
		msg.rate = rate;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgSendDTMF(int, int, java.lang.String)
	 */
	public int mgSendDTMF(int resID, int callID, String szSignals)
			throws Exception
	{
		MGSendDtmfsMessage msg = new MGSendDtmfsMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.dtmfs = szSignals;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgSendFax(int, int, java.lang.String)
	 */
	public int mgSendFax(int resID, int callID, String szFileName)
			throws Exception
	{
		MGSendFaxMessage msg = new MGSendFaxMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.filename = szFileName;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#mgStopIO(int, int)
	 */
	public int mgStopIO(int resID, int callID) throws Exception
	{
		MGStopIOMessage msg = new MGStopIOMessage();
		msg.resId = resID;
		msg.callId = callID;

		return waitForReply(msg).status;
	}

	boolean onReply(VGCPMessage msg) throws Exception
	{
		CallerWaiter callerWaiter = null;
		synchronized (callerWaiters)
		{
			callerWaiter = callerWaiters.remove(msg.invokeId);
		}

		if (callerWaiter != null)
		{
			callerWaiter.msg = msg;
			try
			{
				callerWaiter.waiter.await(0, TimeUnit.SECONDS);
			}
			catch (Exception e)
			{
			}
		}

		return callerWaiter != null;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#rmBlindCloseConf(int, int, int)
	 */
	public int rmBlindCloseConf(int resID, int callID, int confID)
			throws Exception
	{
		RMBlindCloseConfMessage msg = new RMBlindCloseConfMessage();
		msg.confId = confID;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#rmBlindCreateConf(int, int, int, Ice.IntHolder)
	 */
	public int rmBlindCreateConf(int resID1, int resID2, int callID,
			IntHolder pnConfID, boolean isVideoCall) throws Exception
	{
		RMBlindCreateConfMessage msg = new RMBlindCreateConfMessage();
		msg.resId1 = resID1;
		msg.resId2 = resID2;

		VGCPMessage msgReply = waitForReply(msg);
		if (msgReply instanceof RMBlindCreateConfReply)
		{
			pnConfID.value = ((RMBlindCreateConfReply) msgReply).confId;
			return msgReply.status;
		}
		return msg.status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#rmRouteTwoRes(int, int, int)
	 */
	public int rmRouteTwoRes(int res1ID, int res2ID, int mode,
			String videoUrl1, String videoUrl2) throws Exception
	{
		RMRouteTwoResMessage msg = new RMRouteTwoResMessage();
		msg.resId1 = res1ID;
		msg.resId2 = res2ID;
		msg.mode = mode;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#rmStartConferrenceRecord(int, int, java.lang.String)
	 */
	public int rmStartConferrenceRecord(int resID, int callID,
			String strRecordFileName) throws Exception
	{
		RMAddChannel2VoiceMixerMessage msg = new RMAddChannel2VoiceMixerMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.recordFile = strRecordFileName;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#rmUnrouteTwoRes(int, int, int)
	 */
	public int rmUnrouteTwoRes(int res1ID, int res2ID, int mode)
			throws Exception
	{
		RMUnrouteTwoResMessage msg = new RMUnrouteTwoResMessage();
		msg.resId1 = res1ID;
		msg.resId2 = res2ID;
		msg.mode = mode;

		return waitForReply(msg).status;
	}

	/**
	 * @param operationTimeoutSeconds
	 *            the operationTimeoutSeconds to set
	 */
	public void setOperationTimeoutSeconds(int operationTimeoutSeconds)
	{
		this.operationTimeoutSeconds = operationTimeoutSeconds;
	}

	/**
	 * @param sleeName
	 *            the sleeName to set
	 */
	public void setSleeName(String sleeName)
	{
		this.sleeName = sleeName;
	}

	/**
	 * @param sleePassword
	 *            the sleePassword to set
	 */
	public void setSleePassword(String sleePassword)
	{
		this.sleePassword = sleePassword;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#sgAnswerCall(int, int)
	 */
	public int sgAnswerCall(int resID, int callID) throws Exception
	{
		SGAnswerCallMessage msg = new SGAnswerCallMessage();
		msg.callId = callID;
		msg.resId = resID;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#sgBlindMakeCall(int, java.lang.String, java.lang.String, int, Ice.IntHolder, Ice.IntHolder)
	 */
	public int sgBlindMakeCall(int callID, String szCallerNum,
			String szOriCallerNum, String szCalledNum, String szOriCalledNum,
			int timeout, IntHolder resID, IntHolder callID2, boolean isVideoCall)
			throws Exception
	{
		SGBlindMakecallMessage msg = new SGBlindMakecallMessage();
		msg.caller = szCallerNum;
		msg.called = szCalledNum;
		msg.oriCaller = szOriCallerNum;
		msg.oriCalled = szOriCalledNum;
		msg.timeout = timeout;
		msg.mediaType = (isVideoCall) ? Constant.VGCP_MEDIA_TYPE_VIDEO
				: Constant.VGCP_MEDIA_TYPE_AUDIO;

		VGCPMessage msgReply = waitForReply(msg);
		if (msgReply instanceof SGBlindMakecallReply)
		{
			resID.value = ((SGBlindMakecallReply) msgReply).resId;
			callID2.value = ((SGBlindMakecallReply) msgReply).callId;
			return msgReply.status;
		}
		return msg.status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#sgReleaseCall(int, int)
	 */
	public int sgReleaseCall(int resID, int callID) throws Exception
	{
		SGReleaseCallMessage msg = new SGReleaseCallMessage();
		msg.callId = callID;
		msg.resId = resID;

		return waitForReply(msg).status;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#shiftService(int, int, java.lang.String, java.lang.String, Ice.IntHolder)
	 */
	public boolean shiftService(int port, int callId, String callingNumber,
			String destNumber, IntHolder result)
	{
		return false;
	}

	int vgcpStatus2Result(int status)
	{
		switch (status)
		{
		case VGCPConstants.VGCP_GATEWAY_SUCCESS:
			return Constant.GATEWAY_SUCCESS;

		case VGCPConstants.VGCP_ERR_ASR_Exception:
			return Constant.ERR_ASR_EXCEPTION;

		case VGCPConstants.VGCP_ERR_ASR_Unavailable:
			return Constant.ERR_ASR_EXCEPTION;

		case VGCPConstants.VGCP_ERR_Invalid_CallID:
			return Constant.ERR_NoCall;

		case VGCPConstants.VGCP_ERR_Invalid_File:
			return Constant.ERR_InvalidFile;

		case VGCPConstants.VGCP_ERR_Invalid_ResID:
			return Constant.ERR_IVALID_DN;

		case VGCPConstants.VGCP_ERR_Invalid_Parameter:
			return Constant.ERR_INVLAIDARG;

		case VGCPConstants.VGCP_ERR_Media_Unmatch:
			return Constant.ERR_MediaUnmatch;

		case VGCPConstants.VGCP_ERR_No_Resource:
			return Constant.ERR_RES_ALREADYOPEN;

		case VGCPConstants.VGCP_ERR_Nocall_State:
			return Constant.ERR_NOCALL_STATE;

		case VGCPConstants.VGCP_ERR_TTS_Convert:
			return Constant.ERR_TTS_CONVERT;

		default:
			return Constant.ERR_GeneralError;
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#VGInitialize(com.channelsoft.slee.unifiedservicemanagement.ISysCfgData)
	 */
	public int VGInitialize(ISysCfgData sysCfgData)
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#VGInitialize(java.lang.String, int, java.lang.String, int, java.lang.String)
	 */
	public int VGInitialize(String mgIp, int mgPort, String sgIp, int sgPort,
			String monitorDnis)
	{
		this.sgIp = sgIp;
		this.sgPort = sgPort;

		int result = 0;

		try
		{
			result = initialize();
		}
		catch (Throwable e)
		{
			result = 0;
		}

		return result;
	}

	VGCPMessage waitForReply(VGCPMessage msg) throws Exception
	{
		CallerWaiter callWaiter = new CallerWaiter();

		try
		{
			synchronized (callerWaiters)
			{
				callerWaiters.put(msg.invokeId, callWaiter);
			}

			if (!client.sendPackage(msg))
			{
				msg.status = Constant.ERR_RPC;
				return msg;
			}

			try
			{
				callWaiter.waiter.await(operationTimeoutSeconds,
						TimeUnit.SECONDS);
				callWaiter.msg.status = vgcpStatus2Result(callWaiter.msg.status);
				return callWaiter.msg;
			}
			catch (TimeoutException te)
			{
				msg.status = Constant.ERR_TimeOut;
				return msg;
			}
		}
		finally
		{
			callWaiter.waiter.reset();
			synchronized (callerWaiters)
			{
				callerWaiters.remove(msg.invokeId);
			}
		}
	}

	@Override
	public int mgVoiceEdit(int resID, int callID, String szFileName, int rate,
			int beginTime, int keyCount, String szInteruptKeys,
			int max1stkeyTime, int timeBetweenKey) throws Exception
	{
		MGPlayFileMessage msg = new MGPlayFileMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.filename = szFileName;
		msg.iopParm = new MGIOPara();
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.iopParm.betweenKeyTimeout = timeBetweenKey;
		msg.iopParm.firstKeyTimeout = max1stkeyTime;
		msg.iopParm.keyLength = keyCount;
		msg.rate = rate;
		msg.begintime = beginTime;

		return waitForReply(msg).status;
	}

	@Override
	public int mgVoiceListEdit(int resID, int callID, int rate, int fileCount,
			String[] szVoxFileList, int voiceLib, int keyCount,
			String szInteruptKeys, int max1stkeyTime, int timeBetweenKey)
			throws Exception
	{
		MGPlayListMessage msg = new MGPlayListMessage();
		msg.resId = resID;
		msg.callId = callID;
		msg.iopParm = new MGIOPara();
		msg.iopParm.betweenKeyTimeout = timeBetweenKey;
		msg.iopParm.breakKeys = szInteruptKeys;
		msg.iopParm.firstKeyTimeout = max1stkeyTime;
		msg.iopParm.keyLength = keyCount;
		msg.rate = rate;
		msg.voiceLib = voiceLib;
		msg.files = new VecStr(szVoxFileList);

		return waitForReply(msg).status;
	}

	@Override
	public int mgAppendVideo(int resID, int callID, String[][] infoOnVideo)
			throws Exception
	{
		MGAppendVideoMessage msg = new MGAppendVideoMessage();
		msg.resId = resID;
		msg.callId = callID;
		int size = infoOnVideo.length;
		if (size > 0)
		{
			msg.ivParams = new MGInfoOnVideoParam[size];

			for (int i = 0; i < size; i++)
			{
				msg.ivParams[i] = new MGInfoOnVideoParam();
				msg.ivParams[i].nInfoID = Integer.parseInt(infoOnVideo[i][0]);
				msg.ivParams[i].nOpType = Integer.parseInt(infoOnVideo[i][1]);
				msg.ivParams[i].nInfoType = Integer.parseInt(infoOnVideo[i][2]);
				msg.ivParams[i].nCfgIndex = Integer.parseInt(infoOnVideo[i][3]);
				msg.ivParams[i].szInfo = infoOnVideo[i][4];
			}
		}
		return waitForReply(msg).status;
	}
}
