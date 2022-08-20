/**
 * 
 */
package com.channelsoft.slee.callagent.ssr2;

/**
 * @author wuxz
 */
public class VGCPConstants
{
	static final int VGCP_ACK_CNF_ADJCONFVOLUME = 0x08030001;

	static final int VGCP_ACK_CNF_CLOSECONF = 0x08030003;

	static final int VGCP_ACK_CNF_CREATECONF = 0x08030002;

	static final int VGCP_ACK_CNF_GETROOMINFO = 0x08030008;

	static final int VGCP_ACK_CNF_GETSYSTEMCONFRESINFO = 0x08030009;

	static final int VGCP_ACK_CNF_GETTALKERS = 0x0803000A;

	static final int VGCP_ACK_CNF_GETUSERATTRIB = 0x0803000B;

	static final int VGCP_ACK_CNF_GETVOXRESOURCENUMBER = 0x0803000C;

	static final int VGCP_ACK_CNF_JOIN = 0x0803000D;

	static final int VGCP_ACK_CNF_LEAVE = 0x0803000E;

	static final int VGCP_ACK_CNF_LEAVEPRIVATETALK = 0x08030013;

	static final int VGCP_ACK_CNF_PLAY = 0x08030004;

	static final int VGCP_ACK_CNF_PLAYLIST = 0x08030010;

	static final int VGCP_ACK_CNF_PLAYTTS = 0x08030011;

	static final int VGCP_ACK_CNF_PRIVATETALK = 0x08030012;

	static final int VGCP_ACK_CNF_RECORD = 0x08030005;

	static final int VGCP_ACK_CNF_SETROOMINFO = 0x08030014;

	static final int VGCP_ACK_CNF_SETUSERATTRIB = 0x08030015;

	static final int VGCP_ACK_CNF_STOPPLAY = 0x08030006;

	static final int VGCP_ACK_CNF_STOPRECORD = 0x08030007;

	static final int VGCP_ACK_CNFM_PLAY = 0x08040001;

	static final int VGCP_ACK_CNFM_PLAYLIST = 0x08040005;

	static final int VGCP_ACK_CNFM_PLAYTTS = 0x08040004;

	static final int VGCP_ACK_CNFM_RECEIVEFAX = 0x08040007;

	static final int VGCP_ACK_CNFM_RECORD = 0x08040002;

	static final int VGCP_ACK_CNFM_SENDDTMFS = 0x08040003;

	static final int VGCP_ACK_CNFM_SENDFAX = 0x08040006;

	static final int VGCP_ACK_CNFM_STOPIO = 0x08040008;

	static final int VGCP_ACK_MG_ADJSPEED = 0x08020001;

	static final int VGCP_ACK_MG_ADJVOLUME = 0x08020002;

	static final int VGCP_ACK_MG_ASR = 0x0802000C;

	static final int VGCP_ACK_MG_FLUSHBUFFER = 0x08020005;

	static final int VGCP_ACK_MG_GETIOSTATE = 0x0802000E;

	static final int VGCP_ACK_MG_PLAYFILE = 0x08020006;

	static final int VGCP_ACK_MG_PLAYLIST = 0x0802000A;

	static final int VGCP_ACK_MG_PLAYTTS = 0x0802000B;

	static final int VGCP_ACK_MG_RECEIVEDTMFS = 0x08020004;

	static final int VGCP_ACK_MG_RECEIVEFAX = 0x08020009;

	static final int VGCP_ACK_MG_RECORDFILE = 0x08020007;

	static final int VGCP_ACK_MG_SENDDTMFS = 0x08020003;

	static final int VGCP_ACK_MG_SENDFAX = 0x08020008;

	static final int VGCP_ACK_MG_STOPIO = 0x0802000D;

	static final int VGCP_ACK_OAM_BLOCKCHANNEL = 0x08060005;

	static final int VGCP_ACK_OAM_CLIENTHOLD = 0x08060002;

	static final int VGCP_ACK_OAM_CLIENTUNHOLD = 0x08060003;

	static final int VGCP_ACK_OAM_CLOSEALLCONF = 0x08060007;

	static final int VGCP_ACK_OAM_GETCLIENTSTATE = 0x08060008;
	
	static final int VGCP_ACK_OAM_REGISTER = 0x08060001;

	static final int VGCP_ACK_OAM_RESETCHANNEL = 0x08060004;

	static final int VGCP_ACK_OAM_UNBLOCKCHANNEL = 0x08060006;

	static final int VGCP_ACK_RM_ADDCHNL2VOICEMIXER = 0x08050007;

	static final int VGCP_ACK_RM_BLINDCLOSECONF = 0x08050006;

	static final int VGCP_ACK_RM_BLINDCREATECONF = 0x08050005;

	static final int VGCP_ACK_RM_CLOSEVOICEMIXER = 0x08050008;

	static final int VGCP_ACK_RM_ROUTE2HOLDMUSIC = 0x08050003;

	static final int VGCP_ACK_RM_ROUTETWORES = 0x08050001;

	static final int VGCP_ACK_RM_UNROUTE2HOLDMUSIC = 0x08050004;

	static final int VGCP_ACK_RM_UNROUTETWORES = 0x08050002;

	static final int VGCP_ACK_SG_ACCEPTCALL = 0x08010001;

	static final int VGCP_ACK_SG_ANSWERCALL = 0x08010002;

	static final int VGCP_ACK_SG_BLINDMAKECALL = 0x08010004;

	static final int VGCP_ACK_SG_CALLTIMEOUT = 0x08010006;

	static final int VGCP_ACK_SG_MAKECALL = 0x08010005;

	static final int VGCP_ACK_SG_RELEASECALL = 0x08010003;

	static final int VGCP_CMD_CNF_ADJVOLUME = 0x00030001;

	static final int VGCP_CMD_CNF_CLOSECONF = 0x00030003;

	static final int VGCP_CMD_CNF_CREATECONF = 0x00030002;

	static final int VGCP_CMD_CNF_GETROOMINFO = 0x00030008;

	static final int VGCP_CMD_CNF_GETSYSTEMCONFRESINFO = 0x00030009;

	static final int VGCP_CMD_CNF_GETTALKERS = 0x0003000A;

	static final int VGCP_CMD_CNF_GETUSERATTRIB = 0x0003000B;

	static final int VGCP_CMD_CNF_GETVOXRESOURCENUMBER = 0x0003000C;

	static final int VGCP_CMD_CNF_JOIN = 0x0003000D;

	static final int VGCP_CMD_CNF_LEAVE = 0x0003000E;

	static final int VGCP_CMD_CNF_LEAVEPRIVATETALK = 0x00030013;

	static final int VGCP_CMD_CNF_PLAY = 0x00030004;

	static final int VGCP_CMD_CNF_PLAYLIST = 0x00030010;

	static final int VGCP_CMD_CNF_PLAYTTS = 0x00030011;

	static final int VGCP_CMD_CNF_PRIVATETALK = 0x00030012;

	static final int VGCP_CMD_CNF_RECORD = 0x00030005;

	static final int VGCP_CMD_CNF_SETROOMINFO = 0x00030014;

	static final int VGCP_CMD_CNF_SETUSERATTRIB = 0x00030015;

	static final int VGCP_CMD_CNF_STOPPLAY = 0x00030006;

	static final int VGCP_CMD_CNF_STOPRECORD = 0x00030007;

	static final int VGCP_CMD_CNFM_PLAY = 0x00040001;

	static final int VGCP_CMD_CNFM_PLAYLIST = 0x00040005;

	static final int VGCP_CMD_CNFM_PLAYTTS = 0x00040004;

	static final int VGCP_CMD_CNFM_RECEIVEFAX = 0x00040007;

	static final int VGCP_CMD_CNFM_RECORD = 0x00040002;

	static final int VGCP_CMD_CNFM_SENDDTMFS = 0x00040003;

	static final int VGCP_CMD_CNFM_SENDFAX = 0x00040006;

	static final int VGCP_CMD_CNFM_STOPIO = 0x00040008;

	static final int VGCP_CMD_MG_ADJSPEED = 0x00020001;

	static final int VGCP_CMD_MG_ADJVOLUME = 0x00020002;

	static final int VGCP_CMD_MG_ASR = 0x0002000C;

	static final int VGCP_CMD_MG_FLUSHBUFFER = 0x00020005;

	static final int VGCP_CMD_MG_GETIOSTATE = 0x0002000E;

	static final int VGCP_CMD_MG_PLAYFILE = 0x00020006;
	
	static final int VGCP_CMD_MG_PLAYINFOONVIDEO = 0x0002000F;

	static final int VGCP_CMD_MG_PLAYLIST = 0x0002000A;

	static final int VGCP_CMD_MG_PLAYTTS = 0x0002000B;

	static final int VGCP_CMD_MG_RECEIVEDTMFS = 0x00020004;

	static final int VGCP_CMD_MG_RECEIVEFAX = 0x00020009;

	static final int VGCP_CMD_MG_RECORDFILE = 0x00020007;

	static final int VGCP_CMD_MG_SENDDTMFS = 0x00020003;

	static final int VGCP_CMD_MG_SENDFAX = 0x00020008;

	static final int VGCP_CMD_MG_STOPIO = 0x0002000D;

	static final int VGCP_CMD_OAM_BLOCKCHANNEL = 0x00060005;

	static final int VGCP_CMD_OAM_CLIENTHOLD = 0x00060002;

	static final int VGCP_CMD_OAM_CLIENTUNHOLD = 0x00060003;

	static final int VGCP_CMD_OAM_CLOSEALLCONF = 0x00060007;

	static final int VGCP_CMD_OAM_GETCLIENTSTATE = 0x00060008;
	
	static final int VGCP_CMD_OAM_REGISTER = 0x00060001;

	static final int VGCP_CMD_OAM_RESETCHANNEL = 0x00060004;

	static final int VGCP_CMD_OAM_UNBLOCKCHANNEL = 0x00060006;

	static final int VGCP_CMD_RM_ADDCHNL2VOICEMIXER = 0x00050007;

	static final int VGCP_CMD_RM_BLINDCLOSECONF = 0x00050006;

	static final int VGCP_CMD_RM_BLINDCREATECONF = 0x00050005;

	static final int VGCP_CMD_RM_CLOSEVOICEMIXER = 0x00050008;

	static final int VGCP_CMD_RM_ROUTE2HOLDMUSIC = 0x00050003;

	static final int VGCP_CMD_RM_ROUTETWORES = 0x00050001;

	static final int VGCP_CMD_RM_UNROUTE2HOLDMUSIC = 0x00050004;

	static final int VGCP_CMD_RM_UNROUTETWORES = 0x00050002;

	static final int VGCP_CMD_SG_ACCEPTCALL = 0x00010001;

	static final int VGCP_CMD_SG_ANSWERCALL = 0x00010002;

	static final int VGCP_CMD_SG_BLINDMAKECALL = 0x00010004;

	static final int VGCP_CMD_SG_CALLTIMEOUT = 0x00010006;

	static final int VGCP_CMD_SG_MAKECALL = 0x00010005;

	static final int VGCP_CMD_SG_RELEASECALL = 0x00010003;

	static final int VGCP_CNF_ATTIB_DUALPLEX = 3; // 听说

	static final int VGCP_CNF_ATTIB_HEARER = 5; // 旁听

	static final int VGCP_CNF_ATTIB_KEEPSILENT = 4; // 不听不说

	static final int VGCP_CNF_ATTIB_LISTENONLY = 1; // 只听

	static final int VGCP_CNF_ATTIB_TALKONLY = 2; // 只说

	static final int VGCP_CNF_ERR_InvalidConfroomID = 0x00010000; // 应认为会议室已经关闭

	static final int VGCP_CNF_ERR_Media_Res_Not_Define = 0x00010006;

	static final int VGCP_CNF_ERR_No_Enough_Res = 0x00010004;

	static final int VGCP_CNF_ERR_Not_Enough_Media_Res = 0x00010007;

	static final int VGCP_CNF_ERR_Port_Already_In_Conf = 0x00010001;

	static final int VGCP_CNF_ERR_Port_Not_Exist = 0x00010003;

	static final int VGCP_CNF_ERR_Port_Not_In_Conf = 0x00010002;

	static final int VGCP_CNF_ERR_Resource_Busy = 0x00010005;

	static final int VGCP_CNF_MEDIA_BIND_TYPE_ALWAYS = 0; // 语音资源与会议一直绑定

	static final int VGCP_CNF_MEDIA_BIND_TYPE_ONCE = 1; // 每一次使用完就释放

	static final int VGCP_CNF_VOX_RES_TYPE_NONE = 0; // 不需要语音资源

	static final int VGCP_CNF_VOX_RES_TYPE_PLAY = 1; // 需要对会议放音

	static final int VGCP_CNF_VOX_RES_TYPE_PLAY_RECORD = 3; // 录音放音都需要

	static final int VGCP_CNF_VOX_RES_TYPE_RECORD = 2; // 需要对会议录音

	static final int VGCP_ERR_ASR_Exception = 23;

	static final int VGCP_ERR_ASR_Unavailable = 24; // ASR不可用

	static final int VGCP_ERR_ClientIDRegistered = 6; // 注册命令的返回值

	static final int VGCP_ERR_Command_Timeout = 5; // 命令执行超时

	static final int VGCP_ERR_Existcall_State = 19;

	static final int VGCP_ERR_GeneralError = 1;

	static final int VGCP_ERR_Invalid_CallID = 15; // CallID错误，应认为该呼叫已经断

	static final int VGCP_ERR_Invalid_File = 17;

	static final int VGCP_ERR_Invalid_Parameter = 14; // 参数非法

	static final int VGCP_ERR_Invalid_ResID = 16;

	static final int VGCP_ERR_InvalidClientID = 8;

	static final int VGCP_ERR_InvalidClientState = 10;

	static final int VGCP_ERR_InvalidPassword = 9;

	static final int VGCP_ERR_Msg_Decode_Fail = 3; // 消息解码失败

	static final int VGCP_ERR_NetworkFailed = 2; // 可能是网络连接未建立

	static final int VGCP_ERR_No_Resource = 25;

	static final int VGCP_ERR_Media_Unmatch = 26; // 用于搭接时双方媒体不匹配

	static final int VGCP_ERR_Nocall_State = 18;

	static final int VGCP_ERR_TTS_Convert = 21; // TTS转换失败

	static final int VGCP_ERR_TTS_Unavailable = 22; // TTS不可用

	static final int VGCP_ERR_UnRegistered = 7; // 客户端尚未成功注册

	static final int VGCP_ERR_Unsupported_Command = 12; // 不支持的命令

	static final int VGCP_ERR_Unsupported_Parameter = 13; // 不支持的参数

	static final int VGCP_ERR_Unsupported_State = 20;

	static final int VGCP_ERR_Unsupported_VGCPVersion = 11;

	static final int VGCP_ERR_VG_Internal = 4; // VG内部错误

	static final int VGCP_EVT_CNF_TDX_GETDTMFS = 0x000A0004;

	static final int VGCP_EVT_CNF_TDX_PLAY = 0x000A0001;

	static final int VGCP_EVT_CNF_TDX_RECORD = 0x000A0002;

	static final int VGCP_EVT_CNF_TDX_SENDDTMFS = 0x000A0003;

	static final int VGCP_EVT_CNFM_TDX_GETDTMFS = 0x000B0004;

	static final int VGCP_EVT_CNFM_TDX_PLAY = 0x000B0001;

	static final int VGCP_EVT_CNFM_TDX_RECORD = 0x000B0002;

	static final int VGCP_EVT_CNFM_TDX_SENDDTMFS = 0x000B0003;

	static final int VGCP_EVT_CNFM_TFX_FAIL = 0x000B0007;

	static final int VGCP_EVT_CNFM_TFX_RECV = 0x000B0006;

	static final int VGCP_EVT_CNFM_TFX_SEND = 0x000B0005;

	static final int VGCP_EVT_MG_TDX_ASR = 0x00090005;

	static final int VGCP_EVT_MG_TDX_GETDTMFS = 0x00090004;

	static final int VGCP_EVT_MG_TDX_PLAY = 0x00090001;

	static final int VGCP_EVT_MG_TDX_RECORD = 0x00090002;

	static final int VGCP_EVT_MG_TDX_SENDDTMFS = 0x00090003;

	static final int VGCP_EVT_MG_TFX_FAIL = 0x00090008;

	static final int VGCP_EVT_MG_TFX_RECV = 0x00090007;

	static final int VGCP_EVT_MG_TFX_SEND = 0x00090006;

	static final int VGCP_EVT_SG_CALLACCEPTED = 0x00080002;

	static final int VGCP_EVT_SG_CALLALERTING = 0x00080007;

	static final int VGCP_EVT_SG_CALLCONNECTED = 0x00080003;

	static final int VGCP_EVT_SG_CALLDISCONNECTED = 0x00080004;

	static final int VGCP_EVT_SG_CALLRELEASED = 0x00080005;

	static final int VGCP_EVT_SG_DAILED = 0x00080006;

	static final int VGCP_EVT_SG_INCOMINGCALL = 0x00080001;

	static final int VGCP_EVT_SG_LINE_DOWN = 0x00080009;

	static final int VGCP_EVT_SG_LINE_IN_SERVICE = 0x0008000A;

	static final int VGCP_EVT_SG_LINE_OUT_SERVICE = 0x0008000B;

	static final int VGCP_EVT_SG_LINE_START = 0x00080008;

	static final int VGCP_EVT_SYS_LINK_DOWN = 0x00070008;

	static final int VGCP_EVT_SYS_LINK_UP = 0x00070007;

	static final int VGCP_EVT_SYS_SSR_SERVICEDOWN = 0x00070006;

	static final int VGCP_EVT_SYS_SSR_SERVICESTART = 0x00070004;

	static final int VGCP_EVT_SYS_SSR_SERVICEUP = 0x00070005;

	static final int VGCP_EVT_SYS_VG_SERVICEDOWN = 0x00070003;

	static final int VGCP_EVT_SYS_VG_SERVICESTART = 0x00070001;

	static final int VGCP_EVT_SYS_VG_SERVICEUP = 0x00070002;

	static final int VGCP_GATEWAY_SUCCESS = 0;

	static final int VGCP_MEDIA_BUSY = 2;

	static final int VGCP_MEDIA_IDLE = 1;

	static final int VGCP_MGREASON_ARS_ABORT = 0x79; // 识别被取消

	static final int VGCP_MGREASON_ARS_PLAYPROMPT = 0x78; // 放提示音失败

	static final int VGCP_MGREASON_ASR_EXCEPTION = 0x76; // 有其他异常发生

	static final int VGCP_MGREASON_ASR_MEMRECORD = 0x77; // 内存录音失败

	static final int VGCP_MGREASON_ASR_NO_SPEECH = 0x71; // 放完提示音后用户长时间不说话

	static final int VGCP_MGREASON_ASR_REC_SLOW = 0x73; // 识别超时

	static final int VGCP_MGREASON_ASR_RECGNIZED = 0x70; // 识别完成，有结果返回

	static final int VGCP_MGREASON_ASR_REJECT = 0x75; // 没有超过设定的分数，识别被拒绝

	static final int VGCP_MGREASON_ASR_SPEECH_TOO_EARLY = 0x74; // 用户说的太早了，放提示音之前就开始说话了

	static final int VGCP_MGREASON_ASR_TOOMUCH_SPEECH = 0x72; // 用户说的太多

	static final int VGCP_MGREASON_TM_DISCONNECT = 0x65; // 操作过程中设备挂机

	static final int VGCP_MGREASON_TM_DTMF = 0x62; // 收到指定键

	static final int VGCP_MGREASON_TM_EOD = 0x61; // 文件播放操作结束或发键结束

	static final int VGCP_MGREASON_TM_INVALIDFILE = 0x67; // 无效的文件（例如文件格式错误）

	static final int VGCP_MGREASON_TM_MAXDTMF = 0x63; // 收到的DTMF数达到指定最大数

	static final int VGCP_MGREASON_TM_MAXTIME = 0x64; // 操作时间达到指定时长

	static final int VGCP_MGREASON_TM_USRSTOP = 0x66; // 操作被外界终止

	static final int VGCP_MSG_ACK_BEGIN = 0x08000000;

	static final int VGCP_MSG_EXT_BEGIN = 0x80000000;

	static final int VGCP_MSG_EXT_CONFIGUPDATE = 0x80000001;

	static final int VGCP_MSG_REPLY_BEGIN = 0x04000000;

	static final int VGCP_MSG_STYLE_ACK = 0x08;

	static final int VGCP_MSG_STYLE_NORMAL = 0x00;

	static final int VGCP_MSG_STYLE_REPLY = 0x04;

	static final int VGCP_MSG_STYLE_RESERVED = 0x80;

	static final int VGCP_MSG_TYPE_CMD_CNF = 0x03;

	static final int VGCP_MSG_TYPE_CMD_CNFM = 0x04;

	static final int VGCP_MSG_TYPE_CMD_MG = 0x02;

	static final int VGCP_MSG_TYPE_CMD_OAM = 0x06;

	static final int VGCP_MSG_TYPE_CMD_RM = 0x05;

	static final int VGCP_MSG_TYPE_CMD_SG = 0x01;

	static final int VGCP_MSG_TYPE_EVT_CNF = 0x0A;

	static final int VGCP_MSG_TYPE_EVT_CNFM = 0x0B;

	static final int VGCP_MSG_TYPE_EVT_MG = 0x09;

	static final int VGCP_MSG_TYPE_EVT_SG = 0x08;

	static final int VGCP_MSG_TYPE_EVT_SYSTEM = 0x07;

	static final int VGCP_REASON_CALLWAITING = 0x15; // 呼叫等待中

	static final int VGCP_REASON_CFB = 0x22; // 遇忙前转

	static final int VGCP_REASON_CFNR = 0x21; // 无应答前转

	static final int VGCP_REASON_CFUC = 0x20; // 无条件前转

	static final int VGCP_REASON_CFUR = 0x23; // 不可及（关机/不在服务区）前转

	static final int VGCP_REASON_DESTBUSY = 0x01; // 被叫忙

	static final int VGCP_REASON_DESTNOANSWER = 0x02; // 被叫未应答

	static final int VGCP_REASON_DESTNOTFAX = 0x52; // 被叫为非传真设备

	static final int VGCP_REASON_DESTUNKNOWN = 0x03; // 被叫状态未知

	static final int VGCP_REASON_FAXTONE_DETECTED = 0x51; // 被叫为传真设备

	static final int VGCP_REASON_INVLAIDNUM = 0x04; // 被叫号码非法（号码不全）

	static final int VGCP_REASON_NORMAL = 0x00; // 正常事件

	static final int VGCP_REASON_NULL_NUMBER = 0x12; // 空号

	static final int VGCP_REASON_OUT_AREA = 0x11; // 不在服务区

	static final int VGCP_REASON_POWER_OFF = 0x10; // 关机

	static final int VGCP_REASON_REFUSE = 0x13; // 拒接

	static final int VGCP_REASON_SUBSCRIBERFREE = 0x14; // 用户空闲

	static final int VGCP_REASON_VOICE_DETECTED = 0x50; // 检测到语音应答

	static final int VGCP_REASON_UNSUPPORTED_MEDIA = 0x30; // 不支持的媒体类型，例如不支持视频

	static final int VGCP_REASON_MEDIA_NEGOTIATE = 0x31; // 媒体协商失败，如3G-324M失败

	static final int VGCP_RELEASE_NORMAL = 0;

	static final int VGCP_RELEASE_REJECT = 1;

	static final int VGCP_REPLY_CNF_ADJVOLUME = 0x04030001;

	static final int VGCP_REPLY_CNF_CLOSECONF = 0x04030003;

	static final int VGCP_REPLY_CNF_CREATECONF = 0x04030002;

	static final int VGCP_REPLY_CNF_GETROOMINFO = 0x04030008;

	static final int VGCP_REPLY_CNF_GETSYSTEMCONFRESINFO = 0x04030009;

	static final int VGCP_REPLY_CNF_GETTALKERS = 0x0403000A;

	static final int VGCP_REPLY_CNF_GETUSERATTRIB = 0x0403000B;

	static final int VGCP_REPLY_CNF_GETVOXRESOURCENUMBER = 0x0403000C;

	static final int VGCP_REPLY_CNF_JOIN = 0x0403000D;

	static final int VGCP_REPLY_CNF_LEAVE = 0x0403000E;

	static final int VGCP_REPLY_CNF_LEAVEPRIVATETALK = 0x04030013;

	static final int VGCP_REPLY_CNF_PLAY = 0x04030004;

	static final int VGCP_REPLY_CNF_PLAYLIST = 0x04030010;

	static final int VGCP_REPLY_CNF_PLAYTTS = 0x04030011;

	static final int VGCP_REPLY_CNF_PRIVATETALK = 0x04030012;

	static final int VGCP_REPLY_CNF_RECORD = 0x04030005;

	static final int VGCP_REPLY_CNF_SETROOMINFO = 0x04030014;

	static final int VGCP_REPLY_CNF_SETUSERATTRIB = 0x04030015;

	static final int VGCP_REPLY_CNF_STOPPLAY = 0x04030006;

	static final int VGCP_REPLY_CNF_STOPRECORD = 0x04030007;

	static final int VGCP_REPLY_CNFM_PLAY = 0x04040001;

	static final int VGCP_REPLY_CNFM_PLAYLIST = 0x04040005;

	static final int VGCP_REPLY_CNFM_PLAYTTS = 0x04040004;

	static final int VGCP_REPLY_CNFM_RECEIVEFAX = 0x04040007;

	static final int VGCP_REPLY_CNFM_RECORD = 0x04040002;

	static final int VGCP_REPLY_CNFM_SENDDTMFS = 0x04040003;

	static final int VGCP_REPLY_CNFM_SENDFAX = 0x04040006;

	static final int VGCP_REPLY_CNFM_STOPIO = 0x04040008;

	static final int VGCP_REPLY_MG_ADJSPEED = 0x04020001;

	static final int VGCP_REPLY_MG_ADJVOLUME = 0x04020002;

	static final int VGCP_REPLY_MG_ASR = 0x0402000C;

	static final int VGCP_REPLY_MG_FLUSHBUFFER = 0x04020005;

	static final int VGCP_REPLY_MG_GETIOSTATE = 0x0402000E;

	static final int VGCP_REPLY_MG_PLAYFILE = 0x04020006;

	static final int VGCP_REPLY_MG_PLAYLIST = 0x0402000A;

	static final int VGCP_REPLY_MG_PLAYTTS = 0x0402000B;

	static final int VGCP_REPLY_MG_RECEIVEDTMFS = 0x04020004;

	static final int VGCP_REPLY_MG_RECEIVEFAX = 0x04020009;

	static final int VGCP_REPLY_MG_RECORDFILE = 0x04020007;

	static final int VGCP_REPLY_MG_SENDDTMFS = 0x04020003;

	static final int VGCP_REPLY_MG_SENDFAX = 0x04020008;

	static final int VGCP_REPLY_MG_STOPIO = 0x0402000D;

	static final int VGCP_REPLY_OAM_BLOCKCHANNEL = 0x04060005;

	static final int VGCP_REPLY_OAM_CLIENTHOLD = 0x04060002;

	static final int VGCP_REPLY_OAM_CLIENTUNHOLD = 0x04060003;

	static final int VGCP_REPLY_OAM_CLOSEALLCONF = 0x04060007;

	static final int VGCP_REPLY_OAM_GETCLIENTSTATE = 0x04060008;
	
	static final int VGCP_REPLY_OAM_REGISTER = 0x04060001;

	static final int VGCP_REPLY_OAM_RESETCHANNEL = 0x04060004;

	static final int VGCP_REPLY_OAM_UNBLOCKCHANNEL = 0x04060006;

	static final int VGCP_REPLY_RM_ADDCHNL2VOICEMIXER = 0x04050007;

	static final int VGCP_REPLY_RM_BLINDCLOSECONF = 0x04050006;

	static final int VGCP_REPLY_RM_BLINDCREATECONF = 0x04050005;

	static final int VGCP_REPLY_RM_CLOSEVOICEMIXER = 0x04050008;

	static final int VGCP_REPLY_RM_ROUTE2HOLDMUSIC = 0x04050003;

	static final int VGCP_REPLY_RM_ROUTETWORES = 0x04050001;

	static final int VGCP_REPLY_RM_UNROUTE2HOLDMUSIC = 0x04050004;

	static final int VGCP_REPLY_RM_UNROUTETWORES = 0x04050002;

	static final int VGCP_REPLY_SG_ACCEPTCALL = 0x04010001;

	static final int VGCP_REPLY_SG_ANSWERCALL = 0x04010002;

	static final int VGCP_REPLY_SG_BLINDMAKECALL = 0x04010004;

	static final int VGCP_REPLY_SG_CALLTIMEOUT = 0x04010006;

	static final int VGCP_REPLY_SG_MAKECALL = 0x04010005;

	static final int VGCP_REPLY_SG_RELEASECALL = 0x04010003;

	static final int VGCP_RM_FULLDUP = 0; // Full duplex

	static final int VGCP_RM_HALFDUP = 1; // Half duplex

	static final int VGCP_TTS_BUF_TO_AUDIO_BUF = 1;

	static final int VGCP_TTS_BUF_TO_AUDIO_FILE = 3;

	static final int VGCP_TTS_FILE_TO_AUDIO_BUF = 2;

	static final int VGCP_TTS_FILE_TO_AUDIO_FILE = 4;

	static final int VGCP_TTS_VOICE_LIB_MAN = 2; // 男声普通话音库

	static final int VGCP_TTS_VOICE_LIB_MAN_CANTONESE = 4; // 男声广东话音库

	static final int VGCP_TTS_VOICE_LIB_MAN_ENGLISH = 6; // 男声英语话音库

	static final int VGCP_TTS_VOICE_LIB_ORIGINAL = 0; // 不改变原来使用的语音库

	static final int VGCP_TTS_VOICE_LIB_WOMAN = 1; // 女声普通话音库

	static final int VGCP_TTS_VOICE_LIB_WOMAN_CANTONESE = 3; // 女声广东话音库

	// connection

	static final int VGCP_TTS_VOICE_LIB_WOMAN_ENGLISH = 5; // 女声英语话音库

	// connection

	static String msg2String(int messageId)
	{
		switch (messageId)
		{
		case VGCP_ACK_OAM_REGISTER:
			return "VGCP_ACK_OAM_REGISTER";

		case VGCP_REPLY_OAM_REGISTER:
			return "VGCP_REPLY_OAM_REGISTER";
			
		case VGCP_ACK_OAM_GETCLIENTSTATE:
			return "VGCP_ACK_OAM_GETCLIENTSTATE";
			
		case VGCP_REPLY_OAM_GETCLIENTSTATE:
			return "VGCP_REPLY_OAM_GETCLIENTSTATE";
			
		case VGCP_ACK_SG_ANSWERCALL:
			return "VGCP_ACK_SG_ANSWERCALL";

		case VGCP_ACK_SG_RELEASECALL:
			return "VGCP_ACK_SG_RELEASECALL";

		case VGCP_ACK_SG_BLINDMAKECALL:
			return "VGCP_ACK_SG_BLINDMAKECALL";

		case VGCP_REPLY_SG_ANSWERCALL:
			return "VGCP_REPLY_SG_ANSWERCALL";

		case VGCP_REPLY_SG_RELEASECALL:
			return "VGCP_REPLY_SG_RELEASECALL";

		case VGCP_REPLY_SG_BLINDMAKECALL:
			return "VGCP_REPLY_SG_BLINDMAKECALL";

		case VGCP_ACK_MG_ADJSPEED:
			return "VGCP_ACK_MG_ADJSPEED";

		case VGCP_ACK_MG_ADJVOLUME:
			return "VGCP_ACK_MG_ADJVOLUME";

		case VGCP_ACK_MG_ASR:
			return "VGCP_ACK_MG_ASR";

		case VGCP_ACK_MG_FLUSHBUFFER:
			return "VGCP_ACK_MG_FLUSHBUFFER";

		case VGCP_ACK_MG_GETIOSTATE:
			return "VGCP_ACK_MG_GETIOSTATE";

		case VGCP_ACK_MG_PLAYFILE:
			return "VGCP_ACK_MG_PLAYFILE";

		case VGCP_ACK_MG_PLAYLIST:
			return "VGCP_ACK_MG_PLAYLIST";

		case VGCP_ACK_MG_PLAYTTS:
			return "VGCP_ACK_MG_PLAYTTS";

		case VGCP_ACK_MG_RECEIVEDTMFS:
			return "VGCP_ACK_MG_RECEIVEDTMFS";

		case VGCP_ACK_MG_RECEIVEFAX:
			return "VGCP_ACK_MG_RECEIVEFAX";

		case VGCP_ACK_MG_RECORDFILE:
			return "VGCP_ACK_MG_RECORDFILE";

		case VGCP_ACK_MG_SENDDTMFS:
			return "VGCP_ACK_MG_SENDDTMFS";

		case VGCP_ACK_MG_SENDFAX:
			return "VGCP_ACK_MG_SENDFAX";

		case VGCP_ACK_MG_STOPIO:
			return "VGCP_ACK_MG_STOPIO";

		case VGCP_REPLY_MG_ADJSPEED:
			return "VGCP_REPLY_MG_ADJSPEED";

		case VGCP_REPLY_MG_ADJVOLUME:
			return "VGCP_REPLY_MG_ADJVOLUME";

		case VGCP_REPLY_MG_ASR:
			return "VGCP_REPLY_MG_ASR";

		case VGCP_REPLY_MG_FLUSHBUFFER:
			return "VGCP_REPLY_MG_FLUSHBUFFER";

		case VGCP_REPLY_MG_GETIOSTATE:
			return "VGCP_REPLY_MG_GETIOSTATE";

		case VGCP_REPLY_MG_PLAYFILE:
			return "VGCP_REPLY_MG_PLAYFILE";

		case VGCP_REPLY_MG_PLAYLIST:
			return "VGCP_REPLY_MG_PLAYLIST";

		case VGCP_REPLY_MG_PLAYTTS:
			return "VGCP_REPLY_MG_PLAYTTS";

		case VGCP_REPLY_MG_RECEIVEDTMFS:
			return "VGCP_REPLY_MG_RECEIVEDTMFS";

		case VGCP_REPLY_MG_RECEIVEFAX:
			return "VGCP_REPLY_MG_RECEIVEFAX";

		case VGCP_REPLY_MG_RECORDFILE:
			return "VGCP_REPLY_MG_RECORDFILE";

		case VGCP_REPLY_MG_SENDDTMFS:
			return "VGCP_REPLY_MG_SENDDTMFS";

		case VGCP_REPLY_MG_SENDFAX:
			return "VGCP_REPLY_MG_SENDFAX";

		case VGCP_REPLY_MG_STOPIO:
			return "VGCP_REPLY_MG_STOPIO";

		case VGCP_ACK_RM_ADDCHNL2VOICEMIXER:
			return "VGCP_ACK_RM_ADDCHNL2VOICEMIXER";

		case VGCP_ACK_RM_BLINDCLOSECONF:
			return "VGCP_ACK_RM_BLINDCLOSECONF";

		case VGCP_ACK_RM_BLINDCREATECONF:
			return "VGCP_ACK_RM_BLINDCREATECONF";

		case VGCP_ACK_RM_CLOSEVOICEMIXER:
			return "VGCP_ACK_RM_CLOSEVOICEMIXER";

		case VGCP_ACK_RM_ROUTETWORES:
			return "VGCP_ACK_RM_ROUTETWORES";

		case VGCP_ACK_RM_UNROUTETWORES:
			return "VGCP_ACK_RM_UNROUTETWORES";

		case VGCP_REPLY_RM_ADDCHNL2VOICEMIXER:
			return "VGCP_REPLY_RM_ADDCHNL2VOICEMIXER";

		case VGCP_REPLY_RM_BLINDCLOSECONF:
			return "VGCP_REPLY_RM_BLINDCLOSECONF";

		case VGCP_REPLY_RM_BLINDCREATECONF:
			return "VGCP_REPLY_RM_BLINDCREATECONF";

		case VGCP_REPLY_RM_CLOSEVOICEMIXER:
			return "VGCP_REPLY_RM_CLOSEVOICEMIXER";

		case VGCP_REPLY_RM_ROUTETWORES:
			return "VGCP_REPLY_RM_ROUTETWORES";

		case VGCP_REPLY_RM_UNROUTETWORES:
			return "VGCP_REPLY_RM_UNROUTETWORES";

		case VGCP_EVT_SYS_VG_SERVICEUP:
			return "VGCP_EVT_SYS_VG_SERVICEUP";

		default:
			return "Unknown:" + messageId;
		}
	}
}
