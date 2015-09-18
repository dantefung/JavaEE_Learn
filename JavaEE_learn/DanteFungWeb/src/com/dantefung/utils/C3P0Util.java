package com.dantefung.utils;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;

/**
 * c3p0的工具类
 * @author Dante Fung
 *
 */
public class C3P0Util {
	
	/*
	 * 根据c3p0的文档可知：
	 * 
	 * 将c3p0-config.xml文件放在了类路径下，
	 * c3p0就会自动的扫描并读取配置文件中的内容 
	 *
	 */
	private static DataSource ds = null;
	
	static 
	{
		// 无参的构造方法，读取的就是默认的配置
		ds = new ComboPooledDataSource();
	}
	
	/**
	 * 获取数据源
	 * @return 返回一个数据源
	 */
	public static DataSource getDataSource()
	{
		return ds;
	}
	
	/**
	 * 从数据库连接池中获取连接
	 * @return 返回一个数据库连接
 	 * @throws Exception 如果出现错误，就将错误抛给调用处
	 */
	public static Connection getConnection() throws Exception
	{
		return (Connection)ds.getConnection();
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn 数据库连接
	 * @throws Exception 
	 */
	public static void close(Connection conn) throws Exception
	{
		conn.close();  //注意这个close方法是被c3p0连接池框架的作者通过动态代理重写过的
	}
}
