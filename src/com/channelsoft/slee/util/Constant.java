package com.channelsoft.slee.util;

import java.util.Locale;

public class Constant
{
	public static final int ASR_GRAMMAR_COMPILED = 1; // 语法文件经过编译

	public static final int ASR_GRAMMAR_UNCOMPILED = 2; // 语法文件未经过编译

	public static final String ASRError = "ASRError"; // ASR相关错误，

	public static final String ATSESYSWAV_CantFindFile = "CantFindFile.vox";

	public static final String ATSESYSWAV_DiskFull = "DiskFull.vox";

	// 具体错误通过m_LastError取得

	// *****************************************************************
	// 定义ATSE系统内部使用的语音文件名
	public static final String ATSESYSWAV_GeneralError = "GeneralError.vox";

	public static final String ATSESYSWAV_InvalidFile = "InvalidFile.vox";

	public static final String ATSESYSWAV_InvalidUSMLFile = "InvalidUSMLFile.vox";

	public static final String ATSESYSWAV_ScriptRunError = "ScriptRunError.vox";

	public static final String ATSESYSWAV_Timeout = "Timeout.vox";

	public static final String BREAK_LIST_ALL = "0123456789*#";

	public static final String CantFindUSMLFile = "CantFindUSMLFile"; // 找不到指定的USML文件

	// 缺省的保存通道的变量的名称
	public static final String ChannelDNVarName = "m_ChannelDN";

	public static final String CHINA_CURRENCY = java.util.Currency.getInstance(
			Locale.CHINA).getSymbol();

	public static final String CS_ATSEMgService = "CS_ATSEMgService";

	public static final String CS_ATSESrvManagerService = "CS_ATSESrvManagerService";

	public static final String CS_USMessageMgService = "CS_USMessageMgService";

	public static final String Currency = "Currency";

	public static final String CustomHangup = "CustomHangup"; // 客户挂断

	public static final String Date = "Date";

	public static final String DefEventVarName = "m_StartEventType";

	public static final String DefLastErrorVarName = "m_LastError";

	// 缺省的语言类型变量的名称
	public static final String DefPronLangTypeVarName = "m_PronLanguage";

	public static final String DefResultVarName = "m_PrevResult";

	public static final String DestBusy = "DestBusy"; // 目标忙

	public static final int DeviceStatu_Busy = 1;// 设备当前有呼叫

	// ATS_ChannelStatus;
	public static final int DeviceStatu_Idle = 0;// 设备当前没有呼叫

	public static final String DiskFull = "DiskFull"; // 磁盘空间不足

	public static final String EMail = "EMail";

	public static final String English = "English"; // 英语

	public static final String Equal = "Equal";

	public static final int ERR_ASR_EXCEPTION = 0x151; // ASR异常

	public static final int ERR_ASR_MemRecord = 0x100000C4; // 内存录音失败

	public static final int ERR_ASR_PlayPrompt = 0x100000C5; // 放提示音失败

	public static final int ERR_ASR_RecSlow = 0x100000C2; // 识别超时

	public static final int ERR_ASR_Reject = 0x100000C6; // 没有超过设定的分数，识别被拒绝

	public static final int ERR_ASR_SpeechTooEarly = 0x100000C3; // 用户说的太早了，

	// ASR
	public static final int ERR_ASR_TooMuchSpeech = 0x100000C1; // 用户说的太多

	public static final int ERR_ChannelBusy = 0x100000AD; // 指定通道上已经有呼叫

	// 放提示音之前就开始说话了

	public static final int ERR_ChargeNumber = 0x2001000C; // Fee_terminal_Id错误

	public static final int ERR_Connect_NoFax = 0x100000A9; // 呼叫成功，对方接通，

	public static final int ERR_Connect_Voice = 0x100000AE; // 呼叫成功，对方接通，

	public static final int ERR_ContentMax = 0x20010006; // 超过最大信息长

	// 但是对方不是传真设备
	// 、或者是手动传真设备

	public static final int ERR_CorpID = 0x2001000B; // Msg_src错误

	// 但是对方是语音应答设备

	public static final int ERR_DestBusy = 0x100000A6; // 目标号码忙

	public static final int ERR_Disconnect = 0x100000AC; // IO过程中客户挂断

	public static final int ERR_DiskFull = 0x100000A3; // 磁盘空间已满

	public static final int ERR_EXISTCALL_STATE = 0x106; // 资源上已存在呼叫

	public static final int ERR_FATALERROR = 0x107; // 严重操作失败，需要上层重置通道

	// add by dengsong for SMSMediaServer on 2004.6.23
	// SMSMediaServer Submit_Resp Error
	public static final int ERR_FeeCode = 0x20010005; // 资费代码错

	public static final int ERR_Force_Exit = 0x2fff0000;

	public static final int ERR_GeneralError = 0x100000A2; // 一般性错误

	public static final int ERR_GENERALERROR = 0x100; // 操作失败

	public static final int ERR_INVALID_FILE = 0x102; // 无效的文件名

	public static final int ERR_INVALID_RESID = 0x101; // 无效的资源ID

	public static final int ERR_InvalidFile = 0x100000A4; // 指定的文件名无效

	public static final int ERR_InvalidTelNum = 0x100000A7; // 指定的目标号码无效

	public static final int ERR_INVLAIDARG = 0x108; // 外呼时传入参数错误

	public static final int ERR_IVALID_DN = 0x100000B1; // 指定的DN未配置

	public static final int ERR_IVALID_PARAM = 0x100000B6; // 无效的参数

	public static final int ERR_IVALID_Teminal = 0x100000B7; // 无效的终端，终端号码错误，终端不可用

	public static final int ERR_MediaUnmatch = 0x100000BE; // 搭接时双方媒体不匹配

	public static final int ERR_NO_MSG = 0x100000B4; // 当前没有事件

	public static final int ERR_NoAnswer = 0x100000A8; // 对方指定时间内未应答

	public static final int ERR_NoCall = 0x100000A5; // 当前没有呼叫不能执行指定的操作

	public static final int ERR_NOCALL_STATE = 0x105; // 资源上不存在呼叫

	public static final int ERR_NoConnect = 0x20020002; // 没有可用连接

	public static final int ERR_NOSTART = 0x100000B3; // 指定的DN没有启动

	public static final int ERR_NothingTelNum = 0x100000B9; // 空号

	// SMSMediaServer 内部错误
	public static final int ERR_OpenServer = 0x20020001; // 连接服务器

	// 外拨 七号定义
	public static final int ERR_OutOfService = 0x100000B8; // 不在服务区

	public static final int ERR_PhoneNumber = 0x2001000D; // Dest_terminal_Id错误

	public static final int ERR_REMOTE_DISCONNECT = 0x109; // 永于，同步的媒体操作接口，

	public static final int ERR_RES_ALREADYOPEN = 0x104; // 资源已经被打开

	public static final int ERR_RouteDest = 0x20020011; // 路由目标失败

	// 执行媒体操作时远端挂断

	public static final int ERR_RPC = 0x100000B2; // RPC错误

	public static final int ERR_ServiceType = 0x20010007; // 业务代码错

	public static final int ERR_SPNumber = 0x2001000A; // Src_Id错误

	// ***********************************************************************
	// add by xiawei on 2001.09.17//常量取值与ATSE保持一致
	public static final int ERR_Success = 0x100000A1; // 操作成功

	public static final int ERR_TerminalCannotFind = 0x100000BC; // 无法接通

	public static final int ERR_TerminalPowerOff = 0x100000BA; // 关机

	public static final int ERR_TerminalRefuse = 0x100000BB; // 拒绝应答

	public static final int ERR_TimeOut = 0x100000AA; // 超过了IO参数指定的时间长度

	public static final int ERR_ToAgent = 0x100000AB; // 未使用

	public static final int ERR_TTS_CONVERT = 0x150; // TTS转换失败

	public static final int ERR_UnsptChargeNum = 0x20010009; // 网关不负责服务此计费号码

	public static final int ERR_UNSUPPORTED_STATE = 0x103; // 资源所在状态不支持当前操作

	public static final int Err_UnsupportedMedia = 0x100000BD; // 不支持的媒体类型，例如不支持视频(hzl2009-3-25)

	public static final int ERR_UnsupportProc = 0x100000B5; // 当前媒体服务器不支持此操作

	// ATSEEventDefine
	// expend ATS Event;
	public static final int ERR_User_Event = 0x1fff0000; // 用户自定义事件中断

	public static final int ERR_User_StopIO = 0x100000AF; // 呼叫成功，对方接通，但是对方是语音应答设备

	public static final int EVENT_ASR_MemRecordError = 0x10003004;

	public static final int EVENT_ASR_PlayPromptError = 0x10003005;

	public static final int EVENT_ASR_RecSlow = 0x10003002;

	public static final int EVENT_ASR_Reject = 0x10003006;

	public static final int EVENT_ASR_SpeechTooEarly = 0x10003003;

	public static final int EVENT_ASR_TooMuchSpeech = 0x10003001;

	public static final int EVENT_ASRError = 0x10003000; // ASR相关

	public static final int EVENT_CantFindUSMLFile = 0x100000AE; //

	public static final int EVENT_CONNECT = 0x100000C9; // 呼叫接通（对方摘机）

	public static final int EVENT_CustomHangup = 0x100000AC; //

	public static final int EVENT_DestBusy = 0x100000A6; //

	public static final int Event_Disconnect = 0x1000000A; // 呼叫挂断（对方或者本方挂机）

	public static final int EVENT_DiskFull = 0x100000A3; //

	public static final int EVENT_End = 0x100000B2; //

	public static final int EVENT_ExitWorkflow = 0x2fff0004; //

	public static final int EVENT_ForceExit = 0x2fff0000; //

	public static final int EVENT_GeneralError = 0x100000A2; //

	public static final int EVENT_GotoService = 0x2fff0003; //

	public static final int EVENT_HOOKOFF = 0x100000C2; // 摘机(己方)

	public static final int EVENT_HOOKON = 0x100000C3; // 挂机(己方)

	public static final int Event_InboundCall = 0x10000001; // 呼叫进入

	public static final int EVENT_InsufficientResource = 0x10000000; //

	public static final int EVENT_InvalidAuthorization = 0x100000C2; // 服务授权证书错误

	public static final int EVENT_InvalidFile = 0x100000A4; //

	public static final int EVENT_InvalidNodeCode = 0x100000C4; // 服务不能在指定节点运行

	public static final int EVENT_InvalidTelNum = 0x100000A7; //

	public static final int EVENT_InvalidUSMLFile = 0x100000A0; //

	public static final int EVENT_IVALID_PARAM = 0x100000BB; // 无效的参数

	public static final int EVENT_IVALID_Teminal = 0x100000BC; // 无效的终端，终端号码错误，

	public static final int EVENT_Loop_Break = 0x100000B7; //

	public static final int EVENT_Loop_continue = 0x100000B6; //

	// 终端不可用

	public static final int EVENT_MediaUnmatch = 0x100000CB; // 搭接时双方媒体不匹配

	public static final int EVENT_No_Authorization = 0x100000C1; // 服务未授权

	public static final int EVENT_No_Error = 0x100000A1; //

	public static final int EVENT_NoAnswer = 0x100000A8; //

	public static final int EVENT_NoCall = 0x100000A5; //

	public static final int EVENT_NoFaxDevice = 0x100000A9; //

	public static final int EVENT_Over_ValidDateTime = 0x100000C3; // 服务超过了有效时间

	public static final int EVENT_PauseService = 0x2fff0001; //

	public static final int EVENT_ResumeService = 0x2fff0002; //

	public static final int EVENT_Script_End = 0x100000B3; //

	public static final int EVENT_ScriptRunError = 0x100000AD; //

	public static final int EVENT_Sms_ChargeNumberError = 0x10002006;

	public static final int EVENT_Sms_ContentMax = 0x10002001;

	public static final int EVENT_Sms_CorpIDError = 0x10002005;

	public static final int EVENT_Sms_FeeCodeError = 0x10002002;

	public static final int Event_SMS_MO = 0x20000001; // 短信的MO

	public static final int EVENT_Sms_NoConnect = 0x1000200A;

	public static final int EVENT_Sms_OpenServerError = 0x10002009;

	public static final int EVENT_Sms_PhoneNumberError = 0x10002007;

	public static final int Event_SMS_Report = 0x20000010; // 短信的Report

	public static final int EVENT_Sms_RouteDestError = 0x1000200B;

	public static final int EVENT_Sms_ServiceTypeError = 0x10002003;

	public static final int EVENT_Sms_SPNumberError = 0x10002004;

	public static final int EVENT_Sms_UnsptChargeNumber = 0x1000200;

	public static final int EVENT_SmsError = 0x10002000; // 短信相关

	public static final int EVENT_StackOverflow = 0x100000B8; // 调用堆栈溢出

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

	public static final int EVENT_TDX_GETDIGIT = 0x100000C6; // 收键结束

	public static final int EVENT_TDX_PLAY = 0x100000C4; // 播放结束

	public static final int EVENT_TDX_RECORD = 0x100000C5; // 录音结束

	public static final int EVENT_TDX_RECV = 0x100000C8; // 传真接受结束

	public static final int EVENT_TDX_SEND = 0x100000C7; // 传真发送结束

	// PrevResult return EVENT_TelDialOut, LastError return EVENT_TDO_XXX
	public static final int EVENT_TelDialOut = 0x10001000; // 电话外拨相关

	public static final int EVENT_TimeOut = 0x100000AA; //

	public static final int EVENT_To_PrevNode = 0x100000B5; //

	public static final int EVENT_To_RootNode = 0x100000B4; //

	public static final int EVENT_ToAgent = 0x100000AB; //

	public static final int EVENT_UnsupportedMedia = 0x100000CA; // 服务不能使用指定媒体

	public static final int EVENT_UnsupportProc = 0x100000BA; // 当前媒体服务器不支持此操作

	public static final int EVENT_User_StopIO = 0x100000AF; //

	public static final int EVENT_UserEvent = 0x1fff0000; //

	public static final int Event_UssdDisconnect = 0x3000000A; // 呼叫挂断（对方或者本方挂机）

	public static final int Event_UssdInboundCall = 0x30000001; // 呼叫进入

	// 事件工作流指定的事件类型变量名称
	public static final String EventVarNameTag = "EventVarName";

	public static final String FemaleCantonese = "Female-Cantonese"; // 女粤语

	public static final String FemaleEnglish = "Female-English"; // 女英语

	public static final String FemaleMandarin = "Female-Mandarin"; // 女普通话

	public static final int FILENAME_LEN = 256;

	public static final String Float = "Float";

	public static final int GATEWAY_SUCCESS = 0x0; // 操作成功

	public static final String GeneralError = "GeneralError"; // 一般性错误

	public static final String GuangdongDialect = "GuangdongDialect"; // 粤语

	public static final String HIDKEY = "_QNSoft_Wangxf_Purple_Glacier";

	public static final String InsufficientResource = "InsufficientResource"; // 调用堆栈溢出

	public static final String Int = "Int";

	public static final int INT_FemaleCantonese = 3;

	public static final int INT_FemaleEnglish = 5;

	public static final int INT_FemaleMandarin = 1;

	public static final int INT_MaleCantonese = 4;

	public static final int INT_MaleEnglish = 6;

	public static final int INT_MaleMandarin = 2;

	public static final int INT_Undefine = 0;

	public static final String InvalidAuthorization = "InvalidAuthorization"; // 服务授权证书错误

	public static final String InvalidFile = "InvalidFile"; // 无效的文件

	public static final String InvalidNodeCode = "InvalidNodeCode"; // 服务不能在指定节点运行

	public static final String InvalidParam = "InvalidParam"; // 找不到指定的USML文件

	public static final String InvalidTelNum = "InvalidTelNum"; // 无效的电话号码

	public static final String InvalidTerminal = "InvalidTerminal"; // 无效的USML文件

	public static final String InvalidUSMLFile = "InvalidUSMLFile"; // 无效的USML文件

	public static final int IOST_BUSY = 0x02;

	public static final int IOST_IDLE = 0x01;

	public static final String JavaScript = "JavaScript";

	public static final String Less = "Less";

	public static final String LessAndEqual = "LessAndEqual";

	public static final String LocalDll = "LocalDll";

	public static final String MaleCantonese = "Male-Cantonese"; // 男粤语

	public static final String MaleEnglish = "Male-English"; // 男英语

	public static final String MaleMandarin = "Male-Mandarin"; // 男普通话

	public static final String Mandarin = "Mandarin"; // 普通话

	public static final String MediaUnmatch = "MediaUnmatch"; // 搭接时双方媒体不匹配

	public static final int MG_FAIL = 0x1002; // 异步操作失败

	// MG event
	public static final int MG_SUCCESS = 0x1001; // 异步操作成功

	public static final int MG_TDX_ASR = 0x10050; // 语音识别完成

	public static final int MG_TDX_GETDIGIT = 0x1006; // 收键结束

	public static final int MG_TDX_PLAY = 0x1004; // 播放结束

	public static final int MG_TDX_RECORD = 0x1005; // 录音结束

	public static final int MG_TFX_FAIL = 0x10009; // 传真操作失败

	public static final int MG_TFX_RECV = 0x10008; // 传真接受结束

	public static final int MG_TFX_SEND = 0x1007; // 传真发送结束

	public static final int MGREASON_ARS_ABORT = 0x59; // 识别被取消

	public static final int MGREASON_ARS_PLAYPROMPT = 0x58; // 放提示音失败

	public static final int MGREASON_ASR_EXCEPTION = 0x56; // 有其他异常发生

	public static final int MGREASON_ASR_MEMRECORD = 0x57; // 内存录音失败

	public static final int MGREASON_ASR_NO_SPEECH = 0x51; // 放完提示音后用户长时间不说话

	public static final int MGREASON_ASR_REC_SLOW = 0x53; // 识别超时

	public static final int MGREASON_ASR_RECGNIZED = 0x50; // 识别完成，有结果返回

	// 放提示音之前就开始说话了

	public static final int MGREASON_ASR_REJECT = 0x55; // 没有超过设定的分数，识别被拒绝

	public static final int MGREASON_ASR_SPEECH_TOO_EARLY = 0x54; // 用户说的太早了，

	public static final int MGREASON_ASR_TOOMUCH_SPEECH = 0x52; // 用户说的太多

	// MGREASON_ ASR_NO_SPEECH 放完提示音后用户长时间不说话 use ERR_TimeOut

	public static final int MGREASON_TM_DIGIT = 0x02; // 收到指定键

	public static final int MGREASON_TM_DISCONNECT = 0x05; // 操作过程中设备挂机

	// MGREASON_ASR_EXCEPTION 有其他异常发生 use ERR_GeneralError

	// 事件发生的原因
	public static final int MGREASON_TM_EOD = 0x01; // 文件播放操作结束

	public static final int MGREASON_TM_INVALIDFILE = 0x07; // 无效的文件（例如文件格式错误）

	public static final int MGREASON_TM_MAXDTMF = 0x03; // 收到的DTMF数达到指定最大数

	public static final int MGREASON_TM_MAXTIME = 0x04; // 操作时间达到指定时长

	public static final int MGREASON_TM_USRSTOP = 0x06; // 操作被外界终止

	public static final String More = "More";

	public static final String MoreAndEqual = "MoreAndEqual";

	public static final String No_Authorization = "No_Authorization"; // 服务未授权

	// 节点操作结果和事件类型
	public static final String No_Error = "No_Error"; // 没有错误

	public static final String NoAnswer = "NoAnswer"; // 对方未应答

	public static final String NoCall = "NoCall"; // 当前没有呼叫

	public static final String NoFaxDevice = "NoFaxDevice"; // 对方非传真设备

	public static final int NUMBER_LEN = 32;

	public static final int VIDEOPARAM_LEN = 5;

	// 比较操作符
	public static final String OperatorIn = "In";

	public static final String Over_ValidDateTime = "Over_ValidDateTime"; // 服务超过了有效时间

	public static final String PronouncingLanguage = "PronouncingLanguage";

	public static final int REASON_DESTBUSY = 0x51; // 被叫忙

	public static final int REASON_DESTFAXTONE = 0x54; // 被叫为传真设备

	public static final int REASON_DESTNOANSWER = 0x52; // 被叫未应答

	public static final int REASON_DESTNOTFAX = 0x55; // 被叫为非传真设备

	public static final int REASON_DESTUNKNOWN = 0x53; // 被叫状态未知

	public static final int REASON_INVLAIDNUM = 0x56; // 被叫号码非法

	public static final int REASON_MEDIA_NEGOTIATE = 0x59; // 媒体协商失败，如3G-324M失败

	public static final int REASON_NORMAL = 0x50; // 正常

	public static final int REASON_NUMBER_NULL = 0x2c;

	public static final int REASON_OUT_AREA = 0x2b;

	public static final int REASON_POWER_OFF = 0x5a;

	public static final int REASON_REFUSE = 0x2d;

	public static final int REASON_UNSUPPORTED_MEDIA = 0x58; // 不支持的媒体类型，例如不支持视频(hzl2009-3-25)

	public static final int REASON_VOICE_DETECTED = 0x57; // 检测到语音应答

	// 服务节点指定的结果变量名称
	public static final String ResultVarNameTag = "ResultVarName";

	//public static final int RM_AUDIO_ONLY = 5;

	// (hzl2009-3-25)

	public static final int RM_DSPDUP = 2;

	public static final int RM_FULLDUP = 0;

	public static final int RM_HALFDUP = 1;

	//双方音视频
	public static final int RM_BOTHVIDEO = 3;

	//只传ori音视频给Dest
	public static final int RM_ORIVIDEO = 4;
	
	//只传Dest音视频给ori
	public static final int RM_DESTVIDEO = 5;
	
	public static final String ScriptRunError = "ScriptRunError"; // 脚本运行错误

	public static final String ServerPage = "ServerPage";

	// 则EventData字段存储呼叫建立时
	// ，对方设备的情况

	public static final int SG_CALLACCEPTED = 0x2002; // 已经成功Accept入呼叫, --add by

	public static final int SG_CALLALERTING = 0x2013; // 外呼成功,被叫已收到连接请求

	public static final int SG_CALLCONNECTED = 0x2003; // 呼叫建立，如果是系统发起的外呼，

	public static final int SG_CALLDISCONNECTED = 0x2004; // 对方挂机

	// hzl--

	public static final int SG_CALLRELEASED = 0x2005; // 呼叫清除

	public static final int SG_DAILED = 0x2008; // 呼叫发起成功

	// EventData字段存储呼叫的CRN号码

	public static final int SG_FATALERROR = 0x2007; // 发生严重错误,可能需要重新初始化设备资源,暂不使用

	// 事件ID
	public static final int SG_INCOMINGCALL = 0x2001; // 入呼叫到达，如果采用globalcall协议，

	public static final int SG_LINE_DOWN = 0x2010; // 资源停止工作

	public static final int SG_LINE_IN_SERVICE = 0x2011; // 话路通道因为网络故障，暂不可用

	public static final int SG_LINE_OUT_SERVICE = 0x2012; // 话路通道网络故障恢复，变为可用

	public static final int SG_LINE_START = 0x2009; // 资源开始工作

	public static final int SG_NO_ENOUGH_DIAL_RES = 0x2020; // 没有足够的外部资源

	public static final int SG_TASKFAIL = 0x2006; // 任务执行失败,暂不使用

	public static final int SGST_NULL = 0x00; // 呼叫处于NULL状态，已经被释放

	public static final int SGST_OTHERS = 0x01; // 呼叫处于其他状态，未被释放

	// 具体错误通过m_LastError取得

	// 系统定义
	public static final int SHORT_STRING_LENGTH = 16;

	public static final String SmsError = "SmsError"; // 短信相关错误，

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

	public static final String StackOverflow = "StackOverflow"; // 调用堆栈溢出

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

	// IOProcess 类型
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
	
	// 通道类型
	public static final String Tel = "Tel";

	// 具体错误通过m_LastError取得

	public static final String TelDialOut = "TelDialOut"; // 电话外拨相关事件，

	public static final String TelNumber = "TelNumber";

	public static final String Time = "Time";

	public static final String TimeOut = "TimeOut"; // 超时

	public static final String ToAgent = "ToAgent"; // 转座席

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_BUF = 1; // 把文本内存转换为语音内存播放

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_BUF1 = 1; // 把文本内存转换为语音内存播放

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_FILE = 3; // 把文本内存转换为语音文件后再播放

	public static final int TTS_TYPE_TEXT_BUF_TO_AUDIO_FILE3 = 3; // 把文本内存转换为语音文件后再播放

	public static final int TTS_TYPE_TEXT_FILE_TO_AUDIO_BUF = 2; // 把文本文件转换为语音内存播放

	public static final int TTS_TYPE_TEXT_FILE_TO_AUDIO_BUF2 = 2; // 把文本文件转换为语音内存播放

	public static final int TTS_TYPE_TEXT_FILE_TO_AUDIO_FILE = 4; // 把文本文件转换为语音文件后再播放

	public static final int TTS_VOICE_LIB_MAN = 2; // 男声音库

	public static final int TTS_VOICE_LIB_MAN2 = 2; // 男声音库

	public static final int TTS_VOICE_LIB_WOMAN = 1; // 女声音库

	public static final int TTS_VOICE_LIB_WOMAN1 = 1; // 女声音库

	public static final int ULONG_STRING_LENGTH = 128;

	public static final String Unequal = "Unequal";

	public static final String UnsupportedMedia = "UnsupportedMedia"; // 服务不能使用指定媒体

	public static final String UnsupportProc = "UnsupportProc"; // 无效的USML文件

	// 变量类型
	public static final String USMLEvent = "USMLEvent";

	// 语言类型
	public static final String VBScript = "VBScript";

	public static final String VFILE_0 = "Number_0.vox";

	public static final String VFILE_1 = "Number_1.vox";

	public static final String VFILE_2 = "Number_2.vox";

	public static final String VFILE_3 = "Number_3.vox";

	// 事件携带数据: EventData

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

	; // 线路呼叫状态定义

	public static final String VFILE_Ten = "Unit_ten.vox";

	public static final String VFILE_TenMillion = "Unit_TenMillion.vox";

	; // IO操作状态

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

	public static final int VGCP_MEDIA_TYPE_AUDIO = 1; // 媒体类型-音频

	public static final int VGCP_MEDIA_TYPE_VIDEO = 2; // 媒体类型-视频

	public static final String WAP = "WAP";

	public static final String Web = "Web";

	public static int convertError2Event(int atsError)
	{
		// 转换ATS错误代码到USE错误代码
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
			// #define EVENT_TelDialOut 0x10001000 // 电话外拨相关
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
			// #define EVENT_SmsError 0x10002000 // 短信相关
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
			// ASR #define EVENT_ASRError 0x10003000 // ASR相关
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
