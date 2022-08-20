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

public enum CCODResultT
{
    ResSuccess(0),
    ResInvokeFailed(1),
    ResNotSupport(2),
    ResNotAttachedWithMedia(3),
    ResNotEnoughResource(4),
    ResInvalidState(5),
    ResInvalidIDType(6),
    ResSessionIDNotExist(7),
    ResConnIDNotExist(8),
    ResMepIDNotExist(9),
    ResConfIDNotExist(10),
    ResInvalidTTSText(11),
    ResInvalidUri(12),
    ResCallbackLost(13),
    ResNetworkProblem(14),
    ResInvalidEndpointID(15),
    ResInvalidParameter(16);

    private static CCODResultT[] __values = new CCODResultT[17];
    static
    {
        __values[0] = ResSuccess;
        __values[1] = ResInvokeFailed;
        __values[2] = ResNotSupport;
        __values[3] = ResNotAttachedWithMedia;
        __values[4] = ResNotEnoughResource;
        __values[5] = ResInvalidState;
        __values[6] = ResInvalidIDType;
        __values[7] = ResSessionIDNotExist;
        __values[8] = ResConnIDNotExist;
        __values[9] = ResMepIDNotExist;
        __values[10] = ResConfIDNotExist;
        __values[11] = ResInvalidTTSText;
        __values[12] = ResInvalidUri;
        __values[13] = ResCallbackLost;
        __values[14] = ResNetworkProblem;
        __values[15] = ResInvalidEndpointID;
        __values[16] = ResInvalidParameter;
    }
    private int __value;

    public static final int _ResSuccess = 0;
    public static final int _ResInvokeFailed = 1;
    public static final int _ResNotSupport = 2;
    public static final int _ResNotAttachedWithMedia = 3;
    public static final int _ResNotEnoughResource = 4;
    public static final int _ResInvalidState = 5;
    public static final int _ResInvalidIDType = 6;
    public static final int _ResSessionIDNotExist = 7;
    public static final int _ResConnIDNotExist = 8;
    public static final int _ResMepIDNotExist = 9;
    public static final int _ResConfIDNotExist = 10;
    public static final int _ResInvalidTTSText = 11;
    public static final int _ResInvalidUri = 12;
    public static final int _ResCallbackLost = 13;
    public static final int _ResNetworkProblem = 14;
    public static final int _ResInvalidEndpointID = 15;
    public static final int _ResInvalidParameter = 16;

    public static CCODResultT
    convert(int val)
    {
        assert val < 17;
        return __values[val];
    }

    public static CCODResultT
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
    CCODResultT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static CCODResultT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 17)
        {
            throw new Ice.MarshalException();
        }
        return CCODResultT.convert(__v);
    }
}
