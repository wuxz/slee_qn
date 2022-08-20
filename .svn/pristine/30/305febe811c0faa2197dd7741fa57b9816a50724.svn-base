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

public final class VideoContentParamT implements java.lang.Cloneable
{
    public int infoID;

    public OperateTypeT opType;

    public ContentTypeT contType;

    public int cfgIndex;

    public String content;

    public VideoContentParamT()
    {
    }

    public VideoContentParamT(int infoID, OperateTypeT opType, ContentTypeT contType, int cfgIndex, String content)
    {
        this.infoID = infoID;
        this.opType = opType;
        this.contType = contType;
        this.cfgIndex = cfgIndex;
        this.content = content;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        VideoContentParamT _r = null;
        try
        {
            _r = (VideoContentParamT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(infoID != _r.infoID)
            {
                return false;
            }
            if(opType != _r.opType && opType != null && !opType.equals(_r.opType))
            {
                return false;
            }
            if(contType != _r.contType && contType != null && !contType.equals(_r.contType))
            {
                return false;
            }
            if(cfgIndex != _r.cfgIndex)
            {
                return false;
            }
            if(content != _r.content && content != null && !content.equals(_r.content))
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
        __h = 5 * __h + infoID;
        __h = 5 * __h + opType.hashCode();
        __h = 5 * __h + contType.hashCode();
        __h = 5 * __h + cfgIndex;
        if(content != null)
        {
            __h = 5 * __h + content.hashCode();
        }
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
        __os.writeInt(infoID);
        opType.__write(__os);
        contType.__write(__os);
        __os.writeInt(cfgIndex);
        __os.writeString(content);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        infoID = __is.readInt();
        opType = OperateTypeT.__read(__is);
        contType = ContentTypeT.__read(__is);
        cfgIndex = __is.readInt();
        content = __is.readString();
    }
}
