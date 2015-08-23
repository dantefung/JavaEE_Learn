package com.dantefung.tld;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForeachTag extends SimpleTagSupport {
	
	private String var;
	
	private Object items;
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setItems(Object items) {
		this.items = items;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		List list = (List) items;
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			
			
			// 把List中迭代出的东西存放在Context域中
			this.getJspContext().setAttribute(var, value);
			// 通知标签体执行
			this.getJspBody().invoke(null);
		}
	}
}
