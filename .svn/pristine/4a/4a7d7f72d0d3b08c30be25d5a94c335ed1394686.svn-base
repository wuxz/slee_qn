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

public abstract class _VGServiceDisp extends Ice.ObjectImpl implements VGService
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::VGProxy::VGService"
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
        return __ids[1];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String
    ice_staticId()
    {
        return __ids[1];
    }

    public final int
    GetCnfEvt(int nTimeout, CnfEventHolder event)
    {
        return GetCnfEvt(nTimeout, event, null);
    }

    public final int
    GetMgEvt(int nTimeout, MGEventHolder event)
    {
        return GetMgEvt(nTimeout, event, null);
    }

    public final int
    GetSgEvt(int nTimeout, SGEventHolder event)
    {
        return GetSgEvt(nTimeout, event, null);
    }

    public final int
    GetUVMGResNum(Ice.IntHolder ResTotal, Ice.IntHolder OutboundResTotal)
    {
        return GetUVMGResNum(ResTotal, OutboundResTotal, null);
    }

    public final int
    VGInitialize(int nDistributeMode, String szMonitorDNIS, String szReserved)
    {
        return VGInitialize(nDistributeMode, szMonitorDNIS, szReserved, null);
    }

    public final int
    cnfCloseConf(int nConfID)
    {
        return cnfCloseConf(nConfID, null);
    }

    public final int
    cnfConfPlay(int nConfID, String szFileName, int nSpeed, int nVolume, int nRate)
    {
        return cnfConfPlay(nConfID, szFileName, nSpeed, nVolume, nRate, null);
    }

    public final int
    cnfConfRecord(int nConfID, String szFileName, int nRate)
    {
        return cnfConfRecord(nConfID, szFileName, nRate, null);
    }

    public final int
    cnfCreateConf(int nMaxUserNum, int nVoxResType, int nVoxResBindType, Ice.IntHolder nConfID)
    {
        return cnfCreateConf(nMaxUserNum, nVoxResType, nVoxResBindType, nConfID, null);
    }

    public final int
    cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute)
    {
        return cnfGetUserAttrib(nResID, nAttribute, null);
    }

    public final int
    cnfJoinConf(int nResID, int nConfID, int nAttribute)
    {
        return cnfJoinConf(nResID, nConfID, nAttribute, null);
    }

    public final int
    cnfLeaveConf(int nResID, int nConfID)
    {
        return cnfLeaveConf(nResID, nConfID, null);
    }

    public final int
    cnfMemberPlay(int nResID, String cszFileName, String szInteruptKeys, int nRate)
    {
        return cnfMemberPlay(nResID, cszFileName, szInteruptKeys, nRate, null);
    }

    public final int
    cnfPlayList(int nConfID, int nRate, int nFileCount, String[] szVoxFileNames, int nVoiceLib)
    {
        return cnfPlayList(nConfID, nRate, nFileCount, szVoxFileNames, nVoiceLib, null);
    }

    public final int
    cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib)
    {
        return cnfPlayTTS(nConfID, nType, szText, nVoiceLib, null);
    }

    public final int
    cnfSetUserAttrib(int nResID, int nAttribute)
    {
        return cnfSetUserAttrib(nResID, nAttribute, null);
    }

    public final int
    cnfStopConfPlay(int nConfID)
    {
        return cnfStopConfPlay(nConfID, null);
    }

    public final int
    cnfStopConfRecord(int nConfID)
    {
        return cnfStopConfRecord(nConfID, null);
    }

    public final int
    mgASR(int nResID, int nCallID, int nMaxKeys, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout, int nAcceptScore, int nResultNum, float fTimeoutBetweenWord, int nVoiceLib)
    {
        return mgASR(nResID, nCallID, nMaxKeys, szInteruptKeys, nRate, nFileCount, szVoxFileList, szGrammar, nNoSpeechTimeout, nAcceptScore, nResultNum, fTimeoutBetweenWord, nVoiceLib, null);
    }

    public final int
    mgFlushBuffer(int nResID, int nCallID)
    {
        return mgFlushBuffer(nResID, nCallID, null);
    }

    public final int
    mgPlayList(int nResID, int nCallID, String szInteruptKeys, int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib)
    {
        return mgPlayList(nResID, nCallID, szInteruptKeys, nRate, nFileCount, szVoxFileList, nVoiceLib, null);
    }

    public final int
    mgPlayTTS(int nResID, int nCallID, String szInteruptKeys, int nType, String szText, int nVoiceLib, int nRate)
    {
        return mgPlayTTS(nResID, nCallID, szInteruptKeys, nType, szText, nVoiceLib, nRate, null);
    }

    public final int
    mgPlayVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nRate, int nBeginTime)
    {
        return mgPlayVoice(nResID, nCallID, szFileName, szInteruptKeys, nRate, nBeginTime, null);
    }

    public final int
    mgReceiveDTMF(int nResID, int nCallID, int nKeyCount, String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey)
    {
        return mgReceiveDTMF(nResID, nCallID, nKeyCount, szInteruptKeys, nMax1stkeyTime, nTimeBetweenKey, null);
    }

    public final int
    mgReceiveFax(int nResID, int nCallID, String szFileName)
    {
        return mgReceiveFax(nResID, nCallID, szFileName, null);
    }

    public final int
    mgRecordVoice(int nResID, int nCallID, String szFileName, String szInteruptKeys, int nMaxTime, int nRate)
    {
        return mgRecordVoice(nResID, nCallID, szFileName, szInteruptKeys, nMaxTime, nRate, null);
    }

    public final int
    mgSendDTMF(int nResID, int nCallID, String szSignals)
    {
        return mgSendDTMF(nResID, nCallID, szSignals, null);
    }

    public final int
    mgSendFax(int nResID, int nCallID, String szFileName)
    {
        return mgSendFax(nResID, nCallID, szFileName, null);
    }

    public final int
    mgStopIO(int nResID, int nCallID)
    {
        return mgStopIO(nResID, nCallID, null);
    }

    public final int
    rmBlindCloseConf(int nConfID)
    {
        return rmBlindCloseConf(nConfID, null);
    }

    public final int
    rmBlindCreateConf(int nResID1, int nResID2, Ice.IntHolder pnConfID)
    {
        return rmBlindCreateConf(nResID1, nResID2, pnConfID, null);
    }

    public final int
    rmRouteToHoldMusic(int nResID, int nHoldMusicID)
    {
        return rmRouteToHoldMusic(nResID, nHoldMusicID, null);
    }

    public final int
    rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode)
    {
        return rmRouteTwoRes(nRes1ID, nRes2ID, mode, null);
    }

    public final int
    rmStartConferrenceRecord(int nResID, int nCallID, String strRecordFileName)
    {
        return rmStartConferrenceRecord(nResID, nCallID, strRecordFileName, null);
    }

    public final int
    rmUnRouteToHoldMusic(int nResID, int nHoldMusicID)
    {
        return rmUnRouteToHoldMusic(nResID, nHoldMusicID, null);
    }

    public final int
    rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode)
    {
        return rmUnrouteTwoRes(nRes1ID, nRes2ID, mode, null);
    }

    public final int
    sgAnswerCall(int nResID, int nCallID)
    {
        return sgAnswerCall(nResID, nCallID, null);
    }

    public final int
    sgBlindMakeCall(String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID)
    {
        return sgBlindMakeCall(szCallerNum, szCalledNum, nTimeout, nResID, nCallID, null);
    }

    public final int
    sgLimitBilling(int nResID, int nCallID, String sFilename, int nTimeout)
    {
        return sgLimitBilling(nResID, nCallID, sFilename, nTimeout, null);
    }

    public final int
    sgMakeCall(int nResID, String szCallerNum, String szCalledNum, int nTimeout, Ice.IntHolder nCallID)
    {
        return sgMakeCall(nResID, szCallerNum, szCalledNum, nTimeout, nCallID, null);
    }

    public final int
    sgReleaseCall(int nResID, int nCallID)
    {
        return sgReleaseCall(nResID, nCallID, null);
    }

    public static IceInternal.DispatchStatus
    ___VGInitialize(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nDistributeMode;
        nDistributeMode = __is.readInt();
        String szMonitorDNIS;
        szMonitorDNIS = __is.readString();
        String szReserved;
        szReserved = __is.readString();
        int __ret = __obj.VGInitialize(nDistributeMode, szMonitorDNIS, szReserved, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___sgBlindMakeCall(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        String szCallerNum;
        szCallerNum = __is.readString();
        String szCalledNum;
        szCalledNum = __is.readString();
        int nTimeout;
        nTimeout = __is.readInt();
        Ice.IntHolder nResID = new Ice.IntHolder();
        Ice.IntHolder nCallID = new Ice.IntHolder();
        int __ret = __obj.sgBlindMakeCall(szCallerNum, szCalledNum, nTimeout, nResID, nCallID, __current);
        __os.writeInt(nResID.value);
        __os.writeInt(nCallID.value);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___sgMakeCall(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        String szCallerNum;
        szCallerNum = __is.readString();
        String szCalledNum;
        szCalledNum = __is.readString();
        int nTimeout;
        nTimeout = __is.readInt();
        Ice.IntHolder nCallID = new Ice.IntHolder();
        int __ret = __obj.sgMakeCall(nResID, szCallerNum, szCalledNum, nTimeout, nCallID, __current);
        __os.writeInt(nCallID.value);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___sgAnswerCall(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        int __ret = __obj.sgAnswerCall(nResID, nCallID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___sgReleaseCall(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        int __ret = __obj.sgReleaseCall(nResID, nCallID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgReceiveFax(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szFileName;
        szFileName = __is.readString();
        int __ret = __obj.mgReceiveFax(nResID, nCallID, szFileName, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgSendFax(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szFileName;
        szFileName = __is.readString();
        int __ret = __obj.mgSendFax(nResID, nCallID, szFileName, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgReceiveDTMF(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        int nKeyCount;
        nKeyCount = __is.readInt();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nMax1stkeyTime;
        nMax1stkeyTime = __is.readInt();
        int nTimeBetweenKey;
        nTimeBetweenKey = __is.readInt();
        int __ret = __obj.mgReceiveDTMF(nResID, nCallID, nKeyCount, szInteruptKeys, nMax1stkeyTime, nTimeBetweenKey, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgSendDTMF(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szSignals;
        szSignals = __is.readString();
        int __ret = __obj.mgSendDTMF(nResID, nCallID, szSignals, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgPlayVoice(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szFileName;
        szFileName = __is.readString();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nRate;
        nRate = __is.readInt();
        int nBeginTime;
        nBeginTime = __is.readInt();
        int __ret = __obj.mgPlayVoice(nResID, nCallID, szFileName, szInteruptKeys, nRate, nBeginTime, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgRecordVoice(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szFileName;
        szFileName = __is.readString();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nMaxTime;
        nMaxTime = __is.readInt();
        int nRate;
        nRate = __is.readInt();
        int __ret = __obj.mgRecordVoice(nResID, nCallID, szFileName, szInteruptKeys, nMaxTime, nRate, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgPlayTTS(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nType;
        nType = __is.readInt();
        String szText;
        szText = __is.readString();
        int nVoiceLib;
        nVoiceLib = __is.readInt();
        int nRate;
        nRate = __is.readInt();
        int __ret = __obj.mgPlayTTS(nResID, nCallID, szInteruptKeys, nType, szText, nVoiceLib, nRate, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgPlayList(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nRate;
        nRate = __is.readInt();
        int nFileCount;
        nFileCount = __is.readInt();
        String[] szVoxFileList;
        szVoxFileList = strSequenceHelper.read(__is);
        int nVoiceLib;
        nVoiceLib = __is.readInt();
        int __ret = __obj.mgPlayList(nResID, nCallID, szInteruptKeys, nRate, nFileCount, szVoxFileList, nVoiceLib, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgASR(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        int nMaxKeys;
        nMaxKeys = __is.readInt();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nRate;
        nRate = __is.readInt();
        int nFileCount;
        nFileCount = __is.readInt();
        String[] szVoxFileList;
        szVoxFileList = strSequenceHelper.read(__is);
        String szGrammar;
        szGrammar = __is.readString();
        int nNoSpeechTimeout;
        nNoSpeechTimeout = __is.readInt();
        int nAcceptScore;
        nAcceptScore = __is.readInt();
        int nResultNum;
        nResultNum = __is.readInt();
        float fTimeoutBetweenWord;
        fTimeoutBetweenWord = __is.readFloat();
        int nVoiceLib;
        nVoiceLib = __is.readInt();
        int __ret = __obj.mgASR(nResID, nCallID, nMaxKeys, szInteruptKeys, nRate, nFileCount, szVoxFileList, szGrammar, nNoSpeechTimeout, nAcceptScore, nResultNum, fTimeoutBetweenWord, nVoiceLib, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgStopIO(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        int __ret = __obj.mgStopIO(nResID, nCallID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___mgFlushBuffer(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        int __ret = __obj.mgFlushBuffer(nResID, nCallID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmRouteToHoldMusic(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nHoldMusicID;
        nHoldMusicID = __is.readInt();
        int __ret = __obj.rmRouteToHoldMusic(nResID, nHoldMusicID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmUnRouteToHoldMusic(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nHoldMusicID;
        nHoldMusicID = __is.readInt();
        int __ret = __obj.rmUnRouteToHoldMusic(nResID, nHoldMusicID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmRouteTwoRes(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nRes1ID;
        nRes1ID = __is.readInt();
        int nRes2ID;
        nRes2ID = __is.readInt();
        int mode;
        mode = __is.readInt();
        int __ret = __obj.rmRouteTwoRes(nRes1ID, nRes2ID, mode, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmUnrouteTwoRes(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nRes1ID;
        nRes1ID = __is.readInt();
        int nRes2ID;
        nRes2ID = __is.readInt();
        int mode;
        mode = __is.readInt();
        int __ret = __obj.rmUnrouteTwoRes(nRes1ID, nRes2ID, mode, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmBlindCreateConf(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID1;
        nResID1 = __is.readInt();
        int nResID2;
        nResID2 = __is.readInt();
        Ice.IntHolder pnConfID = new Ice.IntHolder();
        int __ret = __obj.rmBlindCreateConf(nResID1, nResID2, pnConfID, __current);
        __os.writeInt(pnConfID.value);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmStartConferrenceRecord(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String strRecordFileName;
        strRecordFileName = __is.readString();
        int __ret = __obj.rmStartConferrenceRecord(nResID, nCallID, strRecordFileName, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfCreateConf(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nMaxUserNum;
        nMaxUserNum = __is.readInt();
        int nVoxResType;
        nVoxResType = __is.readInt();
        int nVoxResBindType;
        nVoxResBindType = __is.readInt();
        Ice.IntHolder nConfID = new Ice.IntHolder();
        int __ret = __obj.cnfCreateConf(nMaxUserNum, nVoxResType, nVoxResBindType, nConfID, __current);
        __os.writeInt(nConfID.value);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfCloseConf(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        int __ret = __obj.cnfCloseConf(nConfID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfJoinConf(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nConfID;
        nConfID = __is.readInt();
        int nAttribute;
        nAttribute = __is.readInt();
        int __ret = __obj.cnfJoinConf(nResID, nConfID, nAttribute, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfLeaveConf(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nConfID;
        nConfID = __is.readInt();
        int __ret = __obj.cnfLeaveConf(nResID, nConfID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfConfPlay(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        String szFileName;
        szFileName = __is.readString();
        int nSpeed;
        nSpeed = __is.readInt();
        int nVolume;
        nVolume = __is.readInt();
        int nRate;
        nRate = __is.readInt();
        int __ret = __obj.cnfConfPlay(nConfID, szFileName, nSpeed, nVolume, nRate, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfConfRecord(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        String szFileName;
        szFileName = __is.readString();
        int nRate;
        nRate = __is.readInt();
        int __ret = __obj.cnfConfRecord(nConfID, szFileName, nRate, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfStopConfPlay(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        int __ret = __obj.cnfStopConfPlay(nConfID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfStopConfRecord(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        int __ret = __obj.cnfStopConfRecord(nConfID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfSetUserAttrib(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nAttribute;
        nAttribute = __is.readInt();
        int __ret = __obj.cnfSetUserAttrib(nResID, nAttribute, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfGetUserAttrib(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        Ice.IntHolder nAttribute = new Ice.IntHolder();
        int __ret = __obj.cnfGetUserAttrib(nResID, nAttribute, __current);
        __os.writeInt(nAttribute.value);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfMemberPlay(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        String cszFileName;
        cszFileName = __is.readString();
        String szInteruptKeys;
        szInteruptKeys = __is.readString();
        int nRate;
        nRate = __is.readInt();
        int __ret = __obj.cnfMemberPlay(nResID, cszFileName, szInteruptKeys, nRate, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetMgEvt(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nTimeout;
        nTimeout = __is.readInt();
        MGEventHolder event = new MGEventHolder();
        int __ret = __obj.GetMgEvt(nTimeout, event, __current);
        event.value.__write(__os);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetSgEvt(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nTimeout;
        nTimeout = __is.readInt();
        SGEventHolder event = new SGEventHolder();
        int __ret = __obj.GetSgEvt(nTimeout, event, __current);
        event.value.__write(__os);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetCnfEvt(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nTimeout;
        nTimeout = __is.readInt();
        CnfEventHolder event = new CnfEventHolder();
        int __ret = __obj.GetCnfEvt(nTimeout, event, __current);
        event.value.__write(__os);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___sgLimitBilling(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nResID;
        nResID = __is.readInt();
        int nCallID;
        nCallID = __is.readInt();
        String sFilename;
        sFilename = __is.readString();
        int nTimeout;
        nTimeout = __is.readInt();
        int __ret = __obj.sgLimitBilling(nResID, nCallID, sFilename, nTimeout, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___GetUVMGResNum(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __os = __inS.os();
        Ice.IntHolder ResTotal = new Ice.IntHolder();
        Ice.IntHolder OutboundResTotal = new Ice.IntHolder();
        int __ret = __obj.GetUVMGResNum(ResTotal, OutboundResTotal, __current);
        __os.writeInt(ResTotal.value);
        __os.writeInt(OutboundResTotal.value);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfPlayTTS(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        int nType;
        nType = __is.readInt();
        String szText;
        szText = __is.readString();
        int nVoiceLib;
        nVoiceLib = __is.readInt();
        int __ret = __obj.cnfPlayTTS(nConfID, nType, szText, nVoiceLib, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___cnfPlayList(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        int nRate;
        nRate = __is.readInt();
        int nFileCount;
        nFileCount = __is.readInt();
        String[] szVoxFileNames;
        szVoxFileNames = strSequenceHelper.read(__is);
        int nVoiceLib;
        nVoiceLib = __is.readInt();
        int __ret = __obj.cnfPlayList(nConfID, nRate, nFileCount, szVoxFileNames, nVoiceLib, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___rmBlindCloseConf(VGService __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        IceInternal.BasicStream __os = __inS.os();
        int nConfID;
        nConfID = __is.readInt();
        int __ret = __obj.rmBlindCloseConf(nConfID, __current);
        __os.writeInt(__ret);
        return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "GetCnfEvt",
        "GetMgEvt",
        "GetSgEvt",
        "GetUVMGResNum",
        "VGInitialize",
        "cnfCloseConf",
        "cnfConfPlay",
        "cnfConfRecord",
        "cnfCreateConf",
        "cnfGetUserAttrib",
        "cnfJoinConf",
        "cnfLeaveConf",
        "cnfMemberPlay",
        "cnfPlayList",
        "cnfPlayTTS",
        "cnfSetUserAttrib",
        "cnfStopConfPlay",
        "cnfStopConfRecord",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "mgASR",
        "mgFlushBuffer",
        "mgPlayList",
        "mgPlayTTS",
        "mgPlayVoice",
        "mgReceiveDTMF",
        "mgReceiveFax",
        "mgRecordVoice",
        "mgSendDTMF",
        "mgSendFax",
        "mgStopIO",
        "rmBlindCloseConf",
        "rmBlindCreateConf",
        "rmRouteToHoldMusic",
        "rmRouteTwoRes",
        "rmStartConferrenceRecord",
        "rmUnRouteToHoldMusic",
        "rmUnrouteTwoRes",
        "sgAnswerCall",
        "sgBlindMakeCall",
        "sgLimitBilling",
        "sgMakeCall",
        "sgReleaseCall"
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
                return ___GetCnfEvt(this, in, __current);
            }
            case 1:
            {
                return ___GetMgEvt(this, in, __current);
            }
            case 2:
            {
                return ___GetSgEvt(this, in, __current);
            }
            case 3:
            {
                return ___GetUVMGResNum(this, in, __current);
            }
            case 4:
            {
                return ___VGInitialize(this, in, __current);
            }
            case 5:
            {
                return ___cnfCloseConf(this, in, __current);
            }
            case 6:
            {
                return ___cnfConfPlay(this, in, __current);
            }
            case 7:
            {
                return ___cnfConfRecord(this, in, __current);
            }
            case 8:
            {
                return ___cnfCreateConf(this, in, __current);
            }
            case 9:
            {
                return ___cnfGetUserAttrib(this, in, __current);
            }
            case 10:
            {
                return ___cnfJoinConf(this, in, __current);
            }
            case 11:
            {
                return ___cnfLeaveConf(this, in, __current);
            }
            case 12:
            {
                return ___cnfMemberPlay(this, in, __current);
            }
            case 13:
            {
                return ___cnfPlayList(this, in, __current);
            }
            case 14:
            {
                return ___cnfPlayTTS(this, in, __current);
            }
            case 15:
            {
                return ___cnfSetUserAttrib(this, in, __current);
            }
            case 16:
            {
                return ___cnfStopConfPlay(this, in, __current);
            }
            case 17:
            {
                return ___cnfStopConfRecord(this, in, __current);
            }
            case 18:
            {
                return ___ice_id(this, in, __current);
            }
            case 19:
            {
                return ___ice_ids(this, in, __current);
            }
            case 20:
            {
                return ___ice_isA(this, in, __current);
            }
            case 21:
            {
                return ___ice_ping(this, in, __current);
            }
            case 22:
            {
                return ___mgASR(this, in, __current);
            }
            case 23:
            {
                return ___mgFlushBuffer(this, in, __current);
            }
            case 24:
            {
                return ___mgPlayList(this, in, __current);
            }
            case 25:
            {
                return ___mgPlayTTS(this, in, __current);
            }
            case 26:
            {
                return ___mgPlayVoice(this, in, __current);
            }
            case 27:
            {
                return ___mgReceiveDTMF(this, in, __current);
            }
            case 28:
            {
                return ___mgReceiveFax(this, in, __current);
            }
            case 29:
            {
                return ___mgRecordVoice(this, in, __current);
            }
            case 30:
            {
                return ___mgSendDTMF(this, in, __current);
            }
            case 31:
            {
                return ___mgSendFax(this, in, __current);
            }
            case 32:
            {
                return ___mgStopIO(this, in, __current);
            }
            case 33:
            {
                return ___rmBlindCloseConf(this, in, __current);
            }
            case 34:
            {
                return ___rmBlindCreateConf(this, in, __current);
            }
            case 35:
            {
                return ___rmRouteToHoldMusic(this, in, __current);
            }
            case 36:
            {
                return ___rmRouteTwoRes(this, in, __current);
            }
            case 37:
            {
                return ___rmStartConferrenceRecord(this, in, __current);
            }
            case 38:
            {
                return ___rmUnRouteToHoldMusic(this, in, __current);
            }
            case 39:
            {
                return ___rmUnrouteTwoRes(this, in, __current);
            }
            case 40:
            {
                return ___sgAnswerCall(this, in, __current);
            }
            case 41:
            {
                return ___sgBlindMakeCall(this, in, __current);
            }
            case 42:
            {
                return ___sgLimitBilling(this, in, __current);
            }
            case 43:
            {
                return ___sgMakeCall(this, in, __current);
            }
            case 44:
            {
                return ___sgReleaseCall(this, in, __current);
            }
        }

        assert(false);
        return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
