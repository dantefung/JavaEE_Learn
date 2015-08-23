package com.dantefung.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceBundle bundler = ResourceBundle.getBundle("com.dantefung.resource.MessageResource",Locale.CANADA);
	    String username = bundler.getString("username");
	    String password = bundler.getString("password");
	    
	    System.out.println(username);
	    System.out.println(password);
	}

}
