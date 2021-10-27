package com.yeqing.mybatis.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//封装了表department中的部门信息
@Getter@Setter
public class Department {
	private Long id;  //部门的ID
	private String name;  //部门的名称
	private List<Employee> emps = new ArrayList<>();  //部门内的员工集合
    //为了验证延迟加载，覆写toString方法，以便单独打印emps属性
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
}
