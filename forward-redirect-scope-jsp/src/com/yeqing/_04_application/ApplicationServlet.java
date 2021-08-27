package com.yeqing._04_application;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/app")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	//===============获取ServletContext对象的方式==============
    	//方式1：在Servlet类中获取
    	ServletContext app1 = super.getServletContext();
    	//方式2：通过请求对象来获取：
    	ServletContext app2 = req.getServletContext();
    	//方式3：通过session对象来获取
    	ServletContext app3 = req.getSession().getServletContext();
    	// 三种方式过去的ServletContext对象，都是同一个对象
    	Boolean b1 = app1==app2;
    	Boolean b2 = app1==app3;
    	Boolean b3 = app2==app3;
    	System.out.println(b1 + ", " + b2 + ", " + b3);
    	//================ServletContext接口常用的方法=============
    	// 根据一个资源的相对Web跟的路径，获取它的绝对路径
    	String realPath = app1.getRealPath("/WEB-INF/login.html");
    	System.out.println("RealPath: " + realPath);
    	// 返回当前响应下的上下文路径
    	String contextPath = app1.getContextPath();
    	System.out.println("ContextPath: " + contextPath);
    	//===============设置并获取全局初始化参数====================
    	// 首先在项目自己的web.xml中使用<context-param>标签设置全局初始化参数
    	// 然后调用调用ServletContext对象的getInitParameter()方法来获取指定名称的全局初始化参数
    	String encodingValue = app1.getInitParameter("encoding");
    	System.out.println("encoding: " + encodingValue);
    	// 获取所有全局的初始化参数的名字
    	Enumeration<String> names = app1.getInitParameterNames();
    	while(names.hasMoreElements()) {
    		System.out.println(names.nextElement());
		}
    }
}
