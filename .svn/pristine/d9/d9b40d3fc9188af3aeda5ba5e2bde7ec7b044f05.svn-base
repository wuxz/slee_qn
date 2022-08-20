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

public enum MediaEndpointStateT
{
    MediaStateIdle(0),
    MediaStateFailed(1),
    MediaStatePlaying(2),
    MediaStateCollecting(3),
    MediaStateGeneratingDTMF(4),
    MediaStateSendingFax(5),
    MediaStateReceivingFax(6),
    MediaStateRecording(7);

    private static MediaEndpointStateT[] __values = new MediaEndpointStateT[8];
    static
    {
        __values[0] = MediaStateIdle;
        __values[1] = MediaStateFailed;
        __values[2] = MediaStatePlaying;
        __values[3] = MediaStateCollecting;
        __values[4] = MediaStateGeneratingDTMF;
        __values[5] = MediaStateSendingFax;
        __values[6] = MediaStateReceivingFax;
        __values[7] = MediaStateRecording;
    }
    private int __value;

    public static final int _MediaStateIdle = 0;
    public static final int _MediaStateFailed = 1;
    public static final int _MediaStatePlaying = 2;
    public static final int _MediaStateCollecting = 3;
    public static final int _MediaStateGeneratingDTMF = 4;
    public static final int _MediaStateSendingFax = 5;
    public static final int _MediaStateReceivingFax = 6;
    public static final int _MediaStateRecording = 7;

    public static MediaEndpointStateT
    convert(int val)
    {
        assert val < 8;
        return __values[val];
    }

    public static MediaEndpointStateT
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
    MediaEndpointStateT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static MediaEndpointStateT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 8)
        {
            throw new Ice.MarshalException();
        }
        return MediaEndpointStateT.convert(__v);
    }
}
