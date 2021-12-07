package com.yeqing.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;



//封装了数据库springmvcdemo中表employee中的数据
@Setter@Getter
public class Employee {
	private Long id;
	@NotNull(message = "用户名不能为空")
	@Size(min = 6, max = 12, message = "用户名长度在6位到12位之间")
	private String username;
	@NotNull(message = "年龄不能为空")
	@Min(value = 18, message = "年龄最小为18")
	@Max(value = 60, message = "年龄最大为60")
	private Integer age;
	@NotNull(message = "雇佣日期不能为空")
	private Date hiredate;

	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", age=" + age + ", hiredate=" + hiredate.toLocaleString() + "]";
	}
}
