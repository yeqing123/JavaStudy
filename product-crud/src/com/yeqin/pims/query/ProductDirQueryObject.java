package com.yeqin.pims.query;


import org.apache.commons.lang3.StringUtils;

public class ProductDirQueryObject extends QueryObject {
    private String dirName;
    private String note;
	
	//覆写父类中的customizedQuery方法
	public void customizedQuery() {
		if(StringUtils.isNotBlank(dirName)) {
			super.addQuery("dirName LIKE ?", "%"+dirName+"%");
		}
		if(StringUtils.isNotBlank(note)) {
			super.addQuery("note LIKE ?", "%"+note+"%");
		}
	}
}
