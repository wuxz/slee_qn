package com.channelsoft.slee.log;

public class LogDef
{
	public static final String[] fmts = {
			"[][0x10040001][result=0x%X]|saveSDR返回:[%s,%s,%s,%s,%s,%s,%s,%d],队列长度:%d",
			"[][0x10140010][result=%d]|写计费数据失败。",
			"[][0x10040000][sdrQueueSize=%d]|计费数据队列长度。",
			"[][0x10240001][]|计费处理线程异常!",
			"[][0x10140000][]|%s",
			"[][0x10050001][port=%d callId=%d]|CallAgent 挂断外呼通道",
			"[][0x10250001][]|CallAgent 服务器端启动失败",
			"[][0x10150000][]|%s",
			"[][0x10050000][]|CallAgent 正在启动CCOD CMS服务器端[%d]",
			"[][0x10050002][]|CallAgent 启动CCOD CMS服务器端[%d]成功",
			"[][0x10050003][]|CallAgent 启动CCOD GLS客户器端成功",
			"[][0x10150001][]|%s",
			"[][0x10150020][port=%d callId=%d callingNumber=%s destNumber=%s]|CallAgent atsSingleStepTransfer[timeout=%d]失败",
			"[][0x10050023][port=%d callId=%d]|CallAgent answercall receive SGEvent [%d][%d][0x%X]",
			"[][0x10050004][port=%d callId=%d]|CallAgent releaseCall receive SGEvent[%d][%d][0x%X]",
			"[][0x10050005][port=%d callId=%d]|CallAgent ASR Event Result:%s",
			"[][0x10050006][port=%d callId=%d]|CallAgent makecall receive SGEvent [%d][%d][0x%X][%d]",
			"[][0x1015001D][]|CallAgent 访问GLS服务器失败，关闭连接。%s。",
			"[][0x10050000][]|CallAgent 正在连接GLS服务器 [%s]",
			"[][0x10050007][]|CallAgent 连接GLS服务器成功",
			"[][0x10250002][result=%d]|CallAgent 注册到GLS失败",
			"[][0x10050024][]|CallAgent收到媒体事件，EventId：%s,EventReason:%s，SessionId:0x%x,MepId:%d,cms[%s]",
			"[][0x10050008][]|%s",
			"[][0x10050009][]|CallAgent收到信令事件，EventId：%s,EventReason:%s,SessionId:0x%x,ConnectionId:%d,cms[%s]",
			"[][0x1005000A][]|CMS客户端传回SLEE连接成功,cms[%s],proxy[%s]",
			"[][0x10250003][ip=%s ipPort=%d]|CallAgent 连接UVMG服务器失败",
			"[][0x10050000][ip=%s ipPort=%d]|CallAgent 正在连接UVMG服务器",
			"[][0x1005000B][ip=%s ipPort=%d]|CallAgent 连接UVMG服务器成功",
			"[][0x10150015][port=%d callId=%d]|CallAgent atsMakeCall 外拨已经成功，不能重复发起外拨",
			"[][0x1005000C][port=%d callId=%d callingNumber=%s destNumber=%s result=0x%X]|CallAgent 发起MakeCall返回",
			"[][0x1005000D][port=%d callId=%d callingNumber=%s destNumber=%s]|CallAgent 发起MakeCall成功",
			"[][0x1005000E][port=%d callId=%d otherPort=%d otherCallId=%d result=0x%X]|CallAgent 搭接返回",
			"[][0x10150016][port=%d callId=%d otherPort=%d otherCallId=%d]|CallAgent 搭接失败，挂断外呼通道",
			"[channelDN=%d][0x1005000F][port=%d callId=%d callingNumber=%s destNumber=%s]|CallAgent MakeCall超时，挂断外呼通道",
			"[channelDN=%d][0x10050000][port=%d callId=%d]|外拨总机成功，休眠%d秒",
			"[channelDN=%d][0x10050000][port=%d callId=%d]|sendDtmf[%s]",
			"[channelDN=%d][0x10050010][port=%d callId=%d result=0x%X]|sendDtmf[%s]返回",
			"[][0x1015001E][callingNumber=%s destNumber=%s]|CallAgent makeCall[%d]失败",
			"[][0x10050011][port=%d callId=%d otherPort=%d otherCallId=%d result=0x%X]|CallAgent 会议搭接返回",
			"[][0x10150017][port=%d callId=%d otherPort=%d otherCallId=%d]|CallAgent 会议搭接失败，挂断外呼通道",
			"[][0x10150010][port=%d callId=%d]|CallAgent answercall收到新的CallID的事件，本次呼叫已结束",
			"[][0x10150011][port=%d callId=%d]|CallAgent releaseCall收到新的CallID的事件，本次呼叫已结束",
			"[][0x10150012][port=%d callId=%d]|CallAgent doMgOperation收到新的CallID的事件，本次呼叫已结束",
			"[][0x10150013][port=%d callId=%d]|CallAgent doMGOperation收到新的CallID的事件，本次呼叫已结束",
			"[][0x10150014][port=%d callId=%d]|CallAgent makecall收到新的CallID的事件，本次呼叫已结束",
			"[][0x10050012][port=%d callId=%d]|CallAgent 收到MGEvent[0x%X][0x%X]",
			"[][0x10050013][port=%d callId=%d]|CallAgent 收到SGEvent[0x%X]",
			"[][0x10150002][]|%s",
			"[][0x10150018][]|CallAgent 与VGProxy的连接出现错误，关闭连接",
			"[][0x10050025][]|CallAgent 60秒没有收到消息，主动断开连接",
			"[][0x10150019][transactionId=%d]|CallAgent atsMakeCall 正在发起外拨，不能重复发起外拨",
			"[][0x10050014][port=%d callId=%d]|CallAgent answercall receive AnswerCallAck [%d][%d][0x%X]",
			"[][0x10050015][port=%d callId=%d]|CallAgent releaseCall receive ReleaseCallAck [%d][%d][0x%X]",
			"[][0x10050016][ip=%s port=%d]|CallAgent receive RegisterReply",
			"[][0x10050017][port=%d callId=%d]|CallAgent receive SGEvent [0x%X][%d]",
			"[][0x10050018][port=%d callId=%d ani=%s dnis=%s oriAni=%s oriDnis=%s]|CallAgent receive IncomingCall",
			"[][0x10050019][transactionId=%d port=%d callId=%d]|CallAgent receive MakeCallAck [0x%X]",
			"[][0x1005001A][port=%d callId=%d]|CallAgent receive %s [0x%X]",
			"[][0x1005001B][port=%d callId=%d]|CallAgent receive MakeCallReply [0x%X]",
			"[][0x1005001C][port=%d]|CallAgent receive SwitchPortReply [0x%X]",
			"[][0x1005001D][port=%d]|CallAgent receive DisSwitchPortReply [0x%X]",
			"[][0x1005001E][port=%d callId=%d]|CallAgent receive BindCreateConferenceReply [0x%X]",
			"[][0x1005001F][port=%d callId=%d]|CallAgent receive MGEvent [0x%X][%d]",
			"[][0x10050020][port=%d callId=%d]|CallAgent receive DtmfReply [0x%X][%d][%s]",
			"[][0x10050021][port=%d callId=%d]|CallAgent receive AsrReply [0x%X][%d][%s]",
			"[][0x10050022][port=%d callId=%d]|CallAgent receive ConferenceRecordReply [0x%X]",
			"[][0x1015001A][]|CallAgent 收到无效的包，msgType=%d msgLength=%d",
			"[][0x10050000][ip=%s ipPort=%d]|CallAgent 正在连接UVMG服务器 ",
			"[][0x1015001F][]|CallAgent 网络连接出现错误，关闭连接",
			"[][0x1015001B][]|CallAgent 收包时发生IO错误,%s",
			"[][0x1015001C][]|CallAgent 发包时发生IO错误,%s",
			"[channelDN=%s][0x10120001][result=0x%X]|查询通道状态返回错误",
			"[channelDN=%s][0x10020000][]|通道管理线程启动。。。",
			"[channelDN=%s][0x10220001][]|CATSChannel.execInboundSrv 异常",
			"[][0x10120000][]|%s",
			"[channelDN=%s][0x10220002][]|CATSChannel.run异常",
			"[channelDN=%s][0x10020001][]|接收到Inbound事件",
			"[channelDN=%s][0x10120010][]|接收到入呼叫事件但是当前的通道状态为Idle",
			"[channelDN=%s][0x10120011][]|启动服务失败，路径：%s, 原因：绑定电话失败。",
			"[channelDN=%s][0x10020002][]|启动服务，路径：%s，参数：%s\n",
			"[channelDN=%s][0x10120012][]|入呼叫路由表中无法查到通道对应的服务。可能的错误原因：应用启动时间配置错误；应用呼叫类型配置错误",
			"[channelDN=%s][0x10120013][port=%d callId=%d]|ReasoningProvider[%d] 拒绝服务。",
			"[channelDN=%s][0x10020003][port=%d callId=%d]|自动应答",
			"[channelDN=%s][0x10120014][port=%d callId=%d]|自动应答失败, 返回",
			"[channelDN=%s][0x10020004][port=%d callId=%d ani=%s dnis=%s oriAni=%s oriDnis=%s]|",
			"[channelDN=%s][0x10020005][port=%d callId=%d]|绑定通道成功。",
			"[channelDN=%s][0x10120015][result=0x%X]|GetContactInfoEx返回错误",
			"[channelDN=%s][0x1002002A][]|执行服务结束",
			"[channelDN=%s][0x10220003][]|入呼叫处理异常",
			"[channelDN=%s][0x10120016][]|指定的启动文档%s不存在，使用系统默认文档",
			"[channelDN=%s][0x10020006][]|申请启动通道, ReasoningProvider[%d] 拒绝",
			"[channelDN=%s][0x10120017][]|外拨状态下，没有执行服务但是通道状态为Busy",
			"[channelDN=%s][0x10220004][]|出呼叫下呼叫处理异常",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|disconnetCall",
			"[channelDN=%s][0x10020007][port=%d callId=%d result=0x%X]|disconnetCall返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|answerCall(billing=%d)",
			"[channelDN=%s][0x10020008][port=%d callId=%d result=0x%X]|answerCall(billing=%d)返回",
			"[channelDN=%s][0x10020000][callingNumber=%s destNumber=%s]|consultCall[%d]",
			"[channelDN=%s][0x10020009][callingNumber=%s destNumber=%s result=0x%X]|consultCall[%d]返回",
			"[channelDN=%s][0x10020000][callingNumber=%s destNumber=%s]|makeCall[%d]",
			"[channelDN=%s][0x1002000A][callingNumber=%s destNumber=%s result=0x%X]|makeCall[%d]返回",
			"[channelDN=%s][0x10120018][callingNumber=%s destNumber=%s]|makeCall[%d]失败",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|releaseConnection",
			"[channelDN=%s][0x1002001D][port=%d callId=%d result=0x%X]|releaseConnection返回",
			"[channelDN=%s][0x10020000][port=%d otherPort=%d]|routeConnection",
			"[channelDN=%s][0x1002000B][port=%d otherPort=%d result=0x%X]|routeConnection返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d callingNumber=%s destNumber=%s]|singleStepConference[%d]",
			"[channelDN=%s][0x1002000C][port=%d callId=%d callingNumber=%s destNumber=%s result=0x%X]|singleStepConference[%d]返回",
			"[channelDN=%s][0x10020000][]|closeConference",
			"[channelDN=%s][0x1002000D][result=0x%X]|closeConference返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d callingNumber=%s destNumber=%s]|singleStepTransfer[%d]",
			"[channelDN=%s][0x1002000E][port=%d callId=%d callingNumber=%s destNumber=%s result=0x%X]|singleStepTransfer[%d]返回",
			"[channelDN=%s][0x10020000][port=%d otherPort=%d]|unrouteConnection",
			"[channelDN=%s][0x1002000F][port=%d otherPort=%d result=0x%X]|unrouteConnection返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|asr",
			"[channelDN=%s][0x10020010][port=%d callId=%d result=0x%X]|asr返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|asr2",
			"[channelDN=%s][0x10020011][port=%d callId=%d result=0x%X]|asr2返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|clearDtmfBuffer",
			"[channelDN=%s][0x10020012][port=%d callId=%d result=0x%X]|clearDtmfBuffer返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|getDtmf[%d %s]",
			"[channelDN=%s][0x10020013][port=%d callId=%d result=0x%X]|getDtmf返回, dtmf=%s",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|playVoiceArray",
			"[channelDN=%s][0x10020000][]|SLEE Will PlayVoice in List %s",
			"[channelDN=%s][0x10020014][port=%d callId=%d result=0x%X]|playVoiceArray返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|playVoice[%s %s %s]",
			"[channelDN=%s][0x10020015][port=%d callId=%d result=0x%X]|playVoice[%s]返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|playVoiceAsync",
			"[channelDN=%s][0x10020016][port=%d callId=%d result=0x%X]|playVoiceAsync返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|playVoiceByTTS",
			"[channelDN=%s][0x10020017][port=%d callId=%d result=0x%X]|playVoiceByTTS返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|receiveFax[%s]",
			"[channelDN=%s][0x10020018][port=%d callId=%d result=0x%X]|receiveFax[%s]返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|recordVoice[%s]",
			"[channelDN=%s][0x10020019][port=%d callId=%d result=0x%X]|recordVoice[%s]返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|sendDtmf[%s]",
			"[channelDN=%s][0x1002001A][port=%d callId=%d result=0x%X]|sendDtmf[%s]返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|sendFax[%s]",
			"[channelDN=%s][0x1002001B][port=%d callId=%d result=0x%X]|sendFax[%s]返回",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|startConferencRecording[%s]",
			"[channelDN=%s][0x1002001C][port=%d callId=%d result=0x%X]| startConferencRecording[%s]返回",
			"[channelDN=%s][0x10120019][port=%d callId=%d]|attachCall，指定的呼叫不存在",
			"[][0x1012001A][]|ChannelManage GetAppChannel 出现异常",
			"[][0x1012001B][]|ChannelManage GetInboundChannel 队列为空",
			"[channelDN=%s][0x1002001E][]|ChannelManage GetInboundChannel ,-空闲通道数=%d",
			"[][0x1012001C][]|ChannelManage GetInboudChannel 出现异常",
			"[][0x1012001D][]|ChannelManage GetMoChannel 出现异常",
			"[][0x1012001E][]|ChannelManage GetReportChannel 出现异常",
			"[channelDN=%s][0x1002001F][]|ChannelManage 回收通道成功，空闲通道数=%d",
			"[channelDN=%s][0x10020020][]|CChannelManage接收到m_cAppQueue事件",
			"[][0x10020021][]|ChannelManage接收到m_cAppQueue事件,分配ChannelDN时返回NULL",
			"[][0x10020022][port=%d callId=%d]|ChannelManage接收到Inbound事件,总个数:%d",
			"[channelDN=%s][0x10020023][port=%d callId=%d]|ChannelManage分配Channel",
			"[][0x10020024][port=%d callId=%d]|CChannelManage分配Channel时返回null，挂断呼叫。",
			"[][0x10020025][port=%d callId=%d]|ChannelManage接收到Disconnect事件,总个数:%d",
			"[channelDN=%s][0x10020026][port=%d callId=%d]|ChannelManage接收到Event_SMS_MO事件,总个数:%d,分配通道",
			"[][0x10020027][]|ChannelManage接收到Event_SMS_MO事件,总个数:%d,分配ChannelDN时返回null",
			"[channelDN=%s][0x10020028][port=%d, callId=%d]|ChannelManage接收到Event_SMS_Report事件,总个数:%d,分配通道",
			"[][0x10020029][]|ChannelManage接收到Event_SMS_Report事件,分配ChannelDN时返回null,reportCount=%d",
			"[][0x1012001F][]|ChannelManage接收到无效事件:%d",
			"[][0x10220005][]|通道管理线程出现异常",
			"[][0x10120002][]|%s",
			"[][0x10120003][]|%s",
			"[][0x10170001][]|%s",
			"[][0x10170002][]|%s",
			"[][0x10170003][]|%s",
			"[][0x10170005][]|%s",
			"[][0x10070002][ip=%s]|got new connection",
			"[][0x10070001][]|收到无效的监控命令，断开连接",
			"[][0x10170004][]|%s",
			"[][0x10170000][]|%s",
			"[][0x10070003][]|parsecommand %s",
			"[][0x10160001][]|%s",
			"[][0x10160002][]|%s",
			"[][0x10160003][]|%s",
			"[][0x10060000][]|启动SNMP代理线程",
			"[][0x10160004][]|%s",
			"[][0x10160005][]|%s",
			"[][0x10160006][]|%s",
			"[][0x10060001][]|Invalide PDU: NULL",
			"[][0x10060002][]|(SNMP Agent rcv) %s",
			"[][0x10160007][]|%s",
			"[][0x10060003][]|SNMP Agent send undefined trap msg[' %s ']",
			"[][0x10060004][]|SNMP Agent SendTrapMsg[' %s ']:%s",
			"[][0x10210001][]|用户配置的ATSE的安装路径错误，程序终止!",
			"[][0x10210002][]|配置文件不存在，程序终止!",
			"[][0x10010000][]|配置文件中RunLog的值为false，关闭运行日志。",
			"[][0x10210003][]|没有配置SLEE在GLS上的注册名称",
			"[][0x10110010][]|打开配置文件错误!",
			"[][0x10110000][]|%s",
			"[][0x10010000][]|装载ReasoningProvider[%s]成功",
			"[][0x10010001][]|装载ReasoningProvider[%s]失败",
			"[][0x10110011][]|未发现ATSE安装目录，程序终止!",
			"[][0x10010000][]|AppPath=%s。",
			"[][0x10010000][]|开始加载系统配置信息。",
			"[][0x10010000][]|加载配置成功。",
			"[][0x10110012][ipPort=%d]|监听端口已被占用,SLEE无法启动！",
			"[][0x10010004][ip=%s ipPort=%d]|与媒体服务器建立连接。",
			"[][0x10110001][]|%s",
			"[][0x10010000][]|>>>>>>>>>>>>>Start",
			"[][0x10010000][]|SLEE启动",
			"[][0x10010000][]|启动所有的通道监控线程。",
			"[][0x10010000][]|Version[UnifiedServiceMgServer.exe]:",
			"[][0x10010002][]|%s",
			"[][0x10010003][]|配置文件发生变化，重新装载。",
			"[channelDN=%s][0x10030000][]|##EnterService##%s",
			"[channelDN=%s][0x10030000][]|##LeaveService##%s",
			"[channelDN=%s][0x10030000][]|##EnterWorkflow##%s",
			"[channelDN=%s][0x10030000][]|##LeaveWorkflow##%s",
			"[channelDN=%s][0x10030000][]|##EnterServiceNode##%s",
			"[channelDN=%s][0x10030000][]|##LeaveServiceNode##%s",
			"[channelDN=%s][0x10030000][]|##IOProcess##%s",
			"[channelDN=%s][0x10030001][]|提示:文档初始化,不运行服务.终止执行.",
			"[channelDN=%s][0x10030002][]|申请进入服务%s, ReasoningProvider[%d] 拒绝",
			"[][0x10130000][]|%s",
			"[channelDN=%s][0x10030003][]|申请进入工作流%s, ReasoningProvider[%d] 拒绝",
			"[channelDN=%s][0x10130010][]|执行工作流=%s时出现堆栈溢出.",
			"[channelDN=%s][0x10030004][]|执行工作流=%s时达到指定Timer.",
			"[channelDN=%s][0x10030005][]|申请进入节点%s, ReasoningProvider[%d] 拒绝",
			"[channelDN=%s][0x10030006][event=%s]|执行服务时返回(启动事件工作流)",
			"[channelDN=%s][0x10030007][event=%s]|执行服务时返回(事件由ATSE系统处理)",
			"[channelDN=%s][0x10030000][]|msg:%s",
			"[channelDN=%s][0x10130011][]|errorInfo=%s",
			"[channelDN=%s][0x10030000][]|提示:更改CallingID{%s==>>%s}",
			"[channelDN=%s][0x10030000][]|提示:更改CalledID{%s==>>%s}",
			"[channelDN=%s][0x10030000][]|提示:更改ServiceID{%s==>>%s}",
			"[channelDN=%s][0x10030000][]|提示:更改AccountID{%s==>>%s}",
			"[channelDN=%s][0x10030008][]|申请进入IOProcess%s, ReasoningProvider[%d] 拒绝",
			"[][0x10130001][]|%s",
			"[][0x10130012][]|收到无效的预编译命令",
			"[][0x10130002][]|%s",
			"[][0x10000000][]|",
			"[][0x10100000][]|%s",
			"[][0x10100010][]|",
			"[][0x10200000][]|",
			"[][0x10050026][]|SSR2Client丢弃REPLY消息%s",
			"[][0x10050027][sessionId=0x%x size=%d]|CMSMessageProcessor 分发入呼叫事件成功",
			"[][0x10050028][eventId=0x%X sessionId=0x%x size=%d]|CMSMessageProcessor 分发信令事件失败, 资源不存在.",
			"[][0x10050029][eventId=0x%x sessionId=0x%x size=%d]|CMSMessageProcessor 分发信令事件成功.",
			"[][0x10050030][eventId=0x%X sessionId=0x%x size=%d]|CMSMessageProcessor 分发媒体事件失败， 资源不存在.",
			"[][0x10050031][eventId=0x%X sessionId=0x%x size=%d]|CMSMessageProcessor 分发媒体事件成功.",
			"[][0x10150003][callId=%d SessionId=0x%x]|CallAgent CMS Accept出现网络错误，重置回调接口.",
			"[][0x10150004][callId=%d SessionId=0x%x]|CallAgent CMS Accept出现网络错误，回调接口已经更新",
			"[][0x10150005][callId=%d SessionId=0x%x result=0x%x]|CallAgent CMS Accept失败",
			"[][0x10050032][callId=%d sessionId=0x%x mepId=%d]|CallAgent CMS Accept成功",
			"[][0x10150006][sessionId=0x%x connectionId=0x%x result=0x%x]|CallAgent Join失败",
			"[][0x10150007][sessionId=0x%x result=0x%x]|CallAgent CreateMediaEndpoint失败",
			"[][0x10150008][size=%d]|CallAgent 清除全部Session",
			"[][0x10150009][sessionId=0x%x port=%d callId=%d callingNumber=%s destNumber=%s]|CallAgent 转移服务失败",
			"[][0x1015000A][]|CallAgent 访问DDS服务器失败，关闭连接",
			"[][0x1015000B][]|文件路径[%s]不存在，不写消息日志.",
			"[][0x1015000C][]|SSR2Client注册失败，原因=0x%08X.",
			"[][0x1015000D][]|AppMessageProcessor接收到无效事件:%d.",
			"[channelDN=%s][0x1015000E][sessionId=%d]|指定的USML会话不存在.",
			"[][0x10150000][port=%d callId=%d transactionId=%d callingNumber=%s destNumber=%s]|CallAgent MakeCall发送按键失败，挂断外呼通道.",
			"[][0x10150000][sessionId=%d]|CMS随路数据：%s.",
			"[][0x10150000][sessionId=0x%x connectionId=%d callId=%d mapSize=%d %d]|CallAgent 拆除Session与Connection.",
			"[][0x10150000][sessionId=0x%x sessionSize=%d]|CallAgent 清除Session",
			"[][0x10150000][sessionId=0x%x connectionId=%d callId=%d size=%d]|CallAgent 绑定Session与Connection失败.",
			"[][0x10150000][sessionId=0x%x connectionId=%d callId=%d callSnSize=%d sessionSize=%d]|CallAgent 绑定Session与Connection成功.",
			"[][0x10150000][]|CallAgent 启动CCOD DDS客户端成功.",
			"[][0x10150000][sessionId=0x%x]|CallAgent 收到UCDS转移过来的呼叫.",
			"[][0x10150000][connection=%s]|CallAgent 正在连接DDS服务器.",
			"[][0x10150000][]|CallAgent 连接DDS服务器失败.",
			"[][0x10150000][]|CallAgent 连接DDS服务器成功.",
			"[][0x10150000][]|DDS客户端PersistPath[%s]",
			"[][0x10150000][sessionId=0x%x connectionId=%d]|CallAgent收到入呼叫事件.cms[%s]",
			"[][0x10150000][sessionId=0x%x ani=%s dnis=%s oriAni=%s oriDnis=%s]|CallAgent StartService成功,cms[%s],mediaType[%s]",
			"[][0x10150000][]|SSR2Client注册成功.",
			"[][0x10150000][messageId=%s]|SSR2Client丢弃ACK消息.",
			"[][0x10150000][messageId=%s]|SSSR2Client处理REPLY消息.",
			"[][0x10150000][messageId=%s]|SSR2Client丢弃未知消息.",
			"[][0x10150000][port=%d callId=%d]|dispatch未知事件[%d], 抛弃.",
			"[channelDN=%s][0x10020000][sessionId=%d]|开始USML会话.",
			"[channelDN=%s][0x10020000][sessionId=%d]|恢复USML会话.",
			"[channelDN=%s][0x10020000][sessionId=%d]|清理USML会话.完成总数=%d, 未完成总数=%d.",
			"[channelDN=%s][0x10020000][sessionId=%d]|收取消息成功，不需要挂起会话.完成总数=%d, 未完成总数=%d.",
			"[channelDN=%s][0x10020000][sessionId=%d]|挂起USML会话，未完成总数=%d.",
			"[][0x10020000][sessionId=%d]|ServiceTimerTask清除异步任务, 未完成的定时器=%d, 未完成的任务=%d.",
			"[][0x10020000][sessionId=%d]|QueueMessageReceiver处理消息，启动通道.",
			"[][0x10020000][sessionId=%d]|QueueMessageReceiver收到消息，等候处理.",
			"[][0x10020000][sessionId=%s]|QueueMessageReceiver收到消息.",
			"[][0x10020000][sessionId=%d]|ServiceTimerTask启动定时任务, 未完成的定时器=%d, 未完成的任务=%d",
			"[][0x10020000][result=%d]|初始化计费组件失败.",
			"[][0x10020000][result=%d]|初始化计费组件成功.",
			"[channelDN=%s][0x10020000][port=%d callId=%d result=0x%X]|VoiceEdit返回, dtmf=%s",
			"[channelDN=%s][0x10020000][port=%d callId=%d result=0x%X]|VoiceListEdit返回, dtmf=%s",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|VoiceEdit",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|VoiceListEdit",
			"[channelDN=%s][0x10020000][port=%d callId=%d]|AppendVideo",
			"[channelDN=%s][0x10020000][port=%d callId=%d result=0x%X]|AppendVideo返回",
			"[channelDN=%s][0x10020000][callingNumber=%s destNumber=%s]|callRecordRing[file=%s timeout=%d]",
			"[channelDN=%s][0x1002000A][callingNumber=%s destNumber=%s result=0x%X]|callRecordRing[file=%s timeout=%d]返回",
			"[channelDN=%s][0x10020000][callingNumber=%s destNumber=%s]|callRecordRing[timeout=%d]失败"};//293

	/**
	 * package(公用): 通用跟踪日志 []
	 */
	public static final int id_0x10000000 = 231;

	/**
	 * package(unifiedServiceManagement): 配置文件中RunLog的值为false，关闭运行日志 []
	 */
	public static final int id_0x10010000 = 186;

	/**
	 * package(unifiedServiceManagement): 装载ReasoningProvider成功 [ %s]
	 */
	public static final int id_0x10010000_1 = 190;

	/**
	 * package(unifiedServiceManagement): AppPath是… [ %s]
	 */
	public static final int id_0x10010000_2 = 193;

	/**
	 * package(unifiedServiceManagement): 开始加载系统配置信息 []
	 */
	public static final int id_0x10010000_3 = 194;

	/**
	 * package(unifiedServiceManagement): 加载配置成功 []
	 */
	public static final int id_0x10010000_4 = 195;

	/**
	 * package(unifiedServiceManagement): >>>>>>>>>>>>>Start []
	 */
	public static final int id_0x10010000_5 = 199;

	/**
	 * package(unifiedServiceManagement): SLEE启动 []
	 */
	public static final int id_0x10010000_6 = 200;

	/**
	 * package(unifiedServiceManagement): 启动所有的通道监控线程 []
	 */
	public static final int id_0x10010000_7 = 201;

	/**
	 * package(unifiedServiceManagement): Version[UnifiedServiceMgServer.exe] []
	 */
	public static final int id_0x10010000_8 = 202;

	/**
	 * package(unifiedServiceManagement): 装载ReasoningProvider失败 [ %s]
	 */
	public static final int id_0x10010001 = 191;

	/**
	 * package(unifiedServiceManagement): versionInfo [ %s]
	 */
	public static final int id_0x10010002 = 203;

	/**
	 * package(unifiedServiceManagement): 配置文件发生变化，重新装载 []
	 */
	public static final int id_0x10010003 = 204;

	/**
	 * package(unifiedServiceManagement): 与媒体服务器建立连接 [ %s %d]
	 */
	public static final int id_0x10010004 = 197;

	/**
	 * package(channelManager): 通道管理线程启动 [ %s]
	 */
	public static final int id_0x10020000 = 72;

	/**
	 * package(channelManager): disconnetCall [ %s %d %d]
	 */
	public static final int id_0x10020000_1 = 93;

	/**
	 * package(channelManager): unrouteConnection [ %s %d %d]
	 */
	public static final int id_0x10020000_10 = 112;

	/**
	 * package(channelManager): asr [ %s %d %d]
	 */
	public static final int id_0x10020000_11 = 114;

	/**
	 * package(channelManager): asr2 [ %s %d %d]
	 */
	public static final int id_0x10020000_12 = 116;

	/**
	 * package(channelManager): clearDtmfBuffer [ %s %d %d]
	 */
	public static final int id_0x10020000_13 = 118;

	/**
	 * package(channelManager): getDtmf [ %s %d %d %d %s]
	 */
	public static final int id_0x10020000_14 = 120;

	/**
	 * package(channelManager): playVoiceArray [ %s %d %d]
	 */
	public static final int id_0x10020000_15 = 122;

	/**
	 * package(channelManager): SLEE Will PlayVoice in List [ %s %s]
	 */
	public static final int id_0x10020000_16 = 123;

	/**
	 * package(channelManager): playVoice [ %s %d %d %s %s %s]
	 */
	public static final int id_0x10020000_17 = 125;

	/**
	 * package(channelManager): playVoiceAsync [ %s %d %d]
	 */
	public static final int id_0x10020000_18 = 127;

	/**
	 * package(channelManager): playVoiceByTTS [ %s %d %d]
	 */
	public static final int id_0x10020000_19 = 129;

	/**
	 * package(channelManager): answerCall [ %s %d %d %d]
	 */
	public static final int id_0x10020000_2 = 95;

	/**
	 * package(channelManager): receiveFax [ %s %d %d %s]
	 */
	public static final int id_0x10020000_20 = 131;

	/**
	 * package(channelManager): recordVoice [ %s %d %d %s]
	 */
	public static final int id_0x10020000_21 = 133;

	/**
	 * package(channelManager): sendDtmf [ %s %d %d %s]
	 */
	public static final int id_0x10020000_22 = 135;

	/**
	 * package(channelManager): sendFax [ %s %d %d %s]
	 */
	public static final int id_0x10020000_23 = 137;

	/**
	 * package(channelManager): startConferencRecording [ %s %d %d %s]
	 */
	public static final int id_0x10020000_24 = 139;

	/**
	 * package(channelManager): 开始USML会话. [%s %d]
	 */
	public static final int id_0x10020000_25 = 273;

	/**
	 * package(channelManager): 恢复USML会话. [%s %d]
	 */
	public static final int id_0x10020000_26 = 274;

	/**
	 * package(channelManager): 清理USML会话. [%s %d %d %d]
	 */
	public static final int id_0x10020000_27 = 275;

	/**
	 * package(channelManager): 收取消息成功，不需要挂起会话. [%s %d %d %d]
	 */
	public static final int id_0x10020000_28 = 276;

	/**
	 * package(channelManager): 挂起USML会话. [%s %d %d]
	 */
	public static final int id_0x10020000_29 = 277;

	/**
	 * package(channelManager): consultCall [ %s %s %s %d]
	 */
	public static final int id_0x10020000_3 = 97;

	/**
	 * package(channelManager): ServiceTimerTask清除异步任务. [%d %d %d]
	 */
	public static final int id_0x10020000_30 = 278;

	/**
	 * package(channelManager): QueueMessageReceiver处理消息，启动通道. [%d]
	 */
	public static final int id_0x10020000_31 = 279;

	/**
	 * package(channelManager): QueueMessageReceiver收到消息，等候处理. [%d]
	 */
	public static final int id_0x10020000_32 = 280;

	/**
	 * package(channelManager): QueueMessageReceiver收到消息. [%s]
	 */
	public static final int id_0x10020000_33 = 281;

	/**
	 * package(channelManager): ServiceTimerTask启动定时任务. [%d %d %d]
	 */
	public static final int id_0x10020000_34 = 282;

	/**
	 * package(channelManager): 初始化计费组件失败. [%d]
	 */
	public static final int id_0x10020000_35 = 283;

	/**
	 * package(channelManager): 初始化计费组件成功. [%d]
	 */
	public static final int id_0x10020000_36 = 284;

	/**
	 * package(channelManager): VoiceEdit返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x10020000_37 = 285;

	/**
	 * package(channelManager): VoiceListEdit返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x10020000_38 = 286;

	/**
	 * package(channelManager): VoiceEdit[ %s %d %d]
	 */
	public static final int id_0x10020000_39 = 287;
	
	/**
	 * package(channelManager): VoiceListEdit[ %s %d %d]
	 */
	public static final int id_0x10020000_40 = 288;
	
	/**
	 * package(channelManager): AppendVideo[%s %d %d]
	 */
	public static final int id_0x10020000_41 = 289;
	
	/**
	 * package(channelManager): AppendVideo返回[%s %d %d %d]
	 */
	public static final int id_0x10020000_42 = 290;
	
	/**
	 * callRecordRing[file=%s timeout=%d]
	 */
	public static final int id_0x10020000_43 = 291;
	
	/**
	 * callRecordRing[file=%s timeout=%d]返回
	 */
	public static final int id_0x10020000_44 = 292;
	
	/**
	 * callRecordRing失败
	 */
	public static final int id_0x10020000_45 = 293;
	
	/**
	 * package(channelManager): makeCall [ %s %s %s %d]
	 */
	public static final int id_0x10020000_4 = 99;

	/**
	 * package(channelManager): releaseConnection [ %s %d %d]
	 */
	public static final int id_0x10020000_5 = 102;

	/**
	 * package(channelManager): routeConnection [ %s %d %d]
	 */
	public static final int id_0x10020000_6 = 104;

	/**
	 * package(channelManager): singleStepConference [ %s %d %d %s %s %d]
	 */
	public static final int id_0x10020000_7 = 106;

	/**
	 * package(channelManager): closeConference [ %s]
	 */
	public static final int id_0x10020000_8 = 108;

	/**
	 * package(channelManager): singleStepTransfer [ %s %d %d %s %s %d]
	 */
	public static final int id_0x10020000_9 = 110;

	/**
	 * package(channelManager): 接收到Inbound事件 [ %s]
	 */
	public static final int id_0x10020001 = 76;

	/**
	 * package(channelManager): 启动服务 [ %s %s %s]
	 */
	public static final int id_0x10020002 = 79;

	/**
	 * package(channelManager): 自动应答 [ %s %d %d]
	 */
	public static final int id_0x10020003 = 82;

	/**
	 * package(channelManager): 呼叫基本信息 [ %s %d %d %s %s %s %s]
	 */
	public static final int id_0x10020004 = 84;

	/**
	 * package(channelManager): 绑定通道成功 [ %s %d %d]
	 */
	public static final int id_0x10020005 = 85;

	/**
	 * package(channelManager): 申请启动通道, ReasoningProvider拒绝 [ %s %d]
	 */
	public static final int id_0x10020006 = 90;

	/**
	 * package(channelManager): disconnetCall返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020007 = 94;

	/**
	 * package(channelManager): answerCall返回 [ %s %d %d %X %d]
	 */
	public static final int id_0x10020008 = 96;

	/**
	 * package(channelManager): consultCall[%d]返回 [ %s %s %s %X %d]
	 */
	public static final int id_0x10020009 = 98;

	/**
	 * package(channelManager): makeCall返回 [ %s %s %s %X %d]
	 */
	public static final int id_0x1002000A = 100;

	/**
	 * package(channelManager): routeConnection返回 [ %s %d %d %X]
	 */
	public static final int id_0x1002000B = 105;

	/**
	 * package(channelManager): singleStepConference返回 [ %s %d %d %s %s %X %d]
	 */
	public static final int id_0x1002000C = 107;

	/**
	 * package(channelManager): closeConference返回 [ %s %X]
	 */
	public static final int id_0x1002000D = 109;

	/**
	 * package(channelManager): singleStepTransfer返回 [ %s %d %d %s %s %X %d]
	 */
	public static final int id_0x1002000E = 111;

	/**
	 * package(channelManager): unrouteConnection返回 [ %s %d %d %X]
	 */
	public static final int id_0x1002000F = 113;

	/**
	 * package(channelManager): asr返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020010 = 115;

	/**
	 * package(channelManager): asr2返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020011 = 117;

	/**
	 * package(channelManager): clearDtmfBuffer返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020012 = 119;

	/**
	 * package(channelManager): getDtmf返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x10020013 = 121;

	/**
	 * package(channelManager): playVoiceArray返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020014 = 124;

	/**
	 * package(channelManager): playVoice返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x10020015 = 126;

	/**
	 * package(channelManager): playVoiceAsync返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020016 = 128;

	/**
	 * package(channelManager): playVoiceByTTS返回 [ %s %d %d %X]
	 */
	public static final int id_0x10020017 = 130;

	/**
	 * package(channelManager): receiveFax返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x10020018 = 132;

	/**
	 * package(channelManager): recordVoice返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x10020019 = 134;

	/**
	 * package(channelManager): sendDtmf返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x1002001A = 136;

	/**
	 * package(channelManager): sendFax返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x1002001B = 138;

	/**
	 * package(channelManager): startConferencRecording返回 [ %s %d %d %X %s]
	 */
	public static final int id_0x1002001C = 140;

	/**
	 * package(channelManager): releaseConnection返回 [ %s %d %d %X]
	 */
	public static final int id_0x1002001D = 103;

	/**
	 * package(channelManager): GetInboundChannel [ %s %d]
	 */
	public static final int id_0x1002001E = 144;

	/**
	 * package(channelManager): 回收通道成功 [ %s %d]
	 */
	public static final int id_0x1002001F = 148;

	/**
	 * package(channelManager): 接收到m_cAppQueue事件 [ %s]
	 */
	public static final int id_0x10020020 = 149;

	/**
	 * package(channelManager): 分配ChannelDN时返回NULL []
	 */
	public static final int id_0x10020021 = 150;

	/**
	 * package(channelManager): 接收到Inbound事件 [ %d %d %d]
	 */
	public static final int id_0x10020022 = 151;

	/**
	 * package(channelManager): 分配Channel [ %s %d %d]
	 */
	public static final int id_0x10020023 = 152;

	/**
	 * package(channelManager): 分配Channel时返回null [ %d %d]
	 */
	public static final int id_0x10020024 = 153;

	/**
	 * package(channelManager): 接收到Disconnect事件 [ %d %d %d]
	 */
	public static final int id_0x10020025 = 154;

	/**
	 * package(channelManager): 接收到Event_SMS_MO事件 [ %s %d %d %d]
	 */
	public static final int id_0x10020026 = 155;

	/**
	 * package(channelManager): 接收到Event_SMS_MO事件 [ %d]
	 */
	public static final int id_0x10020027 = 156;

	/**
	 * package(channelManager): 接收到Event_SMS_Report事件 [ %s %d %d %d]
	 */
	public static final int id_0x10020028 = 157;

	/**
	 * package(channelManager): 接收到Event_SMS_Report事件,分配ChannelDN时返回null [ %d]
	 */
	public static final int id_0x10020029 = 158;

	/**
	 * package(channelManager): 执行服务结束 [ %s]
	 */
	public static final int id_0x1002002A = 87;

	/**
	 * package(usmlReasoning): EnterService [ %s %s]
	 */
	public static final int id_0x10030000 = 205;

	/**
	 * package(usmlReasoning): LeaveService [ %s %s]
	 */
	public static final int id_0x10030000_1 = 206;

	/**
	 * package(usmlReasoning): 提示:更改ServiceID [ %s %s %s]
	 */
	public static final int id_0x10030000_10 = 225;

	/**
	 * package(usmlReasoning): 提示:更改AccountID [ %s %s %s]
	 */
	public static final int id_0x10030000_11 = 226;

	/**
	 * package(usmlReasoning): EnterWorkflow [ %s %s]
	 */
	public static final int id_0x10030000_2 = 207;

	/**
	 * package(usmlReasoning): LeaveWorkflow [ %s %s]
	 */
	public static final int id_0x10030000_3 = 208;

	/**
	 * package(usmlReasoning): EnterServiceNode [ %s %s]
	 */
	public static final int id_0x10030000_4 = 209;

	/**
	 * package(usmlReasoning): LeaveServiceNode [ %s %s]
	 */
	public static final int id_0x10030000_5 = 210;

	/**
	 * package(usmlReasoning): IOProcess [ %s %s]
	 */
	public static final int id_0x10030000_6 = 211;

	/**
	 * package(usmlReasoning): onMessage [ %s %s]
	 */
	public static final int id_0x10030000_7 = 221;

	/**
	 * package(usmlReasoning): 提示:更改CallingID [ %s %s %s]
	 */
	public static final int id_0x10030000_8 = 223;

	/**
	 * package(usmlReasoning): 提示:更改CalledID [ %s %s %s]
	 */
	public static final int id_0x10030000_9 = 224;

	/**
	 * package(usmlReasoning): 提示:文档初始化,不运行服务. [ %s]
	 */
	public static final int id_0x10030001 = 212;

	/**
	 * package(usmlReasoning): 申请进入服务, ReasoningProvider拒绝 [ %s %s %d]
	 */
	public static final int id_0x10030002 = 213;

	/**
	 * package(usmlReasoning): 申请进入工作流, ReasoningProvider拒绝 [ %s %s %d]
	 */
	public static final int id_0x10030003 = 215;

	/**
	 * package(usmlReasoning): 执行工作流时达到指定Timer [ %s %s]
	 */
	public static final int id_0x10030004 = 217;

	/**
	 * package(usmlReasoning): 申请进入节点, ReasoningProvider拒绝 [ %s %s %d]
	 */
	public static final int id_0x10030005 = 218;

	/**
	 * package(usmlReasoning): 执行服务时返回(启动事件工作流) [ %s %s]
	 */
	public static final int id_0x10030006 = 219;

	/**
	 * package(usmlReasoning): 执行服务时返回(事件由ATSE系统处理) [ %s %s]
	 */
	public static final int id_0x10030007 = 220;

	/**
	 * package(usmlReasoning): 申请进入IOProcess, ReasoningProvider拒绝 [ %s %s %d]
	 */
	public static final int id_0x10030008 = 227;

	/**
	 * package(billing): 计费数据队列长度 [ %d]
	 */
	public static final int id_0x10040000 = 2;

	/**
	 * package(billing): 保存SDR返回结果 [ %X %s %s %s %s %s %s %s %d %d]
	 */
	public static final int id_0x10040001 = 0;

	/**
	 * package(callAgent): 正在启动CCOD CMS服务器端 [ %d]
	 */
	public static final int id_0x10050000 = 8;

	/**
	 * package(callAgent): 正在连接GLS服务器 [ %s]
	 */
	public static final int id_0x10050000_1 = 18;

	/**
	 * package(callAgent): CallAgent 绑定Session与Connection失败. [0x%x %d %d %d]
	 */
	public static final int id_0x10050000_10 = 258;

	/**
	 * package(callAgent): CallAgent 绑定Session与Connection成功. [0x%x %d %d %d %d]
	 */
	public static final int id_0x10050000_11 = 259;

	/**
	 * package(callAgent): CallAgent 启动CCOD DDS客户端成功
	 */
	public static final int id_0x10050000_12 = 260;

	/**
	 * package(callAgent): CallAgent 收到UCDS转移过来的呼叫 [0x%x]
	 */
	public static final int id_0x10050000_13 = 261;

	/**
	 * package(callAgent): CallAgent 正在连接DDS服务器 [%s]
	 */
	public static final int id_0x10050000_14 = 262;

	/**
	 * package(callAgent): CallAgent 连接DDS服务器失败
	 */
	public static final int id_0x10050000_15 = 263;

	/**
	 * package(callAgent): CallAgent 连接DDS服务器成功
	 */
	public static final int id_0x10050000_16 = 264;

	/**
	 * package(callAgent): DDS客户端PersistPath [%s]
	 */
	public static final int id_0x10050000_17 = 265;

	/**
	 * package(callAgent): CallAgent收到入呼叫事件 [0x%x %d %s]
	 */
	public static final int id_0x10050000_18 = 266;

	/**
	 * package(callAgent): CallAgent StartService成功 [0x%x %s %s %s %s %s %s]
	 */
	public static final int id_0x10050000_19 = 267;

	/**
	 * package(callAgent): 正在连接UVMG服务器 [ %s %d]
	 */
	public static final int id_0x10050000_2 = 26;

	/**
	 * package(callAgent): SSR2Client注册成功
	 */
	public static final int id_0x10050000_20 = 268;

	/**
	 * package(callAgent): SSR2Client丢弃ACK消息 [%s]
	 */
	public static final int id_0x10050000_21 = 269;

	/**
	 * package(callAgent): SSSR2Client处理REPLY消息 [%s]
	 */
	public static final int id_0x10050000_22 = 270;

	/**
	 * package(callAgent): SSR2Client丢弃未知消息 [%s]
	 */
	public static final int id_0x10050000_23 = 271;

	/**
	 * package(callAgent): dispatch未知事件, 抛弃. [%d %d %d]
	 */
	public static final int id_0x10050000_24 = 272;

	/**
	 * package(callAgent): 外拨总机成功，休眠3秒 [ %d %d %d %d]
	 */
	public static final int id_0x10050000_3 = 34;

	/**
	 * package(callAgent): sendDtmf [ %d %d %d %s]
	 */
	public static final int id_0x10050000_4 = 35;

	/**
	 * package(callAgent): 正在连接UVMG服务器 [ %s %d]
	 */
	public static final int id_0x10050000_5 = 67;

	/**
	 * package(callAgent): CallAgent MakeCall发送按键失败，挂断外呼通道[%d %d %d %s %s]
	 */
	public static final int id_0x10050000_6 = 254;

	/**
	 * package(callAgent): CMS随路数据 [%d %s]
	 */
	public static final int id_0x10050000_7 = 255;

	/**
	 * package(callAgent): CallAgent 拆除Session与Connection [0x%x %d %d %d %d]
	 */
	public static final int id_0x10050000_8 = 256;

	/**
	 * package(callAgent): CallAgent 清除Session [0x%x %d]
	 */
	public static final int id_0x10050000_9 = 257;

	/**
	 * package(callAgent): 挂断外呼通道 [ %d %d]
	 */
	public static final int id_0x10050001 = 5;

	/**
	 * package(callAgent): 启动CCOD CMS服务器端成功 [ %d]
	 */
	public static final int id_0x10050002 = 9;

	/**
	 * package(callAgent): 启动CCOD GLS客户器端成功 []
	 */
	public static final int id_0x10050003 = 10;

	/**
	 * package(callAgent): releaseCall receive SGEvent [ %d %d %d %d %X]
	 */
	public static final int id_0x10050004 = 14;

	/**
	 * package(callAgent): Asr Event Result [ %d %d %s]
	 */
	public static final int id_0x10050005 = 15;

	/**
	 * package(callAgent): makecall receive SGEvent [ %d %d %d %d %X %d]
	 */
	public static final int id_0x10050006 = 16;

	/**
	 * package(callAgent): 连接GLS服务器成功 []
	 */
	public static final int id_0x10050007 = 19;

	/**
	 * package(callAgent): callAgent.dispatchMessage异常 [ %s]
	 */
	public static final int id_0x10050008 = 22;

	/**
	 * package(callAgent): 获取信令事件 [ %s %s %x %d %s]
	 */
	public static final int id_0x10050009 = 23;

	/**
	 * package(callAgent): CMS客户端传回SLEE连接成功,cms[%s],proxy[%s]
	 */
	public static final int id_0x1005000A = 24;

	/**
	 * package(callAgent): 连接UVMG服务器成功 [ %s %d]
	 */
	public static final int id_0x1005000B = 27;

	/**
	 * package(callAgent): 发起MakeCall返回 [ %d %d %s %s %X]
	 */
	public static final int id_0x1005000C = 29;

	/**
	 * package(callAgent): 发起MakeCall成功 [ %d %d %s %s]
	 */
	public static final int id_0x1005000D = 30;

	/**
	 * package(callAgent): 搭接返回 [ %d %d %d %d %X]
	 */
	public static final int id_0x1005000E = 31;

	/**
	 * package(callAgent): MakeCall超时，挂断外呼通道 [ %d %d %d %s %s]
	 */
	public static final int id_0x1005000F = 33;

	/**
	 * package(callAgent): sendDtmf返回 [ %d %d %d %X %s]
	 */
	public static final int id_0x10050010 = 36;

	/**
	 * package(callAgent): 会议搭接返回 [ %d %d %d %d %X]
	 */
	public static final int id_0x10050011 = 38;

	/**
	 * package(callAgent): 收到媒体事件 [ %d %d %X %X]
	 */
	public static final int id_0x10050012 = 45;

	/**
	 * package(callAgent): 收到信令事件 [ %d %d %X]
	 */
	public static final int id_0x10050013 = 46;

	/**
	 * package(callAgent): answercall receive AnswerCallAck [ %d %d %d %d %X]
	 */
	public static final int id_0x10050014 = 51;

	/**
	 * package(callAgent): releaseCall receive ReleaseCallAck [ %d %d %d %d %X]
	 */
	public static final int id_0x10050015 = 52;

	/**
	 * package(callAgent): receive RegisterReply [ %s %d]
	 */
	public static final int id_0x10050016 = 53;

	/**
	 * package(callAgent): receive SGEvent [ %d %d %X %d]
	 */
	public static final int id_0x10050017 = 54;

	/**
	 * package(callAgent): receive IncomingCall [ %d %d %s %s %s %s]
	 */
	public static final int id_0x10050018 = 55;

	/**
	 * package(callAgent): receive MakeCallAck [ %d %d %d %X]
	 */
	public static final int id_0x10050019 = 56;

	/**
	 * package(callAgent): receive [ %d %d %s %X]
	 */
	public static final int id_0x1005001A = 57;

	/**
	 * package(callAgent): CallAgent receive MakeCallReply [ %d %d %X]
	 */
	public static final int id_0x1005001B = 58;

	/**
	 * package(callAgent): receive SwitchPortReply [ %d %X]
	 */
	public static final int id_0x1005001C = 59;

	/**
	 * package(callAgent): receive DisSwitchPortReply [ %d %X]
	 */
	public static final int id_0x1005001D = 60;

	/**
	 * package(callAgent): receive BindCreateConferenceReply [ %d %d %X]
	 */
	public static final int id_0x1005001E = 61;

	/**
	 * package(callAgent): receive MGEvent [ %d %d %X %d]
	 */
	public static final int id_0x1005001F = 62;

	/**
	 * package(callAgent): receive DtmfReply [ %d %d %X %d %s]
	 */
	public static final int id_0x10050020 = 63;

	/**
	 * package(callAgent): receive AsrReply [ %d %d %X %d %s]
	 */
	public static final int id_0x10050021 = 64;

	/**
	 * package(callAgent): receive ConferenceRecordReply [ %d %d %X]
	 */
	public static final int id_0x10050022 = 65;

	/**
	 * package(callAgent): answercall receive SGEvent [ %d %d %d %d %X]
	 */
	public static final int id_0x10050023 = 13;

	/**
	 * package(callAgent): 获取媒体事件 [ %s %s %x %d %s]
	 */
	public static final int id_0x10050024 = 21;

	/**
	 * package(callAgent): 60秒没有收到消息，主动断开连接 []
	 */
	public static final int id_0x10050025 = 49;

	/**
	 * package(callAgent): SSR2Client丢弃REPLY消息 [%s]
	 */
	public static final int id_0x10050026 = 235;

	/**
	 * package(callAgent): CMSMessageProcessor 分发入呼叫事件成功[0x%x %d]
	 */
	public static final int id_0x10050027 = 236;

	/**
	 * package(callAgent): CMSMessageProcessor 分发信令事件失败，资源不存在, [0x%X 0x%x %d]
	 */
	public static final int id_0x10050028 = 237;

	/**
	 * package(callAgent): CMSMessageProcessor 分发信令事件成功，[0x%X 0x%x %d]
	 */
	public static final int id_0x10050029 = 238;

	/**
	 * package(callAgent): CMSMessageProcessor 分发媒体事件失败，资源不存在 [0x%X 0x%x %d]
	 */
	public static final int id_0x10050030 = 239;

	/**
	 * package(callAgent): CMSMessageProcessor 分发媒体事件成功，[0x%X 0x%x %d]
	 */
	public static final int id_0x10050031 = 240;

	/**
	 * package(callAgent): CallAgent CMS Accept成功 [%d 0x%x %d]
	 */
	public static final int id_0x10050032 = 244;

	/**
	 * package(snmpAgent): 启动SNMP代理线程 []
	 */
	public static final int id_0x10060000 = 175;

	/**
	 * package(snmpAgent): Invalide PDU: NULL []
	 */
	public static final int id_0x10060001 = 179;

	/**
	 * package(snmpAgent): SNMP Agent rcv [ %s]
	 */
	public static final int id_0x10060002 = 180;

	/**
	 * package(snmpAgent): SNMP Agent send undefined trap msg [ %s]
	 */
	public static final int id_0x10060003 = 182;

	/**
	 * package(snmpAgent): SNMP Agent SendTrapMsg [ %s %s]
	 */
	public static final int id_0x10060004 = 183;

	/**
	 * package(perfMonitor): 收到无效的监控命令，断开连接 []
	 */
	public static final int id_0x10070001 = 168;

	/**
	 * package(perfMonitor): got new connection [ %s]
	 */
	public static final int id_0x10070002 = 167;

	/**
	 * package(perfMonitor): parsecommand [ %s]
	 */
	public static final int id_0x10070003 = 171;

	/**
	 * package(公用): 通用异常日志 [ %s]
	 */
	public static final int id_0x10100000 = 232;

	/**
	 * package(公用): 通用错误日志 []
	 */
	public static final int id_0x10100010 = 233;

	/**
	 * package(unifiedServiceManagement): 异常 [ %s]
	 */
	public static final int id_0x10110000 = 189;

	/**
	 * package(unifiedServiceManagement): UnifiedServiceManagement.run异常 [ %s]
	 */
	public static final int id_0x10110001 = 198;

	/**
	 * package(unifiedServiceManagement): 打开配置文件错误 []
	 */
	public static final int id_0x10110010 = 188;

	/**
	 * package(unifiedServiceManagement): 未发现ATSE安装目录，程序终止 []
	 */
	public static final int id_0x10110011 = 192;

	/**
	 * package(unifiedServiceManagement): 监听端口已被占用,SLEE无法启动 [ %d]
	 */
	public static final int id_0x10110012 = 196;

	/**
	 * package(channelManager): 异常 [ %s]
	 */
	public static final int id_0x10120000 = 74;

	/**
	 * package(channelManager): 查询通道状态返回错误 [ %s %X]
	 */
	public static final int id_0x10120001 = 71;

	/**
	 * package(channelManager): atsGetSGEvent异常 [ %s]
	 */
	public static final int id_0x10120002 = 161;

	/**
	 * package(channelManager): transFromXXXX异常 [ %s]
	 */
	public static final int id_0x10120003 = 162;

	/**
	 * package(channelManager): 接收到入呼叫事件但是当前的通道状态为Idle [ %s]
	 */
	public static final int id_0x10120010 = 77;

	/**
	 * package(channelManager): 启动服务失败,原因：绑定电话失败 [ %s %s]
	 */
	public static final int id_0x10120011 = 78;

	/**
	 * package(channelManager): 入呼叫路由表中无法查到通道对应的服务 [ %s]
	 */
	public static final int id_0x10120012 = 80;

	/**
	 * package(channelManager): ReasoningProvider拒绝服务。 [ %s %d %d %d]
	 */
	public static final int id_0x10120013 = 81;

	/**
	 * package(channelManager): 自动应答失败 [ %s %d %d]
	 */
	public static final int id_0x10120014 = 83;

	/**
	 * package(channelManager): GetContactInfoEx返回错误 [ %s %X]
	 */
	public static final int id_0x10120015 = 86;

	/**
	 * package(channelManager): 指定的启动文档不存在，使用系统默认文档 [ %s %s]
	 */
	public static final int id_0x10120016 = 89;

	/**
	 * package(channelManager): 外拨状态下，没有执行服务但是通道状态为Busy [ %s]
	 */
	public static final int id_0x10120017 = 91;

	/**
	 * package(channelManager): makeCall失败 [ %s %s %s %d]
	 */
	public static final int id_0x10120018 = 101;

	/**
	 * package(channelManager): attachCall，指定的呼叫不存在 [ %s %d %d]
	 */
	public static final int id_0x10120019 = 141;

	/**
	 * package(channelManager): GetAppChannel 出现异常 []
	 */
	public static final int id_0x1012001A = 142;

	/**
	 * package(channelManager): GetInboundChannel 队列为空 []
	 */
	public static final int id_0x1012001B = 143;

	/**
	 * package(channelManager): GetInboudChannel 出现异常 []
	 */
	public static final int id_0x1012001C = 145;

	/**
	 * package(channelManager): GetMoChannel 出现异常 []
	 */
	public static final int id_0x1012001D = 146;

	/**
	 * package(channelManager): GetReportChannel 出现异常 []
	 */
	public static final int id_0x1012001E = 147;

	/**
	 * package(channelManager): 接收到无效事件 [ %d]
	 */
	public static final int id_0x1012001F = 159;

	/**
	 * package(usmlReasoning): 异常 [ %s]
	 */
	public static final int id_0x10130000 = 214;

	/**
	 * package(usmlReasoning): reset(startParam)异常 [ %s]
	 */
	public static final int id_0x10130001 = 228;

	/**
	 * package(usmlReasoning): ManageServer.run异常 [ %s]
	 */
	public static final int id_0x10130002 = 230;

	/**
	 * package(usmlReasoning): 执行工作流时出现堆栈溢出 [ %s %s]
	 */
	public static final int id_0x10130010 = 216;

	/**
	 * package(usmlReasoning): onRunError [ %s %s]
	 */
	public static final int id_0x10130011 = 222;

	/**
	 * package(usmlReasoning): 收到无效的预编译命令 []
	 */
	public static final int id_0x10130012 = 229;

	/**
	 * package(billing): 异常 [ %s]
	 */
	public static final int id_0x10140000 = 4;

	/**
	 * package(billing): 写计费数据失败 [ %d]
	 */
	public static final int id_0x10140010 = 1;

	/**
	 * package(callAgent): 异常 [ %s]
	 */
	public static final int id_0x10150000 = 7;

	/**
	 * package(callAgent): RPC调用失败 [ %s]
	 */
	public static final int id_0x10150001 = 11;

	/**
	 * package(callAgent): reConnectVGProxy异常 [ %s]
	 */
	public static final int id_0x10150002 = 47;

	/**
	 * package(callAgent): CallAgent CMS Accept, 出现网络错误，重置回调接口.[%d 0x%x]
	 */
	public static final int id_0x10150003 = 241;

	/**
	 * package(callAgent): CallAgent CMS Accept, 出现网络错误，重置回调接口，回调接口已经更新.[%d
	 * 0x%x]
	 */
	public static final int id_0x10150004 = 242;

	/**
	 * package(callAgent): CallAgent CMS Accept失败.[%d 0x%x 0x%X]
	 */
	public static final int id_0x10150005 = 243;

	/**
	 * package(callAgent): CallAgent Join失败, [0x%X 0x%x 0x%X]
	 */
	public static final int id_0x10150006 = 245;

	/**
	 * package(callAgent): CallAgent CreateMediaEndpoint失败, [0x%x 0x%X]
	 */
	public static final int id_0x10150007 = 246;

	/**
	 * package(callAgent): CallAgent 清除全部Session[%d]
	 */
	public static final int id_0x10150008 = 247;

	/**
	 * package(callAgent): CallAgent 转移服务失败[0x%x %d %d %s %s]
	 */
	public static final int id_0x10150009 = 248;

	/**
	 * package(callAgent): CallAgent 访问DDS服务器失败，关闭连接 []
	 */
	public static final int id_0x1015000A = 249;

	/**
	 * package(callAgent): 文件路径不存在，不写消息日志 [%s]
	 */
	public static final int id_0x1015000B = 250;

	/**
	 * package(callAgent): SSR2Client注册失败，原因 [0x%08X]
	 */
	public static final int id_0x1015000C = 251;

	/**
	 * package(callAgent): AppMessageProcessor接收到无效事件 [%d]
	 */
	public static final int id_0x1015000D = 252;

	/**
	 * package(callAgent): ChannelDN指定的USML会话不存在 [%s %d]
	 */
	public static final int id_0x1015000E = 253;

	/**
	 * package(callAgent): answercall收到新的CallID的事件，本次呼叫已结束 [ %d %d]
	 */
	public static final int id_0x10150010 = 40;

	/**
	 * package(callAgent): releaseCall收到新的CallID的事件，本次呼叫已结束 [ %d %d]
	 */
	public static final int id_0x10150011 = 41;

	/**
	 * package(callAgent): doMgOperation收到新的CallID的事件，本次呼叫已结束 [ %d %d]
	 */
	public static final int id_0x10150012 = 42;

	/**
	 * package(callAgent): doMGOperation收到新的CallID的事件，本次呼叫已结束 [ %d %d]
	 */
	public static final int id_0x10150013 = 43;

	/**
	 * package(callAgent): makecall收到新的CallID的事件，本次呼叫已结束 [ %d %d]
	 */
	public static final int id_0x10150014 = 44;

	/**
	 * package(callAgent): 外拨已经成功，不能重复发起外拨 [ %d %d]
	 */
	public static final int id_0x10150015 = 28;

	/**
	 * package(callAgent): 搭接失败，挂断外呼通道 [ %d %d %d %d]
	 */
	public static final int id_0x10150016 = 32;

	/**
	 * package(callAgent): 会议搭接失败，挂断外呼通道 [ %d %d %d %d]
	 */
	public static final int id_0x10150017 = 39;

	/**
	 * package(callAgent): 与VGProxy的连接出现错误，关闭连接 []
	 */
	public static final int id_0x10150018 = 48;

	/**
	 * package(callAgent): atsMakeCall 正在发起外拨，不能重复发起外拨 [ %d]
	 */
	public static final int id_0x10150019 = 50;

	/**
	 * package(callAgent): 收到无效的包 [ %d %d]
	 */
	public static final int id_0x1015001A = 66;

	/**
	 * package(callAgent): 收包时发生IO错误 [ %s]
	 */
	public static final int id_0x1015001B = 69;

	/**
	 * package(callAgent): 发包时发生IO错误 [ %s]
	 */
	public static final int id_0x1015001C = 70;

	/**
	 * package(callAgent): 访问GSL服务器失败 [ %s]
	 */
	public static final int id_0x1015001D = 17;

	/**
	 * package(callAgent): makeCall失败 [ %s %s %d]
	 */
	public static final int id_0x1015001E = 37;

	/**
	 * package(callAgent): 网络连接出现错误，关闭连接 []
	 */
	public static final int id_0x1015001F = 68;

	/**
	 * package(callAgent): atsSingleStepTransfer调用失败 [ %d %d %s %s %d]
	 */
	public static final int id_0x10150020 = 12;

	/**
	 * package(snmpAgent): SnmpDataSender.Run异常 [ %s]
	 */
	public static final int id_0x10160001 = 172;

	/**
	 * package(snmpAgent): SnmpStatDataCounter.run异常 [ %s]
	 */
	public static final int id_0x10160002 = 173;

	/**
	 * package(snmpAgent): SnmpAgentHandler.config异常 [ %s]
	 */
	public static final int id_0x10160003 = 174;

	/**
	 * package(snmpAgent): SnmpAgentHandler.start异常 [ %s]
	 */
	public static final int id_0x10160004 = 176;

	/**
	 * package(snmpAgent): SnmpAgentHandler.processGet异常 [ %s]
	 */
	public static final int id_0x10160005 = 177;

	/**
	 * package(snmpAgent): SnmpAgentHandler.processSet异常 [ %s]
	 */
	public static final int id_0x10160006 = 178;

	/**
	 * package(snmpAgent): SnmpAgentHandler.sendTrap异常 [ %s]
	 */
	public static final int id_0x10160007 = 181;

	/**
	 * package(perfMonitor): 异常 [ %s]
	 */
	public static final int id_0x10170000 = 170;

	/**
	 * package(perfMonitor): writePackage异常 [ %s]
	 */
	public static final int id_0x10170001 = 163;

	/**
	 * package(perfMonitor): checkListening异常 [ %s]
	 */
	public static final int id_0x10170002 = 164;

	/**
	 * package(perfMonitor): writePackage异常 [ %s]
	 */
	public static final int id_0x10170003 = 165;

	/**
	 * package(perfMonitor): parseCommand异常 [ %s]
	 */
	public static final int id_0x10170004 = 169;

	/**
	 * package(perfMonitor): PerfDataSender.run异常 [ %s]
	 */
	public static final int id_0x10170005 = 166;

	/**
	 * package(公用): 通用严重错误日志 []
	 */
	public static final int id_0x10200000 = 234;

	/**
	 * package(unifiedServiceManagement): 用户配置的ATSE的安装路径错误，程序终止 []
	 */
	public static final int id_0x10210001 = 184;

	/**
	 * package(unifiedServiceManagement): 配置文件不存在，程序终止 []
	 */
	public static final int id_0x10210002 = 185;

	/**
	 * package(unifiedServiceManagement): 没有配置SLEE在GLS上的注册名称 []
	 */
	public static final int id_0x10210003 = 187;

	/**
	 * package(channelManager): CATSChannel.execInboundSrv 异常 [ %s]
	 */
	public static final int id_0x10220001 = 73;

	/**
	 * package(channelManager): CATSChannel.run异常 [ %s]
	 */
	public static final int id_0x10220002 = 75;

	/**
	 * package(channelManager): 入呼叫处理异常 [ %s]
	 */
	public static final int id_0x10220003 = 88;

	/**
	 * package(channelManager): 出呼叫下呼叫处理异常 [ %s]
	 */
	public static final int id_0x10220004 = 92;

	/**
	 * package(channelManager): 通道管理线程出现异常 []
	 */
	public static final int id_0x10220005 = 160;

	/**
	 * package(billing): 计费处理线程异常 []
	 */
	public static final int id_0x10240001 = 3;

	/**
	 * package(callAgent): 服务器端启动失败 []
	 */
	public static final int id_0x10250001 = 6;

	/**
	 * package(callAgent): 注册到GLS失败 [ %d]
	 */
	public static final int id_0x10250002 = 20;

	/**
	 * package(callAgent): 连接UVMG服务器失败 [ %s %d]
	 */
	public static final int id_0x10250003 = 25;

	public static final int[] ids = { 0x10040001, 0x10140010, 0x10040000,
			0x10240001, 0x10140000, 0x10050001, 0x10250001, 0x10150000,
			0x10050000, 0x10050002, 0x10050003, 0x10150001, 0x10150020,
			0x10050023, 0x10050004, 0x10050005, 0x10050006, 0x1015001D,
			0x10050000, 0x10050007, 0x10250002, 0x10050024, 0x10050008,
			0x10050009, 0x1005000A, 0x10250003, 0x10050000, 0x1005000B,
			0x10150015, 0x1005000C, 0x1005000D, 0x1005000E, 0x10150016,
			0x1005000F, 0x10050000, 0x10050000, 0x10050010, 0x1015001E,
			0x10050011, 0x10150017, 0x10150010, 0x10150011, 0x10150012,
			0x10150013, 0x10150014, 0x10050012, 0x10050013, 0x10150002,
			0x10150018, 0x10050025, 0x10150019, 0x10050014, 0x10050015,
			0x10050016, 0x10050017, 0x10050018, 0x10050019, 0x1005001A,
			0x1005001B, 0x1005001C, 0x1005001D, 0x1005001E, 0x1005001F,
			0x10050020, 0x10050021, 0x10050022, 0x1015001A, 0x10050000,
			0x1015001F, 0x1015001B, 0x1015001C, 0x10120001, 0x10020000,
			0x10220001, 0x10120000, 0x10220002, 0x10020001, 0x10120010,
			0x10120011, 0x10020002, 0x10120012, 0x10120013, 0x10020003,
			0x10120014, 0x10020004, 0x10020005, 0x10120015, 0x1002002A,
			0x10220003, 0x10120016, 0x10020006, 0x10120017, 0x10220004,
			0x10020000, 0x10020007, 0x10020000, 0x10020008, 0x10020000,
			0x10020009, 0x10020000, 0x1002000A, 0x10120018, 0x10020000,
			0x1002001D, 0x10020000, 0x1002000B, 0x10020000, 0x1002000C,
			0x10020000, 0x1002000D, 0x10020000, 0x1002000E, 0x10020000,
			0x1002000F, 0x10020000, 0x10020010, 0x10020000, 0x10020011,
			0x10020000, 0x10020012, 0x10020000, 0x10020013, 0x10020000,
			0x10020000, 0x10020014, 0x10020000, 0x10020015, 0x10020000,
			0x10020016, 0x10020000, 0x10020017, 0x10020000, 0x10020018,
			0x10020000, 0x10020019, 0x10020000, 0x1002001A, 0x10020000,
			0x1002001B, 0x10020000, 0x1002001C, 0x10120019, 0x1012001A,
			0x1012001B, 0x1002001E, 0x1012001C, 0x1012001D, 0x1012001E,
			0x1002001F, 0x10020020, 0x10020021, 0x10020022, 0x10020023,
			0x10020024, 0x10020025, 0x10020026, 0x10020027, 0x10020028,
			0x10020029, 0x1012001F, 0x10220005, 0x10120002, 0x10120003,
			0x10170001, 0x10170002, 0x10170003, 0x10170005, 0x10070002,
			0x10070001, 0x10170004, 0x10170000, 0x10070003, 0x10160001,
			0x10160002, 0x10160003, 0x10060000, 0x10160004, 0x10160005,
			0x10160006, 0x10060001, 0x10060002, 0x10160007, 0x10060003,
			0x10060004, 0x10210001, 0x10210002, 0x10010000, 0x10210003,
			0x10110010, 0x10110000, 0x10010000, 0x10010001, 0x10110011,
			0x10010000, 0x10010000, 0x10010000, 0x10110012, 0x10010004,
			0x10110001, 0x10010000, 0x10010000, 0x10010000, 0x10010000,
			0x10010002, 0x10010003, 0x10030000, 0x10030000, 0x10030000,
			0x10030000, 0x10030000, 0x10030000, 0x10030000, 0x10030001,
			0x10030002, 0x10130000, 0x10030003, 0x10130010, 0x10030004,
			0x10030005, 0x10030006, 0x10030007, 0x10030000, 0x10130011,
			0x10030000, 0x10030000, 0x10030000, 0x10030000, 0x10030008,
			0x10130001, 0x10130012, 0x10130002, 0x10000000, 0x10100000,
			0x10100010, 0x10200000, 0x10050026, 0x10050027, 0x10050028,
			0x10050029, 0x10050030, 0x10050031, 0x10150003, 0x10150004,
			0x10150005, 0x10050032, 0x10150006, 0x10150007, 0x10150008,
			0x10150009, 0x1015000A, 0x1015000B, 0x1015000C, 0x1015000D,
			0x1015000E, 0x10050000, 0x10050000, 0x10050000, 0x10050000,
			0x10050000, 0x10050000, 0x10050000, 0x10050000, 0x10050000,
			0x10050000, 0x10050000, 0x10050000, 0x10050000, 0x10050000,
			0x10050000, 0x10050000, 0x10050000, 0x10050000, 0x10050000,
			0x10020000, 0x10020000, 0x10020000, 0x10020000, 0x10020000,
			0x10020000, 0x10020000, 0x10020000, 0x10020000, 0x10020000,
			0x10020000, 0x10020000, 0x10020000, 0x10020000, 0x10020000,
			0x10020000, 0x10020000, 0x10020000, 0x10020000};

}
