package com.channelsoft.vbs2java_v2;

/**
 * ����Object����
 * 
 * @author dragon.huang
 */
public class ExprCallObj extends ExprComplex
{
	static ExprCallObj parseCallObj(ParserEnv env) throws Exception
	{
		ExprCallObj co = new ExprCallObj();
		co.exprTk = env.currToken();
		co.parseSubExpr(env);
		co.checkObjMember(env);
		return co;
	}

	/**
	 * �Ƿ�Ϊobject���󣬶�����object��Ա/���Ե��á�object��Ϊ�������á�
	 * 
	 * @return
	 */
	boolean isObj()
	{
		return (this.left == null && this.right == null && this.subexprlist == null);
	}

	/**
	 * ���object��Ա/���Ե��õĺϷ��ԡ�������object����Ч��Ա/���ԡ�
	 * 
	 * @param env
	 * @throws Exception
	 */
	void checkObjMember(ParserEnv env) throws Exception
	{
		if (subexprlist != null && subexprlist.size() > 0)
		{
			Expr expr = subexprlist.elementAt(0);
			if (expr instanceof ExprDotFun)
			{
				if (!env.keyword().isObjMember(
						ParserWord.encapsulateObj(exprTk), expr.exprTk.str))
				{
					throw new ParserException(env, expr.exprTk,
							ExceptionDesc.DS014);
				}
			}
		}
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(ParserWord.encapsulateObj(exprTk));
		super.printJava(sb, env);
	}
}
