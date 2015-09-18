package com.dantefung.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//jdbc的工具类
public class JdbcUtil {
	private static String driverClass = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;
	
	//读取jdbc.properties文件（只需要走1次就足够了）
	static{
		try {
			//使用类路径方式读取
			InputStream is = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			Properties prop = new Properties();
			prop.load(is);
			
			//获取配置信息
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
			//注册驱动程序
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取jdbc.properties失败");
		}

	}
	
	/**
	 * 获取数据库连接的方法
	 * @return 连接对象
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		return DriverManager.getConnection(url, user , password);
	}
	
	/**
	 * 释放连接的方法
	 * @param rs  结果集对象
	 * @param stmt statement对象
	 * @param conn 连接对象
	 * @throws Exception
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn) throws Exception{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
	
}
