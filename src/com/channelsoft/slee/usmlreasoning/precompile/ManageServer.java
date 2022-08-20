package com.channelsoft.slee.usmlreasoning.precompile;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import com.channelsoft.slee.log.Log;
import com.channelsoft.slee.log.LogDef;
import com.channelsoft.slee.unifiedservicemanagement.ISysCfgData;
import com.channelsoft.slee.unifiedservicemanagement.UnifiedServiceManagement;
import com.channelsoft.slee.usmlreasoning.precompile.PreCompiler.FileCompileInfo;

public class ManageServer extends Thread
{
	/**
	 * ���ر����ļ��ı�����Ϣ
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	static FileCompileInfo getCompileInfo(String fileName) throws Exception
	{
		fileName = ISysCfgData.queryRegData()
				+ (ISysCfgData.isNewPath() ? "/document/"
						: "/SoftSwtich/Document/") + fileName;
		FileCompileInfo fci = PreCompiler.getCompileInfo(fileName);
		return fci;
	}

	ObjectInputStream dis = null;

	ObjectOutputStream dos = null;

	void doGetCompileInfo(GetCompileInfoCmd gcic) throws Exception
	{
		FileCompileInfo fci = getCompileInfo(gcic.fileName);
		gcic.ordinal = (fci != null ? fci.ordinal : -1);
		gcic.millis = (fci != null ? fci.millis : 0);
		gcic.succeeded = ((fci != null) && fci.succeeded);
		gcic.encoding = Charset.defaultCharset().name();
		gcic.usmlPath = UnifiedServiceManagement.configData.getUsmlPath();

		dos.writeObject(gcic);
	}

	/**
	 * ���ϴ������ļ��ƶ���DocumentĿ¼��generatedĿ¼��ͬʱ����compiled.log��
	 * �´�ʹ�ô�USML�ļ�ʱ�����ɲ�����ֱ�������ϴ�����class�ļ�
	 * 
	 * @param ufc
	 * @throws Exception
	 */
	void doUpload(UploadFileCmd ufc) throws Exception
	{
		String fileDir = ISysCfgData.queryRegData()
				+ (ISysCfgData.isNewPath() ? "/upload/" : "/SoftSwitch/upload/");
		FileCompileInfo fci = getCompileInfo(ufc.fileName);
		if (fci == null)
		{
			// ����û�б�������ļ������һ���¼�¼����־�ļ���
			fci = PreCompiler.insertFileCompileInfo(ISysCfgData.queryRegData()
					+ (ISysCfgData.isNewPath() ? "/document/"
							: "/SoftSwitch/Document/") + ufc.fileName, 0,
					false, -1, null);
		}

		File usmlFile = new File(fileDir + ufc.fileName);
		File classFile = new File(fileDir + ufc.classFileName);
		File newUsmlFile = new File(ISysCfgData.queryRegData()
				+ (ISysCfgData.isNewPath() ? "/document/"
						: "/SoftSwitch/Document/") + ufc.fileName);
		File newClassFile = new File(ISysCfgData.queryRegData()
				+ (ISysCfgData.isNewPath() ? "/generated/"
						: "/SoftSwitch/generated/") + ufc.classFileName);

		if ((fci.ordinal > ufc.ordinal)
				|| ((fci.ordinal == ufc.ordinal) && fci.succeeded))
		{
			// ������صİ汾���ϴ������£�������ȫ��ͬ����˴��ϴ������ԣ�
			// ��Ϊ��ʱ���ص�classloader������ʹ������ϴ�����class�ļ���
			ufc.error = "�ϴ��İ汾�ȱ��صľ�";
			dos.writeObject(ufc);

			usmlFile.delete();
			classFile.delete();

			return;
		}

		File parentDir = newClassFile.getParentFile();
		if (!parentDir.exists() && !parentDir.mkdirs())
		{
			ufc.error = "�����ļ�ʧ�ܣ�����class�ļ�Ŀ¼ʧ��";
			dos.writeObject(ufc);

			usmlFile.delete();
			classFile.delete();

			return;
		}

		parentDir = newUsmlFile.getParentFile();
		if (!parentDir.exists() && !parentDir.mkdirs())
		{
			ufc.error = "�����ļ�ʧ�ܣ�����usml�ļ�Ŀ¼ʧ��";
			dos.writeObject(ufc);

			usmlFile.delete();
			classFile.delete();

			return;
		}

		if (newClassFile.exists() && !newClassFile.delete())
		{
			ufc.error = "�����ļ�ʧ�ܣ�ɾ����class�ļ�ʧ��";
			dos.writeObject(ufc);

			usmlFile.delete();
			classFile.delete();

			return;
		}

		if (!classFile.renameTo(newClassFile))
		{
			ufc.error = "�����ļ�ʧ�ܣ�class�ļ��ƶ�ʧ��";
			dos.writeObject(ufc);

			usmlFile.delete();
			classFile.delete();

			return;
		}

		File bakUsmlFile = new File(newUsmlFile.getAbsolutePath() + ".bak");
		if (bakUsmlFile.exists())
		{
			bakUsmlFile.delete();
		}
		if (newUsmlFile.exists())
		{
			if (!newUsmlFile.renameTo(bakUsmlFile))
			{
				ufc.error = "�����ļ�ʧ�ܣ�ɾ����usml�ļ�ʧ��";
				dos.writeObject(ufc);

				usmlFile.delete();
				classFile.delete();
				bakUsmlFile.delete();

				return;
			}
		}

		if (!usmlFile.renameTo(newUsmlFile))
		{
			ufc.error = "�����ļ�ʧ�ܣ�usml�ļ��ƶ�ʧ��";
			dos.writeObject(ufc);

			if (bakUsmlFile.exists())
			{
				bakUsmlFile.renameTo(newUsmlFile);
			}

			usmlFile.delete();
			classFile.delete();
			newClassFile.delete();

			return;
		}

		bakUsmlFile.delete();

		newUsmlFile.setLastModified(ufc.millis);
		if (fci.succeeded)
		{
			fci.lastClassName = fci.className;
			fci.lastMillis = fci.millis;
		}

		fci.ordinal = ufc.ordinal;
		fci.className = ufc.className;
		fci.millis = newUsmlFile.lastModified();
		fci.succeeded = true;

		PreCompiler.saveCompileInfo();

		usmlFile.delete();
		classFile.delete();

		ufc.error = null;
		dos.writeObject(ufc);
	}

	boolean onUpload(String fileName, String className, long millis, int ordinal)
	{
		return false;
	}

	boolean processCommand(Object cmd) throws Exception
	{
		if (cmd instanceof GetCompileInfoCmd)
		{
			GetCompileInfoCmd gcic = (GetCompileInfoCmd) cmd;

			synchronized (PreCompiler.class)
			{
				doGetCompileInfo(gcic);
			}

			return true;
		}
		else if (cmd instanceof UploadFileCmd)
		{
			UploadFileCmd ufc = (UploadFileCmd) cmd;

			synchronized (PreCompiler.class)
			{
				doUpload(ufc);
			}

			return true;
		}

		return false;
	}

	@Override
	public void run()
	{
		try
		{
			setName("Precompile ManageServer");

			while (true)
			{
				ServerSocket ssk;

				Socket sk;

				ssk = new ServerSocket(UnifiedServiceManagement.configData
						.getManageServerPort());

				while (true)
				{
					sk = ssk.accept();

					try
					{
						dis = new ObjectInputStream(sk.getInputStream());
						dos = new ObjectOutputStream(sk.getOutputStream());

						Object cmd = dis.readObject();
						if (!processCommand(cmd))
						{
							Log.wError(LogDef.id_0x10130012);
						}

						sk.close();
					}
					catch (Throwable e)
					{
						sk.close();
						sk = null;
						if (dis != null)
						{
							dis.close();
						}
						if (dos != null)
						{
							dos.close();
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			Log.wException(LogDef.id_0x10130002, e);
		}
	}

}
