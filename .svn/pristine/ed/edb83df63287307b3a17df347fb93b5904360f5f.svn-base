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

final class _AMD_CMSInterface_ShiftService extends IceInternal.IncomingAsync implements AMD_CMSInterface_ShiftService
{
    public
    _AMD_CMSInterface_ShiftService(IceInternal.Incoming in)
    {
        super(in);
    }

    public void
    ice_response(CCODResultT __ret, int timestamp)
    {
        try
        {
            IceInternal.BasicStream __os = this.__os();
            __os.writeInt(timestamp);
            __ret.__write(__os);
        }
        catch(Ice.LocalException __ex)
        {
            ice_exception(__ex);
        }
        __response(true);
    }

    public void
    ice_exception(java.lang.Exception ex)
    {
        __exception(ex);
    }
}
