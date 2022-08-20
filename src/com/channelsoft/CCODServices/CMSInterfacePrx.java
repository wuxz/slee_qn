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

public interface CMSInterfacePrx extends Ice.ObjectPrx
{
    public CCODResultT ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp);
    public CCODResultT ShiftService(long sessionID, ServiceTypeT targetService, Ice.IntHolder timestamp, java.util.Map<String, String> __ctx);

    public CCODResultT CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info);
    public CCODResultT CreateSession(String enterpriseID, java.util.Map<java.lang.String, java.lang.String> sessionData, ServiceTypeT type, ServiceInfoTHolder info, java.util.Map<String, String> __ctx);

    public CCODResultT Accept(long sessionID, int id, Ice.IntHolder mepID);
    public CCODResultT Accept(long sessionID, int id, Ice.IntHolder mepID, java.util.Map<String, String> __ctx);

    public CCODResultT Reject(long sessionID, int id);
    public CCODResultT Reject(long sessionID, int id, java.util.Map<String, String> __ctx);

    public CCODResultT CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID);
    public CCODResultT CreateCall(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx);

    public CCODResultT CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID);
    public CCODResultT CreateCallV(long sessionID, String src, String dst, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx);

    public CCODResultT CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID);
    public CCODResultT CreateCallEx(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx);

    public CCODResultT CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID);
    public CCODResultT CreateCallExV(long sessionID, String src, String oriSrc, String dst, String oriDst, String strTrunkID, int joinID, MediaDirectionT duplex, int timeout, MediaTypeT mediaType, Ice.IntHolder connectionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx);

    public CCODResultT Disconnect(long sessionID, int id);
    public CCODResultT Disconnect(long sessionID, int id, java.util.Map<String, String> __ctx);

    public CCODResultT Join(long sessionID, int id1, int id2, MediaDirectionT duplex);
    public CCODResultT Join(long sessionID, int id1, int id2, MediaDirectionT duplex, java.util.Map<String, String> __ctx);

    public CCODResultT JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2);
    public CCODResultT JoinV(long sessionID, int id1, int id2, MediaDirectionT duplex, String videoUrl1, String videoUrl2, java.util.Map<String, String> __ctx);

    public CCODResultT Unjoin(long sessionID, int id1, int id2);
    public CCODResultT Unjoin(long sessionID, int id1, int id2, java.util.Map<String, String> __ctx);

    public CCODResultT UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode);
    public CCODResultT UnjoinV(long sessionID, int id1, int id2, MediaDirectionT mode, java.util.Map<String, String> __ctx);

    public CCODResultT CreateConference(long sessionID, int maxUser, Ice.IntHolder confID);
    public CCODResultT CreateConference(long sessionID, int maxUser, Ice.IntHolder confID, java.util.Map<String, String> __ctx);

    public CCODResultT CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID);
    public CCODResultT CreateConferenceV(long sessionID, int maxUser, MediaTypeT confMediaType, Ice.IntHolder confID, java.util.Map<String, String> __ctx);

    public CCODResultT DestroyConference(long sessionID, int confID);
    public CCODResultT DestroyConference(long sessionID, int confID, java.util.Map<String, String> __ctx);

    public CCODResultT CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID);
    public CCODResultT CreateMediaEndpoint(long sessionID, Ice.IntHolder mepID, java.util.Map<String, String> __ctx);

    public CCODResultT DestroyMediaEndpoint(long sessionID, int mepID);
    public CCODResultT DestroyMediaEndpoint(long sessionID, int mepID, java.util.Map<String, String> __ctx);

    public CCODResultT PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys);
    public CCODResultT PlayFile(long sessionID, int id, String uri, int rate, String interruptKeys, java.util.Map<String, String> __ctx);

    public CCODResultT AppendVideo(long sessionID, int id, VideoContentParamT[] contList);
    public CCODResultT AppendVideo(long sessionID, int id, VideoContentParamT[] contList, java.util.Map<String, String> __ctx);

    public CCODResultT PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys);
    public CCODResultT PlayTTS(long sessionID, int id, String text, EncodingT encoding, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx);

    public CCODResultT PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys);
    public CCODResultT PlayList(long sessionID, int id, PlayListElemT[] list, TTSVoiceLibT voiceLib, String interruptKeys, java.util.Map<String, String> __ctx);

    public CCODResultT ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys);
    public CCODResultT ReceiveDTMF(long sessionID, int id, int keyCount, int nMax1stkeyTime, int nTimeBetweenKey, String interruptKeys, java.util.Map<String, String> __ctx);

    public CCODResultT SendDTMF(long sessionID, int id, String DTMFString);
    public CCODResultT SendDTMF(long sessionID, int id, String DTMFString, java.util.Map<String, String> __ctx);

    public CCODResultT RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys);
    public CCODResultT RecordVoice(long sessionID, int id, String uri, int maxTime, String interruptKeys, java.util.Map<String, String> __ctx);

    public CCODResultT SendFax(long sessionID, int id, String uri);
    public CCODResultT SendFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx);

    public CCODResultT ReceiveFax(long sessionID, int id, String uri);
    public CCODResultT ReceiveFax(long sessionID, int id, String uri, java.util.Map<String, String> __ctx);

    public CCODResultT StopIO(long sessionID, int id);
    public CCODResultT StopIO(long sessionID, int id, java.util.Map<String, String> __ctx);

    public CCODResultT FlushDtmfBuffer(long sessionID, int id);
    public CCODResultT FlushDtmfBuffer(long sessionID, int id, java.util.Map<String, String> __ctx);

    public CCODResultT SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding);
    public CCODResultT SetServiceData(long sessionID, java.util.Map<java.lang.String, java.lang.String> data, EncodingT encoding, java.util.Map<String, String> __ctx);

    public CCODResultT GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data);
    public CCODResultT GetServiceData(long sessionID, EncodingT encoding, ServiceDataTHolder data, java.util.Map<String, String> __ctx);

    public CCODResultT ClearServiceData(long sessionID);
    public CCODResultT ClearServiceData(long sessionID, java.util.Map<String, String> __ctx);

    public void SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type);
    public void SetCallback(Ice.Identity ident, String serviceName, ServiceTypeT type, java.util.Map<String, String> __ctx);
}
