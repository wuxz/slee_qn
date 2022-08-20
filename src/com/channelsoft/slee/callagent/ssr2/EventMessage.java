/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.VGProxy.MGEvent;
import com.channelsoft.VGProxy.SGEvent;
import com.channelsoft.reusable.net.TCPConnection;
import com.channelsoft.slee.util.Constant;

/**
 * @author wuxz
 */
public class EventMessage extends VGCPMessage
{
	static int messageType2SGEventId(int messageId)
	{
		switch (messageId)
		{
		case VGCPConstants.VGCP_EVT_SG_CALLCONNECTED:
			return Constant.SG_CALLCONNECTED;

		case VGCPConstants.VGCP_EVT_SG_CALLDISCONNECTED:
			return Constant.SG_CALLDISCONNECTED;

		case VGCPConstants.VGCP_EVT_SG_CALLRELEASED:
			return Constant.SG_CALLRELEASED;

		case VGCPConstants.VGCP_EVT_SG_CALLALERTING:
			return Constant.SG_CALLALERTING;

		case VGCPConstants.VGCP_EVT_SG_LINE_DOWN:
			return Constant.SG_LINE_DOWN;

		case VGCPConstants.VGCP_EVT_SG_LINE_START:
			return Constant.SG_LINE_START;

		case VGCPConstants.VGCP_EVT_SG_LINE_IN_SERVICE:
			return Constant.SG_LINE_IN_SERVICE;

		case VGCPConstants.VGCP_EVT_SG_LINE_OUT_SERVICE:
			return Constant.SG_LINE_OUT_SERVICE;

		case VGCPConstants.VGCP_EVT_SG_INCOMINGCALL:
			return Constant.SG_INCOMINGCALL;

		case VGCPConstants.VGCP_EVT_SG_DAILED:
			return Constant.SG_DAILED;

		case VGCPConstants.VGCP_EVT_SG_CALLACCEPTED:
			return Constant.SG_CALLACCEPTED;

		default:
			return Constant.SG_FATALERROR;
		}
	}

	static int reason2SGEventData(int reason)
	{
		switch (reason)
		{
		case VGCPConstants.VGCP_REASON_DESTBUSY:
			return Constant.REASON_DESTBUSY;

		case VGCPConstants.VGCP_REASON_DESTNOANSWER:
			return Constant.REASON_DESTNOANSWER;

		case VGCPConstants.VGCP_REASON_INVLAIDNUM:
			return Constant.REASON_INVLAIDNUM;

		case VGCPConstants.VGCP_REASON_DESTUNKNOWN:
			return Constant.REASON_DESTUNKNOWN;

		case VGCPConstants.VGCP_REASON_VOICE_DETECTED:
			return Constant.REASON_VOICE_DETECTED;

		case VGCPConstants.VGCP_REASON_POWER_OFF:
			return Constant.REASON_POWER_OFF;

		case VGCPConstants.VGCP_REASON_OUT_AREA:
			return Constant.REASON_OUT_AREA;

		case VGCPConstants.VGCP_REASON_NULL_NUMBER:
			return Constant.REASON_NUMBER_NULL;

		case VGCPConstants.VGCP_REASON_REFUSE:
			return Constant.REASON_REFUSE;

		case VGCPConstants.VGCP_REASON_FAXTONE_DETECTED:
			return Constant.REASON_DESTFAXTONE;

		case VGCPConstants.VGCP_REASON_DESTNOTFAX:
			return Constant.REASON_DESTNOTFAX;

		case VGCPConstants.VGCP_REASON_UNSUPPORTED_MEDIA:
			return Constant.REASON_UNSUPPORTED_MEDIA;
			
		case VGCPConstants.VGCP_REASON_MEDIA_NEGOTIATE:
			return Constant.REASON_MEDIA_NEGOTIATE;
			
		case VGCPConstants.VGCP_REASON_NORMAL:
		case VGCPConstants.VGCP_REASON_SUBSCRIBERFREE:
		case VGCPConstants.VGCP_REASON_CALLWAITING:
		case VGCPConstants.VGCP_REASON_CFB:
		case VGCPConstants.VGCP_REASON_CFNR:
		case VGCPConstants.VGCP_REASON_CFUC:
		case VGCPConstants.VGCP_REASON_CFUR:
			return Constant.REASON_NORMAL;

		default:
			return Constant.ERR_GeneralError;
		}
	}

	static int messageType2MGEventId(int messageId)
	{
		switch (messageId)
		{
		case VGCPConstants.VGCP_EVT_MG_TDX_PLAY:
			return Constant.MG_TDX_PLAY;
		case VGCPConstants.VGCP_EVT_MG_TDX_RECORD:
			return Constant.MG_TDX_RECORD;
		case VGCPConstants.VGCP_EVT_MG_TDX_SENDDTMFS:
			return Constant.MG_TDX_PLAY;
		case VGCPConstants.VGCP_EVT_MG_TDX_GETDTMFS:
			return Constant.MG_TDX_GETDIGIT;
		case VGCPConstants.VGCP_EVT_MG_TDX_ASR:
			return Constant.MG_TDX_ASR;
		case VGCPConstants.VGCP_EVT_MG_TFX_SEND:
			return Constant.MG_TFX_SEND;
		case VGCPConstants.VGCP_EVT_MG_TFX_RECV:
			return Constant.MG_TFX_RECV;
		case VGCPConstants.VGCP_EVT_MG_TFX_FAIL:
			return Constant.MG_TFX_FAIL;
		default:
			return Constant.MG_FAIL;
		}
	}

	static int reason2MGEventData(int reason)
	{
		switch (reason)
		{
		case VGCPConstants.VGCP_MGREASON_TM_EOD:
			return Constant.MGREASON_TM_EOD;
		case VGCPConstants.VGCP_MGREASON_TM_DTMF:
			return Constant.MGREASON_TM_DIGIT;
		case VGCPConstants.VGCP_MGREASON_TM_MAXDTMF:
			return Constant.MGREASON_TM_MAXDTMF;
		case VGCPConstants.VGCP_MGREASON_TM_MAXTIME:
			return Constant.MGREASON_TM_MAXTIME;
		case VGCPConstants.VGCP_MGREASON_TM_DISCONNECT:
			return Constant.MGREASON_TM_DISCONNECT;
		case VGCPConstants.VGCP_MGREASON_TM_USRSTOP:
			return Constant.MGREASON_TM_USRSTOP;
		case VGCPConstants.VGCP_MGREASON_TM_INVALIDFILE:
			return Constant.MGREASON_TM_INVALIDFILE;
		case VGCPConstants.VGCP_MGREASON_ASR_RECGNIZED:
			return Constant.MGREASON_ASR_RECGNIZED;
		case VGCPConstants.VGCP_MGREASON_ASR_NO_SPEECH:
			return Constant.MGREASON_ASR_NO_SPEECH;
		case VGCPConstants.VGCP_MGREASON_ASR_TOOMUCH_SPEECH:
			return Constant.MGREASON_ASR_TOOMUCH_SPEECH;
		case VGCPConstants.VGCP_MGREASON_ASR_REC_SLOW:
			return Constant.MGREASON_ASR_REC_SLOW;
		case VGCPConstants.VGCP_MGREASON_ASR_SPEECH_TOO_EARLY:
			return Constant.MGREASON_ASR_SPEECH_TOO_EARLY;
		case VGCPConstants.VGCP_MGREASON_ASR_REJECT:
			return Constant.MGREASON_ASR_REJECT;
		case VGCPConstants.VGCP_MGREASON_ASR_EXCEPTION:
			return Constant.MGREASON_ASR_EXCEPTION;
		case VGCPConstants.VGCP_MGREASON_ASR_MEMRECORD:
			return Constant.MGREASON_ASR_MEMRECORD;
		case VGCPConstants.VGCP_MGREASON_ARS_PLAYPROMPT:
			return Constant.MGREASON_ARS_PLAYPROMPT;
		case VGCPConstants.VGCP_MGREASON_ARS_ABORT:
			return Constant.MGREASON_ARS_ABORT;
		default:
			return Constant.ERR_GeneralError;
		}
	}

	int callId;

	int reason;

	int resId;

	public SGEvent convert2SGEvent(SGEvent sgEvent)
	{
		sgEvent.EventID = messageType2SGEventId(messageId);
		sgEvent.CallID = callId;
		sgEvent.ResID = resId;
		sgEvent.EventData = reason2SGEventData(reason);

		return sgEvent;
	}

	public MGEvent convert2MGEvent(MGEvent mgEvent)
	{
		mgEvent.EventID = messageType2MGEventId(messageId);
		mgEvent.CallID = callId;
		mgEvent.ResID = resId;
		mgEvent.Reason = reason2MGEventData(reason);

		return mgEvent;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#readPackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		callId = conn.readInt();
		resId = conn.readInt();
		reason = conn.readInt();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.ssr2.VGCPMessage#writePackage(com.channelsoft.reusable.net.TCPConnection)
	 */
	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}
}
