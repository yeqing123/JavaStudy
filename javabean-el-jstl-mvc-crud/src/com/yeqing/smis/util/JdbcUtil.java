package com.yeqing.smis.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
    private static DataSource ds = null;
    static {
    	Properties p = new Properties();
    	try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			//与DBCP具有很好的兼容性，仅仅只需要修改为Druid的工厂即可
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    //从连接池中获得连接对象
    public static Connection getConnection() {
    	try {
			return ds.getConnection();
		} catch (SQLException e) {
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
