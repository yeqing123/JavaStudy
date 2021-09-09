package com.yeqin.upload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理文件上传的请求
public class UploadServlet_bak4_使用自定义工具类FileUtil来处理上传文件 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	try {
    		FileUtil.upload(req, null, null);
    	}catch(Exception e) {
    		req.setAttribute("errorMsg", e.getMessage());
    		req.getRequestDispatcher("/input.jsp").forward(req, resp);
    		
    	}
    }
}
