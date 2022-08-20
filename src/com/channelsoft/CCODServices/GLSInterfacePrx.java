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

public interface GLSInterfacePrx extends Ice.ObjectPrx
{
    public GLSResult RegisterServiceUnit(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap);
    public GLSResult RegisterServiceUnit(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap, java.util.Map<String, String> __ctx);

    public GLSResult UnRegisterServiceUnit(String strServiceName);
    public GLSResult UnRegisterServiceUnit(String strServiceName, java.util.Map<String, String> __ctx);

    public GLSResult HeartBeat(String strServiceName);
    public GLSResult HeartBeat(String strServiceName, java.util.Map<String, String> __ctx);

    public GLSResult QueryServiceUnitParams(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap);
    public GLSResult QueryServiceUnitParams(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap, java.util.Map<String, String> __ctx);

    public GLSResult RequestRouteTable(String strServiceName, RouteTableHolder seqRouteTable);
    public GLSResult RequestRouteTable(String strServiceName, RouteTableHolder seqRouteTable, java.util.Map<String, String> __ctx);

    public GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID, ServiceType srcService, ServiceUnitInfoHolder serviceUnit);
    public GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID, ServiceType srcService, ServiceUnitInfoHolder serviceUnit, java.util.Map<String, String> __ctx);

    public GLSResult GetServiceUnitState(String strAreaID, ServiceUnitListHolder seqServiceUnitInfo);
    public GLSResult GetServiceUnitState(String strAreaID, ServiceUnitListHolder seqServiceUnitInfo, java.util.Map<String, String> __ctx);

    public GLSResult GetUcdsLocationDesc(String strEmsName, Ice.StringHolder strUcdsLocationDesc);
    public GLSResult GetUcdsLocationDesc(String strEmsName, Ice.StringHolder strUcdsLocationDesc, java.util.Map<String, String> __ctx);

    public GLSResult GetEnterpriseInfor(String strDialerName, EnterpriseTableHolder seqEnterpriseTable);
    public GLSResult GetEnterpriseInfor(String strDialerName, EnterpriseTableHolder seqEnterpriseTable, java.util.Map<String, String> __ctx);

    public GLSResult GetDDSLocationDesc(String strServiceName, Ice.StringHolder strLocationDesc);
    public GLSResult GetDDSLocationDesc(String strServiceName, Ice.StringHolder strLocationDesc, java.util.Map<String, String> __ctx);

    public GLSResult SynchronizeData();
    public GLSResult SynchronizeData(java.util.Map<String, String> __ctx);
}
