package com.yeqing.vo;

import java.util.List;

import lombok.Data;

//封装一个List集合，该集合用于保存从一个请求参数中传入多个值
@Data
public class FormBean {
	private List<Long> ids;
}
