package com.dantefung.hibernate_test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.dantefung.utils.WebUtil;

public class CommonTest {
	
private static SessionFactory sf;
	
	static{
		sf = new Configuration()// 创建配置管理器
			.configure()// 加载主配置文件
			//.addClass(Category.class)// 相当于在hibernate.cfg.xml中配置了<mapping resource="com/dantefung/blog/entity/category.hbm.xml"></mapping>
			.buildSessionFactory();// 创建session工厂
	}
	
	@Test
	public void testWebUtil() {
		System.out.println(WebUtil.genUUID());
	}
	
	@Test
	public void testSessionScope()
	{
		Session session = sf.openSession();
		Session session2 = sf.openSession();
		System.out.println(session);
		System.out.println(session2);
		System.out.println(session == session2);
		session.close();
		session2.close();
	}
}
