package com.channelsoft.slee.usmlreasoning.precompile;

import java.io.Serializable;
import java.nio.charset.Charset;

public class GetCompileInfoCmd implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8338388657051987493L;
	
	public String fileName;
	
	public String className;
	
	public long millis = 0;
	
	public int ordinal = -1;
	
	public boolean succeeded;
	
	public String encoding;
	
	public String usmlPath;
}
