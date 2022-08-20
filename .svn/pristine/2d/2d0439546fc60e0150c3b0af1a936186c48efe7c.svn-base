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

public enum MediaEventTypeT
{
    MgEvtFailed(0),
    MgEvtPlayEnd(1),
    MgEvtRecordEnd(2),
    MgEvtGotDtmf(3),
    MgEvtSendFaxEnd(4),
    MgEvtRecvFaxEnd(5),
    MgEvtFaxFailed(6);

    private static MediaEventTypeT[] __values = new MediaEventTypeT[7];
    static
    {
        __values[0] = MgEvtFailed;
        __values[1] = MgEvtPlayEnd;
        __values[2] = MgEvtRecordEnd;
        __values[3] = MgEvtGotDtmf;
        __values[4] = MgEvtSendFaxEnd;
        __values[5] = MgEvtRecvFaxEnd;
        __values[6] = MgEvtFaxFailed;
    }
    private int __value;

    public static final int _MgEvtFailed = 0;
    public static final int _MgEvtPlayEnd = 1;
    public static final int _MgEvtRecordEnd = 2;
    public static final int _MgEvtGotDtmf = 3;
    public static final int _MgEvtSendFaxEnd = 4;
    public static final int _MgEvtRecvFaxEnd = 5;
    public static final int _MgEvtFaxFailed = 6;

    public static MediaEventTypeT
    convert(int val)
    {
        assert val < 7;
        return __values[val];
    }

    public static MediaEventTypeT
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
    MediaEventTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static MediaEventTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 7)
        {
            throw new Ice.MarshalException();
        }
        return MediaEventTypeT.convert(__v);
    }
}
