package com.yeqing.mybatis.query;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

//封装了查询所需的条件
@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
	private String keyword; // 关键字，用于查询员工姓名或编号中包含关键字的数据
	private BigDecimal minsalary; // 最低工资
	private BigDecimal maxsalary; // 最高工资
	private Long deptId = -1L; // 所属部门，默认值为-1，表示所有部门
	
	// 如果关键字是一个空字符串（比如一串空格），就返回null
	public String getKeyword() {
		return empty2null(keyword);
	}

}
