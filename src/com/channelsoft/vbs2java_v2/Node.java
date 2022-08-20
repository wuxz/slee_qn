package com.channelsoft.vbs2java_v2;

public abstract class Node extends Base
{
	/**
	 * 当前语句(Node)在vbs中的行号
	 */
	int linenum = -1;

	/**
	 * 语句的关键字
	 */
	Token keyword = null;

	/**
	 * 下一条语句
	 */
	Node sibling = null;

	/**
	 * 解析并返回一个语句跟节点
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	static Node g_parse(ParserEnv env) throws Exception
	{
		return g_parse(env, null);
	}

	/**
	 * 解析并返回一个语句跟节点
	 * 
	 * @param env
	 * @param sc
	 *            语句结束标记
	 * @return
	 * @throws Exception
	 */
	static Node g_parse(ParserEnv env, StatementContext sc) throws Exception
	{
		// 根节点
		Node root = null;
		// 当前语句节点
		Node curr = null;
		// 当前语句的下一条语句
		Node sibling = null;
		// 最近解析Token
		Token tk = null;

		while (true)
		{
			// 跳过换行、注释等
			skipBeforeMainStatement(env, sc);

			tk = env.nextToken();

			// 判断是否脚本结束
			if (env.isEnd(tk))
			{
				break;
			}

			// 依据外部指定条件，判断语句是否结束
			if (sc != null && sc.checkEndSubStatement(tk))
			{
				env.pushBackT();
				break;
			}

			// 根据Token创建语句节点
			sibling = createMainStatement(tk, env);

			if (root == null)
			{
				root = sibling;
			}
			else
			{
				curr.sibling = sibling;
			}
			curr = sibling;
		}

		return root;
	}

	/**
	 * 创建子语句
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	protected static Node createSubStatement(ParserEnv env) throws Exception
	{
		return createSubStatement(env.nextToken(), env);
	}

	/**
	 * 创建自定类型子语句
	 * 
	 * @param env
	 * @param type
	 *            子语句类型。如果类型不满足则抛出异常。
	 * @return
	 * @throws Exception
	 */
	protected static Node createSubStatement(ParserEnv env, int type)
			throws Exception
	{
		Token tk = env.nextToken();
		if (tk.type != type)
		{
			throw new ParserException(env, tk, ExceptionDesc.DS004_SUB_STATEMENT);
		}

		return createSubStatement(tk, env);
	}

	/**
	 * 根据Token创建子语句
	 * 
	 * @param tk
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createSubStatement(Token tk, ParserEnv env)
			throws Exception
	{
		Node nd = null;

		switch (tk.type)
		{
		case ParserConst.KT_CASE:
			nd = createByCase(env, tk);
			break;
		case ParserConst.KT_ELSE:
			nd = new NodeElse();
			break;
		case ParserConst.KT_ELSEIF:
			nd = new NodeElseif();
			break;
		case ParserConst.KT_END:
			nd = createByEnd(env);
			break;
		case ParserConst.KT_LOOP:
			nd = new NodeLoop();
			break;
		case ParserConst.KT_STEP:
			nd = new NodeStep();
			break;
		case ParserConst.KT_THEN:
			nd = new NodeThen();
			break;
		case ParserConst.KT_TO:
			nd = new NodeTo();
			break;
		case ParserConst.KT_NEXT:
			nd = new NodeNext();
			break;
		case ParserConst.KT_IN:
			nd = new NodeIn();
			break;
		case ParserConst.KT_WEND:
			nd = new NodeWend();
			break;
		default:
			break;
		}

		if (nd != null)
		{
			// 设置节点关键字
			nd.keyword = tk;
			// 设置上下文：当前正在解析的子语句
			env.setCurSub(tk);
			nd.parse(env);
			// 设置上下文：最近解析完成的子语句
			env.setPreSub(tk);
		}
		else
		{
			// 放回未使用的Token
			env.pushBackT();
		}

		return nd;
	}

	/**
	 * 依据token创建主语句。如果主语句类型不存在，则抛出异常。
	 * 
	 * @param tk
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createMainStatement(Token tk, ParserEnv env)
			throws Exception
	{
		Node nd = null;
		int curline = env.curLineNum();

		switch (tk.type)
		{
		case ParserConst.KT_CALL:
		case ParserConst.KT_CALLINTERNALFUN:
			nd = new NodeCall();
			break;
		case ParserConst.KT_CALLSEFDEFFUN:
			nd = createBySefFun(env);
			break;
		case ParserConst.KT_OBJ:
		case ParserConst.KT_INTERNALOBJ:
			nd = new NodeCallObj();
			break;
		case ParserConst.KT_VAR:
		case ParserConst.KT_ARR:
			env.pushBackT();
			nd = new NodeComplex();
			break;
		case ParserConst.KT_IN_KNOWLEDGEVAR:
		case ParserConst.KT_OUT_KNOWLEDGEVAR:
			nd = new NodeAssign();
			break;
		case ParserConst.KT_CONST:
			nd = new NodeConst();
			break;
		case ParserConst.KT_DEFAULT:
			nd = createByDefault(env, tk);
			break;
		case ParserConst.KT_PRIVATE:
		case ParserConst.KT_PUBLIC:
			nd = createByPublicOrPrivate(env, tk);
			break;
		case ParserConst.KT_DIM:
			nd = new NodeDim();
			break;
		case ParserConst.KT_DO:
			nd = new NodeDo();
			break;
		case ParserConst.KT_ERASE:
			nd = new NodeErase();
			break;
		case ParserConst.KT_FOR:
			nd = createByFor(env);
			break;
		case ParserConst.KT_FUNCTION:
			nd = new NodeFunction();
			break;
		case ParserConst.KT_IF:
			nd = new NodeIf();
			break;
		// case MConst.KT_NOTE:
		// nd = new NodeNote();
		// break;
		case ParserConst.KT_ON:
			nd = new NodeOn();
			break;
		case ParserConst.KT_OPTION:
			nd = new NodeOption();
			break;
		case ParserConst.KT_RANDOMIZE:
			nd = new NodeRandomize();
			break;
		case ParserConst.KT_REDIM:
			nd = new NodeRedim();
			break;
		// case MConst.KT_REM:
		// nd = new NodeRem();
		// break;
		case ParserConst.KT_SELECT:
			nd = new NodeSelect();
			break;
		case ParserConst.KT_SET:
			nd = new NodeSet();
			break;
		case ParserConst.KT_SUB:
			nd = new NodeSub();
			break;
		case ParserConst.KT_WHILE:
			nd = new NodeWhile();
			break;
		case ParserConst.KT_EXIT:
			nd = createByExit(env);
			break;
		default:
			throw new ParserException(env, tk,
					ExceptionDesc.DS004_NOT_STATEMENT);
		}

		if (nd != null)
		{
			// 设置节点关键字
			nd.keyword = tk;
			// 设置节点对应的vbs语句行
			nd.linenum = curline;
			// 设置上下文：当前正在解析的主语句以及子语句
			env.pushMainNode(tk, tk);
			nd.parse(env);
			// 设置上下文：完成主语句
			env.popMainNode();
		}

		return nd;
	}

	/**
	 * 创建以‘End’开始的语句，如果End语句不在vbs规范之内，抛出异常。
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createByEnd(ParserEnv env) throws Exception
	{
		Node nd = null;
		Token tk = env.nextToken();
		switch (tk.type)
		{
		case ParserConst.KT_IF:
			nd = new NodeEndIf();
			break;
		case ParserConst.KT_FUNCTION:
			nd = new NodeEndFunction();
			break;
		case ParserConst.KT_SELECT:
			nd = new NodeEndSelect();
			break;
		case ParserConst.KT_SUB:
			nd = new NodeEndSub();
			break;
		default:
			throw new ParserException(env, tk, ExceptionDesc.DS004_END);
		}

		return nd;
	}

	/**
	 * 创建以‘Exit’开始的语句，如果Exit语句不在vbs规范之内，抛出异常。
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createByExit(ParserEnv env) throws Exception
	{
		Token tk = env.nextToken();
		switch (tk.type)
		{
		case ParserConst.KT_DO:
			return new NodeExitDo();
		case ParserConst.KT_FUNCTION:
			return new NodeExitFunction();
		case ParserConst.KT_FOR:
			return new NodeExitFor();
		case ParserConst.KT_SUB:
			return new NodeExitSub();
		default:
			throw new ParserException(env, tk, ExceptionDesc.DS004_EXIT);
		}

	}

	/**
	 * 创建以‘Default’开始的语句，如果Default语句不在vbs规范之内，抛出异常。
	 * 忽略Default标记，将下一标记（token）作为当前语句标记。
	 * 
	 * @param env
	 * @param retTk
	 *            当前的语句标记
	 * @return
	 * @throws Exception
	 */
	private static Node createByDefault(ParserEnv env, Token retTk)
			throws Exception
	{
		retTk.copy(env.nextToken());
		switch (retTk.type)
		{
		case ParserConst.KT_FUNCTION:
			return new NodeFunction();
		case ParserConst.KT_SUB:
			return new NodeSub();
		default:
			throw new ParserException(env, retTk, ExceptionDesc.DS004_DEFAULT);
		}
	}

	/**
	 * 创建以‘Public/Private’开始的语句，如果Public/Private语句不在vbs规范之内，抛出异常。
	 * 忽略Public/Private标记，将下一标记（token）作为当前语句标记。
	 * 
	 * @param env
	 * @param retTk
	 *            当前的语句标记
	 * @return
	 * @throws Exception
	 */
	private static Node createByPublicOrPrivate(ParserEnv env, Token retTk)
			throws Exception
	{
		Token tk = env.nextToken();
		switch (tk.type)
		{
		case ParserConst.KT_FUNCTION:
			retTk.copy(tk);
			return new NodeFunction();
		case ParserConst.KT_SUB:
			retTk.copy(tk);
			return new NodeSub();
		case ParserConst.KT_VAR:
		case ParserConst.KT_OBJ:
		case ParserConst.KT_ARR:
			retTk.type = ParserConst.KT_DIM;
			retTk.str = "dim";
			env.pushBackT();
			return new NodeDim();
		case ParserConst.KT_CONST:
			retTk.copy(tk);
			return new NodeConst();
		default:
			throw new ParserException(env, retTk,
					ExceptionDesc.DS004_PUBLIC_PRIVATE);
		}
	}

	/**
	 * 创建以‘For’开始的语句。
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createByFor(ParserEnv env) throws Exception
	{
		Token tk = env.nextToken();
		if (tk.type == ParserConst.KT_EACH)
		{
			return new NodeForEach();
		}
		else
		{
			env.pushBackT();
			return new NodeFor();
		}
	}

	/**
	 * 创建以自定义函数标记开始的语句。
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createBySefFun(ParserEnv env) throws Exception
	{
		if (env.readNextToken().type == ParserConst.KT_EQ)
		{
			return new NodeFunctionReturn();
		}
		else
		{
			return new NodeCall();
		}
	}

	/**
	 * 创建以‘Case’开始的语句。
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createByCase(ParserEnv env, Token tk) throws Exception
	{
		if (env.nextToken().type == ParserConst.KT_ELSE)
		{
			// 为了区分 case 和 case else
			tk.type = ParserConst.KT_CASEELSE;
			return new NodeCaseElse();
		}
		else
		{
			env.pushBackT();
			return new NodeCase();
		}
	}

	/**
	 * 跳过语句间的换行、注释等
	 * 
	 * @param env
	 * @param sc
	 * @throws Exception
	 */
	private static void skipBeforeMainStatement(ParserEnv env,
			StatementContext sc) throws Exception
	{
		if (env.afterMainStatement())
		{
			skipLine(env, sc);
		}
		else
		{
			Token tk = env.getCurSub();
			Object gi = env.getGrammerInfo();
			if (tk != null)
			{
				if (tk.type == ParserConst.KT_DO
						|| tk.type == ParserConst.KT_WHILE
						|| tk.type == ParserConst.KT_UNTIL
						|| tk.type == ParserConst.KT_TO
						|| tk.type == ParserConst.KT_STEP
						// then ...
						|| (tk.type == ParserConst.KT_THEN && (gi == null || (Integer) gi == ParserConst.SEF_IF_BLOCK_GRAMMER))
						|| tk.type == ParserConst.KT_FUNCTION)
				{
					skipLine(env, sc);
					return;
				}
			}

			skip(env, sc);
		}
	}

	private static void skip(ParserEnv env, SkipCondition sk) throws Exception
	{
		while (true)
		{
			Token tk = env.nextToken();
			if (sk.ifTrue(tk))
			{
				continue;
			}
			else
			{
				env.pushBackT();
				sk.check();
				return;
			}
		}
	}

	/**
	 * 跳过注释、行等
	 * 
	 * @param env
	 * @param sc
	 * @throws Exception
	 */
	protected static void skip(ParserEnv env, StatementContext sc)
			throws Exception
	{
		skip(env, new SkipCondition(env, sc));
	}

	/**
	 * 跳过行
	 * 
	 * @param env
	 * @param sc
	 * @throws Exception
	 */
	protected static void skipLine(ParserEnv env, StatementContext sc)
			throws Exception
	{
		skip(env, new SkipLine(env, sc));
	}

	abstract protected void parse(ParserEnv env) throws Exception;

	abstract protected void print(StringBuilder sb, ParserEnv env)
			throws Exception;

	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		if (linenum != -1)
		{
			env.setCurLineNum(linenum);
			sb.append(env.indent()).append("cci.setCurrentLine(").append(
					linenum).append(");\r\n");
		}

		print(sb, env);
		sb.append("\r\n");

		if (sibling != null)
		{
			sibling.printJava(sb, env);
		}
	}
}
