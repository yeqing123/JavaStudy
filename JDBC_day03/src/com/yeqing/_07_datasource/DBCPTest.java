package com.yeqing._07_datasource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import util.DbcpUtil;
import util.JdbcUtil;

// 测试DBCP连接池
public class DBCPTest {
	// 创建连接池对象
	public static DataSource setupDataSource() {
		// 基于DBCP创建连接池对象
		BasicDataSource ds = new BasicDataSource();
		// 设置连接四要素
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jdbcdemo?Timezone=Asia/Shanghai");
		ds.setUsername("root");
		ds.setPassword("mysqladmin");
		ds.setMaxTotal(5);  // 设置最大连接数
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
		//Connection是一个接口，不同的实现类其实现方式不同，因此它们实现close()的方式也不同。
		//通过连接池获得的连接对象，当使用完毕调用close()方法时，会将该连接对象重新放回连接池（也许是标记为空闲），以便今后重复使用，
		// 而不是真的断开连接。
		JdbcUtil.close(conn, ps, rs); 
	}
    // 使用配置文件dbcp.properties和工具类DBCPUtil，来创建连接池对象
    @Test
	public void test2() throws Exception {
		Connection conn = DbcpUtil.getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM t_students");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getLong("id"));
		}
		DbcpUtil.close(conn, ps, rs);
	}
}
