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

public enum SignalFailedReasonT
{
    ReasonSuccess(0),
    ReasonCallDestBusy(1),
    ReasonCallDestNotAnswer(2),
    ReasonCallDestUnknown(3),
    ReasonCallDestFaxtone(4),
    ReasonCallDestNotFax(5),
    ReasonCallDestInvalid(6),
    ReasonCallVoiceDetected(7),
    ReasonMobilePowerOff(8),
    ReasonMobileOutOfArea(9),
    ReasonMobileNullNumber(10),
    ReasonMobilePeerRefused(11),
    ReasonInvalidMedia(12);

    private static SignalFailedReasonT[] __values = new SignalFailedReasonT[13];
    static
    {
        __values[0] = ReasonSuccess;
        __values[1] = ReasonCallDestBusy;
        __values[2] = ReasonCallDestNotAnswer;
        __values[3] = ReasonCallDestUnknown;
        __values[4] = ReasonCallDestFaxtone;
        __values[5] = ReasonCallDestNotFax;
        __values[6] = ReasonCallDestInvalid;
        __values[7] = ReasonCallVoiceDetected;
        __values[8] = ReasonMobilePowerOff;
        __values[9] = ReasonMobileOutOfArea;
        __values[10] = ReasonMobileNullNumber;
        __values[11] = ReasonMobilePeerRefused;
        __values[12] = ReasonInvalidMedia;
    }
    private int __value;

    public static final int _ReasonSuccess = 0;
    public static final int _ReasonCallDestBusy = 1;
    public static final int _ReasonCallDestNotAnswer = 2;
    public static final int _ReasonCallDestUnknown = 3;
    public static final int _ReasonCallDestFaxtone = 4;
    public static final int _ReasonCallDestNotFax = 5;
    public static final int _ReasonCallDestInvalid = 6;
    public static final int _ReasonCallVoiceDetected = 7;
    public static final int _ReasonMobilePowerOff = 8;
    public static final int _ReasonMobileOutOfArea = 9;
    public static final int _ReasonMobileNullNumber = 10;
    public static final int _ReasonMobilePeerRefused = 11;
    public static final int _ReasonInvalidMedia = 12;

    public static SignalFailedReasonT
    convert(int val)
    {
        assert val < 13;
        return __values[val];
    }

    public static SignalFailedReasonT
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
    SignalFailedReasonT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static SignalFailedReasonT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 13)
        {
            throw new Ice.MarshalException();
        }
        return SignalFailedReasonT.convert(__v);
    }
}
