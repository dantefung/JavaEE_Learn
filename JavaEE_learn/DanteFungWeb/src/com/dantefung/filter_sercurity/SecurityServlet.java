package com.dantefung.filter_sercurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		System.out.println(userName);
		HttpSession session = request.getSession();
		// TO-DO 登录验证逻辑  成功则将用户名放入到session域中
		session.setAttribute("userInfo", userName);
		response.getWriter().write("恭喜登录成功！！");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
