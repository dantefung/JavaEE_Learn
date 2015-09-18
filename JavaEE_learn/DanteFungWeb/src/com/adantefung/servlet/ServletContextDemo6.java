package com.adantefung.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 一个ServletContext对象，域对象（用于数据存取,内部用Map实现.）
 * 对应一个Web应用.
 * 演示ServletContext对象的功能.
 * @author Dante Fung
 *
 */
public class ServletContextDemo6 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 功能一：存取数据-->servlet之间共享数据.(jsp的本质也是servlet.)
//		ServletContext context = getServletConfig().getServletContext();
		ServletContext context = getServletContext();
		context.setAttribute("p","ppp");
		System.out.println(context);
		
		// 功能二：获取全局参数.在web.xml中配置全局的参数,可以由这个对象获取.
		ServletContext context2 = getServletContext();
		String encoding = context2.getInitParameter("encoding");
		System.out.println(encoding);
		response.getWriter().write(encoding+ "\r\n");
		
		Enumeration<String> enums = context.getInitParameterNames();
		while(enums.hasMoreElements())
		{
			String paramName = enums.nextElement();
			String paramValue = context.getInitParameter(paramName);
			System.out.println(paramName +"="+paramValue);
			response.getWriter().write(paramName+"="+paramValue +"\r\n");
		}
		
		// 功能三：转发
		ServletContext context3 = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/demo10");
		// 为了后面的代码能演示暂时屏蔽，如果要看此功能，去掉注释即可.
//		rd.forward(request, response);
		
		// 功能四：读取资源文件.
		// 得到一个文件的绝对路径, /(斜杠)指向部署在服务器上当前web应用的根目录
		/*--------------------------方式一--------------------------------*/
		String path = getServletContext().getRealPath("/WEB-INF/classes/com/adantefung/servlet/c.properties");
		
		FileInputStream fis = new FileInputStream(new File(path));
		Properties prop = new Properties();
		prop.load(fis);
		response.getWriter().write(prop.getProperty("name"));
		response.getWriter().write(prop.getProperty("password")+"\r\n");
		
		/*-------------------------方式二------------------------------*/
		//读取一个文件，返回流的形式
		InputStream in = getServletContext().getResourceAsStream("/a.properties");

		Properties prop1 = new Properties();
		prop1.load(in);
		response.getWriter().write(prop1.getProperty("name"));
		response.getWriter().write(prop1.getProperty("password")+"\r\n");
		
		// 不建议用传统的相对路径的方式读取文件,因为现在是web开发：区别：工作空间  web服务器
		// 工作空间的项目部署到web服务器上：
		//  1.将src的java文件(其中jsp->servlet->编译)编译成.class文件后放在web应用的WEB-INF/classes
		//  文件夹中
		//  2.然后将整个WebRoot下的文件拷贝到web服务器的webapps目录下.web应用的名字为工程名.
	}
	
	//使用类路径的方式：（局限，只能读classes目录下，不能读classes以外的文件）读取b.properties  读取c.properties
	public void test5(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Class clazz = ServletContextDemo6.class;
		// / （斜杠）： 代表classes目录下
		//InputStream in = clazz.getResourceAsStream("/b.properties");
		InputStream in = clazz.getResourceAsStream("/com/adantefung/servlet/c.properties");
		
		Properties prop = new Properties();
		prop.load(in);
		response.getWriter().write(prop.getProperty("name"));
		response.getWriter().write(prop.getProperty("password"));
	}
	
	//相对路径：不建议！！！
	public void test1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File file = new File("./a.properties"); //相对路径方式
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		response.getWriter().write(prop.getProperty("name"));
		response.getWriter().write(prop.getProperty("password"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
