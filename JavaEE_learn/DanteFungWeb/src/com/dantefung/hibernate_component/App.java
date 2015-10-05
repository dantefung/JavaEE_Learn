package com.dantefung.hibernate_component;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {

	private static SessionFactory sf = null;
	
	static 
	{
		sf  = new Configuration()
		.configure()
		.addClass(Car.class)
		.buildSessionFactory();
	}
	
	@Test
	public void testComponentMapping() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
	    // 模拟数据
		Wheel wheel = new Wheel();
		wheel.setSize(100);
		wheel.setCount(4);
		
		Car car = new Car();
		car.setName("兰博基尼");
		car.setWheel(wheel);
		
		session.save(car);
		
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
}
