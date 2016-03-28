package com.dantefung.tools.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一个类有多个请求方法，每个类的请求方法的原型跟servies相同 原型=返回值+方法名+参数列表
 * 
 * @author Administrator
 * 
 */
public class BaseServlet extends HttpServlet {
	/**
	 * 重写servies方法
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 处理响应编码
		/*
		 * 获取方法名称，即用户要调用的方法 将方法名称转化成method的对象,反射，好处：不用做很多个if...else循环
		 * 调用反射invoke（）
		 */
		String methodName = request.getParameter("method");
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("你要调用的方法：" + methodName + "不存在", e);
		}
		try {
			String result = (String) method.invoke(this, request, response);
			if (result != null && !result.trim().isEmpty()) {
				/*
				 * 现将字符串分割，冒号前的r是重定向；f是转发
				 */
				int index = result.indexOf(":");// 获取第一个冒号的位置
				if (index == -1) {// 如果没有第一个冒号，转发
					request.getRequestDispatcher(result).forward(request,
							response);
				} else {
					String start = result.substring(0, index);
					String path = result.substring(index + 1);
					if (start.equals("f")) {
						request.getRequestDispatcher(path).forward(request,
								response);
					} else if (start.equals("r")) {
						response.sendRedirect(path);// ?request.getContextPath()显示不了页面
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
