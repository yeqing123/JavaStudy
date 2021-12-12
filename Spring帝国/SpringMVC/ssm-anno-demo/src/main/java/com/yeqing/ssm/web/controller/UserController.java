package com.yeqing.ssm.web.controller;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.service.IUserService;

//处理User对象请求的控制器
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	@Autowired
	private ServletContext servletContext;

	// 请求路径：http://localhost:port/user/list
	@RequestMapping("/list")
	public String listAll(Model m) {
		m.addAttribute("list", service.listAll());
		return "user/list";
	}

	@RequestMapping("/delete")
	public String delete(Long id) throws Exception {
		deleteHeadImg(id);
		service.delete(id);
		return "redirect:/user/list";
	}

	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(User u, MultipartFile img) throws Exception {
		if (u.getId() == null) { // save
			saveHeadImg(u, img);
			service.save(u);
		} else { // update
			updateHeadImg(u, img);
			service.update(u);
		}
		return "redirect:/user/list";
	}

	@RequestMapping("/input")
	public String input(Long id, Model m) {
		if (id != null) { // update
			m.addAttribute("u", service.get(id));
		}
		return "user/input";
	}

	// 按照16:9的大小修改图片，并保存到服务器的指定位置
	private void saveHeadImg(User u, MultipartFile img) throws Exception {
		String saveDir = servletContext.getRealPath("/images");
		String uuid = UUID.randomUUID().toString();
		String ex = FilenameUtils.getExtension(img.getOriginalFilename()); // 获得上传文件的扩展名称
		String fileName = uuid + "." + ex;
		// 上传图片时对图片按照16:9的比例压缩240*135
		// 读取图片
		BufferedInputStream in = new BufferedInputStream(img.getInputStream());
		// 字节流转图片对象
		Image bi = ImageIO.read(in);
		// 构建图片流 按照16:9的比例 设置宽高 240*135
		// 宽240 高135 我这里直接写死了 也可以写成参数形式的。
		BufferedImage tag = new BufferedImage(240, 135, BufferedImage.TYPE_INT_RGB);
		// 绘制改变尺寸后的图
		tag.getGraphics().drawImage(bi, 0, 0, 240, 135, null);
		// 输出流
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveDir + File.separator + fileName));
		ImageIO.write(tag, ex, out);
		in.close();
		out.close();
		u.setHeadImg("/images/" + fileName);
	}

	// 删除指定用户的头像
	private void deleteHeadImg(Long id) throws Exception {
		User u = service.get(id);
		if (u != null) {
			String imgName = u.getHeadImg();
			if (imgName != null) { // 要先判断一下，否则如果用户没有上传头像，执行下面的语句就会出错
				Path imgPath = Paths.get(servletContext.getRealPath(imgName));
				Files.delete(imgPath);
			}
		}
	}

	// 修改指定用户的头像
	private void updateHeadImg(User u, MultipartFile img) throws Exception {
		// 注意，一定要先删除原来的头像，再保存新的头像，否则会出错
		deleteHeadImg(u.getId());
		saveHeadImg(u, img);
	}
}
