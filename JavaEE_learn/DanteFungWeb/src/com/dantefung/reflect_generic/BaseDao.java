package com.dantefung.reflect_generic;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.dantefung.utils.C3P0Util;

/**
 * 数据库访问操作的基类
 * 
 * 技术：
 *     反射 + 泛型
 * @author Dante Fung
 * @since 2015.9.13
 *
 * @param <T> 类类型
 */
public class BaseDao<T>{
	
	private Class targetClazz;
	private String tableName;
	private Object[] params;

	QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	private String fields="";
	private String signs="";
	
	public BaseDao()
	{
	    ////////////////////获取表名////////////////////	
	
		// 约定：类名和数据库表名要一致
		
		// 获取本类(指具体的子类)的class对象
		Class clazz = this.getClass();
		// TEST: 获取本类类名
		String className = clazz.getSimpleName();		
		System.out.println("本类为：" + className);
		
		////////////////////获取泛型参数的类型//////////////////////
		
		// 获取父类的泛型类型    参数化类型
//		Type type = clazz.getGenericSuperclass();
		ParameterizedTypeImpl type = (ParameterizedTypeImpl) clazz.getGenericSuperclass();
		// 获取实参类型的Type对象数组
		Type[] tp = type.getActualTypeArguments();
		// 获取类的第一个泛型参数
	    Type gType = tp[0];
		// Class类实现了Type接口    Class 类类型  强制转换类型
	    targetClazz = (Class)gType;
	    System.out.println("泛型实参的类型的实例为： " + targetClazz);
	    System.out.println("泛型实参的类型的名字为： " + targetClazz.getSimpleName());
	    // 生成表名
	    tableName = targetClazz.getSimpleName().toLowerCase();
	    System.out.println("生成的表名：" + tableName);
	    
	    ///////////////获取实体的属性名以及获取其实参//////////////////
	    Field[] declaredFields = targetClazz.getDeclaredFields();
	    // 构造  ?,?,?,?... 的字符串
	    String temp = "";
	    for(Field field : declaredFields)
	    {
	    	fields = fields + field.getName() + ",";
	    	temp+="?,";
	    }
		signs = temp.substring(0, temp.length()-1);
		fields = fields.substring(0, fields.length()-1);
		// Teacher : ?,?,?  id,name,salary
		System.out.println(signs+"  "+fields);
	    
	}

	
	/**
	 * 查询所有
	 * @return 返回所有的数据
	 */
	public List<T> findAll()
	{
		try 
		{
			String sql = "select * from " + tableName;
			List<T> list = (List<T>)qr.query(sql,new BeanListHandler(targetClazz));
			return list;
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据id查询数据
	 * @return 返回该id所对应的实体对象，否则返回null.
	 */
	public T findById(Object o)
	{
		T t = null;
		try 
		{
			String sql = "select * from "+tableName+" where id=?";
			t = (T) qr.query(sql, new BeanHandler(targetClazz), new Object[]{o});
			return t;
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 增加数据
	 * @return  成功，返回true,否则，返回false
	 */
	public boolean add(T t)
	{
		boolean flag = false;
		try 
		{
			String sql = "insert into "+tableName+"("+fields+") values("+signs+")";
			int count = qr.update(sql, params);
			if(count > 0)
			{
				flag = true;
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
		
		return flag;
	}
}
