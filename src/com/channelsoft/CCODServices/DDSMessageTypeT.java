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

public enum DDSMessageTypeT
{
    DDSUnDefine(0),
    IvrMsg(1),
    RouterReqMsg(2),
    RouterSrvMsg(3),
    SessionSrvMsg(4),
    AgentSrvMsg(5),
    RecordSrvMsg(6),
    CallFlowMsg(7),
    SessionMsg(8),
    NoLicenseMsg(9),
    QueueAbandonCallMsg(10),
    AlertAbandonCallMsg(11);

    private static DDSMessageTypeT[] __values = new DDSMessageTypeT[12];
    static
    {
        __values[0] = DDSUnDefine;
        __values[1] = IvrMsg;
        __values[2] = RouterReqMsg;
        __values[3] = RouterSrvMsg;
        __values[4] = SessionSrvMsg;
        __values[5] = AgentSrvMsg;
        __values[6] = RecordSrvMsg;
        __values[7] = CallFlowMsg;
        __values[8] = SessionMsg;
        __values[9] = NoLicenseMsg;
        __values[10] = QueueAbandonCallMsg;
        __values[11] = AlertAbandonCallMsg;
    }
    private int __value;

    public static final int _DDSUnDefine = 0;
    public static final int _IvrMsg = 1;
    public static final int _RouterReqMsg = 2;
    public static final int _RouterSrvMsg = 3;
    public static final int _SessionSrvMsg = 4;
    public static final int _AgentSrvMsg = 5;
    public static final int _RecordSrvMsg = 6;
    public static final int _CallFlowMsg = 7;
    public static final int _SessionMsg = 8;
    public static final int _NoLicenseMsg = 9;
    public static final int _QueueAbandonCallMsg = 10;
    public static final int _AlertAbandonCallMsg = 11;

    public static DDSMessageTypeT
    convert(int val)
    {
        assert val < 12;
        return __values[val];
    }

    public static DDSMessageTypeT
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
    DDSMessageTypeT(int val)
    {
        __value = val;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeByte((byte)__value);
    }

    public static DDSMessageTypeT
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readByte();
        if(__v < 0 || __v >= 12)
        {
            throw new Ice.MarshalException();
        }
        return DDSMessageTypeT.convert(__v);
    }
}
