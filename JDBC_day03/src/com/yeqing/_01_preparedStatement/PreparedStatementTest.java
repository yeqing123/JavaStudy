package com.yeqing._01_preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

// Statement与PreparedStatement对比测试
public class PreparedStatementTest {
    @Test
	public void testSaveByStatement() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES ('AA', 12)";
		Connection conn = JdbcUtil.getConnection();  // 贾琏
		Statement st = conn.createStatement();   // 欲
		st.executeUpdate(sql);   // 执
		JdbcUtil.close(conn, st, null);  // 释
	}
    @Test
	public void testSaveByPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES (?, ?)";
		Connection conn = JdbcUtil.getConnection();  // 贾琏
		//-------------------------------------------------------
		PreparedStatement ps = conn.prepareStatement(sql);  // 欲
		ps.setString(1, "AA");   // 注意：Java的JDBC中的下标都是从1开始
		ps.setInt(2, 12);
		//-------------------------------------------------------
		ps.executeUpdate();  // 执
		JdbcUtil.close(conn, ps, null);  // 释
	}
    @Test
	public void testLoginByStatement() throws Exception {
    	// 使用Statement容易造成SQL注入，不安全
		String sql = "SELECT * FROM t_students WHERE name = '' OR 1=1 OR '' AND password = '1'";
		Connection conn = JdbcUtil.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
	}
    @Test
	public void testLoginByPreparedStatement() throws Exception {
    	// 使用PreparedStatement不会造成SQL注入，比Statement更安全
		String sql = "SELECT * FROM t_students WHERE name = ? AND password = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "' OR 1=1 OR '");
		ps.setString(2, "1234");
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
	}
}
