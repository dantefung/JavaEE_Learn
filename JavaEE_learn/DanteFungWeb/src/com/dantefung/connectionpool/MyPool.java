package com.dantefung.connectionpool;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

// 实现自己的数据库连接池
public class MyPool implements DataSource{

	private static String URL = "";
	private static String USER = "";
	private static String PASSWORD = "";
	private static int initialSize=0;
	private static int maxActive=0;
	private static Connection conn = null;
	private int currentNum = 1;
	private static LinkedList<Connection> conns = new LinkedList<Connection>();
	
	static
	{
		try 
		{
			InputStream in = MyPool.class.getClassLoader().getResourceAsStream("/jdbc.properties");
			Properties prop = new Properties(); 
			prop.load(in);
			Class.forName(prop.getProperty("driverClass"));
			URL=prop.getProperty("url");
			USER=prop.getProperty("user");
			PASSWORD=prop.getProperty("password");
			initialSize=Integer.valueOf(prop.getProperty("initialSize"));
			maxActive=Integer.valueOf(prop.getProperty("maxActive"));
			
			// 类一加载，就创建5条连接资源
			for(int i=0; i<initialSize; i++)
			{
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				conns.addLast(conn);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public Connection getConnection() throws SQLException 
	{
		// 小于初始化的连接数
		if(currentNum < initialSize)
		{
			return conns.removeFirst();
		}
		
		// 当前连接数介于 初始化连接数  和 最大连接数之间
		if(initialSize < currentNum && currentNum <= maxActive)
		{
			// 创建新的连接数
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		}
		
		throw new RuntimeException("已经超过最大连接数！！");
	}
	
	/**
	 * 释放连接 
	 * @param conn 连接的实例
	 */
	public void release(Connection conn)
	{
		conns.addLast(conn);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
