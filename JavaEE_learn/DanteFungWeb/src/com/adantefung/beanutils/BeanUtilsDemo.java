package com.adantefung.beanutils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.adantefung.example.Student;

public class BeanUtilsDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=========BeanUtilsDemo========");
		Student stu = new Student();
		System.out.println("封装前： "+stu);
		request.setCharacterEncoding("UTF-8");
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(stu, map);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("封装后： "+stu);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
