package com.yeqin.pims.util.template.handler;

import java.sql.ResultSet;

//结果集处理器
public interface IResultSetHandler<T> {
    /**
     * 处理结果结果集，返回一个JavaBean对象或一个List集合
     * @param rs
     * @return
     */
	T handler(ResultSet rs) throws Exception;
}
