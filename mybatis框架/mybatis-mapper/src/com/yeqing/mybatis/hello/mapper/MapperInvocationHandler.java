package com.yeqing.mybatis.hello.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

import lombok.Setter;

public class MapperInvocationHandler<T> implements InvocationHandler {
    private Class<T> mapperClass;
    @Setter
    private SqlSession session;
    
    public MapperInvocationHandler(Class<T> mapperClass) {
    	this.mapperClass = mapperClass;
    }
	@SuppressWarnings("unchecked")
	public T getProxy() {
		return (T) Proxy.newProxyInstance(this.mapperClass.getClassLoader(), new Class[] {mapperClass}, this);
	}
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		String methodName = method.getName();
		String mapperName = mapperClass.getName();
		String statement = mapperName + "." + methodName;
		return method.invoke(proxy, params);
	}

}
