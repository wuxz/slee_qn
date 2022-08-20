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

public final class CMSInterfacePrxHelper extends Ice.ObjectPrxHelperBase implements CMSInterfacePrx
{
    public CCODResultT
    Accept(long sessionID, int id, Ice.IntHolder mepID)
    {
        return Accept(sessionID, id, mepID, null, false);
    }

    public CCODResultT
    Accept(long sessionID, int id, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
    {
        return Accept(sessionID, id, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    Accept(long sessionID, int id, Ice.IntHolder mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("Accept");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.Accept(sessionID, id, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    AppendVideo(long sessionID, int id, VideoContentParamT[] contList)
    {
        return AppendVideo(sessionID, id, contList, null, false);
    }

    public CCODResultT
    AppendVideo(long sessionID, int id, VideoContentParamT[] contList, java.util.Map<String, String> __ctx)
    {
        return AppendVideo(sessionID, id, contList, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    AppendVideo(long sessionID, int id, VideoContentParamT[] contList, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("AppendVideo");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.AppendVideo(sessionID, id, contList, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    ClearServiceData(long sessionID)
    {
        return ClearServiceData(sessionID, null, false);
    }

    public CCODResultT
    ClearServiceData(long sessionID, java.util.Map<String, String> __ctx)
    {
        return ClearServiceData(sessionID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    ClearServiceData(long sessionID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("ClearServiceData");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.ClearServiceData(sessionID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCall(sessionID, src, dst, joinID, duplex, timeout, connectionID, mepID, null, false);
    }

    public CCODResultT
    CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
    {
        return CreateCall(sessionID, src, dst, joinID, duplex, timeout, connectionID, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateCall");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateCall(sessionID, src, dst, joinID, duplex, timeout, connectionID, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCallEx(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, connectionID, mepID, null, false);
    }

    public CCODResultT
    CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
    {
        return CreateCallEx(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, connectionID, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateCallEx");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateCallEx(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, connectionID, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCallExV(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, mediaType, connectionID, mepID, null, false);
    }

    public CCODResultT
    CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
    {
        return CreateCallExV(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, mediaType, connectionID, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateCallExV");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateCallExV(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, mediaType, connectionID, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCallV(sessionID, src, dst, joinID, duplex, timeout, mediaType, connectionID, mepID, null, false);
    }

    public CCODResultT
    CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
    {
        return CreateCallV(sessionID, src, dst, joinID, duplex, timeout, mediaType, connectionID, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateCallV");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateCallV(sessionID, src, dst, joinID, duplex, timeout, mediaType, connectionID, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateConference(long sessionID, int maxUser, Ice.IntHolder confID)
    {
        return CreateConference(sessionID, maxUser, confID, null, false);
    }

    public CCODResultT
    CreateConference(long sessionID, int maxUser, Ice.IntHolder confID, java.util.Map<String, String> __ctx)
    {
        return CreateConference(sessionID, maxUser, confID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateConference(long sessionID, int maxUser, Ice.IntHolder confID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateConference");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateConference(sessionID, maxUser, confID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID)
    {
        return CreateConferenceV(sessionID, maxUser, confMediaType, confID, null, false);
    }

    public CCODResultT
    CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID, java.util.Map<String, String> __ctx)
    {
        return CreateConferenceV(sessionID, maxUser, confMediaType, confID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateConferenceV");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateConferenceV(sessionID, maxUser, confMediaType, confID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID)
    {
        return CreateMediaEndpoint(sessionID, mepID, null, false);
    }

    public CCODResultT
    CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
    {
        return CreateMediaEndpoint(sessionID, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateMediaEndpoint");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateMediaEndpoint(sessionID, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info)
    {
        return CreateSession(enterpriseID, sessionData, type, info, null, false);
    }

    public CCODResultT
    CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info, java.util.Map<String, String> __ctx)
    {
        return CreateSession(enterpriseID, sessionData, type, info, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("CreateSession");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.CreateSession(enterpriseID, sessionData, type, info, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    DestroyConference(long sessionID, int confID)
    {
        return DestroyConference(sessionID, confID, null, false);
    }

    public CCODResultT
    DestroyConference(long sessionID, int confID, java.util.Map<String, String> __ctx)
    {
        return DestroyConference(sessionID, confID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    DestroyConference(long sessionID, int confID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("DestroyConference");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.DestroyConference(sessionID, confID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    DestroyMediaEndpoint(long sessionID, int mepID)
    {
        return DestroyMediaEndpoint(sessionID, mepID, null, false);
    }

    public CCODResultT
    DestroyMediaEndpoint(long sessionID, int mepID, java.util.Map<String, String> __ctx)
    {
        return DestroyMediaEndpoint(sessionID, mepID, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    DestroyMediaEndpoint(long sessionID, int mepID, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("DestroyMediaEndpoint");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.DestroyMediaEndpoint(sessionID, mepID, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    Disconnect(long sessionID, int id)
    {
        return Disconnect(sessionID, id, null, false);
    }

    public CCODResultT
    Disconnect(long sessionID, int id, java.util.Map<String, String> __ctx)
    {
        return Disconnect(sessionID, id, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    Disconnect(long sessionID, int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("Disconnect");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.Disconnect(sessionID, id, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    FlushDtmfBuffer(long sessionID, int id)
    {
        return FlushDtmfBuffer(sessionID, id, null, false);
    }

    public CCODResultT
    FlushDtmfBuffer(long sessionID, int id, java.util.Map<String, String> __ctx)
    {
        return FlushDtmfBuffer(sessionID, id, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    FlushDtmfBuffer(long sessionID, int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("FlushDtmfBuffer");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.FlushDtmfBuffer(sessionID, id, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data)
    {
        return GetServiceData(sessionID, encoding, data, null, false);
    }

    public CCODResultT
    GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data, java.util.Map<String, String> __ctx)
    {
        return GetServiceData(sessionID, encoding, data, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("GetServiceData");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.GetServiceData(sessionID, encoding, data, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    Join(long sessionID, int id1, int id2, MediaDirectionT duplex)
    {
        return Join(sessionID, id1, id2, duplex, null, false);
    }

    public CCODResultT
    Join(long sessionID, int id1, int id2, MediaDirectionT duplex, java.util.Map<String, String> __ctx)
    {
        return Join(sessionID, id1, id2, duplex, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    Join(long sessionID, int id1, int id2, MediaDirectionT duplex, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("Join");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.Join(sessionID, id1, id2, duplex, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2)
    {
        return JoinV(sessionID, id1, id2, duplex, videoUrl1, videoUrl2, null, false);
    }

    public CCODResultT
    JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2, java.util.Map<String, String> __ctx)
    {
        return JoinV(sessionID, id1, id2, duplex, videoUrl1, videoUrl2, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("JoinV");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.JoinV(sessionID, id1, id2, duplex, videoUrl1, videoUrl2, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys)
    {
        return PlayFile(sessionID, id, uri, rate, interruptKeys, null, false);
    }

    public CCODResultT
    PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys, java.util.Map<String, String> __ctx)
    {
        return PlayFile(sessionID, id, uri, rate, interruptKeys, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("PlayFile");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.PlayFile(sessionID, id, uri, rate, interruptKeys, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys)
    {
        return PlayList(sessionID, id, list, voiceLib, interruptKeys, null, false);
    }

    public CCODResultT
    PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx)
    {
        return PlayList(sessionID, id, list, voiceLib, interruptKeys, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("PlayList");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.PlayList(sessionID, id, list, voiceLib, interruptKeys, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys)
    {
        return PlayTTS(sessionID, id, text, encoding, voiceLib, interruptKeys, null, false);
    }

    public CCODResultT
    PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx)
    {
        return PlayTTS(sessionID, id, text, encoding, voiceLib, interruptKeys, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("PlayTTS");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.PlayTTS(sessionID, id, text, encoding, voiceLib, interruptKeys, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys)
    {
        return ReceiveDTMF(sessionID, id, keyCount, nMax1stkeyTime, nTimeBetweenKey, interruptKeys, null, false);
    }

    public CCODResultT
    ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys, java.util.Map<String, String> __ctx)
    {
        return ReceiveDTMF(sessionID, id, keyCount, nMax1stkeyTime, nTimeBetweenKey, interruptKeys, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("ReceiveDTMF");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.ReceiveDTMF(sessionID, id, keyCount, nMax1stkeyTime, nTimeBetweenKey, interruptKeys, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    ReceiveFax(long sessionID, int id, String uri)
    {
        return ReceiveFax(sessionID, id, uri, null, false);
    }

    public CCODResultT
    ReceiveFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx)
    {
        return ReceiveFax(sessionID, id, uri, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    ReceiveFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("ReceiveFax");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.ReceiveFax(sessionID, id, uri, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys)
    {
        return RecordVoice(sessionID, id, uri, maxTime, interruptKeys, null, false);
    }

    public CCODResultT
    RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys, java.util.Map<String, String> __ctx)
    {
        return RecordVoice(sessionID, id, uri, maxTime, interruptKeys, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("RecordVoice");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.RecordVoice(sessionID, id, uri, maxTime, interruptKeys, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    Reject(long sessionID, int id)
    {
        return Reject(sessionID, id, null, false);
    }

    public CCODResultT
    Reject(long sessionID, int id, java.util.Map<String, String> __ctx)
    {
        return Reject(sessionID, id, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    Reject(long sessionID, int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("Reject");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.Reject(sessionID, id, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    SendDTMF(long sessionID, int id, String DTMFString)
    {
        return SendDTMF(sessionID, id, DTMFString, null, false);
    }

    public CCODResultT
    SendDTMF(long sessionID, int id, String DTMFString, java.util.Map<String, String> __ctx)
    {
        return SendDTMF(sessionID, id, DTMFString, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    SendDTMF(long sessionID, int id, String DTMFString, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("SendDTMF");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.SendDTMF(sessionID, id, DTMFString, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    SendFax(long sessionID, int id, String uri)
    {
        return SendFax(sessionID, id, uri, null, false);
    }

    public CCODResultT
    SendFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx)
    {
        return SendFax(sessionID, id, uri, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    SendFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("SendFax");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.SendFax(sessionID, id, uri, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public void
    SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type)
    {
        SetCallback(ident, serviceName, type, null, false);
    }

    public void
    SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type, java.util.Map<String, String> __ctx)
    {
        SetCallback(ident, serviceName, type, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                __del.SetCallback(ident, serviceName, type, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding)
    {
        return SetServiceData(sessionID, data, encoding, null, false);
    }

    public CCODResultT
    SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding, java.util.Map<String, String> __ctx)
    {
        return SetServiceData(sessionID, data, encoding, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("SetServiceData");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.SetServiceData(sessionID, data, encoding, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp)
    {
        return ShiftService(sessionID, targetService, timestamp, null, false);
    }

    public CCODResultT
    ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp, java.util.Map<String, String> __ctx)
    {
        return ShiftService(sessionID, targetService, timestamp, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("ShiftService");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.ShiftService(sessionID, targetService, timestamp, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    StopIO(long sessionID, int id)
    {
        return StopIO(sessionID, id, null, false);
    }

    public CCODResultT
    StopIO(long sessionID, int id, java.util.Map<String, String> __ctx)
    {
        return StopIO(sessionID, id, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    StopIO(long sessionID, int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("StopIO");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.StopIO(sessionID, id, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    Unjoin(long sessionID, int id1, int id2)
    {
        return Unjoin(sessionID, id1, id2, null, false);
    }

    public CCODResultT
    Unjoin(long sessionID, int id1, int id2, java.util.Map<String, String> __ctx)
    {
        return Unjoin(sessionID, id1, id2, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    Unjoin(long sessionID, int id1, int id2, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("Unjoin");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.Unjoin(sessionID, id1, id2, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public CCODResultT
    UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode)
    {
        return UnjoinV(sessionID, id1, id2, mode, null, false);
    }

    public CCODResultT
    UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode, java.util.Map<String, String> __ctx)
    {
        return UnjoinV(sessionID, id1, id2, mode, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private CCODResultT
    UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("UnjoinV");
                __delBase = __getDelegate();
                _CMSInterfaceDel __del = (_CMSInterfaceDel)__delBase;
                return __del.UnjoinV(sessionID, id1, id2, mode, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public static CMSInterfacePrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        CMSInterfacePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (CMSInterfacePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::CCODServices::CMSInterface"))
                {
                    CMSInterfacePrxHelper __h = new CMSInterfacePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static CMSInterfacePrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        CMSInterfacePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (CMSInterfacePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::CCODServices::CMSInterface", __ctx))
                {
                    CMSInterfacePrxHelper __h = new CMSInterfacePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static CMSInterfacePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        CMSInterfacePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::CCODServices::CMSInterface"))
                {
                    CMSInterfacePrxHelper __h = new CMSInterfacePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static CMSInterfacePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        CMSInterfacePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::CCODServices::CMSInterface", __ctx))
                {
                    CMSInterfacePrxHelper __h = new CMSInterfacePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static CMSInterfacePrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        CMSInterfacePrx __d = null;
        if(__obj != null)
        {
            CMSInterfacePrxHelper __h = new CMSInterfacePrxHelper();
            __h.__copyFrom(__obj);
            __d = __h;
        }
        return __d;
    }

    public static CMSInterfacePrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        CMSInterfacePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            CMSInterfacePrxHelper __h = new CMSInterfacePrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _CMSInterfaceDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _CMSInterfaceDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, CMSInterfacePrx v)
    {
        __os.writeProxy(v);
    }

    public static CMSInterfacePrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            CMSInterfacePrxHelper result = new CMSInterfacePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
