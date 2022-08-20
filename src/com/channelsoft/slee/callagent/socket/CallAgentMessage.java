package com.channelsoft.slee.callagent.socket;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

class AnswerCallRequest extends CallAgentMessage
{
	int callId;

	int isBilling;

	int port;

	public AnswerCallRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_ANSWERCALL;
		messageLength = 20;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeInt(isBilling);
	}
}

class ASRReply extends CallAgentMessage
{
	int callId;

	int eventId;

	int reason;

	int resId;

	String result;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 224)
		{
			throw new Exception("CallAgent收到错误的信息包AsrReply，原因：包头长度错误");
		}

		resId = conn.readInt();
		eventId = conn.readInt();
		reason = conn.readInt();
		callId = conn.readInt();
		result = conn.readString(200);

		Log
				.wDebug(LogDef.id_0x10050021, resId, callId, eventId, reason,
						result);
	}
}

class ASRRequest extends CallAgentMessage
{
	int acceptScore;

	int callId;

	int fileCount;

	String grammar;

	String interruptKeys;

	int noSpeechTimeout;

	int port;

	int rate;

	int resultNum;

	int timeoutBetweenWord;

	String voiceFileList[];

	int voiceLib;

	public ASRRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_ASR;
		messageLength = 332;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		byte buffers[][] = new byte[voiceFileList.length][];
		for (int i = 0; i < voiceFileList.length; i++)
		{
			buffers[i] = null;
			if (voiceFileList[i] != null)
			{
				buffers[i] = voiceFileList[i].getBytes();
				messageLength += buffers[i].length + 1;
			}
		}

		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeInt(rate);
		conn.writeInt(voiceLib);
		conn.writeInt(noSpeechTimeout);
		conn.writeInt(acceptScore);
		conn.writeInt(resultNum);
		conn.writeInt(timeoutBetweenWord * 10);
		conn.writeString(interruptKeys, 32);
		conn.writeString(grammar, Constant.FILENAME_LEN);
		conn.writeInt(fileCount);

		for (int i = 0; i < voiceFileList.length; i++)
		{
			if (buffers[i] != null)
			{
				conn.write(buffers[i]);
				conn.writeByte(0);
			}
		}
	}
}

class BlindCreateConferenceReply extends CallAgentMessage
{
	int callId;

	int port1;

	int result;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 20)
		{
			throw new Exception(
					"CallAgent收到错误的信息包BindCreateConferenceReply，原因：包头长度错误");
		}

		port1 = conn.readInt();
		result = conn.readInt();
		callId = conn.readInt();

		Log.wDebug(LogDef.id_0x1005001E, port1, callId, result);
	}
}

class BlindCreateConferenceRequest extends CallAgentMessage
{
	int callId1;

	int port1;

	int port2;

	public BlindCreateConferenceRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_BLINDCREATECONFERENCE;
		messageLength = 20;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port1);
		conn.writeInt(callId1);
		conn.writeInt(port2);
	}
}

public class CallAgentMessage implements PackageMessage
{
	public int messageLength;

	public int messageType;

	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 8)
		{
			throw new Exception("CallAgentMessage，原因：包头长度错误");
		}

		messageType = conn.readInt();
		messageLength = conn.readInt();
	}

	public void writePackage(TCPConnection conn) throws Exception
	{
		conn.writeInt(messageType);
		conn.writeInt(messageLength);
	}
}

class CallAgentMessageWrapper
{
	CallAgentMessage event = null;

	public CallAgentMessageWrapper()
	{

	}

	public CallAgentMessageWrapper(CallAgentMessage event)
	{
		this.event = event;
	}
}

class ConferenceRecordReply extends CallAgentMessage
{
	int callId;

	int port;

	int result;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 20)
		{
			throw new Exception(
					"CallAgent收到错误的信息包ConferenceRecordReply，原因：包头长度错误");
		}

		port = conn.readInt();
		callId = conn.readInt();
		result = conn.readInt();

		Log.wDebug(LogDef.id_0x10050022, port, callId, result);
	}
}

class ConferenceRecordRequest extends CallAgentMessage
{
	int callId;

	String fileName;

	int port;

	public ConferenceRecordRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_STARTCONFERENCERECORD;
		messageLength = 272;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeString(fileName, Constant.FILENAME_LEN);
	}
}

class DisSwitchPortRequest extends SwitchPortRequest
{
	public DisSwitchPortRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_DISSWITCH;
	}
}

class IncomingCall extends CallAgentMessage
{
	String ani;

	int callId;

	String dnis;

	int eventData;

	int eventId;

	String oriAni;

	String oriDnis;

	int portId;

	String reserved;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 184)
		{
			throw new Exception("CallAgent收到错误的信息包IncomingCall，原因：包头长度错误");
		}

		portId = conn.readInt();
		eventId = conn.readInt();
		eventData = conn.readInt();
		callId = conn.readInt();
		ani = conn.readString(Constant.NUMBER_LEN);
		dnis = conn.readString(Constant.NUMBER_LEN);
		oriAni = conn.readString(Constant.NUMBER_LEN);
		oriDnis = conn.readString(Constant.NUMBER_LEN);
		reserved = conn.readString(Constant.NUMBER_LEN);

		Log.wDebug(LogDef.id_0x10050018, portId, callId, ani, dnis, oriAni,
				oriDnis);
	}
}

class MakeCallAck extends CallAgentMessage
{
	int callId;

	int port;

	int reserved;

	int result;

	int transactionId;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 28)
		{
			throw new Exception("CallAgent收到错误的信息包MakeCallAck，原因：包头长度错误");
		}

		transactionId = conn.readInt();
		port = conn.readInt();
		callId = conn.readInt();
		result = conn.readInt();
		reserved = conn.readInt();

		Log.wDebug(LogDef.id_0x10050019, transactionId, port, callId, result);
	}
}

class MakeCallReply extends CallAgentMessage
{
	int callId;

	int port;

	int result;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 20)
		{
			throw new Exception("CallAgent收到错误的信息包MakeCallReply，原因：包头长度错误");
		}

		port = conn.readInt();
		callId = conn.readInt();
		result = conn.readInt();

		Log.wDebug(LogDef.id_0x1005001B, port, callId, result);
	}
}

class MakeCallRequest extends CallAgentMessage
{
	String calledNumber;

	String callingNumber;

	int reserved;

	int timeout;

	int transactionId;

	public MakeCallRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_MAKECALL;
		messageLength = 84;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(transactionId);
		conn.writeInt(timeout);
		conn.writeString(callingNumber, Constant.NUMBER_LEN);
		conn.writeString(calledNumber, Constant.NUMBER_LEN);
		conn.writeInt(reserved);
	}
}

class MessageAck extends CallAgentMessage
{
	static String translateMessageAck(int type)
	{
		switch (type)
		{
		case Constant.SOFTSWITCH_MESSAGE_ACK_ANSWERCALL:
			return "AnswerCallAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_RELEASECALL:
			return "ReleaseCallAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEFAX:
			return "ReceiveFaxAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_SENDFAX:
			return "SendFaxAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEDTMF:
			return "ReceiveDtmfAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_SENDDTMF:
			return "SendDtmfAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYVOICE:
			return "PlayVoiceAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_RECORDVOICE:
			return "RecordVoiceAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYLIST:
			return "PlayVoiceListAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYTTS:
			return "PlayTTSAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_ASR:
			return "AsrAck";

		case Constant.SOFTSWITCH_MESSAGE_ACK_STOPIO:
			return "StopIOAck";
		}

		return "UnknownMessageAck[" + type + "]";
	}

	int callId;

	int port;

	int result;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 20)
		{
			throw new Exception("CallAgent收到错误的信息包MessageAck，原因：包头长度错误");
		}

		port = conn.readInt();
		callId = conn.readInt();
		result = conn.readInt();

		Log.wDebug(LogDef.id_0x1005001A, port, callId,
				translateMessageAck(messageType), result);
	}
}

class MGEventReply extends CallAgentMessage
{
	int callId;

	int eventId;

	int reason;

	int resId;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 24)
		{
			throw new Exception("CallAgent收到错误的信息包MGEventReply，原因：包头长度错误");
		}

		resId = conn.readInt();
		eventId = conn.readInt();
		reason = conn.readInt();
		callId = conn.readInt();

		Log.wDebug(LogDef.id_0x1005001F, resId, callId, eventId, reason);
	}
}

class PlayListRequest extends CallAgentMessage
{
	int callId;

	int fileCount;

	String interruptKeys;

	int port;

	int rate;

	String voiceFileList[];

	int voiceLib;

	public PlayListRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_PLAYLIST;
		messageLength = 60;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		byte buffers[][] = new byte[voiceFileList.length][];
		for (int i = 0; i < voiceFileList.length; i++)
		{
			buffers[i] = null;
			if (voiceFileList[i] != null)
			{
				buffers[i] = voiceFileList[i].getBytes();
				messageLength += buffers[i].length + 1;
			}
		}

		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeString(interruptKeys, 32);
		conn.writeInt(rate);
		conn.writeInt(voiceLib);
		conn.writeInt(fileCount);

		for (int i = 0; i < voiceFileList.length; i++)
		{
			if (buffers[i] != null)
			{
				conn.write(buffers[i]);
				conn.writeByte(0);
			}
		}
	}
}

class PlayTTSRequest extends CallAgentMessage
{
	int callId;

	String interruptKeys;

	int port;

	String text;

	int type;

	int voiceLib;

	public PlayTTSRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_PLAYTTS;
		messageLength = 56;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		byte buffer[] = text.getBytes();
		messageLength += (buffer.length + 1);

		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeString(interruptKeys, 32);
		conn.writeInt(type);
		conn.writeInt(voiceLib);

		conn.write(buffer);
		conn.writeByte(0);
	}
}

class PlayVoiceRequest extends CallAgentMessage
{
	int callId;

	String fileName;

	String interruptKeys;

	int port;

	int rate;

	public PlayVoiceRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_PLAYVOICE;
		messageLength = 308;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeInt(rate);
		conn.writeString(interruptKeys, 32);
		conn.writeString(fileName, Constant.FILENAME_LEN);
	}
}

class ReceiveDtmfReply extends CallAgentMessage
{
	int callId;

	String dtmf;

	int eventId;

	int reason;

	int resId;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 56)
		{
			throw new Exception("CallAgent收到错误的信息包ReceiveDtmfReply，原因：包头长度错误");
		}

		resId = conn.readInt();
		eventId = conn.readInt();
		reason = conn.readInt();
		callId = conn.readInt();
		dtmf = conn.readString(32);

		Log.wDebug(LogDef.id_0x10050020, resId, callId, eventId, reason, dtmf);
	}
}

class ReceiveDTMFRequest extends CallAgentMessage
{
	int callId;

	int iddTime;

	String interruptKeys;

	int keyCount;

	int maxTime;

	int port;

	public ReceiveDTMFRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_RECEIVEDTMF;
		messageLength = 60;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeInt(keyCount);
		conn.writeInt(maxTime);
		conn.writeInt(iddTime);
		conn.writeString(interruptKeys, 32);
	}
}

class ReceiveFaxRequest extends CallAgentMessage
{
	int callId;

	String fileName;

	int port;

	public ReceiveFaxRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_RECEIVEFAX;
		messageLength = 272;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeString(fileName, 256);
	}
}

class RecordVoiceRequest extends CallAgentMessage
{
	int callId;

	String fileName;

	String interruptKeys;

	int maxTime;

	int port;

	int rate;

	public RecordVoiceRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_RECORDVOICE;
		messageLength = 312;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeInt(rate);
		conn.writeInt(maxTime);
		conn.writeString(interruptKeys, 32);
		conn.writeString(fileName, Constant.FILENAME_LEN);
	}
}

class RegisterReply extends CallAgentMessage
{
	String logIp;

	int logPort;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if ((messageType != Constant.SOFTSWITCH_MESSAGE_REPLY_REGISTER)
				|| (messageLength != 76))
		{
			throw new Exception("RegisterReply，原因：包头长度错误");
		}

		logIp = conn.readString(64);
		logPort = conn.readInt();

		Log.wDebug(LogDef.id_0x10050016, logIp, logPort);
	}
}

class RegisterRequest extends CallAgentMessage
{
	String monitorDnis;

	public RegisterRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_REGISTER;
		messageLength = 72;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeString(monitorDnis, 64);
	}
}

class ReleaseCallRequest extends CallAgentMessage
{
	int callId;

	int port;

	int reserved;

	public ReleaseCallRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_RELEASECALL;
		messageLength = 20;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeInt(reserved);
	}
}

class SendDtmfRequest extends CallAgentMessage
{
	int callId;

	String dtmf;

	int port;

	public SendDtmfRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_SENDDTMF;
		messageLength = 48;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeString(dtmf, 32);
	}
}

class SendFaxRequest extends CallAgentMessage
{
	int callId;

	String fileName;

	int port;

	public SendFaxRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_SENDFAX;
		messageLength = 272;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
		conn.writeString(fileName, Constant.FILENAME_LEN);
	}
}

class SGEventReply extends CallAgentMessage
{
	int callId;

	int eventData;

	int eventId;

	int portId;

	int reserved;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 28)
		{
			throw new Exception("CallAgent收到错误的信息包SGEventReply，原因：包头长度错误");
		}

		portId = conn.readInt();
		eventId = conn.readInt();
		eventData = conn.readInt();
		callId = conn.readInt();
		reserved = conn.readInt();

		Log.wDebug(LogDef.id_0x10050017, portId, callId, eventId, eventData);
	}
}

class StopIORequest extends CallAgentMessage
{
	int callId;

	int port;

	public StopIORequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_STOPIO;
		messageLength = 16;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port);
		conn.writeInt(callId);
	}
}

class SwitchPortReply extends CallAgentMessage
{
	int port1;

	int reserved;

	int result;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		if (messageLength != 20)
		{
			throw new Exception("CallAgent收到错误的信息包SwitchPortReply，原因：包头长度错误");
		}

		port1 = conn.readInt();
		result = conn.readInt();
		reserved = conn.readInt();

		if (messageType == Constant.SOFTSWITCH_MESSAGE_REPLY_SWITCH)
		{
			Log.wDebug(LogDef.id_0x1005001C, port1, result);
		}
		else
		{
			Log.wDebug(LogDef.id_0x1005001D, port1, result);
		}
	}
}

class SwitchPortRequest extends CallAgentMessage
{
	int port1;

	int port2;

	int reserved;

	public SwitchPortRequest()
	{
		super();
		messageType = Constant.SOFTSWITCH_MESSAGE_REQUEST_SWITCH;
		messageLength = 20;
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		super.writePackage(conn);
		conn.writeInt(port1);
		conn.writeInt(port2);
		conn.writeInt(reserved);
	}
}
