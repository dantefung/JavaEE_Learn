package com.adantefung.cookies_app2;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 上次访问的时间
 * @author Dante Fung 
 *
 */
public class LastAccessTimeDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 统一解决乱码问题
		response.setContentType("text/html;charset=utf-8");
		// 统一给一个输出流
		PrintWriter out = response.getWriter();
		// 先判断用户是否是初次访问
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if("LastAccessTime".equals(c.getName()))
				out.write("你上次访问的时间为：" + c.getValue());
				out.write("<a href='"+request.getContextPath()+"/ClearCookie'>清空时间</a>");
			}
		}
		else
		{
			out.write("您是第一次访问本网站");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			Date date = new Date();
			String currentTime = sdf.format(date);
			Cookie cookie = new Cookie("LastAccessTime",currentTime);
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
