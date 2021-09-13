package com.yeqin.pims.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yeqin.pims.util.handler.IResultSetHandler;

//完成数据库操作的模板类（永远记住基本流程：贾琏欲执事）
public class JdbcTemplate {
    /**
     * 完成添加信息和修改指定信息的操作
     * @param sql 操作数据库的SQL语句
     * @param params 完成操作所必须的参数
     */
	public static int update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
		conn = JdbcUtil_druid.getConnection(); //贾琏
		ps = conn.prepareStatement(sql);  //欲
		//设置预处理语句中的参数
		for (int index = 0; index < params.length; index++) {
			ps.setObject(index+1, params[index]);
		}
		return ps.executeUpdate(); //执
		}catch(Exception e) {
			e.printStackTrace();
		}finally {  //事
			JdbcUtil_druid.close(conn, ps, null);
		}
		return 0;
	}
	/**
	 * 完成查询信息的操作
	 * @param <T> 泛指用于封装信息的任意类型
	 * @param sql 操作数据库的SQL语句
	 * @param handler 结果集处理器
	 * @param params 完成操作所必须的参数
	 * @return 返回一个封装对象或者包含了多个对象的List集合
	 */
	public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil_druid.getConnection(); //贾琏
			ps = conn.prepareStatement(sql);  //欲
			//设置预处理语句中的参数
			for (int index = 0; index < params.length; index++) {
				ps.setObject(index+1, params[index]);
			}
			rs = ps.executeQuery(); //执
			return handler.handler(rs); //处理结果集
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_druid.close(conn, ps, rs); //事
		}
		return null;
	}
}
