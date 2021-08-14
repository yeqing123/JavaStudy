package com.yeqing.exercise.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yeqing.exercise.handler.IResultSetHandler;

// 数据库操作的模板工具类
public class JdbcTemplate {
    // 用于DML（增删改）操作的模版方法
	public static void update(String sql, Object... params) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
        	conn = JdbcUtil.getConn(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			ps.executeUpdate(); //执
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null); //事
		}
    }
    // 用于DQL（查询）操作的模版方法
	public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
		Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
        	conn = JdbcUtil.getConn(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery(); //执
			return handler.handler(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null); //事
		}
    	throw new RuntimeException("查询出错");  // 一般不会执行到这一步，否则说明出错了
	}
}
