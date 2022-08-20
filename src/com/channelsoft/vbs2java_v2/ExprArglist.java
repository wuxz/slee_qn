package com.channelsoft.vbs2java_v2;

import java.util.Iterator;
import java.util.Vector;

/**
 * 参数列表表达式，描述以‘，’分割的参数。用于方法定义/调用、数组定义/调用、obj成员/属性调用、dim/redim语句等。
 * 
 * @author dragon.huang
 */
abstract class ExprArglist extends Expr
{
	/**
	 * 参数列表
	 */
	Vector<ArgNode> arglist = new Vector<ArgNode>();

	abstract void parse(ParserEnv env) throws Exception;

	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		printJavaForGetProp(sb, env);
	}

	/**
	 * 参数列表翻译为属性设置。见于：数组赋值、obj属性赋值。
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
	 * 参数列表翻译为属性获取。见于：获取数组值、获取object属性、方法调用、object方法调用
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
	 * 解析参数列表中，判断解析结束。
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	boolean isBreak(ParserEnv env) throws Exception
	{
		Token tk = env.nextToken();
		if (tk.type == ParserConst.KT_COMMA)
		{// 去掉','
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
	 * 参数列表解析过程中，去掉右括号
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
	 * varname是否为列表中的一个参数。见于：Function定义，判断变量是否为方法形参。
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
	 * 列表是否有参数
	 * 
	 * @return
	 */
	boolean hasArgument()
	{
		return (arglist.size() > 0);
	}

	/**
	 * 列表中的一个参数节点
	 * 
	 * @author dragon.huang
	 */
	class ArgNode
	{
		/**
		 * 调用方式，见于: Function定义，值有‘byval’、‘byref’。
		 */
		Token callmanner = null;

		/**
		 * 参数表达式
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
