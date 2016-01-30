/**
 * Project Name:DanteFungWeb
 * File Name:StudentAction.java
 * Package Name:com.dantefung.springmvc.annotation.controller
 * Date:2015-11-29上午10:16:29
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.springmvc.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:StudentAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-29 上午10:16:29 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller//用来声明是处理器
@RequestMapping("/student2")
public class StudentAction
{
	public StudentAction()
	{
		System.out.println("StudentAction.StudentAction()");
	}
	
	// 访问可用student/save.action,save后边的action是根据web.xml来配置的
	
	// 如果要添加其它的数据到最后跳转过去的页面，可以在方法中添加ModelMap的参数，例如：public String save(Student student, Modelmap map){...}
	// 通过map再存放其它数据
	@RequestMapping(value="/save")
	public ModelAndView save(Student student)// 局部变量，参数列表入参的方式注入
	{
		System.out.println("save方法注入" + student);
		System.out.println("调用业务逻辑层处理逻辑。。。");
		
		// 修改学生的姓名，跳转到下一页面时能否显示修改后的名字
		student.setStuName("rename");
		
		return new ModelAndView("forward:/springmvc/Annotation/main.jsp");
	}
	
	// 同一个action中可以定义多个方法，返回值类型也可以是String
	@RequestMapping(value="/update")
	public String update(Student student, ModelMap paramMap)
	{
		System.out.println("update方法已经注入student对象： " + student);
		System.out.println("----调用业务逻辑层的方法处理逻辑");
		paramMap.put("other","testOtherValue");
		// 直接使用字符串，返回视图，进行结果展示等
		return "forward:/springmvc/Annotation/main.jsp";
	}
	
	
}

