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

public enum ServiceTypeT
{
    NoService(0),
    IVRService(1),
    ACDService(2),
    DialerService(3),
    ConferenceService(4),
    EMService(5),
    DDService(6);

    private static ServiceTypeT[] __values = new ServiceTypeT[7];
    static
    {
        __values[0] = NoService;
        __values[1] = IVRService;
        __values[2] = ACDService;
        __values[3] = DialerService;
        __values[4] = ConferenceService;
        __values[5] = EMService;
        __values[6] = DDService;
    }
    private int __value;

    public static final int _NoService = 0;
    public static final int _IVRService = 1;
    public static final int _ACDService = 2;
    public static final int _DialerService = 3;
    public static final int _ConferenceService = 4;
    public static final int _EMService = 5;
    public static final int _DDService = 6;

    public static ServiceTypeT
    convert(int val)
    {
        assert val < 7;
        return __values[val];
    }

    public static ServiceTypeT
    convert(String val)
    {
        for(int __i = 0; __i < __values.length; ++__i)
        {
            if(__values[__i].toString().equals(val))
            {
                return __values[__i];
            }
        }
        assert false;
        return null;
    }

    public int
    value()
    {
        return __value;
    }

    private
    ServiceTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static ServiceTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 7)
        {
            throw new Ice.MarshalException();
        }
        return ServiceTypeT.convert(__v);
    }
}
