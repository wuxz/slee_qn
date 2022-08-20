// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.VGProxy;

public interface VGServicePrx extends Ice.ObjectPrx
{
    public int VGInitialize(int nDistributeMode, String szMonitorDNIS, String szReserved);
    public int VGInitialize(int nDistributeMode, String szMonitorDNIS, String szReserved, java.util.Map<String, String> __ctx);

    public int sgBlindMakeCall(String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID);
    public int sgBlindMakeCall(String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID, java.util.Map<String, String> __ctx);

    public int sgMakeCall(int nResID, String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nCallID);
    public int sgMakeCall(int nResID, String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nCallID, java.util.Map<String, String> __ctx);

    public int sgAnswerCall(int nResID, int nCallID);
    public int sgAnswerCall(int nResID, int nCallID, java.util.Map<String, String> __ctx);

    public int sgReleaseCall(int nResID, int nCallID);
    public int sgReleaseCall(int nResID, int nCallID, java.util.Map<String, String> __ctx);

    public int mgReceiveFax(int nResID, int nCallID, String szFileName);
    public int mgReceiveFax(int nResID, int nCallID, String szFileName, java.util.Map<String, String> __ctx);

    public int mgSendFax(int nResID, int nCallID, String szFileName);
    public int mgSendFax(int nResID, int nCallID, String szFileName, java.util.Map<String, String> __ctx);

    public int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount, String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey);
    public int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount, String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey, java.util.Map<String, String> __ctx);

    public int mgSendDTMF(int nResID, int nCallID, String szSignals);
    public int mgSendDTMF(int nResID, int nCallID, String szSignals, java.util.Map<String, String> __ctx);

    public int mgPlayVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nRate, int nBeginTime);
    public int mgPlayVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nRate, int nBeginTime, java.util.Map<String, String> __ctx);

    public int mgRecordVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nMaxTime, int nRate);
    public int mgRecordVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nMaxTime, int nRate, java.util.Map<String, String> __ctx);

    public int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys, int nType, String szText, int nVoiceLib, int nRate);
    public int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys, int nType, String szText, int nVoiceLib, int nRate, java.util.Map<String, String> __ctx);

    public int mgPlayList(int nResID, int nCallID, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib);
    public int mgPlayList(int nResID, int nCallID, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib, java.util.Map<String, String> __ctx);

    public int mgASR(int nResID, int nCallID, int nMaxKeys, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout, int nAcceptScore, int nResultNum, float fTimeoutBetweenWord, int nVoiceLib);
    public int mgASR(int nResID, int nCallID, int nMaxKeys, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout, int nAcceptScore, int nResultNum, float fTimeoutBetweenWord, int nVoiceLib, java.util.Map<String, String> __ctx);

    public int mgStopIO(int nResID, int nCallID);
    public int mgStopIO(int nResID, int nCallID, java.util.Map<String, String> __ctx);

    public int mgFlushBuffer(int nResID, int nCallID);
    public int mgFlushBuffer(int nResID, int nCallID, java.util.Map<String, String> __ctx);

    public int rmRouteToHoldMusic(int nResID, int nHoldMusicID);
    public int rmRouteToHoldMusic(int nResID, int nHoldMusicID, java.util.Map<String, String> __ctx);

    public int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID);
    public int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID, java.util.Map<String, String> __ctx);

    public int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode);
    public int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode, java.util.Map<String, String> __ctx);

    public int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode);
    public int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode, java.util.Map<String, String> __ctx);

    public int rmBlindCreateConf(int nResID1, int nResID2, Ice.IntHolder pnConfID);
    public int rmBlindCreateConf(int nResID1, int nResID2, Ice.IntHolder pnConfID, java.util.Map<String, String> __ctx);

    public int rmStartConferrenceRecord(int nResID, int nCallID, String strRecordFileName);
    public int rmStartConferrenceRecord(int nResID, int nCallID, String strRecordFileName, java.util.Map<String, String> __ctx);

    public int cnfCreateConf(int nMaxUserNum, int nVoxResType, int nVoxResBindType, Ice.IntHolder nConfID);
    public int cnfCreateConf(int nMaxUserNum, int nVoxResType, int nVoxResBindType, Ice.IntHolder nConfID, java.util.Map<String, String> __ctx);

    public int cnfCloseConf(int nConfID);
    public int cnfCloseConf(int nConfID, java.util.Map<String, String> __ctx);

    public int cnfJoinConf(int nResID, int nConfID, int nAttribute);
    public int cnfJoinConf(int nResID, int nConfID, int nAttribute, java.util.Map<String, String> __ctx);

    public int cnfLeaveConf(int nResID, int nConfID);
    public int cnfLeaveConf(int nResID, int nConfID, java.util.Map<String, String> __ctx);

    public int cnfConfPlay(int nConfID, String szFileName, int nSpeed, int nVolume, int nRate);
    public int cnfConfPlay(int nConfID, String szFileName, int nSpeed, int nVolume, int nRate, java.util.Map<String, String> __ctx);

    public int cnfConfRecord(int nConfID, String szFileName, int nRate);
    public int cnfConfRecord(int nConfID, String szFileName, int nRate, java.util.Map<String, String> __ctx);

    public int cnfStopConfPlay(int nConfID);
    public int cnfStopConfPlay(int nConfID, java.util.Map<String, String> __ctx);

    public int cnfStopConfRecord(int nConfID);
    public int cnfStopConfRecord(int nConfID, java.util.Map<String, String> __ctx);

    public int cnfSetUserAttrib(int nResID, int nAttribute);
    public int cnfSetUserAttrib(int nResID, int nAttribute, java.util.Map<String, String> __ctx);

    public int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute);
    public int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute, java.util.Map<String, String> __ctx);

    public int cnfMemberPlay(int nResID, String cszFileName, String szInteruptKeys, int nRate);
    public int cnfMemberPlay(int nResID, String cszFileName, String szInteruptKeys, int nRate, java.util.Map<String, String> __ctx);

    public int GetMgEvt(int nTimeout, MGEventHolder event);
    public int GetMgEvt(int nTimeout, MGEventHolder event, java.util.Map<String, String> __ctx);

    public int GetSgEvt(int nTimeout, SGEventHolder event);
    public int GetSgEvt(int nTimeout, SGEventHolder event, java.util.Map<String, String> __ctx);

    public int GetCnfEvt(int nTimeout, CnfEventHolder event);
    public int GetCnfEvt(int nTimeout, CnfEventHolder event, java.util.Map<String, String> __ctx);

    public int sgLimitBilling(int nResID, int nCallID, String sFilename, int nTimeout);
    public int sgLimitBilling(int nResID, int nCallID, String sFilename, int nTimeout, java.util.Map<String, String> __ctx);

    public int GetUVMGResNum(Ice.IntHolder ResTotal, Ice.IntHolder OutboundResTotal);
    public int GetUVMGResNum(Ice.IntHolder ResTotal, Ice.IntHolder OutboundResTotal, java.util.Map<String, String> __ctx);

    public int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib);
    public int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib, java.util.Map<String, String> __ctx);

    public int cnfPlayList(int nConfID, int nRate, int nFileCount, String[] szVoxFileNames, int nVoiceLib);
    public int cnfPlayList(int nConfID, int nRate, int nFileCount, String[] szVoxFileNames, int nVoiceLib, java.util.Map<String, String> __ctx);

    public int rmBlindCloseConf(int nConfID);
    public int rmBlindCloseConf(int nConfID, java.util.Map<String, String> __ctx);
}
