package com.yeqin.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
	private List<CartItem> items = new ArrayList<>();
	
	//向购物车中添加商品
	public void save(CartItem newItem) {
		for (CartItem cartItem : items) {
			if(cartItem.getName().equals(newItem.getName())) {
				cartItem.setNum(cartItem.getNum() + newItem.getNum());
				return ;
			}
		}
		items.add(newItem);
	}
	//从购物车中删除指定的商品
	public void delete(Long id) {
		Iterator<CartItem> it = items.iterator();
		while(it.hasNext()) {
			CartItem item = it.next();
			if(item.getId().equals(id)) {
				it.remove();
				break;
			}
		}
	}
	
	//计算购物车中所有商品的总价格
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (CartItem cartItem : items) {
			totalPrice = totalPrice.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getNum())));
		}
		return totalPrice;
	}
	
	//返回购物车中的所有商品
	public List<CartItem> getItems() {
		return items;
	}
}
