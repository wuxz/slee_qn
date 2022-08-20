package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;

public class MGInfoOnVideoParam implements PackageMessage
{
	int nInfoID; // 所显示信息标识(取值从0开始递增，所有字幕标识不可相同、Logo标识不可相同，字幕标识和Logo标识可以相同)

	int nOpType; // 操作类型：0=显示字幕/Logo；1=修改字幕/Logo；2=关闭字幕/Logo

	int nInfoType; // 所显示信息类型，1为Logo, 2为字幕

	int nCfgIndex; // nCfgIndex，使用的模板编号，当opType为2时该值无效

	String szInfo; // logo时为logo文件路径，字幕时为字幕内容
	
	byte[] szInfoBuffer = null;
	
	int szInfoLength;

	@Override
	public void readPackage(TCPConnection conn) throws Exception
	{
	}

	@Override
	public void writePackage(TCPConnection conn) throws Exception
	{
		SSR2Client client = (SSR2Client) conn;

		conn.writeInt(nInfoID);
		conn.writeInt(nOpType);
		conn.writeInt(nInfoType);
		conn.writeInt(nCfgIndex);

		if (szInfoBuffer != null)
		{
			client.writeString(szInfoBuffer, szInfoLength);
		}
	}
	public int length()
	{
		parse();
		return 5 * 4 + szInfoLength;
	}
	
	private void parse()
	{
		if (szInfoBuffer == null)
		{
			szInfo = (szInfo == null)?"":szInfo;
			szInfoBuffer = szInfo.getBytes();
			szInfoLength = VGCPMessage.calcStringLength(szInfoBuffer);
		}
	}

}
