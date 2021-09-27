package com.yeqing.pms.util.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yeqing.pms.util.JdbcUtil;
import com.yeqing.pms.util.template.handler.IResultSetHandler;

//实现数据库操作的模板类（之所以称之为模板，是因为数据库的操作有固定的步骤：贾琏欲执事）
public class JdbcTemplate {
	//“增、珊、改”操作
	public static void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConn(); //贾琏
			ps = conn.prepareStatement(sql);  //欲
		    //设置预处理语句中的参数值
			for (int index = 0; index < params.length; index++) {
				ps.setObject(index+1, params[index]);
			}
			ps.executeUpdate();  //执
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, null);//事
		}
	}
	
	//查询符合条件的数据（返回的结果可能是一个单独的javabean对象，也可能是一个List集合）
	public static <T> T query(String sql, IResultSetHandler<T> handler, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			//设置预处理语句中的参数值
			for(int index = 0; index < params.length; index++) {
				ps.setObject(index+1, params[index]);
			}
			rs = ps.executeQuery();
			return handler.handler(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}
}
