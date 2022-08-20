package com.channelsoft.slee.snmpagent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.channelmanager.ChannelManager;
import com.channelsoft.slee.log.ErrorObserver;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.perfmonitor.ChannelPerfData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;

/**
 * @author liyan
 */
public class SnmpAgentHandler implements org.snmp4j.CommandResponder,
		ErrorObserver
{

	class SnmpDataSender extends Thread
	{
		@Override
		public void run()
		{
			setName("SnmpDataSender");

			SnmpData snmpData = null;

			while (true)
			{
				try
				{
					try
					{
						snmpData = snmpDatas.take();
					}
					catch (InterruptedException e)
					{
					}

					try
					{
						SnmpAgentHandler.sendTrap(snmpData);
					}

					catch (Exception e)
					{
						Log.wException(LogDef.id_0x10160001, e);
					}
				}
				catch (Throwable t)
				{

				}
			}
		}
	}

	class SnmpStatDataCounter extends Thread
	{
		int maxBysyChannelCount = 0;

		void computeChannelOccupiedRate()
		{
			int rate = 100 * computeBusyChannel()
					/ SnmpRelatedData.instance().getTotalChannelCount();
			if (rate > SnmpRelatedData.instance()
					.getChannelOccupiedRate_Upper())
			{
				String content = "通道占用率高于"
						+ SnmpRelatedData.instance()
								.getChannelOccupiedRate_Upper() + "％";
				SnmpAgentHandler.instance().addTrapData(content, 2);
			}
		}

		void computeMaxBusyChannelCount()
		{

			maxBysyChannelCount = Math.max(computeBusyChannel(),
					maxBysyChannelCount);
		}

		void modifyStatData()
		{
			SnmpRelatedData.instance().setStartServiceCount(
					ChannelManager.startServiceCount);
			ChannelManager.startServiceCount = 0;

			SnmpRelatedData.instance().setHighestOccupiedChannelCount(
					maxBysyChannelCount);
			maxBysyChannelCount = 0;

			SnmpRelatedData.instance().setStopServiceCount(
					ChannelManager.endServiceCount);
			ChannelManager.endServiceCount = 0;

			SnmpRelatedData.instance().setServiceFailedCount(
					ChannelManager.failedServiceCount);
			ChannelManager.failedServiceCount = 0;

			SnmpRelatedData.instance().setServiceSucceedCount(
					ChannelManager.successfulServiceCount);
			ChannelManager.successfulServiceCount = 0;
			SnmpRelatedData.instance().setStatisticID(++statID);
		}

		@Override
		public void run()
		{
			setName("SnmpStatDataCounter");

			while (true)
			{
				for (int i = 0; i <= 300; i++)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (Exception e)
					{
						Log.wException(LogDef.id_0x10160002, e);
					}
					computeMaxBusyChannelCount();
					// computeChannelOccupiedRate();

					if (i == 300)
					{
						modifyStatData();
					}
				}
			}

		}
	}

	public static final OID cancelSession = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 2, 4, 3, 0 });

	public static final OID curHungSessionCount = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 2, 3, 0 });

	public static final OID getSessionHungPos = new OID(new int[] { 1, 3, 6, 1,
			4, 1, 28012, 2, 4, 8, 0 });

	public static final OID getSessionHungTime = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 4, 7, 0 });

	public static volatile boolean isStartFile = false;

	public static volatile boolean isStartParam = false;

	public static final OID makeSessionTimeout = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 4, 4, 0 });

	public static volatile String mibTargetIP = null;

	public static volatile String mibTargetPort = "162";

	static OctetString mMyCommunityName = null;

	public static volatile boolean newTargetAddress = false;

	public static final OID oidCannotLinkRelatedProc = new OID(new int[] { 1,
			3, 6, 1, 4, 1, 28012, 2, 5, 1, 0 });

	public static final OID oidChannelUsedTooMuch = new OID(new int[] { 1, 3,
			6, 1, 4, 1, 28012, 2, 5, 2, 0 });

	// 协议里是必须要到trap报文OID的上一级,和specific code值一起决定具体企业自定义报文的OID
	public static final OID oidEnterprise = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 2, 5 });

	static OID oidErrorInfo = null;

	public static final OID oidHighestOccupiedChannelCount = new OID(new int[] {
			1, 3, 6, 1, 4, 1, 28012, 2, 3, 1, 0 });

	public static final OID oidNoIdleChannel = new OID(new int[] { 1, 3, 6, 1,
			4, 1, 28012, 2, 5, 5, 0 });

	public static final OID oidOccupiedChannelCount = new OID(new int[] { 1, 3,
			6, 1, 4, 1, 28012, 2, 2, 2, 0 });

	public static final OID oidOutBoundFailed = new OID(new int[] { 1, 3, 6, 1,
			4, 1, 28012, 2, 5, 4, 0 });

	public static final OID oidServiceFailedCount = new OID(new int[] { 1, 3,
			6, 1, 4, 1, 28012, 2, 3, 5, 0 });

	public static final OID oidServiceSucceedCount = new OID(new int[] { 1, 3,
			6, 1, 4, 1, 28012, 2, 3, 4, 0 });

	public static final OID oidSleeCreateTime = new OID(new int[] { 1, 3, 6, 1,
			4, 1, 28012, 2, 1, 4, 0 });

	public static final OID oidSleeFlag = new OID(new int[] { 1, 3, 6, 1, 4, 1,
			28012, 2, 1, 1, 0 });

	public static final OID oidSleeIpAddr = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 2, 1, 7, 0 });

	public static final OID oidSleeLocation = new OID(new int[] { 1, 3, 6, 1,
			4, 1, 28012, 2, 1, 3, 0 });

	// public static final OID oidChannelOccupiedRate_Upper = new OID(new int[]
	// {
	// 1, 3, 6, 1, 4, 1, 28012, 2, 4, 1, 0 });

	public static final OID oidSleeName = new OID(new int[] { 1, 3, 6, 1, 4, 1,
			28012, 2, 1, 2, 0 });

	public static final OID oidSleeSoftVersion = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 1, 9, 0 });

	public static final OID oidSleeTimePrick = new OID(new int[] { 1, 3, 6, 1,
			4, 1, 28012, 2, 1, 8, 0 });

	public static final OID oidSleeWorkingState = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 1, 6, 0 });

	public static final OID oidSleeWorkingType = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 1, 5, 0 });

	public static final OID oidStartFile = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 2, 4, 1, 0 });

	public static final OID oidStartParam = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 2, 4, 2, 0 });

	public static final OID oidStartServiceCount = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 3, 2, 0 });

	public static final OID oidStatisticID = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 2, 3, 6, 0 });

	public static final OID oidStopServiceCount = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 3, 3, 0 });

	public static final OID oidTargetIP = new OID(new int[] { 1, 3, 6, 1, 4, 1,
			28012, 3, 1, 3, 0 });

	public static final OID oidTargetPort = new OID(new int[] { 1, 3, 6, 1, 4,
			1, 28012, 3, 1, 4, 0 });

	public static final OID oidTotalChannelCount = new OID(new int[] { 1, 3, 6,
			1, 4, 1, 28012, 2, 2, 1, 0 });

	public static final OID oidWriteBillingDataFailed = new OID(new int[] { 1,
			3, 6, 1, 4, 1, 28012, 2, 5, 3, 0 });

	public static final OID sendMsgToSessionParam1 = new OID(new int[] { 1, 3,
			6, 1, 4, 1, 28012, 2, 4, 5, 0 });

	public static final OID sendMsgToSessionParam2 = new OID(new int[] { 1, 3,
			6, 1, 4, 1, 28012, 2, 4, 6, 0 });

	public static volatile String serviceStartFile = null;

	public static volatile String serviceStartParam = null;

	private static int statID = 0;

	static Address targetAddress = null;

	private static SnmpAgentHandler theInstance = null;

	public static int computeBusyChannel()
	{
		int total = 0;
		for (int i = 0; i < UnifiedServiceManagement.channelManager.channels
				.size(); i++)
		{
			ChannelPerfData cpf = UnifiedServiceManagement.channelManager.channels
					.elementAt(i).sleeResPerfData;
			if (cpf == null)
			{
				return 0;
			}
			if (cpf.getStatus() == 1)
			{
				total++;
			}
		}
		return total;
	}

	static boolean getSendFlag()
	{
		return newTargetAddress;
	}

	public static SnmpAgentHandler instance()
	{
		if (null == theInstance)
		{
			theInstance = new SnmpAgentHandler();
		}
		return theInstance;
	}

	static void sendTrap(SnmpData msg) throws Exception
	{
		if (!getSendFlag())
		{
			return;
		}

		if (targetAddress == null)
		{
			targetAddress = GenericAddress.parse("udp:" + mibTargetIP + "/"
					+ mibTargetPort);
		}

		PDUv1 pdu = new PDUv1();
		pdu.setType(PDU.V1TRAP);
		pdu.setGenericTrap(msg.getTrapType());
		pdu.setSpecificTrap(msg.getSpecificCode());
		pdu.setEnterprise(oidEnterprise);

		// 设置trap报文头的代理端Ip地址,2007-4-16 ly
		try
		{
			IpAddress agentIP = new IpAddress(InetAddress.getLocalHost()
					.getHostAddress());
			pdu.setAgentAddress(agentIP);
		}
		catch (UnknownHostException e)
		{
			Log.wException(LogDef.id_0x10160007, e);
		}
		if (msg.getSpecificCode() == 1)
		{
			SnmpAgentHandler.oidErrorInfo = SnmpAgentHandler.oidCannotLinkRelatedProc;
		}
		else if (msg.getSpecificCode() == 2)
		{
			SnmpAgentHandler.oidErrorInfo = SnmpAgentHandler.oidChannelUsedTooMuch;
		}
		else if (msg.getSpecificCode() == 3)
		{
			SnmpAgentHandler.oidErrorInfo = SnmpAgentHandler.oidWriteBillingDataFailed;
		}
		else if (msg.getSpecificCode() == 4)
		{
			SnmpAgentHandler.oidErrorInfo = SnmpAgentHandler.oidOutBoundFailed;
		}
		else if (msg.getSpecificCode() == 5)
		{
			SnmpAgentHandler.oidErrorInfo = SnmpAgentHandler.oidNoIdleChannel;
		}
		else
		{
			Log.wDebug(LogDef.id_0x10060003, msg.content);
			return;
		}
		pdu.add(new VariableBinding(SnmpAgentHandler.oidErrorInfo,
				new OctetString(msg.content)));

		CommunityTarget target = new CommunityTarget();
		target.setCommunity(mMyCommunityName);
		target.setAddress(targetAddress);
		target.setVersion(SnmpConstants.version1);

		DefaultUdpTransportMapping udpTransportMap = new DefaultUdpTransportMapping();
		Snmp snmp = new Snmp(udpTransportMap);
		Log.wDebug(LogDef.id_0x10060004, msg.getSpecificCode(), msg.content);
		snmp.send(pdu, target);
	}

	protected java.lang.String mAddress = null;

	protected int mPort = 0;

	protected org.snmp4j.TransportMapping mServerSocket = null;

	protected org.snmp4j.Snmp mSNMP = null;

	public LinkedBlockingQueue<SnmpData> snmpDatas = new LinkedBlockingQueue<SnmpData>();

	public SnmpAgentHandler()
	{
	}

	public void addTrapData(String content, int specificCode)
	{
		if (!getSendFlag())
		{
			return;
		}
		SnmpData trapData = new SnmpData(specificCode);
		trapData.content = content;
		snmpDatas.add(trapData);
	}

	public void addVitalInfo(String content)
	{
		return;
		// 不再发送全部Snmp Trap报文
		/*
		 * if (!getSendFlag()) return; SnmpData logErrorInfo = new SnmpData();
		 * logErrorInfo.content = content; snmpDatas.add(logErrorInfo);
		 */
	}

	public boolean configure(int port)
	{
		try
		{
			if (port == 0)
			{
				return false;
			}

			mAddress = "0.0.0.0";
			mPort = port;
			mMyCommunityName = new OctetString("public");
			return true;
		}
		catch (Exception vException)
		{
			Log.wException(LogDef.id_0x10160003, vException);
			return false;
		}
	}

	public synchronized void processGet(org.snmp4j.CommandResponderEvent aEvent)
	{
		org.snmp4j.PDU vPDU = aEvent.getPDU();
		try
		{
			for (int i = 0; i < vPDU.getVariableBindings().size(); i++)
			{
				if (vPDU.get(i).getOid().equals(oidTotalChannelCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getTotalChannelCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidOccupiedChannelCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getOccupiedChannelCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidTargetIP))
				{
					IpAddress var = new org.snmp4j.smi.IpAddress(
							SnmpAgentHandler.mibTargetIP);
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidTargetPort))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpAgentHandler.mibTargetPort);
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidStartServiceCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getStartServiceCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidStopServiceCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getStopServiceCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidServiceSucceedCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getServiceSucceedCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidServiceFailedCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getServiceFailedCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(
						oidHighestOccupiedChannelCount))
				{
					Integer32 var = new Integer32(0);
					var.setValue(SnmpRelatedData.instance()
							.getHighestOccupiedChannelCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeCreateTime))
				{
					OctetString var = new OctetString();
					var
							.setValue(SnmpRelatedData.instance()
									.getSleeCreateTime());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeFlag))
				{
					Integer32 var = new Integer32();
					var.setValue(SnmpRelatedData.instance().getSleeFlag());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				// else if (vPDU.get(i).getOid().equals(
				// oidChannelOccupiedRate_Upper))
				// {
				// Integer32 var = new Integer32();
				// var.setValue(SnmpRelatedData.instance()
				// .getChannelOccupiedRate_Upper());
				// vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				// }
				else if (vPDU.get(i).getOid().equals(oidSleeIpAddr))
				{
					IpAddress var = new IpAddress();
					var.setValue(SnmpRelatedData.instance().getSleeIpAddr());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeLocation))
				{
					OctetString var = new OctetString();
					var.setValue(SnmpRelatedData.instance().getSleeLocation());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeName))
				{
					OctetString var = new OctetString();
					var.setValue(SnmpRelatedData.instance().getSleeName());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeSoftVersion))
				{
					OctetString var = new OctetString();
					var.setValue(SnmpRelatedData.instance()
							.getSleeSoftVersion());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeTimePrick))
				{
					OctetString var = new OctetString();
					var.setValue(SnmpRelatedData.instance().getSleeTimePrick());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeWorkingState))
				{
					Integer32 var = new Integer32();
					var.setValue(SnmpRelatedData.instance()
							.getSleeWorkingState());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidSleeWorkingType))
				{
					Integer32 var = new Integer32();
					var.setValue(SnmpRelatedData.instance()
							.getSleeWorkingType());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidStatisticID))
				{
					Integer32 var = new Integer32();
					var.setValue(SnmpRelatedData.instance().getStatisticID());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidStartFile))
				{
					OctetString var = new OctetString();
					var.setValue(SnmpAgentHandler.serviceStartFile);
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(oidStartParam))
				{
					OctetString var = new OctetString();
					var.setValue(SnmpAgentHandler.serviceStartParam);
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(curHungSessionCount))
				{
					Integer32 var = new Integer32();
					var.setValue(SnmpRelatedData.instance()
							.getCurHungSessionCount());
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else
				{
					vPDU
							.set(i, new VariableBinding(vPDU.get(i).getOid(),
									null));// to be extended
				}

				vPDU.setType(PDU.RESPONSE);

			}

			aEvent.getMessageDispatcher().returnResponsePdu(
					aEvent.getMessageProcessingModel(),
					aEvent.getSecurityModel(), aEvent.getSecurityName(),
					aEvent.getSecurityLevel(), vPDU,
					aEvent.getMaxSizeResponsePDU(), aEvent.getStateReference(),
					new org.snmp4j.mp.StatusInformation());
		}
		catch (org.snmp4j.MessageException vException)
		{
			Log.wException(LogDef.id_0x10160005, vException);
		}

	}

	public synchronized void processPdu(org.snmp4j.CommandResponderEvent aEvent)
	{
		try
		{
			if (aEvent.getPDU() == null)
			{
				Log.wDebug(LogDef.id_0x10060001);
			}
			else
			{
				Log.wDebug(LogDef.id_0x10060002, aEvent.getPDU().toString());
				switch (aEvent.getPDU().getType())
				{
				case org.snmp4j.PDU.GET:
					processGet(aEvent);
					break;

				case org.snmp4j.PDU.SET:
					processSet(aEvent);
					break;

				default:
					return;
				}
			}
		}
		catch (Throwable t)
		{

		}
	}

	public synchronized void processSet(org.snmp4j.CommandResponderEvent aEvent)
	{
		org.snmp4j.PDU vPDU = aEvent.getPDU();
		serviceStartFile = "";
		serviceStartParam = "";
		isStartFile = false;
		isStartParam = false;

		try
		{
			String sendSessionId = null;
			String sendMsg = null;
			StringWrapper hungTime = new StringWrapper();
			StringWrapper hungPos = new StringWrapper();
			vPDU.setType(PDU.RESPONSE);
			for (int i = 0; i < vPDU.getVariableBindings().size(); i++)
			{
				if (vPDU.get(i).getOid().equals(oidTargetIP))
				{

					if ((vPDU.get(i).getVariable().toString().compareTo(
							"0.0.0.0") == 0))
					// 设置IP=0,则表示不对当前管理站发送trap报文
					{
						mibTargetIP = null;
					}
					else
					{
						mibTargetIP = vPDU.get(i).getVariable().toString();
						newTargetAddress = true;
						targetAddress = null;
					}
				}
				else if (vPDU.get(i).getOid().equals(oidTargetPort))
				{
					mibTargetPort = vPDU.get(i).getVariable().toString();
					newTargetAddress = true;
					targetAddress = null;
				}
				else if (vPDU.get(i).getOid().equals(oidStartFile))
				{
					try
					{
						serviceStartFile = "";
						serviceStartFile = new String(OctetString
								.fromHexString(
										vPDU.get(i).getVariable().toString())
								.toByteArray());

					}
					catch (Exception e)
					{
						serviceStartFile = vPDU.get(i).getVariable().toString();
					}

					isStartFile = true;
					// System.out.println("获取到启动文档！");

				}
				else if (vPDU.get(i).getOid().equals(oidStartParam))
				{
					try
					{
						serviceStartParam = "";
						serviceStartParam = new String(OctetString
								.fromHexString(
										vPDU.get(i).getVariable().toString())
								.toByteArray());

					}
					catch (Exception e)
					{
						serviceStartParam = vPDU.get(i).getVariable()
								.toString();
					}
					isStartParam = true;
					// System.out.println("获取到启动参数！");
				}
				else if (vPDU.get(i).getOid().equals(cancelSession))
				{
					SnmpRelatedData.instance().cancelSession(
							vPDU.get(i).getVariable().toString());
				}
				else if (vPDU.get(i).getOid().equals(makeSessionTimeout))
				{
					SnmpRelatedData.instance().makeSessionTimeout(
							vPDU.get(i).getVariable().toString());
				}
				else if (vPDU.get(i).getOid().equals(sendMsgToSessionParam1))
				{
					sendSessionId = vPDU.get(i).getVariable().toString();
					if (sendMsg != null)
					{
						SnmpRelatedData.instance().sendMsgToSession(
								sendSessionId, sendMsg);
					}
				}
				else if (vPDU.get(i).getOid().equals(sendMsgToSessionParam2))
				{
					sendMsg = vPDU.get(i).getVariable().toString();
					if (sendSessionId != null)
					{
						SnmpRelatedData.instance().sendMsgToSession(
								sendSessionId, sendMsg);
					}
				}
				else if (vPDU.get(i).getOid().equals(getSessionHungTime))
				{
					if (hungTime.value == null)
					{
						SnmpRelatedData.instance().getSessionInfo(
								vPDU.get(i).getVariable().toString(), hungTime,
								hungPos);
					}
					OctetString var = new OctetString();
					var.setValue(hungTime.value);
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}
				else if (vPDU.get(i).getOid().equals(getSessionHungPos))
				{
					if (hungPos.value == null)
					{
						SnmpRelatedData.instance().getSessionInfo(
								vPDU.get(i).getVariable().toString(), hungTime,
								hungPos);
					}
					OctetString var = new OctetString();
					var.setValue(hungPos.value);
					vPDU.set(i, new VariableBinding(vPDU.get(i).getOid(), var));
				}

				// if
				// (vPDU.get(i).getOid().equals(oidChannelOccupiedRate_Upper))
				// {
				// SnmpRelatedData.instance().setChannelOccupiedRate_Upper(
				// vPDU.get(i).getVariable().toInt());
				// }
				if ((isStartFile == true) && (isStartParam == true))
				{
					if (UnifiedServiceManagement.channelManager.beginService(
							serviceStartFile, serviceStartParam).equals(""))
					{
						vPDU.setErrorStatus(1000);// 自定义1000赋给错误状态，表示slee启动通道失败
						// System.out.println("返回错误包，启动通道不成功！");
					}
					isStartFile = false;
					isStartParam = false;
					// System.out.println("启动通道成功！");
				}
			}
			// System.out.println("返回信息！");
			aEvent.getMessageDispatcher().returnResponsePdu(
					aEvent.getMessageProcessingModel(),
					aEvent.getSecurityModel(), aEvent.getSecurityName(),
					aEvent.getSecurityLevel(), vPDU,
					aEvent.getMaxSizeResponsePDU(), aEvent.getStateReference(),
					new org.snmp4j.mp.StatusInformation());
		}
		catch (org.snmp4j.MessageException vException)
		{
			Log.wException(LogDef.id_0x10160006, vException);
		}
	}

	public void start()
	{
		Log.wDebug(LogDef.id_0x10060000);
		Log.addObserver(this);
		SnmpDataSender sds = new SnmpDataSender();
		sds.start();
		SnmpStatDataCounter ssdc = new SnmpStatDataCounter();
		ssdc.start();

		try
		{
			mServerSocket = new org.snmp4j.transport.DefaultUdpTransportMapping(
					new org.snmp4j.smi.UdpAddress(java.net.InetAddress
							.getByName(mAddress), mPort));
			mSNMP = new org.snmp4j.Snmp(mServerSocket);
			mSNMP.addCommandResponder(this);
			mServerSocket.listen();
		}
		catch (java.net.UnknownHostException vException)
		{
			Log.wException(LogDef.id_0x10160004, vException);
		}
		catch (java.io.IOException vException)
		{
			Log.wException(LogDef.id_0x10160004, vException);
		}
		catch (Exception vException)
		{
			Log.wException(LogDef.id_0x10160004, vException);
		}
	}
}// class

