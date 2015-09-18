package com.adantefung.httpsession_app2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 简易版购物车
 * @author Dante Fung
 *
 */
public class BookShopUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("<h3>欢迎来到计算机科学书店：</h3><br/>");
		HttpSession session = request.getSession();
		// 模拟数据
		for(int i=0; i<10; i++)
		{
			Book book = new Book("java编程思想"+i,"30元","外国人");
			session.setAttribute(""+i, book);
			out.write("书名： "+book.getName()+" &nbsp&nbsp作者： "+book.getAuthor()+" &nbsp&nbsp价格： "+book.getPrice()+"&nbsp&nbsp<a href='"+request.getContextPath()+"/AddCart?bookId="+i+"'>加入购物车</a><br/>");
		}
		
		out.write("<br/><br/><a href='"+request.getContextPath()+"/ShowProInCart'>查看购物车</a>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
