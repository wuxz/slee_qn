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

public final class _CMSInterfaceDelM extends Ice._ObjectDelM implements _CMSInterfaceDel
{
    public CCODResultT
    Accept(long sessionID, int id, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "Accept", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                mepID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    AppendVideo(long sessionID, int id, VideoContentParamT[] contList, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "AppendVideo", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                VideoContentParamListTHelper.write(__os, contList);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    ClearServiceData(long sessionID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "ClearServiceData", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateCall", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeString(src);
                __os.writeString(dst);
                __os.writeInt(joinID);
                duplex.__write(__os);
                __os.writeInt(timeout);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                connectionID.value = __is.readInt();
                mepID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateCallEx", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeString(src);
                __os.writeString(oriSrc);
                __os.writeString(dst);
                __os.writeString(oriDst);
                __os.writeString(strTrunkID);
                __os.writeInt(joinID);
                duplex.__write(__os);
                __os.writeInt(timeout);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                connectionID.value = __is.readInt();
                mepID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateCallExV", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeString(src);
                __os.writeString(oriSrc);
                __os.writeString(dst);
                __os.writeString(oriDst);
                __os.writeString(strTrunkID);
                __os.writeInt(joinID);
                duplex.__write(__os);
                __os.writeInt(timeout);
                mediaType.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                connectionID.value = __is.readInt();
                mepID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateCallV", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeString(src);
                __os.writeString(dst);
                __os.writeInt(joinID);
                duplex.__write(__os);
                __os.writeInt(timeout);
                mediaType.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                connectionID.value = __is.readInt();
                mepID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateConference(long sessionID, int maxUser, Ice.IntHolder confID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateConference", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(maxUser);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                confID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateConferenceV", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(maxUser);
                confMediaType.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                confID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateMediaEndpoint", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                mepID.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "CreateSession", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeString(enterpriseID);
                ServiceDataTHelper.write(__os, sessionData);
                type.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                info.value = new ServiceInfoT();
                info.value.__read(__is);
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    DestroyConference(long sessionID, int confID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "DestroyConference", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(confID);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    DestroyMediaEndpoint(long sessionID, int mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "DestroyMediaEndpoint", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(mepID);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    Disconnect(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "Disconnect", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    FlushDtmfBuffer(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "FlushDtmfBuffer", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "GetServiceData", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                encoding.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                data.value = ServiceDataTHelper.read(__is);
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    Join(long sessionID, int id1, int id2, MediaDirectionT duplex, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "Join", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id1);
                __os.writeInt(id2);
                duplex.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "JoinV", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id1);
                __os.writeInt(id2);
                duplex.__write(__os);
                __os.writeString(videoUrl1);
                __os.writeString(videoUrl2);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "PlayFile", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeString(uri);
                __os.writeInt(rate);
                __os.writeString(interruptKeys);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "PlayList", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                PlayListTHelper.write(__os, list);
                voiceLib.__write(__os);
                __os.writeString(interruptKeys);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "PlayTTS", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeString(text);
                encoding.__write(__os);
                voiceLib.__write(__os);
                __os.writeString(interruptKeys);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "ReceiveDTMF", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeInt(keyCount);
                __os.writeInt(nMax1stkeyTime);
                __os.writeInt(nTimeBetweenKey);
                __os.writeString(interruptKeys);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    ReceiveFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "ReceiveFax", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeString(uri);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "RecordVoice", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeString(uri);
                __os.writeInt(maxTime);
                __os.writeString(interruptKeys);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    Reject(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "Reject", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    SendDTMF(long sessionID, int id, String DTMFString, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "SendDTMF", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeString(DTMFString);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    SendFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "SendFax", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
                __os.writeString(uri);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public void
    SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "SetCallback", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                ident.__write(__os);
                __os.writeString(serviceName);
                type.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "SetServiceData", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                ServiceDataTHelper.write(__os, data);
                encoding.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "ShiftService", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                targetService.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                timestamp.value = __is.readInt();
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    StopIO(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "StopIO", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    Unjoin(long sessionID, int id1, int id2, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "Unjoin", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id1);
                __os.writeInt(id2);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }

    public CCODResultT
    UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        IceInternal.Outgoing __og = __connection.getOutgoing(__reference, "UnjoinV", Ice.OperationMode.Normal, __ctx, __compress);
        try
        {
            try
            {
                IceInternal.BasicStream __os = __og.os();
                __os.writeLong(sessionID);
                __os.writeInt(id1);
                __os.writeInt(id2);
                mode.__write(__os);
            }
            catch(Ice.LocalException __ex)
            {
                __og.abort(__ex);
            }
            boolean __ok = __og.invoke();
            try
            {
                IceInternal.BasicStream __is = __og.is();
                if(!__ok)
                {
                    try
                    {
                        __is.throwException();
                    }
                    catch(Ice.UserException __ex)
                    {
                        throw new Ice.UnknownUserException(__ex.ice_name());
                    }
                }
                CCODResultT __ret;
                __ret = CCODResultT.__read(__is);
                return __ret;
            }
            catch(Ice.LocalException __ex)
            {
                throw new IceInternal.LocalExceptionWrapper(__ex, false);
            }
        }
        finally
        {
            __connection.reclaimOutgoing(__og);
        }
    }
}
