package com.dantefung.hibernate_extends;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {
	
	private static SessionFactory sf = null;
	
	static
	{
		sf = new Configuration()
		.configure()
//		.addClass(Animal.class)
		.buildSessionFactory();
	}
	
	@Test
	public void testExtendsConfig() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 模拟数据
		Dog dog = new Dog();
		dog.setName("小狗");
		dog.setPlay("跟主人玩");
		Cat cat = new Cat();
		cat.setName("威威猫");
		cat.setCatching("抓老鼠");
		
		session.save(cat);
		session.save(dog);
		
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
}
