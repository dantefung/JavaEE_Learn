package com.adantefung.cookies_app3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 浏览历史记录
 * @author Dante Fung
 *
 */
public class HistoryUIDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解决 server -> browser 写数据  乱码问题
		response.setContentType("text/html;charset=utf-8");
		// 构造出商品列表
		PrintWriter out = response.getWriter();
		out.write("<h3>客官，本店有以下书籍:</h3>");
		BookDB db = new BookDB();
		Map<String, String> books = db.getBooks();
		for(Entry en : books.entrySet())
		{
			// 当用户点击时，顺便带上这本书的id号过去HistoryDemo里面进行逻辑处理
			out.write("<a href='"+request.getContextPath()+"/HistoryDemo?bookId="+en.getKey()+"'>"+en.getKey() + ": " + en.getValue() + "</a><br/>");
		}
		
		out.write("<h3>您浏览过的书籍：</h3>");
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if("proHist".equals(c.getName()))
				{
					System.out.println(">>>>>>>>>>>>>>>>>"+c.getValue());
					String[] ids = c.getValue().split("\\-");
					for(String id : ids)
					{
						out.write("<a href='"+request.getContextPath()+"/HistoryDemo?bookId="+id+"'>" + id + ": " + books.get(id) + "</a><br/>");
					}
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
