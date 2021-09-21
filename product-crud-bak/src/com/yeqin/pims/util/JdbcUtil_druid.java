package com.yeqin.pims.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

//用于从druid连接池中获取一个数据库连接对象的工具类
public class JdbcUtil_druid {
    private static DataSource ds;
    static {  //使用静态代码块，目的是在工具类首次加载到JVM中时，该代码块同时被加载，今后就不会重复加载了
    	try {
    		Properties p = new Properties();
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    //静态方法从连接池中获取连接对象
    public static Connection getConnection() {
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
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
	    		if(st != null) {
	    			st.close();
	    		}
    		}catch(Exception e) {
    			e.printStackTrace();
    		}finally {
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
