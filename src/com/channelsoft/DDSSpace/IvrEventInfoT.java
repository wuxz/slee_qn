// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.DDSSpace;

public final class IvrEventInfoT implements java.lang.Cloneable
{
    public String veName;

    public long TimeStamp;

    public long SessionID;

    public String EnterpriseID;

    public String LocalURI;

    public String RemoteURI;

    public IvrCallTypeT CallType;

    public IvrCallEventT CallEvent;

    public IvrEventReasonT EventReason;

    public IvrEventInfoT()
    {
    }

    public IvrEventInfoT(String veName, long TimeStamp, long SessionID, String EnterpriseID, String LocalURI, String RemoteURI, IvrCallTypeT CallType, IvrCallEventT CallEvent, IvrEventReasonT EventReason)
    {
        this.veName = veName;
        this.TimeStamp = TimeStamp;
        this.SessionID = SessionID;
        this.EnterpriseID = EnterpriseID;
        this.LocalURI = LocalURI;
        this.RemoteURI = RemoteURI;
        this.CallType = CallType;
        this.CallEvent = CallEvent;
        this.EventReason = EventReason;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        IvrEventInfoT _r = null;
        try
        {
            _r = (IvrEventInfoT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(veName != _r.veName && veName != null && !veName.equals(_r.veName))
            {
                return false;
            }
            if(TimeStamp != _r.TimeStamp)
            {
                return false;
            }
            if(SessionID != _r.SessionID)
            {
                return false;
            }
            if(EnterpriseID != _r.EnterpriseID && EnterpriseID != null && !EnterpriseID.equals(_r.EnterpriseID))
            {
                return false;
            }
            if(LocalURI != _r.LocalURI && LocalURI != null && !LocalURI.equals(_r.LocalURI))
            {
                return false;
            }
            if(RemoteURI != _r.RemoteURI && RemoteURI != null && !RemoteURI.equals(_r.RemoteURI))
            {
                return false;
            }
            if(CallType != _r.CallType && CallType != null && !CallType.equals(_r.CallType))
            {
                return false;
            }
            if(CallEvent != _r.CallEvent && CallEvent != null && !CallEvent.equals(_r.CallEvent))
            {
                return false;
            }
            if(EventReason != _r.EventReason && EventReason != null && !EventReason.equals(_r.EventReason))
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
        if(veName != null)
        {
            __h = 5 * __h + veName.hashCode();
        }
        __h = 5 * __h + (int)TimeStamp;
        __h = 5 * __h + (int)SessionID;
        if(EnterpriseID != null)
        {
            __h = 5 * __h + EnterpriseID.hashCode();
        }
        if(LocalURI != null)
        {
            __h = 5 * __h + LocalURI.hashCode();
        }
        if(RemoteURI != null)
        {
            __h = 5 * __h + RemoteURI.hashCode();
        }
        __h = 5 * __h + CallType.hashCode();
        __h = 5 * __h + CallEvent.hashCode();
        __h = 5 * __h + EventReason.hashCode();
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
        __os.writeString(veName);
        __os.writeLong(TimeStamp);
        __os.writeLong(SessionID);
        __os.writeString(EnterpriseID);
        __os.writeString(LocalURI);
        __os.writeString(RemoteURI);
        CallType.__write(__os);
        CallEvent.__write(__os);
        EventReason.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        veName = __is.readString();
        TimeStamp = __is.readLong();
        SessionID = __is.readLong();
        EnterpriseID = __is.readString();
        LocalURI = __is.readString();
        RemoteURI = __is.readString();
        CallType = IvrCallTypeT.__read(__is);
        CallEvent = IvrCallEventT.__read(__is);
        EventReason = IvrEventReasonT.__read(__is);
    }
}
