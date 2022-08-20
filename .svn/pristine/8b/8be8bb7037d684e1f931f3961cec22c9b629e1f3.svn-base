package com.channelsoft.vbs2java_v2;

public class ExprDotFun extends Expr
{
	static ExprDotFun parse(ParserEnv env) throws Exception
	{
		ExprDotFun ed = new ExprDotFun();

		// 去掉‘.’
		env.nextToken();
		// 方法/参数解析 .funname(arg1, arg2,...)
		Token tk = env.nextToken();
		if (tk.isAlpha())
		{
			// funname不是关键字，词法解析时被当成Var，调用remove去掉此Var.
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

	// 方法参数
	ExprArglistForCallDotfun arglist = null;

	void printJavaForGetProp(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append(".");
		if (this.left.exprTk.type == ParserConst.KT_INTERNALOBJ)
		{// 内置对象
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
		{// com对象
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
		{// 内置对象
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
		{// com对象
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
