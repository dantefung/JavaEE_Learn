package com.dantefung.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


// 工具类
public class HibernateUtils {

	// 初始化sessionFactory对象
	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	// 返回session
	public static Session getSession(){
		return sf.getCurrentSession();
	}
}
