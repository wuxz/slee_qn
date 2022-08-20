// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.CCODServices;

public final class GLSInterfacePrxHelper extends Ice.ObjectPrxHelperBase
		implements GLSInterfacePrx
{
	public static GLSInterfacePrx __read(IceInternal.BasicStream __is)
	{
		Ice.ObjectPrx proxy = __is.readProxy();
		if (proxy != null)
		{
			GLSInterfacePrxHelper result = new GLSInterfacePrxHelper();
			result.__copyFrom(proxy);
			return result;
		}
		return null;
	}

	public static void __write(IceInternal.BasicStream __os, GLSInterfacePrx v)
	{
		__os.writeProxy(v);
	}

	public static GLSInterfacePrx checkedCast(Ice.ObjectPrx __obj)
	{
		GLSInterfacePrx __d = null;
		if (__obj != null)
		{
			try
			{
				__d = (GLSInterfacePrx) __obj;
			}
			catch (ClassCastException ex)
			{
				if (__obj.ice_isA("::CCODServices::GLSInterface"))
				{
					GLSInterfacePrxHelper __h = new GLSInterfacePrxHelper();
					__h.__copyFrom(__obj);
					__d = __h;
				}
			}
		}
		return __d;
	}

	public static GLSInterfacePrx checkedCast(Ice.ObjectPrx __obj,
			java.util.Map<String, String> __ctx)
	{
		GLSInterfacePrx __d = null;
		if (__obj != null)
		{
			try
			{
				__d = (GLSInterfacePrx) __obj;
			}
			catch (ClassCastException ex)
			{
				if (__obj.ice_isA("::CCODServices::GLSInterface", __ctx))
				{
					GLSInterfacePrxHelper __h = new GLSInterfacePrxHelper();
					__h.__copyFrom(__obj);
					__d = __h;
				}
			}
		}
		return __d;
	}

	public static GLSInterfacePrx checkedCast(Ice.ObjectPrx __obj,
			String __facet)
	{
		GLSInterfacePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			try
			{
				if (__bb.ice_isA("::CCODServices::GLSInterface"))
				{
					GLSInterfacePrxHelper __h = new GLSInterfacePrxHelper();
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

	public static GLSInterfacePrx checkedCast(Ice.ObjectPrx __obj,
			String __facet, java.util.Map<String, String> __ctx)
	{
		GLSInterfacePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			try
			{
				if (__bb.ice_isA("::CCODServices::GLSInterface", __ctx))
				{
					GLSInterfacePrxHelper __h = new GLSInterfacePrxHelper();
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

	public static GLSInterfacePrx uncheckedCast(Ice.ObjectPrx __obj)
	{
		GLSInterfacePrx __d = null;
		if (__obj != null)
		{
			GLSInterfacePrxHelper __h = new GLSInterfacePrxHelper();
			__h.__copyFrom(__obj);
			__d = __h;
		}
		return __d;
	}

	public static GLSInterfacePrx uncheckedCast(Ice.ObjectPrx __obj,
			String __facet)
	{
		GLSInterfacePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			GLSInterfacePrxHelper __h = new GLSInterfacePrxHelper();
			__h.__copyFrom(__bb);
			__d = __h;
		}
		return __d;
	}

	@Override
	protected Ice._ObjectDelD __createDelegateD()
	{
		return new _GLSInterfaceDelD();
	}

	@Override
	protected Ice._ObjectDelM __createDelegateM()
	{
		return new _GLSInterfaceDelM();
	}

	public GLSResult GetDDSLocationDesc(String strServiceName,
			Ice.StringHolder strLocationDesc)
	{
		return GetDDSLocationDesc(strServiceName, strLocationDesc, null, false);
	}

	public GLSResult GetDDSLocationDesc(String strServiceName,
			Ice.StringHolder strLocationDesc,
			java.util.Map<String, String> __ctx)
	{
		return GetDDSLocationDesc(strServiceName, strLocationDesc, __ctx, true);
	}

	private GLSResult GetDDSLocationDesc(String strServiceName,
			Ice.StringHolder strLocationDesc,
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
				__checkTwowayOnly("GetDDSLocationDesc");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.GetDDSLocationDesc(strServiceName,
						strLocationDesc, __ctx);
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

	public GLSResult GetEnterpriseInfor(String strDialerName,
			EnterpriseTableHolder seqEnterpriseTable)
	{
		return GetEnterpriseInfor(strDialerName, seqEnterpriseTable, null,
				false);
	}

	public GLSResult GetEnterpriseInfor(String strDialerName,
			EnterpriseTableHolder seqEnterpriseTable,
			java.util.Map<String, String> __ctx)
	{
		return GetEnterpriseInfor(strDialerName, seqEnterpriseTable, __ctx,
				true);
	}

	private GLSResult GetEnterpriseInfor(String strDialerName,
			EnterpriseTableHolder seqEnterpriseTable,
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
				__checkTwowayOnly("GetEnterpriseInfor");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.GetEnterpriseInfor(strDialerName,
						seqEnterpriseTable, __ctx);
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

	public GLSResult GetServiceUnitState(String strAreaID,
			ServiceUnitListHolder seqServiceUnitInfo)
	{
		return GetServiceUnitState(strAreaID, seqServiceUnitInfo, null, false);
	}

	public GLSResult GetServiceUnitState(String strAreaID,
			ServiceUnitListHolder seqServiceUnitInfo,
			java.util.Map<String, String> __ctx)
	{
		return GetServiceUnitState(strAreaID, seqServiceUnitInfo, __ctx, true);
	}

	private GLSResult GetServiceUnitState(String strAreaID,
			ServiceUnitListHolder seqServiceUnitInfo,
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
				__checkTwowayOnly("GetServiceUnitState");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.GetServiceUnitState(strAreaID, seqServiceUnitInfo,
						__ctx);
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

	public GLSResult GetUcdsLocationDesc(String strEmsName,
			Ice.StringHolder strUcdsLocationDesc)
	{
		return GetUcdsLocationDesc(strEmsName, strUcdsLocationDesc, null, false);
	}

	public GLSResult GetUcdsLocationDesc(String strEmsName,
			Ice.StringHolder strUcdsLocationDesc,
			java.util.Map<String, String> __ctx)
	{
		return GetUcdsLocationDesc(strEmsName, strUcdsLocationDesc, __ctx, true);
	}

	private GLSResult GetUcdsLocationDesc(String strEmsName,
			Ice.StringHolder strUcdsLocationDesc,
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
				__checkTwowayOnly("GetUcdsLocationDesc");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.GetUcdsLocationDesc(strEmsName,
						strUcdsLocationDesc, __ctx);
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

	public GLSResult HeartBeat(String strServiceName)
	{
		return HeartBeat(strServiceName, null, false);
	}

	public GLSResult HeartBeat(String strServiceName,
			java.util.Map<String, String> __ctx)
	{
		return HeartBeat(strServiceName, __ctx, true);
	}

	private GLSResult HeartBeat(String strServiceName,
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
				__checkTwowayOnly("HeartBeat");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.HeartBeat(strServiceName, __ctx);
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

	public GLSResult QueryServiceUnitParams(String strServiceName,
			ServiceUnitParamMapHolder svrUnitParamMap)
	{
		return QueryServiceUnitParams(strServiceName, svrUnitParamMap, null,
				false);
	}

	public GLSResult QueryServiceUnitParams(String strServiceName,
			ServiceUnitParamMapHolder svrUnitParamMap,
			java.util.Map<String, String> __ctx)
	{
		return QueryServiceUnitParams(strServiceName, svrUnitParamMap, __ctx,
				true);
	}

	private GLSResult QueryServiceUnitParams(String strServiceName,
			ServiceUnitParamMapHolder svrUnitParamMap,
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
				__checkTwowayOnly("QueryServiceUnitParams");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.QueryServiceUnitParams(strServiceName,
						svrUnitParamMap, __ctx);
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

	public GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID,
			ServiceType srcService, ServiceUnitInfoHolder serviceUnit)
	{
		return QuerySvrUnitByEnterpriseID(strEnterpriseID, srcService,
				serviceUnit, null, false);
	}

	public GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID,
			ServiceType srcService, ServiceUnitInfoHolder serviceUnit,
			java.util.Map<String, String> __ctx)
	{
		return QuerySvrUnitByEnterpriseID(strEnterpriseID, srcService,
				serviceUnit, __ctx, true);
	}

	private GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID,
			ServiceType srcService, ServiceUnitInfoHolder serviceUnit,
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
				__checkTwowayOnly("QuerySvrUnitByEnterpriseID");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.QuerySvrUnitByEnterpriseID(strEnterpriseID,
						srcService, serviceUnit, __ctx);
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

	public GLSResult RegisterServiceUnit(String strServiceName,
			ServiceUnitParamMapHolder svrUnitParamMap)
	{
		return RegisterServiceUnit(strServiceName, svrUnitParamMap, null, false);
	}

	public GLSResult RegisterServiceUnit(String strServiceName,
			ServiceUnitParamMapHolder svrUnitParamMap,
			java.util.Map<String, String> __ctx)
	{
		return RegisterServiceUnit(strServiceName, svrUnitParamMap, __ctx, true);
	}

	private GLSResult RegisterServiceUnit(String strServiceName,
			ServiceUnitParamMapHolder svrUnitParamMap,
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
				__checkTwowayOnly("RegisterServiceUnit");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.RegisterServiceUnit(strServiceName,
						svrUnitParamMap, __ctx);
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

	public GLSResult RequestRouteTable(String strServiceName,
			RouteTableHolder seqRouteTable)
	{
		return RequestRouteTable(strServiceName, seqRouteTable, null, false);
	}

	public GLSResult RequestRouteTable(String strServiceName,
			RouteTableHolder seqRouteTable, java.util.Map<String, String> __ctx)
	{
		return RequestRouteTable(strServiceName, seqRouteTable, __ctx, true);
	}

	private GLSResult RequestRouteTable(String strServiceName,
			RouteTableHolder seqRouteTable,
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
				__checkTwowayOnly("RequestRouteTable");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.RequestRouteTable(strServiceName, seqRouteTable,
						__ctx);
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

	public GLSResult SynchronizeData()
	{
		return SynchronizeData(null, false);
	}

	public GLSResult SynchronizeData(java.util.Map<String, String> __ctx)
	{
		return SynchronizeData(__ctx, true);
	}

	private GLSResult SynchronizeData(java.util.Map<String, String> __ctx,
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
				__checkTwowayOnly("SynchronizeData");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.SynchronizeData(__ctx);
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

	public GLSResult UnRegisterServiceUnit(String strServiceName)
	{
		return UnRegisterServiceUnit(strServiceName, null, false);
	}

	public GLSResult UnRegisterServiceUnit(String strServiceName,
			java.util.Map<String, String> __ctx)
	{
		return UnRegisterServiceUnit(strServiceName, __ctx, true);
	}

	private GLSResult UnRegisterServiceUnit(String strServiceName,
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
				__checkTwowayOnly("UnRegisterServiceUnit");
				__delBase = __getDelegate();
				_GLSInterfaceDel __del = (_GLSInterfaceDel) __delBase;
				return __del.UnRegisterServiceUnit(strServiceName, __ctx);
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
