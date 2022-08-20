package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.TCPConnection;

public class MGAppendVideoMessage extends VGCPMessage
{
	int resId;

	int callId;

	MGInfoOnVideoParam[] ivParams = null;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
		throw new IllegalStateException();
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		messageId = VGCPConstants.VGCP_CMD_MG_PLAYINFOONVIDEO;

		int paramsNum = (ivParams == null) ? 0 : ivParams.length;
		int paramsLen = (paramsNum == 0) ? 0 : 4;
		for (int i = 0; i < paramsNum; i++)
		{
			paramsLen += ivParams[i].length();
		}
		messageLength = 4 * 4 + 2 * 4 + paramsLen;

		super.writePackage(conn);
		conn.writeInt(resId);
		conn.writeInt(callId);
		conn.writeInt(paramsNum);
		for (int i = 0; i < paramsNum; i++)
		{
			ivParams[i].writePackage(conn);
		}
	}

}
