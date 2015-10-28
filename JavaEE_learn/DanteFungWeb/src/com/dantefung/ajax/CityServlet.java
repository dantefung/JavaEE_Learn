package com.dantefung.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CityServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf8");
		
		String method = request.getParameter("method");
		String country = "";
		if( "GET".equals(method) )
		{
			System.out.println(request.getParameter("country"));
			 country = new String(request.getParameter("country").getBytes("ISO-8859-1"),"UTF-8");
//			 country = new String(request.getParameter("country").getBytes("ISO-8859-1"),"GBK");
		}
		else
		{
			 country = request.getParameter("country");
		}
		
		if( "中国".equals(country) )
		{
			response.getWriter().write("你选择了中国！！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
