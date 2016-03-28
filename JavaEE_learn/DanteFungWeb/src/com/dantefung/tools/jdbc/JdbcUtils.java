package com.dantefung.tools.jdbc;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 连接数据库，使用本方法必须要有c3p0-config.xml文件
 * @author lenovo
 *
 */
public class JdbcUtils {
	//饿汉式单例模式
	private static DataSource ds = new ComboPooledDataSource();
	/*
	 * 开启事务时，给con赋值
	 * 关闭事务时，给con赋值为null
	 * 而且在开启事务时，让多个dao共享这个connection,线程本地类
	 */
	private static ThreadLocal<Connection> t1 =new ThreadLocal<Connection>();
	//取得数据源
	public static DataSource getDataSource(){
		return ds;
	}
	//取得数据库连接
	public static Connection getConnection() throws SQLException{
		Connection con = t1.get();//获取当前线程的连接
		if(con != null)return con;  //如果存在连接，返回当前连接
		return ds.getConnection();  //不存在连接，在数据库连接池产生新的连接
	}
	/*
	 * 开启事务
	 */
	public static void beginTransaction() throws SQLException{
		Connection con = t1.get();//获取当前线程的连接
		if(con!=null)throw new SQLException("已经开启了事务，不能重复开启");
		con = ds.getConnection();
		con.setAutoCommit(false);//设置手动提交
		t1.set(con); //把当前事务连接放到t1中
	}
	/*
	 * 回滚事务
	 */
	public static void rollbackTransaction() throws SQLException{
		Connection con = t1.get();//获取当前线程的连接
		if(con==null)throw new SQLException("事务还未开启，不能回滚");
		con.rollback();//设置回滚
		con.close();//关闭连接 
		con = null;
		t1.remove(); //把当前事务移除
	}
	/*
	 * 提交事务
	 */
	public static void commitTransaction() throws SQLException{
		Connection con = t1.get();//获取当前线程的连接
		if(con==null)throw new SQLException("已经开启了事务，不能提交");
		con.commit();//设置提交
		con.close();//关闭连接
		con = null;
		t1.set(con); //把当前事务连接放到t1中
	}
	/**
	 * 断开连接
	 * @param connection
	 * @throws SQLException
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection con = t1.get();//获取当前线程的连接
		if(connection != con){//如果当前参数连接与当前事务连接不同
			if(connection!=null && !connection.isClosed()){//如果当前事务参数未连接，关闭之
				connection.close();
			}
		}
	}
}
