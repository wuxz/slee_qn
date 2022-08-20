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

public enum MediaTypeT
{
    VoiceMedia(0),
    VideoMedia(1),
    OtherMedia(2);

    private static MediaTypeT[] __values = new MediaTypeT[3];
    static
    {
        __values[0] = VoiceMedia;
        __values[1] = VideoMedia;
        __values[2] = OtherMedia;
    }
    private int __value;

    public static final int _VoiceMedia = 0;
    public static final int _VideoMedia = 1;
    public static final int _OtherMedia = 2;

    public static MediaTypeT
    convert(int val)
    {
        assert val < 3;
        return __values[val];
    }

    public static MediaTypeT
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
    MediaTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static MediaTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 3)
        {
            throw new Ice.MarshalException();
        }
        return MediaTypeT.convert(__v);
    }
}
