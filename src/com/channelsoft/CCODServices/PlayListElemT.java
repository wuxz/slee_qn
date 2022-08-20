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

public final class PlayListElemT implements java.lang.Cloneable
{
    public ElemT type;

    public EncodingT encoding;

    public String content;

    public int rate;

    public PlayListElemT()
    {
    }

    public PlayListElemT(ElemT type, EncodingT encoding, String content, int rate)
    {
        this.type = type;
        this.encoding = encoding;
        this.content = content;
        this.rate = rate;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        PlayListElemT _r = null;
        try
        {
            _r = (PlayListElemT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(type != _r.type && type != null && !type.equals(_r.type))
            {
                return false;
            }
            if(encoding != _r.encoding && encoding != null && !encoding.equals(_r.encoding))
            {
                return false;
            }
            if(content != _r.content && content != null && !content.equals(_r.content))
            {
                return false;
            }
            if(rate != _r.rate)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 0;
        __h = 5 * __h + type.hashCode();
        __h = 5 * __h + encoding.hashCode();
        if(content != null)
        {
            __h = 5 * __h + content.hashCode();
        }
        __h = 5 * __h + rate;
        return __h;
    }

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        type.__write(__os);
        encoding.__write(__os);
        __os.writeString(content);
        __os.writeInt(rate);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        type = ElemT.__read(__is);
        encoding = EncodingT.__read(__is);
        content = __is.readString();
        rate = __is.readInt();
    }
}
