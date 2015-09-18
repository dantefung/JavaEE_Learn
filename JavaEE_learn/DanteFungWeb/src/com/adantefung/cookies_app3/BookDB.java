package com.adantefung.cookies_app3;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 内存版的数据库（临时数据库）:模拟数据用的
 * @author Dante Fung
 *
 */
public class BookDB {
	
	private static Map<String, String> books = new LinkedHashMap<String,String>();

	static
	{
		books.put("1","JavaWeb开发");
		books.put("2","Java编程思想");
		books.put("3","设计模式");
		books.put("4","极限编程");
	}
	
	public Map<String, String> getBooks()
	{
		return books;
	}
}
