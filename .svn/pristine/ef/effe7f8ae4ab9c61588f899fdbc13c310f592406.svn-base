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

public final class CnfEvent implements java.lang.Cloneable
{
    public int nConfID;

    public int nResID;

    public int EventID;

    public int Reason;

    public String szDTMF;

    public CnfEvent()
    {
    }

    public CnfEvent(int nConfID, int nResID, int EventID, int Reason, String szDTMF)
    {
        this.nConfID = nConfID;
        this.nResID = nResID;
        this.EventID = EventID;
        this.Reason = Reason;
        this.szDTMF = szDTMF;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        CnfEvent _r = null;
        try
        {
            _r = (CnfEvent)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(nConfID != _r.nConfID)
            {
                return false;
            }
            if(nResID != _r.nResID)
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
            if(szDTMF != _r.szDTMF && szDTMF != null && !szDTMF.equals(_r.szDTMF))
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
        __h = 5 * __h + nConfID;
        __h = 5 * __h + nResID;
        __h = 5 * __h + EventID;
        __h = 5 * __h + Reason;
        if(szDTMF != null)
        {
            __h = 5 * __h + szDTMF.hashCode();
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
        __os.writeInt(nConfID);
        __os.writeInt(nResID);
        __os.writeInt(EventID);
        __os.writeInt(Reason);
        __os.writeString(szDTMF);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        nConfID = __is.readInt();
        nResID = __is.readInt();
        EventID = __is.readInt();
        Reason = __is.readInt();
        szDTMF = __is.readString();
    }
}
