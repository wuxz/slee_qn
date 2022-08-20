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

public interface _VGServiceOperationsNC
{
    int VGInitialize(int nDistributeMode, String szMonitorDNIS, String szReserved);

    int sgBlindMakeCall(String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID);

    int sgMakeCall(int nResID, String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nCallID);

    int sgAnswerCall(int nResID, int nCallID);

    int sgReleaseCall(int nResID, int nCallID);

    int mgReceiveFax(int nResID, int nCallID, String szFileName);

    int mgSendFax(int nResID, int nCallID, String szFileName);

    int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount, String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey);

    int mgSendDTMF(int nResID, int nCallID, String szSignals);

    int mgPlayVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nRate, int nBeginTime);

    int mgRecordVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nMaxTime, int nRate);

    int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys, int nType, String szText, int nVoiceLib, int nRate);

    int mgPlayList(int nResID, int nCallID, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib);

    int mgASR(int nResID, int nCallID, int nMaxKeys, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout, int nAcceptScore, int nResultNum, float fTimeoutBetweenWord, int nVoiceLib);

    int mgStopIO(int nResID, int nCallID);

    int mgFlushBuffer(int nResID, int nCallID);

    int rmRouteToHoldMusic(int nResID, int nHoldMusicID);

    int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID);

    int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode);

    int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode);

    int rmBlindCreateConf(int nResID1, int nResID2, Ice.IntHolder pnConfID);

    int rmStartConferrenceRecord(int nResID, int nCallID, String strRecordFileName);

    int cnfCreateConf(int nMaxUserNum, int nVoxResType, int nVoxResBindType, Ice.IntHolder nConfID);

    int cnfCloseConf(int nConfID);

    int cnfJoinConf(int nResID, int nConfID, int nAttribute);

    int cnfLeaveConf(int nResID, int nConfID);

    int cnfConfPlay(int nConfID, String szFileName, int nSpeed, int nVolume, int nRate);

    int cnfConfRecord(int nConfID, String szFileName, int nRate);

    int cnfStopConfPlay(int nConfID);

    int cnfStopConfRecord(int nConfID);

    int cnfSetUserAttrib(int nResID, int nAttribute);

    int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute);

    int cnfMemberPlay(int nResID, String cszFileName, String szInteruptKeys, int nRate);

    int GetMgEvt(int nTimeout, MGEventHolder event);

    int GetSgEvt(int nTimeout, SGEventHolder event);

    int GetCnfEvt(int nTimeout, CnfEventHolder event);

    int sgLimitBilling(int nResID, int nCallID, String sFilename, int nTimeout);

    int GetUVMGResNum(Ice.IntHolder ResTotal, Ice.IntHolder OutboundResTotal);

    int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib);

    int cnfPlayList(int nConfID, int nRate, int nFileCount, String[] szVoxFileNames, int nVoiceLib);

    int rmBlindCloseConf(int nConfID);
}
