package com.yeqin.pims.query;

import java.util.List;

//封装查询条件的通用接口，用于规范实现类
public interface IQuery {

	List<Object> getParameters();

	Integer getCurrentPage();

	Integer getPageSize();

	String getConditions();
	
}
