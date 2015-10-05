package com.dantefung.hibernate_one2one;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {
	private static SessionFactory sf = null;
	
	static 
	{
		sf = new Configuration()// 创建配置管理器对象
		.configure()// 加载主配置文件hibernate.cfg.xml
		.addClass(User.class)// 相当于加载了*.hbm.xml文件
		.addClass(Card.class)
		.buildSessionFactory();
	}
	
	@Test
	public void testOne2One() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		
		try
		{
			// 模拟数据
			User user = new User();
			user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1992-06-11"));
			user.setName("张三");
			user.setSex('男');
			
			Card card = new Card();
			card.setCardNo("44444xxx");
			card.setPlace("广州天河");
			
			// 建立关系  -- 注意：经常忘记
//			user.setCard(card); -- 这样写会报错 ，因为不是由user来维护两者之间的关系
			// 关系由Card（有外键方来维护）
			card.setUser(user);
			
			session.save(user);
			session.save(card);
			// 提交事务
			tx.commit();
		} 
		catch (Exception e) 
		{
			// 回滚
			tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			// 关闭会话
			session.close();
		}
	}
}
