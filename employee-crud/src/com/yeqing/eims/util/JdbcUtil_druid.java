package com.yeqing.eims.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

//获取数据库连接对象，释放资源的工具列。它创建一个druid连接池，并提供一个从连接池中获取连接对象的公共的方法。资源使用完毕，将连接对象放回连接池。
public class JdbcUtil_druid {
    private static DataSource ds;
    static {
    	Properties p = new Properties();  //创建用于读取资源文件的工具类
    	try {//通过一个字节输入流加载资源文件
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			ds = DruidDataSourceFactory.createDataSource(p); //根据资源文件创建druid连接池
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    //返回数据库连接对象
    public static Connection getConnection() {
    	try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    //将连接对象放回连接池
    public static void close(Connection conn, Statement st, ResultSet rs) {
    	try {
    		if(rs != null) {
    			rs.close();
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if(st!= null) {
    				st.close();
    			}
    		}catch(Exception e) {
    			e.printStackTrace();
    		} finally {
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
}
