package com.yeqing.eims.util.handler;

import java.sql.ResultSet;

//结果集处理器
public interface IResultSetHandler<T> {
    /**
     * 处理结果集
     * @param rs 查询数据库后产生的结果集
     * @return 返回处理结果（如果查询的是指定信息，则返回一个封账了该信息的JavaBean对象；如果查询的是所有信息，就返回一个List集合）
     */
	T handler(ResultSet rs);
}
