package com.dantefung.tools.commons;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
/**
 * 小小工具
 * @author lenovo
 * 用到反射技术
 *
 */
public class CommonUtils {
	private static String hex[] = {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	//生成32位不重复字符串，可用于生成验证码
	public static String uuid(){
		UUID uuid= UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
	/**
	 * 将map封装到指定的javabean中
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T>T toBean(Map<String,Object> map,Class<T> clazz){
		/*
		 * 通过clazz创建实例
		 * 用将map的数据封装到bean中
		 */
		try {
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);//注册转换器
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
	
}
