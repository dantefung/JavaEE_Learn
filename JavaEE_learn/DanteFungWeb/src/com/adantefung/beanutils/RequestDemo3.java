package com.adantefung.beanutils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
//演示如何获取用户提交的参数（表单数据）
public class RequestDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test1(request);
		
		//test2(request);
		
		//test3(request);
		
		//test4(request);
		
		//test5(request);
		
		//精华：约定大于配置（灵活）
		//约定： 表单的参数名称一定要和封装的对象的属性（getter和setter）一致！！！！
		//test6(request);
		
		//终极版：实际开发中使用这种方法封装对象，BeanUtils工具
		Map map = request.getParameterMap();
		User user = new User();
		System.out.println("封装前： "+user);
		try {
			BeanUtils.populate(user, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("封装后： "+user);
	}

	//使用内省来封装对象
	private void test6(HttpServletRequest request) {
		Map<String,String[]> map = request.getParameterMap();
		
		User user = new User();
		System.out.println("封装前： "+user);
		for(Entry<String,String[]> entry: map.entrySet()){
			String paramName = entry.getKey();//参数名称 （就是对象的setter属性名称）
			String[] paramValue = entry.getValue();//参数值  userPwd
			
			//内省（反射的简化版）（Method 方法对象   调用方法   invoke）
			try {
				PropertyDescriptor pd = new PropertyDescriptor(paramName, User.class);
				Method m = pd.getWriteMethod(); // setUserName  setUserPwd
				//调用方法  参数一：对象  参数二：实际参数  （相对于给对象赋值）
				if(paramValue.length>1){
					//2个以上
					m.invoke(user,(Object)paramValue);//作为一个数组对象存放setter属性
				}else{
					//1个
					m.invoke(user, paramValue);//数组中的第一个元素存入setter属性
				}
				//pd.getReadMethod();// getUserName getUserPwd
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("封装后： "+user);
	}

	//方式一：参考封装对象
	private void test5(HttpServletRequest request) {
		//获取参数，封装对象
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		User user = new User();
		
		System.out.println("封装前"+user);
		
		user.setUserName(userName);
		//user.setUserPwd(userPwd);
		
		System.out.println("封装后"+user);
	}

	//使用Map形式遍历参数
	private void test4(HttpServletRequest request) {
		Map<String,String[]> map = request.getParameterMap();
		for(Entry<String,String[]> entry: map.entrySet()){
			String paramName = entry.getKey();//获取参数名称(String)
			String[] paramValue = entry.getValue();//获取参数值（String[]）
			System.out.println(paramName+"="+Arrays.asList(paramValue));
		}
	}


	//遍历所有参数名称
	private void test3(HttpServletRequest request) {
		//遍历所有的参数
		Enumeration<String> enums = request.getParameterNames();//得到所有参数的名称
		while(enums.hasMoreElements()){
			String paramName = enums.nextElement();
			String paramValue = request.getParameter(paramName);
			System.out.println(paramName+"="+paramValue);
		}
	}
	
	
	
	//获取多个值的参数(获取的值都是String类型)
	private void test2(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String[] userPwd = request.getParameterValues("userPwd");//获取多个值的参数
		System.out.println(userName);
		System.out.println(Arrays.asList(userPwd));
	}

	//获取一个值的参数(获取的值都是String类型)
	private void test1(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		System.out.println(userName);
		System.out.println(userPwd);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
