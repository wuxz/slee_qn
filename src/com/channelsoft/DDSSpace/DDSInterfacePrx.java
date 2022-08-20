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

public interface DDSInterfacePrx extends Ice.ObjectPrx
{
    public void setCmsEvent(CMSEventInfoT event);
    public void setCmsEvent(CMSEventInfoT event, java.util.Map<String, String> __ctx);

    public void ResetIvrEvent(String veName);
    public void ResetIvrEvent(String veName, java.util.Map<String, String> __ctx);

    public void setIvrLicense(IvrLicenseInfoT[] lstLicense);
    public void setIvrLicense(IvrLicenseInfoT[] lstLicense, java.util.Map<String, String> __ctx);

    public void setIvrEvent(IvrEventInfoT infoCallEvent);
    public void setIvrEvent(IvrEventInfoT infoCallEvent, java.util.Map<String, String> __ctx);

    public int regService(String ServiceName, String strProxy, String[] lstParam);
    public int regService(String ServiceName, String strProxy, String[] lstParam, java.util.Map<String, String> __ctx);

    public void AddEnterprise(String ServiceName, String EntpID);
    public void AddEnterprise(String ServiceName, String EntpID, java.util.Map<String, String> __ctx);

    public void RmEnterprise(String ServiceName, String EntpID);
    public void RmEnterprise(String ServiceName, String EntpID, java.util.Map<String, String> __ctx);
}
