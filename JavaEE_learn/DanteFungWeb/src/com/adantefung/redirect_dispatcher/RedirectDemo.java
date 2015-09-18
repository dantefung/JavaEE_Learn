package com.adantefung.redirect_dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 请求重定向.
 * 简单的解释：你找我借钱，我让你找他借.
 * 特点：
 *    1.有两次请求，浏览器的地址栏会发生改变.
 *    2.由于有两次请求，因此产生两个request对象，重定向到servlet
 *    无法通过request对象拿到上一个servlet的存在request域的数据.
 *    因为此时的request是被新创建出来的
 * @author Dante Fung
 *
 */
public class RedirectDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 
		
		request.setAttribute("p","ppp");
		// 请求重定向  浏览器行为，/ （斜杠） 代表地址栏虚拟主机的根目录
		//response.sendRedirect("/DanteFungWeb/SolveEncodingProblem");
		// 转发，你找我借钱，我帮你去借钱。
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/SolveEncodingProblem");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
