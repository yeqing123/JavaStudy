package com.yeqing.mybatis.domain;

import lombok.Data;

//封装了t_employee表中的员工信息
@Data
public class Employee {
	private Long id;    //员工的ID
	private String name;  //员工的姓名
	private Long deptId;  //员工所属部门的ID
}
