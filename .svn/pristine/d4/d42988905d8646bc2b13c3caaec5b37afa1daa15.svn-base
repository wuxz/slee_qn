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

public interface _GLSInterfaceOperations
{
    GLSResult RegisterServiceUnit(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap, Ice.Current __current);

    GLSResult UnRegisterServiceUnit(String strServiceName, Ice.Current __current);

    GLSResult HeartBeat(String strServiceName, Ice.Current __current);

    GLSResult QueryServiceUnitParams(String strServiceName, ServiceUnitParamMapHolder svrUnitParamMap, Ice.Current __current);

    GLSResult RequestRouteTable(String strServiceName, RouteTableHolder seqRouteTable, Ice.Current __current);

    GLSResult QuerySvrUnitByEnterpriseID(String strEnterpriseID, ServiceType srcService, ServiceUnitInfoHolder serviceUnit, Ice.Current __current);

    GLSResult GetServiceUnitState(String strAreaID, ServiceUnitListHolder seqServiceUnitInfo, Ice.Current __current);

    GLSResult GetUcdsLocationDesc(String strEmsName, Ice.StringHolder strUcdsLocationDesc, Ice.Current __current);

    GLSResult GetEnterpriseInfor(String strDialerName, EnterpriseTableHolder seqEnterpriseTable, Ice.Current __current);

    GLSResult GetDDSLocationDesc(String strServiceName, Ice.StringHolder strLocationDesc, Ice.Current __current);

    GLSResult SynchronizeData(Ice.Current __current);
}
