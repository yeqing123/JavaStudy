package com.yeqing.mybatis.query;

import lombok.Getter;
import lombok.Setter;

//封装了通用的查询条件信息
@Getter@Setter
public class QueryObject {
	private Integer currentPage = 1;  //用户想要去的页面，默认为1
	private Integer pageSize = 3;  //每页最多有几条数据，默认为3
	
	//获取每页查询的起始位置：LIMIT start,pageSize
	public Integer getStart() {
		return (currentPage - 1) * pageSize;
	}

	// 如果参数为空字符串，就返回null
	protected String empty2null(String str) {
		return hasLength(str) ? str : null;
	}

	// 判断字符串是否为空
	protected boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}
