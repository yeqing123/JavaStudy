package com.yeqing._06_return_pk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import util.JdbcUtil;

// 保存数据时，获取其主键值
public class ReturnPkTest {
    // 使用Statement
	@Test
	public void testSaveByStatement() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES ('XXX', 19)";
		Connection conn = JdbcUtil.getConnection();
		Statement st = conn.createStatement();
		// -----------设置执行sql时返回自动生成的主键值---------------------
		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		//获取主键
		ResultSet rs = st.getGeneratedKeys();
		if(rs.next()) {
			// 不同于查询，因为我们不知道返回的主键的列名是什么，所以只能用序号获取主键值
			// 如果连类型也不知道，可以一概用String类型获取
			String id = rs.getString(1);
			System.out.println(id);
		}
		JdbcUtil.close(conn, st, rs);
	}
	// 使用PreparedStatement
	@Test
	public void testSavePreparedStatement() throws Exception {
		String sql = "INSERT INTO t_students (name, age) VALUES (?,?)";
		Connection conn = JdbcUtil.getConnection();
		// ----------凡是必须传入SQL的地方，才能设置是否返回自动生成的主键值
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, "AAA");
		ps.setInt(2, 20);
		ps.executeUpdate();
		// 获取主键值
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			Long id = rs.getLong(1);
			System.out.println(id);
		}
		JdbcUtil.close(conn, ps, rs);
	}
}
