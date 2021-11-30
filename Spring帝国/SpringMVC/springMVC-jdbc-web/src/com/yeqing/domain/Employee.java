package com.yeqing.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//封装了数据库springmvcdemo中表employee中的数据
@Data
public class Employee {
	private Long id;
	private String username;
	private Integer age;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date hiredate;
}
