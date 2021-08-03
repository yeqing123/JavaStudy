package com.yeqing._02_JDBC_Exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class DMLTest {
    // 需求：向表t_students中添加数据
	@Test
	public void testInsert() throws Exception {
		//1,创建SQL语句
		String sql = "INSERT INTO t_students (name,age) VALUES ('陆小凤', 18)";
		//2,//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5，执行SQL语句
		System.out.println(st.executeUpdate(sql));
		//6，释放资源
		st.close();
		conn.close();
	}
	
	//需求：修改表中id为2的数据
	@Test
	public void testUpdate() throws Exception {
		//1,创建SQL语句
		String sql = "UPDATE t_students SET name='乔峰', age=30 WHERE id=2";
		//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5,执行sql语句，返回受影响的行数
		System.out.println(st.executeUpdate(sql));
		//6,释放资源
		st.close();
		conn.close();
	}
	
	//需求：删除表中id为1的数据
	@Test
	public void testDelete() throws Exception {
		//1,创建SQL语句
		String sql = "DELETE FROM t_students WHERE id=1";
		//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5,执行sql语句，返回受影响的行数
		System.out.println(st.executeUpdate(sql));
		//6,释放资源
		st.close();
		conn.close();
	}
}
