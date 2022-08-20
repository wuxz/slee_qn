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

public enum ContentTypeT
{
    OTHER(0),
    LOGO(1),
    CAPTION(2);

    private static ContentTypeT[] __values = new ContentTypeT[3];
    static
    {
        __values[0] = OTHER;
        __values[1] = LOGO;
        __values[2] = CAPTION;
    }
    private int __value;

    public static final int _OTHER = 0;
    public static final int _LOGO = 1;
    public static final int _CAPTION = 2;

    public static ContentTypeT
    convert(int val)
    {
        assert val < 3;
        return __values[val];
    }

    public static ContentTypeT
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
    ContentTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static ContentTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 3)
        {
            throw new Ice.MarshalException();
        }
        return ContentTypeT.convert(__v);
    }
}
