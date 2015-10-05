package com.dantefung.hibernate_test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.dantefung.hibernate_entity.Admin;
import com.dantefung.hibernate_entity.Article;

/**
 * HQL: hibernate query language
 * 1.执行更新
 * 2.query 查询相关方法
 *     2.1 查询全部
 *     2.2 查询返回唯一结果
 *     2.3 查询指定的列
 *     2.4 查询指定的列，自动封装为对象
 *     2.5 条件查询
 *     2.6 命名hql查询（为了提升hql的可维护性，可以把hql写到xml中，在程序里读取！！）
 *     2.7 占位符查询：普通占位符查询、带命令参数的占位符查询
 *     2.8 测试：连接查询：内连接、左外连接、右连接
 *     2.9 分组查询
 *     3.0 Query接口执行更新方法
 *  
 *     
 * 3.统计总记录数
 *     3.1 方式一（了解）
 *     3.2 方式二（掌握）
 *     
 * 4.分页查询
 *      
 * @author Dante Fung
 *
 */
public class TestHQL {
	
	private static SessionFactory sf = null;
	
	static
	{
		sf = new Configuration()// 创建配置管理器对象
		.configure()// 加载hibernate.cfg.xml文件
		.buildSessionFactory();// 创建session工厂
	}
	
	
	/*
	 * 2.1查询全部
	 */
	@Test
	public void testQueryAll() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 查询全部
		Query query = session.createQuery("from Category");
		System.out.println(query.list());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 *  2.2 查询返回唯一结果
	 * 
	 */
	@Test
	public void testQueryUniqueResult() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		Query query = session.createQuery("from Category where id='72e195a10fb04943a2022df1748f0406'");
		Object result = query.uniqueResult();
		System.out.println(result);
		
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 *  2.3 查询制定的列
	 */
	@Test
	public void testAssignColumn(){
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		Query query = session.createQuery("select a.id,a.aname from Admin a");
		// 用List<Object[]>接收，不会自动封装为对象
		List<Object[]> adminArr = query.list();
		for(Object[] elem : adminArr)
		{
			for(Object o : elem)
			{
				System.out.println(o);
			}
		}
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	/*
	 * 2.4 查询指定的列，自动封装为对象
	 */
	@Test
	public void testAssignColumnQueryEncapsulateObj() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 查询指定的列，自动封装为对象
		/*
		 * 注意： 必须要有相应的构造方法 
		 */
	    Query query = session.createQuery("select new Admin( a.id,a.aname ) from Admin a");
	    List<Admin> result = query.list();// 指定具体的类型，减少类型转换
	    System.out.println(result);
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 * 2.5 条件查询
	 */
	@Test
	public void testQueryByCondition() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		/*
		 * 条件查询：
		 * 1.一个条件
		 * 2.多个条件： and、or、in、between...and、having
		 * 3.模糊查询
		 * 
		 * 注意：写*会报错
		 */
		// 1.一个条件查询
		Query query = session.createQuery("from Article where aid='1b9ef0137c984131bd8a397eee7a8033'");
		System.out.println("一个条件： \t" + query.list() +"\r\n");
		// 2.多个条件:
		// 2.1 or
		Query query2 = session.createQuery("from Article where aid='1' or aid='a3c041e1deb0494a87b5949b24f33d55' ");
		System.out.println("多个条件查询 -- -- or：\t" + query2.list()+"\r\n");
		// 2.2 and
		Query query3 = session.createQuery("from Article where aid='a3c041e1deb0494a87b5949b24f33d55' and cid='8bae7c2a77334ed2abc7dd0dd7c2e7c4' ");
		System.out.println("多个条件查询 -- -- and：\t" + query3.list()+"\r\n");
		// 2.3 in指令
		Query query4 = session.createQuery("from Label where id in('97f55a7a80f446f4a18bb8bc5a5b811f','e88d6d21a42c4b5fb6a24e912168f3b1')");
		System.out.println("多个条件查询 -- -- in：\t" + query4.list()+"\r\n");
		// 2.4 between...and
		Query query5 = session.createQuery("from Article where acount between 100 and 110");
		System.out.println("多个条件查询 -- -- between...and：\t" + query5.list()+"\r\n");
		// 2.5 having
		Query query7 = session.createQuery("from Article group by acount having digest like '%中%'");
		System.out.println("多个条件查询 -- -- having：\t" + query7.list()+"\r\n");
		// 3.模糊查询
		Query query6 = session.createQuery("from Article where digest like '%在%' ");
		System.out.println("模糊查询 -- -- like \t" + query6.list() + "\r\n");
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 * 2.6 命名hql查询（为了提升hql的可维护性，可以把hql写到xml中，在程序里读取！！）
	 */
	@Test
	public void testNameHql() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 命名hql查询
		Query query = session.getNamedQuery("getById");
		System.out.println(query.list());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 * 2.7占位符查询
	 */
	@Test
	public void testPlaceholderQuery() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 2.7.1 普通占位符查询
		Query query = session.createQuery("from Article where aid=? ");
		query.setString(0, "1b9ef0137c984131bd8a397eee7a8033");
		System.out.println("普通占位符查询:\t"+query.list());
		// 2.7.2 带命令参数的占位符查询
		Query query2 = session.createQuery("from Article where aid=:id_ ");
		query2.setString("id_", "a3c041e1deb0494a87b5949b24f33d55");
		System.out.println("带命令参数的占位符查询：\t" + query2.list());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 *  2.8 连接查询：内连接、左外连接、右外连接
	 */
	@Test
	public void testJoinQuery() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 2.8.1 内连接查询
		/*
		 * 注意： 写a.*,l.*  会出错
		 */
		Query query = session.createQuery("select a.digest,l.lname " + // 查询两张表的字段
				"from Article a " + // 表
				"inner join a.label l" );// 逻辑条件
		List<Object[]> olist = query.list();
		System.out.print("内连接查询：\t");
		for(Object[] objArr : olist)
		{
			for(Object o : objArr )
			{
				System.out.print(o + "\t");
			}
		}
		// 迫切内连接
		/*
		 * 把Article查询出来的，顺便把label查询出来填充到Article中
		 * 
		 */
		System.out.println();
		System.out.println("迫切内连接查询：");
		Query query2 = session.createQuery("from Article a inner join fetch a.label l ");
		List<Object[]> result = query2.list();
		for(Object obj : result)
		{
			Article article = (Article)obj;
			System.out.println("\r\n\t"+article);
			Iterator it = article.getLabel().iterator();
			while(it.hasNext())
			{
				System.out.println("\t"+it.next());
			}
		}
		
		System.out.println();
		// 2.8.2 左外连接查询
		Query query3 = session.createQuery("from Article a left join a.label  ");
		List<Object[]> list = query3.list();
		System.out.println("左外连接查询：");
		for(Object[] oArr : list)
		{
			System.out.println("\t"+Arrays.toString(oArr));
		}
		// 2.8.4 右外连接查询
		Query query4 = session.createQuery("from Article a right join a.label  ");
		List<Object[]> list1 = query3.list();
		System.out.println("右外连接查询：");
		for(Object[] oArr : list1)
		{
			System.out.println("\t"+Arrays.toString(oArr));
		}
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	
	/*
	 * 2.9 分组查询 
	 */
	@Test
	public void testGroupQuery() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		Query query = session.createQuery("from Article group by acount");
		System.out.println("分组查询 -- -- group by :\t" + query.list());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 *  3.0 Query 执行更新方法
	 */
	@Test
	public void testUpdate() {
		// 开启回话（内部维护着一条连接）
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// -- HQL
		// Query接口
		// INSERT INTO admin(id,aname,apassword) VALUES('e64939e3cd934fcf8ad63f1c3493f54c','andy','0913');
		Query query = session.createQuery("delete from Admin where id='e64939e3cd934fcf8ad63f1c3493f54c'");
		// 执行更新
		query.executeUpdate();
		// 提交事务
		tx.commit();
		// 关闭回话
		session.close();
	}
	
	/*
	 *  3.统计总记录数
	 */
	@Test
	public void testCount() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		
		// 方式一：（熟悉hibernate的api）统计记录数
		Query query = session.createQuery("from Article");
		// 得到一个滚动的结果集
		ScrollableResults sr = query.scroll();
		// 滚动到最后一行
		sr.last();
		// 获取总的行数
		int totalCount = sr.getRowNumber() + 1;
		System.out.println("数据库中Article表的记录数：\t" + totalCount+"\r\n");
		
		// 方式二：统计记录数
		Query q = session.createQuery("select count(*) from Article");
		Long count =(Long) q.uniqueResult();
		System.out.println("数据库中Article表的记录数：\t" + count);
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*
	 * 4.分页查询
	 */
	@Test
	public void testPage() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// Query接口
		Query query = session.createQuery("from Article");
		query.setFirstResult(1);// limit的第一个参数：设置查询的起始行
		query.setMaxResults(2);// limit的第二个参数：查询返回的行数
		System.out.println(query.list());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
}
