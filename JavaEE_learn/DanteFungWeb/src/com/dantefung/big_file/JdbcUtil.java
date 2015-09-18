package com.dantefung.big_file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

//jdbc的工具类(在java项目和web项目通用的！！！！)
public class JdbcUtil {

	// 常量
	private static String driverClass = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;
	
	//只需要读取1次足够
	static{
		try {
			//读取配置文件
			Properties prop = new Properties();
			//使用类路径方式读取
			//java项目：类路径在bin目录下
			//web项目： 类路径在WEB-INF/classes目录下
			InputStream in = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			prop.load(in);
			
			url = prop.getProperty("url");
			driverClass = prop.getProperty("driverClass");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("配置文件加载失败");
		}
	}

	/**
	 * 获取数据库连接的方法
	 * @return  数据库的连接对象
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		// 1.注册驱动
		Class.forName(driverClass);
		// 2.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	 * 关闭数据库连接的方法
	 * @param stmt
	 * @param conn
	 * @throws Exception
	 */
	public static void close(Statement stmt,Connection conn) throws Exception{
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
	}
}
