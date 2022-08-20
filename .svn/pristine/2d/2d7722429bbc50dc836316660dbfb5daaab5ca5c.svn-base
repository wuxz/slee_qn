package com.channelsoft.slee.callagent.socket;

import java.io.IOException;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPClient;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class UVMGClient extends TCPClient
{
	CallAgentImpl callAgent;

	boolean hasReceivedMsg = false;

	String monitorDnis;

	public UVMGClient(String vgIp, int vgPort, String monitorDnis,
			CallAgentImpl callAgent)
	{
		super(vgIp, vgPort);

		this.monitorDnis = monitorDnis;
		this.callAgent = callAgent;
	}

	@Override
	protected void afterConnection() throws Exception
	{
		super.afterConnection();

		register();
		Log.wDebug(LogDef.id_0x1005000B, ip, port);

		callAgent.onConnect2Server();
	}

	@Override
	protected void beforeCloseConnection()
	{
		super.beforeCloseConnection();
		Log.wError(LogDef.id_0x1015001F);
		if (UnifiedServiceManagement.hasSnmpAgent)
		{
			SnmpAgentHandler.instance().addTrapData("CallAgent网络连接出现错误", 1);
		}
	}

	@Override
	protected void beforeConnect2Server()
	{
		super.beforeConnect2Server();
		Log.wDebug(LogDef.id_0x10050000_2, ip, port);
	}

	@Override
	protected void onConnectionError()
	{
		super.onConnectionError();
		Log.wDebug(LogDef.id_0x10250003, ip, port);
	}

	@Override
	protected void onError(Throwable e)
	{
		super.onError(e);
		Log.wException(LogDef.id_0x10150000, e);
	}

	@Override
	protected void onReadMessageError(IOException ioe)
	{
		super.onReadMessageError(ioe);
		Log.wError(LogDef.id_0x1015001B, ioe.getMessage());
	}

	@Override
	protected void onSendMessageError(IOException ioe)
	{
		super.onSendMessageError(ioe);
		Log.wError(LogDef.id_0x1015001C, ioe.getMessage());
	}

	@Override
	protected void processMessage(PackageMessage msg) throws Exception
	{
		callAgent.dispatchMessage((CallAgentMessage) msg);
	}

	@Override
	protected PackageMessage readMessage() throws Exception
	{
		int msgType = readInt();
		int msgLength = readInt();

		hasReceivedMsg = true;

		CallAgentMessage msg = null;

		switch (msgType)
		{
		case Constant.SOFTSWITCH_MESSAGE_REPLY_REGISTER:
		{
			msg = new RegisterReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REQUEST_INCOMINGCALL:
		{
			msg = new IncomingCall();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_SGEVENT:
		{
			msg = new SGEventReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_ACK_MAKECALL:
		{
			msg = new MakeCallAck();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_MAKECALL:
		{
			msg = new MakeCallReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_SWITCH:
		case Constant.SOFTSWITCH_MESSAGE_REPLY_DISSWITCH:
		{
			msg = new SwitchPortReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_MGEVENT:
		{
			msg = new MGEventReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF:
		{
			msg = new ReceiveDtmfReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_ASR:
		{
			msg = new ASRReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_BLINDCREATECONFERENCE:
		{
			msg = new BlindCreateConferenceReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_REPLY_STARTCONFERENCERECORD:
		{
			msg = new ConferenceRecordReply();
		}
			break;

		case Constant.SOFTSWITCH_MESSAGE_ACK_ANSWERCALL:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RELEASECALL:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEFAX:
		case Constant.SOFTSWITCH_MESSAGE_ACK_SENDFAX:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RECEIVEDTMF:
		case Constant.SOFTSWITCH_MESSAGE_ACK_SENDDTMF:
		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYVOICE:
		case Constant.SOFTSWITCH_MESSAGE_ACK_RECORDVOICE:
		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYTTS:
		case Constant.SOFTSWITCH_MESSAGE_ACK_PLAYLIST:
		case Constant.SOFTSWITCH_MESSAGE_ACK_ASR:
		case Constant.SOFTSWITCH_MESSAGE_ACK_STOPIO:

		{
			msg = new MessageAck();
		}
			break;

		}

		if (msg != null)
		{
			msg.messageType = msgType;
			msg.messageLength = msgLength;

			msg.readPackage(this);
		}
		else
		{
			Log.wError(LogDef.id_0x1015001A, msgType, msgLength);
		}

		return msg;
	}

	void register()
	{
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.monitorDnis = monitorDnis;
		sendPackage(registerRequest);
	}
}
