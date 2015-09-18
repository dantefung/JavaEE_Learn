package com.dantefung.online;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyLogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sessionId = request.getParameter("sessionId");
//		HttpSession session = request.getSession(false);
//		if(session != null)
//		{   // 取出的session一定是我们要删除的那个session吗？
//			session.removeAttribute("loginInfo");
//		}
		
		Map<String, OnlineBean> map = (Map<String, OnlineBean>)getServletContext().getAttribute("onLine");
		if(map != null)
		{
			map.get(sessionId).getSession().removeAttribute("loginInfo");
			map.remove(sessionId);
			System.out.println("注销成功！！");
		}
		response.sendRedirect(request.getContextPath()+"/online/onlineList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
