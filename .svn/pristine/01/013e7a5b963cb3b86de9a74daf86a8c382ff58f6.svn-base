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

public interface _CMSInterfaceDel extends Ice._ObjectDel
{
    CCODResultT ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT Accept(long sessionID, int id, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT Reject(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT Disconnect(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT Join(long sessionID, int id1, int id2, MediaDirectionT duplex, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT Unjoin(long sessionID, int id1, int id2, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateConference(long sessionID, int maxUser, Ice.IntHolder confID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT DestroyConference(long sessionID, int confID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT DestroyMediaEndpoint(long sessionID, int mepID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT AppendVideo(long sessionID, int id, VideoContentParamT[] contList, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT SendDTMF(long sessionID, int id, String DTMFString, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT SendFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT ReceiveFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT StopIO(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT FlushDtmfBuffer(long sessionID, int id, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    CCODResultT ClearServiceData(long sessionID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
