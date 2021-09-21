package com.yeqing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
    private String encoding;
    private Boolean forceEncoding = false;
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
//		String requestEncoding = req.getCharacterEncoding();
//		String responseEncoding = resp.getContentType();
//		//设置请求的字符编码
//		if(!hasLength(encoding) && (requestEncoding == null || forceEncoding)) {
//			req.setCharacterEncoding("UTF-8");
//		}
//		//响应的字符编码
//		if(responseEncoding == null && forceEncoding) {
//			resp.setContentType("text/html;charset=utf-8");
//		}
//    	chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
//		this.encoding = config.getInitParameter("encoding");
//		this.forceEncoding = Boolean.valueOf(config.getInitParameter("force"));
	}
    private boolean hasLength(String str) {
    	return str != null && !"".equals(str.trim());
    }
}
