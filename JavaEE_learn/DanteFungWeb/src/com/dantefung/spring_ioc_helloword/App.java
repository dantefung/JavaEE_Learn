package com.dantefung.spring_ioc_helloword;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	/*
	 * 实验结果：
	 * UserDaoImpl.UserDaoImpl():	UserDaoImpl对象创建了！！
		UserServiceImpl.UserServiceImpl()：	UserServiceImpl对象创建了！！
		UserServiceImpl.setUserDao(): 	UserDao对象被注入！！
		--------------------------------------
		UserDaoImpl.save()
		UserDaoImpl.save()
	 * 
	 * 结论：
	 *  容器一启动：
	 *  1.载入配置文件
	 *  2.创建配置文件里面的bean
	 *  3.处理依赖关系：【依赖注入】
	 */
	@Test
	public void testSpringHelloWorld1() {
		ApplicationContext  context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
//		ApplicationContext  context = new ClassPathXmlApplicationContext("com/dantefung/spring_ioc_helloword/bean.xml");
		// of course, an ApplicationContext is just a BeanFactory
		System.out.println("--------------------------------------");
		BeanFactory factory = (BeanFactory) context;
		UserDao userDao = (UserDao)factory.getBean("userDao_");
		userDao.save();
		UserServiceImpl userService = (UserServiceImpl)factory.getBean("userService_");
		userService.getUserDao().save();
	}
	
	@Test
	public void testSpringHelloWorld2() {
//		ApplicationContext  context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("---------容器开始创建！！！--------");
		ApplicationContext  context = new ClassPathXmlApplicationContext("com/dantefung/spring_ioc_helloword/bean.xml");
		// of course, an ApplicationContext is just a BeanFactory
		System.out.println("--------------------------------------");
		UserServiceImpl userService = (UserServiceImpl)context.getBean("userService_");
		userService.getUserDao().save();
	}
}
