package com.adantefung.httpsession_app2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 简易版购物车,由于使用了map这个数据结构，
 * 因此，无法重复添加同样的商品.(日后再做改进)
 * @author Dante Fung
 *
 */
public class AddCart extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("bookId");
		HttpSession session = request.getSession();
		Map<String, Book> map = (Map<String, Book>)session.getAttribute("map");
		Book book = (Book) session.getAttribute(id);
		System.out.println("----->book:"+book);
		if(map != null)
		{
			map.put(id, book);
			session.setAttribute("map", map);
		}
		else
		{
			map = new HashMap();
			map.put(id, book);
			session.setAttribute("map", map);
		}
		
		//手动更改JSESSIONID（cookie）的存活时间
		out.write("加入购物车成功!!！！<br/>");
		out.write("<a href='"+request.getContextPath()+"/BookShopUI'>返回上一页</a>");
		
		Cookie c1 = new Cookie("JSESSIONID",session.getId());
		c1.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(c1);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
