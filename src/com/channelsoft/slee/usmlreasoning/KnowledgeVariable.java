package com.channelsoft.slee.usmlreasoning;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.MyDateFormat;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.util.Constant;

// ****************************************
public class KnowledgeVariable implements java.io.Serializable
{
	public enum VarType
	{
		Currency, Date, Float, Int, PronouncingLanguage, String, TelNumber, Time, USMLEvent
	};

	static NumberFormat currencyFormat = NumberFormat
			.getCurrencyInstance(Locale.CHINA);// 中国货币

	// MyDateFormat的格式说明：yyyy.MM.dd HH:mm:ss:SSS
	static MyDateFormat dateFormat = new MyDateFormat("yyyyMMdd");

	/**
	 * 
	 */
	private static final long serialVersionUID = 8237313694426779551L;

	static MyDateFormat timeFormat = new MyDateFormat("HHmmss");

	static public VarType convertStringToVarType(String value) throws Exception
	{
		if ("String".equals(value))
		{
			return VarType.String;
		}
		if ("USMLEvent".equals(value))
		{
			return VarType.USMLEvent;
		}
		if ("TelNumber".equals(value))
		{
			return VarType.TelNumber;
		}
		if ("PronouncingLanguage".equals(value))
		{
			return VarType.PronouncingLanguage;
		}
		if ("Date".equals(value))
		{
			return VarType.Date;
		}
		if ("Time".equals(value))
		{
			return VarType.Time;
		}
		if ("Int".equals(value))
		{
			return VarType.Int;
		}
		if ("Currency".equals(value))
		{
			return VarType.Currency;
		}
		if ("Float".equals(value))
		{
			return VarType.Float;
		}
		else
		{
			throw new MyException("没有此类型的知识变量！");
		}
	}

	public String codeName;

	public double currencyValue;

	public Calendar datetime;

	public double doubleValue;

	public int intValue;

	public String strValue;// String TelNumber USEEvent

	StringWrapper valueWrapper = new StringWrapper();

	public String varName;

	public VarType varType;

	public KnowledgeVariable()
	{
		intValue = 0;
		doubleValue = 0;
		datetime = Calendar.getInstance();

	}

	public void cloneVar(KnowledgeVariable sourceVar)
	{
		varType = sourceVar.varType;
		currencyValue = sourceVar.currencyValue;
		datetime.setTimeInMillis(sourceVar.datetime.getTimeInMillis());
		doubleValue = sourceVar.doubleValue;
		intValue = sourceVar.intValue;
		strValue = sourceVar.strValue;
	}

	public void copy2Variant(Variant dest) throws Exception
	{
		switch (varType)
		{
		case String:
		case USMLEvent:
		case TelNumber:
		case PronouncingLanguage:
		{
			dest.setValue(strValue);
			break;
		}

		case Currency:
		{
			dest.setValue(currencyValue);
			break;
		}
		case Float:
		{
			dest.setValue(doubleValue);
			break;
		}

		case Date:
		case Time:
		{
			dest.setValue(datetime.getTime());
			break;
		}

		case Int:
		{
			dest.setValue(intValue);
			break;
		}

		default:
		{
			dest.init();
		}
		}
	}

	public void copyVar(KnowledgeVariable sourceVar)
	{
		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber)
				|| (varType == VarType.PronouncingLanguage))
		{
			strValue = sourceVar.strValue;
		}
		if (varType == VarType.Date)
		{
			datetime.setTime(sourceVar.datetime.getTime());
		}
		if (varType == VarType.Time)
		{
			datetime.setTime(sourceVar.datetime.getTime());
		}
		if (varType == VarType.Currency)
		{
			currencyValue = sourceVar.currencyValue;

		}
		if (varType == VarType.Int)
		{
			intValue = sourceVar.intValue;
		}
		if (varType == VarType.Float)
		{
			this.doubleValue = sourceVar.doubleValue;
		}

	}

	public void fillInputString(StringBuilder buff)
	{
		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber))
		{
			buff.append(" = ");
			ServiceNode.translateString2SourceCode(buff, strValue);
		}
		else if (varType == VarType.Date)
		{
			buff.append(" = new Date(");
			buff.append(datetime.get(Calendar.YEAR));
			buff.append(", ");
			buff.append(datetime.get(Calendar.MONTH));
			buff.append(", ");
			buff.append(datetime.get(Calendar.DAY_OF_MONTH));
			buff.append("); ");
		}
		else if (varType == VarType.Time)
		{
			buff.append(" = new Date(");
			buff.append(datetime.get(Calendar.YEAR));
			buff.append(", ");
			buff.append(datetime.get(Calendar.MONTH));
			buff.append(", ");
			buff.append(datetime.get(Calendar.DAY_OF_MONTH));
			buff.append(", ");
			buff.append(datetime.get(Calendar.HOUR_OF_DAY));
			buff.append(", ");
			buff.append(datetime.get(Calendar.MINUTE));
			buff.append(", ");
			buff.append(datetime.get(Calendar.SECOND));
			buff.append("); ");
		}
		else if (varType == VarType.Currency)
		{
			buff.append(" = ");
			buff.append(currencyValue);
			buff.append(";");
		}
		else if (varType == VarType.Int)
		{
			buff.append(" = ");
			buff.append(intValue);
			buff.append(";");
		}
		else if (varType == VarType.Float)
		{
			buff.append(" = ");
			buff.append(doubleValue);
			buff.append(";");
		}
	}

	public boolean getDateValue(IntWrapper year, IntWrapper month,
			IntWrapper day)
	{
		if (varType == VarType.Date)
		{
			year.value = datetime.get(Calendar.YEAR);
			month.value = datetime.get(Calendar.MONTH) + 1;
			day.value = datetime.get(Calendar.DAY_OF_MONTH);
			return true;
		}
		return false;
	}

	public boolean getTimeValue(IntWrapper hour, IntWrapper minute,
			IntWrapper second)
	{
		if (this.varType == VarType.Time)
		{
			hour.value = datetime.get(Calendar.HOUR_OF_DAY);
			minute.value = datetime.get(Calendar.MINUTE);
			second.value = datetime.get(Calendar.SECOND);
			return true;
		}
		return false;
	}

	public String getValue()
	{
		if (getValue(valueWrapper))
		{
			return valueWrapper.value;
		}

		return null;
	}

	public boolean getValue(StringWrapper value)
	{
		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber)
				|| (varType == VarType.PronouncingLanguage))
		{
			value.value = strValue;
			return true;
		}
		if (varType == VarType.Date)
		{
			synchronized (dateFormat)
			{
				value.value = dateFormat.format(datetime.getTime());
			}
			return true;
		}
		if (varType == VarType.Time)
		{
			synchronized (timeFormat)
			{
				value.value = timeFormat.format(datetime.getTime());
			}
			return true;

		}
		if (varType == VarType.Currency)
		{
			synchronized (currencyFormat)
			{
				value.value = Constant.CHINA_CURRENCY + currencyValue;
				return true;// 返回标准货币格式
			}
		}
		if (varType == VarType.Int)
		{
			value.value = "" + intValue;
			return true;
		}
		if (varType == VarType.Float)
		{
			value.value = "" + doubleValue;
			return true;
		}
		return true;
	}

	public int getValueInt()
	{
		if (varType == VarType.Int)
		{
			return intValue;
		}
		else
		{
			return 0;
		}
	}

	public void setPronLanguageTypeValue(String szPronLang)
	{
		if (varType == VarType.PronouncingLanguage)
		{
			strValue = szPronLang;
		}
	}

	public boolean setUSMLEventValue(int nEventType)

	{
		if (varType == VarType.USMLEvent)
		{
			strValue = Constant.transEventToString(nEventType, true);
			return true;
		}
		return false;
	}

	public boolean setValue(int nIntValue)
	{
		if (varType == VarType.Int)
		{
			intValue = nIntValue;
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean setValue(String szString)
	{

		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber)
				|| (varType == VarType.PronouncingLanguage))
		{
			strValue = szString;
			return true;
		}
		if (varType == VarType.Date)
		{
			if ((szString == null) || szString.equals(""))
			{
				datetime.setTimeInMillis(0);
				return true;
			}

			synchronized (dateFormat)
			{
				try
				{
					Date t = new Date();
					t = dateFormat.parse(szString);
					datetime.setTime(t);
				}
				catch (ParseException e)
				{
				}

				return true;
			}

		}
		if (varType == VarType.Time)
		{
			if ((szString == null) || szString.equals(""))
			{
				datetime.setTimeInMillis(0);
				return true;
			}

			synchronized (timeFormat)
			{

				try
				{
					Date t = new Date();
					t = timeFormat.parse(szString);
					datetime.setTime(t);
				}
				catch (ParseException e)
				{
				}

				return true;
			}
		}
		if (varType == VarType.Currency)
		{
			if ((szString == null) || szString.equals(""))
			{
				currencyValue = 0;
				return true;
			}

			if (szString.startsWith(Constant.CHINA_CURRENCY))// 标准货币格式
			{
				synchronized (currencyFormat)
				{
					try
					{
						currencyValue = currencyFormat.parse(szString)
								.doubleValue();
					}
					catch (ParseException e)
					{
						return false;
					}

					return true;
				}
			}
			else
			{// 一般字符串格式
				szString = szString.replace(",", "");

				try
				{
					currencyValue = Double.parseDouble(szString);
				}
				catch (Exception e)
				{
					currencyValue = 0;
				}

				return true;
			}

		}
		if (varType == VarType.Int)
		{
			if ((szString == null) || szString.equals(""))
			{
				intValue = 0;
				return true;
			}

			try
			{
				intValue = Integer.parseInt(szString);
			}
			catch (Exception e)
			{
				intValue = 0;
			}

			return true;
		}
		if (varType == VarType.Float)
		{
			if ((szString == null) || szString.equals(""))
			{
				doubleValue = 0;
				return true;
			}

			try
			{
				doubleValue = Double.parseDouble(szString);
			}
			catch (Exception e)
			{
				doubleValue = 0;
			}

			return true;
		}

		return false;
	}

	public void setValue(Variant newValue) throws Exception
	{
		switch (varType)
		{
		case String:
		case USMLEvent:
		case TelNumber:
		case PronouncingLanguage:
		{
			if (newValue.varType == Variant.VarType.BOOLEAN)
			{
				strValue = (newValue.boolValue) ? "-1" : "0";
			}
			else
			{
				strValue = newValue.getString();
			}
			break;
		}

		case Currency:
		case Float:
		{
			currencyValue = doubleValue = newValue.getDouble();
			break;
		}

		case Date:
		case Time:
		{
			datetime.setTime(newValue.getDate());
			break;
		}

		case Int:
		{
			intValue = (int) newValue.getDouble();
			break;
		}
		}

	}

	public void setVarValue(Object value)
	{
		if (value == null)
		{
			intValue = 0;
			doubleValue = 0;
			strValue = "";
			currencyValue = 0;
			datetime.setTimeInMillis(0);
		}

		if (value instanceof String)
		{
			setValue((String) value);
		}
		else if (value instanceof Double)
		{
			currencyValue = ((Double) value).doubleValue();
			datetime.setTimeInMillis(((Double) value).longValue());
			doubleValue = ((Double) value).doubleValue();
			intValue = ((Double) value).intValue();
			strValue = value.toString();
		}
		else
		{
			setValue(value.toString());
		}
	}

	public boolean valueEqual(String szString)
	{

		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber)
				|| (varType == VarType.PronouncingLanguage))
		{
			if (strValue.compareTo(szString) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (varType == VarType.Date)
		{
			synchronized (dateFormat)
			{
				// yyyy.MM.dd HH:mm:ss:SSS
				String szTime = dateFormat.format(this.datetime.getTime());
				if (szTime.compareTo(szString) == 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}

		}
		if (varType == VarType.Time)
		{
			synchronized (timeFormat)
			{
				// yyyy.MM.dd HH:mm:ss:SSS
				String szTime = timeFormat.format(this.datetime.getTime());
				if (szTime.compareTo(szString) == 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		if (varType == VarType.Currency)
		{
			if (szString.startsWith(Constant.CHINA_CURRENCY))// 货币格式
			{
				synchronized (currencyFormat)
				{
					try
					{
						return ((int) (currencyValue * 100) == (int) (currencyFormat
								.parse(szString).doubleValue() * 100));
					}
					catch (ParseException e)
					{
						return false;
					}
				}
			}
			else
			// 一般格式
			{
				szString = szString.replace(",", "");
				try
				{
					return ((int) (currencyValue * 100) == (int) (Double
							.parseDouble(szString) * 100));
				}
				catch (Exception e)
				{
					return false;
				}
			}

		}
		if (varType == VarType.Int)
		{
			try
			{
				return (intValue == Integer.parseInt(szString));
			}
			catch (Exception e)
			{
				return false;
			}
		}
		if (varType == VarType.Float)
		{
			try
			{
				return (doubleValue == Double.parseDouble(szString));
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return false;
	}

	// -------------------------------------------
	public boolean valueIn(String szString)
	{
		if (varType == VarType.String)
		{
			String szDest;
			szDest = String.format("%1$s,", strValue);
			if (szString.indexOf(szDest) >= 0)
			{
				return true;
			}

			szDest = String.format(",%1$s,", strValue);
			if (szString.indexOf(szDest) >= 0)
			{
				return true;
			}

			szDest = String.format(",%1$s", strValue);
			if (szString.indexOf(szDest) >= 0)
			{
				return true;
			}
		}
		return false;
	}

	// -----------------------------------------------
	// 变量值小于传入参数的值
	public boolean valueLess(String szString)
	{
		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber)
				|| (varType == VarType.PronouncingLanguage))

		{
			if (strValue.compareTo(szString) < 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (varType == VarType.Date)
		{
			synchronized (dateFormat)
			{
				String szTime = dateFormat.format(datetime.getTime());
				if (szTime.compareTo(szString) < 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}

		}
		if (varType == VarType.Time)
		{
			synchronized (timeFormat)
			{
				String szTime = timeFormat.format(datetime.getTime());
				if (szTime.compareTo(szString) < 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		if (varType == VarType.Currency)
		{
			if (szString.startsWith(Constant.CHINA_CURRENCY))
			{
				synchronized (currencyFormat)
				{
					try
					{
						return (currencyValue < currencyFormat.parse(szString)
								.doubleValue());
					}
					catch (ParseException e)
					{
						return currencyValue < 0;
					}
				}
			}
			else
			{
				szString = szString.replace(",", "");
				try
				{
					return (currencyValue < Double.parseDouble(szString));
				}
				catch (Exception e)
				{
					return currencyValue < 0;
				}
			}
		}
		if (varType == VarType.Int)
		{
			try
			{
				return (intValue < Integer.parseInt(szString));
			}
			catch (Exception e)
			{
				return intValue < 0;
			}
		}
		if (varType == VarType.Float)
		{
			try
			{
				return (doubleValue < Double.parseDouble(szString));
			}
			catch (Exception e)
			{
				return doubleValue < 0;
			}
		}

		return false;
	}

	// *******变量值大于传入参数的值****************
	public boolean valueMore(String szString)
	{
		if ((varType == VarType.String) || (varType == VarType.USMLEvent)
				|| (varType == VarType.TelNumber)
				|| (varType == VarType.PronouncingLanguage))
		{
			if (strValue.compareTo(szString) > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if (varType == VarType.Date)
		{
			synchronized (dateFormat)
			{
				String szTime = dateFormat.format(datetime.getTime());
				if (szTime.compareTo(szString) > 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		if (varType == VarType.Time)
		{
			synchronized (timeFormat)
			{
				String szTime = timeFormat.format(datetime.getTime());
				if (szTime.compareTo(szString) > 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		if (varType == VarType.Currency)
		{
			if (szString.startsWith(Constant.CHINA_CURRENCY))
			{
				synchronized (currencyFormat)
				{
					try
					{
						return (currencyValue > currencyFormat.parse(szString)
								.doubleValue());
					}
					catch (ParseException e)
					{
						return currencyValue > 0;
					}

				}
			}
			else
			{
				szString = szString.replace(",", "");
				try
				{
					return (currencyValue > Double.parseDouble(szString));
				}
				catch (Exception e)
				{
					return currencyValue > 0;
				}
			}
		}
		if (varType == VarType.Int)
		{
			try
			{
				return (intValue > Integer.parseInt(szString));
			}
			catch (Exception e)
			{
				return intValue > 0;
			}
		}
		if (varType == VarType.Float)
		{
			try
			{
				return (doubleValue > Double.parseDouble(szString));
			}
			catch (Exception e)
			{
				return doubleValue > 0;
			}
		}

		return false;
	}
}
