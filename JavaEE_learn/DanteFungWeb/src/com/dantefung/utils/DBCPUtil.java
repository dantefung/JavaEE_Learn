package com.dantefung.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//基于DBCP数据源的工具类
public class DBCPUtil {

	//创建数据源对象
	private static DataSource ds = null;
	
	static{
		try {
			//读取配置文件
			Properties prop = new Properties();
			InputStream is = DBCPUtil.class.getResourceAsStream("/dbcp.properties");
			prop.load(is);
			
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 获取连接的方法
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 释放资源的方法
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();//这个方法已经被带了，返回连接池
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
