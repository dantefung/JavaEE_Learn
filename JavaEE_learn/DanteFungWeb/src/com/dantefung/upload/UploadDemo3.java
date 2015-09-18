package com.dantefung.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

//演示多个文件上传
public class UploadDemo3 extends HttpServlet {
	
//	AttachService service = new AttachService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory(1*1024*1024, new File("c:/tempFiles"));
			
			ServletFileUpload sfu = new ServletFileUpload(factory);
			
			sfu.setHeaderEncoding("utf-8");
			
			List<FileItem> list = sfu.parseRequest(request);
			
			if(list!=null && list.size()>0){
				//遍历所有文件
				for(FileItem file : list){
					//1）保存把文件相关信息保存到数据库中（文件名，大小，类型，作者）
					//封装附件对象
//					Attach attach = new Attach();
//					attach.setName(file.getName());
					long size = file.getSize();
					
					//计算大小
					String sizeStr = "";
					if(size>0 && size<1024){
						sizeStr = size+"B";
					}else if(size>=1024 && size<1024*1024){
						sizeStr = size/1024+"K";
					}else if(size>=1024*1024 && size<1024*1024*1024){
						sizeStr = size/1024/1024+"M";
					}else{
						sizeStr = size/1024/1024/1024+"G";
					}
//					attach.setSize(sizeStr); 
//					attach.setType(file.getContentType());
					
					String servetPath = "C:/uploadFiles";
//					attach.setUri(servetPath);
					
//					service.saveAttach(attach);
					
					//2) 把文件保存到硬盘
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(servetPath+"/"+file.getName()));
					
					file.delete();
				}
			}
			
			//跳转到成功页面
			response.sendRedirect(request.getContextPath()+"/succ.jsp");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
