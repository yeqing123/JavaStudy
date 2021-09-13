package com.yeqin.repeatsubmit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理表单的重复提交问题
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	resp.setContentType("text/html;charset=utf-8");
    	//获取session中的口令
    	String tokenInSession = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");  
    	//获取表单中的口令
    	String tokenInRequest = req.getParameter("token");  
    	//验证口令是否正确
    	if(tokenInRequest.equals(tokenInSession)) {  
    		req.getSession().removeAttribute("TOKEN_IN_SESSION"); //立即销毁Session中的口令，避免重复提交
    		PrintWriter out = resp.getWriter();
    		String money = req.getParameter("money");
	    	System.out.println("转出" + money + "元");
	    	out.print("转账成功！");
    	}else {
    		System.out.println("手贱...");
    	}
    }
}
