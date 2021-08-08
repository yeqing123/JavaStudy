package com.yeqing._07_datasource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

import util.DbcpUtil;
import util.DruidUtil;

// 测试Druid连接池
public class DruidTest {
	// 创建连接池对象
	public static DataSource setupDataSource() {
		// 基于Druid创建连接池对象
		DruidDataSource ds = new DruidDataSource();
		// 设置连接四要素
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai");
		ds.setUsername("root");
		ds.setPassword("mysqladmin");
		ds.setMaxActive(5);  // 设置最大连接数
		return ds;
	}
    @Test
	public void test1() throws Exception {
		DataSource ds = setupDataSource();
		Connection conn = ds.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM t_students");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getLong("id"));
		}
		// 关闭资源
		DruidUtil.close(conn, ps, rs); 
    }
    // 使用配置文件druid.properties和工具类DruidUtil，来创建连接池对象
    @Test
	public void test2() throws Exception {
		Connection conn = DruidUtil.getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM t_students");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getLong("id"));
		}
		DruidUtil.close(conn, ps, rs);
	}
}
