/**
 * 
 */
package com.channelsoft.slee.callagent.ucce;

import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.channelmanager.AtsSGEvent;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

/**
 * @author wuxz
 */
public class CallAgentImpl implements CallAgent
{
	/**
	 * 负责连接UCCE的线程。在连接不成功后重试，直到连接成功。
	 * 
	 * @author wuxz
	 */
	class UCCEClientConnector extends Thread
	{
		@Override
		public void run()
		{
			while (true)
			{
				try
				{
					ChannelFuture connectFuture = bootstrap
							.connect(new InetSocketAddress(ucceIp, uccePort));

					Channel channel = connectFuture.awaitUninterruptibly()
							.getChannel();

					handler = channel.getPipeline()
							.get(UCCEClientHandler.class);
					if (handler.isConnected())
					{
						Log.wDebug(LogDef.id_0x1005000B, ucceIp, uccePort);

						return;
					}

					Log.wDebug(LogDef.id_0x10250003, ucceIp, uccePort);

					try
					{
						Thread.sleep(4000);
					}
					catch (Exception e1)
					{
					}
				}
				catch (Exception e)
				{
					Log.wDebug(LogDef.id_0x10250003, ucceIp, uccePort);

					try
					{
						Thread.sleep(5000);
					}
					catch (Exception e1)
					{
					}
				}
			}
		}
	}

	ClientBootstrap bootstrap = new ClientBootstrap(
			new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
					Executors.newCachedThreadPool()));

	UCCEClientHandler handler = null;

	String ucceIp;

	int uccePort;

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsAdjustSpeed(int, int, int)
	 */
	@Override
	public int atsAdjustSpeed(int port, int callId, int adjment)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsAdjustSpeed(
				port, callId, adjment);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsAdjustVolume(int, int, int)
	 */
	@Override
	public int atsAdjustVolume(int port, int callId, int adjment)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsAdjustVolume(
				port, callId, adjment);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsAnswerCall(int, int, int)
	 */
	@Override
	public int atsAnswerCall(int port, int callId, int isBilling)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsAnswerCall(port,
				callId, isBilling);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsASR(int, int, java.lang.String, int, int, com.channelsoft.reusable.util.StringWrapper, java.lang.String[], java.lang.String, int, int, int, float)
	 */
	@Override
	public int atsASR(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String[] voiceFileList,
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsASR(port,
				callId, interruptKeys, rate, fileCount, result, voiceFileList,
				grammar, noSpeechTimeout, acceptScore, resultNum,
				timeoutBetweenWord, -1, "", -1, -1);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsASR2(int, int, java.lang.String, int, int, com.channelsoft.reusable.util.StringWrapper, java.lang.String[], java.lang.String, int, int, int, float, int, java.lang.String, int, int)
	 */
	@Override
	public int atsASR2(int port, int callId, String interruptKeys, int rate,
			int fileCount, StringWrapper result, String[] voiceFileList,
			String grammar, int noSpeechTimeout, int acceptScore,
			int resultNum, float timeoutBetweenWord, int DTMF_dtmfCount,
			String DTMF_endFlag, int DTMF_timeoutSecond, int DTMF_betweenTimeout)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsASR(port,
				callId, interruptKeys, rate, fileCount, result, voiceFileList,
				grammar, noSpeechTimeout, acceptScore, resultNum,
				timeoutBetweenWord, DTMF_dtmfCount, DTMF_endFlag,
				DTMF_timeoutSecond, DTMF_betweenTimeout);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsClearDTMFBuffer(int, int)
	 */
	@Override
	public int atsClearDTMFBuffer(int port, int callId)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsClearDTMFBuffer(
				port, callId);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsCloseConf(int, int)
	 */
	@Override
	public int atsCloseConf(int port, int callId)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsCloseConf(port,
				callId);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsDisSwitchTwoPort(int, int, int, int)
	 */
	@Override
	public int atsDisSwitchTwoPort(int port1, int callId1, int port2,
			int reserved)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsDisSwitchTwoPort(port1, callId1, port2, reserved);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsGetCallInfo(int, int, com.channelsoft.reusable.util.StringWrapper, com.channelsoft.reusable.util.StringWrapper, com.channelsoft.reusable.util.StringWrapper, com.channelsoft.reusable.util.StringWrapper, com.channelsoft.reusable.util.StringWrapper, com.channelsoft.reusable.util.BooleanWrapper)
	 */
	@Override
	public int atsGetCallInfo(int port, int callId, StringWrapper ani,
			StringWrapper dnis, StringWrapper oriAni, StringWrapper oriDnis,
			StringWrapper reserved, BooleanWrapper isVideoCall)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsGetCallInfo(port, callId, ani, dnis, oriAni, oriDnis,
						reserved, isVideoCall);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsGetOtherPartyCallSn(int, int)
	 */
	@Override
	public int atsGetOtherPartyCallSn(int port, int callId)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsGetOtherPartyCallSn(port, callId);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsGetOtherPartyTrunkID(int, int)
	 */
	@Override
	public int atsGetOtherPartyTrunkID(int port, int callId)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsGetOtherPartyTrunkID(port, callId);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsGetSGEvent()
	 */
	@Override
	public AtsSGEvent atsGetSGEvent()
	{
		try
		{
			if (handler == null)
			{
				Thread.sleep(1000);
				return null;
			}

			return UCCEClientHandler.sgEvents.take();
		}
		catch (InterruptedException e)
		{
			Log.wException(e);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsInitialize(com.channelsoft.slee.unifiedservicemanagement.ISysCfgData)
	 */
	@Override
	public int atsInitialize(ISysCfgData sysCfgData)
	{
		bootstrap.setPipelineFactory(new UCCEClientPipelineFactory());

		startConnector(sysCfgData);

		return 0;
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsMakeCall(com.channelsoft.reusable.util.IntWrapper, java.lang.String, java.lang.String, com.channelsoft.reusable.util.IntWrapper, int, int, int, boolean)
	 */
	@Override
	public int atsMakeCall(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsMakeCall(port,
				callingNumber, destNumber, callId, timeout, reserved,
				transactionId, isVideoCall);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsPlayList(int, int, java.lang.String, int, int, java.lang.String[], int)
	 */
	@Override
	public int atsPlayList(int port, int callId, String interruptKeys,
			int rate, int fileCount, String[] voiceFileList, int voiceLib)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsPlayList(port, callId, interruptKeys, rate, fileCount,
						voiceFileList, voiceLib);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsPlayTTS(int, int, java.lang.String, int, java.lang.String, int)
	 */
	@Override
	public int atsPlayTTS(int port, int callId, String interruptKeys, int type,
			String text, int voiceLib)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsPlayTTS(port,
				callId, interruptKeys, type, text, voiceLib);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsPlayVoice(int, int, java.lang.String, java.lang.String, int, com.channelsoft.reusable.util.IntWrapper, java.util.Calendar)
	 */
	@Override
	public int atsPlayVoice(int port, int callId, String fileName,
			String interruptKeys, int rate, IntWrapper secondsPlayed,
			Calendar lastInteractionTime)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsPlayVoice(port,
				callId, fileName, interruptKeys, rate, secondsPlayed,
				lastInteractionTime);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsPlayVoiceAsync(int, int, java.lang.String, java.lang.String, int)
	 */
	@Override
	public int atsPlayVoiceAsync(int port, int callId, String fileName,
			String interruptKeys, int rate)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsPlayVoiceAsync(
				port, callId, fileName, interruptKeys, rate);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsQueryCallState(int, int, com.channelsoft.reusable.util.IntWrapper)
	 */
	@Override
	public int atsQueryCallState(int port, int callId, IntWrapper callStatus)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsQueryCallState(
				port, callId, callStatus);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsReceiveDTMF(int, int, com.channelsoft.reusable.util.StringWrapper, int, java.lang.String, int, int)
	 */
	@Override
	public int atsReceiveDTMF(int port, int callId, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsReceiveDTMF(
				port, callId, buffer, keyCount, interruptKeys, maxTime,
				betweenTime);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsReceiveFax(int, int, java.lang.String)
	 */
	@Override
	public int atsReceiveFax(int port, int callId, String fileName)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsReceiveFax(port,
				callId, fileName);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsRecordVoice(int, int, java.lang.String, char, int, int)
	 */
	@Override
	public int atsRecordVoice(int port, int callId, String fileName,
			char endFlag, int maxTime, int rate)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsRecordVoice(
				port, callId, fileName, endFlag, maxTime, rate);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsReleaseCall(int, int, int)
	 */
	@Override
	public int atsReleaseCall(int port, int callId, int reserved)
	{
		return atsReleaseCall(port, callId, reserved, true);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsReleaseCall(int, int, int, boolean)
	 */
	@Override
	public int atsReleaseCall(int port, int callId, int reserved, boolean wait)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsReleaseCall(
				port, callId, reserved, wait);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsRetrieveCall(int, int, int)
	 */
	@Override
	public int atsRetrieveCall(int port, int callId, int reserved)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsRetrieveCall(
				port, callId, reserved);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsSendFax(int, int, java.lang.String)
	 */
	@Override
	public int atsSendFax(int port, int callId, String fileName)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsSendFax(port,
				callId, fileName);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsSendSignals(int, int, java.lang.String)
	 */
	@Override
	public int atsSendSignals(int port, int callId, String signals)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsSendSignals(
				port, callId, signals);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsSingleStepConference(int, int, java.lang.String, java.lang.String, int, boolean, int, int, boolean)
	 */
	@Override
	public int atsSingleStepConference(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean isVideoCall)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsSingleStepConference(port, callId, callingNumber,
						destNumber, timeout, routeMediaFirst, reserved,
						transactionId, isVideoCall);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsSingleStepTransfer(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, boolean, int, int, boolean, int, boolean, boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public int atsSingleStepTransfer(int port, int callId,
			String callingNumber, String oriCallingNumber, String destNumber,
			String oriDestNumber, int timeout, boolean routeMediaFirst,
			int reserved, int transactionId, boolean createConf, int mode,
			boolean reverseRoute, boolean isVideoCall, String videoUrl1,
			String videoUrl2)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsSingleStepTransfer(port, callId, callingNumber, destNumber,
						timeout, routeMediaFirst, reserved, transactionId,
						createConf, mode, reverseRoute, isVideoCall);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsStartConferencRecording(int, int, java.lang.String, int)
	 */
	@Override
	public int atsStartConferencRecording(int port, int callId,
			String recordFileName, int reserved)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsStartConferencRecording(port, callId, recordFileName,
						reserved);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsSwitchTwoPort(int, int, int, int)
	 */
	@Override
	public int atsSwitchTwoPort(int port1, int callId1, int port2, int mode)
	{
		return atsSwitchTwoPort(port1, callId1, port2, mode, "", "");
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsSwitchTwoPort(int, int, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public int atsSwitchTwoPort(int port1, int callId1, int port2, int reserved,
			String videoUrl1, String videoUrl2)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsSwitchTwoPort(
				port1, callId1, port2, reserved);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsVoiceEdit(int, int, java.lang.String, int, com.channelsoft.reusable.util.IntWrapper, java.util.Calendar, com.channelsoft.reusable.util.StringWrapper, int, java.lang.String, int, int)
	 */
	@Override
	public int atsVoiceEdit(int port, int callId, String fileName, int rate,
			IntWrapper secondsPlayed, Calendar lastInteractionTime,
			StringWrapper buffer, int keyCount, String interruptKeys,
			int maxTime, int betweenTime)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsVoiceEdit(port,
				callId, fileName, rate, secondsPlayed, lastInteractionTime,
				buffer, keyCount, interruptKeys, maxTime, betweenTime);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#atsVoiceListEdit(int, int, int, int, java.lang.String[], int, com.channelsoft.reusable.util.StringWrapper, int, java.lang.String, int, int)
	 */
	@Override
	public int atsVoiceListEdit(int port, int callId, int rate, int fileCount,
			String[] voiceFileList, int voiceLib, StringWrapper buffer,
			int keyCount, String interruptKeys, int maxTime, int betweenTime)
	{
		return handler == null ? Constant.ERR_RPC : handler.atsVoiceListEdit(
				port, callId, rate, fileCount, voiceFileList, voiceLib, buffer,
				keyCount, interruptKeys, maxTime, betweenTime);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#attachCall(int, int)
	 */
	@Override
	public void attachCall(int port, int callID)
	{
		handler.atsAttachCall(port, callID);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#attachOtherParty(int, int, int, int)
	 */
	@Override
	public void attachOtherParty(int port, int callID, int otherPort,
			int otherCallId)
	{
		handler.atsAttachOtherParty(port, callID, otherPort, otherCallId);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.callagent.CallAgent#blindCreateConference(int, int, int, boolean)
	 */
	@Override
	public int blindCreateConference(int port1, int callId, int port2,
			boolean isVideoCall)
	{
		return handler == null ? Constant.ERR_RPC : handler
				.atsBlindCreateConference(port1, callId, port2, isVideoCall);
	}

	/**
	 * 与UCCE的连接断开。
	 */
	public void onConnectionClosed()
	{
		Log.wError(LogDef.id_0x10150018);
		if (UnifiedServiceManagement.hasSnmpAgent)
		{
			SnmpAgentHandler.instance().addTrapData("CallAgent与VGProxy的连接出现错误",
					1);
		}

		startConnector(null);
	}

	private void startConnector(ISysCfgData sysCfgData)
	{
		UCCEClientConnector connector = new UCCEClientConnector();

		ucceIp = UnifiedServiceManagement.configData.getUvmgServerIp();
		uccePort = UnifiedServiceManagement.configData.getUvmgServerPort();

		Log.wDebug(LogDef.id_0x10050000_2, ucceIp, uccePort);

		connector.start();
	}

	@Override
	public int atsAppendVideo(int port, int callId, String[][] infoOnVideo)
	{
		return Constant.ERR_GeneralError;
	}

	@Override
	public int atsCallRecordRing(IntWrapper port, String callingNumber,
			String oriCallingNumber, String destNumber, String oriDestNumber,
			IntWrapper callId, int timeout, int reserved, int transactionId,
			boolean isVideoCall, String fileName, char endFlag, int maxTime,
			int rate)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
