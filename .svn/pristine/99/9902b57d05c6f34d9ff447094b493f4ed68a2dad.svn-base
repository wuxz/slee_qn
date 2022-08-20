/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPClient;
import com.channelsoft.slee.callagent.V2_CallSession;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

/**
 * 负责处理从网关收到的事件。
 * 
 * @author wuxz
 */
class EventReceiver extends Thread
{
	SSR2Client client = null;

	LinkedBlockingQueue<VGCPMessage> msgs = new LinkedBlockingQueue<VGCPMessage>();

	EventReceiver(SSR2Client client)
	{
		this.client = client;
	}

	void dispatchMessage(VGCPMessage msg) throws Exception
	{
		msgs.add(msg);
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				VGCPMessage msg = msgs.take();
				client.processEvent(msg);
			}
			catch (Exception e)
			{
				client.onError(e);
				client.closeConnection();
			}
		}
	}
}

/**
 * 负责维护与SSR2的TCP连接，负责与SSR2交换TCP消息并处理消息。
 * 
 * @author wuxz
 */
public class SSR2Client extends TCPClient
{
	SSR2Agent agent = null;

	boolean isRegistered = false;

	EventReceiver receiver = null;

	/**
	 * @param ip
	 * @param port
	 */
	public SSR2Client(String ip, int port)
	{
		super(ip, port);

		receiver = new EventReceiver(this);
		receiver.start();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPClient#afterConnection()
	 */
	@Override
	protected void afterConnection() throws Exception
	{
		super.afterConnection();

		Log.wDebug(LogDef.id_0x1005000B, ip, port);

		V2_VGProxySession.Initialize();

		register();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPConnection#beforeCloseConnection()
	 */
	@Override
	protected void beforeCloseConnection()
	{
		super.beforeCloseConnection();

		Log.wError(LogDef.id_0x10150018);
		if (UnifiedServiceManagement.hasSnmpAgent)
		{
			SnmpAgentHandler.instance().addTrapData("CallAgent与VGProxy的连接出现错误",
					1);
		}

		isRegistered = false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPConnection#heartBeatMessage()
	 */
	@Override
	protected PackageMessage generateHeartBeatMessage()
	{
		return new OAMGetClientStateMessage();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPClient#onConnectionError()
	 */
	@Override
	protected void onConnectionError()
	{
		super.onConnectionError();

		Log.wDebug(LogDef.id_0x10250003, ip, port);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPConnection#onError(java.lang.Throwable)
	 */
	@Override
	protected void onError(Throwable e)
	{
		Log.wException(e);
	}

	void onRegisterReply(OAMClientRegisterReply msg)
	{
		if (msg.status == Constant.GATEWAY_SUCCESS)
		{
			Log.wDebug(LogDef.id_0x10050000_20);

			isRegistered = true;
		}
		else
		{
			Log.wError(LogDef.id_0x1015000C, msg.status);
		}
	}

	void processEvent(VGCPMessage msg) throws Exception
	{
		EventMessage event = (EventMessage) msg;
		if (agent != null)
		{
			agent.dispatchMessage(event);
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPConnection#processMessage(com.channelsoft.reusable.net.PackageMessage)
	 */
	@Override
	protected void processMessage(PackageMessage msg) throws Exception
	{
		// 在此线程中只处理与VGCP协议相关的消息。媒体与信令消息需要在另外的线程中处理。
		// 避免在处理信令消息时发新的媒体命令（比如在转外线中主线挂机时需要直接挂断被叫），导致通道堵塞。
		VGCPMessage vgMsg = (VGCPMessage) msg;

		switch (vgMsg.messageId)
		{
		case VGCPConstants.VGCP_REPLY_OAM_REGISTER:
		{
			onRegisterReply((OAMClientRegisterReply) vgMsg);
		}

			break;

		case VGCPConstants.VGCP_ACK_OAM_GETCLIENTSTATE:
		case VGCPConstants.VGCP_REPLY_OAM_GETCLIENTSTATE:
			break;

		case VGCPConstants.VGCP_EVT_SG_INCOMINGCALL:
		case VGCPConstants.VGCP_EVT_SG_CALLACCEPTED:
		case VGCPConstants.VGCP_EVT_SG_DAILED:
		case VGCPConstants.VGCP_EVT_SG_CALLCONNECTED:
		case VGCPConstants.VGCP_EVT_SG_CALLDISCONNECTED:
		case VGCPConstants.VGCP_EVT_SG_CALLRELEASED:
		case VGCPConstants.VGCP_EVT_SG_CALLALERTING:
		case VGCPConstants.VGCP_EVT_SG_LINE_IN_SERVICE:
		case VGCPConstants.VGCP_EVT_SG_LINE_OUT_SERVICE:
		case VGCPConstants.VGCP_EVT_SG_LINE_DOWN:
		case VGCPConstants.VGCP_EVT_SG_LINE_START:
		{
			EventMessage event = (EventMessage) msg;

			Log.wDebug(LogDef.id_0x10050013, event.resId, event.callId,
					event.messageId);
			receiver.dispatchMessage(vgMsg);
		}

			break;
		case VGCPConstants.VGCP_EVT_MG_TDX_GETDTMFS:
		case VGCPConstants.VGCP_EVT_MG_TDX_ASR:
		case VGCPConstants.VGCP_EVT_MG_TDX_PLAY:
		case VGCPConstants.VGCP_EVT_MG_TDX_RECORD:
		case VGCPConstants.VGCP_EVT_MG_TDX_SENDDTMFS:
		case VGCPConstants.VGCP_EVT_MG_TFX_SEND:
		case VGCPConstants.VGCP_EVT_MG_TFX_RECV:
		case VGCPConstants.VGCP_EVT_MG_TFX_FAIL:
		{
			EventMessage event = (EventMessage) msg;

			Log.wDebug(LogDef.id_0x10050012, event.resId, event.callId,
					event.messageId, event.reason);

			receiver.dispatchMessage(vgMsg);
		}

			break;
		case VGCPConstants.VGCP_EVT_SYS_SSR_SERVICEDOWN:
		case VGCPConstants.VGCP_EVT_SYS_SSR_SERVICESTART:
		case VGCPConstants.VGCP_EVT_SYS_VG_SERVICEDOWN:
		case VGCPConstants.VGCP_EVT_SYS_VG_SERVICESTART:
		{
			V2_VGProxySession.Initialize();
		}

			break;
		default:
		{
			if (vgMsg.messageId >> 24 == VGCPConstants.VGCP_MSG_STYLE_ACK)
			{
				Log.wDebug(LogDef.id_0x10050000_21, VGCPConstants
						.msg2String(vgMsg.messageId));
			}
			else if (vgMsg.messageId >> 24 == VGCPConstants.VGCP_MSG_STYLE_REPLY)
			{
				boolean canProcess = agent.onReply(vgMsg);

				if ((vgMsg.messageId == VGCPConstants.VGCP_REPLY_SG_BLINDMAKECALL)
						&& (vgMsg.status == VGCPConstants.VGCP_GATEWAY_SUCCESS))
				{
					SGBlindMakecallReply reply = (SGBlindMakecallReply) vgMsg;

					// 选线成功，清除原有事件。
					V2_CallSession.clearAgentMessage(reply.resId);
				}

				if (canProcess)
				{
					Log.wDebug(LogDef.id_0x10050000_22, VGCPConstants
							.msg2String(vgMsg.messageId));
				}
				else
				{
					Log.wWarn(LogDef.id_0x10050026, VGCPConstants
							.msg2String(vgMsg.messageId));
				}
			}
			else
			{
				Log.wDebug(LogDef.id_0x10050000_23, VGCPConstants
						.msg2String(vgMsg.messageId));
			}
		}
		}
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.reusable.net.TCPConnection#readMessage()
	 */
	@Override
	protected PackageMessage readMessage() throws Exception
	{
		int invokeId;
		int messageId;
		int messageLength = 0;
		int status;

		messageLength = readInt();
		messageId = readInt();
		invokeId = readInt();
		status = readInt();

		VGCPMessage msg = null;

		switch (messageId)
		{
		case VGCPConstants.VGCP_REPLY_OAM_REGISTER:
			msg = new OAMClientRegisterReply();
			break;

		case VGCPConstants.VGCP_REPLY_SG_BLINDMAKECALL:
			msg = new SGBlindMakecallReply();
			break;

		case VGCPConstants.VGCP_REPLY_RM_BLINDCREATECONF:
			msg = new RMBlindCreateConfReply();
			break;

		case VGCPConstants.VGCP_EVT_SG_INCOMINGCALL:
			msg = new IncomingCallEventMessage();
			break;

		case VGCPConstants.VGCP_EVT_SG_DAILED:
			msg = new DailedEventMessage();
			break;

		case VGCPConstants.VGCP_EVT_MG_TDX_GETDTMFS:
			msg = new GetDtmfEventMessage();
			break;

		case VGCPConstants.VGCP_EVT_MG_TDX_ASR:
			msg = new ASREventMessage();
			break;

		case VGCPConstants.VGCP_EVT_SG_CALLACCEPTED:
		case VGCPConstants.VGCP_EVT_SG_CALLCONNECTED:
		case VGCPConstants.VGCP_EVT_SG_CALLDISCONNECTED:
		case VGCPConstants.VGCP_EVT_SG_CALLRELEASED:
		case VGCPConstants.VGCP_EVT_SG_CALLALERTING:
		case VGCPConstants.VGCP_EVT_SG_LINE_IN_SERVICE:
		case VGCPConstants.VGCP_EVT_SG_LINE_OUT_SERVICE:
		case VGCPConstants.VGCP_EVT_SG_LINE_DOWN:
		case VGCPConstants.VGCP_EVT_SG_LINE_START:
		case VGCPConstants.VGCP_EVT_SYS_SSR_SERVICEDOWN:
		case VGCPConstants.VGCP_EVT_SYS_SSR_SERVICESTART:
		case VGCPConstants.VGCP_EVT_SYS_VG_SERVICEDOWN:
		case VGCPConstants.VGCP_EVT_SYS_VG_SERVICESTART:
		case VGCPConstants.VGCP_EVT_MG_TDX_PLAY:
		case VGCPConstants.VGCP_EVT_MG_TDX_RECORD:
		case VGCPConstants.VGCP_EVT_MG_TDX_SENDDTMFS:
		case VGCPConstants.VGCP_EVT_MG_TFX_SEND:
		case VGCPConstants.VGCP_EVT_MG_TFX_RECV:
		case VGCPConstants.VGCP_EVT_MG_TFX_FAIL:
			msg = new EventMessage();
			break;

		default:
			msg = new VGCPMessage();
			for (int i = 0; i < messageLength - 4 * 4; i++)
			{
				readByte();
			}
			msg.messageLength = messageLength;
			msg.messageId = messageId;
			msg.invokeId = invokeId;
			msg.status = status;
			return msg;

		}

		if (messageLength > VGCPMessage.VGCP_HEADER_LENGTH)
		{
			msg.readPackage(this);
		}
		msg.messageLength = messageLength;
		msg.messageId = messageId;
		msg.invokeId = invokeId;
		msg.status = status;

		return msg;
	}

	void register() throws Exception
	{
		OAMClientRegisterMessage msg = new OAMClientRegisterMessage();
		msg.version = 0x00010001;
		msg.clientId = agent.getSleeName();
		msg.pwd = agent.getSleePassword();

		sendPackage(msg);
	}

	public void setEventConsumer(SSR2Agent agent)
	{
		this.agent = agent;
	}

	@Override
	public void writeBoolean(boolean value) throws IOException
	{
		writeInt(value ? 1 : 0);
	}

	public void writeString(byte[] buffer, int length) throws IOException
	{
		writeInt(length);
		write(buffer);
		for (int i = 0; i < length - buffer.length; i++)
		{
			writeByte(0);
		}
	}
}
