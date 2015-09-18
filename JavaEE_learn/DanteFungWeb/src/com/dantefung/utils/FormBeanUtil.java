package com.dantefung.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
//表单数据拷贝的工具类
public class FormBeanUtil {

	//把表单数据封装到Javabean对象中
	public static <T> T fillBean(HttpServletRequest request,Class<T> clazz) throws Exception{
		//1)创建对象
		T t = clazz.newInstance();
		//2)拷贝数据
		BeanUtils.populate(t, request.getParameterMap());
		//3)返回封装好的javabean对象
		return t;
	}
}
