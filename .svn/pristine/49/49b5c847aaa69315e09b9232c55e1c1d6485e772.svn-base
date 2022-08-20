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

public interface _GLSInterfaceOperationsNC
{
    GLSResult RegisterServiceUnit(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap);

    GLSResult UnRegisterServiceUnit(String strServiceName);

    GLSResult HeartBeat(String strServiceName);

    GLSResult QueryServiceUnitParams(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap);

    GLSResult RequestRouteTable(String strServiceName, RouteTableHolder seqRouteTable);

    GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID, ServiceType srcService, ServiceUnitInfoHolder serviceUnit);

    GLSResult GetServiceUnitState(String strAreaID, ServiceUnitListHolder seqServiceUnitInfo);

    GLSResult GetUcdsLocationDesc(String strEmsName, Ice.StringHolder strUcdsLocationDesc);

    GLSResult GetEnterpriseInfor(String strDialerName, EnterpriseTableHolder seqEnterpriseTable);

    GLSResult GetDDSLocationDesc(String strServiceName, Ice.StringHolder strLocationDesc);

    GLSResult SynchronizeData();
}
