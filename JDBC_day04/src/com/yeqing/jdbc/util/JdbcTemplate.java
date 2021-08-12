package com.yeqing.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yeqing.jdbc.handler.IResultSetHandler;


// 数据库操作的模版工具类
public class JdbcTemplate {
    
	//用于“增删改”操作的模版
    public static void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			for(int index = 0; index < params.length; index++) {
				ps.setObject(index + 1, params[index]);
			}
			ps.executeUpdate();//执
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, null);//事
		}
    }
    // 用于查询操作的模板
    public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection(); //贾琏
			ps = conn.prepareStatement(sql); //欲
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery(); //执，无参数
			//处理结果集，并返回一个domain对象的集合
			return (T)handler.handler(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);//事
		}
		throw new RuntimeException("查询出错");  // 程序一般不会走到这一步，加入到这里则直接抛出一个自定义异常
	}
}
