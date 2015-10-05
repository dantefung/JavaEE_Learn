package com.dantefung.hibernate_test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.dantefung.hibernate_entity.Article;
import com.dantefung.hibernate_entity.Category;
import com.dantefung.hibernate_entity.Comments;
import com.dantefung.hibernate_entity.Label;
import com.dantefung.utils.WebUtil;
/**
 * 多对一： 1.保存 2.查询
 * 多的一方来维护关系
 * @author Dante Fung
 *
 */
public class TestAssociationCRUD {

	private static SessionFactory sf;
	
	static{
		sf = new Configuration()// 创建配置管理器
			.configure()// 加载主配置文件
			//.addClass(Category.class)// 相当于在hibernate.cfg.xml中配置了<mapping resource="com/dantefung/blog/entity/category.hbm.xml"></mapping>
			.buildSessionFactory();// 创建session工厂
	}
	
	/*----------------------多对一：1.保存 2.查询--------------------------*/
	
	// 1.保存数据
	@Test
	public void testMany2OneSave() {
		// 开启session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		/*   n              1
		 * Article   ---  Category  
		 * 
		 * 模拟数据
		 */
		Category category = new Category();
		category.setCname("散文");
		category.setCid("8bae7c2a77334ed2abc7dd0dd7c2e7c4");
		category.setDescr("形散而神不散");
		
		Article article1 = new Article(
				"a3c041e1deb0494a87b5949b24f33d55",
				"最美中国",
				"8bae7c2a77334ed2abc7dd0dd7c2e7c4",
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss"),
				"美立中国",
				"文化古国",
				100,
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss")
				 );
		
		Article article2 = new Article(
				"1b9ef0137c984131bd8a397eee7a8033",
				"明天会更好",
				"8bae7c2a77334ed2abc7dd0dd7c2e7c4",
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss"),
				"阳光总在风雨后",
				"笑，是永远的",
				110,
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss")
				);
		
		// *建立关系  -- 注意：这里容易忘了
		article1.setCategory(category);
		article2.setCategory(category);
		
		// 保存数据
		/* 执行的先后顺序没有关系.
		 * 
		 * 一下保存的顺序，生成的sql比较多，不推荐使用.
		 * session.save(article1);
		   session.save(article2);
		   session.save(category);
		   
		   Hibernate: insert into article (title, digest, content, acount, atimestamp, label, cid, aid) values (?, ?, ?, ?, ?, ?, ?, ?)
           Hibernate: insert into article (title, digest, content, acount, atimestamp, label, cid, aid) values (?, ?, ?, ?, ?, ?, ?, ?)
           Hibernate: insert into category (cname, descr, cid) values (?, ?, ?)
           Hibernate: update article set title=?, digest=?, content=?, acount=?, atimestamp=?, label=?, cid=? where aid=?
           Hibernate: update article set title=?, digest=?, content=?, acount=?, atimestamp=?, label=?, cid=? where aid=?
		 *
		 */
		session.save(category);
		session.save(article1);
		session.save(article2);
		
		// 提交事务
		tx.commit();
		// 关闭回话
		session.close();
		
	}
	
	// 通过一方获取另一方
	@Test
	public void testMany2OneGet() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 获取
		Article a = (Article)session.get(Article.class, "1b9ef0137c984131bd8a397eee7a8033");
		System.out.println(a);
		// 获取关联的数据，用到数据才查，懒加载
		System.out.println(a.getCategory());
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
		
		/*
		 *  result:
		 *  Article [aid=1b9ef0137c984131bd8a397eee7a8033, title=明天会更好, cid=null, adate=null, digest=阳光总在风雨后, content=笑，是永远的, acount=110, atimestamp=2015-09-28 08:17:25.0, label=阳光依旧，我心依旧向阳]
			Hibernate: select category0_.cid as cid0_0_, category0_.cname as cname0_0_, category0_.descr as descr0_0_ from category category0_ where category0_.cid=?
			Category [cid=8bae7c2a77334ed2abc7dd0dd7c2e7c4, cname=散文, descr=形散而神不散]

		 * 
		 */
	}
	
	/*---------------------------一对多： 1.保存 2.查询-----------------------------------*/
	
	@Test
	public void testOne2ManySave() {
		// 1.开启会话
		Session session = sf.openSession();
		// 2.开启事务
		Transaction tx = session.beginTransaction();
		// 3.操作
		// 3.1 模拟数据
			// -- 瞬时状态的对象
		// 多
		Comments comments = new Comments();
		comments.setAid("8988d599b0b5448aa57de3a6c4b5fdda");
		comments.setCdate(WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss"));
		comments.setCmid("a41a4176fa654444b4930a13175c4e39");
		comments.setCname("andy");
		comments.setContent("文章写得不错！！");
		
		// 一
		Article article = new Article(
				"8988d599b0b5448aa57de3a6c4b5fdda",
				"最美中国",
				"8bae7c2a77334ed2abc7dd0dd7c2e7c4",
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss"),
				"美立中国",
				"文化古国",
				100,
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss")
				 );
	
		// 建立关系
		article.getComments().add(comments);
		
		// 3.2 保存数据
		// 一的一方维护关系
		session.save(article);
		session.save(comments);// 
		
		// 4提交事务
		tx.commit();
		// 关闭回话
		session.close();
	}
	

	@Test
	public void testOne2ManyGet() {
		// 1.开启会话
		Session session = sf.openSession();
		// 2.开启事务
		Transaction tx = session.beginTransaction();
		// 3.操作
		// 查询
		Article article =(Article) session.get(Article.class,"8988d599b0b5448aa57de3a6c4b5fdda");
		System.out.println(article);
		// 4提交事务
		tx.commit();
		// 关闭回话
		session.close();
	}
	
	/*-----------------------多对多：1.保存数据 2.获取数据 3.解除关系 4.删除数据-------------------------*/
	
	@Test // 报类型转换错误
	public void testMany2ManySave() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
	    // 模拟数据
		Label label1 = new Label();
		label1.setId(WebUtil.genUUID());
		label1.setLname("传统文化");
		
		Label label2 = new Label();
		label2.setId(WebUtil.genUUID());
		label2.setLname("一曲清词");
		
		Article article = new Article(
				WebUtil.genUUID(),
				"最美中国",
				"8bae7c2a77334ed2abc7dd0dd7c2e7c4",
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss"),
				"美立中国",
				"文化古国",
				100,
				WebUtil.genMkDate("yyyy-MM-dd hh:mm:ss")
				 );
		
		// 建立关系  （多对多，article方来维护关系）
		article.getLabel().add(label1);
		article.getLabel().add(label2);
		// 保存
		session.save(article);
		session.save(label1);
		session.save(label2);
		
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	// 查询数据
	@Test
	public void testMany2ManyGet() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
		// 查询
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	// 解除关系
	@Test
	public void testMany2ManyClear() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
	    // 清除关系
		
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	// 删除数据
	@Test
	public void testMany2ManyDelete() {
		// 开启会话
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// -- 操作
	    // 删除
		// 提交事务
		tx.commit();
		// 关闭会话
		session.close();
	}
	
	/*-----------------------------一对一：1.保存 2.查询----------------------------------------*/


}
