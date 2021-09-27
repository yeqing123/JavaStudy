package com.yeqing.pms.util.hibernate;

import lombok.Getter;

//封装了数据库表中主键列的名称和值
@Getter
public class PrimaryColumn {
	private String name;  //主键的列名
    private Long value;   //主键的值
    
    public PrimaryColumn(String name, Long value) {
    	this.name = name;
    	this.value = value;
    }
}
