package com.channelsoft.slee.usmlreasoning;

import java.util.Vector;

import org.w3c.dom.Node;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.util.Constant;

public class SrvIONode extends ServiceNode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6971645094280776994L;

	public Vector<KnowledgeVariable> inVars = new Vector<KnowledgeVariable>();

	public Vector<IOProcess> ioProcesses = new Vector<IOProcess>();

	public boolean isProcessError;

	public KnowledgeVariable lastErrorVar;

	public Vector<KnowledgeVariable> outVars = new Vector<KnowledgeVariable>();

	public KnowledgeVariable resultVar;

	SrvIONode()
	{
	}

	public SrvIONode(Service service, Workflow workflow)
	{
		super(service, workflow);
		nextSrvNode = null;
		resultVar = null;
		lastErrorVar = null;
	}

	ServiceNode browseNode(Node node) throws Exception
	{
		nodeName = XMLFunction.returnValueFromAttr(node, "Name");

		String szArrib = XMLFunction.returnValueFromAttr(node, "ProcessError");
		if ("true".equals(szArrib))
		{
			isProcessError = true;
		}
		else
		{
			isProcessError = false;
		}

		String strNodeName;
		Node ioNode = null;

		String varName;
		String varType;
		szArrib = XMLFunction.returnValueFromAttr(node, "Name");

		// IO接点
		ioNode = XMLFunction.getFirstChild(node);
		while (null != ioNode)
		{
			strNodeName = XMLFunction.getNodeName(ioNode);

			if ("InputVarNameSet".equals(strNodeName))
			{
				Node inVarNode = null;
				inVarNode = XMLFunction.getFirstChild(ioNode);
				while (null != inVarNode)
				{
					XMLFunction.verifyNodeTag(inVarNode, "VarName");
					varName = XMLFunction.getNodeText(inVarNode);

					KnowledgeVariable var = service.lookupKeyVar(varName);
					if (null == var)
					{
						throw new MyException(String.format(
								"节点%s中输入变量%s等于NULL", nodeName, varName));
					}
					inVars.add(var);

					inVarNode = XMLFunction.getNextSibling(inVarNode);
				}
			}
			else if ("OutVarNameSet".equals(strNodeName))
			{// OutVarNameSet
				Node outVarNode = null;
				outVarNode = XMLFunction.getFirstChild(ioNode);
				while (null != outVarNode)
				{
					// CXMLFunction::VerifyNodeTag(OutVarNode,"VarName");
					varName = XMLFunction.getNodeText(outVarNode);
					KnowledgeVariable var = service.lookupKeyVar(varName);
					if (null == var)
					{
						throw new MyException(String.format(
								"节点%s中输出变量%s等于NULL", nodeName, varName));
					}

					varType = XMLFunction.getNodeName(outVarNode);

					if (Constant.ResultVarNameTag.equals(varType))
					{
						if (Constant.DefResultVarName.equals(varName))
						{
							resultVar = var;
						}
						else if (Constant.DefLastErrorVarName.equals(varName))
						{
							lastErrorVar = var;
						}
					}
					else
					{
						outVars.add(var);
					}

					outVarNode = XMLFunction.getNextSibling(outVarNode);
				}
			}
			else
			{// IOProcessGroup
				if (Constant.strPlayFile.equals(strNodeName))
				{
					int playCount = XMLFunction.getIntFromAttrib(ioNode,
							"PlayCount");
					PlayVoice playVoice = null;
					if (playCount > 0)
					{
						playVoice = new PlayVoice(service);
					}
					else
					{
						playVoice = new PlayVoiceAsync(service);
					}

					playVoice.ioProcessName = Constant.strPlayFile;
					ioProcesses.add(playVoice);

					playVoice.fileName = XMLFunction.returnValueFromAttr(
							ioNode, "FileName");

					szArrib = XMLFunction.returnValueFromAttr(ioNode,
							"FileNameVar");

					playVoice.fileNameVar = service.lookupKeyVar(szArrib);

					String strFalse = XMLFunction.returnValueFromAttr(ioNode,
							"CanBreak");
					if ("false".equals(strFalse))
					{
						playVoice.canBreak = false;
					}
					else
					{
						playVoice.canBreak = true;
					}

					playVoice.breakList = XMLFunction.returnValueFromAttr(
							ioNode, "BreakList");
					playVoice.rate = XMLFunction.getIntFromAttrib(ioNode,
							"Rate");
					if ((playVoice.rate != 6) && (playVoice.rate != 8))
					{
						playVoice.rate = 6;
					}

					playVoice.playCount = XMLFunction.getIntFromAttrib(ioNode,
							"PlayCount");

					// 0代表当前为异步调用IONode,所以不用容错;
					// if(playvoice->m_nPlayCount == 0)
					// playvoice->m_nPlayCount = 1;
					playVoice.waitTimeOnce = XMLFunction.getIntFromAttrib(
							ioNode, "WaitTimeOnce");
				}
				else if (Constant.strRecordVoice.equals(strNodeName))
				{
					int timeDuration = XMLFunction.getIntFromAttrib(ioNode,
							"TimeDuration");
					RecodeVoice recodeVoice = null;
					if (timeDuration == 0)
					{
						recodeVoice = new RecodeVoiceAsync(service);
					}
					else
					{
						recodeVoice = new RecodeVoice(service);
					}

					szArrib = XMLFunction.returnValueFromAttr(ioNode,
							"FileNameVar");

					recodeVoice.fileNameVar = service.lookupKeyVar(szArrib);
					if (recodeVoice.fileNameVar == null)
					{
						throw new MyException(String.format(
								"节点%s中录音文件变量%s等于NULL", this.nodeName,
								recodeVoice.fileNameVar));
					}

					szArrib = XMLFunction
							.returnValueFromAttr(ioNode, "EndFlag");

					if (szArrib.length() != 0)
					{
						recodeVoice.endFlag = szArrib.charAt(0);
					}
					else
					{
						recodeVoice.endFlag = 0;
					}
					recodeVoice.timeDuration = XMLFunction.getIntFromAttrib(
							ioNode, "TimeDuration");

					recodeVoice.rate = XMLFunction.getIntFromAttrib(ioNode,
							"Rate");
					if ((recodeVoice.rate != 6) && (recodeVoice.rate != 8))
					{
						recodeVoice.rate = 6;
					}
					// 0代表当前为异步调用IONode,所以不用容错;
					// if( recodevoice->m_nTimeDuration == 0)
					// recodevoice->m_nTimeDuration = 1800;
					recodeVoice.timeDurationVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "TimeDurationVar"));
					recodeVoice.ioProcessName = Constant.strRecordVoice;
					ioProcesses.add(recodeVoice);
				}
				else if (Constant.strSendDTMF.equals(strNodeName))
				{
					SendDTMF sendDTMF = new SendDTMF(service);
					sendDTMF.dtmfVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "DTMFVar"));
					if (null == sendDTMF.dtmfVar)
					{
						throw new MyException(String.format(
								"节点%s中DTMFVar变量%s等于NULL", nodeName,
								sendDTMF.dtmfVar));
					}
					ioProcesses.add(sendDTMF);
					sendDTMF.ioProcessName = Constant.strSendDTMF;
				}
				else if (Constant.strGetDTMF.equals(strNodeName))
				{
					GetDTMF getDTMF = new GetDTMF(service);
					getDTMF.dtmfVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "DTMFVar"));
					if (getDTMF.dtmfVar == null)
					{
						throw new MyException(String.format(
								"节点%s中DTMFVar变量%s等于NULL", nodeName,
								getDTMF.dtmfVar));
					}
					getDTMF.endCharVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "EndCharVar"));

					String str = XMLFunction.returnValueFromAttr(ioNode,
							"IsClearDTMFBuffer");

					if ("true".equals(str))
					{
						getDTMF.isClearBuffer = true;
					}
					else
					{
						getDTMF.isClearBuffer = false;
					}
					str = XMLFunction.returnValueFromAttr(ioNode,
							"ReturnEndChar");
					if ("true".equals(str))
					{
						getDTMF.returnEndChar = true;
					}
					else
					{
						getDTMF.returnEndChar = false;
					}

					szArrib = XMLFunction
							.returnValueFromAttr(ioNode, "EndFlag");
					getDTMF.endFlag = szArrib;
					getDTMF.dtmfCount = XMLFunction.getIntFromAttrib(ioNode,
							"Count");
					if (getDTMF.dtmfCount < 0)
					{
						getDTMF.dtmfCount = 0;// 0代表接收任意多个字符
					}
					getDTMF.timeoutSecond = XMLFunction.getIntFromAttrib(
							ioNode, "TimeoutSecond");
					if (getDTMF.timeoutSecond <= 0)
					{
						getDTMF.timeoutSecond = 15;
					}
					getDTMF.timeoutSecondVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "TimeoutSecondVar"));
					getDTMF.betweenTimeout = XMLFunction.getIntFromAttrib(
							ioNode, "BetweenTimeout");
					if (getDTMF.betweenTimeout <= 0)
					{
						getDTMF.betweenTimeout = 15;
					}
					getDTMF.betweenTimeoutVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "BetweenTimeoutVar"));
					getDTMF.ioProcessName = Constant.strGetDTMF;
					ioProcesses.add(getDTMF);
				}
				else if (Constant.strSendFax.equals(strNodeName))
				{
					SendFax sendFax = new SendFax(service);
					sendFax.fileNameVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "FileNameVar"));
					if (null == sendFax.fileNameVar)
					{
						throw new MyException(String.format(
								"节点%s中传真文件变量%s等于NULL", nodeName,
								sendFax.fileNameVar));
					}
					sendFax.ioProcessName = Constant.strSendFax;

					ioProcesses.add(sendFax);
				}
				else if (Constant.strReceiveFax.equals(strNodeName))
				{
					ReceiveFax receiveFax = new ReceiveFax(service);
					receiveFax.fileNameVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "FileNameVar"));
					if (null == receiveFax.fileNameVar)
					{
						throw new MyException(String.format(
								"节点%s中传真文件变量%s等于NULL", nodeName,
								receiveFax.fileNameVar));
					}

					receiveFax.timeDuration = XMLFunction.getIntFromAttrib(
							ioNode, "TimeDuration");
					if (receiveFax.timeDuration == 0)
					{
						receiveFax.timeDuration = 1800;
					}
					receiveFax.ioProcessName = Constant.strReceiveFax;
					ioProcesses.add(receiveFax);
				}
				else if (Constant.strVoiceEdit.equals(strNodeName))
				{
					VoiceEdit voiceEdit = new VoiceEdit(service);
					voiceEdit.ioProcessName = Constant.strVoiceEdit;
					String str = XMLFunction.returnValueFromAttr(ioNode,
							"PreGetCash");
					if ("true".equals(str))
					{
						voiceEdit.isPreGetCash = true;
					}
					else
					{
						voiceEdit.isPreGetCash = false;
					}

					Node childNode = null;
					childNode = XMLFunction.getFirstChild(ioNode);
					while (null != childNode)
					{
						String strChildNodeName;
						strChildNodeName = XMLFunction.getNodeName(childNode);

						if (Constant.strPlayFile.equals(strChildNodeName))
						{
							voiceEdit.playVoice.fileName = XMLFunction
									.returnValueFromAttr(childNode, "FileName");
							szArrib = XMLFunction.returnValueFromAttr(
									childNode, "FileNameVar");
							voiceEdit.playVoice.fileNameVar = service
									.lookupKeyVar(szArrib);

							if ("false".equals(XMLFunction.returnValueFromAttr(
									childNode, "CanBreak")))
							{
								voiceEdit.playVoice.canBreak = false;
							}
							else
							{
								voiceEdit.playVoice.canBreak = true;
							}
							voiceEdit.playVoice.breakList = XMLFunction
									.returnValueFromAttr(childNode, "BreakList");
							voiceEdit.playVoice.rate = XMLFunction
									.getIntFromAttrib(childNode, "Rate");
							if ((voiceEdit.playVoice.rate != 6)
									&& (voiceEdit.playVoice.rate != 8))
							{
								voiceEdit.playVoice.rate = 6;
							}
							voiceEdit.playVoice.playCount = XMLFunction
									.getIntFromAttrib(childNode, "PlayCount");
							if (voiceEdit.playVoice.playCount <= 0)
							{
								voiceEdit.playVoice.playCount = 1;
							}
							voiceEdit.playVoice.waitTimeOnce = XMLFunction
									.getIntFromAttrib(childNode, "WaitTimeOnce");
							if (voiceEdit.playVoice.waitTimeOnce <= 0)
							{
								voiceEdit.playVoice.waitTimeOnce = 5;
							}
							voiceEdit.playVoice.ioProcessName = Constant.strPlayFile;
						}
						else if (Constant.strGetDTMF.equals(strChildNodeName))
						{
							voiceEdit.getDTMF.dtmfVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"DTMFVar"));
							if (voiceEdit.getDTMF.dtmfVar == null)
							{
								throw new MyException(String.format(
										"节点%s中DTMFVar变量%s等于NULL", nodeName,
										voiceEdit.getDTMF.dtmfVar));
							}
							voiceEdit.getDTMF.endCharVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"EndCharVar"));

							str = XMLFunction.returnValueFromAttr(childNode,
									"IsClearDTMFBuffer");
							if ("true".equals(str))
							{
								voiceEdit.getDTMF.isClearBuffer = true;
							}
							else
							{
								voiceEdit.getDTMF.isClearBuffer = false;
							}
							str = XMLFunction.returnValueFromAttr(childNode,
									"ReturnEndChar");
							if ("true".equals(str))
							{
								voiceEdit.getDTMF.returnEndChar = true;
							}
							else
							{
								voiceEdit.getDTMF.returnEndChar = false;
							}

							szArrib = XMLFunction.returnValueFromAttr(
									childNode, "EndFlag");
							voiceEdit.getDTMF.endFlag = szArrib;
							voiceEdit.getDTMF.dtmfCount = XMLFunction
									.getIntFromAttrib(childNode, "Count");
							if (voiceEdit.getDTMF.dtmfCount < 0)
							{
								voiceEdit.getDTMF.dtmfCount = 0;// 0代表接收任意多个字符
							}
							voiceEdit.getDTMF.timeoutSecond = XMLFunction
									.getIntFromAttrib(childNode,
											"TimeoutSecond");
							if (voiceEdit.getDTMF.timeoutSecond <= 0)
							{
								voiceEdit.getDTMF.timeoutSecond = 15;
							}
							voiceEdit.getDTMF.timeoutSecondVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"TimeoutSecondVar"));
							voiceEdit.getDTMF.betweenTimeout = XMLFunction
									.getIntFromAttrib(childNode,
											"BetweenTimeout");
							if (voiceEdit.getDTMF.betweenTimeout <= 0)
							{
								voiceEdit.getDTMF.betweenTimeout = 15;
							}
							voiceEdit.getDTMF.betweenTimeoutVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"BetweenTimeoutVar"));
							voiceEdit.getDTMF.ioProcessName = Constant.strGetDTMF;
						}
						else
						{
							;
						}

						childNode = XMLFunction.getNextSibling(childNode);

					}
					// VoiceEdit,包含的子过程PlayFile和GetDTMF参数的重新解释
					// 在PlayVoice中指定的循环次数即是VoiceEdit的循环次数，PlayVoice自己没有循环
					// 循环等待时长即为GetDTMF的超时时长
					voiceEdit.doCount = voiceEdit.playVoice.playCount;
					voiceEdit.playVoice.playCount = 1;// 设置PlayVoice的循环次数为1
					ioProcesses.add(voiceEdit);
					// ///////////////////////////////////////////////////////////////////////
				}
				else if (Constant.strASREdit.equals(strNodeName))
				{
					ASREdit asr = new ASREdit(service);// PreGetCash
					asr.ioProcessName = Constant.strASREdit;
					String str = XMLFunction.returnValueFromAttr(ioNode,
							"PreGetCash");
					if ("true".equals(str))
					{
						asr.isPreGetCash = true;
					}
					else
					{
						asr.isPreGetCash = false;
					}

					Node childNode = null;
					childNode = XMLFunction.getFirstChild(ioNode);
					while (null != childNode)
					{
						String strChildNodeName;
						strChildNodeName = childNode.getNodeName();

						if (Constant.strPlayTTS.equals(strChildNodeName))
						{
							asr.playTTS.ioProcessName = Constant.strPlayTTS;
							if ("false".equals(XMLFunction.returnValueFromAttr(
									childNode, "CanBreak")))
							{
								asr.playTTS.canBreak = false;
							}
							else
							{
								asr.playTTS.canBreak = true;
							}
							asr.playTTS.breakList = XMLFunction
									.returnValueFromAttr(childNode, "BreakList");
							if ("true".equals(XMLFunction.returnValueFromAttr(
									childNode, "ThirdParty")))
							{
								asr.playTTS.thirdParty = true;
							}
							else
							{
								asr.playTTS.thirdParty = false;
							}

							asr.playTTS.languageTypeVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"LanguageTypeVar"));

							asr.playTTS.rate = XMLFunction.getIntFromAttrib(
									childNode, "Rate");
							if ((asr.playTTS.rate != 6)
									&& (asr.playTTS.rate != 8))
							{
								asr.playTTS.rate = 6;
							}
							asr.playTTS.playCount = XMLFunction
									.getIntFromAttrib(childNode, "PlayCount");
							if (asr.playTTS.playCount == 0)
							{
								asr.playTTS.playCount = 1;
							}
							asr.playTTS.waitTimeOnce = XMLFunction
									.getIntFromAttrib(childNode, "WaitTimeOnce");
							if (asr.playTTS.waitTimeOnce == 0)
							{
								asr.playTTS.waitTimeOnce = 5;
							}

							Node childNodeSub = null;
							childNodeSub = XMLFunction.getFirstChild(childNode);
							while (null != childNodeSub)
							{
								TTSSegmentImpl segment = new TTSSegmentImpl();
								strChildNodeName = XMLFunction
										.getNodeName(childNodeSub);

								if ("VoiceFileName".equals(strChildNodeName))
								{
									segment.isVar = false;
									segment.fileName = XMLFunction
											.getNodeText(childNodeSub);

								}
								else if ("VoiceFileNameVar"
										.equals(strChildNodeName))
								{
									segment.isVar = false;
									szArrib = XMLFunction
											.getNodeText(childNodeSub);
									segment.fileNameVar = service
											.lookupKeyVar(szArrib);
									if (segment.fileNameVar == null)
									{
										throw new MyException(String.format(
												"节点%s中TTS文件变量%s等于NULL",
												nodeName, szArrib));
									}

								}
								else if ("VarName".equals(strChildNodeName))
								{
									segment.isVar = true;
									szArrib = XMLFunction
											.getNodeText(childNodeSub);

									segment.ttsOutVar = service
											.lookupKeyVar(szArrib);
									if (segment.ttsOutVar == null)
									{
										throw new MyException(String.format(
												"节点%s中TTS文件变量%s等于NULL",
												nodeName, szArrib));
									}

								}
								else
								{
									;
								}
								asr.playTTS.ttsSegmentGroup.add(segment);
								childNodeSub = XMLFunction
										.getNextSibling(childNodeSub);

							}// while
						}
						else if (Constant.strGetASR.equals(strChildNodeName))
						{
							asr.getASR.ioProcessName = Constant.strGetASR;

							asr.getASR.grammarStringVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"GrammarStringVar"));
							asr.getASR.grammarFileVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"GrammarFileVar"));

							asr.getASR.noSpeechTimeout = XMLFunction
									.getIntFromAttrib(childNode,
											"NoSpeechTimeout");
							asr.getASR.noSpeechTimeoutVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"NoSpeechTimeoutVar"));

							asr.getASR.betweenWordTimeout = XMLFunction
									.getIntFromAttrib(childNode,
											"BetweenWordTimeout");
							asr.getASR.betweenWordTimeoutVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"BetweenWordTimeoutVar"));

							asr.getASR.acceptScore = XMLFunction
									.getIntFromAttrib(childNode, "AcceptScore");
							asr.getASR.acceptScoreVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"AcceptScoreVar"));

							asr.getASR.resultMaxNum = XMLFunction
									.getIntFromAttrib(childNode, "ResultMaxNum");
							asr.getASR.resultMaxNumVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"ResultMaxNumVar"));

							asr.getASR.resultVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"ResultVar"));

						}
						else
						{
							;
						}

						childNode = XMLFunction.getNextSibling(childNode);

					}// while
					// VoiceEdit,包含的子过程PlayFile和GetDTMF参数的重新解释
					// 在PlayVoice中指定的循环次数即是VoiceEdit的循环次数，PlayVoice自己没有循环
					// 循环等待时长即为GetDTMF的超时时长
					asr.doCount = asr.playTTS.playCount;
					asr.playTTS.playCount = 1;// 设置PlayVoice的循环次数为1
					ioProcesses.add(asr);

				}
				else if (Constant.strASREdit2.equals(strNodeName))
				{
					ASREdit2 asr = new ASREdit2(service);// PreGetCash
					asr.ioProcessName = Constant.strASREdit2;
					String str = XMLFunction.returnValueFromAttr(ioNode,
							"PreGetCash");
					if ("true".equals(str))
					{
						asr.isPreGetCash = true;
					}
					else
					{
						asr.isPreGetCash = false;
					}

					Node childNode = null;
					childNode = XMLFunction.getFirstChild(ioNode);
					while (null != childNode)
					{
						String strChildNodeName;
						strChildNodeName = XMLFunction.getNodeName(childNode);

						if (Constant.strVoiceListEdit.equals(strChildNodeName))
						{
							Node childNodeVoiceListEdit = null;
							childNodeVoiceListEdit = XMLFunction
									.getFirstChild(childNode);
							while (childNodeVoiceListEdit != null)
							{
								String childNodeVoiceListEditName = XMLFunction
										.getNodeName(childNodeVoiceListEdit);
								if (Constant.strPlayTTS
										.equals(childNodeVoiceListEditName))
								{
									if ("false".equals(XMLFunction
											.returnValueFromAttr(
													childNodeVoiceListEdit,
													"CanBreak")))
									{
										asr.voiceListEdit.playTTS.canBreak = false;
									}
									else
									{
										asr.voiceListEdit.playTTS.canBreak = true;
									}
									asr.voiceListEdit.playTTS.breakList = XMLFunction
											.returnValueFromAttr(
													childNodeVoiceListEdit,
													"BreakList");
									if ("true".equals(XMLFunction
											.returnValueFromAttr(
													childNodeVoiceListEdit,
													"ThirdParty")))
									{
										asr.voiceListEdit.playTTS.thirdParty = true;
									}
									else
									{
										asr.voiceListEdit.playTTS.thirdParty = false;
									}

									asr.voiceListEdit.playTTS.languageTypeVar = service
											.lookupKeyVar(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"LanguageTypeVar"));

									asr.voiceListEdit.playTTS.rate = XMLFunction
											.getIntFromAttrib(
													childNodeVoiceListEdit,
													"Rate");
									if ((asr.voiceListEdit.playTTS.rate != 6)
											&& (asr.voiceListEdit.playTTS.rate != 8))
									{
										asr.voiceListEdit.playTTS.rate = 6;
									}
									asr.voiceListEdit.playTTS.playCount = Integer
											.parseInt(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"PlayCount"));
									if (asr.voiceListEdit.playTTS.playCount == 0)
									{
										asr.voiceListEdit.playTTS.playCount = 1;
									}
									asr.voiceListEdit.playTTS.waitTimeOnce = Integer
											.parseInt(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"WaitTimeOnce"));
									if (asr.voiceListEdit.playTTS.waitTimeOnce == 0)
									{
										asr.voiceListEdit.playTTS.waitTimeOnce = 5;
									}

									Node childNode1 = null;
									childNode1 = XMLFunction
											.getFirstChild(childNodeVoiceListEdit);
									while (childNode1 != null)
									{
										TTSSegmentImpl segment = new TTSSegmentImpl();
										String strChildNodeName1 = XMLFunction
												.getNodeName(childNode1);

										if ("VoiceFileName"
												.equals(strChildNodeName1))
										{
											segment.isVar = false;
											segment.fileName = XMLFunction
													.getNodeText(childNode1);

										}
										else if ("VoiceFileNameVar"
												.equals(strChildNodeName1))
										{
											segment.isVar = false;
											szArrib = XMLFunction
													.getNodeText(childNode1);
											segment.fileNameVar = service
													.lookupKeyVar(szArrib);
											if (segment.fileNameVar == null)
											{
												throw new MyException(
														String
																.format(
																		"节点%s中TTS文件变量%s等于NULL",
																		nodeName,
																		szArrib));
											}

										}
										else if ("VarName"
												.equals(strChildNodeName1))
										{
											segment.isVar = true;
											szArrib = XMLFunction
													.getNodeText(childNode1);

											segment.ttsOutVar = service
													.lookupKeyVar(szArrib);
											if (segment.ttsOutVar == null)
											{
												throw new MyException(
														String
																.format(
																		"节点%s中TTS文件变量%s等于NULL",
																		nodeName,
																		szArrib));
											}

										}
										asr.voiceListEdit.playTTS.ttsSegmentGroup
												.add(segment);
										childNode1 = XMLFunction
												.getNextSibling(childNode1);
									}

									asr.voiceListEdit.playTTS.ioProcessName = Constant.strPlayTTS;
								}
								else if (Constant.strGetDTMF
										.equals(childNodeVoiceListEditName))
								{
									asr.voiceListEdit.getDTMF.dtmfVar = service
											.lookupKeyVar(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"DTMFVar"));
									if (asr.voiceListEdit.getDTMF.dtmfVar == null)
									{
										throw new MyException(
												String
														.format(
																"节点%s中DTMFVar变量%s等于NULL",
																nodeName,
																asr.voiceListEdit.getDTMF.dtmfVar));
									}
									asr.voiceListEdit.getDTMF.endCharVar = service
											.lookupKeyVar(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"EndCharVar"));

									str = XMLFunction.returnValueFromAttr(
											childNodeVoiceListEdit,
											"IsClearDTMFBuffer");
									if ("true".equals(str))
									{
										asr.voiceListEdit.getDTMF.isClearBuffer = true;
									}
									else
									{
										asr.voiceListEdit.getDTMF.isClearBuffer = false;
									}
									str = XMLFunction.returnValueFromAttr(
											childNodeVoiceListEdit,
											"ReturnEndChar");
									if ("true".equals(str))
									{
										asr.voiceListEdit.getDTMF.returnEndChar = true;
									}
									else
									{
										asr.voiceListEdit.getDTMF.returnEndChar = false;
									}

									szArrib = XMLFunction.returnValueFromAttr(
											childNodeVoiceListEdit, "EndFlag");
									asr.voiceListEdit.getDTMF.endFlag = szArrib;
									asr.voiceListEdit.getDTMF.dtmfCount = Integer
											.parseInt(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"Count"));
									if (asr.voiceListEdit.getDTMF.dtmfCount < 0)
									{
										asr.voiceListEdit.getDTMF.dtmfCount = 0;// 0代表接收任意多个字符
									}
									asr.voiceListEdit.getDTMF.timeoutSecond = Integer
											.parseInt(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"TimeoutSecond"));
									if (asr.voiceListEdit.getDTMF.timeoutSecond <= 0)
									{
										asr.voiceListEdit.getDTMF.timeoutSecond = 15;
									}
									asr.voiceListEdit.getDTMF.timeoutSecondVar = service
											.lookupKeyVar(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"TimeoutSecondVar"));
									asr.voiceListEdit.getDTMF.betweenTimeout = Integer
											.parseInt(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"BetweenTimeout"));
									if (asr.voiceListEdit.getDTMF.betweenTimeout <= 0)
									{
										asr.voiceListEdit.getDTMF.betweenTimeout = 15;
									}
									asr.voiceListEdit.getDTMF.betweenTimeoutVar = service
											.lookupKeyVar(XMLFunction
													.returnValueFromAttr(
															childNodeVoiceListEdit,
															"BetweenTimeoutVar"));
									asr.voiceListEdit.getDTMF.ioProcessName = Constant.strGetDTMF;
								}
								childNodeVoiceListEdit = XMLFunction
										.getNextSibling(childNodeVoiceListEdit);
							}
							// VoiceEdit,包含的子过程PlayFile和GetDTMF参数的重新解释
							// 在PlayVoice中指定的循环次数即是VoiceEdit的循环次数，PlayVoice自己没有循环
							// 循环等待时长即为GetDTMF的超时时长
							asr.voiceListEdit.doCount = asr.voiceListEdit.playTTS.playCount;
							asr.voiceListEdit.playTTS.playCount = 1;// 设置PlayVoice的循环次数为1
						}
						else if (Constant.strGetASR.equals(strChildNodeName))
						{
							asr.getASR.ioProcessName = Constant.strGetASR;

							asr.getASR.grammarStringVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"GrammarStringVar"));
							asr.getASR.grammarFileVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"GrammarFileVar"));

							asr.getASR.noSpeechTimeout = XMLFunction
									.getIntFromAttrib(childNode,
											"NoSpeechTimeout");
							asr.getASR.noSpeechTimeoutVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"NoSpeechTimeoutVar"));

							asr.getASR.betweenWordTimeout = XMLFunction
									.getIntFromAttrib(childNode,
											"BetweenWordTimeout");
							asr.getASR.betweenWordTimeoutVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"BetweenWordTimeoutVar"));

							asr.getASR.acceptScore = XMLFunction
									.getIntFromAttrib(childNode, "AcceptScore");
							asr.getASR.acceptScoreVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"AcceptScoreVar"));

							asr.getASR.resultMaxNum = XMLFunction
									.getIntFromAttrib(childNode, "ResultMaxNum");
							asr.getASR.resultMaxNumVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"ResultMaxNumVar"));

							asr.getASR.resultVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"ResultVar"));

						}
						else
						{
							;
						}

						childNode = XMLFunction.getNextSibling(childNode);

					}// while
					ioProcesses.add(asr);

				}
				else if (Constant.strPlayVoiceByTTS.equals(strNodeName))
				{
					PlayVoiceByTTS tts = new PlayVoiceByTTS(service);

					tts.ioProcessName = Constant.strPlayVoiceByTTS;
					ioProcesses.add(tts);

					tts.txtString = XMLFunction.returnValueFromAttr(ioNode,
							"TxtString");
					tts.txtStringVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "TxtStringVar"));

					tts.fileName = XMLFunction.returnValueFromAttr(ioNode,
							"FileName");
					tts.fileNameVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "FileNameVar"));

					tts.languageTypeVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "LanguageTypeVar"));

					String strFalse = XMLFunction.returnValueFromAttr(ioNode,
							"CanBreak");
					if ("false".equals(strFalse))
					{
						tts.canBreak = false;
					}
					else
					{
						tts.canBreak = true;
					}

					tts.breakList = XMLFunction.returnValueFromAttr(ioNode,
							"BreakList");
					strFalse = XMLFunction.returnValueFromAttr(ioNode,
							"UseConvertAudioFile");
					if ("false".equals(strFalse))
					{
						tts.useConvertAudioFile = false;
					}
					else
					{
						tts.useConvertAudioFile = true;
					}

					tts.playCount = XMLFunction.getIntFromAttrib(ioNode,
							"PlayCount");
					if (tts.playCount <= 0)
					{
						tts.playCount = 1;
					}
					tts.waitTimeOnce = XMLFunction.getIntFromAttrib(ioNode,
							"WaitTimeOnce");
					// ///////////////////////////////////////////////////////////////////////
				}
				else if (Constant.strPlayTTS.equals(strNodeName))
				{
					PlayTTS playtts = new PlayTTS(service);
					if ("false".equals(XMLFunction.returnValueFromAttr(ioNode,
							"CanBreak")))
					{
						playtts.canBreak = false;
					}
					else
					{
						playtts.canBreak = true;
					}
					playtts.breakList = XMLFunction.returnValueFromAttr(ioNode,
							"BreakList");
					if ("true".equals(XMLFunction.returnValueFromAttr(ioNode,
							"ThirdParty")))
					{
						playtts.thirdParty = true;
					}
					else
					{
						playtts.thirdParty = false;
					}

					playtts.languageTypeVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "LanguageTypeVar"));
					playtts.rate = XMLFunction.getIntFromAttrib(ioNode, "Rate");
					if ((playtts.rate != 6) && (playtts.rate != 8))
					{
						playtts.rate = 6;
					}
					playtts.playCount = XMLFunction.getIntFromAttrib(ioNode,
							"PlayCount");
					if (playtts.playCount == 0)
					{
						playtts.playCount = 1;
					}
					playtts.waitTimeOnce = XMLFunction.getIntFromAttrib(ioNode,
							"WaitTimeOnce");
					if (playtts.waitTimeOnce == 0)
					{
						playtts.waitTimeOnce = 5;
					}

					Node childNode = null;
					childNode = XMLFunction.getFirstChild(ioNode);
					while (null != childNode)
					{
						TTSSegmentImpl segment = new TTSSegmentImpl();
						String strChildNodeName;
						strChildNodeName = XMLFunction.getNodeName(childNode);

						if ("VoiceFileName".equals(strChildNodeName))
						{
							segment.isVar = false;
							segment.fileName = XMLFunction
									.getNodeText(childNode);

						}
						else if ("VoiceFileNameVar".equals(strChildNodeName))
						{
							segment.isVar = false;
							szArrib = XMLFunction.getNodeText(childNode);
							segment.fileNameVar = service.lookupKeyVar(szArrib);
							if (segment.fileNameVar == null)
							{
								throw new MyException(String.format(
										"节点%s中TTS文件变量%s等于NULL", nodeName,
										szArrib));
							}

						}
						else if ("VarName".equals(strChildNodeName))
						{
							segment.isVar = true;
							szArrib = XMLFunction.getNodeText(childNode);

							segment.ttsOutVar = service.lookupKeyVar(szArrib);
							if (segment.ttsOutVar == null)
							{
								throw new MyException(String.format(
										"节点%s中TTS文件变量%s等于NULL", nodeName,
										szArrib));
							}

						}
						else
						{
							;
						}
						playtts.ttsSegmentGroup.add(segment);

						childNode = XMLFunction.getNextSibling(childNode);

					}

					playtts.ioProcessName = Constant.strPlayTTS;
					ioProcesses.add(playtts);
				}
				else if (Constant.strVoiceListEdit.equals(strNodeName))
				{
					VoiceListEdit voiceListEdit = new VoiceListEdit(service);
					voiceListEdit.ioProcessName = Constant.strVoiceListEdit;
					String str = XMLFunction.returnValueFromAttr(ioNode,
							"PreGetCash");
					if ("true".equals(str))
					{
						voiceListEdit.isPreGetCash = true;
					}
					else
					{
						voiceListEdit.isPreGetCash = false;
					}

					Node childNode = null;
					childNode = XMLFunction.getFirstChild(ioNode);
					while (childNode != null)
					{
						String strChildNodeName = XMLFunction
								.getNodeName(childNode);
						if (Constant.strPlayTTS.equals(strChildNodeName))
						{
							if ("false".equals(XMLFunction.returnValueFromAttr(
									childNode, "CanBreak")))
							{
								voiceListEdit.playTTS.canBreak = false;
							}
							else
							{
								voiceListEdit.playTTS.canBreak = true;
							}
							voiceListEdit.playTTS.breakList = XMLFunction
									.returnValueFromAttr(childNode, "BreakList");
							if ("true".equals(XMLFunction.returnValueFromAttr(
									childNode, "ThirdParty")))
							{
								voiceListEdit.playTTS.thirdParty = true;
							}
							else
							{
								voiceListEdit.playTTS.thirdParty = false;
							}

							voiceListEdit.playTTS.languageTypeVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"LanguageTypeVar"));

							voiceListEdit.playTTS.rate = XMLFunction
									.getIntFromAttrib(childNode, "Rate");
							if ((voiceListEdit.playTTS.rate != 6)
									&& (voiceListEdit.playTTS.rate != 8))
							{
								voiceListEdit.playTTS.rate = 6;
							}
							voiceListEdit.playTTS.playCount = Integer
									.parseInt(XMLFunction.returnValueFromAttr(
											childNode, "PlayCount"));
							if (voiceListEdit.playTTS.playCount == 0)
							{
								voiceListEdit.playTTS.playCount = 1;
							}
							voiceListEdit.playTTS.waitTimeOnce = Integer
									.parseInt(XMLFunction.returnValueFromAttr(
											childNode, "WaitTimeOnce"));
							if (voiceListEdit.playTTS.waitTimeOnce == 0)
							{
								voiceListEdit.playTTS.waitTimeOnce = 5;
							}

							Node childNode1 = null;
							childNode1 = XMLFunction.getFirstChild(childNode);
							while (childNode1 != null)
							{
								TTSSegmentImpl segment = new TTSSegmentImpl();
								String strChildNodeName1 = XMLFunction
										.getNodeName(childNode1);

								if ("VoiceFileName".equals(strChildNodeName1))
								{
									segment.isVar = false;
									segment.fileName = XMLFunction
											.getNodeText(childNode1);

								}
								else if ("VoiceFileNameVar"
										.equals(strChildNodeName1))
								{
									segment.isVar = false;
									szArrib = XMLFunction
											.getNodeText(childNode1);
									segment.fileNameVar = service
											.lookupKeyVar(szArrib);
									if (segment.fileNameVar == null)
									{
										throw new MyException(String.format(
												"节点%s中TTS文件变量%s等于NULL",
												nodeName, szArrib));
									}

								}
								else if ("VarName".equals(strChildNodeName1))
								{
									segment.isVar = true;
									szArrib = XMLFunction
											.getNodeText(childNode1);

									segment.ttsOutVar = service
											.lookupKeyVar(szArrib);
									if (segment.ttsOutVar == null)
									{
										throw new MyException(String.format(
												"节点%s中TTS文件变量%s等于NULL",
												nodeName, szArrib));
									}

								}
								voiceListEdit.playTTS.ttsSegmentGroup
										.add(segment);
								childNode1 = XMLFunction
										.getNextSibling(childNode1);
							}

							voiceListEdit.playTTS.ioProcessName = Constant.strPlayTTS;
						}
						else if (Constant.strGetDTMF.equals(strChildNodeName))
						{
							voiceListEdit.getDTMF.dtmfVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"DTMFVar"));
							if (voiceListEdit.getDTMF.dtmfVar == null)
							{
								throw new MyException(String.format(
										"节点%s中DTMFVar变量%s等于NULL", nodeName,
										voiceListEdit.getDTMF.dtmfVar));
							}
							voiceListEdit.getDTMF.endCharVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"EndCharVar"));

							str = XMLFunction.returnValueFromAttr(childNode,
									"IsClearDTMFBuffer");
							if ("true".equals(str))
							{
								voiceListEdit.getDTMF.isClearBuffer = true;
							}
							else
							{
								voiceListEdit.getDTMF.isClearBuffer = false;
							}
							str = XMLFunction.returnValueFromAttr(childNode,
									"ReturnEndChar");
							if ("true".equals(str))
							{
								voiceListEdit.getDTMF.returnEndChar = true;
							}
							else
							{
								voiceListEdit.getDTMF.returnEndChar = false;
							}

							szArrib = XMLFunction.returnValueFromAttr(
									childNode, "EndFlag");
							voiceListEdit.getDTMF.endFlag = szArrib;
							voiceListEdit.getDTMF.dtmfCount = Integer
									.parseInt(XMLFunction.returnValueFromAttr(
											childNode, "Count"));
							if (voiceListEdit.getDTMF.dtmfCount < 0)
							{
								voiceListEdit.getDTMF.dtmfCount = 0;// 0代表接收任意多个字符
							}
							voiceListEdit.getDTMF.timeoutSecond = Integer
									.parseInt(XMLFunction.returnValueFromAttr(
											childNode, "TimeoutSecond"));
							if (voiceListEdit.getDTMF.timeoutSecond <= 0)
							{
								voiceListEdit.getDTMF.timeoutSecond = 15;
							}
							voiceListEdit.getDTMF.timeoutSecondVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"TimeoutSecondVar"));
							voiceListEdit.getDTMF.betweenTimeout = Integer
									.parseInt(XMLFunction.returnValueFromAttr(
											childNode, "BetweenTimeout"));
							if (voiceListEdit.getDTMF.betweenTimeout <= 0)
							{
								voiceListEdit.getDTMF.betweenTimeout = 15;
							}
							voiceListEdit.getDTMF.betweenTimeoutVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"BetweenTimeoutVar"));
							voiceListEdit.getDTMF.ioProcessName = Constant.strGetDTMF;
						}
						childNode = XMLFunction.getNextSibling(childNode);
					}
					// VoiceEdit,包含的子过程PlayFile和GetDTMF参数的重新解释
					// 在PlayVoice中指定的循环次数即是VoiceEdit的循环次数，PlayVoice自己没有循环
					// 循环等待时长即为GetDTMF的超时时长
					voiceListEdit.doCount = voiceListEdit.playTTS.playCount;
					voiceListEdit.playTTS.playCount = 1;// 设置PlayVoice的循环次数为1
					ioProcesses.add(voiceListEdit);
				}
				else if (Constant.strCallRecordRing.equals(strNodeName))
				{
					CallRecordRing callRecord = new CallRecordRing(service);
					Node childNode = null;
					childNode = XMLFunction.getFirstChild(ioNode);
					while (null != childNode)
					{
						String strChildNodeName;
						strChildNodeName = XMLFunction.getNodeName(childNode);
						if(Constant.strMakeCall.equals(strChildNodeName))
						{
							callRecord.makeCall.destNumberVar = service.lookupKeyVar(XMLFunction
									.returnValueFromAttr(childNode, "DestVar"));
							if (callRecord.makeCall.destNumberVar == null)
							{
								throw new MyException(String.format(
										"节点%s中目标号码变量%s等于NULL", nodeName,
										callRecord.makeCall.destNumberVar));
							}
							if ("true".equals(XMLFunction.returnValueFromAttr(childNode,
									"IsForFax")))
							{
								callRecord.makeCall.isForFax = true;
							}
							else
							{
								callRecord.makeCall.isForFax = false;
							}

							if ("true".equals(XMLFunction.returnValueFromAttr(childNode,
									"IsVideoCall")))
							{
								callRecord.makeCall.isVideoCall = true;
							}
							else
							{
								callRecord.makeCall.isVideoCall = false;
							}

							callRecord.makeCall.timeOutSecond = XMLFunction.getIntFromAttrib(
									childNode, "TimeoutSecond");
							if (callRecord.makeCall.timeOutSecond == 0)
							{
								callRecord.makeCall.timeOutSecond = 30;
							}
							callRecord.makeCall.timeOutSecondVar = service
									.lookupKeyVar(XMLFunction.returnValueFromAttr(
											childNode, "TimeoutSecondVar"));

						}
						else if (Constant.strRecordVoice
								.equals(strChildNodeName))
						{
							callRecord.recordVoice.timeDuration = XMLFunction
									.getIntFromAttrib(childNode, "TimeDuration");

							szArrib = XMLFunction.returnValueFromAttr(childNode,
									"FileNameVar");

							callRecord.recordVoice.fileNameVar = service
									.lookupKeyVar(szArrib);
							if (callRecord.recordVoice.fileNameVar == null)
							{
								throw new MyException(String.format(
										"节点%s中录音文件变量%s等于NULL", this.nodeName,
										callRecord.recordVoice.fileNameVar));
							}

							szArrib = XMLFunction.returnValueFromAttr(childNode,
									"EndFlag");

							if (szArrib.length() != 0)
							{
								callRecord.recordVoice.endFlag = szArrib.charAt(0);
							}
							else
							{
								callRecord.recordVoice.endFlag = 0;
							}
							callRecord.recordVoice.timeDuration = XMLFunction
									.getIntFromAttrib(childNode, "TimeDuration");

							callRecord.recordVoice.rate = XMLFunction.getIntFromAttrib(
									childNode, "Rate");
							if ((callRecord.recordVoice.rate != 6)
									&& (callRecord.recordVoice.rate != 8))
							{
								callRecord.recordVoice.rate = 6;
							}

							callRecord.recordVoice.timeDurationVar = service
									.lookupKeyVar(XMLFunction
											.returnValueFromAttr(childNode,
													"TimeDurationVar"));
						}
						childNode = XMLFunction.getNextSibling(childNode);
					}
					callRecord.ioProcessName = Constant.strCallRecordRing;
					ioProcesses.add(callRecord);
				}
				else if (Constant.strMakeCall.equals(strNodeName))
				{
					MakeCall makeCall = new MakeCall(service);
					makeCall.destNumberVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "DestVar"));
					if (makeCall.destNumberVar == null)
					{
						throw new MyException(String.format(
								"节点%s中目标号码变量%s等于NULL", nodeName,
								makeCall.destNumberVar));
					}
					if ("true".equals(XMLFunction.returnValueFromAttr(ioNode,
							"IsForFax")))
					{
						makeCall.isForFax = true;
					}
					else
					{
						makeCall.isForFax = false;
					}

					if ("true".equals(XMLFunction.returnValueFromAttr(ioNode,
							"IsVideoCall")))
					{
						makeCall.isVideoCall = true;
					}
					else
					{
						makeCall.isVideoCall = false;
					}

					makeCall.timeOutSecond = XMLFunction.getIntFromAttrib(
							ioNode, "TimeoutSecond");
					if (makeCall.timeOutSecond == 0)
					{
						makeCall.timeOutSecond = 30;
					}
					makeCall.timeOutSecondVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "TimeoutSecondVar"));

					makeCall.ioProcessName = Constant.strMakeCall;
					ioProcesses.add(makeCall);
				}
				else if (Constant.strAnswerCall.equals(strNodeName))
				{
					AnswerCall answerCall = new AnswerCall(service);
					answerCall.ioProcessName = Constant.strAnswerCall;
					ioProcesses.add(answerCall);
				}
				else if (Constant.strDisconnectCall.equals(strNodeName))
				{
					DisconnetCall disconnectCall = new DisconnetCall(service);
					disconnectCall.ioProcessName = Constant.strDisconnectCall;
					ioProcesses.add(disconnectCall);
				}
				else if (Constant.strTransferCall.equals(strNodeName))
				{
					TransferCall transferCall = new TransferCall(service);
					transferCall.destNumberVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "DestVar"));
					if (transferCall.destNumberVar == null)
					{
						throw new MyException(String.format(
								"节点%s中目标号码变量%s等于NULL", nodeName,
								transferCall.destNumberVar));
					}

					transferCall.ioProcessName = Constant.strTransferCall;
					ioProcesses.add(transferCall);
				}
				else if (Constant.strGetSmsMsg.equals(strNodeName))
				{
					GetSmsMsg getSmsMsg = new GetSmsMsg(service);

					getSmsMsg.isUssdMsg = XMLFunction.getBoolFromAttrib(ioNode,
							"IsUssdMsg", false);
					getSmsMsg.sPVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "SPVar"));
					getSmsMsg.phoneNumberVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "PhoneNumberVar"));
					getSmsMsg.serviceTypeVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "ServiceType"));
					getSmsMsg.msgVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgVar"));
					getSmsMsg.tP_pid = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "TP_pid"));
					getSmsMsg.tP_udhi = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "TP_udhi"));
					getSmsMsg.linkID = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "LinkID"));
					getSmsMsg.timeout = XMLFunction.getIntFromAttrib(ioNode,
							"Timeout");

					getSmsMsg.ioProcessName = Constant.strGetSmsMsg;

					ioProcesses.add(getSmsMsg);
				}
				else if (Constant.strSendSmsMsg.equals(strNodeName))
				{
					SendSmsMsg sendSmsMsg = new SendSmsMsg(service);

					sendSmsMsg.m_bIsUssdMsg = XMLFunction.getBoolFromAttrib(
							ioNode, "IsUssdMsg", false);
					sendSmsMsg.m_nParamLevel = XMLFunction.getIntFromAttrib(
							ioNode, "ParamLevel");
					sendSmsMsg.m_pMsgID = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgID"));
					sendSmsMsg.m_pMsgTotal = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgTotal"));
					sendSmsMsg.m_pMsgNumber = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgNumber"));

					sendSmsMsg.m_pPhoneNumberVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "PhoneNumberVar"));
					sendSmsMsg.m_pSPVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "SPVar"));
					sendSmsMsg.m_pChargeNumberVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "ChargeNumber"));
					sendSmsMsg.m_pCorpId = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "CorpId"));
					sendSmsMsg.m_pServiceTypeVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "ServiceType"));

					sendSmsMsg.m_pFeeTypeVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "FeeType"));
					sendSmsMsg.m_pFeeValueVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "FeeValue"));
					sendSmsMsg.m_pGivenValueVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "GivenValue"));
					sendSmsMsg.m_pAgentFlag = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "AgentFlag"));
					sendSmsMsg.m_pMorelatetoMTFlag = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "MorelatetoMTFlag"));

					sendSmsMsg.m_pPriorityVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "Priority"));
					sendSmsMsg.m_pExpireTimeVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "ExpireTime"));
					sendSmsMsg.m_pScheduleTimeVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "ScheduleTime"));

					sendSmsMsg.m_pReportFlag = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "ReportFlag"));
					sendSmsMsg.m_pTP_pid = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "TP_pid"));
					sendSmsMsg.m_pTP_udhi = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "TP_udhi"));
					sendSmsMsg.m_pMessageCoding = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "MessageCoding"));
					sendSmsMsg.m_pMessageType = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "MessageType"));
					sendSmsMsg.m_pLinkID = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "LinkID"));
					sendSmsMsg.m_pMsgVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgVar"));
					sendSmsMsg.m_pMsgFileVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgFileVar"));

					sendSmsMsg.ioProcessName = Constant.strSendSmsMsg;

					ioProcesses.add(sendSmsMsg);
				}
				else if (Constant.strGetSmsReport.equals(strNodeName))
				{
					GetSmsReport getSmsReport = new GetSmsReport(service);

					getSmsReport.m_bIsUssdMsg = XMLFunction.getBoolFromAttrib(
							ioNode, "IsUssdMsg", false);
					getSmsReport.m_pPhoneNumberVar = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "PhoneNumberVar"));
					getSmsReport.m_pMsgIdVar = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "MsgID"));
					getSmsReport.m_pState = service.lookupKeyVar(XMLFunction
							.returnValueFromAttr(ioNode, "State"));
					getSmsReport.m_pErrorCode = service
							.lookupKeyVar(XMLFunction.returnValueFromAttr(
									ioNode, "ErrorCode"));
					getSmsReport.m_nTimeout = XMLFunction.getIntFromAttrib(
							ioNode, "Timeout");
					getSmsReport.ioProcessName = Constant.strSendSmsMsg;

					ioProcesses.add(getSmsReport);
				}

			}

			ioNode = XMLFunction.getNextSibling(ioNode);

		}

		return null;
	}

	@Override
	ServiceNode executeNode(USMLReasoningResult error)
	{
		try
		{
			service.usmlReasoningEnv.initIdlePrompt(service, workflow);
			// 2007-4-29 liyan for idlePromp

			error.value = Constant.EVENT_No_Error;
			for (int i = 0; i < ioProcesses.size(); i++)
			{
				error.value = ioProcesses.elementAt(i).executeIOProcess(
						service.browserSite);

				if (error.value != Constant.EVENT_No_Error)
				{
					break;// 结束当前节点;出现错误以后IO节点不再继续执行
				}
			}

			resultVar.setUSMLEventValue(error.value);
			if (lastErrorVar != null)
			{
				lastErrorVar.setValue(error.value);
			}

			service.setEventVar(error.value);

			if (error.value == Constant.EVENT_No_Error)
			{
				return nextSrvNode;
			}
			else if ((error.value & 0xffff0000) == Constant.EVENT_UserEvent)
			{
				String msg;
				msg = String.format("文档执行被用户事件终止，节点名称=%s", nodeName);
				service.browserSite.onMessage(msg);
				return null;
			}
			if (error.value == Constant.EVENT_To_PrevNode)
			{
				return prevSrvNode;
			}
			else if (error.value == Constant.EVENT_To_RootNode)
			{
				return null;
				// }else if( (nError == EVENT_CustomHangup)||(nError ==
				// EVENT_ToAgent))
				// {//这两种事件不能够本地处理
				// return NULL;
			}
			else
			// 这两个事件改为本地处理
			{
				String temp;
				String errString = Constant.transEventToString(error.value,
						false);
				if ("".equals(errString))
				{
					errString = String.format("%d", error.value);
				}
				temp = String.format("执行IO节点返回%s,m_bProcessError=%s",
						errString, isProcessError);
				service.browserSite.onMessage(temp);
				if (isProcessError == true)
				{
					return nextSrvNode;
				}
				else
				{
					return null;
				}
			}
		}
		catch (Exception e)
		{
			service.browserSite.onRunError("NodeName=" + nodeName + "执行IO节点异常");
			Log.wException(LogDef.id_0x10130000, e);
			return null;
		}
	}

	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("SrvIONode ");
		buff.append(varName);
		buff.append(" = new SrvIONode(this, wf);\n");

		buff.append(varName);
		buff.append(".setNodeName(");
		translateString2SourceCode2(buff, nodeName);
		buff.append(");\n");

		buff.append(varName);
		buff.append(".isProcessError = ");
		buff.append(isProcessError);
		buff.append(";\n");

		buff.append(varName);
		buff.append(".lastErrorVar = ");
		translateVar2SourceCode(buff, lastErrorVar);

		buff.append(varName);
		buff.append(".resultVar = ");
		translateVar2SourceCode(buff, resultVar);

		for (int i = 0; i < inVars.size(); i++)
		{
			KnowledgeVariable var = inVars.elementAt(i);

			buff.append(varName);
			buff.append(".inVars.add(");
			buff.append(var.codeName);
			buff.append(");\n");
		}

		for (int i = 0; i < outVars.size(); i++)
		{
			KnowledgeVariable var = outVars.elementAt(i);

			buff.append(varName);
			buff.append(".outVars.add(");
			buff.append(var.codeName);
			buff.append(");\n");
		}

		for (int i = 0; i < ioProcesses.size(); i++)
		{
			IOProcess process = ioProcesses.elementAt(i);

			process.translate2SourceCode(buff);

			buff.append(process.getVarName());
			buff.append(".ioProcessName = ");
			ServiceNode.translateString2SourceCode(buff, process.ioProcessName);

			buff.append(varName);
			buff.append(".ioProcesses.add(");
			buff.append(process.getVarName());
			buff.append(");\n");
		}
	}
}
