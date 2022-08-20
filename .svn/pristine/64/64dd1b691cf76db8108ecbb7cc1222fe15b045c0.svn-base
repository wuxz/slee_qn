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

public abstract class _CMSInterfaceDisp extends Ice.ObjectImpl implements CMSInterface
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::CCODServices::CMSInterface",
        "::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[0];
    }

    public static String
    ice_staticId()
    {
        return __ids[0];
    }

    public final CCODResultT
    Accept(long sessionID, int id, Ice.IntHolder mepID)
    {
        return Accept(sessionID, id, mepID, null);
    }

    public final CCODResultT
    AppendVideo(long sessionID, int id, VideoContentParamT[] contList)
    {
        return AppendVideo(sessionID, id, contList, null);
    }

    public final CCODResultT
    ClearServiceData(long sessionID)
    {
        return ClearServiceData(sessionID, null);
    }

    public final CCODResultT
    CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCall(sessionID, src, dst, joinID, duplex, timeout, connectionID, mepID, null);
    }

    public final CCODResultT
    CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCallEx(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, connectionID, mepID, null);
    }

    public final CCODResultT
    CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCallExV(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, mediaType, connectionID, mepID, null);
    }

    public final CCODResultT
    CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID)
    {
        return CreateCallV(sessionID, src, dst, joinID, duplex, timeout, mediaType, connectionID, mepID, null);
    }

    public final CCODResultT
    CreateConference(long sessionID, int maxUser, Ice.IntHolder confID)
    {
        return CreateConference(sessionID, maxUser, confID, null);
    }

    public final CCODResultT
    CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID)
    {
        return CreateConferenceV(sessionID, maxUser, confMediaType, confID, null);
    }

    public final CCODResultT
    CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID)
    {
        return CreateMediaEndpoint(sessionID, mepID, null);
    }

    public final CCODResultT
    CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info)
    {
        return CreateSession(enterpriseID, sessionData, type, info, null);
    }

    public final CCODResultT
    DestroyConference(long sessionID, int confID)
    {
        return DestroyConference(sessionID, confID, null);
    }

    public final CCODResultT
    DestroyMediaEndpoint(long sessionID, int mepID)
    {
        return DestroyMediaEndpoint(sessionID, mepID, null);
    }

    public final CCODResultT
    Disconnect(long sessionID, int id)
    {
        return Disconnect(sessionID, id, null);
    }

    public final CCODResultT
    FlushDtmfBuffer(long sessionID, int id)
    {
        return FlushDtmfBuffer(sessionID, id, null);
    }

    public final CCODResultT
    GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data)
    {
        return GetServiceData(sessionID, encoding, data, null);
    }

    public final CCODResultT
    Join(long sessionID, int id1, int id2, MediaDirectionT duplex)
    {
        return Join(sessionID, id1, id2, duplex, null);
    }

    public final CCODResultT
    JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2)
    {
        return JoinV(sessionID, id1, id2, duplex, videoUrl1, videoUrl2, null);
    }

    public final CCODResultT
    PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys)
    {
        return PlayFile(sessionID, id, uri, rate, interruptKeys, null);
    }

    public final CCODResultT
    PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys)
    {
        return PlayList(sessionID, id, list, voiceLib, interruptKeys, null);
    }

    public final CCODResultT
    PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys)
    {
        return PlayTTS(sessionID, id, text, encoding, voiceLib, interruptKeys, null);
    }

    public final CCODResultT
    ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys)
    {
        return ReceiveDTMF(sessionID, id, keyCount, nMax1stkeyTime, nTimeBetweenKey, interruptKeys, null);
    }

    public final CCODResultT
    ReceiveFax(long sessionID, int id, String uri)
    {
        return ReceiveFax(sessionID, id, uri, null);
    }

    public final CCODResultT
    RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys)
    {
        return RecordVoice(sessionID, id, uri, maxTime, interruptKeys, null);
    }

    public final CCODResultT
    Reject(long sessionID, int id)
    {
        return Reject(sessionID, id, null);
    }

    public final CCODResultT
    SendDTMF(long sessionID, int id, String DTMFString)
    {
        return SendDTMF(sessionID, id, DTMFString, null);
    }

    public final CCODResultT
    SendFax(long sessionID, int id, String uri)
    {
        return SendFax(sessionID, id, uri, null);
    }

    public final void
    SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type)
    {
        SetCallback(ident, serviceName, type, null);
    }

    public final CCODResultT
    SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding)
    {
        return SetServiceData(sessionID, data, encoding, null);
    }

    public final void
    ShiftService_async(AMD_CMSInterface_ShiftService __cb, long sessionID, ServiceTypeT targetService)
    {
        ShiftService_async(__cb, sessionID, targetService, null);
    }

    public final CCODResultT
    StopIO(long sessionID, int id)
    {
        return StopIO(sessionID, id, null);
    }

    public final CCODResultT
    Unjoin(long sessionID, int id1, int id2)
    {
        return Unjoin(sessionID, id1, id2, null);
    }

    public final CCODResultT
    UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode)
    {
        return UnjoinV(sessionID, id1, id2, mode, null);
    }

    public static IceInternal.DispatchStatus
    ___ShiftService(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        long sessionID;
        sessionID = __is.readLong();
        ServiceTypeT targetService;
        targetService = ServiceTypeT.__read(__is);
        AMD_CMSInterface_ShiftService __cb = new _AMD_CMSInterface_ShiftService(__inS);
        try
        {
            __obj.ShiftService_async(__cb, sessionID, targetService, __current);
        }
        catch(java.lang.Exception ex)
        {
            __cb.ice_exception(ex);
        }
        return IceInternal.DispatchStatus.DispatchAsync;
    }

    public static IceInternal.DispatchStatus
    ___CreateSession(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String enterpriseID;
        enterpriseID = __is.readString();
        java.util.Map<java.lang.String, java.lang.String> sessionData;
        sessionData = ServiceDataTHelper.read(__is);
        ServiceTypeT type;
        type = ServiceTypeT.__read(__is);
        ServiceInfoTHolder info = new ServiceInfoTHolder();
        CCODResultT __ret = __obj.CreateSession(enterpriseID, sessionData, type, info, __current);
        info.value.__write(__os);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___Accept(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        Ice.IntHolder mepID = new Ice.IntHolder();
        CCODResultT __ret = __obj.Accept(sessionID, id, mepID, __current);
        __os.writeInt(mepID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___Reject(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        CCODResultT __ret = __obj.Reject(sessionID, id, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateCall(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        String src;
        src = __is.readString();
        String dst;
        dst = __is.readString();
        int joinID;
        joinID = __is.readInt();
        MediaDirectionT duplex;
        duplex = MediaDirectionT.__read(__is);
        int timeout;
        timeout = __is.readInt();
        Ice.IntHolder connectionID = new Ice.IntHolder();
        Ice.IntHolder mepID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateCall(sessionID, src, dst, joinID, duplex, timeout, connectionID, mepID, __current);
        __os.writeInt(connectionID.value);
        __os.writeInt(mepID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateCallV(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        String src;
        src = __is.readString();
        String dst;
        dst = __is.readString();
        int joinID;
        joinID = __is.readInt();
        MediaDirectionT duplex;
        duplex = MediaDirectionT.__read(__is);
        int timeout;
        timeout = __is.readInt();
        MediaTypeT mediaType;
        mediaType = MediaTypeT.__read(__is);
        Ice.IntHolder connectionID = new Ice.IntHolder();
        Ice.IntHolder mepID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateCallV(sessionID, src, dst, joinID, duplex, timeout, mediaType, connectionID, mepID, __current);
        __os.writeInt(connectionID.value);
        __os.writeInt(mepID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateCallEx(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        String src;
        src = __is.readString();
        String oriSrc;
        oriSrc = __is.readString();
        String dst;
        dst = __is.readString();
        String oriDst;
        oriDst = __is.readString();
        String strTrunkID;
        strTrunkID = __is.readString();
        int joinID;
        joinID = __is.readInt();
        MediaDirectionT duplex;
        duplex = MediaDirectionT.__read(__is);
        int timeout;
        timeout = __is.readInt();
        Ice.IntHolder connectionID = new Ice.IntHolder();
        Ice.IntHolder mepID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateCallEx(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, connectionID, mepID, __current);
        __os.writeInt(connectionID.value);
        __os.writeInt(mepID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateCallExV(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        String src;
        src = __is.readString();
        String oriSrc;
        oriSrc = __is.readString();
        String dst;
        dst = __is.readString();
        String oriDst;
        oriDst = __is.readString();
        String strTrunkID;
        strTrunkID = __is.readString();
        int joinID;
        joinID = __is.readInt();
        MediaDirectionT duplex;
        duplex = MediaDirectionT.__read(__is);
        int timeout;
        timeout = __is.readInt();
        MediaTypeT mediaType;
        mediaType = MediaTypeT.__read(__is);
        Ice.IntHolder connectionID = new Ice.IntHolder();
        Ice.IntHolder mepID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateCallExV(sessionID, src, oriSrc, dst, oriDst, strTrunkID, joinID, duplex, timeout, mediaType, connectionID, mepID, __current);
        __os.writeInt(connectionID.value);
        __os.writeInt(mepID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___Disconnect(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        CCODResultT __ret = __obj.Disconnect(sessionID, id, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___Join(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id1;
        id1 = __is.readInt();
        int id2;
        id2 = __is.readInt();
        MediaDirectionT duplex;
        duplex = MediaDirectionT.__read(__is);
        CCODResultT __ret = __obj.Join(sessionID, id1, id2, duplex, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___JoinV(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id1;
        id1 = __is.readInt();
        int id2;
        id2 = __is.readInt();
        MediaDirectionT duplex;
        duplex = MediaDirectionT.__read(__is);
        String videoUrl1;
        videoUrl1 = __is.readString();
        String videoUrl2;
        videoUrl2 = __is.readString();
        CCODResultT __ret = __obj.JoinV(sessionID, id1, id2, duplex, videoUrl1, videoUrl2, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___Unjoin(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id1;
        id1 = __is.readInt();
        int id2;
        id2 = __is.readInt();
        CCODResultT __ret = __obj.Unjoin(sessionID, id1, id2, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___UnjoinV(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id1;
        id1 = __is.readInt();
        int id2;
        id2 = __is.readInt();
        MediaDirectionT mode;
        mode = MediaDirectionT.__read(__is);
        CCODResultT __ret = __obj.UnjoinV(sessionID, id1, id2, mode, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateConference(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int maxUser;
        maxUser = __is.readInt();
        Ice.IntHolder confID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateConference(sessionID, maxUser, confID, __current);
        __os.writeInt(confID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateConferenceV(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int maxUser;
        maxUser = __is.readInt();
        MediaTypeT confMediaType;
        confMediaType = MediaTypeT.__read(__is);
        Ice.IntHolder confID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateConferenceV(sessionID, maxUser, confMediaType, confID, __current);
        __os.writeInt(confID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___DestroyConference(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int confID;
        confID = __is.readInt();
        CCODResultT __ret = __obj.DestroyConference(sessionID, confID, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___CreateMediaEndpoint(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        Ice.IntHolder mepID = new Ice.IntHolder();
        CCODResultT __ret = __obj.CreateMediaEndpoint(sessionID, mepID, __current);
        __os.writeInt(mepID.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___DestroyMediaEndpoint(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int mepID;
        mepID = __is.readInt();
        CCODResultT __ret = __obj.DestroyMediaEndpoint(sessionID, mepID, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___PlayFile(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        String uri;
        uri = __is.readString();
        int rate;
        rate = __is.readInt();
        String interruptKeys;
        interruptKeys = __is.readString();
        CCODResultT __ret = __obj.PlayFile(sessionID, id, uri, rate, interruptKeys, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___AppendVideo(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        VideoContentParamT[] contList;
        contList = VideoContentParamListTHelper.read(__is);
        CCODResultT __ret = __obj.AppendVideo(sessionID, id, contList, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___PlayTTS(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        String text;
        text = __is.readString();
        EncodingT encoding;
        encoding = EncodingT.__read(__is);
        TTSVoiceLibT voiceLib;
        voiceLib = TTSVoiceLibT.__read(__is);
        String interruptKeys;
        interruptKeys = __is.readString();
        CCODResultT __ret = __obj.PlayTTS(sessionID, id, text, encoding, voiceLib, interruptKeys, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___PlayList(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        PlayListElemT[] list;
        list = PlayListTHelper.read(__is);
        TTSVoiceLibT voiceLib;
        voiceLib = TTSVoiceLibT.__read(__is);
        String interruptKeys;
        interruptKeys = __is.readString();
        CCODResultT __ret = __obj.PlayList(sessionID, id, list, voiceLib, interruptKeys, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___ReceiveDTMF(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        int keyCount;
        keyCount = __is.readInt();
        int nMax1stkeyTime;
        nMax1stkeyTime = __is.readInt();
        int nTimeBetweenKey;
        nTimeBetweenKey = __is.readInt();
        String interruptKeys;
        interruptKeys = __is.readString();
        CCODResultT __ret = __obj.ReceiveDTMF(sessionID, id, keyCount, nMax1stkeyTime, nTimeBetweenKey, interruptKeys, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SendDTMF(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        String DTMFString;
        DTMFString = __is.readString();
        CCODResultT __ret = __obj.SendDTMF(sessionID, id, DTMFString, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___RecordVoice(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        String uri;
        uri = __is.readString();
        int maxTime;
        maxTime = __is.readInt();
        String interruptKeys;
        interruptKeys = __is.readString();
        CCODResultT __ret = __obj.RecordVoice(sessionID, id, uri, maxTime, interruptKeys, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SendFax(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        String uri;
        uri = __is.readString();
        CCODResultT __ret = __obj.SendFax(sessionID, id, uri, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___ReceiveFax(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        String uri;
        uri = __is.readString();
        CCODResultT __ret = __obj.ReceiveFax(sessionID, id, uri, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___StopIO(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        CCODResultT __ret = __obj.StopIO(sessionID, id, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___FlushDtmfBuffer(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        int id;
        id = __is.readInt();
        CCODResultT __ret = __obj.FlushDtmfBuffer(sessionID, id, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SetServiceData(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        java.util.Map<java.lang.String, java.lang.String> data;
        data = ServiceDataTHelper.read(__is);
        EncodingT encoding;
        encoding = EncodingT.__read(__is);
        CCODResultT __ret = __obj.SetServiceData(sessionID, data, encoding, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetServiceData(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        EncodingT encoding;
        encoding = EncodingT.__read(__is);
        ServiceDataTHolder data = new ServiceDataTHolder();
        CCODResultT __ret = __obj.GetServiceData(sessionID, encoding, data, __current);
        ServiceDataTHelper.write(__os, data.value);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___ClearServiceData(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        long sessionID;
        sessionID = __is.readLong();
        CCODResultT __ret = __obj.ClearServiceData(sessionID, __current);
        __ret.__write(__os);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___SetCallback(CMSInterface __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        Ice.Identity ident;
        ident = new Ice.Identity();
        ident.__read(__is);
        String serviceName;
        serviceName = __is.readString();
        ServiceTypeT type;
        type = ServiceTypeT.__read(__is);
        __obj.SetCallback(ident, serviceName, type, __current);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "Accept",
        "AppendVideo",
        "ClearServiceData",
        "CreateCall",
        "CreateCallEx",
        "CreateCallExV",
        "CreateCallV",
        "CreateConference",
        "CreateConferenceV",
        "CreateMediaEndpoint",
        "CreateSession",
        "DestroyConference",
        "DestroyMediaEndpoint",
        "Disconnect",
        "FlushDtmfBuffer",
        "GetServiceData",
        "Join",
        "JoinV",
        "PlayFile",
        "PlayList",
        "PlayTTS",
        "ReceiveDTMF",
        "ReceiveFax",
        "RecordVoice",
        "Reject",
        "SendDTMF",
        "SendFax",
        "SetCallback",
        "SetServiceData",
        "ShiftService",
        "StopIO",
        "Unjoin",
        "UnjoinV",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public IceInternal.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            return IceInternal.DispatchStatus.DispatchOperationNotExist;
        }

        switch(pos)
        {
            case 0:
            {
                return ___Accept(this, in, __current);
            }
            case 1:
            {
                return ___AppendVideo(this, in, __current);
            }
            case 2:
            {
                return ___ClearServiceData(this, in, __current);
            }
            case 3:
            {
                return ___CreateCall(this, in, __current);
            }
            case 4:
            {
                return ___CreateCallEx(this, in, __current);
            }
            case 5:
            {
                return ___CreateCallExV(this, in, __current);
            }
            case 6:
            {
                return ___CreateCallV(this, in, __current);
            }
            case 7:
            {
                return ___CreateConference(this, in, __current);
            }
            case 8:
            {
                return ___CreateConferenceV(this, in, __current);
            }
            case 9:
            {
                return ___CreateMediaEndpoint(this, in, __current);
            }
            case 10:
            {
                return ___CreateSession(this, in, __current);
            }
            case 11:
            {
                return ___DestroyConference(this, in, __current);
            }
            case 12:
            {
                return ___DestroyMediaEndpoint(this, in, __current);
            }
            case 13:
            {
                return ___Disconnect(this, in, __current);
            }
            case 14:
            {
                return ___FlushDtmfBuffer(this, in, __current);
            }
            case 15:
            {
                return ___GetServiceData(this, in, __current);
            }
            case 16:
            {
                return ___Join(this, in, __current);
            }
            case 17:
            {
                return ___JoinV(this, in, __current);
            }
            case 18:
            {
                return ___PlayFile(this, in, __current);
            }
            case 19:
            {
                return ___PlayList(this, in, __current);
            }
            case 20:
            {
                return ___PlayTTS(this, in, __current);
            }
            case 21:
            {
                return ___ReceiveDTMF(this, in, __current);
            }
            case 22:
            {
                return ___ReceiveFax(this, in, __current);
            }
            case 23:
            {
                return ___RecordVoice(this, in, __current);
            }
            case 24:
            {
                return ___Reject(this, in, __current);
            }
            case 25:
            {
                return ___SendDTMF(this, in, __current);
            }
            case 26:
            {
                return ___SendFax(this, in, __current);
            }
            case 27:
            {
                return ___SetCallback(this, in, __current);
            }
            case 28:
            {
                return ___SetServiceData(this, in, __current);
            }
            case 29:
            {
                return ___ShiftService(this, in, __current);
            }
            case 30:
            {
                return ___StopIO(this, in, __current);
            }
            case 31:
            {
                return ___Unjoin(this, in, __current);
            }
            case 32:
            {
                return ___UnjoinV(this, in, __current);
            }
            case 33:
            {
                return ___ice_id(this, in, __current);
            }
            case 34:
            {
                return ___ice_ids(this, in, __current);
            }
            case 35:
            {
                return ___ice_isA(this, in, __current);
            }
            case 36:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
