package com.dantefung.filter_gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//演示网页压缩的效果
public class ContentGZIPServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  StringBuffer str = new StringBuffer();
		  for(int i=1;i<=10000;i++){
			  str.append("abcd");
		  }
		  System.out.println("压缩前： "+str.toString().getBytes().length);
		 // response.getWriter().write(str.toString());
		  
		  
		  //常用的网页压缩格式： GZIP    jdk的工具： GZIPOutputStream 专门进行GZI压缩
		  //1)创建字节数组输出流
		  ByteArrayOutputStream bas = new ByteArrayOutputStream();
		  //2)创建GZIPOutputStream
		  GZIPOutputStream gzip = new GZIPOutputStream(bas);
		  //3)开始写出
		  gzip.write(str.toString().getBytes());
		  //注意：刷新缓存
		  gzip.finish(); //  gzip.flush()
		  //4)获取到压缩后的内容
		  byte[] result = bas.toByteArray();
		  
		  
		  System.out.println("压缩后的大小： "+result.length);
		  
		  //通知浏览器 ，服务器发送的内容压缩格式：gzip
		  response.setHeader("content-encoding", "gzip");
		  
		  //5）输出到用户
		  response.getOutputStream().write(result);
		  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
