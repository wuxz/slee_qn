// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.0

package com.channelsoft.DDSSpace;

public final class _DDSInterfaceDelD extends Ice._ObjectDelD implements _DDSInterfaceDel
{
    public void
    AddEnterprise(String ServiceName, String EntpID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "AddEnterprise", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    __servant.AddEnterprise(ServiceName, EntpID, __current);
                    return;
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }

    public void
    ResetIvrEvent(String veName, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "ResetIvrEvent", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    __servant.ResetIvrEvent(veName, __current);
                    return;
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }

    public void
    RmEnterprise(String ServiceName, String EntpID, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "RmEnterprise", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    __servant.RmEnterprise(ServiceName, EntpID, __current);
                    return;
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }

    public int
    regService(String ServiceName, String strProxy, String[] lstParam, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "regService", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    return __servant.regService(ServiceName, strProxy, lstParam, __current);
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }

    public void
    setCmsEvent(CMSEventInfoT event, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "setCmsEvent", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    __servant.setCmsEvent(event, __current);
                    return;
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }

    public void
    setIvrEvent(IvrEventInfoT infoCallEvent, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "setIvrEvent", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    __servant.setIvrEvent(infoCallEvent, __current);
                    return;
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }

    public void
    setIvrLicense(IvrLicenseInfoT[] lstLicense, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper
    {
        Ice.Current __current = new Ice.Current();
        __initCurrent(__current, "setIvrLicense", Ice.OperationMode.Normal, __ctx);
        while(true)
        {
            IceInternal.Direct __direct = new IceInternal.Direct(__current);
            try
            {
                DDSInterface __servant = null;
                try
                {
                    __servant = (DDSInterface)__direct.servant();
                }
                catch(ClassCastException __ex)
                {
                    Ice.OperationNotExistException __opEx = new Ice.OperationNotExistException();
                    __opEx.id = __current.id;
                    __opEx.facet = __current.facet;
                    __opEx.operation = __current.operation;
                    throw __opEx;
                }
                try
                {
                    __servant.setIvrLicense(lstLicense, __current);
                    return;
                }
                catch(Ice.LocalException __ex)
                {
                    throw new IceInternal.LocalExceptionWrapper(__ex, false);
                }
            }
            finally
            {
                __direct.destroy();
            }
        }
    }
}
