package com.channelsoft.slee.callagent.socket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.channelsoft.slee.util.Constant;

public class MakeCallAgent
{
	int port = -1;

	int callId = -1;

	int result = 0;

	CallAgentImpl callAgent;

	static Map<Integer, MakeCallAgent> makeCallAgentMap = Collections
			.synchronizedMap(new HashMap<Integer, MakeCallAgent>());

	MakeCallAgent(CallAgentImpl callAgent)
	{
		this.callAgent = callAgent;
	}

	public synchronized static MakeCallAgent getMakeCallAgent(
			int transactionId, CallAgentImpl callAgent)
	{
		MakeCallAgent makeCallAgent = makeCallAgentMap.get(transactionId);
		if (makeCallAgent == null)
		{
			makeCallAgent = new MakeCallAgent(callAgent);
			makeCallAgentMap.put(transactionId, makeCallAgent);
		}

		return makeCallAgent;
	}

	synchronized public void processAck(MakeCallAck ackMsg)
	{
		port = ackMsg.port;
		callId = ackMsg.callId;
		result = callAgent.translateResult(ackMsg.result);

		if (result == Constant.ERR_Success)
		{
			CallAgentImpl agent = callAgent.getCallAgent(port);
			if (agent != null)
			{
				agent.msgQueue.clear();
			}
		}

		notify();
	}
}
