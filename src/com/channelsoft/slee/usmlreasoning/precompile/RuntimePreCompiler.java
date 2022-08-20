/**
 * 
 */
package com.channelsoft.slee.usmlreasoning.precompile;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.usmlreasoning.RuntimeBaseBrowserMock;
import com.channelsoft.slee.usmlreasoning.USMLReasoningEnv;

/**
 * @author wuxz
 */
public class RuntimePreCompiler
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		new UnifiedServiceManagement();

		String usmlFile = null;
		usmlFile = args[0];

		StringWrapper packageNameWrapper = new StringWrapper();
		StringWrapper classNameWrapper = new StringWrapper();
		UnifiedServiceManagement.configData.load(false);

		usmlFile =
				UnifiedServiceManagement.configData.getUsmlPath() + "/"
						+ usmlFile;

		USMLReasoningEnv env =
				USMLReasoningEnv.createAUSMLReasoningEnv(
						new RuntimeBaseBrowserMock(), "3001");
		IntWrapper updatedWrapper = new IntWrapper();

		System.out.println("开始编译文档[" + usmlFile + "]...");

		PreCompiler.useStdOut = true;
		boolean result =
				PreCompiler.compile(env, usmlFile, packageNameWrapper,
						classNameWrapper, updatedWrapper, null, false, 0,
						"GBK", null);
		if (result)
		{
			if (updatedWrapper.value == 0)
			{
				System.out.println("文档[" + usmlFile
						+ "]自从上次编译后没有改变，不再编译此文档和它引用的文档");
			}

			System.out.println("\n编译任务全部完成");
		}
		else
		{
			System.out.println("\n编译文档[" + usmlFile + "]失败");
		}
	}
}
