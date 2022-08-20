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

public final class RouteInfo implements java.lang.Cloneable
{
    public String strServiceName;

    public String strDNIS;

    public String strEnterpriseID;

    public int nRouteType;

    public int nServiceType;

    public String strLocationDesc;

    public RouteInfo()
    {
    }

    public RouteInfo(String strServiceName, String strDNIS, String strEnterpriseID, int nRouteType, int nServiceType, String strLocationDesc)
    {
        this.strServiceName = strServiceName;
        this.strDNIS = strDNIS;
        this.strEnterpriseID = strEnterpriseID;
        this.nRouteType = nRouteType;
        this.nServiceType = nServiceType;
        this.strLocationDesc = strLocationDesc;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        RouteInfo _r = null;
        try
        {
            _r = (RouteInfo)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(strServiceName != _r.strServiceName && strServiceName != null && !strServiceName.equals(_r.strServiceName))
            {
                return false;
            }
            if(strDNIS != _r.strDNIS && strDNIS != null && !strDNIS.equals(_r.strDNIS))
            {
                return false;
            }
            if(strEnterpriseID != _r.strEnterpriseID && strEnterpriseID != null && !strEnterpriseID.equals(_r.strEnterpriseID))
            {
                return false;
            }
            if(nRouteType != _r.nRouteType)
            {
                return false;
            }
            if(nServiceType != _r.nServiceType)
            {
                return false;
            }
            if(strLocationDesc != _r.strLocationDesc && strLocationDesc != null && !strLocationDesc.equals(_r.strLocationDesc))
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
        if(strServiceName != null)
        {
            __h = 5 * __h + strServiceName.hashCode();
        }
        if(strDNIS != null)
        {
            __h = 5 * __h + strDNIS.hashCode();
        }
        if(strEnterpriseID != null)
        {
            __h = 5 * __h + strEnterpriseID.hashCode();
        }
        __h = 5 * __h + nRouteType;
        __h = 5 * __h + nServiceType;
        if(strLocationDesc != null)
        {
            __h = 5 * __h + strLocationDesc.hashCode();
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
        __os.writeString(strServiceName);
        __os.writeString(strDNIS);
        __os.writeString(strEnterpriseID);
        __os.writeInt(nRouteType);
        __os.writeInt(nServiceType);
        __os.writeString(strLocationDesc);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        strServiceName = __is.readString();
        strDNIS = __is.readString();
        strEnterpriseID = __is.readString();
        nRouteType = __is.readInt();
        nServiceType = __is.readInt();
        strLocationDesc = __is.readString();
    }
}
