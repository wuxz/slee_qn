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

public interface _VGServiceOperations
{
    int VGInitialize(int nDistributeMode, String szMonitorDNIS, String szReserved, Ice.Current __current);

    int sgBlindMakeCall(String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID, Ice.Current __current);

    int sgMakeCall(int nResID, String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nCallID, Ice.Current __current);

    int sgAnswerCall(int nResID, int nCallID, Ice.Current __current);

    int sgReleaseCall(int nResID, int nCallID, Ice.Current __current);

    int mgReceiveFax(int nResID, int nCallID, String szFileName, Ice.Current __current);

    int mgSendFax(int nResID, int nCallID, String szFileName, Ice.Current __current);

    int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount, String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey, Ice.Current __current);

    int mgSendDTMF(int nResID, int nCallID, String szSignals, Ice.Current __current);

    int mgPlayVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nRate, int nBeginTime, Ice.Current __current);

    int mgRecordVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nMaxTime, int nRate, Ice.Current __current);

    int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys, int nType, String szText, int nVoiceLib, int nRate, Ice.Current __current);

    int mgPlayList(int nResID, int nCallID, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib, Ice.Current __current);

    int mgASR(int nResID, int nCallID, int nMaxKeys, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout, int nAcceptScore, int nResultNum, float fTimeoutBetweenWord, int nVoiceLib, Ice.Current __current);

    int mgStopIO(int nResID, int nCallID, Ice.Current __current);

    int mgFlushBuffer(int nResID, int nCallID, Ice.Current __current);

    int rmRouteToHoldMusic(int nResID, int nHoldMusicID, Ice.Current __current);

    int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID, Ice.Current __current);

    int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode, Ice.Current __current);

    int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode, Ice.Current __current);

    int rmBlindCreateConf(int nResID1, int nResID2, Ice.IntHolder pnConfID, Ice.Current __current);

    int rmStartConferrenceRecord(int nResID, int nCallID, String strRecordFileName, Ice.Current __current);

    int cnfCreateConf(int nMaxUserNum, int nVoxResType, int nVoxResBindType, Ice.IntHolder nConfID, Ice.Current __current);

    int cnfCloseConf(int nConfID, Ice.Current __current);

    int cnfJoinConf(int nResID, int nConfID, int nAttribute, Ice.Current __current);

    int cnfLeaveConf(int nResID, int nConfID, Ice.Current __current);

    int cnfConfPlay(int nConfID, String szFileName, int nSpeed, int nVolume, int nRate, Ice.Current __current);

    int cnfConfRecord(int nConfID, String szFileName, int nRate, Ice.Current __current);

    int cnfStopConfPlay(int nConfID, Ice.Current __current);

    int cnfStopConfRecord(int nConfID, Ice.Current __current);

    int cnfSetUserAttrib(int nResID, int nAttribute, Ice.Current __current);

    int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute, Ice.Current __current);

    int cnfMemberPlay(int nResID, String cszFileName, String szInteruptKeys, int nRate, Ice.Current __current);

    int GetMgEvt(int nTimeout, MGEventHolder event, Ice.Current __current);

    int GetSgEvt(int nTimeout, SGEventHolder event, Ice.Current __current);

    int GetCnfEvt(int nTimeout, CnfEventHolder event, Ice.Current __current);

    int sgLimitBilling(int nResID, int nCallID, String sFilename, int nTimeout, Ice.Current __current);

    int GetUVMGResNum(Ice.IntHolder ResTotal, Ice.IntHolder OutboundResTotal, Ice.Current __current);

    int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib, Ice.Current __current);

    int cnfPlayList(int nConfID, int nRate, int nFileCount, String[] szVoxFileNames, int nVoiceLib, Ice.Current __current);

    int rmBlindCloseConf(int nConfID, Ice.Current __current);
}
