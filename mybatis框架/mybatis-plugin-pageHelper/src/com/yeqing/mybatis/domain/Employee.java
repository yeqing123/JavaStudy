package com.yeqing.mybatis.domain;

import java.math.BigDecimal;

import lombok.Data;

//封装了employee表一条数据
@Data
public class Employee {
	private Long id;  //员工ID
	private String name; //姓名
	private String sn;  //编号
	private BigDecimal salary;  //工资
	private Long deptId;  //所属部门
}
