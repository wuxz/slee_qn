package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.jni.JniFunction;
import com.sun.jna.Function;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.ptr.IntByReference;

// ----------------------------------------------
class CallDllCommand implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3203412215147608360L;

	Vector<CallDllCommandParam> arrayParam = new Vector<CallDllCommandParam>();

	String dllName;

	String methodName;

	int paramIntValue[] = new int[DllCaller.MAX_PARAM_COUNT];

	String paramStringValue[] = new String[DllCaller.MAX_PARAM_COUNT];

	int paramType[] = new int[DllCaller.MAX_PARAM_COUNT];

	KnowledgeVariable returnVar = new KnowledgeVariable();

	void init()
	{
		arrayParam.removeAllElements();
	}

}

// ---------------------
class CallDllCommandParam implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8307763560103142428L;

	String charValue;

	int intValue;

	String name;

	int type;

	KnowledgeVariable var = new KnowledgeVariable();

	CallDllCommandParam()
	{
		type = DllCaller.PARAM_TYPE_CONST_STRING;
		intValue = 0;
	}
}

public class DllCaller implements Serializable
{
	static HashMap<String, Integer> dllHandleMap = new HashMap<String, Integer>();

	static final int MAX_PARAM_COUNT = 20;

	static final int PARAM_TYPE_CONST_LONG = 0;

	static final int PARAM_TYPE_CONST_STRING = 1;

	static final int PARAM_TYPE_LONG_REF_VAR = 3;

	static final int PARAM_TYPE_LONG_VAR = 2;

	static final int PARAM_TYPE_STRING_VAR = 4;

	/**
	 * 
	 */
	private static final long serialVersionUID = -377810789628548672L;

	// ----------------------------------------------------------------
	static boolean testCallMethod(DllCaller dllCaller)
	{
		return true;
	}

	public Vector<CallDllCommand> commands = new Vector<CallDllCommand>();

	public String errorInfo;

	public boolean isValid;

	public DllCaller()
	{
		isValid = false;
	}

	// ------------------------------------------------------------
	public boolean browseContent(SrvScriptNode node)
	{
		init();
		String content = node.content;
		Vector<StringWrapper> contents = new Vector<StringWrapper>();
		char szEnd = '\n';
		StringWrapper contentLine = new StringWrapper();
		IntWrapper startPos = new IntWrapper();
		boolean ret = splitString(szEnd, content, contentLine, startPos);
		while (ret)
		{
			if (!contentLine.value.trim().equals(""))
			{
				contents.addElement(contentLine);
			}

			contentLine = new StringWrapper();
			ret = splitString(szEnd, content, contentLine, startPos);
		}
		if (startPos.value < content.length())
		{
			StringWrapper lastContentLine = new StringWrapper(content
					.substring(startPos.value));
			if (!lastContentLine.value.trim().equals(""))
			{
				contents.addElement(lastContentLine);
			}
		}
		for (int nCount = 0; nCount < contents.size(); nCount++)
		{
			contentLine = contents.elementAt(nCount);
			if (contentLine.value.length() <= 0)
			{
				continue;
			}
			if (contentLine.value.charAt(0) == '\'')
			{
				continue; // '\'' 是注释
			}
			int nPos = contentLine.value.indexOf("rundll");
			if (nPos == -1)
			{
				contentLine.value = contentLine.value.trim();
				CallDllCommand command = new CallDllCommand();
				if (parseCommandLine(contentLine, command, node))
				{
					commands.addElement(command);
				}
				else
				{
					return false;
				}
			}
		}

		isValid = true;
		return true;
	}

	boolean execute(CallDllCommand command, SrvScriptNode node)
	{
		NativeLibrary nl = null;

		try
		{
			nl = NativeLibrary.getInstance(command.dllName);
		}
		catch (Throwable e)
		{
		}

		if (nl == null)
		{
			errorInfo = "装载库" + command.dllName + "失败";

			return false;
		}

		Object[] params = new Object[command.arrayParam.size()];

		for (int i = 0; i < command.arrayParam.size(); i++)
		{
			CallDllCommandParam param = command.arrayParam.elementAt(i);
			command.paramType[i] = param.type;
			switch (param.type)
			{
			case PARAM_TYPE_CONST_LONG:
				params[i] = param.intValue;

				break;

			case PARAM_TYPE_CONST_STRING:
				params[i] = JniFunction.string2Buffer(param.charValue, 2048);
				if (params[i] == null)
				{
					errorInfo = "参数[" + i + "]字符串过长";
					return false;
				}

				break;

			case PARAM_TYPE_LONG_VAR:
				params[i] = param.var.getValueInt();

				break;

			case PARAM_TYPE_LONG_REF_VAR:
				params[i] = new IntByReference(param.var.getValueInt());

				break;

			case PARAM_TYPE_STRING_VAR:
				StringWrapper temp = new StringWrapper();
				param.var.getValue(temp);
				params[i] = JniFunction.string2Buffer(temp.value, 2048);
				if (params[i] == null)
				{
					errorInfo = "参数[" + i + "]字符串过长";
					return false;
				}

				break;
			}
		}

		Function func = null;

		try
		{
			func = nl.getFunction(command.methodName);
		}
		catch (UnsatisfiedLinkError e)
		{
			errorInfo = String.format("指定的DLL(%s)中的方法(%s)不存在", command.dllName,
					command.methodName);

			return false;
		}

		int returnVal = 0;
		try
		{
			returnVal = func.invokeInt(params);
		}
		catch (Throwable e)
		{

			errorInfo = String.format("调用指定的DLL(%s)中的方法(%s)出现异常",
					command.dllName, command.methodName);

			return false;
		}

		for (int i = 0; i < command.arrayParam.size(); i++)
		{
			CallDllCommandParam param = command.arrayParam.elementAt(i);
			switch (param.type)
			{
			case PARAM_TYPE_CONST_LONG:
			case PARAM_TYPE_LONG_VAR:
				break;

			case PARAM_TYPE_LONG_REF_VAR:
				param.var.setValue(((IntByReference) params[i]).getValue());
				break;

			case PARAM_TYPE_CONST_STRING:
				break;

			case PARAM_TYPE_STRING_VAR:
				param.var.setValue(Native.toString((byte[]) params[i]));
				break;
			}
		}

		if (command.returnVar != null)
		{
			command.returnVar.setValue(returnVal);
		}

		return true;
	}

	boolean execute(SrvScriptNode node)
	{
		for (int count = 0; count < commands.size(); count++)
		{
			CallDllCommand command = commands.elementAt(count);
			if (!execute(command, node))
			{
				// m_errorInfo.Format("调用DLL(%s)的方法(%s)失败", pCommand->m_DllName,
				// pCommand->m_MethodName);
				return false;
			}
		}

		return true;
	}

	void init()
	{
		errorInfo = "";
		commands.removeAllElements();
	}

	// -------------------------------------
	boolean parseCommandLine(StringWrapper contentLine, CallDllCommand command,
			SrvScriptNode node)
	{
		StringWrapper returnStr = new StringWrapper();
		StringWrapper callStr = new StringWrapper();
		IntWrapper start = new IntWrapper();
		if (splitKeyValue(contentLine.value, returnStr, callStr, start, '='))
		{
			returnStr.value = returnStr.value.trim();
			command.returnVar = node.getOutVar(returnStr.value);
			if (command.returnVar == null)
			{
				errorInfo = String.format("输出变量%1$s不存在", returnStr.value);
				return false;
			}
			command.returnVar.varName = returnStr.value;
		}
		else
		{
			callStr.value = contentLine.value;
		}

		callStr.value = callStr.value.trim();
		if (callStr.value.charAt(0) == '=')
		{
			errorInfo = String.format("缺少返回值的声明");
			return false;
		}

		StringWrapper dllName = new StringWrapper();
		StringWrapper callBodyStr = new StringWrapper();
		start.value = 0;
		if (!splitKeyValue(callStr.value, dllName, callBodyStr, start, '.'))
		{
			errorInfo = String.format("缺少方法调用操作符");
			return false;
		}

		dllName.value = dllName.value.trim();
		callBodyStr.value = callBodyStr.value.trim();
		command.dllName = dllName.value.trim();

		StringWrapper methodName = new StringWrapper();
		StringWrapper paramStr = new StringWrapper();
		start.value = 0;
		if (!splitKeyValue(callBodyStr.value, methodName, paramStr, start, '('))
		{
			errorInfo = String.format("缺少左括号");
			return false;
		}

		methodName.value = methodName.value.trim();
		paramStr.value = paramStr.value.trim();
		if (paramStr.value.charAt(paramStr.value.length() - 1) != ')')
		{
			errorInfo = String.format("缺少右括号");
			return false;
		}
		command.methodName = methodName.value;
		if (!splitParamValue(paramStr.value.substring(0, paramStr.value
				.length() - 1), command, node))
		{
			return false;
		}

		return true;
	}

	boolean ParseParam(String typeString, String valueString,
			SrvScriptNode node, CallDllCommandParam param)
	{
		if (typeString.equalsIgnoreCase("long"))
		{
			if (valueString.indexOf("m_") == 0)
			{
				param.type = PARAM_TYPE_LONG_VAR;
				if (!ParseParamKnowledgeVariable(param, node, valueString))
				{
					return false;
				}
			}
			else
			{
				param.type = PARAM_TYPE_CONST_LONG;
				param.intValue = Integer.parseInt(valueString);
			}
		}
		else if (typeString.equalsIgnoreCase("long*"))
		{
			if (valueString.indexOf("m_") == 0)
			{
				param.type = PARAM_TYPE_LONG_REF_VAR;
				if (!ParseParamKnowledgeVariable(param, node, valueString))
				{
					return false;
				}
			}
			else if (valueString.indexOf("m_") != 0)
			{
				errorInfo = String.format("指针参数不能是常量");
				return false;
			}
		}
		else if (typeString.equalsIgnoreCase("String"))
		{
			if (valueString.indexOf("m_") == 0)
			{
				param.type = PARAM_TYPE_STRING_VAR;
				if (!ParseParamKnowledgeVariable(param, node, valueString))
				{
					return false;
				}
			}
			else if (valueString.indexOf("m_") != 0)
			{
				param.type = PARAM_TYPE_CONST_STRING;
				int lastPos = valueString.length() - 1;
				if ((valueString.indexOf('"') == 0)
						&& (valueString.charAt(lastPos) == '"')
						&& (lastPos > 0))
				{
					param.charValue = valueString.substring(1, lastPos);
				}
				else
				{
					errorInfo = String.format("字符串常量非法");
					return false;
				}
			}
		}

		else
		{
			errorInfo = String.format("参数的类型%1$s定义非法", typeString);
			return false;
		}

		return true;

	}

	// -------------------------------------
	boolean ParseParamKnowledgeVariable(CallDllCommandParam param,
			SrvScriptNode node, String valueString)
	{
		param.name = valueString;
		KnowledgeVariable pVar = node.getInVar(param.name);
		if (pVar == null)
		{
			errorInfo = String.format("输入变量%1$s不存在", param.name);
			return false;
		}

		param.var = pVar;
		return true;
	}

	// ----------------------------------------------------------------------------------------
	boolean splitKeyValue(String source, StringWrapper key,
			StringWrapper value, IntWrapper start, char delimiter)
	{
		if (!splitString(delimiter, source, key, start)
				|| (key.value.length() == 0))
		{
			return false;
		}

		value.value = source.substring(start.value);

		return true;
	}

	// ------------------------------------------------
	boolean splitParamValue(String szParam, CallDllCommand command,
			SrvScriptNode node)
	{
		StringWrapper paramSeg = new StringWrapper();
		IntWrapper startPos = new IntWrapper();

		boolean shouldExit = false;
		while (!shouldExit)
		{
			boolean ret = splitString(',', szParam, paramSeg, startPos);
			if (!ret)
			{
				paramSeg.value = szParam.substring(startPos.value);
				shouldExit = true;
			}

			paramSeg.value = paramSeg.value.trim();
			if (paramSeg.value.length() == 0)
			{
				return true;
			}

			StringWrapper typeString = new StringWrapper();
			StringWrapper valueString = new StringWrapper();
			int pos2 = paramSeg.value.indexOf('[') + 1;
			if (pos2 == 0)
			{
				errorInfo = String.format("第%1$d个参数缺少类型声明", command.arrayParam
						.size() + 1);
				return false;
			}
			IntWrapper start = new IntWrapper(pos2);
			boolean ret2 = splitString(']', paramSeg.value, typeString, start);
			if (!ret2)
			{
				errorInfo = String.format("第%1$d个参数缺少类型声明", command.arrayParam
						.size() + 1);
				return false;
			}

			if (start.value >= paramSeg.value.length())
			{
				errorInfo = String.format("第%1$d个参数缺少值", command.arrayParam
						.size() + 1);
				return false;
			}

			valueString.value = paramSeg.value.substring(start.value);

			typeString.value.replace(" ", "");
			valueString.value = valueString.value.trim();

			CallDllCommandParam param = new CallDllCommandParam();
			if (ParseParam(typeString.value, valueString.value, node, param))
			{
				command.arrayParam.addElement(param);
			}
			else
			{
				return false;
			}
		}

		return true;
	}

	// -----------------------------------------------------
	boolean splitString(char delimiter, String source, StringWrapper result,
			IntWrapper start)
	{
		int end = source.indexOf(delimiter, start.value);
		if (end == -1)
		{
			return false;
		}
		result.value = source.substring(start.value, end);// before delimiter
		start.value = end + 1;
		return true;
	}
}
