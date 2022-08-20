package com.channelsoft.slee.usmlreasoning.precompile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.channelsoft.reusable.util.IntWrapper;
import com.channelsoft.reusable.util.StringWrapper;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.usmlreasoning.BaseBrowserMock;
import com.channelsoft.slee.usmlreasoning.Service;
import com.channelsoft.slee.usmlreasoning.USMLReasoningEnv;

/**
 * @author wuxz
 */
public class PreCompiler
{
	public static class FileCompileInfo implements Serializable
	{
		private static final long serialVersionUID = 4100192616201149182L;

		public String className;

		public String lastClassName; // 最近一次编译成功的类名

		public long lastMillis; // 最近一次编译成功的USML文件的修改日期

		public long millis;

		public int ordinal = 0;

		public boolean succeeded = false;

		public FileCompileInfo()
		{

		}

		public FileCompileInfo(long millis, boolean succeeded, int ordinal,
				String className)
		{
			this.millis = millis;
			this.succeeded = succeeded;
			this.ordinal = ordinal;
			this.className = className;
		}
	}

	static HashMap<String, FileCompileInfo> fileCompileInfos;

	static long logFileMillis;

	static File logFl = new File(UnifiedServiceManagement.configData
			.getGeneratedPath()
			+ "/compiled.log");

	static boolean useStdOut = false;

	/**
	 * 根据日志中的编译过的USML当时的更新日期来决定是否需要重新编译。 如果日志记录不存在或者日志中的日期与现有的USML文件不符合，都重新编译。
	 * 如果JAVA源文件不存在，则肯定需要重新编译。 编译失败后，只要USML文件不再更新，则不再重新编译，而是直接报告失败。
	 * 
	 * @param env
	 * @param fileName
	 * @param packageNameWrapper
	 * @param classNameWrapper
	 * @param updatedWrapper
	 * @return
	 * @throws Exception
	 */
	synchronized public static boolean compile(USMLReasoningEnv env,
			String fileName, StringWrapper packageNameWrapper,
			StringWrapper classNameWrapper, IntWrapper updatedWrapper,
			Date myModifiedDate, boolean recursiveCompile)
			throws FileNotFoundException, Exception
	{
		return compile(env, fileName, packageNameWrapper, classNameWrapper,
				updatedWrapper, myModifiedDate, recursiveCompile, -1, null,
				null);
	}

	synchronized public static boolean compile(USMLReasoningEnv env,
			String fileName, StringWrapper packageNameWrapper,
			StringWrapper classNameWrapper, IntWrapper updatedWrapper,
			Date myModifiedDate, boolean recursiveCompile, int newOrdinal,
			String encoding, String usmlPath) throws FileNotFoundException,
			Exception
	{
		fileName = fileName.replace('\\', '/');

		File usmlFl = new File(fileName);
		if (!usmlFl.exists())
		{
			throw new FileNotFoundException("文件[" + fileName + "]不存在");
		}

		loadCompileInfo();

		FileCompileInfo fci = fileCompileInfos.get(fileName);

		Service.parsePath(fileName, packageNameWrapper, classNameWrapper);
		String packageName = packageNameWrapper.value;

		if (fci != null)
		{
			// 只有日志中有记录的，才有可能不需要重新编译
			if ((usmlFl.lastModified() / 1000 == fci.millis / 1000)
					&& (newOrdinal == -1))
			{
				if (fci.succeeded)
				{
					if (myModifiedDate != null)
					{
						myModifiedDate.setTime(fci.millis);
					}

					classNameWrapper.value = fci.className;
				}
				else if (fci.lastClassName != null)
				{
					if (myModifiedDate != null)
					{
						myModifiedDate.setTime(fci.lastMillis);
					}

					classNameWrapper.value = fci.lastClassName;
				}
				else
				{
					classNameWrapper.value = null;
				}

				return fci.succeeded;
			}
			else
			{
				if (newOrdinal == -1)
				{
					fci.ordinal++;
				}
				else
				{
					fci.ordinal = newOrdinal;
				}

				fci.className = classNameWrapper.value + "_" + fci.ordinal;
				fci.millis = usmlFl.lastModified() / 1000 * 1000;
				fci.succeeded = false;
				if (myModifiedDate != null)
				{
					myModifiedDate.setTime(fci.millis);
				}
			}
		}
		else
		{
			if (newOrdinal == -1)
			{
				newOrdinal = 0;
			}

			fci = insertFileCompileInfo(fileName,
					usmlFl.lastModified() / 1000 * 1000, false, newOrdinal,
					classNameWrapper.value + "_" + newOrdinal);
			if (myModifiedDate != null)
			{
				myModifiedDate.setTime(fci.millis);
			}
		}

		saveCompileInfo();

		classNameWrapper.value = fci.className;

		String javaFilePath = getFileDir(packageName) + "/"
				+ classNameWrapper.value + ".java";
		String logPath = getFileDir(packageName) + "/" + classNameWrapper.value
				+ ".log";

		Service service = new Service(env, env.browser);
		service.setUsmlFile(fileName);
		if (usmlPath != null)
		{
			service.setRemoteUsmlPath(usmlPath);
		}
		StringBuilder buff = new StringBuilder();
		try
		{
			service
					.translate2SourceCode(fileName, classNameWrapper.value,
							buff);
		}
		catch (IOException e)
		{
			Thread.sleep(1000);
			fci.millis = 0;
			throw e;
		}

		createPackageDirs(service.packageName);
		FileWriter writer = new FileWriter(javaFilePath);
		writer.write(buff.toString());
		writer.close();

		String compilerArgs[] = null;
		if (encoding == null)
		{
			compilerArgs = new String[5];
			compilerArgs[0] = "-sourcepath";
			compilerArgs[1] = UnifiedServiceManagement.configData
					.getGeneratedPath();
			compilerArgs[2] = "-encoding";
			compilerArgs[3] = "GBK";
			compilerArgs[4] = javaFilePath;
		}
		else
		{
			compilerArgs = new String[5];
			compilerArgs[0] = "-sourcepath";
			compilerArgs[1] = UnifiedServiceManagement.configData
					.getGeneratedPath();
			compilerArgs[2] = "-encoding";
			compilerArgs[3] = encoding;
			compilerArgs[4] = javaFilePath;
		}

		PrintWriter pw = null;
		if (useStdOut)
		{
			pw = new PrintWriter(System.out);
		}
		else
		{
			pw = new PrintWriter(logPath);
		}

		try
		{
			int status = com.sun.tools.javac.Main.compile(compilerArgs, pw);
			fci.succeeded = (status == 0);
			if (fci.succeeded)
			{
				fci.lastClassName = fci.className;
				fci.lastMillis = fci.millis;
			}
			else
			{
				classNameWrapper.value = null;
			}
		}
		finally
		{
			if (!useStdOut)
			{
				pw.close();
			}
		}

		saveCompileInfo();

		updatedWrapper.value = 1;

		if (recursiveCompile)
		{
			if (!fci.succeeded)
			{
				System.out.println("文件[" + fileName + "]，类["
						+ classNameWrapper.value + "]编译失败，退出批量编译");
				return false;
			}

			if (updatedWrapper.value != 0)
			{
				System.out.println("编译文档[" + fileName + "]成功");
			}

			HashMap<String, Service> importSrv = service.getImportServices();
			Iterator<Entry<String, Service>> it = importSrv.entrySet()
					.iterator();
			for (int i = 0; it.hasNext(); i++)
			{
				Entry<String, Service> entry = it.next();
				String svcFileName = entry.getKey();

				System.out.println("\n开始编译文档[" + svcFileName + "]...");

				IntWrapper localUpdatedWrapper = new IntWrapper();
				localUpdatedWrapper.value = 0;

				try
				{
					if (!compile(env, svcFileName, packageNameWrapper,
							classNameWrapper, localUpdatedWrapper, null, true))
					{
						System.out.println("编译文档[" + svcFileName + "]，类["
								+ classNameWrapper.value + "失败，结束编译任务");
						return false;
					}
				}
				catch (FileNotFoundException fnfe)
				{
					System.out.println(fnfe.getMessage());
					continue;
				}

				if (localUpdatedWrapper.value == 0)
				{
					System.out.println("文档[" + svcFileName
							+ "]自从上次编译后没有改变，不再编译此文档和它引用的文档");
				}
			}
		}

		return fci.succeeded;
	}

	static String createPackageDirs(String packageName) throws Exception
	{
		String newDir = getFileDir(packageName);

		File dir = new File(newDir);
		if (!dir.exists() && !dir.mkdirs())
		{
			throw new Exception("创建目录[" + newDir + "]失败!");
		}

		return newDir;
	}

	synchronized public static FileCompileInfo getCompileInfo(String fileName)
			throws Exception
	{
		loadCompileInfo();
		FileCompileInfo fci = fileCompileInfos.get(fileName);
		return fci;
	}

	static String getFileDir(String packageName)
	{
		String baseDir = ISysCfgData.queryRegData()
				+ (ISysCfgData.isNewPath() ? "/generated/"
						: "/SoftSwitch/generated/");
		String newDir = baseDir + packageName.replace('.', '/');

		return newDir;
	}

	public static FileCompileInfo insertFileCompileInfo(String fileName,
			long millis, boolean succeeded, int ordinal, String className)
	{
		FileCompileInfo fci = new FileCompileInfo(millis, succeeded, ordinal,
				className);
		fileCompileInfos.put(fileName, fci);

		return fci;
	}

	/**
	 * 从磁盘装载日志文件。装载之前哈希表为null，装载之后，无论装载是否成功， 哈希表都不会为空。以此为标志判断是否已经装载过了。
	 * 如果装载失败，也不会再装载。
	 */
	synchronized public static void loadCompileInfo()
	{
		if (logFl.exists() && (logFl.lastModified() == logFileMillis))
		{
			return;
		}

		try
		{
			FileInputStream fis = new FileInputStream(logFl);
			ObjectInputStream ois = new ObjectInputStream(fis);
			fileCompileInfos = (HashMap<String, FileCompileInfo>) (ois
					.readObject());
			fis.close();
			logFileMillis = logFl.lastModified();
		}
		catch (Exception e)
		{
			fileCompileInfos = new HashMap<String, FileCompileInfo>();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		new UnifiedServiceManagement();

		String helpInfo = "Usage:\tPreCompiler [-s] usmlFile\n";
		helpInfo += "\t-s:\t\tCompile imported service too\n";
		helpInfo += "\tusmlFile\tPath of USML document, relative to SLEE document directory\n";
		helpInfo += "\n\tfor example:\tPreCompiler -s index.usml";

		if (args.length < 1)
		{
			System.out.println(helpInfo);
			return;
		}

		boolean recursiveCompile = false;
		String encoding = System.getProperty("encoding", "GBK");

		String usmlFile = null;
		if (args.length == 1)
		{
			usmlFile = args[0];
		}
		else if (args.length == 2)
		{
			recursiveCompile = (args[0].equals("-s"));
			usmlFile = args[1];
		}
		else
		{
			System.out.println(helpInfo);
			return;
		}

		StringWrapper packageNameWrapper = new StringWrapper();
		StringWrapper classNameWrapper = new StringWrapper();
		UnifiedServiceManagement.configData.load(false);

		usmlFile = UnifiedServiceManagement.configData.getUsmlPath() + "/"
				+ usmlFile;

		USMLReasoningEnv env = USMLReasoningEnv.createAUSMLReasoningEnv(
				new BaseBrowserMock(), "3001");
		IntWrapper updatedWrapper = new IntWrapper();

		System.out.println("开始编译文档[" + usmlFile + "]...");

		boolean result = compile(env, usmlFile, packageNameWrapper,
				classNameWrapper, updatedWrapper, null, recursiveCompile, 0,
				encoding, null);
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

	synchronized public static void saveCompileInfo() throws Exception
	{
		String logFileName = UnifiedServiceManagement.configData
				.getGeneratedPath()
				+ "/compiled.log";

		FileOutputStream fos = new FileOutputStream(logFileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(fileCompileInfos);
		fos.close();
	}

}
