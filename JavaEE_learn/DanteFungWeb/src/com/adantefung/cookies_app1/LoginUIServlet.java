package com.adantefung.cookies_app1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 登录界面:记住用户名和密码
 * @author Dante Fung
 *
 */
public class LoginUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		///////////////////////////////删除cookie逻辑///////////////////////////////////
		// 先解决乱码问题
		response.setContentType("text/html;charset=utf-8");
		/*---------------------------方式一：修改原来的cookie的生命----------------------------------*/
		// 判断是否是从登陆成功页面，点击了删除cookie超链接过来的.
		// 取出用户带过来的唯一的cookie
		Cookie[] cookies1 = request.getCookies();
		String cookieName;
		if(cookies1!=null)
		{
			for(Cookie c : cookies1)
			{
				if(c!=null && (cookieName=request.getParameter("cookieName")) != null)
				{
					if(cookieName.equals(c.getName()))
					{
						// 杀死cookie
						c.setMaxAge(0);
						// 修改原来的cookie的生命
						response.addCookie(c);
						response.getWriter().write("你的cookie，已经帮你删除");
						return;
					}
				}
			}
		}
		
		/*----------------------方式二：给用户发一个新的cookie--------------------------*/
//		if(request.getParameter("cookieName")!=null)
//		{
//			Cookie c1 = new Cookie("username_password","");
//			c1.setMaxAge(0);
//			response.addCookie(c1);
//			response.getWriter().write("你的cookie，已经帮你删除");
//			return;
//		}

		
		
		/////////////////////////////登录界面逻辑////////////////////////////
		
		String checked = null;
		// 由于cookie是伴随着请求来的,因此，取出cookie要找request对象.
		Cookie[] cookies = request.getCookies();
		String username = "";
		String password = "";
		// 首先要判断cookie是否为null，即检查用户是否带着cookie数据过来.
		if(cookies != null)
		{
			// 用户是带着cookie数据过来的，遍历cookie
			for(Cookie c : cookies)
			{
				// 从cookie中取出数据. name - value, 取出cookie的值.
				String value = c.getValue();
				if(value != null)
				{
					checked = "checked";
				}
				username = value.split("-")[0];
				username = URLDecoder.decode(username, "UTF-8");
				password = value.split("-")[1];
			}
		}
		// 构造出登录界面.
		PrintWriter out = response.getWriter();
		out.write("<form action='"+request.getContextPath()+"/LoginServlet' method='post'>");
		out.write("用户名：<input type='text' name='username' value='"+username+"'/><br/>");
		out.write("密码：<input type='password' name='password' value='"+password+"'/><br/>");
		// 如果用户勾选了记住密码的多选框，点击提交后就会把value的值给传递过来.
		out.write("记住用户名和密码：<input type='checkbox' name='remember' value='succ' "+checked+"/><br/>");
		out.write("<input type='submit' value='提交'/>");
		out.write("</form>");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
