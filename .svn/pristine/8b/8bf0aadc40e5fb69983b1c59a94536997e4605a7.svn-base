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

public final class CMSEventInfoT implements java.lang.Cloneable
{
    public String cmsName;

    public long TimeStamp;

    public long SessionID;

    public String EnterpriseID;

    public String LocalURI;

    public String RemoteURI;

    public String EventDesc;

    public CMSEventInfoT()
    {
    }

    public CMSEventInfoT(String cmsName, long TimeStamp, long SessionID, String EnterpriseID, String LocalURI, String RemoteURI, String EventDesc)
    {
        this.cmsName = cmsName;
        this.TimeStamp = TimeStamp;
        this.SessionID = SessionID;
        this.EnterpriseID = EnterpriseID;
        this.LocalURI = LocalURI;
        this.RemoteURI = RemoteURI;
        this.EventDesc = EventDesc;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        CMSEventInfoT _r = null;
        try
        {
            _r = (CMSEventInfoT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(cmsName != _r.cmsName && cmsName != null && !cmsName.equals(_r.cmsName))
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
            if(EventDesc != _r.EventDesc && EventDesc != null && !EventDesc.equals(_r.EventDesc))
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
        if(cmsName != null)
        {
            __h = 5 * __h + cmsName.hashCode();
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
        if(EventDesc != null)
        {
            __h = 5 * __h + EventDesc.hashCode();
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
        __os.writeString(cmsName);
        __os.writeLong(TimeStamp);
        __os.writeLong(SessionID);
        __os.writeString(EnterpriseID);
        __os.writeString(LocalURI);
        __os.writeString(RemoteURI);
        __os.writeString(EventDesc);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        cmsName = __is.readString();
        TimeStamp = __is.readLong();
        SessionID = __is.readLong();
        EnterpriseID = __is.readString();
        LocalURI = __is.readString();
        RemoteURI = __is.readString();
        EventDesc = __is.readString();
    }
}
