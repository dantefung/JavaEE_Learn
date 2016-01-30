package com.dantefung.struts_baseaction;

import java.util.Map;

import javax.faces.application.Application;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 所有的Action的基类
 * 
 * 1.包含数据处理所需的所有域对象.   -- 解耦的方式
 * 2.业务逻辑层的所有对象.
 * @author Dante Fung
 *
 */
public class BaseAction1 extends ActionSupport implements RequestAware, SessionAware,ApplicationAware{

	protected Map<String, Object> application;
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
	    this.session = session;	
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	
	

}
