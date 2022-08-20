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

public enum IvrCallEventT
{
    TIvrAlerting(0),
    TIvrConnected(1),
    TIvrReleased(2);

    private static IvrCallEventT[] __values = new IvrCallEventT[3];
    static
    {
        __values[0] = TIvrAlerting;
        __values[1] = TIvrConnected;
        __values[2] = TIvrReleased;
    }
    private int __value;

    public static final int _TIvrAlerting = 0;
    public static final int _TIvrConnected = 1;
    public static final int _TIvrReleased = 2;

    public static IvrCallEventT
    convert(int val)
    {
        assert val < 3;
        return __values[val];
    }

    public static IvrCallEventT
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
    IvrCallEventT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static IvrCallEventT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 3)
        {
            throw new Ice.MarshalException();
        }
        return IvrCallEventT.convert(__v);
    }
}
