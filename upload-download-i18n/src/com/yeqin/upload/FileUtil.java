package com.yeqin.upload;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class FileUtil {
private static final String ALLOWED_IMAGE_TYPE = "jpg;jpeg;png;gif";
	
    public static void upload(HttpServletRequest req, Map<String, String> fieldMap, Map<String, CFile> binaryMap) {
    	// 创建DiskFileItemFactory对象
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	// 配置一个仓库 (以确保使用一个安全的临时位置)
    	ServletContext servletContext = req.getServletContext();
    	File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    	factory.setRepository(repository);
    	// 创建一个新上传的文件处理器
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	//设置单个文件大小不超过500KB
    	upload.setFileSizeMax(1024 * 500);
    	//设置该次请求的大小不超过2M
    	upload.setSizeMax(1024 * 1024 * 2);
    	// 解析request
    	try {
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem item : items) {
				String fieldName = item.getFieldName();
				if(item.isFormField()) {
					String value = item.getString();
					System.out.println(fieldName + "---" +value);
					fieldMap.put(fieldName, value);
				}else {
					//------------------------------------------------------
					//提取文件后缀，文件类型约束
					String[] arr = ALLOWED_IMAGE_TYPE.split(";"); //以;将字符串分割为数组
					String ext = FilenameUtils.getExtension(item.getName());
					if(Arrays.asList(arr).contains(ext)) { //判断是否为图片类型
						String fileName = FilenameUtils.getName(item.getName());
						System.out.println(fieldName + "---" + fileName);
						String path = req.getServletContext().getRealPath("/upload");
						String randomFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
						item.write(new File(path, randomFileName));
						CFile cf = new CFile();
						cf.setImageName(fileName);
						cf.setImageUrl(randomFileName);
						binaryMap.put(fieldName, cf);
					}else {
						throw new LogicException("亲，请上传正确格式的图片");
					}
					//------------------------------------------------------
				}
			}
    	}catch(SizeLimitExceededException e) {
    		throw new LogicException("亲，你该次请求太大了，请不要超过2M", e);
    	}catch(FileSizeLimitExceededException e) {
    		throw new LogicException("亲，你上传的文件太大了，请不要超过500KB", e);
        }catch(LogicException e) {
    		throw e;  //继续抛出异常给调用者（UploadServlet）
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
