package com.yeqin.pims.query;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductDirQueryObject extends QueryObject {
    private String dirName;  //商品分类名称
    private String note;  //商品分类简介
    //实现父类中的抽象方法    
	protected void customizedConditions() {
		if(StringUtils.isNotBlank(dirName)) {
			super.addConditions("dirName LIKE ?", "%"+dirName+"%");
		}
		if(StringUtils.isNotBlank(note)) {
			super.addConditions("note LIKE ?", "%"+note+"%");
		}
	}
}
