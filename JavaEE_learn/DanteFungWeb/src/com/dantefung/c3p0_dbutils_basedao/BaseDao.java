package com.dantefung.c3p0_dbutils_basedao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dantefung.utils.C3P0Util;
//反射泛型
//基础dao（通用dao）： 把一些通用的CRUD方法放在这里
public abstract class BaseDao<T> {
	QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	
	private String tableName;//表名  students    teachers
	private Class targetClass;//当前业务的对象类型  Students.class Teachers.class
	// 约定：表名和类名保存一致
	
	public BaseDao(){
		//得到业务dao传过来的对象类型
		
		//1. 得到当前业务dao的类型
		Class daoClazz = this.getClass(); // StudentDao
		//System.out.println(daoClazz.getName());
		
		//2.得到业务dao上的泛型父类
		//daoClazz.getSuperclass()
		Type type = daoClazz.getGenericSuperclass(); // BaseDao<Teachers>
		
		//3.强制转换成对应的参数化类型
		ParameterizedType pt = (ParameterizedType)type;  // BaseDao<Teachers>
		
		//4.得到具体的泛型类型的数组
		Type[] types = pt.getActualTypeArguments(); // Teachers
		
		//5.取第一个具体的类型
		targetClass = (Class)types[0];  // Teachers
		
		//System.out.println(targetClass.getName());
		
		//tableName = targetClass.getSimpleName().toLowerCase();
		
		//1)得到当前类上的注解信息
		Table objTable = (Table)targetClass.getAnnotation(Table.class);
		//2)得到注解中的属性值
		tableName = objTable.name();
		System.out.println(tableName);
	}
	
	
	public List<T> findAll(){
		try {
			return (List<T>)qr.query("select * from "+tableName+"", new MyBeanListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//自定义ResultSetHandler
	class MyBeanListHandler implements ResultSetHandler{

		@Override
		public Object handle(ResultSet rs) throws SQLException {
			try {
				List<T> list = new ArrayList<T>();
				//得到列数量
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				//遍历
				while(rs.next()){
					//创建对象
					T obj = (T)targetClass.newInstance();  // Students Teachers
					
					//把表的数据封装到对象中
					for(int i=0;i<columnCount;i++){
						//得到列名
						String columName = rsmd.getColumnName(i+1); // t_id
						//得到列值
						Object columnValue = rs.getObject(columName);
						
						//得到对象的属性？？？
						//得到所有字段列表
						Field[] fields = targetClass.getDeclaredFields();
						//遍历：找出每个属性上的注解，取出表的字段名，和当前的columnName进行比较，如果相等
						for(Field field:fields){
							//得到属性上面的注解
							Column column = (Column)field.getAnnotation(Column.class);
							String dbColumnName = column.name();
							if(columName.equals(dbColumnName)){
								//相等: 给对象的属性赋值
								field.setAccessible(true);
								//给属性赋值
								field.set(obj, columnValue);
								break;
							}
						}	
					}
					list.add(obj);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
	}
	
	
	
	public T findById(int id){
		try {
			return (T)qr.query("select * from "+tableName+" where id=?", new BeanHandler(targetClass),new Object[]{id});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//约定:表的字段名称和对象的属性名称保持一致
	public void save(T t){
		/*try {
			String sql = "insert into "+tableName+"(.....) values(?...)";
			//return qr.query(sql, new BeanHandler(targetClass),new Object[]{id});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}*/
	}
}
