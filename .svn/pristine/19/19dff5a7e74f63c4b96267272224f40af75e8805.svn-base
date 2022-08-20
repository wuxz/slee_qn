package com.channelsoft.slee.channelmanager;

import java.util.Vector;

import com.channelsoft.reusable.util.BooleanWrapper;
import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.util.Constant;

public class TTSFile
{

	Vector<String> arrayTVM = new Vector<String>();

	public boolean addVoiceFile(String fileName)
	{
		return addVoiceName(fileName);
	}

	// ===========================
	boolean addVoiceName(String szVoiceFile)
	{
		arrayTVM.addElement(szVoiceFile);
		return true;
	}

	// ======================================
	boolean transFromDate(int year, int month, int day, boolean thirdParty)
	{
		String szTemp;
		if (thirdParty == true)
		{
			szTemp = String.format("TTS_date:%1$04d%2$02d%3$02d", year, month,
					day);
			addVoiceFile(szTemp);
			return true;
		}
		if (year != 0)
		{
			szTemp = String.format("%1$d", year);
			transFromDigit(szTemp, false);
			addVoiceName(Constant.VFILE_Year);
		}
		if (month != 0)
		{
			transFromInt(month, false);
			addVoiceName(Constant.VFILE_Mon);
		}
		if (day != 0)
		{
			transFromInt(day, false);
			addVoiceName(Constant.VFILE_Day);
		}
		return true;
	}

	// =====================
	boolean transFromDigit(String sValue, boolean bThirdParty)
	{
		String szTemp;
		if (bThirdParty == true)
		{
			szTemp = String.format("TTS_string:%1$s", sValue);
			addVoiceFile(szTemp);
			return true;
		}
		try
		{
			char cChar;
			for (int i = 0; i < sValue.length(); i++)
			{
				cChar = sValue.charAt(i);
				switch (cChar)
				{
				case '1':
					addVoiceName(Constant.VFILE_1);
					break;
				case '2':
					addVoiceName(Constant.VFILE_2);
					break;
				case '3':
					addVoiceName(Constant.VFILE_3);
					break;
				case '4':
					addVoiceName(Constant.VFILE_4);
					break;
				case '5':
					addVoiceName(Constant.VFILE_5);
					break;
				case '6':
					addVoiceName(Constant.VFILE_6);
					break;
				case '7':
					addVoiceName(Constant.VFILE_7);
					break;
				case '8':
					addVoiceName(Constant.VFILE_8);
					break;
				case '9':
					addVoiceName(Constant.VFILE_9);
					break;
				case '0':
					addVoiceName(Constant.VFILE_0);
					break;
				// case '.':
				// AddVoiceName(VFILE_Point);
				// break;
				case 'a':
				case 'A':
					addVoiceName(Constant.VFILE_A);
					break;
				case 'b':
				case 'B':
					addVoiceName(Constant.VFILE_B);
					break;
				case 'c':
				case 'C':
					addVoiceName(Constant.VFILE_C);
					break;
				case 'd':
				case 'D':
					addVoiceName(Constant.VFILE_D);
					break;
				case 'e':
				case 'E':
					addVoiceName(Constant.VFILE_E);
					break;
				case 'f':
				case 'F':
					addVoiceName(Constant.VFILE_F);
					break;
				case 'g':
				case 'G':
					addVoiceName(Constant.VFILE_G);
					break;

				case 'h':
				case 'H':
					addVoiceName(Constant.VFILE_H);
					break;
				case 'i':
				case 'I':
					addVoiceName(Constant.VFILE_I);
					break;
				case 'j':
				case 'J':
					addVoiceName(Constant.VFILE_J);
					break;
				case 'k':
				case 'K':
					addVoiceName(Constant.VFILE_K);
					break;
				case 'l':
				case 'L':
					addVoiceName(Constant.VFILE_L);
					break;
				case 'm':
				case 'M':
					addVoiceName(Constant.VFILE_M);
					break;
				case 'n':
				case 'N':
					addVoiceName(Constant.VFILE_N);
					break;

				case 'o':
				case 'O':
					addVoiceName(Constant.VFILE_O);
					break;
				case 'p':
				case 'P':
					addVoiceName(Constant.VFILE_P);
					break;
				case 'q':
				case 'Q':
					addVoiceName(Constant.VFILE_Q);
					break;
				case 'r':
				case 'R':
					addVoiceName(Constant.VFILE_R);
					break;
				case 's':
				case 'S':
					addVoiceName(Constant.VFILE_S);
					break;
				case 't':
				case 'T':
					addVoiceName(Constant.VFILE_T);
					break;

				case 'u':
				case 'U':
					addVoiceName(Constant.VFILE_U);
					break;
				case 'v':
				case 'V':
					addVoiceName(Constant.VFILE_V);
					break;
				case 'w':
				case 'W':
					addVoiceName(Constant.VFILE_W);
					break;
				case 'x':
				case 'X':
					addVoiceName(Constant.VFILE_X);
					break;
				case 'y':
				case 'Y':
					addVoiceName(Constant.VFILE_Y);
					break;
				case 'z':
				case 'Z':
					addVoiceName(Constant.VFILE_Z);
					break;
				default:
					return false;
				}
			}
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	// =====================================================
	boolean transFromInt(double value, boolean thirdParty)
	{
		String szTemp;
		if (thirdParty == true)
		{
			szTemp = String.format("TTS_int:%1$.0f", Math.floor(value));
			addVoiceFile(szTemp);
			return true;
		}
		try
		{
			if (Math.floor(value) == 0)
			{
				transFromDigit("0", false);
				return true;
			}
			String strValue;
			strValue = String.format("%1$016.0f", Math.floor(value));
			if (strValue.length() > 16)// 最多处理到千万亿
				return false;

			String szBaseNumber1 = strValue.substring(12, 16);
			String szBaseNumber2 = strValue.substring(8, 12);
			String szBaseNumber3 = strValue.substring(4, 8);
			String szBaseNumber4 = strValue.substring(0, 4);
			BooleanWrapper zero = new BooleanWrapper(true);
			BooleanWrapper firstNum = new BooleanWrapper(true);
			if (transBaseIntValue(szBaseNumber4, zero, firstNum) == true)
			{
				addVoiceName(Constant.VFILE_TenThousand);
				addVoiceName(Constant.VFILE_TenMillion);
			}
			if (transBaseIntValue(szBaseNumber3, zero, firstNum) == true)
			{
				addVoiceName(Constant.VFILE_TenMillion);
			}
			if (transBaseIntValue(szBaseNumber2, zero, firstNum) == true)
			{
				addVoiceName(Constant.VFILE_TenThousand);
			}
			transBaseIntValue(szBaseNumber1, zero, firstNum);

		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10120003, e);
			return false;
		}
		return true;
	}

	// ==============
	boolean transBaseIntValue(String szValue, BooleanWrapper zero,
			BooleanWrapper firstNum)
	{
		boolean returnValue = false;
		try
		{
			int intValue;
			String szString;
			for (int i = 0; i < szValue.length(); i++)
			{
				szString = szValue.substring(i, i + 1);
				intValue = Integer.parseInt(szString);
				if (intValue != 0)
				{
					returnValue = true;
					if ((zero.value == true) && (firstNum.value == false))
					{
						addVoiceName(Constant.VFILE_0);
					}

					zero.value = false;
					if (!((i == 2) && (firstNum.value == true) && (intValue == 1)))
						transFromDigit(szString, false);

					firstNum.value = false;
					if (i == 0)
						addVoiceName(Constant.VFILE_Thousand);
					else if (i == 1)
						addVoiceName(Constant.VFILE_Hundred);
					else if (i == 2)
						addVoiceName(Constant.VFILE_Ten);
					else
						;
				}
				else
				{
					zero.value = true;
				}
			}

		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10120003, e);
			return false;
		}
		return returnValue;
	}

	// =====================
	boolean transFromFloat(double value, boolean thirdParty)
	{
		String szTemp;
		if (thirdParty == true)
		{
			szTemp = String.format("TTS_float:%1$.3f", value);
			addVoiceFile(szTemp);
			return true;
		}
		try
		{
			String sValue;
			sValue = String.format("%1$f", value);
			int nPos = sValue.indexOf('.');
			String szAfter = sValue.substring(nPos + 1);
			transFromInt(value, false);
			for (int i = szAfter.length(); i > 0; i--)
			{
				if (szAfter.charAt(i - 1) != '0')
				{
					break;
				}
				else
				{
					szAfter = szAfter.substring(0, i - 1);
				}
			}
			// if(szAfter.GetLength() == 0)//小数点后至少要报一个零
			// szAfter = "0";
			if (szAfter.length() != 0)
			{
				addVoiceName(Constant.VFILE_Point);
				transFromDigit(szAfter, false);
			}
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10120003, e);
			return false;
		}
		return true;
	}

	// ===========================
	boolean transFromTelNum(String szTelNum, boolean thirdParty)
	{
		String szTemp;
		if (thirdParty == true)
		{
			szTemp = String.format("TTS_string:%1$s", szTelNum);
			addVoiceFile(szTemp);
			return true;
		}
		String szNumber = szTelNum;
		char cChar;
		for (int i = 0; i < szNumber.length(); i++)
		{
			cChar = szNumber.charAt(i);
			if ('1' == cChar)
			{
				addVoiceName(Constant.VFILE_Yao);
			}
			else
			{
				if (false == transFromDigit(String.valueOf(cChar), false))
					return false;
			}
		}

		return true;
	}

	// =================================================
	boolean transFromMoney(double value, boolean thirdParty)
	{
		String szTemp;
		if (thirdParty == true)
		{
			szTemp = String.format("TTS_currency:%1$f", value);
			addVoiceFile(szTemp);
			return true;
		}
		try
		{
			if (value == 0)
			{
				transFromDigit("0", false);
				addVoiceName(Constant.VFILE_Yuan);
				transFromDigit("0", false);
				addVoiceName(Constant.VFILE_Jiao);
				transFromDigit("0", false);
				addVoiceName(Constant.VFILE_Fen);
			}
			if (value < 0)
			{
				addVoiceName(Constant.VFILE_Negative);
				value = -value;
			}

			String sValue;
			sValue = String.format("%1$f", value);
			int nPos = sValue.indexOf('.');
			String szAfter = sValue.substring(nPos + 1);
			if (value >= 1)
			{
				transFromInt(value, false);
				addVoiceName(Constant.VFILE_Yuan);
			}

			try
			{
				char cJiao = szAfter.charAt(0);
				char cFen = szAfter.charAt(1);
				if (cJiao == '0')
				{
					if ((cFen != '0') && (value >= 1))
						transFromDigit(String.valueOf(cJiao), false);
				}
				else
				{
					transFromDigit(String.valueOf(cJiao), false);
					addVoiceName(Constant.VFILE_Jiao);
				}

				if (cFen != '0')
				{
					transFromDigit(String.valueOf(szAfter.charAt(1)), false);
					addVoiceName(Constant.VFILE_Fen);
				}
			}
			catch (Exception e)
			{
				Log.wException(LogDef.id_0x10120003, e);
			}
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10120003, e);
			return false;
		}
		return true;
	}

	// ================================================
	boolean transFromTime(int hour, int minute, int second, boolean thirdParty)
	{
		String szTemp;
		if (thirdParty == true)
		{
			szTemp = String.format("TTS_time:%1$02d%2$02d%3$02d", hour, minute,
					second);
			addVoiceFile(szTemp);
			return true;
		}
		if (hour == 2)
			addVoiceName(Constant.VFILE_Liang);
		else
			transFromInt(hour, false);
		addVoiceName(Constant.VFILE_Hour);

		transFromInt(minute, false);
		addVoiceName(Constant.VFILE_Minute);

		transFromInt(second, false);
		addVoiceName(Constant.VFILE_Second);

		return true;
	}

	// ===============================
	void assembleTTSFileName(int pronLanguage)
	{
		for (int i = 0; i < arrayTVM.size(); i++)
		{
			arrayTVM.set(i, UnifiedServiceManagement.configData
					.assembleTTSFileName(pronLanguage, arrayTVM.elementAt(i)));
		}
	}

	// =======================================
	void assembleTTSFileNameEx(int pronLanguage)
	{
		String szFile;
		for (int i = 0; i < arrayTVM.size(); i++)
		{
			szFile = arrayTVM.elementAt(i);
			if (szFile.indexOf("TTS_") == 0)
				continue;
			arrayTVM.set(i, UnifiedServiceManagement.configData
					.assembleTTSFileName(pronLanguage, arrayTVM.elementAt(i)));
		}
	}

	// =====================================
	Vector<String> getTVMArray()
	{
		return arrayTVM;
	}
}
