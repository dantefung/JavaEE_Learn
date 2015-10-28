package com.dantefung.imageCode_struts;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class MyBytesResult implements Result {

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		
		ImageCodeAction action = (ImageCodeAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType(action.getMyContentType());
		response.setContentLength(action.getMyContentLength());

		response.getOutputStream().write(action.getMyImageInBytes());
		response.getOutputStream().flush();
	}

}
