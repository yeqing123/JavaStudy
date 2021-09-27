package com.yeqing.pms.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

//封装了查询条件的基本类型，它是一个抽象类，不能被直接创建，只能用作父类来继承
public abstract class BaseQuery implements IQuery {
    private List<String> conditions = new ArrayList<>();
    private List<Object> parameters = new ArrayList<>();
    @Setter@Getter
    private Integer currentPage = 1;  //用户希望跳转到的页面，默认为第一页
    @Setter@Getter
    private Integer pageSize = 5;     //每页的有多少条数据，默认为5
    private boolean isBuild = false;  //用于判断是否已经调用customizedQuery方法创建了条件，false表示还没有，true表示已经创建。

    //类似一个初始化操作，它判断isBuild，然后决定是否调用customizedQuery方法
    private void init() {
    	if(!isBuild) {  //如果isBuild为false，就说明还没有创建条件
    		this.customizedQuery();
    		isBuild = true;
    	}
    }
    //一个抽象方法，留给子类根据自己的需要去覆写，用于创建查询条件
    protected abstract void customizedQuery();
    
    //用于将查询条件填充到conditions和parameters两个集合中去
    protected void addQuery(String condition, Object... params) {
    	this.conditions.add(condition);
    	this.parameters.addAll(Arrays.asList(params));
    }
    
    public List<Object> getParameters() {
		return this.parameters;
	}
    //从两个集合中获取元素，创建SQL语句的条件部分
	public String getQuery() {
		this.init(); //创建条件
		if(conditions.size() ==0) {  //如果集合为空，就说明没有任何查询条件，因此返回一个空字符
			return "";
		}
		StringBuilder sql = new StringBuilder(80);
		return sql.append(" WHERE ").append(StringUtils.join(conditions, " AND ")).toString();
	}

}
