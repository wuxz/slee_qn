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

public final class EnterpriseInfor implements java.lang.Cloneable
{
    public String strEnterpriseName;

    public String strEnterpriseID;

    public String strANI;

    public int nInboundLicense;

    public int nOutboundLicense;

    public int nFaxLicense;

    public int nEmailLicense;

    public int nSMSLicense;

    public int nTTSLicense;

    public int nAgentLicense;

    public int nRecordLicense;

    public String strUCDSLocationDesc;

    public String strChargingID;

    public String strServiceID;

    public EnterpriseInfor()
    {
    }

    public EnterpriseInfor(String strEnterpriseName, String strEnterpriseID, String strANI, int nInboundLicense, int nOutboundLicense, int nFaxLicense, int nEmailLicense, int nSMSLicense, int nTTSLicense, int nAgentLicense, int nRecordLicense, String strUCDSLocationDesc, String strChargingID, String strServiceID)
    {
        this.strEnterpriseName = strEnterpriseName;
        this.strEnterpriseID = strEnterpriseID;
        this.strANI = strANI;
        this.nInboundLicense = nInboundLicense;
        this.nOutboundLicense = nOutboundLicense;
        this.nFaxLicense = nFaxLicense;
        this.nEmailLicense = nEmailLicense;
        this.nSMSLicense = nSMSLicense;
        this.nTTSLicense = nTTSLicense;
        this.nAgentLicense = nAgentLicense;
        this.nRecordLicense = nRecordLicense;
        this.strUCDSLocationDesc = strUCDSLocationDesc;
        this.strChargingID = strChargingID;
        this.strServiceID = strServiceID;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        EnterpriseInfor _r = null;
        try
        {
            _r = (EnterpriseInfor)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(strEnterpriseName != _r.strEnterpriseName && strEnterpriseName != null && !strEnterpriseName.equals(_r.strEnterpriseName))
            {
                return false;
            }
            if(strEnterpriseID != _r.strEnterpriseID && strEnterpriseID != null && !strEnterpriseID.equals(_r.strEnterpriseID))
            {
                return false;
            }
            if(strANI != _r.strANI && strANI != null && !strANI.equals(_r.strANI))
            {
                return false;
            }
            if(nInboundLicense != _r.nInboundLicense)
            {
                return false;
            }
            if(nOutboundLicense != _r.nOutboundLicense)
            {
                return false;
            }
            if(nFaxLicense != _r.nFaxLicense)
            {
                return false;
            }
            if(nEmailLicense != _r.nEmailLicense)
            {
                return false;
            }
            if(nSMSLicense != _r.nSMSLicense)
            {
                return false;
            }
            if(nTTSLicense != _r.nTTSLicense)
            {
                return false;
            }
            if(nAgentLicense != _r.nAgentLicense)
            {
                return false;
            }
            if(nRecordLicense != _r.nRecordLicense)
            {
                return false;
            }
            if(strUCDSLocationDesc != _r.strUCDSLocationDesc && strUCDSLocationDesc != null && !strUCDSLocationDesc.equals(_r.strUCDSLocationDesc))
            {
                return false;
            }
            if(strChargingID != _r.strChargingID && strChargingID != null && !strChargingID.equals(_r.strChargingID))
            {
                return false;
            }
            if(strServiceID != _r.strServiceID && strServiceID != null && !strServiceID.equals(_r.strServiceID))
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
        if(strEnterpriseName != null)
        {
            __h = 5 * __h + strEnterpriseName.hashCode();
        }
        if(strEnterpriseID != null)
        {
            __h = 5 * __h + strEnterpriseID.hashCode();
        }
        if(strANI != null)
        {
            __h = 5 * __h + strANI.hashCode();
        }
        __h = 5 * __h + nInboundLicense;
        __h = 5 * __h + nOutboundLicense;
        __h = 5 * __h + nFaxLicense;
        __h = 5 * __h + nEmailLicense;
        __h = 5 * __h + nSMSLicense;
        __h = 5 * __h + nTTSLicense;
        __h = 5 * __h + nAgentLicense;
        __h = 5 * __h + nRecordLicense;
        if(strUCDSLocationDesc != null)
        {
            __h = 5 * __h + strUCDSLocationDesc.hashCode();
        }
        if(strChargingID != null)
        {
            __h = 5 * __h + strChargingID.hashCode();
        }
        if(strServiceID != null)
        {
            __h = 5 * __h + strServiceID.hashCode();
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
        __os.writeString(strEnterpriseName);
        __os.writeString(strEnterpriseID);
        __os.writeString(strANI);
        __os.writeInt(nInboundLicense);
        __os.writeInt(nOutboundLicense);
        __os.writeInt(nFaxLicense);
        __os.writeInt(nEmailLicense);
        __os.writeInt(nSMSLicense);
        __os.writeInt(nTTSLicense);
        __os.writeInt(nAgentLicense);
        __os.writeInt(nRecordLicense);
        __os.writeString(strUCDSLocationDesc);
        __os.writeString(strChargingID);
        __os.writeString(strServiceID);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        strEnterpriseName = __is.readString();
        strEnterpriseID = __is.readString();
        strANI = __is.readString();
        nInboundLicense = __is.readInt();
        nOutboundLicense = __is.readInt();
        nFaxLicense = __is.readInt();
        nEmailLicense = __is.readInt();
        nSMSLicense = __is.readInt();
        nTTSLicense = __is.readInt();
        nAgentLicense = __is.readInt();
        nRecordLicense = __is.readInt();
        strUCDSLocationDesc = __is.readString();
        strChargingID = __is.readString();
        strServiceID = __is.readString();
    }
}
