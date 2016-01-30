package com.dantefung.mybatis.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSessionFactory {

	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	private static SqlSessionFactory sessionFactory;
	private static String CONFIG_FILE_LOCATION = "myBatis-config.xml";

	static {
		buildSessionFactory();
	}

	private MybatisSessionFactory() {
	}

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 * 
	 * @return SQlSession
	 * @throws Exception
	 */
	public static SqlSession getSession() throws Exception {
		SqlSession session = (SqlSession) threadLocal.get();

		if (session == null) {
			if (sessionFactory == null) {
				buildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

		return session;
	}

	/**
	 * Rebuild session factory
	 * 
	 */
	public static void buildSessionFactory() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(CONFIG_FILE_LOCATION);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Close the single session instance.
	 * 
	 * @throws Exception
	 */
	public static void closeSession() throws Exception {
		SqlSession session = (SqlSession) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	/**
	 * return session factory
	 * 
	 */
	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}

}