package com.dantefung.upload;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * upload demo
 * 1. 支持多文件上传，
 *    可区分 form field(文件描述那部分的<input type="text" name="desc"/>组件)
 * 	     和upload file(上传的文件)<input type="file" name="attach"/>
 * 2. 限制：
 *       单个文件的大小
 *       总文件的大小
 * 3. 按时间创建文件夹分类  c:/Upload_test/dest/年/月/日/文件名.后缀
 * 4. 支持数据库存储（详细的信息：
 *                   描述信息
 *                   文件名
 *                   文件大小
 *                   文件的类型
 *                   文件的存放路径
 *                   文件的存放时间
 *                ）
 * 
 * @author Dante Fung
 * @since 2015.9.12 SAT GZ CN
 *
 */
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart)
		{
			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// Set factory constraints
				factory.setSizeThreshold(1024*1024*1024);// 1G MaxMemorySize
				
				File file = new File("c:/Upload_test/temp");
				if(!file.exists())
				{
					file.mkdirs();
				}
				factory.setRepository(file);// Temp Directory 
				
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				/*
				 *  public ServletFileUpload()
					Constructs an uninitialised instance of this class. 
					A factory must be configured, using setFileItemFactory(),
 					before attempting to parse requests.
 					
 					public ServletFileUpload(FileItemFactory fileItemFactory)
					Constructs an instance of this class which 
					uses the supplied factory to create FileItem instances.
				 */
				
				
				upload.setFileSizeMax(200*1024);// Single File Max Memory  200kb
				upload.setSizeMax(400*1024);// set Overall request size constraint 400Kb
				
				
				/*
				 * Specifies the character encoding to be used 
				 * when reading the headers of individual part. 
				 * When not specified, or null, 
				 * the request encoding is used. 
				 * If that is also not specified, or null, 
				 * the platform default encoding is used
				 */
				upload.setHeaderEncoding("utf-8");
				
				
				// Retrieves the character encoding used 
				// when reading the headers of an individual part. 
				String encoding = upload.getHeaderEncoding();
				System.out.println("encoding: " + encoding);
				
				// Parse the request
				List<FileItem> items =(List<FileItem>)upload.parseRequest(request);
				UploadBean bean=null;
				List<UploadBean> list = new ArrayList<UploadBean>();
				String desc="";
				if(items != null)
				{
					// Handle the file items
					for(FileItem item : items)
					{
						// is regular form field or not, comment or description
						if(item.isFormField())
						{
							desc = processFormField(item);
						}
						else// normal file
						{
							bean = processUploadedFile(item);
						}
						list.add(bean);
					}
				}
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
				// Store details of file into DB
				UploadService service = new UploadService();
				System.out.println("bean : "+bean);
				if(bean != null)
				{
					if(service.saveInfo(list))
					{
						request.getRequestDispatcher("/upload/succ.jsp").forward(request, response);
					}
					else
					{
						request.setAttribute("errors", "上传失败！！");
						request.getRequestDispatcher("/upload/upload.jsp").forward(request, response);
					}
				}
				
				//int x = 1/0;
			}// single file limit 
			catch(FileSizeLimitExceededException e)
			{
				request.setAttribute("errors", "单个文件的大小不能超过200kb");
				request.getRequestDispatcher("/upload/upload.jsp").forward(request, response);
		    }// overall upload file limit
			catch(SizeLimitExceededException e)
			{
				request.setAttribute("errors", "总文件的大小不能超过200kb");
				request.getRequestDispatcher("/upload/upload.jsp").forward(request, response);
				
			}
		    catch (Exception e) 
		    {
				e.printStackTrace();
				request.getRequestDispatcher("/upload/errors.jsp").forward(request, response);
				//throw new RuntimeException(e);
			}
		}

	}

	/**
	 * process the regular form field.
	 * @param item  the file item
	 * @throws Exception 
	 */
	private String processFormField(FileItem item) throws Exception {
		System.out.println("process the regular form field");
		String desc = item.getString("utf-8");
		System.out.println(desc);// content of input widget
		return desc;
	}

	/**
	 * process the upload file
	 * @param item the file item
	 * @throws Exception 
	 */
	private UploadBean processUploadedFile(FileItem item) throws Exception {
		System.out.println("process the upload file");
		UploadBean bean = new UploadBean();
		
		// something i want to know before process the content
		String fieldName = item.getFieldName();
		String name = item.getName();
		String contentType = item.getContentType();
		long size = item.getSize();
		boolean inMemory = item.isInMemory();
		String content = item.getString("utf-8");// content of file
		
		System.out.println("fieldName--" + fieldName + 
				           "   name--" + name +
				           "   contentType--" + contentType +
				           "   size--" + size + 
				           "   isInMeory--" + inMemory + 
				           "   String--" + content);
		

		
		// write to file
		String filePath = "c:/Upload_test/dest/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		File timeFd = new File(filePath);
		if(!timeFd.exists())
		{
			timeFd.mkdirs();
		}
		String fileName = filePath + "/" +  name;
		File uploadedFile = new File(fileName);
		// Note that, in the default implementation of FileUpload,
		// write() will attempt to rename the file to the specified destination,
		// if the data is already in a temporary file.
		item.write(uploadedFile);
		
		// add data 
		bean.setName(name);
		bean.setContentType(contentType);
		bean.setSize(size+"");
		bean.setContent(content);
		bean.setPath(fileName);
		bean.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		return bean;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
