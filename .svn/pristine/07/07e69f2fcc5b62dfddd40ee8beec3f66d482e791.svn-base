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

public enum IvrEventReasonT
{
    TIvrResNormal(0),
    TIvrResTransfer(1),
    TIvrResError(2),
    TIvrResRemoteRelease(3),
    TIvrResLocateRelease(4);

    private static IvrEventReasonT[] __values = new IvrEventReasonT[5];
    static
    {
        __values[0] = TIvrResNormal;
        __values[1] = TIvrResTransfer;
        __values[2] = TIvrResError;
        __values[3] = TIvrResRemoteRelease;
        __values[4] = TIvrResLocateRelease;
    }
    private int __value;

    public static final int _TIvrResNormal = 0;
    public static final int _TIvrResTransfer = 1;
    public static final int _TIvrResError = 2;
    public static final int _TIvrResRemoteRelease = 3;
    public static final int _TIvrResLocateRelease = 4;

    public static IvrEventReasonT
    convert(int val)
    {
        assert val < 5;
        return __values[val];
    }

    public static IvrEventReasonT
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
    IvrEventReasonT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static IvrEventReasonT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 5)
        {
            throw new Ice.MarshalException();
        }
        return IvrEventReasonT.convert(__v);
    }
}
