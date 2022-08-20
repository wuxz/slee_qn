package com.channelsoft.slee.callagent.ssr2;

import com.channelsoft.slee.callagent.CallAgent;
import com.channelsoft.slee.callagent.V2_CallAgentImpl;
import com.channelsoft.slee.callagent.V2_CallSession;

public class V2_VGProxySession extends V2_CallSession
{
	static void Initialize()
	{
		V2_CallSession.session = new V2_VGProxySession();
		clear();
	}

	@Override
	protected void clearAgentMessageI(int port)
	{
		if (port < 0)
		{
			return;
		}

		V2_CallAgentImpl callAgent = (V2_CallAgentImpl) V2_CallSession.callAgentMap
				.get(port);
		if (callAgent != null)
		{
			callAgent.clearMessages();
		}
	}

	@Override
	protected CallAgent getCallAgentI(int port, int callId)
	{
		if (port < 0)
		{
			return null;
		}

		V2_CallAgentImpl callAgent = (V2_CallAgentImpl) V2_CallSession.callAgentMap
				.get(port);
		if ((callAgent != null) && (callAgent.resId == port))
		{
			return callAgent;
		}

		callAgent = new V2_CallAgentImpl();
		callAgent.resId = port;
		V2_CallSession.callAgentMap.put(port, callAgent);

		return callAgent;
	}

	@Override
	protected boolean isSupportI(int funType)
	{
		return ((FUN_NORMAL_MAKECALL == funType)
				|| (FUN_SINGLESTEPCONFERENCE == funType)
				|| (FUN_CHECKMESSAGE == funType)
				|| (FUN_RET_ERR_FOR_UNKOWNMSG == funType)
				|| (FUN_ATTACHCALL == funType) || (FUN_MAXUSERIDLESECONDS == funType));
	}
}
