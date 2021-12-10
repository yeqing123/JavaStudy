package com.yeqing.ssm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;
	private String name;
	private Integer age;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bornDate;
	private String headImg;
}
