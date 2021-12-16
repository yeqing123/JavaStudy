package com.yeqing.moneylog.query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//封装了高级查询的查询条件
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
