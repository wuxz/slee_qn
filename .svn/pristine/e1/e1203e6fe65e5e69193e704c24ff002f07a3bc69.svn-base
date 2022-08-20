/**
 * 
 */
package com.channelsoft.slee.jni;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.util.Constant;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

/**
 * @author wuxz
 */
public class SmsClient
{
	static IntByReference handleHolder = new IntByReference();

	static SmsClientLib INSTANCE = null;

	static String serverIp;

	static boolean connect2SmsServer()
	{
		if (handleHolder.getValue() != 0)
		{
			return true;
		}

		if ((handleHolder.getValue() == 0) && (serverIp != null))
		{
			INSTANCE.SMS_ConnectToServer(serverIp, handleHolder);
		}

		return handleHolder.getValue() != 0;
	}

	public static int getSmsMsg(boolean isUssdMsg, StringWrapper spHolder,
			StringWrapper phoneNumberHolder, IntWrapper msgLengthHolder,
			StringWrapper msgHolder, StringWrapper serviceTypeHolder,
			IntWrapper tpIdHolder, IntWrapper tpUdhiHolder,
			StringWrapper linkIdHolder, int timeout)
	{
		try
		{
			byte[] msgId = JniFunction.string2Buffer(null, 41);
			byte[] spNumber = JniFunction.string2Buffer(null, 22);
			byte[] phone = JniFunction.string2Buffer(null, 22);
			byte[] serviceType = JniFunction.string2Buffer(null, 11);
			IntByReference msgLengthHold = new IntByReference();
			byte[] msg = JniFunction.string2Buffer(null, 161);
			ByteByReference tpIdHold = new ByteByReference();
			ByteByReference tpUdhiHold = new ByteByReference();
			byte[] linkId = JniFunction.string2Buffer(null, 21);

			int error = Constant.ERR_GeneralError;
			if (isUssdMsg)
			{
			}
			else
			{
				for (int i = 1; i <= 3; i++)
				{
					if (!connect2SmsServer())
					{
						return Constant.ERR_GeneralError;
					}

					error = INSTANCE.SMS_GetSmsMsg(handleHolder.getValue(), "",
							msgId, spNumber, phone, serviceType, msgLengthHold,
							msg, tpIdHold, tpUdhiHold, linkId, timeout);
					if (error == Constant.ERR_RPC)
					{
						INSTANCE.SMS_DisconnectFromServer(handleHolder);
						handleHolder.setValue(0);
						Thread.sleep(200); // 重新连接前等待一段时间
					}
					else
					{
						break;
					}
				}

				if (Constant.ERR_Success == error)
				{
					spHolder.value = Native.toString(spNumber);
					phoneNumberHolder.value = Native.toString(phone);
					msgLengthHolder.value = msgLengthHold.getValue();
					msgHolder.value = Native.toString(msg);
					serviceTypeHolder.value = Native.toString(serviceType);
					tpIdHolder.value = tpIdHold.getValue();
					tpUdhiHolder.value = tpUdhiHold.getValue();
					linkIdHolder.value = Native.toString(linkId);
				}
			}

			return error;
		}
		catch (Exception e)
		{
			return Constant.ERR_GeneralError;
		}
	}

	public static int getSmsReport(boolean isUssdMsg,
			StringWrapper msgIdHolder, StringWrapper phoneNumberHolder,
			IntWrapper stateHolder, IntWrapper errorCodeHolder, int timeout)
	{
		try
		{
			byte[] msgId = JniFunction.string2Buffer(null, 41);
			byte[] phone = JniFunction.string2Buffer(null, 22);
			ShortByReference stateHold = new ShortByReference();
			ShortByReference errorCodeHold = new ShortByReference();

			int error = Constant.ERR_GeneralError;
			if (isUssdMsg)
			{
			}
			else
			{
				for (int i = 1; i <= 3; i++)
				{
					if (!connect2SmsServer())
					{
						return Constant.ERR_GeneralError;
					}

					error = INSTANCE
							.SMS_GetSmsReport(handleHolder.getValue(), "",
									msgId, phone, stateHold, errorCodeHold,
									timeout);
					if (error == Constant.ERR_RPC)
					{
						INSTANCE.SMS_DisconnectFromServer(handleHolder);
						handleHolder.setValue(0);
						Thread.sleep(200); // 重新连接前等待一段时间
					}
					else
					{
						break;
					}
				}

				if (Constant.ERR_Success == error)
				{
					phoneNumberHolder.value = Native.toString(phone);
					msgIdHolder.value = Native.toString(msgId);
					stateHolder.value = stateHold.getValue();
					errorCodeHolder.value = errorCodeHold.getValue();
				}
			}

			return error;
		}
		catch (Exception e)
		{
			return Constant.ERR_GeneralError;
		}
	}

	public static void init(String serverIp)
	{
		INSTANCE = (SmsClientLib) Native.loadLibrary("SMSMediaServer",
				SmsClientLib.class);

		SmsClient.serverIp = serverIp;
	}

	public static int sendSmsMsg(boolean isUssdMsg, int paramLevel,
			StringWrapper msgIdHolder, int msgTotal, int msgNumber,
			String numberArray[], String sp, String chargeNumber,
			String corpId, String serviceType, int feeType, int feeValue,
			int givenValue, int agentFlag, int morelatetoMTFlag, int priority,
			String expireTime, String scheduleTime, int reportFlag, int tpPid,
			int tpUdhi, int messageType, String linkId, int messageCoding,
			int length, String msg)
	{
		if ((feeValue > 9999999) || (givenValue > 9999999))
		{
			return Constant.ERR_IVALID_PARAM;
		}

		try
		{
			byte[] msgId = JniFunction.string2Buffer(null, 41);
			length = (msg == null ? 0 : msg.getBytes().length);

			byte[] phones = new byte[numberArray.length * 22];
			for (int i = 0; i < numberArray.length; i++)
			{
				byte[] phone = JniFunction.string2Buffer(numberArray[i], 22);
				System.arraycopy(phone, 0, phones, 22 * i, phone.length);
			}

			int error = Constant.ERR_GeneralError;
			if (isUssdMsg)
			{
			}
			else
			{
				for (int i = 1; i <= 3; i++)
				{
					if (!connect2SmsServer())
					{
						return Constant.ERR_GeneralError;
					}

					if (paramLevel == 1)
					{
						error = INSTANCE.SMS_SendSmsMsg(
								handleHolder.getValue(), "", msgId,
								(numberArray.length > 0 ? numberArray[0] : ""),
								(sp == null ? "" : sp),
								(serviceType == null ? "" : serviceType),
								(short) feeType, "" + feeValue,
								(short) morelatetoMTFlag, (short) reportFlag,
								(linkId == null ? "" : linkId),
								(short) messageCoding, (short) length,
								(msg == null ? "" : msg));
					}
					else if (paramLevel == 2)
					{
						error = INSTANCE.SMS_SendSmsMsgEx(handleHolder
								.getValue(), "",
								(numberArray.length > 0 ? numberArray[0] : ""),
								(sp == null ? "" : sp),
								(msg == null ? "" : msg));
					}
					else
					{
						error = INSTANCE.SMS_SendSmsMsgPro(handleHolder
								.getValue(), "", msgId, (short) msgTotal,
								(short) msgNumber, numberArray.length, phones,
								(sp == null ? "" : sp),
								(chargeNumber == null ? "" : chargeNumber),
								(corpId == null ? "" : corpId),
								(serviceType == null ? "" : serviceType),
								(short) feeType, "" + feeValue,
								"" + givenValue, (short) agentFlag,
								(short) morelatetoMTFlag, (short) priority,
								(expireTime == null ? "" : expireTime),
								(scheduleTime == null ? "" : scheduleTime),
								(short) reportFlag, (short) tpPid,
								(short) tpUdhi, (short) messageType,
								(linkId == null ? "" : linkId),
								(short) messageCoding, length,
								(msg == null ? "" : msg));
					}

					if (error == Constant.ERR_RPC)
					{
						INSTANCE.SMS_DisconnectFromServer(handleHolder);
						handleHolder.setValue(0);
						Thread.sleep(200); // 重新连接前等待一段时间
					}
					else
					{
						break;
					}
				}

				if (Constant.ERR_Success == error)
				{
					msgIdHolder.value = Native.toString(msgId);
				}
			}

			return error;
		}
		catch (Exception e)
		{
			return Constant.ERR_GeneralError;
		}
	}
}

interface SmsClientLib extends Library
{
	int SMS_ConnectToServer(String server, IntByReference handleHolder);

	int SMS_DisconnectFromServer(IntByReference handleHolder);

	int SMS_GetSmsMsg(int handle, String dn, byte[] msgId, byte[] spNumber,
			byte[] phone, byte[] serviceType, IntByReference msgLength,
			byte[] msg, ByteByReference tpIdHolder,
			ByteByReference tpUdhiHolder, byte[] linkId, int timeout);

	int SMS_GetSmsReport(int handle, String dn, byte[] msgId, byte[] userPhone,
			ShortByReference stateHolder, ShortByReference errorCodeHolder,
			int timeout);

	int SMS_SendSmsMsg(int handle, String dn, byte[] msgId, String phone,
			String spNumber, String serviceType, short feeType,
			String feeValue, short morelatetoMTFlag, short priority,
			String linkId, short messageCoding, int messageLength,
			String messageContent);

	int SMS_SendSmsMsgEx(int handle, String dn, String phone, String sPNumber,
			String messageContent);

	int SMS_SendSmsMsgPro(int handle, String dn, byte[] msgId, short nMsgTotal,
			short nMsgNumber, int ArraySize, byte[] phones, String spNumber,
			String chargeNumber, String corpId, String serviceType,
			short feeType, String feeValue, String givenValue, short agentFlag,
			short morelatetoMTFlag, short priority, String expireTime,
			String scheduleTime, short reportFlag, short tPPpid, short tpUdhi,
			short messageType, String linkId, short messageCoding,
			int messageLength, String MessageContent);
}
