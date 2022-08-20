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

public final class IvrLicenseInfoT implements java.lang.Cloneable
{
    public String EnterpriseID;

    public long IvrLicense;

    public IvrLicenseInfoT()
    {
    }

    public IvrLicenseInfoT(String EnterpriseID, long IvrLicense)
    {
        this.EnterpriseID = EnterpriseID;
        this.IvrLicense = IvrLicense;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        IvrLicenseInfoT _r = null;
        try
        {
            _r = (IvrLicenseInfoT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(EnterpriseID != _r.EnterpriseID && EnterpriseID != null && !EnterpriseID.equals(_r.EnterpriseID))
            {
                return false;
            }
            if(IvrLicense != _r.IvrLicense)
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
        if(EnterpriseID != null)
        {
            __h = 5 * __h + EnterpriseID.hashCode();
        }
        __h = 5 * __h + (int)IvrLicense;
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
        __os.writeString(EnterpriseID);
        __os.writeLong(IvrLicense);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        EnterpriseID = __is.readString();
        IvrLicense = __is.readLong();
    }
}
