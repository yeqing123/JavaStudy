package com.yeqing.web.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yeqing.domain.User;

@Controller
public class UploadFileController {
	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/upload")
	public ModelAndView save(User u, MultipartFile pic) throws Exception {
		System.out.println(u);
		String saveDir = servletContext.getRealPath("/upload");
		String fileName = pic.getOriginalFilename();
		System.out.println(Paths.get(saveDir, fileName).toString());
		Files.copy(pic.getInputStream(), Paths.get(saveDir, fileName));
		return null;
	}

}
