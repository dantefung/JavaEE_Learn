package com.dantefung.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class WebUtil {
	private static SimpleDateFormat sdf = null;
	
	// 生成UUID,作为大多数实体的id
	public static  String genUUID()
	{
		return UUID.randomUUID().toString().replace("-","");
	}
	
	// 生成一定格式的日期字符串
	public static String genMkDate(String pattern)
	{
		if(sdf==null)
		{
			sdf = new SimpleDateFormat(pattern);
		}
		
		return sdf.format(new Date());
	}
	
	// 生成订单编号  User.hashCode+ddHHmmssSSS
	public static String genOrderNo(Object obj)
	{
		return obj.hashCode() + new SimpleDateFormat("ddHHmmssSSS").format(new Date());
	}
}
