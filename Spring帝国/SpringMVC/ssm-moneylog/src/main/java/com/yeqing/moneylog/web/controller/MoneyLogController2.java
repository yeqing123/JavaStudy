package com.yeqing.moneylog.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yeqing.moneylog.domain.MoneyLog;
import com.yeqing.moneylog.query.QueryObject;
import com.yeqing.moneylog.service.IMoneyLogService;

//实现高级查询+分页查询的控制器
@RequestMapping("/moneylog")
public class MoneyLogController2 implements Controller {
	
	@Autowired
	private IMoneyLogService service;

	//调用不同的方法，处理不同类型的请求
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cmd = request.getParameter("cmd");
		System.out.println("cmd: " + cmd);
		if("saveOrUpdate".equals(cmd)) {
			return saveOrUpdate(request);
		}
		if("delete".equals(cmd)) {
			return delete(request);
		}
		if("input".equals(cmd)) {
			return input(request);
		} else {
			return query(request);
		}
	}
	//处理用户新增或编辑请求，跳转到编辑页面
	private ModelAndView input(HttpServletRequest request) throws Exception {
		String idStr = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		if(idStr != null) {
			MoneyLog moneylog = service.get(Long.valueOf(idStr));
			mv.addObject("moneylog", moneylog);
		}
		mv.setViewName("moneylog/input");
		return mv;
	}
	//保存或修改信息
	private ModelAndView saveOrUpdate(HttpServletRequest request) throws Exception {
		MoneyLog ml = parametersToMoneyLog(request);
		if(ml.getId() == null) {
			service.save(ml);
		} else {
			service.update(ml);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/moneylog");  //注意：这里一定要用重定向
		return mv;
	}
	//处理删除信息的请求
	private ModelAndView delete(HttpServletRequest request) throws Exception {
		String idStr = request.getParameter("id");
		service.delete(Long.valueOf(idStr));
		return query(request);
	}
	//处理查询（包含高级+分页查询）请求
	private ModelAndView query(HttpServletRequest request) throws Exception {
		QueryObject qo = parametersToQueryObject(request);
		System.out.println("query() --> qo: " + qo);
		List<MoneyLog> list = service.query(qo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.addObject("qo", qo);
		mv.setViewName("moneylog/list2");
		return mv;
	}
	
	private boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
	//获取高级查询的参数，并封装成一个QueryObejct对象
	private QueryObject parametersToQueryObject(HttpServletRequest request) throws Exception {
		QueryObject qo = new QueryObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		String minMoneyStr = request.getParameter("minMoney");
		String maxMoneyStr = request.getParameter("maxMoney");
		String keyword = request.getParameter("keyword");
		if(hasLength(name)) {
			qo.setName(name);
		} else {
			qo.setName(null);
		}
		if(hasLength(keyword)) {
			qo.setKeyword(keyword);
		} else {
			qo.setKeyword(null);
		}
		if(hasLength(startDateStr)) {
			qo.setStartDate(sdf.parse(startDateStr));
		}else {
			qo.setStartDate(null);
		}
		if(hasLength(endDateStr)) {
			qo.setEndDate(sdf.parse(endDateStr));
		}else {
			qo.setEndDate(null);
		}
		if(hasLength(minMoneyStr)) {
			qo.setMinMoney(Integer.valueOf(minMoneyStr));
		}else {
			qo.setMinMoney(null);
		}
		if(hasLength(maxMoneyStr)) {
			qo.setMaxMoney(Integer.valueOf(maxMoneyStr));
		}else {
			qo.setMaxMoney(null);
		}
		return qo;
	}
	//获取input.jsp页面的参数，将它封装成一个MoneyLog对象
	private MoneyLog parametersToMoneyLog(HttpServletRequest request) throws Exception {
		MoneyLog ml = new MoneyLog();
		String name = request.getParameter("name");
		String actionDateStr = request.getParameter("actionDate");
		String locale = request.getParameter("locale");
		String event = request.getParameter("event");
		String moneyStr = request.getParameter("money");
		String idStr = request.getParameter("id");
		if(hasLength(idStr)) {
			ml.setId(Long.valueOf(idStr));
		} else {
			ml.setId(null);
		}
		if(hasLength(actionDateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ml.setActionDate(sdf.parse(actionDateStr));
		}else {
			ml.setActionDate(null);
		}
		if(hasLength(moneyStr)) {
			ml.setMoney(Integer.valueOf(moneyStr));
		}else {
			ml.setMoney(null);
		}
		ml.setEvent(event);
		ml.setLocale(locale);
		ml.setName(name);
		return ml;
	}
}
