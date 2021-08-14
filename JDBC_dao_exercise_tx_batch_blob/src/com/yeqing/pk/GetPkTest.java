package com.yeqing.pk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.yeqing.exercise.util.JdbcUtil;

public class GetPkTest {
    @Test
	public void test1() throws Exception {
		String sql = "INSERT INTO t_students (name,age) VALUES (?,?)";
		Connection conn = JdbcUtil.getConn();
		// 设置为可以自动获得主键值
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
		ps.setString(1, "张三");
		ps.setInt(2, 20);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();  // 获取刚刚添加到表中的学生的主键值
		if(rs.next()) {
			Long id = rs.getLong(1);
			System.out.println("新增信息的主键值为：" + id);
		}
	}
}
