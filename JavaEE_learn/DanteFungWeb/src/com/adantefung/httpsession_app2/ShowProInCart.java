package com.adantefung.httpsession_app2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowProInCart extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("<h3>您的购物车内有如下商品：</h3>");
		HttpSession session = request.getSession();
		Map<String, Book> map = (Map<String, Book>)session.getAttribute("map");
		for(Entry<String, Book>  en : map.entrySet())
		{
			System.out.println(">>>>>>>>en:" + en);
			if(en != null)
			{
				out.write("编号：  "+en.getKey()+"书名： "+en.getValue().getName()+" &nbsp&nbsp作者： "+en.getValue().getAuthor()+" &nbsp&nbsp价格： "+en.getValue().getPrice()+"&nbsp&nbsp<a href='javascript(0)'>立刻结账</a><br/>");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
