package com.yeqing._06_login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String passworld = req.getParameter("passworld");
		System.out.println(username + ", " + passworld);
	}

}
