package com.adantefung.httpsession_app1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//注销逻辑
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		//session.invalidate();//注销HttpSesson对象（不建议）
		session.removeAttribute("loginInfo"); //只需要清空登录时放入的数据即可！！！
		
		//3)转到主页
		response.setHeader("Refresh", "2;url="+request.getContextPath()+"/IndexServlet");
		response.getWriter().write("注销成功！2秒后自动转到主页");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
