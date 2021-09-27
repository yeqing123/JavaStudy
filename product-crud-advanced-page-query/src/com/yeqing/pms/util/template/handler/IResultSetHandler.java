package com.yeqing.pms.util.template.handler;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
	
    T handler(ResultSet rs) throws Exception;
}
