package com.dantefung.tools.filter;

/**
 * 装饰者模式
 */
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 全栈编码过滤器
 * 装饰者模式
 * @author lenovo
 *
 */
public class CharSetRequest extends HttpServletRequestWrapper {

	public CharSetRequest(HttpServletRequest request) {
		super(request);//调用父类构造方法，给父类传递底层对象
	}
	/*
	 * 增强getparameter方法
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);   //是super，因为是调用父类的方法得到值
		if(getMethod().equalsIgnoreCase("GET")){
		try {
				value=new String(value.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		}
		return value;  //返回编码后的值
	}
}
