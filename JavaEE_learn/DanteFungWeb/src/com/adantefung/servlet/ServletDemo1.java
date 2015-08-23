package com.adantefung.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * GenericServlet
 * 查看API知道，里面只有service()是抽象的，因此是强制用户要继承.
 * @author Dante.Fung
 *
 */
public class ServletDemo1 extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// 设置响应头信息，告诉浏览器用什么编码解析以及我传给它的是什么mime类型的数据.
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		res.getWriter().write("Hello world!!\t"+ "你好！世界！");
	}


}
