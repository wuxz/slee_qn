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

public final class ServiceUnitInterfacePrxHelper extends Ice.ObjectPrxHelperBase implements ServiceUnitInterfacePrx
{
    public void
    SendMediaEvent(MediaEventT event)
    {
        SendMediaEvent(event, null, false);
    }

    public void
    SendMediaEvent(MediaEventT event, java.util.Map<String, String> __ctx)
    {
        SendMediaEvent(event, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    SendMediaEvent(MediaEventT event, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate();
                _ServiceUnitInterfaceDel __del = (_ServiceUnitInterfaceDel)__delBase;
                __del.SendMediaEvent(event, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public void
    SendSignalEvent(SignalEventT event)
    {
        SendSignalEvent(event, null, false);
    }

    public void
    SendSignalEvent(SignalEventT event, java.util.Map<String, String> __ctx)
    {
        SendSignalEvent(event, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    SendSignalEvent(SignalEventT event, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate();
                _ServiceUnitInterfaceDel __del = (_ServiceUnitInterfaceDel)__delBase;
                __del.SendSignalEvent(event, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public void
    SetCallback(Ice.Identity ident, String serviceName)
    {
        SetCallback(ident, serviceName, null, false);
    }

    public void
    SetCallback(Ice.Identity ident, String serviceName, java.util.Map<String, String> __ctx)
    {
        SetCallback(ident, serviceName, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    SetCallback(Ice.Identity ident, String serviceName, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate();
                _ServiceUnitInterfaceDel __del = (_ServiceUnitInterfaceDel)__delBase;
                __del.SetCallback(ident, serviceName, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    StartService(ServiceInfoT serviceInfo)
    {
        return StartService(serviceInfo, null, false);
    }

    public CCODResultT
    StartService(ServiceInfoT serviceInfo, java.util.Map<String, String> __ctx)
    {
        return StartService(serviceInfo, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    StartService(ServiceInfoT serviceInfo, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("StartService");
                __delBase = __getDelegate();
                _ServiceUnitInterfaceDel __del = (_ServiceUnitInterfaceDel)__delBase;
                return __del.StartService(serviceInfo, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public static ServiceUnitInterfacePrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        ServiceUnitInterfacePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ServiceUnitInterfacePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::CCODServices::ServiceUnitInterface"))
                {
                    ServiceUnitInterfacePrxHelper __h = new ServiceUnitInterfacePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ServiceUnitInterfacePrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        ServiceUnitInterfacePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ServiceUnitInterfacePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::CCODServices::ServiceUnitInterface", __ctx))
                {
                    ServiceUnitInterfacePrxHelper __h = new ServiceUnitInterfacePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ServiceUnitInterfacePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ServiceUnitInterfacePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::CCODServices::ServiceUnitInterface"))
                {
                    ServiceUnitInterfacePrxHelper __h = new ServiceUnitInterfacePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ServiceUnitInterfacePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        ServiceUnitInterfacePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::CCODServices::ServiceUnitInterface", __ctx))
                {
                    ServiceUnitInterfacePrxHelper __h = new ServiceUnitInterfacePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ServiceUnitInterfacePrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        ServiceUnitInterfacePrx __d = null;
        if(__obj != null)
        {
            ServiceUnitInterfacePrxHelper __h = new ServiceUnitInterfacePrxHelper();
            __h.__copyFrom(__obj);
            __d = __h;
        }
        return __d;
    }

    public static ServiceUnitInterfacePrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ServiceUnitInterfacePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            ServiceUnitInterfacePrxHelper __h = new ServiceUnitInterfacePrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _ServiceUnitInterfaceDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _ServiceUnitInterfaceDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, ServiceUnitInterfacePrx v)
    {
        __os.writeProxy(v);
    }

    public static ServiceUnitInterfacePrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ServiceUnitInterfacePrxHelper result = new ServiceUnitInterfacePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
