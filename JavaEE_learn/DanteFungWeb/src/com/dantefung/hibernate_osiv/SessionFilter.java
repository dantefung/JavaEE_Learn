package com.dantefung.hibernate_osiv;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dantefung.utils.HibernateUtils;

public class SessionFilter implements Filter{

	// 过滤器业务处理方法
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Session session = null;
		Transaction tx = null;
		try {
			// 1. 创建Session对象
			session = HibernateUtils.getSession();
			// 2. 开启事务
			tx = session.beginTransaction();
			// 3. 放行
			chain.doFilter(request, response);
			
			System.out.println();
		} catch (Exception e) {
			tx.rollback();   // 回滚
			e.printStackTrace();
			// 跳转到错误页面.....
		} finally{
			// 4. 提交事务
			tx.commit();
		}
	}
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}


}
