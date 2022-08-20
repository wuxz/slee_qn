package com.channelsoft.slee.usmlreasoning;

import com.channelsoft.slee.util.*;

public class GetASR extends IOProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2514247962531658225L;

	public KnowledgeVariable grammarStringVar;

	public KnowledgeVariable grammarFileVar;

	public KnowledgeVariable noSpeechTimeoutVar;

	public KnowledgeVariable betweenWordTimeoutVar;

	public KnowledgeVariable resultMaxNumVar;

	public KnowledgeVariable resultVar;

	public KnowledgeVariable acceptScoreVar;

	public int noSpeechTimeout;

	public int betweenWordTimeout;

	public int acceptScore;

	public int resultMaxNum;

	public GetASR(Service service)
	{
		super(service);
		noSpeechTimeout = 5;
		betweenWordTimeout = 3;
		acceptScore = 45;
		resultMaxNum = 3;
	}

	int doIOProcess(BaseBrowser browser)
	{
		return Constant.EVENT_No_Error;
	}
	
	public void translate2SourceCode(StringBuilder buff) throws Exception
	{
		buff.append("GetASR ");
		buff.append(getVarName());
		buff.append(" = new GetASR(this);\n");
		
		buff.append(getVarName());
		buff.append(".acceptScore = ");
		buff.append(acceptScore);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".acceptScoreVar = ");
		ServiceNode.translateVar2SourceCode(buff, acceptScoreVar);
		
		buff.append(getVarName());
		buff.append(".betweenWordTimeout = ");
		buff.append(betweenWordTimeout);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".betweenWordTimeoutVar = ");
		ServiceNode.translateVar2SourceCode(buff, betweenWordTimeoutVar);
		
		buff.append(getVarName());
		buff.append(".grammarFileVar = ");
		ServiceNode.translateVar2SourceCode(buff, grammarFileVar);
		
		buff.append(getVarName());
		buff.append(".grammarStringVar = ");
		ServiceNode.translateVar2SourceCode(buff, grammarStringVar);
		
		buff.append(getVarName());
		buff.append(".noSpeechTimeout = ");
		buff.append(noSpeechTimeout);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".noSpeechTimeoutVar = ");
		ServiceNode.translateVar2SourceCode(buff, noSpeechTimeoutVar);
		
		buff.append(getVarName());
		buff.append(".resultMaxNum = ");
		buff.append(resultMaxNum);
		buff.append(";\n");
		
		buff.append(getVarName());
		buff.append(".resultMaxNumVar = ");
		ServiceNode.translateVar2SourceCode(buff, resultMaxNumVar);
		
		buff.append(getVarName());
		buff.append(".resultVar = ");
		ServiceNode.translateVar2SourceCode(buff, resultVar);
	}
}
