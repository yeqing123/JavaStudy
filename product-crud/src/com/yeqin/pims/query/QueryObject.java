package com.yeqin.pims.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

//封装了高级查询和分页查询的通用条件信息的抽象类
public abstract class QueryObject implements IQuery {
	@Getter@Setter
	private Integer currentPage = 1;  //用户输入的当前页
	@Getter@Setter
	private Integer pageSize = 5;  //用户输入的每页显示的数据长度
    private List<String> sqlFragments = new ArrayList<>();  //包含SQL语句中所有的条件片段
    private List<Object> parameters = new ArrayList<>();    //包含所有的条件参数
    private boolean isBuild = false;  //用于判断条件是否已经构建完成，false表示没有构建，true表示已完成构建
    
    private void init() {
    	if(!isBuild) {
    		this.customizedConditions();
    		isBuild = true;
    	}
    }
    //一个抽象方法，留给子类根据自己的实际情况去实现
    abstract protected void customizedConditions();
    
    protected void addConditions(String sqlFragment, Object... params) {
    	sqlFragments.add(sqlFragment);
    	parameters.addAll(Arrays.asList(params));
    }
    
    //返回以WHERE打头的SQL查询条件
    public String getConditions() {
    	StringBuilder sql = new StringBuilder(80);
    	this.init();
    	if(sqlFragments.size() == 0) {
    		return "";
    	}
    	return sql.append(" WHERE ").append(StringUtils.join(sqlFragments, " AND ")).toString();
    }
    
    public List<Object> getParameters() {
    	return this.parameters;
    }
}
