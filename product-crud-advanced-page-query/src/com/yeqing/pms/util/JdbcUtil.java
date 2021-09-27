package com.yeqing.pms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
	//使用阿里巴巴的druid，创建数据库连接池
	//使用静态代码块，当JdbcUtil的字节码首次被加载到JVM中，静态代码块的字节码就被加载进JVM，避免重复加载
    private static DataSource ds;
    static {
    	Properties p = new Properties();
    	try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    //从连接池中获取一个连接对象
    public static Connection getConn() {
    	try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    //释放资源
    public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null) {
					st.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }
}
