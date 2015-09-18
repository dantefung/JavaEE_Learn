package com.dantefung.test;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

public class TestMethod {

	@Test
	public void test() throws Exception {
		String name = URLEncoder.encode("张三","UTF-8");
		System.out.println(name);
		//%E5%BC%A0 %E4%B8%89
		//%E5%BC%A0 %E4%B8%89
		String result = URLDecoder.decode("%E5%BC%A0%E4%B8%89", "UTF-8");
		System.out.println("result:" + result);
	}
	
	@Test
	public void testRandom()
	{
		Random rd = new Random();
		for(int i=0; i<10; i++)
		{
			System.out.print(rd.nextInt(31) + "  ");
		}
	}
	
	@Test
	public void testStringBuffer()
	{
		String username = "username";
		String password = "password";
		StringBuffer sb = new StringBuffer();
		sb.append("<input type=\"text\" name=\""+username+"\"/><br/>")
		   .append("<input type=\"password\" name=\""+password+"\"/>");
		String result = sb.toString();
		System.out.println(result);
	}
	
	@Test
	public void testVarIsChangeInFor()
	{
		// 返回true 说明不会修改到当前  temp=i+1，不会修改i的值.
		for(int i=0; i<10; i++)
		{
			int temp = i+1;
			System.out.println(temp==i+1);
		}
	}
	
	@Test
	public void deleteFolder()
	{
		File file = new File("c:\\mydir");
		System.out.println(file.delete());
	}
	
	@Test
	public void testMap()
	{
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("Info", "1");
		map.put("Info", "2");
		map.put("Info", "3");
		for(Map.Entry me : map.entrySet())
		{
			System.out.println(me.getKey() + "=" + me.getValue());
		}
		
		// result:Info=3  说明map不能插入相同的key
	}
	
	@Test
	public void testFile()
	{  	
		File file = new File("c:/Test/uplodTemp");
		System.out.println(file);
		System.out.println(file.exists());
	}
	
	@Test
	public void testSimpleDateFormat()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(new Date());
		System.out.println(date);
	}
	
}
