package com.yeqing.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

//封装了数据库springmvcdemo中表employee中的数据
@Setter@Getter
public class Employee {
	private Long id;
	private String username;
	private Integer age;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hiredate;

	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", age=" + age + ", hiredate=" + hiredate.toLocaleString() + "]";
	}
}
