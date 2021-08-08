package com.yeqing.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

// 数据库操作的模版工具类
public class JdbcTemplate {
    
	//用于“增删改”操作的方法模版
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
}
