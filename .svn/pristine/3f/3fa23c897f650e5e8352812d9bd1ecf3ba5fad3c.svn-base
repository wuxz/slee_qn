package com.channelsoft.slee.snmpagent;

/**
 * @author liyan
 */
public class SnmpData
{
	String content;

	final int SNMP_Trap_Type = 6;

	int trapType = SNMP_Trap_Type;

	int specificCode;

	SnmpData()
	{
	}

	SnmpData(int code)
	{
		// trap的特定代码,和企业OID一起拼成trap的OID,以后扩展此字段来增加不同的trap报文
		specificCode = code;

	}

	public void setTrapType(int t)
	{
		trapType = t;
	}

	public int getTrapType()
	{
		return trapType;
	}

	public int getSpecificCode()
	{
		return specificCode;
	}

}
