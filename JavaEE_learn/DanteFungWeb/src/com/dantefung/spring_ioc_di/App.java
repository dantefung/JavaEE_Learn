package com.dantefung.spring_ioc_di;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 依赖注入方式：
 * 1.构造方法
 * 2.setter方法注入                                                                    |—  传统配置
 *                                                      |— 父子标签配置 —|— 内部bean
 * 专题：							   |—  xml -|
 *                                        |             |— 标签属性配置 —|— P名称
 *           setter方法注入 -- --|                                         |— 自动装配
 *                                        |—  注解注入 
 *                                        
 *                                                                                         
 * @author Dante Fung 
 *
 */
public class App {

		/*
		 * XML 配置：父子标签配置--传统配置
		 * 实验类： UserDaoImpl UserServiceImpl
		 *  实验一：
		 */
		@Test
		public void testTraditional() {
			// 启动容器
			System.out.println("----------Container is creating ... ...------------");
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
			System.out.println("----------After Container create----------------------");
			
		}
		
		/*
		 * XML配置  父子标签配置--内部Bean
		 * 实验类：
		 * 实验二：
		 * 
		 */
		@Test
		public void testInnerBean() {
			// 启动容器
			System.out.println("----------Container is creating ... ...------------");
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
			System.out.println("----------After Container create----------------------");
			
		}
		
		
		@Test
		public void testPName() {
			// 启动容器
			System.out.println("----------Container is creating ... ...------------");
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
			System.out.println("----------After Container create----------------------");
			
		}		
		
		/*
		 * 注解注入1：
		 *  实验类： Boat  Person 
		 *  实验三：
		 * 
		 */
		@Test
		public void testAnno() {
			// 启动容器
			System.out.println("----------Container is creating ... ...------------");
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
			System.out.println("----------After Container create----------------------");
			System.out.println(((Person)context.getBean("person")).getBoat());
		}		

}
