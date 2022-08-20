package com.channelsoft.vbs2java_v2;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.channelsoft.slee.usmlreasoning.KnowledgeVariable;

class InternalObj
{
	/**
	 * 实现此对象的的类的名称。
	 */
	String className = "";

	/**
	 * 实现此对象的方法列表。
	 */
	Vector<String> funcs = new Vector<String>();

	/**
	 * 生成的JAVA代码中的变量的名称。
	 */
	String javaName = "";

	/**
	 * 此对象的VB脚本中的变量名称。
	 */
	String vbName = "";
}

/**
 * 关键字类。存放vbs中所有关键字及其类型。
 * 
 * @author dragon.huang
 */
public class ParserWord
{
	/**
	 * vbs常量以及对应java数值
	 */
	private static Map<String, String> constmap = new Hashtable<String, String>();

	/**
	 * java关键字。vbs的变量名不能与之雷同。
	 */
	private static Vector<String> javaKeywords = new Vector<String>();

	/**
	 * 从内置对象的代码的变量名称到实现它的类的对照表。
	 */
	private static HashMap<String, InternalObj> javaName2Obj = new HashMap<String, InternalObj>();

	/**
	 * vbs关键字以及类型
	 */
	private static Map<String, Integer> keymap = new Hashtable<String, Integer>();

	public static final String SLEE_INTERNAL_FUN = "internalMethod";

	/**
	 * 从内置对象的VB名称到实现它的类的对照表。
	 */
	private static HashMap<String, InternalObj> vbName2Obj = new HashMap<String, InternalObj>();

	/**
	 * 返回obj的java名称
	 * 
	 * @param objtk
	 * @return
	 */
	static String encapsulateObj(Token objtk)
	{
		InternalObj obj = vbName2Obj.get(objtk.str);
		if (obj != null)
		{
			return obj.javaName;
		}

		return objtk.e_str();
	}

	/**
	 * 变量名称封装： 遇到variant违反java命名规则，则封装为channelsoft_var形式
	 * 
	 * @param var
	 * @return
	 */
	static String encapsulateVar(String varname)
	{
		if (javaKeywords.contains(varname) || !isValidJavaIdentifer(varname))
		{
			return "channelsoft_" + varname;
		}

		return varname;
	}

	private static boolean isValidJavaIdentifer(String token)
	{
		if ((token.length() == 0)
				|| !Character.isJavaIdentifierStart(token.charAt(0)))
		{
			return false;
		}

		for (int i = 0; i < token.length(); i++)
		{
			if (!Character.isJavaIdentifierPart(token.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否为vbs的boolean常量
	 * 
	 * @param key
	 * @return
	 */
	static boolean isvbConstBool(String key)
	{
		return key.equals("true") || key.equals("false")
				|| key.equals("vbtrue") || key.equals("vbfalse");
	}

	/**
	 * 是否为vbs的数字常量
	 * 
	 * @param key
	 * @return
	 */
	static boolean isvbConstNum(String key)
	{
		return !(isvbConstBool(key) || isvbConstStr(key));
	}

	/**
	 * 是否为vbs的字符串常量
	 * 
	 * @param key
	 * @return
	 */
	static boolean isvbConstStr(String key)
	{
		return key.equals("vbcr") || key.equals("vbcrlf")
				|| key.equals("vbformfeed") || key.equals("vblf")
				|| key.equals("vbnewline") || key.equals("vbnullchar")
				|| key.equals("vbnullstring") || key.equals("vbtab")
				|| key.equals("vbverticaltab");
	}

	private static void load()
	{
		if (keymap.size() == 0)
		{
			loadKey();
			loadConst();
			loadInternalObj();
			loadInternalObjMember();
			loadJavaKeyword();
		}
	}

	private static void loadConst()
	{
		constmap.put("true", "true");
		constmap.put("false", "false");
		constmap.put("vbtrue", "true");
		constmap.put("vbfalse", "false");

		constmap.put("vbblack", "0");
		constmap.put("vbred", "255");
		constmap.put("vbgreen", "65280");
		constmap.put("vbyellow", "65535");
		constmap.put("vbblue", "16711680");
		constmap.put("vbmagenta", "16711935");
		constmap.put("vbcyan", "16776960");
		constmap.put("vbwhite", "16777215");
		constmap.put("vbbinarycompare", "0");
		constmap.put("vbtextcompare", "1");

		constmap.put("vbsunday", "1");
		constmap.put("vbmonday", "2");
		constmap.put("vbtuesday", "3");
		constmap.put("vbwednesday", "4");
		constmap.put("vbthursday", "5");
		constmap.put("vbfriday", "6");
		constmap.put("vbsaturday", "7");
		constmap.put("vbusesystem", "0");
		constmap.put("vbusesystemdayofweek", "0");
		constmap.put("vbfirstjan1", "1");
		constmap.put("vbfirstfourdays", "2");
		constmap.put("vbfirstfullweek", "3");

		constmap.put("vbgeneraldate", "0");
		constmap.put("vblongdate", "1");
		constmap.put("vbshortdate", "2");
		constmap.put("vblongtime", "3");
		constmap.put("vbshorttime", "4");

		constmap.put("vbobjecterror", "-2147221504");

		constmap.put("vbokonly", "0");
		constmap.put("vbokcancel", "1");
		constmap.put("vbabortretryignore", "2");
		constmap.put("vbyesnocancel", "3");
		constmap.put("vbyesno", "4");
		constmap.put("vbretrycancel", "5");
		constmap.put("vbcritical", "16");
		constmap.put("vbquestion", "32");
		constmap.put("vbexclamation", "48");
		constmap.put("vbinformation", "64");
		constmap.put("vbdefaultbutton1", "0");
		constmap.put("vbdefaultbutton2", "256");
		constmap.put("vbdefaultbutton3", "512");
		constmap.put("vbdefaultbutton4", "768");
		constmap.put("vbapplicationmodal", "0");
		constmap.put("vbsystemmodal", "4096");
		constmap.put("vbok", "1");
		constmap.put("vbcancel", "2");
		constmap.put("vbabort", "3");
		constmap.put("vbretry", "4");
		constmap.put("vbignore", "5");
		constmap.put("vbyes", "6");
		constmap.put("vbno", "7");
		constmap.put("vbcr", "\"\\r\"");
		constmap.put("vbcrlf", "\"\\r\\n\"");
		constmap.put("vbformfeed", "\"\\f\"");
		constmap.put("vblf", "\"\\n\"");
		constmap.put("vbnewline", "\"\\r\\n\"");
		constmap.put("vbnullchar", "\"\\0\"");
		constmap.put("vbnullstring", "null");
		constmap.put("vbtab", "\"\\t\"");
		constmap.put("vbverticaltab", "String.valueOf((char)11)");

		constmap.put("vbusedefault", "-2");
		// vbtrue -1
		// vbfalse 0

		constmap.put("vbempty", "0");
		constmap.put("vbnull", "1");
		constmap.put("vbinteger", "2");
		constmap.put("vblong", "3");
		constmap.put("vbsingle", "4");
		constmap.put("vbdouble", "5");
		constmap.put("vbcurrency", "6");
		constmap.put("vbdate", "7");
		constmap.put("vbstring", "8");
		constmap.put("vbobject", "9");
		constmap.put("vberror", "10");
		constmap.put("vbboolean", "11");
		constmap.put("vbvariant", "12");
		constmap.put("vbdataobject", "13");
		constmap.put("vbdecimal", "14");
		constmap.put("vbbyte", "17");
		constmap.put("vbarray", "8192");
	}

	private static void loadInternalObj()
	{
		String vbNames[] = new String[] { "", "err", "sleeapp",
				"sleecallcontroller", "sleeservice", "sleesession" };
		String javaNames[] = new String[] { SLEE_INTERNAL_FUN, "internalErr",
				"internalSleeApp", "internalSleeCallController",
				"internalSleeService", "internalSleeSession" };
		String classNames[] = new String[] {
				"com.channelsoft.slee.usmlreasoning.InternalMethod",
				"com.channelsoft.slee.usmlreasoning.InternalErr",
				"com.channelsoft.slee.usmlreasoning.InternalSleeApp",
				"com.channelsoft.slee.usmlreasoning.InternalSleeCallController",
				"com.channelsoft.slee.usmlreasoning.InternalSleeService",
				"com.channelsoft.slee.usmlreasoning.InternalSleeSession" };

		for (int i = 0; i < vbNames.length; i++)
		{
			InternalObj obj = new InternalObj();
			obj.vbName = vbNames[i];
			obj.javaName = javaNames[i];
			obj.className = classNames[i];

			vbName2Obj.put(obj.vbName, obj);
			javaName2Obj.put(obj.javaName, obj);
		}
	}

	private static void loadInternalObjMember()
	{
		// internalFuns
		// ---- com.channelsoft.slee.usmlreasoning.InternalMethod
		// sleeappFuns
		// ---- com.channelsoft.slee.usmlreasoning.InternalSleeApp
		// sleeserviceFuns
		// ---- com.channelsoft.slee.usmlreasoning.InternalSleeService
		// sleesessionFuns
		// ---- com.channelsoft.slee.usmlreasoning.InternalSleeSession
		// sleecallcontrollerFuns
		// ---- com.channelsoft.slee.usmlreasoning.InternalSleeCallController
		Vector<String> rids = new Vector<String>();
		// rids.add("sleep");
		// rids.add("timer");
		rids.add("unescape");
		// rids.add("showmsg");
		rids.add("getdefaultpropertyname");
		rids.add("getproperty");
		rids.add("invoke");
		rids.add("setcomputingcontext");
		rids.add("setproperty");
		rids.add("getclass");
		rids.add("hashcode");
		rids.add("equals");
		rids.add("tostring");
		rids.add("notify");
		rids.add("notifyall");
		rids.add("wait");
		rids.add("init");

		Iterator<Entry<String, InternalObj>> it = vbName2Obj.entrySet()
				.iterator();

		while (it.hasNext())
		{
			Entry<String, InternalObj> entry = it.next();
			InternalObj obj = entry.getValue();

			if (obj.vbName.equals(""))
			{
				// 不是InternalMethod的方法，是Variant的方法。
				obj.funcs.add("createobject");
				obj.funcs.add("getobject");

				// 内置方法有int，但类中不能有int，因为是关键字。
				obj.funcs.add("int");
			}

			Class<?> cls;
			try
			{
				cls = Class.forName(obj.className);
				Method methlist[] = cls.getMethods();
				for (int j = 0; j < methlist.length; j++)
				{
					String name = methlist[j].getName().toLowerCase();
					if (!rids.contains(name))
					{
						obj.funcs.add(name);
					}
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void loadJavaKeyword()
	{
		javaKeywords.add("boolean");
		javaKeywords.add("byte");
		javaKeywords.add("char");
		javaKeywords.add("double");
		javaKeywords.add("float");
		javaKeywords.add("int");
		javaKeywords.add("long");
		javaKeywords.add("short");
		// javaKeywords.add("public"); ---也是vbs关键字
		// javaKeywords.add("private");
		// javaKeywords.add("protected");
		javaKeywords.add("abstract");
		javaKeywords.add("final");
		javaKeywords.add("native");
		javaKeywords.add("static");
		javaKeywords.add("strictfp");
		javaKeywords.add("synchronized");
		javaKeywords.add("transient");
		javaKeywords.add("volatile");
		// javaKeywords.add("if");
		// javaKeywords.add("else");
		// javaKeywords.add("do");
		// javaKeywords.add("while");
		javaKeywords.add("switch");
		// javaKeywords.add("case");
		// javaKeywords.add("default");
		javaKeywords.add("for");
		javaKeywords.add("break");
		javaKeywords.add("continue");
		javaKeywords.add("class");
		javaKeywords.add("extends");
		javaKeywords.add("implements");
		javaKeywords.add("import");
		javaKeywords.add("instanceof");
		javaKeywords.add("interface");
		javaKeywords.add("new");
		javaKeywords.add("package");
		javaKeywords.add("super");
		javaKeywords.add("this");
		javaKeywords.add("catch");
		javaKeywords.add("finally");
		javaKeywords.add("try");
		javaKeywords.add("throw");
		javaKeywords.add("throws");
		javaKeywords.add("return");
		javaKeywords.add("void");
		// javaKeywords.add("const");
		javaKeywords.add("goto");
	}

	private static void loadKey()
	{
		keymap.put("dim", ParserConst.KT_DIM);
		keymap.put("const", ParserConst.KT_CONST);
		keymap.put("as", ParserConst.KT_AS);

		// keymap.put("integer", MConst.KT_DATATYPE);
		// keymap.put("char", MConst.KT_DATATYPE);
		// keymap.put("string", MConst.KT_DATATYPE);
		// keymap.put("long", MConst.KT_DATATYPE);

		keymap.put("if", ParserConst.KT_IF);
		keymap.put("then", ParserConst.KT_THEN);
		keymap.put("else", ParserConst.KT_ELSE);
		keymap.put("elseif", ParserConst.KT_ELSEIF);
		keymap.put("end", ParserConst.KT_END);

		keymap.put("mod", ParserConst.KT_MOD);

		keymap.put("and", ParserConst.KT_AND);
		keymap.put("or", ParserConst.KT_OR);
		keymap.put("xor", ParserConst.KT_XOR);
		keymap.put("not", ParserConst.KT_NOT);
		keymap.put("eqv", ParserConst.KT_EQV);
		keymap.put("imp", ParserConst.KT_IMP);

		keymap.put("for", ParserConst.KT_FOR);
		keymap.put("to", ParserConst.KT_TO);
		keymap.put("next", ParserConst.KT_NEXT);
		keymap.put("step", ParserConst.KT_STEP);

		keymap.put("exit", ParserConst.KT_EXIT);

		keymap.put("select", ParserConst.KT_SELECT);
		keymap.put("case", ParserConst.KT_CASE);

		keymap.put("do", ParserConst.KT_DO);
		keymap.put("loop", ParserConst.KT_LOOP);
		keymap.put("while", ParserConst.KT_WHILE);
		keymap.put("wend", ParserConst.KT_WEND);
		keymap.put("until", ParserConst.KT_UNTIL);

		// 新增
		keymap.put("call", ParserConst.KT_CALL);
		keymap.put("class", ParserConst.KT_CLASS);
		keymap.put("erase", ParserConst.KT_ERASE);
		// keymap.put("execute", ParserConst.KT_EXECUTE);
		keymap.put("function", ParserConst.KT_FUNCTION);
		keymap.put("property", ParserConst.KT_PROPERTY);
		keymap.put("sub", ParserConst.KT_SUB);
		keymap.put("each", ParserConst.KT_EACH);
		keymap.put("in", ParserConst.KT_IN);
		keymap.put("default", ParserConst.KT_DEFAULT);
		keymap.put("public", ParserConst.KT_PUBLIC);
		keymap.put("private", ParserConst.KT_PRIVATE);
		keymap.put("byval", ParserConst.KT_BYVAL);
		keymap.put("byref", ParserConst.KT_BYREF);
		keymap.put("on", ParserConst.KT_ON);
		// keymap.put("error", MConst.KT_ERROR); //可作为变量使用
		// keymap.put("resume", MConst.KT_RESUME);
		keymap.put("option", ParserConst.KT_OPTION);
		// keymap.put("explicit", MConst.KT_EXPLICIT);
		keymap.put("get", ParserConst.KT_GET);
		keymap.put("let", ParserConst.KT_LET);
		keymap.put("randomize", ParserConst.KT_RANDOMIZE);
		keymap.put("redim", ParserConst.KT_REDIM);
		keymap.put("preserve", ParserConst.KT_PRESERVE);
		keymap.put("rem", ParserConst.KT_REM);
		keymap.put("set", ParserConst.KT_SET);
		keymap.put("with", ParserConst.KT_WITH);
		keymap.put("nothing", ParserConst.KT_NOTHING);
		keymap.put("null", ParserConst.KT_NULL);
		keymap.put("empty", ParserConst.KT_EMPTY);
		keymap.put("is", ParserConst.KT_IS);
	}

	/**
	 * 是否为vbs常量
	 * 
	 * @param key
	 * @return
	 */
	static String vbConst(String key)
	{
		return constmap.get(key);
	}

	private ParserEnv env = null;

	/**
	 * [in]知识变量
	 */
	private Vector<KnowledgeVariable> inknvarList = null;

	/**
	 * [out]知识变量
	 */
	private Vector<KnowledgeVariable> outknvarList = null;

	/**
	 * vbs脚本的定义数组
	 */
	private Vector<String> sefDefArr = new Vector<String>();

	/**
	 * vbs脚本的自定义函数
	 */
	private Vector<NodeSefFun> sefDefFuns = new Vector<NodeSefFun>();

	/**
	 * vbs脚本的定义Obj
	 */
	private Vector<String> sefDefObj = new Vector<String>();

	/**
	 * vbs脚本的变量名称。用于生成变量声明。包括：数组(arr)、对象(object)、变量(var)
	 */
	private Vector<Token> variants = new Vector<Token>();

	ParserWord(ParserEnv env, Vector<KnowledgeVariable> inknvarList,
			Vector<KnowledgeVariable> outknvarList)
	{
		this.env = env;
		this.inknvarList = inknvarList;
		this.outknvarList = outknvarList;
		load();
	}

	/**
	 * 增加一个vbs数组声明
	 * 
	 * @param token
	 */
	void addArr(Token token)
	{
		token.type = ParserConst.KT_ARR;

		Object obj = null;
		if ((obj = env.getCurSefFunGrammerInfo()) != null)
		{
			NodeSefFun seffun = ((NodeSefFun.GrammerInfo) obj).seffunnode;
			seffun.addArr(token.str);
		}
		else
		{
			if (!sefDefArr.contains(token.str))
			{
				sefDefArr.add(token.str);
			}
		}
	}

	/**
	 * 增加一个vbs对象声明
	 * 
	 * @param token
	 */
	void addObj(Token token)
	{
		token.type = ParserConst.KT_OBJ;

		Object obj = null;
		if ((obj = env.getCurSefFunGrammerInfo()) != null)
		{
			NodeSefFun seffun = ((NodeSefFun.GrammerInfo) obj).seffunnode;
			seffun.addObj(token.str);
		}
		else
		{
			if (!sefDefObj.contains(token.str))
			{
				sefDefObj.add(token.str);
			}
		}
	}

	/**
	 * 增加一个vbs自定义函数声明。 注：对重定义(名字相同)的vbs自定义方法，取最新的
	 * 
	 * @param nf
	 */
	void addSefDefFun(NodeSefFun nf)
	{
		sefDefFuns.remove(nf);
		sefDefFuns.add(nf);
		variants.remove(nf.funname);
	}

	/**
	 * 增加一个vbs变量声明
	 * 
	 * @param token
	 */
	void addVar(Token token)
	{
		if ((token.type == ParserConst.KT_VAR)
				|| (token.type == ParserConst.KT_IN_KNOWLEDGEVAR)
				|| (token.type == ParserConst.KT_OUT_KNOWLEDGEVAR)
				|| (token.type == ParserConst.KT_OBJ)
				|| (token.type == ParserConst.KT_ARR))
		{
			Object obj = null;
			if ((obj = env.getCurSefFunGrammerInfo()) != null)
			{// 在function/sub 定义内部, 变量为Function/sub的局部变量
				NodeSefFun seffun = ((NodeSefFun.GrammerInfo) obj).seffunnode;
				seffun.addVar(token.str);
			}
			else if (token.type == ParserConst.KT_VAR)
			{
				if (variants.contains(token))
				{
					return;
				}
				variants.add(token);
			}
		}
	}

	/**
	 * 变量名称封装：将funname改为function_funname;将subname改为sub_funname
	 * 
	 * @param var
	 * @return
	 */
	String encapsulateSefDefFun(String funname)
	{
		return env.globalId() + "_" + funname;
	}

	NodeSefFun getSefDefFun(String funname) throws Exception
	{
		NodeSefFun sf = sefDefFun(funname);
		if (sf == null)
		{
			throw new ParserException(env, ExceptionDesc.DS003_UNDEFFUN + "'"
					+ funname + "'");
		}
		return sf;
	}

	private boolean isInKnowledgeVar(String word)
	{
		if (inknvarList != null)
		{
			Iterator<KnowledgeVariable> itor = inknvarList.iterator();
			while (itor.hasNext())
			{
				KnowledgeVariable kn = itor.next();
				if (kn.varName.equalsIgnoreCase(word))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * objname对象是否包含membername成员。对于非内部obj，均返回true。
	 * 
	 * @param objname
	 *            obj对象
	 * @param membername
	 *            成员名称
	 * @return
	 */
	boolean isObjMember(String objname, String membername)
	{
		InternalObj obj = javaName2Obj.get(objname);
		if (obj != null)
		{
			return obj.funcs.contains(membername);
		}

		return true;
	}

	private boolean isOutKnowledgeVar(String word)
	{
		if (outknvarList != null)
		{
			Iterator<KnowledgeVariable> itor = outknvarList.iterator();
			while (itor.hasNext())
			{
				KnowledgeVariable kn = itor.next();
				if (kn.varName.equalsIgnoreCase(word))
				{
					return true;
				}
			}
		}

		return false;
	}

	boolean isSefDefFun(String funname)
	{
		return (sefDefFun(funname) != null);
	}

	/**
	 * 获取vbs词汇的类型
	 * 
	 * @param word
	 * @return
	 */
	int kt(String word)
	{
		Integer kt = keymap.get(word);
		if (kt != null)
		{
			return kt.intValue();
		}

		String ct = constmap.get(word);
		if (ct != null)
		{
			return ParserConst.KT_VBCONST;
		}

		if (isSefDefFun(word))
		{
			return ParserConst.KT_CALLSEFDEFFUN;
		}

		if (isObjMember(SLEE_INTERNAL_FUN, word))
		{
			return ParserConst.KT_CALLINTERNALFUN;
		}

		if (vbName2Obj.get(word) != null)
		{
			return ParserConst.KT_INTERNALOBJ;
		}

		if (sefDefArr.contains(word))
		{
			return ParserConst.KT_ARR;
		}

		if (sefDefObj.contains(word))
		{
			return ParserConst.KT_OBJ;
		}

		if (isInKnowledgeVar(word))
		{
			return ParserConst.KT_IN_KNOWLEDGEVAR;
		}

		if (isOutKnowledgeVar(word))
		{
			return ParserConst.KT_OUT_KNOWLEDGEVAR;
		}

		return ParserConst.KT_VAR;
	}

	private String kvName(KnowledgeVariable kv) throws Exception
	{
		if ((kv.varName == null) || kv.varName.equals(""))
		{
			throw new ParserException(this.env, ExceptionDesc.DS013);
		}

		return "var_" + kv.varName;
	}

	private String kvVariantName(KnowledgeVariable kv) throws Exception
	{
		return kv.varName.toLowerCase();
	}

	/**
	 * 初始化知识变量在vbs中的变量声明
	 * 
	 * @param sb
	 * @throws Exception
	 */
	void printJavaForInitKV(StringBuilder sb) throws Exception
	{
		if (inknvarList != null)
		{
			if (outknvarList != null)
			{
				inknvarList.addAll(outknvarList);
			}

			Vector<String> tmp = new Vector<String>();
			// 声明
			Iterator<KnowledgeVariable> itor = inknvarList.iterator();
			while (itor.hasNext())
			{
				KnowledgeVariable kv = itor.next();
				if (!tmp.contains(kvVariantName(kv)))
				{
					sb.append(this.env.indent()).append("Variant ")
							.append(kvVariantName(kv))
							.append(" = new Variant(cci, \"")
							.append(kvVariantName(kv)).append("\");\r\n");
					tmp.add(kvVariantName(kv));
				}
			}

			// 初始化
			itor = inknvarList.iterator();
			while (itor.hasNext())
			{
				KnowledgeVariable kv = itor.next();
				sb.append(this.env.indent()).append(kvName(kv))
						.append(".copy2Variant(").append(kvVariantName(kv))
						.append(");\r\n");
			}
		}
	}

	/**
	 * 输出[out]知识变量返回值设置的java代码
	 * 
	 * @param sb
	 * @throws Exception
	 */
	void printJavaForOutKV(StringBuilder sb) throws Exception
	{
		if (outknvarList != null)
		{
			Iterator<KnowledgeVariable> itor = outknvarList.iterator();
			while (itor.hasNext())
			{
				KnowledgeVariable kv = itor.next();
				sb.append(this.env.indent()).append(kvName(kv))
						.append(".setValue(").append(kvVariantName(kv))
						.append(");\r\n");
			}
		}
	}

	/**
	 * 输出脚本自定义方法定义
	 * 
	 * @param sb
	 * @throws Exception
	 */
	void printJavaForSefDefFun(StringBuilder sb) throws Exception
	{
		Iterator<NodeSefFun> itor = sefDefFuns.iterator();
		while (itor.hasNext())
		{
			itor.next().printDefine(sb, this.env);
		}
	}

	/**
	 * 输出vbs脚本的的变量声明
	 * 
	 * @param sb
	 * @throws Exception
	 */
	void printJavaForVar(StringBuilder sb) throws Exception
	{
		Iterator<Token> itor = variants.iterator();
		while (itor.hasNext())
		{
			Token tk = itor.next();
			sb.append(this.env.indent()).append("Variant ").append(tk.e_str())
					.append(" = new Variant(cci, \"").append(tk.e_str())
					.append("\");\r\n");
		}
	}

	/**
	 * 移除最新定义的vbs变量。在解析时会先将fun/property识别为变量；当识别.fun/.property为表达式时，需要将fun/
	 * property从变量列表中移除。
	 * 
	 * @param token
	 */
	void removeLastVar(Token token)
	{
		Object obj = null;
		if ((obj = env.getCurSefFunGrammerInfo()) != null)
		{
			NodeSefFun seffun = ((NodeSefFun.GrammerInfo) obj).seffunnode;
			seffun.removeLastVar(token.str);
		}
		else
		{
			if (variants.size() == 0)
			{
				return;
			}
			if (variants.lastElement() == token)
			{
				variants.remove(variants.lastElement());
			}
		}
	}

	/**
	 * 根据方法名称获取方法定义节点
	 * 
	 * @param funname
	 * @return
	 */
	private NodeSefFun sefDefFun(String funname)
	{
		Iterator<NodeSefFun> itor = sefDefFuns.iterator();
		while (itor.hasNext())
		{
			NodeSefFun nf = itor.next();
			if (nf.funname.str.equals(funname))
			{
				return nf;
			}
		}

		return null;
	}

}
