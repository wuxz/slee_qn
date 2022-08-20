package com.channelsoft.slee.usmlreasoning;

import java.io.Serializable;
import java.util.Vector;

import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.jni.JniFunction;

public class PlugIn implements Serializable
{

	public class Command implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4179622369178043640L;

		CommandId id;

		Vector<CommandParam> params = new Vector<CommandParam>();

		String returnName; // 如果为空，不需要返回值
	}

	public enum CommandId
	{
		cmdIf, cmdIf0, cmdIf1, cmdIf2, cmdIf3, cmdIfEx, cmdSIf, cmdSleep, cmdUSE_BlindTransferCall, cmdUSE_GetAssociateData, cmdUSE_GetTransTarget, cmdUSE_MakeServiceVir, cmdUSE_ReqVirtualSrv, cmdUSE_SetAssociateData, cmdUSE_WithdrawPreLock,
	};

	public class CommandParam implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -1939354877197184960L;

		boolean isConst; // isConst=TRUE; value=name

		String name;

		String value;
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232495619673130836L;;

	static int ifEx(int channel, int switchCase, StringWrapper p1,
			StringWrapper p2)
	{
		return JniFunction.sleePlgIfEx(channel, switchCase, p1, p2);
	}

	static int sIf(int channel, String server, int switchCase, String in,
			StringWrapper out, int timeout)
	{
		return JniFunction.sleePlgSIf(channel, server, switchCase, in, out,
				timeout);
	}

	static void sleep(int millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch (Exception e)
		{

		}
	}

	static void start()
	{
		JniFunction.sleePlgStart();
	}

	static void stop()
	{
		JniFunction.sleePlgStop();
	}

	public Command createCommand()
	{

		return new Command();
	}

	public boolean getCommand(String context, Command command)
	{
		context = context.toLowerCase();
		int pos = context.indexOf("sleeplg"); // sleeplg.sleep(2000) //
		// sleeplg.sleep 2000
		if (pos < 0)
		{
			return false;
		}
		// 返回值
		int pos2 = context.indexOf("=");
		if ((pos2 >= 0) && (pos2 < pos))
		{
			command.returnName = context.substring(0, pos2);
			command.returnName = command.returnName.trim();
		}
		else
		{
			command.returnName = "";
		}

		// 函数名
		CommandId cmd = CommandId.cmdIf;
		String strCmd = "";
		int left = context.indexOf(".", pos + 1);
		int right = context.indexOf("(", left + 1);
		if (right < -1)
		{
			right = context.indexOf(" ", left + 1);
		}

		if (right > left)
		{
			strCmd = context.substring(left + 1, right);
		}
		else
		{
			strCmd = context.substring(left + 1);
		}

		strCmd = strCmd.trim();

		if ("sleep".equals(strCmd))
		{
			cmd = CommandId.cmdSleep;
		}
		else if ("if0".equals(strCmd))
		{
			cmd = CommandId.cmdIf0;
		}
		else if ("if1".equals(strCmd))
		{
			cmd = CommandId.cmdIf1;
		}
		else if ("if2".equals(strCmd))
		{
			cmd = CommandId.cmdIf2;
		}
		else if ("if3".equals(strCmd))
		{
			cmd = CommandId.cmdIf3;
		}
		else if ("if".equals(strCmd))
		{
			cmd = CommandId.cmdIf;
		}
		else if ("ifex".equals(strCmd))
		{
			cmd = CommandId.cmdIfEx;
		}
		else if ("sif".equals(strCmd))
		{
			cmd = CommandId.cmdSIf;
		}
		else if ("use_makeservicevir".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_MakeServiceVir;
		}
		else if ("use_getassociatedata".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_GetAssociateData;
		}
		else if ("use_setassociatedata".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_SetAssociateData;
		}
		else if ("use_reqvirtualsrv".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_ReqVirtualSrv;
		}
		else if ("use_gettranstarget".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_GetTransTarget;
		}
		else if ("use_blindtransfercall".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_BlindTransferCall;
		}
		else if ("use_withdrawprelock".equals(strCmd))
		{
			cmd = CommandId.cmdUSE_WithdrawPreLock;
		}
		else
		{
			return false;
		}

		command.id = cmd;
		// 参数
		String param = "";
		left = context.indexOf("(", pos);
		right = context.indexOf(")", left);
		if (right > left)
		{
			param = context.substring(left + 1, right);
		}
		else
		{
			left = context.indexOf(" ", pos);
			if ((left > -1) && (left < context.length() - 1))
			{
				param = context.substring(left + 1);
			}
		}

		param = param.trim();
		if (!"".equals(param))
		{
			return splitContextParam(param, command);
		}

		return true;
	}

	boolean splitContextParam(String param, Command command)
	{
		// szParam = m_a, m_b
		// szParam = m_a, "t1", m_b, "t2,3" , "t3, 4"
		// szParam = "m_a", "t1", m_b
		// szParam = "m, ; , a", "t1", m_b
		// 不支持 szParam = "m, ; \" a"
		// ////////////////////// //
		int pos = 0;
		int pos2 = 0;
		String subParam = "";
		boolean isConst = false;
		CommandParam scp = new CommandParam();
		while (pos >= 0)
		{
			subParam = "";
			isConst = false;
			int posQuot = param.indexOf("\"", pos); // 引号
			int posComma = param.indexOf(",", pos); // 逗号
			if ((posComma > 0) && ((posComma < posQuot) || (posQuot < 0)))
			{
				subParam = param.substring(pos, posComma);
				pos = posComma + 1;
			}
			else if ((posComma > 0) && ((posComma > posQuot) && (posQuot < 0)))
			{
				pos2 = param.indexOf("\"", posComma);
				if (pos2 < 0)
				{
					return false;
				}

				subParam = param.substring(pos, pos2);
				isConst = true;
				pos = pos2 + 1;
			}
			else if ((posComma > 0) && ((posComma > posQuot) && (posQuot >= 0)))
			{
				pos2 = param.indexOf("\"", posQuot + 1);
				if (pos2 < posQuot)
				{
					return false;
				}

				subParam = param.substring(pos, pos2 + 1);
				isConst = true;

				// //校正逗号位置
				posComma = param.indexOf(",", pos2);
				if (posComma > 0)
				{
					pos = posComma + 1;
				}
				else
				{
					pos = posComma;
				}
			}
			else if ((posComma < 0) && (posQuot >= 0))
			{
				pos2 = param.indexOf("\"", posQuot + 1);
				if (pos2 < posQuot)
				{
					return false;
				}

				subParam = param.substring(pos, pos2 + 1);
				isConst = true;

				// //校正逗号位置
				posComma = param.indexOf(",", pos2);
				if (posComma > 0)
				{
					pos = posComma + 1;
				}
				else
				{
					pos = posComma;
				}
			}
			else if ((posComma < 0) && (posQuot < 0))
			{
				subParam = param.substring(pos);
				pos = posComma;
			}
			else
			{
				return false;
			}

			subParam = subParam.trim();
			if (isConst)
			{
				scp.name = subParam.substring(1, subParam.length() - 1);
			}
			else
			{
				scp.name = subParam;
			}

			scp.isConst = isConst;
			scp.value = scp.name;
			command.params.add(scp);
			scp = new CommandParam();
			// TRACE("{%s[%d]}\n",scp.name, scp.isConst);
		}

		return true;
	}
}
