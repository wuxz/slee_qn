package com.channelsoft.slee.usmlreasoning;

import java.util.Calendar;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.util.Constant;

public class PlayVoice extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2068502262314540333L;

	public String breakList;

	public boolean canBreak;

	public String fileName;

	public KnowledgeVariable fileNameVar;

	public int playCount;

	public int rate;

	public int waitTimeOnce;

	public PlayVoice(Service service)
	{
		super(service);
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		StringWrapper tempFileName = new StringWrapper();
		if (null != fileNameVar)
		{
			fileNameVar.getValue(tempFileName);
			fileName = tempFileName.value;
		}

		if (playCount == 0)
		{
			try
			{
				Thread.sleep(300);
			}
			catch (Exception e)
			{
			}
		}
		else
		{
			USMLReasoningResult error = new USMLReasoningResult();
			for (int i = 0; i < playCount; i++)
			{
				IntWrapper secondsPlayed = new IntWrapper(0);
				error.value = browser.getUICapability().playVoice(fileName,
						canBreak, breakList, rate, playCount, waitTimeOnce,
						secondsPlayed,
						service.usmlReasoningEnv.lastInteractionTime);
				while ((service.usmlReasoningEnv.lastInteractionTime
						.getTimeInMillis() == 0)
						&& (secondsPlayed.value != -1))
				{
					service.usmlReasoningEnv.lastInteractionTime = Calendar
							.getInstance();
					try
					{
						service.usmlReasoningEnv.idlePromptNode
								.executeIdlePrompt(error);
					}
					catch (Exception e)
					{

					}
					error.value = browser.getUICapability().playVoice(fileName,
							canBreak, breakList, rate, playCount, waitTimeOnce,
							secondsPlayed,
							service.usmlReasoningEnv.lastInteractionTime);
				}
				if (error.value != Constant.EVENT_No_Error)
				{
					return (error.value == Constant.EVENT_User_StopIO ? Constant.EVENT_No_Error
							: error.value);
				}
				if (playCount != 1)
				{
					try
					{
						Thread.sleep(waitTimeOnce * 1000);
					}
					catch (Exception e)
					{
					}
				}
			}
		}
		return Constant.EVENT_No_Error;
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("PlayVoice ");
		buff.append(getVarName());
		buff.append(" = new PlayVoice(this);\n");

		buff.append(getVarName());
		buff.append(".breakList = ");
		ServiceNode.translateString2SourceCode(buff, breakList);

		buff.append(getVarName());
		buff.append(".canBreak = ");
		buff.append(canBreak);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".rate = ");
		buff.append(rate);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".fileName = ");
		ServiceNode.translateString2SourceCode(buff, fileName);

		buff.append(getVarName());
		buff.append(".fileNameVar = ");
		ServiceNode.translateVar2SourceCode(buff, fileNameVar);

		buff.append(getVarName());
		buff.append(".playCount = ");
		buff.append(playCount);
		buff.append(";\n");

		buff.append(getVarName());
		buff.append(".waitTimeOnce = ");
		buff.append(waitTimeOnce);
		buff.append(";\n");
	}
}
