package com.adantefung.cookies_app2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCookie extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		// 先从request域中取出cookie对象.
//		Cookie[] cookies = request.getCookies();
//		if(cookies != null)
//		{
//			for(Cookie c : cookies)
//			{
//				if("LastAccessTime".equals(c.getName()))
//				{
//					c.setMaxAge(0);
//					response.addCookie(c);
//					response.getWriter().write("删除时间成功");
//				}
//			}
//		}
		
		// 或者直接发一个新的cookie给用户，就不必写这么多代码了
		Cookie c1 = new Cookie("LastAccessTime","");
		c1.setMaxAge(0);
		response.addCookie(c1);
		response.getWriter().write("删除时间成功");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
