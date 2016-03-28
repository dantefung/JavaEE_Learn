package com.dantefung.mail;

import sun.misc.BASE64Encoder;

public class Demo {
	public static void main(String[] args) {
		
		BASE64Encoder be = new BASE64Encoder();
		
		String username = be.encode("ericxu_12345@126.com".getBytes()); //进行Base64编码
		String password = be.encode("eric12345".getBytes()); //进行Base64编码
		System.out.println(username);
		System.out.println(password);
		
	}
}
