package com.yeqing._04_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

// 测试Mysql数据库的批处理，结论：Mysql不支持批处理。
// 从数据库驱动5.1.13来时才支持，但要在url中进行手动设置"rewriteBatchedStatements=true"
public class BatchTest {
	// Statement未使用批处理
	// InnoDB耗时:10024ms
	// MyISAM耗时：5191ms
	@Test
	public void testSaveByStatement() throws Exception {
		Connection conn = JdbcUtil.getConnection(); // 贾琏
		Statement st = conn.createStatement(); // 欲
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 3001; i++) {
			String sql = "INSERT INTO t_students (name, age) VALUES ('AA'," + i + ")";
			st.executeUpdate(sql); // 执
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcUtil.close(conn, st, null); // 释
	}

	// PreparedStatement未使用批处理
	// InnoDB耗时:8186ms
	// MyISAM耗时：4055ms
	@Test
	public void testSaveByPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES (?, ?)";
		Connection conn = JdbcUtil.getConnection(); // 贾琏
		PreparedStatement ps = conn.prepareStatement(sql); // 欲
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 3001; i++) {
			ps.setString(1, "AA"); // 注意：Java的JDBC中的下标都是从1开始
			ps.setInt(2, i);
			ps.executeUpdate(); // 执
			ps.clearParameters(); // 清理参数
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcUtil.close(conn, ps, null); // 释
	}

	// Statement使用批处理
	// InnoDB耗时:8738ms
	// MyISAM耗时：4227ms
	@Test
	public void testSaveByStatement_batch() throws Exception {
		Connection conn = JdbcUtil.getConnection(); // 贾琏
		Statement st = conn.createStatement(); // 欲
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 3001; i++) {
			String sql = "INSERT INTO t_students (name, age) VALUES ('AA'," + i + ")";
			st.addBatch(sql);  // 放入批处理
            if(i % 200 == 0) {
			    st.executeBatch(); // 执批处理
			    st.clearBatch();   // 清理批处理
            }
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcUtil.close(conn, st, null); // 释
	}

	// PreparedStatement使用批处理
	// InnoDB耗时:8387ms
	// MyISAM耗时：3971ms
	@Test
	public void testSaveByPreparedStatement_batch() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES (?, ?)";
		Connection conn = JdbcUtil.getConnection(); // 贾琏
		PreparedStatement ps = conn.prepareStatement(sql); // 欲
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 3001; i++) {
			ps.setString(1, "AA"); // 注意：Java的JDBC中的下标都是从1开始
			ps.setInt(2, i);
			ps.addBatch();   // 放入批处理
			if(i % 200 == 0) {
				ps.executeBatch(); // 执行批处理
				ps.clearBatch();  // 清理批处理
				ps.clearParameters(); // 清理参数
			}
		}
		System.out.println(System.currentTimeMillis() - begin);
		JdbcUtil.close(conn, ps, null); // 释
	}
}
