
package com.yeqing.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
//封装了用户数据的JavaBean
public class User {
	private Long id;
	private String username;
	private Integer age;
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //当Date类型定义在JavaBean中时，需要DateTimeFormat注解在定义日期时间的格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date hiredate;
}
