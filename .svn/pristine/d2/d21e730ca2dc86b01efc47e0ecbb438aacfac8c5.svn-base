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

public enum ConnectionStateT
{
    StateStart(0),
    StateAlerting(1),
    StateProgressing(2),
    StateConnected(3),
    StateFailed(4),
    StateDisconnected(5),
    StateError(6),
    StateEnd(7);

    private static ConnectionStateT[] __values = new ConnectionStateT[8];
    static
    {
        __values[0] = StateStart;
        __values[1] = StateAlerting;
        __values[2] = StateProgressing;
        __values[3] = StateConnected;
        __values[4] = StateFailed;
        __values[5] = StateDisconnected;
        __values[6] = StateError;
        __values[7] = StateEnd;
    }
    private int __value;

    public static final int _StateStart = 0;
    public static final int _StateAlerting = 1;
    public static final int _StateProgressing = 2;
    public static final int _StateConnected = 3;
    public static final int _StateFailed = 4;
    public static final int _StateDisconnected = 5;
    public static final int _StateError = 6;
    public static final int _StateEnd = 7;

    public static ConnectionStateT
    convert(int val)
    {
        assert val < 8;
        return __values[val];
    }

    public static ConnectionStateT
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
    ConnectionStateT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static ConnectionStateT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 8)
        {
            throw new Ice.MarshalException();
        }
        return ConnectionStateT.convert(__v);
    }
}
