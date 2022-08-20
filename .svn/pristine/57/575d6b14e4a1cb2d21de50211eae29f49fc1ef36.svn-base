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

public enum ServiceType
{
    ServiceUnknown(0),
    ServiceVE(1),
    ServiceUCDS(2),
    ServiceDialer(3),
    ServiceDDS(4),
    ServiceEMS(5),
    ServiceCMS(6),
    ServiceGLS(7);

    private static ServiceType[] __values = new ServiceType[8];
    static
    {
        __values[0] = ServiceUnknown;
        __values[1] = ServiceVE;
        __values[2] = ServiceUCDS;
        __values[3] = ServiceDialer;
        __values[4] = ServiceDDS;
        __values[5] = ServiceEMS;
        __values[6] = ServiceCMS;
        __values[7] = ServiceGLS;
    }
    private int __value;

    public static final int _ServiceUnknown = 0;
    public static final int _ServiceVE = 1;
    public static final int _ServiceUCDS = 2;
    public static final int _ServiceDialer = 3;
    public static final int _ServiceDDS = 4;
    public static final int _ServiceEMS = 5;
    public static final int _ServiceCMS = 6;
    public static final int _ServiceGLS = 7;

    public static ServiceType
    convert(int val)
    {
        assert val < 8;
        return __values[val];
    }

    public static ServiceType
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
    ServiceType(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static ServiceType
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 8)
        {
            throw new Ice.MarshalException();
        }
        return ServiceType.convert(__v);
    }
}
