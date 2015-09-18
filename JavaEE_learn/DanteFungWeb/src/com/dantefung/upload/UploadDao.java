package com.dantefung.upload;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.dantefung.utils.C3P0Utils;

public class UploadDao {
	
	QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
	
	public boolean saveInfo(List<UploadBean> list)
	{
		boolean flag = false;
		try 
		{
		   /*
		    * 	 private String name;
      			 private String size;
	  			 private String contentType;
	  			 private String path;
	  			 private String time;
	  			 private String content;
	  			 private String desc;
		    */
		   Connection conn = C3P0Utils.getConnection();
		  for(UploadBean bean : list)
		  {
			   String sql = "insert into upload(name,size,contentType,path,time,content) values(?,?,?,?,?,?)";
			   int column = runner.update(conn, sql, new Object[]{bean.getName(),bean.getSize(),bean.getContentType(),bean.getPath(),bean.getTime(),bean.getContent()});
		  }
		  flag = true;
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
		
		return flag;
	}
}
