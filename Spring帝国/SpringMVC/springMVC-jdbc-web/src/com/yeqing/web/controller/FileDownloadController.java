package com.yeqing.web.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

//文件下载控制器
@Controller
public class FileDownloadController {

	//传统方式
	//@RequestMapping("/download")
	public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get请求中文乱码处理
		fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		response.setContentType("application/x-msdownload");
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("IE")) {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		}else {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
		}
		String dir = request.getServletContext().getRealPath("/WEB-INF/down");
		Files.copy(Paths.get(dir, fileName), response.getOutputStream());
	}
	
	//SpringMVC方式
	@Autowired
	private ServletContext servletContext;
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestHeader("User-Agent")String userAgent, String fileName) throws Exception {
		//ileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		String dir = servletContext.getRealPath("/WEB-INF/down");
		HttpHeaders headers = new HttpHeaders();
		//设置下载显示文件名称
		if(userAgent.contains("IE")) {
			headers.setContentDispositionFormData("attachment",URLEncoder.encode(fileName, "utf-8"));
		}else {
			headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("ISO-8859-1"), "UTF-8"));
		}
		//保存文件
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<>(FileUtils.readFileToByteArray(new File(dir, fileName)), headers, HttpStatus.CREATED);
		
	}
}
