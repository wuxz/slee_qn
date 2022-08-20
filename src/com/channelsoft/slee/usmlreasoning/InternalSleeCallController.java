/**
 * 
 */
package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.reusable.util.InvalidParameterCountException;
import com.channelsoft.reusable.util.Variant;
import com.channelsoft.slee.util.Constant;

/**
 * 封装内置的SleeCallController对象的实现。
 * 
 * @author wuxz
 */
public class InternalSleeCallController extends InternalObject
{
	public Variant ani(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getAni(), computingContext);
	}

	public Variant answer(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length > 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.answerCall(args.length == 0 ? false
				: args[0].getBoolean()), computingContext);
	}

	public Variant appendvideo(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}
		if (!args[0].isDimedArray)
		{
			throw new InvalidParameterCountException();
		}
		Variant[] arr = args[0].arrayValue;
		int len = arr.length-1;
		for (int i = 0; i < len; i++)
		{
			Variant elt = arr[i];
			if (elt.arrayValue.length-1 != Constant.VIDEOPARAM_LEN)
			{
				throw new InvalidParameterCountException();
			}
			if(elt.arrayValue[0].getString().equals(""))
			{
				len = i;
				break;
			}
		}
		if(len < 1)
		{
			throw new InvalidParameterCountException();
		}
		String[][] infoOnVideo = new String[len][Constant.VIDEOPARAM_LEN];
		for (int i = 0; i < len; i++)
		{
			Variant elt = arr[i];
			for(int j = 0; j < Constant.VIDEOPARAM_LEN; j++)
			{
				infoOnVideo[i][j] = elt.arrayValue[j].getString();
			}
		}
		return new Variant(callController.appendVideo(infoOnVideo),
				computingContext);
	}

	public Variant attachcall(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.attachCall((int) args[0].getDouble(),
				(int) args[1].getDouble()), computingContext);
	}

	public Variant attachotherparty(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.attachOtherParty((int) args[0]
				.getDouble(), (int) args[1].getDouble()), computingContext);
	}

	public Variant callingnumber(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getCallingNumber(), computingContext);
	}

	public Variant callsn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getCallSn(), computingContext);
	}

	public Variant channeldn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getChannelDn(), computingContext);
	}

	public Variant closeconference(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.closeConference(), computingContext);
	}

	public Variant consult(Variant[] args) throws Exception
	{
		if ((args == null) || ((args.length < 3) || (args.length > 4)))
		{
			throw new InvalidParameterCountException();
		}

		switch (args.length)
		{
		case 3:
		{
			return new Variant(callController.consultCall(args[0].toString(),
					args[1].toString(), (int) args[2].getDouble(), false),
					computingContext);
		}

		case 4:
		{
			return new Variant(callController.consultCall(args[0].toString(),
					args[1].toString(), (int) args[2].getDouble(), args[3]
							.getBoolean()), computingContext);
		}

		default:
			return null;
		}
	}

	public Variant detachcall(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.detachCall(), computingContext);
	}

	public Variant disconnect(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.disconnetCall(), computingContext);
	}

	public Variant disconnet(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.disconnetCall(), computingContext);
	}

	public Variant dnis(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getDnis(), computingContext);
	}

	public Variant errorcodeconvert(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.convertError2String((int) args[0]
				.getDouble()), computingContext);
	}

	/* (non-Javadoc)
	 * @see com.channelsoft.slee.usmlreasoning.InternalObject#getDefaultPropertyName()
	 */
	@Override
	public String getDefaultPropertyName()
	{
		return null;
	}

	public Variant isinconference(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.isInConference(), computingContext);
	}

	public Variant isvideocall(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.isVideoCall(), computingContext);
	}

	public Variant mediadn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getTrunkID(), computingContext);
	}

	public Variant oriani(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getOriAni(), computingContext);
	}

	public Variant oridnis(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getOriDnis(), computingContext);
	}

	public Variant otherpartycallsn(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getOtherPartyCallSn(),
				computingContext);
	}

	public Variant otherpartytrunkid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getOtherPartyTrunkID(),
				computingContext);
	}

	public Variant releaseconnection(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length > 1))
		{
			// 参数没有用
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.releaseConnection(), computingContext);
	}

	public Variant routeconnection(Variant[] args) throws Exception
	{
		return singlestepconnect(args);
	}

	public void setani(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		callController.setAni(args[0].toString());
	}

	public void setcallingnumber(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		callController.setCallingNumber(args[0].toString());
	}

	public void setdnis(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		callController.setDnis(args[0].toString());
	}

	public Variant setmaxcalltime(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.setMaxCallTime((int) args[0]
				.getDouble()), computingContext);
	}

	public void setoriani(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		callController.setOriAni(args[0].toString());
	}

	public void setoridnis(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		callController.setOriDnis(args[0].toString());
	}

	public Variant singlestepconference(Variant[] args) throws Exception
	{
		if ((args == null) || ((args.length < 2) || (args.length > 5)))
		{
			throw new InvalidParameterCountException();
		}

		switch (args.length)
		{
		case 2:
		{
			return new Variant(callController.singleStepConference(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(), 120,
					false, false), computingContext);
		}
		case 3:
		{
			return new Variant(callController.singleStepConference(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(), 120,
					args[2].getBoolean(), false), computingContext);
		}
		case 4:
		{
			return new Variant(callController.singleStepConference(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), false),
					computingContext);
		}
		case 5:
		{
			return new Variant(callController.singleStepConference(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), args[4]
							.getBoolean()), computingContext);
		}
		default:
			return null;

		}
	}

	public Variant singlestepconference2(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 2))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.singleStepConference(args[0]
				.toString(), callController.getOriAni(), args[1].toString(),
				callController.getOriDnis(), 120, true, false),
				computingContext);
	}

	public Variant singlestepconnect(Variant[] args) throws Exception
	{
		if ((args == null) || ((args.length != 1) && (args.length != 3)))
		{
			throw new InvalidParameterCountException();
		}

		switch (args.length)
		{
		case 1:
		{
			return new Variant(callController.routeConnection((int) args[0]
					.getDouble()), computingContext);
		}
		case 3:
		{
			return new Variant(callController.routeConnection((int) args[0]
					.getDouble(), (int) args[1].getDouble(), (int) args[2]
					.getDouble()), computingContext);
		}

		default:
			return null;
		}
	}

	public Variant singlestephalfconnect(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 1))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.routeConnection(Integer
				.parseInt(callController.getTrunkID()), (int) args[0]
				.getDouble(), 1), computingContext);
	}

	public Variant singlesteptransfer(Variant[] args) throws Exception
	{
		if ((args == null) || ((args.length < 2) || (args.length == 8 || args.length > 9)))
		{
			throw new InvalidParameterCountException();
		}

		switch (args.length)
		{
		case 2:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(), 120,
					false, false), computingContext);
		}
		case 3:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(), 120,
					args[2].getBoolean(), false), computingContext);
		}
		case 4:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), false),
					computingContext);
		}
		case 5:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), args[4]
							.getBoolean(), 0, false), computingContext);
		}
		case 6:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), args[4]
							.getBoolean(), (int) args[5].getDouble(), false),
					computingContext);
		}
		case 7:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), args[4]
							.getBoolean(), (int) args[5].getDouble(), args[6]
							.getBoolean()), computingContext);
		}
		case 9:
		{
			return new Variant(callController.singleStepTransfer(args[0]
					.toString(), callController.getOriAni(),
					args[1].toString(), callController.getOriDnis(),
					(int) args[3].getDouble(), args[2].getBoolean(), args[4]
							.getBoolean(), (int) args[5].getDouble(), args[6]
							.getBoolean(), args[7].toString(), args[8]
							.toString()), computingContext);
		}
		default:
			return null;

		}
	}

	public Variant transfer(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.transferCall(), computingContext);
	}

	public Variant trunkid(Variant[] args) throws Exception
	{
		if ((args == null) || (args.length != 0))
		{
			throw new InvalidParameterCountException();
		}

		return new Variant(callController.getTrunkID(), computingContext);
	}

	public Variant unrouteconnection(Variant[] args) throws Exception
	{
		if ((args == null) || ((args.length != 1) && (args.length != 3)))
		{
			throw new InvalidParameterCountException();
		}

		switch (args.length)
		{
		case 1:
		{
			return new Variant(callController.unrouteConnection((int) args[0]
					.getDouble()), computingContext);
		}
		case 3:
		{
			return new Variant(callController.unrouteConnection((int) args[0]
					.getDouble(), (int) args[1].getDouble(), (int) args[2]
					.getDouble()), computingContext);
		}

		default:
			return null;
		}
	}
}
