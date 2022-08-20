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

public enum MediaDirectionT
{
    DirectionNone(0),
    DirectionHalf(1),
    DirectionFull(2),
    DirectionVideoBoth(3),
    DirectionOriVideo(4),
    DirectionDestVideo(5);

    private static MediaDirectionT[] __values = new MediaDirectionT[6];
    static
    {
        __values[0] = DirectionNone;
        __values[1] = DirectionHalf;
        __values[2] = DirectionFull;
        __values[3] = DirectionVideoBoth;
        __values[4] = DirectionOriVideo;
        __values[5] = DirectionDestVideo;
    }
    private int __value;

    public static final int _DirectionNone = 0;
    public static final int _DirectionHalf = 1;
    public static final int _DirectionFull = 2;
    public static final int _DirectionVideoBoth = 3;
    public static final int _DirectionOriVideo = 4;
    public static final int _DirectionDestVideo = 5;

    public static MediaDirectionT
    convert(int val)
    {
        assert val < 6;
        return __values[val];
    }

    public static MediaDirectionT
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
    MediaDirectionT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static MediaDirectionT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 6)
        {
            throw new Ice.MarshalException();
        }
        return MediaDirectionT.convert(__v);
    }
}
