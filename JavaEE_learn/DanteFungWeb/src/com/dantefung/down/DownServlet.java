package com.dantefung.down;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//下载资源(实际开发中使用这种方式下载)
public class DownServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)读取到需要下载的资源文件
		String path = getServletContext().getRealPath("/uploadFiles/3.jpg");
		File file = new File(path);
		FileInputStream fis  = new FileInputStream(file);
		
		//通知浏览器以下载方式打开
		response.setHeader("content-disposition", "attachment;filename="+file.getName());
		
		//2)输出到浏览器
		ServletOutputStream out = response.getOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while( (len=fis.read(buf))!=-1  ){
			out.write(buf, 0, len);
		}
		//3）关闭
		out.close();
		fis.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
