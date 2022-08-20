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

public interface _CMSInterfaceOperationsNC
{
    void ShiftService_async(AMD_CMSInterface_ShiftService __cb, long sessionID, ServiceTypeT targetService);

    CCODResultT CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info);

    CCODResultT Accept(long sessionID, int id, Ice.IntHolder mepID);

    CCODResultT Reject(long sessionID, int id);

    CCODResultT CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID);

    CCODResultT CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID);

    CCODResultT CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID);

    CCODResultT CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID);

    CCODResultT Disconnect(long sessionID, int id);

    CCODResultT Join(long sessionID, int id1, int id2, MediaDirectionT duplex);

    CCODResultT JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2);

    CCODResultT Unjoin(long sessionID, int id1, int id2);

    CCODResultT UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode);

    CCODResultT CreateConference(long sessionID, int maxUser, Ice.IntHolder confID);

    CCODResultT CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID);

    CCODResultT DestroyConference(long sessionID, int confID);

    CCODResultT CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID);

    CCODResultT DestroyMediaEndpoint(long sessionID, int mepID);

    CCODResultT PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys);

    CCODResultT AppendVideo(long sessionID, int id, VideoContentParamT[] contList);

    CCODResultT PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys);

    CCODResultT PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys);

    CCODResultT ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys);

    CCODResultT SendDTMF(long sessionID, int id, String DTMFString);

    CCODResultT RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys);

    CCODResultT SendFax(long sessionID, int id, String uri);

    CCODResultT ReceiveFax(long sessionID, int id, String uri);

    CCODResultT StopIO(long sessionID, int id);

    CCODResultT FlushDtmfBuffer(long sessionID, int id);

    CCODResultT SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding);

    CCODResultT GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data);

    CCODResultT ClearServiceData(long sessionID);

    void SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type);
}
