package com.dantefung.spring_ioc_create;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 创建对象：有参构造
 * @author Dante Fung
 *
 */
public class App {
	
	@Test
	public void testConstructorWithParam() {
		
		// 启动容器
		System.out.println("-------Container creating ... ...  ------");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("--------After Container create-------------------");
		
		// print 
		Admin admin = context.getBean(Admin.class);
		System.out.println(admin);
		User user = context.getBean(User.class);
		System.out.println(user);
		Person person = context.getBean(Person.class);
		System.out.println(person);
		
		/*
		 *  查看创建的工厂
		 */
		System.out.println(context.getBean("factory"));
		System.out.println(context.getBean("factory_"));
		
		/*
		 * 从实验的结果，可得出如下的结论：
		 * 工厂创建的对象：
		 *        用到的时候才创建！！
		 */
		 
		Man man = (Man)context.getBean("man_static");
		System.out.println(man);
		Man man1 = (Man) context.getBean("man_");
		System.out.println(man1);
	}

}
