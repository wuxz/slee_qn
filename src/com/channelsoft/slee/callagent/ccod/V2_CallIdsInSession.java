package com.channelsoft.slee.callagent.ccod;

class V2_CallIdsInSession
{
	static int JOIN2MEP = 0;
	
	static int JOIN2CONNECTION = 1;
	
	int callId = -1;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	int connectionId = -1;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	int mepId = -1;

	/**
	 * 当前电话是由UCDS转过来的的
	 */
	boolean isOldCall = false;

	/**
	 * 呼叫接通的统一时间戳。只保存传过来的数据，不标志呼叫。
	 */
	int startTimestamp = 0;

	/**
	 * 呼叫到达的统一时间戳。只保存传过来的数据，不标志呼叫。
	 */
	int accessTimestamp = 0;

	int otherCallId = -1;

	int otherConnectionId = -1;

	int otherMepId = -1;

	boolean isOtherOldCall = false;

	int otherStartTimestamp = 0;

	int otherAccessTimestamp = 0;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	String enterpriseId;

	/**
	 * 只保存传过来的数据，不标志呼叫。
	 */
	String accountId;
	
	int joinStatus = JOIN2MEP;
	
	/**
	 * 与此呼叫相关的CMS
	 */
	String cmsId;

	int getCallIdFromConnectionId(int connectionId)
	{
		return this.connectionId == connectionId ? callId
				: (this.otherConnectionId == connectionId ? otherCallId : -1);
	}

	int getCallIdFromMepId(int mepId)
	{
		return this.mepId == mepId ? callId : (this.otherMepId == mepId
				? otherCallId : -1);
	}

	int getMepIdFromCallId(int callId)
	{
		return this.callId == callId ? mepId : (this.otherCallId == callId
				? otherMepId : -1);
	}

	int getConnectionIdFromCallId(int callId)
	{
		return this.callId == callId ? connectionId
				: (this.otherCallId == callId ? otherConnectionId : -1);
	}

	boolean getIsOldCallFromCallId(int callId)
	{
		return this.callId == callId ? isOldCall : (this.otherCallId == callId
				? isOtherOldCall : false);
	}

	int getStartTimestampFromCallID(int callId)
	{
		return this.callId == callId ? startTimestamp
				: (this.otherCallId == callId ? otherStartTimestamp : 0);
	}

	int getAccessTimestampFromCallID(int callId)
	{
		return this.callId == callId ? accessTimestamp
				: (this.otherCallId == callId ? otherAccessTimestamp : 0);
	}

	void setStartTimestampFromCallID(int callId, int startTimestamp)
	{
		if (this.callId == callId)
		{
			this.startTimestamp = startTimestamp;
		}
		else if (this.otherCallId == callId)
		{
			this.otherStartTimestamp = startTimestamp;
		}
	}

	void setAccessTimestampFromCallID(int callId, int accessTimestamp)
	{
		if (this.callId == callId)
		{
			this.accessTimestamp = accessTimestamp;
		}
		else if (this.otherCallId == callId)
		{
			this.otherAccessTimestamp = accessTimestamp;
		}
	}
	
	boolean isHostAgent(int callId)
	{
		return (this.callId == callId);
	}

	void setJoin2Connection(int callId)
	{
		joinStatus = JOIN2CONNECTION;
	}
	
	void setJoin2Mep(int callId)
	{
		joinStatus = JOIN2MEP;
	}
	
	boolean needJoin2Mep(int callId)
	{
		return (otherConnectionId == -1 && joinStatus == JOIN2CONNECTION);
	}
}
