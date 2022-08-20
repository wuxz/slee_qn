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

public interface _DDSInterfaceOperationsNC
{
    void setCmsEvent(CMSEventInfoT event);

    void ResetIvrEvent(String veName);

    void setIvrLicense(IvrLicenseInfoT[] lstLicense);

    void setIvrEvent(IvrEventInfoT infoCallEvent);

    int regService(String ServiceName, String strProxy, String[] lstParam);

    void AddEnterprise(String ServiceName, String EntpID);

    void RmEnterprise(String ServiceName, String EntpID);
}
