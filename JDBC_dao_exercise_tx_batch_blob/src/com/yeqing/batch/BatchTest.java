package com.yeqing.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import com.yeqing.exercise.util.JdbcUtil;

// 分别使用PreparedStatement与Statement，测试不使用批处理与使用批处理的性能差别。
// 结论：MySQL既不支持PreparedStatement性能优化，也不支持批处理！
public class BatchTest {
	// PreparedStatement不使用批处理:
	// 共耗时：5596ms
	@Test
	public void testSaveByPreparedStatement() throws Exception {
		String sql = "INSERT INTO t_students (name,age) VALUE (?, ?)";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 2001; i++) {
			ps.setString(1, "AA");
			ps.setInt(2, i);
			ps.executeUpdate();
		}
		Long end = System.currentTimeMillis();
		JdbcUtil.close(conn, ps, null);
		System.out.println("共耗时：" + (end - begin) + "ms");
	}

	// PreparedStatement使用批处理：
	// 共耗时：5656ms
	@Test
	public void testSaveByPreparedStatement_batch() throws Exception {
		String sql = "INSERT INTO t_students (name,age) VALUE (?, ?)";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 2001; i++) {
			ps.setString(1, "AA");
			ps.setInt(2, i);
			ps.addBatch();
			if (i % 200 == 0) {
				ps.executeBatch();
				ps.clearBatch();
				ps.clearParameters();
			}
		}
		Long end = System.currentTimeMillis();
		JdbcUtil.close(conn, ps, null);
		System.out.println("共耗时：" + (end - begin) + "ms");
	}

	// Statement不使用批处理
	// 共耗时：5573ms
	@Test
	public void testSaveByStatement() throws Exception {
		Connection conn = JdbcUtil.getConn();
		Statement st = conn.createStatement();
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 2001; i++) {
			String sql = "INSERT INTO t_students (name,age) VALUE ('BB', " + i + ")";
			st.executeUpdate(sql);
		}
		Long end = System.currentTimeMillis();
		JdbcUtil.close(conn, st, null);
		System.out.println("共耗时：" + (end - begin) + "ms");
	}

	// Statement使用批处理
	// 共耗时：5817ms
	@Test
	public void testSaveByStatement_batch() throws Exception {
		Connection conn = JdbcUtil.getConn();
		Statement st = conn.createStatement();
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 2001; i++) {
			String sql = "INSERT INTO t_students (name,age) VALUE ('BB', " + i + ")";
			st.addBatch(sql);
			if (i % 200 == 0) {
				st.executeBatch();
				st.clearBatch();
			}
		}
		Long end = System.currentTimeMillis();
		JdbcUtil.close(conn, st, null);
		System.out.println("共耗时：" + (end - begin) + "ms");
	}

	// 听说MySQL的驱动从5.1.13开始支持了批处理，只要在url中加入rewriteBatchedStatements=true可以使MySQL支持批处理，我们在试试看
	// 共耗时：5825ms
	// 我用的驱动版本是8.0.18，修改url后依然不支持批处理，不知为何？
	@Test
	public void testSaveBy_rewriteBatchedStatements() throws Exception {
		String sql = "INSERT INTO t_students (name,age) VALUE (?, ?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection( //
				"jdbc:mysql://localhost:3306/jdbcdemo?rewriteBatchedStatements=true", // url，加入了支持批处理的属性
				"root", // username
				"mysqladmin"); // password
		PreparedStatement ps = conn.prepareStatement(sql);
		Long begin = System.currentTimeMillis();
		for (int i = 1; i < 2001; i++) {
			ps.setString(1, "CC");
			ps.setInt(2, i);
			ps.addBatch();
			if (i % 200 == 0) {
				ps.executeBatch();
				ps.clearBatch();
				ps.clearParameters();
			}
		}
		Long end = System.currentTimeMillis();
		JdbcUtil.close(conn, ps, null);
		System.out.println("共耗时：" + (end - begin) + "ms");
	}
}
