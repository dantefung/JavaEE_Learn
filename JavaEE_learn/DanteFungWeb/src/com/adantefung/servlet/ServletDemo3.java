package com.adantefung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 如何写HttpServlet的servlet程序
 * @author Dante Fung
 *
 */
public class ServletDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应头信息，告诉浏览我带给它的数据Mime类型及编码格式
		response.setCharacterEncoding("utf-8");
		// mime类型 与 普通文件类型的映射关系可在tomcat /conf/web.xml中查看.
		response.setContentType("text/html");
		// response对象封装了“你好，世界！！”这条字符串信息.
		response.getWriter().write("你好，世界！！");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
