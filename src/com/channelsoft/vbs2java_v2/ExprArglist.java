package com.channelsoft.vbs2java_v2;

import java.util.Iterator;
import java.util.Vector;

/**
 * �����б���ʽ�������ԡ������ָ�Ĳ��������ڷ�������/���á����鶨��/���á�obj��Ա/���Ե��á�dim/redim���ȡ�
 * 
 * @author dragon.huang
 */
abstract class ExprArglist extends Expr
{
	/**
	 * �����б�
	 */
	Vector<ArgNode> arglist = new Vector<ArgNode>();

	abstract void parse(ParserEnv env) throws Exception;

	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		printJavaForGetProp(sb, env);
	}

	/**
	 * �����б���Ϊ�������á����ڣ����鸳ֵ��obj���Ը�ֵ��
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	void printJavaForSetProp(StringBuilder sb, ParserEnv env) throws Exception
	{// new Variant(0, cci), new Variant(1, cci), ...
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{
			Expr varname = itor.next().varname;
			if (varname == null)
			{
				sb.append("null");
			}
			else
			{
				varname.printVarJava(sb, env);
			}
			if (itor.hasNext())
			{
				sb.append(", ");
			}
		}
	}

	/**
	 * �����б���Ϊ���Ի�ȡ�����ڣ���ȡ����ֵ����ȡobject���ԡ��������á�object��������
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	void printJavaForGetProp(StringBuilder sb, ParserEnv env) throws Exception
	{// new Variant[]{new Variant(., cci), ...}
		sb.append("new Variant[]{");
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{
			Expr varname = itor.next().varname;
			if (varname == null)
			{
				sb.append("null");
			}
			else
			{
				varname.printVarJava(sb, env);
			}
			if (itor.hasNext())
			{
				sb.append(", ");
			}
		}
		sb.append("}");
	}

	/**
	 * ���������б��У��жϽ���������
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	boolean isBreak(ParserEnv env) throws Exception
	{
		Token tk = env.nextToken();
		if (tk.type == ParserConst.KT_COMMA)
		{// ȥ��','
			return false;
		}
		else if (tk.type == ParserConst.KT_RIGHT_BRACKET)
		{
			env.pushBackT();
			return true;
		}
		else
		{
			env.pushBackT();
			return true;
		}
	}

	/**
	 * �����б���������У�ȥ��������
	 * 
	 * @param env
	 * @throws Exception
	 */
	void skipRightBracket(ParserEnv env) throws Exception
	{
		if (env.nextToken().type != ParserConst.KT_RIGHT_BRACKET)
		{
			env.pushBackT();
		}
	}

	/**
	 * varname�Ƿ�Ϊ�б��е�һ�����������ڣ�Function���壬�жϱ����Ƿ�Ϊ�����βΡ�
	 * 
	 * @param varname
	 * @return
	 */
	boolean isArgument(String varname)
	{
		Iterator<ArgNode> itor = arglist.iterator();
		while (itor.hasNext())
		{
			ArgNode an = itor.next();
			if (an.varname.exprTk.str.equals(varname))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * �б��Ƿ��в���
	 * 
	 * @return
	 */
	boolean hasArgument()
	{
		return (arglist.size() > 0);
	}

	/**
	 * �б��е�һ�������ڵ�
	 * 
	 * @author dragon.huang
	 */
	class ArgNode
	{
		/**
		 * ���÷�ʽ������: Function���壬ֵ�С�byval������byref����
		 */
		Token callmanner = null;

		/**
		 * �������ʽ
		 */
		Expr varname = null;
	}

	class EC implements ExprContext
	{

		public boolean checkEndExpr(Token tk)
		{
			return (tk.type == ParserConst.KT_COMMA || tk.type == ParserConst.KT_RIGHT_BRACKET);
		}

	}
}
