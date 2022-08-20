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

		public String lastClassName; // ���һ�α���ɹ�������

		public long lastMillis; // ���һ�α���ɹ���USML�ļ����޸�����

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
	 * ������־�еı������USML��ʱ�ĸ��������������Ƿ���Ҫ���±��롣 �����־��¼�����ڻ�����־�е����������е�USML�ļ������ϣ������±��롣
	 * ���JAVAԴ�ļ������ڣ���϶���Ҫ���±��롣 ����ʧ�ܺ�ֻҪUSML�ļ����ٸ��£��������±��룬����ֱ�ӱ���ʧ�ܡ�
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
			throw new FileNotFoundException("�ļ�[" + fileName + "]������");
		}

		loadCompileInfo();

		FileCompileInfo fci = fileCompileInfos.get(fileName);

		Service.parsePath(fileName, packageNameWrapper, classNameWrapper);
		String packageName = packageNameWrapper.value;

		if (fci != null)
		{
			// ֻ����־���м�¼�ģ����п��ܲ���Ҫ���±���
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
				System.out.println("�ļ�[" + fileName + "]����["
						+ classNameWrapper.value + "]����ʧ�ܣ��˳���������");
				return false;
			}

			if (updatedWrapper.value != 0)
			{
				System.out.println("�����ĵ�[" + fileName + "]�ɹ�");
			}

			HashMap<String, Service> importSrv = service.getImportServices();
			Iterator<Entry<String, Service>> it = importSrv.entrySet()
					.iterator();
			for (int i = 0; it.hasNext(); i++)
			{
				Entry<String, Service> entry = it.next();
				String svcFileName = entry.getKey();

				System.out.println("\n��ʼ�����ĵ�[" + svcFileName + "]...");

				IntWrapper localUpdatedWrapper = new IntWrapper();
				localUpdatedWrapper.value = 0;

				try
				{
					if (!compile(env, svcFileName, packageNameWrapper,
							classNameWrapper, localUpdatedWrapper, null, true))
					{
						System.out.println("�����ĵ�[" + svcFileName + "]����["
								+ classNameWrapper.value + "ʧ�ܣ�������������");
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
					System.out.println("�ĵ�[" + svcFileName
							+ "]�Դ��ϴα����û�иı䣬���ٱ�����ĵ��������õ��ĵ�");
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
			throw new Exception("����Ŀ¼[" + newDir + "]ʧ��!");
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
	 * �Ӵ���װ����־�ļ���װ��֮ǰ��ϣ��Ϊnull��װ��֮������װ���Ƿ�ɹ��� ��ϣ������Ϊ�ա��Դ�Ϊ��־�ж��Ƿ��Ѿ�װ�ع��ˡ�
	 * ���װ��ʧ�ܣ�Ҳ������װ�ء�
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

		System.out.println("��ʼ�����ĵ�[" + usmlFile + "]...");

		boolean result = compile(env, usmlFile, packageNameWrapper,
				classNameWrapper, updatedWrapper, null, recursiveCompile, 0,
				encoding, null);
		if (result)
		{
			if (updatedWrapper.value == 0)
			{
				System.out.println("�ĵ�[" + usmlFile
						+ "]�Դ��ϴα����û�иı䣬���ٱ�����ĵ��������õ��ĵ�");
			}

			System.out.println("\n��������ȫ�����");
		}
		else
		{
			System.out.println("\n�����ĵ�[" + usmlFile + "]ʧ��");
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
