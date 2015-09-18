package com.dantefung.connectionpool;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPDemo {
	public static void main(String[] args) throws Exception {
		
		Properties prop = new Properties();
		InputStream in = DBCPDemo.class.getClassLoader().getResourceAsStream("/dbcp.properties");
		prop.load(in);
		DataSource ds = BasicDataSourceFactory.createDataSource(prop);
		//BasicDataSource bds = new BasicDataSource();
		for(int i=0; i<11; i++)
		{
			Connection conn = ds.getConnection();
			System.out.println(conn);
		}
	}
}
