package com.adantefung.beanutils;

import java.util.Arrays;

public class User {
	private String userName;
	private String[] userPwd;
	public String getUserName() { //读属性：getxxx 例如 getUserName: 读取属性名称为：userName
		return userName;
	}
	public void setUserName(String userName) {//写属性：setxxx 写属性名称：userName
		this.userName = userName;
	}
	public String[] getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String[] userPwd) {
		this.userPwd = userPwd;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPwd="
				+ Arrays.toString(userPwd) + "]";
	}
	
	
	
	
	
	
}
