package com.yeqing.mybatis.hello.domain;

import java.math.BigDecimal;

import lombok.Data;

//封装了employee表中的数据
@Data
public class Employee {
	private Long id;
	private String name;
	private String sn;
	private BigDecimal salary;
	private Long deptId;
}
