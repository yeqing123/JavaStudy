package com.yeqing.di_setter;

import java.math.BigDecimal;

public class Employee1 {
	private String username;
	private Integer age;
	private BigDecimal salary;
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public String toString() {
		return "Employee1 [username=" + username + ", age=" + age + ", salary=" + salary + "]";
	}
	
}
