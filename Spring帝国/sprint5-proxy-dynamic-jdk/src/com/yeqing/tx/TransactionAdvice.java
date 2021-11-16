package com.yeqing.tx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("all")
public class TransactionAdvice implements InvocationHandler {
	
	private Object target;   //目标对象，被代理的真实对象（又叫委托对象）
	private TransactionManager txManager; //事务管理类
	
	public void setTarget(Object target) {
		this.target = target;
	}

	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	public <T> T getProxyObject() { //实例化一个动态代理对象
		return (T) Proxy.newProxyInstance(
				target.getClass().getClassLoader(),  //真实对象的类加载器 
				target.getClass().getInterfaces(),   //真实对象所实现的接口（使用JDK必须有接口）
				this);             //事务增强对象
	}

	//在该方法中调用真实对象中的方法，并在调用方法之前和之后做增强
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		txManager.begin();  //开启事务
		Object ret = null;
		try {
			ret = method.invoke(target, args);  //调用真实对象中的方法
			txManager.commit();  //提交事务
		} catch(Exception e) {
			txManager.rollback();  //回滚事务
			e.printStackTrace();
		}
		return ret;
	}
}
