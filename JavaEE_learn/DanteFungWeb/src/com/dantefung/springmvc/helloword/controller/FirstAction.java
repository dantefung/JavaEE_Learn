/**
 * Project Name:DanteFungWeb
 * File Name:FirstAction.java
 * Package Name:com.dantefung.springmvc.helloword.controller
 * Date:2015-11-28下午6:54:11
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.springmvc.helloword.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * ClassName:FirstAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-28 下午6:54:11 <br/>
 * Description：
 * 单实例多线程
 * 基于方法的参数列表的注入
 * Aop
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FirstAction implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("传入的数据为：");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		System.out.println("userName:" + userName + "userPwd:" + userPwd);
		System.out.println("这里可以调用业务层的方法...");
		
		/**
		 * 封装视图数据的三种方式 
		 */
		// 方式一：request作用域
//		return FirstWay(request, userName, userPwd);
		// 方式二：request作用域
//		return SecondWay(request, userName, userPwd);
		// 方式三：request作用域
//		return new ModelAndView("/springmvc/HelloWorld.jsp","userName",userName);
		
		/**
		 * 1.视图处理器配置前后缀
		 * 2.重定向
		 * 3.转发
		 * 
		 */
//		return noFix(request, userName, userPwd);
		return redirect(request, userName, userPwd);
//		return forward(request, userName, userPwd);
	}

	private ModelAndView FirstWay(HttpServletRequest request, String userName,
			String userPwd)
	{
		// 方式一：封装数据可以使用request域
		request.setAttribute("userName", userName);
		request.setAttribute("userPwd", userPwd);
		// 默认转发
		return new ModelAndView("/springmvc/HelloWorld.jsp");
	}
	
	private ModelAndView SecondWay(HttpServletRequest request, String userName,
			String userPwd)
	{
		// 方式二:用map封装数据
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("userPwd", userPwd);
		// 默认转发
		return new ModelAndView("/springmvc/HelloWorld.jsp",map);
	}
	
	private ModelAndView noFix(HttpServletRequest request, String userName,
			String userPwd)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("userPwd", userPwd);
		// 无前后缀
		return new ModelAndView("HelloWorld",map);
	}
	
	private ModelAndView forward(HttpServletRequest request, String userName,
			String userPwd)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("userPwd", userPwd);
		// 转发 :forward 一定要写完整的路径  配置文件的视图处理器的配置不起作用
		return new ModelAndView("forward:/springmvc/HelloWorld.jsp",map);
	}
	
	private ModelAndView redirect(HttpServletRequest request, String userName,
			String userPwd)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("userPwd", userPwd);
		// 重定向：一定要写完整的路径名，配置文件的试图处理器的配置不起作用
		return new ModelAndView("redirect:/springmvc/HelloWorld.jsp",map);
	}

}

