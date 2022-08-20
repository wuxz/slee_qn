package com.channelsoft.slee.usmlreasoning;

import java.util.Calendar;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.*;

public class VoiceEdit extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8659187434077043511L;

	public boolean isPreGetCash;

	public GetDTMF getDTMF;

	public PlayVoice playVoice;

	public int doCount;

	public VoiceEdit(Service service)
	{
		super(service);
		playVoice = new PlayVoice(service);
		getDTMF = new GetDTMF(service);
	}

	int doIOProcess(BaseBrowser browser)
	{
		int result = 0;
		for (int i = 0; i < doCount; i++)
		{
			if(!UnifiedServiceManagement.configData.isCombineVoiceEdit())
			{
				result = playVoice.executeIOProcess(browser);
				if (result != Constant.EVENT_No_Error)
					break;
				result = getDTMF.executeIOProcess(browser);
				if (result != Constant.EVENT_TimeOut)
					break;
				else
					(browser.getUICapability()).clearDtmfBuffer();
			}
			else
			{
				//playfile
				IntWrapper secondsPlayed = new IntWrapper(0);
				StringWrapper tempFileName = new StringWrapper();
				if (null != playVoice.fileNameVar)
				{
					playVoice.fileNameVar.getValue(tempFileName);
					playVoice.fileName = tempFileName.value;
				}
				//getdtmf
				getDTMF.dtmf.value = "";

				int timeoutSecond = getDTMF.timeoutSecond;
				if (null != getDTMF.timeoutSecondVar)
				{
					timeoutSecond = getDTMF.timeoutSecondVar.getValueInt();
				}

				int betweenTimeout = getDTMF.betweenTimeout;
				if (null != getDTMF.betweenTimeoutVar)
				{
					betweenTimeout = getDTMF.betweenTimeoutVar.getValueInt();
				}
				//voiceEdit
				result = browser.getUICapability().voiceEdit(
						playVoice.fileName, playVoice.rate,
						playVoice.playCount, playVoice.waitTimeOnce,
						secondsPlayed,
						service.usmlReasoningEnv.lastInteractionTime,
						getDTMF.dtmfCount, getDTMF.endFlag,
						getDTMF.isClearBuffer, timeoutSecond, betweenTimeout,
						getDTMF.dtmf);
				if ((result == Constant.EVENT_No_Error)
						|| (result == Constant.EVENT_TimeOut))
				{// m_cEndCharVar
					if (result == Constant.EVENT_No_Error)
					{
						service.usmlReasoningEnv.lastInteractionTime = Calendar
								.getInstance();
					}
					String endChar = "";
					if ((getDTMF.dtmf.value != null) && !"".equals(getDTMF.dtmf.value))
					{
						endChar = String.valueOf(getDTMF.dtmf.value
								.charAt(getDTMF.dtmf.value.length() - 1));
					}
					if (!"".equals(endChar) && (-1 != getDTMF.endFlag.indexOf(endChar)))
					{
						getDTMF.dtmf.value = getDTMF.dtmf.value.substring(0, getDTMF.dtmf.value.length() - 1);
						if ((getDTMF.returnEndChar) && (null != getDTMF.endCharVar))
						{
							getDTMF.endCharVar.setValue(endChar);
						}
					}
					if (null != getDTMF.dtmfVar)
					{
						getDTMF.dtmfVar.setValue(getDTMF.dtmf.value);
					}
					break;
				}
				else
					(browser.getUICapability()).clearDtmfBuffer();
			}
		}
		return result;
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("VoiceEdit ");
		buff.append(getVarName());
		buff.append(" = new VoiceEdit(this);\n");
		
		buff.append(getVarName());
		buff.append(".isPreGetCash = ");
		buff.append(isPreGetCash);
		buff.append(";\n");
		
		getDTMF.translate2SourceCode(buff);

		buff.append(getDTMF.getVarName());
		buff.append(".ioProcessName = ");
		ServiceNode.translateString2SourceCode(buff, getDTMF.ioProcessName);

		buff.append(getVarName());
		buff.append(".getDTMF = ");
		buff.append(getDTMF.getVarName());
		buff.append(";\n");
		
		playVoice.translate2SourceCode(buff);

		buff.append(playVoice.getVarName());
		buff.append(".ioProcessName = ");
		ServiceNode.translateString2SourceCode(buff, playVoice.ioProcessName);
		
		buff.append(getVarName());
		buff.append(".playVoice = ");
		buff.append(playVoice.getVarName());
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".doCount = ");
		buff.append(doCount);
		buff.append(";\n");
	}
}
