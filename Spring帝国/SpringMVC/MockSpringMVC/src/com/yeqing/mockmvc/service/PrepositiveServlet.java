package com.yeqing.mockmvc.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeqing.mockmvc.annotation.Controller;
import com.yeqing.mockmvc.annotation.RequestMapper;
import com.yeqing.mockmvc.bean.RequestBean;

//获取用户请求，并找到对应的控制类和方法，然后根据方法中的设定进行页面跳转
public class PrepositiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, RequestBean> urlMap = new HashMap<>();
	
	public void init() throws ServletException {
		String packageName = this.getClass().getPackageName();  //获取当类所在的完整包名
		while(packageName.contains(".")) {  //获取最大的包的名称
			packageName = packageName.substring(0, packageName.lastIndexOf("."));
		}
		List<Class<?>> classList = ClassScanUtil.getContrlloerClassListByAnnotation("packageName", Controller.class);
		if(classList != null) {
			for (Class<?> clazz : classList) {
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					RequestMapper rm = method.getAnnotation(RequestMapper.class);
					if(rm != null) {
						String url = rm.value();
						urlMap.put(url, new RequestBean(clazz, method));
					}
				}
			}
		}
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		System.out.println(urlMap);
	}
}
