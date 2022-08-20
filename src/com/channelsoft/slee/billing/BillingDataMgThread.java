package com.channelsoft.slee.billing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import com.channelsoft.slee.jni.DRWRClient;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.snmpagent.SnmpAgentHandler;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

public class BillingDataMgThread extends Thread
{
	static Random randomBill = new Random();

	static SimpleDateFormat sdFormatBill = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	static int sn = 1;

	static BillingDataMgThread theInstance = null;

	public static BillingDataMgThread instance()
	{
		if (null == theInstance)
		{
			theInstance = new BillingDataMgThread();
		}
		return theInstance;
	}

	LinkedBlockingQueue<BillingData> billingDataQueue = new LinkedBlockingQueue<BillingData>();

	BillingDataMgThread()
	{
	}

	public void bill(String callingId, String calledId, String origCallingId,
			String origCalledId, String accountId, int accessTimeStamp,
			int startTimeStamp, int endTimeStamp, String serviceId,
			String subscriberId)
	{
		if (!UnifiedServiceManagement.configData.getShouldWriteSDR())
		{
			return;
		}

		BillingData billData = new BillingData(true);
		billData.accessTime = Calendar.getInstance();
		billData.accessTime.setTimeInMillis(accessTimeStamp * 1000l);
		billData.startTime = Calendar.getInstance();
		billData.startTime.setTimeInMillis(startTimeStamp * 1000l);
		billData.endTime = Calendar.getInstance();
		billData.endTime.setTimeInMillis(endTimeStamp * 1000l);
		billData.accountID = accountId;
		billData.accountType = 3;
		billData.agentDn = "";
		billData.agentId = "";
		billData.calledID = calledId;
		billData.callingID = callingId;
		billData.channelType = 1;
		billData.contentId = "";
		billData.feeType = 0;
		billData.networkAccount = "";
		billData.networkId = "";
		billData.networkPortalId = "";
		billData.originCalledId = origCalledId;
		billData.originCallingId = origCallingId;
		billData.reserve1 = "";
		billData.reserve2 = "";
		billData.reserve3 = "";
		billData.reserve4 = "";
		billData.reserve5 = "";
		billData.serviceAgentRate = 0;
		billData.serviceID = serviceId;
		billData.serviceRate = 0;
		billData.skillId = "";
		billData.spID = "";
		billData.subscriberId = subscriberId;
		billData.userIpAddr = "";
		billData.userNumber = "";
		billData.serviceQty = endTimeStamp - startTimeStamp;
		if (billData.serviceQty < 0)
		{
			billData.serviceQty = 0;
		}

		billingDataQueue.add(billData);
	}

	public BillingData createBillingData(boolean isBilling)
	{
		return new BillingData(isBilling);
	}

	@Override
	public void run()
	{
		setName("BillingDataMg");

		DRWRClient.drwr_threadAttach();

		BillingData cData = null;
		int lRt = 0;
		boolean bReset = false;

		while (true)
		{
			try
			{
				if (bReset == true)
				{
					Thread.sleep(300000);
					bReset = false;
				}

				cData = billingDataQueue.take();

				String startTime, endTime, accessTime;
				startTime = time2str(cData.startTime);
				endTime = time2str(cData.endTime);
				accessTime = time2str(cData.accessTime);

				String snString, callIdString;
				snString = String.format("%010x%010x%05x", (int) (System
						.currentTimeMillis() / 1000), sn++, randomBill
						.nextInt(100000));
				callIdString = String.valueOf(cData.callID);

				if (UnifiedServiceManagement.configData
						.getWriteUserDefinedCallID()
						&& (cData.reserve1 != null)
						&& !"".equals(cData.reserve1))
				{
					callIdString = cData.reserve1;
					cData.reserve1 = "";
				}

				lRt = DRWRClient.drwr_saveSDR(
						UnifiedServiceManagement.configData.getNodeCode(),
						UnifiedServiceManagement.configData.getDeviceCode(),
						snString, callIdString, cData.serviceID,
						cData.callingID, cData.calledID, cData.originCallingId,
						cData.originCalledId, cData.accountID,
						cData.accountType, cData.agentId, cData.agentDn,
						cData.skillId, cData.contentId, cData.channelType,
						cData.spID, cData.subscriberId, accessTime, startTime,
						endTime, cData.serviceRate, cData.feeType,
						cData.serviceQty, cData.serviceAgentRate,
						cData.userNumber, cData.userIpAddr,
						cData.networkAccount, cData.networkId,
						cData.networkPortalId, cData.reserve1, cData.reserve2,
						cData.reserve3, cData.reserve4, cData.reserve5);

				Log.wDebug(LogDef.id_0x10040001, lRt, snString, callIdString,
						cData.callingID, cData.calledID, cData.serviceID,
						startTime, endTime, cData.serviceQty, billingDataQueue
								.size());

				if (0 != lRt)
				{
					billingDataQueue.add(cData); // 重新入队
					String content = String.format("写计费数据失败，错误代码：%d。", lRt);
					Log.wError(LogDef.id_0x10140010, lRt);
					if (UnifiedServiceManagement.hasSnmpAgent)
					{
						SnmpAgentHandler.instance().addTrapData(content, 3);
					}
					Log.wDebug(LogDef.id_0x10040000, billingDataQueue.size());
					bReset = true;
				}
			}
			catch (Throwable e)
			{
				billingDataQueue.add(cData); // 重新入队
				Log.wFatal(LogDef.id_0x10240001);
				Log.wDebug(LogDef.id_0x10040000, billingDataQueue.size());
				Log.wException(LogDef.id_0x10140000, e);
				bReset = true;

				try
				{
					Thread.sleep(3000);
				}
				catch (Throwable ee)
				{
				}
			}
		}

		// JniFunction.drwr_threadDetach();
	}

	public String time2str(Calendar t)
	{
		if (t != null)
		{
			String strTime = sdFormatBill.format(t.getTime());
			return strTime;
		}
		return "";
	}

}