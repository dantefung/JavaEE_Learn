package com.dantefung.struts_baseaction;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
// 耦合度较高的方式
public class BaseAction2 extends ActionSupport{
	
	protected HttpServletRequest request = ServletActionContext.getRequest();
	
	protected HttpSession session = request.getSession();
	
	protected ServletContext servletContext = ServletActionContext.getServletContext();
	
	protected HttpServletResponse response = ServletActionContext.getResponse();
			
			
	
	
}
