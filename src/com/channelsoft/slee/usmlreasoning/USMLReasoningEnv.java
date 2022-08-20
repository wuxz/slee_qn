package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.usmlreasoning.precompile.PreCompiler;
import com.channelsoft.slee.util.Constant;

public class USMLReasoningEnv implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6746838239677889562L;

	public static USMLReasoningEnv createAUSMLReasoningEnv(
			BaseBrowser baseBrowser, String srvResID)
	{
		USMLReasoningEnv usmlReasoningEnv = new USMLReasoningEnv();
		usmlReasoningEnv.browser = baseBrowser;
		usmlReasoningEnv.srvResID = srvResID;

		return usmlReasoningEnv;
	}

	public static String getUniformPath(String path)
	{
		if (path == null)
		{
			return null;
		}

		String result = path.replace('\\', '/');

		if (result.indexOf("..") == -1)
		{
			return result;
		}

		int pos = result.indexOf("..");
		while (pos != -1)
		{
			int pos0 = result.substring(0, pos - 1).lastIndexOf('/', pos - 1) + 1;
			int posEnd = pos + 3;
			if (posEnd >= result.length())
			{
				posEnd = result.length();
			}

			result = result.substring(0, pos0) + result.substring(posEnd);
			pos = result.indexOf("..");
		}

		return result;
	}

	transient public BaseBrowser browser;

	transient SleepTask currentTask = null;

	private Service curService;

	ReferenceNode idlePromptNode;

	Calendar lastInteractionTime;

	Service rootService;

	/**
	 * ��ǰ�Ự�ĻỰID��
	 */
	private long sessionId;

	String srvResID;

	USMLReasoningEnv()
	{
		rootService = null;
		curService = null;

	}

	public void browseUSMLService(long sessionId, USMLReasoningResult error)
	{
		this.sessionId = sessionId;

		if (error.value == Constant.EVENT_ResumeService)
		{
			if (currentTask == null)
			{
				currentTask = browser.restoreTask(sessionId);
			}

			if (currentTask != null)
			{
				currentTask.isExpired = true;
			}

			rootService.execute(this, browser, srvResID, null, null, error);
		}
		else
		{
			currentTask = null;
			executeService(error);
		}

		while ((error.value == Constant.EVENT_GotoService)
				&& (error.usmlFileName != null))
		{
			executeService(error);
		}
	}

	/**
	 * �鿴�Ƿ�������ͣ��ǰ�ĻỰ��
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean canPauseService() throws Exception
	{
		if (!browser.isIdle())
		{
			browser.onRunError("��������ͨ�������й������");

			return false;
		}

		return true;
	}

	/**
	 * �������е�Serviceʵ�����USML�ĵ��ĸ���ʱ�䣬����빤�߸�֪�ı��� ��ɵ�USML�ĵ��ĸ���ʱ��Ƚϡ�
	 * �������û��Serviceʵ������������ʱ�䲻ƥ�䣬����Ҫ���������µ�Service ʵ����
	 * 
	 * @param usmlFileName
	 * @param oldService
	 * @return
	 * @throws Exception
	 */
	public Service compileUsml(String usmlFileName, Service oldService)
			throws Exception
	{
		StringWrapper packageNameWrapper = new StringWrapper();
		StringWrapper classNameWrapper = new StringWrapper();
		IntWrapper updatedWrapper = new IntWrapper();
		updatedWrapper.value = 0;

		if ((oldService != null)
				&& !oldService.usmlFileName.endsWith(usmlFileName))
		{
			oldService.clearService();
			oldService = null;
		}

		Date myModifiedDate = new Date();
		myModifiedDate.setTime(oldService == null ? 0
				: oldService.lastModifyTime);
		boolean succeeded = PreCompiler.compile(this, usmlFileName,
				packageNameWrapper, classNameWrapper, updatedWrapper,
				myModifiedDate, false);
		if (!succeeded)
		{
			if (classNameWrapper.value != null)
			{
				browser.onRunError("�ļ�" + usmlFileName + "�Ѿ�װ��ʧ�ܣ�ʹ��ԭ���ĵ�");
			}
			else
			{
				browser.onRunError("�ļ�" + usmlFileName + "�Ѿ�װ��ʧ�ܣ�ֱ�ӷ���");

				return null;
			}
		}

		if ((oldService == null)
				|| (myModifiedDate.getTime() != oldService.lastModifyTime))
		{
			if (oldService != null)
			{
				oldService.clearService();
			}

			oldService = newService(updatedWrapper.value == 1,
					packageNameWrapper.value + "." + classNameWrapper.value,
					myModifiedDate.getTime());
		}

		return oldService;
	}

	void executeService(USMLReasoningResult error)
	{
		try
		{
			lastInteractionTime = Calendar.getInstance();
			error.usmlFileName = getUniformPath(error.usmlFileName);

			rootService = compileUsml(error.usmlFileName, rootService);
			if (rootService == null)
			{
				error.value = Constant.EVENT_InvalidUSMLFile;

				return;
			}

			error.usmlFileName = null;

			rootService.execute(this, browser, srvResID, null, null, error);
		}
		catch (MyException e)
		{
			browser.onRunError(e.errMsg);

			error.value = Constant.EVENT_InvalidUSMLFile;
		}
		catch (Exception e)
		{
			browser.onRunError("BaseBrowser::BrowseUSMLService�쳣");
			Log.wException(e);

			error.value = Constant.EVENT_GeneralError;
		}
	}

	public boolean getKeyValue(String varName, StringWrapper varValue)
	{
		KnowledgeVariable var = curService.lookupKeyVar(varName);
		try
		{
			if (var != null)
			{
				var.getValue(varValue);
				return true;
			}
		}
		catch (Exception e)
		{
		}
		return false;
	}

	void getKnowledgeVariables(Vector<String> vars)
	{
		if ((curService == null) || (curService.keyVars == null))
		{
			return;
		}

		Iterator<KnowledgeVariable> it = curService.keyVars.iterator();
		while (it.hasNext())
		{
			KnowledgeVariable var = it.next();
			if (!var.varName.equals("m_ChannelDN")
					&& !var.varName.equals("m_LastError")
					&& !var.varName.equals("m_PrevResult")
					&& !var.varName.equals("m_PronLanguage")
					&& !var.varName.equals("m_StartEventType"))
			{
				vars.add(var.varName);
			}
		}
	}

	public String getKnowledgeVariableValue(String varName)
	{
		KnowledgeVariable var = getVar(varName);
		return (var == null ? "" : var.getValue());
	}

	public int getPronouncingLanguage()
	{
		int pronLanguage = curService.getPronLanguageTypeVar();
		return pronLanguage;
	}

	KnowledgeVariable getVar(String varName)
	{
		if ((curService == null) || (curService.keyVars == null)
				|| varName.equalsIgnoreCase("m_ChannelDN")
				|| varName.equalsIgnoreCase("m_LastError")
				|| varName.equalsIgnoreCase("m_PrevResult")
				|| varName.equalsIgnoreCase("m_PronLanguage")
				|| varName.equalsIgnoreCase("m_StartEventType"))
		{
			return null;
		}

		Iterator<KnowledgeVariable> it = curService.keyVars.iterator();
		while (it.hasNext())
		{
			KnowledgeVariable var = it.next();
			if (var.varName.equalsIgnoreCase(varName))
			{
				return var;
			}
		}

		return null;
	}

	void initIdlePrompt(Service service, Workflow workflow) throws Exception
	{
		if (idlePromptNode != null)
		{
			return;
		}

		idlePromptNode = new ReferenceNode(service, workflow);
		idlePromptNode.initIdlePrompt();
	}

	/**
	 * ����ڴ����µ�ʵ��ʱ�����е�ClassLoader�Ѿ�װ���˾ɵ����ļ�������Ҫ
	 * ����һ���µ�ClassLoader�������µ����ļ����ᱻװ�ء���ΪClassLoader �л��档
	 * ��ClassLoader���е����в��������������ͬ����������ܵ���ClassLoader װ��ʧ�ܻ�װ���˾ɵ����ļ�
	 * 
	 * @param initFirst
	 * @param className
	 * @param millis
	 * @return
	 * @throws Exception
	 */
	Service newService(boolean initFirst, String className, long millis)
			throws Exception
	{
		Service service = (Service) Class.forName(className).newInstance();
		service.lastModifyTime = millis;

		return service;
	}

	void restoreReasoningEnv(BaseBrowser baseBrowser, String srvResID)
	{
		browser = baseBrowser;
		this.srvResID = srvResID;
	}

	void setCurService(Service curService)
	{
		this.curService = curService;
	}

	void setKnowledgeVariableValue(String varName, String value)
	{
		KnowledgeVariable var = getVar(varName);
		if (var != null)
		{
			var.setValue(value);
		}
	}
}
