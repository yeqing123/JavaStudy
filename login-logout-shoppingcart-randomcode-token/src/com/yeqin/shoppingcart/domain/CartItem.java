package com.yeqin.shoppingcart.domain;

import java.math.BigDecimal;

import lombok.Data;

//保存了购物车中的商品信息
@Data
public class CartItem {
	private Long id;   //商品ID
	private String name;  //商品名称
	private BigDecimal price;  //商品价格
	private Integer num;  //商品数量
	public CartItem() {}
	public CartItem(Long id, String name, BigDecimal price, Integer num) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.num = num;
	}
}
