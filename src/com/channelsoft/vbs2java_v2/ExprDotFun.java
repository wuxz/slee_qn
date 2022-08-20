package com.channelsoft.vbs2java_v2;

public class ExprDotFun extends Expr
{
	static ExprDotFun parse(ParserEnv env) throws Exception
	{
		ExprDotFun ed = new ExprDotFun();

		// ȥ����.��
		env.nextToken();
		// ����/�������� .funname(arg1, arg2,...)
		Token tk = env.nextToken();
		if (tk.isAlpha())
		{
			// funname���ǹؼ��֣��ʷ�����ʱ������Var������removeȥ����Var.
			env.keyword().removeLastVar(tk);
			ed.arglist = new ExprArglistForCallDotfun();
			ed.arglist.parse(env);
			ed.exprTk = tk;
		}
		else
		{
			throw new ParserException(env, tk, ExceptionDesc.DS008);
		}

		return ed;
	}

	// ��������
	ExprArglistForCallDotfun arglist = null;

	void printJavaForGetProp(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(".");
		if (this.left.exprTk.type == ParserConst.KT_INTERNALOBJ)
		{// ���ö���
		// if (this.left.exprTk.str.equals("err"))
		// {
		// sb.append("invoke(");
		// sb.append("\"").append(this.exprTk.str).append("\", ");
		// this.arglist.printJavaForGetProp(sb, env);
		// sb.append(")");
		// }
		// else
			{
				// obj.funname(arglist)
				sb.append(this.exprTk.str);
				sb.append("(");
				this.arglist.printJavaForGetProp(sb, env);
				sb.append(")");
			}
		}
		else
		{// com����
			// obj.invokeObjectMethod("funname", arglist)
			sb.append("invokeObjectMethod(");
			sb.append("\"").append(this.exprTk.str).append("\", ");
			this.arglist.printJavaForGetProp(sb, env);
			sb.append(")");
		}
	}

	void printJavaForSetProp(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(".");
		if (this.left.exprTk.type == ParserConst.KT_INTERNALOBJ)
		{// ���ö���
			// obj.setpropertyname(new Variant[]{arglist, propertyvalue})
			sb.append("setProperty").append("(\"").append(this.exprTk.str)
					.append("\", ");
			sb.append("new Variant[]{");
			if (this.arglist.hasArgument())
			{
				this.arglist.printJavaForSetProp(sb, env);
				sb.append(", ");
			}
			right.printVarJava(sb, env);
			sb.append("}");
			sb.append(")");
		}
		else
		{// com����
			// obj.setObjectProperty("propertyname", new Variant[]{arglist,
			// propertyvalue})
			sb.append("setObjectProperty(");
			sb.append("\"").append(this.exprTk.str).append("\", ");
			sb.append("new Variant[]{");
			if (this.arglist.hasArgument())
			{
				this.arglist.printJavaForSetProp(sb, env);
				sb.append(", ");
			}
			right.printVarJava(sb, env);
			sb.append("}");
			sb.append(")");
		}
	}
}
