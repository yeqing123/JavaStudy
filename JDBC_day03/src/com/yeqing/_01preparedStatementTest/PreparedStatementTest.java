package com.yeqing._01preparedStatementTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

public class PreparedStatementTest {
    @Test
	public void statementSaveTest() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES ('AA', 12)";
		Connection conn = JdbcUtil.getConnection();  // 贾琏
		Statement st = conn.createStatement();   // 欲
		st.executeUpdate(sql);   // 执
		JdbcUtil.close(conn, st, null);  // 释
	}
    @Test
	public void preparedStatementSaveTest() throws Exception {
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
}
