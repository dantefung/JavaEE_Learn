package com.dantefung.upload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//演示手动解析上传的文件
public class UploadDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)读取文件的内容
		//读取请求正文内容
		ServletInputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		//开始读取
		//读取到文件的分割符
		String fileTag = br.readLine();
		//读取文件名称
		String sLine = br.readLine();
		String fileName = sLine.substring(sLine.lastIndexOf("filename=")+10 , sLine.length()-1);
		
		//跳过两行
		br.readLine();
		br.readLine();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("c:/uploadFiles/"+fileName));
		//读取文件内容
		String str = null;
		while( (str=br.readLine())!=null ){
			//读取文件结束分割符前面为止
			if( (fileTag+"--").equals(str) ){
				break;
			}
			
			//2)保存到硬盘中
			bw.write(str);
			//写出换行符
			bw.newLine();
		}
		bw.close();
		br.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
