package com.channelsoft.slee.callagent.ccod;

import com.channelsoft.CCODServices.CCODResultT;
import com.channelsoft.CCODServices.MediaEventCauseT;
import com.channelsoft.CCODServices.MediaEventTypeT;
import com.channelsoft.CCODServices.SignalEventTypeT;
import com.channelsoft.CCODServices.SignalFailedReasonT;
import com.channelsoft.CCODServices.TTSVoiceLibT;
import com.channelsoft.slee.util.Constant;

public class V2_ConstTransform
{
	static int turnCCODResultT2SleeResult(CCODResultT ccodR)
	{
		switch (ccodR)
		{
		case ResSuccess:
			return Constant.GATEWAY_SUCCESS;
		case ResConnIDNotExist:
		case ResInvalidState:
		case ResNotAttachedWithMedia:
		case ResSessionIDNotExist:
			return Constant.ERR_NoCall;
		case ResInvalidUri:
			return Constant.ERR_InvalidFile;
		case ResMepIDNotExist:
			return Constant.ERR_INVALID_RESID;
		case ResNotEnoughResource:
			return Constant.ERR_RES_ALREADYOPEN;

		default:
			return Constant.ERR_GeneralError;

		}
	}

	static TTSVoiceLibT turnASRVoiceLib(int voiceLib)
	{
		TTSVoiceLibT tvlt = null;
		if ((voiceLib == Constant.TTS_VOICE_LIB_MAN)
				|| (voiceLib == Constant.TTS_VOICE_LIB_MAN2))
		{
			tvlt = TTSVoiceLibT.TTSVoiceLibMan;
		}
		else if ((voiceLib == Constant.TTS_VOICE_LIB_WOMAN)
				|| (voiceLib == Constant.TTS_VOICE_LIB_WOMAN1))
		{
			tvlt = TTSVoiceLibT.TTSVoiceLibWoman;
		}
		else
		{
			tvlt = TTSVoiceLibT.TTSVoiceLibWoman;// 其余情况用女生读
		}
		return tvlt;
	}
	
	/**
	 * 转换CCOD到SLEE的媒体事件ID
	 * 
	 * @param event
	 * @return
	 */
	static int turnMediaEventTypeT2MGEventID(MediaEventTypeT event)
	{
		switch (event)
		{
		case MgEvtFailed:
		{
			return Constant.MG_FAIL;
		}
		case MgEvtFaxFailed:
		{
			return Constant.MG_TFX_FAIL;
		}
		case MgEvtGotDtmf:
		{
			return Constant.MG_TDX_GETDIGIT;
		}
		case MgEvtPlayEnd:
		{
			return Constant.MG_TDX_PLAY;
		}
		case MgEvtRecordEnd:
		{
			return Constant.MG_TDX_RECORD;
		}
		case MgEvtRecvFaxEnd:
		{
			return Constant.MG_TFX_RECV;
		}
		case MgEvtSendFaxEnd:
		{
			return Constant.MG_TFX_SEND;
		}
		default:
		{
			return Constant.MG_SUCCESS;
		}
		}
	}

	/**
	 * 转换CCOD到SLEE的媒体事件原因
	 * 
	 * @param event
	 * @return
	 */
	static int turnMediaEventTypeT2MGEventReason(MediaEventCauseT event)
	{
		switch (event)
		{
		case MecTmDigit:
		{
			return Constant.MGREASON_TM_DIGIT;
		}
		case MecTmDisconnect:
		{
			return Constant.MGREASON_TM_DISCONNECT;
		}
		case MecTmEod:
		{
			return Constant.MGREASON_TM_EOD;
		}
		case MecTmInvalidFile:
		{
			return Constant.MGREASON_TM_INVALIDFILE;
		}
		case MecTmMaxDtmf:
		{
			return Constant.MGREASON_TM_MAXDTMF;
		}
		case MecTmMaxTime:
		{
			return Constant.MGREASON_TM_MAXTIME;
		}
		case MecTmUserStop:
		{
			return Constant.MGREASON_TM_USRSTOP;
		}
		default:
		{
			return Constant.ERR_GeneralError;
		}
		}
	}
	
	static int turnSignalEventTypeT2SGEventID(SignalEventTypeT sett)
	{
		switch (sett)
		{
		case SgEvtConnected:
		{
			return Constant.SG_CALLCONNECTED;
		}
		case SgEvtDisconnected:
		{
			return Constant.SG_CALLDISCONNECTED;
		}
		case SgEvtFailed:
		{
			return Constant.SG_CALLDISCONNECTED;
		}
		case SgEvtLocalAlerting:
		{
			return Constant.SG_CALLALERTING;
		}
		case SgEvtRemoteAlerting:
		{
			return Constant.SG_CALLALERTING;
		}
		default:
		{
			return Constant.ERR_GeneralError;
		}
		}
	}

	/**
	 * CCOD到SLEE信令事件原因的映射
	 * 
	 * @param sfrt
	 * @return
	 */
	static int turnSignalFailedReasonT2SGEventData(SignalFailedReasonT sfrt)
	{
		switch (sfrt)
		{
		case ReasonCallDestBusy:
		{
			return Constant.REASON_DESTBUSY;
		}
		case ReasonCallDestFaxtone:
		{
			return Constant.REASON_DESTFAXTONE;
		}
		case ReasonCallDestInvalid:
		{
			return Constant.REASON_INVLAIDNUM;
		}
		case ReasonCallDestNotAnswer:
		{
			return Constant.REASON_DESTNOANSWER;
		}
		case ReasonCallDestNotFax:
		{
			return Constant.REASON_DESTNOTFAX;
		}
		case ReasonCallDestUnknown:
		{
			return Constant.REASON_DESTUNKNOWN;
		}
		case ReasonSuccess:
		{
			return Constant.REASON_DESTNOTFAX;
		}
		default:
		{
			return Constant.REASON_DESTUNKNOWN;
		}
		}
	}

}
