package com.yeqin.pims.query;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

//封装了查询条件
@Getter
@Setter
public class ProductQueryObject extends QueryObject {
	private String name;
	private String note;
	private BigDecimal minprice;
	private BigDecimal maxprice;
	private Long dir = -1L;
	private String keyword;
	
	//覆写父类中的customizedQuery
	public void customizedQuery() {
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
