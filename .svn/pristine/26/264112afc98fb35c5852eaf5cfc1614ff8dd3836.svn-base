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

public enum ProtocolT
{
    ProtoSip(0),
    ProtoH323(1),
    ProtoIsdn(2),
    ProtoH263(3);

    private static ProtocolT[] __values = new ProtocolT[4];
    static
    {
        __values[0] = ProtoSip;
        __values[1] = ProtoH323;
        __values[2] = ProtoIsdn;
        __values[3] = ProtoH263;
    }
    private int __value;

    public static final int _ProtoSip = 0;
    public static final int _ProtoH323 = 1;
    public static final int _ProtoIsdn = 2;
    public static final int _ProtoH263 = 3;

    public static ProtocolT
    convert(int val)
    {
        assert val < 4;
        return __values[val];
    }

    public static ProtocolT
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
    ProtocolT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static ProtocolT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 4)
        {
            throw new Ice.MarshalException();
        }
        return ProtocolT.convert(__v);
    }
}
