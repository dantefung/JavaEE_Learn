package com.dantefung.c3p0_dbutils_basedao2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.dantefung.utils.C3P0Utils;

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
public class BaseDaoImpl<T> implements BaseDao{
	
	private Class targetClazz;
	private String tableName;

	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	private String fields="";
	private String signs="";
	
	public BaseDaoImpl()
	{
	    ////////////////////获取表名////////////////////	
	
		// 约定：类名和数据库表名要一致 -- 这种写法 有待改进    需要弥补违反了约定的出现的问题：  技术： 反射注解
		
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
//	    for(Field field : declaredFields)
//	    {
//	    	fields = fields + field.getName() + ",";
//	    	temp+="?,";
//	    }
	    Connection conn = null;
	    try 
	    {
	    	conn = C3P0Utils.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from "+tableName);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			for(int i=1; i<=count; i++)
			{
				fields = fields + metaData.getColumnName(i) + ",";
				temp+="?,";
			}
			
			signs = temp.substring(0, temp.length()-1);
			fields = fields.substring(0, fields.length()-1);
		} 
	    catch (Exception e)
	    {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	    finally
	    {
	    	try 
	    	{
				if(conn != null)
				{
					conn.close();//  将连接放回连接池
				}
			}
	    	catch (Exception e) 
	    	{
//				e.printStackTrace();
				throw new RuntimeException(e);
			}
	    }
	    
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
			// ResultSetHandler 默认还是约定字段名和Bean的属性名要一致
			t = (T) qr.query(sql, new BeanHandler(targetClazz), new Object[]{o});
			return t;
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据name查询数据
	 * @return 返回该name所对应的实体对象，否则返回null.
	 */
	public T findByName(Object o)
	{
		T t = null;
		try 
		{
			String sql = "select * from "+tableName+" where name=?";
			// ResultSetHandler 默认还是约定字段名和Bean的属性名要一致
			t = (T) qr.query(sql, new BeanHandler(targetClazz), new Object[]{o});
			return t;
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<T> queryByName(Object o)
	{
		List<T> t = null;
		try 
		{
			String sql = "select * from "+tableName+" where name=?";
			// ResultSetHandler 默认还是约定字段名和Bean的属性名要一致
			t = (List<T>) qr.query(sql, new BeanListHandler(targetClazz), new Object[]{o});
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
	public boolean add(Object t)
	{
		boolean flag = false;
		Connection conn = null;
		try 
		{
			String sql = "insert into "+tableName+"("+fields+") values("+signs+")";
			conn = C3P0Utils.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from "+tableName);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			
			int colunmCount = metaData.getColumnCount();
			Object[] params=new Object[colunmCount];
			/*由于表中的字段与类中声明的成员变量不一致，所以以数据库的字段为准*/
/*			Field[] fields = t.getClass().getDeclaredFields();
			for(int i=0; i<fields.length; i++)
			{
				String name = fields[i].getName();
				String word = name.charAt(0)+"";
//				Class<?> type = fields[i].getType();
				// get方法不需要传参
//				Method method = t.getClass().getDeclaredMethod("get"+name.replace(word, word.toUpperCase()), type);
				Method method = t.getClass().getDeclaredMethod("get"+name.replace(word, word.toUpperCase()), null);
				params[i]=method.invoke(t, null);
			}*/
			
			for(int i=1; i<=colunmCount; i++)
			{
				String name = metaData.getColumnName(i);
				String word = name.charAt(0)+"";
				String subString = name.substring(1);
				System.out.println(word+"=="+subString);
				String methodName = "get"+word.toUpperCase()+subString;
				System.out.println("methodName ---- " + methodName);
				Method method = t.getClass().getDeclaredMethod(methodName, null);
				params[i-1]=method.invoke(t, null);
			}
			
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
		finally
	    {
	    	try 
	    	{
				if(conn != null)
				{
					conn.close();//  将连接放回连接池
				}
			}
	    	catch (Exception e) 
	    	{
//				e.printStackTrace();
				throw new RuntimeException(e);
			}
	    }
		
		
		return flag;
	}


	@Override
	public boolean delById(Object o) {
		boolean flag = false;
		try 
		{
			String sql = "delete from " + tableName + " where id=?";
			int count = qr.update(sql,new Object[]{o});
			if(count>1)
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
