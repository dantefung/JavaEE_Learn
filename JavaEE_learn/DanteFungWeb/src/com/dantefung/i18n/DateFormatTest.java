package com.dantefung.i18n;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateFormatTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
        
		Date date = new Date();
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);
		String str = df.format(date);
		System.out.println(str);
		
		df = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
		System.out.println(df.format(date));
		
		df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.CHINA);
		System.out.println(df.format(date));
		
		// get the default dateformat
		df = DateFormat.getDateInstance();
		System.out.println(df.format(date));
		
		// 使用dateformat反向把一个字符串格式化成一个日期对象
		String str2 = "2015年8月2日 星期日 下午4:23";
		
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.CHINA);
		Date date2 = df2.parse(str2);
		System.out.println(date2);
		
	}

}
