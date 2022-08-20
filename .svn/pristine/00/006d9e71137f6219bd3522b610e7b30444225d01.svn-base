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

public final class MGEvent implements java.lang.Cloneable
{
    public int ResID;

    public int CallID;

    public int EventID;

    public int Reason;

    public String DTMFString;

    public MGEvent()
    {
    }

    public MGEvent(int ResID, int CallID, int EventID, int Reason, String DTMFString)
    {
        this.ResID = ResID;
        this.CallID = CallID;
        this.EventID = EventID;
        this.Reason = Reason;
        this.DTMFString = DTMFString;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        MGEvent _r = null;
        try
        {
            _r = (MGEvent)rhs;
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
            if(Reason != _r.Reason)
            {
                return false;
            }
            if(DTMFString != _r.DTMFString && DTMFString != null && !DTMFString.equals(_r.DTMFString))
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
        __h = 5 * __h + Reason;
        if(DTMFString != null)
        {
            __h = 5 * __h + DTMFString.hashCode();
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
        __os.writeInt(Reason);
        __os.writeString(DTMFString);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        ResID = __is.readInt();
        CallID = __is.readInt();
        EventID = __is.readInt();
        Reason = __is.readInt();
        DTMFString = __is.readString();
    }
}
