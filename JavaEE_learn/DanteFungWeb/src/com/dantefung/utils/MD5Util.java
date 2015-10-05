package com.dantefung.utils;

import java.security.MessageDigest;

//MD5工具类
public class MD5Util {

	/**
	 * 密码转换的方法
	 * @param password 原始密码
	 * @return md5加密后的密码
	 */
	public static String md5(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			//转换
			byte[] result = md.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			for(byte b: result){ // 
//				System.out.print(b+" ");
				sb.append(byteToHexString(b));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	// -127 -36 -101 -37 82 -48 77 -62 0 54 -37 -40 49 62 -48 85   16位（10进制）
	//  81dc9bdb52d04dc20036dbd8313e                       d0 55     32位（16进制）
	
	/**
	 * 把一个指定的10进制字节数字，转为2位的16进制的字符串
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte b){
		
		int target = b;
		//如果是负数则+256，再计算16进制的数字
		if(target<0){
			target=target+256;
		}
		
		int first = target/16;
		int second = target%16;
		
		return hexString[first]+hexString[second];
	}
	
	private static String[] hexString = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	
	
	
	public static void main(String[] args) {
		System.out.println(md5("1234")); // 81dc9bdb52d04dc20036dbd8313ed055   81dc9bdb52d04dc20036dbd8313ed055
	}
}
