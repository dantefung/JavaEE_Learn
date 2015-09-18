package com.dantefung.upload;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
//演示上传文件的细节
//1)限制文件类型
//2)限制文件大小
//3)解决文件名重复的问题
//4)解决目标存放问题
//5)获取普通表单的内容
public class UploadDemo4 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory(1*1024*1024, new File("c:/tempFiles"));
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setHeaderEncoding("utf-8");
			
			//2)限制文件上传的大小
			sfu.setFileSizeMax(200*1024);
			sfu.setSizeMax(400*1024);
			
			List<FileItem> list = sfu.parseRequest(request);
			
			if(list!=null && list.size()>0){
				for(FileItem file : list){
					//判断是否是普通表单： true：是 text/password/radio/check/select            false：不是，是file
					if(file.isFormField()){
						//获取普通表单的内容
						if(file.getFieldName().equals("info1")){
							String info1 = file.getString("utf-8");
							System.out.println("info1"+info1);
						}
						if(file.getFieldName().equals("info2")){
							String info2 = file.getString("utf-8");
							System.out.println("info2"+info2);
						}
					}else{
						//1）限制文件类型
						String contentType = file.getContentType(); // mime: 大类型/小类型 
						//需求： 只能上传图片文件    image/*    
						if(!contentType.matches("image/[a-z]+")){
							//类型错误
							request.setAttribute("msg", "该格式不支持上传！");
							request.getRequestDispatcher("upload4.jsp").forward(request, response);
							return;
						}
						
						//3）处理文件名称，避免重复
						String uuid = UUID.randomUUID().toString(); //作为前缀
						String ext = file.getName().substring(file.getName().lastIndexOf("."));//后缀
						//新的文件名称
						String fileName = uuid+ext;
						
						
						//4)解决文件存放目录的问题： 年 目录   月目录   日目录
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						
						//年
						int year = c.get(Calendar.YEAR);
						//月： 0-11的值
						int month = c.get(Calendar.MONTH)+1;
						//日
						int day = c.get(Calendar.DATE);
						
						// c:/uploadFiles/2015/9/11
						String serverPath = "c:/uploadFiles/";
						//建立目录结构
						File savePath = new File(serverPath+year+"/"+month+"/"+day);
						//如果不存在，则创建
						if(!savePath.exists()){
							savePath.mkdirs();
						}
						
						FileUtils.copyInputStreamToFile(file.getInputStream(), new File(savePath+"/"+fileName));
						
						file.delete();
					}
				}
				
			}
		} catch (FileSizeLimitExceededException e) {
			 //e.printStackTrace();
			request.setAttribute("msg", "单个文件不能超过200KB！");
			request.getRequestDispatcher("upload4.jsp").forward(request, response);
			return;
		}catch (SizeLimitExceededException e) {
			//e.printStackTrace();
			request.setAttribute("msg", "总文件不能超过400KB！");
			request.getRequestDispatcher("upload4.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
