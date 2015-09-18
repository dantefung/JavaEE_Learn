package com.dantefung.servletcontextlistener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * 	javax.servlet 
	Interface ServletContextListener
	All Superinterfaces: 
	java.util.EventListener 
	
	注意： 一定要在web.xml中注册，否则，web服务器不会知道这个类的存在。
 */
public class MyServletContextListener implements ServletContextListener,ServletContextAttributeListener{

	// 监听Servlet的创建与销毁
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("web服务器加载,我已经监听到了： ServletContext被创建了！！！");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web容器卸载，我已经监听到了：ServletContext被销毁了!!!");
	}

	// 监听属性   添加
	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		String name = scab.getName();
		Object value = scab.getValue();
		System.out.println("添加：" + name + "-" + value);
	}

	// 监听属性 修改
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		String name = scab.getName();
		Object value = scab.getValue();
		System.out.println("修改" + name + "-" + value);
	}
	
	// 监听属性 删除
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		String name = scab.getName();
		ServletContext context = scab.getServletContext();
		Object value = context.getAttribute("name");
		System.out.println("删除：" + name + "-" + value);
	}


}
