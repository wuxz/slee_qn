package com.channelsoft.slee.util;

import java.util.Locale;

public class Constant
{
	public static final int ASR_GRAMMAR_COMPILED = 1; // �﷨�ļ���������

	public static final int ASR_GRAMMAR_UNCOMPILED = 2; // �﷨�ļ�δ��������

	public static final String ASRError = "ASRError"; // ASR��ش���

	public static final String ATSESYSWAV_CantFindFile = "CantFindFile.vox";

	public static final String ATSESYSWAV_DiskFull = "DiskFull.vox";

	// �������ͨ��m_LastErrorȡ��

	// *****************************************************************
	// ����ATSEϵͳ�ڲ�ʹ�õ������ļ���
	public static final String ATSESYSWAV_GeneralError = "GeneralError.vox";

	public static final String ATSESYSWAV_InvalidFile = "InvalidFile.vox";

	public static final String ATSESYSWAV_InvalidUSMLFile = "InvalidUSMLFile.vox";

	public static final String ATSESYSWAV_ScriptRunError = "ScriptRunError.vox";

	public static final String ATSESYSWAV_Timeout = "Timeout.vox";

	public static final String BREAK_LIST_ALL = "0123456789*#";

	public static final String CantFindUSMLFile = "CantFindUSMLFile"; // �Ҳ���ָ����USML�ļ�

	// ȱʡ�ı���ͨ���ı���������
	public static final String ChannelDNVarName = "m_ChannelDN";

	public static final String CHINA_CURRENCY = java.util.Currency.getInstance(
			Locale.CHINA).getSymbol();

	public static final String CS_ATSEMgService = "CS_ATSEMgService";

	public static final String CS_ATSESrvManagerService = "CS_ATSESrvManagerService";

	public static final String CS_USMessageMgService = "CS_USMessageMgService";

	public static final String Currency = "Currency";

	public static final String CustomHangup = "CustomHangup"; // �ͻ��Ҷ�

	public static final String Date = "Date";

	public static final String DefEventVarName = "m_StartEventType";

	public static final String DefLastErrorVarName = "m_LastError";

	// ȱʡ���������ͱ���������
	public static final String DefPronLangTypeVarName = "m_PronLanguage";

	public static final String DefResultVarName = "m_PrevResult";

	public static final String DestBusy = "DestBusy"; // Ŀ��æ

	public static final int DeviceStatu_Busy = 1;// �豸��ǰ�к���

	// ATS_ChannelStatus;
	public static final int DeviceStatu_Idle = 0;// �豸��ǰû�к���

	public static final String DiskFull = "DiskFull"; // ���̿ռ䲻��

	public static final String EMail = "EMail";

	public static final String English = "English"; // Ӣ��

	public static final String Equal = "Equal";

	public static final int ERR_ASR_EXCEPTION = 0x151; // ASR�쳣

	public static final int ERR_ASR_MemRecord = 0x100000C4; // �ڴ�¼��ʧ��

	public static final int ERR_ASR_PlayPrompt = 0x100000C5; // ����ʾ��ʧ��

	public static final int ERR_ASR_RecSlow = 0x100000C2; // ʶ��ʱ

	public static final int ERR_ASR_Reject = 0x100000C6; // û�г����趨�ķ�����ʶ�𱻾ܾ�

	public static final int ERR_ASR_SpeechTooEarly = 0x100000C3; // �û�˵��̫���ˣ�

	// ASR
	public static final int ERR_ASR_TooMuchSpeech = 0x100000C1; // �û�˵��̫��

	public static final int ERR_ChannelBusy = 0x100000AD; // ָ��ͨ�����Ѿ��к���

	// ����ʾ��֮ǰ�Ϳ�ʼ˵����

	public static final int ERR_ChargeNumber = 0x2001000C; // Fee_terminal_Id����

	public static final int ERR_Connect_NoFax = 0x100000A9; // ���гɹ����Է���ͨ��

	public static final int ERR_Connect_Voice = 0x100000AE; // ���гɹ����Է���ͨ��

	public static final int ERR_ContentMax = 0x20010006; // ���������Ϣ��

	// ���ǶԷ����Ǵ����豸
	// ���������ֶ������豸

	public static final int ERR_CorpID = 0x2001000B; // Msg_src����

	// ���ǶԷ�������Ӧ���豸

	public static final int ERR_DestBusy = 0x100000A6; // Ŀ�����æ

	public static final int ERR_Disconnect = 0x100000AC; // IO�����пͻ��Ҷ�

	public static final int ERR_DiskFull = 0x100000A3; // ���̿ռ�����

	public static final int ERR_EXISTCALL_STATE = 0x106; // ��Դ���Ѵ��ں���

	public static final int ERR_FATALERROR = 0x107; // ���ز���ʧ�ܣ���Ҫ�ϲ�����ͨ��

	// add by dengsong for SMSMediaServer on 2004.6.23
	// SMSMediaServer Submit_Resp Error
	public static final int ERR_FeeCode = 0x20010005; // �ʷѴ����

	public static final int ERR_Force_Exit = 0x2fff0000;

	public static final int ERR_GeneralError = 0x100000A2; // һ���Դ���

	public static final int ERR_GENERALERROR = 0x100; // ����ʧ��

	public static final int ERR_INVALID_FILE = 0x102; // ��Ч���ļ���

	public static final int ERR_INVALID_RESID = 0x101; // ��Ч����ԴID

	public static final int ERR_InvalidFile = 0x100000A4; // ָ�����ļ�����Ч

	public static final int ERR_InvalidTelNum = 0x100000A7; // ָ����Ŀ�������Ч

	public static final int ERR_INVLAIDARG = 0x108; // ���ʱ�����������

	public static final int ERR_IVALID_DN = 0x100000B1; // ָ����DNδ����

	public static final int ERR_IVALID_PARAM = 0x100000B6; // ��Ч�Ĳ���

	public static final int ERR_IVALID_Teminal = 0x100000B7; // ��Ч���նˣ��ն˺�������ն˲�����

	public static final int ERR_MediaUnmatch = 0x100000BE; // ���ʱ˫��ý�岻ƥ��

	public static final int ERR_NO_MSG = 0x100000B4; // ��ǰû���¼�

	public static final int ERR_NoAnswer = 0x100000A8; // �Է�ָ��ʱ����δӦ��

	public static final int ERR_NoCall = 0x100000A5; // ��ǰû�к��в���ִ��ָ���Ĳ���

	public static final int ERR_NOCALL_STATE = 0x105; // ��Դ�ϲ����ں���

	public static final int ERR_NoConnect = 0x20020002; // û�п�������

	public static final int ERR_NOSTART = 0x100000B3; // ָ����DNû������

	public static final int ERR_NothingTelNum = 0x100000B9; // �պ�

	// SMSMediaServer �ڲ�����
	public static final int ERR_OpenServer = 0x20020001; // ���ӷ�����

	// �Ⲧ �ߺŶ���
	public static final int ERR_OutOfService = 0x100000B8; // ���ڷ�����

	public static final int ERR_PhoneNumber = 0x2001000D; // Dest_terminal_Id����

	public static final int ERR_REMOTE_DISCONNECT = 0x109; // ���ڣ�ͬ����ý������ӿڣ�

	public static final int ERR_RES_ALREADYOPEN = 0x104; // ��Դ�Ѿ�����

	public static final int ERR_RouteDest = 0x20020011; // ·��Ŀ��ʧ��

	// ִ��ý�����ʱԶ�˹Ҷ�

	public static final int ERR_RPC = 0x100000B2; // RPC����

	public static final int ERR_ServiceType = 0x20010007; // ҵ������

	public static final int ERR_SPNumber = 0x2001000A; // Src_Id����

	// ***********************************************************************
	// add by xiawei on 2001.09.17//����ȡֵ��ATSE����һ��
	public static final int ERR_Success = 0x100000A1; // �����ɹ�

	public static final int ERR_TerminalCannotFind = 0x100000BC; // �޷���ͨ

	public static final int ERR_TerminalPowerOff = 0x100000BA; // �ػ�

	public static final int ERR_TerminalRefuse = 0x100000BB; // �ܾ�Ӧ��

	public static final int ERR_TimeOut = 0x100000AA; // ������IO����ָ����ʱ�䳤��

	public static final int ERR_ToAgent = 0x100000AB; // δʹ��

	public static final int ERR_TTS_CONVERT = 0x150; // TTSת��ʧ��

	public static final int ERR_UnsptChargeNum = 0x20010009; // ���ز��������˼ƷѺ���

	public static final int ERR_UNSUPPORTED_STATE = 0x103; // ��Դ����״̬��֧�ֵ�ǰ����

	public static final int Err_UnsupportedMedia = 0x100000BD; // ��֧�ֵ�ý�����ͣ����粻֧����Ƶ(hzl2009-3-25)

	public static final int ERR_UnsupportProc = 0x100000B5; // ��ǰý���������֧�ִ˲���

	// ATSEEventDefine
	// expend ATS Event;
	public static final int ERR_User_Event = 0x1fff0000; // �û��Զ����¼��ж�

	public static final int ERR_User_StopIO = 0x100000AF; // ���гɹ����Է���ͨ�����ǶԷ�������Ӧ���豸

	public static final int EVENT_ASR_MemRecordError = 0x10003004;

	public static final int EVENT_ASR_PlayPromptError = 0x10003005;

	public static final int EVENT_ASR_RecSlow = 0x10003002;

	public static final int EVENT_ASR_Reject = 0x10003006;

	public static final int EVENT_ASR_SpeechTooEarly = 0x10003003;

	public static final int EVENT_ASR_TooMuchSpeech = 0x10003001;

	public static final int EVENT_ASRError = 0x10003000; // ASR���

	public static final int EVENT_CantFindUSMLFile = 0x100000AE; //

	public static final int EVENT_CONNECT = 0x100000C9; // ���н�ͨ���Է�ժ����

	public static final int EVENT_CustomHangup = 0x100000AC; //

	public static final int EVENT_DestBusy = 0x100000A6; //

	public static final int Event_Disconnect = 0x1000000A; // ���йҶϣ��Է����߱����һ���

	public static final int EVENT_DiskFull = 0x100000A3; //

	public static final int EVENT_End = 0x100000B2; //

	public static final int EVENT_ExitWorkflow = 0x2fff0004; //

	public static final int EVENT_ForceExit = 0x2fff0000; //

	public static final int EVENT_GeneralError = 0x100000A2; //

	public static final int EVENT_GotoService = 0x2fff0003; //

	public static final int EVENT_HOOKOFF = 0x100000C2; // ժ��(����)

	public static final int EVENT_HOOKON = 0x100000C3; // �һ�(����)

	public static final int Event_InboundCall = 0x10000001; // ���н���

	public static final int EVENT_InsufficientResource = 0x10000000; //

	public static final int EVENT_InvalidAuthorization = 0x100000C2; // ������Ȩ֤�����

	public static final int EVENT_InvalidFile = 0x100000A4; //

	public static final int EVENT_InvalidNodeCode = 0x100000C4; // ��������ָ���ڵ�����

	public static final int EVENT_InvalidTelNum = 0x100000A7; //

	public static final int EVENT_InvalidUSMLFile = 0x100000A0; //

	public static final int EVENT_IVALID_PARAM = 0x100000BB; // ��Ч�Ĳ���

	public static final int EVENT_IVALID_Teminal = 0x100000BC; // ��Ч���նˣ��ն˺������

	public static final int EVENT_Loop_Break = 0x100000B7; //

	public static final int EVENT_Loop_continue = 0x100000B6; //

	// �ն˲�����

	public static final int EVENT_MediaUnmatch = 0x100000CB; // ���ʱ˫��ý�岻ƥ��

	public static final int EVENT_No_Authorization = 0x100000C1; // ����δ��Ȩ

	public static final int EVENT_No_Error = 0x100000A1; //

	public static final int EVENT_NoAnswer = 0x100000A8; //

	public static final int EVENT_NoCall = 0x100000A5; //

	public static final int EVENT_NoFaxDevice = 0x100000A9; //

	public static final int EVENT_Over_ValidDateTime = 0x100000C3; // ���񳬹�����Чʱ��

	public static final int EVENT_PauseService = 0x2fff0001; //

	public static final int EVENT_ResumeService = 0x2fff0002; //

	public static final int EVENT_Script_End = 0x100000B3; //

	public static final int EVENT_ScriptRunError = 0x100000AD; //

	public static final int EVENT_Sms_ChargeNumberError = 0x10002006;

	public static final int EVENT_Sms_ContentMax = 0x10002001;

	public static final int EVENT_Sms_CorpIDError = 0x10002005;

	public static final int EVENT_Sms_FeeCodeError = 0x10002002;

	public static final int Event_SMS_MO = 0x20000001; // ���ŵ�MO

	public static final int EVENT_Sms_NoConnect = 0x1000200A;

	public static final int EVENT_Sms_OpenServerError = 0x10002009;

	public static final int EVENT_Sms_PhoneNumberError = 0x10002007;

	public static final int Event_SMS_Report = 0x20000010; // ���ŵ�Report

	public static final int EVENT_Sms_RouteDestError = 0x1000200B;

	public static final int EVENT_Sms_ServiceTypeError = 0x10002003;

	public static final int EVENT_Sms_SPNumberError = 0x10002004;

	public static final int EVENT_Sms_UnsptChargeNumber = 0x1000200;

	public static final int EVENT_SmsError = 0x10002000; // �������

	public static final int EVENT_StackOverflow = 0x100000B8; // ���ö�ջ���

	public static final int EVENT_Start = 0x100000B1; //

	public static final int EVENT_TDO_DestBusy = 0x10001002;

	public static final int EVENT_TDO_InvalidTelNum = 0x10001001;

	public static final int EVENT_TDO_MediaUnmatch = 0x10001009;

	public static final int EVENT_TDO_NoAnswer = 0x10001003;

	public static final int EVENT_TDO_NothingTelNum = 0x10001005;

	public static final int EVENT_TDO_OutOfService = 0x10001004;

	public static final int EVENT_TDO_TerminalCannotFind = 0x10001008;

	public static final int EVENT_TDO_TerminalPowerOff = 0x10001006;

	public static final int EVENT_TDO_TerminalRefuse = 0x10001007;

	public static final int EVENT_TDO_UnsupportedMedia = 0x10001010;

	public static final int EVENT_TDX_GETDIGIT = 0x100000C6; // �ռ�����

	public static final int EVENT_TDX_PLAY = 0x100000C4; // ���Ž���

	public static final int EVENT_TDX_RECORD = 0x100000C5; // ¼������

	public static final int EVENT_TDX_RECV = 0x100000C8; // ������ܽ���

	public static final int EVENT_TDX_SEND = 0x100000C7; // ���淢�ͽ���

	// PrevResult return EVENT_TelDialOut, LastError return EVENT_TDO_XXX
	public static final int EVENT_TelDialOut = 0x10001000; // �绰�Ⲧ���

	public static final int EVENT_TimeOut = 0x100000AA; //

	public static final int EVENT_To_PrevNode = 0x100000B5; //

	public static final int EVENT_To_RootNode = 0x100000B4; //

	public static final int EVENT_ToAgent = 0x100000AB; //

	public static final int EVENT_UnsupportedMedia = 0x100000CA; // ������ʹ��ָ��ý��

	public static final int EVENT_UnsupportProc = 0x100000BA; // ��ǰý���������֧�ִ˲���

	public static final int EVENT_User_StopIO = 0x100000AF; //

	public static final int EVENT_UserEvent = 0x1fff0000; //

	public static final int Event_UssdDisconnect = 0x3000000A; // ���йҶϣ��Է����߱����һ���

	public static final int Event_UssdInboundCall = 0x30000001; // ���н���

	// �¼�������ָ�����¼����ͱ�������
	public static final String EventVarNameTag = "EventVarName";

	public static final String FemaleCantonese = "Female-Cantonese"; // Ů����

	public static final String FemaleEnglish = "Female-English"; // ŮӢ��

	public static final String FemaleMandarin = "Female-Mandarin"; // Ů��ͨ��

	public static final int FILENAME_LEN = 256;

	public static final String Float = "Float";

	public static final int GATEWAY_SUCCESS = 0x0; // �����ɹ�

	public static final String GeneralError = "GeneralError"; // һ���Դ���

	public static final String GuangdongDialect = "GuangdongDialect"; // ����

	public static final String HIDKEY = "_QNSoft_Wangxf_Purple_Glacier";

	public static final String InsufficientResource = "InsufficientResource"; // ���ö�ջ���

	public static final String Int = "Int";

	public static final int INT_FemaleCantonese = 3;

	public static final int INT_FemaleEnglish = 5;

	public static final int INT_FemaleMandarin = 1;

	public static final int INT_MaleCantonese = 4;

	public static final int INT_MaleEnglish = 6;

	public static final int INT_MaleMandarin = 2;

	public static final int INT_Undefine = 0;

	public static final String InvalidAuthorization = "InvalidAuthorization"; // ������Ȩ֤�����

	public static final String InvalidFile = "InvalidFile"; // ��Ч���ļ�

	public static final String InvalidNodeCode = "InvalidNodeCode"; // ��������ָ���ڵ�����

	public static final String InvalidParam = "InvalidParam"; // �Ҳ���ָ����USML�ļ�

	public static final String InvalidTelNum = "InvalidTelNum"; // ��Ч�ĵ绰����

	public static final String InvalidTerminal = "InvalidTerminal"; // ��Ч��USML�ļ�

	public static final String InvalidUSMLFile = "InvalidUSMLFile"; // ��Ч��USML�ļ�

	public static final int IOST_BUSY = 0x02;

	public static final int IOST_IDLE = 0x01;

	public static final String JavaScript = "JavaScript";

	public static final String Less = "Less";

	public static final String LessAndEqual = "LessAndEqual";

	public static final String LocalDll = "LocalDll";

	public static final String MaleCantonese = "Male-Cantonese"; // ������

	public static final String MaleEnglish = "Male-English"; // ��Ӣ��

	public static final String MaleMandarin = "Male-Mandarin"; // ����ͨ��

	public static final String Mandarin = "Mandarin"; // ��ͨ��

	public static final String MediaUnmatch = "MediaUnmatch"; // ���ʱ˫��ý�岻ƥ��

	public static final int MG_FAIL = 0x1002; // �첽����ʧ��

	// MG event
	public static final int MG_SUCCESS = 0x1001; // �첽�����ɹ�

	public static final int MG_TDX_ASR = 0x10050; // ����ʶ�����

	public static final int MG_TDX_GETDIGIT = 0x1006; // �ռ�����

	public static final int MG_TDX_PLAY = 0x1004; // ���Ž���

	public static final int MG_TDX_RECORD = 0x1005; // ¼������

	public static final int MG_TFX_FAIL = 0x10009; // �������ʧ��

	public static final int MG_TFX_RECV = 0x10008; // ������ܽ���

	public static final int MG_TFX_SEND = 0x1007; // ���淢�ͽ���

	public static final int MGREASON_ARS_ABORT = 0x59; // ʶ��ȡ��

	public static final int MGREASON_ARS_PLAYPROMPT = 0x58; // ����ʾ��ʧ��

	public static final int MGREASON_ASR_EXCEPTION = 0x56; // �������쳣����

	public static final int MGREASON_ASR_MEMRECORD = 0x57; // �ڴ�¼��ʧ��

	public static final int MGREASON_ASR_NO_SPEECH = 0x51; // ������ʾ�����û���ʱ�䲻˵��

	public static final int MGREASON_ASR_REC_SLOW = 0x53; // ʶ��ʱ

	public static final int MGREASON_ASR_RECGNIZED = 0x50; // ʶ����ɣ��н������

	// ����ʾ��֮ǰ�Ϳ�ʼ˵����

	public static final int MGREASON_ASR_REJECT = 0x55; // û�г����趨�ķ�����ʶ�𱻾ܾ�

	public static final int MGREASON_ASR_SPEECH_TOO_EARLY = 0x54; // �û�˵��̫���ˣ�

	public static final int MGREASON_ASR_TOOMUCH_SPEECH = 0x52; // �û�˵��̫��

	// MGREASON_ ASR_NO_SPEECH ������ʾ�����û���ʱ�䲻˵�� use ERR_TimeOut

	public static final int MGREASON_TM_DIGIT = 0x02; // �յ�ָ����

	public static final int MGREASON_TM_DISCONNECT = 0x05; // �����������豸�һ�

	// MGREASON_ASR_EXCEPTION �������쳣���� use ERR_GeneralError

	// �¼�������ԭ��
	public static final int MGREASON_TM_EOD = 0x01; // �ļ����Ų�������

	public static final int MGREASON_TM_INVALIDFILE = 0x07; // ��Ч���ļ��������ļ���ʽ����

	public static final int MGREASON_TM_MAXDTMF = 0x03; // �յ���DTMF���ﵽָ�������

	public static final int MGREASON_TM_MAXTIME = 0x04; // ����ʱ��ﵽָ��ʱ��

	public static final int MGREASON_TM_USRSTOP = 0x06; // �����������ֹ

	public static final String More = "More";

	public static final String MoreAndEqual = "MoreAndEqual";

	public static final String No_Authorization = "No_Authorization"; // ����δ��Ȩ

	// �ڵ����������¼�����
	public static final String No_Error = "No_Error"; // û�д���

	public static final String NoAnswer = "NoAnswer"; // �Է�δӦ��

	public static final String NoCall = "NoCall"; // ��ǰû�к���

	public static final String NoFaxDevice = "NoFaxDevice"; // �Է��Ǵ����豸

	public static final int NUMBER_LEN = 32;

	public static final int VIDEOPARAM_LEN = 5;

	// �Ƚϲ�����
	public static final String OperatorIn = "In";

	public static final String Over_ValidDateTime = "Over_ValidDateTime"; // ���񳬹�����Чʱ��

	public static final String PronouncingLanguage = "PronouncingLanguage";

	public static final int REASON_DESTBUSY = 0x51; // ����æ

	public static final int REASON_DESTFAXTONE = 0x54; // ����Ϊ�����豸

	public static final int REASON_DESTNOANSWER = 0x52; // ����δӦ��

	public static final int REASON_DESTNOTFAX = 0x55; // ����Ϊ�Ǵ����豸

	public static final int REASON_DESTUNKNOWN = 0x53; // ����״̬δ֪

	public static final int REASON_INVLAIDNUM = 0x56; // ���к���Ƿ�

	public static final int REASON_MEDIA_NEGOTIATE = 0x59; // ý��Э��ʧ�ܣ���3G-324Mʧ��

	public static final int REASON_NORMAL = 0x50; // ����

	public static final int REASON_NUMBER_NULL = 0x2c;

	public static final int REASON_OUT_AREA = 0x2b;

	public static final int REASON_POWER_OFF = 0x5a;

	public static final int REASON_REFUSE = 0x2d;

	public static final int REASON_UNSUPPORTED_MEDIA = 0x58; // ��֧�ֵ�ý�����ͣ����粻֧����Ƶ(hzl2009-3-25)

	public static final int REASON_VOICE_DETECTED = 0x57; // ��⵽����Ӧ��

	// ����ڵ�ָ���Ľ����������
	public static final String ResultVarNameTag = "ResultVarName";

	//public static final int RM_AUDIO_ONLY = 5;

	// (hzl2009-3-25)

	public static final int RM_DSPDUP = 2;

	public static final int RM_FULLDUP = 0;

	public static final int RM_HALFDUP = 1;

	//˫������Ƶ
	public static final int RM_BOTHVIDEO = 3;

	//ֻ��ori����Ƶ��Dest
	public static final int RM_ORIVIDEO = 4;
	
	//ֻ��Dest����Ƶ��ori
	public static final int RM_DESTVIDEO = 5;
	
	public static final String ScriptRunError = "ScriptRunError"; // �ű����д���

	public static final String ServerPage = "ServerPage";

	// ��EventData�ֶδ洢���н���ʱ
	// ���Է��豸�����

	public static final int SG_CALLACCEPTED = 0x2002; // �Ѿ��ɹ�Accept�����, --add by

	public static final int SG_CALLALERTING = 0x2013; // ����ɹ�,�������յ���������

	public static final int SG_CALLCONNECTED = 0x2003; // ���н����������ϵͳ����������

	public static final int SG_CALLDISCONNECTED = 0x2004; // �Է��һ�

	// hzl--

	public static final int SG_CALLRELEASED = 0x2005; // �������

	public static final int SG_DAILED = 0x2008; // ���з���ɹ�

	// EventData�ֶδ洢���е�CRN����

	public static final int SG_FATALERROR = 0x2007; // �������ش���,������Ҫ���³�ʼ���豸��Դ,�ݲ�ʹ��

	// �¼�ID
	public static final int SG_INCOMINGCALL = 0x2001; // ����е���������globalcallЭ�飬

	public static final int SG_LINE_DOWN = 0x2010; // ��Դֹͣ����

	public static final int SG_LINE_IN_SERVICE = 0x2011; // ��·ͨ����Ϊ������ϣ��ݲ�����

	public static final int SG_LINE_OUT_SERVICE = 0x2012; // ��·ͨ��������ϻָ�����Ϊ����

	public static final int SG_LINE_START = 0x2009; // ��Դ��ʼ����

	public static final int SG_NO_ENOUGH_DIAL_RES = 0x2020; // û���㹻���ⲿ��Դ

	public static final int SG_TASKFAIL = 0x2006; // ����ִ��ʧ��,�ݲ�ʹ��

	public static final int SGST_NULL = 0x00; // ���д���NULL״̬���Ѿ����ͷ�

	public static final int SGST_OTHERS = 0x01; // ���д�������״̬��δ���ͷ�

	// �������ͨ��m_LastErrorȡ��

	// ϵͳ����
	public static final int SHORT_STRING_LENGTH = 16;

	public static final String SmsError = "SmsError"; // ������ش���

	public static final int SOFTSWITCH_MESSAGE_ACK_ANSWERCALL = 1007;

	public static final int SOFTSWITCH_MESSAGE_ACK_ASR = 1034;

	public static final int SOFTSWITCH_MESSAGE_ACK_MAKECALL = 1004;

	public static final int SOFTSWITCH_MESSAGE_ACK_PLAYLIST = 1032;

	public static final int SOFTSWITCH_MESSAGE_ACK_PLAYTTS = 1030;

	public static final int SOFTSWITCH_MESSAGE_ACK_PLAYVOICE = 1026;

	public static final int SOFTSWITCH_MESSAGE_ACK_RECEIVEDTMF = 1021;

	public static final int SOFTSWITCH_MESSAGE_ACK_RECEIVEFAX = 1017;

	public static final int SOFTSWITCH_MESSAGE_ACK_RECORDVOICE = 1028;

	public static final int SOFTSWITCH_MESSAGE_ACK_RELEASECALL = 1010;

	public static final int SOFTSWITCH_MESSAGE_ACK_SENDDTMF = 1024;

	public static final int SOFTSWITCH_MESSAGE_ACK_SENDFAX = 1019;

	public static final int SOFTSWITCH_MESSAGE_ACK_STOPIO = 1039;

	public static final int SOFTSWITCH_MESSAGE_REPLY_ASR = 1035;

	public static final int SOFTSWITCH_MESSAGE_REPLY_BLINDCREATECONFERENCE = 1041;

	public static final int SOFTSWITCH_MESSAGE_REPLY_DISSWITCH = 1014;

	public static final int SOFTSWITCH_MESSAGE_REPLY_MAKECALL = 1005;

	public static final int SOFTSWITCH_MESSAGE_REPLY_MGEVENT = 1015;

	public static final int SOFTSWITCH_MESSAGE_REPLY_RECEIVEDTMF = 1022;

	public static final int SOFTSWITCH_MESSAGE_REPLY_REGISTER = 1001;

	public static final int SOFTSWITCH_MESSAGE_REPLY_SGEVENT = 1008;

	public static final int SOFTSWITCH_MESSAGE_REPLY_STARTCONFERENCERECORD = 1037;

	public static final int SOFTSWITCH_MESSAGE_REPLY_SWITCH = 1012;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_ANSWERCALL = 1006;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_ASR = 1033;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_BLINDCREATECONFERENCE = 1040;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_DISSWITCH = 1013;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_INCOMINGCALL = 1002;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_MAKECALL = 1003;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_PLAYLIST = 1031;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_PLAYTTS = 1029;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_PLAYVOICE = 1025;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_RECEIVEDTMF = 1020;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_RECEIVEFAX = 1016;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_RECORDVOICE = 1027;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_REGISTER = 1000;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_RELEASECALL = 1009;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_SENDDTMF = 1023;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_SENDFAX = 1018;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_STARTCONFERENCERECORD = 1036;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_STOPIO = 1038;

	public static final int SOFTSWITCH_MESSAGE_REQUEST_SWITCH = 1011;

	public static final String StackOverflow = "StackOverflow"; // ���ö�ջ���

	public static final int StatusConference = 2;

	public static final int StatusConnect = 1;

	public static final int StatusIdle = 0;

	public static final String strAnswerCall = "AnswerCall";

	public static final String strASREdit = "ASREdit";

	public static final String strASREdit2 = "ASREdit2";

	public static final String strDisconnectCall = "DisconnectCall";

	public static final String strGetASR = "GetASR";

	public static final String strGetDTMF = "GetDTMF";

	// *************
	public static final String strGetSmsMsg = "GetSmsMsg";

	public static final String strGetSmsReport = "GetSmsReport";

	public static final String String = "String";

	public static final String strMakeCall = "MakeCall";

	// IOProcess ����
	public static final String strPlayFile = "PlayFile";

	public static final String strPlayTTS = "PlayTTS";

	// *************
	public static final String strPlayVoiceByTTS = "PlayVoiceByTTS";

	public static final String strReceiveFax = "ReceiveFax";

	public static final String strRecordVoice = "RecordVoice";

	public static final String strSendDTMF = "SendDTMF";

	public static final String strSendFax = "SendFax";

	public static final String strSendSmsMsg = "SendSmsMsg";

	public static final String strTransferCall = "TransferCall";

	public static final String strVoiceEdit = "VoiceEdit";

	public static final String strVoiceListEdit = "VoiceListEdit";

	public static final String strCallRecordRing = "CallRecordRing";
	
	// ͨ������
	public static final String Tel = "Tel";

	// �������ͨ��m_LastErrorȡ��

	public static final String TelDialOut = "TelDialOut"; // �绰�Ⲧ����¼���

	public static final String TelNumber = "TelNumber";

	public static final String Time = "Time";

	public static final String TimeOut = "TimeOut"; // ��ʱ

	public static final String ToAgent = "ToAgent"; // ת��ϯ

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_BUF = 1; // ���ı��ڴ�ת��Ϊ�����ڴ沥��

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_BUF1 = 1; // ���ı��ڴ�ת��Ϊ�����ڴ沥��

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_FILE = 3; // ���ı��ڴ�ת��Ϊ�����ļ����ٲ���

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_FILE3 = 3; // ���ı��ڴ�ת��Ϊ�����ļ����ٲ���

	public static final int TTS_TYPE_TEXT_FILE_TO_AUDIO_BUF = 2; // ���ı��ļ�ת��Ϊ�����ڴ沥��

	public static final int TTS_TYPE_TEXT_FILE_TO_AUDIO_BUF2 = 2; // ���ı��ļ�ת��Ϊ�����ڴ沥��

	public static final int TTS_TYPE_TEXT_FILE_TO_AUDIO_FILE = 4; // ���ı��ļ�ת��Ϊ�����ļ����ٲ���

	public static final int TTS_VOICE_LIB_MAN = 2; // ��������

	public static final int TTS_VOICE_LIB_MAN2 = 2; // ��������

	public static final int TTS_VOICE_LIB_WOMAN = 1; // Ů������

	public static final int TTS_VOICE_LIB_WOMAN1 = 1; // Ů������

	public static final int ULONG_STRING_LENGTH = 128;

	public static final String Unequal = "Unequal";

	public static final String UnsupportedMedia = "UnsupportedMedia"; // ������ʹ��ָ��ý��

	public static final String UnsupportProc = "UnsupportProc"; // ��Ч��USML�ļ�

	// ��������
	public static final String USMLEvent = "USMLEvent";

	// ��������
	public static final String VBScript = "VBScript";

	public static final String VFILE_0 = "Number_0.vox";

	public static final String VFILE_1 = "Number_1.vox";

	public static final String VFILE_2 = "Number_2.vox";

	public static final String VFILE_3 = "Number_3.vox";

	// �¼�Я������: EventData

	public static final String VFILE_4 = "Number_4.vox";

	public static final String VFILE_5 = "Number_5.vox";

	public static final String VFILE_6 = "Number_6.vox";

	public static final String VFILE_7 = "Number_7.vox";

	public static final String VFILE_8 = "Number_8.vox";

	public static final String VFILE_9 = "Number_9.vox";

	public static final String VFILE_A = "Alphabet_a.vox";

	public static final String VFILE_B = "Alphabet_b.vox";

	public static final String VFILE_C = "Alphabet_c.vox";

	public static final String VFILE_D = "Alphabet_d.vox";

	public static final String VFILE_Day = "Date_day.vox";

	public static final String VFILE_E = "Alphabet_e.vox";

	public static final String VFILE_F = "Alphabet_f.vox";

	public static final String VFILE_Fen = "Money_fen.vox";

	public static final String VFILE_G = "Alphabet_g.vox";

	public static final String VFILE_H = "Alphabet_h.vox";

	public static final String VFILE_Hour = "Time_hour.vox";

	public static final String VFILE_Hundred = "Unit_hundred.vox";

	public static final String VFILE_I = "Alphabet_i.vox";

	public static final String VFILE_J = "Alphabet_j.vox";

	public static final String VFILE_Jiao = "Money_jiao.vox";

	public static final String VFILE_K = "Alphabet_k.vox";

	public static final String VFILE_L = "Alphabet_l.vox";

	// TTS_FILE name;
	public static final String VFILE_Liang = "Time_liang.vox";

	public static final String VFILE_M = "Alphabet_m.vox";

	public static final String VFILE_Minute = "Time_minute.vox";

	public static final String VFILE_Mon = "Date_month.vox";

	public static final String VFILE_N = "Alphabet_n.vox";

	public static final String VFILE_Negative = "Number_Fu.vox";

	public static final String VFILE_O = "Alphabet_o.vox";

	public static final String VFILE_P = "Alphabet_p.vox";

	public static final String VFILE_Point = "Number_Point.vox";

	public static final String VFILE_Q = "Alphabet_q.vox";

	public static final String VFILE_R = "Alphabet_r.vox";

	public static final String VFILE_S = "Alphabet_s.vox";

	public static final String VFILE_Second = "Time_second.vox";

	public static final String VFILE_T = "Alphabet_t.vox";

	; // ��·����״̬����

	public static final String VFILE_Ten = "Unit_ten.vox";

	public static final String VFILE_TenMillion = "Unit_TenMillion.vox";

	; // IO����״̬

	public static final String VFILE_TenThousand = "Unit_TenThousand.vox";

	public static final String VFILE_Thousand = "Unit_thousand.vox";

	public static final String VFILE_U = "Alphabet_u.vox";

	public static final String VFILE_V = "Alphabet_v.vox";

	public static final String VFILE_W = "Alphabet_w.vox";

	public static final String VFILE_X = "Alphabet_x.vox";

	public static final String VFILE_Y = "Alphabet_y.vox";

	public static final String VFILE_Yao = "Number_yao.vox";

	public static final String VFILE_Year = "Date_year.vox";

	public static final String VFILE_Yuan = "Money_yuan.vox";

	public static final String VFILE_Z = "Alphabet_z.vox";

	public static final int VGCP_MEDIA_TYPE_AUDIO = 1; // ý������-��Ƶ

	public static final int VGCP_MEDIA_TYPE_VIDEO = 2; // ý������-��Ƶ

	public static final String WAP = "WAP";

	public static final String Web = "Web";

	public static int convertError2Event(int atsError)
	{
		// ת��ATS������뵽USE�������
		if ((atsError & 0xffff0000) == Constant.EVENT_UserEvent)
		{
			return Constant.EVENT_UserEvent + (atsError & 0xffff);
		}

		switch (atsError)
		{
		case Constant.ERR_User_StopIO:
			return Constant.EVENT_User_StopIO;
		case Constant.SG_NO_ENOUGH_DIAL_RES:
		case Constant.ERR_RES_ALREADYOPEN:
			return Constant.EVENT_InsufficientResource;
		case Constant.EVENT_ForceExit:
			return Constant.EVENT_ForceExit;
		case Constant.ERR_Success:
			return Constant.EVENT_No_Error;
		case Constant.ERR_GeneralError:
			return Constant.EVENT_GeneralError;
		case Constant.ERR_DiskFull:
			return Constant.EVENT_DiskFull;
		case Constant.ERR_InvalidFile:
		case Constant.ERR_INVALID_FILE:
			return Constant.EVENT_InvalidFile;
		case Constant.ERR_NoCall:
		case Constant.ERR_NOCALL_STATE:
			return Constant.EVENT_CustomHangup;// EVENT_NoCall;
		case Constant.ERR_DestBusy:
		case Constant.ERR_EXISTCALL_STATE:
			return Constant.EVENT_DestBusy;
		case Constant.ERR_InvalidTelNum:
			return Constant.EVENT_InvalidTelNum;
		case Constant.ERR_NoAnswer:
			return Constant.EVENT_NoAnswer;
		case Constant.ERR_Connect_NoFax:
			return Constant.EVENT_NoFaxDevice;
		case Constant.ERR_TimeOut:
			return Constant.EVENT_TimeOut;
		case Constant.ERR_ToAgent:
			return Constant.EVENT_ToAgent;
		case Constant.ERR_Disconnect:
			return Constant.EVENT_CustomHangup;
		case Constant.ERR_ChannelBusy:
			return Constant.EVENT_GeneralError;
			// **************************
		case Constant.ERR_IVALID_DN:
			return Constant.EVENT_GeneralError;
		case Constant.ERR_RPC:
			return Constant.EVENT_GeneralError;
		case Constant.ERR_NOSTART:
			return Constant.EVENT_GeneralError;
		case Constant.ERR_UnsupportProc:
			return Constant.EVENT_UnsupportProc;
		case Constant.ERR_IVALID_PARAM:
			return Constant.EVENT_IVALID_PARAM;
		case Constant.ERR_IVALID_Teminal:
			return Constant.EVENT_IVALID_Teminal;
			// //////////////////////////////////////////////////////////////////
			// ////
			// //
			// #define EVENT_TelDialOut 0x10001000 // �绰�Ⲧ���
			// #define EVENT_TDO_InvalidTelNum (EVENT_TelDialOut+1)
			// #define EVENT_TDO_DestBusy (EVENT_TelDialOut+2)
			// #define EVENT_TDO_NoAnswer (EVENT_TelDialOut+3)
		case Constant.ERR_OutOfService:
			return Constant.EVENT_TDO_OutOfService;
		case Constant.ERR_NothingTelNum:
			return Constant.EVENT_TDO_NothingTelNum;
		case Constant.Err_UnsupportedMedia:
			return Constant.EVENT_TDO_UnsupportedMedia;
		case Constant.ERR_MediaUnmatch:
			return Constant.EVENT_TDO_MediaUnmatch;
		case Constant.ERR_TerminalPowerOff:
			return Constant.EVENT_TDO_TerminalPowerOff;
		case Constant.ERR_TerminalRefuse:
			return Constant.EVENT_TDO_TerminalRefuse;
		case Constant.ERR_TerminalCannotFind:
			return Constant.EVENT_TDO_TerminalCannotFind;
			// #define EVENT_SmsError 0x10002000 // �������
		case Constant.ERR_NO_MSG:
			return Constant.EVENT_SmsError;
		case Constant.ERR_ContentMax:
			return Constant.EVENT_Sms_ContentMax;
		case Constant.ERR_FeeCode:
			return Constant.EVENT_Sms_FeeCodeError;
		case Constant.ERR_ServiceType:
			return Constant.EVENT_Sms_ServiceTypeError;
		case Constant.ERR_SPNumber:
			return Constant.EVENT_Sms_SPNumberError;
		case Constant.ERR_CorpID:
			return Constant.EVENT_Sms_CorpIDError;
		case Constant.ERR_ChargeNumber:
			return Constant.EVENT_Sms_ChargeNumberError;
		case Constant.ERR_PhoneNumber:
			return Constant.EVENT_Sms_PhoneNumberError;
		case Constant.ERR_UnsptChargeNum:
			return Constant.EVENT_Sms_UnsptChargeNumber;
		case Constant.ERR_OpenServer:
			return Constant.EVENT_Sms_OpenServerError;
		case Constant.ERR_NoConnect:
			return Constant.EVENT_Sms_NoConnect;
		case Constant.ERR_RouteDest:
			return Constant.EVENT_Sms_RouteDestError;
			// ASR #define EVENT_ASRError 0x10003000 // ASR���
		case Constant.ERR_ASR_TooMuchSpeech:
			return Constant.EVENT_ASR_TooMuchSpeech;
		case Constant.ERR_ASR_RecSlow:
			return Constant.EVENT_ASR_RecSlow;
		case Constant.ERR_ASR_SpeechTooEarly:
			return Constant.EVENT_ASR_SpeechTooEarly;
		case Constant.ERR_ASR_MemRecord:
			return Constant.EVENT_ASR_MemRecordError;
		case Constant.ERR_ASR_PlayPrompt:
			return Constant.EVENT_ASR_PlayPromptError;
		case Constant.ERR_ASR_Reject:
			return Constant.EVENT_ASR_Reject;

		default:
			return Constant.EVENT_GeneralError;
		}
	}

	public static String transEventToString(int eventType, boolean isMix)
	{
		if ((eventType & EVENT_UserEvent) == EVENT_UserEvent)
		{
			return "UserEvent";
		}

		switch (eventType)
		{
		case EVENT_ForceExit:
			return "ForceExit";
		case EVENT_DestBusy:
			return DestBusy;
		case EVENT_NoFaxDevice:
			return NoFaxDevice;
		case EVENT_NoCall:
			return NoCall;
		case EVENT_InvalidFile:
			return InvalidFile;
		case EVENT_DiskFull:
			return DiskFull;
		case EVENT_NoAnswer:
			return NoAnswer;
		case EVENT_TimeOut:
			return TimeOut;
		case EVENT_InvalidTelNum:
			return InvalidTelNum;
		case EVENT_CustomHangup:
			return CustomHangup;
		case EVENT_ToAgent:
			return ToAgent;
		case EVENT_GeneralError:
			return GeneralError;
		case EVENT_No_Error:
			return No_Error;
		case EVENT_ScriptRunError:
			return ScriptRunError;
		case EVENT_CantFindUSMLFile:
			return CantFindUSMLFile;
		case EVENT_InvalidUSMLFile:
			return InvalidUSMLFile;
		case EVENT_UnsupportProc:
			return UnsupportProc;
		case EVENT_IVALID_PARAM:
			return InvalidParam;
		case EVENT_IVALID_Teminal:
			return InvalidTerminal;
		case EVENT_No_Authorization:
			return No_Authorization;
		case EVENT_InvalidAuthorization:
			return InvalidAuthorization;
		case EVENT_Over_ValidDateTime:
			return Over_ValidDateTime;
		case EVENT_InvalidNodeCode:
			return InvalidNodeCode;
		case EVENT_UnsupportedMedia:
			return UnsupportedMedia;
		case EVENT_MediaUnmatch:
			return MediaUnmatch;
		case EVENT_StackOverflow:
			return StackOverflow;
		case Constant.EVENT_InsufficientResource:
			return InsufficientResource;
		case EVENT_TelDialOut:
		case EVENT_TDO_InvalidTelNum:
			if (!isMix)
			{
				return "EVENT_TDO_InvalidTelNum";
			}
		case EVENT_TDO_DestBusy:
			if (!isMix)
			{
				return "EVENT_TDO_DestBusy";
			}
		case EVENT_TDO_NoAnswer:
			if (!isMix)
			{
				return "EVENT_TDO_NoAnswer";
			}
		case EVENT_TDO_OutOfService:
			if (!isMix)
			{
				return "EVENT_TDO_OutOfService";
			}
		case EVENT_TDO_NothingTelNum:
			if (!isMix)
			{
				return "EVENT_TDO_NothingTelNum";
			}
		case Constant.EVENT_TDO_UnsupportedMedia:
			if (!isMix)
			{
				return "EVENT_TDO_UnsupportedMedia";
			}
		case Constant.EVENT_TDO_MediaUnmatch:
			if (!isMix)
			{
				return "EVENT_TDO_MediaUnmatch";
			}
		case EVENT_TDO_TerminalPowerOff:
			if (!isMix)
			{
				return "EVENT_TDO_TerminalPowerOff";
			}
		case EVENT_TDO_TerminalRefuse:
			if (!isMix)
			{
				return "EVENT_TDO_TerminalRefuse";
			}
		case EVENT_TDO_TerminalCannotFind:
			if (!isMix)
			{
				return "EVENT_TDO_TerminalCannotFind";
			}

			return TelDialOut;

		case EVENT_SmsError:
		case EVENT_Sms_ContentMax:
		case EVENT_Sms_FeeCodeError:
		case EVENT_Sms_ServiceTypeError:
		case EVENT_Sms_SPNumberError:
		case EVENT_Sms_CorpIDError:
		case EVENT_Sms_ChargeNumberError:
		case EVENT_Sms_PhoneNumberError:
		case EVENT_Sms_UnsptChargeNumber:
		case EVENT_Sms_OpenServerError:
		case EVENT_Sms_NoConnect:
		case EVENT_Sms_RouteDestError:
			return SmsError;
		case EVENT_ASRError:
		case EVENT_ASR_TooMuchSpeech:
			if (!isMix)
			{
				return "EVENT_ASR_TooMuchSpeech";
			}
		case EVENT_ASR_RecSlow:
			if (!isMix)
			{
				return "EVENT_ASR_RecSlow";
			}
		case EVENT_ASR_SpeechTooEarly:
			if (!isMix)
			{
				return "EVENT_ASR_SpeechTooEarly";
			}
		case EVENT_ASR_MemRecordError:
			if (!isMix)
			{
				return "EVENT_ASR_MemRecordError";
			}
		case EVENT_ASR_PlayPromptError:
			if (!isMix)
			{
				return "EVENT_ASR_PlayPromptError";
			}
		case EVENT_ASR_Reject:
			if (!isMix)
			{
				return "EVENT_ASR_Reject";
			}
			return ASRError;
		default:
			return "";
		}
	}
}
