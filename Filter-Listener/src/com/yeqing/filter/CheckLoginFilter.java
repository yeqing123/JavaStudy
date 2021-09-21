package com.yeqing.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class CheckLoginFilter implements Filter {
	private List<String> list = new ArrayList<>();

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
//		// 为了使用我们需要的方法，将父类强转为子类
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//		Object user = request.getSession().getAttribute("USER_IN_SESSION");
//		String requestUri = request.getRequestURI();
//		if (!list.contains(requestUri)) {
//			System.out.println("过滤器正在拦截的请求页面是：" + requestUri);
//			if (user == null) {
//				response.sendRedirect("/login.jsp");
//				return;
//			}
//		}
//
//		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
//		String configFile = config.getInitParameter("uncheckUri");
//		if(configFile == null) {
//			return ;
//		}
//		File f = new File(
//				Thread.currentThread().getContextClassLoader().getResource(configFile).getFile());
//    	try {
//    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    		// 依靠工厂类对象创建一个DocumentBuilder对象
//    		DocumentBuilder builder = factory.newDocumentBuilder();
//			if (f.exists()) {
//		    	// 使用DocumentBuilder对象的parse()方法，解析指定的XML文件，获得一个Document对象
//		    	Document doc = builder.parse(f);
//				NodeList uncheckUris = doc.getElementsByTagName("uncheckUri");
//				for (int i = 0; i < uncheckUris.getLength(); i++) {
//					list.add(uncheckUris.item(i).getTextContent());
//				}
//			} else {
//				throw new RuntimeException("配置文件uncheckRequestUri.xml不存在！");
//			}
//    	}catch(Exception e) {
//    		 e.printStackTrace();
//    	}
	}
}
