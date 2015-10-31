package com.dantefung.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CityServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		String dataType = request.getParameter("dataType");
		String country = "";
		if( "GET".equals(method) )
		{
			 country = new String(request.getParameter("country").getBytes("ISO-8859-1"),"UTF-8");
		}
		else
		{
			 country = request.getParameter("country");
		}
		
		StringBuffer sb = new StringBuffer();
		if ("json".equals(dataType)) {
			sb.append("{\"cities\":[");
			if ("中国".equals(country)) {
				sb.append("{\"city\":\"北京\"},{\"city\":\"上海\"},{\"city\":\"广州\"},{\"city\":\"深圳\"}");
			} else// 美国
			{
				sb.append("{\"city\":\"华盛顿特区\"},{\"city\":\"纽约\"},{\"city\":\"洛杉矶\"},{\"city\":\"西雅图\"}");
			}
			sb.append("]}");
			response.setContentType("text/html;charset=utf8");
		}
		else// xml
		{
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<root>");
			if ("中国".equals(country)) {
				sb.append("<city>北京</city><city>上海</city><city>广州</city><city>深圳</city>");
			} else// 美国
			{
				sb.append("<city>华盛顿特区</city><city>纽约</city><city>洛杉矶</city><city>西雅图</city>");
			}
			sb.append("</root>");
			response.setContentType("text/xml;charset=utf-8");
		}
		response.getWriter().write(sb.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
