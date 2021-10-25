package com.yeqing.mybatis.domain;

import lombok.Data;

//封装了department表中的每行信息
@Data
public class Department {
	private Long id;
	private String name;
}
