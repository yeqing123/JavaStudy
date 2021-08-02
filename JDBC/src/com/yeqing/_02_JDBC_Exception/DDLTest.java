package com.yeqing._02_JDBC_Exception;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class DDLTest {
    // 需求：使用JDBC在数据库jdbcdemo中创建一个表
	@Test
	public void testCreateTable() throws Exception {
		//1,创建SQL语句
		String sql = "CREATE TABLE t_students("
				+ "id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,"
				+ "name VARCHAR(8) NOT NULL, "
				+ "age INT(2) NOT NULL)";
		
		// ===========贾琏欲执事============
		//2,贾：加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,琏：连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,欲：创建语句对象
		Statement st = conn.createStatement();
		//5，执：执行SQL语句
		st.executeUpdate(sql);
		//6,事：释放资源
		st.close();
		conn.close();
	}
	
	//因为与SQL有关的异常，都不是运行时异常，所以不同直接抛出，必须进行处理，
	//所以需要按照正确的方式处理SQL异常（）
	@Test
	public void testHandleException() {
		String sql = "CREATE TABLE t_students("
				+ "id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,"
				+ "name VARCHAR(8) NOT NULL, "
				+ "age INT(2) NOT NULL)";
		Connection conn = null;
		Statement st = null;
		
		try {
			//2,贾：加载并注册数据库驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			//3,琏：连接数据库
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
					"root", "mysqladmin");
			//4,欲：创建语句对象
			st = conn.createStatement();
			//5，执：执行SQL语句
			st.executeUpdate(sql);
			//6,事：释放资源，下面的代码又臭又长
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(st != null) {
					st.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}finally {  // 无论st是否释放，conn都要释放
				try {
					if(conn != null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//删除一个表
	@Test
	public void testDeleteTable() throws Exception {
		//1,创建sql语句
		String sql = "DROP TABLE t_students";
		//2,加载并注册数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3,连接数据库
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbcdemo?ServerTimezone=Asia/Shanghai", 
				"root", "mysqladmin");
		//4,创建语句对象
		Statement st = conn.createStatement();
		//5，执行SQL语句
		st.executeUpdate(sql);
		//6，释放资源
		st.close();
		conn.close();
	}
}
