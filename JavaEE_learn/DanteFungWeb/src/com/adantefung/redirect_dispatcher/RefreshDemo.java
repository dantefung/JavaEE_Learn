package com.adantefung.redirect_dispatcher;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 定时刷新，定时刷新跳转
 * @author Dante Fung
 * @since 2015-8-24
 *
 */
public class RefreshDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 
		test2(response);
		//test1(response);
	}
	

	private void test1(HttpServletResponse response) throws IOException {
		
		response.setHeader("Refresh", "2");
		response.setContentType("text/html;charset=utf-8");
		Random ran = new Random();
		response.getWriter().write("随机数：" + ran.nextFloat());
		
	}


	private void test2(HttpServletResponse response) throws IOException {
		// 服务器行为之写：告诉浏览器，我发的是什么，用什么码表解码.
		response.setContentType("text/html;charset=utf-8");
		// 设置响应头信息：给浏览器一些指示.
		response.setHeader("Refresh","3;url=/DanteFungWeb/index.jsp");
		response.getWriter().write("亲，3秒后即将跳转到我的主页");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
