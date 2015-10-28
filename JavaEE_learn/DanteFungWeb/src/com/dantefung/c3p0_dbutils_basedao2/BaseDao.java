package com.dantefung.c3p0_dbutils_basedao2;

import java.util.List;



public interface BaseDao<T>{
	/**
	 * 根据名字查询
	 * @param name 用户名
	 * @return 如果存在此用户，返回对象；否则，返回null
	 */
	public T findByName(Object o);
	
	/**
	 * 根据id查询
	 * @param id 
	 * @return 如果存在此用户，返回实体对象；否则，返回null
	 */
	public T findById(Object o);
	
	/**
	 * 查询所有
	 * @return 返回所有的数据
	 */
	public List<T> findAll();
	
	/**
	 * 增加数据
	 * @return  成功，返回true,否则，返回false
	 */
	public boolean add(Object t);
	
	/**
	 * 根据id删除数据
	 * @return 成功，返回true，否则，返回false
	 */
	public boolean delById(Object o);
	
	/**
	 * 根据名字查询
	 * @param o
	 * @return 成功，返回List<T>,否则，返回null
	 */
	
	public List<T> queryByName(Object o);
}
