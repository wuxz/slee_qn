package com.channelsoft.slee.callagent;

import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;

/**
 * 封装与底层网关交互的接口。
 * <p>
 * 由于底层网关提供的接口类型不同，呼叫控制有多种实现方式，差异由VGProxy的实现类封装。
 * 
 * @author wuxz
 */
public interface V2_VGProxy
{
	/**
	 * 如果当前没有媒体资源，则创建媒体资源。
	 * 
	 * @return
	 */
	public int attachMedia(int callID);

	/**
	 * 是否初始化成功。如果不成功，则所有对网关的操作都会失败。
	 * 
	 * @return
	 */
	public boolean isInitialized();

	public int mgAdjustSpeed(int nResID, int nCallID, int adjment)
			throws Exception;

	public int mgAdjustVolume(int nResID, int nCallID, int adjment)
			throws Exception;

	public int mgAppendVideo(int nResID, int nCallID, String[][] infoOnVideo)
			throws Exception;

	public int mgASR(int nResID, int nCallID, int nMaxKeys,
			String szInteruptKeys, int nRate, int nFileCount,
			String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout,
			int nAcceptScore, int nResultNum, float fTimeoutBetweenWord,
			int nVoiceLib) throws Exception;

	public int mgFlushBuffer(int nResID, int nCallID) throws Exception;

	public int mgPlayList(int nResID, int nCallID, String szInteruptKeys,
			int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib)
			throws Exception;

	public int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys,
			int nType, String szText, int nVoiceLib, int nRate)
			throws Exception;

	public int mgPlayVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nRate, int nBeginTime) throws Exception;

	public int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount,
			String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey)
			throws Exception;

	public int mgVoiceListEdit(int nResID, int nCallID, int nRate,
			int nFileCount, String[] szVoxFileList, int nVoiceLib,
			int nKeyCount, String szInteruptKeys, int nMax1stkeyTime,
			int nTimeBetweenKey) throws Exception;

	public int mgVoiceEdit(int nResID, int nCallID, String szFileName,
			int nRate, int nBeginTime, int nKeyCount, String szInteruptKeys,
			int nMax1stkeyTime, int nTimeBetweenKey) throws Exception;

	public int mgReceiveFax(int nResID, int nCallID, String szFileName)
			throws Exception;

	public int mgRecordVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nMaxTime, int nRate) throws Exception;

	public int mgSendDTMF(int nResID, int nCallID, String szSignals)
			throws Exception;

	public int mgSendFax(int nResID, int nCallID, String szFileName)
			throws Exception;

	public int mgStopIO(int nResID, int nCallID) throws Exception;

	public int rmBlindCloseConf(int nResID, int nCallID, int nConfID)
			throws Exception;

	public int rmBlindCreateConf(int nResID1, int nResID2, int nCallID,
			Ice.IntHolder pnConfID, boolean isVideoCall) throws Exception;

	public int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode,
			String videoUrl1, String videoUrl2) throws Exception;

	public int rmStartConferrenceRecord(int nResID, int nCallID,
			String strRecordFileName) throws Exception;

	public int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode)
			throws Exception;

	public int sgAnswerCall(int nResID, int nCallID) throws Exception;

	public int sgBlindMakeCall(int callID, String szCallerNum,
			String szOriCallerNum, String szCalledNum, String szOriCalledNum,
			int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID,
			boolean isVideoCall) throws Exception;

	public int sgReleaseCall(int nResID, int nCallID) throws Exception;

	public boolean shiftService(int port, int callId, String callingNumber,
			String destNumber, Ice.IntHolder result);

	/**
	 * 根据系统配置进行初始化。
	 * 
	 * @param sysCfgData
	 * @return
	 * @throws Exception
	 */
	public int VGInitialize(ISysCfgData sysCfgData) throws Exception;
}
