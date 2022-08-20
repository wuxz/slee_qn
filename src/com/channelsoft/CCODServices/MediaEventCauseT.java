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

public enum MediaEventCauseT
{
    MecTmEod(0),
    MecTmDigit(1),
    MecTmMaxDtmf(2),
    MecTmMaxTime(3),
    MecTmDisconnect(4),
    MecTmUserStop(5),
    MecTmInvalidFile(6);

    private static MediaEventCauseT[] __values = new MediaEventCauseT[7];
    static
    {
        __values[0] = MecTmEod;
        __values[1] = MecTmDigit;
        __values[2] = MecTmMaxDtmf;
        __values[3] = MecTmMaxTime;
        __values[4] = MecTmDisconnect;
        __values[5] = MecTmUserStop;
        __values[6] = MecTmInvalidFile;
    }
    private int __value;

    public static final int _MecTmEod = 0;
    public static final int _MecTmDigit = 1;
    public static final int _MecTmMaxDtmf = 2;
    public static final int _MecTmMaxTime = 3;
    public static final int _MecTmDisconnect = 4;
    public static final int _MecTmUserStop = 5;
    public static final int _MecTmInvalidFile = 6;

    public static MediaEventCauseT
    convert(int val)
    {
        assert val < 7;
        return __values[val];
    }

    public static MediaEventCauseT
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
    MediaEventCauseT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static MediaEventCauseT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 7)
        {
            throw new Ice.MarshalException();
        }
        return MediaEventCauseT.convert(__v);
    }
}
