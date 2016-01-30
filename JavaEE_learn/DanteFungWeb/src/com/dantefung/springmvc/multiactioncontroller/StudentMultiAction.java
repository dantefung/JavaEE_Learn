/**
 * Project Name:DanteFungWeb
 * File Name:StudentMultiAction.java
 * Package Name:com.dantefung.springmvc.MultiActionController
 * Date:2015-11-28下午8:25:14
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.springmvc.multiactioncontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * ClassName:StudentMultiAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-28 下午8:25:14 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StudentMultiAction extends MultiActionController
{
	//定义方法时，参数规则：(HttpServletRequest request, HttpServletResponse response, [,HttpSession session] [,MyObject]);
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response,Student student){
		System.out.println("add.student:"+student);
		student.setStuName("rename");
		return new ModelAndView("MultiActionController/main","student",student);
	}
		
	//定义方法时，参数规则：(HttpServletRequest request, HttpServletResponse response, [,HttpSession session] [,MyObject]);
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,Student student){
		System.out.println("update.student:"+student);
		student.setStuName("rename");
		return new ModelAndView("MultiActionController/main","student",student);
	}
	
	//定义方法时，参数规则：(HttpServletRequest request, HttpServletResponse response, [,HttpSession session] [,MyObject]);
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response,Student student){
		System.out.println("list.student:"+student);
		student.setStuName("updateName");
		return new ModelAndView("MultiActionController/main");
	}
	
}

