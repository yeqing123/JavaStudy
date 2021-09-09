package com.yeqin.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

//处理文件上传的请求
public class UploadServlet_bak1_上传文件的处理 extends HttpServlet {

	private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	// 创建DiskFileItemFactory对象
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	// 配置一个仓库 (以确保使用一个安全的临时位置)
    	ServletContext servletContext = this.getServletConfig().getServletContext();
    	File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    	factory.setRepository(repository);
    	// 创建一个新上传的文件处理器
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	// 解析request
    	try {
    		
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem item : items) {
				String name = item.getFieldName();
				if(item.isFormField()) {
					String value = item.getString();
					System.out.println(name + "-" + value);
				}else {
					//使用FilenameUtils工具类，可以只保留文件的简单名称
					System.out.println(name + "-" + FilenameUtils.getName(item.getName()));
					//为了避免使用相同的文件名重复保存，我们可以使用UUID生成一个随机的文件名。
					String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
                    //获取Web应用中upload文件夹的真实路径
					String path = super.getServletContext().getRealPath("/upload");
					System.out.println(path);
					//最好将文件保存在项目应用中，否则今后服务器无法直接访问该文件
					item.write(new File(path, fileName));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
