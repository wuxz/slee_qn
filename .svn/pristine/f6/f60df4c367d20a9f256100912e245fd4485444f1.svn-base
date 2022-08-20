package com.channelsoft.vbs2java_v2;

public class ExprStr extends Expr
{
	// 转换对斜号的转换
	static public String changeSlash(String str)
	{
		return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\t",
				"\\t").replace("\n", "\\n").replace("\r", "\\r");
	}

	@Override
	void printJava(StringBuilder sb, ParserEnv env) throws Exception
	{
		sb.append("\"");
		sb.append(changeSlash(exprTk.str));
		sb.append("\"");
	}
}
