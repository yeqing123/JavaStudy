package com.yeqing.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.yeqing.util.FilterUtil;

//过滤敏感字的请求包装类
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
    public String getParameter(String name) {
    	if("title".equals(name) || "content".equals(name)) {
    		return FilterUtil.filter(super.getParameter(name));
    	}
    	return super.getParameter(name);
    }
}
