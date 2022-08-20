package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.reusable.net.PackageMessage;
import com.channelsoft.reusable.net.TCPConnection;

public class MGInfoOnVideoParam implements PackageMessage
{
	int nInfoID; // ����ʾ��Ϣ��ʶ(ȡֵ��0��ʼ������������Ļ��ʶ������ͬ��Logo��ʶ������ͬ����Ļ��ʶ��Logo��ʶ������ͬ)

	int nOpType; // �������ͣ�0=��ʾ��Ļ/Logo��1=�޸���Ļ/Logo��2=�ر���Ļ/Logo

	int nInfoType; // ����ʾ��Ϣ���ͣ�1ΪLogo, 2Ϊ��Ļ

	int nCfgIndex; // nCfgIndex��ʹ�õ�ģ���ţ���opTypeΪ2ʱ��ֵ��Ч

	String szInfo; // logoʱΪlogo�ļ�·������ĻʱΪ��Ļ����
	
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
