package com.yeqing._08_calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//设置服务器请求与响应的字符编码，以及网页内容的类型
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		//=================================================
		//2.获取浏览器中输入的数字
		String sNum1 = req.getParameter("num1");
		String sNum2 = req.getParameter("num2");
		String operate = req.getParameter("operate");
		String result = "";
		if(hasLength(sNum1) && hasLength(sNum2)) {
			Integer num1 = Integer.valueOf(sNum1);
	        Integer num2 = Integer.valueOf(sNum2);
	        switch (operate) {
				case "+":
					result = num1 + num2 + "";
					break;
				case "-":
					result = num1 - num2 + "";
					break;
				case "*":
					result = num1 * num2 + "";
					break;
				case "/":
					result = num1 / num2 + "";
					break;
				default:
					break;
	        }
		}
		//1.在浏览器中打印出一个计算器界面
		PrintWriter out = resp.getWriter();
		out.write("<form action='/servlet/cal' method='post'>");
		out.write("<input type='number' name='num1' value='"+sNum1+"'/>");
		out.write("<input type='number' name='num2'value='"+sNum2+"'/>");
		out.write("<select name='operate'>");
		out.write("<option >+</option>");
		out.write("<option >-</option>");
		out.write("<option >*</option>");
		out.write("<option >/</option>");
		out.write("<input type='submit' value=' = '/>");
		out.write("<input type='number' name='result' value='"+result+"'/>");
		out.write("</form>");
	
	}
	// 判断字符串是否有长度
	private boolean hasLength(String str) {
		return str != null && !"".equals(str);
	}
}
