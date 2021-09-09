package com.yeqin.download;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理资源下载请求
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//1:检查该用户是否具备下载资源的资格（比如：是否已登录或积分是否足够等等）
    	//2:获取被下载资源的名称
    	String fileName = req.getParameter("fileName");
    	fileName = new String(fileName.getBytes(), "UTF-8");
    	System.out.println(fileName);
    	//3:从服务器中找到被下载资源的绝对路径
    	String realPath = super.getServletContext().getRealPath("/WEB-INF/download/" + fileName);
    	//①：告诉浏览器不要直接打开文件，而是弹出下载框保存文件（因为IE浏览器会直接打开文件）
    	resp.setContentType("application/x-msdownload");
    	//②：设置下载文件的名称
    	String userAgent = req.getHeader("User-Agent"); //获取请求头中key为User-Agent的信息
    	if(userAgent!=null && !"".equals(userAgent)) {
    		if(userAgent.contains("Gecko")) {  //IE(根据自己的IE版本名称，进行判断)
    			fileName = URLEncoder.encode(fileName, "UTF-8");
    		}else {   //非IE
        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        	}
    	}
    	resp.setHeader("Content-Disposition", "attachment; filename=" +fileName);  //设置下载文件的名称
    	//4:将服务器磁盘中的文件拷贝到用户浏览器中
    	Files.copy(Paths.get(realPath), resp.getOutputStream());  //将指定路径下的文件拷贝到服务器响应的输出流中，最终即是拷贝到了浏览器中
    }
}
