package com.dantefung.online;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

public class OnlineBean implements Serializable{
       private String userName;
	   private String remoteHost;
	   private String loginTime;
	   private String lastTime;
	   private HttpSession session;
	   
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	@Override
	public String toString() {
		return "OnlineBean [userName=" + userName + ", remoteHost="
				+ remoteHost + ", loginTime=" + loginTime + ", lastTime="
				+ lastTime + "]";
	}
	   
	
	
	
	
	
	
	
}
