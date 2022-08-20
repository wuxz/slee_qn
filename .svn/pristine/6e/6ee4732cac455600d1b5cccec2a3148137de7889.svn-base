package com.channelsoft.vbs2java_v2;

/**
 * for each {element} {in}
 * </p>
 * {statements}
 * </p>
 * {next}
 * </p>
 * 
 * @author dragon.huang
 */
public class NodeForEach extends Node
{

	Token element = null;

	NodeIn in = null;

	Node statements = null;

	NodeNext next = null;

	/**
	 * for循环break时，设置break标记。
	 */
	NodeFor.GrammerInfo gi = new NodeFor.GrammerInfo();

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		gi.breakTag = "tag" + this.linenum;
		env.setGrammerInfo(gi);

		env.setPreSub(env.currToken());

		element = env.nextToken();
		if (!(element.isVar() || element.isObj()))
		{// element 可能是 Variant 变量、通用 Object 变量或任意指定的 Automation 对象变量
			throw new ParserException(env, ExceptionDesc.DS002_FOR_EACH);
		}

		in = (NodeIn) createSubStatement(env, ParserConst.KT_IN);

		statements = g_parse(env, new SC());

		next = (NodeNext) createSubStatement(env, ParserConst.KT_NEXT);
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		String _idx = "_foreach_" + this.linenum + "_idx";
		if (gi.needBreakTag)
		{
			sb.append(gi.breakTag).append(" : ");
		}
		sb.append(env.indent()).append("for(");
		sb.append("int ").append(_idx).append("=0; ").append(_idx).append("<=");
		in.printJava(sb, env);
		sb.append(".arrayUBound;").append(_idx).append("++)\r\n");
		sb.append(env.lBracket());
		sb.append(env.indent()).append(element.str);
		sb.append(".setValue(");
		in.printJava(sb, env);
		sb.append(".getElementAt(");
		sb.append("new Variant[]{new Variant(");
		sb.append(_idx);
		sb.append(", cci)}");
		sb.append(")");
		sb.append(");\r\n");
		if (statements != null)
		{
			statements.printJava(sb, env);
		}
		sb.append(env.rBracket());
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
