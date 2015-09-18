package com.adantefung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 演示servlet多线程安全问题.
 * servlet的机制是：单实例多线程.
 * @author Dante Fung
 *
 */
public class ServletDemo4 extends HttpServlet {
	//成员变量，共享数据，临界资源
	int count = 1;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//首先应该给代码块枷锁，但是锁的范围尽量小（哪里使用的共享数据，就因该加锁）
		synchronized("锁")//锁对象，必须唯一，字符串对象.万能锁.
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write("你是第" + count+"访客");
			try {
			Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
		
/*		synchronized(ServletDemo4.class)//锁对象，必须唯一，类的对象一定是唯一的.
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write("你是第" + count+"访客");
			try {
			Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
