package com.yeqing._04_request;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing._03_inherit.MyHttpServlet;

public class RequestServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) {
		// 常用方法
		//01.String getMethod():返回请求方式，如GET/POST
		String method = req.getMethod();
		System.out.println(method);
		//02.String getRequestURI():返回请求行中的资源名字部分：如/test/index.html
		String uri = req.getRequestURI();
		System.out.println(uri);
		//03.StringBuffer getRequestURL():返回浏览器地址中的所有信息
		StringBuffer sb = req.getRequestURL();
		System.out.println(sb.toString());
		//04.String getContextPath():返回当前项目的上下文路径（<Context/>元素的path属性值）
		String path = req.getContextPath();
		System.out.println(path);
		//05.String getRemoteAddr():返回发出请求的客户机的IP地址
		String addr = req.getRemoteAddr();
		System.out.println(addr);
		//06.String getHeader(String name):返回指定名称的请求头的值
		String header = req.getHeader("user-Agent");
		System.out.println(header);
		System.out.println("-------------------------------------");
		// 获取请求参数的方法
		//01.String getParameter(String name):返回指定名字参数的值
		String param = req.getParameter("name");
		System.out.println(param);
		//02.String[] getParameterValues(String name):返回指定名字参数的多个参数值
		String[] params = req.getParameterValues("hobby");
		System.out.println(Arrays.toString(params));
		//03.Enumeration<String> getParameterNames():返回所有参数名的Enumeration对象
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
		//04.Map<String, String[]> getParameterMap():返回所有的参数和值所组成的Map对象
		Map<String, String[]> map = req.getParameterMap();
		for (String key : map.keySet()) {
			String[] values = map.get(key);
			System.out.println(key + ":" + Arrays.toString(values));
		}
	}

}
