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

public interface _DDSInterfaceDel extends Ice._ObjectDel
{
    void setCmsEvent(CMSEventInfoT event, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void ResetIvrEvent(String veName, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void setIvrLicense(IvrLicenseInfoT[] lstLicense, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void setIvrEvent(IvrEventInfoT infoCallEvent, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int regService(String ServiceName, String strProxy, String[] lstParam, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void AddEnterprise(String ServiceName, String EntpID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void RmEnterprise(String ServiceName, String EntpID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
