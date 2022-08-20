package com.channelsoft.vbs2java_v2;

/**
 * 表达式类。表达式基本结构（leftExpr operator rightExpr）
 * 
 * @author dragon.huang
 */
public abstract class Expr extends Base
{
	/**
	 * 左边的子表达式
	 */
	Expr left = null;

	/**
	 * 右边的子表达式
	 */
	Expr right = null;

	/**
	 * 当前表达式标记
	 */
	Token exprTk = null;

	/**
	 * 解析并返回一个表达式
	 * 
	 * @param env
	 * @param ec
	 *            表达式结束标记
	 * @return 最新解析的表达式。没有则返回null。
	 * @throws Exception
	 */
	static Expr g_parse(ParserEnv env, ExprContext ec) throws Exception
	{
		// 设置上下文
		env.pushExpr(null);
		// 表达式根节点
		Expr root = null;
		// 最近解析的表达式
		Expr curr = null;
		// 最近解析的Token
		Token tk = null;
		while (true)
		{
			tk = env.nextToken();

			// 依据外部指定条件，判断表达式是否结束
			if (ec != null && ec.checkEndExpr(tk))
			{
				break;
			}

			// 判断当前token是否适合作为表达式开头
			if (root == null && !isExprHead(tk, env))
			{
				break;
			}

			// 根据前后两个子表达式，判断表达式是否结束
			if (curr != null && isExprEnd(curr.exprTk, tk))
			{
				break;
			}

			// 生成最近子表达式
			if ((curr = createByToken(tk, env)) == null)
			{
				break;
			}

			if (root == null)
			{
				root = curr;
			}
			else
			{
				// 根据优先级调整表达式
				root = adjust(root, curr);
			}
		}

		// 放回未使用Token
		env.pushBackT();

		// 检查表达式是否合法
		checkExpr(root, env);

		// 设置上下文
		env.popExpr();

		return root;
	}

	/**
	 * 解析并返回一个表达式
	 * 
	 * @param env
	 * @return 最新解析的表达式。没有则返回null。
	 * @throws Exception
	 */
	static Expr g_parse(ParserEnv env) throws Exception
	{
		return g_parse(env, null);
	}

	/**
	 * 根据标记生成表达式
	 * 
	 * @param tk
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Expr createByToken(Token tk, ParserEnv env) throws Exception
	{
		Expr ep = null;

		switch (tk.type)
		{
		case ParserConst.KT_ADD:
			ep = new ExprAdd();
			break;
		case ParserConst.KT_MINUS:
			ep = new ExprMinus();
			break;
		case ParserConst.KT_MINUX:
			ep = new ExprMinus();
			break;
		case ParserConst.KT_DIV:
			ep = new ExprDiv();
			break;
		case ParserConst.KT_INTDIV:
			ep = new ExprIntDiv();
			break;
		case ParserConst.KT_MULTIPLY:
			ep = new ExprMultiply();
			break;
		case ParserConst.KT_POWER:
			ep = new ExprPower();
			break;
		case ParserConst.KT_EQ:
			ep = new ExprLogicEQ();
			break;
		// case MConst.KT_COMMA:
		// break;
		case ParserConst.KT_LT:
			ep = new ExprLogicLT();
			break;
		case ParserConst.KT_GT:
			ep = new ExprLogicGT();
			break;
		case ParserConst.KT_NLT:
			ep = new ExprLogicNLT();
			break;
		case ParserConst.KT_NGT:
			ep = new ExprLogicNGT();
			break;
		case ParserConst.KT_STRCAT:
			ep = new ExprStrcat();
			break;
		case ParserConst.KT_NEQ:
			ep = new ExprLogicNEQ();
			break;
		// case MConst.KT_DOT: //见createSubExpr
		// ep = new ExprDotFun();
		// break;
		case ParserConst.KT_LEFT_BRACKET:
			ep = ExprBracket.parseBracket(env);
			break;
		// case MConst.KT_RIGHT_BRACKET: //见KT_LEFT_BRACKET
		// break;
		case ParserConst.KT_CALLSEFDEFFUN:
		case ParserConst.KT_CALLINTERNALFUN:
			ep = ExprCallFun.parseCallFun(env);
			break;
		case ParserConst.KT_OBJ:
		case ParserConst.KT_INTERNALOBJ:
			ep = ExprCallObj.parseCallObj(env);
			break;
		case ParserConst.KT_ARR:
			ep = ExprArr.parseForCall(env);
			break;
		case ParserConst.KT_VAR:
			ep = createByVar(env);
			break;
		case ParserConst.KT_IN_KNOWLEDGEVAR:
		case ParserConst.KT_OUT_KNOWLEDGEVAR:
			ep = new ExprVar();
			break;
		case ParserConst.TT_INT:
		case ParserConst.TT_FLOAT:
		case ParserConst.KT_VBCONST:
			ep = new ExprNum();
			break;
		case ParserConst.TT_STR:
			ep = new ExprStr();
			break;
		case ParserConst.KT_IS:
			ep = new ExprLogicIS();
			break;
		case ParserConst.KT_NOT:
			ep = new ExprLogicNOT();
			break;
		case ParserConst.KT_AND:
			ep = new ExprLogicAND();
			break;
		case ParserConst.KT_OR:
			ep = new ExprLogicOR();
			break;
		case ParserConst.KT_XOR:
			ep = new ExprLogicXOR();
			break;
		case ParserConst.KT_EQV:
			ep = new ExprLogicEQV();
			break;
		case ParserConst.KT_IMP:
			ep = new ExprLogicIMP();
			break;
		case ParserConst.KT_MOD:
			ep = new ExprMod();
			break;
		case ParserConst.KT_EMPTY:
		case ParserConst.KT_NULL:
			ep = new ExprEmpty();
			break;
		case ParserConst.KT_NOTHING:
			ep = new ExprNothing();
			break;
		default:
			break;
		}

		if (ep != null)
		{
			ep.exprTk = tk;
		}
		return ep;
	}

	/**
	 * 创建子表达式。用于ExprComplex(复合表达式)。（复合表达式：a.fun(...)(...).fun = ...）
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	protected static Expr createSubExpr(ParserEnv env) throws Exception
	{
		Token tk = env.readNextToken();
		if (tk.type == ParserConst.KT_DOT)
		{
			return ExprDotFun.parse(env);
		}
		else if (tk.type == ParserConst.KT_LEFT_BRACKET)
		{
			ExprArglistForCallFun ea = new ExprArglistForCallFun();
			ea.parse(env);
			return ea;
		}
		return null;
	}

	/**
	 * 依据优先级调整表达式。l高，则l作为r的左表达式，返回r；r高，则r作为l的右表达式，返回l。
	 * 
	 * @param l
	 * @param r
	 * @return 调整后的表达式
	 * @throws Exception
	 */
	private static Expr adjust(Expr l, Expr r) throws Exception
	{
		if (compare(l, r) >= 0)
		{
			return r.appendLeftExpr(l);
		}
		else
		{
			return l.appendRightExpr(r);
		}
	}

	/**
	 * 比较表达式优先级。l > r? >0；l = r? =0；l < r? <0
	 * 
	 * @param l
	 * @param r
	 * @return
	 */
	private static int compare(Expr l, Expr r)
	{
		int comp = oplevel(r.exprTk) - oplevel(l.exprTk);
		if (comp == 0)
		{
			if (l.exprTk.type == ParserConst.KT_NOT
					|| l.exprTk.type == ParserConst.KT_MINUX)
			{// 右值运算符，右侧优先级高，因此返回负值
				return -1;
			}
		}
		return comp;
	}

	/**
	 * 返回操作符级别，级别越高，值越小
	 * 
	 * @param tk
	 * @return
	 */
	private static int oplevel(Token tk)
	{
		switch (tk.type)
		{
		case ParserConst.KT_IMP:
			return 16;
		case ParserConst.KT_EQV:
			return 15;
		case ParserConst.KT_XOR:
			return 14;
		case ParserConst.KT_OR:
			return 13;
		case ParserConst.KT_AND:
			return 12;
		case ParserConst.KT_NOT:
			return 11;
		case ParserConst.KT_COMMA:
			return 10;
		case ParserConst.KT_IS:
		case ParserConst.KT_LT:
		case ParserConst.KT_GT:
		case ParserConst.KT_NLT:
		case ParserConst.KT_NGT:
		case ParserConst.KT_EQ:
		case ParserConst.KT_NEQ:
			return 9;
		case ParserConst.KT_STRCAT:
			return 8;
		case ParserConst.KT_ADD:
		case ParserConst.KT_MINUS:
			return 7;
		case ParserConst.KT_MOD:
			return 6;
		case ParserConst.KT_INTDIV:
			return 5;
		case ParserConst.KT_DIV:
		case ParserConst.KT_MULTIPLY:
			return 4;
		case ParserConst.KT_POWER:
			return 3;
		case ParserConst.KT_MINUX: // 负数
			return 2;
		case ParserConst.KT_LEFT_BRACKET:
		case ParserConst.KT_RIGHT_BRACKET:
			return 1;
		case ParserConst.KT_CALLSEFDEFFUN:
		case ParserConst.KT_CALLINTERNALFUN:
		case ParserConst.KT_OBJ:
		case ParserConst.KT_INTERNALOBJ:
		case ParserConst.KT_IN_KNOWLEDGEVAR:
		case ParserConst.KT_OUT_KNOWLEDGEVAR:
		case ParserConst.KT_VAR:
		case ParserConst.TT_INT:
		case ParserConst.TT_FLOAT:
		case ParserConst.TT_STR:
		case ParserConst.KT_EMPTY:
		case ParserConst.KT_NULL:
		case ParserConst.KT_NOTHING:
		default:
			return 0;
		}
	}

	/**
	 * 当前Token是否可以作为表达式开头
	 * 
	 * @param tk
	 * @param env
	 * @return
	 * @throws Exception
	 */
	protected static boolean isExprHead(Token tk, ParserEnv env)
			throws Exception
	{
		switch (tk.type)
		{
		case ParserConst.KT_LEFT_BRACKET:
		case ParserConst.KT_CALLSEFDEFFUN:
		case ParserConst.KT_CALLINTERNALFUN:
		case ParserConst.KT_OBJ:
		case ParserConst.KT_INTERNALOBJ:
		case ParserConst.KT_IN_KNOWLEDGEVAR:
		case ParserConst.KT_OUT_KNOWLEDGEVAR:
		case ParserConst.KT_VAR:
		case ParserConst.KT_ARR:
		case ParserConst.TT_INT:
		case ParserConst.TT_FLOAT:
		case ParserConst.KT_VBCONST:
		case ParserConst.TT_STR:
		case ParserConst.KT_NOT:
		case ParserConst.KT_MINUX:
		case ParserConst.KT_EMPTY:
		case ParserConst.KT_NULL:
		case ParserConst.KT_NOTHING:
			return true;
		}

		return false;
	}

	/**
	 * 是否为原子表达式（可作为单独的表达式存在）
	 * 
	 * @param tk
	 * @return
	 */
	protected static boolean isAtomExpr(Token tk)
	{
		switch (tk.type)
		{
		// case MConst.KT_LEFT_BRACKET:
		case ParserConst.KT_CALLSEFDEFFUN:
		case ParserConst.KT_CALLINTERNALFUN:
		case ParserConst.KT_OBJ:
		case ParserConst.KT_INTERNALOBJ:
		case ParserConst.KT_IN_KNOWLEDGEVAR:
		case ParserConst.KT_OUT_KNOWLEDGEVAR:
		case ParserConst.KT_VAR:
		case ParserConst.KT_ARR:
		case ParserConst.TT_INT:
		case ParserConst.TT_FLOAT:
		case ParserConst.KT_VBCONST:
		case ParserConst.TT_STR:
			// case MConst.KT_NOT:
			// case MConst.KT_MINUX:
		case ParserConst.KT_EMPTY:
		case ParserConst.KT_NULL:
		case ParserConst.KT_NOTHING:
			return true;
		}

		return false;
	}

	/**
	 * 根据前后子表达式标记，检查表达式是否结束
	 * 
	 * @param preTk
	 * @param curTk
	 * @return
	 */
	private static boolean isExprEnd(Token preTk, Token curTk)
	{
		if (preTk.type == ParserConst.KT_LEFT_BRACKET
				&& (isAtomExpr(curTk) || curTk.type == ParserConst.KT_LEFT_BRACKET))
		{// a() | ()() | ()a
			return true;
		}
		return (isAtomExpr(preTk) && isAtomExpr(curTk));
	}

	/**
	 * 检查表达式是否合法
	 * 
	 * @param expr
	 * @param env
	 * @throws Exception
	 */
	private static void checkExpr(Expr expr, ParserEnv env) throws Exception
	{
		if (expr == null)
		{
			return;
		}

		switch (expr.exprTk.type)
		{
		case ParserConst.KT_LEFT_BRACKET:
			// case MConst.KT_RIGHT_BRACKET:
		case ParserConst.KT_CALLSEFDEFFUN:
		case ParserConst.KT_CALLINTERNALFUN:
		case ParserConst.KT_OBJ:
		case ParserConst.KT_INTERNALOBJ:
		case ParserConst.KT_IN_KNOWLEDGEVAR:
		case ParserConst.KT_OUT_KNOWLEDGEVAR:
		case ParserConst.KT_VAR:
		case ParserConst.TT_INT:
		case ParserConst.TT_FLOAT:
		case ParserConst.KT_VBCONST:
		case ParserConst.TT_STR:
			// case MConst.KT_DOT:
		case ParserConst.KT_ARR:
		case ParserConst.KT_NOTHING:
		case ParserConst.KT_NULL:
		case ParserConst.KT_EMPTY:
			if (expr.left == null && expr.right == null)
			{
				return;
			}
			break;
		case ParserConst.KT_ADD:
		case ParserConst.KT_MINUS:
		case ParserConst.KT_DIV:
		case ParserConst.KT_INTDIV:
		case ParserConst.KT_MULTIPLY:
		case ParserConst.KT_POWER:
		case ParserConst.KT_EQ:
			// case MConst.KT_COMMA:
			// break;
		case ParserConst.KT_LT:
		case ParserConst.KT_GT:
		case ParserConst.KT_NLT:
		case ParserConst.KT_NGT:
		case ParserConst.KT_STRCAT:
		case ParserConst.KT_NEQ:
		case ParserConst.KT_IS:
		case ParserConst.KT_AND:
		case ParserConst.KT_OR:
		case ParserConst.KT_XOR:
		case ParserConst.KT_EQV:
		case ParserConst.KT_IMP:
		case ParserConst.KT_MOD:
			if (expr.left != null && expr.right != null)
			{
				checkExpr(expr.left, env);
				checkExpr(expr.right, env);
				return;
			}
			break;
		case ParserConst.KT_NOT:
		case ParserConst.KT_MINUX: // 负数
			if (expr.left == null && expr.right != null)
			{
				checkExpr(expr.right, env);
				return;
			}
			break;
		default:
			break;
		}

		throw new ParserException(env, expr.exprTk, ExceptionDesc.DS002);
	}

	/**
	 * 根据kt_var标记，创建表达式
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Expr createByVar(ParserEnv env) throws Exception
	{
		// 一个kt_var开头的表达式可能：数组定义、方法调用、普通表达式；
		// 区分：
		// 数组定义 ：dim var(...), ...
		// 数组重定义 : redim var(...), ...
		// 方法调用 ：var expr
		// 其他 ：普通表达式
		Token ctk = env.currToken();
		Token ntk = env.readNextToken();
		if (ntk.type == ParserConst.KT_LEFT_BRACKET
				&& env.getCurSub().type == ParserConst.KT_DIM)
		{// dim var(...)
			env.keyword().addArr(ctk);
			return ExprArr.parseForDefine(env);
		}
		if (ntk.type == ParserConst.KT_LEFT_BRACKET
				&& env.getCurSub().type == ParserConst.KT_REDIM)
		{// redim var(...)
			env.keyword().addArr(ctk);
			return ExprArr.parseForCall(env);
		}
		else if (env.getCurSub().equals(ctk) && env.getGrammerInfo() == null
				&& (isAtomExpr(ntk) || ntk.type == ParserConst.KT_COMMA))
		{// var expr 或 var ,...
			env.setCurSub(ctk);
			ExprCallFun cf = ExprCallFun.parseCallFun(env);
			return cf;
		}
		else
		{// 其他
			return ExprVar.parseVar(env);
		}
	}

	/**
	 * 生成表达式java代码
	 */
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(exprTk.str);
	}

	/**
	 * 扩展表达式左边的子表达式
	 * 
	 * @param l
	 * @return
	 * @throws Exception
	 */
	private Expr appendLeftExpr(Expr l) throws Exception
	{
		if (left == null)
		{
			left = l;
		}
		else
		{
			left = adjust(left, l);
		}

		return this;
	}

	/**
	 * 扩展表达式右边的子表达式
	 * 
	 * @param l
	 * @return
	 * @throws Exception
	 */
	private Expr appendRightExpr(Expr r) throws Exception
	{
		if (right == null)
		{
			right = r;
		}
		else
		{
			right = adjust(right, r);
		}

		return this;
	}

	/**
	 * 表达式是否返回常量bool
	 * 
	 * @return
	 */
	protected boolean isConstBool()
	{
		if (left == null && right == null)
		{
			if (exprTk.type == ParserConst.KT_LEFT_BRACKET)
				return ((ExprBracket) this).expr.isConstBool();
			return exprTk.isConstBool();
		}
		else if (ParserConst.isCompareOp(exprTk.type))
		{
			return true;
		}
		else if (ParserConst.isLogicalOp(exprTk.type))
		{
			if (exprTk.type == ParserConst.KT_IMP
					|| exprTk.type == ParserConst.KT_EQV)
				return false;
			if (exprTk.type == ParserConst.KT_NOT)
				return right.isConstBool();
			return (left.isConstBool() && right.isConstBool());
		}

		return false;
	}

	/**
	 * 表达式是否返回常量数字
	 * 
	 * @return
	 */
	protected boolean isConstNum()
	{
		if (left == null && right == null)
		{
			return exprTk.isConstNum();
		}
		else
		{
			if (exprTk.type == ParserConst.KT_MOD)
			{
				return true;
			}
			if (exprTk.type == ParserConst.KT_MINUX)
			{
				return right.isConstNum();
			}

			if (exprTk.type == ParserConst.KT_ADD
					|| exprTk.type == ParserConst.KT_MINUS
					|| exprTk.type == ParserConst.KT_MULTIPLY
					|| exprTk.type == ParserConst.KT_DIV)
			{
				if (left != null && right != null)
				{
					return (left.isConstNum() && right.isConstNum());
				}
				else
				{
					return (left == null) ? right.isConstNum() : left
							.isConstNum();
				}
			}
		}

		return false;
	}

	/**
	 * 表达式是否返回常量整数
	 * 
	 * @return
	 */
	protected boolean isConstInt()
	{
		if (left == null && right == null)
		{
			return exprTk.isConstInt();
		}
		else
		{
			if (exprTk.type == ParserConst.KT_MOD)
			{
				return true;
			}
			if (exprTk.type == ParserConst.KT_MINUX)
			{
				return right.isConstInt();
			}

			if (exprTk.type == ParserConst.KT_ADD
					|| exprTk.type == ParserConst.KT_MINUS
					|| exprTk.type == ParserConst.KT_MULTIPLY
			/*|| exprTk.type == ParserConst.KT_DIV*/)
			{
				if (left != null && right != null)
				{
					return (left.isConstInt() && right.isConstInt());
				}
			}
		}

		return false;
	}

	/**
	 * 表达式是否返回常量字符串
	 * 
	 * @return
	 */
	protected boolean isConstStr()
	{
		if (left == null && right == null)
		{
			return exprTk.isConstStr();
		}
		else
		{
			if (left != null && right != null)
			{
				if (exprTk.type == ParserConst.KT_STRCAT
						|| (left.isConstStr() && right.isConstStr() && (exprTk.type == ParserConst.KT_ADD)))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 表达式是否返回常量
	 * 
	 * @return
	 */
	protected boolean isConst()
	{
		if (isConstNum() || isConstStr() || isConstBool())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 表达式是否返回变体
	 * 
	 * @return
	 */
	protected boolean isReturnVar()
	{
		return !isConst();
	}

	/**
	 * 表达式是否返回null
	 * 
	 * @return
	 */
	protected boolean isNull()
	{
		return (left != null && left.isNull())
				|| (right != null && right.isNull()) || (exprTk.isNull());
	}

	/**
	 * 输出表达式变体样式的java代码
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	protected void printVarJava(StringBuilder sb, ParserEnv env)
			throws Exception
	{
		if (this.isReturnVar())
		{
			this.printJava(sb, env);
		}
		else
		{
			sb.append("new Variant(");
			this.printJava(sb, env);
			sb.append(", cci)");
		}
	}

	/**
	 * 输出表达式数字样式的java代码
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	protected void printNumJava(StringBuilder sb, ParserEnv env)
			throws Exception
	{
		if (this.isConstNum())
		{
			this.printJava(sb, env);
		}
		else
		{
			this.printVarJava(sb, env);
			sb.append(".getDouble()");
		}
	}

	/**
	 * 输出表达式数字样式的java代码
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	protected void printIntJava(StringBuilder sb, ParserEnv env)
			throws Exception
	{
		if (this.isConstInt())
		{
			this.printJava(sb, env);
		}
		else
		{
			sb.append("(int)");
			this.printVarJava(sb, env);
			sb.append(".getDouble()");
		}
	}

	/**
	 * 输出表达式字符串样式的java代码
	 * 
	 * @param sb
	 * @param env
	 * @throws Exception
	 */
	protected void printStrJava(StringBuilder sb, ParserEnv env)
			throws Exception
	{
		if (this.isConstStr())
		{
			this.printJava(sb, env);
		}
		else
		{
			this.printVarJava(sb, env);
			sb.append(".toString()");
		}
	}
}
