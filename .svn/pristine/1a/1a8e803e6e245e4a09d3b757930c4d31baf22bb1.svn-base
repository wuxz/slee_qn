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

public enum ElemT
{
    VoiceFile(0),
    TTSString(1);

    private static ElemT[] __values = new ElemT[2];
    static
    {
        __values[0] = VoiceFile;
        __values[1] = TTSString;
    }
    private int __value;

    public static final int _VoiceFile = 0;
    public static final int _TTSString = 1;

    public static ElemT
    convert(int val)
    {
        assert val < 2;
        return __values[val];
    }

    public static ElemT
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
    ElemT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static ElemT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 2)
        {
            throw new Ice.MarshalException();
        }
        return ElemT.convert(__v);
    }
}
