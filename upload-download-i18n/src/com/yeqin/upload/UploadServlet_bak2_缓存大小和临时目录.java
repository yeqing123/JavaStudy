package com.yeqin.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//处理文件上传的请求
public class UploadServlet_bak2_缓存大小和临时目录 extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	// 创建DiskFileItemFactory对象
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	//默认的上传文件缓存为10KB，我们也可以自行设置缓存大小
    	factory.setSizeThreshold(50 * 1024);
    	//重新设置临时目录的位置
    	factory.setRepository(new File("D:/Program Files (x86)/apache-tomcat-8.5.70/temp"));
    	// 创建一个新上传的文件处理器
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	// 解析request
    	try {
    		
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem item : items) {
				if(!item.isFormField()) {
					System.out.println(item.isInMemory());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
