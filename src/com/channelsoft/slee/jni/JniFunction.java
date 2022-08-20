package com.channelsoft.slee.jni;

import com.channelsoft.reusable.util.StringWrapper;

public class JniFunction
{
	public static native int sleePlgIfEx(int channel, int switchCase,
			StringWrapper p1, StringWrapper p2);

	public static native int sleePlgSIf(int channel, String server,
			int switchCase, String in, StringWrapper out, int timeout);

	public static native void sleePlgSleep(int millis);

	public static native void sleePlgStart();

	public static native void sleePlgStop();

	public static byte[] string2Buffer(String src, int length)
	{
		byte[] dest = new byte[length];
		if (src == null)
		{
			dest[0] = 0;
			return dest;
		}

		byte bytes[] = src.getBytes();
		if (bytes.length >= length)
		{
			return null;
		}

		System.arraycopy(bytes, 0, dest, 0, bytes.length);
		dest[bytes.length] = 0;

		return dest;
	}
}
