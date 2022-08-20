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

public enum OperateTypeT
{
    SHOW(0),
    MODIFY(1),
    HIDE(2);

    private static OperateTypeT[] __values = new OperateTypeT[3];
    static
    {
        __values[0] = SHOW;
        __values[1] = MODIFY;
        __values[2] = HIDE;
    }
    private int __value;

    public static final int _SHOW = 0;
    public static final int _MODIFY = 1;
    public static final int _HIDE = 2;

    public static OperateTypeT
    convert(int val)
    {
        assert val < 3;
        return __values[val];
    }

    public static OperateTypeT
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
    OperateTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static OperateTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 3)
        {
            throw new Ice.MarshalException();
        }
        return OperateTypeT.convert(__v);
    }
}
