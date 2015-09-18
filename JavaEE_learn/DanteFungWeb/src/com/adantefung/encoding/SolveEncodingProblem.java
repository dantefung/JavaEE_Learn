package com.adantefung.encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 解决中文乱码问题：
 *                                      |-写字节数据
 * 1.response 服务器向浏览器写中文数据，乱码问题.|-写字符数据
 * 2.request POST方式 ：获取表单提交提交的数据出现乱码.request.setCharacterEncoding("UTF-8");
 *           GET方式：手动解码 :URL编码问题.  浏览器-->服务器  例子：表单.
 * 3.URL 编码问题.   
 *   请求：浏览器-->服务器
 *   响应：浏览器<--服务器   例子：文件下载.
 * @author Dante Fung 
 * @since  2015-8-24
 *
 */
public class SolveEncodingProblem extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("---------------");
		// -----------------------Response系列-----------------------------
		// 乱码问题之向浏览器写字节数据
		//test1(response);
		// 乱码问题之想浏览器写字符数据
		//test2(response);
		// ----------------------Request系列------------------------------
		// GET方式
		test3(request, response);
		// POST方式
		//test4(request);
		
		//-----------------------URL编码问题------------------------------
		//test5(response);

	}
	
	private void test4(HttpServletRequest request) {
		
	    // POST方式
		// 在获取数据前要指定用UTF-8对请求正文进行解码
		try 
		{
			String username = request.getParameter("name");
			System.out.println( "设定用UTF-8解码前：" + username);
			request.setCharacterEncoding("UFT-8");
			username = request.getParameter("name");
			System.out.println("设定用UTF-8解码后：" + username);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
		
	}

	// URL编码问题.
	/*查看jdk1.6的API:
	 * 
	 * HTML 格式编码的实用工具类。该类包含了将 String 转换为 
	 * application/x-www-form-urlencoded MIME 格式的静态方法。
	 * 有关 HTML 格式编码的更多信息，请参阅 HTML 规范。 
		对 String 编码时，使用以下规则： 

		字母数字字符 "a" 到 "z"、"A" 到 "Z" 和 "0" 到 "9" 保持不变。 
		特殊字符 "."、"-"、"*" 和 "_" 保持不变。 
		空格字符 " " 转换为一个加号 "+"。 
		所有其他字符都是不安全的，因此首先使用一些编码机制将它们转换为一个或多个字节。
		然后每个字节用一个包含 3 个字符的字符串 "%xy" 表示，其中 xy 为该字节的两位十六进制表示形式。
		推荐的编码机制是 UTF-8。但是，出于兼容性考虑，如果未指定一种编码，则使用相应平台的默认编码。 
		例如，使用 UTF-8 编码机制，字符串 "The string ü@foo-bar" 
		将转换为 "The+string+%C3%BC%40foo-bar"，因为在 UTF-8 中，字符 ü 编码为两个字节，
		C3 （十六进制）和 BC （十六进制），字符 @ 编码为一个字节 40 （十六进制）。 

	 */
	private void test5(HttpServletResponse response)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		// 读取本地文件
		String path = getServletContext().getRealPath("/img/美女.jpg");
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		// 获取输出流
		ServletOutputStream out = response.getOutputStream();
		// 美女.jpg
		//通知浏览器以提示框的形式下载资源（Content-Disposition: attachment; filename=aaa.zip）
		// 出现中文文件名称，就需要对名称进行URL编码
		response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(file.getName(),"UTF-8"));
		// 使用缓存的形式
		byte[] buf = new byte[1024];
		int len = 0;
		while( (len=fis.read(buf))!=-1)
		{
			out.write(buf, 0, len);
		}
		// 关闭流.
		fis.close();
		// 这个输出流会自动关闭
	}
    
	// GET方式提交表单，获取表单数据的时候需要自己手动解码.
	// 说明：在做这个测试之前，要在地址栏上自己添加参数
	// 当你复制过来时： http://localhost:8080/DanteFungWeb/SolveEncodingProblem?name=%E5%BC%A0%E4%B8%89
	// http://localhost:8080/DanteFungWeb/SolveEncodingProblem?name=张三
	private void test3(HttpServletRequest request, HttpServletResponse response) {
		// 由于request.setCharacterEncoding("UTF-8");只能改变POST方式请求正文的编码问题.
		// 浏览器GET方式提交数据,服务器取出数据时，这个数据时被以ISO-8859-1解码过的.
		String username = request.getParameter("name");
		System.out.println("手动解码前：" + username);
		// 手动解码
		try 
		{
			username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		} 
		catch (Exception e) 
		{ 	
			throw new RuntimeException(e);
		}
		System.out.println("手动解码后：" + username);
	}

	// 写字符数据
	private void test2(HttpServletResponse response) throws IOException {
		String content = "服务器向浏览器写字符数据实验：哈哈哈，我是谁？";
		// 1.查看API可以知道，setContentType("text/html;charset=utf-8");
		//   会自动设置内部编码为utf-8（万国码表），但是 ，必须在调用getWriter方法之前.
		// 2.同下test1的说明。
		response.setContentType("text/html;charset=utf-8");
		// 获得字符输出流
		PrintWriter writer = response.getWriter();
		// 这个不成功.
		//writer.write("<meta http-equiv='content-type' content='text/html'; charset='UTF-8'>");
		// 向浏览器写字符数据
		writer.write(content);
	}

	// 写字节数据
	private void test1(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		// server-->browser：字节数据.
		String content = "服务器向浏览器写字节数据实验：大家好！！我是冯皓林！！";
		// 字节输出流
		ServletOutputStream out = response.getOutputStream();
		// 2.用HTTP协议的响应头的方式告诉浏览器我传给它的是什么mime类型的数据，并且用什么码表解码
		response.setContentType("text/html;charset=utf-8");
		// 以下这个方法存在浏览器兼容性问题.不建议使用
		//out.write("<meta http-equiv='content-type' content='text/html'; charset='UTF-8'>".getBytes("UTF-8"));
		// 以下方式还是不够优雅
		//response.setHeader("content-type", "text/html;charset=utf-8");
		// 向浏览器写数据
		out.write(content.getBytes("UTF-8")); // 用UTF-8码表进行编码.中文字符-3个字节
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
