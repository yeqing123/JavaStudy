package com.yeqing.sims.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 用于加载数据库驱动，并获取连接的工具类
 * @author yeqing
 *
 */
public class JdbcUtil_druid {
	private static DataSource druid = null;
	static { // 在静态代码块中加载驱动，这样一个工具类中就只执行一次加载动作，避免重复加载
		try {
			//加载配置文件获得一个字节输入流
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			Properties p = new Properties();
			p.load(in);  //从字节输入流读取资源
			druid = DruidDataSourceFactory.createDataSource(p);  // 生成一个德鲁伊连接池
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取数据连接
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = druid.getConnection();  // 从德鲁伊连接池中获取连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
    // 关闭资源
	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
