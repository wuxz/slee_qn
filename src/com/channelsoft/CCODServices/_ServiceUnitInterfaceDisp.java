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

public abstract class _ServiceUnitInterfaceDisp extends Ice.ObjectImpl implements ServiceUnitInterface
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::CCODServices::ServiceUnitInterface",
        "::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[0];
    }

    public static String
    ice_staticId()
    {
        return __ids[0];
    }

    public final void
    SendMediaEvent(MediaEventT event)
    {
        SendMediaEvent(event, null);
    }

    public final void
    SendSignalEvent(SignalEventT event)
    {
        SendSignalEvent(event, null);
    }

    public final void
    SetCallback(Ice.Identity ident, String serviceName)
    {
        SetCallback(ident, serviceName, null);
    }

    public final CCODResultT
    StartService(ServiceInfoT serviceInfo)
    {
        return StartService(serviceInfo, null);
    }

    public static IceInternal.DispatchStatus
    ___StartService(ServiceUnitInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        ServiceInfoT serviceInfo;
        serviceInfo = new ServiceInfoT();
        serviceInfo.__read(__is);
        CCODResultT __ret = __obj.StartService(serviceInfo, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SendSignalEvent(ServiceUnitInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        SignalEventT event;
        event = new SignalEventT();
        event.__read(__is);
        __obj.SendSignalEvent(event, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SendMediaEvent(ServiceUnitInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        MediaEventT event;
        event = new MediaEventT();
        event.__read(__is);
        __obj.SendMediaEvent(event, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SetCallback(ServiceUnitInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        Ice.Identity ident;
        ident = new Ice.Identity();
        ident.__read(__is);
        String serviceName;
        serviceName = __is.readString();
        __obj.SetCallback(ident, serviceName, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "SendMediaEvent",
        "SendSignalEvent",
        "SetCallback",
        "StartService",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public IceInternal.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            return IceInternal.DispatchStatus.DispatchOperationNotExist;
        }

        switch(pos)
        {
            case 0:
            {
                return ___SendMediaEvent(this, in, __current);
            }
            case 1:
            {
                return ___SendSignalEvent(this, in, __current);
            }
            case 2:
            {
                return ___SetCallback(this, in, __current);
            }
            case 3:
            {
                return ___StartService(this, in, __current);
            }
            case 4:
            {
                return ___ice_id(this, in, __current);
            }
            case 5:
            {
                return ___ice_ids(this, in, __current);
            }
            case 6:
            {
                return ___ice_isA(this, in, __current);
            }
            case 7:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
