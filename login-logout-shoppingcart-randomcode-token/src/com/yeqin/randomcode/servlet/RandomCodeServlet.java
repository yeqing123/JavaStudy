package com.yeqin.randomcode.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yeqin.randomcode.RandomCode;

//处理生成一个验证码图片的请求，响应到浏览器中
@WebServlet("/randomCode")
public class RandomCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	RandomCode randomcode = new RandomCode();  //创建一个RandomCode对象
    	req.getSession().setAttribute("RANDOMCODE_IN_SESSION", randomcode);
    	OutputStream out = resp.getOutputStream();  //获取响应的输出流
    	RandomCode.output(randomcode.getImage(), out);  //创建一个验证码图片保存到响应输出流中
    }
}
