package com.yeqing._02_jdbc_exception;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class DQLTest {
	//需求：查询t_students表中所有数据
	@Test
	public void testQueryAll() throws Exception {
		//1,创建SQL语句
		String sql = "SELECT id, name, age FROM t_students";
		//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5，执行SQL语句，获得查询结果的数据集
		ResultSet rs = st.executeQuery(sql);
		//6，遍历结果集，逐条打印数据
		while(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println("id:" + id + ", name:" + name + ", age:" + age);
		}
		//7,释放资源
		rs.close();
		st.close();
		conn.close();
	}
	
	//需求：查询t_students表中id为1的数据
	@Test
	public void testQueryOne() throws Exception {
		//1,创建SQL语句
		String sql = "SELECT id, name, age FROM t_students Where id = 1";
		//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5，执行SQL语句，获得查询结果的数据集
		ResultSet rs = st.executeQuery(sql);
		//6，因为只查询一条数据，所以不需要循环遍历
		if(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println("id:" + id + ", name:" + name + ", age:" + age);
		}
		//7,释放资源
		rs.close();
		st.close();
		conn.close();
	}
	
	//需求：查询t_students表中一共有多少条数据
	@Test
	public void testRows() throws Exception {
		//1,创建SQL语句
		String sql = "SELECT COUNT(id) rows FROM t_students";
		//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5，执行SQL语句，获得查询结果的数据集
	    ResultSet rs = st.executeQuery(sql);
	    //6,获取结果
	    if(rs.next()) {
	    	int rows = rs.getInt("rows");
			System.out.println("t_students表中一共有" + rows + "条数据。");
		}
	    //7,释放资源
  		rs.close();
  		st.close();
  		conn.close();
	}
}
