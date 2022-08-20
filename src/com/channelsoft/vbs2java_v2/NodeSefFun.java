package com.channelsoft.vbs2java_v2;

import java.util.Iterator;
import java.util.Vector;

/**
 * 自定义方法
 * </p>
 * function|sub {funname}{arglist}
 * </p>
 * {statements}
 * </p>
 * {endfun}
 * </p>
 * 
 * @author dragon.huang
 */
public abstract class NodeSefFun extends Node
{
	/**
	 * 方法名
	 */
	Token funname = null;

	/**
	 * 形参
	 */
	ExprArglistForFunction arglist = null;

	/**
	 * 方法体语句
	 */
	Node statements = null;

	/**
	 * 方法定义结束标记
	 */
	Node endfun = null;

	/**
	 * 方法内部(引)使用的变体参数
	 */
	Vector<String> refglobalvar = null;

	/**
	 * 方法内部(引)使用的obj参数
	 */
	Vector<String> sefDefObj = null;

	/**
	 * 方法内部(引)使用的数组参数
	 */
	Vector<String> sefDefArr = null;

	/**
	 * 自定义方法语法信息。用于解析。
	 */
	GrammerInfo gi = new GrammerInfo();

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		if (!env.isRootNode())
		{// function 不能作为嵌套节点
			throw new ParserException(env, ExceptionDesc.DS004_FUNCTION_DEF);
		}

		gi.seffunnode = this;
		gi.isfunction = (env.getPreSub().type == ParserConst.KT_FUNCTION);
		env.setGrammerInfo(gi);

		funname = env.nextToken();
		if (funname.type == ParserConst.KT_VAR
				|| funname.type == ParserConst.KT_CALLSEFDEFFUN
				|| funname.type == ParserConst.KT_CALLINTERNALFUN)
		{
			env.keyword().addSefDefFun(this);
		}
		else
		{
			throw new ParserException(env, funname,
					ExceptionDesc.DS006_FUNCTION);
		}

		arglist = ExprArglistForFunction.parseArglistX(env);
		gi.funname = funname.str;

		statements = g_parse(env, new SC());

		endfun = createSubStatement(env);

		check(env);
	}

	/**
	 * 检查方法语法结构是否合法
	 * 
	 * @param env
	 * @throws Exception
	 */
	abstract protected void check(ParserEnv env) throws Exception;

	/**
	 * 打印方法定义的Java代码
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	abstract protected void printDefine(StringBuilder sb, ParserEnv env)
			throws Exception;

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (sibling != null)
		{
			sibling.printJava(sb, env);
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		return this.funname.str.equals(((NodeSefFun) obj).funname.str);
	}

	/**
	 * 是否正在解析形参
	 * 
	 * @return
	 */
	private boolean isParsingArglist()
	{
		return (arglist == null);
	}

	void addObj(String objname)
	{
		if (sefDefObj == null)
		{
			sefDefObj = new Vector<String>();
		}
		if (!sefDefObj.contains(objname))
		{
			sefDefObj.add(objname);
		}
	}

	void addArr(String arrname)
	{
		if (sefDefArr == null)
		{
			sefDefArr = new Vector<String>();
		}
		if (!sefDefArr.contains(arrname))
		{
			sefDefArr.add(arrname);
		}
	}

	void addVar(String varname)
	{
		if (isParsingArglist() || arglist.isArgument(varname))
		{
			return;
		}

		if (refglobalvar == null)
		{
			refglobalvar = new Vector<String>();
		}

		if (!refglobalvar.contains(varname))
		{
			refglobalvar.add(varname);
		}
	}

	/**
	 * 移除最新定义的vbs变量。在解析时会先将fun/property识别为变量；当识别.fun/.property为表达式时，需要将fun/property从变量列表中移除。
	 * 
	 * @param varname
	 */
	void removeLastVar(String varname)
	{
		if (refglobalvar != null && refglobalvar.size() > 0
				&& refglobalvar.lastElement() == varname)
		{
			refglobalvar.remove(varname);
		}
	}

	/**
	 * 打印方法的局部变量
	 * 
	 * @param sb
	 * @param env
	 */
	protected void printVar(StringBuilder sb, ParserEnv env)
	{
		if (refglobalvar != null)
		{
			Iterator<String> itor = refglobalvar.iterator();
			while (itor.hasNext())
			{
				String varname = itor.next();
				sb.append(env.indent()).append("Variant ").append(varname)
						.append(" = ");
				sb.append("cci.getVariant(\"").append(varname).append(
						"\");\r\n");
			}
		}
	}

	class GrammerInfo
	{
		/**
		 * 方法名
		 */
		String funname = "";

		/**
		 * function对象
		 */
		NodeSefFun seffunnode = null;

		/**
		 * 是function还是sub定义
		 */
		boolean isfunction = false; // true : sub
	}

	private class SC implements StatementContext
	{

		public boolean checkMainStatement(Token tk)
		{
			return false;
		}

		public boolean checkEndSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_END);
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return false;
		}
	}

}
