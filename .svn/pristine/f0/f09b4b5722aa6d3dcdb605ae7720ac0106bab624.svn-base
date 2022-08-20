// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.VGProxy;

public final class VGServicePrxHelper extends Ice.ObjectPrxHelperBase implements
		VGServicePrx
{
	public static VGServicePrx __read(IceInternal.BasicStream __is)
	{
		Ice.ObjectPrx proxy = __is.readProxy();
		if (proxy != null)
		{
			VGServicePrxHelper result = new VGServicePrxHelper();
			result.__copyFrom(proxy);
			return result;
		}
		return null;
	}

	public static void __write(IceInternal.BasicStream __os, VGServicePrx v)
	{
		__os.writeProxy(v);
	}

	public static VGServicePrx checkedCast(Ice.ObjectPrx __obj)
	{
		VGServicePrx __d = null;
		if (__obj != null)
		{
			try
			{
				__d = (VGServicePrx) __obj;
			}
			catch (ClassCastException ex)
			{
				if (__obj.ice_isA("::VGProxy::VGService"))
				{
					VGServicePrxHelper __h = new VGServicePrxHelper();
					__h.__copyFrom(__obj);
					__d = __h;
				}
			}
		}
		return __d;
	}

	public static VGServicePrx checkedCast(Ice.ObjectPrx __obj,
			java.util.Map<String, String> __ctx)
	{
		VGServicePrx __d = null;
		if (__obj != null)
		{
			try
			{
				__d = (VGServicePrx) __obj;
			}
			catch (ClassCastException ex)
			{
				if (__obj.ice_isA("::VGProxy::VGService", __ctx))
				{
					VGServicePrxHelper __h = new VGServicePrxHelper();
					__h.__copyFrom(__obj);
					__d = __h;
				}
			}
		}
		return __d;
	}

	public static VGServicePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
	{
		VGServicePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			try
			{
				if (__bb.ice_isA("::VGProxy::VGService"))
				{
					VGServicePrxHelper __h = new VGServicePrxHelper();
					__h.__copyFrom(__bb);
					__d = __h;
				}
			}
			catch (Ice.FacetNotExistException ex)
			{
			}
		}
		return __d;
	}

	public static VGServicePrx checkedCast(Ice.ObjectPrx __obj, String __facet,
			java.util.Map<String, String> __ctx)
	{
		VGServicePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			try
			{
				if (__bb.ice_isA("::VGProxy::VGService", __ctx))
				{
					VGServicePrxHelper __h = new VGServicePrxHelper();
					__h.__copyFrom(__bb);
					__d = __h;
				}
			}
			catch (Ice.FacetNotExistException ex)
			{
			}
		}
		return __d;
	}

	public static VGServicePrx uncheckedCast(Ice.ObjectPrx __obj)
	{
		VGServicePrx __d = null;
		if (__obj != null)
		{
			VGServicePrxHelper __h = new VGServicePrxHelper();
			__h.__copyFrom(__obj);
			__d = __h;
		}
		return __d;
	}

	public static VGServicePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
	{
		VGServicePrx __d = null;
		if (__obj != null)
		{
			Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
			VGServicePrxHelper __h = new VGServicePrxHelper();
			__h.__copyFrom(__bb);
			__d = __h;
		}
		return __d;
	}

	@Override
	protected Ice._ObjectDelD __createDelegateD()
	{
		return new _VGServiceDelD();
	}

	@Override
	protected Ice._ObjectDelM __createDelegateM()
	{
		return new _VGServiceDelM();
	}

	public int cnfCloseConf(int nConfID)
	{
		return cnfCloseConf(nConfID, null, false);
	}

	public int cnfCloseConf(int nConfID, java.util.Map<String, String> __ctx)
	{
		return cnfCloseConf(nConfID, __ctx, true);
	}

	private int cnfCloseConf(int nConfID, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfCloseConf");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfCloseConf(nConfID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfConfPlay(int nConfID, String szFileName, int nSpeed,
			int nVolume, int nRate)
	{
		return cnfConfPlay(nConfID, szFileName, nSpeed, nVolume, nRate, null,
				false);
	}

	public int cnfConfPlay(int nConfID, String szFileName, int nSpeed,
			int nVolume, int nRate, java.util.Map<String, String> __ctx)
	{
		return cnfConfPlay(nConfID, szFileName, nSpeed, nVolume, nRate, __ctx,
				true);
	}

	private int cnfConfPlay(int nConfID, String szFileName, int nSpeed,
			int nVolume, int nRate, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfConfPlay");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfConfPlay(nConfID, szFileName, nSpeed, nVolume,
						nRate, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfConfRecord(int nConfID, String szFileName, int nRate)
	{
		return cnfConfRecord(nConfID, szFileName, nRate, null, false);
	}

	public int cnfConfRecord(int nConfID, String szFileName, int nRate,
			java.util.Map<String, String> __ctx)
	{
		return cnfConfRecord(nConfID, szFileName, nRate, __ctx, true);
	}

	private int cnfConfRecord(int nConfID, String szFileName, int nRate,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfConfRecord");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfConfRecord(nConfID, szFileName, nRate, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfCreateConf(int nMaxUserNum, int nVoxResType,
			int nVoxResBindType, Ice.IntHolder nConfID)
	{
		return cnfCreateConf(nMaxUserNum, nVoxResType, nVoxResBindType,
				nConfID, null, false);
	}

	public int cnfCreateConf(int nMaxUserNum, int nVoxResType,
			int nVoxResBindType, Ice.IntHolder nConfID,
			java.util.Map<String, String> __ctx)
	{
		return cnfCreateConf(nMaxUserNum, nVoxResType, nVoxResBindType,
				nConfID, __ctx, true);
	}

	private int cnfCreateConf(int nMaxUserNum, int nVoxResType,
			int nVoxResBindType, Ice.IntHolder nConfID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfCreateConf");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfCreateConf(nMaxUserNum, nVoxResType,
						nVoxResBindType, nConfID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute)
	{
		return cnfGetUserAttrib(nResID, nAttribute, null, false);
	}

	public int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute,
			java.util.Map<String, String> __ctx)
	{
		return cnfGetUserAttrib(nResID, nAttribute, __ctx, true);
	}

	private int cnfGetUserAttrib(int nResID, Ice.IntHolder nAttribute,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfGetUserAttrib");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfGetUserAttrib(nResID, nAttribute, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfJoinConf(int nResID, int nConfID, int nAttribute)
	{
		return cnfJoinConf(nResID, nConfID, nAttribute, null, false);
	}

	public int cnfJoinConf(int nResID, int nConfID, int nAttribute,
			java.util.Map<String, String> __ctx)
	{
		return cnfJoinConf(nResID, nConfID, nAttribute, __ctx, true);
	}

	private int cnfJoinConf(int nResID, int nConfID, int nAttribute,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfJoinConf");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfJoinConf(nResID, nConfID, nAttribute, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfLeaveConf(int nResID, int nConfID)
	{
		return cnfLeaveConf(nResID, nConfID, null, false);
	}

	public int cnfLeaveConf(int nResID, int nConfID,
			java.util.Map<String, String> __ctx)
	{
		return cnfLeaveConf(nResID, nConfID, __ctx, true);
	}

	private int cnfLeaveConf(int nResID, int nConfID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfLeaveConf");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfLeaveConf(nResID, nConfID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfMemberPlay(int nResID, String cszFileName,
			String szInteruptKeys, int nRate)
	{
		return cnfMemberPlay(nResID, cszFileName, szInteruptKeys, nRate, null,
				false);
	}

	public int cnfMemberPlay(int nResID, String cszFileName,
			String szInteruptKeys, int nRate,
			java.util.Map<String, String> __ctx)
	{
		return cnfMemberPlay(nResID, cszFileName, szInteruptKeys, nRate, __ctx,
				true);
	}

	private int cnfMemberPlay(int nResID, String cszFileName,
			String szInteruptKeys, int nRate,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfMemberPlay");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfMemberPlay(nResID, cszFileName, szInteruptKeys,
						nRate, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfPlayList(int nConfID, int nRate, int nFileCount,
			String[] szVoxFileNames, int nVoiceLib)
	{
		return cnfPlayList(nConfID, nRate, nFileCount, szVoxFileNames,
				nVoiceLib, null, false);
	}

	public int cnfPlayList(int nConfID, int nRate, int nFileCount,
			String[] szVoxFileNames, int nVoiceLib,
			java.util.Map<String, String> __ctx)
	{
		return cnfPlayList(nConfID, nRate, nFileCount, szVoxFileNames,
				nVoiceLib, __ctx, true);
	}

	private int cnfPlayList(int nConfID, int nRate, int nFileCount,
			String[] szVoxFileNames, int nVoiceLib,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfPlayList");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfPlayList(nConfID, nRate, nFileCount,
						szVoxFileNames, nVoiceLib, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib)
	{
		return cnfPlayTTS(nConfID, nType, szText, nVoiceLib, null, false);
	}

	public int cnfPlayTTS(int nConfID, int nType, String szText, int nVoiceLib,
			java.util.Map<String, String> __ctx)
	{
		return cnfPlayTTS(nConfID, nType, szText, nVoiceLib, __ctx, true);
	}

	private int cnfPlayTTS(int nConfID, int nType, String szText,
			int nVoiceLib, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfPlayTTS");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfPlayTTS(nConfID, nType, szText, nVoiceLib,
						__ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfSetUserAttrib(int nResID, int nAttribute)
	{
		return cnfSetUserAttrib(nResID, nAttribute, null, false);
	}

	public int cnfSetUserAttrib(int nResID, int nAttribute,
			java.util.Map<String, String> __ctx)
	{
		return cnfSetUserAttrib(nResID, nAttribute, __ctx, true);
	}

	private int cnfSetUserAttrib(int nResID, int nAttribute,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfSetUserAttrib");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfSetUserAttrib(nResID, nAttribute, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfStopConfPlay(int nConfID)
	{
		return cnfStopConfPlay(nConfID, null, false);
	}

	public int cnfStopConfPlay(int nConfID, java.util.Map<String, String> __ctx)
	{
		return cnfStopConfPlay(nConfID, __ctx, true);
	}

	private int cnfStopConfPlay(int nConfID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfStopConfPlay");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfStopConfPlay(nConfID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int cnfStopConfRecord(int nConfID)
	{
		return cnfStopConfRecord(nConfID, null, false);
	}

	public int cnfStopConfRecord(int nConfID,
			java.util.Map<String, String> __ctx)
	{
		return cnfStopConfRecord(nConfID, __ctx, true);
	}

	private int cnfStopConfRecord(int nConfID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("cnfStopConfRecord");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.cnfStopConfRecord(nConfID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int GetCnfEvt(int nTimeout, CnfEventHolder event)
	{
		return GetCnfEvt(nTimeout, event, null, false);
	}

	public int GetCnfEvt(int nTimeout, CnfEventHolder event,
			java.util.Map<String, String> __ctx)
	{
		return GetCnfEvt(nTimeout, event, __ctx, true);
	}

	private int GetCnfEvt(int nTimeout, CnfEventHolder event,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("GetCnfEvt");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.GetCnfEvt(nTimeout, event, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int GetMgEvt(int nTimeout, MGEventHolder event)
	{
		return GetMgEvt(nTimeout, event, null, false);
	}

	public int GetMgEvt(int nTimeout, MGEventHolder event,
			java.util.Map<String, String> __ctx)
	{
		return GetMgEvt(nTimeout, event, __ctx, true);
	}

	private int GetMgEvt(int nTimeout, MGEventHolder event,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("GetMgEvt");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.GetMgEvt(nTimeout, event, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int GetSgEvt(int nTimeout, SGEventHolder event)
	{
		return GetSgEvt(nTimeout, event, null, false);
	}

	public int GetSgEvt(int nTimeout, SGEventHolder event,
			java.util.Map<String, String> __ctx)
	{
		return GetSgEvt(nTimeout, event, __ctx, true);
	}

	private int GetSgEvt(int nTimeout, SGEventHolder event,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("GetSgEvt");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.GetSgEvt(nTimeout, event, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int GetUVMGResNum(Ice.IntHolder ResTotal,
			Ice.IntHolder OutboundResTotal)
	{
		return GetUVMGResNum(ResTotal, OutboundResTotal, null, false);
	}

	public int GetUVMGResNum(Ice.IntHolder ResTotal,
			Ice.IntHolder OutboundResTotal, java.util.Map<String, String> __ctx)
	{
		return GetUVMGResNum(ResTotal, OutboundResTotal, __ctx, true);
	}

	private int GetUVMGResNum(Ice.IntHolder ResTotal,
			Ice.IntHolder OutboundResTotal,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("GetUVMGResNum");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.GetUVMGResNum(ResTotal, OutboundResTotal, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgASR(int nResID, int nCallID, int nMaxKeys,
			String szInteruptKeys, int nRate, int nFileCount,
			String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout,
			int nAcceptScore, int nResultNum, float fTimeoutBetweenWord,
			int nVoiceLib)
	{
		return mgASR(nResID, nCallID, nMaxKeys, szInteruptKeys, nRate,
				nFileCount, szVoxFileList, szGrammar, nNoSpeechTimeout,
				nAcceptScore, nResultNum, fTimeoutBetweenWord, nVoiceLib, null,
				false);
	}

	public int mgASR(int nResID, int nCallID, int nMaxKeys,
			String szInteruptKeys, int nRate, int nFileCount,
			String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout,
			int nAcceptScore, int nResultNum, float fTimeoutBetweenWord,
			int nVoiceLib, java.util.Map<String, String> __ctx)
	{
		return mgASR(nResID, nCallID, nMaxKeys, szInteruptKeys, nRate,
				nFileCount, szVoxFileList, szGrammar, nNoSpeechTimeout,
				nAcceptScore, nResultNum, fTimeoutBetweenWord, nVoiceLib,
				__ctx, true);
	}

	private int mgASR(int nResID, int nCallID, int nMaxKeys,
			String szInteruptKeys, int nRate, int nFileCount,
			String[] szVoxFileList, String szGrammar, int nNoSpeechTimeout,
			int nAcceptScore, int nResultNum, float fTimeoutBetweenWord,
			int nVoiceLib, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgASR");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgASR(nResID, nCallID, nMaxKeys, szInteruptKeys,
						nRate, nFileCount, szVoxFileList, szGrammar,
						nNoSpeechTimeout, nAcceptScore, nResultNum,
						fTimeoutBetweenWord, nVoiceLib, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgFlushBuffer(int nResID, int nCallID)
	{
		return mgFlushBuffer(nResID, nCallID, null, false);
	}

	public int mgFlushBuffer(int nResID, int nCallID,
			java.util.Map<String, String> __ctx)
	{
		return mgFlushBuffer(nResID, nCallID, __ctx, true);
	}

	private int mgFlushBuffer(int nResID, int nCallID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgFlushBuffer");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgFlushBuffer(nResID, nCallID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgPlayList(int nResID, int nCallID, String szInteruptKeys,
			int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib)
	{
		return mgPlayList(nResID, nCallID, szInteruptKeys, nRate, nFileCount,
				szVoxFileList, nVoiceLib, null, false);
	}

	public int mgPlayList(int nResID, int nCallID, String szInteruptKeys,
			int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib,
			java.util.Map<String, String> __ctx)
	{
		return mgPlayList(nResID, nCallID, szInteruptKeys, nRate, nFileCount,
				szVoxFileList, nVoiceLib, __ctx, true);
	}

	private int mgPlayList(int nResID, int nCallID, String szInteruptKeys,
			int nRate, int nFileCount, String[] szVoxFileList, int nVoiceLib,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgPlayList");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgPlayList(nResID, nCallID, szInteruptKeys, nRate,
						nFileCount, szVoxFileList, nVoiceLib, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys,
			int nType, String szText, int nVoiceLib, int nRate)
	{
		return mgPlayTTS(nResID, nCallID, szInteruptKeys, nType, szText,
				nVoiceLib, nRate, null, false);
	}

	public int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys,
			int nType, String szText, int nVoiceLib, int nRate,
			java.util.Map<String, String> __ctx)
	{
		return mgPlayTTS(nResID, nCallID, szInteruptKeys, nType, szText,
				nVoiceLib, nRate, __ctx, true);
	}

	private int mgPlayTTS(int nResID, int nCallID, String szInteruptKeys,
			int nType, String szText, int nVoiceLib, int nRate,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgPlayTTS");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgPlayTTS(nResID, nCallID, szInteruptKeys, nType,
						szText, nVoiceLib, nRate, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgPlayVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nRate, int nBeginTime)
	{
		return mgPlayVoice(nResID, nCallID, szFileName, szInteruptKeys, nRate,
				nBeginTime, null, false);
	}

	public int mgPlayVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nRate, int nBeginTime,
			java.util.Map<String, String> __ctx)
	{
		return mgPlayVoice(nResID, nCallID, szFileName, szInteruptKeys, nRate,
				nBeginTime, __ctx, true);
	}

	private int mgPlayVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nRate, int nBeginTime,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgPlayVoice");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgPlayVoice(nResID, nCallID, szFileName,
						szInteruptKeys, nRate, nBeginTime, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount,
			String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey)
	{
		return mgReceiveDTMF(nResID, nCallID, nKeyCount, szInteruptKeys,
				nMax1stkeyTime, nTimeBetweenKey, null, false);
	}

	public int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount,
			String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey,
			java.util.Map<String, String> __ctx)
	{
		return mgReceiveDTMF(nResID, nCallID, nKeyCount, szInteruptKeys,
				nMax1stkeyTime, nTimeBetweenKey, __ctx, true);
	}

	private int mgReceiveDTMF(int nResID, int nCallID, int nKeyCount,
			String szInteruptKeys, int nMax1stkeyTime, int nTimeBetweenKey,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgReceiveDTMF");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgReceiveDTMF(nResID, nCallID, nKeyCount,
						szInteruptKeys, nMax1stkeyTime, nTimeBetweenKey, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgReceiveFax(int nResID, int nCallID, String szFileName)
	{
		return mgReceiveFax(nResID, nCallID, szFileName, null, false);
	}

	public int mgReceiveFax(int nResID, int nCallID, String szFileName,
			java.util.Map<String, String> __ctx)
	{
		return mgReceiveFax(nResID, nCallID, szFileName, __ctx, true);
	}

	private int mgReceiveFax(int nResID, int nCallID, String szFileName,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgReceiveFax");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgReceiveFax(nResID, nCallID, szFileName, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgRecordVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nMaxTime, int nRate)
	{
		return mgRecordVoice(nResID, nCallID, szFileName, szInteruptKeys,
				nMaxTime, nRate, null, false);
	}

	public int mgRecordVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nMaxTime, int nRate,
			java.util.Map<String, String> __ctx)
	{
		return mgRecordVoice(nResID, nCallID, szFileName, szInteruptKeys,
				nMaxTime, nRate, __ctx, true);
	}

	private int mgRecordVoice(int nResID, int nCallID, String szFileName,
			String szInteruptKeys, int nMaxTime, int nRate,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgRecordVoice");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgRecordVoice(nResID, nCallID, szFileName,
						szInteruptKeys, nMaxTime, nRate, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgSendDTMF(int nResID, int nCallID, String szSignals)
	{
		return mgSendDTMF(nResID, nCallID, szSignals, null, false);
	}

	public int mgSendDTMF(int nResID, int nCallID, String szSignals,
			java.util.Map<String, String> __ctx)
	{
		return mgSendDTMF(nResID, nCallID, szSignals, __ctx, true);
	}

	private int mgSendDTMF(int nResID, int nCallID, String szSignals,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgSendDTMF");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgSendDTMF(nResID, nCallID, szSignals, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgSendFax(int nResID, int nCallID, String szFileName)
	{
		return mgSendFax(nResID, nCallID, szFileName, null, false);
	}

	public int mgSendFax(int nResID, int nCallID, String szFileName,
			java.util.Map<String, String> __ctx)
	{
		return mgSendFax(nResID, nCallID, szFileName, __ctx, true);
	}

	private int mgSendFax(int nResID, int nCallID, String szFileName,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgSendFax");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgSendFax(nResID, nCallID, szFileName, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int mgStopIO(int nResID, int nCallID)
	{
		return mgStopIO(nResID, nCallID, null, false);
	}

	public int mgStopIO(int nResID, int nCallID,
			java.util.Map<String, String> __ctx)
	{
		return mgStopIO(nResID, nCallID, __ctx, true);
	}

	private int mgStopIO(int nResID, int nCallID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("mgStopIO");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.mgStopIO(nResID, nCallID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmBlindCloseConf(int nConfID)
	{
		return rmBlindCloseConf(nConfID, null, false);
	}

	public int rmBlindCloseConf(int nConfID, java.util.Map<String, String> __ctx)
	{
		return rmBlindCloseConf(nConfID, __ctx, true);
	}

	private int rmBlindCloseConf(int nConfID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmBlindCloseConf");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmBlindCloseConf(nConfID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmBlindCreateConf(int nResID1, int nResID2,
			Ice.IntHolder pnConfID)
	{
		return rmBlindCreateConf(nResID1, nResID2, pnConfID, null, false);
	}

	public int rmBlindCreateConf(int nResID1, int nResID2,
			Ice.IntHolder pnConfID, java.util.Map<String, String> __ctx)
	{
		return rmBlindCreateConf(nResID1, nResID2, pnConfID, __ctx, true);
	}

	private int rmBlindCreateConf(int nResID1, int nResID2,
			Ice.IntHolder pnConfID, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmBlindCreateConf");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmBlindCreateConf(nResID1, nResID2, pnConfID,
						__ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmRouteToHoldMusic(int nResID, int nHoldMusicID)
	{
		return rmRouteToHoldMusic(nResID, nHoldMusicID, null, false);
	}

	public int rmRouteToHoldMusic(int nResID, int nHoldMusicID,
			java.util.Map<String, String> __ctx)
	{
		return rmRouteToHoldMusic(nResID, nHoldMusicID, __ctx, true);
	}

	private int rmRouteToHoldMusic(int nResID, int nHoldMusicID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmRouteToHoldMusic");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmRouteToHoldMusic(nResID, nHoldMusicID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode)
	{
		return rmRouteTwoRes(nRes1ID, nRes2ID, mode, null, false);
	}

	public int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode,
			java.util.Map<String, String> __ctx)
	{
		return rmRouteTwoRes(nRes1ID, nRes2ID, mode, __ctx, true);
	}

	private int rmRouteTwoRes(int nRes1ID, int nRes2ID, int mode,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmRouteTwoRes");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmRouteTwoRes(nRes1ID, nRes2ID, mode, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmStartConferrenceRecord(int nResID, int nCallID,
			String strRecordFileName)
	{
		return rmStartConferrenceRecord(nResID, nCallID, strRecordFileName,
				null, false);
	}

	public int rmStartConferrenceRecord(int nResID, int nCallID,
			String strRecordFileName, java.util.Map<String, String> __ctx)
	{
		return rmStartConferrenceRecord(nResID, nCallID, strRecordFileName,
				__ctx, true);
	}

	private int rmStartConferrenceRecord(int nResID, int nCallID,
			String strRecordFileName, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmStartConferrenceRecord");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmStartConferrenceRecord(nResID, nCallID,
						strRecordFileName, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID)
	{
		return rmUnRouteToHoldMusic(nResID, nHoldMusicID, null, false);
	}

	public int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID,
			java.util.Map<String, String> __ctx)
	{
		return rmUnRouteToHoldMusic(nResID, nHoldMusicID, __ctx, true);
	}

	private int rmUnRouteToHoldMusic(int nResID, int nHoldMusicID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmUnRouteToHoldMusic");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmUnRouteToHoldMusic(nResID, nHoldMusicID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode)
	{
		return rmUnrouteTwoRes(nRes1ID, nRes2ID, mode, null, false);
	}

	public int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode,
			java.util.Map<String, String> __ctx)
	{
		return rmUnrouteTwoRes(nRes1ID, nRes2ID, mode, __ctx, true);
	}

	private int rmUnrouteTwoRes(int nRes1ID, int nRes2ID, int mode,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("rmUnrouteTwoRes");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.rmUnrouteTwoRes(nRes1ID, nRes2ID, mode, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int sgAnswerCall(int nResID, int nCallID)
	{
		return sgAnswerCall(nResID, nCallID, null, false);
	}

	public int sgAnswerCall(int nResID, int nCallID,
			java.util.Map<String, String> __ctx)
	{
		return sgAnswerCall(nResID, nCallID, __ctx, true);
	}

	private int sgAnswerCall(int nResID, int nCallID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("sgAnswerCall");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.sgAnswerCall(nResID, nCallID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int sgBlindMakeCall(String szCallerNum, String szCalledNum,
			int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID)
	{
		return sgBlindMakeCall(szCallerNum, szCalledNum, nTimeout, nResID,
				nCallID, null, false);
	}

	public int sgBlindMakeCall(String szCallerNum, String szCalledNum,
			int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID,
			java.util.Map<String, String> __ctx)
	{
		return sgBlindMakeCall(szCallerNum, szCalledNum, nTimeout, nResID,
				nCallID, __ctx, true);
	}

	private int sgBlindMakeCall(String szCallerNum, String szCalledNum,
			int nTimeout, Ice.IntHolder nResID, Ice.IntHolder nCallID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("sgBlindMakeCall");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.sgBlindMakeCall(szCallerNum, szCalledNum,
						nTimeout, nResID, nCallID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int sgLimitBilling(int nResID, int nCallID, String sFilename,
			int nTimeout)
	{
		return sgLimitBilling(nResID, nCallID, sFilename, nTimeout, null, false);
	}

	public int sgLimitBilling(int nResID, int nCallID, String sFilename,
			int nTimeout, java.util.Map<String, String> __ctx)
	{
		return sgLimitBilling(nResID, nCallID, sFilename, nTimeout, __ctx, true);
	}

	private int sgLimitBilling(int nResID, int nCallID, String sFilename,
			int nTimeout, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("sgLimitBilling");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.sgLimitBilling(nResID, nCallID, sFilename,
						nTimeout, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int sgMakeCall(int nResID, String szCallerNum, String szCalledNum,
			int nTimeout, Ice.IntHolder nCallID)
	{
		return sgMakeCall(nResID, szCallerNum, szCalledNum, nTimeout, nCallID,
				null, false);
	}

	public int sgMakeCall(int nResID, String szCallerNum, String szCalledNum,
			int nTimeout, Ice.IntHolder nCallID,
			java.util.Map<String, String> __ctx)
	{
		return sgMakeCall(nResID, szCallerNum, szCalledNum, nTimeout, nCallID,
				__ctx, true);
	}

	private int sgMakeCall(int nResID, String szCallerNum, String szCalledNum,
			int nTimeout, Ice.IntHolder nCallID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("sgMakeCall");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.sgMakeCall(nResID, szCallerNum, szCalledNum,
						nTimeout, nCallID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int sgReleaseCall(int nResID, int nCallID)
	{
		return sgReleaseCall(nResID, nCallID, null, false);
	}

	public int sgReleaseCall(int nResID, int nCallID,
			java.util.Map<String, String> __ctx)
	{
		return sgReleaseCall(nResID, nCallID, __ctx, true);
	}

	private int sgReleaseCall(int nResID, int nCallID,
			java.util.Map<String, String> __ctx, boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("sgReleaseCall");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.sgReleaseCall(nResID, nCallID, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}

	public int VGInitialize(int nDistributeMode, String szMonitorDNIS,
			String szReserved)
	{
		return VGInitialize(nDistributeMode, szMonitorDNIS, szReserved, null,
				false);
	}

	public int VGInitialize(int nDistributeMode, String szMonitorDNIS,
			String szReserved, java.util.Map<String, String> __ctx)
	{
		return VGInitialize(nDistributeMode, szMonitorDNIS, szReserved, __ctx,
				true);
	}

	private int VGInitialize(int nDistributeMode, String szMonitorDNIS,
			String szReserved, java.util.Map<String, String> __ctx,
			boolean __explicitCtx)
	{
		if (__explicitCtx && (__ctx == null))
		{
			__ctx = _emptyContext;
		}
		int __cnt = 0;
		while (true)
		{
			Ice._ObjectDel __delBase = null;
			try
			{
				__checkTwowayOnly("VGInitialize");
				__delBase = __getDelegate();
				_VGServiceDel __del = (_VGServiceDel) __delBase;
				return __del.VGInitialize(nDistributeMode, szMonitorDNIS,
						szReserved, __ctx);
			}
			catch (IceInternal.LocalExceptionWrapper __ex)
			{
				__handleExceptionWrapper(__delBase, __ex);
			}
			catch (Ice.LocalException __ex)
			{
				__cnt = __handleException(__delBase, __ex, __cnt);
			}
		}
	}
}
