package com.yeqing.eims.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yeqing.eims.util.handler.IResultSetHandler;

//执行数据库操作的基本流程（贾琏欲执事）的模版类
public class JdbcTemplate {
    
	public static void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil_druid.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			//设置预处理数据
			for (int index = 0; index < params.length; index++) {
				ps.setObject(index+1, params[index]);
			}
			ps.executeUpdate();  //执
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  //事
			JdbcUtil_druid.close(conn, ps, null);
		}
	}
	
	public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil_druid.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			//设置预处理数据
			for (int index = 0; index < params.length; index++) {
				ps.setObject(index+1, params[index]);
			}
			rs = ps.executeQuery();  //执
			//处理结果集
			return handler.handler(rs); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  //事
			JdbcUtil_druid.close(conn, ps, null);
		}
	    throw new RuntimeException("程序出错了！");
	}
}
