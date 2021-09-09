package com.yeqin.upload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理文件上传的请求
@WebServlet("/upload")
public class UploadServlet_bak5_使用Map封装请求信息 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	try {
    		User user = new User();
    		Map<String, String> fieldMap = new HashMap<>(); //保存请求中的字段信息
    		Map<String, CFile> binaryMap = new HashMap<>(); //保存上传文件的信息
    		FileUtil.upload(req, fieldMap, binaryMap);  //处理上传请求，并将请求中的信息转换成Map集合
    		user.setUsername(fieldMap.get("username"));
    		user.setEmail(fieldMap.get("email"));
    		user.setImageInfo(binaryMap.get("headImg"));
    		System.out.println(user);
    		req.setAttribute("u", user);
    		req.getRequestDispatcher("/show.jsp").forward(req, resp);
    	}catch(Exception e) {
    		req.setAttribute("errorMsg", e.getMessage());
    		req.getRequestDispatcher("/input.jsp").forward(req, resp);
    		
    	}
    }
}
