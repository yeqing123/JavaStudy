// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TransactionAdvice.java

package com.yeqing.tx;

import java.lang.reflect.*;

// Referenced classes of package com.yeqing.tx:
//			TransactionManager

public class TransactionAdvice
	implements InvocationHandler
{

	private Object target;
	private TransactionManager txManager;

	public TransactionAdvice()
	{
	}

	public void setTarget(Object target)
	{
		this.target = target;
	}

	public void setTxManager(TransactionManager txManager)
	{
		this.txManager = txManager;
	}

	public Object getProxyObject()
	{
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object args[])
		throws Throwable
	{
		txManager.begin();
		Object ret = null;
		try
		{
			ret = method.invoke(target, args);
			txManager.commit();
		}
		catch (Exception e)
		{
			txManager.rollback();
			e.printStackTrace();
		}
		return ret;
	}
}
