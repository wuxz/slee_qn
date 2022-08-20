/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.comobj.IDispatch;
import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;

/**
 * 封装内置的SleeApp对象的实现。
 * 
 * @author wuxz
 */
public class InternalSleeApp extends InternalObject implements IDispatch
{
	public Variant content(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(app.getContent(args[0].toString()), computingContext);
	}

	public void contentremoveall(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		app.contentRemoveAll();
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.InternalObject#getDefaultPropertyName()
	 */
	@Override
	public String getDefaultPropertyName()
	{
		return "content";
	}

	public Variant internalQueueName(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(app.getInternalQueueName(), computingContext);
	}

	public void setcontent(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		app.setContent(args[0].toString(), args[1].toString());
	}

	public Variant startservice(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(app.startService(args[0].toString(), args[1]
				.toString()), computingContext);
	}

	public Variant startserviceforotherparty(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		String paramLine = "_channelsoft_attachCall_"
				+ callController.getOtherPartyTrunkID() + "_"
				+ callController.getOtherPartyCallSn() + " ";
		paramLine += args[1].toString();

		return new Variant(app.startService(args[0].toString(), paramLine),
				computingContext);
	}

	public Variant userdefined(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(app.getUserDefined(args[0].toString()),
				computingContext);
	}
}
