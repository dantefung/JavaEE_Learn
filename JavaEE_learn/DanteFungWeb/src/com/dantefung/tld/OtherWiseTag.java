package com.dantefung.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OtherWiseTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		ChooseTag parent = (ChooseTag)this.getParent();
		if(!parent.isDo())
		{
			this.getJspBody().invoke(null);
			parent.setDo(true);
		}
	}
}
