package com.adantefung.httpsession_app1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		//1) 登录逻辑： 任何人都能登录成功.不做用户名和密码的验证，只做验证码的验证
		String username = request.getParameter("userName");
		
	    //2) 把用户数据放入HttpSession中
		HttpSession session = request.getSession();
		session.setAttribute("loginInfo", username);
		
		// 验证码验证
		String code = request.getParameter("code");
		// 从HttpSession对象中取出生成的验证码
		String scode = (String)session.getAttribute("sCode");
		if(!code.equals(scode))
		{
			response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/IndexServlet");
			response.getWriter().write("验证码错误,2秒后自动转到登录页面");
		}
		
		// 3)转到主页
		response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/IndexServlet");
		response.getWriter().write("登录成功！！2秒钟之后跳回主页");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
