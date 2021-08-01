package com.yeqing._01_connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class ConnectionTest {
	// 使用 show processlist; 命令来测试连接数据库是否成功
	@Test
	public void test1() throws Exception {
		//1,注册并加载数据库驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2,连接数据库
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo?serverTimezone=Asia/Shanghai", "root", "mysqladmin");
	    Thread.sleep(5000);
	}
}
