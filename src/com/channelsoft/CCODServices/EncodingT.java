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

public enum EncodingT
{
    UTF8(0),
    GB18030(1);

    private static EncodingT[] __values = new EncodingT[2];
    static
    {
        __values[0] = UTF8;
        __values[1] = GB18030;
    }
    private int __value;

    public static final int _UTF8 = 0;
    public static final int _GB18030 = 1;

    public static EncodingT
    convert(int val)
    {
        assert val < 2;
        return __values[val];
    }

    public static EncodingT
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
    EncodingT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static EncodingT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 2)
        {
            throw new Ice.MarshalException();
        }
        return EncodingT.convert(__v);
    }
}
