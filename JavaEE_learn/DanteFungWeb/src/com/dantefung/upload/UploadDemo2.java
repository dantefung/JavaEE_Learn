package com.dantefung.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
//演示第一个组件上传
public class UploadDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1)创建工厂类
			/**
			 * 参数一： 缓存大小   默认10KB  如果不超过10KB的文件，不使用缓存，超过10KB就使用缓存
			 * 参数二：缓存临时目录    
			 */
			DiskFileItemFactory factory = new DiskFileItemFactory(1*1024*1024, new File("c:/tempFiles/"));
			
			//2)创建ServletFileUpload
			ServletFileUpload sfu = new ServletFileUpload(factory);
			
			//设置文件名称的编码
			sfu.setHeaderEncoding("utf-8");
			
			//3)开始上传
			List<FileItem> list = sfu.parseRequest(request);
			
			if(list!=null && list.size()>0){
				//4）得到上传的文件
				FileItem file = list.get(0);
				
				System.out.println(file.getName());
				System.out.println(file.getSize());
				System.out.println(file.getContentType());
				
				//5)处理上传文件
				//得到文件内容
				InputStream is = file.getInputStream();
				
				/*FileOutputStream fos = new FileOutputStream(new File("c:/uploadFiles/"+file.getName()));
				byte[] buf = new byte[1024];
				int len = 0;
				while(  (len=is.read(buf))!=-1 ){
					fos.write(buf, 0, len);
				}
				fos.close();
				is.close();*/
				
				FileUtils.copyInputStreamToFile(is, new File("c:/uploadFiles/"+file.getName()));
				
				//6)删除临时文件
				file.delete();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
