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

public interface _VGServiceDel extends Ice._ObjectDel
{
    int VGInitialize(int nDistributeMode, String szMonitorDNIS, String szReserved, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int sgBlindMakeCall(String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int sgMakeCall(int nResID, String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nCallID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int sgAnswerCall(int nResID, int nCallID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int sgReleaseCall(int nResID, int nCallID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgReceiveFax(int nResID, int nCallID, String szFileName, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgSendFax(int nResID, int nCallID, String szFileName, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount, String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgSendDTMF(int nResID, int nCallID, String szSignals, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgPlayVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nRate, int nBeginTime, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgRecordVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nMaxTime, int nRate, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys, int nType, String szText, int nVoiceLib, int nRate, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgPlayList(int nResID, int nCallID, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgASR(int nResID, int nCallID, int nMaxKeys, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout, int nAcceptScore, int nResultNum, float fTimeoutBetweenWord, int nVoiceLib, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgStopIO(int nResID, int nCallID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int mgFlushBuffer(int nResID, int nCallID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmRouteToHoldMusic(int nResID, int nHoldMusicID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmBlindCreateConf(int nResID1, int nResID2, Ice.IntHolder pnConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmStartConferrenceRecord(int nResID, int nCallID, String strRecordFileName, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfCreateConf(int nMaxUserNum, int nVoxResType, int nVoxResBindType, Ice.IntHolder nConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfCloseConf(int nConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfJoinConf(int nResID, int nConfID, int nAttribute, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfLeaveConf(int nResID, int nConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfConfPlay(int nConfID, String szFileName, int nSpeed, int nVolume, int nRate, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfConfRecord(int nConfID, String szFileName, int nRate, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfStopConfPlay(int nConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfStopConfRecord(int nConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfSetUserAttrib(int nResID, int nAttribute, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfMemberPlay(int nResID, String cszFileName, String szInteruptKeys, int nRate, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int GetMgEvt(int nTimeout, MGEventHolder event, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int GetSgEvt(int nTimeout, SGEventHolder event, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int GetCnfEvt(int nTimeout, CnfEventHolder event, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int sgLimitBilling(int nResID, int nCallID, String sFilename, int nTimeout, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int GetUVMGResNum(Ice.IntHolder ResTotal, Ice.IntHolder OutboundResTotal, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int cnfPlayList(int nConfID, int nRate, int nFileCount, String[] szVoxFileNames, int nVoiceLib, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    int rmBlindCloseConf(int nConfID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
