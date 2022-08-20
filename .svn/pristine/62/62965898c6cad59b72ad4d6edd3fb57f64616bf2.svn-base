package com.channelsoft.slee.callagent.ice;

import java.io.UnsupportedEncodingException;

import Ice.IntHolder;

import com.channelsoft.VGProxy.MGEventHolder;
import com.channelsoft.VGProxy.SGEventHolder;
import com.channelsoft.VGProxy.VGServicePrx;
import com.channelsoft.VGProxy.VGServicePrxHelper;
import com.channelsoft.slee.callagent.V2_CallSession;
import com.channelsoft.slee.callagent.V2_VGProxy;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.util.Constant;

public class V2_VGProxyImp implements V2_VGProxy
{
	static VGServicePrx vgproxy;

	Ice.Communicator ic = null;

	String monitorDnis;

	String sgIp;

	int sgPort;

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.V2_VGProxy#attachMedia(int)
	 */
	public int attachMedia(int callID)
	{
		return Constant.ERR_GeneralError;
	}

	int GetMgEvt(int timeout, MGEventHolder event)
	{
		return vgproxy.GetMgEvt(timeout, event);
	}

	int GetSgEvt(int timeout, SGEventHolder event)
	{
		return vgproxy.GetSgEvt(timeout, event);
	}

	/**
	 * @return
	 */
	synchronized public int initialize()
	{
		try
		{
			if (ic != null)
			{
				ic.destroy();
			}
		}
		catch (Throwable e)
		{
		}

		V2_VGProxySession.Initialize();

		ic = null;

		Log.wDebug(LogDef.id_0x10050000_2, sgIp, sgPort);

		ic = Ice.Util.initialize(new String[] { "" });
		Ice.ObjectPrx base = ic.stringToProxy("VGProxy:tcp -p " + sgPort
				+ " -h " + sgIp);
		vgproxy = VGServicePrxHelper.checkedCast(base);
		vgproxy.VGInitialize(1, monitorDnis, null);

		Log.wDebug(LogDef.id_0x1005000B, sgIp, sgPort);

		return 1;
	}

	public boolean isInitialized()
	{
		return (null != vgproxy);
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
		return vgproxy.mgASR(resID, callID, maxKeys, szInteruptKeys, rate,
				fileCount, szVoxFileList, szGrammar, noSpeechTimeout,
				acceptScore, resultNum, timeoutBetweenWord, voiceLib);
	}

	public int mgFlushBuffer(int resID, int callID)
	{
		return vgproxy.mgFlushBuffer(resID, callID);
	}

	public int mgPlayList(int resID, int callID, String szInteruptKeys,
			int rate, int fileCount, String[] szVoxFileList, int voiceLib)
	{
		for (int i = 0; i < fileCount; i++)
		{
			String fileName = "";
			try
			{
				fileName = new String(szVoxFileList[i].getBytes("GB2312"),
						"ISO-8859-1");
			}
			catch (UnsupportedEncodingException e)
			{
				Log.wException(LogDef.id_0x10100000, e);
			}
			szVoxFileList[i] = fileName;
		}
		return vgproxy.mgPlayList(resID, callID, szInteruptKeys, rate,
				fileCount, szVoxFileList, voiceLib);
	}

	public int mgPlayTTS(int resID, int callID, String szInteruptKeys,
			int type, String szText, int voiceLib, int rate)
	{
		String textStr = "";
		try
		{
			textStr = new String(szText.getBytes("GB2312"), "ISO-8859-1");
		}
		catch (UnsupportedEncodingException e)
		{
			Log.wException(LogDef.id_0x10100000, e);
		}
		return vgproxy.mgPlayTTS(resID, callID, szInteruptKeys, type, textStr,
				voiceLib, rate);
	}

	public int mgPlayVoice(int resID, int callID, String szFileName,
			String szInteruptKeys, int rate, int beginTime)
	{
		String fileNameStr = "";
		try
		{
			if (szFileName == null)
			{
				fileNameStr = "";
			}
			else
			{
				fileNameStr = new String(szFileName.getBytes("GB2312"),
						"ISO-8859-1");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			Log.wException(LogDef.id_0x10100000, e);
		}
		return vgproxy.mgPlayVoice(resID, callID, fileNameStr, szInteruptKeys,
				rate, beginTime);
	}

	public int mgReceiveDTMF(int resID, int callID, int keyCount,
			String szInteruptKeys, int max1stkeyTime, int timeBetweenKey)
	{
		return vgproxy.mgReceiveDTMF(resID, callID, keyCount, szInteruptKeys,
				max1stkeyTime, timeBetweenKey);
	}

	public int mgReceiveFax(int resID, int callID, String szFileName)
	{
		String fileNameStr = "";

		try
		{
			if (szFileName == null)
			{
				fileNameStr = "";
			}
			else
			{
				fileNameStr = new String(szFileName.getBytes("GB2312"),
						"ISO-8859-1");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			Log.wException(LogDef.id_0x10100000, e);
		}

		return vgproxy.mgReceiveFax(resID, callID, fileNameStr);
	}

	public int mgRecordVoice(int resID, int callID, String szFileName,
			String szInteruptKeys, int maxTime, int rate)
	{
		String fileNameStr = "";
		try
		{
			fileNameStr = new String(szFileName.getBytes("GB2312"),
					"ISO-8859-1");
		}
		catch (UnsupportedEncodingException e)
		{
			Log.wException(LogDef.id_0x10100000, e);
		}
		return vgproxy.mgRecordVoice(resID, callID, fileNameStr,
				szInteruptKeys, maxTime, rate);
	}

	public int mgSendDTMF(int resID, int callID, String szSignals)
	{
		return vgproxy.mgSendDTMF(resID, callID, szSignals);
	}

	public int mgSendFax(int resID, int callID, String szFileName)
	{
		String fileNameStr = "";

		try
		{
			if (szFileName == null)
			{
				fileNameStr = "";
			}
			else
			{
				fileNameStr = new String(szFileName.getBytes("GB2312"),
						"ISO-8859-1");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			Log.wException(LogDef.id_0x10100000, e);
		}

		return vgproxy.mgSendFax(resID, callID, fileNameStr);
	}

	public int mgStopIO(int resID, int callID)
	{
		return vgproxy.mgStopIO(resID, callID);
	}

	public int rmBlindCloseConf(int nResID, int callID, int confID)
	{
		return vgproxy.rmBlindCloseConf(confID);
	}

	public int rmBlindCreateConf(int resID1, int resID2, int callID,
			IntHolder pnConfID, boolean isVideoCall)
	{
		return vgproxy.rmBlindCreateConf(resID1, resID2, pnConfID);
	}

	public int rmRouteTwoRes(int res1ID, int res2ID, int mode,
			String videoUrl1, String videoUrl2)
	{
		return vgproxy.rmRouteTwoRes(res1ID, res2ID, mode);
	}

	public int rmStartConferrenceRecord(int resID, int callID,
			String strRecordFileName)
	{
		return vgproxy.rmStartConferrenceRecord(resID, callID,
				strRecordFileName);
	}

	public int rmUnrouteTwoRes(int res1ID, int res2ID, int mode)
	{
		return vgproxy.rmUnrouteTwoRes(res1ID, res2ID, mode);
	}

	public int sgAnswerCall(int resID, int callID)
	{
		return vgproxy.sgAnswerCall(resID, callID);
	}

	public int sgBlindMakeCall(int callID, String szCallerNum,
			String szOriCallerNum, String szCalledNum, String szOriCalledNum,
			int timeout, IntHolder resID, IntHolder callID2, boolean isVediaCall)
	{
		int ret = vgproxy.sgBlindMakeCall(szCallerNum, szCalledNum, timeout,
				resID, callID2);
		if (ret == Constant.GATEWAY_SUCCESS)
		{
			// 选线成功，清除原有事件。
			V2_CallSession.clearAgentMessage(resID.value);
		}
		return ret;
	}

	public int sgReleaseCall(int resID, int callID)
	{
		return vgproxy.sgReleaseCall(resID, callID);
	}

	public boolean shiftService(int port, int callId, String callingNumber,
			String destNumber, IntHolder result)
	{
		return false;
	}

	public int VGInitialize(ISysCfgData sysCfgData)
	{
		try
		{
			// String mgIp = sysCfgData.getUvmgServerIp();
			// int mgPort = sysCfgData.getUvmgServerPort();
			sgIp = sysCfgData.getUvmgServerIp();
			sgPort = sysCfgData.getUvmgServerPort();
			monitorDnis = "*";

			int result = 0;
			try
			{
				result = initialize();
			}
			catch (Throwable e)
			{
				Log.wException(LogDef.id_0x10150000, e);

				Log.wDebug(LogDef.id_0x10250003, this.sgIp, this.sgPort);

				result = 0;
			}

			V2_SGEventReceiver sgr = new V2_SGEventReceiver(this);
			sgr.start();
			V2_MGEventReceiver mgr = new V2_MGEventReceiver(this);
			mgr.start();

			Log.wTrace(LogDef.id_0x10010004, sysCfgData.getUvmgServerIp(),
					sysCfgData.getUvmgServerPort());

			return result;
		}
		catch (Throwable e)
		{
			Log.wException(LogDef.id_0x10150000, e);
			Log.wDebug(LogDef.id_0x10250003, sysCfgData.getUvmgServerIp(),
					sysCfgData.getUvmgServerPort());
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
		return Constant.ERR_GeneralError;
	}

}
