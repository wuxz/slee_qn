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

public final class ServiceUnitInfo implements java.lang.Cloneable
{
    public String strServiceName;

    public int nServiceType;

    public int nServiceMode;

    public int nRegisterState;

    public int nRunningState;

    public int nHeartBeatInterval;

    public long nLastHBTime;

    public String strLocationDesc;

    public ServiceUnitInfo()
    {
    }

    public ServiceUnitInfo(String strServiceName, int nServiceType, int nServiceMode, int nRegisterState, int nRunningState, int nHeartBeatInterval, long nLastHBTime, String strLocationDesc)
    {
        this.strServiceName = strServiceName;
        this.nServiceType = nServiceType;
        this.nServiceMode = nServiceMode;
        this.nRegisterState = nRegisterState;
        this.nRunningState = nRunningState;
        this.nHeartBeatInterval = nHeartBeatInterval;
        this.nLastHBTime = nLastHBTime;
        this.strLocationDesc = strLocationDesc;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ServiceUnitInfo _r = null;
        try
        {
            _r = (ServiceUnitInfo)rhs;
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
            if(nServiceType != _r.nServiceType)
            {
                return false;
            }
            if(nServiceMode != _r.nServiceMode)
            {
                return false;
            }
            if(nRegisterState != _r.nRegisterState)
            {
                return false;
            }
            if(nRunningState != _r.nRunningState)
            {
                return false;
            }
            if(nHeartBeatInterval != _r.nHeartBeatInterval)
            {
                return false;
            }
            if(nLastHBTime != _r.nLastHBTime)
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
        __h = 5 * __h + nServiceType;
        __h = 5 * __h + nServiceMode;
        __h = 5 * __h + nRegisterState;
        __h = 5 * __h + nRunningState;
        __h = 5 * __h + nHeartBeatInterval;
        __h = 5 * __h + (int)nLastHBTime;
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
        __os.writeInt(nServiceType);
        __os.writeInt(nServiceMode);
        __os.writeInt(nRegisterState);
        __os.writeInt(nRunningState);
        __os.writeInt(nHeartBeatInterval);
        __os.writeLong(nLastHBTime);
        __os.writeString(strLocationDesc);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        strServiceName = __is.readString();
        nServiceType = __is.readInt();
        nServiceMode = __is.readInt();
        nRegisterState = __is.readInt();
        nRunningState = __is.readInt();
        nHeartBeatInterval = __is.readInt();
        nLastHBTime = __is.readLong();
        strLocationDesc = __is.readString();
    }
}
