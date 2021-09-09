package com.yeqin.upload;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

//处理文件上传的请求
public class UploadServlet_bak3_文件的类型约束 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ALLOWED_IMAGE_TYPE = "jpg;jpeg;png;gif";
	
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
				String fieldName = item.getFieldName();
				if(item.isFormField()) {
					String value = item.getString();
					System.out.println(fieldName + "-" + value);
				} else {
					//获取上传文件的MIME类型
					String mimeType = super.getServletContext().getMimeType(item.getName());
					System.out.println(mimeType);
					//------------------------------------------------------
					//提取文件后缀，文件类型约束
					String[] arr = ALLOWED_IMAGE_TYPE.split(";"); //以;将字符串分割为数组
					String ext = FilenameUtils.getExtension(item.getName());
					if(Arrays.asList(arr).contains(ext)) { //判断是否为图片类型
						System.out.println(fieldName + "-" + FilenameUtils.getName(item.getName()));
						String path = super.getServletContext().getRealPath("/upload");
						String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
						item.write(new File(path, fileName));
					}else {
						req.setAttribute("errorMsg", "亲，只能上传图片！");
						req.getRequestDispatcher("/input.jsp").forward(req, resp);
					}
					//------------------------------------------------------
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
