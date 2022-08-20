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

public abstract class _GLSInterfaceDisp extends Ice.ObjectImpl implements GLSInterface
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::CCODServices::GLSInterface",
        "::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[0];
    }

    public static String
    ice_staticId()
    {
        return __ids[0];
    }

    public final GLSResult
    GetDDSLocationDesc(String strServiceName, Ice.StringHolder strLocationDesc)
    {
        return GetDDSLocationDesc(strServiceName, strLocationDesc, null);
    }

    public final GLSResult
    GetEnterpriseInfor(String strDialerName, EnterpriseTableHolder seqEnterpriseTable)
    {
        return GetEnterpriseInfor(strDialerName, seqEnterpriseTable, null);
    }

    public final GLSResult
    GetServiceUnitState(String strAreaID, ServiceUnitListHolder seqServiceUnitInfo)
    {
        return GetServiceUnitState(strAreaID, seqServiceUnitInfo, null);
    }

    public final GLSResult
    GetUcdsLocationDesc(String strEmsName, Ice.StringHolder strUcdsLocationDesc)
    {
        return GetUcdsLocationDesc(strEmsName, strUcdsLocationDesc, null);
    }

    public final GLSResult
    HeartBeat(String strServiceName)
    {
        return HeartBeat(strServiceName, null);
    }

    public final GLSResult
    QueryServiceUnitParams(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap)
    {
        return QueryServiceUnitParams(strServiceName, svrUnitParamMap, null);
    }

    public final GLSResult
    QuerySvrUnitByEnterpriseID(String strEnterpriseID, ServiceType srcService, ServiceUnitInfoHolder serviceUnit)
    {
        return QuerySvrUnitByEnterpriseID(strEnterpriseID, srcService, serviceUnit, null);
    }

    public final GLSResult
    RegisterServiceUnit(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap)
    {
        return RegisterServiceUnit(strServiceName, svrUnitParamMap, null);
    }

    public final GLSResult
    RequestRouteTable(String strServiceName, RouteTableHolder seqRouteTable)
    {
        return RequestRouteTable(strServiceName, seqRouteTable, null);
    }

    public final GLSResult
    SynchronizeData()
    {
        return SynchronizeData(null);
    }

    public final GLSResult
    UnRegisterServiceUnit(String strServiceName)
    {
        return UnRegisterServiceUnit(strServiceName, null);
    }

    public static IceInternal.DispatchStatus
    ___RegisterServiceUnit(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strServiceName;
        strServiceName = __is.readString();
        ServiceUnitParamMapHolder svrUnitParamMap = new ServiceUnitParamMapHolder();
        GLSResult __ret = __obj.RegisterServiceUnit(strServiceName, svrUnitParamMap, __current);
        ServiceUnitParamMapHelper.write(__os, svrUnitParamMap.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___UnRegisterServiceUnit(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strServiceName;
        strServiceName = __is.readString();
        GLSResult __ret = __obj.UnRegisterServiceUnit(strServiceName, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___HeartBeat(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strServiceName;
        strServiceName = __is.readString();
        GLSResult __ret = __obj.HeartBeat(strServiceName, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___QueryServiceUnitParams(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strServiceName;
        strServiceName = __is.readString();
        ServiceUnitParamMapHolder svrUnitParamMap = new ServiceUnitParamMapHolder();
        GLSResult __ret = __obj.QueryServiceUnitParams(strServiceName, svrUnitParamMap, __current);
        ServiceUnitParamMapHelper.write(__os, svrUnitParamMap.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___RequestRouteTable(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strServiceName;
        strServiceName = __is.readString();
        RouteTableHolder seqRouteTable = new RouteTableHolder();
        GLSResult __ret = __obj.RequestRouteTable(strServiceName, seqRouteTable, __current);
        RouteTableHelper.write(__os, seqRouteTable.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___QuerySvrUnitByEnterpriseID(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strEnterpriseID;
        strEnterpriseID = __is.readString();
        ServiceType srcService;
        srcService = ServiceType.__read(__is);
        ServiceUnitInfoHolder serviceUnit = new ServiceUnitInfoHolder();
        GLSResult __ret = __obj.QuerySvrUnitByEnterpriseID(strEnterpriseID, srcService, serviceUnit, __current);
        serviceUnit.value.__write(__os);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetServiceUnitState(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strAreaID;
        strAreaID = __is.readString();
        ServiceUnitListHolder seqServiceUnitInfo = new ServiceUnitListHolder();
        GLSResult __ret = __obj.GetServiceUnitState(strAreaID, seqServiceUnitInfo, __current);
        ServiceUnitListHelper.write(__os, seqServiceUnitInfo.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetUcdsLocationDesc(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strEmsName;
        strEmsName = __is.readString();
        Ice.StringHolder strUcdsLocationDesc = new Ice.StringHolder();
        GLSResult __ret = __obj.GetUcdsLocationDesc(strEmsName, strUcdsLocationDesc, __current);
        __os.writeString(strUcdsLocationDesc.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetEnterpriseInfor(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strDialerName;
        strDialerName = __is.readString();
        EnterpriseTableHolder seqEnterpriseTable = new EnterpriseTableHolder();
        GLSResult __ret = __obj.GetEnterpriseInfor(strDialerName, seqEnterpriseTable, __current);
        EnterpriseTableHelper.write(__os, seqEnterpriseTable.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetDDSLocationDesc(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String strServiceName;
        strServiceName = __is.readString();
        Ice.StringHolder strLocationDesc = new Ice.StringHolder();
        GLSResult __ret = __obj.GetDDSLocationDesc(strServiceName, strLocationDesc, __current);
        __os.writeString(strLocationDesc.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SynchronizeData(GLSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __os = __inS.os();
        GLSResult __ret = __obj.SynchronizeData(__current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "GetDDSLocationDesc",
        "GetEnterpriseInfor",
        "GetServiceUnitState",
        "GetUcdsLocationDesc",
        "HeartBeat",
        "QueryServiceUnitParams",
        "QuerySvrUnitByEnterpriseID",
        "RegisterServiceUnit",
        "RequestRouteTable",
        "SynchronizeData",
        "UnRegisterServiceUnit",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public IceInternal.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            return IceInternal.DispatchStatus.DispatchOperationNotExist;
        }

        switch(pos)
        {
            case 0:
            {
                return ___GetDDSLocationDesc(this, in, __current);
            }
            case 1:
            {
                return ___GetEnterpriseInfor(this, in, __current);
            }
            case 2:
            {
                return ___GetServiceUnitState(this, in, __current);
            }
            case 3:
            {
                return ___GetUcdsLocationDesc(this, in, __current);
            }
            case 4:
            {
                return ___HeartBeat(this, in, __current);
            }
            case 5:
            {
                return ___QueryServiceUnitParams(this, in, __current);
            }
            case 6:
            {
                return ___QuerySvrUnitByEnterpriseID(this, in, __current);
            }
            case 7:
            {
                return ___RegisterServiceUnit(this, in, __current);
            }
            case 8:
            {
                return ___RequestRouteTable(this, in, __current);
            }
            case 9:
            {
                return ___SynchronizeData(this, in, __current);
            }
            case 10:
            {
                return ___UnRegisterServiceUnit(this, in, __current);
            }
            case 11:
            {
                return ___ice_id(this, in, __current);
            }
            case 12:
            {
                return ___ice_ids(this, in, __current);
            }
            case 13:
            {
                return ___ice_isA(this, in, __current);
            }
            case 14:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
