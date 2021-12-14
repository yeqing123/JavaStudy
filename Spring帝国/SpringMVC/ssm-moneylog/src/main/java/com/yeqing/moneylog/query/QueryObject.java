package com.yeqing.moneylog.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//封装了高级查询的各类查询条件
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
}
