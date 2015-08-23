package com.dantefung.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ChooseTag extends SimpleTagSupport {
	
	private boolean isDo;

	public boolean isDo() {
		return isDo;
	}

	public void setDo(boolean isDo) {
		this.isDo = isDo;
	}

	@Override
	public void doTag() throws JspException, IOException {
	    this.getJspBody().invoke(null);
	}
	
	
	
}
