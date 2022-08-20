package com.channelsoft.vbs2java_v2;

public abstract class Node extends Base
{
	/**
	 * ��ǰ���(Node)��vbs�е��к�
	 */
	int linenum = -1;

	/**
	 * ���Ĺؼ���
	 */
	Token keyword = null;

	/**
	 * ��һ�����
	 */
	Node sibling = null;

	/**
	 * ����������һ�������ڵ�
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
	 * ����������һ�������ڵ�
	 * 
	 * @param env
	 * @param sc
	 *            ���������
	 * @return
	 * @throws Exception
	 */
	static Node g_parse(ParserEnv env, StatementContext sc) throws Exception
	{
		// ���ڵ�
		Node root = null;
		// ��ǰ���ڵ�
		Node curr = null;
		// ��ǰ������һ�����
		Node sibling = null;
		// �������Token
		Token tk = null;

		while (true)
		{
			// �������С�ע�͵�
			skipBeforeMainStatement(env, sc);

			tk = env.nextToken();

			// �ж��Ƿ�ű�����
			if (env.isEnd(tk))
			{
				break;
			}

			// �����ⲿָ���������ж�����Ƿ����
			if (sc != null && sc.checkEndSubStatement(tk))
			{
				env.pushBackT();
				break;
			}

			// ����Token�������ڵ�
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
	 * ���������
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
	 * �����Զ����������
	 * 
	 * @param env
	 * @param type
	 *            ��������͡�������Ͳ��������׳��쳣��
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
	 * ����Token���������
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
			// ���ýڵ�ؼ���
			nd.keyword = tk;
			// ���������ģ���ǰ���ڽ����������
			env.setCurSub(tk);
			nd.parse(env);
			// ���������ģ����������ɵ������
			env.setPreSub(tk);
		}
		else
		{
			// �Ż�δʹ�õ�Token
			env.pushBackT();
		}

		return nd;
	}

	/**
	 * ����token��������䡣�����������Ͳ����ڣ����׳��쳣��
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
			// ���ýڵ�ؼ���
			nd.keyword = tk;
			// ���ýڵ��Ӧ��vbs�����
			nd.linenum = curline;
			// ���������ģ���ǰ���ڽ�����������Լ������
			env.pushMainNode(tk, tk);
			nd.parse(env);
			// ���������ģ���������
			env.popMainNode();
		}

		return nd;
	}

	/**
	 * �����ԡ�End����ʼ����䣬���End��䲻��vbs�淶֮�ڣ��׳��쳣��
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
	 * �����ԡ�Exit����ʼ����䣬���Exit��䲻��vbs�淶֮�ڣ��׳��쳣��
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
	 * �����ԡ�Default����ʼ����䣬���Default��䲻��vbs�淶֮�ڣ��׳��쳣��
	 * ����Default��ǣ�����һ��ǣ�token����Ϊ��ǰ����ǡ�
	 * 
	 * @param env
	 * @param retTk
	 *            ��ǰ�������
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
	 * �����ԡ�Public/Private����ʼ����䣬���Public/Private��䲻��vbs�淶֮�ڣ��׳��쳣��
	 * ����Public/Private��ǣ�����һ��ǣ�token����Ϊ��ǰ����ǡ�
	 * 
	 * @param env
	 * @param retTk
	 *            ��ǰ�������
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
	 * �����ԡ�For����ʼ����䡣
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
	 * �������Զ��庯����ǿ�ʼ����䡣
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
	 * �����ԡ�Case����ʼ����䡣
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	private static Node createByCase(ParserEnv env, Token tk) throws Exception
	{
		if (env.nextToken().type == ParserConst.KT_ELSE)
		{
			// Ϊ������ case �� case else
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
	 * ��������Ļ��С�ע�͵�
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
	 * ����ע�͡��е�
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
	 * ������
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
