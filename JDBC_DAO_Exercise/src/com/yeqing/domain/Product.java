package com.yeqing.domain;



import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Product {
	private Long pid;      // 货品的id
    private String name;   // 货品的名称
    private String note;   // 货品的简介
    private Double price;  // 货品的单价
    private Integer count;      // 货品的当前库存
    private Integer types_id;   // 货品的分类id
    private Integer subtypes_id; // 货品的子分类id
    
    @Override
    public String toString() {
    	return "Product [pid=" + pid + ", name=" + name + ", note=" + note + ", price=" + price + ", count=" + count
    			+ ", types_id=" + types_id + ", subtypes_id=" + subtypes_id + "]";
    }
}
