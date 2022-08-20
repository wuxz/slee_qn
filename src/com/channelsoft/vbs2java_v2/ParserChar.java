package com.channelsoft.vbs2java_v2;

/**
 * �ַ������ࡣ�ж�vbs�е��ַ����͡�
 * 
 * @author dragon.huang
 */
public class ParserChar
{

	static final String opkeys = "()+-/\\*^=,<>&";

	/**
	 * �Ƿ�Ϊ�ʻ�ָ���
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
	 * �Ƿ������
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isopkey(char ch)
	{
		return (opkeys.indexOf(ch) != -1);
	}

	/**
	 * �Ƿ�Ϊע��
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isnotechar(char ch)
	{
		return (ch == '\'');
	}

	/**
	 * �Ƿ�Ϊ�н�����
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
	 * �Ƿ�Ϊ�ļ�������
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
	 * ����ĸ��������ĸ����true, ���� false
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
	 * ��ĸ + '_' ������true, ���� false
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
