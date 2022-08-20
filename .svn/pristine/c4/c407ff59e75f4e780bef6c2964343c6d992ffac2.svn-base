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

public enum IvrCallTypeT
{
    TIvrIn(0),
    TIvrOut(1);

    private static IvrCallTypeT[] __values = new IvrCallTypeT[2];
    static
    {
        __values[0] = TIvrIn;
        __values[1] = TIvrOut;
    }
    private int __value;

    public static final int _TIvrIn = 0;
    public static final int _TIvrOut = 1;

    public static IvrCallTypeT
    convert(int val)
    {
        assert val < 2;
        return __values[val];
    }

    public static IvrCallTypeT
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
    IvrCallTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static IvrCallTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 2)
        {
            throw new Ice.MarshalException();
        }
        return IvrCallTypeT.convert(__v);
    }
}
