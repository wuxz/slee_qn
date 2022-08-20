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

public final class ConnectionT implements java.lang.Cloneable
{
    public int connectionID;

    public String localUri;

    public String remoteUri;

    public String origLocalUri;

    public String origRemoteUri;

    public ProtocolT protocol;

    public OriginatorT originator;

    public ConnectionStateT state;

    public int input;

    public int[] ouputs;

    public ConnectionT()
    {
    }

    public ConnectionT(int connectionID, String localUri, String remoteUri, String origLocalUri, String origRemoteUri, ProtocolT protocol, OriginatorT originator, ConnectionStateT state, int input, int[] ouputs)
    {
        this.connectionID = connectionID;
        this.localUri = localUri;
        this.remoteUri = remoteUri;
        this.origLocalUri = origLocalUri;
        this.origRemoteUri = origRemoteUri;
        this.protocol = protocol;
        this.originator = originator;
        this.state = state;
        this.input = input;
        this.ouputs = ouputs;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ConnectionT _r = null;
        try
        {
            _r = (ConnectionT)rhs;
        }
        catch(ClassCastException ex)
        {
        }

        if(_r != null)
        {
            if(connectionID != _r.connectionID)
            {
                return false;
            }
            if(localUri != _r.localUri && localUri != null && !localUri.equals(_r.localUri))
            {
                return false;
            }
            if(remoteUri != _r.remoteUri && remoteUri != null && !remoteUri.equals(_r.remoteUri))
            {
                return false;
            }
            if(origLocalUri != _r.origLocalUri && origLocalUri != null && !origLocalUri.equals(_r.origLocalUri))
            {
                return false;
            }
            if(origRemoteUri != _r.origRemoteUri && origRemoteUri != null && !origRemoteUri.equals(_r.origRemoteUri))
            {
                return false;
            }
            if(protocol != _r.protocol && protocol != null && !protocol.equals(_r.protocol))
            {
                return false;
            }
            if(originator != _r.originator && originator != null && !originator.equals(_r.originator))
            {
                return false;
            }
            if(state != _r.state && state != null && !state.equals(_r.state))
            {
                return false;
            }
            if(input != _r.input)
            {
                return false;
            }
            if(!java.util.Arrays.equals(ouputs, _r.ouputs))
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
        __h = 5 * __h + connectionID;
        if(localUri != null)
        {
            __h = 5 * __h + localUri.hashCode();
        }
        if(remoteUri != null)
        {
            __h = 5 * __h + remoteUri.hashCode();
        }
        if(origLocalUri != null)
        {
            __h = 5 * __h + origLocalUri.hashCode();
        }
        if(origRemoteUri != null)
        {
            __h = 5 * __h + origRemoteUri.hashCode();
        }
        __h = 5 * __h + protocol.hashCode();
        __h = 5 * __h + originator.hashCode();
        __h = 5 * __h + state.hashCode();
        __h = 5 * __h + input;
        if(ouputs != null)
        {
            for(int __i0 = 0; __i0 < ouputs.length; __i0++)
            {
                __h = 5 * __h + ouputs[__i0];
            }
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
        __os.writeInt(connectionID);
        __os.writeString(localUri);
        __os.writeString(remoteUri);
        __os.writeString(origLocalUri);
        __os.writeString(origRemoteUri);
        protocol.__write(__os);
        originator.__write(__os);
        state.__write(__os);
        __os.writeInt(input);
        OutputListTHelper.write(__os, ouputs);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        connectionID = __is.readInt();
        localUri = __is.readString();
        remoteUri = __is.readString();
        origLocalUri = __is.readString();
        origRemoteUri = __is.readString();
        protocol = ProtocolT.__read(__is);
        originator = OriginatorT.__read(__is);
        state = ConnectionStateT.__read(__is);
        input = __is.readInt();
        ouputs = OutputListTHelper.read(__is);
    }
}
