// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.DDSSpace;

public final class DDSInterfacePrxHelper extends Ice.ObjectPrxHelperBase
		implements DDSInterfacePrx
{
	public static DDSInterfacePrx __read(IceInternal.BasicStream __is)
	{
		Ice.ObjectPrx proxy = __is.readProxy();
		if (proxy != null)
		{
			DDSInterfacePrxHelper result = new DDSInterfacePrxHelper();
			result.__copyFrom(proxy);
			return result;
		}
		return null;
	}

	public static void __write(IceInternal.BasicStream __os, DDSInterfacePrx v)
	{
		__os.writeProxy(v);
	}

	public static DDSInterfacePrx checkedCast(Ice.ObjectPrx __obj)
	{
		DDSInterfacePrx __d = null;
		if (__obj != null)
		{
			try
			{
				__d = (DDSInterfacePrx) __obj;
			}
			catch (ClassCastException ex)
			{
				if (__obj.ice_isA("::DDSSpace::DDSInterface"))
				{
					DDSInterfacePrxHelper __h = new DDSInterfacePrxHelper();
					__h.__copyFrom(__obj);
					__d = __h;
				}
			}
		}
		return __d;
	}

	public static DDSInterfacePrx checkedCast(Ice.ObjectPrx __obj,
			java.util.Map<String, String> __ctx)
	{
		DDSInterfacePrx __d = null;
		if (__obj != null)
		{
			try
			{
				__d = (DDSInterfacePrx) __obj;
			}
			catch (ClassCastException ex)
			{
				if (__obj.ice_isA("::DDSSpace::DDSInterface", __ctx))
				{
					DDSInterfacePrxHelper __h = new DDSInterfacePrxHelper();
					__h.__copyFrom(__obj);
					__d = __h;
				}
			}
		}
		return __d;
	}

	public static DDSInterfacePrx checkedCast(Ice.ObjectPrx __obj,
			String __facet)
	{
		DDSInterfacePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			try
			{
				if (__bb.ice_isA("::DDSSpace::DDSInterface"))
				{
					DDSInterfacePrxHelper __h = new DDSInterfacePrxHelper();
					__h.__copyFrom(__bb);
					__d = __h;
				}
			}
			catch (Ice.FacetNotExistException ex)
			{
			}
		}
		return __d;
	}

	public static DDSInterfacePrx checkedCast(Ice.ObjectPrx __obj,
			String __facet, java.util.Map<String, String> __ctx)
	{
		DDSInterfacePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			try
			{
				if (__bb.ice_isA("::DDSSpace::DDSInterface", __ctx))
				{
					DDSInterfacePrxHelper __h = new DDSInterfacePrxHelper();
					__h.__copyFrom(__bb);
					__d = __h;
				}
			}
			catch (Ice.FacetNotExistException ex)
			{
			}
		}
		return __d;
	}

	public static DDSInterfacePrx uncheckedCast(Ice.ObjectPrx __obj)
	{
		DDSInterfacePrx __d = null;
		if (__obj != null)
		{
			DDSInterfacePrxHelper __h = new DDSInterfacePrxHelper();
			__h.__copyFrom(__obj);
			__d = __h;
		}
		return __d;
	}

	public static DDSInterfacePrx uncheckedCast(Ice.ObjectPrx __obj,
			String __facet)
	{
		DDSInterfacePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			DDSInterfacePrxHelper __h = new DDSInterfacePrxHelper();
			__h.__copyFrom(__bb);
			__d = __h;
		}
		return __d;
	}

	@Override
	protected Ice._ObjectDelD __createDelegateD()
	{
		return new _DDSInterfaceDelD();
	}

	@Override
	protected Ice._ObjectDelM __createDelegateM()
	{
		return new _DDSInterfaceDelM();
	}

	public void AddEnterprise(String ServiceName, String EntpID)
	{
		AddEnterprise(ServiceName, EntpID, null, false);
	}

	public void AddEnterprise(String ServiceName, String EntpID,
			java.util.Map<String, String> __ctx)
	{
		AddEnterprise(ServiceName, EntpID, __ctx, true);
	}

	private void AddEnterprise(String ServiceName, String EntpID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				__del.AddEnterprise(ServiceName, EntpID, __ctx);
				return;
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int regService(String ServiceName, String strProxy, String[] lstParam)
	{
		return regService(ServiceName, strProxy, lstParam, null, false);
	}

	public int regService(String ServiceName, String strProxy,
			String[] lstParam, java.util.Map<String, String> __ctx)
	{
		return regService(ServiceName, strProxy, lstParam, __ctx, true);
	}

	private int regService(String ServiceName, String strProxy,
			String[] lstParam, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("regService");
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				return __del.regService(ServiceName, strProxy, lstParam, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public void ResetIvrEvent(String veName)
	{
		ResetIvrEvent(veName, null, false);
	}

	public void ResetIvrEvent(String veName, java.util.Map<String, String> __ctx)
	{
		ResetIvrEvent(veName, __ctx, true);
	}

	private void ResetIvrEvent(String veName,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				__del.ResetIvrEvent(veName, __ctx);
				return;
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public void RmEnterprise(String ServiceName, String EntpID)
	{
		RmEnterprise(ServiceName, EntpID, null, false);
	}

	public void RmEnterprise(String ServiceName, String EntpID,
			java.util.Map<String, String> __ctx)
	{
		RmEnterprise(ServiceName, EntpID, __ctx, true);
	}

	private void RmEnterprise(String ServiceName, String EntpID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				__del.RmEnterprise(ServiceName, EntpID, __ctx);
				return;
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public void setCmsEvent(CMSEventInfoT event)
	{
		setCmsEvent(event, null, false);
	}

	public void setCmsEvent(CMSEventInfoT event,
			java.util.Map<String, String> __ctx)
	{
		setCmsEvent(event, __ctx, true);
	}

	private void setCmsEvent(CMSEventInfoT event,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				__del.setCmsEvent(event, __ctx);
				return;
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public void setIvrEvent(IvrEventInfoT infoCallEvent)
	{
		setIvrEvent(infoCallEvent, null, false);
	}

	public void setIvrEvent(IvrEventInfoT infoCallEvent,
			java.util.Map<String, String> __ctx)
	{
		setIvrEvent(infoCallEvent, __ctx, true);
	}

	private void setIvrEvent(IvrEventInfoT infoCallEvent,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				__del.setIvrEvent(infoCallEvent, __ctx);
				return;
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public void setIvrLicense(IvrLicenseInfoT[] lstLicense)
	{
		setIvrLicense(lstLicense, null, false);
	}

	public void setIvrLicense(IvrLicenseInfoT[] lstLicense,
			java.util.Map<String, String> __ctx)
	{
		setIvrLicense(lstLicense, __ctx, true);
	}

	private void setIvrLicense(IvrLicenseInfoT[] lstLicense,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__delBase = __getDelegate();
				_DDSInterfaceDel __del = (_DDSInterfaceDel) __delBase;
				__del.setIvrLicense(lstLicense, __ctx);
				return;
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}
}
