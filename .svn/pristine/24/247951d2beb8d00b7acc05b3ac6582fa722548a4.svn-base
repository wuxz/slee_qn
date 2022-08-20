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

public enum SignalEventTypeT
{
    SgEvtLocalAlerting(0),
    SgEvtRemoteAlerting(1),
    SgEvtConnected(2),
    SgEvtDisconnected(3),
    SgEvtFailed(4);

    private static SignalEventTypeT[] __values = new SignalEventTypeT[5];
    static
    {
        __values[0] = SgEvtLocalAlerting;
        __values[1] = SgEvtRemoteAlerting;
        __values[2] = SgEvtConnected;
        __values[3] = SgEvtDisconnected;
        __values[4] = SgEvtFailed;
    }
    private int __value;

    public static final int _SgEvtLocalAlerting = 0;
    public static final int _SgEvtRemoteAlerting = 1;
    public static final int _SgEvtConnected = 2;
    public static final int _SgEvtDisconnected = 3;
    public static final int _SgEvtFailed = 4;

    public static SignalEventTypeT
    convert(int val)
    {
        assert val < 5;
        return __values[val];
    }

    public static SignalEventTypeT
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
    SignalEventTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static SignalEventTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 5)
        {
            throw new Ice.MarshalException();
        }
        return SignalEventTypeT.convert(__v);
    }
}
