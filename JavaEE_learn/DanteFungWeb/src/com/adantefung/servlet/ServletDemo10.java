package com.adantefung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 转发.
 * 生活的例子：浏览器（男孩）
 * -----送iphone6--->ServletContextDemo6(女孩)
 * ---转送给-->ServletDemo10（女孩心爱的男生）
 * @author Dante Fung
 *
 */
public class ServletDemo10 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().write("Hello~~~I 'm ServletDemo10, I get the iphone6!!");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
