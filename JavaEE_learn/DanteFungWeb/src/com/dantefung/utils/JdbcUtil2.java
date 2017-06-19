package com.winanet.netd.dao.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//jdbc的工具类
public class JdbcUtil {
    private static String driverClass = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static DataSource ds;  

    //读取jdbc.properties文件（只需要走1次就足够了）
    static{
        try {
            //使用类路径方式读取
            InputStream is = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
            Properties prop = new Properties();
            prop.load(is);

            //获取配置信息
            driverClass = prop.getProperty("driverClassName");
            url = prop.getProperty("jdbcUrl");
            user = prop.getProperty("user");
            password = prop.getProperty("paswd");

            //注册驱动程序
            Class.forName(driverClass);
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取jdbc.properties失败");
        }

    }
    
    public static DataSource getDataSource(){
    	return ds;
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

    public static void main(String[] args) throws Exception {
        System.out.println(JdbcUtil.getConnection());
    }

    public static String getUrl() {
        return url;
    }

	public static String getUser() {
		return user;
	}
    
    
}