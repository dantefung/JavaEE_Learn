package com.dantefung.big_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BlobDemo {
	public static void main(String[] args) throws Exception {
		//writeLongBlob();
		readLongBlob();
		
	}

	private static void readLongBlob() throws Exception, SQLException,
			FileNotFoundException, IOException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select t_file from attachments where t_id=?");
		stmt.setInt(1, 2);
		ResultSet rs = stmt.executeQuery();
		String content = null;
		if(rs.next())
		{
			// 第一种处理方式：
			content = rs.getString("t_file");
		}
		System.out.println(content);
		// 第二种处理方式: 将该资源以流的形式写出.
		InputStream in = rs.getBinaryStream("t_file");
		FileOutputStream fos = new FileOutputStream("c:/1.jpg");
		//InputStream in = rs.getBlob("t_file").getBinaryStream();
		byte[] buf = new byte[1024];
		int len=0;
		while( (len=in.read(buf)) != -1)
		{
			fos.write(buf, 0, len);
		}
		fos.close();
		in.close();
		System.out.println("========END============");
	}

	private static void writeLongBlob() throws Exception, SQLException,
			FileNotFoundException, ParseException {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into attachments(t_name,t_file,t_addtime,t_author) " +
				"values(?,?,?,?)");
		InputStream in = new FileInputStream("c:/mm.jpg");
		stmt.setString(1, "mm");
		stmt.setBlob(2, in);
		stmt.setDate(3, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1992-06-11").getTime()));
		stmt.setString(4,"张三");
		stmt.executeUpdate();
		System.out.println("=========END==========");
	}
}
