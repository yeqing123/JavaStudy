package com.yeqing.mybatis.hello.mapper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lombok.Setter;

@Setter
public class MyMapperProxy<T> implements InvocationHandler {
	private SqlSession session = null;
	private Class<T> mapperClass = null;
	
	public T getMapperObject() {
		return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[] {mapperClass}, this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String mapperName = mapperClass.getName();
		String methodName = method.getName();
		String statement = mapperName + "." + methodName;
		System.out.println("statement:" + statement);
		List<Method> objMethods = Arrays.asList(Object.class.getMethods());  //找出Object对象的所有方法
		if(objMethods.contains(method)) {  //如果是Object的方法，则直接调用
			return method.invoke(proxy.getClass(), args);  //注意:这里不能直接传入proxy对象，否则会造成递归调用，导致内存溢出
		}
		//如果不是Object的方法，则简单判断是否为数据库的操作方法
		switch(methodName) {
			case "insert":return session.insert(statement, args[0]);
			case "delete":return session.delete(statement, args[0]);
			case "update":return session.update(statement, args[0]);
			case "get":return session.selectOne(statement, args[0]);
			default: return session.selectList(statement);
		}
	}
	
}