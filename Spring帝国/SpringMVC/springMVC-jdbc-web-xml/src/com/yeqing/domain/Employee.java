package com.yeqing.domain;

import java.util.Date;

import lombok.Data;

//封装了数据库springmvcdemo中表employee中的数据
@Data
public class Employee {
	private Long id;
	private String username;
	private Integer age;
	private Date hiredate;
}
