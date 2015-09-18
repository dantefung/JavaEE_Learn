package com.adantefung.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 演示ServletConfig对象的作用.
 * 主要用于读取web.xml中配置的servlet的参数数据（因为servletconfig对象已经封装了数据.）
 * 方便客户端程序员开发，而不用自己写一套解析并封装参数的程序.无需硬编码.
 * 
 * ServletConfig是在初始化Servlet的时候被传servlet里面的.
 * @author Dante Fung
 *
 */
public class ServletConfigDemo5 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		// 获取所有的参数,其实就一迭代器.迭代器的前身.
		Enumeration<String> enums = config.getInitParameterNames();
		while(enums.hasMoreElements())
		{
			// 先拿到name，即键key
			String paramName = enums.nextElement();
		    // 在拿到value，即值
			String paramValue = config.getInitParameter(paramName);
			System.out.println(paramName + "="+ paramValue);
			response.getWriter().write(paramName + "="+ paramValue);
		}
		
		// 硬编码
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
