package com.dantefung.online;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnlineServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 每个登录进来的用户都登录成功
		String userName = request.getParameter("userName");
		HttpSession session = request.getSession();
		session.setAttribute("ip", request.getRemoteHost());
		session.setAttribute("loginInfo",userName);// 监听器监听属性的添加
		request.getRequestDispatcher("/online/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
