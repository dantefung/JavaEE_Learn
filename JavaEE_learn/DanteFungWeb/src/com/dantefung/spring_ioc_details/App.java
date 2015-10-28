package com.dantefung.spring_ioc_details;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 对象创建的一些细节
 * 注意：实验时，将配置文件的其他bean注释掉. 以免造成干扰
 * @author Dante Fung
 *
 */
public class App {
	/*
	 *  容器一启动：
	 *  1.载入配置文件
	 *  2.创建配置文件里面的bean
	 *  3.处理依赖关系：【依赖注入】
	 */

	// 细节1：
	/*
	 *  1.1 Spring创建bean的过程  
	 *                                     |
	 *                             单例_|_多例
	 *                              |           |——在每次用的时候创建！
	 *   容器创建时创建 -- |
	 *   延迟创建(用的时候创建)-- |
	 *   
	 *   对象的生命周期；
	 *   scope="singleton"  单例(默认)   lazy-init="default"（饿汉式） lazy-init="true" (懒汉式)  
	 *   scope="prototype" 多例   
	 */
	
	/*                                                          _单例对象 -->   必须调用容器的ac.destroy;  [备注：ClassPathXmlApplicationContext ac]
	 *  1.2 对象的初始化(init)与销毁(destory)--|_多例对象 -->   容器不知道具体销毁哪一个.
	 *                     |
	 *             单例_|_多例
	 *                都初始化      
	 */
	
	// 细节2：配置文件中    id  &  name 的区别？
	
	@Test
	public void testExperiment1() {
		
		/*
		 *  实验一： 证明：spring容器创建对象默认单例且是饿汉式创建（容器启动时创建！！）
		 */
		
		// 启动容器
		System.out.println("-----------container creating ... ...----------");
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("------------after container create-----------");
	     
		 User user1 = context.getBean(User.class);
		 User user2 = context.getBean(User.class);
		 System.out.println("scope=\"singleton\"\t user is singleton?\t" + (user1 == user2));
	}
	
	@Test
	public void testExperiment2()
	{
	      /*
	       *  实验二：单例对象懒汉式创建
	       */
		// 启动容器
		System.out.println("-----------container creating ... ...----------");
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("------------after container create-----------");
		
		Admin admin1 = (Admin)context.getBean("admin");
		Admin admin2 = (Admin)context.getBean("admin");
		System.out.println( "scope=\"singleton\"\t  is singleton?\t" + (admin1 == admin2));
	}
	
	@Test
	public void testExperiment3()
	{
		/*
		 * 实验三：多例对象
		 */
		// 启动容器
		System.out.println("-----------container creating ... ...----------");
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("------------after container create-----------");
		
		Dog dog1 = (Dog)context.getBean("dog");
		Dog dog2 = (Dog)context.getBean("dog");
		System.out.println( "scope=\"prototype\"\t  is prototype?\t" + (dog1 != dog2));
		
	}
	
	@Test
	public void testExperiment4()
	{
		/*
		 * 实验四：单例对象： init  destroy
		 */
		// 启动容器
		System.out.println("-----------container creating ... ...----------");
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("------------after container create-----------");
		
		Dog dog1 = (Dog)context.getBean("dog");
		Dog dog2 = (Dog)context.getBean("dog");
		System.out.println( "scope=\"prototype\"\t  is prototype?\t" + (dog1 != dog2));
		ClassPathXmlApplicationContext ac=(ClassPathXmlApplicationContext)context;
		ac.destroy();
	}
	
	@Test
	public void testExperiment5()
	{
		/*
		 * 实验五：多例对象： init  destroy
		 */
		// 启动容器
		System.out.println("-----------container creating ... ...----------");
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml",this.getClass());
		System.out.println("------------after container create-----------");
		
		Tank tank1 = (Tank)context.getBean("tank");
		Tank tank2 = (Tank)context.getBean("tank");
		System.out.println( "scope=\"prototype\"\t  is prototype?\t" + (tank1 != tank2));
		ClassPathXmlApplicationContext ac=(ClassPathXmlApplicationContext)context;
		ac.destroy();
	}
	
	
}
