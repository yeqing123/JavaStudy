package com.yeqing.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

// JDBC工具类
public class JdbcUtil {
	private static Properties p = new Properties();
	
	// 当JdbcUtil这份字节码文件被加载进JVM时，该代码块运行一次，可避免驱动程序的重复加载
	static {
		try {
			//加载和读取bd.properties文件
			InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			p.load(inStream);
			Class.forName(p.getProperty("driverClassName")); // 加载驱动程序
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 返回数据库的连接对象
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection( //
					p.getProperty("url"), // 获取URL
					p.getProperty("username"), // 获取用户名
					p.getProperty("password")); // 获取密码
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 释放资源
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
