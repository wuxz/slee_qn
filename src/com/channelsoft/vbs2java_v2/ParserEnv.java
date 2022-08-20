package com.channelsoft.vbs2java_v2;

import java.util.Stack;
import java.util.Vector;

import com.channelsoft.slee.usmlreasoning.KnowledgeVariable;

/**
 * 检查Complex语句的语法
 * 
 * @author dragon.huang
 */
interface CheckComplexGrammer
{
	boolean check(NodeComplex nc);
}

class ExceptionDesc
{
	static final String DS001 = "语句已结束,此标识符多余";

	static final String DS002 = "表达式未完成";

	static final String DS002_ARR_DEF_BRACKET = "表达式未完成,数组定义缺少'('";

	static final String DS002_BRACKET_NO_END = "表达式未完成,缺少‘)’";

	static final String DS002_BRACKET_NO_EXPR = "表达式未完成,括号内缺少表达式";

	static final String DS002_FOR_EACH = "类型不匹配,for each内缺少变量(element)";

	static final String DS003 = "类型不匹配";

	static final String DS003_ARR_DEF_INT = "类型不匹配,数组下标必须为整型常量";

	static final String DS003_CALL = "类型不匹配,call需指定方法";

	static final String DS003_CONST = "类型不匹配或重定义,const需指定常量名称";

	static final String DS003_ERASE = "erase需指定数组";

	static final String DS003_IN = "类型不匹配,In需要指定对象集合";

	static final String DS003_NAME_NORMS = "类型不匹配,变量不符合命名规范";

	static final String DS003_SET = "类型不匹配,set需指定变量";

	static final String DS003_SET_ARR_PROP = "类型不匹配,数组调用需指定数组下标";

	static final String DS003_SET_NO_OBJ = "类型不匹配,此处应为对象";

	static final String DS003_UNDEFFUN = "类型不匹配,方法未定义：";

	static final String DS004 = "缺少语句(或语句未完成、语法错误)";

	static final String DS004_CASE = "语法错误,case后缺少表达式";

	static final String DS004_COMPLEX = "语法错误";

	static final String DS004_COMPLEX_NOT_FUN = "方法未定义";

	static final String DS004_CONDITION = "语句未完成,缺少boolean表达式";

	static final String DS004_CONST = "语句未完成,const需指定常量数值";

	static final String DS004_CONSTNUM = "语句未完成,缺少常量数值";

	static final String DS004_DEFAULT = "语句未完成,default后缺少有效语句";

	static final String DS004_DIM = "dim需要指定变量,标识符不符合命名规范";

	static final String DS004_ELSE = "语法错误,else后不要换行";

	static final String DS004_END = "语句未完成,end后缺少有效标识符";

	static final String DS004_ENDIF = "语句未完成,缺少'end if'";

	static final String DS004_EXIT = "语句未完成,exit后缺少有效标识符";

	static final String DS004_FOR = "语法错误,无效的标识符";

	static final String DS004_FUN_DEF_BRACKET = "语句未完成,函数缺少')'";

	static final String DS004_FUN_NEED_COMMA = "此处缺少','";

	static final String DS004_FUNCTION_DEF = "语法错误,function定义不能被嵌套";

	static final String DS004_FUNCTION_NO_END = "语句未完成,function定义缺少end";

	static final String DS004_FUNCTION_RETURN = "语句未完成,方法返回值需要指定赋值表达式";

	static final String DS004_FUNCTION_USAGE = "语法错误,方法用法错误";

	static final String DS004_NO_ASSIGNEXPR = "语句未完成,缺少赋值表达式";

	static final String DS004_NO_CASE = "语句未完成,缺少'case'";

	static final String DS004_NO_TESTEXPR = "语句未完成,缺少条件语句";

	static final String DS004_NOT_FUN_OR_VAR = "无效的方法或变量";

	static final String DS004_NOT_STATEMENT = "此标识符不能作为主语句开始标记";

	static final String DS004_PUBLIC_PRIVATE = "语句未完成,public/private后缺少有效语句";

	static final String DS004_REDIM = "redim需要指定数组变量,标识符不符合命名规范";

	static final String DS004_SELECT_NO_END = "select语句未完成,缺少'end'语句";

	static final String DS004_SET = "语句未完成,set需指定赋值表达式";

	static final String DS004_SUB_NO_END = "语句未完成,sub定义缺少end";

	static final String DS004_SUB_STATEMENT = "语法错误,无效的语句标识符";

	static final String DS004_THEN = "语句未完成,缺少'then'";

	static final String DS005 = "错误的参数个数或无效的参数值";

	// static final String DS006 = "名称重定义或名称不合法";

	static final String DS006_FUNCTION = "名称重定义或名称不合法,缺少有效方法名";

	static final String DS006_REDIM = "Redim后必须指定数组";

	// static final String DS007 = "缺少整数常量";

	static final String DS008 = "对象不支持此属性或方法";

	// static final String DS010 = "无效的语句";

	static final String DS011 = "无效的语句,语句上下文不满足";

	static final String DS012_FUN_ASSIGN = "非法赋值,不能对方法赋值";

	static final String DS013 = "知识变量名为空";

	static final String DS014 = "对象成员不存在";

	static final String DS015 = "无效字符";

	static final String DS015_UNDERLINE = "'_'后应为换行符";

	static final String DS017 = "createObject参数个数不匹配";

	static final String DS018 = "getObject参数个数不匹配";
}

/**
 * 表达式上下文判断
 * 
 * @author dragon.huang
 */
interface ExprContext
{
	/**
	 * 检查是否为当前表达式的结束标记
	 * 
	 * @param tk
	 *            最新待解析的标记
	 * @return
	 */
	public boolean checkEndExpr(Token tk);
}

/**
 * 非结构性语句的上下文判断(非结构性语句: exit语句、return语句等)
 * 
 * @author dragon.huang
 */
interface MainStatementContext
{
	/**
	 * 检查语句上下文
	 * 
	 * @param tk
	 *            外层嵌套的主语句标记
	 * @param grammerinfo
	 *            外层嵌套的主语句的特殊语法信息
	 * @return
	 */
	public boolean check(Token tk, Object grammerinfo);
}

/**
 * 当前vbs解析环境
 * 
 * @author dragon.huang
 */
public class ParserEnv
{
	/**
	 * vbscript已解析的最新字符
	 */
	private char chnow = ' ';

	/**
	 * 最新解析出的Token
	 */
	private Token curtoken = null;

	/**
	 * vbs所在计算节点的全局标示
	 */
	private String globalId = null;

	/**
	 * 语句生成java代码时，代码对齐缩进量
	 */
	private int indentationCount = 0;

	/**
	 * 解析过程中所需的关键字
	 */
	private ParserWord keyword = null;

	/**
	 * vbscript源代码长度
	 */
	private int len = 0;

	/**
	 * vbscript已解析的最新行
	 */
	private int lineNumNow = 1;

	/**
	 * 最新Token的在vbs中的位置
	 */
	private int pos = 0;

	/**
	 * 刚解析完成的主语句
	 */
	private TraceNode preMainStatement = null;

	/**
	 * 前一个Token的在vbs中的位置
	 */
	private int prepos = 0;

	/**
	 * 上一个解析出的Token
	 */
	private Token pretoken = null;

	/**
	 * 解析过程中，记录语句的嵌套关系，作为解析上下文环境。堆栈的前后关系表示主语句之间的嵌套。
	 */
	private Stack<TraceNode> traceNodes = new Stack<TraceNode>();

	/**
	 * vbscript源代码
	 */
	private String vbs;

	ParserEnv(String vbs, String globalId,
			Vector<KnowledgeVariable> inknvarList,
			Vector<KnowledgeVariable> outknvarList)
	{
		this.vbs = vbs;
		this.globalId = globalId;
		len = vbs.length();

		keyword = new ParserWord(this, inknvarList, outknvarList);
	}

	/**
	 * 判断是否刚刚解析完成一个主语句
	 * 
	 * @return
	 */
	boolean afterMainStatement()
	{
		if (preMainStatement != null)
		{
			return true;
		}
		return false;
	}

	/**
	 * 检查主语句的上下文环境。用于exit、end语句等。
	 * 
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	TraceNode check(MainStatementContext msc) throws Exception
	{
		for (int i = traceNodes.size() - 1; i >= 0; i--)
		{
			TraceNode tn = traceNodes.get(i);
			if (msc.check(tn.mainNode, tn.grammerinfo))
			{
				return tn;
			}
		}

		throw new ParserException(this, this.curtoken, ExceptionDesc.DS011);
	}

	/**
	 * 检查子语句的上下文环境(包括主/子语句)
	 * 
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	Token check(StatementContext sc) throws Exception
	{
		TraceNode tn = traceNodes.peek();
		if ((tn != null) && sc.checkMainStatement(tn.mainNode)
				&& sc.checkPreSubStatement(tn.preSubNode))
		{
			return tn.preSubNode;
		}

		throw new ParserException(this, this.curtoken, ExceptionDesc.DS011);
	}

	int curLineNum()
	{
		return this.lineNumNow;
	}

	char currChar()
	{
		return chnow;
	}

	/**
	 * 返回最新已解析Token
	 * 
	 * @return
	 */
	Token currToken()
	{
		return curtoken;
	}

	/**
	 * 将自定义方法名，封装成特殊方法名
	 * 
	 * @param funname
	 * @return
	 */
	String e_funname(String funname)
	{
		return keyword.encapsulateSefDefFun(funname);
	}

	/**
	 * 获取当前正在解析的主语句的类型
	 * 
	 * @return
	 */
	int getCurMainStatementType()
	{
		if (traceNodes.size() == 0)
		{
			return ParserConst.KT_INIT;
		}
		else
		{
			return traceNodes.peek().mainNode.type;
		}
	}

	/**
	 * 取出当前正在解析的自定义函数的共享语法信息
	 * 
	 * @return
	 */
	Object getCurSefFunGrammerInfo()
	{
		if (traceNodes.size() > 0)
		{
			TraceNode tn = traceNodes.firstElement();
			int kt = tn.mainNode.type;
			if ((kt == ParserConst.KT_FUNCTION) || (kt == ParserConst.KT_SUB))
			{
				return tn.grammerinfo;
			}
		}
		return null;
	}

	/**
	 * 获取正在解析的子语句标记
	 * 
	 * @return
	 */
	Token getCurSub()
	{
		if (traceNodes.size() == 0)
		{
			return null;
		}
		else
		{
			return traceNodes.peek().curSubNode;
		}
	}

	/**
	 * 取出共享的语句语法信息
	 * 
	 * @return
	 */
	Object getGrammerInfo()
	{
		if (traceNodes.size() > 0)
		{
			return traceNodes.peek().grammerinfo;
		}

		return null;
	}

	/**
	 * 获取最近解析完成的子语句标记
	 * 
	 * @return
	 */
	Token getPreSub()
	{
		if (traceNodes.size() == 0)
		{
			return null;
		}
		else
		{
			return traceNodes.peek().preSubNode;
		}
	}

	String globalId()
	{
		return globalId;
	}

	/**
	 * 返回当前java代码的缩进量
	 * 
	 * @return
	 */
	String indent()
	{
		String nl = "";
		for (int i = 0; i < this.indentationCount; i++)
		{
			nl += "\t";
		}

		return nl;
	}

	/**
	 * 判断是否为解析结束标记
	 * 
	 * @param tk
	 * @return
	 */
	boolean isEnd(Token tk)
	{
		return (tk.type == ParserConst.KT_EOF);
	}

	/**
	 * 判断当前正在解析的表达式是根表达式，其外部没有嵌套(或没有括号)
	 * 
	 * @return
	 */
	boolean isRootExpr()
	{
		if (traceNodes.size() > 0)
		{
			return (traceNodes.peek().exprStackSize == 1);
		}
		return true;
	}

	/**
	 * 判断当前正在解析的主语句是根语句，其外部没有嵌套
	 * 
	 * @return
	 */
	boolean isRootNode()
	{
		return (traceNodes.size() == 1);
	}

	ParserWord keyword()
	{
		return keyword;
	}

	/**
	 * 生成java代码的左括号，并变更代码缩进量
	 * 
	 * @return
	 */
	String lBracket()
	{
		String s = indent() + "{\r\n";
		indentationCount++;
		return s;
	}

	/**
	 * 取下一个未解析的vbs字符
	 * 
	 * @return
	 */
	char nextChar()
	{
		if (pos < len)
		{
			chnow = vbs.charAt(pos);
		}
		else
		{
			chnow = (char) ParserConst.KT_EOF;
		}
		pos++;
		return chnow;
	}

	/**
	 * 取下一个未解析Token，并变更解析环境
	 * 
	 * @return
	 * @throws Exception
	 */
	Token nextToken() throws Exception
	{
		prepos = pos;
		pretoken = curtoken;

		if ((curtoken != null) && (curtoken.type == ParserConst.KT_EOL))
		{
			this.lineNumNow++;
		}
		curtoken = Token.nextToken(this);

		if (curtoken.isUnderline())
		{// '_' 以及下一个换行符，返回下一个Token
			if (nextToken().type != ParserConst.KT_EOL)
			{
				throw new ParserException(this, curtoken,
						ExceptionDesc.DS015_UNDERLINE);
			}
			return nextToken();
		}

		return curtoken;
	}

	void popExpr()
	{
		if (traceNodes.size() > 0)
		{
			traceNodes.peek().exprStackSize--;
		}
	}

	/**
	 * 主语句解析结束，将语句标示从上下文环境中去除
	 */
	void popMainNode()
	{
		preMainStatement = traceNodes.pop();
	}

	/**
	 * 将最新解析的字符置为未解析字符
	 */
	void pushBackC()
	{
		pos--;
	}

	/**
	 * 将最新解析的Token置为未解析
	 */
	void pushBackT()
	{
		if (prepos != pos)
		{
			if ((pretoken != null) && (pretoken.type == ParserConst.KT_EOL))
			{
				this.lineNumNow--;
			}
			pos = prepos;
			curtoken = pretoken;
		}
	}

	void pushExpr(Expr expr)
	{
		if (traceNodes.size() > 0)
		{
			traceNodes.peek().exprStackSize++;
		}
	}

	/**
	 * 记录当前解析的语句的主/子语句标记，作为上下文环境
	 * 
	 * @param mainTk
	 *            主语句Token标记
	 * @param preSubTk
	 *            子语句Token标记
	 */
	void pushMainNode(Token mainTk, Token preSubTk)
	{
		traceNodes.push(new TraceNode(mainTk, preSubTk));
		preMainStatement = null;
	}

	/**
	 * 生成java代码的右括号，并变更代码缩进量
	 * 
	 * @return
	 */
	String rBracket()
	{
		indentationCount--;
		String s = indent() + "}";
		return s;
	}

	/**
	 * 读下一个未解析Token，解析环境的最新状态没有改变
	 * 
	 * @return
	 * @throws Exception
	 */
	Token readNextToken() throws Exception
	{
		int oldPos = this.pos;
		int oldLinenum = this.lineNumNow;
		Token curToken = this.curtoken;
		Token token = Token.nextToken(this);
		this.pos = oldPos;
		this.lineNumNow = oldLinenum;
		this.curtoken = curToken;
		return token;
	}

	void setCurLineNum(int linenum)
	{
		this.lineNumNow = linenum;
	}

	/**
	 * 上下文环境中，设置当前正在解析的子语句标记
	 * 
	 * @param tk
	 * @throws Exception
	 */
	void setCurSub(Token tk) throws Exception
	{
		if (traceNodes.size() == 0)
		{
			throw new ParserException(this, tk, ExceptionDesc.DS011);
		}
		else
		{
			traceNodes.peek().curSubNode = tk;
			preMainStatement = null;
		}
	}

	/**
	 * 设置当前正在解析主语句特殊的语法信息，与其子语句共享
	 * 
	 * @param gi
	 * @throws Exception
	 */
	void setGrammerInfo(Object gi) throws Exception
	{
		if (traceNodes.size() == 0)
		{
			throw new ParserException(this, this.curtoken, ExceptionDesc.DS011);
		}
		else
		{
			traceNodes.peek().grammerinfo = gi;
		}
	}

	/**
	 * 上下文环境中，设置刚刚解析完成的子语句标记
	 * 
	 * @param tk
	 * @throws Exception
	 */
	void setPreSub(Token tk) throws Exception
	{
		if (traceNodes.size() == 0)
		{
			throw new ParserException(this, tk, ExceptionDesc.DS011);
		}
		else
		{
			traceNodes.peek().preSubNode = tk;
			traceNodes.peek().curSubNode = null;
		}
	}

	/**
	 * 忽略下面Token的解析，直接跳到换行或文件结束标记
	 * 
	 * @throws Exception
	 */
	void toEol() throws Exception
	{
		Token tk;
		do
		{
			tk = this.nextToken();
			keyword.removeLastVar(tk);
		} while (!((tk.type == ParserConst.KT_EOF) || (tk.type == ParserConst.KT_EOL)));

		this.pushBackT();
	}
}

class ParserException extends Exception
{
	static final long serialVersionUID = 2101;

	static private String toDesc(ParserEnv env, Token tk, String desc)
	{
		String s = "行[" + ((env != null) ? env.curLineNum() : "") + "]";
		if (tk == null)
		{
		}
		else
		{
			s += ",符号[" + tk.toString() + "]";
		}
		if (!desc.equals(""))
		{
			s += ",描述[" + desc + "]";
		}
		return s;
	}

	String desc = "";

	int linenum = -1;

	String token = "";

	ParserException()
	{
		this(null, null, "");
	}

	ParserException(ParserEnv env)
	{
		this(env, null, "");
	}

	ParserException(ParserEnv env, String desc)
	{
		this(env, null, desc);
	}

	ParserException(ParserEnv env, Token tk)
	{
		this(env, tk, "");
	}

	ParserException(ParserEnv env, Token tk, String desc)
	{
		super(toDesc(env, tk, desc));
	}

	ParserException(String desc)
	{
		this(null, null, desc);
	}
}

/**
 * 跳过主语句前后不用解析的Token（如：注释、换行等）
 * 
 * @author dragon.huang
 */
class SkipCondition
{
	ParserEnv env = null;

	StatementContext sc = null;

	SkipCondition(ParserEnv env, StatementContext sc)
	{
		this.env = env;
		this.sc = sc;
	}

	/**
	 * 已跳过操作是否合法。主要用于换行判断：主语句之间必须有换行标记
	 * 
	 * @throws Exception
	 */
	public void check() throws Exception
	{

	}

	/***************************************************************************
	 * 是否可以不解析
	 * 
	 * @param tk
	 *            最新未解析的标记
	 * @return
	 */
	public boolean ifTrue(Token tk)
	{
		if ((sc != null) && sc.checkEndSubStatement(tk))
		{
			return false;
		}
		return tk.isSkip();
	}
}

/**
 * 跳过换行。跳过的token中必须存在换行
 * 
 * @author dragon.huang
 */
class SkipLine extends SkipCondition
{
	boolean linechanged = false;

	SkipLine(ParserEnv env, StatementContext sc)
	{
		super(env, sc);
	}

	@Override
	public void check() throws Exception
	{
		if (!linechanged)
		{
			throw new ParserException(env, env.nextToken(), ExceptionDesc.DS001);
		}
		super.check();
	}

	@Override
	public boolean ifTrue(Token tk)
	{
		if (tk.isLineEnd())
		{
			linechanged = true;
		}

		if (env.getCurMainStatementType() == ParserConst.KT_IF)
		{// if ... then ... else ... endif : 此时不用换行，这里虚构一个换行动作
			if ((sc != null) && sc.checkEndSubStatement(tk))
			{
				linechanged = true;
			}
		}

		return super.ifTrue(tk);
	}
}

/**
 * 子语句的上下文判断
 * 
 * @author dragon.huang
 */
interface StatementContext
{
	/**
	 * 检查是否为当前子语句的结束标记
	 * 
	 * @param tk
	 *            最新待解析的标记
	 * @return
	 */
	public boolean checkEndSubStatement(Token tk);

	/**
	 * 检查是否在合法的主语句中
	 * 
	 * @param tk
	 *            当前主语句标记
	 * @return
	 */
	public boolean checkMainStatement(Token tk);

	/**
	 * 检查是否在合法的子语句后
	 * 
	 * @param tk
	 *            前一子语句标记
	 * @return
	 */
	public boolean checkPreSubStatement(Token tk);
}

/**
 * 语句在上下文中信息
 * 
 * @author dragon.huang
 */
class TraceNode
{
	// 当前子句
	Token curSubNode;

	// 解析语句表达式过程中，表达式的嵌套堆栈(‘()/方法’调用)
	// Stack<Expr> exprStack = null;
	int exprStackSize = 0;

	// 子句间语法信息
	Object grammerinfo;

	// 当前主句
	Token mainNode;

	// 前一子句
	Token preSubNode;

	TraceNode(Token mainTk, Token preSubTk)
	{
		mainNode = mainTk;
		curSubNode = preSubTk;
		preSubNode = preSubTk;
	}
}
