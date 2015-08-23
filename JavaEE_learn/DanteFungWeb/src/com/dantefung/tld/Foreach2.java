package com.dantefung.tld;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Foreach2 extends SimpleTagSupport {
	
	private Object items;
	
	private String var;

	private Collection collection;
	
	public void setItems(Object items) {
		this.items = items;
		
		if(items == null)
		{
			collection = null;
		}
		
		if(items instanceof Collection)
		{
			collection = (Collection) items;
		}
		
		if(items instanceof Map)
		{
			Map map = (Map)items;
			collection = map.entrySet();
		}
		
		if(items.getClass().isArray())
		{
			this.collection = new ArrayList();
			int len = Array.getLength(items);
			for(int i=0; i<len; i++)
			{
				this.collection.add(Array.get(items, i));
			}
		}
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		if(collection == null) return;
		Iterator it = collection.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			this.getJspContext().setAttribute(var, value);
			this.getJspBody().invoke(null);
		}
	}
}
