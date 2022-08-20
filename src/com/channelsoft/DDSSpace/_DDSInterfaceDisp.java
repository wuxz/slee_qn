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

public abstract class _DDSInterfaceDisp extends Ice.ObjectImpl implements DDSInterface
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::DDSSpace::DDSInterface",
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
    AddEnterprise(String ServiceName, String EntpID)
    {
        AddEnterprise(ServiceName, EntpID, null);
    }

    public final void
    ResetIvrEvent(String veName)
    {
        ResetIvrEvent(veName, null);
    }

    public final void
    RmEnterprise(String ServiceName, String EntpID)
    {
        RmEnterprise(ServiceName, EntpID, null);
    }

    public final int
    regService(String ServiceName, String strProxy, String[] lstParam)
    {
        return regService(ServiceName, strProxy, lstParam, null);
    }

    public final void
    setCmsEvent(CMSEventInfoT event)
    {
        setCmsEvent(event, null);
    }

    public final void
    setIvrEvent(IvrEventInfoT infoCallEvent)
    {
        setIvrEvent(infoCallEvent, null);
    }

    public final void
    setIvrLicense(IvrLicenseInfoT[] lstLicense)
    {
        setIvrLicense(lstLicense, null);
    }

    public static IceInternal.DispatchStatus
    ___setCmsEvent(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        CMSEventInfoT event;
        event = new CMSEventInfoT();
        event.__read(__is);
        __obj.setCmsEvent(event, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___ResetIvrEvent(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        String veName;
        veName = __is.readString();
        __obj.ResetIvrEvent(veName, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___setIvrLicense(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IvrLicenseInfoT[] lstLicense;
        lstLicense = LicenseListHelper.read(__is);
        __obj.setIvrLicense(lstLicense, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___setIvrEvent(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IvrEventInfoT infoCallEvent;
        infoCallEvent = new IvrEventInfoT();
        infoCallEvent.__read(__is);
        __obj.setIvrEvent(infoCallEvent, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___regService(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String ServiceName;
        ServiceName = __is.readString();
        String strProxy;
        strProxy = __is.readString();
        String[] lstParam;
        lstParam = ParamListHelper.read(__is);
        int __ret = __obj.regService(ServiceName, strProxy, lstParam, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___AddEnterprise(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        String ServiceName;
        ServiceName = __is.readString();
        String EntpID;
        EntpID = __is.readString();
        __obj.AddEnterprise(ServiceName, EntpID, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___RmEnterprise(DDSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        String ServiceName;
        ServiceName = __is.readString();
        String EntpID;
        EntpID = __is.readString();
        __obj.RmEnterprise(ServiceName, EntpID, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "AddEnterprise",
        "ResetIvrEvent",
        "RmEnterprise",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "regService",
        "setCmsEvent",
        "setIvrEvent",
        "setIvrLicense"
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
                return ___AddEnterprise(this, in, __current);
            }
            case 1:
            {
                return ___ResetIvrEvent(this, in, __current);
            }
            case 2:
            {
                return ___RmEnterprise(this, in, __current);
            }
            case 3:
            {
                return ___ice_id(this, in, __current);
            }
            case 4:
            {
                return ___ice_ids(this, in, __current);
            }
            case 5:
            {
                return ___ice_isA(this, in, __current);
            }
            case 6:
            {
                return ___ice_ping(this, in, __current);
            }
            case 7:
            {
                return ___regService(this, in, __current);
            }
            case 8:
            {
                return ___setCmsEvent(this, in, __current);
            }
            case 9:
            {
                return ___setIvrEvent(this, in, __current);
            }
            case 10:
            {
                return ___setIvrLicense(this, in, __current);
            }
        }

        assert(false);
        return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
