package com.channelsoft.vbs2java_v2;

/**
 * 词法类，每个Token表示一个基本vbs词汇;
 * 
 * @author dragon.huang
 */
public class Token
{

	/**
	 * 词汇内容
	 */
	public String str = "";

	/**
	 * 词法类型
	 */
	public int type = ParserConst.KT_INIT;

	/**
	 * 取出下一个Token
	 * 
	 * @param env
	 *            解析环境对象
	 * @return 下一个Token
	 * @throws Exception
	 */
	static Token nextToken(ParserEnv env) throws Exception
	{
		Token tk = new Token();

		char ch = env.nextChar();
		// 去掉空格
		while (ParserChar.isspace(ch))
		{
			ch = env.nextChar();
		}

		tk.str += ch;

		if (ParserChar.islineend(ch))
			tk.type = ParserConst.KT_EOL;
		else if (ParserChar.isfileend(ch))
			tk.type = ParserConst.KT_EOF;
		else if (ParserChar.isdigit(ch))
			doDigit(tk, env);
		else if (ParserChar.isFirstAlpha(ch))
			doAlpha(tk, env);
		else if (ParserChar.isnotechar(ch))
			doNote(tk, env);
		else if (ch == '\"')
			doStr(tk, env);
		else if (ch == ':') // 换行 连接行
			tk.type = ParserConst.KT_COLON;
		else if (ch == '_')
			tk.type = ParserConst.KT_UNDERLINE;
		else if (ParserChar.isopkey(ch))
			doOpkey(tk, env);
		else if (ParserChar.isdot(ch)) // 方法调用处理
			tk.type = ParserConst.KT_DOT;
		else
			doOther(tk, env);

		return tk;
	}

	/**
	 * 完成数字Token
	 * 
	 * @param token
	 * @param env
	 * @throws Exception
	 */
	private static void doDigit(Token token, ParserEnv env) throws Exception
	{
		boolean hasdot = false;

		char ch = env.nextChar();

		while (ParserChar.isdigit(ch) || (!hasdot && ParserChar.isdot(ch)))
		{
			token.str += ch;
			if (!hasdot && ch == '.')
				hasdot = true;
			ch = env.nextChar();
		}

		env.pushBackC();
		token.type = hasdot ? ParserConst.TT_FLOAT : ParserConst.TT_INT;
	}

	/**
	 * 完成字母表达式Token
	 * 
	 * @param token
	 * @param env
	 */
	private static void doAlpha(Token token, ParserEnv env)
	{
		char ch;
		ch = env.nextChar();

		while (ParserChar.isalpha(ch) || ParserChar.isdigit(ch))
		{
			token.str += ch;
			ch = env.nextChar();
		}

		env.pushBackC();

		token.str = token.str.toLowerCase();
		if (token.str.equals("rem"))
		{
			doNote(token, env);
			token.type = ParserConst.KT_REM;
		}
		else
		{
			token.type = env.keyword().kt(token.str);
			env.keyword().addVar(token);
		}
	}

	/**
	 * 完成注释Token(包括“‘”和“rem”语句)
	 * 
	 * @param token
	 * @param env
	 */
	private static void doNote(Token token, ParserEnv env)
	{
		token.str = "";

		char ch = env.nextChar();

		while (!(ParserChar.islineend(ch) || ParserChar.isfileend(ch)))
		{
			token.str += ch;
			ch = env.nextChar();
		}

		env.pushBackC();

		token.type = ParserConst.KT_NOTE;
		// 去掉末尾的 '\r'
		int istrlen = token.str.length();
		if (istrlen >= 1)
		{
			if (token.str.charAt(istrlen - 1) == '\r')
				token.str = token.str.substring(0, token.str.length() - 1);
		}
	}

	/**
	 * 完成常量字符串
	 * 
	 * @param token
	 * @param env
	 */
	private static void doStr(Token token, ParserEnv env) throws Exception
	{
		token.str = "";
		char ch = env.nextChar();
		char ch2 = env.nextChar();

		while (!ParserChar.islineend(ch) && !ParserChar.isfileend(ch))
		{
			if (ch == '\"')
			{// 将’""‘变为’\"‘
				if (ch2 == '\"')
				{
					ch = ch2;
					ch2 = env.nextChar();
				}
				else
				{
					env.pushBackC(); // ch2
					break;
				}
			}

			token.str += ch;
			ch = ch2;
			ch2 = env.nextChar();
		}

		if (ch == '\"')
		{
			token.type = ParserConst.TT_STR;
		}
		else
		{
			String desc = "解析字符串常量出错[字符：\'" + ch + "\']";
			if (ch == '\n')
			{
				desc = "解析字符串常量出错[字符：\'\\r\\n\']";
			}

			throw new ParserException(env, desc);
		}
	}

	/**
	 * 完成操作符(运算、逻辑、关系操作符：><()+-/*\^=&)
	 * 
	 * @param token
	 * @param env
	 */
	private static void doOpkey(Token token, ParserEnv env)
	{
		switch (env.currChar())
		{
		case '>':
		{
			char ch = env.nextChar();
			if (ch == '=')
			{
				token.str += ch;
				token.type = ParserConst.KT_NLT;
			}
			else
			{
				env.pushBackC();
				token.type = ParserConst.KT_GT;
			}
		}
			break;
		case '<':
		{
			char ch = env.nextChar();
			if (ch == '=')
			{
				token.str += ch;
				token.type = ParserConst.KT_NGT;
			}
			else if (ch == '>')
			{
				token.str += ch;
				token.type = ParserConst.KT_NEQ;
			}
			else
			{
				env.pushBackC();
				token.type = ParserConst.KT_LT;
			}
		}
			break;
		case '(':
			token.type = ParserConst.KT_LEFT_BRACKET;
			break;
		case ')':
			token.type = ParserConst.KT_RIGHT_BRACKET;
			break;
		case '+':
			token.type = ParserConst.KT_ADD;
			break;
		case '-':
			if (Expr.isAtomExpr(env.currToken())
					|| env.currToken().type == ParserConst.KT_RIGHT_BRACKET)
			{
				token.type = ParserConst.KT_MINUS;
			}
			else
			{
				token.type = ParserConst.KT_MINUX;
			}
			break;
		case '/':
			token.type = ParserConst.KT_DIV;
			break;
		case '\\':
			token.type = ParserConst.KT_INTDIV;
			break;
		case '*':
			token.type = ParserConst.KT_MULTIPLY;
			break;
		case '^':
			token.type = ParserConst.KT_POWER;
			break;
		case '=':
			token.type = ParserConst.KT_EQ;
			break;
		case '&':
			token.type = ParserConst.KT_STRCAT;
			break;
		case ',':
			token.type = ParserConst.KT_COMMA;
			break;
		default:
			break;
		}
	}

	/**
	 * 处理无法识别字符
	 * 
	 * @param token
	 * @param env
	 * @throws Exception
	 */
	private static void doOther(Token token, ParserEnv env) throws Exception
	{
		throw new ParserException(env, token, ExceptionDesc.DS015);
	}

	/**
	 * 将非法变量名称封装成合法变量名称
	 * 
	 * @return
	 */
	String e_str()
	{
		return ParserWord.encapsulateVar(str);
	}

	protected boolean isVar()
	{
		return (type == ParserConst.KT_VAR
				|| type == ParserConst.KT_IN_KNOWLEDGEVAR || type == ParserConst.KT_OUT_KNOWLEDGEVAR);
	}

	protected boolean isInternalFun()
	{
		return (type == ParserConst.KT_CALLINTERNALFUN);
	}

	protected boolean isArr()
	{
		return (type == ParserConst.KT_ARR);
	}

	protected boolean isObj()
	{
		return (type == ParserConst.KT_OBJ);
	}

	protected boolean isInternalObj()
	{
		return (type == ParserConst.KT_INTERNALOBJ);
	}

	protected boolean isOperator()
	{
		return (str.length() >= 1 && ParserChar.isopkey(str.charAt(0)));
	}

	protected boolean isConst()
	{
		return (type == ParserConst.TT_FLOAT || type == ParserConst.TT_INT
				|| type == ParserConst.TT_STR || type == ParserConst.KT_VBCONST);
	}

	protected boolean isConstNum()
	{
		return (type == ParserConst.TT_FLOAT || type == ParserConst.TT_INT || (type == ParserConst.KT_VBCONST && ParserWord
				.isvbConstNum(str)));
	}

	protected boolean isConstInt()
	{
		return (type == ParserConst.TT_INT || (type == ParserConst.KT_VBCONST && ParserWord
				.isvbConstNum(str)));
	}

	protected boolean isConstStr()
	{
		return (type == ParserConst.TT_STR || (type == ParserConst.KT_VBCONST && ParserWord
				.isvbConstStr(str)));
	}

	protected boolean isConstBool()
	{
		return (type == ParserConst.KT_VBCONST && ParserWord.isvbConstBool(str));
	}

	protected boolean isNull()
	{
		return (type == ParserConst.KT_NULL);
	}

	protected boolean isEmpty()
	{
		return (type == ParserConst.KT_EMPTY);
	}

	/**
	 * 语句翻译时是否可以跳过当前Token
	 * 
	 * @return
	 */
	protected boolean isSkip()
	{
		return (type == ParserConst.KT_EOL || type == ParserConst.KT_COLON
				|| type == ParserConst.KT_REM || type == ParserConst.KT_NOTE);
	}

	/**
	 * 是否换行的Token
	 * 
	 * @return
	 */
	protected boolean isLineEnd()
	{
		return (type == ParserConst.KT_EOL || type == ParserConst.KT_COLON || type == ParserConst.KT_EOF);
	}

	/**
	 * 是否字母开头Token
	 * 
	 * @return
	 */
	protected boolean isAlpha()
	{
		if (this.str.length() > 0)
		{
			return ParserChar.isalpha(this.str.charAt(0));
		}
		return false;
	}

	protected boolean isUnderline()
	{
		return (type == ParserConst.KT_UNDERLINE);
	}

	void copy(Token tk)
	{
		this.str = tk.str;
		this.type = tk.type;
	}

	@Override
	public boolean equals(Object obj)
	{
		Token tk = (Token) obj;
		return this.str.equals(tk.str) && (this.type == tk.type);
	}

	@Override
	public String toString()
	{
		if (type == ParserConst.TT_STR)
		{
			return "\"" + str + "\"";
		}
		if (type == ParserConst.KT_NOTE)
		{
			return "\'" + str;
		}
		if (type == ParserConst.KT_REM)
		{
			return "rem " + str;
		}
		if (type == ParserConst.KT_EOL || type == ParserConst.KT_EOF)
		{
			return "\\r\\n";
		}
		return str;
	}
}
