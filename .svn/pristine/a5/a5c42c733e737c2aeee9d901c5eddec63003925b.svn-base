/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class MGASRMessage extends VGCPMessage
{
	int resId;

	int callId;

	MGIOPara iopParm;

	int rate;

	VecStr files;

	String grammar;

	int noSpeechTimeout;

	int acceptScore;

	int resultNum;

	int timeoutBetweenWord;

	int voiceLib;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		messageId = VGCPConstants.VGCP_CMD_MG_ASR;

		byte[] grammarBuffer = grammar.getBytes();
		int grammarLength = calcStringLength(grammarBuffer);
		messageLength = 4 * 4 + 2 * 4 + iopParm.length() + 4 + files.length()
				 + 6 * 4 + grammarLength;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		iopParm.writePackage(conn);
		conn.writeInt(rate);
		files.writePackage(conn);
		client.writeString(grammarBuffer, grammarLength);
		conn.writeInt(noSpeechTimeout);
		conn.writeInt(acceptScore);
		conn.writeInt(resultNum);
		conn.writeInt(timeoutBetweenWord);
		conn.writeInt(voiceLib);
	}

}
