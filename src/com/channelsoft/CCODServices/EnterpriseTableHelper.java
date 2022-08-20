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

public final class EnterpriseTableHelper
{
    public static void
    write(IceInternal.BasicStream __os, EnterpriseInfor[] __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.length);
            for(int __i0 = 0; __i0 < __v.length; __i0++)
            {
                __v[__i0].__write(__os);
            }
        }
    }

    public static EnterpriseInfor[]
    read(IceInternal.BasicStream __is)
    {
        EnterpriseInfor[] __v;
        final int __len0 = __is.readSize();
        __is.startSeq(__len0, 38);
        __v = new EnterpriseInfor[__len0];
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            __v[__i0] = new EnterpriseInfor();
            __v[__i0].__read(__is);
            __is.checkSeq();
            __is.endElement();
        }
        __is.endSeq(__len0);
        return __v;
    }
}
