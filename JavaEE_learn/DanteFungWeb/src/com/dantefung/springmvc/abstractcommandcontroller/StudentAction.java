/**
 * Project Name:DanteFungWeb
 * File Name:StudentAction.java
 * Package Name:com.dantefung.springmvc.AbstractCommandController
 * Date:2015-11-28下午7:56:24
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.springmvc.abstractcommandcontroller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

/**
 * ClassName:StudentAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-28 下午7:56:24 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StudentAction extends AbstractCommandController
{

	public StudentAction()
	{
		// 配置Student对象可以被注入
		setCommandClass(Student.class);
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object obj, BindException exception)
			throws Exception
	{
		System.out.println("---接收数据--");
		// 方式一（传统的接收参数的方式）：一个属性一个属性的接收
		String stuName = request.getParameter("stuName");
		String stuPwd = request.getParameter("stuPwd");
		System.out.println("stuName: " + stuName + " stuPwd: " + stuPwd);
		// 方式二：通过Objec obj 注入
		Student stu = (Student)obj;
		System.out.println(stu);
		
		// 封装视图数据有多种方式
		// 方式一：放到域对象中
		// 方式二：放到普通map中（作用域：request）
		// 方式三：直接将参数作为ModelAndView的入参
		// 详见 FirstAction
		
		request.setAttribute("stu", stu);
		request.getSession().setAttribute("stu", stu);
		
		// 默认是转发的方式
		return new ModelAndView("AbstractCommandController/main");
	}

}

