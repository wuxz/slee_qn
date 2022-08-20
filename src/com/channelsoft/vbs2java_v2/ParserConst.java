package com.channelsoft.vbs2java_v2;

/**
 * 解析过程常量定义
 * 
 * @author dragon.huang
 */
public class ParserConst
{

	public static final int KT_ADD = -112;

	public static final int KT_AND = -131;

	/**
	 * 数组
	 */
	public static final int KT_ARR = -109;

	public static final int KT_AS = -32;

	/**
	 * 自定义函数形参调用方式
	 */
	public static final int KT_BYREF = -61;

	/**
	 * 自定义函数形参调用方式
	 */
	public static final int KT_BYVAL = -60;

	public static final int KT_CALL = -51;

	/**
	 * vbs内置函数调用
	 */
	public static final int KT_CALLINTERNALFUN = -50;

	/**
	 * 自定义函数调用
	 */
	public static final int KT_CALLSEFDEFFUN = -105;

	public static final int KT_CASE = -46;

	public static final int KT_CASEELSE = -47;

	// 新增
	public static final int KT_CLASS = -82;

	/**
	 * ：(冒号)
	 */
	public static final int KT_COLON = -3;

	/**
	 * ,
	 */
	public static final int KT_COMMA = -120;

	/**
	 * const标示符
	 */
	public static final int KT_CONST = -106;

	public static final int KT_DEFAULT = -48;

	public static final int KT_DIM = -31;

	/**
	 * /
	 */
	public static final int KT_DIV = -115;

	public static final int KT_DO = -43;

	/**
	 * .fun
	 */
	public static final int KT_DOT = -128;

	public static final int KT_EACH = -88;

	public static final int KT_ELSE = -37;

	public static final int KT_ELSEIF = -38;

	public static final int KT_EMPTY = -152;

	public static final int KT_END = -39;

	/**
	 * end of file
	 */
	public static final int KT_EOF = -1;

	/**
	 * end of line
	 */
	public static final int KT_EOL = -2;

	public static final int KT_EQ = -119;

	public static final int KT_EQV = -134;

	public static final int KT_ERASE = -83;

	/**
	 * unknow
	 */
	public static final int KT_ERR = -1000;

	public static final int KT_EXECUTE = -84;

	public static final int KT_EXIT = -25;

	public static final int KT_FOR = -20;

	/**
	 * 函数定义标示符
	 */
	public static final int KT_FUNCTION = -52;

	public static final int KT_GET = -99;

	public static final int KT_GT = -122;

	public static final int KT_IF = -35;

	public static final int KT_IMP = -135;

	public static final int KT_IN = -89;

	/**
	 * 输入知识变量
	 */
	public static final int KT_IN_KNOWLEDGEVAR = -160;

	public static final int KT_INIT = -2000;

	/**
	 * \
	 */
	public static final int KT_INTDIV = -116;

	/**
	 * 内置对象
	 */
	public static final int KT_INTERNALOBJ = -54;

	public static final int KT_IS = -127;

	// "()+-/\\*^=,<>&"
	public static final int KT_LEFT_BRACKET = -110;

	public static final int KT_LET = -100;

	public static final int KT_LOOP = -40;

	public static final int KT_LT = -121;

	public static final int KT_MINUS = -113;

	/**
	 * 负号(负数)
	 */
	public static final int KT_MINUX = -114;

	// public static final int KT_ERROR = -95;

	// public static final int KT_RESUME = -96;

	public static final int KT_MOD = -11;

	// public static final int KT_EXPLICIT = -98;

	public static final int KT_MULTIPLY = -117;

	public static final int KT_NEQ = -126;

	public static final int KT_NEXT = -24;

	public static final int KT_NGT = -124;

	public static final int KT_NLT = -123;

	public static final int KT_NOT = -130;

	/**
	 * 注释，notation
	 */
	public static final int KT_NOTE = -107;

	public static final int KT_NOTHING = -150;

	public static final int KT_NULL = -151;

	/**
	 * Com对象
	 */
	public static final int KT_OBJ = -104;

	public static final int KT_ON = -94;

	public static final int KT_OPTION = -97;

	public static final int KT_OR = -132;

	/**
	 * 输出知识变量
	 */
	public static final int KT_OUT_KNOWLEDGEVAR = -161;

	/**
	 * ^
	 */
	public static final int KT_POWER = -118;

	public static final int KT_PRESERVE = -103;

	public static final int KT_PRIVATE = -91;

	public static final int KT_PROPERTY = -86;

	public static final int KT_PUBLIC = -90;

	public static final int KT_RANDOMIZE = -101;

	public static final int KT_REDIM = -102;

	public static final int KT_REM = -92;

	public static final int KT_RIGHT_BRACKET = -111;

	public static final int KT_SELECT = -45;

	public static final int KT_SET = -93;

	public static final int KT_STEP = -23;

	/**
	 * &
	 */
	public static final int KT_STRCAT = -125;

	public static final int KT_SUB = -87;

	public static final int KT_THEN = -36;

	public static final int KT_TO = -22;

	/**
	 * _ （下划线）
	 */
	public static final int KT_UNDERLINE = -4;

	public static final int KT_UNTIL = -42;

	public static final int KT_VAR = -108;

	/**
	 * vbs常量
	 */
	public static final int KT_VBCONST = -63;

	public static final int KT_WEND = -44;

	public static final int KT_WHILE = -41;

	public static final int KT_WITH = -85;

	public static final int KT_XOR = -133;

	/**
	 * if语句语法是块结构
	 */
	public static final int SEF_IF_BLOCK_GRAMMER = 2;

	/**
	 * if语句语法是行结构
	 */
	public static final int SEF_IF_LINE_GRAMMER = 1;

	/**
	 * float常量
	 */
	public static final int TT_FLOAT = -6;

	/**
	 * int常量
	 */
	public static final int TT_INT = -5;

	/**
	 * string常量
	 */
	public static final int TT_STR = -7;

	/**
	 * 是否比较
	 * 
	 * @param kt
	 * @return
	 */
	public static boolean isCompareOp(int kt)
	{
		return ((kt == ParserConst.KT_NLT) || (kt == ParserConst.KT_GT)
				|| (kt == ParserConst.KT_NGT) || (kt == ParserConst.KT_NEQ)
				|| (kt == ParserConst.KT_LT) || (kt == ParserConst.KT_EQ) || (kt == ParserConst.KT_IS));
	}

	/**
	 * 是否逻辑运算符
	 * 
	 * @param kt
	 * @return
	 */
	public static boolean isLogicalOp(int kt)
	{
		return ((kt == ParserConst.KT_NOT) || (kt == ParserConst.KT_AND)
				|| (kt == ParserConst.KT_OR) || (kt == ParserConst.KT_XOR)
				|| (kt == ParserConst.KT_EQV) || (kt == ParserConst.KT_IMP));
	}
}
