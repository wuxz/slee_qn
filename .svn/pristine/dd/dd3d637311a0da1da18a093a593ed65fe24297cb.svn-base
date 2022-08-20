package com.channelsoft.vbs2java_v2;

/**
 * for {init} {to} {step}
 * </p>
 * {statements}
 * </p>
 * {next}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeFor extends Node
{

	NodeComplex init = null;

	NodeTo to = null;

	NodeStep step = null;

	Node statements = null;

	NodeNext next = null;

	/**
	 * for循环break时，设置break标记。
	 */
	GrammerInfo gi = new GrammerInfo();

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		gi.breakTag = "tag" + this.linenum;
		env.setGrammerInfo(gi);

		init = NodeComplex.parseWithCheck(env, new CG(), false);

		to = (NodeTo) createSubStatement(env, ParserConst.KT_TO);

		Node nd = createSubStatement(env);
		if (nd != null)
		{// step 可缺省
			if (nd instanceof NodeStep)
			{
				step = (NodeStep) nd;
			}
			else
			{
				throw new ParserException(env, nd.keyword,
						ExceptionDesc.DS004_FOR);
			}
		}

		statements = g_parse(env, new SC());

		next = (NodeNext) createSubStatement(env, ParserConst.KT_NEXT);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		String to_str = "";
		String step_str = "";
		Expr counter = init.expr;
		boolean istovar = to.constnum.num.isReturnVar();
		boolean isstepvar = (step != null && step.constnum.num.isReturnVar());
		if (istovar || isstepvar)
		{
			counter.printJava(sb, env);
			sb.append(";\r\n");
		}
		if (istovar)
		{
			to_str = "_for_to_" + linenum;
			sb.append(env.indent()).append("Variant ").append(to_str).append(
					" = new Variant(");
			to.print(sb, env);
			sb.append(");\r\n");
		}
		if (isstepvar)
		{
			step_str = "_for_step_" + linenum;
			sb.append(env.indent()).append("Variant ").append(step_str).append(
					" = new Variant(");
			step.print(sb, env);
			sb.append(");\r\n");
		}
		if (gi.needBreakTag)
		{
			sb.append(gi.breakTag).append(" : ");
		}
		sb.append(env.indent()).append("for(");
		if (!(istovar || isstepvar))
		{
			counter.printJava(sb, env);
		}
		sb.append("; ");
		sb.append(counter.exprTk.str);
		sb.append(".notGT(");
		if (to_str.equals(""))
		{
			to.print(sb, env);
		}
		else
		{
			sb.append(to_str);
		}
		sb.append("); ");
		sb.append(counter.exprTk.str);
		sb.append(".setValue(");
		sb.append(counter.exprTk.str);
		sb.append(".plus(");
		if (step == null)
		{
			sb.append("1");
		}
		else
		{
			if (step_str.equals(""))
			{
				step.print(sb, env);
			}
			else
			{
				sb.append(step_str);
			}
		}
		sb.append(")))\r\n");

		sb.append(env.lBracket());
		if (statements != null)
		{
			statements.printJava(sb, env);
		}
		sb.append(env.rBracket());
	}

	/**
	 * for循环break时，设置break标记。
	 */
	static class GrammerInfo
	{
		String breakTag = "";

		boolean needBreakTag = false;
	}

	private class CG implements CheckComplexGrammer
	{

		public boolean check(NodeComplex nc)
		{// 这个变量不能是数组元素或用户自定义类型的元素
			return (!(nc.expr instanceof ExprArr) && nc.expr.exprTk.isAlpha() && nc.expr.right != null);
		}

	}

	private class SC implements StatementContext
	{

		public boolean checkEndSubStatement(Token tk)
		{
			return (tk.type == ParserConst.KT_NEXT);
		}

		public boolean checkMainStatement(Token tk)
		{
			return false;
		}

		public boolean checkPreSubStatement(Token tk)
		{
			return false;
		}

	}
}
