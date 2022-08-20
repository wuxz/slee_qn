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

public final class MediaEventT implements java.lang.Cloneable
{
    public long sessionID;

    public int mepID;

    public MediaEventTypeT eventType;

    public MediaEventCauseT eventCause;

    public String eventBuffer;

    public int timeStamp;

    public MediaEventT()
    {
    }

    public MediaEventT(long sessionID, int mepID, MediaEventTypeT eventType, MediaEventCauseT eventCause, String eventBuffer, int timeStamp)
    {
        this.sessionID = sessionID;
        this.mepID = mepID;
        this.eventType = eventType;
        this.eventCause = eventCause;
        this.eventBuffer = eventBuffer;
        this.timeStamp = timeStamp;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        MediaEventT _r = null;
        try
        {
            _r = (MediaEventT)rhs;
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
            if(mepID != _r.mepID)
            {
                return false;
            }
            if(eventType != _r.eventType && eventType != null && !eventType.equals(_r.eventType))
            {
                return false;
            }
            if(eventCause != _r.eventCause && eventCause != null && !eventCause.equals(_r.eventCause))
            {
                return false;
            }
            if(eventBuffer != _r.eventBuffer && eventBuffer != null && !eventBuffer.equals(_r.eventBuffer))
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
        __h = 5 * __h + mepID;
        __h = 5 * __h + eventType.hashCode();
        __h = 5 * __h + eventCause.hashCode();
        if(eventBuffer != null)
        {
            __h = 5 * __h + eventBuffer.hashCode();
        }
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
        __os.writeInt(mepID);
        eventType.__write(__os);
        eventCause.__write(__os);
        __os.writeString(eventBuffer);
        __os.writeInt(timeStamp);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        sessionID = __is.readLong();
        mepID = __is.readInt();
        eventType = MediaEventTypeT.__read(__is);
        eventCause = MediaEventCauseT.__read(__is);
        eventBuffer = __is.readString();
        timeStamp = __is.readInt();
    }
}
