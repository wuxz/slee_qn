// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.VGProxy;

public final class SGEvent implements java.lang.Cloneable
{
    public int ResID;

    public int CallID;

    public int EventID;

    public int EventData;

    public String CallerID;

    public String CalledID;

    public String OriCallerID;

    public String OriCalledID;

    public int mediaType;
    
    public String rfu;

    public SGEvent()
    {
    }

    public SGEvent(int ResID, int CallID, int EventID, int EventData,
			String CallerID, String CalledID, String OriCallerID,
			String OriCalledID, int mediaType, String rfu)
    {
        this.ResID = ResID;
        this.CallID = CallID;
        this.EventID = EventID;
        this.EventData = EventData;
        this.CallerID = CallerID;
        this.CalledID = CalledID;
        this.OriCallerID = OriCallerID;
        this.OriCalledID = OriCalledID;
        this.mediaType = mediaType;
        this.rfu = rfu;
    }

    public SGEvent(int ResID, int CallID, int EventID, int EventData,
			String CallerID, String CalledID, String OriCallerID,
			String OriCalledID, String rfu)
    {
        this.ResID = ResID;
        this.CallID = CallID;
        this.EventID = EventID;
        this.EventData = EventData;
        this.CallerID = CallerID;
        this.CalledID = CalledID;
        this.OriCallerID = OriCallerID;
        this.OriCalledID = OriCalledID;
        this.rfu = rfu;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        SGEvent _r = null;
        try
        {
            _r = (SGEvent)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(ResID != _r.ResID)
            {
                return false;
            }
            if(CallID != _r.CallID)
            {
                return false;
            }
            if(EventID != _r.EventID)
            {
                return false;
            }
            if(EventData != _r.EventData)
            {
                return false;
            }
            if(CallerID != _r.CallerID && CallerID != null && !CallerID.equals(_r.CallerID))
            {
                return false;
            }
            if(CalledID != _r.CalledID && CalledID != null && !CalledID.equals(_r.CalledID))
            {
                return false;
            }
            if(OriCallerID != _r.OriCallerID && OriCallerID != null && !OriCallerID.equals(_r.OriCallerID))
            {
                return false;
            }
            if(OriCalledID != _r.OriCalledID && OriCalledID != null && !OriCalledID.equals(_r.OriCalledID))
            {
                return false;
            }
            if(rfu != _r.rfu && rfu != null && !rfu.equals(_r.rfu))
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
        __h = 5 * __h + ResID;
        __h = 5 * __h + CallID;
        __h = 5 * __h + EventID;
        __h = 5 * __h + EventData;
        if(CallerID != null)
        {
            __h = 5 * __h + CallerID.hashCode();
        }
        if(CalledID != null)
        {
            __h = 5 * __h + CalledID.hashCode();
        }
        if(OriCallerID != null)
        {
            __h = 5 * __h + OriCallerID.hashCode();
        }
        if(OriCalledID != null)
        {
            __h = 5 * __h + OriCalledID.hashCode();
        }
        if(rfu != null)
        {
            __h = 5 * __h + rfu.hashCode();
        }
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
        __os.writeInt(ResID);
        __os.writeInt(CallID);
        __os.writeInt(EventID);
        __os.writeInt(EventData);
        __os.writeString(CallerID);
        __os.writeString(CalledID);
        __os.writeString(OriCallerID);
        __os.writeString(OriCalledID);
        __os.writeString(rfu);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        ResID = __is.readInt();
        CallID = __is.readInt();
        EventID = __is.readInt();
        EventData = __is.readInt();
        CallerID = __is.readString();
        CalledID = __is.readString();
        OriCallerID = __is.readString();
        OriCalledID = __is.readString();
        rfu = __is.readString();
    }
}
