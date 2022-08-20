/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

/**
 * @author dragon.huang
 */
public class RMAddChannel2VoiceMixerMessage extends VGCPMessage
{
	int resId;

	int callId;

	String recordFile;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;
		messageId = VGCPConstants.VGCP_CMD_RM_ADDCHNL2VOICEMIXER;

		byte[] recordFileBuffer = recordFile.getBytes();
		int recordFileLength = calcStringLength(recordFileBuffer);
		messageLength = 4 * 4 + 3 * 4 + recordFileLength;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		client.writeString(recordFileBuffer, recordFileLength);
	}

}
