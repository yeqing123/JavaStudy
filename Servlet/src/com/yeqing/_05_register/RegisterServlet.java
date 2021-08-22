package com.yeqing._05_register;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String intro = req.getParameter("intro");
		String[] hobbys = req.getParameterValues("hobby");
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		System.out.println("gender:" + gender);
		System.out.println("intro:" + intro);
		System.out.println("hobby:");
		for (String hobby : hobbys) {
			System.out.println(hobby);
		}
	}

}
