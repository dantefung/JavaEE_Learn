/**
 * Project Name:TestDemo
 * File Name:DownloadDemo.java
 * Package Name:com.dantefung.download
 * Date:2016-1-30下午5:03:54
 * Copyright (c) 2016, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.down;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:DownloadDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Description: TODO 演示文件下载，实际开发中使用这种方式下载. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-1-30 下午5:03:54 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DownloadDemo extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 1、读取到需要下载的资源文件
		String path = getServletContext().getRealPath("/upload/a.jpg");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		
		/**
		 * 处理URL编码问题
		 */
		String fileName = file.getName();
		//对文件名进行URl编码
		fileName = URLEncoder.encode(fileName, "utf-8");
		
		// 通知浏览器以下载的方式打开
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		
		// 2、输出到浏览器
		ServletOutputStream out = response.getOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=fis.read(buf))!=-1)
		{
			out.write(buf, 0, len);
		}
		
		// 3、关闭资源
		out.close();
		fis.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}

