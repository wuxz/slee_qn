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

public interface _ServiceUnitInterfaceOperations
{
    CCODResultT StartService(ServiceInfoT serviceInfo, Ice.Current __current);

    void SendSignalEvent(SignalEventT event, Ice.Current __current);

    void SendMediaEvent(MediaEventT event, Ice.Current __current);

    void SetCallback(Ice.Identity ident, String serviceName, Ice.Current __current);
}
