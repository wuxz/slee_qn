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

public final class ServiceInfoT implements java.lang.Cloneable
{
    public long sessionID;

    public String enterpriseID;

    public ConnectionT[] connectionList;

    public int timeStamp;

    public java.util.Map<java.lang.String, java.lang.String> serviceData;

    public ServiceTypeT srcService;

    public ServiceTypeT hostService;

    public String accountID;

    public ServiceInfoT()
    {
    }

    public ServiceInfoT(long sessionID, String enterpriseID, ConnectionT[] connectionList, int timeStamp, java.util.Map<java.lang.String, java.lang.String> serviceData, ServiceTypeT srcService, ServiceTypeT hostService, String accountID)
    {
        this.sessionID = sessionID;
        this.enterpriseID = enterpriseID;
        this.connectionList = connectionList;
        this.timeStamp = timeStamp;
        this.serviceData = serviceData;
        this.srcService = srcService;
        this.hostService = hostService;
        this.accountID = accountID;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ServiceInfoT _r = null;
        try
        {
            _r = (ServiceInfoT)rhs;
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
            if(enterpriseID != _r.enterpriseID && enterpriseID != null && !enterpriseID.equals(_r.enterpriseID))
            {
                return false;
            }
            if(!java.util.Arrays.equals(connectionList, _r.connectionList))
            {
                return false;
            }
            if(timeStamp != _r.timeStamp)
            {
                return false;
            }
            if(serviceData != _r.serviceData && serviceData != null && !serviceData.equals(_r.serviceData))
            {
                return false;
            }
            if(srcService != _r.srcService && srcService != null && !srcService.equals(_r.srcService))
            {
                return false;
            }
            if(hostService != _r.hostService && hostService != null && !hostService.equals(_r.hostService))
            {
                return false;
            }
            if(accountID != _r.accountID && accountID != null && !accountID.equals(_r.accountID))
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
        if(enterpriseID != null)
        {
            __h = 5 * __h + enterpriseID.hashCode();
        }
        if(connectionList != null)
        {
            for(int __i0 = 0; __i0 < connectionList.length; __i0++)
            {
                __h = 5 * __h + connectionList[__i0].hashCode();
            }
        }
        __h = 5 * __h + timeStamp;
        if(serviceData != null)
        {
            __h = 5 * __h + serviceData.hashCode();
        }
        __h = 5 * __h + srcService.hashCode();
        __h = 5 * __h + hostService.hashCode();
        if(accountID != null)
        {
            __h = 5 * __h + accountID.hashCode();
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
        __os.writeLong(sessionID);
        __os.writeString(enterpriseID);
        ConnectionListTHelper.write(__os, connectionList);
        __os.writeInt(timeStamp);
        ServiceDataTHelper.write(__os, serviceData);
        srcService.__write(__os);
        hostService.__write(__os);
        __os.writeString(accountID);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        sessionID = __is.readLong();
        enterpriseID = __is.readString();
        connectionList = ConnectionListTHelper.read(__is);
        timeStamp = __is.readInt();
        serviceData = ServiceDataTHelper.read(__is);
        srcService = ServiceTypeT.__read(__is);
        hostService = ServiceTypeT.__read(__is);
        accountID = __is.readString();
    }
}
