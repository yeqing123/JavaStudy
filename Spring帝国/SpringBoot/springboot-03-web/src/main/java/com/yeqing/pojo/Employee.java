package com.yeqing.pojo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
	private Long id;
	private String name;
	private String email;
	private Integer sex;  // 0：女        1：男
	private Long deptId;
	private Date birth;
	
	public Employee(Long id, String name, String email, Integer sex, Long deptId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.deptId = deptId;
		this.birth = new Date();
	}
}
