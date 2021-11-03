package com.yeqing.mybatis.product.domain;

import java.math.BigDecimal;

import lombok.Data;

//封装了jdbcdemo数据库中product表的信息
@Data
public class Product {
	private Long pid;     //商品的ID
	private String name; //商品名称
	private String note; //商品简介
	private BigDecimal price; //商品单价
	private Integer count; //商品库存
	private Long subDir; //商品所属分类
}
