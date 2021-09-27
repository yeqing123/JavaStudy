package com.yeqing.pms.query;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

//封装了查询商品分类信息的条件
@Getter@Setter
public class ProductDirQueryObject extends BaseQuery {
	private String dirName;
	private String note;
	private Float minProfitMargin;
	private Float maxProfitMargin;
	
	//根据商品分类信息，覆写customizedQuery方法
	protected void customizedQuery() {
	    if(StringUtils.isNotBlank(dirName)) {
	    	super.addQuery("dirName LIKE ?", "%"+dirName+"%");
	    }
	    if(StringUtils.isNotBlank(note)) {
	    	super.addQuery("note LIKE ?", "%"+note+"%");
	    }
	    if(minProfitMargin != null) {
	    	super.addQuery("profitMargin >= ?", minProfitMargin);
	    }
	    if(maxProfitMargin != null) {
	    	super.addQuery("profitMargin <= ?", maxProfitMargin);
	    }
	}
}
