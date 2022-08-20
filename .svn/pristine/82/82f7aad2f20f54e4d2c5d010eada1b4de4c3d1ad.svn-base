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

public final class SignalEventT implements java.lang.Cloneable
{
    public long sessionID;

    public int connectionID;

    public ConnectionT connectionObj;

    public SignalEventTypeT eventType;

    public SignalFailedReasonT failedReason;

    public int timeStamp;

    public SignalEventT()
    {
    }

    public SignalEventT(long sessionID, int connectionID, ConnectionT connectionObj, SignalEventTypeT eventType, SignalFailedReasonT failedReason, int timeStamp)
    {
        this.sessionID = sessionID;
        this.connectionID = connectionID;
        this.connectionObj = connectionObj;
        this.eventType = eventType;
        this.failedReason = failedReason;
        this.timeStamp = timeStamp;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        SignalEventT _r = null;
        try
        {
            _r = (SignalEventT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(sessionID != _r.sessionID)
            {
                return false;
            }
            if(connectionID != _r.connectionID)
            {
                return false;
            }
            if(connectionObj != _r.connectionObj && connectionObj != null && !connectionObj.equals(_r.connectionObj))
            {
                return false;
            }
            if(eventType != _r.eventType && eventType != null && !eventType.equals(_r.eventType))
            {
                return false;
            }
            if(failedReason != _r.failedReason && failedReason != null && !failedReason.equals(_r.failedReason))
            {
                return false;
            }
            if(timeStamp != _r.timeStamp)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 0;
        __h = 5 * __h + (int)sessionID;
        __h = 5 * __h + connectionID;
        __h = 5 * __h + connectionObj.hashCode();
        __h = 5 * __h + eventType.hashCode();
        __h = 5 * __h + failedReason.hashCode();
        __h = 5 * __h + timeStamp;
        return __h;
    }

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeLong(sessionID);
        __os.writeInt(connectionID);
        connectionObj.__write(__os);
        eventType.__write(__os);
        failedReason.__write(__os);
        __os.writeInt(timeStamp);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        sessionID = __is.readLong();
        connectionID = __is.readInt();
        connectionObj = new ConnectionT();
        connectionObj.__read(__is);
        eventType = SignalEventTypeT.__read(__is);
        failedReason = SignalFailedReasonT.__read(__is);
        timeStamp = __is.readInt();
    }
}
