package com.yeqing.mybatis.domain;

import lombok.Data;

//封装了teacher表中的老师信息
@Data
public class Teacher implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;   //老师ID
	private String name;  //老师姓名
}
