package com.yeqing.moneylog.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//保存给亲戚朋友写礼凑份子的详细信息
public class MoneyLog {
	private Long id;   
	private String name;  //谁过事，给谁写的礼
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actionDate;    //写礼的日期
	private String locale; //地点，记不清了可以为空
	private String event;  //事由
	private Integer money; //写礼金额
}
