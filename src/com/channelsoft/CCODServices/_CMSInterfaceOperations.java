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

public interface _CMSInterfaceOperations
{
    void ShiftService_async(AMD_CMSInterface_ShiftService __cb, long sessionID, ServiceTypeT targetService, Ice.Current __current);

    CCODResultT CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info, Ice.Current __current);

    CCODResultT Accept(long sessionID, int id, Ice.IntHolder mepID, Ice.Current __current);

    CCODResultT Reject(long sessionID, int id, Ice.Current __current);

    CCODResultT CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, Ice.Current __current);

    CCODResultT CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, Ice.Current __current);

    CCODResultT CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, Ice.Current __current);

    CCODResultT CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, Ice.Current __current);

    CCODResultT Disconnect(long sessionID, int id, Ice.Current __current);

    CCODResultT Join(long sessionID, int id1, int id2, MediaDirectionT duplex, Ice.Current __current);

    CCODResultT JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2, Ice.Current __current);

    CCODResultT Unjoin(long sessionID, int id1, int id2, Ice.Current __current);

    CCODResultT UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode, Ice.Current __current);

    CCODResultT CreateConference(long sessionID, int maxUser, Ice.IntHolder confID, Ice.Current __current);

    CCODResultT CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID, Ice.Current __current);

    CCODResultT DestroyConference(long sessionID, int confID, Ice.Current __current);

    CCODResultT CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID, Ice.Current __current);

    CCODResultT DestroyMediaEndpoint(long sessionID, int mepID, Ice.Current __current);

    CCODResultT PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys, Ice.Current __current);

    CCODResultT AppendVideo(long sessionID, int id, VideoContentParamT[] contList, Ice.Current __current);

    CCODResultT PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys, Ice.Current __current);

    CCODResultT PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys, Ice.Current __current);

    CCODResultT ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys, Ice.Current __current);

    CCODResultT SendDTMF(long sessionID, int id, String DTMFString, Ice.Current __current);

    CCODResultT RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys, Ice.Current __current);

    CCODResultT SendFax(long sessionID, int id, String uri, Ice.Current __current);

    CCODResultT ReceiveFax(long sessionID, int id, String uri, Ice.Current __current);

    CCODResultT StopIO(long sessionID, int id, Ice.Current __current);

    CCODResultT FlushDtmfBuffer(long sessionID, int id, Ice.Current __current);

    CCODResultT SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding, Ice.Current __current);

    CCODResultT GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data, Ice.Current __current);

    CCODResultT ClearServiceData(long sessionID, Ice.Current __current);

    void SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type, Ice.Current __current);
}
