/**
 * Project Name:TestDemo
 * File Name:FileUploadDemo3.java
 * Package Name:com.dantefung.upload
 * Date:2016-1-30下午4:30:08
 * Copyright (c) 2016, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.upload;

import java.io.File;
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

/**
 * ClassName:FileUploadDemo3 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Description: TODO 带表单内容的文件上传. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-1-30 下午4:30:08 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FileUploadDemo3 extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 1、创建DiskFileItemFactory类
		/**
		 * 参数说明：
		 *    参数一：表示文件缓存区的大小。如果上传的文件没有超过缓存区大小，则文件不缓存，否则缓存文件，缓存到临时目录。（byte）
		 *    参数二：表示缓存区的临时目录。
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory(10*1024, new File(getServletContext().getRealPath("/") + "/temp"));
		// 2、创建ServletFileUpload类
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		/**
		 * 设置文件名的编码
		 */
		upload.setHeaderEncoding("utf-8");
		
		try
		{
			// 3、解析request数据
			// 3.1、（把每一个文件封装到FileItem对象中，FileItem放入List中）
			List<FileItem> list = upload.parseRequest(request);
			// 3.2、 取出上传的文件
			if(list!=null && list.size()>0)
			{
				for(FileItem item : list)
				{
					/** 上传的文件**/
					if(!item.isFormField())
					{
						// 3.2.1、得到文件名
						String fileName = item.getName();
						// 3.2.2、得到文件大小
						long fileSize = item.getSize();
						// 3.2.3、得到文件内容类型
						String contentType = item.getContentType();
						// 3.2.4、得到文件数据内容
						InputStream in = item.getInputStream();
						// 4、 把文件数据内容存储到服务器端的硬盘中
						FileUtils.copyInputStreamToFile(in, new File(getServletContext().getRealPath("/") + "/upload/" + fileName));
						// 5、文件上传完毕，手动清理缓存文件
						item.delete();
						
						System.out.println("文件名：" + fileName);
						System.out.println("文件大小：" + fileSize);
						System.out.println("文件类型：" + contentType);
						System.out.println("文件数据内容：" + in);
						System.out.println();
						
					}
					else/** 普通表单内容 **/
					{
						System.out.println(item.getFieldName());
					}
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}

