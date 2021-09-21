package com.yeqin.pims.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class QueryObject {
	private List<Object> parameters = new ArrayList<>();
	private List<String> conditions = new ArrayList<>();
	private boolean isBuild = false;  //判断是否已经创建了条件，false表示没有创建，true表示已经创建过了
	
	private void init() {
		if(!isBuild) {
			this.customizedQuery();
			isBuild = true;
		}
	}
	//一个空方法，留给子类根据自己的需要去覆写
	protected void customizedQuery() {}
	
    protected void addQuery(String condition, Object... params) {
    	conditions.add(condition);
    	parameters.addAll(Arrays.asList(params));
    }
	
	public List<Object> getParameters() {
		return parameters;
	}
	public String getQuery() {
		StringBuilder sql = new StringBuilder(80);
		this.init();
		if(conditions.size() == 0) {
			return "";
		}
		return sql.append(" WHERE ").append(StringUtils.join(conditions, " AND ")).toString();
	}
}
