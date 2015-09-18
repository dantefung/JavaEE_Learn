package com.dantefung.filter_gzip;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GZIPServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//2)输出响应的网页内容
		StringBuffer str = new StringBuffer();
		for (int i = 1; i <= 10000; i++) {
			str.append("abcd");
		}
		System.out.println("压缩前： " + str.toString().getBytes().length);
		// PrintWriter对象： response.getWriter()得到
				//write(): 向浏览器写出内容
		//注意：PrintWriter是没有缓冲功能的，所以才会直接写出到浏览器。
		//   如果能够得到一个带有缓存功能的PrintWriter，那么就可以先把内容写到缓存中（而不是写到浏览器上）
		response.getWriter().write(str.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
