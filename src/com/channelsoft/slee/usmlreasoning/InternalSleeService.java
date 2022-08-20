/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;

/**
 * 封装内置的SleeService对象的实现。
 * 
 * @author wuxz
 */
public class InternalSleeService extends InternalObject
{
	public Variant accountid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getAccountID(), computingContext);
	}

	public Variant accounttype(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getAccountType(), computingContext);
	}

	public Variant agentdn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getAgentDn(), computingContext);
	}

	public Variant agentid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getAgentId(), computingContext);
	}

	public Variant contentid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getContentId(), computingContext);
	}

	public Variant fireuserevent(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 3))
		{
			throw new InvalidParameterCountException();
		}

		service.fireUserEvent(args[0].toString(), (int) args[1].getDouble(),
				(int) args[2].getDouble());

		return new Variant(computingContext);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.InternalObject#getDefaultPropertyName()
	 */
	@Override
	public String getDefaultPropertyName()
	{
		return null;
	}

	public Variant mappath(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getMapPath(), computingContext);
	}

	public Variant networkaccount(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getNetworkAccount(), computingContext);
	}

	public Variant networkid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getNetworkId(), computingContext);
	}

	public Variant networkportalid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getNetworkPortalId(), computingContext);
	}

	public Variant reserve1(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getReserve1(), computingContext);
	}

	public Variant reserve2(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getReserve2(), computingContext);
	}

	public Variant reserve3(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getReserve3(), computingContext);
	}

	public Variant reserve4(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getReserve4(), computingContext);
	}

	public Variant reserve5(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getReserve5(), computingContext);
	}

	public Variant serviceid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getServiceID(), computingContext);
	}

	public void setaccountid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setAccountID(args[0].toString());
	}

	public void setaccounttype(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setAccountType((int) args[0].getDouble());
	}

	public void setagentdn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setAgentDn(args[0].toString());
	}

	public void setagentid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setAgentId(args[0].toString());
	}

	public void setcontentid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setContentId(args[0].toString());
	}

	public void setnetworkaccount(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setNetworkAccount(args[0].toString());
	}

	public void setnetworkid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setNetworkId(args[0].toString());
	}

	public void setnetworkportalid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setNetworkPortalId(args[0].toString());
	}

	public void setreserve1(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setReserve1(args[0].toString());
	}

	public void setreserve2(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setReserve2(args[0].toString());
	}

	public void setreserve3(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setReserve3(args[0].toString());
	}

	public void setreserve4(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setReserve4(args[0].toString());
	}

	public void setreserve5(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setReserve5(args[0].toString());
	}

	public void setserviceid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setServiceID(args[0].toString());
	}

	public void setskillid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setSkillId(args[0].toString());
	}

	public void setsubscriberid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setSubscriberId(args[0].toString());
	}

	public void setuseripaddr(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setUserIpAddr(args[0].toString());
	}

	public void setusernumber(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		service.setUserNumber(args[0].toString());
	}

	public Variant skillid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getSkillId(), computingContext);
	}

	public Variant subscriberid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getSubscriberId(), computingContext);
	}

	public Variant useripaddr(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getUserIpAddr(), computingContext);
	}

	public Variant usernumber(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(service.getUserNumber(), computingContext);
	}
}
