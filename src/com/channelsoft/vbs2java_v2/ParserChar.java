package com.channelsoft.vbs2java_v2;

/**
 * 字符工具类。判断vbs中的字符类型。
 * 
 * @author dragon.huang
 */
public class ParserChar
{

	static final String opkeys = "()+-/\\*^=,<>&";

	/**
	 * 是否为词汇分隔符
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isspace(char ch)
	{
		if (ch == ' ' || ch == '\t' || ch == '\r')
		{
			return true;
		}
		return false;
	}

	/**
	 * 是否操作符
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isopkey(char ch)
	{
		return (opkeys.indexOf(ch) != -1);
	}

	/**
	 * 是否为注释
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isnotechar(char ch)
	{
		return (ch == '\'');
	}

	/**
	 * 是否为行结束符
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean islineend(char ch)
	{
		if (ch == '\n')
		{
			return true;
		}
		return false;
	}

	/**
	 * 是否为文件结束符
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isfileend(char ch)
	{
		return (ch == (char) ParserConst.KT_EOF);
	}

	public static boolean isdot(char ch)
	{
		if (ch == '.')
			return true;
		else
			return false;
	}

	public static boolean isdigit(char ch)
	{
		if (ch >= '0' && ch <= '9')
		{
			return true;
		}
		return false;
	}

	/**
	 * 首字母：仅仅字母返回true, 其它 false
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isFirstAlpha(char ch)
	{
		if (ch >= 'a' && ch <= 'z')
		{
			return true;
		}
		if (ch >= 'A' && ch <= 'Z')
		{
			return true;
		}
		return false;
	}

	/**
	 * 字母 + '_' 都返回true, 其它 false
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isalpha(char ch)
	{
		if (isFirstAlpha(ch))
			return true;
		else if (ch == '_')
			return true;

		return false;
	}

}
