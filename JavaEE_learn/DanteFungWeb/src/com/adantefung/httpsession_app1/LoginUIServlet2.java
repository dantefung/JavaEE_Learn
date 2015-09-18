package com.adantefung.httpsession_app1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//登录页面
public class LoginUIServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("<form action='"+request.getContextPath()+"/LoginServlet2' method='post'>");
		out.write("<input type='hidden' name='JSESSIONID' value='"+session.getId()+"'/>");//表单的action使用隐藏域来传递JSESSIONID
		out.write("用户名:<input type='text' name='userName' /><br/>");
		out.write("密码:<input type='password' name='userPwd' /><br/>");
		out.write("验证码:<input type='text' name='code' /><img src='"+request.getContextPath()+"/ImageServlet'/><br/>");
		out.write("<input type='submit' value='登录'/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
