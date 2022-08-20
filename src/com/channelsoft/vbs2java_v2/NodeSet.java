package com.channelsoft.vbs2java_v2;

import java.util.Vector;

import com.channelsoft.vbs2java_v2.ExprArglist.ArgNode;

/**
 * set obj = ...
 * 
 * @author dragon.huang
 */
public class NodeSet extends Node
{
	class EC implements ExprContext
	{

		public boolean checkEndExpr(Token tk)
		{
			return (tk.type == ParserConst.KT_EQ);
		}

	}

	// �ɶ������ơ���һ��������Ϊ��ͬ�������͵ı����򷵻���ͬ�������͵Ķ���ĺ����򷽷���ɵı��ʽ��
	// �� Nothing
	Expr objExpr = null;

	// ���������Ե����ƣ���ѭ��׼��������Լ����
	Expr objvar = null;

	@Override
	protected void parse(ParserEnv env) throws Exception
	{
		objvar = Expr.g_parse(env, new EC());
		if ((objvar == null) || !objvar.isReturnVar())
		{
			throw new ParserException(env, ExceptionDesc.DS003_SET);
		}
		if (objvar instanceof ExprVar)
		{
			if (((ExprVar) objvar).isVar())
			{
				env.keyword().addObj(objvar.exprTk);
			}
		}

		if (env.nextToken().type != ParserConst.KT_EQ)
		{
			throw new ParserException(env, ExceptionDesc.DS004_SET);
		}

		if (objvar != null)
		{
			// ��=����ʽ��Ϊ�﷨��Ϣ������=�ұ��ʽʱ����ʹ��
			env.setGrammerInfo(objvar);
			objExpr = Expr.g_parse(env);
			if (objExpr == null)
			{
				throw new ParserException(env, ExceptionDesc.DS004_SET);
			}
			else if (!objExpr.isReturnVar())
			{
				throw new ParserException(env, objExpr.exprTk,
						ExceptionDesc.DS003_SET_NO_OBJ);
			}
		}
	}

	@Override
	protected void print(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(env.indent());
		objvar.printJava(sb, env);
		if (objExpr instanceof ExprNothing)
		{
			sb.append(".setNothing();");
		}
		else
		{
			if ((objExpr instanceof ExprCallFun)
					&& (objExpr.exprTk.str.equals("createobject") || objExpr.exprTk.str
							.equals("getobject")))
			{
				String funStr = ".createObject(";
				if (objExpr.exprTk.str.equals("getobject"))
				{
					funStr = ".getObject(";
				}

				if (((ExprCallFun) objExpr).subexprlist != null)
				{
					sb.append(".setValue(");
					sb.append("new Variant(cci)");
				}
				sb.append(funStr);

				Vector<ArgNode> arglist =
						((ExprCallFun) objExpr).arglist.arglist;
				if ((arglist.size() == 2) || (arglist.size() == 1))
				{
					arglist.elementAt(0).varname.printStrJava(sb, env);
					sb.append(", ");
					if (arglist.size() == 2)
					{
						arglist.elementAt(1).varname.printStrJava(sb, env);
					}
					else
					{
						sb.append("null");
					}

					if (((ExprCallFun) objExpr).subexprlist != null)
					{
						sb.append(")");
						((ExprCallFun) objExpr).superPrintJava(sb, env);
					}
					sb.append(");");
				}
				else
				{
					throw new ParserException(env, ExceptionDesc.DS017);
				}
			}
			else
			{
				sb.append(".setValue(");
				objExpr.printVarJava(sb, env);
				sb.append(");");
			}
		}
	}
}
