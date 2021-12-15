package com.yeqing.moneylog.query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//封装了高级查询和分页查询的的基本条件
@Data
public class QueryObject {
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private Integer minMoney;
	private Integer maxMoney;
	private String keyword;
	
	private Integer currentPage = 1;  //用户想好跳转的页面，默认为首页
	private Integer pageSize = 5;   //每页最多显示多少数据，默认为5
	private Integer prePage;  //上页
	private Integer nextPage; //下页
	private Integer pageTotal; //末页
	private Integer pageCount; //数据总数
	
	private List<Integer> pageList = Arrays.asList(new Integer[]{3,5,7,10});  //页面上可供选择的pageSize属性的值
	
	public String getName() {
		return emtpy2null(name);
	}
	public String getKeyword() {
		return emtpy2null(keyword);
	}
	//如果参数为空字符串，就返回null
	private String emtpy2null(String str) {
		return hasLength(str) ? str : null;
	}
	//判断字符串是否为空
	private boolean hasLength(String str) {
		return str!=null && !"".equals(str.trim());
	}
}
