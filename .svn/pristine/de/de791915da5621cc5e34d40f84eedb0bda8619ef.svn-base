package com.channelsoft.slee.usmlreasoning;

import java.util.Calendar;
import java.util.Vector;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.capability.TTSSegment;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class VoiceListEdit extends IOProcess
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6878926770841031617L;

	public PlayTTS playTTS;

	public GetDTMF getDTMF;

	public boolean isPreGetCash;

	public int doCount;

	public VoiceListEdit(Service service)
	{
		super(service);
		playTTS = new PlayTTS(service);
		getDTMF = new GetDTMF(service);
	}

	@Override
	int doIOProcess(BaseBrowser browser)
	{
		int nReturn = Constant.EVENT_No_Error;
		if (!UnifiedServiceManagement.configData.isCombineVoiceEdit())
		{
			for (int i = 0; i < doCount; i++)
			{
				nReturn = playTTS.executeIOProcess(browser);
				if (nReturn != Constant.EVENT_No_Error)
					break;
				nReturn = getDTMF.executeIOProcess(browser);
				if (nReturn != Constant.EVENT_TimeOut)
					break;
				else
					browser.getUICapability().clearDtmfBuffer();
			}
		}
		else
		{
			Vector<TTSSegment> ttsSegs = new Vector<TTSSegment>();
			for (int i = 0; i < playTTS.ttsSegmentGroup.size(); i++)
			{
				ttsSegs.add(playTTS.ttsSegmentGroup.elementAt(i));
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
			
			for (int i = 0; i < doCount; i++)
			{
				//playtts
				int pronLang;
				if (null != playTTS.languageTypeVar)
				{
					StringWrapper languageType = new StringWrapper();
					playTTS.languageTypeVar.getValue(languageType);
					pronLang = USMLFunction.transPronLangToInt(languageType.value);
				}
				else
				{
					pronLang = service.getPronLanguageTypeVar();
				}

				//
				nReturn = (browser.getUICapability()).voiceListEdit(pronLang,
						ttsSegs, playTTS.rate, playTTS.thirdParty,
						getDTMF.dtmfCount, getDTMF.endFlag,
						getDTMF.isClearBuffer, timeoutSecond, betweenTimeout,
						getDTMF.dtmf);
				if ((nReturn == Constant.EVENT_No_Error)
						|| (nReturn == Constant.EVENT_TimeOut))
				{// m_cEndCharVar
					if (nReturn == Constant.EVENT_No_Error)
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
				}
				else
					(browser.getUICapability()).clearDtmfBuffer();
			}
		}
		return nReturn;
	}

	@Override
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("VoiceListEdit ");
		buff.append(getVarName());
		buff.append(" = new VoiceListEdit(this);\n");
		
		buff.append(getVarName());
		buff.append(".isPreGetCash = ");
		buff.append(isPreGetCash);
		buff.append(";\n");
		
		//playtts
		playTTS.translate2SourceCode(buff);
		
		buff.append(playTTS.getVarName());
		buff.append(".ioProcessName = ");
		ServiceNode.translateString2SourceCode(buff, playTTS.ioProcessName);
		
		buff.append(getVarName());
		buff.append(".playTTS = ");
		buff.append(playTTS.getVarName());
		buff.append(";\n");
		
		//getdtmf
		getDTMF.translate2SourceCode(buff);
		
		buff.append(getDTMF.getVarName());
		buff.append(".ioProcessName = ");
		ServiceNode.translateString2SourceCode(buff, getDTMF.ioProcessName);
		
		buff.append(getVarName());
		buff.append(".getDTMF = ");
		buff.append(getDTMF.getVarName());
		buff.append(";\n");

		//docount
		buff.append(getVarName());
		buff.append(".doCount = ");
		buff.append(doCount);
		buff.append(";\n");
	}

}
