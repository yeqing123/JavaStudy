package com.yeqin.pims.query;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

//封装了针对商品信息的查询条件
@Getter@Setter
public class ProductQueryObject extends QueryObject {
    private String name;  //商品名称
    private String note;  //商品简介
    private BigDecimal minprice;  //最低价格
    private BigDecimal maxprice;  //最高价格
    private Long dir = -1L;       //商品分类编号，默认-1表示所有分类
    private String keyword;       //关键字
    //实现父类中的抽象方法customizedConditions
    protected void customizedConditions() {
    	if(StringUtils.isNotBlank(name)) {
    		super.addConditions("name LIKE ?", "%"+name+"%");
    	}
    	if(StringUtils.isNotBlank(note)) {
    		super.addConditions("note LIKE ?", "%"+note+"%");
    	}
    	if(minprice != null) {
    		super.addConditions("price >= ?", minprice);
    	}
    	if(maxprice != null) {
    		super.addConditions("price <= ?", maxprice);
    	}
    	if(dir != -1) {
    		super.addConditions("dir = ?", dir);
    	}
    	if(StringUtils.isNotBlank(keyword)) {
    		super.addConditions("(name LIKE ? OR note LIKE ?)", "%"+name+"%", "%"+note+"%");
    	}
    }
}
