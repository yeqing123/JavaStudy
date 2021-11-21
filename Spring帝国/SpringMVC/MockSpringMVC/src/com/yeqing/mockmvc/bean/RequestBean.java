package com.yeqing.mockmvc.bean;

import java.lang.reflect.Method;

//保存了每次用户发出请求所对应的方法和该方法所在的处理类
public class RequestBean {
    private Class<?> controllerClass;
    private Method controllerMethod;
    
    public RequestBean(Class<?> controllerClass, Method controllerMethod) {
    	this.controllerClass = controllerClass;
    	this.controllerMethod = controllerMethod;
    }

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getControllerMethod() {
		return controllerMethod;
	}
    
}
