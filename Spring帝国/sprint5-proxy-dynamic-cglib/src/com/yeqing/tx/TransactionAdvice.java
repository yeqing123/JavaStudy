package com.yeqing.tx;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

@SuppressWarnings("all")
public class TransactionAdvice implements InvocationHandler {
	
	private Object target;   //真实对象
	private TransactionManager txManager; //事务管理类
	
	public void setTarget(Object target) {
		this.target = target;
	}

	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	//获得一个动态代理对象
	public <T> T getProxyObject() { 
		Enhancer enhancer = new Enhancer();  //创建一个增强器
		enhancer.setSuperclass(target.getClass()); //设置增强器的父类
		enhancer.setCallback(this);  //回调事务增强类
		return (T) enhancer.create();   //创建一个代理对象
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
