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

public enum GLSResult
{
    GLSSuccessReturn(0),
    GLSUnknowFailure(1),
    GLSOutofMemory(2),
    GLSInvalidArgs(3),
    GLSServiceTimeout(4),
    GLSBadAddress(5),
    GLSBadImplement(6);

    private static GLSResult[] __values = new GLSResult[7];
    static
    {
        __values[0] = GLSSuccessReturn;
        __values[1] = GLSUnknowFailure;
        __values[2] = GLSOutofMemory;
        __values[3] = GLSInvalidArgs;
        __values[4] = GLSServiceTimeout;
        __values[5] = GLSBadAddress;
        __values[6] = GLSBadImplement;
    }
    private int __value;

    public static final int _GLSSuccessReturn = 0;
    public static final int _GLSUnknowFailure = 1;
    public static final int _GLSOutofMemory = 2;
    public static final int _GLSInvalidArgs = 3;
    public static final int _GLSServiceTimeout = 4;
    public static final int _GLSBadAddress = 5;
    public static final int _GLSBadImplement = 6;

    public static GLSResult
    convert(int val)
    {
        assert val < 7;
        return __values[val];
    }

    public static GLSResult
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
    GLSResult(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static GLSResult
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 7)
        {
            throw new Ice.MarshalException();
        }
        return GLSResult.convert(__v);
    }
}
