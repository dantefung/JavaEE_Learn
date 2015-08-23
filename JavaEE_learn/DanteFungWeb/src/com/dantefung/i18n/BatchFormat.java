package com.dantefung.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

public class BatchFormat {

	/**
	 * 批量格式化
	 * @param args
	 */
	public static void main(String[] args) {

		String pattern = "On {0}, a hurricance destroyed {1} houses and caused {2} of damage.";
        
        MessageFormat format = new MessageFormat(pattern, Locale.CHINA);
        Object params[] = {new Date(), 99, 1000000000};
        String message = format.format(params);
        System.out.println(message);
        
        String pattern1 = "At {0, time, short} on {0, date}, a destroyed {1} houses and caused {2, number, currency} of damage.";
		MessageFormat format1 = new MessageFormat(pattern,Locale.US);
		Object params1[] = {new Date(),99,1000000000};
		String message1 = format.format(params);
		System.out.println(message);
	}

}
