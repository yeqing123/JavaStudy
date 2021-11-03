package com.yeqing.mybatis.product.query;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
	private Integer currentPage = 1;  //用户想好跳转的页面，默认为首页
	private Integer pageSize = 5;   //每页最多显示多少数据，默认为5
	private Integer prePage;  //上页
	private Integer nextPage; //下页
	private Integer pageTotal; //末页
	private Integer pageCount; //数据总数
	private String name;  //名称
	private String note;  //简介
	private BigDecimal minprice;  //最低价格
	private BigDecimal maxprice;  //最高价格
	private String keyword; //关键字
	private Long dir = -1L; //商品分类ID，默认为-1，表示所有分类
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
