package com.yeqing.jdbc.handler;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
    T handler(ResultSet rs);
}
