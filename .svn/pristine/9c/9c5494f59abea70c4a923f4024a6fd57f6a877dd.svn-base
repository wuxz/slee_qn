package com.channelsoft.slee.usmlreasoning;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import com.channelsoft.slee.log.*;

public class XMLFunction
{
	static boolean convertCharset = (!java.nio.charset.Charset.defaultCharset().name().equals("GBK"));
	static String convertString(String value) throws Exception
	{
		return convertCharset ? new String(value.getBytes("GBK")): value;
	}
	
	static String getNodeText(Node node) throws Exception
	{
		if (node == null)
			return "";
		String strText;
		strText = node.getTextContent();
		if (strText != null)
			return convertString(strText);
		else
			return "";
	}

	public static Node getNextSibling(Node me)
	{
		if (me == null)
		{
			return null;
		}

		Node node = me.getNextSibling();
		while (node != null && node.getNodeType() != 1)
		{
			node = node.getNextSibling();
		}

		return node;
	}

	public static Node getFirstChild(Node me)
	{
		if (me == null)
		{
			return null;
		}

		Node node = me.getFirstChild();
		while (node != null && node.getNodeType() != 1)
		{
			node = node.getNextSibling();
		}

		return node;
	}

	static void verifyNodeTag(Node node, String nodeName) throws Exception
	{
		if (node == null)
			return;
		String strNodeName;
		strNodeName = node.getNodeName();
		if (strNodeName == null || !strNodeName.equals(nodeName))
		{
			throw new MyException(String.format("在脚本中应该出现标签%s的位置出现非法标签%s",
					nodeName, strNodeName));
		}
	}

	public static int getIntFromAttrib(Node node, String strName) throws Exception
	{
		String attribute = returnValueFromAttr(node, strName);
		int result = 0;
		if ("".equals(attribute))
			return 0;
		try
		{
			result = Integer.parseInt(attribute);
		}
		catch (NumberFormatException e)
		{
			result = 0;
		}
		return result;
	}

	static boolean getBoolFromAttrib(Node node, String strName,
			boolean defaultResult) throws Exception
	{
		String attribute = returnValueFromAttr(node, strName);
		if ("true".equals(attribute))
			return true;
		else if ("false".equals(attribute))
			return false;
		else
			return defaultResult;
	}

	static String returnValueFromAttr(Node node, String strName) throws Exception
	{
		if (node == null)
		{
			return "";
		}

		NamedNodeMap map = node.getAttributes();
		if (map == null)
		{
			return "";
		}

		Node node2 = map.getNamedItem(strName);
		if (node2 == null)
		{
			return "";
		}

		String ret = node2.getNodeValue();
		if (ret == null)
		{
			return "";
		}

		return convertString(ret);
	}

	static String getNodeName(Node node) throws Exception
	{
		if (node == null)
			return "";
		
		String nodeName = node.getNodeName();
		if (null != nodeName)
			return convertString(nodeName);
		else
			return "";
	}

}
