package com.dantefung.i18n;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatTest {

	/**
	 * 货币的格式    百分比格式
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
        
		int price = 18;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(nf.format(price));
	
	    String a = "￥18";
	    nf = NumberFormat.getCurrencyInstance(Locale.JAPAN);
	    System.out.println(nf.parse(a).doubleValue());
	    
	    double d = 0.1;
	    nf = NumberFormat.getPercentInstance();
	    System.out.println(nf.format(d));
	}

}
