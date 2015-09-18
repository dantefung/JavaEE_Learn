package com.adantefung.httpsession_app1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//首页
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//1)从HttpSession对象中取出登录用户的时间
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("loginInfo");
		
		//2)userName不为null就是登录了
		if(userName!=null){
			out.write("欢迎回来，"+userName+"，<a href='"+request.getContextPath()+"/LogoutServlet'>退出</a>");
		}else{
			String url = response.encodeRedirectURL(request.getContextPath()+"/LoginUIServlet2");
			out.write("你好，请求先<a href='"+url+"'>登录</a>或者注册");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
