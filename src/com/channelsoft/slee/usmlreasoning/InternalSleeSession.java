/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;

/**
 * 封装内置的SleeSession对象的实现。
 * 
 * @author wuxz
 */
public class InternalSleeSession extends InternalObject
{
	public Variant argc(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(session.getArgc(), computingContext);
	}

	public Variant argv(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(session.argv((int) args[0].getDouble()),
				computingContext);
	}

	public Variant content(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(session.getContent(args[0].toString()),
				computingContext);
	}

	public void contentremoveall(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		session.contentRemoveAll();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.InternalObject#getDefaultPropertyName()
	 */
	@Override
	public String getDefaultPropertyName()
	{
		return "content";
	}

	public Variant sessionid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant("" + session.getSessionId(), computingContext);
	}

	public void setcontent(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		session.setContent(args[0].toString(), args[1].toString());
	}

	public void setvarvalue(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		session.setKnowledgeVariableValue(args[0].toString(), args[1]
				.toString());
	}

	public Variant vars(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(session.getKnowledgeVariables(), computingContext);
	}

	public Variant varvalue(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(session
				.getKnowledgeVariableValue(args[0].toString()),
				computingContext);
	}

}
