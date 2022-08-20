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
    int ResID;           //��ԴID��ʶ
    int CallID;          //���б�ʶ
    int EventID;         //�¼�ID
    int Reason;          //�¼�������ԭ��
    string DTMFString;   //�¼�Я�������ݣ���DTMFֵ��
  };

  struct  SGEvent
  {
    int ResID;           //��ԴID��ʶ
    int CallID;          //���б�ʶ
    int EventID;         //�¼�ID
    int EventData;       //�¼��ľ���ԭ��
    string CallerID;     //�¼�Я��������,���к���,�ַ���
    string CalledID;     //�¼�Я��������,���к���,�ַ���
    string OriCallerID;  //�¼�Я��������,ԭ���к���,�ַ���
    string OriCalledID;  //�¼�Я��������,ԭ���к���,�ַ���
    string rfu;          //����
  };
  struct  CnfEvent
  {
    int nConfID;
    int nResID;
    int EventID;         //��Definitions.h�е�ý���������
    int Reason;          //��Definitions.h�е�ý���������
    string szDTMF;
  };
  sequence<string> strSequence;
  
  interface VGService
  {
    //��ʼ��    
    //int nDistributeMode ���з������
    //1 ���н���ƽ�����䵽���ͻ���
    //2 ֻ��ָ���ı��кŲŻ���䵽���ͻ���
    //3 ���ͻ�����CTIlink
    //string szMonitorDNIS ���nDistributeMode��2�����ͻ���Ҫ��صı��к��룬����ж���á�,���Ÿ���
    int VGInitialize(int nDistributeMode,string szMonitorDNIS,string szReserved);
    
    //���п�����
    int sgBlindMakeCall(string szCallerNum,string szCalledNum,int nTimeout,out int nResID,out int nCallID);
    int sgMakeCall(int nResID,string szCallerNum,string szCalledNum,int nTimeout,out int nCallID);
    int sgAnswerCall(int nResID,int nCallID);
    int sgReleaseCall(int nResID,int nCallID);
   
    //ý�������    
    int mgReceiveFax(int nResID,int nCallID,string szFileName);
    int mgSendFax(int nResID,int nCallID,string szFileName);

    //nKeyCount        �ռ�����
    //szInteruptKeys   ��������
    //nMax1stkeyTime   �׼���ʱʱ��
    //nTimeBetweenKey  ���䳬ʱʱ��
    int mgReceiveDTMF(int nResID,int nCallID,int nKeyCount,string szInteruptKeys,int nMax1stkeyTime,int nTimeBetweenKey);
    int mgSendDTMF(int nResID,int nCallID,string szSignals);
    int mgPlayVoice(int nResID,int nCallID,string szFileName,string szInteruptKeys,int nRate,int nBeginTime);
    int mgRecordVoice(int nResID,int nCallID,string szFileName,string szInteruptKeys,int nMaxTime,int nRate);
    int mgPlayTTS(int nResID,int nCallID,string szInteruptKeys,int nType,string szText,int nVoiceLib,int nRate);
    int mgPlayList(int nResID,int nCallID,string szInteruptKeys,int nRate,int nFileCount,strSequence szVoxFileList,int nVoiceLib);
    int mgASR(int nResID,int nCallID,int nMaxKeys,string szInteruptKeys,int nRate,int nFileCount,strSequence szVoxFileList,string szGrammar,int nNoSpeechTimeout,int nAcceptScore,int nResultNum,float fTimeoutBetweenWord, int nVoiceLib);
    int mgStopIO(int nResID,int nCallID);
	//�建����
	int mgFlushBuffer(int nResID,int nCallID);
	

    
    //����������
    int rmRouteToHoldMusic(int nResID,int nHoldMusicID);
    int rmUnRouteToHoldMusic(int nResID,int nHoldMusicID);
    int rmRouteTwoRes(int nRes1ID,int nRes2ID,int mode);
    int rmUnrouteTwoRes(int  nRes1ID, int nRes2ID, int mode);

	//������ͨ�����������
	int rmBlindCreateConf(int nResID1,int nResID2, out int pnConfID);
	//��һ��ͨ�����뵽һ����������,ֻ�е�����rmBlindCreateConf�ӿڲ��ܵ��ô˽ӿ�
    int rmStartConferrenceRecord(int nResID,int nCallID,string strRecordFileName);
    
    //���������
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
	   
  
    //ý���¼�
    int GetMgEvt(int nTimeout, out MGEvent event);
    //�����¼�
    int GetSgEvt(int nTimeout, out SGEvent event);
    //�����¼�
    int GetCnfEvt(int nTimeout, out CnfEvent event);

	//�޷ѽӿ�
	//nResID ͨ����;nTimeout ��ʱʱ��(��);sFilename Ϊ�һ���Ҫ���ŵ������ļ���
	int sgLimitBilling(int nResID,int nCallID,string sFilename,int nTimeout);

	//��ȡ��Դ�������Ⲧ��Դ����
	int GetUVMGResNum(out int ResTotal, out int OutboundResTotal);

	//���鲥��TTS�Ĺ���
	int cnfPlayTTS(int nConfID,int nType,string szText,int nVoiceLib);

	//���鲥�Ŷ�������ļ��Ĺ���
	int cnfPlayList(int nConfID, int nRate, int nFileCount, strSequence szVoxFileNames, int nVoiceLib);

	//�رյ�������
	int rmBlindCloseConf(int nConfID);

  };

};

#endif
