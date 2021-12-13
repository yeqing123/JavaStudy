package com.yeqing.moneylog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.service.IMoneyLogService;

//处理写礼支出相关请求的控制器
@Controller
@RequestMapping("/moneylog")
public class MoneyLogController {

	@Autowired
	private IMoneyLogService moneyLogService;
	
	//查询所有写礼支出信息列表
	@RequestMapping("/list")
	public String list(Model model) {
		List<MoneyLog> all = moneyLogService.listAll();
		model.addAttribute("all", all);
		return "moneylog/list";
	}
	//添加新的支出信息，后者修改已由的支出信息
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(MoneyLog ml) {
		if(ml.getId() != null) {
			moneyLogService.update(ml);
		} else {
			moneyLogService.save(ml);
		}
		return "redirect:/moneylog/list";
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
		return "redirect:/moneylog/list";
	}
}
