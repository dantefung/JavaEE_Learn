package com.adantefung.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Simulate Servlet ' s Life Circle
 * @author Dante Fung
 *
 */
public class ServletLifeCircle extends HttpServlet {
	
	/**
	 * 第一次访问servlet对象被创建，然后就一直在内存中.
	 * 由web容器调用，只调用一次(单例的)
	 */
	public ServletLifeCircle()
	{
		System.out.println("Servlet诞生了！！！");
	}
	
	/**
	 * 诞生后调用初始化方法（调用一次）
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet初始化!");
	}
	
	/**
	 * 活着.
	 * web应用不卸载servlet对象就一直在驻留在内存中.
	 * 调用一些方法：提供服务，响应请求.
	 * 
	 * service方法，内部都是一些模板方法，留给客户端程序员开发.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Servlet开始服务");
	}
	
	/**
	 * 死亡.
	 * web应用卸载或者web服务停止，只调用1次.
	 */
	@Override
	public void destroy() {
		System.out.println("Servlet被摧毁！");
	}
	
}
