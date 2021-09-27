package com.yeqing.pms.query;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

//查询商品信息的条件对象
@Getter@Setter
public class ProductQueryObject extends BaseQuery {
    private String name;  //商品名称
    private String note;  //商品简介
    private BigDecimal minprice;  //最低单价
    private BigDecimal maxprice;  //最高单价
    private Long dir = -1L;    //商品分类编号，默认为-1表示所有分类
    private String keyword;  //查询关键字
    
    //根据商品类型，覆写customizedQuery方，创建查询条件
	protected void customizedQuery() {
		if(StringUtils.isNotBlank(name)) {
			super.addQuery("name LIKE ?", "%"+name+"%");
		}
		if(StringUtils.isNotBlank(note)) {
			super.addQuery("note LIKE ?", "%"+note+"%");
		}
		if(minprice != null) {
			super.addQuery("price >= ?", minprice);
		}
		if(maxprice != null) {
			super.addQuery("price <= ?", maxprice);
		}
		if(dir != -1) {
			super.addQuery("dir = ?", dir);
		}
		if(StringUtils.isNotBlank(keyword)) {
			super.addQuery("(name LIKE ? OR note LIKE ?)", "%"+keyword+"%", "%"+keyword+"%");
		}
	}

}
