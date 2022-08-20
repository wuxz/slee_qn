/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.channelsoft.reusable.comobj.service.ComputingContext;
import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.InvalidParameterException;
import com.channelsoft.reusable.util.TypeMismatchException;
import com.channelsoft.reusable.util.Variant;

/**
 * 封装对内置VB方法的实现。不包括脚本中自定义的方法。
 * 
 * @author wuxz
 */
public class InternalMethod extends InternalObject
{
	static String curFormaSymbol = NumberFormat.getCurrencyInstance()
			.getCurrency().getSymbol();

	static final int Date_Section_Quarter = 10000;

	static NumberFormat numberFormat = NumberFormat.getInstance();

	static NumberFormat percentFormat = NumberFormat.getPercentInstance();

	static int dateDiff_Month(Date date1, Date date2)
	{
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		int month1 = cld.get(Calendar.MONTH);
		int year1 = cld.get(Calendar.YEAR);
		cld.setTime(date2);
		int month2 = cld.get(Calendar.MONTH);
		int year2 = cld.get(Calendar.YEAR);

		return (year2 - year1) * 12 + month2 - month1;
	}

	static long dateDiff_Other(String interval, Date date1, Date date2)
			throws Exception
	{
		long rNum = 0;

		Calendar cld1 = Calendar.getInstance();
		cld1.setTime(date1);

		Calendar cld2 = Calendar.getInstance();
		cld2.setTime(date2);

		cld1.set(Calendar.MILLISECOND, 0);
		cld2.set(Calendar.MILLISECOND, 0);

		if ((interval.compareToIgnoreCase("w") == 0)
				|| (interval.compareToIgnoreCase("ww") == 0))
		{
			cld1.set(Calendar.HOUR_OF_DAY, 0);
			cld1.set(Calendar.MINUTE, 0);
			cld1.set(Calendar.SECOND, 0);

			cld2.set(Calendar.HOUR_OF_DAY, 0);
			cld2.set(Calendar.MINUTE, 0);
			cld2.set(Calendar.SECOND, 0);

			Date d1 = cld1.getTime();
			Date d2 = cld2.getTime();
			rNum = ((d2.getTime() - d1.getTime()) / 1000);
			rNum = rNum / 60 / 60 / 24 / 7;
		}
		else if ((interval.compareToIgnoreCase("y") == 0)
				|| (interval.compareToIgnoreCase("d") == 0))
		{
			cld1.set(Calendar.HOUR_OF_DAY, 0);
			cld1.set(Calendar.MINUTE, 0);
			cld1.set(Calendar.SECOND, 0);

			cld2.set(Calendar.HOUR_OF_DAY, 0);
			cld2.set(Calendar.MINUTE, 0);
			cld2.set(Calendar.SECOND, 0);

			Date d1 = cld1.getTime();
			Date d2 = cld2.getTime();
			rNum = ((d2.getTime() - d1.getTime()) / 1000);
			rNum = rNum / 60 / 60 / 24;
		}
		else if (interval.compareToIgnoreCase("h") == 0)
		{
			cld1.set(Calendar.MINUTE, 0);
			cld1.set(Calendar.SECOND, 0);

			cld2.set(Calendar.MINUTE, 0);
			cld2.set(Calendar.SECOND, 0);

			Date d1 = cld1.getTime();
			Date d2 = cld2.getTime();
			rNum = ((d2.getTime() - d1.getTime()) / 1000);
			rNum = rNum / 60 / 60;
		}
		else if (interval.compareToIgnoreCase("n") == 0)
		{
			cld1.set(Calendar.SECOND, 0);

			cld2.set(Calendar.SECOND, 0);

			Date d1 = cld1.getTime();
			Date d2 = cld2.getTime();
			rNum = ((d2.getTime() - d1.getTime()) / 1000);

			rNum = rNum / 60;
		}
		else if (interval.compareToIgnoreCase("s") == 0)
		{
			rNum = (date2.getTime() - date1.getTime()) / 1000;
		}
		else
		{
			throw new InvalidParameterException("interval", interval);
		}

		return rNum;
	}

	static int dateDiff_Quarter(Date date1, Date date2)
	{
		Calendar cld = Calendar.getInstance();
		cld.setTime(date1);
		int month1 = cld.get(Calendar.MONTH);
		int year1 = cld.get(Calendar.YEAR);
		cld.setTime(date2);
		int month2 = cld.get(Calendar.MONTH);
		int year2 = cld.get(Calendar.YEAR);

		return (year2 - year1) * 4 + (month2 / 3 - month1 / 3);
	}

	static int dateDiff_WeekOfYear(Date date1, Date date2)
	{
		return (int) (((date2.getTime() / 1000 / 86400 - 3) / 7) - ((date1
				.getTime() / 1000 / 86400 - 3) / 7));
	}

	/**
	 * @param date1
	 *            开始计数的日期
	 * @param date2
	 *            终止计数的日期
	 * @return
	 */
	static int dateDiff_Year(Date date1, Date date2)
	{
		Calendar cld1 = Calendar.getInstance();
		Calendar cld2 = Calendar.getInstance();
		cld1.setTime(date1);
		cld2.setTime(date2);
		if ((cld1.get(Calendar.MONTH) == 11)
				&& (cld1.get(Calendar.DAY_OF_MONTH) == 31)
				&& (cld2.get(Calendar.MONTH) == 0)
				&& (cld2.get(Calendar.DAY_OF_MONTH) == 1)
				&& (cld2.get(Calendar.YEAR) - cld1.get(Calendar.YEAR) == 1))
		{
			return 1;
		}

		return (cld2.get(Calendar.YEAR) - cld1.get(Calendar.YEAR));
	}

	static Variant formatDouble(Variant[] args, boolean isCurrency,
			boolean isPercent, ComputingContext computingContext)
			throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 5))
		{
			throw new InvalidParameterCountException();
		}

		int numDigitsAfterDecimal = (((args.length < 2) || (args[1] == null)) ? -1
				: (int) args[1].getDouble());
		int includeLeadingDigit = (((args.length < 3) || (args[2] == null)) ? -1
				: (int) args[2].getDouble());
		int useParensForNegativeNumbers = (((args.length < 4) || (args[3] == null)) ? 0
				: (int) args[3].getDouble());
		int groupDigits = (((args.length < 5) || (args[4] == null)) ? 1
				: (int) args[4].getDouble());

		String value = "";
		double dbValue = args[0].getDouble();
		NumberFormat format = isPercent ? percentFormat : numberFormat;

		boolean isNegative = (dbValue < 0);
		if (isNegative)
		{
			dbValue = -dbValue;
		}

		String symbol = "";
		if (isCurrency)
		{
			symbol = curFormaSymbol;

		}
		if (isCurrency && (numDigitsAfterDecimal == -1))
		{
			numDigitsAfterDecimal = 2;
		}
		synchronized (format)
		{
			if (numDigitsAfterDecimal != -1)
			{
				format.setMinimumFractionDigits(numDigitsAfterDecimal);
				format.setMaximumFractionDigits(numDigitsAfterDecimal);
			}

			format.setGroupingUsed(groupDigits != 0);

			value = format.format(dbValue);
		}

		if ((includeLeadingDigit == 0) && ((dbValue < 1))
				&& (value.indexOf("0.") != -1))
		{
			value = value.substring(0, value.indexOf("0."))
					+ value.substring(value.indexOf("0.") + 1);
		}

		if (isNegative)
		{
			if (useParensForNegativeNumbers == 0)
			{
				value = symbol + "-" + value;
			}
			else
			{
				value = "(" + symbol + value + ")";
			}
		}
		else
		{

			value = symbol + value;
		}

		return new Variant(value, computingContext);
	}

	static int getDateSection(String interval) throws Exception
	{
		int section = 0;

		if (interval.equalsIgnoreCase("yyyy"))
		{
			section = Calendar.YEAR;
		}
		else if (interval.equalsIgnoreCase("m"))
		{
			section = Calendar.MONTH;
		}
		else if (interval.equalsIgnoreCase("y"))
		{
			section = Calendar.DAY_OF_YEAR;
		}
		else if (interval.equalsIgnoreCase("d"))
		{
			section = Calendar.DAY_OF_MONTH;
		}
		else if (interval.equalsIgnoreCase("w"))
		{
			section = Calendar.DAY_OF_WEEK;
		}
		else if (interval.equalsIgnoreCase("ww"))
		{
			section = Calendar.WEEK_OF_YEAR;
		}
		else if (interval.equalsIgnoreCase("h"))
		{
			section = Calendar.HOUR_OF_DAY;
		}
		else if (interval.equalsIgnoreCase("n"))
		{
			section = Calendar.MINUTE;
		}
		else if (interval.equalsIgnoreCase("s"))
		{
			section = Calendar.SECOND;
		}
		else if (interval.equalsIgnoreCase("q"))
		{
			section = Date_Section_Quarter;
		}
		else
		{
			throw new InvalidParameterException("interval", interval);
		}

		return section;
	}

	/**
	 * 从weekday的返回值反推出正常情况下的weekday
	 * 
	 * @param value
	 * @param firstdayofweek
	 * @return
	 * @throws Exception
	 */
	static int getTrueWeekdayFromWeekday(int value, int firstdayofweek)
			throws Exception
	{
		if ((firstdayofweek < 1) || (firstdayofweek > 7))
		{
			throw new InvalidParameterException("firstdayofweek", ""
					+ firstdayofweek);
		}

		value = (value + firstdayofweek - 1) % 7;
		if (value == 0)
		{
			value = 7;
		}

		return value;
	}

	static int getWeekday(int value, int firstdayofweek) throws Exception
	{
		if ((firstdayofweek < 1) || (firstdayofweek > 7))
		{
			throw new InvalidParameterException("firstdayofweek", ""
					+ firstdayofweek);
		}

		value = (value - firstdayofweek + 8) % 7;
		if (value == 0)
		{
			value = 7;
		}

		return value;
	}

	static String monthName(int month, boolean useLocal) throws Exception
	{
		switch (month)
		{
		case 1:
			return useLocal ? "一月" : "Jan";
		case 2:
			return useLocal ? "二月" : "Feb";
		case 3:
			return useLocal ? "三月" : "Mar";
		case 4:
			return useLocal ? "四月" : "Apr";
		case 5:
			return useLocal ? "五月" : "May";
		case 6:
			return useLocal ? "六月" : "Jun";
		case 7:
			return useLocal ? "七月" : "Jul";
		case 8:
			return useLocal ? "八月" : "Aug";
		case 9:
			return useLocal ? "九月" : "Sep";
		case 10:
			return useLocal ? "十月" : "Oct";
		case 11:
			return useLocal ? "十一月" : "Nov";
		case 12:
			return useLocal ? "十二月" : "Dec";
		default:
			throw new InvalidParameterException("month", "" + month);
		}
	}

	public static String replaceFirst(String src, String find, String replace)
	{
		if ((src == null) || (src.length() == 0) || (find.length() == 0))
		{
			return src;
		}

		int pos = src.indexOf(find);
		if (pos == -1)
		{
			return src;
		}

		pos += find.length();
		return src.substring(0, pos).replace(find, replace)
				+ src.substring(pos);
	}

	static double round(double value, int degree)
	{
		// if (true)
		// {
		return Variant.round(value, degree);
		// }
		// double factor = 1.0;
		// for (int i = 0; i < degree; i++)
		// {
		// factor *= 10;
		// }
		//
		// value *= factor;
		// if (value - (long) value == 0.5)
		// {
		// value = Math.round(value);
		// if (value % 2 == 1)
		// {
		// value -= 1;
		// }
		// }
		//
		// value = Math.round(value);
		//
		// return value / factor;
	}

	public static String strReplace(String src, String find,
			String replaceWith, int beginIndex, int count, int compareMode)
			throws Exception
	{
		if (beginIndex < 1)
		{
			throw new InvalidParameterException("start", "" + beginIndex);
		}

		if (src.equals("") || find.equals("") || (count == 0))
		{
			return src;
		}

		if (beginIndex > src.length())
		{
			return "";
		}

		String ret = src.substring(beginIndex - 1);
		if (compareMode == 0)
		{
			if (count == -1)
			{
				ret = ret.replace(find, replaceWith);
			}
			else
			{

				for (int i = 0; i < count; i++)
				{
					ret = replaceFirst(ret, find, replaceWith);
				}
			}
		}
		else if (compareMode == 1)
		{
			int len = find.length();
			int i = 0;
			int sum = 0;
			if (count == -1)
			{
				while (i <= ret.length() - len)
				{
					String temOri = ret.substring(i, i + len);
					if (temOri.compareToIgnoreCase(find) == 0)
					{
						ret = replaceFirst(ret, temOri, replaceWith);
						i += replaceWith.length();
					}
					else
					{
						i += 1;
					}
				}
			}
			else
			{
				while ((i <= ret.length() - len) && (sum < count))
				{
					String temOri = ret.substring(i, i + len);
					if (temOri.compareToIgnoreCase(find) == 0)
					{
						ret = replaceFirst(ret, temOri, replaceWith);
						i += len;
						sum += 1;
					}
					else
					{
						i += 1;
					}
				}
			}
		}
		else
		{
			throw new InvalidParameterException("compare", "" + compareMode);
		}

		return ret;
	}

	static String typeName(Variant v) throws Exception
	{
		int temp = varType(v);
		switch (temp)
		{
		case 0:
			return "Empty";
		case 1:
			return "Null";
		case 2:
			return "Integer";
		case 3:
			return "Long";
		case 5:
			return "Double";
		case 7:
			return "Date";
		case 8:
			return "String";
		case 9:
			return "Object";
		case 11:
			return "Boolean";
		case 8192 + 12:
			return "Variant()";
		default:
			return "Error";
		}
	}

	static int varType(Variant v) throws Exception
	{
		if (v == null)
		{
			return 1;
		}

		switch (v.varType)
		{
		case ARRAY:
			return 8192 + 12;

		case BOOLEAN:
			return 11;

		case DOUBLE:
			return (Variant.isInteger(v.doubleValue) ? 2 : (Variant
					.isLong(v.doubleValue) ? 3 : 5));

		case DATE:
			return 7;

		case EMPTY:
			return 0;

		case OBJECT:
			return 9;

		case STRING:
			return 8;

		default:
			return 10;
		}
	}

	static String weekdayName(int day) throws Exception
	{
		switch (day)
		{
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			throw new InvalidParameterException("weekday", "" + day);
		}
	}

	static String weekdayName(int day, boolean abbrievate) throws Exception
	{
		return weekdayName(day);
	}

	static String weekdayName(int day, boolean abbrievate, int firstDayofWeek)
			throws Exception
	{
		return weekdayName(getTrueWeekdayFromWeekday(day, firstDayofWeek));
	}

	public Variant abs(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.abs(args[0].getDouble()), computingContext,
				null);
	}

	public Variant array(Variant[] args) throws Exception
	{
		if (args == null)
		{
			throw new InvalidParameterCountException();
		}

		Variant ret = new Variant(computingContext);
		ret.dimVariant(
				(args.length > 0 ? new int[] { args.length - 1 } : null),
				computingContext, false);
		ret.isDimedArray = false;
		for (int i = 0; i < args.length; i++)
		{
			ret.arrayValue[i].setValue(args[i]);
		}

		return ret;
	}

	public Variant asc(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		String value = args[0].toString();
		if (value.length() == 0)
		{
			throw new InvalidParameterException("string", value);
		}

		return new Variant(value.charAt(0), computingContext);
	}

	public Variant atn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.atan(args[0].getDouble()), computingContext,
				null);
	}

	public Variant cbool(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(args[0].getBoolean(), computingContext);
	}

	public Variant cbyte(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double value = args[0].getDouble();
		if ((value < -0.5) || (value >= 255.5))
		{
			throw new InvalidParameterException("expression", "" + value);
		}

		return new Variant(round(value, 0), computingContext);
	}

	public Variant ccur(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double oldValue = args[0].getDouble();
		double value = Math.round(oldValue * 10000) / 10000.0;
		if ((oldValue < 0) && (oldValue * 10000 - value * 10000 == -0.5))
		{
			value -= 0.0001;
		}

		return new Variant(value, computingContext);
	}

	public Variant cdate(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return args[0].convert2Date();
	}

	public Variant cdbl(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		BigDecimal b = new BigDecimal(args[0].getDouble());
		BigDecimal one = new BigDecimal("1");

		return new Variant(b.multiply(one).doubleValue(), computingContext);
	}

	public Variant chr(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		if (args[0].varType == Variant.VarType.EMPTY)
		{
			return new Variant("", computingContext);
		}

		return new Variant("" + ((char) Math.round(args[0].getDouble())),
				computingContext);
	}

	public Variant cint(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double num = args[0].getDouble();
		long r = Math.round(num);
		if (Math.abs(r - num - 0.5) < 0.00000001)
		{
			if (Math.abs(r) % 2 == 1)
			{
				r--;
			}
		}

		return new Variant(r, computingContext);
	}

	public Variant clng(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double value = args[0].getDouble();

		return new Variant(round(value, 0), computingContext);
	}

	public Variant cos(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.cos(args[0].getDouble()), computingContext,
				null);
	}

	// public Variant createobject(Variant[] args) throws Exception
	// {
	// if ((args == null) || (args.length < 1) || (args.length > 2))
	// {
	// throw new InvalidParameterCountException();
	// }
	//
	// Variant ret = new Variant(computingContext);
	// ret
	// .createObject(args[0].toString(), (args.length == 2 ? args[1]
	// .toString() : null), computingContext
	// .getSLEEComponentContext());
	//
	// return ret;
	// }

	public Variant csng(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(5);
		numberFormat.setMinimumFractionDigits(0);
		String ret = null;

		synchronized (numberFormat)
		{
			ret = numberFormat.format(args[0].getDouble());
		}

		double dblValue = Double.parseDouble(ret.replace(",", ""));

		return new Variant(dblValue, computingContext);
	}

	public Variant cstr(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		if (args[0].varType == Variant.VarType.BOOLEAN)
		{
			return new Variant(args[0].boolValue ? "True" : "False",
					computingContext);
		}
		else
		{
			return new Variant(args[0].toString(), computingContext);
		}
	}

	public Variant date(Variant[] args) throws Exception
	{
		if ((args != null) && (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Variant ret = new Variant(calendar.getTime(), computingContext);
		ret.hasTimePart = false;

		return ret;
	}

	public Variant dateadd(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 3))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		Variant now = cdate(new Variant[] { args[2] });
		boolean oldHasDatePart = now.hasDatePart;
		boolean oldHasTimePart = now.hasTimePart;
		calendar.setTime(now.dateValue);

		int section = getDateSection(args[0].toString());
		int num = (int) args[1].getDouble();
		if (section == Date_Section_Quarter)
		{
			calendar.add(Calendar.MONTH, 3 * num);
		}
		else
		{
			calendar.add(section, num);
		}

		Variant ret = new Variant(calendar.getTime(), computingContext);

		switch (section)
		{
		case Calendar.MINUTE:
		case Calendar.HOUR_OF_DAY:
		case Calendar.SECOND:
		{
			ret.hasDatePart = oldHasDatePart;
			break;
		}
		default:
		{
			ret.hasTimePart = oldHasTimePart;
			break;
		}

		}

		return ret;
	}

	public Variant datediff(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 3) || (args.length > 5))
		{
			throw new InvalidParameterCountException();
		}

		String interval = args[0].toString();
		Date date1 = args[1].getDate();
		Date date2 = args[2].getDate();

		if (interval.equalsIgnoreCase("yyyy"))
		{
			return new Variant(dateDiff_Year(date1, date2), computingContext);
		}
		else if (interval.equalsIgnoreCase("m"))
		{
			return new Variant(dateDiff_Month(date1, date2), computingContext);
		}
		else if (interval.equalsIgnoreCase("q"))
		{
			return new Variant(dateDiff_Quarter(date1, date2), computingContext);
		}
		else if (interval.equalsIgnoreCase("ww"))
		{
			return new Variant(dateDiff_WeekOfYear(date1, date2),
					computingContext);
		}
		else
		{
			return new Variant(dateDiff_Other(interval, date1, date2),
					computingContext);
		}
	}

	public Variant datepart(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 2) || (args.length > 4))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[1].getDate());

		int value = 0;
		int section = getDateSection(args[0].toString());
		if (section == Date_Section_Quarter)
		{
			value = calendar.get(Calendar.MONTH);
			value = (value / 3) + 1;
		}
		else
		{
			value = calendar.get(section);
			if (section == Calendar.MONTH)
			{
				value++;
			}
			else if ((section == Calendar.DAY_OF_WEEK) && (args.length > 2))
			{
				int firstdayofweek = (args[2] == null ? 1 : (int) args[2]
						.getDouble());

				value = getWeekday(value, firstdayofweek);
			}
		}

		return new Variant(value, computingContext);
	}

	public Variant dateserial(Variant args[]) throws Exception
	{
		if ((args == null) || (args.length != 3))
		{
			throw new InvalidParameterCountException();
		}

		int year = (int) args[0].getDouble();
		int month = (int) args[1].getDouble();
		int day = (int) args[2].getDouble();
		if ((year < 100) || (year > 9999))
		{
			throw new InvalidParameterException("Year", "" + year);
		}

		Calendar calendar = Calendar.getInstance();

		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Variant ret = new Variant(calendar.getTime(), computingContext);
		ret.hasTimePart = false;

		return ret;
	}

	/**
	 * 认为与cDate()功能相同。
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Variant datevalue(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Variant ret = args[0].convert2Date();
		ret.hasTimePart = false;

		if (!ret.hasDatePart)
		{
			ret = date(new Variant[0]);
		}
		else
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(ret.dateValue);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			ret.dateValue = calendar.getTime();
		}

		return ret;
	}

	public Variant day(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[0].getDate());

		return new Variant(calendar.get(Calendar.DAY_OF_MONTH),
				computingContext);
	}

	public Variant exp(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.exp(args[0].getDouble()), computingContext);
	}

	public Variant filter(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 2) || (args.length > 4))
		{
			throw new InvalidParameterCountException();
		}

		Variant strings = args[0];
		if ((strings.varType != Variant.VarType.ARRAY)
				|| (strings.arrayUBound < 0))
		{
			throw new TypeMismatchException();
		}

		String value = args[1].toString();
		boolean include = ((args.length > 2) && (args[2] != null) ? args[2]
				.getBoolean() : true);
		int compare = ((args.length > 3) && (args[3] != null) ? (int) args[3]
				.getDouble() : 0);

		Vector<String> resultSet = new Vector<String>();

		for (int i = 0; i < strings.arrayValue.length; i++)
		{
			Variant var = strings.arrayValue[i];
			if ((var.varType == Variant.VarType.ARRAY)
					|| (var.varType == Variant.VarType.OBJECT))
			{
				throw new TypeMismatchException();
			}

			String varValue = var.toString();
			if (include)
			{
				if (((compare == 0) && (varValue.indexOf(value) != -1))
						|| ((compare == 1) && (varValue.toLowerCase().indexOf(
								value.toLowerCase()) != -1)))
				{
					resultSet.add(varValue);
				}
			}
			else
			{
				if (((compare == 0) && (varValue.indexOf(value) == -1))
						|| ((compare == 1) && (varValue.toLowerCase().indexOf(
								value.toLowerCase()) == -1)))
				{
					resultSet.add(varValue);
				}
			}
		}

		Variant ret = new Variant(computingContext);
		ret.varType = Variant.VarType.ARRAY;
		ret.arrayUBound = resultSet.size() - 1;
		ret.arrayValue = new Variant[resultSet.size()];
		for (int i = 0; i < ret.arrayValue.length; i++)
		{
			ret.arrayValue[i] = new Variant(resultSet.elementAt(i),
					computingContext);
		}

		return ret;
	}

	public Variant fix(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant((long) (args[0].getDouble()), computingContext);
	}

	public Variant formatcurrency(Variant[] args) throws Exception
	{
		return formatDouble(args, true, false, computingContext);
	}

	public Variant formatdatetime(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}

		int namedFormat = (((args.length < 2) || (args[1] == null)) ? 0
				: (int) args[1].getDouble());

		String value = args[0].getString();
		// if (namedFormat != 0)
		{
			Variant dateVar = args[0].convert2Date();
			Date date = dateVar.getDate();
			value = "";
			if (namedFormat == 0)
			{
				if (dateVar.hasDatePart)
				{
					synchronized (Variant.dateFormat)
					{
						value = Variant.dateFormat.format(date);
					}
				}
				if (dateVar.hasTimePart)
				{
					synchronized (Variant.timeFormat)
					{
						if (!value.equals(""))
						{
							value = value + " ";
						}

						value += Variant.timeFormat.format(date);
					}
				}
			}
			else
			{
				if (namedFormat == 1)
				{
					synchronized (Variant.dateFormatChinese)
					{
						value = Variant.dateFormatChinese.format(date);
					}
				}
				else if (namedFormat == 2)
				{
					synchronized (Variant.dateFormat)
					{
						value = Variant.dateFormat.format(date);
					}
				}
			}
			{
				if (namedFormat == 3)
				{
					synchronized (Variant.timeFormat)
					{
						value = Variant.timeFormat.format(date);
					}
				}
				else if (namedFormat == 4)
				{
					synchronized (Variant.shortTimeFormat)
					{
						value = Variant.shortTimeFormat.format(date);
					}
				}
			}
		}

		return new Variant(value, computingContext);
	}

	public Variant formatnumber(Variant[] args) throws Exception
	{
		return formatDouble(args, false, false, computingContext);
	}

	public Variant formatpercent(Variant[] args) throws Exception
	{
		return formatDouble(args, false, true, computingContext);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.InternalObject#getDefaultPropertyName()
	 */
	@Override
	public String getDefaultPropertyName()
	{
		return null;
	}

	public Variant hex(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double num = args[0].getDouble();
		long r = Math.round(num);
		if (Math.abs(r - num - 0.5) < 0.00000001)
		{
			if (Math.abs(r) % 2 == 1)
			{
				r--;
			}
		}

		String value = String.format("%X", r);
		if ((value.length() == 16)
				&& String.format("%X", r).startsWith("FFFFFFFF"))
		{
			value = value.substring(8);
		}

		return new Variant(value, computingContext);
	}

	public Variant hour(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[0].getDate());

		return new Variant(calendar.get(Calendar.HOUR_OF_DAY), computingContext);
	}

	public Variant iint(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double value = args[0].getDouble();

		return new Variant(
				(value < 0) && !Variant.isLong(value) ? ((long) value) - 1
						: (long) value, computingContext);
	}

	public Variant instr(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 2) || (args.length > 4))
		{
			throw new InvalidParameterCountException();
		}

		int start = (((args[0] == null) || (args.length == 2)) ? 0
				: (int) args[0].getDouble() - 1);
		String string1 = (args.length == 2 ? args[0].toString() : args[1]
				.toString());
		String string2 = (args.length == 2 ? args[1].toString() : args[2]
				.toString());
		int compare = (args.length == 4 ? (int) args[3].getDouble() : 0);

		if ((start < 0) || (start >= string1.length()))
		{
			return new Variant(0, computingContext);
		}

		if ((compare != 0) && (compare != 1))
		{
			throw new InvalidParameterException("compare", "" + compare);
		}

		return new Variant(
				(compare == 0 ? string1.indexOf(string2, start) : string1
						.toUpperCase().indexOf(string2.toUpperCase(), start)) + 1,
				computingContext);
	}

	public Variant instrrev(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 2) || (args.length > 4))
		{
			throw new InvalidParameterCountException();
		}

		String string1 = args[0].toString();
		String string2 = args[1].toString();
		int start = ((args.length > 2) && (args[2] != null)) ? (int) args[2]
				.getDouble() : -1;
		int compare = ((args.length > 3) && (args[3] != null)) ? (int) args[3]
				.getDouble() : 0;

		if (start == -1)
		{
			start = string1.length();
		}
		else
		{
			start -= 1;
		}

		if ((start < 0) || (start > string1.length()))
		{
			return new Variant(0, computingContext);
		}

		if ((compare != 0) && (compare != 1))
		{
			throw new InvalidParameterException("compare", "" + compare);
		}

		return new Variant((compare == 0 ? string1.lastIndexOf(string2, start)
				: string1.toUpperCase().lastIndexOf(string2.toUpperCase(),
						start)) + 1, computingContext);
	}

	public Variant isarray(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(args[0].varType == Variant.VarType.ARRAY,
				computingContext);
	}

	public Variant isdate(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		if (args[0].varType == Variant.VarType.DATE)
		{
			return new Variant(true, computingContext);
		}
		else if (args[0].varType == Variant.VarType.STRING)
		{
			try
			{
				args[0].convert2Date();

				return new Variant(true, computingContext);
			}
			catch (Exception e)
			{
				return new Variant(false, computingContext);
			}
		}

		return new Variant(false, computingContext);
	}

	public Variant isempty(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant((args[0] == null)
				|| (args[0].varType == Variant.VarType.EMPTY), computingContext);
	}

	public Variant isnull(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant((args[0] == null) || (args[0].isNull()),
				computingContext);
	}

	public Variant isnumeric(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		try
		{
			String stemp = args[0].getString().trim();
			Double.parseDouble(stemp);
		}
		catch (Exception e)
		{
			return new Variant(false, computingContext);
		}

		return new Variant(true, computingContext);
	}

	public Variant isobject(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant((args[0] != null) && (args[0].isObject()),
				computingContext);
	}

	public Variant join(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}

		Variant strings = args[0];
		if ((strings.varType != Variant.VarType.ARRAY)
				|| (strings.arrayUBound < 0))
		{
			throw new TypeMismatchException();
		}

		String delimiter = (args.length == 2 ? args[1].toString() : " ");

		String result = "";

		for (int i = 0; i < strings.arrayValue.length; i++)
		{
			result += strings.arrayValue[i].toString();
			if (i < strings.arrayValue.length - 1)
			{
				result += delimiter;
			}
		}

		return new Variant(result, computingContext);
	}

	public Variant lbound(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}
		return new Variant(0, computingContext);
	}

	public Variant lcase(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(args[0].toString().toLowerCase(), computingContext);
	}

	public Variant left(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		String string = args[0].toString();
		int length = (int) args[1].getDouble();
		if (length < 0)
		{
			throw new InvalidParameterException("length", "" + length);
		}

		if (length > string.length())
		{
			length = string.length();
		}

		return new Variant(string.substring(0, length), computingContext);
	}

	public Variant len(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(args[0].toString().length(), computingContext);
	}

	public Variant log(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.log(args[0].getDouble()), computingContext);
	}

	public Variant ltrim(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		String str = args[0].getString();
		while ((str != null) && (str.length() != 0) && (str.charAt(0) == ' '))
		{
			str = str.substring(1);
		}

		return new Variant(str, computingContext);
	}

	public Variant mid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 2) || (args.length > 3))
		{
			throw new InvalidParameterCountException();
		}

		String string = args[0].toString();
		int start = (int) args[1].getDouble() - 1;
		int end = (args.length == 2 ? string.length() : (int) args[2]
				.getDouble()
				+ start);

		if (start < 0)
		{
			throw new InvalidParameterException("start", "" + start);
		}

		if (end < start)
		{
			throw new InvalidParameterException("length", "" + (end - start));
		}

		if ((start >= string.length()))
		{
			return new Variant("", computingContext);
		}

		if (end >= string.length())
		{
			end = string.length();
		}

		return new Variant(string.substring(start, end), computingContext);
	}

	public Variant minute(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[0].getDate());

		return new Variant(calendar.get(Calendar.MINUTE), computingContext);
	}

	public Variant month(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[0].getDate());

		return new Variant(calendar.get(Calendar.MONTH) + 1, computingContext);
	}

	public Variant monthname(Variant args[]) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}

		int month = (int) args[0].getDouble();
		boolean useLocal = ((args.length == 2) && (args[1] != null) ? args[1]
				.getBoolean() : true);

		return new Variant(monthName(month, useLocal), computingContext);
	}

	public Variant msgbox(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.msgBox(args[0].toString());

		return null;
	}

	public Variant now(Variant[] args) throws Exception
	{
		if ((args != null) && (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(new Date(), computingContext);
	}

	public Variant oct(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double num = args[0].getDouble();
		long r = Math.round(num);
		if (Math.abs(r - num - 0.5) < 0.00000001)
		{
			if (Math.abs(r) % 2 == 1)
			{
				r--;
			}
		}

		return new Variant(String.format("%o", r), computingContext);
	}

	public Variant replace(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 3) || (args.length > 6))
		{
			throw new InvalidParameterCountException();
		}

		String expression = args[0].toString();
		String find = args[1].toString();
		String replaceWith = args[2].toString();
		int startPos = ((args.length > 3) && (args[3] != null) ? (int) args[3]
				.getDouble() : 1);
		int count = ((args.length > 4) && (args[4] != null) ? (int) args[4]
				.getDouble() : -1);
		int mode = ((args.length > 5) && (args[5] != null) ? (int) args[5]
				.getDouble() : 0);

		String ret = strReplace(expression, find, replaceWith, startPos, count,
				mode);

		return new Variant(ret, computingContext);
	}

	public Variant right(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		String string = args[0].toString();
		int length = (int) args[1].getDouble();
		if (length > string.length())
		{
			return new Variant(string, computingContext);
		}

		if (length < 0)
		{
			throw new InvalidParameterException("length", "" + length);
		}

		return new Variant(length == 0 ? "" : string.substring(string.length()
				- length), computingContext);
	}

	public Variant rnd(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length > 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.random(), computingContext);
	}

	public Variant round(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}

		double num = args[0].getDouble();
		int degree = (args.length == 2 ? (int) args[1].getDouble() : 0);

		return new Variant(round(num, degree), computingContext);
	}

	public Variant rtrim(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		String str = args[0].getString();
		while ((str != null) && (str.length() != 0)
				&& (str.lastIndexOf(' ') == str.length() - 1))
		{
			str = str.substring(0, str.length() - 1);
		}

		return new Variant(str, computingContext);
	}

	public Variant scriptengine(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant("VBScript", computingContext);
	}

	public Variant scriptenginebuildversion(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(2, computingContext);
	}

	public Variant scriptenginemajorversion(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(1, computingContext);
	}

	public Variant scriptengineminorversion(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(1, computingContext);
	}

	public Variant second(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[0].getDate());

		return new Variant(calendar.get(Calendar.SECOND), computingContext);
	}

	public Variant sgn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		double value = args[0].getDouble();
		int result = (value > 0 ? 1 : (value < 0 ? -1 : 0));

		return new Variant(result, computingContext);
	}

	public void showmsg(Variant[] args) throws Exception
	{
		msgbox(args);
	}

	public Variant sin(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.sin(args[0].getDouble()), computingContext,
				null);
	}

	public void sleep(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		try
		{
			Thread.sleep((int) args[0].getDouble());
		}
		catch (Exception e)
		{
		}
	}

	public Variant space(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		int count = (int) args[0].getDouble();
		if (count < 0)
		{
			throw new InvalidParameterException("number", "" + count);
		}

		String ret = "";
		for (int i = 0; i < count; i++)
		{
			ret += " ";
		}

		return new Variant(ret, computingContext);
	}

	public Variant split(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 4))
		{
			throw new InvalidParameterCountException();
		}

		if ((args[0].varType == Variant.VarType.OBJECT)
				|| (args[0].varType == Variant.VarType.ARRAY)
				|| (args[0].varType == Variant.VarType.EMPTY))
		{
			throw new TypeMismatchException();
		}

		String expression = args[0].toString();
		String delimiter = ((args.length > 1) && (args[1] != null) ? args[1]
				.toString() : " ");
		int count = ((args.length > 2) && (args[2] != null) ? (int) args[2]
				.getDouble() : -1);
		int compare = ((args.length > 3) && (args[3] != null) ? (int) args[3]
				.getDouble() : 0);

		if ((compare != 0) && (compare != 1))
		{
			throw new InvalidParameterException("compare", "" + compare);
		}

		Vector<String> resultSet = new Vector<String>();
		String expNew = (compare == 0 ? expression : expression.toLowerCase());
		delimiter = (compare == 0 ? delimiter : delimiter.toLowerCase());

		int delimLen = delimiter.length();
		if ((expression.length() != 0) && (count != 0))
		{
			if (delimLen == 0)
			{
				resultSet.add(expression);
			}
			else
			{
				int start = 0;

				while (start < expression.length())
				{
					int pos = expNew.indexOf(delimiter, start);
					if (pos != -1)
					{
						resultSet.add(expression.substring(start, pos));
						start = pos + delimLen;

						if ((count != -1) && (resultSet.size() >= count))
						{
							break;
						}
					}
					else
					{
						resultSet.add(expression.substring(start));
						break;
					}
				}

				if (start == expression.length())
				{
					// 分隔符是字符串的结尾，则后面补一个空白。
					resultSet.add("");
				}
			}
		}

		if ((resultSet.size() == 0) && (expression.length() > 0))
		{
			resultSet.add(expression);
		}

		Variant ret = new Variant(computingContext);
		ret.varType = Variant.VarType.ARRAY;

		if (resultSet.size() > 0)
		{
			ret.arrayUBound = resultSet.size() - 1;
			ret.arrayValue = new Variant[resultSet.size()];
		}

		for (int i = 0; i < resultSet.size(); i++)
		{
			ret.arrayValue[i] = new Variant(resultSet.elementAt(i),
					computingContext);
		}

		return ret;
	}

	public Variant sqr(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.sqrt(args[0].getDouble()), computingContext,
				null);
	}

	public Variant strcomp(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 2) || (args.length > 3))
		{
			throw new InvalidParameterCountException();
		}

		int mode = (((args.length > 2) && (args[2] != null)) ? (int) args[2]
				.getDouble() : 0);
		String str1 = args[0].toString();
		String str2 = args[1].toString();

		if ((mode != 0) && (mode != 1))
		{
			throw new InvalidParameterException("compare", "" + mode);
		}

		int ret = (mode == 0 ? str1.compareTo(str2) : str1
				.compareToIgnoreCase(str2));
		ret = (ret > 0 ? 1 : (ret < 0 ? -1 : 0));

		return new Variant(ret, computingContext);
	}

	public Variant string(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		int count = (int) args[0].getDouble();

		String cha = args[1].toString();
		if (args[1].varType == Variant.VarType.DOUBLE)
		{
			int chaValue = (int) args[1].doubleValue % 256;
			byte data[] = new byte[1];
			data[0] = (byte) chaValue;
			cha = new String(data);
		}
		else if (cha.length() > 1)
		{
			cha = cha.substring(0, 1);
		}

		String ret = "";
		for (int i = 0; i < count; i++)
		{
			ret += cha;
		}

		return new Variant(ret, computingContext);
	}

	public Variant strreverse(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		String ret = "";
		String src = args[0].toString();
		for (int i = src.length() - 1; i >= 0; i--)
		{
			ret += src.charAt(i);
		}

		return new Variant(ret, computingContext);
	}

	public Variant tan(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(Math.tan(args[0].getDouble()), computingContext,
				null);
	}

	public Variant time(Variant[] args) throws Exception
	{
		if ((args != null) && (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Variant ret = new Variant(calendar.getTime(), computingContext);
		ret.hasDatePart = false;

		return ret;
	}

	public Variant timer(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(
				(int) ((System.currentTimeMillis() % (1000 * 86400)) / 1000),
				computingContext);
	}

	public Variant timeserial(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 3))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(0);
		calendar.set(Calendar.HOUR_OF_DAY, (int) args[0].getDouble());
		calendar.set(Calendar.MINUTE, (int) args[1].getDouble());
		calendar.set(Calendar.SECOND, (int) args[2].getDouble());
		calendar.set(Calendar.MILLISECOND, 0);

		Variant ret = new Variant(calendar.getTime(), computingContext);
		ret.hasDatePart = false;
		ret.hasTimePart = true;

		return ret;
	}

	/**
	 * 认为与cDate()功能相同。
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Variant timevalue(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return args[0].convert2Date();
	}

	public Variant trim(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(args[0].toString().trim(), computingContext);
	}

	public Variant typename(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(typeName(args[0]), computingContext);
	}

	public Variant ubound(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}

		Variant array = args[0];
		int dimension = (args.length == 2 ? (int) args[1].getDouble() - 1 : 0);
		if ((array.varType != Variant.VarType.ARRAY)
				|| (array.arrayUBound == -1))
		{
			throw new TypeMismatchException();
		}

		for (int i = 0; i < dimension; i++)
		{
			array = array.arrayValue[0];
			if ((array.varType != Variant.VarType.ARRAY)
					|| (array.arrayUBound == -1))
			{
				throw new TypeMismatchException();
			}
		}

		return new Variant(array.arrayUBound, computingContext);
	}

	public Variant ucase(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(args[0].toString().toUpperCase(), computingContext);
	}

	public Variant vartype(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(varType(args[0]), computingContext);
	}

	public Variant weekday(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 2))
		{
			throw new InvalidParameterCountException();
		}

		Variant dvalue = cdate(new Variant[] { args[0] });

		if (dvalue.varType == Variant.VarType.DATE)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dvalue.getDate());

			if (args.length == 1)
			{
				return new Variant(calendar.get(Calendar.DAY_OF_WEEK),
						computingContext);
			}
			else
			{
				int firstdayofweek = (args[1] != null ? (int) args[1]
						.getDouble() : 1);
				int value = calendar.get(Calendar.DAY_OF_WEEK);
				return new Variant(getWeekday(value, firstdayofweek),
						computingContext);
			}
		}
		else
		{
			throw new InvalidParameterException("date", args[0].toString());
		}
	}

	public Variant weekdayname(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length < 1) || (args.length > 3))
		{
			throw new InvalidParameterCountException();
		}

		if (args.length == 1)
		{
			return new Variant(weekdayName((int) args[0].getDouble()),
					computingContext);
		}
		else if (args.length == 2)
		{
			return new Variant(weekdayName((int) args[0].getDouble(), args[1]
					.getBoolean()), computingContext);
		}
		else
		{
			return new Variant(weekdayName((int) args[0].getDouble(), args[1]
					.getBoolean(), (int) args[2].getDouble()), computingContext);
		}
	}

	public Variant year(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(args[0].getDate());

		return new Variant(calendar.get(Calendar.YEAR), computingContext);
	}
}
