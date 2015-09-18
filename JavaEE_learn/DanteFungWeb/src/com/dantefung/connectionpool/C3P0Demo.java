package com.dantefung.connectionpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Demo {
	/**
	 * c3p0的文档说了：因此我们   加载配置文件  获取配置文件信息   注册驱动 
	 *  以及获取数据库连接的一系列操作c3p0都帮我们写好了
	 *  因此，我们只需要将配置文件放在类路径的目录下即可
	 *  然后直接创建出一个连接池，然后从连接池中获取连接即可.
	 *    
	 * By default, c3p0 will look for an XML configuration 
	 * file in its classloader's resource path 
	 * under the name "/c3p0-config.xml". 
	 * That means the XML file should be placed 
	 * in a directly or jar file directly named 
	 * in your applications CLASSPATH, 
	 * in WEB-INF/classes, or some similar location. 
	 */
	public static void main(String[] args) throws Exception {
		
		// 1）获取连接池（数据源）
		ComboPooledDataSource cpds = new ComboPooledDataSource(); 
		// 2) 从连接池中获取一条连接
//		Connection conn = cpds.getConnection();
		// 3） 获取PreparedStatement对象
//		PreparedStatement stmt = conn.prepareStatement("insert into student(id,name,chinese,english,math)values(?,?,?,?,?)");
		// 4) 模拟用户并发访问
		for(int i=0; i<11; i++)
		{
			Connection conn = cpds.getConnection();
			System.out.println(conn);
			if( i== 4)
			{
				conn.close();
			}
		}
	}
}
