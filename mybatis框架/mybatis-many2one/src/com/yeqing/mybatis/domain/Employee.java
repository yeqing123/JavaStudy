package com.yeqing.mybatis.domain;


import lombok.Getter;
import lombok.Setter;

//封装了t_employee表一条数据
@Setter@Getter
public class Employee {
	private Long id;  //员工ID
	private String name; //姓名
	private Department dept;  //员工所属部门

	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}
