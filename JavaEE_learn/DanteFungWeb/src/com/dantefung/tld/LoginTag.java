package com.dantefung.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoginTag extends SimpleTagSupport {
	// 表单项的名字
	private String username;
	private String password;
	 
	
	
	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public void doTag() throws JspException, IOException {
		// 获取页面的上下文
		PageContext pageContext = (PageContext)getJspContext();
//		getJspBody().getJspContext();
//		StringWriter sw = new StringWriter(); 
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("用户名：<input type=\"text\" name=\""+username+"\"/><br/>")
		   .append("密码：<input type=\"password\" name=\""+password+"\"/>");
		System.out.println("调用doTag()");
		out.write(sb.toString());
		//out.write("Helloo!!");
	}
	
	
}
