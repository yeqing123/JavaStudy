package com.yeqing.moneylog.web.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.query.QueryObject;
import com.yeqing.moneylog.service.IMoneyLogService;

//处理写礼支出相关请求的控制器
@Controller
@RequestMapping("/moneylog")
public class MoneyLogController {

	@Autowired
	private IMoneyLogService moneyLogService;
	@Autowired
	private ServletContext servletContext;
	
	//添加新的支出信息，后者修改已由的支出信息
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(MoneyLog ml, Model model) {
		if(ml.getId() != null) {
			moneyLogService.update(ml);
		} else {
			moneyLogService.save(ml);
		}
		return "moneylog/query";
	}
	//处理用户发出的编辑或新增请求
	@RequestMapping("/input")
	public String input(Long id, Model model) {
		if(id != null) {
		    MoneyLog ml = moneyLogService.get(id);
		    model.addAttribute("moneylog", ml);
		}
		return "moneylog/input";
	}
	//处理删除一条支出信息的请求
	@RequestMapping("/delete")
	public String delete(Long id) {
		moneyLogService.delete(id);
		return "moneylog/list";
	}
	//处理高级查询请求
	@RequestMapping("/query")
	public String query(String cmd, QueryObject qo, Model model) {
		//首先判断查询条件中的字符串是否为空字符串，如果为空字符串就设置为null
		if(isEmptyString(qo.getName())) {
			qo.setName(null);
		}
		if(isEmptyString(qo.getKeyword())) {
			qo.setKeyword(null);
		}
		System.out.println(qo);
		List<MoneyLog> list = moneyLogService.query(qo);
		model.addAttribute("list", list);
		servletContext.setAttribute("qo", qo);
		return "moneylog/list";
	}
	//判断字符串是否为空字符串
	private boolean isEmptyString(String str) {
		return str != null && str.trim().isEmpty();
	}
}
