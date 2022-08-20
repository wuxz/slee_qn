/*=================================================================== 
 Interface for universal VG proxy
 date:2007.3.6 
 copyright(c) 2006, channelsoft co.Ltd

 ======================================================================= */

#ifndef _VG_PROXY_ICE
#define _VG_PROXY_ICE

[["java:package:com.channelsoft"]]

module VGProxy
{
  struct  MGEvent
  {
    int ResID;           //资源ID标识
    int CallID;          //呼叫标识
    int EventID;         //事件ID
    int Reason;          //事件发生的原因
    string DTMFString;   //事件携带的数据（如DTMF值）
  };

  struct  SGEvent
  {
    int ResID;           //资源ID标识
    int CallID;          //呼叫标识
    int EventID;         //事件ID
    int EventData;       //事件的具体原因
    string CallerID;     //事件携带的数据,主叫号码,字符串
    string CalledID;     //事件携带的数据,被叫号码,字符串
    string OriCallerID;  //事件携带的数据,原主叫号码,字符串
    string OriCalledID;  //事件携带的数据,原被叫号码,字符串
    string rfu;          //予留
  };
  struct  CnfEvent
  {
    int nConfID;
    int nResID;
    int EventID;         //见Definitions.h中的媒体操作定义
    int Reason;          //见Definitions.h中的媒体操作定义
    string szDTMF;
  };
  sequence<string> strSequence;
  
  interface VGService
  {
    //初始化    
    //int nDistributeMode 呼叫分配策略
    //1 呼叫将被平均分配到本客户端
    //2 只有指定的被叫号才会分配到本客户端
    //3 本客户端是CTIlink
    //string szMonitorDNIS 如果nDistributeMode是2，本客户端要监控的被叫号码，如果有多个用‘,’号隔开
    int VGInitialize(int nDistributeMode,string szMonitorDNIS,string szReserved);
    
    //呼叫控制类
    int sgBlindMakeCall(string szCallerNum,string szCalledNum,int nTimeout,out int nResID,out int nCallID);
    int sgMakeCall(int nResID,string szCallerNum,string szCalledNum,int nTimeout,out int nCallID);
    int sgAnswerCall(int nResID,int nCallID);
    int sgReleaseCall(int nResID,int nCallID);
   
    //媒体控制类    
    int mgReceiveFax(int nResID,int nCallID,string szFileName);
    int mgSendFax(int nResID,int nCallID,string szFileName);

    //nKeyCount        收键个数
    //szInteruptKeys   结束按键
    //nMax1stkeyTime   首键超时时间
    //nTimeBetweenKey  键间超时时间
    int mgReceiveDTMF(int nResID,int nCallID,int nKeyCount,string szInteruptKeys,int nMax1stkeyTime,int nTimeBetweenKey);
    int mgSendDTMF(int nResID,int nCallID,string szSignals);
    int mgPlayVoice(int nResID,int nCallID,string szFileName,string szInteruptKeys,int nRate,int nBeginTime);
    int mgRecordVoice(int nResID,int nCallID,string szFileName,string szInteruptKeys,int nMaxTime,int nRate);
    int mgPlayTTS(int nResID,int nCallID,string szInteruptKeys,int nType,string szText,int nVoiceLib,int nRate);
    int mgPlayList(int nResID,int nCallID,string szInteruptKeys,int nRate,int nFileCount,strSequence szVoxFileList,int nVoiceLib);
    int mgASR(int nResID,int nCallID,int nMaxKeys,string szInteruptKeys,int nRate,int nFileCount,strSequence szVoxFileList,string szGrammar,int nNoSpeechTimeout,int nAcceptScore,int nResultNum,float fTimeoutBetweenWord, int nVoiceLib);
    int mgStopIO(int nResID,int nCallID);
	//清缓冲区
	int mgFlushBuffer(int nResID,int nCallID);
	

    
    //交换控制类
    int rmRouteToHoldMusic(int nResID,int nHoldMusicID);
    int rmUnRouteToHoldMusic(int nResID,int nHoldMusicID);
    int rmRouteTwoRes(int nRes1ID,int nRes2ID,int mode);
    int rmUnrouteTwoRes(int  nRes1ID, int nRes2ID, int mode);

	//把两个通道加入会议中
	int rmBlindCreateConf(int nResID1,int nResID2, out int pnConfID);
	//把一个通道加入到一个混音器中,只有调用了rmBlindCreateConf接口才能调用此接口
    int rmStartConferrenceRecord(int nResID,int nCallID,string strRecordFileName);
    
    //会议控制类
    int cnfCreateConf(int nMaxUserNum,int nVoxResType,int nVoxResBindType,out int nConfID);
    int cnfCloseConf(int nConfID);    
    int cnfJoinConf(int nResID,int nConfID, int nAttribute);
    int cnfLeaveConf(int nResID,int nConfID);    
    int cnfConfPlay(int nConfID, string szFileName, int nSpeed, int nVolume,int nRate );  
    int cnfConfRecord(int nConfID, string szFileName,int nRate);
    int cnfStopConfPlay(int nConfID);  
    int cnfStopConfRecord(int nConfID);
    int cnfSetUserAttrib(int nResID, int nAttribute);        
    int cnfGetUserAttrib(int nResID, out int nAttribute);      
    int cnfMemberPlay(int nResID, string cszFileName,string szInteruptKeys, int nRate); 
	   
  
    //媒体事件
    int GetMgEvt(int nTimeout, out MGEvent event);
    //信令事件
    int GetSgEvt(int nTimeout, out SGEvent event);
    //会议事件
    int GetCnfEvt(int nTimeout, out CnfEvent event);

	//限费接口
	//nResID 通道号;nTimeout 超时时间(秒);sFilename 为挂机后要播放的语音文件名
	int sgLimitBilling(int nResID,int nCallID,string sFilename,int nTimeout);

	//获取资源总数和外拨资源总数
	int GetUVMGResNum(out int ResTotal, out int OutboundResTotal);

	//会议播放TTS的功能
	int cnfPlayTTS(int nConfID,int nType,string szText,int nVoiceLib);

	//会议播放多个语音文件的功能
	int cnfPlayList(int nConfID, int nRate, int nFileCount, strSequence szVoxFileNames, int nVoiceLib);

	//关闭单步会议
	int rmBlindCloseConf(int nConfID);

  };

};

#endif
