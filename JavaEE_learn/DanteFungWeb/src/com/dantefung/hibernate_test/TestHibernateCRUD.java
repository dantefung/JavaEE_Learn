package com.dantefung.hibernate_test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.dantefung.hibernate_entity.Admin;
import com.dantefung.utils.MD5Util;
import com.dantefung.utils.WebUtil;

// API
public class TestHibernateCRUD {
	
	private static SessionFactory sf;
	static{
		sf = new Configuration()// 创建配置管理器对象
		     .configure()// 加载主配置文件 hibernate.cfg.xml
//		     .addClass(Admin.class)// 
		     .buildSessionFactory();// 根据加载的主配置文件，加载SessionFactory对象
	}
	
	/*--------------------牛刀小试----------------------------*/
	
	// 用Hibernate，不写sql，查询对象！！
	@Test
	public void testGet()
	{
		// 创建session（维护了一个连接）
		Session session = sf.openSession();
	    // 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作 -- 查询数据
		Admin admin = (Admin)session.get(Admin.class, "79ac562676c846349e6bad5f17db7b12");
		/*
		 * ORM : 1.传一个Admin.class 反射可以拿到所有的字段（对象的属性）
		 *       2.传一个实现了Serializable（只为了范围更加的广）的变量，即查询条件
		 *       3.hibernate内部自己拼出sql.
		 *       
		 *       for instance:
		 *       Hibernate: select admin0_.id as id1_0_, admin0_.aname as aname1_0_, admin0_.apassword as apassword1_0_ from admin admin0_ where admin0_.id=?
		 */
		// Test
		System.out.println(admin);
		// 提交事务
		tx.commit();
		// 关闭session
		session.close();
	}
	
	// 用Hibernate，不写sql,保存对象！
	@Test
	public void testsave()
	{
		// 准备好数据
		Admin admin = new Admin(WebUtil.genUUID(),"andy", MD5Util.md5("456"));
		// 开启session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作 -- 新增数据
		session.saveOrUpdate(admin);
		// session.save(admin);
		// 提交事务
		tx.commit();
		// 关闭session
		session.close();
		
		/*
		Caused by: java.sql.BatchUpdateException: Data truncation: Data too long for column 'apassword' at row 1
		数据库的字段：
		apassword VARCHAR(20)
		改为：
		apassword VARCHAR(32)即可
		 */
	}
	
	/*------------------查询:1.主键查询 2.hql查询（推荐）（半sql半面向对象） 3.criteria 查询 (完全面向对象的查询) 4.本地sql查询（由于一些很复杂的sql  Hibernate支持不了）---------------------------*/
	// 1.主键查询
	// 参见 testGet方法
	
	// 2.hql查询
	// Hibernate query language hibernate提供的面向对象的查询语言
	// *查询一定是对象！！！
	// *一般查询都是指定对象的属性，但高版本的也可以使用字段！推荐：写属性
	// 适合：
	// 有sql语言基础， 使用最多！
	@Test
	public void testGet2() {
		
		// 开启会话（内部维护了一条数据库连接）
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		
		// -- 操作--
//		Query q = session.createQuery("from Admin where id='79ac562676c846349e6bad5f17db7b12'");
		Query q = session.createQuery("from Admin");// 查询全部
		List<Admin> admins = q.list();
		for(Admin admin : admins )
		{
			System.out.println(admin);
		}
		
		// 提交事务
		tx.commit();
		// 关闭会话(连接)
		session.close();
	}
	
	// 3.criteria查询
	// query by criteria  完全面向对象
	@Test
	public void testGet3() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作 --
		Criteria criteria = session.createCriteria(Admin.class);
		criteria.add(Restrictions.eq("id", "79ac562676c846349e6bad5f17db7b12"));
		System.out.println(criteria.list());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	// 本地sql查询
	// 有些复杂的sql无法实现，所以直接执行sql语句
	// 缺点：不能跨数据库平台
	/*
	 * 结果1：
	 * 50309a387e534750a3276e3b8bd9c5fd
		andy
		250cf8b51c773f3f8dc8b4be867a9a02

	   79ac562676c846349e6bad5f17db7b12
	   dante
	   202cb962ac59075b964b07152d234b70
	   
	   结果2：
	   [Admin [id=50309a387e534750a3276e3b8bd9c5fd, aname=andy, apassword=250cf8b51c773f3f8dc8b4be867a9a02], Admin [id=79ac562676c846349e6bad5f17db7b12, aname=dante, apassword=202cb962ac59075b964b07152d234b70]]
	 */
	@Test
	public void testGet4() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作 --
		// 方式一： 查询所有记录，返回结果封装在一个Object[]内，然后放到list中
		SQLQuery query = session.createSQLQuery("select * from admin");
		List<Admin[]> admins = query.list();
		for(Object[] adminArr : admins)
		{
			for(Object admin : adminArr)
			{
				System.out.println(admin);
			}
			System.out.println();
		}
		
		System.out.println("============");
		// 方式二：查询的结果封装为对象
		SQLQuery q = session.createSQLQuery("select * from admin");
		q.addEntity(Admin.class);
		System.out.println(q.list());
		
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*-------------------------更新-------------------------*/
	// 1.参见 testsave()方法 save()
	//  session更新相关方法
	@Test
	public void testUpdate() {
		// 开启会话
		Session session = sf.openSession();

		// 模拟数据
		Admin admin = new Admin("79ac562676c846349e6bad5f17db7b12","dante", "202cb962ac59075b964b07152d234b70");
		
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作 -- 
		// 1.保存
//		session.save(new Admin());
		
		// 2.更新(根据主键更新，主键必须在数据库中存在)
		session.update(admin);
		//   先查询再更新
//		  Admin admin = (Admin) session.get(Admin.class,"79ac562676c846349e6bad5f17db7b12");
//		  admin.setAname("dante1");
//		  session.update(admin);
		
		// 3.保存或更新
		// * 如果有设主键，执行更新  注意：主键不存在就报错
		// * 没有主键设置主键，就 保存
//		Admin a = new Admin();
//		a.setId(WebUtil.genUUID());
//		session.saveOrUpdate(a);
		
		// 4.删除
//		Admin ad = new Admin();
//		ad.setId("79ac562676c846349e6bad5f17db7b12");
//		session.delete(ad);
		
		// 或者 先查询 再删除
//		Admin admins = (Admin)session.get(Admin.class, "79ac562676c846349e6bad5f17db7b12");
//		session.delete(admins);
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	
	
}
