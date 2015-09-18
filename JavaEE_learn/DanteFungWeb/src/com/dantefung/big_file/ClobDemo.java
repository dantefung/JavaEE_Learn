package com.dantefung.big_file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClobDemo {
	public static void main(String[] args) throws Exception {
//		writeClob();
		readClob();
	}

	private static void readClob() throws Exception, SQLException, IOException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from clob_attach where t_id=?");
		stmt.setInt(1,1);
		ResultSet rs = stmt.executeQuery();
		// 异常：Before start of result set 是因为 rs当前的指针在结果集的非数据行，要 rs.next() 才真正的指向数据行
		if( rs.next())
		{
			System.out.println(rs.getString("t_file"));
		}
		
		Clob clob = rs.getClob("t_file");
		Reader reader = clob.getCharacterStream();
//		Reader reader = rs.getClob("t_file").getCharacterStream();
		FileWriter fw = new FileWriter("c:/2.txt");
		int len;
		char[] buf = new char[1024];
		while((len=reader.read(buf))!= -1)
		{
			fw.write(buf,0,len);
		}
		fw.close();
		reader.close();
	
		System.out.println("=========END=======");
	}

	private static void writeClob() throws Exception, SQLException,
			FileNotFoundException, ParseException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert " +
				"into clob_attach(t_name,t_file,t_addtime,t_author)values(?,?,?,?)");
		File file = new File("c:/adjList.txt");
		FileReader reader = new FileReader(file);
		stmt.setString(1, file.getName());
		stmt.setClob(2, reader);
		stmt.setDate(3, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1992-06-11").getTime()));
		stmt.setString(4,"张三");
		stmt.executeUpdate();
		System.out.println("=========END==========");
	}
}
