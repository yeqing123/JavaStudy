package com.yeqing.interceptor;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.yeqing.log.LogUtil;

@SuppressWarnings("all")
//CGLIB动态代理提供的接口与JDK的接口一模一样，也是实现InvocationHandler接口。
//CGLIB动态代理的另外一种写法是使用“拦截器”，继承Callback接口下的MethodInterceptor子接口，实现对真实对象方法调用的拦截，并增强其功能
public class LogAdvice implements MethodInterceptor {

	private Object target;   //目标对象，被拦截的对象
	private LogUtil logUtil;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public void setLogUtil(LogUtil logUtil) {
		this.logUtil = logUtil;
	}

	public <T> T getProxyObject() {
		return (T) Enhancer.create(target.getClass(), this);  //使用Enhancer的静态方法，创建一个代理对象
	}
    //拦截真实对象中的方法调用，对方法调用做前置增强（记录日志）
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		logUtil.writeLog(method.getDeclaringClass().getName(), method.getName());  //记录日志
		return method.invoke(target, args);  //放行
	}

}
